import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductCatalogue extends BasePage {

    WebDriver driver;

    public ProductCatalogue(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[contains(@class, 'mb-3')]")
    List<WebElement> products;

    By productsList = By.xpath("//div[contains(@class, 'mb-3')]");
    By productNameElement = By.xpath(".//div[@class='card-body']//b");
    By productAddToCartButton = By.xpath(".//div[@class='card-body']/button[2]");

    By toastMessage= By.cssSelector("#toast-container");
    By spinnerAnimation= By.cssSelector(".ng-animating");

    public void addProductToCart(String productName){
        elementToBeVisible(productsList);
        WebElement prod = products.stream().filter(s -> s.findElement(productNameElement).getText()
                .equals(productName)).findFirst().orElse(null);
        elementToBeVisible(toastMessage);
        //elementToBeDisappeared(spinnerAnimation);
        prod.findElement(productAddToCartButton).click();
//        CartPage cartPage = new CartPage(driver);
//        return cartPage;

    }

//
//        driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();
}