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

public class Merchant {

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
        System.out.println(">>>>  Free RAM OF SYSTEM: " + freeMemory / (1024 * 1024 * 1024) + " GB");
        System.out.println(">>>>  Used RAM OF SYSTEM: " + usedMemory / (1024 * 1024 * 1024) + " GB");
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

        screnshoots.takeScreenshot(driver);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[1]/div/ul/li[5]/a/span[2]"))).click();
        System.out.println(">>>>  Open Merchant Tab");
        screnshoots.takeScreenshot(driver);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"searchBar\"]"))).sendKeys("noknok");
        System.out.println(">>>>  Search for merchant");


 screnshoots.takeScreenshot(driver);

    }

    @Test(priority = 2)
    public void MerchantTabCreat() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Explicit wait with 10 seconds timeout

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[1]/div/ul/li[5]/a/span[2]"))).click();
        System.out.println("Open Merchant Tab");
        screnshoots.takeScreenshot(driver);

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-merchants-management/div/div/div/div[1]/div/ul[2]/li[3]/button"))).click();
        System.out.println(">>>> Create new Merchant ");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"companyName\"]"))).sendKeys("zarrakMer");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"phoneNumber\"]"))).sendKeys("1212345");
        screnshoots.takeScreenshot(driver);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"supportPhone\"]"))).sendKeys("1142345");
        System.out.println(">>>> Put Company name Contact num and Primary Contact number for Merchant");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"contactName\"]"))).sendKeys("Zarrak");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"email\"]"))).sendKeys("Zarrak@yopmail.com");
        System.out.println(">>>> Contact Name email and email: credentials");
        screnshoots.takeScreenshot(driver);


        //PIN AREA
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/ngb-modal-window/div/div/app-add-merchant/div[2]/div/div/div/div/form/app-form-control[6]/div/div/div/button"))).click();
        System.out.println("-->> Pin Area for Merchant");
        screnshoots.takeScreenshot(driver);

        WebElement Popop= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/ngb-modal-window[2]/div/div/app-pin-area/div[1]/h2")));
        String pop = Popop.getText();
        System.out.println(">>>>>>> Popop opend for : " + pop);
        screnshoots.takeScreenshot(driver);

//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        js.executeScript("document.querySelector('.popup-class').dispatchEvent(new Event('close'));");
        // Enter the location name in the input field
//        WebElement locationInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("")));
//        locationInput.sendKeys("Naqoura, Lebanon");
//        System.out.println("Enter the location name");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"pac-input\"]"))).sendKeys("Naqoura, Lebanon");
        System.out.println(">>>> Enter the location name");
        screnshoots.takeScreenshot(driver);
        Thread.sleep(2000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/ngb-modal-window[2]/div/div/app-pin-area/div[1]/button"))).click();
        System.out.println("-->> Close the location popop");
        screnshoots.takeScreenshot(driver);

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/ngb-modal-window/div/div/app-add-merchant/div[1]/button"))).click();
        System.out.println("-->> Close the Add contact popop");
        screnshoots.takeScreenshot(driver);



//// Wait for the dropdown options to be visible
//        List<WebElement> locationOptions = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//ul[@class='pac-container']//li")));
//
//        boolean locationFound = false;
//
//// Iterate through the dropdown options and select the one matching "Naqoura, Lebanon"
//        for (WebElement option : locationOptions) {
//            String locationText = option.getText();
//            System.out.println("Option found: " + locationText);
//
//            if (locationText.contains("Naqoura, Lebanon")) {
//                option.click();
//                System.out.println("Selected 'Naqoura, Lebanon' from the dropdown");
//                locationFound = true;
//                break;
//            }
//        }
//
//        if (!locationFound) {
//            System.out.println("Location 'Naqoura, Lebanon' not found in the dropdown");
//        }
//
//
//        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/ngb-modal-window[2]/div/div/app-pin-area/div[2]/div/div/div/ngui-map/div/div/div[3]/div[1]/div[2]"))).click();
//        System.out.println("-->> Select area");
//        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/ngb-modal-window[2]/div/div/app-pin-area/div[2]/div/div/div/ngui-map/div/div/div[3]/div[1]/div[2]"))).click();
//        System.out.println("-->> Select area");
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/ngb-modal-window[2]/div/div/app-pin-area/div[2]/div/div/div/div[3]/form/div[1]/div[1]/app-form-control/div/input"))).sendKeys("Naqour");
//
//        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/ngb-modal-window[2]/div/div/app-pin-area/div[1]/button"))).click();
//        System.out.println("Cross the tab ");
//        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/ngb-modal-window/div/div/app-add-merchant/div[1]/button"))).click();
//        System.out.println("Cross add merchant tab ");
//
//
    }


    @Test(priority = 3)
    public void OpenMerchant() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Explicit wait with 10 seconds timeout

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-merchants-management/div/div/div/div[2]/div/div/app-merchant-list/p-table/div/div/table/tbody/tr/td[1]/div/div/span"))).click();
        System.out.println(">>>>  Open Merhcant");


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
    public void EditMerchnt() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Explicit wait with 10 seconds timeout

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-merchant-view/div/div/div/div[2]/div/div/div/app-information/div[1]/div/ul[2]/li/button"))).click();
        System.out.println(">>>>  Edit the merchant information");
        screnshoots.takeScreenshot(driver);
        Thread.sleep(1000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-merchant-view/div/div/div/div[2]/div/div/div/app-information/div[2]/form/div[1]/div/div/div/div[4]/div/textarea"))).sendKeys("Test Notes...................");
        System.out.println(">>>>  Test Notes................... in merchant edit information");
        screnshoots.takeScreenshot(driver);
        Thread.sleep(1000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-merchant-view/div/div/div/div[2]/div/div/div/app-information/div[1]/div/ul[2]/li[2]/button"))).click();
        System.out.println(">>>>  Save the merchant information after edit");
        screnshoots.takeScreenshot(driver);
        // Scroll to the bottom of the page
        scrollToBottom11();

        // Scroll back to the top of the page
        scrollToTop1();
        screnshoots.takeScreenshot(driver);
        Thread.sleep(1000);

    }

    // Method to scroll to the bottom of the page and wait
    public void scrollToBottom11() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Get the current scroll position
        long currentScrollPosition = 0;
        long pageHeight = (long) js.executeScript("return document.body.scrollHeight;");

        while (currentScrollPosition < pageHeight) {
            // Scroll down in increments of 500 pixels
            js.executeScript("window.scrollBy(0, 500);");
            Thread.sleep(500); // Wait to mimic smooth scrolling

            // Update the current scroll position
            currentScrollPosition = (long) js.executeScript("return window.pageYOffset;");
            pageHeight = (long) js.executeScript("return document.body.scrollHeight;");
            Thread.sleep(500);
            screnshoots.takeScreenshot(driver);
        }
        System.out.println(">>>>  Scrolled to the bottom of the page");
    }

    // Method to scroll to the top of the page and wait
    public void scrollToTop1() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Get the current scroll position
        long currentScrollPosition = (long) js.executeScript("return window.pageYOffset;");

        while (currentScrollPosition > 0) {
            // Scroll up in increments of 500 pixels
            js.executeScript("window.scrollBy(0, -500);");
            Thread.sleep(500); // Wait to mimic smooth scrolling

            // Update the current scroll position
            currentScrollPosition = (long) js.executeScript("return window.pageYOffset;");
        }
        System.out.println(">>>>  Scrolled to the top of the page");
        screnshoots.takeScreenshot(driver);
    }


    @Test(priority = 5)
    public void Addcontact() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Explicit wait with 10 seconds timeout

        // Click on "Add Contact" button
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-merchant-view/div/div/div/div[2]/div/div/div/app-information/div[2]/div/div/div/div/div/h4/button"))).click();
        System.out.println(">>>>  Click on add contact button");
        Thread.sleep(1000);

        screnshoots.takeScreenshot(driver);

        // Add contact name
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/ngb-modal-window/div/div/app-add-contact/div[2]/div/div/div/div/form/div[1]/input"))).sendKeys("Zarrak");
        System.out.println(">>>>  Add contact name in field");

        // Add contact number
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/ngb-modal-window/div/div/app-add-contact/div[2]/div/div/div/div/form/div[2]/div/input"))).sendKeys("1132345");
        System.out.println(">>>>  Add contact number in field");

        screnshoots.takeScreenshot(driver);
        Thread.sleep(1000);

        // Add email
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/ngb-modal-window/div/div/app-add-contact/div[2]/div/div/div/div/form/div[3]/input"))).sendKeys("za@yopmail.com");
        System.out.println(">>>>  Add email in field box");

        screnshoots.takeScreenshot(driver);
        Thread.sleep(1000);

        // Click the dropdown to open the options
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/ngb-modal-window/div/div/app-add-contact/div[2]/div/div/div/div/form/div[4]/ng-select/div/span[2]"))).click();
        System.out.println(">>>>  Dropdown opened");

        try {
            // Re-locate the dropdown options each time before interacting with them
            List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//ng-dropdown-panel//div[contains(@class,'ng-option')]")));

            // Iterate through the options to find the desired value
            boolean optionSelected = false;
            for (WebElement option : options) {
                if (option.getText().equals("Zrk store")) { // Replace "Zrk store" with the desired value
                    option.click(); // Click the desired option
                    System.out.println("Option selected: " + option.getText());
                    optionSelected = true;
                    break;
                }
            }

            if (!optionSelected) {
                System.out.println("Option not found!");
            }
        } catch (Exception e) {
            System.out.println("Error selecting option: " + e.getMessage());
        }
        // Click on submit button after filling in the form
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/ngb-modal-window/div/div/app-add-contact/div[2]/div/div/div/div/form/div[5]/button"))).click();
        System.out.println(">>>>  Click on submit contact details");
        screnshoots.takeScreenshot(driver);

        // Click on 3 dots button after filling in the form
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-merchant-view/div/div/div/div[2]/div/div/div/app-information/div[2]/div/div/div/div/div/app-contact-list/p-table/div/div/table/tbody/tr[1]/td[6]"))).click();
        System.out.println(">>>>  Click on 3 Dots menu for customer");
        screnshoots.takeScreenshot(driver);

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-merchant-view/div/div/div/div[2]/div/div/div/app-information/div[2]/div/div/div/div/div/app-contact-list/p-table/div/div/table/tbody/tr[1]/td[6]/ul/li[1]/a"))).click();
        System.out.println(">>>>  Click on edit for customer");
        screnshoots.takeScreenshot(driver);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/ngb-modal-window/div/div/app-add-contact/div[2]/div/div/div/div/form/div[3]/input"))).sendKeys("za1@yopmail.com");
        System.out.println(">>>>  Edit email in field box");
        screnshoots.takeScreenshot(driver);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/ngb-modal-window/div/div/app-add-contact/div[2]/div/div/div/div/form/div[5]/button"))).click();
        System.out.println(">>>>  Click on submit contact Edit");
        screnshoots.takeScreenshot(driver);
    }


    @Test(priority = 6)
               public void ProductSection () throws InterruptedException {
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Explicit wait with 10 seconds timeout
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"nav-tab\"]/li[2]/a"))).click();
        System.out.println(">>>>  Click on Product section in merchant");
        screnshoots.takeScreenshot(driver);
        Thread.sleep(1000);
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-merchant-view/div/div/div/div[2]/div/div/div/app-products/div[1]/div/ul[2]/li[6]/button\n"))).click();
                System.out.println(">>>>  Click on Add Product in section ");
                screnshoots.takeScreenshot(driver);
                Thread.sleep(1000);


//        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("div/div/div/app-products/div[1]/div/ul[2]/li[6]/button\n"))).click();
//        System.out.println("Click on choose image for product ");
//        screnshoots.takeScreenshot(driver);
//        Thread.sleep(1000);
//
//        // Provide the full path of the image file to be uploaded
////        fileInput.sendKeys("Desktop\\43.png");




                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-add-product/div/div/div/div[2]/form/div[2]/div/div/div/div[1]/div/input"))).sendKeys("BBQ Test");
                System.out.println(">>>>  Enter the name of Product ");
                screnshoots.takeScreenshot(driver);
                Thread.sleep(1000);

                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-add-product/div/div/div/div[2]/form/div[2]/div/div/div/div[2]/div/textarea"))).sendKeys("BBQ test product");
                System.out.println(">>>>  Enter the name of DESCRIPTION ");
                screnshoots.takeScreenshot(driver);
                Thread.sleep(1000);

                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-add-product/div/div/div/div[2]/form/div[2]/div/div/div/div[3]/div/input"))).sendKeys("12897");
                System.out.println(">>>>  Enter the name of SKU ");
                screnshoots.takeScreenshot(driver);
                Thread.sleep(1000);

//        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-add-product/div/div/div/div[2]/form/div[2]/div/div/div/div[4]/div/ng-select"))).click();
//        System.out.println("Click on drop down");
//        screnshoots.takeScreenshot(driver);


                // Wait for the dropdown to be clickable
//                WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(
//                        By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-add-product/div/div/div/div[2]/form/div[2]/div/div/div/div[4]/div/ng-select/div/span")
//                ));
//                dropdown.click(); // Open the dropdown
//                System.out.println(">>>>  Dropdown opened successfully.");
//                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"a88f6887dc52-0\"]"))).click();
//                System.out.println(">>>>  Select the brand for product");
//                screnshoots.takeScreenshot(driver);

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-add-product/div/div/div/div[2]/form/div[2]/div/div/div/div[6]/div/div/input"))).sendKeys("10");
        System.out.println(">>>>  Enter the commiion ");
        screnshoots.takeScreenshot(driver);
        Thread.sleep(1000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-add-product/div/div/div/div[2]/form/div[2]/div/div/div/div[7]/div/div/input"))).sendKeys("10");
        System.out.println(">>>>  Enter VAT ");
        screnshoots.takeScreenshot(driver);
        Thread.sleep(1000);

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-add-product/div/div/div/div[2]/div/div/div/h4/button"))).click();
        System.out.println(">>>>  Click on New secton in product");
        screnshoots.takeScreenshot(driver);
        Thread.sleep(1000);


        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-add-product/div/div/div/div[2]/div/div/div[2]/div/div/div/div[1]/div[1]/input"))).sendKeys("Zarrak Section");
        System.out.println(">>>>  Enter Section name ");
        screnshoots.takeScreenshot(driver);

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-add-product/div/div/div/div[2]/form/div[2]/div/div/div/div[7]/div/div/input"))).sendKeys("10");
        System.out.println(">>>>  Enter VAT ");
        screnshoots.takeScreenshot(driver);

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-add-product/div/div/div/div[2]/div/div/div[2]/div/div/div/div[1]/div[3]/input"))).sendKeys("Test section");
        System.out.println(">>>>  Enter description for section ");
        screnshoots.takeScreenshot(driver);
        Thread.sleep(1000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-add-product/div/div/div/div[2]/div/div/div[2]/div/div/div/div[1]/div[4]/div/div[2]/label"))).click();
        System.out.println(">>>>  Selct on Free checkboc");
        screnshoots.takeScreenshot(driver);
        Thread.sleep(1000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-add-product/div/div/div/div[2]/div/div/div[2]/div/div/div/div[2]/button"))).click();
        System.out.println(">>>>  New item addition");
        screnshoots.takeScreenshot(driver);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/ngb-modal-window/div/div/div[2]/div/div/p-table/div/div/table/tbody/tr[2]/td[3]/button"))).click();
        System.out.println(">>>>  select otem from list");
        screnshoots.takeScreenshot(driver);

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/ngb-modal-window/div/div/div[1]/button"))).click();
        System.out.println(">>>>  Close tab");
        screnshoots.takeScreenshot(driver);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/ngb-modal-window/div/div/app-add-contact/div[2]/div/div/div/div/form/div[5]/button"))).click();
        System.out.println(">>>>  Click on Save procuct");
        screnshoots.takeScreenshot(driver);

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[1]/div/ul[1]/li[3]/button"))).click();
        System.out.println(">>>>  Back from product screen");
        screnshoots.takeScreenshot(driver);


    }


}