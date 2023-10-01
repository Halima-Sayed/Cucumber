package APIStepDef;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import utils.APIConstants;
import utils.APIPayloadConstants;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.hamcrest.Matchers.*;


import static io.restassured.RestAssured.given;

public class APIWorkFlowSteps {
    RequestSpecification request;
    Response response;
    public static String employee_id;

    @Given("a request is prepared for creating an employee")
    public void a_request_is_prepared_for_creating_an_employee() {
        /*request = given().header("Content-Type","application/json").header("Authorization", GenerateTokenStep.token).body("{\n" +
                "  \"emp_firstname\": \"hi\",\n" +
                "  \"emp_lastname\": \"bye\",\n" +
                "  \"emp_middle_name\": \"ms\",\n" +
                "  \"emp_gender\": \"F\",\n" +
                "  \"emp_birthday\": \"2000-07-21\",\n" +
                "  \"emp_status\": \"what\",\n" +
                "  \"emp_job_title\": \"laziness\"\n" +
                "}");
         */
        request = given().header(APIConstants.HEADER_CONTENT_TYPE_KEY,APIConstants.HEADER_CONTENT_TYPE_VALUE).header(APIConstants.HEADER_AUTHORIZATION_KEY, GenerateTokenStep.token)
                .body(APIPayloadConstants.createEmployeePayload());
    }
    @When("a POST call is made to create an employee")
    public void a_post_call_is_made_to_create_an_employee() {
         response = request.when().post(APIConstants.CREATE_EMPLOYEE_URI);
         response.prettyPrint();
    }
    @Then("the status code for creating an employee is {int}")
    public void the_status_code_for_creating_an_employee_is(int statusCode) {
        response.then().assertThat().statusCode(statusCode);
    }
    @Then("the employee created contains key {string} and value {string}")
    public void the_employee_created_contains_key_and_value(String key, String value) {
        response.then().assertThat().body(key,equalTo(value));
    }
    @Then("the employee id {string} is stored as a global variable")
    public void the_employee_id_is_stored_as_a_global_variable(String empID) {
        employee_id=response.jsonPath().getString(empID);
        System.out.println(employee_id);
    }
    //----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    @Given("a request is prepared for retrieving an employee")
    public void a_request_is_prepared_for_retrieving_an_employee() {
        request = given().header(APIConstants.HEADER_AUTHORIZATION_KEY,GenerateTokenStep.token)
                .queryParam("employee_id", employee_id);
    }
    @When("a GET call is made to retrieve the employee")
    public void a_get_call_is_made_to_retrieve_the_employee() {
        response = request.when().get(APIConstants.GET_ONE_EMPLOYEE_URI);
    }

    @Then("the status code for this employee is {int}")
    public void the_status_code_for_this_employee_is(int statusCode) {
        response.then().assertThat().statusCode(statusCode);
    }
    @Then("the employee id {string} must match with globally stored employee id")
    public void the_employee_id_must_match_with_globally_stored_employee_id(String empId) {
        String temporaryEmpId = response.jsonPath().getString(empId);
        Assert.assertEquals(employee_id, temporaryEmpId);
    }
    @Then("this employee data at {string} object matches with the data used to create the employee")
    public void this_employee_data_at_object_matches_with_the_data_used_to_create_the_employee(String employeeObject, io.cucumber.datatable.DataTable dataTable) {
        // Write code here that turns the phrase above into concrete actions
        List<Map<String,String>> expectedData=dataTable.asMaps();
        //since we need whole object we are calling .get method instead on ,getString
        Map<String,String> actualData=response.body().jsonPath().get(employeeObject);

        for (Map<String, String> map : expectedData) {
            //we use set to keep the order and to avoid duplicates. List allows duplicates and Set does not.
            //.keySet gets all keys
            Set<String> keys=map.keySet();
            //one key at one time that is why we use another for each loop
            for (String key : keys) {
                //from the key we will get value
                String expectedValue=map.get(key);
                String actualValue=actualData.get(key);
                Assert.assertEquals(expectedValue,actualValue);
            }
        }
    }
    @Given("a request is prepared for creating an employee using json payload")
    public void a_request_is_prepared_for_creating_an_employee_using_json_payload() {
        request = given().header(APIConstants.HEADER_CONTENT_TYPE_KEY,
                        APIConstants.HEADER_CONTENT_TYPE_VALUE).
                header(APIConstants.HEADER_AUTHORIZATION_KEY, GenerateTokenStep.token).
                body(APIPayloadConstants.createEmployeeJsonPayload());
    }


}
