package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_11_Button_Radio_Checkbox_I {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {

        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Egov_Button() {
        driver.get("https://egov.danang.gov.vn/reg");
        WebElement registerButton = driver.findElement(By.cssSelector("input.egov-button"));
        //Verify button is disable when not clicking on checkbox
        Assert.assertFalse(registerButton.isEnabled());
        driver.findElement(By.cssSelector("input#chinhSach")).click();
        sleepInSecond(3);
        Assert.assertTrue(registerButton.isEnabled());
        String backgroundColorRGB = registerButton.getCssValue("background-color");
        System.out.println("Background color with hexa code: " + backgroundColorRGB);
        Color backgroundColor = Color.fromString(backgroundColorRGB);
        String backgroundHexa = backgroundColor.asHex();
        Assert.assertEquals(backgroundHexa.toLowerCase(), "#ef5a00");


    }

    @Test
    public void TC_02_Fahasa_Button() {
        driver.get("https://www.fahasa.com/customer/account/create");
        driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();
        WebElement loginButton = driver.findElement(By.cssSelector("button.fhs-btn-login"));
        Assert.assertFalse(loginButton.isEnabled());
        String loginBackground = Color.fromString(loginButton.getCssValue("background-color")).asHex();
        Assert.assertEquals(loginBackground.toLowerCase(), "#000000");
        sleepInSecond(3);
        driver.findElement(By.cssSelector("input#login_username")).sendKeys("thu322@gmail.com");
        driver.findElement(By.cssSelector("input#login_password")).sendKeys("123456");
        Assert.assertTrue(loginButton.isEnabled());
        String loginBackgroundEnable = Color.fromString(loginButton.getCssValue("background-color")).asHex();
        Assert.assertEquals(loginBackgroundEnable.toLowerCase(), "#c92127");

    }

    @Test
    public void TC_03_() {

    }

    @Test
    public void TC_04_() {

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


