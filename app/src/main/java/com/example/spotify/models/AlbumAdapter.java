package com.example.spotify.models;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.spotify.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

//Album adapter to help fill the albums (items) details in the gridview in Albumss activity

public class AlbumAdapter extends BaseAdapter {
    public Context context;
    public ArrayList<Album> Albums;


    public AlbumAdapter(Context context, ArrayList<Album> Albums) {
        this.context=context;
        this.Albums=Albums;

    }

    @Override
    public int getCount() {
        return Albums.size() ;
    }

    @Override
    public Album getItem(int position) {
        return Albums.get(position);
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
            convertView = inflater.inflate(R.layout.artistalbum, parent, false);
        }else{
            convertView=view;
        }

        ImageView profilee = convertView.findViewById(R.id.imgalb);
        TextView namee = convertView.findViewById(R.id.namealb);
        TextView artname=convertView.findViewById(R.id.artistname);
        TextView DateAndTracks = convertView.findViewById(R.id.datetracks);
        Button prev=convertView.findViewById(R.id.preview);

        Album item = Albums.get(position);

        namee.setText(item.getAName());
        int i,n=0;
        String re="";
        for(i=0;i<item.getArtists().size();i++){
            if(i<item.getArtists().size()-1)
             re=re+item.getArtists().get(i).getAAName()+" ,";
            else re=re+item.getArtists().get(i).getAAName();

        }
        artname.setText(re);
        String dt=item.getRelease_date()+"\n"+item.getTotal_tracks()+" tracks";
        DateAndTracks.setText(dt);

        if (item.getImages() != null & item.getImages().size()!=0) {

            for ( i=0;i<item.getImages().size();i++) {

                if(!(item.getImages().get(i).getUrl().equals(""))){

                    Picasso.get().load(item.getImages().get(i).getUrl()).into(profilee);
                    break;
                }

            }

        }

        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent openalb = new Intent(Intent.ACTION_VIEW, Uri.parse(item.getExternal_urls().getSpotify()));
                 context.startActivity(openalb);
            }
        });

        return convertView;

    }
}

