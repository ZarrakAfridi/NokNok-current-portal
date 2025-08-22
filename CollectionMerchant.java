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

public class CollectionMerchant {

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
        public void CollectionSection () throws InterruptedException {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Explicit wait with 10 seconds timeout
try {
    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-merchants-management/div/div/div/div[2]/div/div/app-merchant-list/p-table/div/div/table/tbody/tr/td[1]/div/div/span"))).click();
    System.out.println(">>>>  Open Merhcant");
    Reporter.log("<p>>>>>  Open Merhcant</p>");
    screnshoots.takeScreenshot(driver);
    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"nav-tab\"]/li[3]/a"))).click();
    System.out.println(">>>>  Open collection section");
    Reporter.log("<p>>>>>  Open collection section</p>");
    screnshoots.takeScreenshot(driver);

    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-merchant-view/div/div/div/div[2]/div/div/div/app-collections/div[2]/div/div/div/app-collection-list/p-table/div/div/table/tbody/tr[1]/td[7]"))).click();
    System.out.println(">>>>  Open 3 dots menu of collection verify that its working ");
    Reporter.log("<p>>>>>  Open 3 dots menu of collection verify that its working</p>");
    screnshoots.takeScreenshot(driver);


} catch (Exception e) {

    System.out.println("\u001B[31m Error: There is no Collections tab opened \u001B[0m");
    Reporter.log("<p>>>>> There is no Collections tab opened</p>");
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
                System.out.println("\u001B[31m Error: Failed to scroll the page: " + e.getMessage()+"\u001B[0m");
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


        @Test(priority = 3)
        public void CollectionSerch () throws InterruptedException {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Explicit wait with 10 seconds timeout

            try {
                // Search comparison
                WebElement searchcll = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"searchBar\"]")));
                searchcll.sendKeys("Zarrak simple food");
                System.out.println(">>>>  Enter collection name for search ");
                Reporter.log("<p>>>>>  Enter collection name for search</p>");
                Thread.sleep(1500);
                screnshoots.takeScreenshot(driver);

                String enteredText = searchcll.getAttribute("value");
                System.out.println("Text inside the search bar: " + enteredText);
                Reporter.log("<p>>Text inside the search bar: " + enteredText + "</p>");

                Thread.sleep(2000);

// Campare searched and listed bundle
                //String firstListedCampaignXpath = "/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-campaign-management/div/div/div/div[2]/div/div/app-campaign-list/p-table/div/div/table/tbody/tr/td[1]/div/div/span";
                String firstListedcll = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-merchant-view/div/div/div/div[2]/div/div/div/app-collections/div[2]/div/div/div/app-collection-list/p-table/div/div/table/tbody/tr/td[1]/div/div/div/div/span"))).getText();

                // Log the first listed campaign name
                System.out.println(">>>> First listed Bundle: " + firstListedcll);
                Reporter.log("<p>>>>>> First listed Bundle: " + firstListedcll + "</p>");
                // Assert that the searched campaign and the first listed campaign are the same
                Assert.assertEquals(firstListedcll, enteredText, "The searched collection does not match the first listed bundle!");
                System.out.println(">>>> Verified: The searched collection matches the first listed bundle.");
                Reporter.log("<p>>>>>>>>  Navigating to portal</p>");


                Thread.sleep(1500);
                // This is search collection
                WebElement CollName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-merchant-view/div/div/div/div[2]/div/div/div/app-collections/div[2]/div/div/div/app-collection-list/p-table/div/div/table/tbody/tr/td[1]/div/div/div/div/span")));
                String coll = CollName.getText();
                System.out.println(">>>>>>> Name of Collection is: " + coll);
                Reporter.log("<p>>>>>>> Name of Collection is: " + coll + "</p>");
                screnshoots.takeScreenshot(driver);
                Thread.sleep(1500);

            } catch (Exception e) {

                System.out.println("\u001B[31m Error: We cannot perform search accuratly \u001B[0m");
            }

                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-merchant-view/div/div/div/div[2]/div/div/div/app-collections/div[2]/div/div/div/app-collection-list/p-table/div/div/table/tbody/tr/td[1]/div/div/div/div/span"))).click();
                System.out.println(">>>>  Open searched collection ");
                Reporter.log("<p>>>>>  Open searched collection </p>");
                screnshoots.takeScreenshot(driver);

            }


        @Test(priority = 4)
        public void EditCollection () throws InterruptedException {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Explicit wait with 10 seconds timeout


            try {
                WebElement descriptionField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                        "/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-add-collection/div/div/div/div[2]/div[1]/div/div/div/div/form/div/div[2]/div/textarea"
                )));

// Clear the existing text
                descriptionField.clear();
                System.out.println(">>>> Clear previos Description for new description");
                Reporter.log("<p>>>>> Clear previos Description for new description</p>");
// Add new description
                descriptionField.sendKeys("This is the description for the collection for automation");

                System.out.println(">>>> Add description for editing collection ");
                Reporter.log("<p>>>>> Add description for editing collection</p>");
                screnshoots.takeScreenshot(driver);


                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-add-collection/div/div/div/div[1]/div/ul[2]/li[1]/button[3]"))).click();
                System.out.println(">>>>  Open scheduler for collection");
                Reporter.log("<p>>>>>>  Open scheduler for collection</p>");
                screnshoots.takeScreenshot(driver);
                String expectedset = "Zarrak simple food"; // Replace with the actual title
                String actualset = wait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[1]/div/ul[1]/li[2]/span/i")
                )).getText();
                Assert.assertEquals(actualset, expectedset);
                System.out.println(">>>>>>>  TC: The set schdular pouop is opened successfullly *******");
                Reporter.log("<p>>>>>>>>  TC: The set schdular pouop is opened successfullly *******</p>");

            } catch (Exception e) {

                System.out.println("\u001B[31m Error: We cannot edit Collection \u001B[0m");
            }


            try {

                // Locate the elements for "Everyday Scheduler" and "Specific Day Scheduler"
                WebElement everydayScheduler = wait.until(ExpectedConditions.elementToBeClickable(
                        By.xpath("/html/body/ngb-modal-window/div/div/div[2]/div/div/div/form/div[3]/div[1]/div[1]/label")
                ));
                WebElement specificDayScheduler = wait.until(ExpectedConditions.elementToBeClickable(
                        By.xpath("/html/body/ngb-modal-window/div/div/div[2]/div/div/div/form/div[3]/div[2]/div/label")
                ));

                // Check the state of the first element and click the other if selected
                if (everydayScheduler.isSelected()) {
                    specificDayScheduler.click();
                    System.out.println("Specific Day Scheduler clicked because Everyday Scheduler was selected.");
                    Reporter.log("<p>Specific Day Scheduler clicked because Everyday Scheduler was selected.</p>");
                } else if (specificDayScheduler.isSelected()) {
                    everydayScheduler.click();
                    System.out.println("Everyday Scheduler clicked because Specific Day Scheduler was selected.");
                    Reporter.log("<p>Everyday Scheduler clicked because Specific Day Scheduler was selected.</p>");
                } else {
                    // Handle the scenario where neither is selected (optional)
                    System.out.println("Neither option is selected. Defaulting to Everyday Scheduler.");
                    Reporter.log("<p>Neither option is selected. Defaulting to Everyday Scheduler.\"</p>");
                    everydayScheduler.click();
                }

            } catch (Exception e) {
               System.out.println("\u001B[31m Error: Set schdular module not set \u001B[0m");
            }


            try {
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/ngb-modal-window/div/div/div[2]/div/div/div/form/div[6]/button"))).click();
                System.out.println(">>>>  Save the schedular for collection");
                Reporter.log("<p>>>>>  Save the schedular for collection/p>");
                screnshoots.takeScreenshot(driver);
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-add-collection/div/div/div/div[1]/div/ul[2]/li[1]/button[2]"))).click();
                System.out.println(">>>> Add products in collections");
                Reporter.log("<p>>>>> Add products in collections</p>");
                screnshoots.takeScreenshot(driver);

                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"searchBar\"]"))).sendKeys("a");
    System.out.println(">>>> Add products in collections");
    Reporter.log("<p>>>>> Add products in collections</p>");
    screnshoots.takeScreenshot(driver);
    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/ngb-modal-window/div/div/app-search-products-popup/div[2]/div/div/div/div/div/p-table/div/div/table/tbody/tr[1]/td[5]/div/div/button"))).click();
    System.out.println(">>>> Select the product form the list");
    Reporter.log("<p>>>>> Select the product form the list</p>");
    screnshoots.takeScreenshot(driver);

    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/ngb-modal-window/div/div/app-search-products-popup/div[1]/button"))).click();
    System.out.println(">>>> Cross the popup after adding products");
    Reporter.log("<p>>>>> Cross the popup after adding products</p>");
    screnshoots.takeScreenshot(driver);
    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-add-collection/div/div/div/div[1]/div/ul[2]/li[1]/button[1]"))).click();
    System.out.println(">>>> Update the collections setting");
    Reporter.log("<p>>>>> Update the collections setting</p>");
    screnshoots.takeScreenshot(driver);
    Thread.sleep(1000);
    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[1]/div/ul[1]/li[3]/button"))).click();
    System.out.println(">>>> Back successfully to the collection list screen");
    Reporter.log("<p>>>>> Back successfully to the collection list screen</p>");
    screnshoots.takeScreenshot(driver);
} catch (Exception e) {
    System.out.println("[31m Error: We cannot perform edit for collection   \u001B[0m");
}

        }


        @Test(priority = 5)
        public void AddCollection () throws InterruptedException {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Explicit wait with 10 seconds timeout


            try {
//                String expectednc = "New Collection"; // Replace with the actual title
//                String actualnc = wait.until(ExpectedConditions.visibilityOfElementLocated(
//                        By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[1]/div/ul[1]/li[2]/span/i")
//                )).getText();
//                Assert.assertEquals(actualnc, expectednc);
//                System.out.println(">>>>>>>  TC: Navigate successfully to the New collection adding screen *******");

                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-merchant-view/div/div/div/div[2]/div/div/div/app-collections/div[1]/div/ul[2]/li[2]/button"))).click();
                System.out.println(">>>>  Add Collection via add collection button ");
                Reporter.log("<p>>>>>  Add Collection via add collection button </p>");
                screnshoots.takeScreenshot(driver);

                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-root/app-dashboar" +
                        "d/div/div/div/div[2]/div[3]/app-add-collection/div/div/div/div[2]/div/div/div/div/div/form/div/div[1]/div/input"))).sendKeys("Zarrak Auto Collection");
                System.out.println(">>>>  Create Name/lable for new collection");
                Reporter.log("<p>>>>>  reate Name/lable for new collection </p>");
                screnshoots.takeScreenshot(driver);
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-add-collection/div/div" +
                        "/div/div[2]/div/div/div/div/div/form/div/div[2]/div/textarea"))).sendKeys("Description for new Zarrak Auto Collection ! ");
                System.out.println(">>>>  Add description for new collection");
                Reporter.log("<p>>>>>  Add description for new collection </p>");
                screnshoots.takeScreenshot(driver);

                // add product for collection
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]" +
                        "/div[3]/app-add-collection/div/div/div/div[1]/div/ul[2]/li/button[2]"))).click();
                System.out.println(">>>>  Open add product Modal for collection");
                Reporter.log("<p>>>>>  Open add product Modal for collection </p>");
                screnshoots.takeScreenshot(driver);


// compare search and listed item
                WebElement searchpr = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"searchBar\"]")));
                searchpr.sendKeys("Chair");
                System.out.println(">>>> Search for your specefic Product to add in collection");
                Reporter.log("<p>>>>> Search for your specefic Product to add in collection</p>");
                screnshoots.takeScreenshot(driver);
                Thread.sleep(2000);


// Take the text entered in the input field
                String enteredText = searchpr.getAttribute("value");
                System.out.println("Text inside the search bar: " + enteredText);
                Reporter.log("<p>>Text inside the search bar: " + enteredText + "</p>");

// Campare searched and listed bundle

                //String firstListedCampaignXpath = "/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-campaign-management/div/div/div/div[2]/div/div/app-campaign-list/p-table/div/div/table/tbody/tr/td[1]/div/div/span";
                String firstListedpr = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/ngb-modal-window/div/div/app-search-products-popup/div[2]/div/div/div/div/div/p-table/div/div/table/tbody/tr/td[2]/div/div/div[1]/span"))).getText();

                // Log the first listed campaign name
                System.out.println(">>>> First listed Product: " + firstListedpr);
                Reporter.log("<p>>>>>> First listed Product: " + firstListedpr + "</p>");
                // Assert that the searched campaign and the first listed campaign are the same
                Assert.assertEquals(firstListedpr, enteredText, "The searched product does not match the first listed bundle!");
                System.out.println(">>>> Verified: The searched product matches the first listed Product.");
                Reporter.log("<p>>>>> Verified: The searched product matches the first listed Product.</p>");

                screnshoots.takeScreenshot(driver);
                Thread.sleep(3000);


                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/ngb-modal-window/div/div/app-search-products-popup/div[2]/div/div/div/div/div/p-table/div/div/table/tbody/tr/td[5]/div/div/button"))).click();
                System.out.println(">>>>  Product selected for collection ");
                Reporter.log("<p>>>>>  Product selected for collection</p>");
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/ngb-modal-window/div/div/app-search-products-popup/div[2]/div/div/div/div/div/p-table/div/div/table/tbody/tr/td[5]/div/div/button"))).click();
                screnshoots.takeScreenshot(driver);
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/ngb-modal-window/div/div/app-search-products-popup/div[1]/button"))).click();
                System.out.println(">>>>  Close tab after selection product ");

                screnshoots.takeScreenshot(driver);
            } catch (Exception e) {
                System.out.println("\u001B[31m Error: We cannot open the add screen for collection  \u001B[0m");
            }

//// Locate the file input element (if it exists)
//            WebElement fileInput = driver.findElement(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-add-collection/div/div/div/div[2]/div[1]/div/div/div/div/form/div/div[3]/div/div[2]/div/app-image-upload/div/button"));
//
//// Provide the absolute path of the image to upload
//            fileInput.sendKeys("C:\\Users\\I-Eng\\Desktop\\2.png");
//
//            System.out.println(">>>> Image uploaded successfully");
//            Reporter.log("<p>>>>> Image uploaded successfully</p>");
//            screnshoots.takeScreenshot(driver);

            try {
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-add-collection/div/div/div/div[1]/div/ul[2]/li/button[3]"))).click();
                System.out.println(">>>>  Set schedular for collection ");
                Reporter.log("<p>>>>>  Set schedular for collection</p>");

                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/ngb-modal-window/div/div/div[2]/div/div/div/form/div[3]/div[1]/div[1]/label"))).click();
                System.out.println(">>>>  Set daily / everyday ");
                Reporter.log("<p>>>>>  Set daily / everyday </p>");
            } catch (Exception e) {
                System.out.println("\u001B[31m>>>> Error: We cannot open scheduler for new collection! \u001B[0m");

            }


            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-add-collection/div/div/div/div[1]/div/ul[2]/li/button[1]"))).click();
            System.out.println(">>>>  SAVE new collection");
            Reporter.log("<p>>>>>  SAVE new collection</p>");
            screnshoots.takeScreenshot(driver);

            try{
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-add-collection/div/div/div/div[1]/div/ul[2]/li[1]/button[2]"))).click();
                System.out.println(">>>>  Add product in newly added collection");
                Reporter.log("<p>>>>>  Add product in newly added collection</p>");

                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"searchBar\"]"))).sendKeys("Burger");
                System.out.println(">>>>  Search for new product");
                Reporter.log("<p>>>>>  Search for new product</p>");
                screnshoots.takeScreenshot(driver);
            } catch (Exception e) {
                System.out.println("\u001B[31m >> Error: We cannot add product in newly added collection \u001B[0m");
            }

            try{
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-add-collection/div/div/div/div[1]/div/ul[2]/li[1]/button[3]"))).click();
                System.out.println(">>>>  Reset schedular for newly added Collection");
                Reporter.log("<p>>>>>  Reset schedular for newly added Collection</p>");

                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/ngb-modal-window/div/div/div[2]/div/div/div/form/div[3]/div[1]/div[1]/label"))).click();
                System.out.println(">>>>  Try to select timing ");
                Reporter.log("<p>>>>>  >>>>  Try to select timing </p>");
                screnshoots.takeScreenshot(driver);
            } catch (Exception e) {
                System.out.println("\u001B[31m >> Error: We cannot Reset schedular for newly added collection \u001B[0m");
            }

            try{
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-root/app-dashboard/div/div/div/div[2]/div[3]/app-add-collection/div/div/div/div[1]/div/ul[2]/li[1]/button[4]"))).click();
                System.out.println(">>>>  Open view group for newly added Collection");
                Reporter.log("<p>>>>>  Open view group for newly added Collection</p>");
                screnshoots.takeScreenshot(driver);

                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/ngb-modal-window/div/div/app-collection-group/div[2]/div/div/div/div/div[1]/button"))).click();
                System.out.println(">>>>  Click on new group for the newly added collection ");
                Reporter.log("<p>>>>>  Click on new group for the newly added collection</p>");


                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"groupName\"]"))).sendKeys("Newgr");
                System.out.println(">>>>  Add name of grouo for newly added collection");
                Reporter.log("<p>>>>>  Add name of grouo for newly added collection</p>");
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"position\"]"))).sendKeys("4");
                System.out.println(">>>>  Add position of grouo for newly added collection ");
                Reporter.log("<p>>>>>  Add position of grouo for newly added collection</p>");

                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"sizeLimit\"]"))).sendKeys("10");
                System.out.println(">>>>  Add size of grouo for newly added collection ");
                Reporter.log("<p>>>>>  Add size of grouo for newly added collection</p>");
                screnshoots.takeScreenshot(driver);
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/ngb-modal-window/div/div/app-collection-group/div[2]/div/div/div/form/div[2]/button[3]\n"))).click();
                System.out.println(">>>>  Save grouo for newly added collection ");
                Reporter.log("<p>>>>>  Save grouo for newly added collection</p>");
                screnshoots.takeScreenshot(driver);
                // create new collection
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='ui-helper-hidden-accessible']/input[@type='checkbox']"))).click();
                System.out.println(">>>>  Select checkbox of group for collection ");
                Reporter.log("<p>>>>>  Select checkbox of group for collection</p>");
                screnshoots.takeScreenshot(driver);


                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/ngb-modal-window/div/div/app-collection-group/div[1]/button"))).click();
                System.out.println(">>>>  Close modal of new group ");
                Reporter.log("<p>>>>>   Close modal of new group</p>");
                screnshoots.takeScreenshot(driver);


            } catch (Exception e) {
                System.out.println("\u001B[31m >> Error: We cannot create or assign new group to newly added collection \u001B[0m");
            }
        }
}