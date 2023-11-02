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

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_12_Checkbox_Radio_II {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {

        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();

    }

    @Test
    public void TC_01_Default_Checkbox_Telerik() {
        driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
        By dualZoneCheckbox = By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input");
        By rearSideCheckbox = By.xpath("//label[text()='Rear side airbags']/preceding-sibling::input");

// Select two checkbox
        checkToElement(dualZoneCheckbox);
        checkToElement(rearSideCheckbox);
// Unselect two checkbox
        uncheckToElement(dualZoneCheckbox);

    }

    @Test
    public void TC_02_Default_Radio_Telarik() {
        driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");
        By radioTwoPetrol = By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input");
        By radioTwoDiesel = By.xpath("//label[text()='2.0 Diesel, 103kW']/preceding-sibling::input");
        checkToElement(radioTwoPetrol);
        Assert.assertFalse(driver.findElement(radioTwoDiesel).isSelected());
        checkToElement(radioTwoDiesel);
        Assert.assertFalse(driver.findElement(radioTwoPetrol).isSelected());

    }

    @Test
    public void TC_03_Select_All_Checkbox() {
        driver.get("https://automationfc.github.io/multiple-fields/");
        List<WebElement> allCheckBox = driver.findElements(By.cssSelector("div.form-input-wide input[type='checkbox']"));
        //Select all checkbox
        for(WebElement checkbox: allCheckBox){
            if (!checkbox.isSelected()){
                checkbox.click();
            }
        }
        // Verify all checkbox is selected
        for(WebElement checkbox: allCheckBox){
            Assert.assertTrue(checkbox.isSelected());
        }
        driver.manage().deleteAllCookies();
        driver.navigate().refresh();
        // Select one checkbox in the list
        allCheckBox = driver.findElements(By.cssSelector("div.form-input-wide input[type='checkbox']"));
        for(WebElement checkbox: allCheckBox){
            if(checkbox.getAttribute("value").equals("Emotional Disorder") && !checkbox.isSelected()){
                checkbox.click();
                sleepInSecond(2);

            }
        }
        for(WebElement checkbox : allCheckBox){
            if (checkbox.getAttribute("value").equals("Emotional Disorde")){
                Assert.assertTrue(checkbox.isSelected());
            }
        }

    }

    @Test
    public void TC_04_Radio_Custom() {
        driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");
        By registerRadio = By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div/input");
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].click();", driver.findElement(registerRadio));
        Assert.assertTrue(driver.findElement(registerRadio).isSelected());

    }
    @Test
    public void TC_05_Custom_Radio_Google_Docs(){
        driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
        By canThoRadio = By.xpath("//div[@aria-label='Cần Thơ']");
        By quangNamCheckbox = By.xpath("//div[@aria-label='Quảng Nam']");
        //Verify radio is not checked
        Assert.assertEquals(driver.findElement(canThoRadio).getAttribute("aria-checked"), "false");
        driver.findElement(canThoRadio).click();
        //Verify radio is checked
        Assert.assertEquals(driver.findElement(canThoRadio).getAttribute("aria-checked"), "true");
        //Verify checkbox is not checked
        Assert.assertEquals(driver.findElement(quangNamCheckbox).getAttribute("aria-checked"), "false");
        driver.findElement(quangNamCheckbox).click();
        Assert.assertEquals(driver.findElement(quangNamCheckbox).getAttribute("aria-checked"), "true");


    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
    public void checkToElement(By byXpath){
        if(!driver.findElement(byXpath).isSelected()){
            driver.findElement(byXpath).click();
            sleepInSecond(3);
        }
        Assert.assertTrue(driver.findElement(byXpath).isSelected());

    }
    public void uncheckToElement(By byXpath){
        if(driver.findElement(byXpath).isSelected()){
            driver.findElement(byXpath).click();
            sleepInSecond(3);
        }
        Assert.assertFalse(driver.findElement(byXpath).isSelected());
    }
    public void sleepInSecond(long timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}


