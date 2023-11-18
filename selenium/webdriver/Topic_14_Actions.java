package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chromium.ChromiumDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class Topic_14_Actions {
    WebDriver driver;
    Actions actions;
    JavascriptExecutor javascriptExecutor;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        //driver = new ChromeDriver();
        actions = new Actions(driver);
        javascriptExecutor = (JavascriptExecutor) driver;
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
    @Test
    public void TC_05_Double_Click(){
        driver.get("https://automationfc.github.io/basic-form/index.html");
        WebElement doubleClickElement = driver.findElement(By.xpath("//button[text()='Double click me']"));
        if(driver.toString().contains("firefox")){
            //scrollIntoView = true: keo mep tren cua element tren phia tren cung cua viewport
            // scrollIntoView = false: keo mep duoi cua element phia duoi cung cua viewport
            javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);", doubleClickElement);
            sleepInSecond(3);
        }
            actions.doubleClick(doubleClickElement).perform();
            sleepInSecond(2);

        Assert.assertEquals(driver.findElement(By.xpath("//p[@id='demo']")).getText(), "Hello Automation Guys!");

    }
    @Test
    public void TC_06_Right_Click() {
        driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
        Assert.assertFalse(driver.findElement(By.cssSelector("li.context-menu-icon-paste")).isDisplayed());
        // contextClick = right click
        actions.contextClick(driver.findElement(By.cssSelector("span.context-menu-one"))).perform();
        sleepInSecond(3);
        Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-icon-paste")).isDisplayed());
        actions.moveToElement(driver.findElement(By.cssSelector("li.context-menu-icon-paste"))).perform();
        sleepInSecond(3);
        Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-icon-paste.context-menu-hover.context-menu-visible")).isDisplayed());
        actions.click(driver.findElement(By.cssSelector("li.context-menu-icon-paste"))).perform();
        sleepInSecond(3);
        driver.switchTo().alert().accept();
        sleepInSecond(3);
        Assert.assertFalse(driver.findElement(By.cssSelector("li.context-menu-icon-paste")).isDisplayed());


    }
    @Test
    public void TC_07_Drag_And_Drop_HTML4() {
        driver.get("https://automationfc.github.io/kendo-drag-drop/");
        WebElement sourceCircle = driver.findElement(By.cssSelector("div#draggable"));
        WebElement targetCircle = driver.findElement(By.cssSelector("div#droptarget"));
        actions.dragAndDrop(sourceCircle,targetCircle).pause(3000).perform();
        sleepInSecond(3);
        Assert.assertEquals(targetCircle.getText(), "You did great!");
        Assert.assertEquals(Color.fromString(targetCircle.getCssValue("background-color")).asHex().toLowerCase(), "#03a9f4");


    }
    @Test
    public void TC_08_Drag_And_Drop_HTML5_CSS() throws IOException {
        driver.get("https://automationfc.github.io/drag-drop-html5/");
        WebElement columnA = driver.findElement(By.cssSelector("div#column-a"));
        WebElement columnB = driver.findElement(By.cssSelector("div#column-b"));
        String projectPath = System.getProperty("user.dir");
        String dragAndDropFilePath = projectPath + "/DragAndDrop/Helper.js";
        String jsContentFile = getContentFile(dragAndDropFilePath);
        //Thuc thi doan lenh JS
        javascriptExecutor.executeScript(jsContentFile);
        sleepInSecond(5);


    }
    @Test
    public void TC_09_Drag_And_Drop_HTML5_Xpath() {
        driver.get("https://automationfc.github.io/drag-drop-html5/");

    }
    public String getContentFile(String filePath) throws IOException {
        Charset cs = Charset.forName("UTF-8");
        FileInputStream stream = new FileInputStream(filePath);
        try {
            Reader reader = new BufferedReader(new InputStreamReader(stream, cs));
            StringBuilder builder = new StringBuilder();
            char[] buffer = new char[8192];
            int read;
            while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
                builder.append(buffer, 0, read);
            }
            return builder.toString();
        } finally {
            stream.close();
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


