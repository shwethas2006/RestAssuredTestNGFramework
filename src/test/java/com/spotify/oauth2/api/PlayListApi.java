package com.spotify.oauth2.api;

import com.spotify.oauth2.pojo.Item;
import com.spotify.oauth2.pojo.Playlist;
import com.spotify.oauth2.utils.ConfigLoader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;

import static com.spotify.oauth2.api.Route.PLAY_LISTS;
import static com.spotify.oauth2.api.Route.USERS;
import static com.spotify.oauth2.api.SpecBuilder.getRequestSpec;
import static com.spotify.oauth2.api.SpecBuilder.getResponseSpec;
import static com.spotify.oauth2.api.TokenGenerator.getToken;
import static io.restassured.RestAssured.given;

public class PlayListApi {

    public static Response post(Item requestPlaylist){
        return RestResources.post(USERS +"/"+ ConfigLoader.getInstance().getUserId() + PLAY_LISTS,requestPlaylist);

    }

    public static Response get(String path){

        return RestResources.get(USERS + "/" + ConfigLoader.getInstance().getUserId() +PLAY_LISTS);

    }

    public static Response put(String path, Item requestPlaylist ){
       return RestResources.put(path,requestPlaylist);

    }

    public static Response invalid_token(Item requestPlaylist){
        return  given(getRequestSpec()).
                auth().oauth2("12345Rt").
                body(requestPlaylist).
                when().
                post("/users/31jobnyoknkpcorxnydnn7q7etmi/playlists").
                then().spec(getResponseSpec()).
                extract().response();

    }


}
