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

    PetDetailFragment fragment;
    final String FRAGMENT = "petDetailFragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_detail);
        ButterKnife.bind(this);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }

        if (savedInstanceState != null) {
            fragment = (PetDetailFragment) getSupportFragmentManager().getFragment(savedInstanceState,FRAGMENT);
        } else {
            if (getIntent().getAction() == null) {
                petID = getIntent().getStringExtra(PETID);
            } else {
                getDeepLinkInfo(getIntent());
            }

            fragment = new PetDetailFragment();
            Bundle bundle = new Bundle();
            bundle.putString(PetDetailFragment.PETID, petID);
            fragment.setArguments(bundle);
        }

    }

    @Override
    protected void onStart() {
        super.onStart();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.petdetail_activity_framelayout, fragment, FRAGMENT);
        transaction.commit();
    }

    private void getDeepLinkInfo(Intent intent) {

        String link = intent.getData().toString();
        petID = link.replaceAll("\\D+", "");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

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
