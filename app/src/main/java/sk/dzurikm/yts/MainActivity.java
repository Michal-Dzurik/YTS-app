package sk.dzurikm.yts;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import sk.dzurikm.yts.constants.ApiMap;
import sk.dzurikm.yts.constants.RequestParameters;
import sk.dzurikm.yts.constants.ResponseType;
import sk.dzurikm.yts.constants.YtsUrlBuilder;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.action_bar, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

           /* case R.id.profile:
                //do something
                break;
            default:
                break;*/
        }
        return super.onOptionsItemSelected(item);
    }
}