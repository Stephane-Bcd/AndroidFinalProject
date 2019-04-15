package boucaud.stephane.androidfinalproject.Controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import boucaud.stephane.androidfinalproject.APIMovieDB.APIMovieDB;
import boucaud.stephane.androidfinalproject.Models.Genre;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * This is an abstract class because it will be implemented for different requests types.
 * We can consider this abstract class as the API for APIMovieDB
 */
public abstract class Controller{

    static final String BASE_URL = "https://api.themoviedb.org/3/";

    APIMovieDB API;

    public Controller() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        API = retrofit.create(APIMovieDB.class);
    }
}
