package boucaud.stephane.androidfinalproject.APIMovieDB;

import java.util.List;

import boucaud.stephane.androidfinalproject.Models.Genre;
import retrofit2.Call;
import retrofit2.http.GET;

/*
Every method of an interface represents one possible API call.
It must have a HTTP annotation (GET, POST, etc.) to specify the request type and the relative URL.
The return value wraps the response in a Call object with the type of the expected result.
*/
public interface APIMovieDB {

    @GET("genre/movie/list")
    Call<List<Genre>> getGenres();


}
