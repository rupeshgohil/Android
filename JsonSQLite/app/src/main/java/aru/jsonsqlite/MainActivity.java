package aru.jsonsqlite;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import aru.jsonsqlite.Adapter.RecyclerViewAdapter;
import aru.jsonsqlite.Interface.RecyclerviewClickListener;
import aru.jsonsqlite.Interface.ResponceStatusListener;
import aru.jsonsqlite.Model.Articale;
import aru.jsonsqlite.Utility.Utils;


public class MainActivity extends AppCompatActivity {
    private RecyclerViewAdapter mRecyclerViewAdapter;
    ArrayList<Articale> articaleArrayList = new ArrayList<>();
    String url;
    JSONObject jsonObject;
    Context mContext;
    Toolbar mToolbar;
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        mToolbar = (Toolbar)findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        MakeApiCall(this);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
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
                        articaleArrayList.add(articale);
                    }
                     mRecyclerViewAdapter = new RecyclerViewAdapter(articaleArrayList,mContext, new RecyclerviewClickListener() {
                        @Override
                        public void Onclick(Articale articale) {
                            Toast.makeText(getApplicationContext(),"Author==>"+articale.getAuthor()+"title==>"+articale.getTitle(),Toast.LENGTH_SHORT).show();
                        }
                    });
                    mRecyclerView.setAdapter(mRecyclerViewAdapter);
                    progress.dismiss();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void OnFail(JSONObject jobj) {
                Toast.makeText(MainActivity.this, Utils.JSONNULLMESSAGE, Toast.LENGTH_SHORT).show();
                progress.dismiss();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activitylogin,menu);
        MenuItem myActionMenuItem = menu.findItem( R.id.search);
        final SearchView searchView = (SearchView)myActionMenuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                mRecyclerViewAdapter.setFilter(newText);
                return true;
            }
        });
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
        mRecyclerView.setAdapter(mRecyclerViewAdapter);
        mRecyclerViewAdapter.notifyDataSetChanged();
    }
    public void FilterZtoA(){
        Collections.sort(articaleArrayList, new Comparator<Articale>() {
            @Override
            public int compare(Articale o1, Articale o2) {

                return o1.getAuthor().compareToIgnoreCase(o2.getAuthor());
            }
        });
        mRecyclerView.setAdapter(mRecyclerViewAdapter);
        mRecyclerViewAdapter.notifyDataSetChanged();
    }
}
