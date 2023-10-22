package sk.dzurikm.yts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.ArrayList;

import sk.dzurikm.yts.adapters.MovieAdapter;
import sk.dzurikm.yts.adapters.PaginationAdapter;
import sk.dzurikm.yts.constants.ApiHelper;
import sk.dzurikm.yts.constants.ApiMap;
import sk.dzurikm.yts.constants.RequestParameters;
import sk.dzurikm.yts.constants.ResponseType;
import sk.dzurikm.yts.constants.YtsUrlBuilder;
import sk.dzurikm.yts.helpers.Animations;
import sk.dzurikm.yts.helpers.Time;
import sk.dzurikm.yts.models.FilterBundle;
import sk.dzurikm.yts.models.Movie;
import sk.dzurikm.yts.models.Pagination;
import sk.dzurikm.yts.models.callbacks.Callback;
import sk.dzurikm.yts.views.PaginationView;
import sk.dzurikm.yts.views.dialogs.FilterDialog;

public class SearchActivity extends AppCompatActivity {
    GridView foundMoviesGridView;
    PaginationView paginationView;
    EditText searchTerm;
    ImageButton filterButton;
    LinearLayout emptySearch;

    FilterDialog filterDialog;
    FilterBundle filtersToApply;
    Pagination pagination;

    boolean firstSearch,wasLastSearchSuccessful;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        init();
    }
    
    private void init(){
        firstSearch = true;
        wasLastSearchSuccessful = false;

        hideActionBar();
        changeStatusBarColor();
        setUpViews();
        setUpEvents();
    }

    private void hideActionBar(){
        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}
    }

    private void changeStatusBarColor(){
        Window window = SearchActivity.this.getWindow();

        // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        // finally change the color
        window.setStatusBarColor(ContextCompat.getColor(SearchActivity.this,R.color.main_background_dark));
    }

    private void setUpViews() {
        foundMoviesGridView = findViewById(R.id.foundMoviesGridView);
        paginationView = findViewById(R.id.paginationNumbers);

        searchTerm = findViewById(R.id.searchTerm);
        filterButton = findViewById(R.id.filterButton);

        emptySearch = findViewById(R.id.emptySearch);

        foundMoviesGridView.setVerticalScrollBarEnabled(false);
    }

    private void setUpEvents(){
        searchTerm.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                if ((keyEvent.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    // Perform action on key press
                    String searchTermString = searchTerm.getText().toString();
                    if (searchTermString.trim().length() >= 4){
                        searchForMovies(searchTermString);
                        return true;
                    }

                    Toast.makeText(SearchActivity.this, SearchActivity.this.getText(R.string.minimu_search_term_message),Toast.LENGTH_SHORT).show();

                    return false;
                }

                return false;
            }
        });

        filterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFilterDialog();
            }
        });

        paginationView.setOnPageChangeListener(new PaginationView.OnPageChangeListener() {
            @Override
            public void onPageChange(Pagination newPagination) {
                pagination = newPagination;
                searchForMovies(searchTerm.getText().toString());
            }
        });
    }

    private void searchForMovies(String movie){
        String requestURL = getRequestURL(movie);

        Log.d("REQUEST URL",requestURL);

        RequestQueue queue = Volley.newRequestQueue(SearchActivity.this);
        JsonObjectRequest request = new JsonObjectRequest(requestURL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if (null != response) {
                            Log.d("RESPOS",response.toString());
                            pagination = Pagination.create(response);
                            ArrayList<Movie> movies = ApiHelper.getMovies(response);
                            if (movies != null){
                                displayMovies(movies);
                            }
                            else displayMovies(new ArrayList<Movie>());

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

    private String getRequestURL(String searchTerm){
        RequestParameters featuredMoviesParameters = new RequestParameters();

        featuredMoviesParameters.add("limit","20");
        featuredMoviesParameters.add("query_term",searchTerm.trim());
        if(filtersToApply != null){
            filtersToApply.applyFilters(featuredMoviesParameters);
        }
        if (pagination != null){
            featuredMoviesParameters.add("page", String.valueOf(pagination.pageNumber));
        }

        return new YtsUrlBuilder(ResponseType.JSON, ApiMap.MOVIES_LIST)
                .setParameters(featuredMoviesParameters)
                .getUrl();
    }

    private void displayMovies(ArrayList<Movie> movies){
        boolean wasRequestSuccessful = movies.size() != 0;
        if (!wasRequestSuccessful){
            Time.wait(new Callback() {
                @Override
                public Object callback() {
                    Animations.fadeIn(emptySearch);;

                    return null;
                }
            },Time.DEFAULT_TIME_DURATION);

            // Pagination update
            Animations.fadeOut(paginationView);
            Animations.fadeOut(foundMoviesGridView);
        }
        else{
            if (wasLastSearchSuccessful){
                Animations.fadeOut(foundMoviesGridView,false);
                Animations.fadeOut(paginationView,false);

                Time.wait(new Callback() {
                    @Override
                    public Object callback() {
                        foundMoviesGridView.setAdapter(new MovieAdapter(SearchActivity.this,getSupportFragmentManager(),movies));
                        Animations.fadeIn(foundMoviesGridView);

                        // Pagination update
                        Animations.fadeIn(paginationView);
                        return null;
                    }
                },Time.DEFAULT_TIME_DURATION);
            }
            else if (!firstSearch){
                Animations.fadeOut(emptySearch);

                foundMoviesGridView.setAdapter(new MovieAdapter(SearchActivity.this,getSupportFragmentManager(),movies));

                Time.wait(new Callback() {
                    @Override
                    public Object callback() {
                        Animations.fadeIn(foundMoviesGridView);

                        // Pagination update
                        Animations.fadeIn(paginationView);
                        return null;
                    }
                },Time.DEFAULT_TIME_DURATION);
            }
            else {
                foundMoviesGridView.setAdapter(new MovieAdapter(SearchActivity.this,getSupportFragmentManager(),movies));

                Animations.fadeIn(foundMoviesGridView);

                // Pagination update
                Animations.fadeIn(paginationView);

                firstSearch = false;
            }


        }

        wasLastSearchSuccessful = wasRequestSuccessful;

        updatePagination();

    }

    private void updatePagination(){
        if (pagination.resultsCount != 0) {
            paginationView.setData(pagination);
        }
    }

    private void showFilterDialog(){
        if (filtersToApply == null){
            filtersToApply = new FilterBundle();
        }
        if(filterDialog == null){
            filterDialog = new FilterDialog(SearchActivity.this, SearchActivity.this.getSupportFragmentManager(), filtersToApply, new FilterDialog.OnDialogDismissListener() {
                @Override
                public void onDismiss(FilterBundle filterBundle) {
                    filtersToApply = filterBundle;
                    applyFilters();
                }
            });
        }
        filterDialog.show(SearchActivity.this.getSupportFragmentManager(),"FilterDialog");

    }

    private void applyFilters(){
        if (filtersToApply.getCountOfUsedFilters() != 0){
            ColorStateList colorStateList = ColorStateList.valueOf(ContextCompat.getColor(this, R.color.yts_green));
            filterButton.setImageTintList(colorStateList);
        }
        else {
            ColorStateList colorStateList = ColorStateList.valueOf(ContextCompat.getColor(this, R.color.white));
            filterButton.setImageTintList(colorStateList);
        }

        if (foundMoviesGridView.getAdapter().getCount() != 0){
            searchForMovies(searchTerm.getText().toString());
        }
    }

}