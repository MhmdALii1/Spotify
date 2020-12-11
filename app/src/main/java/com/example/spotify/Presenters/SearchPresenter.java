package com.example.spotify.Presenters;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.ArrayAdapter;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.spotify.Models.Artist;
import com.example.spotify.Models.Data;
import com.example.spotify.R;
import com.example.spotify.Interfaces.ViewSearch;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SearchPresenter {

    Context context;

    public SearchPresenter( Context context) {

        this.context = context;

    }

    public void SearchResultsSubmit(String query) {

        String FirstUrlPart = context.getResources().getString(R.string.FirstPartUrlArtist);
        String SecondUrlPart = context.getResources().getString(R.string.SecondPartUrlArtist);
        SharedPreferences SharedPreferencess = context.getSharedPreferences("SPOTIFY", 0);

        String SearchQuery=query;
        String[] AllQueryWords = query.split(" ");
        query = "%20";
        for (int i = 0; i < AllQueryWords.length; i++) {

            query = query + AllQueryWords[i] + "%20";
        }

        String url = FirstUrlPart + query + SecondUrlPart;
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, response -> {

            Gson gson = new Gson();
            Data data = gson.fromJson(response.toString(), Data.class);

            ArrayList<Artist> artists=data.artists.getItems();
            ViewSearch viewsearch1=(ViewSearch)context;
            viewsearch1.SendArtists(artists,SearchQuery);

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

    }



    public void SearchResultsSuggestions(String newText) {

        String FirstUrlPart = context.getResources().getString(R.string.FirstPartUrlArtist);
        String SecondUrlPart = context.getResources().getString(R.string.SecondPartUrlArtist);
        SharedPreferences SharedPreferencess = context.getSharedPreferences("SPOTIFY", 0);

        String[] AllQueryWord = newText.split(" ");
        newText = "%20";
        for (int i = 0; i < AllQueryWord.length; i++) {

            newText = newText + AllQueryWord[i] + "%20";
        }
        String url =FirstUrlPart + newText + SecondUrlPart;
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, response -> {

            Gson gson = new Gson();
            Data data = gson.fromJson(response.toString(), Data.class);

            if (data.artists.getItems()!= null && data.artists.getItems().size() > 0) {
                ArrayList<String> suggestions=new ArrayList<>();
                for (int i = 0; i < data.artists.getItems().size(); i++) {

                    suggestions.add(data.artists.getItems().get(i).getName());
                }
                ViewSearch viewsearch= (ViewSearch) context;
                ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, suggestions);
                viewsearch.AdapterSet(adapter);

            }}, error -> {
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

    }

}
