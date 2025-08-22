package org.example;

import Utilities.screnshoots;
import com.sun.management.OperatingSystemMXBean;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;
import java.time.Duration;
import java.util.List;

public class StoreMerchant{

    public WebDriver driver = null;

    @BeforeClass
    public void Start() {
        ChromeOptions co = new ChromeOptions();
        co.addArguments("--remote-allow-origins=*");
        co.addArguments("--start-maximized");
        driver = new ChromeDriver(co);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Explicit wait with 10 seconds timeout
        co.addArguments("--start-maximized");
    }

    @Test(priority = 1)
    public void LoginPortal() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Explicit wait with 10 seconds timeout
        long start = System.currentTimeMillis();

        driver.get("https://staging.noknokgroceries.com/#/portal/login");

        long finish = System.currentTimeMillis();
        long totalTime = finish - start;
        System.out.println(">>>>  Total Time for portal process - " + totalTime / 1000 + " Seconds");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"username\"]"))).sendKeys("zarrak_afridi");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"password\"]"))).sendKeys("zarrak@12345678");
        System.out.println(">>>>  Put email and password: credentials");

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"login-btn\"]"))).click();
        System.out.println(">>>>  Click on login");

        long finish2 = System.currentTimeMillis();
        long totalTime2 = finish2 - start;
        System.out.println(">>>>  ******************Total Time for login process - " + totalTime2 / 1000 + " Seconds*********************");
        screnshoots.takeScreenshot(driver);
        // Memory details
        com.sun.management.OperatingSystemMXBean osBean = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);
        long totalPhysicalMemorySize = osBean.getTotalPhysicalMemorySize();
        long freeMemory = osBean.getFreePhysicalMemorySize();
        long usedMemory = totalPhysicalMemorySize - freeMemory;

        System.out.println(">>>>  Total RAM OF SYSTEM: " + totalPhysicalMemorySize / (1024 * 1024 * 1024) + " GB");
        Reporter.log("<p>>>>>  Total RAM OF SYSTEM: " + totalPhysicalMemorySize / (1024 * 1024 * 1024) + " GB</p>");
        System.out.println(">>>>  Free RAM OF SYSTEM: " + freeMemory / (1024 * 1024 * 1024) + " GB");
        Reporter.log("<p>>>>>  Free RAM OF SYSTEM: " + freeMemory / (1024 * 1024 * 1024) + " GB</p>");
        System.out.println(">>>>  Used RAM OF SYSTEM: " + usedMemory / (1024 * 1024 * 1024) + " GB");
        Reporter.log("<p>>>>>  Used RAM OF SYSTEM: " + usedMemory / (1024 * 1024 * 1024) + " GB</p>");
        screnshoots.takeScreenshot(driver);
        // Chrome memory usage
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
        screnshoots.takeScreenshot(driver);
        long totalMemoryUsageInMB = totalMemoryUsageInKB / 1024;
        System.out.println(">>>>  Total Chrome Memory Usage when we open New NOKNOK portal: " + totalMemoryUsageInMB + " MB");
        Reporter.log("<p>>>>>  Total Chrome Memory Usage when we open New NOKNOK portal: " + totalMemoryUsageInMB + " MB</p>");
        screnshoots.takeScreenshot(driver);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[1]/div/ul/li[5]/a/span[2]"))).click();
        System.out.println(">>>>  Open Merchant Tab");
        Reporter.log("<p>>>>>  Open Merchant Tab</p>");
        screnshoots.takeScreenshot(driver);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"searchBar\"]"))).sendKeys("noknok");
        System.out.println(">>>>  Search for merchant");
        Reporter.log("<p>>>>>  Search for merchant</p>");

        screnshoots.takeScreenshot(driver);

    }

    @Test(priority = 2)
    public void StoreSection () throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Explicit wait with 10 seconds timeout
        try {
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-merchants-management/div/div/div/div[2]/div/div/app-merchant-list/p-table/div/div/table/tbody/tr/td[1]/div/div/span"))).click();
            System.out.println(">>>>  Open Merhcant tab");
            Reporter.log("<p>>>>>  Open Merhcant tab</p>");
            screnshoots.takeScreenshot(driver);
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"nav-tab\"]/li[4]/a"))).click();
            System.out.println(">>>>  Open Store section in merchant");
            Reporter.log("<p>>>>>  Open Store section in merchant</p>");
            screnshoots.takeScreenshot(driver);


        } catch (Exception e) {

            System.out.println("\u001B[31m Error: There is no Store in merchant tab \u001B[0m");
            Reporter.log("<p>>>>> Error: There is no Store in merchant tab</p>");

        }

        String expectedstr1 = "Please select a store!"; // Replace with the actual title
        String actualstr1 = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-merchant-view/div/div/div/div[2]/div/div/div/app-stores/div[2]/div/div[2]/div/h1")
        )).getText();
        Assert.assertEquals(actualstr1, expectedstr1);
        System.out.println("\u001B[32m>>>>>>>  TC: Navigate successfully to the Store Screen *******\u001B[0m");


// scroll the deal and bundles
        // Smooth scroll to the bottom (use the document.documentElement for cross-browser compatibility)
        JavascriptExecutor js = (JavascriptExecutor) driver;
        try {
            // Scroll to the bottom of the page
            js.executeScript("window.scrollTo(0, document.documentElement.scrollHeight);");
            Thread.sleep(3000); // Wait for scroll to finish
            screnshoots.takeScreenshot(driver);
            // Scroll back to the top of the page
            js.executeScript("window.scrollTo(0, 0);");
            Thread.sleep(3000); // Wait for scroll to finish
            screnshoots.takeScreenshot(driver);
        } catch (Exception e) {
            System.out.println("\u001B[31m Error: Failed to scroll the page: " + e.getMessage() + "\u001B[0m");
            Reporter.log("<p>>Failed to scroll the page: " + e.getMessage() + "</p>");
            screnshoots.takeScreenshot(driver);
        }

        // Optionally, use a retry mechanism to attempt scrolling again if the page does not scroll as expected.
        int retries = 2;
        for (int i = 0; i < retries; i++) {
            js.executeScript("window.scrollTo(0, document.documentElement.scrollHeight);");
            Thread.sleep(1000);
            screnshoots.takeScreenshot(driver);
            js.executeScript("window.scrollTo(0, 0);");
            Thread.sleep(1000);
            screnshoots.takeScreenshot(driver);
            System.out.println(">>>> Screen Scrolled to bottom , and bottom to top");
            Reporter.log("<p>>>>> Screen Scrolled to bottom , and bottom to top</p>");
            screnshoots.takeScreenshot(driver);
        }


        String expectedbt = "Add Store"; // Replace with the actual title
        String actualbt = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-merchant-view/div/div/div/div[2]/div/div/div/app-stores/div[1]/div/ul[2]/li/button")
        )).getText();
        Assert.assertEquals(actualbt, expectedbt);
        System.out.println("\u001B[32m>>>>>>>  TC: Yes There is a Add Store Button \u001B[0m");
        Reporter.log("<p>>>>>>  TC: Yes There is a Add Store Button</p>");


        try {
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-merchant-view/div/div/div/div[2]/div/div/div/app-stores/div[2]/div/div[1]/div/div/li[1]/div/div"))).click();
            System.out.println(">>>>  We have 3 dots menu for deletion and deactivation of store");
            Reporter.log("<p>>>>>  We have 3 dots menu for deletion and deactivation of store</p>");
            screnshoots.takeScreenshot(driver);
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-merchant-view/div/div/div/div[2]/div/div/div/app-stores/div[2]/div/div[1]/div/div/li[1]/div/ul/li[1]/a"))).click();
            System.out.println(">>>>  Try to delete the Store from the list ! ");
            Reporter.log("<p>>>>>  Try to delete the Store from the list !</p>");
            screnshoots.takeScreenshot(driver);

            String expectdl = "Delete this store?"; // Replace with the actual title
            String actualdl = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//*[@id=\"swal2-title\"]")
            )).getText();
            Assert.assertEquals(actualdl, expectdl);
            System.out.println("\u001B[32m>>>>>>>  TC: Yes The pop op is opened for deletion of store \u001B[0m");
            Reporter.log("<p>>>>>>  TC: TC: Yes The pop op is opened for deletion of store </p>");


            String actualtext = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//*[@id=\"swal2-content\"]/h6")
            )).getText();
            System.out.println("\u001B[32m>>>>>>>  Text with in this popop is : \u001B[0m" + actualtext);
            Thread.sleep(2000);
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div/div[3]/button[2]"))).click();
            System.out.println(">>>>  For now i'll try to not delete the Store");
            Reporter.log("<p>>>>>  For now i'll try to not delete the Store</p>");
            screnshoots.takeScreenshot(driver);
        } catch (Exception e) {
            System.out.println("\u001B[31m>>>> The store 3 dot menu activities not performed !  \u001B[0m");
        }

    }


        @Test(priority = 3)
        public void StoreEditing () throws InterruptedException {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Explicit wait with 10 seconds timeout


            // Scroll down smoothly to the bottom
            JavascriptExecutor js = (JavascriptExecutor) driver;
            try {
                js.executeScript("window.scrollTo(0, document.documentElement.scrollHeight);");
                Thread.sleep(3000); // Allow time for scrolling
                screnshoots.takeScreenshot(driver);

                System.out.println(">>>> Scroll down for the store");
                Reporter.log("<p>>>>> Scroll down for the store </p>");
                // Wait until the store element is clickable and click it
                WebElement storeElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-merchant-view/div/div/div/div[2]/div/div/div/app-stores/div[2]/div/div[1]/div/div/li[45]/span")));
                storeElement.click();

                System.out.println(">>>> Open store for store editing ");
                Reporter.log("<p>>>>> Open store for store editing </p>");
                screnshoots.takeScreenshot(driver);

                // Scroll back to the top after opening the store
                js.executeScript("window.scrollTo(0, 0);");
                Thread.sleep(3000);
                screnshoots.takeScreenshot(driver);

                System.out.println(">>>> Screen Scrolled to bottom, clicked store, and scrolled back to top");
                Reporter.log("<p>>>>> Screen Scrolled to bottom, clicked store, and scrolled back to top</p>");
            } catch (Exception e) {
                System.out.println("\u001B[31m Error: Failed to scroll or open store: " + e.getMessage() + "\u001B[0m");
            }

            // Wait for the store name input field to be visible
            WebElement storeNameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-merchant-view/div/div/div/div[2]/div/div/div/app-stores/div[2]/div/div[2]/div/div/div/div/app-store-details/div[2]/div/div/div/app-store-info/form/div/div[3]/div/input")
            ));
Thread.sleep(2000);
// Get the text from the input field
            String storeName = storeNameElement.getAttribute("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-merchant-view/div/div/div/div[2]/div/div/div/app-stores/div[2]/div/div[2]/div/div/div/div/app-store-details/div[2]/div/div/div/app-store-info/form/div/div[3]/div/input");

// Print the retrieved store name
            System.out.println(">>>> Selected Store Name is : " + storeName);
            Reporter.log("<p>>>>> Selected Store Name is : " + storeName + "</p>");


            try{
                //SCROLL
                // scroll the deal and bundles
                // Smooth scroll to the bottom (use the document.documentElement for cross-browser compatibility)
                JavascriptExecutor jss = (JavascriptExecutor) driver;
                try {
                    // Scroll to the bottom of the page
                    jss.executeScript("window.scrollTo(0, document.documentElement.scrollHeight);");
                    Thread.sleep(3000); // Wait for scroll to finish
                    screnshoots.takeScreenshot(driver);
                    // Scroll back to the top of the page
                    jss.executeScript("window.scrollTo(0, 0);");
                    Thread.sleep(3000); // Wait for scroll to finish
                    screnshoots.takeScreenshot(driver);
                } catch (Exception e) {
                    System.out.println("\u001B[31m Error: Failed to scroll the page: " + e.getMessage() + "\u001B[0m");
                    Reporter.log("<p>>Failed to scroll the page: " + e.getMessage() + "</p>");
                    screnshoots.takeScreenshot(driver);
                }

            // Optionally, use a retry mechanism to attempt scrolling again if the page does not scroll as expected.
            int retries = 2;
            for (int i = 0; i < retries; i++) {
                    js.executeScript("window.scrollTo(0, document.documentElement.scrollHeight);");
                    Thread.sleep(1000);
                    screnshoots.takeScreenshot(driver);
                    js.executeScript("window.scrollTo(0, 0);");
                    Thread.sleep(1000);
                    screnshoots.takeScreenshot(driver);
                    System.out.println(">>>> Screen Scrolled to bottom , and bottom to top");
                    Reporter.log("<p>>>>> Screen Scrolled to bottom , and bottom to top</p>");
                    screnshoots.takeScreenshot(driver);
            }

// EDIT
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-merchant-view/div/div/div/div[2]/div/div/div/app-stores/div[2]/div/div[2]/div/div/div/div/app-store-details/div[2]/div/div/div/app-store-info/form/div/div[1]/div[2]/button"))).click();
                System.out.println(">>>>  Edit store detail in Merhcant tab");
                Reporter.log("<p>>>>>  Edit store detail in Merhcant tab</p>");
                screnshoots.takeScreenshot(driver);

// ON THE TOGGLE
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-merchant-view/div/div/div/div[2]/div/div/div/app-stores/div[2]/div/div[2]/div/div/div/div/app-store-details/div[2]/div/div/div/app-store-info/div/div[1]/div[1]/div/label"))).click();
                System.out.println(">>>>  ON the toggle for publish on App");
                Reporter.log("<p>>>>>  ON the toggle for publish on App</p>");
                screnshoots.takeScreenshot(driver);
                Thread.sleep(1000);
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div/div[3]/button[1]"))).click();
                System.out.println(">>>>  Select yes for toggle");
                Reporter.log("<p>>>>>  Select yes for toggle</p>");
                screnshoots.takeScreenshot(driver);
                Thread.sleep(2000);
//OFF THE TOGGLE
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-merchant-view/div/div/div/div[2]/div/div/div/app-stores/div[2]/div/div[2]/div/div/div/div/app-store-details/div[2]/div/div/div/app-store-info/div/div[1]/div[1]/div/label"))).click();
                System.out.println(">>>>  ON the toggle for publish on App");
                Reporter.log("<p>>>>>  ON the toggle for publish on App</p>");
                screnshoots.takeScreenshot(driver);
                Thread.sleep(1000);
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div/div[3]/button[1]"))).click();
                System.out.println(">>>>  Select yes for toggle");
                Reporter.log("<p>>>>>  Select yes for toggle</p>");
                screnshoots.takeScreenshot(driver);


                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-merchant-view/div/div/div/div[2]/div/div/div/app-stores/div[2]/div/div[2]/div/div/div/div/app-store-details/div[2]/div/div/div/app-store-info/form/div/div[4]/div/input"))).sendKeys("Helle test");
                System.out.println(">>>>  Edit add the description in feild");
                Reporter.log("<p>>>>>  Edit add the description in feild</p>");
                screnshoots.takeScreenshot(driver);



// 1. Select all, remove existing data, and enter "03082982668"
                WebElement phoneField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-merchant-view/div/div/div/div[2]/div/div/div/app-stores/div[2]/div/div[2]/div/div/div/div/app-store-details/div[2]/div/div/div/app-store-info/form/div/div[12]/div/input")));
                phoneField.clear();
                phoneField.sendKeys("03082982668");

// 2. Select all, remove existing data, and enter "4"
                WebElement inputField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-merchant-view/div/div/div/div[2]/div/div/div/app-stores/div[2]/div/div[2]/div/div/div/div/app-store-details/div[2]/div/div/div/app-store-info/form/div/div[17]/div/div/input")));
                inputField.clear();
                inputField.sendKeys("4");

// 3. Select all, remove existing data, and enter "zarrak@yopmail.com"
                WebElement emailField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-merchant-view/div/div/div/div[2]/div/div/div/app-stores/div[2]/div/div[2]/div/div/div/div/app-store-details/div[2]/div/div/div/app-store-info/form/div/div[16]/div/input")));
                emailField.clear();
                emailField.sendKeys("zarrak@yopmail.com");

// 4. Click to open the popup
                WebElement popupButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-merchant-view/div/div/div/div[2]/div/div/div/app-stores/div[2]/div/div[2]/div/div/div/div/app-store-details/div[2]/div/div/div/app-store-info/form/div/div[24]/div/button")));
                popupButton.click();
                System.out.println(">>>> Opened the popup");

// 5. Get and print schedule data confirmation
                WebElement scheduleTextElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/ngb-modal-window/div/div/app-schedule/div[2]/div/div/div/div/h1")));
                String scheduleText = scheduleTextElement.getText();
                System.out.println(">>>> Yes, we are in edit schedule data: " + scheduleText);
                                     Thread.sleep(2000);///html/body/ngb-modal-window/div/div/app-schedule/div[2]/div/div/div/div/div[10]/button
// 6. Click on save button and print confirmation
                WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/ngb-modal-window/div/div/app-schedule/div[2]/div/div/div/div/div[10]/button")));
                saveButton.click();
                Thread.sleep(2000);
                System.out.println(">>>> Yes, set scheduler successfully!");
// This is comment fpr now we will work on it later
                Thread.sleep(2000);
                WebElement saveButto344343434344434344434343434343n = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/bfgody/ngb-modal-window/div/div/app-schedule/div[2]/div/div/div/div/div[10]/button")));
                saveButton.click();
                System.out.println(">>>> Yes, set scheduler successfully!");

            } catch (Exception e) {
                System.out.println("\u001B[31m>>> Error we cannot edit the store \u001B[0m");
             }
      }

}