package utils;

import io.restassured.RestAssured;

public class APIConstants {

    public static final String baseURI= RestAssured.baseURI="http://hrm.syntaxtechs.net/syntaxapi/api";

    //ENDPOINT
    public static final String CREATE_EMPLOYEE_URI=baseURI+"/createEmployee.php";
    public static final String GET_ONE_EMPLOYEE_URI = baseURI+"/getOneEmployee.php";
    public static final String UPDATE_EMPLOYEE_URI = baseURI+"/updateEmployee.php";
    public static final String GENERATE_TOKEN_URI = baseURI+"/generateToken.php";
    public static final String CREATE_ADMIN_URI=baseURI+"/createUser.php";
    public static final String GET_ALL_EMPLOYEES=baseURI+"/getAllEmployees.php";
    public static final String GET_JOB_TITLE=baseURI+"/jobTitle.php";
    public static final String PARTIALLY_UPDATE_EMPLOYEE=baseURI+"/updatePartialEmplyeesDetails.php";
    public static final String GET_EMPLOYEE_STATUS=baseURI+"/employeementStatus.php";

    //HEADERS

    public static final String HEADER_CONTENT_TYPE_KEY = "Content-Type";
    public static final String HEADER_CONTENT_TYPE_VALUE = "application/json";
    public static final String HEADER_AUTHORIZATION_KEY = "Authorization";
}
