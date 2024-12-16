package testRunner;

import config.Setup;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LoginTestRunner extends Setup {
    LoginPage loginPage;

    @Test(priority = 1, description = "admin login with wrong cred")
    public void adminLoginWithWrongCred() {
        loginPage = new LoginPage(driver);
        loginPage.doLogin("admin@test.com","admin");
        String headerActual = driver.findElement(By.tagName("p")).getText();
        String headerExpected = "Invalid";
        Assert.assertTrue(headerActual.contains(headerExpected));
        clear();
    }

    @Test(priority = 2, description = "admin login with right cred")
    public void adminLogin(){
        loginPage = new LoginPage(driver);
        loginPage.doLogin("admin@test.com","admin123");
        String headerActual = driver.findElement(By.tagName("h2")).getText();
        String headerExpected = "Admin Dashboard";
        Assert.assertTrue(headerActual.contains(headerExpected));
        DashboardPage dashboardPage  =  new DashboardPage(driver);
        String total_user = dashboardPage.userCount.getText();
        System.out.println(total_user);
        loginPage.doLogout();
    }

    @Test(priority = 3, description = "user login with right cred")
    public void userLogin() throws IOException, ParseException {
        loginPage = new LoginPage(driver);
        JSONParser parser = new JSONParser();
        JSONArray jsonArray = (JSONArray) parser.parse(new FileReader("./src/test/resources/user.json"));

        JSONObject userObj = (JSONObject) jsonArray.get(jsonArray.size()-1);
        String email = (String) userObj.get("email");
        String password = (String) userObj.get("password");
        loginPage.doLogin(email,password);
        String headerActual = driver.findElement(By.tagName("h2")).getText();
        String headerExpected = "User Daily Costs";
        Assert.assertTrue(headerActual.contains(headerExpected));
        Assert.assertTrue(loginPage.btnProfileIcon.isDisplayed());
        loginPage.doLogout();
    }

    public void clear(){
        loginPage = new LoginPage(driver);
        loginPage.txtEmail.sendKeys(Keys.CONTROL,"a");
        loginPage.txtEmail.sendKeys(Keys.BACK_SPACE);

        loginPage.txtPassword.sendKeys(Keys.CONTROL,"a");
        loginPage.txtPassword.sendKeys(Keys.BACK_SPACE);
    }
}
