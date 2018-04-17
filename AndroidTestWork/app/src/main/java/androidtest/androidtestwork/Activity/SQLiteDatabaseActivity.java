package androidtest.androidtestwork.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import androidtest.androidtestwork.Adapter.RecyclerViewAdapter;
import androidtest.androidtestwork.Database.LocatDataBase;
import androidtest.androidtestwork.Interface.RecyclerviewClickListener;
import androidtest.androidtestwork.Interface.ResponceStatusListener;
import androidtest.androidtestwork.Modal.Articale;
import androidtest.androidtestwork.R;
import androidtest.androidtestwork.Utilitiy.Utils;
import androidtest.androidtestwork.databinding.ActivitySqlitedatabaseBinding;

import static androidtest.androidtestwork.Utilitiy.Utils.iSInsert;

/**
 * Created by Aru on 24-03-2018.
 */

public class SQLiteDatabaseActivity extends BaseActivity{
    public ActivitySqlitedatabaseBinding mActivitySqlitedatabaseBinding;
    private RecyclerViewAdapter mRecyclerViewAdapter;
    ArrayList<Articale> articaleArrayList = new ArrayList<>();
    String url;
    JSONObject jsonObject;
    Context mContext;
    Toolbar mToolbar;
    LocatDataBase mDatabaseHalper;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivitySqlitedatabaseBinding = DataBindingUtil.setContentView(this, R.layout.activity_sqlitedatabase);
        mToolbar = (Toolbar)findViewById(R.id.toolbar);
        mDatabaseHalper = new LocatDataBase(this);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        MakeApiCall(this);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getApplicationContext());
        mActivitySqlitedatabaseBinding.recyclerview.setLayoutManager(manager);
        mActivitySqlitedatabaseBinding.recyclerview.setHasFixedSize(true);
        mActivitySqlitedatabaseBinding.recyclerview.setItemAnimator(new DefaultItemAnimator());
    }
    public void MakeApiCall(final Context mContext){
        final ProgressDialog progress = new ProgressDialog(this);
        progress.setMessage("Please wait... ");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setIndeterminate(true);
        progress.show();
          new OpenHttpConnection(Utils.NEWSAPI,mContext , Utils.GET,jsonObject,new ResponceStatusListener(){

            @Override
            public void OnSucess(JSONObject jobj) {
                try {
                    String source = jobj.getString("source");
                    JSONArray jsonArray = jobj.getJSONArray("articles");
                    for(int i=0; i<jsonArray.length(); i++){
                        Articale articale = new Articale();
                        JSONObject j_obj = jsonArray.getJSONObject(i);
                        articale.setAuthor(j_obj.getString("author"));
                        articale.setTitle(j_obj.getString("title"));
                        articale.setDescription(j_obj.getString("description"));
                        articale.setUrlToimage(j_obj.getString("urlToImage"));
                        articale.setPublishedAt(j_obj.getString("publishedAt"));
                       // mDatabaseHalper.InsetRecord(articale);
                       //articaleArrayList.add(articale);
                    }
                    iSInsert = false;
                    articaleArrayList = mDatabaseHalper.GetRecordFromLocal();
                    mRecyclerViewAdapter = new RecyclerViewAdapter(articaleArrayList,mContext, new RecyclerviewClickListener() {
                        @Override
                        public void Onclick(Articale articale) {
                                Toast.makeText(getApplicationContext(),"Author==>"+articale.getAuthor()+"title==>"+articale.getTitle(),Toast.LENGTH_SHORT).show();
                        }
                    });
                    mActivitySqlitedatabaseBinding.recyclerview.setAdapter(mRecyclerViewAdapter);
                    progress.dismiss();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void OnFail(JSONObject jobj) {
                setToast(getApplicationContext(),Utils.JSONNULLMESSAGE);
                progress.dismiss();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activitylogin,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_Details:
                FilterAtoZ();
             return true;
            case R.id.menu_Logout:
                FilterZtoA();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
    public void FilterAtoZ(){
        Collections.sort(articaleArrayList, new Comparator<Articale>() {
            @Override
            public int compare(Articale o1, Articale o2) {

                return o2.getAuthor().compareToIgnoreCase(o1.getAuthor());
            }
        });
        mActivitySqlitedatabaseBinding.recyclerview.setAdapter(mRecyclerViewAdapter);
        mRecyclerViewAdapter.notifyDataSetChanged();
    }
    public void FilterZtoA(){
        Collections.sort(articaleArrayList, new Comparator<Articale>() {
            @Override
            public int compare(Articale o1, Articale o2) {

                return o1.getAuthor().compareToIgnoreCase(o2.getAuthor());
            }
        });
        mActivitySqlitedatabaseBinding.recyclerview.setAdapter(mRecyclerViewAdapter);
        mRecyclerViewAdapter.notifyDataSetChanged();
    }
}
