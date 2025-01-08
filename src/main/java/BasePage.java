import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

public class BasePage {

    WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }


    @FindBy(xpath = "//button[@routerlink='/dashboard/cart']")
    WebElement cartButton;
    @FindBy(xpath = "//button[@routerlink='/dashboard/myorders']")
    WebElement orderButton;

    public void elementToBeVisible(By findBy){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));
        wait.until(ExpectedConditions.visibilityOfElementLocated((findBy)));
    }

    public void elementToBeVisible2(WebElement findBy){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));
        wait.until(ExpectedConditions.visibilityOf((findBy)));
    }

    public void elementToBeDisappeared(By findBy) {
        //Thread.sleep(1000);
    WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(2));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(findBy));
    }

    public CartPage clickOnCart(){
        cartButton.click();
        CartPage cartPage=new CartPage(driver);
        return cartPage;
    }

    public OrderListPage clickOnOrder(){
        orderButton.click();
        OrderListPage orderListPage= new OrderListPage(driver);
        return orderListPage;

    }


}
