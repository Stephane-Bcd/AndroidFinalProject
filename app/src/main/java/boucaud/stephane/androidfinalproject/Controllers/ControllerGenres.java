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
public class ControllerGenres extends Controller {

    public ControllerGenres(String API_KEY, String LANG){
        super(API_KEY, LANG);
    }

    public void queryGetGenres(Callback actions){
        Call<GenresList> call = super.getAPI().getGenres(super.getAPI_KEY(), super.getLANG());
        call.enqueue(actions);
    }
}