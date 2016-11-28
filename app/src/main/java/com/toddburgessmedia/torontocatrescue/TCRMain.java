package com.toddburgessmedia.torontocatrescue;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.toddburgessmedia.torontocatrescue.view.RecyclerViewPetListAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;
import javax.inject.Named;

import butterknife.BindArray;
import butterknife.BindView;
import butterknife.ButterKnife;

public class TCRMain extends AppCompatActivity {

    MainFragment fragment;

    @BindView(R.id.tcrmain_drawer_layout)
    DrawerLayout drawerLayout;

    @BindView(R.id.tcrmain_drawer_listview)
    ListView listView;

    @BindArray(R.array.navigation_titles)
    String[] titles;

    @BindArray(R.array.navigation_actions)
    String[] actions;

    @Inject @Named("drawerIcons")
    int[] drawerIcons;

    ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onStart() {
        super.onStart();


        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tcrmain);

        ((TorontoCatRescue) getApplication()).getTcrComponent().inject(this);

        ButterKnife.bind(this);

        NavigationList list = new NavigationList(this, titles,drawerIcons);
        listView.setAdapter(list);
        listView.setOnItemClickListener(new DrawerItemClickListener());

        drawerToggle = getActionBarToggle();
        drawerLayout.setDrawerListener(drawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);


        fragment = new MainFragment();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.tcrmain_framelayout, fragment, "mainfragment");
        transaction.commit();
    }

    @Subscribe
    public void startPetDetailActivity (RecyclerViewPetListAdapter.PetListClickMessage message) {

        Intent i = new Intent(this, PetDetailActivity.class);
        i.putExtra("petID", message.getPetID());
        i.putExtra("petURL", message.getPetURL());
        i.putExtra("petName", message.getPetName());
        startActivity(i);
    }

    private ActionBarDrawerToggle getActionBarToggle () {
        return new ActionBarDrawerToggle(
                            this,
                            drawerLayout,
                            R.string.drawer_open,
                            R.string.drawer_close)
            {
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

        return super.onOptionsItemSelected(item);
    }

    private void handleNavBarAction(int position) {
        switch (actions[position]) {

            case "web":
                Intent intent = new Intent(this,PetWebView.class);
                intent.putExtra("url",getString(R.string.main_web_site));
                startActivity(intent);
                break;
            case "bonded":
                Intent bonded = new Intent(this,PetWebView.class);
                bonded.putExtra("url",getString(R.string.bonded_web_site));
                startActivity(bonded);
                break;
            case "facebook":
                Intent facebook = new Intent(this,PetWebView.class);
                facebook.putExtra("url",getString(R.string.facebook_group_url));
                startActivity(facebook);
                break;
            case "available":
                getSupportActionBar().hide();
                break;
            case "volunteer":
                Intent volunteer = new Intent(this,PetWebView.class);
                volunteer.putExtra("url",getString(R.string.volunteer_url));
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


}
