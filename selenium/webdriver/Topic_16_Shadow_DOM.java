package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_16_Shadow_DOM {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        //driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();

    }

    @Test
    public void TC_01_Shadow_DOM_Automation_FC() {
        driver.get("https://automationfc.github.io/shadow-dom/");
        sleepInSecond(3);
        WebElement shadowHostElement = driver.findElement(By.cssSelector("div#shadow_host"));
        SearchContext shadowRootContext = shadowHostElement.getShadowRoot();
        String getTextInShadowDom = shadowRootContext.findElement(By.cssSelector("span#shadow_content")).getText();
        Assert.assertEquals(getTextInShadowDom, "some text");
        sleepInSecond(3);
        List<WebElement> inputElements = shadowRootContext.findElements(By.cssSelector("input"));
        System.out.println(inputElements.size());
        sleepInSecond(3);
        WebElement nestedShadowHost = shadowRootContext.findElement(By.cssSelector("div#nested_shadow_host"));
        SearchContext nestedShadowContext = nestedShadowHost.getShadowRoot();
        Assert.assertEquals(nestedShadowContext.findElement(By.cssSelector("div#nested_shadow_content>div")).getText(), "nested text");


    }

    @Test
    public void TC_02_Shadow_DOM_Shopee() {
        driver.get("https://shopee.vn/");
        sleepInSecond(10);
        WebElement shadowHostElement = driver.findElement(By.cssSelector("shopee-banner-popup-stateful"));
        SearchContext shadowContactElement = shadowHostElement.getShadowRoot();
        if (shadowContactElement.findElements(By.cssSelector("div.home-popup")).size() > 0 && shadowContactElement.findElements(By.cssSelector("div.home-popup")).get(0).isDisplayed()) {
            shadowContactElement.findElement(By.cssSelector("div.shopee-popup__close-btn")).click();
            sleepInSecond(3);

        }
        driver.findElement(By.cssSelector("input.shopee-searchbar-input__input")).sendKeys("iphone 15 Pro Max");
        driver.findElement(By.cssSelector("button.btn-solid-primary")).click();
        sleepInSecond(3);


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


