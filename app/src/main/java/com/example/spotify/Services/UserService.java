package com.example.spotify.Services;

import android.content.Context;
import android.content.SharedPreferences;
import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.spotify.Interfaces.VolleyCallBack;
import com.example.spotify.Models.User;
import com.example.spotify.R;
import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;

// I created this class to help with the request to spotify api when I want to login as a user and
// I used shared preferences to save the token needed in the authentication process and of course
// the volley(HTTP library) dependency to help with request process and make networking faster and easier

public class UserService {

        private final String EndPoint;
        private final SharedPreferences SharedPreferencess;
        private final RequestQueue requestQueue;
        private User user;


        public UserService(RequestQueue requestQueue, SharedPreferences SharedPreferencess, Context context) {
            this.requestQueue = requestQueue;
            this.SharedPreferencess = SharedPreferencess;
            this.EndPoint = context.getResources().getString(R.string.LoginUrl);
        }

        public User getUser() {
            return user;
        }


        public void get(final VolleyCallBack callBack) {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(EndPoint, null, response -> {
                Gson gson = new Gson();
                user = gson.fromJson(response.toString(), User.class);
                callBack.onSuccess();
            }, error -> get(() -> {

            })) {
                @Override
                public Map <String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> headers = new HashMap<>();
                    String token = SharedPreferencess.getString("token", "");
                    String auth = "Bearer " + token;
                    headers.put("Authorization", auth);
                    
                    return headers;
                }
            };
            requestQueue.add(jsonObjectRequest);
        }
}










