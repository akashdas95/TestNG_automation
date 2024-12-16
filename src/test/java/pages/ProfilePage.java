package pages;

import config.ProfileModel;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProfilePage {

    @FindBy(css = "[type=button]")
    public List<WebElement> optionBtn;

    @FindBy(className = "upload-input")
    WebElement uploadPicInput;

    @FindBy(css = "[name=firstName]")
    WebElement firstNameInput;

    @FindBy(css = "[name=lastName]")
    WebElement lastNameInput;

    @FindBy(css = "[name=phoneNumber]")
    WebElement phoneNumberInput;

    @FindBy(css = "[name=address]")
    WebElement addressInput;

    @FindBy(css = "[name=gender]")
    WebElement genderInput;

    //constructor
    public ProfilePage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }

    public void profileEdit(ProfileModel profileModel){
        firstNameInput.sendKeys(Keys.CONTROL,"a");
        firstNameInput.sendKeys(Keys.BACK_SPACE);
        firstNameInput.sendKeys(profileModel.getFirstName());
        lastNameInput.sendKeys(Keys.CONTROL,"a");
        lastNameInput.sendKeys(Keys.BACK_SPACE);
        lastNameInput.sendKeys(profileModel.getLastName());
        phoneNumberInput.sendKeys(Keys.CONTROL,"a");
        phoneNumberInput.sendKeys(Keys.BACK_SPACE);
        phoneNumberInput.sendKeys(profileModel.getPhoneNumber());
        addressInput.sendKeys(Keys.CONTROL,"a");
        addressInput.sendKeys(Keys.BACK_SPACE);
        addressInput.sendKeys(profileModel.getAddress());
        genderInput.sendKeys(Keys.CONTROL,"a");
        genderInput.sendKeys(Keys.BACK_SPACE);
        genderInput.sendKeys(profileModel.getGender());
        optionBtn.get(2).click();
    }

    public void profileEdit(String filePath){
        uploadPicInput.sendKeys(filePath);
        optionBtn.get(1).click();
    }

}
