package org.example;

import com.sun.management.OperatingSystemMXBean;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;
import java.time.Duration;

public class Setting {


    public WebDriver driver = null;

//    public static final String USERNAME = "alquranclasses_g8AEYe";
//    public static final String AUTOMATE_KEY = "7CssvDRxjzxFNWVoUW9Q";
//    public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub.browserstack.com/wd/hub";

    @BeforeClass
    public void Start() {
        //System.setProperty("webdriver.chrome.driver", "C://Users//Zarrak Afridi//Downloads//chromedriver_win32 (2)//chromedriver.exe");
        // Add the arguments to the options

        // Through this chunk we can work with every version of Chromw Driver
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));// Explicit wait with 10 seconds timeout
        // driver = new ChromeDriver();
        ChromeOptions co = new ChromeOptions();
        co.addArguments("--remote-allow-origins=*");
        co.addArguments("--start-maximized");
        driver = new ChromeDriver(co);
        co.addArguments("--start-maximized");
//        System.out.println(">> Maximize the chrome");
    }


    @Test(priority = 1)
    public void LoginPortal() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));// Explicit wait with 10 seconds timeout
        long start = System.currentTimeMillis();

        driver.get("https://staging.noknokgroceries.com/#/portal/login");

        long finish = System.currentTimeMillis();
        long totalTime = finish - start;

        System.out.println("Total Time for portal process - " + totalTime / 1000 + " Seconds");

        // Replace the following lines with explicit waits
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"username\"]"))).sendKeys("zarrak_afridi");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"password\"]")))
                .sendKeys("zarrak@12345678");

        System.out.println(" Put email and password :  credentials ");

        // Use explicit wait for the click action
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"login-btn\"]"))).click();
        System.out.println(" Click on login");
        long finish2 = System.currentTimeMillis();
        long totalTime2 = finish2 - start;

        System.out.println("******************Total Time for login process - " + totalTime2 / 1000 + " Seconds*********************");


        // Calculate the total Memroty using by chrome while using Portal

        // Get memory details
        com.sun.management.OperatingSystemMXBean osBean = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);
        long totalPhysicalMemorySize = osBean.getTotalPhysicalMemorySize();
        long freeMemory = osBean.getFreePhysicalMemorySize();
        long usedMemory = totalPhysicalMemorySize - freeMemory;

        System.out.println("Total RAM OF SYSTEM: " + totalPhysicalMemorySize);
        System.out.println("Free RAM OF SYSTEM: " + freeMemory);
        System.out.println("Used RAM OF SYSTEM: " + usedMemory);

        // chrome usage

        String chromeProcessName = "chrome";
        String command = "tasklist /FI \"IMAGENAME eq " + chromeProcessName + ".exe\"";
        long totalMemoryUsageInKB = 0;

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

        long totalMemoryUsageInMB = totalMemoryUsageInKB / 1024;
        System.out.println("Total Chrome Memory Usage when we open New NOKNOK portal: " + totalMemoryUsageInMB + " MB");
    }



}
