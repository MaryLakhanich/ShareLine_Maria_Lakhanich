import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SingUpTest {

    @Test
    public void negativeZipCodeTestLettersInsteadOfDigits() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.sharelane.com/cgi-bin/register.py");
        driver.findElement(By.name("zip_code")).sendKeys("ааааа");
        WebElement continueButton = driver.findElement(By.cssSelector("[value='Continue']"));
        continueButton.click();
        Assert.assertTrue(driver.findElement(By.className("error_message")).isDisplayed(), "Zipcode consisting of letters has been validated");
        driver.quit();
    }
        @Test
        public void negativeZipCodeTestOneDigit() {
            WebDriverManager.chromedriver().setup();
            WebDriver driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.get("https://www.sharelane.com/cgi-bin/register.py");
            driver.findElement(By.name("zip_code")).sendKeys("1");
            WebElement continueButton = driver.findElement(By.cssSelector("[value='Continue']"));
            continueButton.click();
            Assert.assertTrue(driver.findElement(By.className("error_message")).isDisplayed(), "Zipcode consisting of one digit has been validated");
            driver.quit();
        }
         @Test
        public void positiveSignUpTest () {
            WebDriverManager.chromedriver().setup();
            WebDriver driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.get("https://www.sharelane.com/cgi-bin/register.py?page=1&zip_code=54321");
            driver.findElement(By.name("first_name")).sendKeys("Mary");
            driver.findElement(By.name("email")).sendKeys("amigo@mail.ru");
            driver.findElement(By.name("password1")).sendKeys("22222");
            driver.findElement(By.name("password2")).sendKeys("22222");
            WebElement registerButton = driver.findElement(By.cssSelector("[value='Register']"));
            registerButton.click();
            Assert.assertTrue(driver.findElement(By.className("confirmation_message")).isDisplayed(), "Positive test with correct data has failed");
            driver.quit();
        }
        @Test
        public void negativeSignUpTestInvalidEmail () {
            WebDriverManager.chromedriver().setup();
            WebDriver driver = new ChromeDriver();
            driver.get("https://www.sharelane.com/cgi-bin/register.py?page=1&zip_code=33333");
            driver.findElement(By.name("first_name")).sendKeys("Mary");
            driver.findElement(By.name("email")).sendKeys("amigo");
            driver.findElement(By.name("password1")).sendKeys("33333");
            driver.findElement(By.name("password2")).sendKeys("33333");
            WebElement registerButton3 = driver.findElement(By.cssSelector("[value='Register']"));
            registerButton3.click();
            Assert.assertTrue(driver.findElement(By.className("error_message")).isDisplayed(), "Wrong email has been validated");
            driver.quit();
        }
        @Test
        public void negativeSignUpTestShortPassword () {
            WebDriverManager.chromedriver().setup();
            WebDriver driver = new ChromeDriver();
            driver.get("https://www.sharelane.com/cgi-bin/register.py?page=1&zip_code=88888");
            driver.findElement(By.name("first_name")).sendKeys("Mary");
            driver.findElement(By.name("email")).sendKeys("amigo11@mail.ru");
            driver.findElement(By.name("password1")).sendKeys("3");
            driver.findElement(By.name("password2")).sendKeys("3");
            WebElement registerButton= driver.findElement(By.cssSelector("[value='Register']"));
            registerButton.click();
            Assert.assertTrue(driver.findElement(By.className("error_message")).isDisplayed(), "Password consisting of one digit has been validated");
            driver.quit();
        }

    }
