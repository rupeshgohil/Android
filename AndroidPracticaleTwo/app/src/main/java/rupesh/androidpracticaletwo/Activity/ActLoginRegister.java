package rupesh.androidpracticaletwo.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import rupesh.androidpracticaletwo.R;
import rupesh.androidpracticaletwo.Utils.CommonValidation;
import rupesh.androidpracticaletwo.Utils.Constant;
import rupesh.androidpracticaletwo.Utils.Utils;

/**
 * Created by Aru on 17-09-2017.
 */

public class ActLoginRegister extends AppCompatActivity implements View.OnClickListener {
    public Toolbar mToolbar;
    public Button btn_Login;
    public EditText et_Username,et_password;
    public TextView txt_register;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actloginregister);
       /* mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle(R.string.ToolbarTitle);
        mToolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(mToolbar);*/

        et_Username = (EditText) findViewById(R.id.edt_username);
        et_password = (EditText) findViewById(R.id.edt_password);
        btn_Login = (Button) findViewById(R.id.btnLogin);
        btn_Login.setOnClickListener(this);
        txt_register = (TextView) findViewById(R.id.txt_register);
        txt_register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btnLogin:
                if(Utils.CheckInternetConnection(getApplicationContext())) {
                    if (CheckValidation()) {
                        callLoginApi();
                    }
                }else {

                CommonValidation.CustomDialog(ActLoginRegister.this);
                }
                break;
            case R.id.txt_register:
                startActivity(new Intent(getApplicationContext(),ActRegister.class));
                break;
        }
    }

    private void callLoginApi() {
       // String url = Utils.BaseURL + "register.php?type=0&name=" + name + "&phone=" + phone + "&email=" + email + "&password=" + pass + "&type=" + "1" + "&city=" + city;

    }

    private boolean CheckValidation() {
        if(CommonValidation.isEditTextEmpty(et_Username))
        {
            CommonValidation.ShowTost(getApplicationContext(),"Please Enter UserName");
        }else if(CommonValidation.isEditTextEmpty(et_password)){
            CommonValidation.ShowTost(getApplicationContext(),"Please Enter Password");
        }else{
            return true;
        }
        return false;
    }
}
