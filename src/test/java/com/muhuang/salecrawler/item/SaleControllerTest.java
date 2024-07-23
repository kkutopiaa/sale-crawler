package com.muhuang.salecrawler.item;

import com.muhuang.salecrawler.share.TestPage;
import com.muhuang.salecrawler.share.TestUtil;
import com.muhuang.salecrawler.sale.Sale;
import com.muhuang.salecrawler.sale.SaleRepository;
import com.muhuang.salecrawler.shop.ShopRepository;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.util.Date;
import java.util.Objects;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class SaleControllerTest {

    public static final String API_1_0_SALES = "/api/1.0/sales";
    public static final String API_1_0_SHOPS_ITEMS = "/api/1.0/shops/plugin-items";

    @Resource
    private TestRestTemplate testRestTemplate;

    @Resource
    private SaleRepository saleRepository;

    @Resource
    private ItemRepository itemRepository;

    @Resource
    private ShopRepository shopRepository;

    @BeforeEach
    public void cleanup() {
        saleRepository.deleteAll();
        itemRepository.deleteAll();
        shopRepository.deleteAll();
    }

    @Nested
    class Create {

        @Test
        void postSale_whenSaleIsValid_receiveOK() {
            Item item = TestUtil.createValidItem();
            itemRepository.save(item);
            Sale sale = Sale.builder().saleDate(new Date()).number(3).item(item).build();
            ResponseEntity<Object> response = testRestTemplate.postForEntity(API_1_0_SALES, sale, Object.class);
            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        }

        @Test
        void postSale_whenSaleIsValid_saleSaveToDatabase() {
            Item inDB = itemRepository.save(TestUtil.createValidItem());
            Sale sale = Sale.builder().saleDate(new Date()).number(3).item(inDB).build();
            testRestTemplate.postForEntity(API_1_0_SALES, sale, Object.class);
            assertThat(saleRepository.count()).isEqualTo(1);
        }

        @Test
        void postSale_whenSaleHasNoItem_receiveBadRequest() {
            Sale sale = createValidSale();
            ResponseEntity<Object> response = testRestTemplate.postForEntity(API_1_0_SALES, sale, Object.class);
            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        }

        @Test
        void postSale_whenASaleOfAnItemOfADayExist_receiveBadRequest() {
            Item inDB = itemRepository.save(TestUtil.createValidItem());
            Sale sale = createValidSale();
            sale.setItem(inDB);
            saleRepository.save(sale);
            ResponseEntity<Object> response = testRestTemplate.postForEntity(API_1_0_SALES, sale, Object.class);
            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        }

        @Test
        void postSale_whenASaleOfAnItemOfADayExist_saleNotSaveToDatabase() {
            Item inDB = itemRepository.save(TestUtil.createValidItem());
            Sale sale = createValidSale();
            sale.setItem(inDB);
            saleRepository.save(sale);
            testRestTemplate.postForEntity(API_1_0_SALES, sale, Object.class);
            assertThat(saleRepository.count()).isEqualTo(1);
        }

    }

    @Nested
    class Read {

        @Test
        void getSales_whenDatabaseHasNoSales_receiveOK() {
            ResponseEntity<Object> response = testRestTemplate.getForEntity(API_1_0_SALES, Object.class);
            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        }

        @Test
        void getSales_whenThereAreNoSalesInDB_receivePageWithZeroSales() {
            ResponseEntity<TestPage<Sale>> response = testRestTemplate.exchange(API_1_0_SALES,
                    HttpMethod.GET, null, new ParameterizedTypeReference<>() {
                    });
            assertThat(Objects.requireNonNull(response.getBody()).getTotalElements()).isEqualTo(0);
        }

        @Test
        void getSales_whenThereAreASalesInDB_receivePageWithOneSales() {
            Sale sale = createValidSale();
            saleRepository.save(sale);
            ResponseEntity<TestPage<Sale>> response = testRestTemplate.exchange(API_1_0_SALES,
                    HttpMethod.GET, null, new ParameterizedTypeReference<>() {
                    });
            assertThat(Objects.requireNonNull(response.getBody()).getTotalElements()).isEqualTo(1);
        }

        @Test
        @Disabled
        void getSales_whenThereAreALotOfSalesInDB_defaultReceive10Sales() {
            shopRepository.save(TestUtil.createValidShop());
            Item item = TestUtil.createValidItem();
            item.setSaleList(IntStream.rangeClosed(1, 20).mapToObj(x -> createValidSale()).toList());
            itemRepository.save(item);
            ResponseEntity<TestPage<Item>> response = testRestTemplate.exchange(API_1_0_SHOPS_ITEMS,
                    HttpMethod.GET, null, new ParameterizedTypeReference<>() {
                    });
            Item retrivedItem = Objects.requireNonNull(response.getBody()).getContent().get(0);
            assertThat(retrivedItem.getSaleList().size()).isEqualTo(10);
        }

    }

    private static Sale createValidSale() {
        return Sale.builder().saleDate(new Date()).number(3).build();
    }
}
