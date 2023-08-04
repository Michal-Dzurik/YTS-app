package sk.dzurikm.yts.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.fragment.app.FragmentManager;

import java.util.List;

import sk.dzurikm.yts.models.Movie;
import sk.dzurikm.yts.views.MovieView;
import sk.dzurikm.yts.views.layouts.NonScrollableGridView;

public class MovieAdapter extends BaseAdapter {
    private Context context;
    private List<Movie> movies;
    private NonScrollableGridView gridView;
    private FragmentManager fragmentManager;

    public MovieAdapter(Context context, FragmentManager fragmentManager, List<Movie> movieList, NonScrollableGridView gridView) {
        this.context = context;
        this.movies = movieList;
        this.gridView = gridView;
        this.fragmentManager = fragmentManager;
    }

    @Override
    public int getCount() {
        return movies.size();
    }

    @Override
    public Object getItem(int position) {
        return movies.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MovieView movieView;

        // Set the data for the movie item in the view
        Movie movie = movies.get(position);

        if (convertView == null) {
            // Create a new instance of your custom view
            movieView = new MovieView(context,fragmentManager,movie);
        } else {
            // Reuse the existing view
            movieView = (MovieView) convertView;
        }

        movieView.setGridView(gridView);

        return movieView;
    }
}
