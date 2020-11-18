package com.example.spotify.models;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.spotify.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

//Artist adapter to help fill the artits (items) details in the gridview in Artistss activity

public class ArtistAdapter extends BaseAdapter {
    public Context context;
    public ArrayList<Artist> Artists;

    public ArtistAdapter(Context context, ArrayList<Artist> Artists) {
        this.context=context;
        this.Artists=Artists;

    }

    @Override
    public int getCount() {
        return Artists.size() ;
    }

    @Override
    public Artist getItem(int position) {
        return Artists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        View convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.artistprofile, parent, false);
        }else{
            convertView=view;
        }

        ImageView profile = convertView.findViewById(R.id.imgart);
        TextView name = convertView.findViewById(R.id.name);
        TextView followers = convertView.findViewById(R.id.followers);
        ImageView rating1 = convertView.findViewById(R.id.rate1);
        ImageView rating2 = convertView.findViewById(R.id.rate2);
        ImageView rating3 = convertView.findViewById(R.id.rate3);
        ImageView rating4 = convertView.findViewById(R.id.rate4);
        ImageView rating5 = convertView.findViewById(R.id.rate5);
        int rating, result;

        Artist item = Artists.get(position);

        rating = (item.getPopularity() * 5) / 100;
        result = Math.round(rating);

        switch (result) {

            case 0:

                rating1.setImageResource(android.R.drawable.btn_star_big_off);
                rating2.setImageResource(android.R.drawable.btn_star_big_off);
                rating3.setImageResource(android.R.drawable.btn_star_big_off);
                rating4.setImageResource(android.R.drawable.btn_star_big_off);
                rating5.setImageResource(android.R.drawable.btn_star_big_off);
                break;

            case 1:

                rating1.setImageResource(android.R.drawable.btn_star_big_on);
                rating2.setImageResource(android.R.drawable.btn_star_big_off);
                rating3.setImageResource(android.R.drawable.btn_star_big_off);
                rating4.setImageResource(android.R.drawable.btn_star_big_off);
                rating5.setImageResource(android.R.drawable.btn_star_big_off);
                break;

            case 2:
                rating1.setImageResource(android.R.drawable.btn_star_big_on);
                rating2.setImageResource(android.R.drawable.btn_star_big_on);
                rating3.setImageResource(android.R.drawable.btn_star_big_off);
                rating4.setImageResource(android.R.drawable.btn_star_big_off);
                rating5.setImageResource(android.R.drawable.btn_star_big_off);
                break;

            case 3:

                rating1.setImageResource(android.R.drawable.btn_star_big_on);
                rating2.setImageResource(android.R.drawable.btn_star_big_on);
                rating3.setImageResource(android.R.drawable.btn_star_big_on);
                rating4.setImageResource(android.R.drawable.btn_star_big_off);
                rating5.setImageResource(android.R.drawable.btn_star_big_off);
                break;

            case 4:

                rating1.setImageResource(android.R.drawable.btn_star_big_on);
                rating2.setImageResource(android.R.drawable.btn_star_big_on);
                rating3.setImageResource(android.R.drawable.btn_star_big_on);
                rating4.setImageResource(android.R.drawable.btn_star_big_on);
                rating5.setImageResource(android.R.drawable.btn_star_big_off);
                break;

            case 5:

                rating1.setImageResource(android.R.drawable.btn_star_big_on);
                rating2.setImageResource(android.R.drawable.btn_star_big_on);
                rating3.setImageResource(android.R.drawable.btn_star_big_on);
                rating4.setImageResource(android.R.drawable.btn_star_big_on);
                rating5.setImageResource(android.R.drawable.btn_star_big_on);

            default:

                break;

        }

        name.setText(item.getName());

        if (item.getFollowers() != null) {

            followers.setText(item.getFollowers().getTotal() + " followers");

        } else {

            followers.setText("0 followers");
        }

       if (item.getImages() != null & item.getImages().size()!=0) {

             for (int i=0;i<item.getImages().size();i++) {

                 if(!(item.getImages().get(i).getUrl().equals(""))){
                    Picasso.get().load(item.getImages().get(i).getUrl()).into(profile);
                    break;
                 }

             }
        }

        return convertView;

    }
}
