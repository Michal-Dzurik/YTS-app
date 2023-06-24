package sk.dzurikm.yts.fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.util.LinkedList;
import java.util.List;

import sk.dzurikm.yts.R;
import sk.dzurikm.yts.adapters.MovieAdapter;
import sk.dzurikm.yts.models.Movie;
import sk.dzurikm.yts.views.layouts.NonScrollableGridView;

public class HomeFragment extends Fragment {

    NonScrollableGridView gridView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        gridView = root.findViewById(R.id.gridView);

        List<Movie> list = new LinkedList<Movie>();
        list.add(new Movie("https://img.yts.mx/assets/images/movies/the_angry_black_girl_and_her_monster_2023/medium-cover.jpg","The Angry Black Girl and Her Monster","2023"));
        list.add(new Movie("https://img.yts.mx/assets/images/movies/talking_walls_1987/medium-cover.jpg","Talking Walls","1987"));
        list.add(new Movie("https://img.yts.mx/assets/images/movies/bounty_hunter_in_trinity_1972/medium-cover.jpg","Bounty Hunter in Trinity","1972"));
        list.add(new Movie("https://img.yts.mx/assets/images/movies/maximum_truth_2023/medium-cover.jpg","Maximum Truth","2023"));

        gridView.setAdapter(new MovieAdapter(HomeFragment.this.getContext(),list,gridView));

        return root;
    }
}