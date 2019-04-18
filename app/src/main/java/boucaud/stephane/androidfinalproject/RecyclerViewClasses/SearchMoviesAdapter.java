package boucaud.stephane.androidfinalproject.RecyclerViewClasses;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import boucaud.stephane.androidfinalproject.Models.Movie;
import boucaud.stephane.androidfinalproject.R;

public class SearchMoviesAdapter extends RecyclerView.Adapter<SearchMoviesViewHolder> {
    private List<Movie> movies;

    // Provide a suitable constructor (depends on the kind of dataset)
    public SearchMoviesAdapter(List<Movie> movies) {
        this.movies = movies;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public SearchMoviesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_search_movies, parent, false);

        return new SearchMoviesViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(SearchMoviesViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.bind(movies.get(position));

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return movies.size();
    }
}
