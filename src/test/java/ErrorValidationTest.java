import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class ErrorValidationTest extends BaseTest {


    @Test (groups = "ErrorHandling", retryAnalyzer = Retry.class)
    public void loginErrorValidation() {


        landingPage.doLogin("shafiaaasdfasf.ice@gmail.com", "Ss125345@");
        Assert.assertEquals("Incorrect email or password.", landingPage.getLoginErrorMessage());

    }

    @Test
    public void productErrorValidation() throws InterruptedException, IOException {

        String productName="IPHONE 13 PRO";
        String countryName="india";
        ProductCatalogue productCatalogue=landingPage.doLogin("shafi.ice@gmail.com","Ss12345@");

        productCatalogue.addProductToCart(productName);
        Thread.sleep(2000);

        CartPage cartPage=productCatalogue.clickOnCart();
        Boolean productMatchInCart= cartPage.matchProductInCart(productName);
        Assert.assertTrue(productMatchInCart);
    }
}
