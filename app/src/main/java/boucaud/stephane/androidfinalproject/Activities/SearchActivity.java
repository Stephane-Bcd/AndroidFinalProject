package boucaud.stephane.androidfinalproject.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {

    // General parameters
    private String language = "fr";
    private String api_key;

    //For API
    private Controller controller;

    // View Objects
    private Spinner spinner_genres;
    private EditText SearchQuery;
    private RecyclerView moviesListRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private TextView textview_test;

    // Runtime parameters
    private String intent_typed_api_key;
    private String intent_default_api_key;

    private GenresList genresList;
    private String Selected_genre;
    private int Selected_genre_id;
    private int actual_page = 1;
    private List<Movie> actual_movies = new ArrayList<Movie>();


    /***
     * Fill with values a Spinner (Select list)
     * @param values List of Strings
     * @param spinner Spinner reference
     */
    private void fill_Spinner_Values(List<String> values, Spinner spinner, String value_default){
        values.add(0, "None");
        values.add(0, value_default);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, values);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
    }

    /**
     * Function to make a search query on movies
     * @param page
     * @param include_adult
     * @param query
     */
    private void searchMovies (int page, boolean include_adult, String query) {
        controller.querySearchMovies(page, include_adult, query, new Callback<MoviesList>() {
            public void onResponse(Call<MoviesList> call, Response<MoviesList> response) {
                if (response.isSuccessful()) {
                    MoviesList moviesList = response.body();
                    if (Selected_genre != "None" && Selected_genre != "Select Genre"){
                        //textview_test.setText(moviesList.getMovies(Selected_genre_id).toString());
                        actual_movies = moviesList.getMovies(Selected_genre_id);
                    }
                    else{
                        //textview_test.setText(moviesList.getMovies().toString());
                        actual_movies = moviesList.getMovies();
                    }

                    // specify an adapter (see also next example)
                    mAdapter = new SearchMoviesAdapter(actual_movies, api_key);
                    moviesListRecyclerView.setAdapter(mAdapter);

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
        setContentView(R.layout.activity_search);

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
        spinner_genres = (Spinner) findViewById(R.id.spinner_genres);
        textview_test = (TextView) findViewById(R.id.test);
        SearchQuery = findViewById(R.id.SearchQuery);
        moviesListRecyclerView = (RecyclerView) findViewById(R.id.moviesList);

        // Initialising display data

        controller = new Controller(api_key, language);

        // - Genres initiation
        controller.queryGetGenres(new Callback<GenresList>(){
            public void onResponse(Call<GenresList> call, Response<GenresList> response) {
                if(response.isSuccessful()) {
                    genresList = response.body();
                    fill_Spinner_Values(genresList.getStringList(), spinner_genres, "Select Genre");
                } else {
                    System.out.println(response.errorBody());
                }
            }
            public void onFailure(Call call, Throwable t) {
                t.printStackTrace();
            }
        });

        // - Recycler View for search result
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        moviesListRecyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        moviesListRecyclerView.setLayoutManager(layoutManager);


        // Listeners

        // - On Genres selection
        spinner_genres.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Selected_genre = (String) parent.getItemAtPosition(position);
                Selected_genre_id = genresList.searchGenreID(Selected_genre);
                searchMovies (actual_page, false, SearchQuery.getText().toString());
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        // - Movies Search bar typing
        SearchQuery.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                actual_page = 1;
                if (s.length() >= 0) {
                    searchMovies (actual_page, false, s.toString());
                } else {
                    searchMovies (actual_page, false, "");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });


    }
}
