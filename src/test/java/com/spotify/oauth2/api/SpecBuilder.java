package com.spotify.oauth2.api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static com.spotify.oauth2.api.Route.BASE_URI;

public class SpecBuilder {

    public static RequestSpecification getRequestSpec(){

        return   new RequestSpecBuilder()
                .setBaseUri(System.getProperty("BASE_URI")).
                //.setBaseUri("https://api.spotify.com").
                setBasePath(BASE_URI).
               // addHeader("Authorization","Bearer "+ access_token).
                setContentType(ContentType.JSON).
                log(LogDetail.ALL).
                build();
    }

    public static RequestSpecification getAccountRequestSpec(){

        return   new RequestSpecBuilder()
                .setBaseUri(System.getProperty("ACCOUNTS_BASE_URI")).
                //.setBaseUri("https://accounts.spotify.com").
                setContentType(ContentType.URLENC).
                log(LogDetail.ALL).
                build();
    }

    public static ResponseSpecification getResponseSpec(){
        return new ResponseSpecBuilder().
                log(LogDetail.ALL).
                build();
    }
}
