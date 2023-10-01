package API;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


//this will run my tests alphabetically
//This is the functionality of junit which will break the rule of java or executing line by line
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HardCodedExamples {
    //baseURI=baseURL+endpoint
    //given-preparation
    //when-hitting the endpoint
    //base URI=base URL
    //RestAssured.baseURI will define my URI
    String baseURI = RestAssured.baseURI = "http://hrm.syntaxtechs.net/syntaxapi/api";
    //value of token should be same as postman
    String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2OTEwOTcwMzQsImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MTY5MTE0MDIzNCwidXNlcklkIjoiNTY0NCJ9.HVMao8fNlJO5KifDbE02xOtGWCptZQCK1YhVcFxozRE";
    //above is one basic data warehouse

    static String employee_id;

    //in this method we are going to create an employee
    //we need headers,body to prepare the request
    //junit annotation
    @Test
    public void acreateEmployee() {

        //prepare the request
        RequestSpecification request = given().header("Content-Type", "application/json")
                .header("Authorization", token)
                .body("{\n" +
                        "  \"emp_firstname\": \"hi\",\n" +
                        "  \"emp_lastname\": \"bye\",\n" +
                        "  \"emp_middle_name\": \"ms\",\n" +
                        "  \"emp_gender\": \"M\",\n" +
                        "  \"emp_birthday\": \"2010-07-29\",\n" +
                        "  \"emp_status\": \"what\",\n" +
                        "  \"emp_job_title\": \"nothing\"\n" +
                        "}");

        //We have to call the base URI on line 24 as a central junction. The moment you specify it in the class your rest assured is going to identify
        //that this endpoint which I am hitting will be under this common junction. THIS IS THE FUNCTIONALITY OF REST ASSURED API.
        //hitting the endpoint and getting the response
        Response response = request.when().post("/createEmployee.php");
        //verifying the response and status code
        response.then().assertThat().statusCode(201);
        //System.out.println(response);
        //this method we use to print the response of API in console
        response.prettyPrint();

       //verify all the values and headers from response

        //equalTo we import hamcrest method -> Hamcrest is a popular framework, it helps us  to create the matcher object.
        //Mainly devs use it for unit testing and QA use it for integration testing in java lang
        //it is mainly used with frameworks like testng, cucumber,junit,mockito etc.
        //when I am working with response keyword which is coming from rest assured API and I am using then keyword to verify it is considered as a part of matchers.
        //hence we use hamcrest method to identify the value and to verify its overall value along with the key
       response.then().assertThat().body("Employee.emp_firstname",equalTo("hi"));
       response.then().assertThat().body("Employee.emp_middle_name",equalTo("ms"));
        //verify the message
       response.then().assertThat().body("Message",equalTo("Employee Created"));
       //header will always ask for key and value
       response.then().assertThat().header("X-Powered-By","PHP/7.2.18");

        //it will return the employee id and saved it in variable
        //if I want to get any data from a specific key I have to use jsonPath method
        //JsonPath class -> is a class we have jsonPath() method it is going to return a value against a specific key e.g. in java we have map.get method
        //I created a static variable  inside that static variable I saved employee id and here json path is coming from Json path class from the json object which
        //is coming from json dependency we have added. The moment I provide the key in .getString method it will return me the value
        employee_id=response.jsonPath().getString("Employee.employee_id");
        System.out.println(employee_id);
    }
    @Test
    public void bgetCreatedEmployee(){
        RequestSpecification request=given().header("Authorization",token).queryParam("employee_id",employee_id);
        Response response=request.when().get("/getOneEmployee.php");
        response.then().assertThat().statusCode(200);
        response.prettyPrint();
        //this will fail because we cannot get created employee when It's not even created. I have to change method a,b,c

        //this time we can't have employee e capital because the because in get call the employee starts with lowercase e and in post call starts with capital E
        String tempEmpId=response.jsonPath().getString("employee.employee_id");
        //now we compare the emp ids
        Assert.assertEquals(employee_id,tempEmpId);
    }
    @Test
    public void cUpdateEmployee(){
        RequestSpecification request= given().header("Content-Type", "application/json")
                .header("Authorization", token).body("{\n" +
                        "  \"employee_id\": \""+employee_id+"\",\n" +
                        "  \"emp_firstname\": \"Hi\",\n" +
                        "  \"emp_lastname\": \"Bye\",\n" +
                        "  \"emp_middle_name\": \"Hey\",\n" +
                        "  \"emp_gender\": \"M\",\n" +
                        "  \"emp_birthday\": \"2010-07-29\",\n" +
                        "  \"emp_status\": \"what\",\n" +
                        "  \"emp_job_title\": \"nothing\"\n" +
                        "}");
        Response response=request.when().put("/updateEmployee.php");
        response.then().assertThat().statusCode(200);
        response.then().assertThat().body("Message",equalTo("Employee record Updated"));
    }
    @Test
    public void dgetCreatedEmployee(){
        RequestSpecification request=given().header("Authorization",token).queryParam("employee_id",employee_id);
        Response response=request.when().get("/getOneEmployee.php");
        response.then().assertThat().statusCode(200);
        response.prettyPrint();
        //String tempEmpId=response.jsonPath().getString("employee.employee_id");
        //Assert.assertEquals(employee_id,tempEmpId);
    }
    //Homework -> Make partial employee update request and get the partially updated employee
    @Test
    public void ePartiallyUpdateEmployee(){
        RequestSpecification request=given().header("Content-Type","application/json").header("Authorization", token).body("{\n" +
                "  \"employee_id\": \"88363A\",\n" +
                "  \"emp_middle_name\": \"cool\"\n" +
                "}");
        Response response=request.when().patch("/updatePartialEmplyeesDetails.php");
        response.then().assertThat().statusCode(201);
        response.then().assertThat().body("Message",equalTo( "Employee record updated successfully"));
    }
    @Test
    public void fgetCreatedEmployee() {
        RequestSpecification request = given().header("Authorization", token).queryParam("employee_id", employee_id);
        Response response = request.when().get("/getOneEmployee.php");
        response.then().assertThat().statusCode(200);
        response.prettyPrint();
    }
}
