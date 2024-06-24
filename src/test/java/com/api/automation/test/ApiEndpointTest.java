package com.api.automation.test;

import com.api.automation.listeners.ExtentReportListener;
import com.api.automation.models.builders.RequestBuilder;
import com.api.automation.reports.ExtentLogger;
import com.api.automation.utils.PropertyUtils;
import io.restassured.response.Response;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.SpecificationQuerier;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


import static com.api.automation.reports.ExtentLogger.*;
import static io.restassured.RestAssured.given;


@Listeners(ExtentReportListener.class)
public class ApiEndpointTest {
    private static String apiEndpoint;
    private static String apiKey;

    @BeforeClass
    public static void setup() throws Exception {
        apiEndpoint = PropertyUtils.getPropertyValue("base_api_uri");
        apiKey = PropertyUtils.getPropertyValue("api_key");
    }

    @Test(description = "API Endpoint Test")
    public void testApiEndpoint() {
        RequestSpecification requestSpecification = RequestBuilder.createRequestSpecification(apiEndpoint);
        QueryableRequestSpecification queryRequest = SpecificationQuerier.query(requestSpecification);
        Response response = given().
                spec(requestSpecification)
                .queryParam("access_token", apiKey)
                .when()
                .get("health")
                .then()
                .extract().response();

        logRequestInReport(queryRequest.getURI() + "/health?access_token=" + apiKey);
        logResponseInReport("API RESPONSE", response.asPrettyString());

        int statusCode = response.statusCode();
        String responseStatus = response.jsonPath().getString("status");

        if (statusCode != 200) {
            logFailureDetails("Expected status code to be 200 but is " + statusCode);
        } else {
            ExtentLogger.pass("API Status code is 200");
        }
        Assert.assertEquals(statusCode, 200, "Expected status code as 200 but is " + statusCode);


        if (!responseStatus.equals("OK")) {
            logFailureDetails("Expected status to be `OK` but is " + responseStatus);
        } else {
            ExtentLogger.pass("API Status is OK");
        }
        Assert.assertEquals(responseStatus, "OK", "Expected status to be `OK` but is " + responseStatus);
        // Assert the response body

    }
}
