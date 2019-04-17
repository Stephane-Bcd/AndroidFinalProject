package boucaud.stephane.androidfinalproject.Controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import boucaud.stephane.androidfinalproject.APIMovieDB.APIMovieDB;
import boucaud.stephane.androidfinalproject.Models.Genre;
import boucaud.stephane.androidfinalproject.Models.GenresList;
import boucaud.stephane.androidfinalproject.Models.MoviesList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * This is an abstract class because it will be implemented for different requests types.
 * We can consider this abstract class as the API for APIMovieDB
 */
public class Controller implements ControllerInterface{

    private static final String BASE_URL = "https://api.themoviedb.org/3/";

    private APIMovieDB API;
    private String API_KEY;
    private String LANG;


    public Controller(String API_KEY, String LANG) {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        API = retrofit.create(APIMovieDB.class);

        this.API_KEY = API_KEY;
        this.LANG = LANG;
    }


    public void queryGetGenres(Callback actions){
        Call<GenresList> call = this.API.getGenres(this.API_KEY, this.LANG);
        call.enqueue(actions);
    }

    public void querySearchMovies(int page, boolean include_adult, String query, Callback actions){
        Call<MoviesList> call = this.API.searchMovies(this.API_KEY, this.LANG, page, include_adult, query);
        call.enqueue(actions);
    }





    public String getAPI_KEY() {
        return API_KEY;
    }

    public void setAPI_KEY(String API_KEY) {
        this.API_KEY = API_KEY;
    }

    public String getLANG() {
        return LANG;
    }

    public void setLANG(String LANG) {
        this.LANG = LANG;
    }

}
