package tests;
import base.BaseTest;
import utils.DataDriven;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.InventoryPage;
import org.testng.Assert;

import java.util.Set;

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

    // Verify Social Links
    @Test(description = "Verify social footer links open correct domains")
    public void verifySocialLinks() {
        String mainWindow = driver.getWindowHandle();
        //LinkedIn
        inventoryPage.clickLinkedInIcon();
        String linkedinUrl = switchToNewTabAndGetUrl(mainWindow);
        Assert.assertTrue(linkedinUrl.toLowerCase().contains("linkedin"),
                "LinkedIn URL should contain 'linkedin'. Actual: " + linkedinUrl);
        closeTabAndReturnTo(mainWindow);
        //Facebook
        inventoryPage.clickFacebookIcon();
        String facebookUrl = switchToNewTabAndGetUrl(mainWindow);
        Assert.assertTrue(facebookUrl.toLowerCase().contains("facebook"),
                "Facebook URL should contain 'facebook'. Actual: " + facebookUrl);
        closeTabAndReturnTo(mainWindow);
        //Twitter
        inventoryPage.clickTwitterIcon();
        String twitterUrl = switchToNewTabAndGetUrl(mainWindow);

        closeTabAndReturnTo(mainWindow);
    }

    // Switch to newly opened tab and return its URL
    private String switchToNewTabAndGetUrl(String mainWindowHandle) {
        Set<String> handles = driver.getWindowHandles();
        for (String handle : handles) {
            if (!handle.equals(mainWindowHandle)) {
                driver.switchTo().window(handle);
                return driver.getCurrentUrl();
            }
        }
        return "";
    }

    // Close current tab and go back to main window
    private void closeTabAndReturnTo(String mainWindowHandle) {
        driver.close();
        driver.switchTo().window(mainWindowHandle);
    }
}
