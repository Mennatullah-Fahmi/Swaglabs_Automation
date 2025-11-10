package tests;
import base.BaseTest;
import utils.DataDriven;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.InventoryPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;

public class LoginTest extends BaseTest{
    private DataDriven data;
    private LoginPage loginPage;

    // Runs before each @test method
    @BeforeMethod
    public void beforeEach() {
        data = new DataDriven();
        loginPage = new LoginPage(driver);
    }

    // Verify Successful Login
    @Test(description = "Verify Successful Login, URL contains /inventory.html")
    public void verifySuccessfulLogin() {
        loginPage.login(data.getValidUsername(), data.getValidPassword());
        InventoryPage inventoryPage = new InventoryPage(driver);
        Assert.assertTrue(inventoryPage.urlContainsInventory(), "URL should contain /inventory.html");
    }

    // Verify Invalid Login
    @Test(description = "Verify Invalid Login shows 'Username and password do not match'")
    public void verifyInvalidLogin() {
        loginPage.login(data.getInvalidUsername(), data.getInvalidPassword());
        String err = loginPage.getErrorMessage();
        Assert.assertTrue(err.contains("Username and password do not match") ||
                        err.contains("Username and password do not match any user"),
                "Error message should indicate username/password mismatch. Actual: " + err);
    }

    // Verify Login Without Password
    @Test(description = "Verify Login Without Password shows 'Password is required'")
    public void verifyLoginWithoutPassword() {
        loginPage.enterUsername(data.getValidUsername());
        loginPage.enterPassword(""); // empty
        loginPage.clickLogin();
        String err = loginPage.getErrorMessage();
        Assert.assertTrue(err.contains("Password is required"), "Error message should say Password is required. Actual: " + err);
    }
}
