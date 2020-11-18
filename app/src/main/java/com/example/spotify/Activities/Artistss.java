package com.example.spotify.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SearchView;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.spotify.R;
import com.example.spotify.models.Album;
import com.example.spotify.models.Albums;
import com.example.spotify.models.Artist;
import com.example.spotify.models.ArtistAdapter;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

//Artistss activity where I dispalyed the artists after the search submit and the results(Artists) where fetched through
// the intent from the search activity and displayed in a gridview (Each item has a cardview artistprofile)
// I also fetched the albums of the artist clicked here and gave them to the Albumss activity to display


public class Artistss extends AppCompatActivity {

    public static ArrayList<Artist> artistss = new ArrayList<>();
    public static ArrayList<Album> albums = new ArrayList<>();
    Artist ChoseenArtist;
    android.widget.SearchView searchv;
    Intent intent,intentgo;
    GridView gridView;
    ArtistAdapter adapter;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artists);
        sharedPreferences = this.getSharedPreferences("SPOTIFY", 0);
        searchv=findViewById(R.id.searchvw);
        gridView=findViewById(R.id.grid);
        intent=getIntent();
        searchv.setQueryHint(intent.getStringExtra("querytext"));
        searchv.setIconified(false);
        searchv.setFocusable(false);
        searchv.setInputType(InputType.TYPE_NULL);
        enableSearchView(searchv, false);
        artistss=(ArrayList<Artist>)intent.getSerializableExtra("Artists");
        gridView.setDrawSelectorOnTop(true);
        adapter = new ArtistAdapter(this,(ArrayList<Artist>)intent.getSerializableExtra("Artists"));
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                intentgo = new Intent(Artistss.this, Albumss.class);
                ChoseenArtist = artistss.get(position);
                intentgo.putExtra("artistname", ChoseenArtist.getName());

                String url =" https://api.spotify.com/v1/artists/"+ChoseenArtist.getId()+"/albums?limit=50&offset=0";
                RequestQueue queue = Volley.newRequestQueue(Artistss.this);
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null, response -> {

                    Gson gson = new Gson();
                    Albums albs= gson.fromJson(response.toString(), Albums.class);

                    albums=albs.getItems();

                    if (albums != null && albums.size() > 0) {

                        intentgo.putExtra("Albumss",albums);
                        startActivity(intentgo);

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
        });

    }
    private void enableSearchView(View view, boolean enabled) {
        view.setEnabled(enabled);
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                View child = viewGroup.getChildAt(i);
                enableSearchView(child, enabled);
            }
        }
    }
}