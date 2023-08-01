package sk.dzurikm.yts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.Objects;

import sk.dzurikm.yts.constants.ApiMap;
import sk.dzurikm.yts.constants.RequestParameters;
import sk.dzurikm.yts.constants.ResponseType;
import sk.dzurikm.yts.constants.YtsUrlBuilder;
import sk.dzurikm.yts.fragments.BookmarksFragment;
import sk.dzurikm.yts.fragments.HomeFragment;
import sk.dzurikm.yts.fragments.SuggestionsFragment;
import sk.dzurikm.yts.helpers.Animations;
import sk.dzurikm.yts.helpers.Time;
import sk.dzurikm.yts.models.callbacks.Callback;
import sk.dzurikm.yts.models.observable.HideLoadingModel;
import sk.dzurikm.yts.views.LoadingView;

public class MainActivity extends AppCompatActivity {
    private ImageButton profileButton;
    private NavController navigationController;
    private BottomNavigationView bottomNavigationView;
    private HideLoadingModel hideLoadingModel;
    private LoadingView loadingView;

    HomeFragment homeFragment;
    SuggestionsFragment suggestionsFragment;
    BookmarksFragment bookmarksFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        String url = new YtsUrlBuilder(ResponseType.JSON)
                .setApiRoute(ApiMap.MOVIES_LIST)
                .setParameters(new RequestParameters())
                .getUrl();

        hideLoadingModel = new ViewModelProvider(this).get(HideLoadingModel.class);
        hideLoadingModel.getSelectedItem().observe(this, item -> {
            if (item) {
                Time.wait(new Callback() {
                    @Override
                    public Void callback() {
                        Animations.fadeOut(loadingView);
                        return null;
                    }
                });
            }
            else Animations.fadeIn(loadingView);
        });
    }

    private void init(){
        setUpViews();
        setUpFragments();
        setUpActionBar();
        setUpNavigation();
    }

    private void setUpViews() {
        loadingView = this.findViewById(R.id.loadingView);
    }

    private void setUpFragments() {
        homeFragment = new HomeFragment();
        suggestionsFragment = new SuggestionsFragment();
        bookmarksFragment = new BookmarksFragment();
    }

    private void setUpNavigation() {
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_nav  );
        replaceFragment(homeFragment);

        /*BadgeDrawable badgeDrawable = bottomNavigationView.getOrCreateBadge(R.id.home);
        badgeDrawable.setVisible(true);
        badgeDrawable.setNumber(8);*/

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.home) {
                    replaceFragment(homeFragment);
                    return true;
                } else if (itemId == R.id.suggestions) {
                    replaceFragment(suggestionsFragment);
                    return true;
                } else if (itemId == R.id.bookmarks) {
                    replaceFragment(bookmarksFragment);
                    return true;
                }

                return false;
            }
        });
    }

    private void replaceFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
    }

    private void setUpActionBar(){
        Objects.requireNonNull(getSupportActionBar()).setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.action_bar);

        LinearLayout actionBarView = (LinearLayout) getSupportActionBar().getCustomView();
        profileButton = actionBarView.findViewById(R.id.profileButton);

        //TODO: Login, then after click go to profile , if not then go to login page
    }

    @Override
    public boolean onSupportNavigateUp() {
        return navigationController.navigateUp() || super.onSupportNavigateUp();
    }
}