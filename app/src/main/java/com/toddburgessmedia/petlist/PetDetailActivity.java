package com.toddburgessmedia.petlist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.toddburgessmedia.petlist.data.LimitedPetDetail;
import com.toddburgessmedia.petlist.model.PetListModel;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.MessageFormat;

import butterknife.BindString;
import butterknife.ButterKnife;

public class PetDetailActivity extends AppCompatActivity {

    String petID;
    String petURL;
    String petName;

    PetDetailFragment fragment;
    LimitedPetDetail limitedPetDetail;

    @BindString(R.string.petdetail_share)
    String shareMessage;

    android.support.v7.widget.ShareActionProvider shareActionProvider;
    private Intent shareIntent;

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
    protected void onResume() {
        super.onResume();

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
        setContentView(R.layout.activity_pet_detail);
        ButterKnife.bind(this);




        if (savedInstanceState != null) {
            if (!EventBus.getDefault().isRegistered(this)) {
                EventBus.getDefault().register(this);
            }
            petID = savedInstanceState.getString("petID");
            petURL = savedInstanceState.getString("petURL");
            petName = savedInstanceState.getString("petName");
            fragment = (PetDetailFragment) getSupportFragmentManager().getFragment(savedInstanceState, "fragment");
        } else {
            petID = getIntent().getStringExtra("petID");
            petURL = getIntent().getStringExtra("petURL");
            petName = getIntent().getStringExtra("petName");

            fragment = new PetDetailFragment();
            Bundle bundle = new Bundle();
            bundle.putString("petID",petID);
            bundle.putString("petName", petName);
            fragment.setArguments(bundle);
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.petdetail_activity_framelayout, fragment, "fragment");
        transaction.commit();

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString("petID",petID);
        outState.putString("petURL",petURL);
        outState.putString("petName",petName);

        getSupportFragmentManager().putFragment(outState,"fragment",fragment);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.petdetail_menu,menu);

        MenuItem menuItem = menu.findItem(R.id.action_share);
        shareActionProvider = (android.support.v7.widget.ShareActionProvider) MenuItemCompat.getActionProvider(menuItem);
        if (shareIntent != null) {
            shareActionProvider.setShareIntent(shareIntent);
        }

        return true;
    }

    @Subscribe
    public void startAdoptionActivity(AdoptionMessage message) {

        Intent i = new Intent(PetDetailActivity.this, AdoptionActivity.class);
        i.putExtra("petDetail", message.getInfo());
        i.putExtra("url", limitedPetDetail.getPetDetailsUrl());
        startActivity(i);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        } else if (item.getItemId() == R.id.menu_petdetail_refresh) {
            fragment.getPetInformation();
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updateShareInfo(PetListModel.LimitedPetDetailMessage message) {

        if (message.getFlag()) {
            return;
        }

        limitedPetDetail = message.getLimitedPetDetail();
        MessageFormat mf = new MessageFormat(shareMessage);
        String[] subs = {petName,limitedPetDetail.getPetDetailsUrl()};
        String intentMessage = mf.format(subs);

        shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, intentMessage);
        invalidateOptionsMenu();
    }



}
