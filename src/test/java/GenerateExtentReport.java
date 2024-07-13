import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class GenerateExtentReport {
    public static ExtentHtmlReporter htmlReporter;
    public static ExtentReports extent;
    public static ExtentTest test;


    public static String[] fileName = {"result.txt"};

    @BeforeTest
    public static void startTest() {
        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/ExtenttestReport.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        htmlReporter.config().setChartVisibilityOnOpen(true);
        htmlReporter.config().setDocumentTitle("Extent Report");
        htmlReporter.config().setReportName("Test Report");
        htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
        htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
    }
    @Test
    public void CreateReport() throws FileNotFoundException {
        String[] textReport;

        for (int i = 0; i < fileName.length; i++) {
            File f = new File(fileName[i]);
            if (f.exists() && !f.isDirectory()) {
                Scanner sentenceScanner = new Scanner(new File(fileName[i]));

                ArrayList<String> sentenceList = new ArrayList<String>();

                while (sentenceScanner.hasNextLine()) {

                    sentenceList.add(sentenceScanner.nextLine());

                }
                sentenceScanner.close();

                if (!sentenceList.isEmpty()) {
                    String[] sentenceArray = sentenceList.toArray(new String[sentenceList.size()]);


                    for (int r = 0; r < sentenceArray.length; r++) {
                        textReport = sentenceArray[r].split("\\|");

                        if (!textReport[0].isEmpty()) {
                            test = extent.createTest(textReport[0] , textReport[2]);
                        }

                        if (textReport.length > 3 && !textReport[1].isEmpty()) {
                            if (textReport[1].contains("true")) {
                                test.log(Status.PASS, textReport[1]);
                            } else if (textReport[1].contains("false")) {
                                test.log(Status.FAIL, textReport[1]);
                            }
                        }

                        if (textReport.length > 3 && !textReport[3].isEmpty()) {
                                test.log(Status.DEBUG, textReport[3]);
                        }
                        if (textReport.length > 4 && !textReport[4].isEmpty()) {
                                test.log(Status.DEBUG, textReport[4]);
                        }

                    }
                }

            }
        }

    }

    @AfterMethod
    public void getResult(ITestResult result) {
        try {
            if (result.getStatus() == ITestResult.FAILURE) {
                test.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " FAILED ", ExtentColor.RED));
                test.fail(result.getThrowable());
            } else if (result.getStatus() == ITestResult.SUCCESS) {
                test.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " PASSED ", ExtentColor.GREEN));
            } else {
                test.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " SKIPPED ", ExtentColor.ORANGE));
                test.skip(result.getThrowable());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterTest
    public void tearDown() {
        //to write or update test information to reporter
        extent.flush();
    }

    @AfterClass
    public void Afterclass() throws IOException {
        /*String dir = System.getProperty("user.dir");
        String cmd = "sudo mv " + dir + "/ExtenttestReport.html /var/www/html/";
        System.out.println(cmd);
        Runtime run = Runtime.getRuntime();
        Process pr = run.exec(cmd);*/
    }
}