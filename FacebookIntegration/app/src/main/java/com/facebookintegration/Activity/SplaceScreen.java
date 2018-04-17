package com.facebookintegration.Activity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.util.Base64;
import android.util.Log;

import com.facebookintegration.R;
import com.facebookintegration.databinding.ActivitySpacescreenBinding;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Aru on 14-04-2018.
 */

public class SplaceScreen extends BaseActivity {
    public ActivitySpacescreenBinding mActivitySpacescreenBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivitySpacescreenBinding = DataBindingUtil.setContentView(this,R.layout.activity_spacescreen);

        try{
            PackageInfo info = getPackageManager().getPackageInfo("com.facebookintegration", PackageManager.GET_SIGNATURES);
            for(Signature signature : info.signatures){
                MessageDigest messageDigest = MessageDigest.getInstance("SHA");
                messageDigest.update(signature.toByteArray());
                Log.e("KeyHash:", Base64.encodeToString(messageDigest.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent mIntent = new Intent(SplaceScreen.this,MainActivity.class);
                startActivity(mIntent);
            }
        },5000);
    }

}
