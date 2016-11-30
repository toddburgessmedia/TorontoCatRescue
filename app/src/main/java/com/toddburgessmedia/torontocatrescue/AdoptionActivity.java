package com.toddburgessmedia.torontocatrescue;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.toddburgessmedia.torontocatrescue.data.PetDetailInfo;

public class AdoptionActivity extends AppCompatActivity {

    AdoptionFragment fragment;
    private Tracker tracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adoption);

        tracker = ((TorontoCatRescue) getApplication()).getTracker();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        if (savedInstanceState != null) {
            fragment = (AdoptionFragment) getSupportFragmentManager().getFragment(savedInstanceState, "fragment");

        } else {
            PetDetailInfo pi = (PetDetailInfo) getIntent().getExtras().getParcelable("petDetail");
            fragment = new AdoptionFragment();
            Bundle bundle = new Bundle();
            bundle.putString("description", pi.getAdoptionProcess());
            bundle.putString("phonenumber", pi.getAreaCode() + pi.getPhoneNumber());
            bundle.putString("emailto", pi.getEmail());
            bundle.putString("petname", pi.getPetName());
            bundle.putString("url", getIntent().getStringExtra("url"));
            fragment.setArguments(bundle);

            tracker.setScreenName("AdoptionActivity");
            tracker.send(new HitBuilders.ScreenViewBuilder().build());
        }

        FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
        trans.replace(R.id.adoption_frame_layout, fragment, "fragment");
        trans.commit();

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        getSupportFragmentManager().putFragment(outState,"fragment",fragment);
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
