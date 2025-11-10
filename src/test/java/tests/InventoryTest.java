package tests;
import base.BaseTest;
import utils.DataDriven;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.InventoryPage;
import org.testng.Assert;

public class InventoryTest extends BaseTest {
    private DataDriven data;
    private LoginPage loginPage;
    private InventoryPage inventoryPage;

    // Runs before each @Test method.
    @BeforeMethod
    public void setUpTest() {
        data = new DataDriven();
        loginPage = new LoginPage(driver);
        loginPage.login(data.getValidUsername(), data.getValidPassword()); // Perform login using valid credentials
        inventoryPage = new InventoryPage(driver);
    }

    // Verify Inventory Page Elements After Login
    @Test(description = "Verify Inventory Page Elements After Login")
    public void verifyInventoryPageElements() {
        String title = inventoryPage.getBrowserTitle();
        Assert.assertEquals(title, "Swag Labs", "Browser title should be 'Swag Labs'"); // Verify browser title
        Assert.assertTrue(inventoryPage.isCartIconDisplayed(), "Cart icon should be displayed"); // Verify cart icon exists on page
        Assert.assertEquals(inventoryPage.getProductsCount(), 6, "There should be 6 products on the inventory page"); // Verify 6 products are displayed on the page
    }
}
