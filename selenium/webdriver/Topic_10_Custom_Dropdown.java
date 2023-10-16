package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_10_Custom_Dropdown {
    WebDriver driver;
    WebDriverWait explicitWait;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();

    }

    @Test
    public void TC_01_JQuery() {
        driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
        selectItemInDropdown("span#speed-button", "ul#speed-menu div", "Faster");
        sleepInSecond(3);
        selectItemInDropdown("span#files-button", "ul#files-menu div", "Some unknown file");
        sleepInSecond(3);
        selectItemInDropdown("span#number-button", "ul#number-menu div", "18");
        sleepInSecond(3);
        selectItemInDropdown("span#salutation-button", "ul#salutation-menu div", "Dr.");
        sleepInSecond(3);
        Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button>span.ui-selectmenu-text")).getText(), "Faster");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#files-button>span.ui-selectmenu-text")).getText(), "Some unknown file");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#number-button>span.ui-selectmenu-text")).getText(), "18");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#salutation-button>span.ui-selectmenu-text")).getText(), "Dr.");


    }
    @Test
    public void TC_02_ReactJS(){
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
        selectItemInDropdown("div.ui.fluid", "div.item>span", "Matt");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Matt");
        sleepInSecond(3);


    }
    @Test
    public void TC_03_VueJS(){
        driver.get("https://mikerodham.github.io/vue-dropdowns/");
        selectItemInDropdown("li.dropdown-toggle", "ul.dropdown-menu a", "Second Option");
        Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "Second Option");
        sleepInSecond(3);

    }
    @Test
    public void TC_04_Editable_Dropdown(){
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
        selectItemEditableInDropdown("input.search", "div.item span", "Andorra");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Andorra");
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

    public void selectItemInDropdown(String parentCSS, String childCSS, String itemTextExpected) {
        driver.findElement(By.cssSelector(parentCSS)).click(); //span#number-button
        // Wait to display full in HTML, don't care visible or not
        // Wait and find elements
//        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childCSS))); //ul#number-menu div
        List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childCSS)));
        for (WebElement item : allItems) {
            String textItem = item.getText();
//            System.out.println("Text item: " + textItem);
            if (textItem.equals(itemTextExpected)) {
                item.click();
                break;

            }
        }
    }
    public void selectItemEditableInDropdown(String parentCSS, String childCSS, String itemTextExpected) {
        driver.findElement(By.cssSelector(parentCSS)).clear();
        driver.findElement(By.cssSelector(parentCSS)).sendKeys(itemTextExpected); //span#number-button
        // Wait to display full in HTML, don't care visible or not
        // Wait and find elements
//        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childCSS))); //ul#number-menu div
        List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childCSS)));
        for (WebElement item : allItems) {
            String textItem = item.getText();
//            System.out.println("Text item: " + textItem);
            if (textItem.equals(itemTextExpected)) {
                item.click();
                break;

            }
        }
    }
}


