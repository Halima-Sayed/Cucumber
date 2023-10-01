package utils;

import org.json.JSONObject;

public class APIPayloadConstants {
    public static String createEmployeePayload(){
        String createEmployeePayload="{\n" +
                "  \"emp_firstname\": \"hi\",\n" +
                "  \"emp_lastname\": \"bye\",\n" +
                "  \"emp_middle_name\": \"ms\",\n" +
                "  \"emp_gender\": \"F\",\n" +
                "  \"emp_birthday\": \"2000-07-21\",\n" +
                "  \"emp_status\": \"what\",\n" +
                "  \"emp_job_title\": \"laziness\"\n" +
                "}";

        return createEmployeePayload;
    }

    public static String createEmployeeJsonPayload(){
        JSONObject obj=new JSONObject();
        obj.put("emp_firstname","hi");
        obj.put("emp_lastname","bye");
        obj.put("emp_middle_name","ms");
        obj.put("emp_gender","F");
        obj.put("emp_birthday","2000-07-21");
        obj.put("emp_status","what");
        obj.put("emp_job_title","laziness");
        //convert json object into string
        return obj.toString();
    }
}
