package boucaud.stephane.androidfinalproject.Controllers;

import java.util.List;

import boucaud.stephane.androidfinalproject.Models.Genre;
import boucaud.stephane.androidfinalproject.Models.GenresList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * API for Genres.
 */
public class ControllerGetGenres extends Controller implements Callback<GenresList> {

    public ControllerGetGenres(String api_key, String language){
        super();
        Call<GenresList> call = this.API.getGenres(api_key, language);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<GenresList> call, Response<GenresList> response) {
        if(response.isSuccessful()) {
            GenresList genresList = response.body();
            genresList.genres.forEach(genre -> System.out.println(genre.genre_name));
        } else {
            System.out.println(response.errorBody());
        }
    }

    @Override
    public void onFailure(Call call, Throwable t) {
        t.printStackTrace();
    }
}