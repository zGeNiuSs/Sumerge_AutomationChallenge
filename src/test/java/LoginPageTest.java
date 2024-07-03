import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import org.example.LoginPage;
import org.testng.asserts.SoftAssert;

public class LoginPageTest {
    WebDriver driver = null;
    SoftAssert softAssert = null;
    LoginPage loginPage = null;

    @BeforeClass
    public void openBrowser() {
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
        driver = new ChromeDriver();
        softAssert = new SoftAssert();
        loginPage = new LoginPage(driver);

    }

    @BeforeMethod
    public void beforeTest() throws InterruptedException {
        driver.navigate().to("https://www.saucedemo.com/");
    }

    @AfterClass
    public void closeBrowser() throws InterruptedException {
        Thread.sleep(2000);
        driver.quit();
    }

    //1.Check if the username and password fields are on the main screen of the application
    @Test
    public void testElementsDisplayed() {
        softAssert.assertTrue(loginPage.username().isDisplayed());
        softAssert.assertTrue(loginPage.password().isDisplayed());
        softAssert.assertTrue(loginPage.loginButton().isDisplayed());
        softAssert.assertAll();
    }

    //2.Check if the given valid credentials work
    @Test
    public void testValidCredentials() throws InterruptedException {
        loginPage.clearFields();
        loginPage.username().sendKeys("standard_user");
        loginPage.password().sendKeys("secret_sauce");
        loginPage.loginButton().click();
        Thread.sleep(1000);
        softAssert.assertTrue(driver.getCurrentUrl().contains("www.saucedemo.com/inventory.html"));
        softAssert.assertEquals(loginPage.swagLabsHeader().getText(), "Swag Labs");
        softAssert.assertAll();
    }

    //3.Check if the given wrong credentials work
    @Test
    public void testInvalidCredentials() throws InterruptedException {
        loginPage.clearFields();
        loginPage.username().sendKeys("invalid_user");
        loginPage.password().sendKeys("invalid_password");
        loginPage.loginButton().click();
        Thread.sleep(1000);
        softAssert.assertTrue(loginPage.errorMsg().isDisplayed(), "Error Message is not displayed");
        softAssert.assertEquals(loginPage.errorMsg().getText(),"Epic sadface: Username and password do not match any user in this service");
        softAssert.assertAll();
    }

    //4.Check for empty credentials
    @Test
    public void testEmptyCredentials() throws InterruptedException {
        loginPage.clearFields();
        loginPage.loginButton().click();
        Thread.sleep(1000);
        softAssert.assertTrue(loginPage.errorMsg().isDisplayed(), "Error Message is not displayed");
        softAssert.assertEquals(loginPage.errorMsg().getText(), "Epic sadface: Username is required");

        loginPage.username().sendKeys("standard_user");
        loginPage.loginButton().click();
        Thread.sleep(1000);
        softAssert.assertTrue(loginPage.errorMsg().isDisplayed(), "Error Message is not displayed");
        softAssert.assertEquals(loginPage.errorMsg().getText(), "Epic sadface: Password is required");
        softAssert.assertAll();
    }
}
