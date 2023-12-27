package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_22_23_Wait {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();

    }

    @Test
    public void TC_01_Find_Element() {
        // Case 1: Element được tìm thấy chỉ có 1
        // Steps:
        // 1: Sẽ không chờ hết timeout
        // 2: Tìm thấy sẽ trả về 1 WebElement
        // 3: Qua step tiếp theo
//        System.out.println("Start time" + getCurrentTime());
//        driver.findElement(By.cssSelector("input#email"));
//        System.out.println("End time" + getCurrentTime());
        // Case 2: Element được tìm thấy nhưng nhiều hơn 1
        // Steps:
        // 1: Sẽ không cần chờ hết timeout
        // 2: Lấy ra element đầu tiên dù có n nodes
        // 3: Qua step tiếp theo


        // Case 3: Element không được tìm thấy
        // Steps:
        // 1: Chờ hết timeout
        // 2: Trong thời gian chờ này, cứ mỗi 0.5 seconds sẽ tìm lại 1 lần
        // 3: Nếu tìm lại mà thấy cũng trả về element và sẽ qua step tiếp theo
        // 4: Nếu tìm lại mà không thấy sẽ đánh fail test case và throw exception: No such element.

    }

    @Test
    public void TC_02_Find_Elements() {
        List<WebElement> elementList;
        // Case 1: Element được tìm thấy chỉ có 1
        // Steps:
        // 1: Không cần chờ hết timeout
        // 2: Trả về 1 list element chứa đúng 1 element
        elementList = driver.findElements(By.cssSelector("input#email"));

        // Case 2: Element được tìm thấy chứa nhiều element
        // Steps:
        // 1: Không cần chờ hết timeout
        // 2: Trả về 1 list element chứa nhiều element

        // Case 3: Element không được tìm thấy
        // Steps:
        // 1: Chờ hết timeout
        // 2: Chờ mỗi nửa second sẽ tìm lại một lần (polling)
        // 3: Nếu trong thời gian tìm lại mà thấy element thì cũng sẽ trả về 1 list element
        // 4: Nếu trong thời gian tìm lại mà không thấy element thì trả về list empty (rỗng) và không đánh fail test case
        // 5: Chạy tiếp steps tiếp theo

    }

    @Test
    public void TC_03_Equal_5S() {
        driver.get("https://automationfc.github.io/dynamic-loading/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.findElement(By.cssSelector("div#start button")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish h4")).getText(), "Hello World!");
    }

    @Test
    public void TC_04_Less_than_5S() {
        driver.get("https://automationfc.github.io/dynamic-loading/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.findElement(By.cssSelector("div#start button")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish h4")).getText(), "Hello World!");

    }
    @Test
    public void TC_04_More_than_5S() {
        driver.get("https://automationfc.github.io/dynamic-loading/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(By.cssSelector("div#start button")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish h4")).getText(), "Hello World!");

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
    public String getCurrentTime(){
        Date date = new Date();
        return date.toString();
    }
}


