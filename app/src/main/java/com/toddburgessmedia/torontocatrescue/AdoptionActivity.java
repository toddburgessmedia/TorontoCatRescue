package com.toddburgessmedia.torontocatrescue;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.toddburgessmedia.torontocatrescue.data.PetDetail;
import com.toddburgessmedia.torontocatrescue.data.PetDetailInfo;

public class AdoptionActivity extends AppCompatActivity {

    public static final String URL = "url";
    public static final String PETDETAIL = "petDetail";

    AdoptionFragment fragment;
    final String FRAGMENT = "adoptionFragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adoption);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        if (savedInstanceState != null) {
            fragment = (AdoptionFragment) getSupportFragmentManager().getFragment(savedInstanceState,FRAGMENT);

        } else {
            PetDetail petDetail = getIntent().getExtras().getParcelable(PETDETAIL);
            PetDetailInfo pi = petDetail.getPetDetailInfo();
            fragment = new AdoptionFragment();
            Bundle bundle = new Bundle();
            bundle.putString("description", pi.getAdoptionProcess());
            bundle.putString("phonenumber", pi.getAreaCode() + pi.getPhoneNumber());
            bundle.putString("emailto", pi.getEmail());
            bundle.putString("petname", pi.getPetName());
            bundle.putString(URL, petDetail.getPetURL());
            fragment.setArguments(bundle);
        }


    }

    @Override
    protected void onStart() {
        super.onStart();

        FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
        trans.replace(R.id.adoption_frame_layout, fragment, FRAGMENT);
        trans.commit();

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
