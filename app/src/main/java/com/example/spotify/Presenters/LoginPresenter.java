package com.example.spotify.Presenters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import com.android.volley.RequestQueue;
import com.example.spotify.Models.User;
import com.example.spotify.R;
import com.example.spotify.Services.UserService;
import com.example.spotify.Views.SearchActivity;
import com.spotify.sdk.android.authentication.AuthenticationClient;
import com.spotify.sdk.android.authentication.AuthenticationRequest;
import com.spotify.sdk.android.authentication.AuthenticationResponse;

public class LoginPresenter {

    String ClientId, RedirectUri, Scopes;
    int REQUEST_CODE=1337;
    Context context;
    User user;

    public LoginPresenter( Context context) {

        this.context = context;
        ClientId = context.getResources().getString(R.string.CLIENT_ID);
        RedirectUri= context.getResources().getString(R.string.REDIRECT_URI);
        Scopes= context.getResources().getString(R.string.SCOPES);

    }

    public void waitForUserInfo(RequestQueue queue,SharedPreferences SharedPreferencess) {
        UserService userService = new UserService(queue, SharedPreferencess,context);
        userService.get(() -> {
            user = userService.getUser();
            SharedPreferences.Editor editor = context.getSharedPreferences("SPOTIFY", 0).edit();
            editor.putString("userid", user.id);
            Log.d("STARTING", "GOT USER INFORMATION");
            // We use commit instead of apply because we need the information stored immediately
            editor.commit();
            Intent intent=new Intent(context, SearchActivity.class);
            context.startActivity(intent);
        });
    }

    public void authenticateSpotify() {
        AuthenticationRequest.Builder builder = new AuthenticationRequest.Builder(ClientId, AuthenticationResponse.Type.TOKEN, RedirectUri);
        builder.setScopes(new String[]{Scopes});
        AuthenticationRequest request = builder.build();
        AuthenticationClient.openLoginActivity((Activity) context, REQUEST_CODE, request);
    }

}
