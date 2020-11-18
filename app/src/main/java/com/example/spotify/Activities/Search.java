package com.example.spotify.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.spotify.R;
import com.example.spotify.models.Artist;
import com.example.spotify.DataObjects.Data;
import com.example.spotify.models.User;
import com.google.gson.Gson;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Search extends AppCompatActivity {
    public ArrayList<Artist> artistss = new ArrayList<>();
    ArrayList<String> options= new ArrayList<>();
    Intent intent;
    ListView listView;
    User userr=new User();
    android.widget.SearchView searchview;
    SharedPreferences sharedPreferences;
    ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        listView = (ListView) findViewById(R.id.list);
        searchview = findViewById(R.id.search1);
        userr = MainActivity.user;
        sharedPreferences = this.getSharedPreferences("SPOTIFY", 0);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                            @Override
                                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                                String selected = (listView.getItemAtPosition(position).toString());
                                                searchview.setQuery(selected,false);
                                                listView.setVisibility(View.INVISIBLE);
                                            }
                                        });

                searchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {

                        if (!(query.equals(""))) {
                            intent = new Intent(Search.this, Artistss.class);
                            intent.putExtra("querytext", query);
                            String[] all = query.split(" ");
                            query = "%20";
                            for (int i = 0; i < all.length; i++) {

                                query = query + all[i] + "%20";
                            }
                            String url = "https://api.spotify.com/v1/search?q=" + query + "&type=artist&limit=50&offset=0";
                            RequestQueue queue = Volley.newRequestQueue(Search.this);
                            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null, response -> {
                                Gson gson = new Gson();
                                Data d = gson.fromJson(response.toString(), Data.class);

                                artistss = d.artists.getItems();

                                if (artistss != null && artistss.size() > 0) {

                                    intent.putExtra("Artists", artistss);
                                    startActivity(intent);

                                } else {
                                    Toast.makeText(Search.this, "Sorry, this artist doesn't exist!", Toast.LENGTH_LONG).show();
                                }

                            }, error -> {
                                // TODO: Handle error


                            }) {
                                @Override
                                public Map<String, String> getHeaders() throws AuthFailureError {
                                    Map<String, String> headers = new HashMap<>();
                                    String token = sharedPreferences.getString("token", "");
                                    String auth = "Bearer " + token;
                                    headers.put("Authorization", auth);
                                    headers.put("Accept", "application/json");
                                    return headers;
                                }
                            };
                            queue.add(jsonObjectRequest);

                        }
                        searchview.clearFocus();
                        return true;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {

                        if(!(newText.equals(""))) {
                            listView.setVisibility(View.VISIBLE);
                            options = new ArrayList<>();
                            listView.setBackgroundColor(Color.WHITE);
                            String[] all = newText.split(" ");
                            newText = "%20";
                            for (int i = 0; i < all.length; i++) {

                                newText = newText + all[i] + "%20";
                            }
                            String url = "https://api.spotify.com/v1/search?q=" + newText + "&type=artist&limit=50&offset=0";
                            RequestQueue queue = Volley.newRequestQueue(Search.this);
                            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null, response -> {
                                Gson gson = new Gson();
                                Data d = gson.fromJson(response.toString(), Data.class);

                                artistss = d.artists.getItems();

                                if (artistss != null && artistss.size() > 0) {

                                    for (int i = 0; i < artistss.size(); i++) {

                                        options.add(artistss.get(i).getName());
                                    }

                                    adapter = new ArrayAdapter<>(Search.this, android.R.layout.simple_list_item_1, options);
                                    adapter.notifyDataSetChanged();
                                    listView.setAdapter(adapter);
                                }

                            }, error -> {
                                // TODO: Handle error


                            }) {
                                @Override
                                public Map<String, String> getHeaders() throws AuthFailureError {
                                    Map<String, String> headers = new HashMap<>();
                                    String token = sharedPreferences.getString("token", "");
                                    String auth = "Bearer " + token;
                                    headers.put("Authorization", auth);
                                    headers.put("Accept", "application/json");
                                    return headers;
                                }
                            };

                            queue.add(jsonObjectRequest);
                        }else{

                            options=new ArrayList<>();
                            adapter = new ArrayAdapter<>(Search.this, android.R.layout.simple_list_item_1, options);
                            adapter.notifyDataSetChanged();
                            listView.setAdapter(adapter);
                            listView.setVisibility(View.GONE);

                        }

                        return true;
                    }
                });
    }

}



















