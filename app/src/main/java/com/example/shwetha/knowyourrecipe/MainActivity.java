package com.example.shwetha.knowyourrecipe;

import android.content.Context;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;

import static com.example.shwetha.knowyourrecipe.CustomAdapter.*;

public class MainActivity extends AppCompatActivity {


    GridView gv;
    Context context;
    ArrayList prgmName;
    Button getRecipe;
    public static String[] prgmNameList = {"Apples", "Flour", "Sugar", "Jsp", "Microsoft .Net", "Android", "PHP", "Jquery", "JavaScript"};
    public static int[] prgmImages = {R.drawable.salt, R.drawable.salt, R.drawable.salt, R.drawable.salt, R.drawable.salt, R.drawable.salt, R.drawable.salt, R.drawable.salt, R.drawable.salt};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gv = (GridView) findViewById(R.id.gridView1);
        gv.setAdapter(new CustomAdapter(this, prgmNameList, prgmImages));

        getRecipe = (Button) findViewById(R.id.btn_output);

        getRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Iterator i=ingredients_list.iterator();

                String ingredients="https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/recipes/findByIngredients?fillIngredients=false&ingredients="+i.next();
                while(i.hasNext()){
                    ingredients=ingredients+"%2C"+i.next();
                }
                ingredients=ingredients+"&limitLicense=false&number=30&ranking=1";
                Toast.makeText(getApplicationContext(),ingredients,Toast.LENGTH_LONG).show();
                Intent intent=new Intent(getApplicationContext(),RecipeList.class);
                intent.putExtra("url",ingredients);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);
        MenuItem item = menu.findItem(R.id.menuSearch);
        SearchView searchview = (SearchView) item.getActionView();
        return true;
    }



}
