package boucaud.stephane.androidfinalproject.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import boucaud.stephane.androidfinalproject.Controllers.Controller;
import boucaud.stephane.androidfinalproject.Models.GenresList;
import boucaud.stephane.androidfinalproject.Models.Movie;
import boucaud.stephane.androidfinalproject.Models.MoviesList;
import boucaud.stephane.androidfinalproject.R;
import boucaud.stephane.androidfinalproject.RecyclerViewClasses.SearchMoviesAdapter;
import boucaud.stephane.androidfinalproject.RecyclerViewClasses.TrendsMoviesAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TrendsActivity extends AppCompatActivity {

    // General parameters
    private String language = "fr";
    private String api_key;

    //For API
    private Controller controller;

    // View Objects
    private RecyclerView moviesListRecyclerView1;
    private RecyclerView moviesListRecyclerView2;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager1;
    private RecyclerView.LayoutManager layoutManager2;

    // Runtime parameters
    private String intent_typed_api_key;
    private String intent_default_api_key;

    private int actual_page = 1;
    private List<Movie> actual_movies = new ArrayList<Movie>();



    private void getMovieTrends(String period, RecyclerView rv){
        controller.queryMovieTrends(period, new Callback<MoviesList>(){
            public void onResponse(Call<MoviesList> call, Response<MoviesList> response) {
                if(response.isSuccessful()) {
                    MoviesList moviesList = response.body();
                    actual_movies = moviesList.getMovies();

                    // specify an adapter
                    mAdapter = new TrendsMoviesAdapter(actual_movies, api_key);
                    rv.setAdapter(mAdapter);
                } else {
                    System.out.println(response.errorBody());
                }
            }
            public void onFailure(Call call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trends);

        // Get Intent data
        intent_default_api_key = getIntent().getStringExtra("default_api_key");
        intent_typed_api_key = getIntent().getStringExtra("typed_api_key");
        if (intent_typed_api_key != null && ! intent_typed_api_key.equals(new String(""))){
            api_key = intent_typed_api_key;
        }
        else{
            api_key = intent_default_api_key;
        }

        // Getting all objects from View
        moviesListRecyclerView1 = (RecyclerView) findViewById(R.id.moviesList);
        moviesListRecyclerView2 = (RecyclerView) findViewById(R.id.moviesList2);

        // Initialising display data

        controller = new Controller(api_key, language);

        // - Recycler Views for movies trends
        layoutManager1 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        moviesListRecyclerView1.setLayoutManager(layoutManager1);
        layoutManager2 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        moviesListRecyclerView2.setLayoutManager(layoutManager2);

        // - Filling recycler views
        getMovieTrends("day", moviesListRecyclerView1);
        getMovieTrends("week", moviesListRecyclerView2);
    }
}
