package com.spotify.oauth2.tests;

import com.spotify.oauth2.api.PlayListApi;
import com.spotify.oauth2.api.StatusCodes;
import com.spotify.oauth2.pojo.Item;
import com.spotify.oauth2.utils.DataLoader;
import io.qameta.allure.Epic;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static com.spotify.oauth2.utils.JavaFakerUtils.generateDescription;
import static com.spotify.oauth2.utils.JavaFakerUtils.generateName;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@Epic("Spotify OAuth2.0 Tests")
public class PlaylistTests extends BaseTest {


    public Item requestPlaylistBuilder(String name,String description,boolean _public){
        return Item.builder().
                name(name)
                .description(description).
                _public(_public).build();
    }

    public void assertPlaylistItems(Item requestPlaylist,Item responsePlaylist){
        assertThat(responsePlaylist.getName(),equalTo(requestPlaylist.getName()));
        assertThat(responsePlaylist.getDescription(),equalTo(requestPlaylist.getDescription()));
        assertThat(responsePlaylist.get_public(),equalTo(requestPlaylist.get_public()));
    }



    @Test(description = "Test to create new playlist")
    public void create_playlist(){

        Item requestPlaylist = requestPlaylistBuilder(generateName(),generateDescription(),false);
        Response response = PlayListApi.post(requestPlaylist);
        Item responsePlaylist = response.as(Item.class);
        assertPlaylistItems(responsePlaylist,requestPlaylist);

    }

    @Test(description = "Test to get all the available playlists")
    public void get_playlist(){
        Item requestPlaylist = requestPlaylistBuilder(generateName(),generateDescription(),false);
        Response response = PlayListApi.get(DataLoader.getInstance().getPlaylistId());
        Item updatedPlaylist = response.as(Item.class);
        assertThat(response.getStatusCode(),equalTo(StatusCodes.CODE_200.getCode()));
    }


    @Test(description = "Test to update the existing Playlists")
    public void update_playlist(){

        Item requestPlaylist = requestPlaylistBuilder(generateName(),generateDescription(),false);
        Response response = PlayListApi.put("/playlists/3aQm1XYyu34DKjI6QwEa73",requestPlaylist);
        assertThat(response.getStatusCode(),equalTo(StatusCodes.CODE_200.getCode()));
    }

    @Test (description = "Negative test to validate error code")
    public void Should_not_create_playlist(){
        Item requestPlaylist = requestPlaylistBuilder(generateName(),generateDescription(),false);
        Response response = PlayListApi.invalid_token(requestPlaylist);
        assertThat(response.getStatusCode(),equalTo(StatusCodes.CODE_401.getCode()));
    }


}
