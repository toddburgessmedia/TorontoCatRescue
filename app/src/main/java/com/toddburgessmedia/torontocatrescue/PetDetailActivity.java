package com.toddburgessmedia.torontocatrescue;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
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

    public static final String PETID = "petID";
    public static final String PETURL = "petURL";
    public static final String PETNAME = "petName";

    String petID;
    String petURL;
    String petName;

    PetDetailFragment fragment;
    final String FRAGMENT = "petDetailFragment";
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
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_detail);
        ButterKnife.bind(this);

        if (savedInstanceState != null) {
            petID = savedInstanceState.getString(PETID);
            petURL = savedInstanceState.getString(PETURL);
            petName = savedInstanceState.getString(PETNAME);
            fragment = (PetDetailFragment) getSupportFragmentManager().getFragment(savedInstanceState,FRAGMENT);
        } else {
            if (getIntent().getAction() == null) {
                petID = getIntent().getStringExtra(PETID);
                petURL = getIntent().getStringExtra(PETURL);
                petName = getIntent().getStringExtra(PETNAME);
            } else {
                getDeepLinkInfo(getIntent());
            }

            fragment = new PetDetailFragment();
            Bundle bundle = new Bundle();
            bundle.putString(PetDetailFragment.PETID, petID);
            bundle.putString(PetDetailFragment.PETNAME, petName);
            fragment.setArguments(bundle);
        }

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.petdetail_activity_framelayout, fragment, FRAGMENT);
        transaction.commit();

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    private void getDeepLinkInfo(Intent intent) {

        String link = intent.getData().toString();
        int start = link.lastIndexOf('/') + 1;
        int end = link.indexOf('-');
        petID = link.substring(start, end);
        petURL = link;
        petName = "";
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString(PETID,petID);
        outState.putString(PETURL,petURL);
        outState.putString(PETNAME,petName);

        getSupportFragmentManager().putFragment(outState,FRAGMENT,fragment);
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
        String[] subs = {limitedPetDetail.getPetName(),limitedPetDetail.getPetDetailsUrl()};
        String intentMessage = mf.format(subs);

        shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, intentMessage);
        invalidateOptionsMenu();
    }



}
