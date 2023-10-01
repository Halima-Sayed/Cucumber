package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import steps.PageInitializer;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class CommonMethods extends PageInitializer {

    public static WebDriver driver;

    public static void openBrowserAndLaunchApplication(){
        ConfigReader.readProperties(Constants.CONFIG_READER_PATH);
        switch (ConfigReader.getPropertyValue("browser")){
            case "chrome":
                driver=new ChromeDriver();
                break;

            case "firefox":
                driver=new FirefoxDriver();
                break;
        }

        driver.manage().window().maximize();
        driver.get(ConfigReader.getPropertyValue("url"));
        driver.manage().timeouts().implicitlyWait( Duration.ofSeconds(20));
        //this method is going to initialize all the objects available inside this method
        initializePageObjects();
    }

    public static void openBrowserAndLaunchApplication2() {
        ConfigReader.readProperties(Constants.CONFIG_READER_PATH);
        switch (ConfigReader.getPropertyValue("browser")) {
            case "chrome":
                ChromeOptions ops = new ChromeOptions();
                ops.addArguments("--no-sandbox");
                ops.addArguments("--remote-allow-origins=*");
                ops.addArguments("--headless=new");

                driver = new ChromeDriver(ops);
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
        }
        driver.manage().window().maximize();
        driver.get(ConfigReader.getPropertyValue("url"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        //this method is going to initialize all the objects available inside this method
        initializePageObjects();
    }

    //To clear the text box element then we send text
    //the things that will change dynamically in my function we pass them as a parameter
    //We are passing 2 here first WebElement -> at this particular element you go and perform an operation
    //Second Data -> this data you are going to pass
    public static void sendText(String text, WebElement element){
        element.clear();
        element.sendKeys(text);
    }

    //This method is going to return a wait and that wait I am going use in my click because maybe my element is not loaded properly or clickable properly
    //in order to make it clickable we have to provide some wait before performing click operation
    public static WebDriverWait getWait(){
        WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(20));
        return wait;
    }

    public static void waitForClickability(WebElement element){
        getWait().until(ExpectedConditions.elementToBeClickable(element));
    }
    public static void click(WebElement element){
        waitForClickability(element);
        element.click();
    }
    public static void selectFromDropdown(WebElement dropDown, String visibleText) {
        Select sel = new Select(dropDown);
        sel.selectByVisibleText(visibleText);
    }

    public static void selectFromDropdown(String value, WebElement dropDown) {
        Select sel = new Select(dropDown);
        sel.selectByValue(value);
    }

    public static void selectFromDropdown(WebElement dropDown, int index) {
        Select sel = new Select(dropDown);
        sel.selectByIndex(index);
    }

    //I need to write a function for screenshot which will save my screenshot for all the test cases with different names for that it will not be overriden
    //Screenshot method in cucumber - here it accepts array of byte got pictures
    //the return type of this method is array of bytes so that it will give me the data weather the test case is passed of failed so it can
    //segregate the path of ss to save it at pertinent places in the framework.
    public static byte[] takeScreenshot(String fileName){
        TakesScreenshot ts = (TakesScreenshot) driver;
        //we write this line because cucumber accepts array of byte for screenshot
        //this line is to generate the image of the screenshot
        byte[] picBytes=ts.getScreenshotAs(OutputType.BYTES);
        //this is to save the file
        File screenShot = ts.getScreenshotAs(OutputType.FILE);
        //Capital M-> Month, Lowercase m-> minutes
        //In case if it doesn't find file name or path it will throw an exception
        try {
            FileUtils.copyFile(screenShot, new File(Constants.SCREENSHOT_FILEPATH + fileName + " "
                    + getTimeStamp("yyyy-MM-dd-HH-mm-ss") + ".png"));
        }catch (IOException e){
            e.printStackTrace();
        }

        return picBytes;
    }

    public static String getTimeStamp(String pattern){
        //it returns the current date and time in java
        Date date = new Date();
        //this function sdf used to format the date as per the pattern we are passing
        SimpleDateFormat sdf=new SimpleDateFormat(pattern);
        //this line is going to return the formatted date
        return sdf.format(date);
    }

    public static void closeBrowser() {
        if(driver!=null) {
            driver.quit();
        }
    }
}
