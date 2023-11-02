package webdriver;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Topic_13_Alert {
    WebDriver driver;
    WebDriverWait explicitWait;
    String projectLocation = System.getProperty("user.dir");
    By result = By.xpath("//p[@id = 'result']");

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();

    }

    @Test
    public void TC_01_Accept_Alert() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
        sleepInSecond(3);
//        CACH 1:
////        Alert alert = driver.switchTo().alert();
////        Assert.assertEquals(alert.getText(), "I am a JS Alert");
////        alert.accept();
////        Assert.assertEquals(driver.findElement(result).getText(), "You clicked an alert successfully");
        // CACH 2:
        Alert alert = explicitWait.until(ExpectedConditions.alertIsPresent());
        Assert.assertEquals(alert.getText(), "I am a JS Alert");
        alert.accept();
        Assert.assertEquals(driver.findElement(result).getText(), "You clicked an alert successfully");


    }

    @Test
    public void TC_02_Confirm_Alert() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
        sleepInSecond(3);
        Alert alert = explicitWait.until(ExpectedConditions.alertIsPresent());
        Assert.assertEquals(alert.getText(), "I am a JS Confirm");
        alert.dismiss();
        Assert.assertEquals(driver.findElement(result).getText(), "You clicked: Cancel");


    }

    @Test
    public void TC_03_Prompt_Alert() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
        sleepInSecond(3);
        Alert alert = explicitWait.until(ExpectedConditions.alertIsPresent());
        Assert.assertEquals(alert.getText(), "I am a JS prompt");
        String text = "Selenium";
        alert.sendKeys(text);
        sleepInSecond(3);
        alert.accept();
        Assert.assertEquals(driver.findElement(result).getText(), "You entered: " + text);


    }

    @Test
    public void TC_04_Authentication_Alert() throws IOException { //IOException la khi khong tim thay duong danadmin


        // Cach 1: truyen user/pass vao url
//        driver.get("http://admin:admin@the-internet.herokuapp.com/basic_auth");
//        Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(), 'Congratulations! You must have the proper credentials.')]")).isDisplayed());
        // Cach 2: Run tren Window (AutoIT)
        // Wait Alert xuat hien
        Runtime.getRuntime().exec(new String[]
                { projectLocation + "\\autoIT\\authen_firefox.exe", "admin", "admin"});
        driver.get("http://the-internet.herokuapp.com/basic_auth");
        sleepInSecond(5);
        Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(), 'Congratulations! You must have the proper credentials.')]")).isDisplayed());

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    public void sleepInSecond(long timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}


