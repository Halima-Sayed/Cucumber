package APIStepDef;

import io.cucumber.java.en.Given;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GenerateTokenStep {
    String baseURI= RestAssured.baseURI="http://hrm.syntaxtechs.net/syntaxapi/api";
    public static String token;


    @Given("a JWT is generated")
    public void a_jwt_is_generated() {
        //here we write the code to generate JWT token
        RequestSpecification request=given().header("Content-Type","application/json")
                .body("{\n" +
                        "  \"email\": \"admin@halima.com\",\n" +
                        "  \"password\": \"Test@1234\"\n" +
                        "}");
        Response response=request.when().post("/generateToken.php");
        response.prettyPrint();
        token="Bearer "+response.jsonPath().getString("token");
        System.out.println(token);
    }
}
