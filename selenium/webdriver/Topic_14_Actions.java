package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_14_Actions {
    WebDriver driver;
    Actions actions;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        actions = new Actions(driver);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Hover_Tooltip() {
        driver.get("https://automationfc.github.io/jquery-tooltip/");
        WebElement ageTextbox = driver.findElement(By.cssSelector("input#age"));
        actions.moveToElement(ageTextbox).perform();
        sleepInSecond(3);
        Assert.assertEquals(driver.findElement(By.cssSelector("div.ui-tooltip-content")).getText(), "We ask for your age only for statistical purposes.");


    }

    @Test
    public void TC_02_Hover_Menu_Mytra() {
        driver.get("https://www.myntra.com/");
        actions.moveToElement(driver.findElement(By.xpath("//a[text()='Kids' and @class='desktop-main']"))).perform();
        sleepInSecond(2);
        actions.click(driver.findElement(By.xpath("//a[text()='Home & Bath' and @class='desktop-categoryName']"))).perform();
        Assert.assertEquals(driver.findElement(By.xpath("//h1[@class='title-title']")).getText(), "Kids Home Bath");
        sleepInSecond(2);

    }

    @Test
    public void TC_03_Hover_Menu_Fahasha() {
        driver.get("https://www.fahasa.com/");
        actions.moveToElement(driver.findElement(By.xpath("//span[@class='icon_seemore_gray']"))).perform();
        sleepInSecond(3);
        actions.moveToElement(driver.findElement(By.xpath("//a[@title='Sách Trong Nước']"))).perform();
        sleepInSecond(3);
        actions.click(driver.findElement(By.xpath("//div[contains(@class,'fhs_column_left')]//a[text()='Câu Chuyện Cuộc Đời']"))).perform();
        sleepInSecond(5);
        Assert.assertEquals(driver.findElement(By.xpath("//span[@class='m-selected-filter-item']")).getText(), "Câu Chuyện Cuộc Đời");
        sleepInSecond(2);

    }

    @Test
    public void TC_04_Click_And_Hover() {
        driver.get("https://automationfc.github.io/jquery-selectable/");
        List<WebElement> allNumbers = driver.findElements(By.cssSelector("li.ui-state-default"));
        Assert.assertEquals(allNumbers.size(), 20);
        actions.clickAndHold(allNumbers.get(0)).moveToElement(allNumbers.get(14)).release().perform();
        sleepInSecond(3);
        List<String> allNumberTextExpected = new ArrayList<String>();
        allNumberTextExpected.add("1");
        allNumberTextExpected.add("2");
        allNumberTextExpected.add("3");
        allNumberTextExpected.add("5");
        allNumberTextExpected.add("6");
        allNumberTextExpected.add("7");
        allNumberTextExpected.add("9");
        allNumberTextExpected.add("10");
        allNumberTextExpected.add("11");
        allNumberTextExpected.add("13");
        allNumberTextExpected.add("14");
        allNumberTextExpected.add("15");

        List<WebElement> allNumbersSelecteds = driver.findElements(By.cssSelector("li.ui-state-default.ui-selected"));
        Assert.assertEquals(allNumbersSelecteds.size(), 12);
        List<String> allNumberTextActual = new ArrayList<String>();
        for (WebElement element: allNumbersSelecteds){
            allNumberTextActual.add(element.getText());
        }
        Assert.assertEquals(allNumberTextExpected, allNumberTextActual);

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


