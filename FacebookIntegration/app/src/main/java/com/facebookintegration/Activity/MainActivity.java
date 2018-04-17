package com.facebookintegration.Activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebookintegration.R;
import com.facebookintegration.databinding.ActivityMainBinding;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    public ActivityMainBinding mainBinding;
    public LoginButton mLoginButton;
    public ImageView ivprofile;
    public CallbackManager callbackManager;
    public AccessTokenTracker accessTokenTracker;
    ProfileTracker mProfileTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        FacebookSdk.sdkInitialize(this);
        callbackManager = CallbackManager.Factory.create();
        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {

            }
        };
        mProfileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile newProfile) {

            }
        };
        accessTokenTracker.startTracking();
        mProfileTracker.startTracking();
        mainBinding.fbLogin.setReadPermissions(Arrays.asList("public_profile","email","user_birthday","user_friends"));
        mainBinding.fbLogin.registerCallback(callbackManager,callback);
    }
    FacebookCallback<LoginResult> callback = new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(LoginResult loginResult) {
            GraphRequest request = GraphRequest.newMeRequest(
                    loginResult.getAccessToken(),
                    new GraphRequest.GraphJSONObjectCallback() {
                        @Override
                        public void onCompleted(JSONObject object, GraphResponse response) {
                            Log.e("LoginActivity", response.toString());
                            Log.e("LoginActivityJsonObject", object.toString());
                            try {
                                Log.e("tttttt",object.getString("id"));
                                String birthday="";
                                if(object.has("birthday")){
                                    birthday = object.getString("birthday"); // 01/31/1980 format
                                }

                                String fnm = object.getString("first_name");
                                String lnm = object.getString("last_name");
                                String mail = object.getString("email");
                                String gender = object.getString("gender");
                                String fid = object.getString("id");
                                mainBinding.textView.setText("Name: "+fnm+" "+lnm+" \n"+"Email: "+mail+" \n"+"Gender: "+gender+" \n"+"ID: "+fid+" \n"+"Birth Date: "+birthday);
                                //aQuery.id(ivpic).image("https://graph.facebook.com/" + fid + "/picture?type=large");
                                //https://graph.facebook.com/143990709444026/picture?type=large
                                Picasso.with(MainActivity.this).load("https://graph.facebook.com/" + fid + "/picture?type=large").into(mainBinding.imgProfile);
                                Log.e("aswwww","https://graph.facebook.com/"+fid+"/picture?type=large");

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    });
            Bundle parameters = new Bundle();
            parameters.putString("fields", "id, first_name, last_name, email, gender, birthday, location");
            request.setParameters(parameters);
            request.executeAsync();
        }
        @Override
        public void onCancel() {

        }

        @Override
        public void onError(FacebookException error) {

        }
    };
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);

    }

    @Override
    protected void onStop() {
        super.onStop();
        accessTokenTracker.stopTracking();
        mProfileTracker.stopTracking();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Profile profile = Profile.getCurrentProfile();
    }
}
