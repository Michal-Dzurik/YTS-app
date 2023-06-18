package sk.dzurikm.yts;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

import sk.dzurikm.yts.constants.ApiMap;
import sk.dzurikm.yts.constants.RequestParameters;
import sk.dzurikm.yts.constants.ResponseType;
import sk.dzurikm.yts.constants.YtsUrlBuilder;

public class MainActivity extends AppCompatActivity {
    private ImageButton profileButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        String url = new YtsUrlBuilder(ResponseType.JSON)
                .setApiRoute(ApiMap.MOVIES_LIST)
                .setParameters(new RequestParameters())
                .getUrl();

        Toast.makeText(MainActivity.this, "URL:" + url, Toast.LENGTH_SHORT).show();

        RequestQueue queue = Volley.newRequestQueue(this);
        JsonObjectRequest request = new JsonObjectRequest(url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if (null != response) {
                            Toast.makeText(MainActivity.this, "Answer:" + response.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(request);
    }

    private void init(){
        setActionBar();
    }

    private void setActionBar(){
        Objects.requireNonNull(getSupportActionBar()).setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.action_bar);

        LinearLayout actionBarView = (LinearLayout) getSupportActionBar().getCustomView();
        profileButton = actionBarView.findViewById(R.id.profileButton);

        //TODO: Login, then after click go to profile , if not then go to login page
    }

}