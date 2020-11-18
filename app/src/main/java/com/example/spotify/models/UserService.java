package com.example.spotify.models;

import android.content.SharedPreferences;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

// I created this class to help with the request to spotify api when I want to login as a user and
// I used shared preferences to save the token needed in the authentication process and of course
// the volley(HTTP library) dependency to help with request process and make networking faster and easier

public class UserService {

        private static final String ENDPOINT = "https://api.spotify.com/v1/me";
        private SharedPreferences msharedPreferences;
        private RequestQueue mqueue;
        private User user;


        public UserService(RequestQueue queue, SharedPreferences sharedPreferences) {
            mqueue = queue;
            msharedPreferences = sharedPreferences;
        }

        public User getUser() {
            return user;
        }


        public void get(final VolleyCallBack callBack) {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(ENDPOINT, null, response -> {
                Gson gson = new Gson();
                user = gson.fromJson(response.toString(), User.class);
                callBack.onSuccess();
            }, error -> get(() -> {

            })) {
                @Override
                public Map <String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> headers = new HashMap<>();
                    String token = msharedPreferences.getString("token", "");
                    String auth = "Bearer " + token;
                    headers.put("Authorization", auth);
                    
                    return headers;
                }
            };
            mqueue.add(jsonObjectRequest);
        }
}










