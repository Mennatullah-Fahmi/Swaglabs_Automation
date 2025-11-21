package tests;
import base.BaseTest;
import utils.DataDriven;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.Assert;
import pages.LoginPage;
import pages.InventoryPage;
import pages.CartPage;
import java.util.List;

public class CartTest extends BaseTest {
    private InventoryPage inventoryPage;
    private CartPage cartPage;
    String p1 = "Sauce Labs Backpack";
    String p2 = "Sauce Labs Bolt T-Shirt";
    String p3 = "Sauce Labs Onesie";

    // Runs before each @Test method.
    @BeforeMethod
    public void setUpTest() {
        DataDriven data = new DataDriven();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(data.getValidUsername(), data.getValidPassword()); //Login and navigate to Inventory page
        inventoryPage = new InventoryPage(driver);
    }



    // Verify cart is empty
    @Test(description = "Verify cart is empty after fresh login")
    public void verifyCartIsEmpty() {
        inventoryPage.clickCartIcon(); //Open Cart page
        cartPage = new CartPage(driver);
        Assert.assertTrue(cartPage.isCartEmpty(), "Cart should be empty for a new session.");
    }

    // Add 3 specific products and Verify in cart
    @Test(description = "Add 3 specific products and verify they appear in cart")
    public void add3SpecificProducts() {
        inventoryPage
                .addProductToCart(p1)
                .addProductToCart(p2)
                .addProductToCart(p3)
                .clickCartIcon();
        cartPage = new CartPage(driver);
        List<String> cartNames = cartPage.getProductNames();
        Assert.assertTrue(cartNames.contains(p1), p1 + " should be in the cart.");
        Assert.assertTrue(cartNames.contains(p2), p2 + " should be in the cart.");
        Assert.assertTrue(cartNames.contains(p3), p3 + " should be in the cart.");
        Assert.assertEquals(cartNames.size(), 3, "Exactly 3 items should be in the cart.");
    }

    // Remove one product and verify buttons
    @Test(description = "Remove one product from cart and verify buttons on inventory page")
    public void removeOneProductAndVerifyButtons() {
        inventoryPage
                .addProductToCart(p1)
                .addProductToCart(p2)
                .addProductToCart(p3);
        inventoryPage.clickCartIcon();
        cartPage = new CartPage(driver);
        cartPage.removeProduct(p2); // Remove "Sauce Labs Bolt T-Shirt"
        cartPage.clickContinueShopping();
        inventoryPage = new InventoryPage(driver);
        // Verify button text for each product
        String p1Button = inventoryPage.getProductButtonText(p1);
        String p2Button = inventoryPage.getProductButtonText(p2);
        String p3Button = inventoryPage.getProductButtonText(p3);
        // Removed product should show "Add to cart"
        Assert.assertEquals(p2Button, "Add to cart",
                p2 + " button should be 'Add to cart' after removal.");
        // Other two should still show "Remove"
        Assert.assertEquals(p1Button, "Remove",
                p1 + " button should still be 'Remove'.");
        Assert.assertEquals(p3Button, "Remove",
                p3 + " button should still be 'Remove'.");
    }
}
