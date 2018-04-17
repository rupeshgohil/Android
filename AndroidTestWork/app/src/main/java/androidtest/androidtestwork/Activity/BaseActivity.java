package androidtest.androidtestwork.Activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import androidtest.androidtestwork.R;

/**
 * Created by Aru on 23-03-2018.
 */

class BaseActivity extends AppCompatActivity{

    public void setToolBar(Toolbar mToolBar) {
        mToolBar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(mToolBar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mToolBar.setTitle("Android Example");
    }
    public void setToolBar(Toolbar mToolbar, int i) {

        if(i == 1){
            mToolbar = (Toolbar)findViewById(R.id.toolbar);
            setSupportActionBar(mToolbar);
            getSupportActionBar().setDisplayShowTitleEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            mToolbar.setTitle("Login And Signup");
        }
    }
    public void setToast(Context mContext,String message){
        Toast.makeText(mContext,message,Toast.LENGTH_SHORT).show();
    }
}
