package boucaud.stephane.androidfinalproject.RecyclerViewClasses;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import boucaud.stephane.androidfinalproject.Models.Movie;
import boucaud.stephane.androidfinalproject.R;

public class SearchMoviesViewHolder extends RecyclerView.ViewHolder {
    private TextView title;
    private TextView description;
    private ImageView thumbnail;

    public SearchMoviesViewHolder(@NonNull View itemView) {
        super(itemView);
        this.title = itemView.findViewById(R.id.title);
        this.description = itemView.findViewById(R.id.description);
        this.thumbnail = itemView.findViewById(R.id.thumbnail);
    }

    public void bind(final Movie movie) {
        title.setText(movie.getTitle());
        String new_description = movie.getOverview();
        if (new_description.length() >= 100){
            new_description = new_description.substring(0,100) + "...";
        }
        description.setText(new_description);

        Glide.with(itemView).load(movie.getPosterFullPath()).into(thumbnail);

        // Create Listener for each ViewHolder, then if we click, we can see video details
        /*itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DetailActivity.start(v.getContext(), youTubeSearchItem.getId().getVideoId());
            }
        });*/
    }
}
