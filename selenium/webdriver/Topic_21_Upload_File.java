package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_21_Upload_File {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String file1 = "aboodi-vesakaran-tzkedm7cL0U-unsplash.jpg";
    String file2 = "anna-sullivan-490WHMTud50-unsplash.jpg";
    String file3 = "benjamin-cheng-aP--hslgsow-unsplash.jpg";
    String file4 = "birgit-heyder-schmidt-ceiKuD0x6aY-unsplash.jpg";
    String file1Path = projectPath + File.separator + "uploadFiles" + File.separator + file1;
    String file2Path = projectPath + File.separator + "uploadFiles" + File.separator + file2;
    String file3Path = projectPath + File.separator + "uploadFiles" + File.separator + file3;
    String file4Path = projectPath + File.separator + "uploadFiles" + File.separator + file4;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();

    }

    @Test
    public void TC_01_Upload_One_File() {
        driver.get("https://blueimp.github.io/jQuery-File-Upload/");
        By uploadButton = By.cssSelector("input[name='files[]']");
        driver.findElement(uploadButton).sendKeys(file1Path);
        sleepInSecond(2);
        driver.findElement(uploadButton).sendKeys(file2Path);
        sleepInSecond(2);
        driver.findElement(uploadButton).sendKeys(file3Path);
        sleepInSecond(2);
        driver.findElement(uploadButton).sendKeys(file4Path);
        sleepInSecond(2);
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text() = '"+ file1 + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text() = '"+ file2 + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text() = '"+ file3 + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text() = '"+ file4 + "']")).isDisplayed());
        List<WebElement> listStartButton = driver.findElements(By.cssSelector("td button.btn-primary"));
        for (WebElement btn : listStartButton){
            btn.click();
            sleepInSecond(2);
        }
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='"+ file1 + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='"+ file2 + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='"+ file3 + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='"+ file4 + "']")).isDisplayed());


    }

    @Test
    public void TC_02_Upload_Multiple_Files() {
        driver.get("https://blueimp.github.io/jQuery-File-Upload/");
        By uploadButton = By.cssSelector("input[name='files[]']");
        driver.findElement(uploadButton).sendKeys(file1Path + "\n" + file2Path + "\n" + file3Path + "\n" + file4Path);
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text() = '"+ file1 + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text() = '"+ file2 + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text() = '"+ file3 + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text() = '"+ file4 + "']")).isDisplayed());
        List<WebElement> listStartButton = driver.findElements(By.cssSelector("td button.btn-primary"));
        for (WebElement btn : listStartButton){
            btn.click();
            sleepInSecond(2);
        }
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='"+ file1 + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='"+ file2 + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='"+ file3 + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='"+ file4 + "']")).isDisplayed());


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


