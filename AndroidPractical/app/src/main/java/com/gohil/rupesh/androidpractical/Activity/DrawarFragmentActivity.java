package com.gohil.rupesh.androidpractical.Activity;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gohil.rupesh.androidpractical.Fragment.CameraFragment;
import com.gohil.rupesh.androidpractical.Fragment.GalleryFragment;
import com.gohil.rupesh.androidpractical.Fragment.SendFragment;
import com.gohil.rupesh.androidpractical.Fragment.ShareFragment;
import com.gohil.rupesh.androidpractical.Fragment.SlideshowFragment;
import com.gohil.rupesh.androidpractical.Fragment.ToolsFragment;
import com.gohil.rupesh.androidpractical.R;
/**
 * Created by Aru on 30-07-2017.
 */

public class DrawarFragmentActivity extends AppCompatActivity {

    public View view;
    public TextView Name,Email;
    NavigationView navigationView;
    public static int navItemIndex = 0;
    private DrawerLayout drawer;
    public Toolbar toolbar;
    private FloatingActionButton fab;
    private String[] activityTitles;
    private static String CAMERA = "Camera";
    private static String GALLERY = "Galeery";
    private static String SLIDESHOW = "SlideShow";
    private static String TOOLS = "Tools";
    private static String SEND = "Send";
    private static String SHARE = "Share";
    private static String CURRENT = CAMERA;
    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawarfragment);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mHandler = new Handler();
        drawer = (DrawerLayout) findViewById(R.id.Drawer_Layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        view =navigationView.getHeaderView(0);
        Name = (TextView)view.findViewById(R.id.name);
        Email = (TextView)view.findViewById(R.id.website);

        NavHeaderData();
        activityTitles = getResources().getStringArray(R.array.nav_item_activity_titles);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        SetNavigationView();
        if(savedInstanceState == null)
        {
            navItemIndex = 0;
            CURRENT = CAMERA;
            loadHomeFragment();
        }
    }

    private void loadHomeFragment() {
        setToolBarTitle();
        if (getSupportFragmentManager().findFragmentByTag(CURRENT) != null) {
            drawer.closeDrawer(GravityCompat.START);
            return;
        }
       Runnable mRunnable = new Runnable() {
           @Override
           public void run() {
               Fragment fragment = getHomeFragment();
               FragmentTransaction mFragmentTransaction = getSupportFragmentManager().beginTransaction();
               mFragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                       android.R.anim.fade_out);
               mFragmentTransaction.replace(R.id.frame, fragment, CURRENT);
               mFragmentTransaction.commitAllowingStateLoss();
           }
       };
        if (mRunnable != null) {
            mHandler.post(mRunnable);
        }
        toggleFab();
        drawer.closeDrawers();
        invalidateOptionsMenu();

    }

    private void toggleFab() {
        if(navItemIndex ==0)
        {
            fab.show();
        }else{
            fab.hide();
        }
    }

    private Fragment getHomeFragment() {
        switch (navItemIndex) {
            case 0:
                CameraFragment cameraFragment = new CameraFragment();
                return cameraFragment;
            case 1:
                GalleryFragment galleryFragment = new GalleryFragment();
                return galleryFragment;
            case 2:
                SlideshowFragment slideshowFragment = new SlideshowFragment();
                return slideshowFragment;
            case 3:
                ToolsFragment toolsFragment = new ToolsFragment();
                return toolsFragment;

            case 4:

                ShareFragment sherFragment = new ShareFragment();
                return sherFragment;
            case 5:
                SendFragment sendFragment = new SendFragment();
                return sendFragment;
            default:
                return new CameraFragment();
        }

    }

    private void setToolBarTitle() {
        getSupportActionBar().setTitle(activityTitles[navItemIndex]);
    }

    private void SetNavigationView() {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                switch (id)
                {
                    case R.id.nav_camera:
                        navItemIndex = 0;
                        CURRENT = CAMERA;
                        break;
                    case R.id.nav_gallery:
                        navItemIndex = 1;
                        CURRENT = GALLERY;
                        break;
                    case R.id.nav_slideshow:
                        navItemIndex = 2;
                        CURRENT = SLIDESHOW;
                        break;
                    case R.id.nav_manage:
                        navItemIndex = 3;
                        CURRENT = TOOLS;
                        break;
                    case R.id.nav_send:
                        navItemIndex = 4;
                        CURRENT = SHARE;
                        break;
                    case R.id.nav_share:
                        navItemIndex = 5;
                        CURRENT = SEND;
                        break;
                    default:
                        navItemIndex = 0;

                }
                if (item.isChecked()) {
                    item.setChecked(false);
                } else {
                    item.setChecked(true);
                }
                item.setChecked(true);
               // drawer.closeDrawer(GravityCompat.START);
                 loadHomeFragment();
                return true;
            }

        });
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer,toolbar , R.string.navigration_drawar_open, R.string.navigation_drawar_close) {

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                   super.onDrawerOpened(drawerView);
            }
        };

        drawer.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }

    private void NavHeaderData() {

        Name.setText("Rupesh Gohil");
        Email.setText("gohelrupesh@gmail.com");
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.Drawer_Layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout) {

            Intent i = new Intent(DrawarFragmentActivity.this,MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);

        }
            return true;

    }
}
