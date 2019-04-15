package boucaud.stephane.androidfinalproject.Controllers;

import java.util.List;

import boucaud.stephane.androidfinalproject.Models.Genre;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * API for Genres.
 */
public class ControllerGetGenres extends Controller implements Callback<List<Genre>> {
    public ControllerGetGenres(){
        super();
        Call<List<Genre>> call = this.API.getGenres();
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<List<Genre>> call, Response<List<Genre>> response) {
        if(response.isSuccessful()) {
            List<Genre> genresList = response.body();
            genresList.forEach(genre -> System.out.println(genre.genre_name));
        } else {
            System.out.println(response.errorBody());
        }
    }

    @Override
    public void onFailure(Call call, Throwable t) {
        t.printStackTrace();
    }
}