package com.muhuang.salecrawler.item;

import com.muhuang.salecrawler.sale.Sale;
import com.muhuang.salecrawler.sale.SaleRepository;
import com.muhuang.salecrawler.sale.SaleService;
import com.muhuang.salecrawler.shop.Shop;
import com.muhuang.salecrawler.shop.ShopRepository;
import com.muhuang.salecrawler.taobao.TaobaoHttpClient;
import com.muhuang.salecrawler.taobao.TaobaoSaleMonthlyResult;
import jakarta.annotation.Resource;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ItemService {

    public static final String TOKEN = "rqPZ2yJQgNp1SQuR";
    @Resource
    private ItemRepository itemRepository;

    @Resource
    private ShopRepository shopRepository;

    @Resource
    private TaobaoHttpClient taobaoHttpClient;

    @Resource
    private SaleService saleService;

    public Item save(Item item) {
        Shop inDB = shopRepository.findByOutShopId(item.getShop().getOutShopId());
        item.setShop(inDB);
        setPublishedAt(item);
        return itemRepository.save(item);
    }

    private static void setPublishedAt(Item item) {
        if (item.getPublishedAt() == null)
            item.setPublishedAt(new Date());
    }

    public void saveAll(List<Item> collect) {
        collect.stream().peek(ItemService::setPublishedAt).collect(Collectors.toList());
        itemRepository.saveAll(collect);
    }

    public Page<Item> getUsers(Sort.Direction direction, String sortBy) {
        PageRequest pageRequest = PageRequest.of(0, 5, direction, sortBy);
        return itemRepository.findAll(pageRequest);
    }

    /**
     * 收藏
     *
     * @param itemId 商品id
     */
    public void favorite(Long itemId) {
        Item item = itemRepository.getReferenceById(itemId);
        TaobaoSaleMonthlyResult saleMonthlyResult = taobaoHttpClient.getMonthlySaleNum(item.getOutItemId(), TOKEN);
        if (saleMonthlyResult.saleMonthlyNum() > 0) {
            saleService.save(saleMonthlyResult.saleMonthlyNum(), item);
        }
    }

    @Resource
    SaleRepository saleRepository;

    public Sale saveSellCount(String itemId, Integer sellCount) {
        Date yesterday = Date.from(LocalDate.now().minusDays(1).atStartOfDay().toInstant(ZoneOffset.UTC));
        return saveSellCount(itemId, sellCount, yesterday);
    }

    public Sale saveSellCount(String itemId, Integer yesterdaySellCount, Date saleDate) {
        Item item = itemRepository.findByOutItemId(itemId);
        if (Objects.isNull(item)) {
            throw new SaleAssociatedItemException("找不到 itemId=" + itemId + " 的商品！");
        }

        Date dayBeforeYesterday = Date.from(LocalDate.now().minusDays(2).atStartOfDay().toInstant(ZoneOffset.UTC));
        Sale dayBeforeYesterdaySale = getSale(itemId, dayBeforeYesterday);
        int dayBeforeYesterdaySellCount = dayBeforeYesterdaySale.getNumber();

        Sale sale = Sale.builder()
                .incrementalSellCount(yesterdaySellCount - dayBeforeYesterdaySellCount)
                .number(yesterdaySellCount)
                .saleDate(saleDate)
                .item(item)
                .build();
        return saleRepository.save(sale);
    }

    private Sale getSale(String itemId, Date saleDate) {
        List<Sale> sales = saleRepository.findAll(new Specification<Sale>() {
            @Override
            public Predicate toPredicate(Root<Sale> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                CriteriaBuilder.In<Object> p1 =
                        criteriaBuilder.in(root.join("item").get("outItemId")).value(itemId);

                LocalDateTime startOfDay = LocalDate.ofInstant(saleDate.toInstant(), ZoneId.systemDefault()).atStartOfDay();
                LocalDateTime endOfDay = startOfDay.plusDays(1).minusSeconds(1);
                Predicate p2 = criteriaBuilder.between(root.get("saleDate"), startOfDay, endOfDay);

                return query.where(p1, p2).getRestriction();
            }
        });
        return sales.stream().findFirst()
                .orElse(Sale.builder().number(0).build());
    }

}
