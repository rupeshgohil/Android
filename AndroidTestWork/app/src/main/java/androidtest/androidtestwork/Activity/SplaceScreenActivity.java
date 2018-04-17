package androidtest.androidtestwork.Activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidtest.androidtestwork.R;
import androidtest.androidtestwork.databinding.ActivitySpacescreenBinding;

/**
 * Created by Aru on 24-03-2018.
 */

public class SplaceScreenActivity extends BaseActivity {
    public ActivitySpacescreenBinding mainBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_spacescreen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mainBinding.gifFrame.setVisibility(View.GONE);
                Intent mIntent = new Intent(SplaceScreenActivity.this,MainActivity.class);
                startActivity(mIntent);
                finish();
            }
        },5000);
    }
}