package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Topic_18_Window_Tab {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();

    }

    @Test
    public void TC_01_Automation_FC_Tab() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        sleepInSecond(3);
        String FcAutomationPageID = driver.getWindowHandle();
        driver.findElement(By.xpath("//a[text() = 'GOOGLE']")).click();
        sleepInSecond(3);
        switchToWindowByID(FcAutomationPageID);
        System.out.println("Google URL: " + driver.getCurrentUrl());
        System.out.println("Google title:" + driver.getTitle());

        driver.findElement(By.cssSelector("textarea[type='search']")).sendKeys("Automation selenium");
        sleepInSecond(3);
        String googlePageID = driver.getWindowHandle();
        switchToWindowByID(googlePageID);
        System.out.println("Automation FC URL: " + driver.getCurrentUrl());
        System.out.println("Automation FC title:" + driver.getTitle());
        driver.findElement(By.xpath("//a[text() = 'FACEBOOK']")).click();
        sleepInSecond(3);
        switchToWindowByTitle("Facebook – log in or sign up");
        driver.findElement(By.cssSelector("input#email")).sendKeys("lethu@gmail.com");
        sleepInSecond(3);


    }

    @Test
    public void TC_02_Kyna_Tab() {
        driver.get("https://skills.kynaenglish.vn/");
        driver.findElement(By.cssSelector("div.hotline img[alt='facebook']")).click();
        switchToWindowByTitle("Kyna.vn | Ho Chi Minh City | Facebook");
        sleepInSecond(3);
        driver.findElement(By.cssSelector("form#login_popup_cta_form input[name='email']")).sendKeys("lethu@gmail.com");
        sleepInSecond(3);
        switchToWindowByTitle("Kyna.vn - Học online cùng chuyên gia");
        driver.findElement(By.cssSelector("div.hotline img[alt='youtube']")).click();
        sleepInSecond(3);
        switchToWindowByTitle("Kyna.vn - YouTube");
        Assert.assertEquals(driver.findElement(By.cssSelector("div#inner-header-container yt-formatted-string#text")).getText(), "Kyna.vn");

    }

    @Test
    public void TC_03_Techpanda_Window() {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.cssSelector("div#header-nav a")).click();
        driver.findElement(By.xpath("//a[@title='IPhone']/parent::h2/following-sibling::div[@class='actions']//a[@class='link-compare']")).click();
        sleepInSecond(3);
        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(), "The product IPhone has been added to comparison list.");
        driver.findElement(By.xpath("//a[@title='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']//a[@class='link-compare']")).click();
        sleepInSecond(3);
        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(), "The product Samsung Galaxy has been added to comparison list.");
        driver.findElement(By.xpath("//a[@title='Sony Xperia']/parent::h2/following-sibling::div[@class='actions']//a[@class='link-compare']")).click();
        sleepInSecond(3);
        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(), "The product Sony Xperia has been added to comparison list.");
        sleepInSecond(3);
        driver.findElement(By.cssSelector("div.block-content button[type='button']")).click();
        switchToWindowByTitle("Products Comparison List - Magento Commerce");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.page-title>h1")).getText(), "COMPARE PRODUCTS");
        sleepInSecond(3);
        driver.findElement(By.cssSelector("div.buttons-set>button[title='Close Window']")).click();
        switchToWindowByTitle("Mobile");
        Assert.assertEquals(By.cssSelector("div.main-container div.page-title"), "MOBILE");

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

    public void switchToWindowByID(String basePageID) {
        Set<String> allIDs = driver.getWindowHandles();
        for (String ID : allIDs) {
            if (!ID.equals(basePageID)) {
                driver.switchTo().window(ID);
                break;
            }
        }
    }

    public void switchToWindowByTitle(String basePageTitle) {
        Set<String> allIDs = driver.getWindowHandles();
        for (String ID : allIDs) {
            driver.switchTo().window(ID);
            String actualTitle = driver.getTitle();
            if (actualTitle.equals(basePageTitle)) {
                break;
            }

        }

    }
    public void closeWindowWithoutParentWindow(String parentID){
        Set<String> allIDs = driver.getWindowHandles();
        for(String ID : allIDs){
            if(!ID.equals(parentID)){
                driver.switchTo().window(ID);
                driver.close();
            }
        }
        driver.switchTo().window(parentID);
    }
}


