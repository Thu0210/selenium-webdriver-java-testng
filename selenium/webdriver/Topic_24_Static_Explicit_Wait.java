package webdriver;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Topic_24_Static_Explicit_Wait {
    WebDriver driver;
    WebDriverWait explicitWait;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_() {
// Chờ cho 1 alert presence trong HTML/ DOM trước khi thao tác lên
        Alert alert = explicitWait.until(ExpectedConditions.alertIsPresent());
        // Chờ cho 1 element không còn trong DOM
        explicitWait.until(ExpectedConditions.stalenessOf(driver.findElement(By.cssSelector(""))));
        // Chờ cho 1 element có ở trong DOM không, không quan tâm việc hiển thị
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("")));
        // Chờ cho 1 list element có ở trong DOM không, không quan tâm việc hiển thị
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("")));
        // Chờ cho 1 - n element được hiển thị trên UI
        explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(""))));
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("")));
        explicitWait.until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.cssSelector(""))));
        // Chờ cho 1 element được phép click
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("")));
        // Chờ page hiện tại có title mong muốn được dùng trước driver.getTitle();
        explicitWait.until(ExpectedConditions.titleIs(""));
        // Kết hợp nhiều điều kiện
        explicitWait.until(ExpectedConditions.and
                (ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(""))),
                        ExpectedConditions.presenceOfElementLocated(By.cssSelector(""))));
        explicitWait.until(ExpectedConditions.or
                (ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(""))),
                        ExpectedConditions.presenceOfElementLocated(By.cssSelector(""))));
// Chờ cho 1 element có attribute chứa giá trị mong đợi (tương đối)
        explicitWait.until(ExpectedConditions.attributeContains(By.cssSelector(""), "", ""));
// Chờ cho 1 element có attribute có giá trị mong đợi (tuyệt đối)
        explicitWait.until(ExpectedConditions.attributeToBe(By.cssSelector(""), "", ""));
        // Chờ cho 1 element có attribute not null
        explicitWait.until(ExpectedConditions.attributeToBeNotEmpty(driver.findElement(By.cssSelector("")),"" ));
        // Chờ cho 1 element có attribute trong DOM: check bằng cách vào tab console, run $$("...") để lấy ra attribute của element
        explicitWait.until(ExpectedConditions.domAttributeToBe(driver.findElement(By.cssSelector("")),"", "" ));
// Chờ cho 1 element được select thành công checkbox/ radio (default)
        explicitWait.until(ExpectedConditions.elementToBeSelected(By.cssSelector("")));
        // Chờ cho 1 element được select rồi
        explicitWait.until(ExpectedConditions.elementSelectionStateToBe(By.cssSelector(""), true));
        // Chờ cho 1 element chưa được selected
        explicitWait.until(ExpectedConditions.elementSelectionStateToBe(By.cssSelector(""),false) );
        // Chờ cho 1 Frame/ iFrame được available và switch qua
        explicitWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.cssSelector("")));
        // Chờ cho 1 đoạn code JS cần trả về giá trị kiểu String
        explicitWait.until(ExpectedConditions.jsReturnsValue("return argument[0].ValidationMessage;"));





    }

    @Test
    public void TC_02_() {

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


