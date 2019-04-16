package boucaud.stephane.androidfinalproject.Models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class GenresList {
    @SerializedName("genres")
    public List<Genre> genres;

    public List<String> getStringList(){
        int length = genres.size();
        int i;
        ArrayList<String> res = new ArrayList<String>();

        for (i = 0; i<length; i++){
            res.add(this.genres.get(i).genre_name);
        }

        return (List) res;
    }
}
