package com.example.library;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;

import java.time.Duration;

public class LibrarySeleniumTest {

    private void pause(int ms) throws InterruptedException {
        Thread.sleep(ms);
    }

    @Test
    void testLibraryFlow() throws Exception {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            driver.get("http://localhost:8081");
            pause(1500);

            // LOGIN
            driver.findElement(By.id("name")).sendKeys("Rahul");
            pause(500);

            driver.findElement(By.id("usn")).sendKeys("4NM20CS101");
            pause(500);

            driver.findElement(By.tagName("button")).click();
            pause(1500);

            wait.until(ExpectedConditions.alertIsPresent()).accept();
            pause(1000);

            // GO TO DASHBOARD
            driver.get("http://localhost:8081/dashboard.html");
            pause(2000);

            wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//button[contains(text(),'Show Books')]")));

            // CLICK SHOW BOOKS
            driver.findElement(By.xpath("//button[contains(text(),'Show Books')]")).click();
            pause(2000);

            // SHOW AVAILABLE TABLE
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("document.getElementById('availableTable').style.display='table';");
            js.executeScript("document.getElementById('availableTitle').style.display='block';");

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("availableTable")));
            pause(1500);

            // ISSUE BOOK
            WebElement input = driver.findElement(By.id("bookId"));
            String firstBookId = driver.findElement(By.xpath("//tbody/tr[1]/td[1]")).getText();
            input.sendKeys(firstBookId);
            pause(1000);

            driver.findElement(By.xpath("//button[contains(text(),'Issue Book')]")).click();
            pause(2000);

            // 🔥 FORCE SHOW ISSUED TABLE
            js.executeScript("document.getElementById('issuedTableMain').style.display='table';");
            js.executeScript("document.getElementById('issuedTitle').style.display='block';");

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("issuedTableMain")));
            pause(1000);

            // RETURN BOOK
            input.clear();
            pause(500);

            input.sendKeys("1");
            pause(1000);

            driver.findElement(By.xpath("//button[contains(text(),'Return Book')]")).click();
            pause(1000);

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("issuedTableMain")));
            pause(1000);

        } finally {
            driver.quit();
        }
    }
}