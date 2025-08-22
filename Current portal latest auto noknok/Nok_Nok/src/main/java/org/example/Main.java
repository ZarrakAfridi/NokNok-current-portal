package org.example;
import Utilities.screnshoots;
import com.sun.management.OperatingSystemMXBean;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import javax.swing.text.Utilities;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;
import java.time.Duration;
import java.util.concurrent.ExecutorService;

public class Main {

    public WebDriver driver = null;



//    public static final String USERNAME = "alquranclasses_g8AEYe";
//    public static final String AUTOMATE_KEY = "7CssvDRxjzxFNWVoUW9Q";
//    public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub.browserstack.com/wd/hub";

    @BeforeClass
    public void Start() {
        //System.setProperty("webdriver.chrome.driver", "C://Users//Zarrak Afridi//Downloads//chromedriver_win32 (2)//chromedriver.exe");
        // Add the arguments to the options

        // Through this chunk we can work with every version of Chromw Driver

        // driver = new ChromeDriver();
        ChromeOptions co = new ChromeOptions();
        co.addArguments("--remote-allow-origins=*");
        co.addArguments("--start-maximized");
        driver = new ChromeDriver(co);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));// Explicit wait with 10 seconds timeout
        co.addArguments("--start-maximized");
//        System.out.println(">> Maximize the chrome");
    }


    @Test(priority = 1)
    public void LoginemptyInvalid() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));// Explicit wait with 10 seconds timeout
        long start1 = System.currentTimeMillis();

        driver.get("https://staging.noknokgroceries.com/#/portal/login");
        System.out.println(">>>>>>>  Navigating to portal");

        long finish = System.currentTimeMillis();
        long totalTime = finish - start1;

        System.out.println(">>>>>>>  Total Time for portal process => " + totalTime / 1000 + " Seconds");
        //
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"login-btn\"]"))).click();
        System.out.println(">>>>>>>  Click on login without putting credential");
        screnshoots.takeScreenshot(driver);

        // Assert dialog title with explicit wait
        String expectedem = "Username is required."; // Replace with the actual title
        String actualem = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("/html/body/app-root/app-dashboard/ng-component/div/div[3]/div/div/form/div/div[1]/app-form-control/div/div/span")
        )).getText();
        Assert.assertEquals(actualem, expectedem);
        System.out.println(">>>>>>>  TC: Validation error showing after clicking for empty credentisls the message is: "+expectedem);
    }



    @Test(priority = 2)
    public void LoginValid() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));// Explicit wait with 10 seconds timeout
        long start = System.currentTimeMillis();
        String expectedlog = "Login"; // Replace with the actual title
        String actualDialog1 = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[@id=\"autoTrigger\"]")
        )).getText();
        Assert.assertEquals(actualDialog1, expectedlog);
        System.out.println(">>>>>>>  TC: Navigate successfully to the login Screen *******");

try {
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
} catch (Exception e) {
    System.out.println("\u001B[31m Error: >>>>> Login proscess not perform accuratly \u001B[0m");
}
        // Calculate the total Memroty using by chrome while using Portal

        // Get memory details
        com.sun.management.OperatingSystemMXBean osBean = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);
        long totalPhysicalMemorySize = osBean.getTotalPhysicalMemorySize();
        long freeMemory = osBean.getFreePhysicalMemorySize();
        long usedMemory = totalPhysicalMemorySize - freeMemory;

        System.out.println(">>>>>>>  Total RAM OF SYSTEM: " + totalPhysicalMemorySize/ (1024 * 1024 * 1024) + " GB");
        System.out.println(">>>>>>>  Free RAM OF SYSTEM: " + freeMemory/ (1024 * 1024 * 1024) +" GB");
        System.out.println(">>>>>>>  Used RAM OF SYSTEM: " + usedMemory/ (1024 * 1024 * 1024) +" GB");

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

//**********************************************************************************************************************

    @Test(priority = 3)
    public void Order() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Explicit wait with 10 seconds timeout

try {
    // Assert dialog title with explicit wait
    String expectedProfile = "zarrak_afridi"; // Replace with the actual title
    String actualProfile = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[1]/div/ul[2]/li/a/div/span")
    )).getText();
    Assert.assertEquals(actualProfile, expectedProfile);
    System.out.println(">>>>>>>  TC: Navigate successfully to the dashboard screen after login *******");


    // Assert dialog title with explicit wait
    String expectedTitle = "Orders"; // Replace with the actual title
    String actualDialog = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[1]/div/ul[1]/li[2]/span/i")
    )).getText();
    Assert.assertEquals(actualDialog, expectedTitle);
    System.out.println(">>>>>>>  TC: Navigate successfully to the dashboard screen after login *******");

    // Click on the Orders tab
    wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[1]/div/ul/li[4]/a/span[2]")
    )).click();
    System.out.println(">>>>>>>  Open Order tab");
} catch (Exception e) {
    System.out.println("\u001B[31m Error: >>>> We cannot navigated successfully to irder screen ! \u001B[0m");
}
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
            System.out.println("Failed to scroll the page: " + e.getMessage());
            Reporter.log("<p>>Failed to scroll the page: " + e.getMessage()+"</p>");
            screnshoots.takeScreenshot(driver);
        }

        // Optionally, use a retry mechanism to attempt scrolling again if the page does not scroll as expected.
        int retries = 2;
        for (int i = 0; i < retries; i++) {
            js.executeScript("window.scrollTo(0, document.documentElement.scrollHeight);");
            Thread.sleep(1000);
            js.executeScript("window.scrollTo(0, 0);");
            Thread.sleep(1000);
            screnshoots.takeScreenshot(driver);
            System.out.println(">>>> Screen Scrolled to bottom , and bottom to top");
            Reporter.log("<p>>>>> Screen Scrolled to bottom , and bottom to top</p>");
        }
    }


    @Test(priority = 4)
    public void SearchOrder() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Explicit wait with 10 seconds timeout


        try {
            // Click the dropdown to display the options
            WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-incident-management/div/div/div/div[1]/div/ul[1]/li[1]/app-multi-select/div/span/span/span[2]")));
            dropdown.click();
            System.out.println(">>>>>>>  Dropdown clicked to display options");

            // Select "ZRK Store" from the dropdown
            WebElement zrkStoreOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"virtual-scroll-style\"]/div[1]/li[4]/a"))); // Locate Test Store option
            zrkStoreOption.click();
            System.out.println(">>>>>>>  Selected 'Test Store' from the dropdown");
            screnshoots.takeScreenshot(driver);
            // Search for "zarrak" in the search bar
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"searchBar\"]"))).sendKeys("40223887584");
            System.out.println(">>>>>>>  Search for customer Order id");
        } catch (Exception e) {
            System.out.println("\u001B[31m Error: >>>> There is no Store for process \u001B[0m");
        }
        Thread.sleep(6000);
    }


    //************************************************************************************************************************

    @Test(priority = 5)
    public void ProductsTab() throws InterruptedException {


        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Explicit wait with 10 seconds timeout

            // Click the view order detail button
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-incident-management/div/div/div/div[2]/div/div[2]/div/div/app-order-list/p-table/div/div/table/tbody/tr/td[8]/button/span"))).click();
            System.out.println(">>>>>>>  View order detail via View Button");


            String expectedid = "Zarrak Afridi 40223887584"; // Replace with the actual title
            String actualid = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[1]/div/ul[1]/li[2]/span/i")
            )).getText();
            Assert.assertEquals(actualid, expectedid);
            System.out.println(">>>>>>>  TC: Navigate successfully on searched order id ******* " + actualid);
        } catch (Exception e) {

            System.out.println("\u001B[31m Error: >>>> Can't view the order details \u001B[0m");
        }


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
            System.out.println("\u001B[31m Error: >> Failed to scroll the page: " + e.getMessage()+"\u001B[0m");
            Reporter.log("<p>>Failed to scroll the page: " + e.getMessage() + "</p>");
            screnshoots.takeScreenshot(driver);
        }

        // Optionally, use a retry mechanism to attempt scrolling again if the page does not scroll as expected.
        int retries = 2;
        for (int i = 0; i < retries; i++) {
            js.executeScript("window.scrollTo(0, document.documentElement.scrollHeight);");
            Thread.sleep(1000);
            js.executeScript("window.scrollTo(0, 0);");
            Thread.sleep(1000);
            screnshoots.takeScreenshot(driver);
            System.out.println(">>>> Screen Scrolled to bottom , and bottom to top");
            Reporter.log("<p>>>>> Screen Scrolled to bottom , and bottom to top</p>");

        }

    }
    @Test(priority = 6)
    public void ProductsTab2() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Explicit wait with 10 seconds timeout
//        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-marketplace-order-view/div/div/div/div[1]/div/ul[2]/li[5]/button"))).click();
//

    // Assert dialog title with explicit wait
    String expecteddet = "Product Details"; // Replace with the actual title
    String actualdet = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-marketplace-order-view/div/div/div/div[2]/div[2]/div[1]/div/div/div/app-products-details/div[1]/label")
    )).getText();
    Assert.assertEquals(actualdet, expecteddet);
    System.out.println(">>>>>>>  TC: Navigate successfully to the product detail screen *******");


//       wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"sidebar\"]//print-preview-button-strip//div/cr-button[2]"))).click();
//        System.out.println("Print the detail of product");
// edit


// Declare newTotalPrice variable to make it accessible outside the try-catch block
    String newTotalPrice = null;

// Check the price now
    WebElement totalPriceElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-marketplace-order-view/div/div/div/div[2]/div[2]/div[1]/div/div/div/app-products-details/div[2]/div/div/h3")));
    String totalPrice = totalPriceElement.getText();
    System.out.println(">>>>>>> Total Price Now is: " + totalPrice);
    screnshoots.takeScreenshot(driver);

    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-marketplace-order-view/div/div/div/div[2]/div[2]/div[1]/div/div/div/app-products-details/div[1]/button"))).click();
    System.out.println(">>>>>>>  Edit the order products");
    screnshoots.takeScreenshot(driver);

    WebElement quantityInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-edit-product-details/div/div/div/div[2]/div/div/p-table/div/div/table/tbody/tr[1]/td[3]/div/div/input")
    ));

// Select all and clear the input field
    quantityInput.sendKeys(Keys.CONTROL + "a"); // For Windows/Linux, use Keys.COMMAND + "a" for Mac
    quantityInput.sendKeys(Keys.DELETE);

// Enter the new quantity
    quantityInput.sendKeys("2");

    System.out.println(">>>>>>>  Increased quantity of product to 2");

    screnshoots.takeScreenshot(driver);
    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-edit-product-details/div/div/div/div[1]/div/ul[2]/li[2]/button"))).click();
    System.out.println(">>>>>>>  Click on save products");
    screnshoots.takeScreenshot(driver);

    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-edit-product-details/div/div/div/div[1]/div/ul[2]/li[1]/button"))).click();
    System.out.println(">>>>>>>  Add products");
    screnshoots.takeScreenshot(driver);

//Search products
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"searchBar\"]"))).sendKeys("Buiscuits");
    System.out.println(">>>>>>>  Search products for adding");

    screnshoots.takeScreenshot(driver);
//        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/ngb-modal-window/div/div/app-add-products/div/div[2]/div[2]/div/p-table/div/div/table/tbody/tr[1]/td[4]/div/div/button"))).click();
//        System.out.println("Add products button");
//
    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/ngb-modal-window/div/div/app-add-products/div/div[1]/button"))).click();
    System.out.println(">>>>>>>  Cross modal");
    screnshoots.takeScreenshot(driver);
    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-root/" +
            "app-dashboard/div/div/div/div[2]/div[3]/app-edit-product-details/div/div/div/div[1]/div/ul[2]/li[2]/button"))).click();
    System.out.println("Save button clicked for product ");
    screnshoots.takeScreenshot(driver);


        try {
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div/div[3]/button[1]"))).click();
            System.out.println(">>>>>>>  Select YES ! I am sure to save the change ,from modal");
            Thread.sleep(600);
// This is ordered item
            WebElement OrderName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-marketplace-order-view/div/div/div/div[2]/div[2]/div[1]/div/div/div/app-products-details/p-table/div/div/table/tbody/tr/td[1]/div/div/div/div/span[1]")));
            String order = OrderName.getText();
            System.out.println(">>>>>>> Name of ordered Items is : " + order);
            screnshoots.takeScreenshot(driver);
        } catch (Exception e) {
            System.out.println("\u001B[31m Error: >>>> No order is availible for details \u001B[0m");
        }


        try {
            // Select total price from receipt
            WebElement newTotalPriceElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-marketplace-order-view/div/div/div/div[2]/div[2]/div[1]/div/div/div/app-products-details/div[2]/div/div/h3")
            ));
            newTotalPrice = newTotalPriceElement.getText();
            System.out.println(">>>>>>> New Total Price is: " + newTotalPrice);
            screnshoots.takeScreenshot(driver);
        } catch (StaleElementReferenceException e) {
            System.err.println("\u001B[31m Error: Element became stale. Re-locate the element and retry.\u001B[0m");
        } catch (TimeoutException e) {
            System.err.println("\u001B[31m Error: Element not found or action timed out.\u001B[0m");
        } catch (Exception e) {
            System.err.println("\u001B[31m Error: An unexpected error occurred: " + e.getMessage()+"\u001B[0m");
        }

        System.out.println(">>>>>>> Old Total Price was : " + totalPrice);
        System.out.println(">>>>>>> New Total Price is: " + newTotalPrice);

// Compare the two prices
        if (newTotalPrice != null && !totalPrice.equals(newTotalPrice)) {
            System.out.println("Price has changed!");
            System.out.println("Old Price: " + totalPrice);
            System.out.println("New Price: " + newTotalPrice);
        } else if (newTotalPrice != null) {
            System.out.println("Price remains the same: " + totalPrice);
        } else {
            System.err.println("Comparison skipped: Could not retrieve the new price.");
        }


//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-marketplace-order-view/div/div/div/div[2]/div[2]/div[5]/div/div/div/app-address-details/div/div[2]/button"))).click();
//        System.out.println(">>>>>>>  View Map in order detail");
//        screnshoots.takeScreenshot(driver);
//        Thread.sleep(2000);
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/ngb-modal-window/div/div/app-order-map-view/div[1]/a"))).click();
//        System.out.println(">>>>>>>  Cross the map modal");
//        screnshoots.takeScreenshot(driver);
try {
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-marketplace-order-view/div/div/div/div[1]/div/ul[2]/li[3]/button"))).click();
    System.out.println(">>>>>>>  Click on confirm order");
    screnshoots.takeScreenshot(driver);
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div[3]/button[1]"))).click();
    System.out.println(">>>>>>>  Click on Yes in popop for order confirmation");
    screnshoots.takeScreenshot(driver);

    Thread.sleep(1000);
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[1]/div/ul/li[4]/ul/li[1]/a"))).click();
    System.out.println(">>>>>>>  Click on Order list");
    screnshoots.takeScreenshot(driver);
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-edit-product-details/div/div/div/div[2]/div/div/p-table/div/div/table/tbody/tr[1]/td[3]/div/div/input"))).sendKeys("12");
//        screnshoots.takeScreenshot(driver);
} catch (Exception e) {
    System.out.println("\u001B[31m Error: >>>> We cannot cofirm order due to error \u001B[0m");
}

    }

    @Test(priority = 7)
    public void MapSection() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Explicit wait with 10 seconds timeout

        // Map tab in order ***************************
        Thread.sleep(1000);
        // Wait for the map to load
        // wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-orders-map/div/div/div/div[2]/div/div/div/app-orders-map-stacking-view/div/div/ngui-map/div/div/div[3]/div[1]/div[2]")));



        try {
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[1]/div/ul/li[4]/ul/li[2]/a"))).click();
            System.out.println(">>>>>>>  Map tab in order is opened");
            screnshoots.takeScreenshot(driver);
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-orders-map/div/div/div/div[1]/div/ul[1]/li[1]/app-multi-select/div/span/span/span[1]"))).click();
            System.out.println(">>>>>>>  Map drop down opened");
            screnshoots.takeScreenshot(driver);
            // Select "ZRK Store" from the dropdown
            WebElement zrkStoreOption1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"virtual-scroll-style\"]/div[1]/li[4]/a"))); // Locate Test Store option
            zrkStoreOption1.click();
            System.out.println(">>>>>>>  Selected 'Test Store' from the dropdown");
            screnshoots.takeScreenshot(driver);
            Thread.sleep(9000);


            // This is selected store
            WebElement storeName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-orders-map/div/div/div/div[1]/div/ul[1]/li[1]/app-multi-select/div/span/span/span[2]")));
            String storeNamee = storeName.getText();
            System.out.println(">>>>>>> Name of store: " + storeNamee);
            screnshoots.takeScreenshot(driver);
        } catch (Exception e) {
            System.out.println(">>>> No store store selected");
        }
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-orders-map/div/div/div/div[2]/div/div/div/app-orders-map-tracking-view/div/div/ngui-map/div/div/div[3]/div[7]/div/div/div/button[1]"))).click();
        System.out.println(">>>>>>>  Map zoom (+)");
        screnshoots.takeScreenshot(driver);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-orders-map/div/div/div/div[2]/div/div/div/app-orders-map-tracking-view/div/div/ngui-map/div/div/div[3]/div[7]/div/div/div/button[1]"))).click();
        System.out.println(">>>>>>>  Map zoom (+)");
        screnshoots.takeScreenshot(driver);
        // Wait for the map to load
        // Wait for the map to load
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-root/app-dashboard/div/" +
                "div/div/div[2]/div[3]/app-orders-map/div/div/div/div[2]/div/div/div/app-orders-map-tracking-view/div/div/ngui-map/div/div/div[3]/div[7]/div/div/div/button[2]"))).click();
        System.out.println(">>>>>>>  Map zoom (-)");
        screnshoots.takeScreenshot(driver);

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-orders-map/div/div/div/div[2]/div/div/div/app-orders-map-tracking-view/div/div/ngui-map/div/div/div[3]/div[7]/div/div/div/button[2]"))).click();
        System.out.println(">>>>>>>  Map zoom (-)");
        screnshoots.takeScreenshot(driver);

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-root/" +
                "app-dashboard/div/div/div/div[2]/div[3]/app-orders-map/div/div/div/div[2]/div/div/div/app-orders-map-tracking-view/div/div/ngui-map/div/div/div[3]/div[7]/button"))).click();
        System.out.println(">>>>>>>  Maximize the Map screen");
        screnshoots.takeScreenshot(driver);
        Thread.sleep(2000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-orders-map/div/div/div/div[2]/div/div/div/app-orders-map-tracking-view/div/div/ngui-map/div/div/div[3]/div[7]/button"))).click();
        System.out.println(">>>>>>>  Manimize the Map screen");
        screnshoots.takeScreenshot(driver);
        Thread.sleep(2000);

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-orders-map/div/div/div/div[1]/div/ul[2]/li/button"))).click();
        System.out.println(">>>>>>>  Refresh the Map screen");
        screnshoots.takeScreenshot(driver);
        Thread.sleep(2000);

//        // Zoom in using JavaScriptExecutor
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//
//        // Zoom in (assumes map object supports a zoom level adjustment using JavaScript)
//        js.executeScript("document.querySelector('ngui-map').__zoom = 5;"); // Adjust zoom level as needed
//        System.out.println("Zoomed in on the map");
//        Thread.sleep(1000); // Wait 1 second
//
//        // Zoom out
//        js.executeScript("document.querySelector('ngui-map').__zoom = 3;"); // Adjust zoom level as needed
//        System.out.println("Zoomed out on the map");

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
            System.out.println("Failed to scroll the page: " + e.getMessage());
            Reporter.log("<p>>Failed to scroll the page: " + e.getMessage()+"</p>");
            screnshoots.takeScreenshot(driver);
        }

        // Optionally, use a retry mechanism to attempt scrolling again if the page does not scroll as expected.
        int retries = 2;
        for (int i = 0; i < retries; i++) {
            js.executeScript("window.scrollTo(0, document.documentElement.scrollHeight);");
            Thread.sleep(1000);
            js.executeScript("window.scrollTo(0, 0);");
            Thread.sleep(1000);
            screnshoots.takeScreenshot(driver);
            System.out.println(">>>> Screen Scrolled to bottom , and bottom to top");
            Reporter.log("<p>>>>> Screen Scrolled to bottom , and bottom to top</p>");
        }
    }

    @Test(priority = 8)
    public void Logout() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Explicit wait with 10 seconds timeout

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[1]/div/ul[2]/li/a/div/span"))).click();
        System.out.println(">>>>>>>  Click on profile of Admin");
        screnshoots.takeScreenshot(driver);

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[1]/div/ul[2]/li/ul/li/a/span"))).click();
        System.out.println(">>>>>>>  Click on Logout");
        screnshoots.takeScreenshot(driver);
    }


//    @AfterClass
//    public void TearDown() {
//        if (this.driver != null) {
//            this.driver.quit();
//            System.out.println("Browser closed successfully.");
//        }
//    }


}



