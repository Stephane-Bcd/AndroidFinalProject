package boucaud.stephane.androidfinalproject.Models;

import com.google.gson.annotations.SerializedName;

public class Genre {

    @SerializedName("id")
    public String genre_id;

    @SerializedName("name")
    public String genre_name;

    @Override
    public String toString() {
        return("Genre: {genre_id: " + this.genre_id + ", genre_name: " + genre_name + "}");
    }
}