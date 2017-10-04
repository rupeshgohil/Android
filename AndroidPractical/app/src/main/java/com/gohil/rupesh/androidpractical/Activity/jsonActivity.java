package com.gohil.rupesh.androidpractical.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.gohil.rupesh.androidpractical.Adapter.MyArticle;
import com.gohil.rupesh.androidpractical.R;
import com.gohil.rupesh.androidpractical.Utils.Utils;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aru on 19-07-2017.
 */

public class jsonActivity extends AppCompatActivity{
    private final String TAG = "jsonActivity";
    public RecyclerView mRecyclerView;
    public RecyclerView.LayoutManager mManager;
    public RecyclerView.Adapter mAdapter;
    public ProgressDialog mDialog;
    public JSONArray j_array;
    public JSONObject j_obj;
    public Gson mGson;
    List<Article> arraylist = new ArrayList<Article>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jsonwithgson);
        mRecyclerView = (RecyclerView) findViewById(R.id.jsonwithgsonview);
        mRecyclerView.setHasFixedSize(true);
        mManager = new LinearLayoutManager(jsonActivity.this);
        mRecyclerView.setLayoutManager(mManager);
        GetJsonData();

    }
    public void GetJsonData(){
        mDialog = new ProgressDialog(jsonActivity.this);
        mDialog.setMessage("Please wait...");
        mDialog.setCancelable(true);
        mDialog.show();

        StringRequest request = new StringRequest(
                Utils.NEWSAPI,new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.v("====>Response<====",response);
                try
                {

                    j_obj = new JSONObject(response);
                    String status = j_obj.getString("status");
                    String Source = j_obj.getString("source");
                  //  art.setSource(Source);
                    String sortBy = j_obj.getString("sortBy");

                    Log.v("====>ResponseItem<====",
                            "STATUS :"+status+"\n"
                            +"SOURCE :"+Source+"\n"
                            +"SORTBY :"+sortBy+"\n");
                    j_array = j_obj.getJSONArray("articles");

                    for(int i = 0 ;i<j_array.length(); i++)
                    {
                        Article art = new Article();
                        j_obj = j_array.getJSONObject(i);
                       /* String Source = j_obj.getString("source");
                        art.setSource(Source);*/
                        art.setAuthor(j_obj.getString("author"));
                        art.setTitle(j_obj.getString("title"));
                        art.setDescription(j_obj.getString("description"));
                        art.setUrl(j_obj.getString("url"));
                        art.setUrlToImage(j_obj.getString("urlToImage"));
                        art.setPublishedAt(j_obj.getString("publishedAt"));
                        arraylist.add(art);
                        mAdapter = new MyArticle(jsonActivity.this,arraylist);
                        mRecyclerView.setAdapter(mAdapter);
                    }


                } catch (JSONException e) {

                    e.printStackTrace();
                    if(mDialog.isShowing()){
                        mDialog.dismiss();
                    }
                }



                if(mDialog.isShowing()){
                    mDialog.dismiss();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
        RequestQueue req = Volley.newRequestQueue(this);
        req.add(request);

    }
    @Override
    public void onBackPressed() {
        Intent i = new Intent(jsonActivity.this,MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
        finish();
        super.onBackPressed();
    }
}
