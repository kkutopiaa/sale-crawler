package com.muhuang.salecrawler.item;

import com.muhuang.salecrawler.sale.Sale;
import com.muhuang.salecrawler.sale.SaleRepository;
import com.muhuang.salecrawler.share.TestUtil;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class ItemSellCountTest {

    @Nested
    class SingleRealTimeFetch {

        private final String itemId = "32838242344";
        @MockBean
        OneBoundService oneBoundService;

        @Resource
        SaleRepository saleRepository;

        @Resource
        ItemService itemService;

        @Resource
        ItemRepository itemRepository;

        @BeforeEach
        void setup() {
            saleRepository.deleteAll();
            itemRepository.deleteAll();
            Mockito.when(oneBoundService.getTaoBaoDetail(itemId)).thenReturn(16);
        }

        @Test
        @Disabled
        public void toFetchItemDetail_callItemDetailApi_receiveDetailJson() {
            String itemId = "1234";
            Integer sellCount = oneBoundService.getTaoBaoDetail(itemId);

        }

        @Test
        public void toFetchItemDetail_callItemDetailApiAndParseSellCount_receiveSellCountField() {
            Integer sellCount = oneBoundService.getTaoBaoDetail(itemId);
            assertThat(sellCount).isEqualTo(16);
        }

        @Test
        public void toFetchItemDetail_callItemDetailApiAndParseSellCount_saveSellCountToDatabase() {
            Integer sellCount = oneBoundService.getTaoBaoDetail(itemId);
            saleRepository.save(Sale.builder().number(sellCount).build());

            assertThat(saleRepository.count()).isEqualTo(1);
        }

        @Test
        public void saveSellCount_itemIsValid_sellCountIsAssociatedItem() {
            itemRepository.save(TestUtil.createValidItem());
            Integer sellCount = oneBoundService.getTaoBaoDetail(itemId);
            Sale sale = itemService.saveSellCount(itemId, sellCount);

            assertThat(sale.getItem().getOutItemId()).isEqualTo(itemId);
        }

        @Test
        public void saveSellCount_itemIsValid_saleDateIsYesterday() {
            itemRepository.save(TestUtil.createValidItem());
            Integer sellCount = oneBoundService.getTaoBaoDetail(itemId);
            Sale sale = itemService.saveSellCount(itemId, sellCount);
            Date yesterday = Date.from(LocalDate.now().minusDays(1).atStartOfDay().toInstant(ZoneOffset.UTC));

            assertThat(sale.getSaleDate()).isEqualTo(yesterday);
        }

        @Test
        public void saveSellCount_itemIsValidAndSaleDateIsFirstDay_incrementalSellCountIsEqualToSellCount() {
            itemRepository.save(TestUtil.createValidItem());
            Integer sellCount = oneBoundService.getTaoBaoDetail(itemId);
            Sale sale = itemService.saveSellCount(itemId, sellCount);

            assertThat(sale.getIncrementalSellCount()).isEqualTo(sale.getNumber());
        }

        @Test
        public void saveSellCount_itemIsValidAndSaleDateIsNotFirstDay_saveIncrementalSellCountToDatabase() {
            itemRepository.save(TestUtil.createValidItem());
            Date dayBeforeYesterday = Date.from(LocalDate.now().minusDays(2).atStartOfDay().toInstant(ZoneOffset.UTC));
            itemService.saveSellCount(itemId, 10, dayBeforeYesterday);

            Integer sellCount = oneBoundService.getTaoBaoDetail(itemId);
            Sale sale = itemService.saveSellCount(itemId, sellCount);

            assertThat(sale.getIncrementalSellCount()).isEqualTo(6);
        }


        @Test
        public void saveSellCount_itemIsInvalid_throwSaleAssociatedItemException() {
            assertThatThrownBy(() -> {
                itemService.saveSellCount(itemId, 1);
            }).isInstanceOf(SaleAssociatedItemException.class);
        }

        @Test
        public void saveSellCount_itemIsInvalid_throwSaleAssociatedItemExceptionHasCorrectMessage() {
            assertThatThrownBy(() -> {
                itemService.saveSellCount(itemId, 1);
            }).hasMessage("找不到 itemId=32838242344 的商品！");
        }


    }


}
