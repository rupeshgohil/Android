package rupesh.androidpracticaletwo.Activity;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import rupesh.androidpracticaletwo.R;

/**
 * Created by Aru on 11-11-2017.
 */

public class ActMenuEvent extends AppCompatActivity {


    Toolbar mToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_menuevent);
        mToolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mToolbar.inflateMenu(R.menu.menu_six);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_six, menu);
        return true;
    }

    boolean canAddItem = false;
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Toast toast;
        if(item.getItemId() == R.id.action_addItem){
            invalidateOptionsMenu();
        }
        else{
            toast = Toast.makeText(this, item.getTitle()+" Clicked!", Toast.LENGTH_SHORT);
            toast.show();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        if(canAddItem){
            menu.getItem(0).setIcon(R.drawable.ic_menu_delete);
            MenuItem mi = menu.add("New Item");
            mi.setIcon(R.drawable.ic_phone);
            mi.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_IF_ROOM);
            canAddItem = false;
        }
        else{
            menu.getItem(0).setIcon(R.drawable.ic_menu_add);
            canAddItem = true;
        }

        return super.onPrepareOptionsMenu(menu);
    }

}
