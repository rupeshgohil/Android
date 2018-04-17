package com.quickbloxchat;

import android.content.Context;
import android.content.Intent;
import android.database.DatabaseUtils;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.quickblox.auth.session.QBSettings;
import com.quickblox.core.QBEntityCallback;
import com.quickblox.core.exception.QBResponseException;
import com.quickblox.users.QBUsers;
import com.quickblox.users.model.QBUser;
import com.quickbloxchat.databinding.ActivityLoginBinding;


/**
 * Created by Aru on 09-04-2018.
 */

public class ActivityLogin extends BaseActivity {
    public static ActivityLoginBinding mActivityLoginBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityLoginBinding = DataBindingUtil.setContentView(this,R.layout.activity_login);
        initilizedFramwork();
        mActivityLoginBinding.LoginBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = mActivityLoginBinding.LoginEdtUsername.getText().toString();
                String password = mActivityLoginBinding.LoginEdtPassword.getText().toString();
                    QBUser qbUser = new QBUser(username,password);
                    QBUsers.signIn(qbUser).performAsync(new QBEntityCallback<QBUser>() {
                        @Override
                        public void onSuccess(QBUser qbUser, Bundle bundle) {
                            Toast.makeText(ActivityLogin.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onError(QBResponseException e) {
                            Log.e("Login Error",e.getMessage());
                        }
                    });

            }
        });
        mActivityLoginBinding.LoginTxtSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActivityLogin.this,ActivitySignup.class));
            }
        });
    }

    private void initilizedFramwork() {
        QBSettings.getInstance().init(getApplicationContext(),Config.APP_ID,Config.AUTH_KEY,Config.AUTH_SECRET);
        QBSettings.getInstance().setAccountKey(Config.ACCOUNT_KEY);
    }
}
