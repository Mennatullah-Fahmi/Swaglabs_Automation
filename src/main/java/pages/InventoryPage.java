package pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

public class InventoryPage {
    private WebDriver driver;

// Page elements
    @FindBy(className = "app_logo")
    private WebElement pageTitle;

    @FindBy(id = "shopping_cart_container")
    private WebElement cartIcon;

    // each product container has class "inventory_item"
    @FindBy(className = "inventory_item")
    private List<WebElement> products;

    // Constructor
    public InventoryPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

// Methods
    // Return the browser title
    public String getBrowserTitle() {
        return driver.getTitle();
    }

    //Checks if the cart icon exists and visible
    public boolean isCartIconDisplayed() {
        try {
            return cartIcon.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    //Returns the total number of products displayed in the inventory page.
    public int getProductsCount() {
        return products.size();
    }

    //Verifies if the page URL contains "/inventory.html"
    public boolean urlContainsInventory() {
        return driver.getCurrentUrl().contains("/inventory.html");
    }
}
