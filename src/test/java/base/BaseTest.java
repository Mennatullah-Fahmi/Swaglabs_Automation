package base;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    protected WebDriver driver;
    protected final String BASE_URL = "https://www.saucedemo.com/";

    // Runs before each @Test method
    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.get(BASE_URL); // Navigating to the URL
    }

    // Runs after each @Test method
    // Close the browser and terminate the session
    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
