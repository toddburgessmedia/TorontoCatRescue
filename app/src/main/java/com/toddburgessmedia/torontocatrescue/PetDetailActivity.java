package com.toddburgessmedia.torontocatrescue;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class PetDetailActivity extends AppCompatActivity {

    String petID;
    String petURL;
    String petName;

    PetDetailFragment fragment;

    android.support.v7.widget.ShareActionProvider shareActionProvider;

    @Override
    protected void onStart() {
        super.onStart();

        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_detail);

        petID = getIntent().getStringExtra("petID");
        petURL = getIntent().getStringExtra("petURL");
        petName = getIntent().getStringExtra("petName");

        fragment = new PetDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString("petID",petID);
        fragment.setArguments(bundle);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.petdetail_activity_framelayout, fragment, "fragment");
        transaction.commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.tcrmain_menu,menu);

        MenuItem menuItem = menu.findItem(R.id.action_share);
        shareActionProvider = (android.support.v7.widget.ShareActionProvider) MenuItemCompat.getActionProvider(menuItem);

        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("text/*");
        i.putExtra(Intent.EXTRA_TEXT, "Check out " + petName);
        shareActionProvider.setShareIntent(i);

        return true;
    }

    @Subscribe
    public void startAdoptionActivity(PetDetailFragment.AdoptionMessage message) {

        Log.d("TCR", "startAdoptionActivity: ");
        Intent i = new Intent(PetDetailActivity.this, AdoptionActivity.class);
        i.putExtra("petDetail", message.getInfo());
        startActivity(i);

    }


}
