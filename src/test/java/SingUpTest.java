import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class SingUpTest {
    public WebDriver driver;

    @BeforeClass
    public void setUp () {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        }
     @AfterClass
     public void quit () {
        driver.quit();
     }
    @Test
    public void negativeZipCodeTestLettersInsteadOfDigits() {
        driver.get("https://www.sharelane.com/cgi-bin/register.py");
        driver.findElement(By.name("zip_code")).sendKeys("ааааа");
        WebElement continueButton = driver.findElement(By.cssSelector("[value='Continue']"));
        continueButton.click();
        Assert.assertTrue(driver.findElement(By.className("error_message")).isDisplayed(), "Zipcode consisting of letters has been validated");
    }
        @Test
        public void negativeZipCodeTestOneDigit() {
            driver.get("https://www.sharelane.com/cgi-bin/register.py");
            driver.findElement(By.name("zip_code")).sendKeys("1");
            WebElement continueButton = driver.findElement(By.cssSelector("[value='Continue']"));
            continueButton.click();
            Assert.assertTrue(driver.findElement(By.className("error_message")).isDisplayed(), "Zipcode consisting of one digit has been validated");
        }
         @Test
        public void positiveSignUpTest () {
            driver.get("https://www.sharelane.com/cgi-bin/register.py?page=1&zip_code=54321");
            driver.findElement(By.name("first_name")).sendKeys("Mary");
            driver.findElement(By.name("email")).sendKeys("amigo@mail.ru");
            driver.findElement(By.name("password1")).sendKeys("22222");
            driver.findElement(By.name("password2")).sendKeys("22222");
            WebElement registerButton = driver.findElement(By.cssSelector("[value='Register']"));
            registerButton.click();
            Assert.assertTrue(driver.findElement(By.className("confirmation_message")).isDisplayed(), "Positive test with correct data has failed");
        }
        @Test
        public void negativeSignUpTestInvalidEmail () {
            driver.get("https://www.sharelane.com/cgi-bin/register.py?page=1&zip_code=33333");
            driver.findElement(By.name("first_name")).sendKeys("Mary");
            driver.findElement(By.name("email")).sendKeys("amigo");
            driver.findElement(By.name("password1")).sendKeys("33333");
            driver.findElement(By.name("password2")).sendKeys("33333");
            WebElement registerButton3 = driver.findElement(By.cssSelector("[value='Register']"));
            registerButton3.click();
            Assert.assertTrue(driver.findElement(By.className("error_message")).isDisplayed(), "Wrong email has been validated");
        }
        @Test
        public void negativeSignUpTestShortPassword () {
            driver.get("https://www.sharelane.com/cgi-bin/register.py?page=1&zip_code=88888");
            driver.findElement(By.name("first_name")).sendKeys("Mary");
            driver.findElement(By.name("email")).sendKeys("amigo11@mail.ru");
            driver.findElement(By.name("password1")).sendKeys("3");
            driver.findElement(By.name("password2")).sendKeys("3");
            WebElement registerButton= driver.findElement(By.cssSelector("[value='Register']"));
            registerButton.click();
            Assert.assertTrue(driver.findElement(By.className("error_message")).isDisplayed(), "Password consisting of one digit has been validated");
        }
        @Test
        public void positiveTestAddBookToCart () {
            driver.get("https://www.sharelane.com/cgi-bin/main.py");
            WebElement testPortalButton = driver.findElement(By.cssSelector("[href='../test_portal.html']"));
            testPortalButton.click();
            WebElement accountCreatorButton = driver.findElement(By.cssSelector("[href='../cgi-bin/create_account.py']"));
            accountCreatorButton.click();
            WebElement newAccountButton = driver.findElement(By.cssSelector("[value='Create new user account']"));
            newAccountButton.click();
            WebElement autoLogInButton = driver.findElement(By.cssSelector("[value='Auto Login']"));
            autoLogInButton.click();
            driver.get("https://www.sharelane.com/cgi-bin/show_book.py?book_id=2");
            WebElement addToCartButton = driver.findElement(By.cssSelector("[src='../images/add_to_cart.gif']"));
            addToCartButton.click();
            Assert.assertTrue(driver.findElement(By.className("confirmation_message")).isDisplayed(), "Positive test with correct data has failed");

        }

    }
