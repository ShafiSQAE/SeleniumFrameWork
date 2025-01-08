import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PaymentMethodPage extends BasePage {

    WebDriver driver;
    public PaymentMethodPage(WebDriver driver) {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css ="[placeholder='Select Country']")
    WebElement countrySearch;

    @FindBy(css = ".ta-item:nth-of-type(2)")
    WebElement countrySelect;

    By countrySelectWait=By.cssSelector("[placeholder='Select Country']");
    @FindBy(xpath = "//a[@class='btnn action__submit ng-star-inserted']")
            WebElement placeOrderButton;

    public void selectCountry(String countryName){
        Actions actions=new Actions(driver);
        actions.sendKeys(countrySearch,countryName).build().perform();
        countrySelect.click();
    }

    public OrderConfirmationPage placeOrder(){
        JavascriptExecutor js=(JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", placeOrderButton);
        OrderConfirmationPage orderConfirmationPage= new OrderConfirmationPage(driver);
        return orderConfirmationPage;

    }


//    WebElement placeOrderButton= driver.findElement(By.xpath("//a[@class='btnn action__submit ng-star-inserted']"));
//    JavascriptExecutor js=(JavascriptExecutor) driver;
//        js.executeScript("arguments[0].click();", placeOrderButton);

}
