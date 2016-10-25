package com.example.shwetha.knowyourrecipe;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidquery.AQuery;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by SHWETHA on 06-08-2016.
 */
class Holder
{
    TextView tv;
    ImageView img;
    CheckBox check;
}
public class CustomAdapter extends ArrayAdapter<list_info>implements Filterable{
    CustomFilter filter;
    String [] result;
    Context context;
    AQuery listq;
    private  ArrayList<list_info> searchArray;

    private ArrayList<list_info> filterlist;
    ArrayList<list_info> listofing;
    private static LayoutInflater inflater=null;
    public static ArrayList<String> ingredients_list=new ArrayList<String>();



    public CustomAdapter(Context context,int R, ArrayList<list_info> listofing) {
        super(context, R, listofing);
        this.searchArray = listofing;
        this.filterlist=listofing;
        this.context = context;
        inflater = LayoutInflater.from(context);
        listq=new AQuery(context);
    }




    public int getCount() {
        return searchArray.size();
    }

    public list_info getItem(int position) {
        return searchArray.get(position);
    }

    public long getItemId(int position) {
        return position;
    }



    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        final Holder holder=new Holder();
        View rowView;

        rowView = inflater.inflate(R.layout.programlist, null);
        holder.tv=(TextView) rowView.findViewById(R.id.textView1);
        holder.img=(ImageView) rowView.findViewById(R.id.imageView1);
        holder.check= (CheckBox)rowView.findViewById(R.id.checkBox1);


        holder.tv.setText(searchArray.get(position).getIngredients());
        AQuery aq=listq.recycle(rowView);
        aq.id(holder.img).image("https://spoonacular.com/cdn/ingredients_100x100/"+searchArray.get(position).getImgResID(), true, true, 0, 0, null, AQuery.FADE_IN_NETWORK, 1.0f);
        holder.check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.check.isChecked()) {
                    ingredients_list.add(searchArray.get(position).getIngredients());
                    Toast.makeText(context, "You Added " + searchArray.get(position).getIngredients(), Toast.LENGTH_SHORT).show();
                } else {
                    ingredients_list.remove(searchArray.get(position).getIngredients());
                    Toast.makeText(context, "You Removed " + searchArray.get(position).getIngredients(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        rowView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Toast.makeText(context, "You Clicked " + searchArray.get(position).getIngredients(), Toast.LENGTH_LONG).show();
            }
        });

        return rowView;
    }


    @Override
    public Filter getFilter() {
        filter = new CustomFilter();
        return filter;

    }



    class CustomFilter extends Filter {


        @Override
        protected FilterResults performFiltering(CharSequence cs) {
            FilterResults results = new FilterResults();
            if (cs != null && cs.length() > 0) {
                cs = cs.toString().toLowerCase().trim();
                Log.d("cs",cs.toString());
                ArrayList<list_info> filter = new ArrayList<list_info>();
                for (int i = 0; i < filterlist.size(); i++) {
                    Log.d("IINn loop",filterlist.get(i).getIngredients());
                    if (filterlist.get(i).getIngredients().toLowerCase().contains(cs) ) {
                       list_info c = new list_info(filterlist.get(i).getIngredients(), filterlist.get(i).getImgResID());
                        filter.add(c);
                        Log.d("added",filterlist.get(i).getIngredients());

                    }
                }
                results.count = filter.size();
                if (results.count == 0) {

                    filter.add(new list_info("No results found",null));

                }
                results.values = filter;

            } else {
                results.count = filterlist.size();
                results.values = filterlist;

            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence cs, FilterResults results) {
            searchArray = (ArrayList<list_info>) results.values;
            notifyDataSetChanged();
        }
    }
}
