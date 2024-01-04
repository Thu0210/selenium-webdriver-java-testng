package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Topic_26_Explicit_Wait_Exercise {
    WebDriver driver;
    WebDriverWait explicitWait;
    String projectPath = System.getProperty("user.dir");
    String file1 = "aboodi-vesakaran-tzkedm7cL0U-unsplash.jpg";
    String file2 = "anna-sullivan-490WHMTud50-unsplash.jpg";
    String file3 = "benjamin-cheng-aP--hslgsow-unsplash.jpg";
    String file4 = "birgit-heyder-schmidt-ceiKuD0x6aY-unsplash.jpg";
    String file1Path = projectPath + File.separator + "uploadFiles" + File.separator + file1;
    String file2Path = projectPath + File.separator + "uploadFiles" + File.separator + file2;
    String file3Path = projectPath + File.separator + "uploadFiles" + File.separator + file3;
    String file4Path = projectPath + File.separator + "uploadFiles" + File.separator + file4;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10000));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Calendar_Ajax_Loading() {
        driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
        By textLoading = By.cssSelector("span#ctl00_ContentPlaceholder1_Label1");
        Assert.assertEquals(driver.findElement(textLoading).getText(), "No Selected Dates to display.");
        driver.findElement(By.xpath("//a[text()='12']")).click();
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[id*='RadCalendar1']>div.raDiv")));
        Assert.assertEquals(driver.findElement(textLoading).getText(), "Friday, January 12, 2024");

    }

    @Test
    public void TC_02_UploadFile() {
        driver.get("https://gofile.io/?t=uploadFiles");
        Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.spinner-border"))));
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.btn-lg"))).click();
        Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div.spinner-border")))));
        driver.findElement(By.cssSelector("input[type='file']")).sendKeys(file1Path + "\n" + file2Path + "\n" + file3Path + "\n" + file4Path);
        Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div.spinner-border")))));
       explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div.progress-bar"))));
       explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.ajaxLink>button"))).click();
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.spinner-border")));
        Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated
                (By.cssSelector("//span[text()='" + file1 + "']/ancestor::div[contains(@class,'text-md-start')]/following-sibling::div//span[text()='Download']"))).isDisplayed());
        Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated
                (By.cssSelector("//span[text()='" + file2 + "']/ancestor::div[contains(@class,'text-md-start')]/following-sibling::div//span[text()='Download']"))).isDisplayed());
        Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated
                (By.cssSelector("//span[text()='" + file3 + "']/ancestor::div[contains(@class,'text-md-start')]/following-sibling::div//span[text()='Download']"))).isDisplayed());
        Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated
                (By.cssSelector("//span[text()='" + file4 + "']/ancestor::div[contains(@class,'text-md-start')]/following-sibling::div//span[text()='Download']"))).isDisplayed());

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


