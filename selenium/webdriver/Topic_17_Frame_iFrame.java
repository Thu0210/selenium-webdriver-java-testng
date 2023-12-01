package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_17_Frame_iFrame {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        //driver = new FirefoxDriver();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Form_Site_Frame() {
        driver.get("https://www.formsite.com/templates/education/campus-safety-survey/");
        driver.findElement(By.cssSelector("div#imageTemplateContainer>img")).click();
        sleepInSecond(5);
//        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.cssSelector("div#formTemplateContainer")));
        WebElement iFrameElement = driver.findElement(By.cssSelector("div#formTemplateContainer>iframe"));
        Assert.assertTrue(iFrameElement.isDisplayed());
        driver.switchTo().frame(iFrameElement);
        new Select(driver.findElement(By.cssSelector("select#RESULT_RadioButton-2"))).selectByVisibleText("Sophomore");
        sleepInSecond(3);
        driver.switchTo().defaultContent();
        driver.findElement(By.cssSelector("nav.header--desktop-floater a.menu-item-login")).click();
        sleepInSecond(3);
        driver.findElement(By.cssSelector("button#login")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("div#message-error")).getText(), "Username and password are both required.");
        sleepInSecond(3);



    }

    @Test
    public void TC_02_Kyna_iFrame() {
        driver.get("https://skills.kynaenglish.vn/");
        driver.switchTo().frame(driver.findElement(By.cssSelector("div.face-content>iframe")));
        Assert.assertEquals(driver.findElement(By.xpath("//a[@title='Kyna.vn']/parent::div/following-sibling::div")).getText(), "169K followers");
        driver.switchTo().defaultContent();
        driver.switchTo().frame("cs_chat_iframe");
        driver.findElement(By.cssSelector("div.border_overlay")).click();
        sleepInSecond(3);
        driver.findElement(By.cssSelector("input.input_name")).sendKeys("Jesse");
        driver.findElement(By.cssSelector("input.input_phone")).sendKeys("0987665667");
        new Select(driver.findElement(By.cssSelector("select#serviceSelect"))).selectByVisibleText("TƯ VẤN TUYỂN SINH");
        driver.findElement(By.cssSelector("textarea.input_textarea")).sendKeys("Dang ky khoa hoc JAVA");
        sleepInSecond(3);
        driver.switchTo().defaultContent();
        driver.findElement(By.cssSelector("input.live-search-bar")).sendKeys("Java");
        driver.findElement(By.cssSelector("button.search-button")).click();
        sleepInSecond(3);
        Assert.assertEquals(driver.findElement(By.cssSelector("div.content h4")).getText(), "Lập trình Java trong 4 tuần");


    }

    @Test
    public void TC_03_HDFCBank_Frame() {
        driver.get("https://netbanking.hdfcbank.com/netbanking/");
        driver.switchTo().frame("login_page");
        driver.findElement(By.cssSelector("input[name='fldLoginUserId']")).sendKeys("Jesse");
        driver.findElement(By.cssSelector("a.login-btn")).click();
        sleepInSecond(5);
        driver.switchTo().defaultContent();
        driver.findElement(By.cssSelector("input#keyboard")).sendKeys("123456");


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


