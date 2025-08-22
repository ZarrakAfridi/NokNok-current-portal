package Utilities;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.testng.Reporter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;

public class screnshoots {

    // Set desired screenshot width and height as a percentage of screen size
    static final double WIDTH_PERCENTAGE = 0.7; // 70% of screen width
    static final double HEIGHT_PERCENTAGE = 0.6; // 60% of screen height

    public static void takeScreenshot(WebDriver driver) {
        String screenshotBase64 = "";
        try {
            // Capture screenshot as a File object
            File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            BufferedImage screenshotImage = ImageIO.read(screenshotFile);

            // Dynamically retrieve the screen dimensions
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            int screenWidth = (int) screenSize.getWidth();
            int screenHeight = (int) screenSize.getHeight();

            // Calculate desired width and height based on screen size
            int desiredWidth = (int) (screenWidth * WIDTH_PERCENTAGE);
            int desiredHeight = (int) (screenHeight * HEIGHT_PERCENTAGE);

            // Compress the screenshot image to match screen dimensions
            BufferedImage compressedImage = compressImage(screenshotImage, desiredWidth, desiredHeight);

            // Convert the compressed image to Base64
            screenshotBase64 = imageToBase64(compressedImage);

        } catch (IOException | NoSuchElementException | TimeoutException ex) {
            ex.printStackTrace();
        }

        // Log the image in the TestNG report
        Reporter.log("<p><img src=\"data:image/png;base64," + screenshotBase64 + "\" alt=\"Screenshot\" width=\"100%\" ></p>");
    }

    private static BufferedImage compressImage(BufferedImage image, int width, int height) {
        // Create a new buffered image with the desired width and height
        BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = resizedImage.createGraphics();
        graphics.drawImage(image, 0, 0, width, height, null);
        graphics.dispose();
        return resizedImage;
    }

    private static String imageToBase64(BufferedImage image) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            // Write the image to the output stream
            ImageIO.write(image, "png", outputStream);
            // Convert the output stream to Base64
            byte[] imageBytes = outputStream.toByteArray();
            return Base64.getEncoder().encodeToString(imageBytes);
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}
