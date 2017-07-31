package com.toddburgessmedia.torontocatrescue;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

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
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }
    }

    private void getDeepLinkInfo(Intent intent) {

        String link = intent.getData().toString();
        petID = link.replaceAll("\\D+", "");
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
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
