package Extent;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Extent_manager{
    private static ExtentReports extent;

    public static ExtentReports createInstance(String fileName) {
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter(fileName);
        htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setDocumentTitle("NokNok Test Results");
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setReportName("NokNok");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("Organization", "Ieng Group");
        extent.setSystemInfo("Project", "NokNok");
        extent.setSystemInfo("Browser", "Chrome");
        return extent;
    }

    public static ExtentReports getInstance() {
        if (extent == null) {
            extent = createInstance("target/surefire-reports/extent.html");
        }
        return extent;
    }
}