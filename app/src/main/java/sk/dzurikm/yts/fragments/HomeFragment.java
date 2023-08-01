package sk.dzurikm.yts.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;

import sk.dzurikm.yts.R;
import sk.dzurikm.yts.adapters.MovieAdapter;
import sk.dzurikm.yts.constants.ApiHelper;
import sk.dzurikm.yts.constants.ApiMap;
import sk.dzurikm.yts.constants.RequestParameters;
import sk.dzurikm.yts.constants.ResponseType;
import sk.dzurikm.yts.constants.YtsUrlBuilder;
import sk.dzurikm.yts.models.observable.HideLoadingModel;
import sk.dzurikm.yts.models.Movie;
import sk.dzurikm.yts.views.layouts.NonScrollableGridView;

public class HomeFragment extends Fragment {
    NonScrollableGridView gridView;
    Context context;
    HideLoadingModel hideLoadingModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        context = HomeFragment.this.getContext();

        gridView = root.findViewById(R.id.gridView);

        listFeaturedMovies();

        return root;
    }

    private void listFeaturedMovies() {
        RequestParameters featuredMoviesParameters = new RequestParameters();

        hideLoadingModel = new ViewModelProvider(requireActivity()).get(HideLoadingModel.class);

        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);

        featuredMoviesParameters.add("limit","4");
        featuredMoviesParameters.add("sort_by","download_count");
        featuredMoviesParameters.add("order_by","DESC");
        featuredMoviesParameters.add("query_term","" + currentYear);

        String featuredMoviesUrl = new YtsUrlBuilder(ResponseType.JSON, ApiMap.MOVIES_LIST)
                .setParameters(featuredMoviesParameters)
                        .getUrl();

        Log.d("REQUEST URL",featuredMoviesUrl);

        RequestQueue queue = Volley.newRequestQueue(context);
        JsonObjectRequest request = new JsonObjectRequest(featuredMoviesUrl, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if (null != response) {
                            ArrayList<Movie> movies = ApiHelper.getMovies(response);
                            displayFeaturedMovies(movies);

                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("RESPONSE ERROR",error.getMessage());
            }
        });
        queue.add(request);
    }


    private void displayFeaturedMovies(ArrayList<Movie> movies){
        gridView.setAdapter(new MovieAdapter(context,movies,gridView));
        hideLoadingModel.selectItem(true);
    }
}