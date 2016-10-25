package com.example.shwetha.knowyourrecipe;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
 class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
    ImageView bmImage;

    public DownloadImageTask(ImageView bmImage) {
        this.bmImage = bmImage;
    }

    protected Bitmap doInBackground(String... urls) {
        String urldisplay = urls[0];
        Bitmap mIcon11 = null;
        try {
            InputStream in = new java.net.URL(urldisplay).openStream();
            mIcon11 = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return mIcon11;
    }

    protected void onPostExecute(Bitmap result) {
        bmImage.setImageBitmap(result);
    }
}
public class RecepieInfo extends AppCompatActivity {
    TextView t;
    TextView heading;
    ImageView image;
    String imageurl;
    String id;
    String steps;
    String[] steps1;
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recepie_info);
        Bundle b=getIntent().getExtras();
        id=b.getInt("id")+"";
        imageurl=b.getString("imageurl");
        name=b.getString("name");

        t= (TextView) findViewById(R.id.textview_steps);
        heading= (TextView) findViewById(R.id.heading);
        heading.setText(name);

        new DownloadImageTask((ImageView) findViewById(R.id.imageView2))
                .execute(imageurl);
        RequestQueue queue = Volley.newRequestQueue(this);

        String url="https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/recipes/"+id+"/analyzedInstructions?stepBreakdown=false";
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        try {
                            JSONArray json=new JSONArray(response);
                            JSONObject js=json.getJSONObject(0);
                            steps=js.getJSONArray("steps").getJSONObject(0).getString("step");

                            t.setText(steps);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                t.setText("That didn't work!");
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> params=new HashMap();
                params.put("X-Mashape-Key","FA8lao5e6HmshYtRB9eAizSV3ikVp1MvAKvjsnnlgE2NdRm89J");
                params.put("Accept","application/json");
                return params;
            }
        };
// Add the request to the RequestQueue.
        queue.add(stringRequest);



    }
}
