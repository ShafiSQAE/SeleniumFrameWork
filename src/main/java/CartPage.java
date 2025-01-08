import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends BasePage {

    WebDriver driver;
    public CartPage(WebDriver driver) {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css =".cartSection h3" )
    private List<WebElement> productsInCart; //encapsulating using private
    @FindBy(css=".totalRow button")
    private WebElement checkoutButton;

    public Boolean matchProductInCart(String productName){
        Boolean productMatchInCart = productsInCart.stream().anyMatch(s->s.getText().equals(productName));
        return productMatchInCart;
    }

    public PaymentMethodPage doCheckOut(){
        JavascriptExecutor js=(JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", checkoutButton);
        PaymentMethodPage paymentMethodPage= new PaymentMethodPage(driver);
        return paymentMethodPage;
    }


//    List<WebElement> productsInCart= driver.findElements(By.cssSelector(".cartSection h3"));
//    Boolean productMatchInCart= productsInCart.stream().anyMatch(prodItem->prodItem.getText().equals(productName));
//        Assert.assertTrue(productMatchInCart);
//
//    WebElement checkoutButton= driver.findElement(By.cssSelector(".totalRow button"));
//    JavascriptExecutor js=(JavascriptExecutor) driver;
//        js.executeScript("arguments[0].click();", checkoutButton);
}
