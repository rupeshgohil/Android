package aru.jsonsqlite;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Created by Aru on 24-03-2018.
 */

public class SplaceScreenActivity extends AppCompatActivity {
    public FrameLayout frameLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spacescreen);
        frameLayout = (FrameLayout)findViewById(R.id.gif_frame);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                frameLayout.setVisibility(View.GONE);
                Intent mIntent = new Intent(SplaceScreenActivity.this,MainActivity.class);
                startActivity(mIntent);
                finish();
            }
        },5000);
    }
}