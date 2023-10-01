package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonMethods;

public class LoginPage extends CommonMethods {

    //@FindBy is the class we use define the locator
    //When we keep all the locators like this in encapsulated form we call it -> we call it object repository of POM,
    @FindBy(xpath = "//*[@id='txtUsername']")
    public WebElement usernameField;

    @FindBy(id="txtPassword")
    public WebElement passwordField;

    @FindBy(id="btnLogin")
    public WebElement loginButton;

    @FindBy(id="spanMessage")
    public WebElement errorMessageField;

    //to initialize all the elements of this page we have to call them inside constructor
    //constructor is a block of code having the same name as the class name we generally use to initialize all the objects of the class
    public LoginPage(){
        //PageFactory has the function implemented inside for page object model design pattern
        //here we are calling the driver which is coming from common methods then we are calling all to initialize all the elements for this -> this is going to be pointing to local page/current reference
        //Breakdown -> PageFactory or POM initElements is the method to initialize all the elements. Driver is for the reference and this is the keyword we generally use as a pointer in Java
        PageFactory.initElements(driver,this);
    }
}
