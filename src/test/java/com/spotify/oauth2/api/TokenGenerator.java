package com.spotify.oauth2.api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.time.Instant;
import java.util.HashMap;

import static com.spotify.oauth2.api.RestResources.postAccount;
import static com.spotify.oauth2.api.SpecBuilder.getResponseSpec;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.responseSpecification;

public class TokenGenerator {
    private static String access_token;
    private static Instant expiry_time;

    public synchronized static String getToken(){
            try {
                if(access_token == null || Instant.now().isAfter(expiry_time)) {
                    System.out.println("Renewing Token...");
                    Response response = renewToken();
                    access_token = response.path("access_token");
                    int expiryDuration = response.path("expires_in");
                    expiry_time = Instant.now().plusSeconds(expiryDuration - 200);
                } else {
                    System.out.println("Token is good to use");
                }
            }
            catch(Exception e){
                throw new RuntimeException("ABORT!! Failed to generate valid Token");
            }
            return access_token;
        }
        private static Response renewToken(){
            HashMap<String,String> formParams = new HashMap<String,String>();
            formParams.put("client_id","0b078847a7cb4f59b406a9b8b1b3bd36");
            formParams.put("client_secret","816076cefd7d4b39ad4e1c47a52f7788");
            formParams.put("refresh_token",
                    "AQCjIfR8worC3-AMgzDPVkLbaMwlwtByC2rA6Av6qn2LxjI7JadmFP1cO6lfBY-88SbRydAk_WntSpufvlEimW0BxVAIE39GSFYpzNT6uw2prT6ZWYhfzYfEQNURMN1ZUXw");
            formParams.put("grant_type","refresh_token");

            Response response = postAccount(formParams);

            if(response.statusCode()!=200)
                throw new RuntimeException("ABORT!!! Renew Token failed");

            return response;

        }

    }

