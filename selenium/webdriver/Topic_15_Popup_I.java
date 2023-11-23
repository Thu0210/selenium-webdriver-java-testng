package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_15_Popup_I {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Fixed_Popup_In_DOM_I() {
        driver.get("https://ngoaingu24h.vn/");
        driver.findElement(By.cssSelector("button.login_")).click();
        sleepInSecond(3);
        By loginPopup = By.cssSelector("div[id='modal-login-v1'][style]>div");
        Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());
        driver.findElement(By.cssSelector("div[id='modal-login-v1'][style] input#account-input")).sendKeys("AutomationFC");
        driver.findElement(By.cssSelector("div[id='modal-login-v1'][style] input#password-input")).sendKeys("AutomationFC");
        driver.findElement(By.cssSelector("div[id='modal-login-v1'][style] button.buttonLoading")).click();
        sleepInSecond(2);
        Assert.assertEquals(driver.findElement(By.cssSelector("div[id='modal-login-v1'][style] div.error-login-panel")).getText(), "Tài khoản không tồn tại!");
        driver.findElement(By.cssSelector("div[id='modal-login-v1'][style] button.close")).click();
        Assert.assertFalse(driver.findElement(loginPopup).isDisplayed());

    }

    @Test
    public void TC_02_Fixed_Popup_In_DOM_II() {
        driver.get("https://skills.kynaenglish.vn/");
        driver.findElement(By.cssSelector("a.login-btn")).click();
        By loginPopup = By.cssSelector("div[id='k-popup-account-login-mb'] div.modal-content");
        Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());
        driver.findElement(By.cssSelector("div[id='k-popup-account-login-mb'] input#user-login")).sendKeys("automation@gmail.com");
        driver.findElement(By.cssSelector("div[id='k-popup-account-login-mb'] input#user-password")).sendKeys("123456");
        driver.findElement(By.cssSelector("div[id='k-popup-account-login-mb'] button#btn-submit-login")).click();
        sleepInSecond(3);
        Assert.assertEquals(driver.findElement(By.cssSelector("div#password-form-login-message")).getText(), "Sai tên đăng nhập hoặc mật khẩu");
        driver.findElement(By.cssSelector("div[id='k-popup-account-login-mb'] button.k-popup-account-close")).click();
        Assert.assertFalse(driver.findElement(loginPopup).isDisplayed());

    }

    @Test
    public void TC_03_Fixed_Popup_Not_In_DOM_I() {
        driver.get("https://tiki.vn/");
        driver.findElement(By.cssSelector("div[data-view-id='header_header_account_container']")).click();
        sleepInSecond(3);
        Assert.assertTrue(driver.findElement(By.cssSelector("div.ReactModal__Content")).isDisplayed());
        driver.findElement(By.cssSelector("p.login-with-email")).click();
        sleepInSecond(3);
        driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();
        sleepInSecond(2);
        Assert.assertEquals(driver.findElement(By.xpath("//input[@type='email']/parent::div/following-sibling::span[1]")).getText(), "Email không được để trống");
        Assert.assertEquals(driver.findElement(By.xpath("//input[@type='password']/parent::div/following-sibling::span[1]")).getText(), "Mật khẩu không được để trống");
        driver.findElement(By.cssSelector("button.btn-close")).click();
        // Khi dong popup thi khong con trong DOM
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Assert.assertEquals(driver.findElements(By.cssSelector("div.ReactModal__Content")).size(), 0);


    }

    @Test
    public void TC_04_Fixed_Popup_Not_In_DOM_II() {
        driver.get("https://www.facebook.com/");
        driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();
        sleepInSecond(2);
        Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/parent::div")).isDisplayed());
        driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/preceding-sibling::img")).click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Assert.assertEquals(driver.findElements(By.xpath("//div[text()='Sign Up']/parent::div/parent::div")).size(), 0);

    }

    @Test
    public void TC_05_Random_Popup_Not_In_DOM() {
        driver.get("https://www.javacodegeeks.com/");
        sleepInSecond(2);
        By popupMarketing = By.cssSelector("div.lepopup-popup-container>div:not([style^='display:none'])");
        if (driver.findElements(popupMarketing).size() > 0 && driver.findElements(popupMarketing).get(0).isDisplayed()) {
            driver.findElement(By.cssSelector("div.lepopup-popup-container>div:not([style^='display:none']) div.lepopup-element-html-content>a")).click();
            sleepInSecond(3);

        }
        driver.findElement(By.cssSelector("input#search-input")).sendKeys("Agile Testing Explained");
        driver.findElement(By.cssSelector("button#search-submit")).click();
        sleepInSecond(3);
        Assert.assertTrue(driver.findElement(By.xpath("//a[text()='Agile Testing Explained']")).isDisplayed());


    }

    @Test
    public void TC_06_Random_Popup_In_DOM() {
        driver.get("https://vnk.edu.vn/");
        sleepInSecond(20);
        By marketingPopup = By.cssSelector("div.tve-leads-conversion-object");
        if (driver.findElement(marketingPopup).isDisplayed()) {
            driver.findElement(By.cssSelector("div.tcb-icon-display")).click();
            sleepInSecond(3);
        }
        driver.findElement(By.cssSelector("button.btn-danger")).click();
        sleepInSecond(3);
        Assert.assertEquals(driver.findElement(By.cssSelector("div.title-content>h1")).getText(), "Lịch Khai Giảng");
    }

    @Test
    public void TC_07_Random_Popup_Not_In_DOM() {
        driver.get("https://dehieu.vn/");
        sleepInSecond(3);
        By marketingPopup = By.cssSelector("div.popup-content");
        if (driver.findElements(marketingPopup).size() > 0 && driver.findElements(marketingPopup).get(0).isDisplayed()) {
            int heightBrowser = driver.manage().window().getSize().getHeight();
            if (heightBrowser < 1920) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("button#close-popup")));

            } else {
                driver.findElement(By.cssSelector("button#close-popup")).click();
            }
            sleepInSecond(3);

        }
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


