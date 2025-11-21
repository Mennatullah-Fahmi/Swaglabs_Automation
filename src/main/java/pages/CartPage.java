package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.ArrayList;
import java.util.List;

public class CartPage {
    private final WebDriver driver;

// Page elements
    //Cart item containers
    @FindBy(className = "cart_item")
    private List<WebElement> cartItems;

    //Product names inside the cart
    @FindBy(className = "inventory_item_name")
    private List<WebElement> cartItemNames;

    //Button to go back to inventory page
    @FindBy(id = "continue-shopping")
    private WebElement continueShoppingButton;

    // Constructor
    public CartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

// Methods
    //Returns total number of items currently in the cart
    public int getCartItemsCount() {
        return cartItems.size();
    }

    //Returns true if the cart has no items
    public boolean isCartEmpty() {
        return getCartItemsCount() == 0;
    }

    //Returns a list of product names currently in the cart
    public List<String> getProductNames() {
        List<String> names = new ArrayList<>();
        for (WebElement itemName : cartItemNames) {
            names.add(itemName.getText().trim());
        }
        return names;
    }

    //Removes a product from the cart by its name
    public void removeProduct(String productName) {
        String xpath = "//div[@class='inventory_item_name' and text()='" + productName + "']" +
                "/ancestor::div[@class='cart_item']//button";
        driver.findElement(By.xpath(xpath)).click();
    }

    //Clicks Continue Shopping and returns to the inventory page.
    public void clickContinueShopping() {
        continueShoppingButton.click();
    }
}
