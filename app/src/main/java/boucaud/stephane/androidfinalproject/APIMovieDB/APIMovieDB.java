package boucaud.stephane.androidfinalproject.APIMovieDB;

import java.util.List;

import boucaud.stephane.androidfinalproject.Models.Genre;
import boucaud.stephane.androidfinalproject.Models.GenresList;
import boucaud.stephane.androidfinalproject.Models.MoviesList;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/*
Every method of an interface represents one possible API call.
It must have a HTTP annotation (GET, POST, etc.) to specify the request type and the relative URL.
The return value wraps the response in a Call object with the type of the expected result.
*/
public interface APIMovieDB {

    @GET("genre/movie/list")
    Call<GenresList> getGenres(@Query("api_key") String api_key, @Query("language") String language);

    @GET("search/movie")
    Call<MoviesList> searchMovies(
            @Query("api_key") String api_key,
            @Query("language") String language,
            @Query("page") int page,
            @Query("include_adult") boolean include_adult,
            @Query("query") String query
    );

}
