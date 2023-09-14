package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_04_Xpath {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");

    @BeforeClass
    public void beforeClass() {
        if (osName.contains("Windows")) {
            System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
        } else {
            System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
        }

        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
    }

    @Test
    public void Register_01_Empty_Data() {
        driver.findElement(By.id("txtFirstname")).clear();
        driver.findElement(By.id("txtEmail")).clear();
        driver.findElement(By.id("txtCEmail")).clear();
        driver.findElement(By.id("txtPassword")).clear();
        driver.findElement(By.id("txtCPassword")).clear();
        driver.findElement(By.id("txtPhone")).clear();
        driver.findElement(By.xpath("//div/button[@type='submit' and text() = \"ĐĂNG KÝ\"]")).click();
        Assert.assertEquals(driver.findElement(By.id("txtFirstname-error")).getText(), "Vui lòng nhập họ tên");
        Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(), "Vui lòng nhập email");
        Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Vui lòng nhập lại địa chỉ email");
        Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(), "Vui lòng nhập mật khẩu");
        Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(), "Vui lòng nhập lại mật khẩu");
        Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Vui lòng nhập số điện thoại.");

    }

    @Test
    public void Register_02_Invalid_Email() {
        driver.findElement(By.id("txtFirstname")).sendKeys("Le Thi Thu");
        driver.findElement(By.id("txtEmail")).sendKeys("123123");
        driver.findElement(By.id("txtCEmail")).sendKeys("123123");
        driver.findElement(By.id("txtPassword")).sendKeys("Thu1234@1234");
        driver.findElement(By.id("txtCPassword")).sendKeys("Thu1234@1234");
        driver.findElement(By.id("txtPhone")).sendKeys("0978654353");
        driver.findElement(By.xpath("//div/button[@type='submit' and text() = \"ĐĂNG KÝ\"]")).click();
        Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(), "Vui lòng nhập email hợp lệ");
        Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Email nhập lại không đúng");

    }

    @Test
    public void Register_03_Incorrect_CEmail() {
        driver.findElement(By.id("txtEmail")).clear();
        driver.findElement(By.id("txtEmail")).sendKeys("1234@123");
        driver.findElement(By.id("txtCEmail")).clear();
        driver.findElement(By.id("txtCEmail")).sendKeys("12345@123");
        driver.findElement(By.xpath("//div/button[@type='submit' and text() = \"ĐĂNG KÝ\"]")).click();
        Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Email nhập lại không đúng");

    }

    @Test
    public void Register_04_Incorrect_Password() {
        driver.findElement(By.id("txtCEmail")).clear();
        driver.findElement(By.id("txtCEmail")).sendKeys("1234@123");
        driver.findElement(By.id("txtPassword")).clear();
        driver.findElement(By.id("txtPassword")).sendKeys("123");
        driver.findElement(By.xpath("//div/button[@type='submit' and text() = \"ĐĂNG KÝ\"]")).click();
        Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
        Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(), "Mật khẩu bạn nhập không khớp");
    }

    @Test
    public void Register_05_Incorrect_CPassword() {
        driver.findElement(By.id("txtPassword")).clear();
        driver.findElement(By.id("txtPassword")).sendKeys("Thu1234@1234");
        driver.findElement(By.id("txtCPassword")).clear();
        driver.findElement(By.id("txtCPassword")).sendKeys("Thu1234@12");
        driver.findElement(By.xpath("//div/button[@type='submit' and text() = \"ĐĂNG KÝ\"]")).click();
        Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(), "Mật khẩu bạn nhập không khớp");
    }

    @Test
    public void Register_06_Invalid_Phone() {
        driver.findElement(By.id("txtCPassword")).clear();
        driver.findElement(By.id("txtCPassword")).sendKeys("Thu1234@1234");
        //phone < 10 ky tu
        driver.findElement(By.id("txtPhone")).clear();
        driver.findElement(By.id("txtPhone")).sendKeys("097865435");
        driver.findElement(By.xpath("//div/button[@type='submit' and text() = \"ĐĂNG KÝ\"]")).click();
        Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Số điện thoại phải từ 10-11 số.");
        //phone> 11 ky tu
        driver.findElement(By.id("txtPhone")).clear();
        driver.findElement(By.id("txtPhone")).sendKeys("097865435023");
        driver.findElement(By.xpath("//div/button[@type='submit' and text() = \"ĐĂNG KÝ\"]")).click();
        Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Số điện thoại phải từ 10-11 số.");
        // phone with start number invalid
        driver.findElement(By.id("txtPhone")).clear();
        driver.findElement(By.id("txtPhone")).sendKeys("2234555440");
        driver.findElement(By.xpath("//div/button[@type='submit' and text() = \"ĐĂNG KÝ\"]")).click();
        Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Số điện thoại bắt đầu bằng: 09 - 03 - 012 - 016 - 018 - 019 - 088 - 03 - 05 - 07 - 08");
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}


