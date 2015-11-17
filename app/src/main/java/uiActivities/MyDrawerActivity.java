package uiActivities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.design.widget.NavigationView;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import faidarecharge.com.faidarecharge.R;
import uiFragments.AboutFragment;
import uiFragments.HomeFragment;
import uiFragments.ReferAndEarnFragment;
import uiFragments.ShareFragment;
import uiFragments.WriteToUsFragment;


public class MyDrawerActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(toolbar != null) {
            toolbar.setTitle("Home");
        }
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.openDrawer, R.string.closeDrawer){

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };

        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment homeFragment = new HomeFragment();
        fragmentTransaction.replace(R.id.frame, homeFragment);
        fragmentTransaction.commit();
    }

    public void setToolbarTitle(String title) {
            toolbar.setTitle(title);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        this.menu = menu;
        return super.onPrepareOptionsMenu(menu);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        this.menu = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void hideMenu() {
        MenuItem item_down = menu.findItem(R.id.action_search);
        item_down.setVisible(false);
    }

    public void showMenu() {
        MenuItem item_down = menu.findItem(R.id.action_search);
        item_down.setVisible(true);
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        if(menuItem.isChecked()) menuItem.setChecked(false);
        else menuItem.setChecked(true);
        //Closing drawer on item click
        drawerLayout.closeDrawers();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        switch (menuItem.getItemId()) {
            case R.id.home:
                Fragment homeFragment = new HomeFragment();
                fragmentTransaction.replace(R.id.frame, homeFragment);
                fragmentTransaction.commit();
                return true;
            case R.id.what_is_faida_recharge:
                Fragment aboutFragment = new AboutFragment();
                fragmentTransaction.replace(R.id.frame, aboutFragment);
                fragmentTransaction.commit();
                return true;

            case R.id.write_to_us:
                Fragment writeToUsFrag = new WriteToUsFragment();
                fragmentTransaction.replace(R.id.frame, writeToUsFrag);
                fragmentTransaction.commit();
                return true;

            case R.id.refer_and_earn:
                Fragment refernearnFrag = new ReferAndEarnFragment();
                fragmentTransaction.replace(R.id.frame, refernearnFrag);
                fragmentTransaction.commit();
                return true;

            case R.id.share:
                Fragment shAREfrAG= new ShareFragment();
                fragmentTransaction.replace(R.id.frame, shAREfrAG);
                fragmentTransaction.commit();
                return true;
            default:
                return true;
        }
    }
}
