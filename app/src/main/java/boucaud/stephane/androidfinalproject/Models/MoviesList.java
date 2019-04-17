package boucaud.stephane.androidfinalproject.Models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class MoviesList {

    @SerializedName("results")
    private List<Movie> movies;

    public List<Movie> getMovies() {
        return movies;
    }

    public List<String> getStringTitlesList(){
        int length = movies.size();
        int i;
        ArrayList<String> res = new ArrayList<String>();

        for (i = 0; i<length; i++){
            res.add(this.movies.get(i).getTitle());
        }

        return (List) res;
    }

    @Override
    public String toString() {
        int length = movies.size();
        int i;
        String res = new String();

        for (i = 0; i<length; i++){
            res += "- " + this.movies.get(i).toString() + "\n";
        }

        return (String) res;
    }
}
