package com.quickbloxchat;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.quickblox.auth.QBAuth;
import com.quickblox.auth.session.QBSession;
import com.quickblox.core.QBEntityCallback;
import com.quickblox.core.exception.QBResponseException;
import com.quickblox.users.QBUsers;
import com.quickblox.users.model.QBUser;
import com.quickbloxchat.databinding.ActivitySignupBinding;

/**
 * Created by Aru on 09-04-2018.
 */

public class ActivitySignup extends BaseActivity {
    public ActivitySignupBinding mActivitySignupBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivitySignupBinding = DataBindingUtil.setContentView(this,R.layout.activity_signup);
        Signupsession();
        mActivitySignupBinding.SignupBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = mActivitySignupBinding.SignupEdtUsername.getText().toString();
                String password = mActivitySignupBinding.SignupEdtPassword.getText().toString();
                Log.e("Username and password","username==>"+username+"password==>"+password);
                    QBUser qbUser = new QBUser(username,password);
                    QBUsers.signUp(qbUser).performAsync(new QBEntityCallback<QBUser>() {
                        @Override
                        public void onSuccess(QBUser qbUser, Bundle bundle) {
                            Toast.makeText(ActivitySignup.this, "SingUp Successfully", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onError(QBResponseException e) {
                            Log.e("Error",e.getMessage());
                        }
                    });

            }
        });
    }

    private void Signupsession() {
        QBAuth.createSession().performAsync(new QBEntityCallback<QBSession>() {
            @Override
            public void onSuccess(QBSession qbSession, Bundle bundle) {

            }

            @Override
            public void onError(QBResponseException e) {

            }
        });
    }
}
