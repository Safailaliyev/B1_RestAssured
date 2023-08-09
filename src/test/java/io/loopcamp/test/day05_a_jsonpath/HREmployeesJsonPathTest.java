package io.loopcamp.test.day05_a_jsonpath;
import io.loopcamp.util.ConfigurationReader;
import io.loopcamp.util.HRApiTestBase;
import io.loopcamp.util.JsonPlaceHolder;
import io.loopcamp.util.MinionTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static io.restassured.RestAssured.*;

public class HREmployeesJsonPathTest extends HRApiTestBase {

    @DisplayName("GET/employees?limit=10 -json filters")
    @Test
    public void jsonPathEmployeesTest(){
        Response response = given().accept(ContentType.JSON)
                .and().queryParam("limit", "200")
                .when().get("/employees");

        assertEquals(200,response.statusCode());

        //convert/parse the Response to JsonPath
        JsonPath jsonPath = response.jsonPath();
        System.out.println("First name of 1st emp: " + jsonPath.getString("items.first_name[0]"));
        System.out.println("ID of 1st emp: " + jsonPath.getString("items.employee_id[0]"));

        //how to get all employees EMAIL
        List<String> emailLIst = jsonPath.getList("items.email");
        System.out.println("All Emails: " + emailLIst);

        //Verify if Tgates is among the email options
        assertTrue(emailLIst.contains("JSEO"));

        //get all employees first name who work for department_id 90
        //Select first_name FROM employees WHERE department_id = 90;
        //We can get all list and apply the filter on the response with the help of JsonPath

        List<String> namesAtDep90 = jsonPath.getList("items.findAll{it.department_id==90}.first_name");
        System.out.println("NAmes,  who work at department ID-90: " +namesAtDep90);

        //get all employee first names who work as IT_PROG

        List<String> itProg = jsonPath.getList("items.findAll{it.job_id=='IT_PROG'}.first_name");
        System.out.println("IT Prog job id employees: " + itProg);


        //get all employee first names whose salary is more than or equal 5000
        List<String> salary = jsonPath.getList("items.findAll{it.salary>=5000}.first_name");
        System.out.println("Emp with salary more than or equal to 5000: " + salary );

        //get emp first name who has max salary
        String maxSalary = jsonPath.getString("items.max{it.salary}.first_name");
        System.out.println("Maximum Salary: " + maxSalary);

        //get min salary
        int minSalary = jsonPath.getInt("items.min{it.salary}.salary");
        System.out.println("Minimum salary: " + minSalary);

        //get emp first name who has min salary
        String minSalaryFirstName = jsonPath.getString("items.min{it.salary}.first_name");
        System.out.println("First name with minimum salary: " + minSalaryFirstName);







    }


}
