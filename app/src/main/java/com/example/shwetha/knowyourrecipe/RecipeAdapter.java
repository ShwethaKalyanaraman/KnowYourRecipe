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

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by User on 9/10/2016.
 */
class ImageLoadTask extends AsyncTask<Void, Void, Bitmap> {

    private String url;
    private ImageView imageView;

    public ImageLoadTask(String url, ImageView imageView) {
        this.url = url;
        this.imageView = imageView;
    }

    @Override
    protected Bitmap doInBackground(Void... params) {
        try {
            URL urlConnection = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) urlConnection.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Bitmap result) {
        super.onPostExecute(result);
        imageView.setImageBitmap(result);
    }

}
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
        holder.tv=(TextView) rowView.findViewById(R.id.textView);
        holder.img=(ImageView) rowView.findViewById(R.id.imageView);

        holder.tv.setText(recipe_name[position]);
        ImageLoadTask i= (ImageLoadTask) new ImageLoadTask(image_url[position],holder.img).execute();

        return rowView;
    }
}
