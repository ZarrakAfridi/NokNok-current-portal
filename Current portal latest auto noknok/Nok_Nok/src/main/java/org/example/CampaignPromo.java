package org.example;

import Utilities.screnshoots;
import com.sun.management.OperatingSystemMXBean;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CampaignPromo {
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
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));// Explicit wait with 10 seconds timeout


        long start1 = System.currentTimeMillis();

        driver.get("https://staging.noknokgroceries.com/#/portal/login");
        System.out.println(">>>>>>>  Navigating to portal");

        long finish = System.currentTimeMillis();
        long totalTime = finish - start1;

        System.out.println(">>>>>>>  Total Time for portal process => " + totalTime / 1000 + " Seconds");

        long start = System.currentTimeMillis();
        String expectedlog = "Login"; // Replace with the actual title
        String actualDialog1 = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[@id=\"autoTrigger\"]")
        )).getText();
        Assert.assertEquals(actualDialog1, expectedlog);
        System.out.println("\u001B[32m>>>>>>>  TC: Navigate successfully to the login Screen ******* \u001B[0m");


        // Replace the following lines with explicit waits
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"username\"]"))).sendKeys("zarrak_afridi");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"password\"]")))
                .sendKeys("zarrak@12345678");

        System.out.println(">>>>>>>  Put email and password :  credentials ");

        // Use explicit wait for the click action
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"login-btn\"]"))).click();
        System.out.println(">>>>>>>  Click on login");
        long finish2 = System.currentTimeMillis();
        long totalTime2 = finish2 - start;

        System.out.println("****************** Total Time for login process => " + totalTime2 / 1000 + " Seconds*********************");


        // Calculate the total Memroty using by chrome while using Portal

        // Get memory details
        com.sun.management.OperatingSystemMXBean osBean = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);
        long totalPhysicalMemorySize = osBean.getTotalPhysicalMemorySize();
        long freeMemory = osBean.getFreePhysicalMemorySize();
        long usedMemory = totalPhysicalMemorySize - freeMemory;

        System.out.println(">>>>>>>  Total RAM OF SYSTEM: " + totalPhysicalMemorySize / (1024 * 1024 * 1024) + " GB");
        System.out.println(">>>>>>>  Free RAM OF SYSTEM: " + freeMemory / (1024 * 1024 * 1024) + " GB");
        System.out.println(">>>>>>>  Used RAM OF SYSTEM: " + usedMemory / (1024 * 1024 * 1024) + " GB");

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
        System.out.println(">>>>>>>  Total Chrome Memory Usage when we open New NOKNOK portal: " + totalMemoryUsageInMB + " MB");


//        // Assert dialog title with explicit wait
//        String expectedTitle1 = "Enter Your Details To Log In To Your Account"; // Replace with the actual title
//        String actualDialog1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div[2]/div/div/div[2]/p[1]"))).getText();
//
//        Assert.assertEquals(actualDialog1, expectedTitle1);
//        System.out.println(">>>> TC: WE CANNOT LOGIN WITH WRONG CREDENTIALS *******");
        screnshoots.takeScreenshot(driver);
    }


    @Test(priority = 2)
    public void CampaignSection() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Explicit wait with 10 seconds timeout

        try {
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[1]/div/ul/li[7]/a/span[3]"))).click();
            System.out.println(">>>> Open the Promotion Dropdown section");
            screnshoots.takeScreenshot(driver);

            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[1]/div/ul/li[7]/ul/li[2]/a/span"))).click();
            System.out.println(">>>> Open the campaign section");
            screnshoots.takeScreenshot(driver);

            String expectedcom = "Campaigns"; // Replace with the actual title
            String actualcom = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[1]/div/ul[1]/li[2]/span/i")
            )).getText();
            Assert.assertEquals(actualcom, expectedcom);
            System.out.println(">>>>>>>  TC: Yes! There is Campaign section in dropdown *******");
        } catch (Exception e) {
            System.out.println(">>>>>>>  TC: There is no Campaign section *******");
        }

        // Smooth scroll to the bottom (use the document.documentElement for cross-browser compatibility)
        JavascriptExecutor js = (JavascriptExecutor) driver;
        try {
            // Scroll to the bottom of the page
            js.executeScript("window.scrollTo(0, document.documentElement.scrollHeight);");
            Thread.sleep(3000); // Wait for scroll to finish

            // Scroll back to the top of the page
            js.executeScript("window.scrollTo(0, 0);");
            Thread.sleep(3000); // Wait for scroll to finish
        } catch (Exception e) {
            System.out.println("Failed to scroll the page: " + e.getMessage());
        }

        // Optionally, use a retry mechanism to attempt scrolling again if the page does not scroll as expected.
        int retries = 3;
        for (int i = 0; i < retries; i++) {
            js.executeScript("window.scrollTo(0, document.documentElement.scrollHeight);");
            Thread.sleep(1000);
            js.executeScript("window.scrollTo(0, 0);");
            Thread.sleep(1000);

            System.out.println(">>>> Screen Scrolled to bottom , and bottom to top");
        }
    }



    @Test(priority = 3)
    public void SearchandActivities() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Explicit wait with 10 seconds timeout


        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-campaign-management/div/div/div/div[2]/div/div/app-campaign-list/p-table/div/div/table/tbody/tr[16]/td[8]"))).click();
        System.out.println(">>>> Menu of 3 dots for deletion and deactivation is open");
        screnshoots.takeScreenshot(driver);

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-campaign-management/div/div/div/div[2]/div/div/app-campaign-list/p-table/div/div/table/tbody/tr[16]/td[8]"))).click();
        System.out.println(">>>> Menu of 3 dots for deletion and deactivation closed");
        screnshoots.takeScreenshot(driver);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-campaign-management/div/div/div/div[1]/div/ul[2]/li[1]/button"))).click();
        System.out.println(">>>> Refresh te screen and list of Campaigns");
        screnshoots.takeScreenshot(driver);

        try{
            WebElement searchName = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='searchBar']")));
            searchName.sendKeys("zrk campaign");
            System.out.println(">>>> Search for your specefic campaign");
            screnshoots.takeScreenshot(driver);
             Thread.sleep(13000);
            driver.findElement(By.xpath("//*[@id=\"searchBar\"]")).sendKeys(Keys.RETURN);
            System.out.println(">>>> Pressed Enter after entering the search text");
            Thread.sleep(4000);


            // Take the text entered in the input field
            String enteredText = searchName.getAttribute("value");
            System.out.println(">>>> Text inside the search bar: " + enteredText);


            // Get the name of the first listed campaign in the results
            //String firstListedCampaignXpath = "/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-campaign-management/div/div/div/div[2]/div/div/app-campaign-list/p-table/div/div/table/tbody/tr/td[1]/div/div/span";
            String firstListedCampaignName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-campaign-management/div/div/div/div[2]/div/div/app-campaign-list/p-table/div/div/table/tbody/tr/td[1]/div/div/span"))).getText();

            // Log the first listed campaign name
            System.out.println(">>>> First listed campaign: " + firstListedCampaignName);
            // Assert that the searched campaign and the first listed campaign are the same
            Assert.assertEquals(firstListedCampaignName, enteredText, "The searched campaign does not match the first listed campaign!");
            System.out.println(">>>> Verified: The searched campaign matches the first listed campaign.");



        } catch (Exception e) {
            System.out.println("\u001B[31m Error: >>>> There is no such campaign ! \"\\u001B[0m");
        }
    }


    @Test(priority = 3)
    public void ViewSearchCampaign() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Explicit wait with 10 seconds timeout
    }

    }