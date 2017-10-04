package com.gohil.rupesh.androidpractical.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.gohil.rupesh.androidpractical.Adapter.MyAdapter;
import com.gohil.rupesh.androidpractical.Adapter.SimpleDividerItemDecoration;
import com.gohil.rupesh.androidpractical.R;
import com.gohil.rupesh.androidpractical.Utils.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aru on 14-07-2017.
 */

public class ToollbarJsonActivity extends AppCompatActivity{

    Toolbar toolbar;
    JSONArray j_array;
    JSONObject j_obj;
    private ProgressDialog mDialog;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    List<Population> arraylist = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.toolbarandjsonactivity);
        initToolbar();
        mRecyclerView = (RecyclerView)findViewById(R.id.toolbarjson);
        mRecyclerView.setHasFixedSize(true);
       // mRecyclerView.addItemDecoration(new SimpleDividerItemDecoration(this));
        mLayoutManager = new LinearLayoutManager(ToollbarJsonActivity.this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        getJsonData();
    }
    public void initToolbar(){

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.ToolbarTitle);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(), "clicking the toolbar!", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(ToollbarJsonActivity.this,MainActivity.class);
                startActivity(i);
            }
        });
    }
    public void getJsonData(){
        mDialog = new ProgressDialog(this);
        mDialog.setMessage("Please Wait");
        mDialog.setCancelable(false);
        mDialog.show();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Utils.GET_JSON_DATA_HTTP_URL,

                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        for(int i = 0; i<response.length(); i++) {

                            Population GetDataAdapter2 = new Population();

                            JSONObject json = null;
                            try {
                                json = response.getJSONObject(i);

                                GetDataAdapter2.setid(json.getString("id"));

                                GetDataAdapter2.setname(json.getString("name"));

                                GetDataAdapter2.setsubject(json.getString("subject"));

                                GetDataAdapter2.setphone_number(json.getString("phone_number"));

                            } catch (JSONException e) {

                                e.printStackTrace();
                            }
                            arraylist.add(GetDataAdapter2);
                            mAdapter = new MyAdapter(getApplicationContext(),arraylist);
                            mRecyclerView.setAdapter(mAdapter);
                        }
                        if(mDialog.isShowing()){
                            mDialog.dismiss();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(getApplicationContext(), volleyError.getMessage(), Toast.LENGTH_SHORT).show();
                if(mDialog.isShowing()){
                    mDialog.dismiss();
                }

            }
        });
        RequestQueue req = Volley.newRequestQueue(this);
        req.add(jsonArrayRequest);


    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(ToollbarJsonActivity.this,MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
        finish();
        super.onBackPressed();
    }
}
