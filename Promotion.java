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

public class Promotion {
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
        System.out.println(">>>>>>>  TC: Navigate successfully to the login Screen *******");


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
    public void PromotionTab() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(this.driver, Duration.ofSeconds(10));
        System.out.println("Promotion Tab test initialized.");


        String expectedpr = "Promotions"; // Replace with the actual title
        String actualpr = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[1]/div/ul/li[7]/a/span[2]")
        )).getText();
        Assert.assertEquals(actualpr, expectedpr);
        System.out.println(">>>>>>>  TC: Yes! There is Promotion Tab *******");

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[1]/div/ul/li[7]/a/span[2]"))).click();
        System.out.println(">>>>>>>  Open the promotion Tab");
        screnshoots.takeScreenshot(driver);


        String expectedsec = "Promo Codes"; // Replace with the actual title
        String actualsec = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[1]/div/ul/li[7]/ul/li[1]/a/span")
        )).getText();
        Assert.assertEquals(actualsec, expectedsec);
        System.out.println(">>>>>>>  TC: Yes! There is Promo code section in drop down *******");


        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[1]/div/ul/li[7]/ul/li[1]/a"))).click();
        System.out.println(">>>>>>>  Open the promo code section");
        screnshoots.takeScreenshot(driver);
        try {
            // Wait for the element to be clickable and click on it
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-home-page-items/div/div/div/div[1]/div/ul[2]/li[1]/button"))).click();
            System.out.println(">>>>>>> Refresh the screen, list id promo codes");

            // Take a screenshot
            screnshoots.takeScreenshot(driver);
        } catch (TimeoutException e) {
            System.err.println("The refresh button did not become clickable within the expected time.");
            e.printStackTrace();
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
        System.out.println(">>>>>>>  Scrolled to the top of the page");
        screnshoots.takeScreenshot(driver);
    }

    @Test(priority = 3)
    public void PomoList() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(this.driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"searchBar\"]"))).sendKeys("ZRK1122");
        System.out.println(">>>>>>>  Searched ZARRAK Promo code ");
        screnshoots.takeScreenshot(driver);
        Thread.sleep(8000);

// For checking search PROMO CODE
        WebElement promoName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-promotion-management/div/div/div/div[2]/div/div/app-promocode-list/p-table/div/div/table/tbody/tr/td[1]/div/div/span")));
        String promoName1 = promoName.getText();
        System.out.println(">>>>>>> Name of search PROMO CODE is :-> " + promoName);
        screnshoots.takeScreenshot(driver);

// Fot the duration of promo code
        WebElement promodur = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-promotion-management/div/div/div/div[2]/div/div/app-promocode-list/p-table/div/div/table/tbody/tr/td[7]/div/div/span")));
        String promoName2 = promodur.getText();
        System.out.println(">>>>>>> Duration for this PROMO CODE is :-> " + promodur);
        screnshoots.takeScreenshot(driver);

        try {

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-promotion-management/div/div/div/div[2]/div/div/app-promocode-list/p-table/div/div/table/tbody/tr/td[6]/div/a/span"))).click();
            System.out.println(">>>>>>> Show more Merchant in list");
            screnshoots.takeScreenshot(driver);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/ngb-modal-window/div/div/app-add-team/div[1]/button"))).click();
            System.out.println(">>>>>>> Cross the merchant label list");
            screnshoots.takeScreenshot(driver);
        } catch (Exception e) {
            System.out.print(">>>>>>  There is no More merchant there fore we cannot show list here .");
        }


        try {

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-promotion-management/div/div/div/div[2]/div/div/app-promocode-list/p-table/div/div/table/tbody/tr/td[8]"))).click();
            System.out.println(">>>>>>> Click on 3 Dots for viewing the searched promo code");
            screnshoots.takeScreenshot(driver);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-promotion-management/div/div/div/div[2]/div/div/app-promocode-list/p-table/div/div/table/tbody/tr/td[8]/ul/li[1]/a"))).click();
            System.out.println(">>>>>>> Click on View for searched promo code details");
            screnshoots.takeScreenshot(driver);
        } catch (Exception e) {
            {
                System.out.println(">>>>>>>  Here no promo code searched");
            }

        }

    }

    @Test(priority = 4)
    public void ViewPromo() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(this.driver, Duration.ofSeconds(10));

        String actualView = null;
        String expectView = null;
        try {
            // Define the expected title
            expectView = "ZRK1122";

            // Wait for the element to become visible and retrieve its text
            actualView = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[1]/div/ul[1]/li[2]/span/i")
            )).getText();

            // Assert that the actual text matches the expected value
            Assert.assertEquals(actualView, expectView);
            System.out.println(">>>>>>> TC: Navigate successfully to the searched Promocode details *******");
        } catch (TimeoutException e) {
            System.err.println("The element did not become visible within the expected time.");
            e.printStackTrace();
        } catch (NoSuchElementException e) {
            System.err.println("The element was not found on the page.");
            e.printStackTrace();
        } catch (AssertionError e) {
            System.err.println("The actual view did not match the expected view. Expected: " + expectView + ", Actual: " + actualView);
            e.printStackTrace();
        } catch (WebDriverException e) {
            System.err.println("An error occurred while interacting with the WebDriver.");
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("An unexpected error occurred.");
            e.printStackTrace();
        }
        System.out.println("Promotion Code detail Displayed .");


        try {

            WebElement PROMOName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[1]/div/ul[1]/li[2]/span/i")));
            String collPROMO = PROMOName.getText();
            System.out.println(">>>>>>> The openend promo code is: " + collPROMO);
            screnshoots.takeScreenshot(driver);
            Thread.sleep(1500);


            try {
                // Locate the textarea field
                WebElement promoCodeDescriptionField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-edit-promocode/div/div[1]/div/div[2]/div/form/div/div[2]/div/textarea")));

                // Clear the field before entering new text
                promoCodeDescriptionField.clear();
                System.out.println(">>>>>>> Cleared the promo code description field.");

                // Enter new text
                promoCodeDescriptionField.sendKeys("Test description for this promo code:");
                System.out.println(">>>>>>> Entered promo code description.");
            } catch (TimeoutException e) {
                System.err.println("The promo code description field did not become visible in time: " + e.getMessage());
            } catch (NoSuchElementException e) {
                System.err.println("The promo code description field was not found on the page: " + e.getMessage());
            } catch (Exception e) {
                System.err.println("An error occurred while interacting with the promo code description field: " + e.getMessage());
            }


            // Click on 'Update Promo' button after editing
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-edit-promocode/div/div[1]/div/div[2]/div/form/div/div[16]/div/div/div[2]/button"))).click();
            System.out.println(">>>>>>> Click on Update promo after editing");
            screnshoots.takeScreenshot(driver); // Take screenshot after updating promo

            // Desired store checkbox selection
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/ngb-modal-window/div/div/app-add-team/div[2]/div/div/form/div[1]/p-table/div/div/table/tbody/tr[6]/td[1]/div/div"))).click();
            System.out.println(">>>>>>> Selecte check box for desired store");
            screnshoots.takeScreenshot(driver); // Take screenshot after updating promo


            // Click on 'Save' button in the store list
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/ngb-modal-window/div/div/app-add-team/div[2]/div/div/form/div[2]/button"))).click();
            System.out.println(">>>>>>> Click on save in store list");
            screnshoots.takeScreenshot(driver); // Take screenshot after saving in store list

            // Click on 'Save' button for Promo activity editing
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-edit-promocode/div/div[1]/div/div[1]/div/ul[2]/li[3]/button"))).click();
            System.out.println(">>>>>>> Click on save for Promo activity editing");
            screnshoots.takeScreenshot(driver); // Take screenshot after saving promo activity
        } catch (TimeoutException e) {
            System.err.println("An element did not appear or become visible within the expected time.");
            e.printStackTrace();


        } catch (NoSuchElementException e) {
            System.err.println("An element was not found on the page.");
            e.printStackTrace();
        } catch (WebDriverException e) {
            System.err.println("An error occurred while interacting with the WebDriver.");
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("An unexpected error occurred.");
            e.printStackTrace();
        }


    }

    @Test(priority = 5)
    public void CreationPromocode() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(this.driver, Duration.ofSeconds(10));
        try {
            // Wait for the element to be visible and click on it
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-promotion-management/div/div/div/div[1]/div/ul[2]/li[3]/button"))).click();
            System.out.println(">>>>>>> Click on create button for promo creation");

            // Take a screenshot
            screnshoots.takeScreenshot(driver);
        } catch (TimeoutException e) {
            System.err.println("The create button did not appear within the expected time.");
            e.printStackTrace();
        } catch (NoSuchElementException e) {
            System.err.println("The create button was not found on the page.");
            e.printStackTrace();
        }

        String expectedSC = "Add Promo Code"; // Replace with the actual title
        String actualSC = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[1]/div/ul[1]/li[2]/span/i")
        )).getText();
        Assert.assertEquals(actualSC, expectedSC);
        System.out.println(">>>>>>>  TC: Navigate successfully to the Promo Adding screen *******");


        WebElement screenADD = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[1]/div/ul[1]/li[2]/span/i")));
        String screenADD2 = screenADD.getText();
        System.out.println(">>>>>>> We are now successfully navigates to the screen of :-> " + screenADD);
        screnshoots.takeScreenshot(driver);


        long start2 = System.currentTimeMillis();
        try {
            // Promo code creation flow
            WebElement codeField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-edit-promocode/div/div/div/div[2]/div/form/div/div[1]/app-form-control/div/input")));
            codeField.sendKeys("1code");
            System.out.println(">>>>>>> Enter code for promo code: ");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-edit-promocode/div/div/div/div[2]/div/form/div/div[2]/div/textarea"))).sendKeys("Test description for promo code");
            System.out.println(">>>>>>> Enter promo code descriptions: ");
            screnshoots.takeScreenshot(driver);
        } catch (Exception e) {
            {
                System.out.println(">>>>>>>  Feild is not clickable or visible");
            }
        }


        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-edit-promocode/div/div/div/div[2]/div/form/div/div[3]/div/ng-select/div/span"))).click();
        System.out.println(">>>>  Dropdown opened for Promo type percentage or fixed");
        screnshoots.takeScreenshot(driver);


        // Select wallet from dropdown
        try {
            // Wait for the dropdown option to become clickable and then click
            WebElement dropdownOption1 = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//*[@id=\"a03174bb7333-1\"]")
            ));
            dropdownOption1.click();
            System.out.println(">>>> I selected 'Wallet' from the dropdown");

        } catch (TimeoutException e) {
            System.err.println("The dropdown option did not become clickable in time: " + e.getMessage());


            // select the promo procedure
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-edit-promocode/div/div/div/div[2]/div/form/div/div[5]/app-form-control/div/div/div/button"))).click();
            System.out.println(">>>>  Dropdown opened for Promo type percentage or fixed");
            screnshoots.takeScreenshot(driver);


            // Select fixed from dropdown
            try {
                // Wait for the dropdown option to become clickable and then click
                WebElement dropdownOption = wait.until(ExpectedConditions.elementToBeClickable(
                        By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-edit" +
                                "-promocode/div/div/div/div[2]/div/form/div/div[5]/app-form-control/div/div/div/ul/li[2]/a")
                ));
                dropdownOption.click();
                System.out.println(">>>> I selected 'Fixed' from the dropdown");


                // Enter amount in the field
                WebElement amountField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-edit-promocode/div/div/div/div[2]/div/form/div/div[5]/app-form-control/div/div/input")));
                amountField.sendKeys("20000");
                System.out.println(">>>>>>> Enter Amount in field for wallet");
                screnshoots.takeScreenshot(driver);

                // Get and print the entered amount (assuming it's displayed in the field)
                String enteredAmount = amountField.getAttribute("value");
                System.out.println(">>>>>>> Entered Amount in field for wallet is: " + enteredAmount);
                screnshoots.takeScreenshot(driver);

                Thread.sleep(1500);


                // Take a screenshot after selecting the option
                screnshoots.takeScreenshot(driver);
            } catch (TimeoutException e3) {
                System.err.println("The dropdown option 'Fixed' did not become clickable in time: " + e.getMessage());
            } catch (NoSuchElementException e3) {
                System.err.println("The dropdown option 'Fixed' was not found: " + e.getMessage());
            } catch (WebDriverException e3) {
                System.err.println("An error occurred while interacting with the dropdown: " + e.getMessage());
            } catch (Exception e3) {
                System.err.println("An unexpected error occurred: " + e.getMessage());
            }


//            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-ro" +
//                    "ot/app-dashboard/div/div/div/div[2]/div[3]/app-edit-promocode/div/div/div/div[2]/div/form/div/div[6]/div/app-form-control/div/input"))).sendKeys("2025/01/16 12:00 AM");
//            System.out.println(">>>>>>>  Enter Starting time in feild for Promo");
//            screnshoots.takeScreenshot(driver);
//
//
//            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-edit-promocode/div/div/div/div[2]/div/form/div/div[7]/div/app-form-control/div/input"))).sendKeys("2025/11/22 12:00 AM ");
//            System.out.println(">>>>>>>  Enter Ending time in feild for Promo");
//            screnshoots.takeScreenshot(driver);


            // Start date for promo code

            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-edit-promocode/div/div/div/div[2]/div/form/div/div[6]/div/app-form-control/div/input"))).click();
            System.out.println(">>>>  Open calender for Start date");
            screnshoots.takeScreenshot(driver);
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-edit-promocode/div/div/div/div[2]/div/form/div/div[6]/div/app-form-control/div/ngx-daterangepicker-material/div/div[2]/div[1]/table/tbody/tr[4]/td[5]"))).click();
            System.out.println(">>>>  Select date for start ");
            screnshoots.takeScreenshot(driver);
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-edit-promocode/div/div/div/div[2]/div/form/div/div[6]/div/app-form-control/div/ngx-daterangepicker-material/div/div[3]/div/button[2]"))).click();
            System.out.println(">>>>  Click on save start date");
            screnshoots.takeScreenshot(driver);


            // End date for promo code
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-edit-promocode/div/div/div/div[2]/div/form/div/div[7]/div/app-form-control/div/input"))).click();
            System.out.println(">>>>  Open calender for End date");
            screnshoots.takeScreenshot(driver);
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-edit-promocode/div/div/div/div[2]/div/form/div/div[7]/div/app-form-control/div/ngx-daterangepicker-material/div/div[2]/div[1]/table/thead/tr[1]/th[3]"))).click();
            System.out.println(">>>>  Select next month for end ");
            screnshoots.takeScreenshot(driver);
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-edit-promocode/div/div/div/div[2]/div/form/div/div[7]/div/app-form-control/div/ngx-daterangepicker-material/div/div[2]/div[1]/table/thead/tr[1]/th[3]"))).click();
            System.out.println(">>>>  Select next to next month for end ");
            screnshoots.takeScreenshot(driver);
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-edit-promocode/div/div/div/div[2]/div/form/div/div[7]/div/app-form-control/div/ngx-daterangepicker-material/div/div[2]/div[1]/table/tbody/tr[5]/td[6]"))).click();
            System.out.println(">>>>  Select date for end date");
            screnshoots.takeScreenshot(driver);
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-edit-promocode/div/div/div/div[2]/div/form/div/div[7]/div/app-form-control/div/ngx-daterangepicker-material/div/div[3]/div/button[2]"))).click();
            System.out.println(">>>>  Click on Save for end date");
            screnshoots.takeScreenshot(driver);


            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/app-" +
                    "dashboard/div/div/div/div[2]/div[3]/app-edit-promocode/div/div/div/div[2]/div/form/div/div[10]/div/input"))).sendKeys("1");
            System.out.println(">>>>>>>  Enter number of usage for Promo");
            screnshoots.takeScreenshot(driver);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-edit-promocode/div/div/div/div[2]/div/form/div/div[11]/div/input"))).sendKeys("1");
            System.out.println(">>>>>>>  Enter number of usage per user");
            screnshoots.takeScreenshot(driver);

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-edit-promocode/div/div/div/div[2]/div/form/div/div[12]/div/label[2]/div/label"))).click();
            System.out.println(">>>>>>>  On the Min Basket Value checkbox");
            screnshoots.takeScreenshot(driver);

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-edit-promocode/div/div/div/div[2]/div/form/div/div[12]/div/input"))).sendKeys("1500");
            System.out.println(">>>>>>>  Enter Min value of basket for This Promo code eligibility");
            screnshoots.takeScreenshot(driver);



            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-edit-promocode/div/div/div/div[2]/div/form/div/div[13]/div/label[2]/div/label"))).click();
            System.out.println(">>>>>>>  On the Mix Basket Value checkbox");
            screnshoots.takeScreenshot(driver);

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-edit-promocode/div/div/div/div[2]/div/form/div/div[13]/div/input"))).sendKeys("15000");
            System.out.println(">>>>>>>  Enter Mix value of basket for This Promo code eligibility");
            screnshoots.takeScreenshot(driver);

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-edit-promocode/div/div/div/div[2]/div/form/div/div[14]/div/input"))).sendKeys("+923082982668");
            System.out.println(">>>>>>>  Enter Number of specific customer");
            screnshoots.takeScreenshot(driver);


            // Click on 'Update Promo' button after editing
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-edit-promocode/div/div/div/div[2]/div/form/div/div[19]/div/div/div[2]/button"))).click();
            System.out.println(">>>>>>> Click on Update promo after editing");
            screnshoots.takeScreenshot(driver); // Take screenshot after updating promo

            // Desired store checkbox selection
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/ngb-modal-window/div/div/app-add-team/div[2]/div/div/form/div[1]/p-table/div/div/table/tbody/tr[6]/td[1]/div/div/label"))).click();
            System.out.println(">>>>>>> Selecte check box for desired store");
            screnshoots.takeScreenshot(driver); // Take screenshot after updating promo


            // Click on 'Save' button in the store list
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/ngb-modal-window/div/div/app-add-team/div[2]/div/div/form/div[2]/button"))).click();
            System.out.println(">>>>>>> Click on save in store list");
            screnshoots.takeScreenshot(driver); // Take screenshot after saving in store list

            // Click on 'Save' button for Promo activity editing
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-edit-promocode/div/div/div/div[1]/div/ul[2]/li[3]/button"))).click();
            System.out.println(">>>>>>> Click on save for Promo activity Add");
            screnshoots.takeScreenshot(driver); // Take screenshot after saving promo activity
            long finish1 = System.currentTimeMillis();
            long totalTime1 = finish1 - start2;
            System.out.println(">>>>>>>  Total Time for Promo code adding process is => " + totalTime1 / 1000 + " Seconds");


            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[1]/div/ul[1]/li[3]/button"))).click();
            System.out.println(">>>>>>> Click on Back for list of Promo code list");
            screnshoots.takeScreenshot(driver); // Take screenshot after saving promo activity



                // 1. Enter code in the search field
                WebElement searchField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"searchBar\"]")));
                searchField.sendKeys("CODE1");
                System.out.println(">>>>>>> Searched code1 Promo code ");
                screnshoots.takeScreenshot(driver);

                // 2. Wait for search results to load (adjust timeout as needed)
                Thread.sleep(8000); // Replace with a more robust wait mechanism if possible

                // 3. Get the text of the first result (assuming it's the first row in the table)
                WebElement firstResultElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-promotion-management/div/div/div/div[2]/div/div/app-promocode-list/p-table/div/div/table/tbody/tr[1]/td[1]/div/div/span")));
                String firstResultText = firstResultElement.getText();

                // 4. Verify if the searched code matches the first result
                if (firstResultText.equals("CODE1")) {
                    System.out.println(">>>>>>> Searched code matches the first result in the table.");
                } else {
                    System.out.println(">>>>>>> Searched code does not match the first result in the table.");
                }

                screnshoots.takeScreenshot(driver);

            }

        System.out.println("******************************************  PROMO SECTION IS COMPLETED  *******************************************");
    }



}




