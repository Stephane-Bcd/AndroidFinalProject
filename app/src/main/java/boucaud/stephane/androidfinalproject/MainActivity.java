package boucaud.stephane.androidfinalproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import boucaud.stephane.androidfinalproject.Controllers.Controller;
import boucaud.stephane.androidfinalproject.Controllers.ControllerGenres;
import boucaud.stephane.androidfinalproject.Models.GenresList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    // General parameters
    String language = "fr";
    String api_key = "8e894528fa9c319948a48ce050f28657";

    // View Objects
    Spinner spinner_genres;

    TextView textview_selected_genre;
    TextView textview_test;

    // Runtime parameters
    String Selected_genre;


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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Getting all objects from View
        spinner_genres = (Spinner) findViewById(R.id.spinner_genres);
        textview_selected_genre = (TextView) findViewById(R.id.spinner_genre_selected);
        textview_test = (TextView) findViewById(R.id.test);

        // Initialising display data

        // - Genres initiation
        ControllerGenres controller = new ControllerGenres(api_key, language);
        controller.queryGetGenres(new Callback<GenresList>(){
            public void onResponse(Call<GenresList> call, Response<GenresList> response) {
                if(response.isSuccessful()) {
                    GenresList genresList = response.body();
                    fill_Spinner_Values(genresList.getStringList(), spinner_genres, "Select Genre");
                } else {
                    System.out.println(response.errorBody());
                }
            }
            public void onFailure(Call call, Throwable t) {
                t.printStackTrace();
            }
        });


        // Listeners

        // - On Genres selection
        spinner_genres.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Selected_genre = (String) parent.getItemAtPosition(position);
                //For tests:
                textview_selected_genre.setText("Selected genre: " + Selected_genre);
                textview_selected_genre.setVisibility(View.GONE);
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }
}
