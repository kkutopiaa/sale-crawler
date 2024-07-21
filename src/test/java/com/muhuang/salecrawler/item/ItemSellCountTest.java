package com.muhuang.salecrawler.item;

import com.muhuang.salecrawler.sale.SaleRepository;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class ItemSellCountTest {

    @Nested
    class SingleRealTimeFetch {

        @Resource
        ItemService itemService;

        @MockBean
        OneBoundService oneBoundService;

        @Test
        public void toFetchItemId_callSellCountApi_receiveTotalSellCount() {
            String toFetchItemId = "811528885164";
            Mockito.when(oneBoundService.getTaobaoDetail(toFetchItemId)).thenReturn(mockJson);
            Integer totalSellCount = itemService.getTotalSellCount(toFetchItemId);
            assertThat(totalSellCount).isEqualTo(16);
        }

        @Test
        public void toFetchItemId_callSellCountApi_saveTotalSellCountToDB() {
            String toFetchItemId = "811528885164";
            Mockito.when(oneBoundService.getTaobaoDetail(toFetchItemId)).thenReturn(mockJson);
            Integer totalSellCount = itemService.getTotalSellCount(toFetchItemId);
            itemService.saveSellCount(totalSellCount);
            Integer getTotalSellCount = itemService.getSellCount(totalSellCount);

            assertThat(getTotalSellCount).isEqualTo(16);
        }



        String mockJson = """
                {
                  "item": {
                    "apiStack": [
                      {
                        "name": "esi",
                        "value": {
                          "consumerProtection": {
                            "items": [
                              {
                                "title": "付款后48小时内发货"
                              },
                              {
                                "desc": "8天退货，退货邮费买家承担",
                                "title": "8天退货"
                              },
                              {
                                "desc": "商品在运输途中出现破损的，消费者可向卖家提出补寄申请，可补寄1次，补寄邮费由买家承担",
                                "title": "1次破损补寄"
                              },
                              {
                                "desc": "购买该商品，每笔成交都会有相应金额捐赠给公益。感谢您的支持，愿公益的快乐伴随您每一天。",
                                "title": "公益宝贝"
                              },
                              {
                                "title": "集分宝"
                              },
                              {
                                "title": "支付宝支付"
                              }
                            ],
                            "passValue": "all"
                          },
                          "debug": {
                            "app": "alidetail",
                            "host": "taodetail033005068013.center.na610@33.5.68.13"
                          },
                          "delivery": {
                            "addressWeexUrl": "https://market.m.taobao.com/apps/market/detailrax/address-picker.html?spm=a2116h.app.0.0.16d957e9nDYOzv&wh_weex=true",
                            "areaId": "330102",
                            "completedTo": "杭州 上城 小营",
                            "extras": [],
                            "from": "广东深圳",
                            "overseaContraBandFlag": "false",
                            "postage": "快递: 快递包邮",
                            "to": "杭州上城"
                          },
                          "diversion": {
                            "detailTopSearch": {
                              "url": "https://s.m.taobao.com/h5entry?g_channelSrp=detail&placeholder=合法刀 随身&showText=合法刀 随身&g_historyOn=true&g_csearchdoor_spm=a2141.13130650&itemId=520813250866&detailShopId=127203758"
                            },
                            "productRecommend": {
                              "preloadUrl": "https://gw.alicdn.com/tfs/TB1PS8nBAPoK1RjSZKbXXX1IXXa-1125-1335.png?getAvatar=avatar",
                              "request": {
                                "api": "mtop.relationrecommend.WirelessRecommend.recommend",
                                "params": {
                                  "appId": "10777",
                                  "from": "dinamicX",
                                  "params": "{\\"itemid\\":520813250866,\\"spm\\":\\"0.0.0.0.hUro0P\\",\\"sellerid\\":2596264565,\\"appId\\":\\"10777\\"}"
                                },
                                "version": "2.0"
                              },
                              "template": {
                                "android": "https://ossgw.alicdn.com/rapid-oss-bucket/template_online/tb_shop_recommend/64005743/tb_shop_recommend_android.xml",
                                "ios": "https://ossgw.alicdn.com/rapid-oss-bucket/template_online/tb_shop_recommend/64005743/tb_shop_recommend_ios.plist",
                                "name": "tb_shop_recommend",
                                "version": "37"
                              }
                            }
                          },
                          "extendedData": [],
                          "feature": {
                            "UTABForceNewSku": "true",
                            "bigImageSkuProp": "true",
                            "cainiaoNoramal": "true",
                            "dailyPrice": "true",
                            "enableDpbModule": "false",
                            "freshmanRedPacket": "true",
                            "guessYouLike": "true",
                            "hasCoupon": "true",
                            "hasNewCombo": "false",
                            "hasSku": "true",
                            "newAddress": "true",
                            "newIndicator": "true",
                            "newPrice": "true",
                            "newTaobaoActivity": "true",
                            "noShareGroup": "true",
                            "openAddOnTools": "false",
                            "openGradient": "true",
                            "openNewSku": "true",
                            "promotion2018": "true",
                            "promotion2019": "true",
                            "showSku": "true",
                            "showSkuThumbnail": "true",
                            "superActTime": "false",
                            "taobao2018": "true"
                          },
                          "gallery": [],
                          "hybrid": {
                            "shopRecommendItems": {
                              "height": "445",
                              "spm": "",
                              "url": "https://market.m.taobao.com/apps/market/detailrax/recommend-shop-bigpage.html?spm=a2116h.app.0.0.16d957e9B7oLGw&wh_weex=true&sellerId=2596264565&itemId=520813250866&detail_v=3.1.1&selfRmdFlag=true"
                            }
                          },
                          "item": {
                            "couponUrl": "//h5.m.taobao.com/present/hongbao.html?sellerId=2596264565",
                            "picGallaryOverScroll": {
                              "mainPic": {
                                "action": [
                                  {
                                    "fields": {
                                      "url": "https://market.m.taobao.com/app/detail-project/pages/pages/banner-recommend?wx_navbar_hidden=true&_wx_statusbar_hidden=true&wh_weex=true&sellerId=2596264565&shopId=127203758&itemId=520813250866&spm=a2141.7631564&detailAlgoParam=&list_param=&abtest=76992_63166&detailUniqueId=02288d69701058eca52532215b7fed17"
                                    },
                                    "type": "open_url"
                                  },
                                  {
                                    "fields": {
                                      "trackName": "Page_Detail_Button-ItemDetailRecommend",
                                      "trackParams": {
                                        "spm": "a2141.7631564"
                                      }
                                    },
                                    "type": "user_track"
                                  }
                                ],
                                "text": "滑\\n动\\n查\\n看\\n更\\n多\\n宝\\n贝"
                              }
                            },
                            "showShopActivitySize": "2",
                            "skuText": "请选择 颜色分类 ",
                            "titleIcon": "",
                            "vagueSellCount": "16"
                          },
                          "layout": {
                            "config": {
                              "dependActionConfigID": "TB1Llk4UwHqK1RjSZFEhA3GMXXa",
                              "dependComponentConfigID": "TB1pw4VcQ9E3KVjSZFGhA319XXa",
                              "dependThemeConfigID": "TB1MfiMsQyWBuNjy0FphA1ssXXa"
                            },
                            "detailTemplateData": {
                              "homePage": [
                                {
                                  "ID": "navi_bar",
                                  "id": "navi_bar",
                                  "key": "navi_bar",
                                  "ruleId": ""
                                },
                                {
                                  "ID": "pic_gallery",
                                  "id": "pic_gallery",
                                  "key": "pic_gallery",
                                  "ruleId": ""
                                },
                                {
                                  "ID": "main_layout",
                                  "children": [
                                    {
                                      "key": "uniform_price",
                                      "ruleId": "TB_default"
                                    },
                                    {
                                      "key": "dinamic",
                                      "ruleId": "tb_detail_price_cheapie_shadow"
                                    },
                                    {
                                      "key": "dinamic",
                                      "ruleId": "tb_detail_price_ttsale_shadow"
                                    },
                                    {
                                      "key": "dinamic",
                                      "ruleId": "TB_jhs_main_Belt_shadow"
                                    },
                                    {
                                      "key": "tip",
                                      "ruleId": "TB_presaleTmall2"
                                    },
                                    {
                                      "key": "tip",
                                      "ruleId": "TB_priceTip2"
                                    },
                                    {
                                      "key": "tip",
                                      "ruleId": "TB_pricedCouponTip2"
                                    },
                                    {
                                      "key": "tip",
                                      "ruleId": "TB_shipTime2"
                                    },
                                    {
                                      "key": "dinamic",
                                      "ruleId": "TB_coupon_promotion2019"
                                    },
                                    {
                                      "key": "dinamic",
                                      "ruleId": "TB_detail_black_card_brand_info"
                                    },
                                    {
                                      "key": "dinamic",
                                      "ruleId": "TB_detail_brand_info"
                                    },
                                    {
                                      "key": "dinamic",
                                      "ruleId": "TB_detail_title_normal"
                                    },
                                    {
                                      "key": "dinamic",
                                      "ruleId": "TB_detail_title_tmallMarket"
                                    },
                                    {
                                      "key": "dinamic",
                                      "ruleId": "TB_detail_title_xinxuan"
                                    },
                                    {
                                      "key": "dinamic",
                                      "ruleId": "TB_detail_kernel_params"
                                    },
                                    {
                                      "key": "dinamic",
                                      "ruleId": "TB_detail_small_activity"
                                    },
                                    {
                                      "key": "dinamic",
                                      "ruleId": "TB_detail_subInfo_default"
                                    },
                                    {
                                      "key": "dinamic",
                                      "ruleId": "TB_detail_appointment_store3"
                                    },
                                    {
                                      "key": "dinamic",
                                      "ruleId": "TB_detail_appointment_store"
                                    },
                                    {
                                      "key": "dinamic",
                                      "ruleId": "TB_detail_divider"
                                    },
                                    {
                                      "key": "dinamic",
                                      "ruleId": "TB_detail_entrance_artascope"
                                    },
                                    {
                                      "key": "dinamic",
                                      "ruleId": "TB_detail_divider"
                                    },
                                    {
                                      "key": "dinamic",
                                      "ruleId": "TB_detail_pick"
                                    },
                                    {
                                      "key": "dinamic",
                                      "ruleId": "TB_detail_coupon"
                                    },
                                    {
                                      "key": "dinamic",
                                      "ruleId": "TB_detail_promotion"
                                    },
                                    {
                                      "key": "dinamic",
                                      "ruleId": "TB_detail_price_coupon"
                                    },
                                    {
                                      "key": "dinamic",
                                      "ruleId": "TB_detail_share"
                                    },
                                    {
                                      "key": "dinamic",
                                      "ruleId": "TB_jk_detail_coupon_share"
                                    },
                                    {
                                      "key": "dinamic",
                                      "ruleId": "TB_detail_tmallfeature"
                                    },
                                    {
                                      "key": "dinamic",
                                      "ruleId": "TB_detail_cuntao_pinchegou"
                                    },
                                    {
                                      "key": "dinamic",
                                      "ruleId": "TB_detail_creditbuy"
                                    },
                                    {
                                      "key": "dinamic",
                                      "ruleId": "TB_detail_divider"
                                    },
                                    {
                                      "key": "dinamic",
                                      "ruleId": "TB_msfx_tmall_banner"
                                    },
                                    {
                                      "key": "dinamic",
                                      "ruleId": "TB_detail_logistics"
                                    },
                                    {
                                      "key": "dinamic",
                                      "ruleId": "TB_detail_sub_logistics"
                                    },
                                    {
                                      "key": "dinamic",
                                      "ruleId": "TB_detail_tax"
                                    },
                                    {
                                      "key": "dinamic",
                                      "ruleId": "TB_detail_trade_guarantee"
                                    },
                                    {
                                      "key": "dinamic",
                                      "ruleId": "TB_detail_guarantee"
                                    },
                                    {
                                      "key": "dinamic_o2o",
                                      "ruleId": "TB_detail_o2o"
                                    },
                                    {
                                      "key": "dinamic",
                                      "ruleId": "TB_detail_divider"
                                    },
                                    {
                                      "key": "dinamic",
                                      "ruleId": "TB_detail_divider"
                                    },
                                    {
                                      "key": "dinamic",
                                      "ruleId": "TB_detail_sku_cross_upgrade"
                                    },
                                    {
                                      "key": "xsku",
                                      "ruleId": "TB_Default"
                                    },
                                    {
                                      "key": "dinamic",
                                      "ruleId": "TB_detail_sku_transform"
                                    },
                                    {
                                      "key": "dinamic",
                                      "ruleId": "TB_detail_product_props"
                                    },
                                    {
                                      "key": "vessel",
                                      "ruleId": "TB_guess_u_like_promotion"
                                    },
                                    {
                                      "key": "dinamic",
                                      "ruleId": "TB_detail_miniapp_diliver"
                                    },
                                    {
                                      "key": "dinamic",
                                      "ruleId": "TB_detail_miniapp_rmd"
                                    },
                                    {
                                      "key": "dinamic",
                                      "ruleId": "TB_detail_divider_rateLocator"
                                    },
                                    {
                                      "key": "dinamic",
                                      "ruleId": "TB_detail_comment_empty"
                                    },
                                    {
                                      "key": "dinamic",
                                      "ruleId": "TB_detail_comment_head"
                                    },
                                    {
                                      "key": "dinamic",
                                      "ruleId": "TB_detail_comment_tag"
                                    },
                                    {
                                      "key": "dinamic",
                                      "ruleId": "TB_detail_comment_single_hot"
                                    },
                                    {
                                      "key": "dinamic",
                                      "ruleId": "TB_detail_buyer_photo"
                                    },
                                    {
                                      "key": "dinamic",
                                      "ruleId": "TB_detail_ask_all_no_question"
                                    },
                                    {
                                      "key": "dinamic",
                                      "ruleId": "TB_detail_ask_all_two_questions"
                                    },
                                    {
                                      "key": "dinamic",
                                      "ruleId": "TB_detail_ask_all_aliMedical"
                                    },
                                    {
                                      "key": "dinamic",
                                      "ruleId": "TB_detail_vipComment"
                                    },
                                    {
                                      "key": "dinamic",
                                      "ruleId": "TB_detail_medical_official_case_card"
                                    },
                                    {
                                      "key": "vessel",
                                      "ruleId": "TB_guess_u_like"
                                    },
                                    {
                                      "key": "dinamic",
                                      "ruleId": "TB_detail_divider"
                                    },
                                    {
                                      "key": "dinamic",
                                      "ruleId": "TB_detail_shop"
                                    },
                                    {
                                      "key": "dinamic",
                                      "ruleId": "TB_detail_endorsement"
                                    },
                                    {
                                      "key": "dinamic_async",
                                      "ruleId": "TB_tblive_detail"
                                    },
                                    {
                                      "key": "vessel",
                                      "ruleId": "TB_shop_recommend"
                                    }
                                  ],
                                  "id": "main_layout",
                                  "key": "sys_list",
                                  "ruleId": ""
                                },
                                {
                                  "ID": "bottom_bar",
                                  "id": "bottom_bar",
                                  "key": "bottom_bar",
                                  "ruleId": "TB_promo2019_get_voucher_buy"
                                }
                              ]
                            },
                            "hierarchyData": {
                              "actions": {
                                "gotoDescRecommend": {
                                  "event": "click",
                                  "params": {
                                    "locatorId": "divisionDescRecmd"
                                  },
                                  "type": "locator"
                                },
                                "gotoDetailDesc": {
                                  "event": "click",
                                  "params": {
                                    "locatorId": "divisionDesc"
                                  },
                                  "type": "locator"
                                },
                                "gotoDetailInfo": {
                                  "event": "click",
                                  "params": {
                                    "locatorId": "detailInfo"
                                  },
                                  "type": "locator"
                                },
                                "gotoDetailRate": {
                                  "event": "click",
                                  "params": {
                                    "locatorId": "divisionRate"
                                  },
                                  "type": "locator"
                                },
                                "gotoPreviewPage": {
                                  "type": "go_back"
                                },
                                "gotoUserTalk": {
                                  "event": "click",
                                  "params": {
                                    "locatorId": "userSay"
                                  },
                                  "type": "locator"
                                },
                                "naviShowBigPic": {
                                  "type": "show_big_pic"
                                },
                                "openPageCart": {
                                  "params": {
                                    "url": "https://h5.m.taobao.com/awp/base/cart.htm",
                                    "urlParams": {
                                      "cartfrom": "detail",
                                      "itemId": "${item.itemId}"
                                    }
                                  },
                                  "type": "open_url"
                                },
                                "openPageCartForInter": {
                                  "params": {
                                    "url": "https://pages.tmall.com/wow/trade/act/cart?wh_weex=true",
                                    "urlParams": {
                                      "cartfrom": "detail",
                                      "itemId": "${item.itemId}"
                                    }
                                  },
                                  "type": "open_url"
                                },
                                "openPageSearch": {
                                  "params": {
                                    "url": "https://s.m.tmall.com/m/searchbar.htm",
                                    "urlParams": {
                                      "cartfrom": "tmall_supermarket",
                                      "tpId": "${vertical.supermarket.tpId}"
                                    }
                                  },
                                  "type": "open_url"
                                },
                                "showMenu": {
                                  "type": "show_menu"
                                },
                                "utClickDescRecommend": {
                                  "event": "click",
                                  "params": {
                                    "trackName": "GotoShopRecommend",
                                    "trackNamePre": "Button-"
                                  },
                                  "type": "user_track"
                                },
                                "utClickDetailDesc": {
                                  "event": "click",
                                  "params": {
                                    "trackName": "GotoDetailDesc",
                                    "trackNamePre": "Button-"
                                  },
                                  "type": "user_track"
                                },
                                "utClickDetailInfo": {
                                  "event": "click",
                                  "params": {
                                    "trackName": "GotoDetailHome",
                                    "trackNamePre": "Button-"
                                  },
                                  "type": "user_track"
                                },
                                "utClickDetailRate": {
                                  "event": "click",
                                  "params": {
                                    "trackName": "GotoDetailRate",
                                    "trackNamePre": "Button-"
                                  },
                                  "type": "user_track"
                                },
                                "utExposureDescRecommend": {
                                  "params": {
                                    "spm": "a2141.7631564.2737766",
                                    "trackPage": "Page_Detail_Show_Recommend"
                                  },
                                  "type": "ut_exposure"
                                },
                                "utExposureDetailDesc": {
                                  "params": {
                                    "scm": "",
                                    "spm": "a2141.7631564.1999077549",
                                    "trackPage": "Page_Detail_Show_ProductDetail"
                                  },
                                  "type": "ut_exposure"
                                },
                                "utExposureDetailRate": {
                                  "params": {
                                    "spm": "a2141.7631564.2737664",
                                    "trackPage": "Page_Detail_Show_Detail"
                                  },
                                  "type": "ut_exposure"
                                },
                                "utGotoCart": {
                                  "params": {
                                    "trackName": "ShoppingCart",
                                    "trackNamePre": "Button-"
                                  },
                                  "type": "user_track"
                                },
                                "utGotoSearch": {
                                  "params": {
                                    "trackName": "Search",
                                    "trackNamePre": "Button-",
                                    "trackParams": {
                                      "tpId": "${vertical.supermarket.tpId}"
                                    }
                                  },
                                  "type": "user_track"
                                },
                                "utNaviShowBigPic": {
                                  "params": {
                                    "trackName": "NaviShowBigPic",
                                    "trackNamePre": "Button-"
                                  },
                                  "type": "user_track"
                                }
                              },
                              "components": {
                                "descRecmd": {
                                  "actions": [
                                    "utExposureDescRecommend"
                                  ],
                                  "name": "descRecmd",
                                  "payload": {
                                    "itemId": "${item.itemId}",
                                    "userId": "${seller.userId}"
                                  },
                                  "style": "shopRecommend",
                                  "type": "descRecmd"
                                },
                                "detail": {
                                  "name": "detail",
                                  "type": "detail"
                                },
                                "detailDesc": {
                                  "actions": [
                                    "utExposureDetailDesc"
                                  ],
                                  "name": "detailDesc",
                                  "payload": {
                                    "itemId": "${item.itemId}",
                                    "moduleDescParams": "${item.moduleDescParams}",
                                    "shopId": "${seller.shopId}",
                                    "taobaoDescUrl": "${item.taobaoDescUrl}",
                                    "taobaoPcDescUrl": "${item.taobaoPcDescUrl}",
                                    "userId": "${seller.userId}"
                                  },
                                  "style": "detailDesc",
                                  "type": "detailDesc"
                                },
                                "detailHome": {
                                  "locatorId": "detailHome",
                                  "name": "detailHome",
                                  "type": "detailHome"
                                },
                                "detailInfo": {
                                  "locatorId": "detailInfo",
                                  "name": "detailInfo",
                                  "type": "detailInfo"
                                },
                                "divisionDesc": {
                                  "locatorId": "divisionDesc",
                                  "name": "divisionDesc",
                                  "payload": {
                                    "displayType": "text",
                                    "fgcolor": "0x999999",
                                    "iconUrl": "//img.alicdn.com/tps/TB1182hOVXXXXcQXXXXXXXXXXXX",
                                    "title": "详情"
                                  },
                                  "type": "division"
                                },
                                "divisionDescRecmd": {
                                  "locatorId": "divisionDescRecmd",
                                  "name": "divisionDescRecmd",
                                  "payload": {
                                    "displayType": "text",
                                    "fgcolor": "0x999999",
                                    "iconUrl": "//img.alicdn.com/tps/TB1PGyPOVXXXXa8aXXXXXXXXXXX",
                                    "title": "推荐"
                                  },
                                  "type": "division"
                                },
                                "divisionEnd": {
                                  "name": "divisionEnd",
                                  "payload": {
                                    "displayType": "text",
                                    "fgcolor": "0x999999",
                                    "title": "已经到底了"
                                  },
                                  "type": "division"
                                },
                                "naviItemCenter": {
                                  "actions": [
                                    "naviShowBigPic",
                                    "utNaviShowBigPic"
                                  ],
                                  "name": "naviItemCenter",
                                  "payload": {
                                    "accessHint": "图片",
                                    "itemType": "2",
                                    "positionKey": "center",
                                    "value": "${item.images[0]}"
                                  },
                                  "type": "detailNaviItem"
                                },
                                "naviItemCustom": {
                                  "actions": [
                                    "utGotoCart",
                                    "openPageCart"
                                  ],
                                  "name": "naviItemCustom",
                                  "payload": {
                                    "accessHint": "购物车",
                                    "positionKey": "custom",
                                    "titleSizeRatio": "0.375",
                                    "value": "ꁊ"
                                  },
                                  "type": "detailNaviItem"
                                },
                                "naviItemLeft": {
                                  "actions": [
                                    "gotoPreviewPage"
                                  ],
                                  "name": "naviItemLeft",
                                  "payload": {
                                    "accessHint": "返回",
                                    "positionKey": "left",
                                    "secondActions": [
                                      {
                                        "domain": "detail",
                                        "eventToken": "${eventToken}",
                                        "type": "go_detail_home"
                                      }
                                    ],
                                    "titleSizeRatio": "0.375",
                                    "value": "ꁺ"
                                  },
                                  "type": "detailNaviItem"
                                },
                                "naviItemRight": {
                                  "actions": [
                                    "showMenu"
                                  ],
                                  "name": "naviItemRight",
                                  "payload": {
                                    "accessHint": "更多",
                                    "positionKey": "right",
                                    "titleSizeRatio": "0.375",
                                    "value": "ꁪ"
                                  },
                                  "type": "detailNaviItem"
                                },
                                "naviTabDesc": {
                                  "actions": [
                                    "gotoDetailDesc",
                                    "utClickDetailDesc"
                                  ],
                                  "name": "naviTabDesc",
                                  "payload": {
                                    "iconUrl": "//img.alicdn.com/tps/TB1ZGAeOFXXXXa6XXXXXXXXXXXX",
                                    "title": "详情"
                                  },
                                  "style": "tab",
                                  "type": "detailNaviTabItem"
                                },
                                "naviTabDescRecmd": {
                                  "actions": [
                                    "gotoDescRecommend",
                                    "utClickDescRecommend"
                                  ],
                                  "name": "naviTabDescRecmd",
                                  "payload": {
                                    "iconUrl": "//img.alicdn.com/tps/TB1ZGAeOFXXXXa6XXXXXXXXXXXX",
                                    "title": "推荐"
                                  },
                                  "style": "tab",
                                  "type": "detailNaviTabItem"
                                },
                                "naviTabInfo": {
                                  "actions": [
                                    "gotoDetailInfo",
                                    "utClickDetailInfo"
                                  ],
                                  "name": "naviTabInfo",
                                  "payload": {
                                    "iconUrl": "//img.alicdn.com/tps/TB1ZGAeOFXXXXa6XXXXXXXXXXXX",
                                    "title": "宝贝"
                                  },
                                  "style": "tab",
                                  "type": "detailNaviTabItem"
                                },
                                "naviTabRate": {
                                  "actions": [
                                    "gotoDetailRate",
                                    "utClickDetailRate",
                                    "utExposureDetailRate"
                                  ],
                                  "name": "naviTabRate",
                                  "payload": {
                                    "iconUrl": "//img.alicdn.com/tps/TB1ZGAeOFXXXXa6XXXXXXXXXXXX",
                                    "pageName": "Page_DetailComments",
                                    "secondActions": [
                                      {
                                        "domain": "detail",
                                        "eventToken": "${eventToken}",
                                        "type": "goto_rate_top"
                                      }
                                    ],
                                    "title": "评价"
                                  },
                                  "style": "tab",
                                  "type": "detailNaviTabItem"
                                },
                                "naviTabs": {
                                  "name": "naviTabs",
                                  "type": "detailNaviTabs"
                                },
                                "navibar": {
                                  "name": "navibar",
                                  "type": "detailNavibar"
                                }
                              },
                              "hierarchy": {
                                "root": "detail",
                                "structure": {
                                  "detail": [
                                    "navibar",
                                    "detailHome"
                                  ],
                                  "detailHome": [
                                    "detailInfo",
                                    "divisionDesc",
                                    "detailDesc",
                                    "divisionDescRecmd",
                                    "descRecmd",
                                    "divisionEnd"
                                  ],
                                  "naviTabs": [
                                    "naviTabInfo",
                                    "naviTabRate",
                                    "naviTabDesc",
                                    "naviTabDescRecmd"
                                  ],
                                  "navibar": [
                                    "naviItemLeft",
                                    "naviItemCenter",
                                    "naviItemCustom",
                                    "naviItemRight",
                                    "naviTabs"
                                  ]
                                }
                              }
                            }
                          },
                          "params": {
                            "aliAbTestTrackParams": {
                              "recommend2018": "[{\\"abtest\\":\\"5154_4504\\",\\"component\\":\\"recommendNewVersion\\",\\"releaseId\\":5154,\\"module\\":\\"2018\\",\\"cm\\":\\"recommendNewVersion_2018\\",\\"experimentId\\":2021,\\"bucketId\\":4504,\\"trackConfigs\\":\\"[]\\"}]"
                            },
                            "trackParams": {
                              "aliBizCode": "ali.china.taobao",
                              "aliBizCodeToken": "YWxpLmNoaW5hLnRhb2Jhbw==",
                              "businessTracks": "%7B%2264362%22%3A%2233826%22%2C%2261206%22%3A%2226817%22%2C%2276992%22%3A%2263166%22%2C%2240187%22%3A%2246496%22%2C%2261267%22%3A%2222301%22%2C%2264356%22%3A%2224229%22%2C%2275148%22%3A%2258397%22%2C%2267561%22%3A%2272421%22%2C%2264360%22%3A%2230993%22%7D",
                              "detailUniqueId": "a35415180f425aa5f17174cdb9aa9a06",
                              "detailabtestdetail": "64362_33826.61206_26817.5154_4504.70678_70675.76992_63166.40187_46496.61267_22301.64356_24229.75148_58397.67561_72421.64360_30993",
                              "layoutId": null,
                              "spm": null
                            },
                            "umbParams": {
                              "aliBizCode": "ali.china.taobao",
                              "aliBizName": "ali.china.taobao"
                            }
                          },
                          "price": {
                            "extraPrices": [],
                            "newExtraPrices": [],
                            "price": {
                              "priceText": "25.8-91.8",
                              "type": "2"
                            },
                            "priceTag": [
                              {
                                "bgColor": "#FFF1EB",
                                "text": "淘金币可抵0.51元起",
                                "textColor": "#FF5000"
                              }
                            ],
                            "transmitPrice": {
                              "priceText": "25.8-91.8"
                            }
                          },
                          "priceSectionData": {
                            "mainBelt": {
                              "bizType": "0",
                              "rightBelt": {
                                "countdown": "0",
                                "extraTextColor": "#FFFFFF",
                                "text": "",
                                "textColor": "#FFFFFF"
                              },
                              "styleType": "0"
                            },
                            "price": {
                              "newLine": "false",
                              "priceText": "25.8-91.8",
                              "priceType": "origin_price"
                            },
                            "priceType": "origin_price",
                            "promotion": {
                              "entranceTip": "领券",
                              "entranceUrl": "https://market.m.taobao.com/app/detail-project/detail-pages/pages/quan2020?wh_weex=true",
                              "items": [
                                {
                                  "bgImage": "https://gw.alicdn.com/tfs/TB1.dqZSgHqK1RjSZJnXXbNLpXa-40-40.png",
                                  "content": "淘金币可抵0.51元起",
                                  "sbgImage": "https://gw.alicdn.com/tfs/TB12R2Oerj1gK0jSZFuXXcrHpXa-302-80.png",
                                  "scontent": "淘金币可抵0.51元起",
                                  "textColor": "#FD5F20",
                                  "type": "default"
                                },
                                {
                                  "bgImage": "https://gw.alicdn.com/tfs/TB1.dqZSgHqK1RjSZJnXXbNLpXa-40-40.png",
                                  "content": "店铺券满28减1",
                                  "endTime": "2021-01-01 23:59:59",
                                  "sbgImage": "https://gw.alicdn.com/tfs/TB1k50Yj4D1gK0jSZFsXXbldVXa-280-40.png",
                                  "scontent": "满28减1",
                                  "startTime": "2020-11-02 00:00:00",
                                  "stitle": "店铺券",
                                  "textColor": "#FD5F20",
                                  "type": "default"
                                }
                              ],
                              "promotionStyle": "false"
                            }
                          },
                          "promotionFloatingData": {
                            "buyEnable": "true",
                            "detailPromotionTimeDO": {
                              "promotionType": "NLORMAL"
                            },
                            "showNow": "true",
                            "showWarm": "true",
                            "skuMoney": {
                              "cent": "2580",
                              "skuId": "3144644292458"
                            }
                          },
                          "resource": {
                            "entrances": {
                              "ltaoBanner": {
                                "bizKey": "ltaoBanner",
                                "icon": "https://gw.alicdn.com/tfs/TB1dU2aLxz1gK0jSZSgXXavwpXa-134-26.png",
                                "link": "https://pages.tmall.com/wow/z/sale/solutionaplus/taobao_xiangqing?channel_id=taobao_xiangqing&jumpurl=https%3A%2F%2Fmarket.m.taobao.com%2Fapp%2Fnozomi%2Fapp-h5-detail%2Fmain%2Findex.html%3Fid%3D520813250866%26cbid%3Djmp.taobao_xiangqing.default&topItemId=520813250866&cbid=dld.taobao_xiangqing.default",
                                "spm": "a2141.7631564.ltaoBanner",
                                "text": "特价版专用，领红包购物立减"
                              }
                            },
                            "entrancesBizsContent": "ltaoBanner",
                            "floatView": {
                              "list": []
                            },
                            "newBigPromotion": [],
                            "promsCalcInfo": {
                              "cheapestMoney": "0",
                              "hasCoupon": "true"
                            },
                            "taobaoAppActivities": [
                              {
                                "action": [
                                  {
                                    "key": "open_url",
                                    "params": {
                                      "params": {
                                        "needLogin": "false"
                                      },
                                      "url": "https://pages.tmall.com/wow/z/sale/solutionaplus/taobao_xiangqing?channel_id=taobao_xiangqing&jumpurl=https%3A%2F%2Fmarket.m.taobao.com%2Fapp%2Fnozomi%2Fapp-h5-detail%2Fmain%2Findex.html%3Fid%3D520813250866%26cbid%3Djmp.taobao_xiangqing.default&topItemId=520813250866&cbid=dld.taobao_xiangqing.default"
                                    }
                                  },
                                  {
                                    "key": "user_track",
                                    "params": {
                                      "trackName": "NeweRightsModule",
                                      "trackNamePre": "Button-",
                                      "trackParams": {
                                        "spm": "a2141.7631564.ltaoBanner"
                                      }
                                    }
                                  }
                                ],
                                "bizKey": "ltaoBanner",
                                "icon": "https://gw.alicdn.com/tfs/TB1dU2aLxz1gK0jSZSgXXavwpXa-134-26.png",
                                "spm": "a2141.7631564.ltaoBanner",
                                "text": "特价版专用，领红包购物立减"
                              }
                            ]
                          },
                          "skuBase": {
                            "props": [
                              {
                                "name": "颜色分类",
                                "pid": "1627207",
                                "values": [
                                  {
                                    "image": "http://img.alicdn.com/imgextra/i3/2596264565/TB2.XeblVXXXXXkXpXXXXXXXXXX_!!2596264565.jpg",
                                    "name": "长方形带开瓶器+送工具刀卡+链子",
                                    "vid": "1347647754"
                                  },
                                  {
                                    "image": "http://img.alicdn.com/imgextra/i4/2596264565/TB2dTrjdVXXXXXBXpXXXXXXXXXX_!!2596264565.jpg",
                                    "name": "椭圆形带开瓶器+送工具刀卡+链子",
                                    "vid": "1347647753"
                                  },
                                  {
                                    "image": "http://img.alicdn.com/imgextra/i2/2596264565/TB2j22kdVXXXXXdXpXXXXXXXXXX_!!2596264565.jpg",
                                    "name": "GJ018X钥匙刀+送工具刀卡+链子",
                                    "vid": "1195392087"
                                  },
                                  {
                                    "image": "http://img.alicdn.com/imgextra/i4/2596264565/TB2_uiXnFXXXXXBXXXXXXXXXXXX_!!2596264565.jpg",
                                    "name": "超凡大师套餐【送工具卡+链子】",
                                    "vid": "1331112595"
                                  },
                                  {
                                    "image": "http://img.alicdn.com/imgextra/i4/2596264565/TB2Gm9xnFXXXXbmXXXXXXXXXXXX_!!2596264565.jpg",
                                    "name": "最强王者套餐【送工具卡+链子】",
                                    "vid": "1331112594"
                                  },
                                  {
                                    "image": "http://img.alicdn.com/imgextra/i3/2596264565/TB2wWohmXXXXXX8XXXXXXXXXXXX_!!2596264565.jpg",
                                    "name": "璀璨钻石套餐【送工具卡+链子】",
                                    "vid": "1331264247"
                                  }
                                ]
                              }
                            ],
                            "skus": [
                              {
                                "propPath": "1627207:1347647754",
                                "skuId": "3166598625985"
                              },
                              {
                                "propPath": "1627207:1347647753",
                                "skuId": "3166598625984"
                              },
                              {
                                "propPath": "1627207:1195392087",
                                "skuId": "3144644292458"
                              },
                              {
                                "propPath": "1627207:1331112595",
                                "skuId": "3161300228970"
                              },
                              {
                                "propPath": "1627207:1331112594",
                                "skuId": "3161300228969"
                              },
                              {
                                "propPath": "1627207:1331264247",
                                "skuId": "3161107666655"
                              }
                            ]
                          },
                          "skuCore": {
                            "abSwitch": [],
                            "atmosphere": [],
                            "sku2info": {
                              "0": {
                                "price": {
                                  "priceMoney": "2580",
                                  "priceText": "25.8-91.8",
                                  "type": "2"
                                },
                                "quantity": "16835"
                              },
                              "3144644292458": {
                                "logisticsTime": "付款后48小时内发货",
                                "price": {
                                  "priceMoney": "2580",
                                  "priceText": "25.8",
                                  "type": "2"
                                },
                                "quantity": "3399"
                              },
                              "3161107666655": {
                                "buyText": "领券购买",
                                "itemApplyParams": "[{\\"couponName\\":\\"满45减2店铺优惠券\\",\\"sellerId\\":2596264565,\\"couponType\\":1,\\"templateCode\\":\\"3561507556\\",\\"uuid\\":\\"6d8719ce866d4fbb9d29649b1e571740\\"}]",
                                "logisticsTime": "付款后48小时内发货",
                                "price": {
                                  "priceMoney": "6380",
                                  "priceText": "63.8",
                                  "type": "2"
                                },
                                "quantity": "163",
                                "skuPromTip": "<font color=\\"#999999\\"> 当前商品可使用 </font><font color=\\"#FF5000\\"> 满45减2 </font> <font color=\\"#999999\\"> 店铺优惠券 </font>",
                                "subPrice": {
                                  "priceColor": "#FF4F00",
                                  "priceMoney": "6180",
                                  "priceText": "61.8",
                                  "priceTitle": "券后",
                                  "priceTitleColor": "#FF4F00"
                                }
                              },
                              "3161300228969": {
                                "buyText": "领券购买",
                                "itemApplyParams": "[{\\"couponName\\":\\"满45减2店铺优惠券\\",\\"sellerId\\":2596264565,\\"couponType\\":1,\\"templateCode\\":\\"3561507556\\",\\"uuid\\":\\"6d8719ce866d4fbb9d29649b1e571740\\"}]",
                                "logisticsTime": "付款后48小时内发货",
                                "price": {
                                  "priceMoney": "9180",
                                  "priceText": "91.8",
                                  "type": "2"
                                },
                                "quantity": "0",
                                "skuPromTip": "<font color=\\"#999999\\"> 当前商品可使用 </font><font color=\\"#FF5000\\"> 满45减2 </font> <font color=\\"#999999\\"> 店铺优惠券 </font>",
                                "subPrice": {
                                  "priceColor": "#FF4F00",
                                  "priceMoney": "8980",
                                  "priceText": "89.8",
                                  "priceTitle": "券后",
                                  "priceTitleColor": "#FF4F00"
                                }
                              },
                              "3161300228970": {
                                "buyText": "领券购买",
                                "itemApplyParams": "[{\\"couponName\\":\\"满45减2店铺优惠券\\",\\"sellerId\\":2596264565,\\"couponType\\":1,\\"templateCode\\":\\"3561507556\\",\\"uuid\\":\\"6d8719ce866d4fbb9d29649b1e571740\\"}]",
                                "logisticsTime": "付款后48小时内发货",
                                "price": {
                                  "priceMoney": "7380",
                                  "priceText": "73.8",
                                  "type": "2"
                                },
                                "quantity": "0",
                                "skuPromTip": "<font color=\\"#999999\\"> 当前商品可使用 </font><font color=\\"#FF5000\\"> 满45减2 </font> <font color=\\"#999999\\"> 店铺优惠券 </font>",
                                "subPrice": {
                                  "priceColor": "#FF4F00",
                                  "priceMoney": "7180",
                                  "priceText": "71.8",
                                  "priceTitle": "券后",
                                  "priceTitleColor": "#FF4F00"
                                }
                              },
                              "3166598625984": {
                                "buyText": "领券购买",
                                "itemApplyParams": "[{\\"couponName\\":\\"满28减1店铺优惠券\\",\\"sellerId\\":2596264565,\\"couponType\\":1,\\"templateCode\\":\\"3496464596\\",\\"uuid\\":\\"88d3735644b747078a283683ae3638c4\\"}]",
                                "logisticsTime": "付款后48小时内发货",
                                "price": {
                                  "priceMoney": "3900",
                                  "priceText": "39",
                                  "type": "2"
                                },
                                "quantity": "3699",
                                "skuPromTip": "<font color=\\"#999999\\"> 当前商品可使用 </font><font color=\\"#FF5000\\"> 满28减1 </font> <font color=\\"#999999\\"> 店铺优惠券 </font>",
                                "subPrice": {
                                  "priceColor": "#FF4F00",
                                  "priceMoney": "3800",
                                  "priceText": "38",
                                  "priceTitle": "券后",
                                  "priceTitleColor": "#FF4F00"
                                }
                              },
                              "3166598625985": {
                                "buyText": "领券购买",
                                "itemApplyParams": "[{\\"couponName\\":\\"满28减1店铺优惠券\\",\\"sellerId\\":2596264565,\\"couponType\\":1,\\"templateCode\\":\\"3496464596\\",\\"uuid\\":\\"88d3735644b747078a283683ae3638c4\\"}]",
                                "logisticsTime": "付款后48小时内发货",
                                "price": {
                                  "priceMoney": "3900",
                                  "priceText": "39",
                                  "type": "2"
                                },
                                "quantity": "9574",
                                "skuPromTip": "<font color=\\"#999999\\"> 当前商品可使用 </font><font color=\\"#FF5000\\"> 满28减1 </font> <font color=\\"#999999\\"> 店铺优惠券 </font>",
                                "subPrice": {
                                  "priceColor": "#FF4F00",
                                  "priceMoney": "3800",
                                  "priceText": "38",
                                  "priceTitle": "券后",
                                  "priceTitleColor": "#FF4F00"
                                }
                              }
                            },
                            "skuItem": {
                              "extraProm": [
                                {
                                  "text": "淘金币可抵0.51元"
                                }
                              ],
                              "location": "杭州上城"
                            }
                          },
                          "skuTransform": {
                            "extraText": "共6种颜色分类可选",
                            "skuContents": [
                              {
                                "img": "http://img.alicdn.com/imgextra/i3/2596264565/TB2.XeblVXXXXXkXpXXXXXXXXXX_!!2596264565.jpg"
                              },
                              {
                                "img": "http://img.alicdn.com/imgextra/i4/2596264565/TB2dTrjdVXXXXXBXpXXXXXXXXXX_!!2596264565.jpg"
                              },
                              {
                                "img": "http://img.alicdn.com/imgextra/i2/2596264565/TB2j22kdVXXXXXdXpXXXXXXXXXX_!!2596264565.jpg"
                              },
                              {
                                "img": "http://img.alicdn.com/imgextra/i4/2596264565/TB2_uiXnFXXXXXBXXXXXXXXXXXX_!!2596264565.jpg"
                              },
                              {
                                "img": "http://img.alicdn.com/imgextra/i4/2596264565/TB2Gm9xnFXXXXbmXXXXXXXXXXXX_!!2596264565.jpg"
                              }
                            ]
                          },
                          "skuVertical": [],
                          "traceDatas": {
                            "bizTrackParams": {
                              "aliBizCode": "ali.china.taobao",
                              "aliBizCodeToken": "YWxpLmNoaW5hLnRhb2Jhbw=="
                            },
                            "dinamic+TB_detail_ask_all_aliMedical": {
                              "module": "tb_detail_ask_all_aliMedical"
                            },
                            "dinamic+TB_detail_ask_all_no_question": {
                              "module": "tb_detail_ask_all_no_question"
                            },
                            "dinamic+TB_detail_ask_all_two_questions": {
                              "module": "tb_detail_ask_all_two_questions"
                            },
                            "dinamic+TB_detail_brand_info": {
                              "module": "tb_detail_brand_info"
                            },
                            "dinamic+TB_detail_buyer_photo": {
                              "module": "tb_detail_buyer_photo"
                            },
                            "dinamic+TB_detail_comment_empty": {
                              "module": "tb_detail_comment_empty"
                            },
                            "dinamic+TB_detail_coupon": {
                              "module": "tb_detail_coupon"
                            },
                            "dinamic+TB_detail_delivery": {
                              "module": "tb_detail_delivery"
                            },
                            "dinamic+TB_detail_endorsement": {
                              "module": "tb_detail_endorsement"
                            },
                            "dinamic+TB_detail_kernel_params": {
                              "module": "tb_detail_kernel_params"
                            },
                            "dinamic+TB_detail_logistics": {
                              "module": "tb_detail_logistics"
                            },
                            "dinamic+TB_detail_new_person_bag_banner": {
                              "module": "tb_detail_new_person_bag_banner"
                            },
                            "dinamic+TB_detail_price_coupon": {
                              "module": "tb_detail_price_coupon"
                            },
                            "dinamic+TB_detail_priced_coupon": {
                              "module": "tb_detail_priced_coupon"
                            },
                            "dinamic+TB_detail_ship_time": {
                              "module": "tb_detail_ship_time"
                            },
                            "dinamic+TB_detail_subInfo_jhs_normal": {
                              "module": "tb_detail_subInfo_jhs_normal"
                            },
                            "dinamic+TB_detail_subInfo_preSellForTaobaoB": {
                              "module": "tb_detail_subInfo_preSellForTaobaoB"
                            },
                            "dinamic+TB_detail_subInfo_preSellForTaobaoC": {
                              "module": "tb_detail_subInfo_preSellForTaobaoC"
                            },
                            "dinamic+TB_detail_subInfo_superMarket": {
                              "module": "tb_detail_subInfo_superMarket"
                            },
                            "dinamic+TB_detail_sub_logistics": {
                              "module": "tb_detail_sub_logistics"
                            },
                            "dinamic+TB_detail_tax": {
                              "module": "tb_detail_tax"
                            },
                            "dinamic+TB_detail_tip_presale": {
                              "module": "tb_detail_tip_presale"
                            },
                            "dinamic+TB_detail_tip_presale2": {
                              "module": "tb_detail_tip_presale2"
                            },
                            "dinamic+TB_detail_tip_price": {
                              "module": "tb_detail_tip_price"
                            },
                            "dinamic+TB_detail_title_tmallMarket": {
                              "module": "tb_detail_title_tmallMarket"
                            },
                            "dinamic+TB_detail_title_xinxuan": {
                              "module": "tb_detail_title_xinxuan"
                            },
                            "dinamic+TB_detail_tmallfeature": {
                              "module": "tb_detail_tmallfeature"
                            },
                            "dinamic_o2o+TB_detail_o2o": {
                              "module": "TB_detail_o2o"
                            }
                          },
                          "trade": {
                            "buyEnable": "true",
                            "buyParam": [],
                            "buyUrl": "buildOrderVersion=3.0",
                            "cartEnable": "true",
                            "cartJumpUrl": "https://h5.m.taobao.com/awp/base/cart.htm",
                            "cartParam": [],
                            "isBanSale4Oversea": "false"
                          },
                          "tradeConsumerProtection": {
                            "passValue": "all",
                            "tradeConsumerService": {
                              "nonService": {
                                "items": [
                                  {
                                    "icon": "//gw.alicdn.com/tfs/TB1O4sFQpXXXXb3apXXXXXXXXXX-200-200.png",
                                    "title": "集分宝"
                                  },
                                  {
                                    "icon": "//gw.alicdn.com/tfs/TB1O4sFQpXXXXb3apXXXXXXXXXX-200-200.png",
                                    "title": "支付宝支付"
                                  }
                                ],
                                "title": "其他"
                              },
                              "service": {
                                "icon": "",
                                "items": [
                                  {
                                    "title": "付款后48小时内发货"
                                  },
                                  {
                                    "desc": "8天退货，退货邮费买家承担",
                                    "icon": "//gw.alicdn.com/tfs/TB1O4sFQpXXXXb3apXXXXXXXXXX-200-200.png",
                                    "title": "8天退货"
                                  },
                                  {
                                    "desc": "商品在运输途中出现破损的，消费者可向卖家提出补寄申请，可补寄1次，补寄邮费由买家承担",
                                    "icon": "//gw.alicdn.com/tfs/TB1O4sFQpXXXXb3apXXXXXXXXXX-200-200.png",
                                    "title": "1次破损补寄"
                                  },
                                  {
                                    "desc": "购买该商品，每笔成交都会有相应金额捐赠给公益。感谢您的支持，愿公益的快乐伴随您每一天。",
                                    "icon": "//gw.alicdn.com/tfs/TB1O4sFQpXXXXb3apXXXXXXXXXX-200-200.png",
                                    "title": "公益宝贝"
                                  }
                                ],
                                "title": "基础服务"
                              }
                            },
                            "type": "0",
                            "url": "https://h5.m.taobao.com/app/detailsubpage/consumer/index.js"
                          },
                          "vertical": {
                            "askAll": {
                              "askIcon": "https://img.alicdn.com/tps/TB1tVU6PpXXXXXFaXXXXXXXXXXX-102-60.png",
                              "askText": "宝贝好不好，问问已买的人",
                              "linkUrl": "//market.m.taobao.com/app/mtb/questions-and-answers/pages/list/index.html?refId=520813250866",
                              "questNum": "0",
                              "title": "问大家"
                            },
                            "newFissionCouponDetailActivity": {
                              "icon": "https://img.alicdn.com/tfs/TB1Cn6MxYr1gK0jSZFDXXb9yVXa-89-28.png",
                              "linkUrl": "poplayer://20191111m?openType=directly&uuid=1574747999811&type=webview&params=%7B%22url%22%3A%22https%3A%2F%2Fpages.tmall.com%2Fwow%2Fhdwk%2Fact%2Ffisson-poplayer%3Fwh_biz%3Dtm%26sellerId%3D2596264565%26token%3Dd7ec8679d21d4923de3d7c60390115ae869013668453c17fa79a30b8eb96d3356c88fc8d33bc7a5515cc71bfcc050c86%26couponType%3D0%26itemId%3D520813250866%22%7D&embed=false&enqueue=false&times=0&showCloseBtn=false",
                              "showTxt": [
                                {
                                  "color": "#111111",
                                  "size": "13",
                                  "text": "邀请好友得"
                                },
                                {
                                  "color": "#FF5000",
                                  "size": "13",
                                  "text": "2元"
                                },
                                {
                                  "color": "#111111",
                                  "size": "13",
                                  "text": "优惠券"
                                }
                              ],
                              "title": "分享"
                            },
                            "overSeas": []
                          },
                          "weappData": []
                        }
                      }
                    ],
                    "debug": {
                      "app": "alidetail",
                      "host": "detail033005056142.center.na610@33.5.56.142"
                    },
                    "feature": {
                      "showSkuProRate": true
                    },
                    "getdesc": [
                      "http://img.alicdn.com/imgextra/i3/2596264565/TB2Sci2jXXXXXXFXpXXXXXXXXXX_!!2596264565.png",
                      "http://img.alicdn.com/imgextra/i3/2596264565/TB2wWohmXXXXXX8XXXXXXXXXXXX_!!2596264565.jpg",
                      "http://img.alicdn.com/imgextra/i4/2596264565/TB2_uiXnFXXXXXBXXXXXXXXXXXX_!!2596264565.jpg_q90.jpg",
                      "http://img.alicdn.com/imgextra/i4/2596264565/TB2Gm9xnFXXXXbmXXXXXXXXXXXX_!!2596264565.jpg_q90.jpg",
                      "http://img.alicdn.com/imgextra/i3/2596264565/TB28Ox4b77OyuJjSsplXXXqdpXa_!!2596264565.jpg",
                      "http://img.alicdn.com/imgextra/i1/2596264565/TB2.mTddVXXXXbeXpXXXXXXXXXX_!!2596264565.jpg",
                      "http://img.alicdn.com/imgextra/i3/2596264565/TB21Ro.jl0lpuFjSszdXXcdxFXa_!!2596264565.jpg",
                      "http://img.alicdn.com/imgextra/i4/2596264565/TB2q9CelVXXXXcUXXXXXXXXXXXX_!!2596264565.jpg",
                      "http://img.alicdn.com/imgextra/i3/2596264565/TB21EX9lVXXXXaXXpXXXXXXXXXX_!!2596264565.jpg",
                      "http://img.alicdn.com/imgextra/i3/2596264565/TB2udCylVXXXXXgXXXXXXXXXXXX_!!2596264565.jpg_q90.jpg",
                      "http://img.alicdn.com/imgextra/i1/2596264565/TB2EARxjB8lpuFjSspaXXXJKpXa_!!2596264565.jpg",
                      "http://img.alicdn.com/imgextra/i3/2596264565/TB2ssuwlVXXXXafXXXXXXXXXXXX_!!2596264565.jpg_q90.jpg",
                      "http://img.alicdn.com/imgextra/i2/2596264565/TB2nAHqgyC9MuFjSZFoXXbUzFXa_!!2596264565.jpg",
                      "http://img.alicdn.com/imgextra/i3/2596264565/TB2ahCelVXXXXc_XXXXXXXXXXXX_!!2596264565.jpg_q90.jpg",
                      "http://img.alicdn.com/imgextra/i3/2596264565/TB2w1JnjwRkpuFjy1zeXXc.6FXa_!!2596264565.jpg",
                      "http://img.alicdn.com/imgextra/i3/2596264565/TB2C902lVXXXXbnXpXXXXXXXXXX_!!2596264565.jpg_q90.jpg",
                      "http://img.alicdn.com/imgextra/i1/2596264565/TB2sGR3lVXXXXblXpXXXXXXXXXX_!!2596264565.jpg_q90.jpg",
                      "http://img.alicdn.com/imgextra/i1/2596264565/TB2ZBGxlVXXXXXMXXXXXXXXXXXX_!!2596264565.jpg_q90.jpg",
                      "http://img.alicdn.com/imgextra/i1/2596264565/TB2MjWklVXXXXcaXXXXXXXXXXXX_!!2596264565.jpg_q90.jpg",
                      "http://img.alicdn.com/imgextra/i2/2596264565/TB2UgV3lVXXXXbdXpXXXXXXXXXX_!!2596264565.jpg_q90.jpg",
                      "http://img.alicdn.com/imgextra/i2/2596264565/TB2ip5XlVXXXXX2XpXXXXXXXXXX_!!2596264565.jpg_q90.jpg",
                      "http://img.alicdn.com/imgextra/i2/2596264565/TB2sMTBdVXXXXXlXXXXXXXXXXXX_!!2596264565.jpg_q90.jpg",
                      "http://img.alicdn.com/imgextra/i3/2596264565/TB2d3HfdVXXXXahXpXXXXXXXXXX_!!2596264565.jpg_q90.jpg",
                      "http://img.alicdn.com/imgextra/i4/2596264565/TB2AVbBdVXXXXXkXXXXXXXXXXXX_!!2596264565.jpg_q90.jpg",
                      "http://img.alicdn.com/imgextra/i2/2596264565/TB2nf_wdVXXXXaMXXXXXXXXXXXX_!!2596264565.jpg_q90.jpg",
                      "http://img.alicdn.com/imgextra/i4/2596264565/TB2dLYddVXXXXbtXpXXXXXXXXXX_!!2596264565.jpg_q90.jpg",
                      "http://img.alicdn.com/imgextra/i2/2596264565/TB2H1_adVXXXXbWXpXXXXXXXXXX_!!2596264565.jpg_q90.jpg",
                      "http://img.alicdn.com/imgextra/i2/2596264565/TB2eBzsdVXXXXbuXXXXXXXXXXXX_!!2596264565.jpg_q90.jpg",
                      "http://img.alicdn.com/imgextra/i4/2596264565/TB2dOTndVXXXXcCXXXXXXXXXXXX_!!2596264565.jpg_q90.jpg",
                      "http://img.alicdn.com/imgextra/i2/2596264565/TB2fK2tdVXXXXbkXXXXXXXXXXXX_!!2596264565.jpg_q90.jpg",
                      "http://img.alicdn.com/imgextra/i2/2596264565/TB29zjedVXXXXaFXpXXXXXXXXXX_!!2596264565.jpg_q90.jpg",
                      "http://img.alicdn.com/imgextra/i3/2596264565/TB2i7rmdVXXXXcTXXXXXXXXXXXX_!!2596264565.jpg_q90.jpg",
                      "https://g.alicdn.com/i/popshop/0.0.23/p/seemore/load.js?c"
                    ],
                    "item": {
                      "brandValueId": "4036703",
                      "cartUrl": "https://h5.m.taobao.com/awp/base/cart.htm",
                      "categoryId": "50014822",
                      "commentCount": "11",
                      "countMultiple": [],
                      "exParams": [],
                      "favcount": "4824",
                      "h5moduleDescUrl": "//mdetail.tmall.com/templates/pages/itemDesc?id=520813250866",
                      "images": [
                        "//img.alicdn.com/imgextra/i4/2596264565/O1CN01mbmuAB1jaogMUWhv8_!!2596264565.jpg",
                        "//img.alicdn.com/imgextra/i3/2596264565/O1CN01dVHKvp1jaogIQyUBv_!!2596264565.jpg",
                        "//img.alicdn.com/imgextra/i1/2596264565/TB2a.x.lVXXXXXPXpXXXXXXXXXX_!!2596264565.jpg",
                        "//img.alicdn.com/imgextra/i4/2596264565/TB2p30elFXXXXXQXpXXXXXXXXXX_!!2596264565.jpg",
                        "//img.alicdn.com/imgextra/i4/2596264565/TB2j2cTXib_F1JjSZFzXXc6KXXa_!!2596264565.jpg"
                      ],
                      "itemId": "520813250866",
                      "moduleDescParams": {
                        "f": "i7/520/810/520813250866/TB1svrvqAcx_u4jSZFl8qtnUFla",
                        "id": "520813250866"
                      },
                      "moduleDescUrl": "//hws.m.taobao.com/d/modulet/v5/WItemMouldDesc.do?id=520813250866&f=TB1svrvqAcx_u4jSZFl8qtnUFla",
                      "openDecoration": false,
                      "rootCategoryId": "50013886",
                      "skuText": "请选择颜色分类 ",
                      "subtitle": "",
                      "taobaoDescUrl": "//h5.m.taobao.com/app/detail/desc.html?_isH5Des=true#!id=520813250866&type=0&f=TB1qrqXuk9l0K4jSZFK8qtFjpla&sellerType=C",
                      "taobaoPcDescUrl": "//h5.m.taobao.com/app/detail/desc.html?_isH5Des=true#!id=520813250866&type=1&f=TB1zAmscF67gK0jSZPf8quhhFla&sellerType=C",
                      "title": "三刃木折叠刀过安检创意迷你钥匙扣钥匙刀军刀随身多功能小刀包邮",
                      "tmallDescUrl": "//mdetail.tmall.com/templates/pages/desc?id=520813250866"
                    },
                    "mockData": {
                      "delivery": [],
                      "feature": {
                        "hasSku": true,
                        "showSku": true
                      },
                      "price": {
                        "price": {
                          "priceText": "25.80"
                        }
                      },
                      "skuCore": {
                        "sku2info": {
                          "0": {
                            "price": {
                              "priceMoney": 2580,
                              "priceText": "25.80",
                              "priceTitle": "价格"
                            },
                            "quantity": 600
                          },
                          "3144644292458": {
                            "price": {
                              "priceMoney": 2580,
                              "priceText": "25.80",
                              "priceTitle": "价格"
                            },
                            "quantity": 100
                          },
                          "3161107666655": {
                            "price": {
                              "priceMoney": 6380,
                              "priceText": "63.80",
                              "priceTitle": "价格"
                            },
                            "quantity": 100
                          },
                          "3161300228969": {
                            "price": {
                              "priceMoney": 9180,
                              "priceText": "91.80",
                              "priceTitle": "价格"
                            },
                            "quantity": 100
                          },
                          "3161300228970": {
                            "price": {
                              "priceMoney": 7380,
                              "priceText": "73.80",
                              "priceTitle": "价格"
                            },
                            "quantity": 100
                          },
                          "3166598625984": {
                            "price": {
                              "priceMoney": 3900,
                              "priceText": "39.00",
                              "priceTitle": "价格"
                            },
                            "quantity": 100
                          },
                          "3166598625985": {
                            "price": {
                              "priceMoney": 3900,
                              "priceText": "39.00",
                              "priceTitle": "价格"
                            },
                            "quantity": 100
                          }
                        },
                        "skuItem": {
                          "hideQuantity": true
                        }
                      },
                      "trade": {
                        "buyEnable": true,
                        "cartEnable": true
                      }
                    },
                    "params": {
                      "trackParams": {
                        "BC_type": "C",
                        "brandId": "4036703",
                        "categoryId": "50014822"
                      }
                    },
                    "props": {
                      "groupProps": [
                        {
                          "基本信息": [
                            {
                              "品牌": "三刃木"
                            },
                            {
                              "产地": "中国"
                            },
                            {
                              "颜色分类": "长方形带开瓶器+送工具刀卡+链子,椭圆形带开瓶器+送工具刀卡+链子,GJ018X钥匙刀+送工具刀卡+链子,超凡大师套餐【送工具卡+链子】,最强王者套餐【送工具卡+链子】,璀璨钻石套餐【送工具卡+链子】"
                            },
                            {
                              "吊牌价": "46"
                            },
                            {
                              "功能数量": "5个及以下"
                            },
                            {
                              "货号": "GJ019C"
                            },
                            {
                              "附加功能": "开瓶器,刀,螺丝刀,钥匙圈,其他"
                            }
                          ]
                        }
                      ]
                    },
                    "props2": [],
                    "propsCut": "品牌 产地 颜色分类 吊牌价 功能数量 货号 附加功能 ",
                    "rate": {
                      "propRate": [],
                      "totalCount": "11"
                    },
                    "resource": {
                      "entrances": {
                        "askAll": {
                          "icon": "https://img.alicdn.com/tps/TB1tVU6PpXXXXXFaXXXXXXXXXXX-102-60.png",
                          "link": "//market.m.taobao.com/app/mtb/questions-and-answers/pages/list/index.html?refId=520813250866",
                          "text": "\\"小刀打开后有锁吗\\""
                        }
                      }
                    },
                    "seller": {
                      "allItemCount": "33",
                      "atmophereMask": false,
                      "atmosphereColor": "#ffffff",
                      "creditLevel": "12",
                      "creditLevelIcon": "//gw.alicdn.com/tfs/TB1zuz8ir_I8KJjy1XaXXbsxpXa-132-24.png",
                      "entranceList": [
                        {
                          "action": [
                            {
                              "key": "open_url",
                              "params": {
                                "url": "//shop.m.taobao.com/shop/shop_index.htm?user_id=2596264565&item_id=520813250866&currentClickTime=-1"
                              }
                            },
                            {
                              "key": "user_track",
                              "params": {
                                "trackName": "Button-NewShopcard-ShopPage",
                                "trackParams": {
                                  "spm": "a.2141.7631564.shoppage"
                                }
                              }
                            }
                          ],
                          "backgroundColor": "#ffffff",
                          "borderColor": "#FF5000",
                          "text": "进店逛逛",
                          "textColor": "#FF5000"
                        },
                        {
                          "action": [
                            {
                              "key": "open_url",
                              "params": {
                                "url": "//shop.m.taobao.com/shop/shop_index.htm?user_id=2596264565&item_id=520813250866&shop_navi=allitems"
                              }
                            },
                            {
                              "key": "user_track",
                              "params": {
                                "trackName": "Button-NewShopcard-AllItem",
                                "trackParams": {
                                  "spm": "a.2141.7631564.allitem"
                                }
                              }
                            }
                          ],
                          "backgroundColor": "#ffffff",
                          "borderColor": "#FF5000",
                          "text": "全部宝贝",
                          "textColor": "#FF5000"
                        }
                      ],
                      "evaluates": [
                        {
                          "level": "1",
                          "levelBackgroundColor": "#FFF1EB",
                          "levelText": "高",
                          "levelTextColor": "#FF5000",
                          "score": "4.8 ",
                          "title": "宝贝描述",
                          "tmallLevelBackgroundColor": "#FFF1EB",
                          "tmallLevelTextColor": "#FF0036",
                          "type": "desc"
                        },
                        {
                          "level": "1",
                          "levelBackgroundColor": "#FFF1EB",
                          "levelText": "高",
                          "levelTextColor": "#FF5000",
                          "score": "4.8 ",
                          "title": "卖家服务",
                          "tmallLevelBackgroundColor": "#FFF1EB",
                          "tmallLevelTextColor": "#FF0036",
                          "type": "serv"
                        },
                        {
                          "level": "1",
                          "levelBackgroundColor": "#FFF1EB",
                          "levelText": "高",
                          "levelTextColor": "#FF5000",
                          "score": "4.8 ",
                          "title": "物流服务",
                          "tmallLevelBackgroundColor": "#FFF1EB",
                          "tmallLevelTextColor": "#FF0036",
                          "type": "post"
                        }
                      ],
                      "evaluates2": [
                        {
                          "level": "1",
                          "levelText": "高",
                          "levelTextColor": "#FF7333",
                          "score": "4.8 ",
                          "scoreTextColor": "#999999",
                          "title": "宝贝描述",
                          "titleColor": "#999999",
                          "type": "desc"
                        },
                        {
                          "level": "1",
                          "levelText": "高",
                          "levelTextColor": "#FF7333",
                          "score": "4.8 ",
                          "scoreTextColor": "#999999",
                          "title": "卖家服务",
                          "titleColor": "#999999",
                          "type": "serv"
                        },
                        {
                          "level": "1",
                          "levelText": "高",
                          "levelTextColor": "#FF7333",
                          "score": "4.8 ",
                          "scoreTextColor": "#999999",
                          "title": "物流服务",
                          "titleColor": "#999999",
                          "type": "post"
                        }
                      ],
                      "fans": "1464",
                      "fbt2User": "欢乐购客栈",
                      "goodRatePercentage": "99.59%",
                      "newItemCount": "11",
                      "sellerNick": "欢乐购客栈",
                      "sellerType": "C",
                      "shopCard": "掌柜近期上新11件宝贝，速览",
                      "shopIcon": "//img.alicdn.com/imgextra//a8/d5/TB1NckwKpXXXXaDXpXXtKXbFXXX.gif",
                      "shopId": "127203758",
                      "shopName": "欢乐购客栈",
                      "shopTextColor": "#111111",
                      "shopType": "C",
                      "shopUrl": "tmall://page.tm/shop?item_id=520813250866&shopId=127203758",
                      "shopVersion": "0",
                      "showShopLinkIcon": false,
                      "simpleShopDOStatus": "1",
                      "starts": "2015-07-19 13:45:52",
                      "taoShopUrl": "//shop.m.taobao.com/shop/shop_index.htm?user_id=2596264565&item_id=520813250866",
                      "userId": "2596264565"
                    },
                    "skuBase": {
                      "props": [
                        {
                          "name": "颜色分类",
                          "pid": "1627207",
                          "values": [
                            {
                              "image": "//img.alicdn.com/imgextra/i3/2596264565/TB2.XeblVXXXXXkXpXXXXXXXXXX_!!2596264565.jpg",
                              "name": "长方形带开瓶器+送工具刀卡+链子",
                              "vid": "1347647754"
                            },
                            {
                              "image": "//img.alicdn.com/imgextra/i4/2596264565/TB2dTrjdVXXXXXBXpXXXXXXXXXX_!!2596264565.jpg",
                              "name": "椭圆形带开瓶器+送工具刀卡+链子",
                              "vid": "1347647753"
                            },
                            {
                              "image": "//img.alicdn.com/imgextra/i2/2596264565/TB2j22kdVXXXXXdXpXXXXXXXXXX_!!2596264565.jpg",
                              "name": "GJ018X钥匙刀+送工具刀卡+链子",
                              "vid": "1195392087"
                            },
                            {
                              "image": "//img.alicdn.com/imgextra/i4/2596264565/TB2_uiXnFXXXXXBXXXXXXXXXXXX_!!2596264565.jpg",
                              "name": "超凡大师套餐【送工具卡+链子】",
                              "vid": "1331112595"
                            },
                            {
                              "image": "//img.alicdn.com/imgextra/i4/2596264565/TB2Gm9xnFXXXXbmXXXXXXXXXXXX_!!2596264565.jpg",
                              "name": "最强王者套餐【送工具卡+链子】",
                              "vid": "1331112594"
                            },
                            {
                              "image": "//img.alicdn.com/imgextra/i3/2596264565/TB2wWohmXXXXXX8XXXXXXXXXXXX_!!2596264565.jpg",
                              "name": "璀璨钻石套餐【送工具卡+链子】",
                              "vid": "1331264247"
                            }
                          ]
                        }
                      ],
                      "skus": [
                        {
                          "propPath": "1627207:1347647754",
                          "skuId": "3166598625985"
                        },
                        {
                          "propPath": "1627207:1347647753",
                          "skuId": "3166598625984"
                        },
                        {
                          "propPath": "1627207:1195392087",
                          "skuId": "3144644292458"
                        },
                        {
                          "propPath": "1627207:1331112595",
                          "skuId": "3161300228970"
                        },
                        {
                          "propPath": "1627207:1331112594",
                          "skuId": "3161300228969"
                        },
                        {
                          "propPath": "1627207:1331264247",
                          "skuId": "3161107666655"
                        }
                      ]
                    },
                    "data_from": "app"
                  },
                  "secache": "ecf6b4e1d1caa69e78467a9f5606f207",
                  "secache_time": 1608558617,
                  "secache_date": "2020-12-21 21:50:17",
                  "error": "",
                  "reason": "",
                  "error_code": "0000",
                  "cache": 1,
                  "api_info": "today:65 max:10000",
                  "execution_time": 0.077,
                  "server_time": "Beijing/2020-12-22 15:51:43",
                  "client_ip": "115.151.184.188",
                  "call_args": {
                    "num_iid": "520813250866"
                  },
                  "api_type": "taobao",
                  "translate_language": "zh-CN",
                  "translate_engine": "google_cn",
                  "server_memory": "5.61MB",
                  "request_id": "1.5fe1a58f1d970"
                }
                """;


    }


}
