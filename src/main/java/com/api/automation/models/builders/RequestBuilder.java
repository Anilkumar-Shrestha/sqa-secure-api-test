package com.api.automation.models.builders;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.specification.RequestSpecification;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.maven.surefire.shared.io.output.WriterOutputStream;


import java.io.PrintStream;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;

import static io.restassured.RestAssured.given;
import static io.restassured.filter.log.LogDetail.ALL;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class RequestBuilder {

    /**
     * RequestSpecification is an Interface and RequestSpecBuilder is a class
     *
     * @return
     */
    public static RequestSpecification createRequestSpecification(String apiEndpoint) {
        PrintStream printStream = new PrintStream(new WriterOutputStream(new StringWriter(), StandardCharsets.UTF_8), true);
        return new RequestSpecBuilder()
                .setBaseUri(apiEndpoint)
                .addFilter(new RequestLoggingFilter(printStream))
                .log(ALL)
                .build();
    }

    public static RequestSpecification buildRequest(String apiEndpoint) {
        return given().baseUri(apiEndpoint)
                .header("Content-Type", "application/json");
    }
}
