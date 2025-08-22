package org.example;

import Utilities.screnshoots;
import com.sun.management.OperatingSystemMXBean;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;
import java.time.Duration;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class BundlePromotion {
    public WebDriver driver = null;

    @BeforeClass
    public void Start() {
        ChromeOptions co = new ChromeOptions();
        co.addArguments("--remote-allow-origins=*");
        co.addArguments("--start-maximized");
        this.driver = new ChromeDriver(co);
        System.out.println("Browser started and maximized successfully.");
        Reporter.log("<p>>>>>>>>  Browser started and maximized successfully.</p>");
    }

    @Test(priority = 1)
    public void LoginPortal() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));// Explicit wait with 10 seconds timeout


        long start1 = System.currentTimeMillis();

        driver.get("https://staging.noknokgroceries.com/#/portal/login");
        System.out.println(">>>>>>>  Navigating to portal");
        Reporter.log("<p>>>>>>>>  Navigating to portal</p>");
        long finish = System.currentTimeMillis();
        long totalTime = finish - start1;

        System.out.println(">>>>>>>  Total Time for portal process => " + totalTime / 1000 + " Seconds");
        Reporter.log("<p>>>>>>>>  Total Time for portal process => " + totalTime / 1000 + " Seconds</p>");
        long start = System.currentTimeMillis();
        String expectedlog = "Login"; // Replace with the actual title
        String actualDialog1 = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[@id=\"autoTrigger\"]")
        )).getText();
        Assert.assertEquals(actualDialog1, expectedlog);
        System.out.println(">>>>>>>  TC: Navigate successfully to the login Screen *******");
        Reporter.log("<p>>>>>>>>>  TC: Navigate successfully to the login Screen *******</p>");

        // Replace the following lines with explicit waits
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"username\"]"))).sendKeys("zarrak_afridi");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"password\"]")))
                .sendKeys("zarrak@12345678");

        System.out.println(">>>>>>>  Put email and password :  credentials ");
        Reporter.log("<p>>>>>>>  Put email and password :  credentials </p>");
        // Use explicit wait for the click action
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"login-btn\"]"))).click();
        System.out.println(">>>>>>>  Click on login");
        Reporter.log("<p>>>>>>>  Click on login</p>");
        long finish2 = System.currentTimeMillis();
        long totalTime2 = finish2 - start;

        System.out.println("****************** Total Time for login process => " + totalTime2 / 1000 + " Seconds*********************");
        Reporter.log("<p>****************** Total Time for login process => " + totalTime2 / 1000 + " Seconds*********************</p>");

        // Calculate the total Memroty using by chrome while using Portal

        // Get memory details
        com.sun.management.OperatingSystemMXBean osBean = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);
        long totalPhysicalMemorySize = osBean.getTotalPhysicalMemorySize();
        long freeMemory = osBean.getFreePhysicalMemorySize();
        long usedMemory = totalPhysicalMemorySize - freeMemory;

        System.out.println(">>>>>>>  Total RAM OF SYSTEM: " + totalPhysicalMemorySize / (1024 * 1024 * 1024) + " GB");
        Reporter.log("<p>>>>>>>>  Total RAM OF SYSTEM: " + totalPhysicalMemorySize / (1024 * 1024 * 1024) + " GB</p>");
        System.out.println(">>>>>>>  Free RAM OF SYSTEM: " + freeMemory / (1024 * 1024 * 1024) + " GB");
        Reporter.log("<p>>>>>>>>  Free RAM OF SYSTEM: " + freeMemory / (1024 * 1024 * 1024) + " GB</p>");
        System.out.println(">>>>>>>  Used RAM OF SYSTEM: " + usedMemory / (1024 * 1024 * 1024) + " GB");
        Reporter.log("<p>>>>>>>>  Used RAM OF SYSTEM: " + usedMemory / (1024 * 1024 * 1024) + " GB</p>");

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
        Reporter.log("<p>>>>>>>>  Total Chrome Memory Usage when we open New NOKNOK portal: " + totalMemoryUsageInMB + " MB</p>");

//        // Assert dialog title with explicit wait
//        String expectedTitle1 = "Enter Your Details To Log In To Your Account"; // Replace with the actual title
//        String actualDialog1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div[2]/div/div/div[2]/p[1]"))).getText();
//
//        Assert.assertEquals(actualDialog1, expectedTitle1);
//        System.out.println(">>>> TC: WE CANNOT LOGIN WITH WRONG CREDENTIALS *******");
        screnshoots.takeScreenshot(driver);
    }

    @Test(priority = 2)
    public void DealBunddle() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Explicit wait with 10 seconds timeout
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[1]/div/ul/li[7]/a/span[2]"))).click();
        System.out.println(">>>>>>>  Open the promotion Tab");
        Reporter.log("<p>>>>>>>>  Open the promotion Tab</p>");
        screnshoots.takeScreenshot(driver);


        try {
            String expectdel = "Bundles & Deals"; // Replace with the actual title
            String actualdel = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[1]/div/ul/li[7]/ul/li[5]/a/span")
            )).getText();
            Assert.assertEquals(actualdel, expectdel);
            System.out.println("\u001B[32m >>>>>>  TC: Yes! There is Deal and Bundles  section in dropdown ******* \u001B[0m");
            Reporter.log("<p>>>>>>>  TC: Yes! There is Deal and Bundles  section in dropdown *******</p>");
        } catch (Exception e) {
            System.out.println("\u001B[31m Error: >>>>>>>  TC: There is no Deal and Bundles section ******* \u001B[0m");
            Reporter.log("<p>>>>>>>>  TC: There is no Deal and Bundles section *******</p>");
        }


        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[1]/div/ul/li[7]/ul/li[5]/a/span"))).click();
        System.out.println(">>>>>>>  Open the promotion Tab");
        Reporter.log("<p>>>>>>>>  Open the promotion Tab</p>");
        screnshoots.takeScreenshot(driver);
        try {
            String expectdel1 = "Bundles"; // Replace with the actual title
            String actualdel1 = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[1]/div/ul[1]/li[2]/span/i")
            )).getText();
            Assert.assertEquals(actualdel1, expectdel1);
            System.out.println("\u001B[32m>>>>>>>  TC: Yes! This is Bundle screen ******* \u001B[0m");
            Reporter.log("<p>>>>>>>  TC: Yes! This is Bundle screen *******</p>");
        } catch (Exception e) {
            System.out.println("\u001B[31m Error: >>>>>>>  TC: This is not a Bundle screen *******\u001B[0m");
            Reporter.log("<p>>>>>>>>  TC: This is not a Bundle screen *******</p>");
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
            System.out.println("\u001B[31m Error: Failed to scroll the page: " + e.getMessage()+ "\u001B[0m");
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

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-bundle-and-deal-view/div/div/div/div[2]/div/div/div/app-bundles-management/div[1]/div/ul[2]/li[1]/button"))).click();
        System.out.println(">>>>>>>  Refresh the list of Bundles");
        Reporter.log("<p>>>>>>>>  Refresh the list of Bundles</p>");
        screnshoots.takeScreenshot(driver);


    }

    @Test(priority = 3)
    public void SaerchAndOther() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Explicit wait with 10 seconds timeout


        try {
            WebElement searchbun = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"searchBar\"]")));
            searchbun.sendKeys("Zrk bundle 2");
            System.out.println(">>>> Search for your specefic Bundle");
            Reporter.log("<p>>>>> Search for your specefic Bundle</p>");
            screnshoots.takeScreenshot(driver);
            Thread.sleep(2000);



// Take the text entered in the input field
            String enteredText = searchbun.getAttribute("value");
            System.out.println("Text inside the search bar: " + enteredText);
            Reporter.log("<p>>Text inside the search bar: " + enteredText+"</p>");

// Campare searched and listed bundle

            //String firstListedCampaignXpath = "/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-campaign-management/div/div/div/div[2]/div/div/app-campaign-list/p-table/div/div/table/tbody/tr/td[1]/div/div/span";
            String firstListedbun = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-bundle-and-deal-view/div/div/div/div[2]/div/div/div/app-bundles-management/div[2]/div/app-deals-and-discounts-list/div/p-table/div/div/table/tbody/tr/td[1]/div/div/span"))).getText();

            // Log the first listed campaign name
            System.out.println(">>>> First listed Bundle: " + firstListedbun);
            Reporter.log("<p>>>>>> First listed Bundle: " + firstListedbun+"</p>");
            // Assert that the searched campaign and the first listed campaign are the same
            Assert.assertEquals(firstListedbun, enteredText, "The searched bundle does not match the first listed bundle!");
            System.out.println(">>>> Verified: The searched bundle matches the first listed bundle.");
            Reporter.log("<p>>> Verified: The searched bundle matches the first listed bundle</p>");

        } catch (Exception e) {
            System.out.println("\u001B[31m Error: There is no such bundle ! \u001B[0m");
            Reporter.log("<p>>>> There is no such bundle !</p>");
        }

        // Delete bundle
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-bundle-and-deal-view/div/div/div/div[2]/div/div/div/app-bundles-management/div[2]/div/app-deals-and-discounts-list/div/p-table/div/div/table/tbody/tr/td[7]"))).click();
        System.out.println(">>>>>>>  Open 3 dots menu fof bundle in list");
        Reporter.log("<p>>>>>>>  Open 3 dots menu fof bundle in list</p>");
        screnshoots.takeScreenshot(driver);
try {
    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-bundle-and-deal-view/div/div/div/div[2]/div/div/div/app-bundles-management/div[2]/div/app-deals-and-discounts-list/div/p-table/div/div/table/tbody/tr/td[7]/ul/li[2]/a"))).click();
    System.out.println(">>>>>>>  Try to delete the bundle from list");
    Reporter.log("<p>>>>>>>  Try to delete the bundle from list</p>");
    screnshoots.takeScreenshot(driver);


    String expectpd = "Are you sure you want to delete this deal?"; // Replace with the actual title
    String actualpd = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//*[@id=\"swal2-title\"]")
    )).getText();
    Assert.assertEquals(actualpd, expectpd);
    System.out.println(">>>>>>>  TC: Delete bundle popup is appeared ! *******");
    Reporter.log("<p>>>>>>>  TC: Delete bundle popup is appeared ! *******</p>");
    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div/div[3]/button[2]"))).click();
    System.out.println(">>>>>>>  For now cancelling the deleting bundle popop");
    Reporter.log("<p>>>>>>>>  For now cancelling the deleting bundle popop</p>");
    screnshoots.takeScreenshot(driver);


} catch (Exception e) {
    {
        System.out.println("\u001B[31m Error: >>>>>>>  Drop down not opened !\u001B[0m");
        Reporter.log("<p>>>>>>>>  Drop down not opened !</p>");
     }
  }

// view bundle



        try {
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-bundle-and-deal-view/div/div/div/div[2]/div/div/div/app-bundles-management/div[2]/div/app-deals-and-discounts-list/div/p-table/div/div/table/tbody/tr/td[7]"))).click();
            System.out.println(">>>>>>>  Open 3 dots menu fof bundle in list");
            Reporter.log("<p>>>>>>>>  Open 3 dots menu fof bundle in list</p>");
            screnshoots.takeScreenshot(driver);

            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-bundle-and-deal-view/div/div/div/div[2]/div/div/div/app-bundles-management/div[2]/div/app-deals-and-discounts-list/div/p-table/div/div/table/tbody/tr/td[7]/ul/li[1]/a"))).click();
            System.out.println(">>>>>>>  View the searched bundle !");
            Reporter.log("<p>>>>>>>>  View the searched bundle !</p>");
            screnshoots.takeScreenshot(driver);


            String expectna = "zrk bundle 2"; // Replace with the actual title
            String actualna = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[1]/div/ul[1]/li[2]/span/i")
            )).getText();
            Assert.assertEquals(actualna, expectna);
            System.out.println("\u001B[32m>>>>>  TC: Navigated successfully to the searched Bundle ! ******\u001B[0m");
            Reporter.log("<p>>>>>>>>  TC: Navigated successfully to the searched Bundle ! *******</p>");


        } catch (Exception e) {
            {
                System.out.println("\u001B[31m Error: >>>>>>>  We have no view option !\u001B[0m");
                Reporter.log("<p>>>>>>>>  We have no view option !</p>");
            }
        }
    }



        @Test(priority = 4)
        public void ViewAndAddBundle() throws InterruptedException {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Explicit wait with 10 seconds timeout

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
                System.out.println("\u001B[31m Error: Failed to scroll the page: " + e.getMessage()+"\u001B[0m");
                Reporter.log("<p>Failed to scroll the page:" + e.getMessage()+"</p>");
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

try {
    WebElement descriptionInput1 = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("/html/body/app-root/app-dashboard" +
                    "/div/div/div/div[2]/div[3]/app-add-deal-and-discount/div/div/div/form/div/div[1]/div/div/div/div[2]/input")));

// Clear existing text before entering new input
    descriptionInput1.clear();  // Attempt to clear
    descriptionInput1.sendKeys(Keys.CONTROL + "a");  // Select all text
    descriptionInput1.sendKeys(Keys.BACK_SPACE);  // Delete selected text

// Now enter the new text
    descriptionInput1.sendKeys("Test description");
    System.out.println(">>>> Clear the feild for new description");
    Reporter.log("<p>>>>> Clear the feild for new description</p>");
    System.out.println(">>>> Added description for Bundle updates");
    Reporter.log("<p>>>>> Added description for Bundle updates</p>");
    screnshoots.takeScreenshot(driver);


    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-add-deal-and-discount/div/div/div/form/div/div[1]/div/div/div/div[5]/div/div[1]/span"))).click();
    System.out.println(">>>>  Option store selectoin for bundle !");
    Reporter.log("<p>>>>>  Option store selectoin for bundle !</p>");
    screnshoots.takeScreenshot(driver);

    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"" +
            "searchBar\"]"))).sendKeys("zrk store");
    System.out.println(">>>>  Insert store in search bar");
    Reporter.log("<p>>>>>  Insert store in search bar</p>");
    screnshoots.takeScreenshot(driver);
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/ngb-modal-window/div/div/app-store-popup/div[3]/button[2]"))).click();
    System.out.println(">>>>  Save the popop for store selection !");
    Reporter.log("<p>>>>>  Save the popop for store selection !</p>");
    screnshoots.takeScreenshot(driver);
} catch (Exception e) {

    System.out.println("\u001B[31m Error: >>>> The Popop for store selection is not working ! \u001B[0m");
    Reporter.log("<p>>>> The Popop for store selection is not working ! </p>");
}

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-add-deal-and-discount/div/div/div/div/div/ul[2]/li/button"))).click();
            System.out.println(">>>>  Click on upddate for changes !");
            Reporter.log("<p>>>>>  Click on upddate for changes !</p>");
            screnshoots.takeScreenshot(driver);
//            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/ngb-modal-window/div/div/app-store-popup/div[3]/button[2]"))).click();
//            System.out.println(">>>>  Go back to the bundle listed screen !");
//            screnshoots.takeScreenshot(driver);

        }


    @Test(priority = 5)
    public void AddBundle() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Explicit wait with 10 seconds timeout



        String expectbt = "Add Bundle"; // Replace with the actual title
        String actualbt = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-bundle-and-deal-view/div/div/div/div[2]/div/div/div/app-bundles-management/div[1]/div/ul[2]/li[3]/button")
        )).getText();
        Assert.assertEquals(actualbt, expectbt);
        System.out.println(">>>>>>>  TC: Add bunddle button is available ! *******");
        Reporter.log("<p>>>>>>>  TC: Add bunddle button is available ! *******</p>");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-bundle-and-deal-view/div/div/div/div[2]/div/div/div/app-bundles-management/div[1]/div/ul[2]/li[3]/button"))).click();
        System.out.println(">>>>  Click on Add bundle button for bundle creation  !");
        Reporter.log("<p>>>>>  Click on Add bundle button for bundle creation  !</p>");
        screnshoots.takeScreenshot(driver);
 try {

     wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-add-deal-and-" +
             "discount/div/div/div/form/div/div[1]/div/div/div/div[1]/app-form-control/div/input"))).sendKeys("zrk test bundle");
     System.out.println(">>>>  Insert name for bunddle");
     Reporter.log("<p>>>>>  Insert name for bunddle</p>");
     screnshoots.takeScreenshot(driver);


     WebElement descriptionInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
             By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-add-deal" +
                     "-and-discount/div/div/div/form/div/div[1]/div/div/div/div[2]/input")));

// Clear the field before entering new text
     descriptionInput.clear();  // Attempt to clear
     descriptionInput.sendKeys(Keys.CONTROL + "a");  // Select all text
     descriptionInput.sendKeys(Keys.BACK_SPACE);  // Delete selected text

// Now enter the new text
     descriptionInput.sendKeys("Test bundle descriptions");

     System.out.println(">>>> Inserted description for the new bundle!");
     Reporter.log("<p>>>>> Inserted description for the new bundle!</p>");
     screnshoots.takeScreenshot(driver);

     // Adding start and end date for bundle

     //for start date
     wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/app-" +
             "dashboard/div/div/div/div[2]/div[3]/app-add-deal-and-discount/div/div/div/form/div/div[1]/div/div/" +
             "div/div[3]/div/app-form-control/div/input"))).click();
     System.out.println(">>>>  Open calacnder for the start date  !");
     Reporter.log("<p>>>>  Open calacnder for the start date  !</p>");
     screnshoots.takeScreenshot(driver);

     wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-add-deal-and-discount/div/div/div/form/" +
             "div/div[1]/div/div/div/div[3]/div/app-form-control/div/ngx-daterangepicker-material/div/div[3]/div/button[2]"))).click();
     System.out.println(">>>>  Click on OK for current date  !");
     Reporter.log("<p>>>>>  Click on OK for current date  !</p>");
     screnshoots.takeScreenshot(driver);



     // Adding start and end date for bundle

     //for end date
     wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-add-deal-and-" +
             "discount/div/div/div/form/div/div[1]/div/div/div/div[4]/div/app-form-control/div/input"))).click();
     System.out.println(">>>>  Open calacnder for the end date  !");
     Reporter.log("<p>>>>>  Open calacnder for the end date  !</p>");
     screnshoots.takeScreenshot(driver);

     wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-add-deal-and-discount/div/div/div/form/div/" +
             "div[1]/div/div/div/div[4]/div/app-form-control/div/ngx-daterangepicker-material" +
             "/div/div[2]/div[1]/table/thead/tr[1]/th[3]"))).click();
     System.out.println(">>>>  Click on shift for next month !");
     Reporter.log("<p>>>>>  Click on shift for next month !</p>");
     screnshoots.takeScreenshot(driver);


     wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-add-deal-and-discount/div/div/div/form/div/div[1]/div/div/div/div[4]/div/app-form-control/div/ngx-daterangepicker-material/div/div[2]/div[1]/table/tbody/tr[5]/td[5]"))).click();
     System.out.println(">>>>  Click required date !");
     Reporter.log("<p>>>>>  Click required date !</p>");
     screnshoots.takeScreenshot(driver);
     wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-add-deal-and-discount/div/" +
             "div/div/form/div/div[1]/div/div/div/div[4]/div/app-form-control/div/ngx-daterangepicker-material" +
             "/div/div[3]/div/button[2]"))).click();
     System.out.println(">>>>  Click on OK for End date  !");
     Reporter.log("<p>>>>>  Click on OK for End date  !</p>");
     screnshoots.takeScreenshot(driver);


     // select store for the bundle

     wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-add-deal-and-discount/div/div/div/form/div/div[1]/div/div/div/div[5]/div/div[1]/span"))).click();
     System.out.println(">>>>  Option store selectoin for bundle !");
     Reporter.log("<p>>>>>  Option store selectoin for bundle !</p>");
     screnshoots.takeScreenshot(driver);


     // Select merchant tab
     wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
             "/html/body/ngb-modal-window/div/div/app-store-popup/div[2]/div/div/div/div/div[1]/ng-select"))).click();

     System.out.println(">>>> select merechant dropdown for selection");
     Reporter.log("<p> >>>> select merechant dropdown for selection</p>");
     screnshoots.takeScreenshot(driver);
//
//     wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
//             "//a[contains(text(),'noknok MAR')]"))).click();
//
//     System.out.println(">>>> select merchant");
//     screnshoots.takeScreenshot(driver);


// Search store
     WebElement searchstr1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"searchBar\"]")));
     searchstr1.clear();
     searchstr1.sendKeys("Zrk store");

     System.out.println(">>>> Insert store in search bar");
     Reporter.log("<p>>>>> Insert store in search bar</p>");
     screnshoots.takeScreenshot(driver);


// Capture entered text for comparison
     String enteredTexts = searchstr1.getAttribute("value");
     System.out.println("Text inside the search bar: " + enteredTexts);
     Reporter.log("<p>>>>> Text inside the search bar: \"" + enteredTexts+"</p>");

// Compare searched and listed store
     WebElement firstListedStore = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
             "/html/body/ngb-modal-window/div/div/app-store-popup/div[2]/div/div/div/cdk-virtual-scroll-viewport/div[1]/p-table/div/div/table/tbody/tr/td[1]/div/div/span"
     )));

     String firstListedstr = firstListedStore.getText();
     System.out.println(">>>> First listed store: " + firstListedstr);
     Reporter.log("<p>>>>> First listed store: \" "+firstListedstr+ "</p>");



// Assert store names match
     Assert.assertEquals(firstListedstr, enteredTexts, "The searched store does not match the first listed store!");
     System.out.println(">>>> Comparison Verified: The searched store matches the first listed store.");
     Reporter.log("<p>>>>> Comparison Verified: The searched store matches the first listed store.</p>");



// Click on store selection button
     WebElement selectStoreButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
             "/html/body/ngb-modal-window/div/div/app-store-popup/div[2]/div/div/div/cdk-virtual-scroll-viewport/div[1]/p-table/div/div/table/tbody/tr[2]/td[3]/div/div/button"
     )));
     selectStoreButton.click();
     System.out.println(">>>> Selected store for this new bundle successfully!");
     Reporter.log("<p>>>>> Selected store for this new bundle successfully!</p>");
     screnshoots.takeScreenshot(driver);

// Save the popup
     WebElement savePopupButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
             "/html/body/ngb-modal-window/div/div/app-store-popup/div[3]/button[2]"
     )));
     savePopupButton.click();
     System.out.println(">>>> Saved the popup for store selection!");
     Reporter.log("<p>>>>> Saved the popup for store selection!</p>");
     screnshoots.takeScreenshot(driver);

 } catch (Exception e) {
     System.out.println("\u001B[31m Error: >>>>  I am not able to create Bundle ! \u001B[0m");
     Reporter.log("<p>>>>>  I am not able to create Bundle ! </p>");
  }
}


    @Test(priority = 6)
    public void AplliedOnDiscount() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Explicit wait with 10 seconds timeout

        // Scroll to the bottom of the page
        JavascriptExecutor js = (JavascriptExecutor) driver;
        try {
            js.executeScript("window.scrollTo(0, document.documentElement.scrollHeight);");
            Thread.sleep(3000); // Wait for scroll to complete
            screnshoots.takeScreenshot(driver);
            System.out.println(">>> Successfully scrolled to the bottom of the page for applied on and discount !.>");
            Reporter.log("<p>>>> Successfully scrolled to the bottom of the page for applied on and discount !.</p>");
        } catch (Exception e) {
            System.out.println("\u001B[31m Error: Failed to scroll to the bottom: " + e.getMessage()+"\u001B[0m");
            Reporter.log("<p>Failed to scroll to the bottom: " + e.getMessage() + "</p>");
            screnshoots.takeScreenshot(driver);
        }

        String expectap = "Applied On:"; // Replace with the actual title
        String actualap = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-add-deal-and-discount/div/div/div/form/div/div[2]/div/div/h5")
        )).getText();
        Assert.assertEquals(actualap, expectap);
        System.out.println("\u001B[32m>>>>>>  TC: We have the applied On and Discount options ! ******\u001B[0m");
        Reporter.log("<p>>>>>>>>  TC: We have the applied On and Discount options ! *******</p>");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-add-deal-and-discount/div/div/div/form/div/div[2]/div/div/div/div[3]/span"))).click();
        System.out.println(">>>>  Open select product Popup !");
        Reporter.log("<p>>>>>  Open select product Popup !</p>");
        screnshoots.takeScreenshot(driver);

        String expectser = "Search Products"; // Replace with the actual title
        String actualser= wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("/html/body/ngb-modal-window/div/div/app-products-popup/div[1]/h2")
        )).getText();
        Assert.assertEquals(actualser, expectser);
        System.out.println("\u001B[32m>>>>>>  TC: We are successfully navigated to the Searech product screen for bundle ! *******\u001B[0m");
        Reporter.log("<p>>>>>>>>  TC: We are successfully navigated to the Searech product screen for bundle ! *******</p>");



try {
// Search store
    WebElement searchpr = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"searchBar\"]")));
    searchpr.clear();
    searchpr.sendKeys("Chair");
    System.out.println(">>>> Insert Product name in search bar");
    Reporter.log("<p>>>>> Insert Product name in search bar</p>");
    screnshoots.takeScreenshot(driver);

Thread.sleep(3000);

// Capture entered text for comparison
    String enteredTexts = searchpr.getAttribute("value");
    System.out.println("Text inside the search bar: " + enteredTexts);


// Compare searched and listed store
    WebElement firstListedpr = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
            "/html/body/ngb-modal-window/div/div/app-products-popup/div[2]/div/div/div/cdk-virtual-scroll-viewport/div[1]/p-table/div/div/table/tbody/tr/td[2]/div/div/div[1]/span"
    )));
    String firstListedstr = firstListedpr.getText();
    System.out.println(">>>> First listed product: " + firstListedpr);
    Reporter.log("<p>>>>>>>>  Navigating to portal</p>");
// Assert store names match
    Assert.assertEquals(firstListedstr, enteredTexts, "The searched product does not match the first listed product!");
    System.out.println(">>>> Comparison Verified: The searched product matches the first listed product.");
    Reporter.log("<p>>>>> Comparison Verified: The searched product matches the first listed product.</p>");
    //*[@id="searchBar"]
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/ngb-modal-window/div/div/app-products-popup/div[2]/div/div/div/cdk-virtual-scroll-viewport/div[1]/p-table/div/div/table/tbody/tr/td[5]/div/div/button"))).click();
    System.out.println(">>>>  Select product for bundle  !");
    Reporter.log("<p>>>>>  Select product for bundle  !");
    screnshoots.takeScreenshot(driver);
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/ngb-modal-window/div/div/app-products-popup/div[3]/button[2]"))).click();
    System.out.println(">>>>  Save after selecting product for bundle  !");
    Reporter.log("<p>>>>>  Save after selecting product for bundle  !</p>");
    screnshoots.takeScreenshot(driver);
} catch (Exception e) {
    System.out.println("\u001B[31m Error: >>>>  Not able to select the product \u001B[0m");
    Reporter.log("<p>>>>>  Not able to select the product</p>");
}


        System.out.println(">>>>  We will save the bundle for now we keep it pending ......  !");
        Reporter.log("<p>>>>>  Save after selecting product for bundle  !</p>");


    }



}








