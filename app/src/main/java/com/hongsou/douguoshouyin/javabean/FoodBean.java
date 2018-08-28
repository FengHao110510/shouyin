package com.hongsou.douguoshouyin.javabean;

import com.hongsou.douguoshouyin.db.FoodZuheTaocanXQ;

import java.util.List;

/**
 * @author lpc
 * <p>
 * @copyright 鸿搜网络公司 版权所有
 * <p>
 * @date 2018/7/16
 * <p>
 * @desc
 */

public class FoodBean {


    /**
     * code : 1000
     * data : [{"cateGoryName":"饮品","cateGoryType":"0488C2DF-61ED-4251-AA96-F67FE2896F22","cateSerialNumber":"6","deleteFlag":0,"foodProductsPicture":"/www/server/tomcat/shopPic/1000180427300326890/20180612164825580854.png--","foodType":"1","page":0,"phr":"1","rows":0,"selling":"0","sellingStatus":"0","ShopStandarList":[{"deleteFlag":0,"foodProductsNumber":"91a8ef6e41f153a2fe4d09020a9f3f9b","page":0,"rows":0,"sell":"5.00","shopNumber":"1000180427300326890","standardName":"小杯","standardNumber":"180427111118129","vipPrice":"4.5000"}],"singleProductName":"可乐","singleProductNumber":"91a8ef6e41f153a2fe4d09020a9f3f9b","singleProductType":"0488C2DF-61ED-4251-AA96-F67FE2896F22","theKitchenPrint":"0"},{"cateGoryName":"饮品","cateGoryType":"0488C2DF-61ED-4251-AA96-F67FE2896F22","cateSerialNumber":"6","deleteFlag":0,"foodProductsPicture":"img/1000180427300326890/20180427140336005953.png--","foodType":"1","page":0,"phr":"1","rows":0,"selling":"1","sellingStatus":"0","ShopStandarList":[{"deleteFlag":0,"foodProductsNumber":"a9c91ab3ad7c4507108d9e12717f2f55","page":0,"rows":0,"sell":"8.00","shopNumber":"1000180427300326890","standardName":"大杯","standardNumber":"180427111116908","vipPrice":"7.5000"}],"singleProductName":"芬达","singleProductNumber":"a9c91ab3ad7c4507108d9e12717f2f55","singleProductType":"0488C2DF-61ED-4251-AA96-F67FE2896F22","theKitchenPrint":"0"},{"cateGoryName":"饮品","cateGoryType":"0488C2DF-61ED-4251-AA96-F67FE2896F22","cateSerialNumber":"6","deleteFlag":0,"foodProductsPicture":"","foodType":"1","page":0,"phr":"1","rows":0,"selling":"1","sellingStatus":"0","ShopStandarList":[{"deleteFlag":0,"foodProductsNumber":"CPAEE926","page":0,"rows":0,"sell":"4.00","shopNumber":"1000180427300326890","standardName":"小杯","standardNumber":"180621111113174","vipPrice":"3.0000"}],"singleProductName":"泰国","singleProductNumber":"CPAEE926","singleProductType":"0488C2DF-61ED-4251-AA96-F67FE2896F22","theKitchenPrint":"1"},{"cateGoryName":"饮品","cateGoryType":"0488C2DF-61ED-4251-AA96-F67FE2896F22","cateSerialNumber":"6","deleteFlag":0,"foodProductsPicture":"","foodType":"1","page":0,"phr":"1","rows":0,"selling":"1","sellingStatus":"0","ShopStandarList":[{"deleteFlag":0,"foodProductsNumber":"CPE05579","page":0,"rows":0,"sell":"8.00","shopNumber":"1000180427300326890","standardName":"大","standardNumber":"180615111119041","vipPrice":"7.0000"}],"singleProductName":"蔬果汁","singleProductNumber":"CPE05579","singleProductType":"0488C2DF-61ED-4251-AA96-F67FE2896F22","theKitchenPrint":"1"},{"cateGoryName":"小食","cateGoryType":"6F41A4B0-09E6-4CDE-ACB1-769CEFE87362","cateSerialNumber":"3","deleteFlag":0,"foodProductsPicture":"","foodType":"1","page":0,"phr":"1","rows":0,"selling":"1","sellingStatus":"0","ShopStandarList":[{"deleteFlag":0,"foodProductsNumber":"CP4F2BA8","page":0,"rows":0,"sell":"29.50","shopNumber":"1000180427300326890","standardName":"个","standardNumber":"180710111119043","vipPrice":"29.5000"}],"singleProductName":"测试29.5","singleProductNumber":"CP4F2BA8","singleProductType":"6F41A4B0-09E6-4CDE-ACB1-769CEFE87362","theKitchenPrint":"0"},{"cateGoryName":"小食","cateGoryType":"6F41A4B0-09E6-4CDE-ACB1-769CEFE87362","cateSerialNumber":"3","deleteFlag":0,"foodProductsPicture":"","foodType":"1","page":0,"phr":"1","rows":0,"selling":"1","sellingStatus":"0","ShopStandarList":[{"deleteFlag":0,"foodProductsNumber":"CP696F36","page":0,"rows":0,"sell":"15.00","shopNumber":"1000180427300326890","standardName":"份","standardNumber":"180516111113456","vipPrice":"15.0000"}],"singleProductName":"特大份薯条","singleProductNumber":"CP696F36","singleProductType":"6F41A4B0-09E6-4CDE-ACB1-769CEFE87362","theKitchenPrint":"1"},{"cateGoryName":"小食","cateGoryType":"6F41A4B0-09E6-4CDE-ACB1-769CEFE87362","cateSerialNumber":"3","deleteFlag":0,"foodProductsPicture":"","foodType":"1","page":0,"phr":"1","rows":0,"selling":"1","sellingStatus":"0","ShopStandarList":[{"deleteFlag":0,"foodProductsNumber":"CP98D126","page":0,"rows":0,"sell":"0.01","shopNumber":"1000180427300326890","standardName":"个","standardNumber":"180530111116819","vipPrice":"0.0100"}],"singleProductName":"找零","singleProductNumber":"CP98D126","singleProductType":"6F41A4B0-09E6-4CDE-ACB1-769CEFE87362","theKitchenPrint":"1"},{"cateSerialNumber":"5","deleteFlag":0,"endTime":"1900-01-01 00:00:00","foodType":"0","list":[{"cateGoryName":"小食","cateSerialNumber":"3","deleteFlag":0,"foodProductsPicture":"img/1000180427300326890/20180427142313829058.png--","packageName":"特大份豪华套餐","packageNumber":"TC30B651","page":0,"remind":"1","rows":0,"serialNumber":"1","singleProductName":"鸡翅","singleProductNumber":"1549d7f91c3478e24f7ec2bd2906a31b","singleQuantity":"1","standardName":"一对","standardNumber":"180427111119253"},{"cateGoryName":"小食","cateSerialNumber":"3","deleteFlag":0,"foodProductsPicture":"","packageName":"特大份豪华套餐","packageNumber":"TC30B651","page":0,"remind":"1","rows":0,"serialNumber":"1","singleProductName":"特大份薯条","singleProductNumber":"CP696F36","singleQuantity":"1","standardName":"份","standardNumber":"180516111113456"},{"cateGoryName":"汉堡","cateSerialNumber":"4","deleteFlag":0,"foodProductsPicture":"","packageName":"特大份豪华套餐","packageNumber":"TC30B651","page":0,"remind":"1","rows":0,"serialNumber":"2","singleProductName":"特大份汉堡","singleProductNumber":"CPE8E77A","singleQuantity":"1","standardName":"个","standardNumber":"180516111114454"},{"cateGoryName":"饮品","cateSerialNumber":"6","deleteFlag":0,"foodProductsPicture":"","packageName":"特大份豪华套餐","packageNumber":"TC30B651","page":0,"remind":"1","rows":0,"serialNumber":"1","singleProductName":"蔬果汁","singleProductNumber":"CPE05579","singleQuantity":"1","standardName":"大","standardNumber":"180615111119041"}],"mealCode":"000326","packageInitials":"TDFHHTC","packageName":"特大份豪华套餐","packageNumber":"TC30B651","packagePrice":"15.00","packageType":"33331B06-095F-42D8-B8A8-B96D9D05881E","page":0,"phr":"1","rows":0,"selling":"1","sellingStatus":"0","serialNumber":"13","startTime":"1900-01-01 00:00:00","takeout":"1","theKitchenPrint":"1","vipprice":"0.00"},{"cateSerialNumber":"2","deleteFlag":0,"endTime":"1900-01-01 00:00:00","foodType":"0","list":[{"cateGoryName":"饮品","cateSerialNumber":"6","deleteFlag":0,"foodProductsPicture":"/www/server/tomcat/shopPic/1000180427300326890/20180612164825580854.png--","packageName":"测试固定套餐是否正常","packageNumber":"TC5F25A9","page":0,"remind":"1","rows":0,"serialNumber":"1","singleProductName":"可乐","singleProductNumber":"91a8ef6e41f153a2fe4d09020a9f3f9b","singleQuantity":"1","standardName":"小杯","standardNumber":"180427111118129"},{"cateGoryName":"汉堡","cateSerialNumber":"4","deleteFlag":0,"foodProductsPicture":"/www/server/tomcat/shopPic/1000180427300326890/20180517141641744505.png--","packageName":"测试固定套餐是否正常","packageNumber":"TC5F25A9","page":0,"remind":"1","rows":0,"serialNumber":"2","singleProductName":"汉堡","singleProductNumber":"84a7b53aaf49ebf4916427c6168fe0f5","singleQuantity":"1","standardName":"一个","standardNumber":"180427111119120"},{"cateGoryName":"汉堡","cateSerialNumber":"4","deleteFlag":0,"foodProductsPicture":"/www/server/tomcat/shopPic//1000180427300326890/20180514181515681809.png--/www/server/tomcat/shopPic//1000180427300326890/20180514181515680549.png--","packageName":"测试固定套餐是否正常","packageNumber":"TC5F25A9","page":0,"remind":"1","rows":0,"serialNumber":"3","singleProductName":"薯条","singleProductNumber":"CP8C1D19","singleQuantity":"1","standardName":"小份","standardNumber":"180502111118149"}],"mealCode":"010001","packageInitials":"CSGDTCSFZC","packageName":"测试固定套餐是否正常","packageNumber":"TC5F25A9","packagePrice":"12.00","packageType":"FL76673F","page":0,"phr":"1","rows":0,"selling":"1","sellingStatus":"0","serialNumber":"17","startTime":"1900-01-01 00:00:00","takeout":"1","theKitchenPrint":"1","vipprice":"0.00"},{"cateSerialNumber":"5","deleteFlag":0,"endTime":"1900-01-01 00:00:00","foodType":"0","list":[{"cateGoryName":"饮品","cateSerialNumber":"6","deleteFlag":0,"foodProductsPicture":"img/1000180427300326890/20180427140336005953.png--","packageName":"豪华总统套餐","packageNumber":"TC810317","page":0,"remind":"0","rows":0,"serialNumber":"1","singleProductName":"芬达","singleProductNumber":"a9c91ab3ad7c4507108d9e12717f2f55","singleQuantity":"4","standardName":"大杯","standardNumber":"180427111116908"},{"cateGoryName":"小食","cateSerialNumber":"3","deleteFlag":0,"foodProductsPicture":"img/1000180427300326890/20180427142313829058.png--","packageName":"豪华总统套餐","packageNumber":"TC810317","page":0,"remind":"0","rows":0,"serialNumber":"2","singleProductName":"鸡翅","singleProductNumber":"1549d7f91c3478e24f7ec2bd2906a31b","singleQuantity":"6","standardName":"一对","standardNumber":"180427111119253"},{"cateGoryName":"汉堡","cateSerialNumber":"4","deleteFlag":0,"foodProductsPicture":"","packageName":"豪华总统套餐","packageNumber":"TC810317","page":0,"remind":"1","rows":0,"serialNumber":"3","singleProductName":"沙拉酱","singleProductNumber":"CPC0C3B6","singleQuantity":"2","standardName":"大包","standardNumber":"180514111119231"},{"cateGoryName":"汉堡","cateSerialNumber":"4","deleteFlag":0,"foodProductsPicture":"","packageName":"豪华总统套餐","packageNumber":"TC810317","page":0,"remind":"0","rows":0,"serialNumber":"4","singleProductName":"特大份汉堡","singleProductNumber":"CPE8E77A","singleQuantity":"4","standardName":"个","standardNumber":"180516111114454"},{"cateGoryName":"小食","cateSerialNumber":"3","deleteFlag":0,"foodProductsPicture":"","packageName":"豪华总统套餐","packageNumber":"TC810317","page":0,"remind":"0","rows":0,"serialNumber":"5","singleProductName":"特大份薯条","singleProductNumber":"CP696F36","singleQuantity":"1","standardName":"份","standardNumber":"180516111113456"}],"mealCode":"123","packageInitials":"HHZTTC","packageName":"豪华总统套餐","packageNumber":"TC810317","packagePrice":"98.00","packageType":"33331B06-095F-42D8-B8A8-B96D9D05881E","page":0,"phr":"1","rows":0,"selling":"1","sellingStatus":"0","serialNumber":"16","startTime":"1900-01-01 00:00:00","takeout":"0","theKitchenPrint":"0","vipprice":"0.00"},{"cateSerialNumber":"5","deleteFlag":0,"endTime":"1900-01-01 00:00:00","foodType":"0","list":[{"cateGoryName":"汉堡","cateSerialNumber":"4","deleteFlag":0,"foodProductsPicture":"/www/server/tomcat/webapps/hongsou-shop/WEB-INF/classes/1000180427300326890/20180516160742119076.png--","packageName":"周末专享","packageNumber":"TC099DA6","page":0,"remind":"1","rows":0,"serialNumber":"1","singleProductName":"雪碧","singleProductNumber":"178e17b1607773b7a455fdeb6a42a10b","singleQuantity":"1","standardName":"小杯","standardNumber":"180427111114447"},{"cateGoryName":"小食","cateSerialNumber":"3","deleteFlag":0,"foodProductsPicture":"img/1000180427300326890/20180427142313829058.png--","packageName":"周末专享","packageNumber":"TC099DA6","page":0,"remind":"1","rows":0,"serialNumber":"2","singleProductName":"鸡翅","singleProductNumber":"1549d7f91c3478e24f7ec2bd2906a31b","singleQuantity":"1","standardName":"一对","standardNumber":"180427111119253"},{"cateGoryName":"汉堡","cateSerialNumber":"4","deleteFlag":0,"foodProductsPicture":"img/1000180427300326890/20180507164049760258.jpg--","packageName":"周末专享","packageNumber":"TC099DA6","page":0,"remind":"1","rows":0,"serialNumber":"3","singleProductName":"鳕鱼堡","singleProductNumber":"CP5833AB","singleQuantity":"1","standardName":"个","standardNumber":"180507111112559"}],"mealCode":"ZMZX690536","packageInitials":"ZMZX","packageName":"周末专享","packageNumber":"TC099DA6","packagePrice":"88.00","packageType":"33331B06-095F-42D8-B8A8-B96D9D05881E","page":0,"phr":"1","rows":0,"selling":"1","sellingStatus":"0","serialNumber":"12","startTime":"1900-01-01 00:00:00","takeout":"1","theKitchenPrint":"1","vipprice":"0.00"},{"cateSerialNumber":"5","deleteFlag":0,"endTime":"1900-01-01 00:00:00","foodType":"0","list":[{"cateGoryName":"饮品","cateSerialNumber":"6","deleteFlag":0,"foodProductsPicture":"/www/server/tomcat/shopPic/1000180427300326890/20180608142934853523.png--","packageName":"泡菜可乐","packageNumber":"TCD9109A","page":0,"remind":"1","rows":0,"serialNumber":"1","singleProductName":"西式汉堡（店内）","singleProductNumber":"CP8E888E","singleQuantity":"1","standardName":"小个","standardNumber":"180608111119891"},{"cateGoryName":"饮品","cateSerialNumber":"6","deleteFlag":0,"foodProductsPicture":"/www/server/tomcat/shopPic/1000180427300326890/20180612164825580854.png--","packageName":"泡菜可乐","packageNumber":"TCD9109A","page":0,"remind":"1","rows":0,"serialNumber":"2","singleProductName":"可乐","singleProductNumber":"91a8ef6e41f153a2fe4d09020a9f3f9b","singleQuantity":"1","standardName":"小杯","standardNumber":"180427111118129"}],"mealCode":"PCKL650875","packageInitials":"PCKL","packageName":"泡菜可乐","packageNumber":"TCD9109A","packagePrice":"13.00","packageType":"33331B06-095F-42D8-B8A8-B96D9D05881E","page":0,"phr":"1","rows":0,"selling":"1","sellingStatus":"0","serialNumber":"1","startTime":"1900-01-01 00:00:00","takeout":"1","theKitchenPrint":"1","vipprice":"0.00"},{"cateSerialNumber":"5","deleteFlag":0,"endTime":"1900-01-01 00:00:00","foodType":"0","list":[{"cateGoryName":"饮品","cateSerialNumber":"6","deleteFlag":0,"foodProductsPicture":"/www/server/tomcat/shopPic/1000180427300326890/20180612164825580854.png--","packageName":"工作餐","packageNumber":"TC8EF877","page":0,"remind":"1","rows":0,"serialNumber":"1","singleProductName":"可乐","singleProductNumber":"91a8ef6e41f153a2fe4d09020a9f3f9b","singleQuantity":"1","standardName":"小杯","standardNumber":"180427111118129"}],"mealCode":"002","packageInitials":"GZC","packageName":"工作餐","packageNumber":"TC8EF877","packagePrice":"65.00","packageType":"33331B06-095F-42D8-B8A8-B96D9D05881E","page":0,"phr":"1","rows":0,"selling":"1","sellingStatus":"0","serialNumber":"14","startTime":"1900-01-01 00:00:00","takeout":"1","theKitchenPrint":"1","vipprice":"0.00"},{"cateSerialNumber":"5","deleteFlag":0,"endTime":"1900-01-01 00:00:00","foodType":"0","list":[{"cateGoryName":"饮品","cateSerialNumber":"6","deleteFlag":0,"foodProductsPicture":"/www/server/tomcat/shopPic/1000180427300326890/20180612164825580854.png--","packageName":"中秋特惠","packageNumber":"TCAA74DA","page":0,"remind":"0","rows":0,"serialNumber":"1","singleProductName":"可乐","singleProductNumber":"91a8ef6e41f153a2fe4d09020a9f3f9b","singleQuantity":"1","standardName":"小杯","standardNumber":"180427111118129"},{"cateGoryName":"汉堡","cateSerialNumber":"4","deleteFlag":0,"foodProductsPicture":"img/1000180427300326890/20180507164142690401.jpg--","packageName":"中秋特惠","packageNumber":"TCAA74DA","page":0,"remind":"1","rows":0,"serialNumber":"2","singleProductName":"热牛奶","singleProductNumber":"CP705DB9","singleQuantity":"1","standardName":"大杯","standardNumber":"180507111112768"},{"cateGoryName":"汉堡","cateSerialNumber":"4","deleteFlag":0,"foodProductsPicture":"img/1000180427300326890/20180507164142690401.jpg--","packageName":"中秋特惠","packageNumber":"TCAA74DA","page":0,"remind":"1","rows":0,"serialNumber":"1","singleProductName":"热牛奶","singleProductNumber":"CP705DB9","singleQuantity":"1","standardName":"中杯","standardNumber":"180507111113781"}],"mealCode":"0002","packageInitials":"ZQTH","packageName":"中秋特惠","packageNumber":"TCAA74DA","packagePrice":"23.00","packageType":"33331B06-095F-42D8-B8A8-B96D9D05881E","page":0,"phr":"1","rows":0,"selling":"1","sellingStatus":"0","serialNumber":"15","startTime":"1900-01-01 00:00:00","takeout":"1","theKitchenPrint":"1","vipprice":"0.00"},{"cateSerialNumber":"5","deleteFlag":0,"endTime":"1900-01-01 00:00:00","foodType":"0","list":[{"cateGoryName":"小食","cateSerialNumber":"3","deleteFlag":0,"foodProductsPicture":"","packageName":"外卖餐","packageNumber":"TCD1410B","page":0,"remind":"1","rows":0,"serialNumber":"1","singleProductName":"特大份薯条","singleProductNumber":"CP696F36","singleQuantity":"0","standardName":"份","standardNumber":"180516111113456"},{"cateGoryName":"组合套餐","cateSerialNumber":"2","deleteFlag":0,"foodProductsPicture":"","packageName":"外卖餐","packageNumber":"TCD1410B","page":0,"remind":"1","rows":0,"serialNumber":"2","singleProductName":"旺旺酥","singleProductNumber":"CPAD4818","singleQuantity":"0","standardName":"大","standardNumber":"180529111119495"},{"cateGoryName":"汉堡","cateSerialNumber":"4","deleteFlag":0,"foodProductsPicture":"","packageName":"外卖餐","packageNumber":"TCD1410B","page":0,"remind":"1","rows":0,"serialNumber":"1","singleProductName":"沙拉酱","singleProductNumber":"CPC0C3B6","singleQuantity":"0","standardName":"大包","standardNumber":"180514111119231"},{"cateGoryName":"小食","cateSerialNumber":"3","deleteFlag":0,"foodProductsPicture":"","packageName":"外卖餐","packageNumber":"TCD1410B","page":0,"remind":"1","rows":0,"serialNumber":"1","singleProductName":"找零","singleProductNumber":"CP98D126","singleQuantity":"0","standardName":"个","standardNumber":"180530111116819"},{"cateGoryName":"汉堡","cateSerialNumber":"4","deleteFlag":0,"foodProductsPicture":"","packageName":"外卖餐","packageNumber":"TCD1410B","page":0,"remind":"1","rows":0,"serialNumber":"1","singleProductName":"沙拉酱","singleProductNumber":"CPC0C3B6","singleQuantity":"0","standardName":"小包","standardNumber":"180514111114870"},{"cateGoryName":"饮品","cateSerialNumber":"6","deleteFlag":0,"foodProductsPicture":"/www/server/tomcat/shopPic/1000180427300326890/20180608142934853523.png--","packageName":"外卖餐","packageNumber":"TCD1410B","page":0,"remind":"1","rows":0,"serialNumber":"1","singleProductName":"西式汉堡（店内）","singleProductNumber":"CP8E888E","singleQuantity":"0","standardName":"小个","standardNumber":"180608111119891"}],"mealCode":"WMC379395","packageInitials":"WMC","packageName":"外卖餐","packageNumber":"TCD1410B","packagePrice":"43.00","packageType":"33331B06-095F-42D8-B8A8-B96D9D05881E","page":0,"phr":"1","rows":0,"selling":"1","sellingStatus":"0","serialNumber":"32","startTime":"1900-01-01 00:00:00","takeout":"1","theKitchenPrint":"1","vipprice":"0.00"},{"cateGoryType":"FL76673F","foodType":"2","groupPackageName":"组合L","groupPackageNumber":"ZH180528111116479","groupPackagePrice":"18.00","listTwo":[{"deletionFlag":"0","groupNumber":"FZ180528111114887","groupPackageNumber":"ZH180528111116479","listThree":[[{"foodProductsCount":"1","foodProductsNumber":"CP98D126","foodProductsType":"2","groupCount":"1","groupName":"","groupNumber":"FZ180528111114887","minGroup":"1","packagePicture":"无","priceMarkup":"0.00","remind":"1","replace":"1","shopNumber":"1000180427300326890","singleProductName":"找零","standard":"180530111116819","standardName":"个","standardNumber":"180530111116819"},{"foodProductsCount":"1","foodProductsNumber":"a9c91ab3ad7c4507108d9e12717f2f55","foodProductsType":"2","groupCount":"1","groupName":"分组AB","groupNumber":"FZ180528111114887","minGroup":"1","packagePicture":"无","priceMarkup":"0.00","remind":"1","replace":"0","shopNumber":"1000180427300326890","singleProductName":"芬达","standard":"180427111116908","standardName":"大杯","standardNumber":"180427111116908"},{"foodProductsCount":"1","foodProductsNumber":"CPE8E77A","foodProductsType":"2","groupCount":"1","groupName":"分组AB","groupNumber":"FZ180528111114887","minGroup":"1","packagePicture":"无","priceMarkup":"0.00","remind":"1","replace":"1","shopNumber":"1000180427300326890","singleProductName":"特大份汉堡","standard":"180516111114454","standardName":"个","standardNumber":"180516111114454"}],[{"foodProductsCount":"1","foodProductsNumber":"CP8E888E","foodProductsType":"2","groupCount":"1","groupName":"","groupNumber":"FZ180528111114887","minGroup":"10","packagePicture":"无","priceMarkup":"0.00","remind":"1","replace":"1","shopNumber":"1000180427300326890","singleProductName":"西式汉堡（店内）","standard":"180608111119891","standardName":"小个","standardNumber":"180608111119891"}],[{"foodProductsCount":"1","foodProductsNumber":"CPAD4818","foodProductsType":"2","groupCount":"1","groupName":"分组AB","groupNumber":"FZ180528111114887","minGroup":"2","packagePicture":"无","priceMarkup":"0.00","remind":"1","replace":"1","shopNumber":"1000180427300326890","singleProductName":"旺旺酥","standard":"180529111119495","standardName":"大","standardNumber":"180529111119495"},{"foodProductsCount":"3","foodProductsNumber":"178e17b1607773b7a455fdeb6a42a10b","foodProductsType":"2","groupCount":"1","groupName":"分组AB","groupNumber":"FZ180528111114887","minGroup":"2","packagePicture":"无","priceMarkup":"0.00","remind":"1","replace":"0","shopNumber":"1000180427300326890","singleProductName":"雪碧","standard":"180427111114447","standardName":"小杯","standardNumber":"180427111114447"}],[{"foodProductsCount":"1","foodProductsNumber":"CPAD4818","foodProductsType":"2","groupCount":"1","groupName":"分组AB","groupNumber":"FZ180528111114887","minGroup":"3","packagePicture":"无","priceMarkup":"0.00","remind":"1","replace":"1","shopNumber":"1000180427300326890","singleProductName":"旺旺酥","standard":"180529111119495","standardName":"大","standardNumber":"180529111119495"},{"foodProductsCount":"1","foodProductsNumber":"91a8ef6e41f153a2fe4d09020a9f3f9b","foodProductsType":"2","groupCount":"1","groupName":"分组AB","groupNumber":"FZ180528111114887","minGroup":"3","packagePicture":"无","priceMarkup":"0.00","remind":"1","replace":"0","shopNumber":"1000180427300326890","singleProductName":"可乐","standard":"180427111118129","standardName":"小杯","standardNumber":"180427111118129"}]],"shopNumber":"1000180427300326890"},{"deletionFlag":"0","groupNumber":"FZ180528111119629","groupPackageNumber":"ZH180528111116479","listThree":[[{"foodProductsCount":"1","foodProductsNumber":"84a7b53aaf49ebf4916427c6168fe0f5","foodProductsType":"2","groupCount":"1","groupName":"分组L2","groupNumber":"FZ180528111119629","minGroup":"1","packagePicture":"无","priceMarkup":"0.00","remind":"1","replace":"0","shopNumber":"1000180427300326890","singleProductName":"汉堡","standard":"180427111119120","standardName":"一个","standardNumber":"180427111119120"},{"foodProductsCount":"1","foodProductsNumber":"1549d7f91c3478e24f7ec2bd2906a31b","foodProductsType":"2","groupCount":"1","groupName":"分组L2","groupNumber":"FZ180528111119629","minGroup":"1","packagePicture":"无","priceMarkup":"0.00","remind":"1","replace":"0","shopNumber":"1000180427300326890","singleProductName":"鸡翅","standard":"180427111119253","standardName":"一对","standardNumber":"180427111119253"},{"foodProductsCount":"1","foodProductsNumber":"CP8C1D19","foodProductsType":"2","groupCount":"1","groupName":"分组L2","groupNumber":"FZ180528111119629","minGroup":"1","packagePicture":"无","priceMarkup":"0.00","remind":"1","replace":"0","shopNumber":"1000180427300326890","singleProductName":"薯条","standard":"180502111118149","standardName":"小份","standardNumber":"180502111118149"}]],"shopNumber":"1000180427300326890"}],"packagePicture":"","selling":"1","sellingStatus":"0","serialNumber":"3","theKitchenPrint":"0","vipPrice":"0.0000"},{"cateGoryType":"FL76673F","foodType":"2","groupPackageName":"超值A+B","groupPackageNumber":"ZH180521111117061","groupPackagePrice":"56.00","listTwo":[{"deletionFlag":"0","groupNumber":"FZ180520111119606","groupPackageNumber":"ZH180521111117061","listThree":[[{"foodProductsCount":"1","foodProductsNumber":"91a8ef6e41f153a2fe4d09020a9f3f9b","foodProductsType":"2","groupCount":"1","groupName":"","groupNumber":"FZ180520111119606","minGroup":"1","packagePicture":"无","priceMarkup":"0.00","remind":"1","replace":"1","shopNumber":"1000180427300326890","singleProductName":"可乐","standard":"180427111118129","standardName":"小杯","standardNumber":"180427111118129"}],[{"foodProductsCount":"1","foodProductsNumber":"178e17b1607773b7a455fdeb6a42a10b","foodProductsType":"2","groupCount":"1","groupName":"","groupNumber":"FZ180520111119606","minGroup":"2","packagePicture":"无","priceMarkup":"0.00","remind":"1","replace":"1","shopNumber":"1000180427300326890","singleProductName":"雪碧","standard":"180427111114447","standardName":"小杯","standardNumber":"180427111114447"}],[{"foodProductsCount":"1","foodProductsNumber":"CP8E888E","foodProductsType":"2","groupCount":"1","groupName":"","groupNumber":"FZ180520111119606","minGroup":"3","packagePicture":"无","priceMarkup":"0.00","remind":"1","replace":"0","shopNumber":"1000180427300326890","singleProductName":"西式汉堡（店内）","standard":"180608111119891","standardName":"小个","standardNumber":"180608111119891"}],[{"foodProductsCount":"1","foodProductsNumber":"CP8E888E","foodProductsType":"2","groupCount":"1","groupName":"","groupNumber":"FZ180520111119606","minGroup":"4","packagePicture":"无","priceMarkup":"0.00","remind":"1","replace":"1","shopNumber":"1000180427300326890","singleProductName":"西式汉堡（店内）","standard":"180608111119891","standardName":"小个","standardNumber":"180608111119891"}],[{"foodProductsCount":"1","foodProductsNumber":"CP8E888E","foodProductsType":"2","groupCount":"1","groupName":"","groupNumber":"FZ180520111119606","minGroup":"5","packagePicture":"无","priceMarkup":"0.00","remind":"1","replace":"1","shopNumber":"1000180427300326890","singleProductName":"西式汉堡（店内）","standard":"180608111119891","standardName":"小个","standardNumber":"180608111119891"}]],"shopNumber":"1000180427300326890"},{"deletionFlag":"0","groupNumber":"FZ180528111119629","groupPackageNumber":"ZH180521111117061","listThree":[[{"foodProductsCount":"1","foodProductsNumber":"84a7b53aaf49ebf4916427c6168fe0f5","foodProductsType":"2","groupCount":"1","groupName":"分组L2","groupNumber":"FZ180528111119629","minGroup":"1","packagePicture":"无","priceMarkup":"0.00","remind":"1","replace":"0","shopNumber":"1000180427300326890","singleProductName":"汉堡","standard":"180427111119120","standardName":"一个","standardNumber":"180427111119120"},{"foodProductsCount":"1","foodProductsNumber":"1549d7f91c3478e24f7ec2bd2906a31b","foodProductsType":"2","groupCount":"1","groupName":"分组L2","groupNumber":"FZ180528111119629","minGroup":"1","packagePicture":"无","priceMarkup":"0.00","remind":"1","replace":"0","shopNumber":"1000180427300326890","singleProductName":"鸡翅","standard":"180427111119253","standardName":"一对","standardNumber":"180427111119253"},{"foodProductsCount":"1","foodProductsNumber":"CP8C1D19","foodProductsType":"2","groupCount":"1","groupName":"分组L2","groupNumber":"FZ180528111119629","minGroup":"1","packagePicture":"无","priceMarkup":"0.00","remind":"1","replace":"0","shopNumber":"1000180427300326890","singleProductName":"薯条","standard":"180502111118149","standardName":"小份","standardNumber":"180502111118149"}]],"shopNumber":"1000180427300326890"}],"packagePicture":"/www/server/tomcat/shopPic/1000180427300326890/20180521171718869275.png--","selling":"1","sellingStatus":"0","serialNumber":"5","theKitchenPrint":"0","vipPrice":"0.0000"},{"cateGoryType":"FL76673F","foodType":"2","groupPackageName":"烛光晚餐","groupPackageNumber":"ZH180522111113198","groupPackagePrice":"521.00","listTwo":[{"deletionFlag":"0","groupNumber":"FZ180522111114266","groupPackageNumber":"ZH180522111113198","listThree":[[{"foodProductsCount":"1","foodProductsNumber":"CP8C1D19","foodProductsType":"2","groupCount":"1","groupName":"","groupNumber":"FZ180522111114266","minGroup":"1","packagePicture":"无","priceMarkup":"0.00","remind":"1","replace":"0","shopNumber":"1000180427300326890","singleProductName":"薯条","standard":"180502111118149","standardName":"小份","standardNumber":"180502111118149"},{"foodProductsCount":"1","foodProductsNumber":"91a8ef6e41f153a2fe4d09020a9f3f9b","foodProductsType":"2","groupCount":"1","groupName":"","groupNumber":"FZ180522111114266","minGroup":"1","packagePicture":"无","priceMarkup":"0.00","remind":"1","replace":"0","shopNumber":"1000180427300326890","singleProductName":"可乐","standard":"180427111118129","standardName":"小杯","standardNumber":"180427111118129"},{"foodProductsCount":"1","foodProductsNumber":"CP705DB9","foodProductsType":"2","groupCount":"1","groupName":"","groupNumber":"FZ180522111114266","minGroup":"1","packagePicture":"无","priceMarkup":"0.00","remind":"1","replace":"0","shopNumber":"1000180427300326890","singleProductName":"热牛奶","standard":"180507111113781","standardName":"中杯","standardNumber":"180507111113781"}],[{"foodProductsCount":"1","foodProductsNumber":"CP5833AB","foodProductsType":"2","groupCount":"1","groupName":"","groupNumber":"FZ180522111114266","minGroup":"2","packagePicture":"无","priceMarkup":"0.00","remind":"1","replace":"1","shopNumber":"1000180427300326890","singleProductName":"鳕鱼堡","standard":"180507111112559","standardName":"个","standardNumber":"180507111112559"}]],"shopNumber":"1000180427300326890"},{"deletionFlag":"0","groupNumber":"FZ180525111111741","groupPackageNumber":"ZH180522111113198","listThree":[[{"foodProductsCount":"1","foodProductsNumber":"91a8ef6e41f153a2fe4d09020a9f3f9b","foodProductsType":"2","groupCount":"1","groupName":"替换2","groupNumber":"FZ180525111111741","minGroup":"1","packagePicture":"无","priceMarkup":"0.00","remind":"1","replace":"0","shopNumber":"1000180427300326890","singleProductName":"可乐","standard":"180427111118129","standardName":"小杯","standardNumber":"180427111118129"},{"foodProductsCount":"1","foodProductsNumber":"84a7b53aaf49ebf4916427c6168fe0f5","foodProductsType":"2","groupCount":"1","groupName":"替换2","groupNumber":"FZ180525111111741","minGroup":"1","packagePicture":"无","priceMarkup":"0.00","remind":"1","replace":"0","shopNumber":"1000180427300326890","singleProductName":"汉堡","standard":"180427111119120","standardName":"一个","standardNumber":"180427111119120"}],[{"foodProductsCount":"1","foodProductsNumber":"1549d7f91c3478e24f7ec2bd2906a31b","foodProductsType":"2","groupCount":"1","groupName":"替换2","groupNumber":"FZ180525111111741","minGroup":"2","packagePicture":"无","priceMarkup":"0.00","remind":"1","replace":"0","shopNumber":"1000180427300326890","singleProductName":"鸡翅","standard":"180427111119253","standardName":"一对","standardNumber":"180427111119253"},{"foodProductsCount":"1","foodProductsNumber":"178e17b1607773b7a455fdeb6a42a10b","foodProductsType":"2","groupCount":"1","groupName":"替换2","groupNumber":"FZ180525111111741","minGroup":"2","packagePicture":"无","priceMarkup":"0.00","remind":"1","replace":"0","shopNumber":"1000180427300326890","singleProductName":"雪碧","standard":"180427111114447","standardName":"小杯","standardNumber":"180427111114447"}],[{"foodProductsCount":"1","foodProductsNumber":"a9c91ab3ad7c4507108d9e12717f2f55","foodProductsType":"2","groupCount":"1","groupName":"替换2","groupNumber":"FZ180525111111741","minGroup":"3","packagePicture":"无","priceMarkup":"0.00","remind":"1","replace":"0","shopNumber":"1000180427300326890","singleProductName":"芬达","standard":"180427111116908","standardName":"大杯","standardNumber":"180427111116908"},{"foodProductsCount":"1","foodProductsNumber":"CP5833AB","foodProductsType":"2","groupCount":"1","groupName":"替换2","groupNumber":"FZ180525111111741","minGroup":"3","packagePicture":"无","priceMarkup":"0.00","remind":"1","replace":"0","shopNumber":"1000180427300326890","singleProductName":"鳕鱼堡","standard":"180507111112559","standardName":"个","standardNumber":"180507111112559"}]],"shopNumber":"1000180427300326890"}],"packagePicture":"","selling":"0","sellingStatus":"0","serialNumber":"8","theKitchenPrint":"0","vipPrice":"0.0000"},{"cateGoryType":"FL76673F","foodType":"2","groupPackageName":"可替换组合套餐","groupPackageNumber":"ZH180528111118539","groupPackagePrice":"18.00","listTwo":[{"deletionFlag":"0","groupNumber":"FZ180520111119606","groupPackageNumber":"ZH180528111118539","listThree":[[{"foodProductsCount":"1","foodProductsNumber":"91a8ef6e41f153a2fe4d09020a9f3f9b","foodProductsType":"2","groupCount":"1","groupName":"","groupNumber":"FZ180520111119606","minGroup":"1","packagePicture":"无","priceMarkup":"0.00","remind":"1","replace":"1","shopNumber":"1000180427300326890","singleProductName":"可乐","standard":"180427111118129","standardName":"小杯","standardNumber":"180427111118129"}],[{"foodProductsCount":"1","foodProductsNumber":"178e17b1607773b7a455fdeb6a42a10b","foodProductsType":"2","groupCount":"1","groupName":"","groupNumber":"FZ180520111119606","minGroup":"2","packagePicture":"无","priceMarkup":"0.00","remind":"1","replace":"1","shopNumber":"1000180427300326890","singleProductName":"雪碧","standard":"180427111114447","standardName":"小杯","standardNumber":"180427111114447"}],[{"foodProductsCount":"1","foodProductsNumber":"CP8E888E","foodProductsType":"2","groupCount":"1","groupName":"","groupNumber":"FZ180520111119606","minGroup":"3","packagePicture":"无","priceMarkup":"0.00","remind":"1","replace":"0","shopNumber":"1000180427300326890","singleProductName":"西式汉堡（店内）","standard":"180608111119891","standardName":"小个","standardNumber":"180608111119891"}],[{"foodProductsCount":"1","foodProductsNumber":"CP8E888E","foodProductsType":"2","groupCount":"1","groupName":"","groupNumber":"FZ180520111119606","minGroup":"4","packagePicture":"无","priceMarkup":"0.00","remind":"1","replace":"1","shopNumber":"1000180427300326890","singleProductName":"西式汉堡（店内）","standard":"180608111119891","standardName":"小个","standardNumber":"180608111119891"}],[{"foodProductsCount":"1","foodProductsNumber":"CP8E888E","foodProductsType":"2","groupCount":"1","groupName":"","groupNumber":"FZ180520111119606","minGroup":"5","packagePicture":"无","priceMarkup":"0.00","remind":"1","replace":"1","shopNumber":"1000180427300326890","singleProductName":"西式汉堡（店内）","standard":"180608111119891","standardName":"小个","standardNumber":"180608111119891"}]],"shopNumber":"1000180427300326890"},{"deletionFlag":"0","groupNumber":"FZ180522111114266","groupPackageNumber":"ZH180528111118539","listThree":[[{"foodProductsCount":"1","foodProductsNumber":"CP8C1D19","foodProductsType":"2","groupCount":"1","groupName":"","groupNumber":"FZ180522111114266","minGroup":"1","packagePicture":"无","priceMarkup":"0.00","remind":"1","replace":"0","shopNumber":"1000180427300326890","singleProductName":"薯条","standard":"180502111118149","standardName":"小份","standardNumber":"180502111118149"},{"foodProductsCount":"1","foodProductsNumber":"91a8ef6e41f153a2fe4d09020a9f3f9b","foodProductsType":"2","groupCount":"1","groupName":"","groupNumber":"FZ180522111114266","minGroup":"1","packagePicture":"无","priceMarkup":"0.00","remind":"1","replace":"0","shopNumber":"1000180427300326890","singleProductName":"可乐","standard":"180427111118129","standardName":"小杯","standardNumber":"180427111118129"},{"foodProductsCount":"1","foodProductsNumber":"CP705DB9","foodProductsType":"2","groupCount":"1","groupName":"","groupNumber":"FZ180522111114266","minGroup":"1","packagePicture":"无","priceMarkup":"0.00","remind":"1","replace":"0","shopNumber":"1000180427300326890","singleProductName":"热牛奶","standard":"180507111113781","standardName":"中杯","standardNumber":"180507111113781"}],[{"foodProductsCount":"1","foodProductsNumber":"CP5833AB","foodProductsType":"2","groupCount":"1","groupName":"","groupNumber":"FZ180522111114266","minGroup":"2","packagePicture":"无","priceMarkup":"0.00","remind":"1","replace":"1","shopNumber":"1000180427300326890","singleProductName":"鳕鱼堡","standard":"180507111112559","standardName":"个","standardNumber":"180507111112559"}]],"shopNumber":"1000180427300326890"}],"packagePicture":"/www/server/tomcat/shopPic/1000180427300326890/20180607174849447122.png--","selling":"1","sellingStatus":"0","serialNumber":"5","theKitchenPrint":"0","vipPrice":"0.0000"},{"cateGoryType":"FL76673F","foodType":"2","groupPackageName":"乐享A+B","groupPackageNumber":"ZH180619111119439","groupPackagePrice":"40.00","listTwo":[{"deletionFlag":"0","groupNumber":"FZ180619111112210","groupPackageNumber":"ZH180619111119439","listThree":[[{"foodProductsCount":"1","foodProductsNumber":"CP696F36","foodProductsType":"2","groupCount":"1","groupName":"乐享B","groupNumber":"FZ180619111112210","minGroup":"1","packagePicture":"无","priceMarkup":"0.00","remind":"1","replace":"1","shopNumber":"1000180427300326890","singleProductName":"特大份薯条","standard":"180516111113456","standardName":"份","standardNumber":"180516111113456"},{"foodProductsCount":"1","foodProductsNumber":"CPE05579","foodProductsType":"2","groupCount":"1","groupName":"乐享B","groupNumber":"FZ180619111112210","minGroup":"1","packagePicture":"无","priceMarkup":"0.00","remind":"1","replace":"1","shopNumber":"1000180427300326890","singleProductName":"蔬果汁","standard":"180615111119041","standardName":"大","standardNumber":"180615111119041"},{"foodProductsCount":"1","foodProductsNumber":"CPE8E77A","foodProductsType":"2","groupCount":"1","groupName":"乐享B","groupNumber":"FZ180619111112210","minGroup":"1","packagePicture":"无","priceMarkup":"0.00","remind":"1","replace":"1","shopNumber":"1000180427300326890","singleProductName":"特大份汉堡","standard":"180516111114454","standardName":"个","standardNumber":"180516111114454"}],[{"foodProductsCount":"1","foodProductsNumber":"a9c91ab3ad7c4507108d9e12717f2f55","foodProductsType":"2","groupCount":"1","groupName":"乐享B","groupNumber":"FZ180619111112210","minGroup":"2","packagePicture":"无","priceMarkup":"0.00","remind":"1","replace":"1","shopNumber":"1000180427300326890","singleProductName":"芬达","standard":"180427111116908","standardName":"大杯","standardNumber":"180427111116908"},{"foodProductsCount":"1","foodProductsNumber":"CPE8E77A","foodProductsType":"2","groupCount":"1","groupName":"乐享B","groupNumber":"FZ180619111112210","minGroup":"2","packagePicture":"无","priceMarkup":"0.00","remind":"1","replace":"1","shopNumber":"1000180427300326890","singleProductName":"特大份汉堡","standard":"180516111114454","standardName":"个","standardNumber":"180516111114454"},{"foodProductsCount":"1","foodProductsNumber":"CP696F36","foodProductsType":"2","groupCount":"1","groupName":"乐享B","groupNumber":"FZ180619111112210","minGroup":"2","packagePicture":"无","priceMarkup":"0.00","remind":"1","replace":"1","shopNumber":"1000180427300326890","singleProductName":"特大份薯条","standard":"180516111113456","standardName":"份","standardNumber":"180516111113456"}]],"shopNumber":"1000180427300326890"},{"deletionFlag":"0","groupNumber":"FZ180619111112559","groupPackageNumber":"ZH180619111119439","listThree":[[{"foodProductsCount":"1","foodProductsNumber":"CP8E888E","foodProductsType":"2","groupCount":"1","groupName":"乐享A","groupNumber":"FZ180619111112559","minGroup":"1","packagePicture":"无","priceMarkup":"0.00","remind":"1","replace":"1","shopNumber":"1000180427300326890","singleProductName":"西式汉堡（店内）","standard":"180608111119891","standardName":"小个","standardNumber":"180608111119891"},{"foodProductsCount":"1","foodProductsNumber":"1549d7f91c3478e24f7ec2bd2906a31b","foodProductsType":"2","groupCount":"1","groupName":"乐享A","groupNumber":"FZ180619111112559","minGroup":"1","packagePicture":"无","priceMarkup":"0.00","remind":"1","replace":"1","shopNumber":"1000180427300326890","singleProductName":"鸡翅","standard":"180427111119253","standardName":"一对","standardNumber":"180427111119253"}]],"shopNumber":"1000180427300326890"}],"packagePicture":"","selling":"1","sellingStatus":"0","serialNumber":"25","theKitchenPrint":"0","vipPrice":"0.0000"},{"cateGoryType":"FL76673F","foodType":"2","groupPackageName":"组合套餐加替换","groupPackageNumber":"ZH180525111113573","groupPackagePrice":"28.00","listTwo":[{"deletionFlag":"0","groupNumber":"FZ180525111117088","groupPackageNumber":"ZH180525111113573","listThree":[[{"foodProductsCount":"1","foodProductsNumber":"91a8ef6e41f153a2fe4d09020a9f3f9b","foodProductsType":"2","groupCount":"1","groupName":"分组加替换","groupNumber":"FZ180525111117088","minGroup":"1","packagePicture":"无","priceMarkup":"0.00","remind":"1","replace":"0","shopNumber":"1000180427300326890","singleProductName":"可乐","standard":"180427111118129","standardName":"小杯","standardNumber":"180427111118129"},{"foodProductsCount":"1","foodProductsNumber":"84a7b53aaf49ebf4916427c6168fe0f5","foodProductsType":"2","groupCount":"1","groupName":"分组加替换","groupNumber":"FZ180525111117088","minGroup":"1","packagePicture":"无","priceMarkup":"0.00","remind":"1","replace":"1","shopNumber":"1000180427300326890","singleProductName":"汉堡","standard":"180427111119120","standardName":"一个","standardNumber":"180427111119120"},{"foodProductsCount":"1","foodProductsNumber":"1549d7f91c3478e24f7ec2bd2906a31b","foodProductsType":"2","groupCount":"1","groupName":"分组加替换","groupNumber":"FZ180525111117088","minGroup":"1","packagePicture":"无","priceMarkup":"0.00","remind":"1","replace":"1","shopNumber":"1000180427300326890","singleProductName":"鸡翅","standard":"180427111119253","standardName":"一对","standardNumber":"180427111119253"}]],"shopNumber":"1000180427300326890"},{"deletionFlag":"0","groupNumber":"FZ180525111111502","groupPackageNumber":"ZH180525111113573","listThree":[[{"foodProductsCount":"1","foodProductsNumber":"CP5833AB","foodProductsType":"2","groupCount":"1","groupName":"分组加替换2","groupNumber":"FZ180525111111502","minGroup":"1","packagePicture":"无","priceMarkup":"0.00","remind":"1","replace":"1","shopNumber":"1000180427300326890","singleProductName":"鳕鱼堡","standard":"180507111112559","standardName":"个","standardNumber":"180507111112559"},{"foodProductsCount":"1","foodProductsNumber":"178e17b1607773b7a455fdeb6a42a10b","foodProductsType":"2","groupCount":"1","groupName":"分组加替换2","groupNumber":"FZ180525111111502","minGroup":"1","packagePicture":"无","priceMarkup":"0.00","remind":"1","replace":"1","shopNumber":"1000180427300326890","singleProductName":"雪碧","standard":"180427111114447","standardName":"小杯","standardNumber":"180427111114447"}],[{"foodProductsCount":"1","foodProductsNumber":"84a7b53aaf49ebf4916427c6168fe0f5","foodProductsType":"2","groupCount":"1","groupName":"分组加替换2","groupNumber":"FZ180525111111502","minGroup":"2","packagePicture":"无","priceMarkup":"0.00","remind":"1","replace":"0","shopNumber":"1000180427300326890","singleProductName":"汉堡","standard":"180427111119120","standardName":"一个","standardNumber":"180427111119120"},{"foodProductsCount":"1","foodProductsNumber":"91a8ef6e41f153a2fe4d09020a9f3f9b","foodProductsType":"2","groupCount":"1","groupName":"分组加替换2","groupNumber":"FZ180525111111502","minGroup":"2","packagePicture":"无","priceMarkup":"0.00","remind":"1","replace":"1","shopNumber":"1000180427300326890","singleProductName":"可乐","standard":"180427111118129","standardName":"小杯","standardNumber":"180427111118129"}],[{"foodProductsCount":"1","foodProductsNumber":"1549d7f91c3478e24f7ec2bd2906a31b","foodProductsType":"2","groupCount":"1","groupName":"分组加替换2","groupNumber":"FZ180525111111502","minGroup":"3","packagePicture":"无","priceMarkup":"0.00","remind":"1","replace":"1","shopNumber":"1000180427300326890","singleProductName":"鸡翅","standard":"180427111119253","standardName":"一对","standardNumber":"180427111119253"}],[{"foodProductsCount":"1","foodProductsNumber":"CP8C1D19","foodProductsType":"2","groupCount":"1","groupName":"分组加替换2","groupNumber":"FZ180525111111502","minGroup":"4","packagePicture":"无","priceMarkup":"0.00","remind":"1","replace":"1","shopNumber":"1000180427300326890","singleProductName":"薯条","standard":"180502111118149","standardName":"小份","standardNumber":"180502111118149"}]],"shopNumber":"1000180427300326890"}],"packagePicture":"","selling":"1","sellingStatus":"0","serialNumber":"10","theKitchenPrint":"0","vipPrice":"0.0000"},{"cateGoryType":"FL76673F","foodType":"2","groupPackageName":"组合替换2","groupPackageNumber":"ZH180525111115359","groupPackagePrice":"28.00","listTwo":[{"deletionFlag":"0","groupNumber":"FZ180525111111741","groupPackageNumber":"ZH180525111115359","listThree":[[{"foodProductsCount":"1","foodProductsNumber":"91a8ef6e41f153a2fe4d09020a9f3f9b","foodProductsType":"2","groupCount":"1","groupName":"替换2","groupNumber":"FZ180525111111741","minGroup":"1","packagePicture":"无","priceMarkup":"0.00","remind":"1","replace":"0","shopNumber":"1000180427300326890","singleProductName":"可乐","standard":"180427111118129","standardName":"小杯","standardNumber":"180427111118129"},{"foodProductsCount":"1","foodProductsNumber":"84a7b53aaf49ebf4916427c6168fe0f5","foodProductsType":"2","groupCount":"1","groupName":"替换2","groupNumber":"FZ180525111111741","minGroup":"1","packagePicture":"无","priceMarkup":"0.00","remind":"1","replace":"0","shopNumber":"1000180427300326890","singleProductName":"汉堡","standard":"180427111119120","standardName":"一个","standardNumber":"180427111119120"}],[{"foodProductsCount":"1","foodProductsNumber":"1549d7f91c3478e24f7ec2bd2906a31b","foodProductsType":"2","groupCount":"1","groupName":"替换2","groupNumber":"FZ180525111111741","minGroup":"2","packagePicture":"无","priceMarkup":"0.00","remind":"1","replace":"0","shopNumber":"1000180427300326890","singleProductName":"鸡翅","standard":"180427111119253","standardName":"一对","standardNumber":"180427111119253"},{"foodProductsCount":"1","foodProductsNumber":"178e17b1607773b7a455fdeb6a42a10b","foodProductsType":"2","groupCount":"1","groupName":"替换2","groupNumber":"FZ180525111111741","minGroup":"2","packagePicture":"无","priceMarkup":"0.00","remind":"1","replace":"0","shopNumber":"1000180427300326890","singleProductName":"雪碧","standard":"180427111114447","standardName":"小杯","standardNumber":"180427111114447"}],[{"foodProductsCount":"1","foodProductsNumber":"a9c91ab3ad7c4507108d9e12717f2f55","foodProductsType":"2","groupCount":"1","groupName":"替换2","groupNumber":"FZ180525111111741","minGroup":"3","packagePicture":"无","priceMarkup":"0.00","remind":"1","replace":"0","shopNumber":"1000180427300326890","singleProductName":"芬达","standard":"180427111116908","standardName":"大杯","standardNumber":"180427111116908"},{"foodProductsCount":"1","foodProductsNumber":"CP5833AB","foodProductsType":"2","groupCount":"1","groupName":"替换2","groupNumber":"FZ180525111111741","minGroup":"3","packagePicture":"无","priceMarkup":"0.00","remind":"1","replace":"0","shopNumber":"1000180427300326890","singleProductName":"鳕鱼堡","standard":"180507111112559","standardName":"个","standardNumber":"180507111112559"}]],"shopNumber":"1000180427300326890"},{"deletionFlag":"0","groupNumber":"FZ180525111111502","groupPackageNumber":"ZH180525111115359","listThree":[[{"foodProductsCount":"1","foodProductsNumber":"CP5833AB","foodProductsType":"2","groupCount":"1","groupName":"分组加替换2","groupNumber":"FZ180525111111502","minGroup":"1","packagePicture":"无","priceMarkup":"0.00","remind":"1","replace":"1","shopNumber":"1000180427300326890","singleProductName":"鳕鱼堡","standard":"180507111112559","standardName":"个","standardNumber":"180507111112559"},{"foodProductsCount":"1","foodProductsNumber":"178e17b1607773b7a455fdeb6a42a10b","foodProductsType":"2","groupCount":"1","groupName":"分组加替换2","groupNumber":"FZ180525111111502","minGroup":"1","packagePicture":"无","priceMarkup":"0.00","remind":"1","replace":"1","shopNumber":"1000180427300326890","singleProductName":"雪碧","standard":"180427111114447","standardName":"小杯","standardNumber":"180427111114447"}],[{"foodProductsCount":"1","foodProductsNumber":"84a7b53aaf49ebf4916427c6168fe0f5","foodProductsType":"2","groupCount":"1","groupName":"分组加替换2","groupNumber":"FZ180525111111502","minGroup":"2","packagePicture":"无","priceMarkup":"0.00","remind":"1","replace":"0","shopNumber":"1000180427300326890","singleProductName":"汉堡","standard":"180427111119120","standardName":"一个","standardNumber":"180427111119120"},{"foodProductsCount":"1","foodProductsNumber":"91a8ef6e41f153a2fe4d09020a9f3f9b","foodProductsType":"2","groupCount":"1","groupName":"分组加替换2","groupNumber":"FZ180525111111502","minGroup":"2","packagePicture":"无","priceMarkup":"0.00","remind":"1","replace":"1","shopNumber":"1000180427300326890","singleProductName":"可乐","standard":"180427111118129","standardName":"小杯","standardNumber":"180427111118129"}],[{"foodProductsCount":"1","foodProductsNumber":"1549d7f91c3478e24f7ec2bd2906a31b","foodProductsType":"2","groupCount":"1","groupName":"分组加替换2","groupNumber":"FZ180525111111502","minGroup":"3","packagePicture":"无","priceMarkup":"0.00","remind":"1","replace":"1","shopNumber":"1000180427300326890","singleProductName":"鸡翅","standard":"180427111119253","standardName":"一对","standardNumber":"180427111119253"}],[{"foodProductsCount":"1","foodProductsNumber":"CP8C1D19","foodProductsType":"2","groupCount":"1","groupName":"分组加替换2","groupNumber":"FZ180525111111502","minGroup":"4","packagePicture":"无","priceMarkup":"0.00","remind":"1","replace":"1","shopNumber":"1000180427300326890","singleProductName":"薯条","standard":"180502111118149","standardName":"小份","standardNumber":"180502111118149"}]],"shopNumber":"1000180427300326890"}],"packagePicture":"","selling":"1","sellingStatus":"0","serialNumber":"1","theKitchenPrint":"0","vipPrice":"0.0000"},{"cateGoryType":"FL76673F","foodType":"2","groupPackageName":"6.8高考组合","groupPackageNumber":"ZH180608111114582","groupPackagePrice":"22.00","listTwo":[{"deletionFlag":"0","groupNumber":"FZ180608111112751","groupPackageNumber":"ZH180608111114582","listThree":[[{"foodProductsCount":"1","foodProductsNumber":"CP5D73AB","foodProductsType":"2","groupCount":"1","groupName":"6.8分组B","groupNumber":"FZ180608111112751","minGroup":"1","packagePicture":"无","priceMarkup":"0.00","remind":"1","replace":"0","shopNumber":"1000180427300326890","standard":"180516111115290","standardNumber":"180516111115290"}],[{"foodProductsCount":"1","foodProductsNumber":"CP296987","foodProductsType":"2","groupCount":"1","groupName":"6.8分组B","groupNumber":"FZ180608111112751","minGroup":"2","packagePicture":"无","priceMarkup":"0.00","remind":"1","replace":"1","shopNumber":"1000180427300326890","singleProductName":"江小白","standard":"180601111118866","standardName":"瓶（中）","standardNumber":"180601111118866"},{"foodProductsCount":"1","foodProductsNumber":"CP705DB9","foodProductsType":"2","groupCount":"1","groupName":"6.8分组B","groupNumber":"FZ180608111112751","minGroup":"2","packagePicture":"无","priceMarkup":"0.00","remind":"1","replace":"1","shopNumber":"1000180427300326890","singleProductName":"热牛奶","standard":"180507111113781","standardName":"中杯","standardNumber":"180507111113781"}],[{"foodProductsCount":"1","foodProductsNumber":"a9c91ab3ad7c4507108d9e12717f2f55","foodProductsType":"2","groupCount":"1","groupName":"6.8分组B","groupNumber":"FZ180608111112751","minGroup":"3","packagePicture":"无","priceMarkup":"0.00","remind":"1","replace":"0","shopNumber":"1000180427300326890","singleProductName":"芬达","standard":"180427111116908","standardName":"大杯","standardNumber":"180427111116908"}],[{"foodProductsCount":"1","foodProductsNumber":"178e17b1607773b7a455fdeb6a42a10b","foodProductsType":"2","groupCount":"1","groupName":"6.8分组B","groupNumber":"FZ180608111112751","minGroup":"4","packagePicture":"无","priceMarkup":"0.00","remind":"1","replace":"1","shopNumber":"1000180427300326890","singleProductName":"雪碧","standard":"180427111114447","standardName":"小杯","standardNumber":"180427111114447"}],[{"foodProductsCount":"1","foodProductsNumber":"91a8ef6e41f153a2fe4d09020a9f3f9b","foodProductsType":"2","groupCount":"1","groupName":"6.8分组B","groupNumber":"FZ180608111112751","minGroup":"6","packagePicture":"无","priceMarkup":"0.00","remind":"1","replace":"1","shopNumber":"1000180427300326890","singleProductName":"可乐","standard":"180427111118129","standardName":"小杯","standardNumber":"180427111118129"}]],"shopNumber":"1000180427300326890"},{"deletionFlag":"0","groupNumber":"FZ180608111113341","groupPackageNumber":"ZH180608111114582","listThree":[[{"foodProductsCount":"1","foodProductsNumber":"CP8C1D19","foodProductsType":"2","groupCount":"1","groupName":"6.8分组A","groupNumber":"FZ180608111113341","minGroup":"11","packagePicture":"无","priceMarkup":"0.00","remind":"0","replace":"0","shopNumber":"1000180427300326890","singleProductName":"薯条","standard":"180502111118149","standardName":"小份","standardNumber":"180502111118149"}],[{"foodProductsCount":"1","foodProductsNumber":"84a7b53aaf49ebf4916427c6168fe0f5","foodProductsType":"2","groupCount":"1","groupName":"6.8分组A","groupNumber":"FZ180608111113341","minGroup":"12","packagePicture":"无","priceMarkup":"0.00","remind":"1","replace":"0","shopNumber":"1000180427300326890","singleProductName":"汉堡","standard":"180427111119120","standardName":"一个","standardNumber":"180427111119120"}],[{"foodProductsCount":"1","foodProductsNumber":"CP5833AB","foodProductsType":"2","groupCount":"1","groupName":"6.8分组A","groupNumber":"FZ180608111113341","minGroup":"13","packagePicture":"无","priceMarkup":"0.00","remind":"1","replace":"1","shopNumber":"1000180427300326890","singleProductName":"鳕鱼堡","standard":"180507111112559","standardName":"个","standardNumber":"180507111112559"}],[{"foodProductsCount":"1","foodProductsNumber":"CP794804","foodProductsType":"2","groupCount":"1","groupName":"6.8分组A","groupNumber":"FZ180608111113341","minGroup":"14","packagePicture":"无","priceMarkup":"0.00","remind":"1","replace":"1","shopNumber":"1000180427300326890","standard":"180522111119872","standardNumber":"180522111119872"}]],"shopNumber":"1000180427300326890"}],"packagePicture":"","selling":"1","sellingStatus":"0","serialNumber":"4","theKitchenPrint":"0","vipPrice":"0.0000"},{"cateGoryType":"33331B06-095F-42D8-B8A8-B96D9D05881E","foodType":"2","groupPackageName":"测试组合TYPE是否正常","groupPackageNumber":"ZH180530111118025","groupPackagePrice":"16.00","listTwo":[{"deletionFlag":"0","groupNumber":"FZ180608111112644","groupPackageNumber":"ZH180530111118025","listThree":[[{"foodProductsCount":"1","foodProductsNumber":"CP8E888E","foodProductsType":"2","groupCount":"1","groupName":"只为多单品","groupNumber":"FZ180608111112644","minGroup":"1","packagePicture":"无","priceMarkup":"0.00","remind":"1","replace":"1","shopNumber":"1000180427300326890","singleProductName":"西式汉堡（店内）","standard":"180608111119891","standardName":"小个","standardNumber":"180608111119891"}],[{"foodProductsCount":"1","foodProductsNumber":"CPC0C3B6","foodProductsType":"2","groupCount":"1","groupName":"只为多单品","groupNumber":"FZ180608111112644","minGroup":"10","packagePicture":"无","priceMarkup":"0.00","remind":"1","replace":"1","shopNumber":"1000180427300326890","singleProductName":"沙拉酱","standard":"180514111119231","standardName":"大包","standardNumber":"180514111119231"}],[{"foodProductsCount":"1","foodProductsNumber":"CPAD4818","foodProductsType":"2","groupCount":"1","groupName":"只为多单品","groupNumber":"FZ180608111112644","minGroup":"3","packagePicture":"无","priceMarkup":"0.00","remind":"1","replace":"1","shopNumber":"1000180427300326890","singleProductName":"旺旺酥","standard":"180529111119495","standardName":"大","standardNumber":"180529111119495"}],[{"foodProductsCount":"1","foodProductsNumber":"CP794804","foodProductsType":"2","groupCount":"1","groupName":"只为多单品","groupNumber":"FZ180608111112644","minGroup":"5","packagePicture":"无","priceMarkup":"0.00","remind":"1","replace":"1","shopNumber":"1000180427300326890","standard":"180522111119872","standardNumber":"180522111119872"}],[{"foodProductsCount":"1","foodProductsNumber":"CP696F36","foodProductsType":"2","groupCount":"1","groupName":"只为多单品","groupNumber":"FZ180608111112644","minGroup":"6","packagePicture":"无","priceMarkup":"0.00","remind":"1","replace":"1","shopNumber":"1000180427300326890","singleProductName":"特大份薯条","standard":"180516111113456","standardName":"份","standardNumber":"180516111113456"}],[{"foodProductsCount":"1","foodProductsNumber":"CPE8E77A","foodProductsType":"2","groupCount":"1","groupName":"只为多单品","groupNumber":"FZ180608111112644","minGroup":"7","packagePicture":"无","priceMarkup":"0.00","remind":"1","replace":"1","shopNumber":"1000180427300326890","singleProductName":"特大份汉堡","standard":"180516111114454","standardName":"个","standardNumber":"180516111114454"}],[{"foodProductsCount":"1","foodProductsNumber":"CP296987","foodProductsType":"2","groupCount":"1","groupName":"只为多单品","groupNumber":"FZ180608111112644","minGroup":"8","packagePicture":"无","priceMarkup":"0.00","remind":"1","replace":"1","shopNumber":"1000180427300326890","singleProductName":"江小白","standard":"180601111118866","standardName":"瓶（中）","standardNumber":"180601111118866"}],[{"foodProductsCount":"1","foodProductsNumber":"CP5D73AB","foodProductsType":"2","groupCount":"1","groupName":"只为多单品","groupNumber":"FZ180608111112644","minGroup":"9","packagePicture":"无","priceMarkup":"0.00","remind":"1","replace":"1","shopNumber":"1000180427300326890","standard":"180516111115290","standardNumber":"180516111115290"}]],"shopNumber":"1000180427300326890"},{"deletionFlag":"0","groupNumber":"FZ180608111113432","groupPackageNumber":"ZH180530111118025","listThree":[[{"foodProductsCount":"1","foodProductsNumber":"CP98D126","foodProductsType":"2","groupCount":"1","groupName":"fe","groupNumber":"FZ180608111113432","minGroup":"1","packagePicture":"无","priceMarkup":"0.00","remind":"1","replace":"1","shopNumber":"1000180427300326890","singleProductName":"找零","standard":"180530111116819","standardName":"个","standardNumber":"180530111116819"}],[{"foodProductsCount":"1","foodProductsNumber":"CPAD4818","foodProductsType":"2","groupCount":"1","groupName":"fe","groupNumber":"FZ180608111113432","minGroup":"13","packagePicture":"无","priceMarkup":"0.00","remind":"1","replace":"1","shopNumber":"1000180427300326890","singleProductName":"旺旺酥","standard":"180529111119495","standardName":"大","standardNumber":"180529111119495"},{"foodProductsCount":"1","foodProductsNumber":"CP794804","foodProductsType":"2","groupCount":"1","groupName":"fe","groupNumber":"FZ180608111113432","minGroup":"13","packagePicture":"无","priceMarkup":"0.00","remind":"1","replace":"1","shopNumber":"1000180427300326890","standard":"180522111119872","standardNumber":"180522111119872"}],[{"foodProductsCount":"1","foodProductsNumber":"CPAD4818","foodProductsType":"2","groupCount":"1","groupName":"fe","groupNumber":"FZ180608111113432","minGroup":"2","packagePicture":"无","priceMarkup":"0.00","remind":"1","replace":"1","shopNumber":"1000180427300326890","singleProductName":"旺旺酥","standard":"180529111119495","standardName":"大","standardNumber":"180529111119495"}],[{"foodProductsCount":"1","foodProductsNumber":"CPE8E77A","foodProductsType":"2","groupCount":"1","groupName":"fe","groupNumber":"FZ180608111113432","minGroup":"3","packagePicture":"无","priceMarkup":"0.00","remind":"1","replace":"1","shopNumber":"1000180427300326890","singleProductName":"特大份汉堡","standard":"180516111114454","standardName":"个","standardNumber":"180516111114454"}],[{"foodProductsCount":"1","foodProductsNumber":"CPE8E77A","foodProductsType":"2","groupCount":"1","groupName":"fe","groupNumber":"FZ180608111113432","minGroup":"4","packagePicture":"无","priceMarkup":"0.00","remind":"1","replace":"1","shopNumber":"1000180427300326890","singleProductName":"特大份汉堡","standard":"180516111114454","standardName":"个","standardNumber":"180516111114454"},{"foodProductsCount":"1","foodProductsNumber":"CP696F36","foodProductsType":"2","groupCount":"1","groupName":"fe","groupNumber":"FZ180608111113432","minGroup":"4","packagePicture":"无","priceMarkup":"0.00","remind":"1","replace":"1","shopNumber":"1000180427300326890","singleProductName":"特大份薯条","standard":"180516111113456","standardName":"份","standardNumber":"180516111113456"},{"foodProductsCount":"1","foodProductsNumber":"CP794804","foodProductsType":"2","groupCount":"1","groupName":"fe","groupNumber":"FZ180608111113432","minGroup":"4","packagePicture":"无","priceMarkup":"0.00","remind":"1","replace":"1","shopNumber":"1000180427300326890","standard":"180522111119872","standardNumber":"180522111119872"}],[{"foodProductsCount":"1","foodProductsNumber":"91a8ef6e41f153a2fe4d09020a9f3f9b","foodProductsType":"2","groupCount":"1","groupName":"fe","groupNumber":"FZ180608111113432","minGroup":"7","packagePicture":"无","priceMarkup":"0.00","remind":"1","replace":"1","shopNumber":"1000180427300326890","singleProductName":"可乐","standard":"180427111118129","standardName":"小杯","standardNumber":"180427111118129"},{"foodProductsCount":"1","foodProductsNumber":"178e17b1607773b7a455fdeb6a42a10b","foodProductsType":"2","groupCount":"1","groupName":"fe","groupNumber":"FZ180608111113432","minGroup":"7","packagePicture":"无","priceMarkup":"0.00","remind":"1","replace":"1","shopNumber":"1000180427300326890","singleProductName":"雪碧","standard":"180427111114447","standardName":"小杯","standardNumber":"180427111114447"}]],"shopNumber":"1000180427300326890"}],"packagePicture":"/www/server/tomcat/shopPic/1000180427300326890/20180604154934677697.png--","selling":"1","sellingStatus":"0","serialNumber":"5","theKitchenPrint":"0","vipPrice":"0.0000"}]
     * msg : 服务成功
     * success : true
     */

    private int code;
    private String msg;
    private boolean success;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * cateGoryName : 饮品
         * cateGoryType : 0488C2DF-61ED-4251-AA96-F67FE2896F22
         * cateSerialNumber : 6
         * deleteFlag : 0
         * foodProductsPicture : /www/server/tomcat/shopPic/1000180427300326890/20180612164825580854.png--
         * foodType : 1
         * page : 0
         * phr : 1
         * rows : 0
         * selling : 0
         * sellingStatus : 0
         * ShopStandarList : [{"deleteFlag":0,"foodProductsNumber":"91a8ef6e41f153a2fe4d09020a9f3f9b","page":0,"rows":0,"sell":"5.00","shopNumber":"1000180427300326890","standardName":"小杯","standardNumber":"180427111118129","vipPrice":"4.5000"}]
         * singleProductName : 可乐
         * singleProductNumber : 91a8ef6e41f153a2fe4d09020a9f3f9b
         * singleProductType : 0488C2DF-61ED-4251-AA96-F67FE2896F22
         * theKitchenPrint : 0
         * endTime : 1900-01-01 00:00:00
         * list : [{"cateGoryName":"小食","cateSerialNumber":"3","deleteFlag":0,"foodProductsPicture":"img/1000180427300326890/20180427142313829058.png--","packageName":"特大份豪华套餐","packageNumber":"TC30B651","page":0,"remind":"1","rows":0,"serialNumber":"1","singleProductName":"鸡翅","singleProductNumber":"1549d7f91c3478e24f7ec2bd2906a31b","singleQuantity":"1","standardName":"一对","standardNumber":"180427111119253"},{"cateGoryName":"小食","cateSerialNumber":"3","deleteFlag":0,"foodProductsPicture":"","packageName":"特大份豪华套餐","packageNumber":"TC30B651","page":0,"remind":"1","rows":0,"serialNumber":"1","singleProductName":"特大份薯条","singleProductNumber":"CP696F36","singleQuantity":"1","standardName":"份","standardNumber":"180516111113456"},{"cateGoryName":"汉堡","cateSerialNumber":"4","deleteFlag":0,"foodProductsPicture":"","packageName":"特大份豪华套餐","packageNumber":"TC30B651","page":0,"remind":"1","rows":0,"serialNumber":"2","singleProductName":"特大份汉堡","singleProductNumber":"CPE8E77A","singleQuantity":"1","standardName":"个","standardNumber":"180516111114454"},{"cateGoryName":"饮品","cateSerialNumber":"6","deleteFlag":0,"foodProductsPicture":"","packageName":"特大份豪华套餐","packageNumber":"TC30B651","page":0,"remind":"1","rows":0,"serialNumber":"1","singleProductName":"蔬果汁","singleProductNumber":"CPE05579","singleQuantity":"1","standardName":"大","standardNumber":"180615111119041"}]
         * mealCode : 000326
         * packageInitials : TDFHHTC
         * packageName : 特大份豪华套餐
         * packageNumber : TC30B651
         * packagePrice : 15.00
         * packageType : 33331B06-095F-42D8-B8A8-B96D9D05881E
         * serialNumber : 13
         * startTime : 1900-01-01 00:00:00
         * takeout : 1
         * vipprice : 0.00
         * groupPackageName : 组合L
         * groupPackageNumber : ZH180528111116479
         * groupPackagePrice : 18.00
         * listTwo : [{"deletionFlag":"0","groupNumber":"FZ180528111114887","groupPackageNumber":"ZH180528111116479","listThree":[[{"foodProductsCount":"1","foodProductsNumber":"CP98D126","foodProductsType":"2","groupCount":"1","groupName":"","groupNumber":"FZ180528111114887","minGroup":"1","packagePicture":"无","priceMarkup":"0.00","remind":"1","replace":"1","shopNumber":"1000180427300326890","singleProductName":"找零","standard":"180530111116819","standardName":"个","standardNumber":"180530111116819"},{"foodProductsCount":"1","foodProductsNumber":"a9c91ab3ad7c4507108d9e12717f2f55","foodProductsType":"2","groupCount":"1","groupName":"分组AB","groupNumber":"FZ180528111114887","minGroup":"1","packagePicture":"无","priceMarkup":"0.00","remind":"1","replace":"0","shopNumber":"1000180427300326890","singleProductName":"芬达","standard":"180427111116908","standardName":"大杯","standardNumber":"180427111116908"},{"foodProductsCount":"1","foodProductsNumber":"CPE8E77A","foodProductsType":"2","groupCount":"1","groupName":"分组AB","groupNumber":"FZ180528111114887","minGroup":"1","packagePicture":"无","priceMarkup":"0.00","remind":"1","replace":"1","shopNumber":"1000180427300326890","singleProductName":"特大份汉堡","standard":"180516111114454","standardName":"个","standardNumber":"180516111114454"}],[{"foodProductsCount":"1","foodProductsNumber":"CP8E888E","foodProductsType":"2","groupCount":"1","groupName":"","groupNumber":"FZ180528111114887","minGroup":"10","packagePicture":"无","priceMarkup":"0.00","remind":"1","replace":"1","shopNumber":"1000180427300326890","singleProductName":"西式汉堡（店内）","standard":"180608111119891","standardName":"小个","standardNumber":"180608111119891"}],[{"foodProductsCount":"1","foodProductsNumber":"CPAD4818","foodProductsType":"2","groupCount":"1","groupName":"分组AB","groupNumber":"FZ180528111114887","minGroup":"2","packagePicture":"无","priceMarkup":"0.00","remind":"1","replace":"1","shopNumber":"1000180427300326890","singleProductName":"旺旺酥","standard":"180529111119495","standardName":"大","standardNumber":"180529111119495"},{"foodProductsCount":"3","foodProductsNumber":"178e17b1607773b7a455fdeb6a42a10b","foodProductsType":"2","groupCount":"1","groupName":"分组AB","groupNumber":"FZ180528111114887","minGroup":"2","packagePicture":"无","priceMarkup":"0.00","remind":"1","replace":"0","shopNumber":"1000180427300326890","singleProductName":"雪碧","standard":"180427111114447","standardName":"小杯","standardNumber":"180427111114447"}],[{"foodProductsCount":"1","foodProductsNumber":"CPAD4818","foodProductsType":"2","groupCount":"1","groupName":"分组AB","groupNumber":"FZ180528111114887","minGroup":"3","packagePicture":"无","priceMarkup":"0.00","remind":"1","replace":"1","shopNumber":"1000180427300326890","singleProductName":"旺旺酥","standard":"180529111119495","standardName":"大","standardNumber":"180529111119495"},{"foodProductsCount":"1","foodProductsNumber":"91a8ef6e41f153a2fe4d09020a9f3f9b","foodProductsType":"2","groupCount":"1","groupName":"分组AB","groupNumber":"FZ180528111114887","minGroup":"3","packagePicture":"无","priceMarkup":"0.00","remind":"1","replace":"0","shopNumber":"1000180427300326890","singleProductName":"可乐","standard":"180427111118129","standardName":"小杯","standardNumber":"180427111118129"}]],"shopNumber":"1000180427300326890"},{"deletionFlag":"0","groupNumber":"FZ180528111119629","groupPackageNumber":"ZH180528111116479","listThree":[[{"foodProductsCount":"1","foodProductsNumber":"84a7b53aaf49ebf4916427c6168fe0f5","foodProductsType":"2","groupCount":"1","groupName":"分组L2","groupNumber":"FZ180528111119629","minGroup":"1","packagePicture":"无","priceMarkup":"0.00","remind":"1","replace":"0","shopNumber":"1000180427300326890","singleProductName":"汉堡","standard":"180427111119120","standardName":"一个","standardNumber":"180427111119120"},{"foodProductsCount":"1","foodProductsNumber":"1549d7f91c3478e24f7ec2bd2906a31b","foodProductsType":"2","groupCount":"1","groupName":"分组L2","groupNumber":"FZ180528111119629","minGroup":"1","packagePicture":"无","priceMarkup":"0.00","remind":"1","replace":"0","shopNumber":"1000180427300326890","singleProductName":"鸡翅","standard":"180427111119253","standardName":"一对","standardNumber":"180427111119253"},{"foodProductsCount":"1","foodProductsNumber":"CP8C1D19","foodProductsType":"2","groupCount":"1","groupName":"分组L2","groupNumber":"FZ180528111119629","minGroup":"1","packagePicture":"无","priceMarkup":"0.00","remind":"1","replace":"0","shopNumber":"1000180427300326890","singleProductName":"薯条","standard":"180502111118149","standardName":"小份","standardNumber":"180502111118149"}]],"shopNumber":"1000180427300326890"}]
         * packagePicture :
         * vipPrice : 0.0000
         */

        private String foodName;
        private String foodPrice;
        private String foodImg;
        private String btnStr;
        private boolean visible;
        private String cateGoryName;
        private String cateGoryType;
        private String cateSerialNumber;
        private int deleteFlag;
        private String foodProductsPicture;
        private int foodProductsCount;
        private String foodType;
        private int page;
        private String phr;
        private int rows;
        private String selling;
        private String sellingStatus;
        private String singleProductName;
        private String singleProductNumber;
        private String singleProductType;
        private String theKitchenPrint;
        private String endTime;
        private String mealCode;
        private String packageInitials;
        private String packageName;
        private String packageNumber;
        private String packagePrice;
        private String packageType;
        private String serialNumber;
        private String startTime;
        private String takeout;
        private String vipprice;
        private String groupPackageName;
        private String groupPackageNumber;
        private String groupPackagePrice;
        private String packagePicture;
        private String vipPrice;
        private List<ShopStandarListBean> shopStandarList;
        private List<ListBean> list;
        private List<ListTwoBean> listTwo;

        public String getBtnStr() {
            return btnStr;
        }

        public void setBtnStr(String btnStr) {
            this.btnStr = btnStr;
        }

        public boolean isVisible() {
            return visible;
        }

        public void setVisible(boolean visible) {
            this.visible = visible;
        }

        public String getFoodName() {
            return foodName;
        }

        public void setFoodName(String foodName) {
            this.foodName = foodName;
        }

        public String getFoodPrice() {
            return foodPrice;
        }

        public void setFoodPrice(String foodPrice) {
            this.foodPrice = foodPrice;
        }

        public String getFoodImg() {
            return foodImg;
        }

        public void setFoodImg(String foodImg) {
            this.foodImg = foodImg;
        }

        public int getFoodProductsCount() {
            return foodProductsCount;
        }

        public void setFoodProductsCount(int foodProductsCount) {
            this.foodProductsCount = foodProductsCount;
        }

        public String getCateGoryName() {
            return cateGoryName;
        }

        public void setCateGoryName(String cateGoryName) {
            this.cateGoryName = cateGoryName;
        }

        public String getCateGoryType() {
            return cateGoryType;
        }

        public void setCateGoryType(String cateGoryType) {
            this.cateGoryType = cateGoryType;
        }

        public String getCateSerialNumber() {
            return cateSerialNumber;
        }

        public void setCateSerialNumber(String cateSerialNumber) {
            this.cateSerialNumber = cateSerialNumber;
        }

        public int getDeleteFlag() {
            return deleteFlag;
        }

        public void setDeleteFlag(int deleteFlag) {
            this.deleteFlag = deleteFlag;
        }

        public String getFoodProductsPicture() {
            return foodProductsPicture;
        }

        public void setFoodProductsPicture(String foodProductsPicture) {
            this.foodProductsPicture = foodProductsPicture;
        }

        public String getFoodType() {
            return foodType;
        }

        public void setFoodType(String foodType) {
            this.foodType = foodType;
        }

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public String getPhr() {
            return phr;
        }

        public void setPhr(String phr) {
            this.phr = phr;
        }

        public int getRows() {
            return rows;
        }

        public void setRows(int rows) {
            this.rows = rows;
        }

        public String getSelling() {
            return selling;
        }

        public void setSelling(String selling) {
            this.selling = selling;
        }

        public String getSellingStatus() {
            return sellingStatus;
        }

        public void setSellingStatus(String sellingStatus) {
            this.sellingStatus = sellingStatus;
        }

        public String getSingleProductName() {
            return singleProductName;
        }

        public void setSingleProductName(String singleProductName) {
            this.singleProductName = singleProductName;
        }

        public String getSingleProductNumber() {
            return singleProductNumber;
        }

        public void setSingleProductNumber(String singleProductNumber) {
            this.singleProductNumber = singleProductNumber;
        }

        public String getSingleProductType() {
            return singleProductType;
        }

        public void setSingleProductType(String singleProductType) {
            this.singleProductType = singleProductType;
        }

        public String getTheKitchenPrint() {
            return theKitchenPrint;
        }

        public void setTheKitchenPrint(String theKitchenPrint) {
            this.theKitchenPrint = theKitchenPrint;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public String getMealCode() {
            return mealCode;
        }

        public void setMealCode(String mealCode) {
            this.mealCode = mealCode;
        }

        public String getPackageInitials() {
            return packageInitials;
        }

        public void setPackageInitials(String packageInitials) {
            this.packageInitials = packageInitials;
        }

        public String getPackageName() {
            return packageName;
        }

        public void setPackageName(String packageName) {
            this.packageName = packageName;
        }

        public String getPackageNumber() {
            return packageNumber;
        }

        public void setPackageNumber(String packageNumber) {
            this.packageNumber = packageNumber;
        }

        public String getPackagePrice() {
            return packagePrice;
        }

        public void setPackagePrice(String packagePrice) {
            this.packagePrice = packagePrice;
        }

        public String getPackageType() {
            return packageType;
        }

        public void setPackageType(String packageType) {
            this.packageType = packageType;
        }

        public String getSerialNumber() {
            return serialNumber;
        }

        public void setSerialNumber(String serialNumber) {
            this.serialNumber = serialNumber;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getTakeout() {
            return takeout;
        }

        public void setTakeout(String takeout) {
            this.takeout = takeout;
        }

        public String getVipprice() {
            return vipprice;
        }

        public void setVipprice(String vipprice) {
            this.vipprice = vipprice;
        }

        public String getGroupPackageName() {
            return groupPackageName;
        }

        public void setGroupPackageName(String groupPackageName) {
            this.groupPackageName = groupPackageName;
        }

        public String getGroupPackageNumber() {
            return groupPackageNumber;
        }

        public void setGroupPackageNumber(String groupPackageNumber) {
            this.groupPackageNumber = groupPackageNumber;
        }

        public String getGroupPackagePrice() {
            return groupPackagePrice;
        }

        public void setGroupPackagePrice(String groupPackagePrice) {
            this.groupPackagePrice = groupPackagePrice;
        }

        public String getPackagePicture() {
            return packagePicture;
        }

        public void setPackagePicture(String packagePicture) {
            this.packagePicture = packagePicture;
        }

        public String getVipPrice() {
            return vipPrice;
        }

        public void setVipPrice(String vipPrice) {
            this.vipPrice = vipPrice;
        }

        public List<ShopStandarListBean> getShopStandarList() {
            return shopStandarList;
        }

        public void setShopStandarList(List<ShopStandarListBean> shopStandarList) {
            this.shopStandarList = shopStandarList;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public List<ListTwoBean> getListTwo() {
            return listTwo;
        }

        public void setListTwo(List<ListTwoBean> listTwo) {
            this.listTwo = listTwo;
        }

        public static class ShopStandarListBean {
            /**
             * deleteFlag : 0
             * foodProductsNumber : 91a8ef6e41f153a2fe4d09020a9f3f9b
             * page : 0
             * rows : 0
             * sell : 5.00
             * shopNumber : 1000180427300326890
             * standardName : 小杯
             * standardNumber : 180427111118129
             * vipPrice : 4.5000
             */

            private int deleteFlag;
            private String foodProductsNumber;
            private int page;
            private int rows;
            private String sell;
            private String shopNumber;
            private String standardName;
            private String standardNumber;
            private String vipPrice;
            private int selectCount;

            public int getSelectCount() {
                return selectCount;
            }

            public void setSelectCount(int selectCount) {
                this.selectCount = selectCount;
            }

            public int getDeleteFlag() {
                return deleteFlag;
            }

            public void setDeleteFlag(int deleteFlag) {
                this.deleteFlag = deleteFlag;
            }

            public String getFoodProductsNumber() {
                return foodProductsNumber;
            }

            public void setFoodProductsNumber(String foodProductsNumber) {
                this.foodProductsNumber = foodProductsNumber;
            }

            public int getPage() {
                return page;
            }

            public void setPage(int page) {
                this.page = page;
            }

            public int getRows() {
                return rows;
            }

            public void setRows(int rows) {
                this.rows = rows;
            }

            public String getSell() {
                return sell;
            }

            public void setSell(String sell) {
                this.sell = sell;
            }

            public String getShopNumber() {
                return shopNumber;
            }

            public void setShopNumber(String shopNumber) {
                this.shopNumber = shopNumber;
            }

            public String getStandardName() {
                return standardName;
            }

            public void setStandardName(String standardName) {
                this.standardName = standardName;
            }

            public String getStandardNumber() {
                return standardNumber;
            }

            public void setStandardNumber(String standardNumber) {
                this.standardNumber = standardNumber;
            }

            public String getVipPrice() {
                return vipPrice;
            }

            public void setVipPrice(String vipPrice) {
                this.vipPrice = vipPrice;
            }
        }

        public static class ListBean {
            /**
             * cateGoryName : 小食
             * cateSerialNumber : 3
             * deleteFlag : 0
             * foodProductsPicture : img/1000180427300326890/20180427142313829058.png--
             * packageName : 特大份豪华套餐
             * packageNumber : TC30B651
             * page : 0
             * remind : 1
             * rows : 0
             * serialNumber : 1
             * singleProductName : 鸡翅
             * singleProductNumber : 1549d7f91c3478e24f7ec2bd2906a31b
             * singleQuantity : 1
             * standardName : 一对
             * standardNumber : 180427111119253
             */

            private String cateGoryName;
            private String cateSerialNumber;
            private int deleteFlag;
            private String foodProductsPicture;
            private String packageName;
            private String packageNumber;
            private int page;
            private String remind;
            private int rows;
            private String serialNumber;
            private String singleProductName;
            private String singleProductNumber;
            private String singleQuantity;
            private String standardName;
            private String standardNumber;

            public String getCateGoryName() {
                return cateGoryName;
            }

            public void setCateGoryName(String cateGoryName) {
                this.cateGoryName = cateGoryName;
            }

            public String getCateSerialNumber() {
                return cateSerialNumber;
            }

            public void setCateSerialNumber(String cateSerialNumber) {
                this.cateSerialNumber = cateSerialNumber;
            }

            public int getDeleteFlag() {
                return deleteFlag;
            }

            public void setDeleteFlag(int deleteFlag) {
                this.deleteFlag = deleteFlag;
            }

            public String getFoodProductsPicture() {
                return foodProductsPicture;
            }

            public void setFoodProductsPicture(String foodProductsPicture) {
                this.foodProductsPicture = foodProductsPicture;
            }

            public String getPackageName() {
                return packageName;
            }

            public void setPackageName(String packageName) {
                this.packageName = packageName;
            }

            public String getPackageNumber() {
                return packageNumber;
            }

            public void setPackageNumber(String packageNumber) {
                this.packageNumber = packageNumber;
            }

            public int getPage() {
                return page;
            }

            public void setPage(int page) {
                this.page = page;
            }

            public String getRemind() {
                return remind;
            }

            public void setRemind(String remind) {
                this.remind = remind;
            }

            public int getRows() {
                return rows;
            }

            public void setRows(int rows) {
                this.rows = rows;
            }

            public String getSerialNumber() {
                return serialNumber;
            }

            public void setSerialNumber(String serialNumber) {
                this.serialNumber = serialNumber;
            }

            public String getSingleProductName() {
                return singleProductName;
            }

            public void setSingleProductName(String singleProductName) {
                this.singleProductName = singleProductName;
            }

            public String getSingleProductNumber() {
                return singleProductNumber;
            }

            public void setSingleProductNumber(String singleProductNumber) {
                this.singleProductNumber = singleProductNumber;
            }

            public String getSingleQuantity() {
                return singleQuantity;
            }

            public void setSingleQuantity(String singleQuantity) {
                this.singleQuantity = singleQuantity;
            }

            public String getStandardName() {
                return standardName;
            }

            public void setStandardName(String standardName) {
                this.standardName = standardName;
            }

            public String getStandardNumber() {
                return standardNumber;
            }

            public void setStandardNumber(String standardNumber) {
                this.standardNumber = standardNumber;
            }
        }

        public static class ListTwoBean {
            /**
             * deletionFlag : 0
             * groupNumber : FZ180528111114887
             * groupPackageNumber : ZH180528111116479
             * listThree : [[{"foodProductsCount":"1","foodProductsNumber":"CP98D126","foodProductsType":"2","groupCount":"1","groupName":"","groupNumber":"FZ180528111114887","minGroup":"1","packagePicture":"无","priceMarkup":"0.00","remind":"1","replace":"1","shopNumber":"1000180427300326890","singleProductName":"找零","standard":"180530111116819","standardName":"个","standardNumber":"180530111116819"},{"foodProductsCount":"1","foodProductsNumber":"a9c91ab3ad7c4507108d9e12717f2f55","foodProductsType":"2","groupCount":"1","groupName":"分组AB","groupNumber":"FZ180528111114887","minGroup":"1","packagePicture":"无","priceMarkup":"0.00","remind":"1","replace":"0","shopNumber":"1000180427300326890","singleProductName":"芬达","standard":"180427111116908","standardName":"大杯","standardNumber":"180427111116908"},{"foodProductsCount":"1","foodProductsNumber":"CPE8E77A","foodProductsType":"2","groupCount":"1","groupName":"分组AB","groupNumber":"FZ180528111114887","minGroup":"1","packagePicture":"无","priceMarkup":"0.00","remind":"1","replace":"1","shopNumber":"1000180427300326890","singleProductName":"特大份汉堡","standard":"180516111114454","standardName":"个","standardNumber":"180516111114454"}],[{"foodProductsCount":"1","foodProductsNumber":"CP8E888E","foodProductsType":"2","groupCount":"1","groupName":"","groupNumber":"FZ180528111114887","minGroup":"10","packagePicture":"无","priceMarkup":"0.00","remind":"1","replace":"1","shopNumber":"1000180427300326890","singleProductName":"西式汉堡（店内）","standard":"180608111119891","standardName":"小个","standardNumber":"180608111119891"}],[{"foodProductsCount":"1","foodProductsNumber":"CPAD4818","foodProductsType":"2","groupCount":"1","groupName":"分组AB","groupNumber":"FZ180528111114887","minGroup":"2","packagePicture":"无","priceMarkup":"0.00","remind":"1","replace":"1","shopNumber":"1000180427300326890","singleProductName":"旺旺酥","standard":"180529111119495","standardName":"大","standardNumber":"180529111119495"},{"foodProductsCount":"3","foodProductsNumber":"178e17b1607773b7a455fdeb6a42a10b","foodProductsType":"2","groupCount":"1","groupName":"分组AB","groupNumber":"FZ180528111114887","minGroup":"2","packagePicture":"无","priceMarkup":"0.00","remind":"1","replace":"0","shopNumber":"1000180427300326890","singleProductName":"雪碧","standard":"180427111114447","standardName":"小杯","standardNumber":"180427111114447"}],[{"foodProductsCount":"1","foodProductsNumber":"CPAD4818","foodProductsType":"2","groupCount":"1","groupName":"分组AB","groupNumber":"FZ180528111114887","minGroup":"3","packagePicture":"无","priceMarkup":"0.00","remind":"1","replace":"1","shopNumber":"1000180427300326890","singleProductName":"旺旺酥","standard":"180529111119495","standardName":"大","standardNumber":"180529111119495"},{"foodProductsCount":"1","foodProductsNumber":"91a8ef6e41f153a2fe4d09020a9f3f9b","foodProductsType":"2","groupCount":"1","groupName":"分组AB","groupNumber":"FZ180528111114887","minGroup":"3","packagePicture":"无","priceMarkup":"0.00","remind":"1","replace":"0","shopNumber":"1000180427300326890","singleProductName":"可乐","standard":"180427111118129","standardName":"小杯","standardNumber":"180427111118129"}]]
             * shopNumber : 1000180427300326890
             */

            private String deletionFlag;
            private String groupNumber;
            private String groupPackageNumber;
            private String shopNumber;
            private List<List<FoodZuheTaocanXQ>> listThree;

            public String getDeletionFlag() {
                return deletionFlag;
            }

            public void setDeletionFlag(String deletionFlag) {
                this.deletionFlag = deletionFlag;
            }

            public String getGroupNumber() {
                return groupNumber;
            }

            public void setGroupNumber(String groupNumber) {
                this.groupNumber = groupNumber;
            }

            public String getGroupPackageNumber() {
                return groupPackageNumber;
            }

            public void setGroupPackageNumber(String groupPackageNumber) {
                this.groupPackageNumber = groupPackageNumber;
            }

            public String getShopNumber() {
                return shopNumber;
            }

            public void setShopNumber(String shopNumber) {
                this.shopNumber = shopNumber;
            }

            public List<List<FoodZuheTaocanXQ>> getListThree() {
                return listThree;
            }

            public void setListThree(List<List<FoodZuheTaocanXQ>> listThree) {
                this.listThree = listThree;
            }

            public static class ListThreeBean {
                /**
                 * foodProductsCount : 1
                 * foodProductsNumber : CP98D126
                 * foodProductsType : 2
                 * groupCount : 1
                 * groupName :
                 * groupNumber : FZ180528111114887
                 * minGroup : 1
                 * packagePicture : 无
                 * priceMarkup : 0.00
                 * remind : 1
                 * replace : 1
                 * shopNumber : 1000180427300326890
                 * singleProductName : 找零
                 * standard : 180530111116819
                 * standardName : 个
                 * standardNumber : 180530111116819
                 */

                private String foodProductsCount;
                private String foodProductsNumber;
                private String foodProductsType;
                private String groupCount;
                private String groupName;
                private String groupNumber;
                private String minGroup;
                private String packagePicture;
                private String priceMarkup;
                private String remind;
                private String replace;
                private String shopNumber;
                private String singleProductName;
                private String standard;
                private String standardName;
                private String standardNumber;

                public String getFoodProductsCount() {
                    return foodProductsCount;
                }

                public void setFoodProductsCount(String foodProductsCount) {
                    this.foodProductsCount = foodProductsCount;
                }

                public String getFoodProductsNumber() {
                    return foodProductsNumber;
                }

                public void setFoodProductsNumber(String foodProductsNumber) {
                    this.foodProductsNumber = foodProductsNumber;
                }

                public String getFoodProductsType() {
                    return foodProductsType;
                }

                public void setFoodProductsType(String foodProductsType) {
                    this.foodProductsType = foodProductsType;
                }

                public String getGroupCount() {
                    return groupCount;
                }

                public void setGroupCount(String groupCount) {
                    this.groupCount = groupCount;
                }

                public String getGroupName() {
                    return groupName;
                }

                public void setGroupName(String groupName) {
                    this.groupName = groupName;
                }

                public String getGroupNumber() {
                    return groupNumber;
                }

                public void setGroupNumber(String groupNumber) {
                    this.groupNumber = groupNumber;
                }

                public String getMinGroup() {
                    return minGroup;
                }

                public void setMinGroup(String minGroup) {
                    this.minGroup = minGroup;
                }

                public String getPackagePicture() {
                    return packagePicture;
                }

                public void setPackagePicture(String packagePicture) {
                    this.packagePicture = packagePicture;
                }

                public String getPriceMarkup() {
                    return priceMarkup;
                }

                public void setPriceMarkup(String priceMarkup) {
                    this.priceMarkup = priceMarkup;
                }

                public String getRemind() {
                    return remind;
                }

                public void setRemind(String remind) {
                    this.remind = remind;
                }

                public String getReplace() {
                    return replace;
                }

                public void setReplace(String replace) {
                    this.replace = replace;
                }

                public String getShopNumber() {
                    return shopNumber;
                }

                public void setShopNumber(String shopNumber) {
                    this.shopNumber = shopNumber;
                }

                public String getSingleProductName() {
                    return singleProductName;
                }

                public void setSingleProductName(String singleProductName) {
                    this.singleProductName = singleProductName;
                }

                public String getStandard() {
                    return standard;
                }

                public void setStandard(String standard) {
                    this.standard = standard;
                }

                public String getStandardName() {
                    return standardName;
                }

                public void setStandardName(String standardName) {
                    this.standardName = standardName;
                }

                public String getStandardNumber() {
                    return standardNumber;
                }

                public void setStandardNumber(String standardNumber) {
                    this.standardNumber = standardNumber;
                }
            }
        }
    }
}
