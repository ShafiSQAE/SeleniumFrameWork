import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends BasePage {

    WebDriver driver;
    public LandingPage(WebDriver driver){
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }



    @FindBy(id="userEmail")
    private WebElement emailField; //encapsulation
    @FindBy(id="userPassword")
    private WebElement passwordField;
    @FindBy(id="login")
    private WebElement loginButton;

    @FindBy(xpath = "//div[@aria-label='Incorrect email or password.']")
    private WebElement errorMessage;

    public ProductCatalogue doLogin(String userName, String userPassword){
        emailField.sendKeys(userName);
        passwordField.sendKeys(userPassword);
        loginButton.click();
        ProductCatalogue productCatalogue=new ProductCatalogue(driver);
        return productCatalogue;
    }

    public String getLoginErrorMessage(){
        elementToBeVisible2(errorMessage);
        return errorMessage.getText();
    }


    public void goToLandingPage(){
        driver.get("https://rahulshettyacademy.com/client");
    }

}
