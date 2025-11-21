package pages;
import org.openqa.selenium.By;
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

    @FindBy(className = "inventory_item")
    private List<WebElement> products; //each product container has class "inventory_item"

    // Social media icons in footer
    @FindBy(className = "social_linkedin")
    private WebElement linkedinIcon;

    @FindBy(className = "social_facebook")
    private WebElement facebookIcon;

    @FindBy(className = "social_twitter")
    private WebElement twitterIcon;

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

    // Checks if the cart icon exists and visible
    public boolean isCartIconDisplayed() {
        try {
            return cartIcon.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // Returns the total number of products displayed in the inventory page.
    public int getProductsCount() {
        return products.size();
    }

    // Verifies if the page URL contains "/inventory.html"
    public boolean urlContainsInventory() {
        return driver.getCurrentUrl().contains("/inventory.html");
    }

    // Clicks the cart icon and opens the cart page.
    public CartPage clickCartIcon() {
        cartIcon.click();
        return new CartPage(driver);
    }

    // Social icon actions methods
    public void clickLinkedInIcon() {
        linkedinIcon.click();
    }

    public void clickFacebookIcon() {
        facebookIcon.click();
    }

    public void clickTwitterIcon() {
        twitterIcon.click();
    }

    //Clicks the "Add to cart" or "Remove" button for a product, identified by its name on the inventory page.
    public void clickProductButton(String productName) {
        for (WebElement product : products) {
            WebElement nameElement = product.findElement(By.className("inventory_item_name"));
            String nameText = nameElement.getText().trim();
            if (nameText.equals(productName)) { // if this is the product we want
                WebElement button = product.findElement(By.tagName("button"));
                button.click(); // find its button and click it
                break;
            }
        }
    }

    //Returns the current button text for a product ("Add to cart" or "Remove") by searching inside the products list.
    public String getProductButtonText(String productName) {
        for (WebElement product : products) {
            WebElement nameElement = product.findElement(By.className("inventory_item_name"));
            String nameText = nameElement.getText().trim();
            if (nameText.equals(productName)) {
                WebElement button = product.findElement(By.tagName("button"));
                return button.getText().trim();
            }
        }
        return ""; //If product not found, return empty text
    }

    // Add a product to the cart by name
    public InventoryPage addProductToCart(String productName) {
        clickProductButton(productName);
        return this;
    }

}
