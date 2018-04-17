package com.quickbloxchat;

import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import static com.quickbloxchat.ActivityLogin.mActivityLoginBinding;

/**
 * Created by Aru on 09-04-2018.
 */

public class BaseActivity extends AppCompatActivity{

    public boolean isValid(){
        if(mActivityLoginBinding.LoginEdtUsername.getText().toString().length() == 0){
            Toast.makeText(getApplicationContext(), "Enter Username", Toast.LENGTH_SHORT).show();
            return false;
        }else if(mActivityLoginBinding.LoginEdtPassword.getText().toString().length() == 0){
            Toast.makeText(getApplicationContext(), "Enter Password", Toast.LENGTH_SHORT).show();
            return false;
        }else{
            return true;
        }

    }

}
