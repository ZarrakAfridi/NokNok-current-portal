package org.example;

import com.sun.management.OperatingSystemMXBean;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Home_Page{
    public WebDriver driver = null;

    @BeforeClass
    public void Start() {
        ChromeOptions co = new ChromeOptions();
        co.addArguments("--remote-allow-origins=*");
        co.addArguments("--start-maximized");
        this.driver = new ChromeDriver(co);
        System.out.println("Browser started and maximized successfully.");
    }

    @Test(priority = 1)
    public void LoginPortal() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(this.driver, Duration.ofSeconds(10));
        long start = System.currentTimeMillis();
        this.driver.get("https://staging.noknokgroceries.com/#/portal/login");
        long finish = System.currentTimeMillis();
        long totalTime = finish - start;
        System.out.println("Total Time for portal process - " + totalTime / 1000L + " Seconds");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"username\"]")))
                .sendKeys("zarrak_afridi");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"password\"]")))
                .sendKeys("zarrak@12345678");
        System.out.println("Put email and password credentials");

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"login-btn\"]"))).click();
        System.out.println("Clicked on login");

        long finish2 = System.currentTimeMillis();
        long totalTime2 = finish2 - start;
        System.out.println("******************Total Time for login process - " + totalTime2 / 1000L + " Seconds*********************");

        OperatingSystemMXBean osBean = (OperatingSystemMXBean) ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);
        long totalPhysicalMemorySize = osBean.getTotalPhysicalMemorySize();
        long freeMemory = osBean.getFreePhysicalMemorySize();
        long usedMemory = totalPhysicalMemorySize - freeMemory;
        System.out.println("Total RAM OF SYSTEM: " + totalPhysicalMemorySize);
        System.out.println("Free RAM OF SYSTEM: " + freeMemory);
        System.out.println("Used RAM OF SYSTEM: " + usedMemory);

        String chromeProcessName = "chrome";
        String command = "tasklist /FI \"IMAGENAME eq " + chromeProcessName + ".exe\"";
        long totalMemoryUsageInKB = 0L;

        try {
            Process process = Runtime.getRuntime().exec(command);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains(chromeProcessName)) {
                    String[] processInfo = line.trim().split("\\s+");
                    String memoryUsage = processInfo[4].replace(",", "");
                    totalMemoryUsageInKB += Long.parseLong(memoryUsage);
                }
            }

            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        long totalMemoryUsageInMB = totalMemoryUsageInKB / 1024L;
        System.out.println("Total Chrome Memory Usage when we open New NOKNOK portal: " + totalMemoryUsageInMB + " MB");
    }

    @Test(priority = 7)
    public void CollectionSection() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(this.driver, Duration.ofSeconds(10));
        System.out.println("Collection Section test initialized.");
    }

    @AfterClass
    public void TearDown() {
        if (this.driver != null) {
            this.driver.quit();
            System.out.println("Browser closed successfully.");
        }
    }
}
