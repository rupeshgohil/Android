package com.gohil.rupesh.androidpractical.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.gohil.rupesh.androidpractical.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public Button btn1,btn2,btn3,btn4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = (Button)findViewById(R.id.button1);
        btn1.setOnClickListener(this);
        btn2 = (Button)findViewById(R.id.button2);
        btn2.setOnClickListener(this);
        btn3 = (Button)findViewById(R.id.button3);
        btn3.setOnClickListener(this);
        btn4 = (Button)findViewById(R.id.button4);
        btn4.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case  R.id.button1:
                Intent i = new Intent(MainActivity.this,ToollbarJsonActivity.class);
                startActivity(i);
                finish();
                break;
            case R.id.button2:
                Intent i2 = new Intent(MainActivity.this,jsonActivity.class);
                startActivity(i2);
                finish();
                break;

            case R.id.button3:
                Intent i3 = new Intent(MainActivity.this,DrawarFragmentActivity.class);
                startActivity(i3);
                finish();
                break;
            case R.id.button4:
                Intent i4 = new Intent(MainActivity.this,CameraImageActivity.class);
                startActivity(i4);
                finish();
                break;
        }
    }
}
