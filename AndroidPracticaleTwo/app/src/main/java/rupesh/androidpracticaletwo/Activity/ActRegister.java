package rupesh.androidpracticaletwo.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONException;

import java.net.URLEncoder;

import rupesh.androidpracticaletwo.Connection.OpenHttpConnection;
import rupesh.androidpracticaletwo.Interface.Listner;
import rupesh.androidpracticaletwo.R;
import rupesh.androidpracticaletwo.Utils.CommonValidation;
import rupesh.androidpracticaletwo.Utils.Utils;

/**
 * Created by Aru on 22-09-2017.
 */

public class ActRegister extends AppCompatActivity{
    public Button btn_Register;
    public EditText et_Name,et_City,et_Email,et_Phone,et_Password,et_Conf_Password;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        et_Name = (EditText) findViewById(R.id.et_reg_name);
        et_City = (EditText) findViewById(R.id.et_reg_city);
        et_Email = (EditText) findViewById(R.id.et_reg_email);
        et_Phone = (EditText) findViewById(R.id.et_reg_phone);
        et_Password = (EditText) findViewById(R.id.et_reg_password);
        et_Conf_Password = (EditText) findViewById(R.id.et_reg_cpassword);
        btn_Register = (Button) findViewById(R.id.btn_sumbit);
        btn_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterCallApi();
            }
        });
    }

    private void RegisterCallApi() {

       // CommonValidation.ShowTost(ActRegister.this,Name+""+City+""+Email+""+Phone+""+Password+""+Conf_password);
            if(Utils.CheckInternetConnection(getApplicationContext()))
            {
                if(CheckValidation())
                {

                }

            }else{
                CommonValidation.ShowTost(ActRegister.this,"No Enternet Connection");
            }
    }

    private boolean CheckValidation() {

        if(CommonValidation.isEditTextEmpty(et_Name))
        {
            CommonValidation.ShowTost(getApplicationContext(),"Enter Name");
        }else if(CommonValidation.isEditTextEmpty(et_Email))
        {
            CommonValidation.ShowTost(getApplicationContext(),"Enter Email");
        }else if(CommonValidation.isEmailIdInvalid(et_Email)){
            CommonValidation.ShowTost(getApplicationContext(),"Enter Valid Email ");
        }else if(CommonValidation.isEditTextEmpty(et_Phone)){
            CommonValidation.ShowTost(getApplicationContext(),"Enter Phone");
        }else if(CommonValidation.isEditTextEmpty(et_City)){
            CommonValidation.ShowTost(getApplicationContext(),"Enter City");
        }else if(CommonValidation.isEditTextEmpty(et_Password)){
            CommonValidation.ShowTost(getApplicationContext(),"Enter Password");
        }else if(CommonValidation.isEditTextEmpty(et_Conf_Password)){
            CommonValidation.ShowTost(getApplicationContext(),"Enter Confirm Password");
        }else  if(et_Password.getText().toString().trim().
                equalsIgnoreCase(et_Conf_Password.getText().toString().trim())){
                NewHttpCallRegidter();
            return true;

        }else{
            CommonValidation.ShowTost(getApplicationContext(),"Password Does not Match");
            return false;
        }
        return false;
    }

    private void NewHttpCallRegidter() {
        String Name = URLEncoder.encode(et_Name.getText().toString().trim());
        String City = URLEncoder.encode(et_City.getText().toString().trim());
        String Email = URLEncoder.encode(et_Email.getText().toString().trim());
        String Phone = URLEncoder.encode(et_Phone.getText().toString().trim());
        String Password = URLEncoder.encode(et_Password.getText().toString().trim());
        String Conf_password = URLEncoder.encode(et_Conf_Password.getText().toString().trim());
        String strUrl = Utils.BaseURL + "register.php?type=0&name=" + Name + "&phone=" + Phone + "&email=" + Email + "&password=" + Password + "&type=" + "1" + "&city=" + City;
        new OpenHttpConnection(getApplicationContext(),strUrl, new Listner(){

            @Override
            public void onSuccess(Object object) {

            }

            @Override
            public void onFail(Object object) throws JSONException {

            }
        }).execute();

    }

}
