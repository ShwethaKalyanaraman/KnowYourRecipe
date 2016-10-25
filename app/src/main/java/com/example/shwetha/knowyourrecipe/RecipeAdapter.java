package com.example.shwetha.knowyourrecipe;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidquery.AQuery;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class RecipeAdapter extends BaseAdapter {
    String[] recipe_name;
    String[] image_url;
    int[] id;
    AQuery listAq ;

    Context context;
    private static LayoutInflater recipe_inflater=null;
    public RecipeAdapter(String[] recipe_name, String[] image_url,int[] id, Context context) {
        this.recipe_name = recipe_name;
        this.image_url = image_url;
        this.id=id;
        this.context = context;
        recipe_inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        listAq= new AQuery(context);
    }

    @Override
    public int getCount() {
        return recipe_name.length;
    }

    @Override
    public Object getItem(int position) {
        return recipe_name[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public class Holder
    {
        TextView tv;
        ImageView img;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

            View rowview;
         ViewHolder holder;


            rowview = recipe_inflater.inflate(R.layout.recipelist, null);
            holder = new ViewHolder();
            holder.img = (ImageView)rowview.findViewById(R.id.imageView);
            holder.title = (TextView) rowview.findViewById(R.id.textView);

           rowview.setTag(holder);



        holder.title.setText(recipe_name[position]);

        AQuery imgaq = listAq.recycle(rowview);
        imgaq.id(holder.img).image(image_url[position], true, true, 0, 0, null, AQuery.FADE_IN_NETWORK, 1.0f);

        /*rowview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(context,RecepieInfo.class);
                i.putExtra("id",id[position]);
                i.putExtra("imageurl",image_url[position]);
                context.startActivity(i);
                Toast.makeText(context,id[position]+" ",Toast.LENGTH_LONG).show();
            }
        });*/
        return rowview;
    }

    private class ViewHolder{
        ImageView img;
        TextView title;

    }
}
