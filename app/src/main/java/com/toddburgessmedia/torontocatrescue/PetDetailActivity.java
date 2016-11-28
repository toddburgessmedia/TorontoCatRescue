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

import com.toddburgessmedia.torontocatrescue.data.LimitedPetDetail;
import com.toddburgessmedia.torontocatrescue.model.PetListModel;

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_detail);
        ButterKnife.bind(this);

        petID = getIntent().getStringExtra("petID");
        petURL = getIntent().getStringExtra("petURL");
        petName = getIntent().getStringExtra("petName");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

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
        if (shareIntent != null) {
            shareActionProvider.setShareIntent(shareIntent);
        }

        return true;
    }

    @Subscribe
    public void startAdoptionActivity(PetDetailFragment.AdoptionMessage message) {

        Log.d("TCR", "startAdoptionActivity: ");
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
        }

        return super.onOptionsItemSelected(item);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updateShareInfo(PetListModel.LimitedPetDetailMessage message) {

        Log.d("TCR", "updateShareInfo: HELLO??????!!!!!!!!");
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
