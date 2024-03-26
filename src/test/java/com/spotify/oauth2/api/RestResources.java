package com.spotify.oauth2.api;

import com.spotify.oauth2.pojo.Item;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;

import static com.spotify.oauth2.api.Route.API;
import static com.spotify.oauth2.api.Route.TOKEN;
import static com.spotify.oauth2.api.SpecBuilder.*;
import static com.spotify.oauth2.api.TokenGenerator.getToken;
import static io.restassured.RestAssured.given;

public class RestResources {


    public static Response post(String path,Object requestPlaylist){
        return  given(getRequestSpec()).
                body(requestPlaylist).
                auth().oauth2(getToken()).
                when().
                post(path).
                then().spec(getResponseSpec()).
                extract().response();

    }

    public static Response get(String path ){
        return     given(getRequestSpec()).
                auth().oauth2(getToken()).
                when().
                get(path).
                then().spec(getResponseSpec()).
                extract().response();

    }

    public static Response put(String path,Object requestPlaylist ){
        return      given(getRequestSpec()).
                auth().oauth2(getToken()).
                body(requestPlaylist).
                when().
                put(path).
                then().spec(getResponseSpec()).
                extract().response();

    }

    public static Response invalid_token(Object requestPlaylist){
        return  given().
                auth().oauth2("getTo1345$").
                body(requestPlaylist).
                when().
                post("/users/31jobnyoknkpcorxnydnn7q7etmi/playlists").
                then().spec(getResponseSpec()).
                extract().response();

    }

    public static Response postAccount(HashMap<String,String> formParams){
        return given(getAccountRequestSpec()).
                formParams(formParams).
                when().post(API + TOKEN).
                then().spec(getResponseSpec()).extract().response();
    }


}
