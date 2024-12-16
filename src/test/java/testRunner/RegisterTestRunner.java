package testRunner;
import com.github.javafaker.Faker;
import config.Setup;
import config.UserModel;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.RegisterPage;
import uttils.Uttils;

import java.io.IOException;
import java.time.Duration;

public class RegisterTestRunner extends Setup {

    @Test(priority = 1, description = "user can register with all info")
    public void userRegisterByAllFields() throws InterruptedException, IOException, ParseException {
        RegisterPage register = new RegisterPage(driver);
        register.btnRegister.get(1).click();
        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String email = firstName+"@gmail.com";
        String password = "1234";
        String phoneNumber = "01710" + Uttils.generateRandomNumber(100000,999999);
        String address = faker.address().fullAddress();
        UserModel userModel = new UserModel();
        userModel.setFirstName(firstName);
        userModel.setLastName(lastName);
        userModel.setEmail(email);
        userModel.setPassword(password);
        userModel.setPhoneNumber(phoneNumber);
        userModel.setAddress(address);
        register.doRegister(userModel);

        registerAssertionTest();

        JSONObject userObject = new JSONObject();
        userObject.put("firstName",firstName);
        userObject.put("lastName",lastName);
        userObject.put("email",email);
        userObject.put("password",password);
        userObject.put("phoneNumber",phoneNumber);
        userObject.put("address",address);

        Uttils.saveUserInfo("./src/test/resources/user.json",userObject);
        Thread.sleep(5000);
    }

    //@Test(priority = 2, description = "user can register with mandatory fields")
    public void userRegisterByMandatoryFields() throws InterruptedException, IOException, ParseException {
        RegisterPage register = new RegisterPage(driver);
        register.btnRegister.get(1).click();
        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String email = firstName+"@gmail.com";
        String password = "1234";
        String phoneNumber = "01710" + Uttils.generateRandomNumber(100000,999999);
        UserModel userModel = new UserModel();
        userModel.setFirstName(firstName);
        userModel.setEmail(email);
        userModel.setPassword(password);
        userModel.setPhoneNumber(phoneNumber);
        register.doRegister(userModel);

        registerAssertionTest();

        JSONObject userObject = new JSONObject();
        userObject.put("firstName",firstName);
        userObject.put("email",email);
        userObject.put("password",password);
        userObject.put("phoneNumber",phoneNumber);

        Uttils.saveUserInfo("./src/test/resources/user.json",userObject);
    }

    public void registerAssertionTest() throws InterruptedException {
        Thread.sleep(2000);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("Toastify__toast")));
        String successMessageActual = driver.findElement(By.className("Toastify__toast")).getText();
        String successMessageExpected = "successfully";
        Assert.assertTrue(successMessageActual.contains(successMessageExpected));
    }
}
