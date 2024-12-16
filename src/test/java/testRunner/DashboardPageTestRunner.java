package testRunner;
import config.Setup;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;

public class DashboardPageTestRunner extends Setup {

    @BeforeTest
    public void doLogin(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin("roseline.lowe@gmail.com","1234");
        //loginPage.doLogin("admin@test.com","admin123");
    }

    //@Test(priority = 1)
    public void userCountFromAdminAccount(){
        DashboardPage dashboardPage  =  new DashboardPage(driver);
        String total_user = dashboardPage.userCount.getText();
        System.out.println(total_user);
    }

    @Test(priority = 1, description = "Add an item with cost")
    public void addCost() throws InterruptedException {
      DashboardPage dashboardPage  =  new DashboardPage(driver);
      dashboardPage.addCostBtn.click();
      String itemName = "bat";
      String amount = "50";
      String remarks = "It is for my brother";
      dashboardPage.addCost(itemName,amount,remarks);
      Thread.sleep(500);
      driver.switchTo().alert().accept();
    }

    @Test(priority = 2, description = "Edit an item from list")
    public void editItem() throws InterruptedException {
        DashboardPage dashboardPage  =  new DashboardPage(driver);
        dashboardPage.viewItem.get(0).click();
        dashboardPage.editItemBtn.click();
        String itemName = "ball";
        String amount = "30";
        String remarks = "It is for my sister";
        dashboardPage.editItemFromList(itemName,amount,remarks);
        Thread.sleep(500);
        driver.switchTo().alert().accept();
        dashboardPage.backToListBtn.click();
    }

    @Test(priority = 3, description = "delete an item from list")
    public void deleteItem() {
        DashboardPage dashboardPage  =  new DashboardPage(driver);
        dashboardPage.viewItem.get(0).click();
        dashboardPage.deleteItemFromList();
        driver.switchTo().alert().accept();
    }

    @AfterTest
    public void doLogout(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogout();
    }

}
