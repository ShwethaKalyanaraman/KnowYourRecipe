package com.example.shwetha.knowyourrecipe;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidquery.AQuery;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class RecipeAdapter extends BaseAdapter {
    String[] recipe_name;
    String[] image_url;
    AQuery listAq ;

    Context context;
    private static LayoutInflater recipe_inflater=null;
    public RecipeAdapter(String[] recipe_name, String[] image_url, Context context) {
        this.recipe_name = recipe_name;
        this.image_url = image_url;
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
    public View getView(int position, View convertView, ViewGroup parent) {


         ViewHolder holder;

        if (convertView == null) {
            convertView = recipe_inflater.inflate(R.layout.recipelist, null);
            holder = new ViewHolder();
            holder.img = (ImageView) convertView.findViewById(R.id.imageView);
            holder.title = (TextView) convertView.findViewById(R.id.textView);

            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.title.setText(recipe_name[position]);

        AQuery imgaq = listAq.recycle(convertView);
        imgaq.id(holder.img).image(image_url[position], true, true, 0, 0, null, AQuery.FADE_IN_NETWORK, 1.0f);
        return convertView;
    }

    private class ViewHolder{
        ImageView img;
        TextView title;

    }
}
