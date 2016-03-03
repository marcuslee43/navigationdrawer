package com.vivianaranha.navigationdrawer;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity implements AdapterView.OnItemClickListener {

    private DrawerLayout drawerLayout;
    private ListView listView;
    private String[] people;
    private ActionBarDrawerToggle drawerListener;
    private TextView tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        people = getResources().getStringArray(R.array.people);
        listView = (ListView) findViewById(R.id.drawerList);
        listView.setOnItemClickListener(this);
        tv = (TextView) findViewById(R.id.textView);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);



        drawerListener = new ActionBarDrawerToggle(this, drawerLayout, R.drawable.ic_action_name, R.string.drawer_open, R.string.drawer_close){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                Toast.makeText(MainActivity.this, "Drawer Open",Toast.LENGTH_LONG).show();

            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                Toast.makeText(MainActivity.this, "Drawer Closed",Toast.LENGTH_LONG).show();

            }
        };
        drawerLayout.setDrawerListener(drawerListener);
        getActionBar().setHomeButtonEnabled(true);
        getActionBar().setDisplayHomeAsUpEnabled(true);
    }

    //Orientation changes
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
//        drawerListener.onConfigurationChanged(newConfig);
    }


    //Click on the ActionBar is forwarded to drawerListener
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
            if(drawerListener.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //Makes sure that for the action bar proper icon shows up + activity based on drawe open or close
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerListener.syncState();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, people[position], Toast.LENGTH_LONG).show();
        listView.setItemChecked(position, true);
        getActionBar().setTitle(people[position]);
        tv.setText(people[position]);
    }
}
