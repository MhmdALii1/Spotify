package com.example.spotify.Presenters;

import android.content.Context;
import android.content.SharedPreferences;
import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.spotify.Models.Album;
import com.example.spotify.Models.Albums;
import com.example.spotify.Models.Artist;
import com.example.spotify.R;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ArtistsPresenter {

    ArrayList<Album> albums;
    Context context;


    public ArtistsPresenter(Context context) {

        this.context = context;

    }

    public ArrayList<Album> FetchArtitAlbums(Artist ChoseenArtist){

        String FirstUrlpart=context.getResources().getString(R.string.FirstPartUrlAlbums);
        String SecondUrlpart=context.getResources().getString(R.string.SecondPartUrlAlbums);
        SharedPreferences SharedPreferencess = context.getSharedPreferences("SPOTIFY", 0);
        String url =FirstUrlpart+ChoseenArtist.getId()+SecondUrlpart;
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null, response -> {

            Gson gson = new Gson();
            Albums AlbumsFetched= gson.fromJson(response.toString(), Albums.class);
            albums=AlbumsFetched.getItems();


        }, error -> {
            // TODO: Handle error


        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                String token = SharedPreferencess.getString("token", "");
                String auth = "Bearer " + token;
                headers.put("Authorization", auth);
                headers.put("Accept", "application/json");
                return headers;
            }
        };
        queue.add(jsonObjectRequest);

        return albums;

    }


















}
