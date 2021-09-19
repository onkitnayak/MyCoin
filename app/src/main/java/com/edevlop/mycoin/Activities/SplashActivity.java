package com.edevlop.mycoin.Activities;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.edevlop.mycoin.Database.AppDatabase;
import com.edevlop.mycoin.Database.DataModel;
import com.edevlop.mycoin.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SplashActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        RequestQueue requestQueue = Volley.newRequestQueue(SplashActivity.this);
        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, "https://demo3231717.mockable.io/coinlist",
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(final JSONObject response) {

                try {
                    if (response.getInt("result") == 1) {

                        // Log.d("RESPONSE", response.getJSONObject("data").toString());
                        JSONArray jsonArray = response.getJSONObject("data").getJSONArray("list");

                        for (int i = 1; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);


                            String name = jsonObject.getString("name");
                            String url = jsonObject.getJSONObject("pictures").getJSONObject("back").getString("url");


                            // save data to room database
                            AppDatabase db = AppDatabase.getDbInstance(SplashActivity.this);
                            DataModel dataModel = new DataModel();
                            dataModel.Name = name;
                            dataModel.Url = url;
                            db.userDao().insertUser(dataModel);

                        }

                    }

                } catch (JSONException e) {
                    Log.i("CATCH", e.getMessage());
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected VolleyError parseNetworkError(VolleyError volleyError) {
                Log.d(TAG, "ERROR_IN_CONNECTING_VOLLEY" + volleyError.getMessage());
                return super.parseNetworkError(volleyError);
            }


        };

        requestQueue.add(objectRequest);




        // move to home activity after 3sec
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent homeIntent = new Intent(SplashActivity.this, HomeActivity.class);
                startActivity(homeIntent);
                finish();
            }
        },SPLASH_TIME_OUT);


    }

}