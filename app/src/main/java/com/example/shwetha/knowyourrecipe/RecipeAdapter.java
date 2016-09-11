package com.example.shwetha.knowyourrecipe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by User on 9/10/2016.
 */
public class RecipeAdapter extends BaseAdapter {
    String[] recipe_name;
    String[] image_url;
    Context context;
    private static LayoutInflater recipe_inflater=null;
    public RecipeAdapter(String[] recipe_name, String[] image_url, Context context) {
        this.recipe_name = recipe_name;
        this.image_url = image_url;
        this.context = context;
        recipe_inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
        Holder holder=new Holder();
        View rowView;

        rowView = recipe_inflater.inflate(R.layout.recipelist, null);
        holder.tv=(TextView) rowView.findViewById(R.id.textView1);
        holder.img=(ImageView) rowView.findViewById(R.id.imageView1);

        holder.tv.setText(recipe_name[position]);
        //holder.img.setImageBitmap();

        return rowView;
    }
}
