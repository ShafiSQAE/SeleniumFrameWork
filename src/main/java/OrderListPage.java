import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class OrderListPage extends BasePage {

    WebDriver driver;
    public OrderListPage(WebDriver driver){
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//tr/td[2]")
    List<WebElement> orderListProductElements;

    public Boolean matchProductNameInOrderList(String productName){
        Boolean orderMatch = orderListProductElements.stream().anyMatch(s->s.getText().equalsIgnoreCase(productName));
        return orderMatch;
    }

}
