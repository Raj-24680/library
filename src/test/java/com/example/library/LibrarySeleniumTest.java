package com.example.library;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class LibrarySeleniumTest {

    @Test
    void testLibraryFlow() throws InterruptedException {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        // open login page
        driver.get("http://localhost:8081/login.html");

        // enter login details
        driver.findElement(By.id("name")).sendKeys("Rahul");
        driver.findElement(By.id("usn")).sendKeys("4NM20CS101");

        // click login button
        driver.findElement(By.tagName("button")).click();

        Thread.sleep(2000);

        // handle login alert popup
        driver.switchTo().alert().accept();

        // open main page manually if redirect not handled
        driver.get("http://localhost:8081/index.html");

        Thread.sleep(2000);

        // click show books button
        driver.findElement(By.xpath("//button[contains(text(),'Show Books')]")).click();

        Thread.sleep(2000);

        // enter book id to issue
        WebElement bookId = driver.findElement(By.id("bookId"));
        bookId.sendKeys("1");

        // click issue button
        driver.findElement(By.xpath("//button[contains(text(),'Issue Book')]")).click();

        Thread.sleep(3000);

        // close browser
        driver.quit();
    }
}