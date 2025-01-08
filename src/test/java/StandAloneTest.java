import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class StandAloneTest {

    public static void main(String[] args) throws InterruptedException {

        String productName="IPHONE 13 PRO"; //Banarsi Saree

        WebDriverManager.edgedriver().setup();
        WebDriver driver= new EdgeDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://rahulshettyacademy.com/client");
        driver.manage().window().maximize();

        driver.findElement(By.id("userEmail")).sendKeys("shafi.ice@gmail.com");
        driver.findElement(By.id("userPassword")).sendKeys("Ss12345@");
        driver.findElement(By.id("login")).click();


        List<WebElement> products = driver.findElements(By.xpath("//div[contains(@class, 'mb-3')]"));
        WebElement prod = products.stream().filter(product->
                product.findElement(By.xpath(".//div[@class='card-body']//b")).getText()
                        .equals(productName)).findFirst().orElse(null);
        prod.findElement(By.xpath(".//div[@class='card-body']/button[2]")).click();

        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
        //wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
        Thread.sleep(2000);
        driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();

        List<WebElement> productsInCart= driver.findElements(By.cssSelector(".cartSection h3"));
        Boolean productMatchInCart= productsInCart.stream().anyMatch(prodItem->prodItem.getText().equals(productName));
        Assert.assertTrue(productMatchInCart);

        WebElement checkoutButton= driver.findElement(By.cssSelector(".totalRow button"));
        JavascriptExecutor js=(JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", checkoutButton);



        Actions a= new Actions(driver);
        a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india").build().perform();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));

        //driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
        //Thread.sleep(2000);
       driver.findElement(By.cssSelector(".ta-item:nth-of-type(2)")).click();
        //driver.findElement(By.xpath("//button[@type='button'][2]")).click();

        WebElement placeOrderButton= driver.findElement(By.xpath("//a[@class='btnn action__submit ng-star-inserted']"));
        js.executeScript("arguments[0].click();", placeOrderButton);

        String confirmationMessage= driver.findElement(By.cssSelector(".hero-primary")).getText();
        Assert.assertEquals("THANKYOU FOR THE ORDER.",confirmationMessage);

    }
}
