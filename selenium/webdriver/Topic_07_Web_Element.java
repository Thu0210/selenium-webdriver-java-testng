package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_07_Web_Element {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();

    }

    @Test
    public void TC_01_Displayed() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        // Neu mong doi 1 element hien thi thi verify True
        // Neu mong doi 1 element khong hien thi thi verify False
        if (driver.findElement(By.cssSelector("input#mail")).isDisplayed()) {
            driver.findElement(By.cssSelector("input#mail")).sendKeys("Automation Testing");
            System.out.println("Email textbox is displayed");
        } else {
            System.out.println("Email textbox is not displayed");
        }
        if (driver.findElement(By.cssSelector("input#under_18")).isDisplayed()) {
            driver.findElement(By.cssSelector("input#under_18")).click();
            System.out.println("Under 18 Radio is displayed");
        } else {
            System.out.println("Under 18 Radio is not displayed");
        }
        if (driver.findElement(By.cssSelector("textarea#edu")).isDisplayed()) {
            driver.findElement(By.cssSelector("textarea#edu")).sendKeys("Automation Testing");
            System.out.println("Education TextArea is displayed");
        } else {
            System.out.println("Education TextArea is not displayed");
        }
        if (driver.findElement(By.xpath("//h5[text() = 'Name: User5']")).isDisplayed()) {
            System.out.println("Name User 5 is displayed");
        } else {
            System.out.println("Name User 5 is not displayed");
        }

    }

    @Test
    public void TC_02_Enabled() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        if (driver.findElement(By.cssSelector("input#mail")).isEnabled()) {
            System.out.println("Email textbox is enabled");
        } else {
            System.out.println("Email textbox is disabled");
        }
        if (driver.findElement(By.cssSelector("input#disable_password")).isEnabled()) {
            System.out.println("Password Textbox is enabled");
        } else {
            System.out.println("Password Textbox is disabled");
        }
        if (driver.findElement(By.cssSelector("input#check-disbaled")).isEnabled()) {
            System.out.println("Checkbox is enabled");
        } else {
            System.out.println("Checkbox is disabled");
        }


    }

    @Test
    public void TC_03_Selected() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.findElement(By.cssSelector("input#under_18")).click();
        driver.findElement(By.cssSelector("input#java")).click();
        Assert.assertTrue(driver.findElement(By.cssSelector("input#under_18")).isSelected());
        Assert.assertTrue(driver.findElement(By.cssSelector("input#java")).isSelected());
        driver.findElement(By.cssSelector("input#java")).click();
        Assert.assertTrue(driver.findElement(By.cssSelector("input#under_18")).isSelected());
        Assert.assertFalse(driver.findElement(By.cssSelector("input#java")).isSelected());

    }

    @Test
    public void TC_04_MailChimp() {
        driver.get("https://login.mailchimp.com/signup/");
        driver.findElement(By.cssSelector("input#email")).sendKeys("automationfc@gmail.com");
        // Case 1: Number
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("12345");
        sleepInSecond(3);
        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class = '8-char not-completed']")).isDisplayed());
        // Case 2: Lower case
        driver.findElement(By.cssSelector("input#new_password")).clear();
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("auto");
        sleepInSecond(3);
        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class = '8-char not-completed']")).isDisplayed());
        // Case 3: Upper case
        driver.findElement(By.cssSelector("input#new_password")).clear();
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("AUTO");
        sleepInSecond(3);
        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class = '8-char not-completed']")).isDisplayed());
        // Case 4: Special Chars
        driver.findElement(By.cssSelector("input#new_password")).clear();
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("@#!");
        sleepInSecond(3);
        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class = '8-char not-completed']")).isDisplayed());
        // Case 5: Max Lengh
        driver.findElement(By.cssSelector("input#new_password")).clear();
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("12345678");
        sleepInSecond(3);
        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char completed']")).isDisplayed());
        // Case 6: Valid
        driver.findElement(By.cssSelector("input#new_password")).clear();
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("Thu097@!");
        sleepInSecond(3);
        Assert.assertFalse(driver.findElement(By.cssSelector("li.lowercase-char.completed")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("li.uppercase-char.completed")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("li.number-char.completed")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("li.special-char.completed")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("li[class = '8-char completed']")).isDisplayed());
        // Case 7: Empty data
//        driver.findElement(By.cssSelector("input#new_password")).clear();
//        driver.findElement(By.cssSelector("button#create-account-enabled")).click();
//        sleepInSecond(3);
//        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
//        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
//        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
//        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
//        Assert.assertTrue(driver.findElement(By.cssSelector("li[class = '8-char not-completed']")).isDisplayed());

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
