package androidtest.androidtestwork.Activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import androidtest.androidtestwork.R;
import androidtest.androidtestwork.databinding.ActivityMainBinding;

public class MainActivity extends BaseActivity {
    public static ActivityMainBinding mainBinding;
     public Toolbar mToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setToolBar(mToolbar);
        mainBinding.ly1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent mIntent = new Intent(MainActivity.this,SQLiteDatabaseActivity.class);
                startActivity(mIntent);
                overridePendingTransition(R.anim.left_to_right_animation,R.anim.right_to_left_animation);
            }
        });
        mainBinding.ly2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent miIntent_ry2 = new Intent(MainActivity.this,ActivitySoap.class);
                startActivity(miIntent_ry2);
                overridePendingTransition(R.anim.left_to_right_animation,R.anim.right_to_left_animation);
            }
        });
    }
}
