package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class DashboardPage {

    @FindBy(className="total-count")
    public WebElement userCount;

    @FindBy(className="add-cost-button")
    public WebElement addCostBtn;

    @FindBy(id="itemName")
    WebElement itemCostInput;

    @FindBy(id="amount")
    WebElement amountInput;

    @FindBy(id="remarks")
    WebElement remarksInput;

    @FindBy(css="[type=submit]")
    WebElement submitBtn;

    @FindBy(className="view-button")
    public List<WebElement> viewItem;

    @FindBy(className="delete-button")
    WebElement deleteItemBtn;

    @FindBy(className="edit-button")
    public WebElement editItemBtn;

    @FindBy(className="back-button")
    public WebElement backToListBtn;

    public DashboardPage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }

    public void addCost(String itemName, String amount, String remarks){
        itemCostInput.sendKeys(itemName);
        amountInput.sendKeys(amount);
        remarksInput.sendKeys(remarks);
        submitBtn.click();
    }

    public void deleteItemFromList(){
        deleteItemBtn.click();
    }

    public void editItemFromList(String itemName,String amount, String remarks){
        itemCostInput.sendKeys(Keys.CONTROL,"a");
        itemCostInput.sendKeys(Keys.BACK_SPACE);
        itemCostInput.sendKeys(itemName);
        amountInput.sendKeys(Keys.CONTROL,"a");
        amountInput.sendKeys(Keys.BACK_SPACE);
        amountInput.sendKeys(amount);
        remarksInput.sendKeys(Keys.CONTROL,"a");
        remarksInput.sendKeys(Keys.BACK_SPACE);
        remarksInput.sendKeys(remarks);
        editItemBtn.click();
    }

}




