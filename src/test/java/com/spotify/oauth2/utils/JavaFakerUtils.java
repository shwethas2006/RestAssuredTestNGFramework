package com.spotify.oauth2.utils;

import com.github.javafaker.Faker;

public class JavaFakerUtils {

    public static String generateName(){
        Faker faker = new Faker();
        return "Playlist"  + faker.regexify("[A-Za-z0-9]{10}");
    }

    public static String generateDescription(){
        Faker faker = new Faker();
        return "Playlist"  + faker.regexify("[A-Za-z0-9 ,_*3:-]{30}");
    }
}
