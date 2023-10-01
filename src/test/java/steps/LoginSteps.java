package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.xml.DOMConfigurator;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.LoginPage;
import utils.CommonMethods;
import utils.ConfigReader;
import utils.Log;

public class LoginSteps extends CommonMethods {

    @Given("user is navigated to HRMS application")
    public void user_is_navigated_to_hrms_application() {
        //to launch Chrome browser
        openBrowserAndLaunchApplication();
        //driver=new ChromeDriver();
        //driver.get("http://hrm.syntaxtechs.net/humanresources/symfony/web/index.php/auth/login");
        //driver.manage().window().maximize();
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
    @When("user enters valid admin username and password")
    public void user_enters_valid_admin_username_and_password() {
        //creating the object of the class to access all the webelements from it
        //LoginPage loginPage=new LoginPage();
       //WebElement username= driver.findElement(By.id("txtUsername"));
       //WebElement password= driver.findElement(By.id("txtPassword"));
       //username.sendKeys(ConfigReader.getPropertyValue("username"));
       //password.sendKeys(ConfigReader.getPropertyValue("password"));
        //for entering the username I need to use sendKeys which is available for me from commonMethods so just call it
        //It is asking for two things now data I need to past
        //Admin username it is available in configReader call it
        //On which locator am I going to call it -> username and it is available well I have page object model it is available in loginPage fine create the object and it's already there call it.
        //All about connecting the dots. I have segregated things at segregated and organized places I just need to connect all of them in step definition
        //this step def is going to link with my feature file -> my feature file is available in my runner class so the moment I run my runner class it will take the code from feature file
        //My feature file is connected to step def -> my step def has all the dots connected it will execute my complete code without passing any data hardcoded
        //we are calling dom configurator which is asking for the file which we used to integrate logs in our project
        DOMConfigurator.configure("log4j.xml");
        Log.startTestCase("My test case starts here");
        sendText(ConfigReader.getPropertyValue("username"),loginPage.usernameField);
        Log.info("my username has been entered");
        sendText(ConfigReader.getPropertyValue("password"),loginPage.passwordField);
        Log.info("my password has been entered");
    }
    @When("user clicks on login button")
    public void user_clicks_on_login_button() {
        //LoginPage loginPage=new LoginPage();
        //WebElement loginButton= driver.findElement(By.name("Submit"));
        //loginButton.click();
        click(loginPage.loginButton);
    }
    @Then("user is successfully logged in the application")
    public void user_is_successfully_logged_in_the_application() {
        System.out.println("test case passed");
    }

    @When("user enters ess username and password")
    public void user_enters_ess_username_and_password() {
        //LoginPage loginPage=new LoginPage();
        //logged in via normal employee
        //WebElement username= driver.findElement(By.id("txtUsername"));
        //WebElement password= driver.findElement(By.id("txtPassword"));
        //username.sendKeys("dalima123");
       // password.sendKeys("Hum@nhrm123");
        sendText("dalima12",loginPage.usernameField);
        sendText("Hum@nhrm123",loginPage.passwordField);
    }
    @When("user enters invalid admin username and password")
    public void user_enters_invalid_admin_username_and_password() {
        //LoginPage loginPage=new LoginPage();
        //WebElement username= driver.findElement(By.id("txtUsername"));
        //WebElement password= driver.findElement(By.id("txtPassword"));
        //username.sendKeys("admin123");
        //password.sendKeys("Hum@nhrm123");
        sendText("admin123", loginPage.usernameField);
        sendText("Hum#n",loginPage.passwordField);
    }
    @Then("error message is displayed")
    public void error_message_is_displayed() {
        System.out.println("Error message is displayed");
    }
    @When("user enters {string} and {string} and verifying the {string} for the combinations")
    public void user_enters_and_and_verifying_the_for_the_combinations(String username, String password, String errorMessageExpected) {
      //we need to write the code here to match the errors
        sendText(username, loginPage.usernameField);
        sendText(password, loginPage.passwordField);
        click(loginPage.loginButton);
        //fetching the error message from the web element
       String errorMessageActual= loginPage.errorMessageField.getText();
        //error message coming from feature file too which we can compare
        //we do validations using assertions, verification we can do manually as well but validations must be done by code.
        //when you are verifying  the existence of  the field on the application or the existence of the web element we call it verifications.
        //Is username field editable or not, is it expecting my data or not. Login button is clickable or not -> validations
        //"values do not match" will show up in the console when both actual and expected don't match,otherwise it will continue and pass the test.
        Assert.assertEquals("values do not match",errorMessageActual,errorMessageExpected);
    }
}