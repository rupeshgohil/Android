package androidtest.androidtestwork.Activity;

import android.content.Context;
import android.database.DatabaseUtils;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import org.json.JSONObject;

import java.util.HashMap;

import androidtest.androidtestwork.Interface.ResponceStatusListener;
import androidtest.androidtestwork.R;
import androidtest.androidtestwork.Utilitiy.Utils;
import androidtest.androidtestwork.databinding.ActivitysoapBinding;

/**
 * Created by Aru on 07-04-2018.
 */

public class ActivitySoap extends BaseActivity{
    public Context mContext;
    public ActivitysoapBinding mActivitySoapBandin;
    public String urlpath;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivitySoapBandin = DataBindingUtil.setContentView(this, R.layout.activitysoap);
        MakeApiCall(mContext);
    }
    public void MakeApiCall(Context mContext){

        new OpenHttpConnectionSoap(mContext,new ResponceStatusListener(){

            @Override
            public void OnSucess(JSONObject jobj) {
                Log.e("Responce","==>"+jobj);

            }

            @Override
            public void OnFail(JSONObject jobj) {

            }
        }).execute();

    }
}
