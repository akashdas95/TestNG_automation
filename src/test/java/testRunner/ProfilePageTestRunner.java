package testRunner;

import config.ProfileModel;
import config.Setup;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProfilePage;

public class ProfilePageTestRunner extends Setup {

    @BeforeTest
    public void doLogin(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin("roseline.lowe@gmail.com","1234");
    }

    @Test(priority = 1, description = "User profile edit")
    public void userProfileEdit() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.btnProfileIcon.click();
        loginPage.btnProfileMenuItems.get(0).click();
        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.optionBtn.get(1).click();
        String filePath = "D:\\Java\\TestNG_Automation\\src\\test\\resources\\avater.JPG";
        profilePage.profileEdit(filePath);
        Thread.sleep(500);
        driver.switchTo().alert().accept();
        ProfileModel profileModel = new ProfileModel();
        profileModel.setFirstName("roseline");
        profileModel.setLastName("lowe");
        profileModel.setPhoneNumber("01234567789");
        profileModel.setAddress("Dhaka,Bangladesh");
        profileModel.setGender("female");
        profilePage.profileEdit(profileModel);
        Thread.sleep(1000);
        driver.switchTo().alert().accept();
    }

    @AfterTest
    public void doLogout(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogout();
    }
}
