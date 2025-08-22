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
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;
import java.time.Duration;

public class BrandMerchant {

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
    public void BrandSection() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Explicit wait with 10 seconds timeout
        try {
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-merchants-management/div/div/div/div[2]/div/div/app-merchant-list/p-table/div/div/table/tbody/tr/td[1]/div/div/span"))).click();
            System.out.println(">>>>  Open Merhcant");
            Reporter.log("<p>>>>>  Open Merhcant</p>");
            screnshoots.takeScreenshot(driver);
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"nav-tab\"]/li[5]/a"))).click();
            System.out.println(">>>>  Open Brand section");
            Reporter.log("<p>>>>>  Open Brand section</p>");
            screnshoots.takeScreenshot(driver);


        } catch (Exception e) {

            System.out.println("\u001B[31m Error: There is no Brand tab opened \u001B[0m");
            Reporter.log("<p>>>>> There is no Brand tab opened</p>");
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
            System.out.println("\u001B[31m Error: Failed to scroll the page: " + e.getMessage() + "\u001B[0m");
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

        String expectb = "Brands"; // Replace with the actual title
        String actualb = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-merchant-view/div/div/div/div[2]/div/div/div/app-brands/div[1]/div/ul[1]/li/h3")
        )).getText();
        Assert.assertEquals(actualb, expectb);
        System.out.println("\u001B[32m>>>>>>>  TC: Yes Its successfully navigating us to the Brand screen \u001B[0m");
        Reporter.log("<p>>>>>>  TC: Yes Its successfully navigating us to the Brand screen  </p>");

        System.out.println("\u001B[32m The tab name is :  \u001B[0m " + actualb);
        Reporter.log("\u001B[32m The tab name is :  \u001B[0m " + actualb);
    }


    @Test(priority = 3)
    public void BrandMenu3() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Explicit wait with 10 seconds timeout
        try {
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-merchant-view/div/div/div/div[2]/div/div/div/app-brands/div[2]/div/div/div/app-brand-list/p-table/div/div/table/tbody/tr[1]/td[4]"))).click();
            System.out.println(">>>>  Open 3 dots menu for brand ");
            Reporter.log("<p>>>>>  Open 3 dots menu for brand </p>");
            screnshoots.takeScreenshot(driver);
            Thread.sleep(2000);
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-merchant-view/div/div/div/div[2]/div/div/div/app-brands/div[2]/div/div/div/app-brand-list/p-table/div/div/table/tbody/tr[1]/td[4]/ul/li[2]/a"))).click();
            System.out.println(">>>>  Try to delete the brand");
            Reporter.log("<p>>>> Try to delete the brand</p>");
            screnshoots.takeScreenshot(driver);

// Popop appearnace for delete
            String expectdlt = "Delete this brand?"; // Replace with the actual title
            String actualdlt = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//*[@id=\"swal2-title\"]")
            )).getText();
            Assert.assertEquals(actualdlt, expectdlt);
            System.out.println("\u001B[32m>>>>>>>  TC: Yes Its successfully navigating us to the delete brand popop \u001B[0m");
            Reporter.log("<p>>>>>>  TC: Yes Its successfully navigating us to the delete brand popop  </p>");

            System.out.println("\u001B[32m The text in delete popop is : \u001B[0m " + actualdlt);
            Reporter.log("\u001B[32m The text in delete popop is : \u001B[0m " + actualdlt);

            Thread.sleep(2000);
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div/div[3]/button[1]"))).click();
            System.out.println(">>>>  For now we will not delete this brand ");
            Reporter.log("<p>>>>>  For now we will not delete this brand </p>");
            screnshoots.takeScreenshot(driver);


        } catch (Exception e) {

            System.out.println("\u001B[31m Error: We cannot perform 3 dots activities \u001B[0m");
            Reporter.log("<p>>>>>Error: We cannot perform 3 dots activities </p>");
        }
    }

    @Test(priority = 4)
    public void EditBrand() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Explicit wait with 10 seconds timeout
        try {
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-merchant-view/div/div/div/div[2]/div/div/div/app-brands/div[2]/div/div/div/app-brand-list/p-table/div/div/table/tbody/tr[1]/td[4]"))).click();
            System.out.println(">>>>  Open 3 dots menu for brand ");
            Reporter.log("<p>>>>>  Open 3 dots menu for brand </p>");
            screnshoots.takeScreenshot(driver);
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-merchant-view/div/div/div/div[2]/div/div/div/app-brands/div[2]/div/div/div/app-brand-list/p-table/div/div/table/tbody/tr[1]/td[4]/ul/li[1]/a"))).click();
            System.out.println(">>>>  Try to Edit the brand");
            Reporter.log("<p>>>> Try to Edit the brand</p>");
            screnshoots.takeScreenshot(driver);

            // Wait until the input field is visible
            WebElement brandInputField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("/html/body/ngb-modal-window/div/div/app-add-brand/div[2]/div/div/div/div/form/div[1]/input")
            ));

// Get the existing text
            String existingText = brandInputField.getAttribute("value");
            System.out.println(">>>>  Pick the existing tetx in feild");
            Reporter.log("<p>>>> Pick the existing tetx in feild </p>");
// Append new text to the existing text
            brandInputField.sendKeys(existingText + " Edit brand");

// Wait for a moment (optional)
            Thread.sleep(2000); // Just for demonstration, avoid using Thread.sleep in actual code

// Clear the field completely
            brandInputField.clear();
            System.out.println(">>>>  Clear the text from feild ");
            Reporter.log("<p>>>> Clear the text from feild  </>");
// Re-enter the original text (if needed)
            brandInputField.sendKeys(existingText);
            System.out.println(">>>>  Re-enter the orignal text ");
            Reporter.log("<p>>>> Re-enter the orignal text  </>");

            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/ngb-modal-window/div/div/app-add-brand/div[2]/div/div/div/div/form/div[5]/div/label"))).click();
            System.out.println(">>>>  On off the toggle for promotion card ");
            Reporter.log("<p>>>>>  On off the toggle for promotion card  </p>");
            screnshoots.takeScreenshot(driver);
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/ngb-modal-window/div/div/app-add-brand/div[2]/div/div/div/div/form/div[5]/div/label"))).click();
            System.out.println(">>>>  On off the toggle for promotion card ");
            Reporter.log("<p>>>>>  On off the toggle for promotion card  </p>");
            screnshoots.takeScreenshot(driver);

            wait.until(ExpectedConditions.elementToBeClickable(By.xpath(" /html/body/ngb-modal-window/div/div/app-add-brand/div[2]/div/div/div/div/form/div[6]/button"))).click();
            System.out.println(">>>>  Save the edit brand ! ");
            Reporter.log("<p>>>>>  Save the edit brand ! </p>");
            screnshoots.takeScreenshot(driver);

Thread.sleep(2000);
        } catch (Exception e) {
            System.out.println("\u001B[31m Error: We cannot perform Edit activity wih 3 dots menu \u001B[0m");
        }

    }


    @Test(priority = 5)
    public void AddNewBrand() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Explicit wait with 10 seconds timeout
        try {
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-merchant-view/div/div/div/div[2]/div/div/div/app-brands/div[1]/div/ul[2]/li[2]/button"))).click();
            System.out.println(">>>>  Add new brand ");
            Reporter.log("<p>>>>>  Add new brand </p>");
            screnshoots.takeScreenshot(driver);


            String expectab = "Add Brand"; // Replace with the actual title
            String actualab = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("/html/body/ngb-modal-window/div/div/app-add-brand/div[2]/div/div/div/div/h1")
            )).getText();
            Assert.assertEquals(actualab, expectab);
            System.out.println("\u001B[32m>>>>>>>  TC: Yes Its successfully navigating us to the Add brand Modal \u001B[0m");
            Reporter.log("<p>>>>>>  TC: Yes Its successfully navigating us to the Add brand Modal   </p>");

            System.out.println("\u001B[32m The text in Add modal is : \u001B[0m " + actualab);
            Reporter.log("\u001B[32m The text in Add modal is : \u001B[0m " + actualab);

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/ngb-modal-window/div/div/app-add-brand/div[2]/div/div/div/div/form/div[1]/input"))).sendKeys("Zrk brand");
            System.out.println(">>>> Add name in field for New brand");
            Reporter.log(">>>> Add name in field for New brand");


            // Click on the "Choose File" button (if required by UI)
// Click on "Choose File" button
            WebElement chooseFileButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("/html/body/ngb-modal-window/div/div/app-add-brand/div[2]/div/div/div/div/form/div[3]/app-image-upload/div/button")
            ));
            chooseFileButton.click();
            System.out.println(">>>> Opened choose file option for image uploading");

// Wait for file picker to open (small delay)
            Thread.sleep(2000);

// Use Robot Class to enter file path
            Robot robot = new Robot();

// Set the file path to clipboard
            // **Set file path to clipboard (Your Line)**
            StringSelection filePath = new StringSelection("C:\\Users\\I-Eng\\Documents\\1.png");
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(filePath, null);
            System.out.println(">>>> Pick the path of the Image which we have to uplaod ");
            Reporter.log(">>>> Pick the path of the Image which we have to uplaod ");

// Press `CTRL + V` to paste the file path
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            System.out.println(">>>> Paste the path of the Image which we have to uplaod ");
            Reporter.log(">>>> Paste the path of the Image which we have to uplaod ");
// Press `Enter` to confirm selection
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);

            System.out.println(">>>> Image selected successfully.");
            Reporter.log(">>>> Image selected successfully.");

Thread.sleep(2000);
// Toggle for promotion card

            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/ngb-modal-window/div/div/app-add-brand/div[2]/div/div/div/div/form/div[4]/div/label"))).click();
            System.out.println(">>>>  On off the toggle for promotion card ");
            Reporter.log("<p>>>>>  On off the toggle for promotion card  </p>");
            screnshoots.takeScreenshot(driver);
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/ngb-modal-window/div/div/app-add-brand/div[2]/div/div/div/div/form/div[4]/div/label"))).click();
            System.out.println(">>>>  On off the toggle for promotion card ");
            Reporter.log("<p>>>>>  On off the toggle for promotion card  </p>");
            screnshoots.takeScreenshot(driver);
//
//// Wait until the submit button is clickable
//            WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/ngb-modal-window/div/div/app-add-brand/div[2]/div/div/div/div/form/div[5]/button")));
//
//// Scroll to the submit button using JavaScriptExecutor
//            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", submitButton);
//            System.out.println(">>>> Scrolled to the submit button");
//
//// Now click the submit button
//            submitButton.click();
//            System.out.println(">>>> Submit the details for new brand");
//            Reporter.log("<p>>>>> Submit the details for new brand </p>");
//
//// Take a screenshot
//            screnshoots.takeScreenshot(driver);



        } catch (Exception e) {
            System.out.println("\u001B[31m Error: We cannot perform Add Brand activity wih 3 dots menu \u001B[0m");
        }
    }


    @Test(priority =6)
    public void Brandavlitlity() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Explicit wait with 10 seconds timeout




    }
}