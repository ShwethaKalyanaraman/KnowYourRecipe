package com.example.shwetha.knowyourrecipe;

import android.content.Context;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.SearchView;
import android.widget.Toast;

import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;

import static com.example.shwetha.knowyourrecipe.CustomAdapter.*;

public class MainActivity extends AppCompatActivity {

    ArrayAdapter<list_info> adapter;
    GridView gv;
    Context context;
    //ArrayList prgmName;
    Button getRecipe;
    public static ArrayList<list_info> prgmNameList=new ArrayList<list_info>(); ;
    //public static ArrayList<list_info> prgmImages ;

    CustomAdapter adapter1;


int a;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        adapter1 = new CustomAdapter(this,android.R.layout.activity_list_item,prgmNameList);
        gv = (GridView) findViewById(R.id.gridView1);
        prgmNameList.add(new list_info("Salt",R.drawable.salt));
        prgmNameList.add(new list_info("Sugar",R.drawable.salt));
        prgmNameList.add(new list_info("Lemon",R.drawable.salt));
        prgmNameList.add(new list_info("Goat",R.drawable.salt));
        prgmNameList.add(new list_info("tomato",R.drawable.salt));
        prgmNameList.add(new list_info("potato",R.drawable.salt));
        prgmNameList.add(new list_info("rice",R.drawable.salt));
        prgmNameList.add(new list_info("curd",R.drawable.salt));
        prgmNameList.add(new list_info("water",R.drawable.salt));
       // gv.setAdapter(new CustomAdapter(this, prgmNameList, prgmImages));
          gv.setAdapter(adapter1);
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
        final EditText inputSearch;
        inputSearch = (EditText) findViewById(R.id.inputSearch);
        inputSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                adapter1.getFilter().filter(cs);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {

            }

            @Override
            public void afterTextChanged(Editable arg0) {

            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchview = (SearchView) item.getActionView();
        return true;
    }





}
