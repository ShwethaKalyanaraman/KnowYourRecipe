package com.example.shwetha.knowyourrecipe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
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
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RecipeList extends AppCompatActivity {
    ListView list;
    TextView t;
    String[] imageurl=new String[30];
    String[] recipe_name=new String[30];
    int[] id=new int[30];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);
        Bundle extras=getIntent().getExtras();
        String url=extras.getString("url");


        RequestQueue queue = Volley.newRequestQueue(this);
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        try {
                            JSONArray json=new JSONArray(response);
                            JSONObject js=null;
                            Toast.makeText(getApplicationContext(),"success",Toast.LENGTH_LONG);
                            for(int i=0;i<json.length();i++){
                                js=json.getJSONObject(i);
                                recipe_name[i]=js.getString("title");
                                imageurl[i]=js.getString("image");
                                id[i]=js.getInt("id");
                                Toast.makeText(getApplicationContext(),recipe_name[i],Toast.LENGTH_LONG);
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"This didnt work",Toast.LENGTH_SHORT);
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> params=new HashMap();
                params.put("X-Mashape-Key","W4HPADCRWsmshpoEWqR63e8Qh1V7p1r0QPkjsnLImLHCmXiT8M");
                params.put("Accept","application/json");
                return params;
            }
        };
        queue.add(stringRequest);

        list= (ListView) findViewById(R.id.listView);
        list.setAdapter(new RecipeAdapter(recipe_name, imageurl, id, this));

       list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id1) {
              // Toast.makeText(getApplicationContext(),id[position]+" ",Toast.LENGTH_LONG).show();
               Intent i=new Intent(getApplicationContext(),RecepieInfo.class);
                i.putExtra("id",id[position]);
                i.putExtra("name",recipe_name[position]);
                i.putExtra("imageurl",imageurl[position]);

                startActivity(i);

            }
        });
    }
}
