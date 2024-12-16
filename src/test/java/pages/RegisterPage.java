package pages;

import config.UserModel;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class RegisterPage {

    //properties
    @FindBy(tagName = "a")
    public List<WebElement> btnRegister;

    @FindBy(id = "firstName")
    WebElement txtFirstName;

    @FindBy(id = "lastName")
    WebElement txtLastName;

    @FindBy(id = "email")
    WebElement txtEmail;

    @FindBy(id = "password")
    WebElement txtPassword;

    @FindBy(id = "phoneNumber")
    WebElement txtPhoneNumber;

    @FindBy(id = "address")
    WebElement txtAddress;

    @FindBy(css = "[type = radio]")
    List<WebElement> radioBtn;

    @FindBy(css = "[type = checkbox]")
    WebElement chkAcceptTerm;

    @FindBy(id = "register")
    WebElement btnSubmit;

    //constructor
    public RegisterPage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }

    //method
    public void doRegister(UserModel userModel){
        txtFirstName.sendKeys(userModel.getFirstName());
        txtLastName.sendKeys(userModel.getLastName()!=null?userModel.getLastName():"");
        txtEmail.sendKeys(userModel.getEmail());
        txtPassword.sendKeys(userModel.getPassword());
        txtPhoneNumber.sendKeys(userModel.getPhoneNumber());
        txtAddress.sendKeys(userModel.getAddress()!=null?userModel.getAddress():"");
        radioBtn.get(0).click();
        chkAcceptTerm.click();
        btnSubmit.click();
    }

}
