package pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

// Page elements
    @FindBy(id = "user-name")
    private WebElement usernameInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    @FindBy(xpath = "//*[@data-test='error']")
    private WebElement errorContainer;

    // Constructor
    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

// Methods
    // Enters username in the username field.
    public void enterUsername(String username) {
        usernameInput.sendKeys(username);
    }

    // Enters password in the password field
    public void enterPassword(String password) {
        passwordInput.sendKeys(password);
    }

    // Clicks the login button.
    public void clickLogin() {
        loginButton.click();
    }

    // Performs the complete login process
    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }

    // Validation ➡ Returns the authentication error text when login fails.
    public String getErrorMessage() {
        try {
            return errorContainer.getText().trim();
        } catch (Exception e) {
            return "";
        }
    }

}
