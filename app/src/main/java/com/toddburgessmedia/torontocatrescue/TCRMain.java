package com.toddburgessmedia.torontocatrescue;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import javax.inject.Inject;
import javax.inject.Named;

import butterknife.BindArray;
import butterknife.BindView;
import butterknife.ButterKnife;

import static com.toddburgessmedia.torontocatrescue.dagger.Injector.getAppComponent;

public class TCRMain extends AppCompatActivity {

    MainFragment fragment;
    final String FRAGMENT = "mainfragment";

    @BindView(R.id.tcrmain_drawer_layout)
    DrawerLayout drawerLayout;

    @BindView(R.id.tcrmain_drawer_listview)
    ListView listView;

    @BindArray(R.array.navigation_titles)
    String[] titles;

    @BindArray(R.array.navigation_actions)
    String[] actions;

    @Inject
    @Named("drawerIcons")
    int[] drawerIcons;

    @BindView(R.id.tcrmain_age_spinner)
    Spinner age;

    @BindView(R.id.tcrmain_sex_spinner)
    Spinner sex;

    @BindArray(R.array.age_spinner)
    String[] ageArray;

    @BindArray(R.array.sex_spinner)
    String[] sexArray;

    ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        getAppComponent().inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tcrmain);

        ButterKnife.bind(this);

        createNavigationDrawer();

        createSpinners();

        if (savedInstanceState != null) {
            fragment = (MainFragment) getSupportFragmentManager().getFragment(savedInstanceState, FRAGMENT);
        } else {
            fragment = new MainFragment();
        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.tcrmain_framelayout, fragment, FRAGMENT);
        transaction.commit();
    }

    private void createSpinners() {
        ArrayAdapter<CharSequence> ageAdapter = ArrayAdapter.createFromResource(this, R.array.age_spinner, R.layout.spinner_item);
        ageAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        age.setAdapter(ageAdapter);

        ArrayAdapter<CharSequence> sexAdapter = ArrayAdapter.createFromResource(this, R.array.sex_spinner, R.layout.spinner_item);
        sexAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sex.setAdapter(sexAdapter);

        age.setOnItemSelectedListener(new PetSpinner());

        sex.setOnItemSelectedListener(new PetSpinner());
    }

    private void createNavigationDrawer() {
        NavigationList list = new NavigationList(this, titles, drawerIcons);
        listView.setAdapter(list);
        listView.setOnItemClickListener(new DrawerItemClickListener());

        drawerToggle = getActionBarToggle();
        drawerLayout.addDrawerListener(drawerToggle);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        getSupportFragmentManager().putFragment(outState,FRAGMENT,fragment);
    }

    private ActionBarDrawerToggle getActionBarToggle() {
        return new ActionBarDrawerToggle(
                this,
                drawerLayout,
                R.string.drawer_open,
                R.string.drawer_close) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
            }


        };
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        if (item.getItemId() == R.id.menu_main_refresh) {
            //fragment.getPetList(true);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void handleNavBarAction(int position) {
        switch (actions[position]) {

            case "web":
                Intent intent = new Intent(this, PetWebView.class);
                intent.putExtra(PetWebView.URL, getString(R.string.main_web_site));
                startActivity(intent);
                break;
            case "bonded":
                Intent bonded = new Intent(this, PetWebView.class);
                bonded.putExtra(PetWebView.URL, getString(R.string.bonded_web_site));
                startActivity(bonded);
                break;
            case "facebook":
                Intent facebook = new Intent(this, PetWebView.class);
                facebook.putExtra(PetWebView.URL, getString(R.string.facebook_group_url));
                startActivity(facebook);
                break;
            case "volunteer":
                Intent volunteer = new Intent(this, PetWebView.class);
                volunteer.putExtra(PetWebView.URL, getString(R.string.volunteer_url));
                startActivity(volunteer);
                break;
            case "donate":
                Intent donate = new Intent(Intent.ACTION_VIEW);
                donate.setData(Uri.parse(getString(R.string.donate_url)));
                startActivity(donate);
                break;
        }
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            handleNavBarAction(i);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.tcrmain_menu, menu);
        return true;
    }

    public class PetSpinner implements AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            fragment.getPetsbySexAge(sexArray[sex.getSelectedItemPosition()],
                    ageArray[age.getSelectedItemPosition()]);
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }

}


