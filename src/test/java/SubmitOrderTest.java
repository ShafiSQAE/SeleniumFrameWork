import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class SubmitOrderTest extends BaseTest{


    @Test(dataProvider="getData", groups = "Purchase")
    public void submitOrderTest(HashMap<String,String> input) throws InterruptedException, IOException {

        String countryName="india";
        ProductCatalogue productCatalogue=landingPage.doLogin(input.get("email"), input.get("password"));

        productCatalogue.addProductToCart(input.get("productName"));
        Thread.sleep(2000);

        CartPage cartPage=productCatalogue.clickOnCart();
        Boolean productMatchInCart= cartPage.matchProductInCart(input.get("productName"));
        Assert.assertTrue(productMatchInCart);

        PaymentMethodPage paymentMethodPage=cartPage.doCheckOut();
        paymentMethodPage.selectCountry(countryName);

        OrderConfirmationPage orderConfirmationPage= paymentMethodPage.placeOrder();
        String orderConfirmed= orderConfirmationPage.confirmMessage();
        Assert.assertEquals("THANKYOU FOR THE ORDER.",orderConfirmed);

    }

    @Test (dataProvider = "getData", dependsOnMethods = "submitOrderTest")
    public void productVerifyInOrderList(HashMap<String,String> input){
        //String productName="IPHONE 13 PRO";
        ProductCatalogue productCatalogue=landingPage.doLogin(input.get("email"),input.get("password"));
        OrderListPage orderListPage= productCatalogue.clickOnOrder();
        Boolean match = orderListPage.matchProductNameInOrderList(input.get("productName"));
        Assert.assertTrue(match, input.get("productName"));

    }


    @DataProvider
    public Object[][] getData() throws IOException {

        List<HashMap<String,String>> data= getJsonDataToMap(
                "src/test/java/data/purchase.json");
        return new Object[][] {{data.get(0)},{data.get(1)}};
    }




/* If we want to give data by Hashmap then the code and data provider will be
like this:
        public void submitOrderTest(HashMap<String,String> input) throws InterruptedException, IOException {

        String countryName="india";
        ProductCatalogue productCatalogue=landingPage.doLogin(input.get("email"), input.get("password"));

        productCatalogue.addProductToCart(input.get("productName"));


    @DataProvider
    public Object[][] getData(){

        HashMap<String,String> map1= new HashMap<String,String>();
        map1.put("email","shafi.ice@gmail.com");
        map1.put("password","Ss12345@");
        map1.put("productName","IPHONE 13 PRO");

        HashMap<String,String> map2= new HashMap<String,String>();
        map2.put("email","safi.ice@gmail.com");
        map2.put("password","Ss12345@");
        map2.put("productName","QWERTY");


        return new Object[][] {{map1},{map2}};
    } */


/*  If we want to hardcode data provider for email password and product name then the code block and data
provider will be like this:

  public void submitOrderTest(String email,String password,String productName) throws InterruptedException, IOException {
   String countryName="india";
   ProductCatalogue productCatalogue=landingPage.doLogin(email,password);
   productCatalogue.addProductToCart(productName);

   @DataProvider
   public Object[][] getData(){

       return new Object[][] {{"shafi.ice@gmail.com","Ss12345@","IPHONE 13 PRO"},
              {"safi.ice@gmail.com","Ss12345@","QWERTY"}};
   } */


}
