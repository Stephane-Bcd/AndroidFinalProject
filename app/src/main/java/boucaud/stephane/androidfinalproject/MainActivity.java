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
import boucaud.stephane.androidfinalproject.Controllers.ControllerGetGenres;

public class MainActivity extends AppCompatActivity {

    // General parameters
    String language = "fr";

    // View Objects
    Spinner spinner_genres;

    TextView textview_selected_genre;

    // Runtime parameters
    String Selected_genre;


    /***
     * Fill with values a Spinner (Select list)
     * @param values List of Strings
     * @param spinner Spinner reference
     */
    private void fill_Spinner_Values(List<String> values, Spinner spinner){
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

        // Initialising display data
        // Genres selection:

        Controller controller = new ControllerGetGenres();

        List<String> list = new ArrayList<String>();
        list.add("list 1");
        list.add("list 2");
        list.add("list 3");

        fill_Spinner_Values(list, spinner_genres);


        spinner_genres.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Selected_genre = (String) parent.getItemAtPosition(position);
                //For tests:
                textview_selected_genre.setText("Selected genre: " + Selected_genre);
                //textview_selected_genre.setVisibility(View.GONE);
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }
}
