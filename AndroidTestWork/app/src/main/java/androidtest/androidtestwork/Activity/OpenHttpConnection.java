package androidtest.androidtestwork.Activity;

import android.app.VoiceInteractor;
import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import androidtest.androidtestwork.Interface.ResponceStatusListener;

/**
 * Created by Aru on 02-04-2018.
 */

public class OpenHttpConnection {
    public JsonObjectRequest mJsonObjectRequest;
    public StringRequest mStringRequest;
    ResponceStatusListener mListener;
    JSONObject jobj;
    public OpenHttpConnection(String url, final Context mContext, String sendrequestmethod, JSONObject jsonObjectpost, ResponceStatusListener responceStatusListener) {
        this.mListener = responceStatusListener;
        this.jobj = jsonObjectpost;
        if(sendrequestmethod.equalsIgnoreCase("GET")){

            mStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String jsonObject) {

                        try {
                            jobj = new JSONObject(jsonObject);
                            if(jsonObject != null){
                                mListener.OnSucess(jobj);
                            }else{
                                mListener.OnFail(jobj);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {

                }
            });
            RequestQueue requestQueue = Volley.newRequestQueue(mContext);
            requestQueue.add(mStringRequest);
        }else{
            jobj = new JSONObject();
            mJsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, jobj, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject jsonObject) {
                    if(jsonObject != null){
                        mListener.OnSucess(jsonObject);
                    }else{
                        mListener.OnFail(jsonObject);
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {

                }
            });
            RequestQueue requestQueue = Volley.newRequestQueue(mContext);
            requestQueue.add(mJsonObjectRequest);
        }

    }
}
