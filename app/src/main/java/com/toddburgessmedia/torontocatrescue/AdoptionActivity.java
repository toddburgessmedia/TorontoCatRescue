package com.toddburgessmedia.torontocatrescue;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import com.toddburgessmedia.torontocatrescue.data.PetDetailInfo;

public class AdoptionActivity extends AppCompatActivity {

    AdoptionFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adoption);

        PetDetailInfo pi = (PetDetailInfo) getIntent().getExtras().getParcelable("petDetail");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        fragment = new AdoptionFragment();
        Bundle bundle = new Bundle();
        bundle.putString("description",pi.getAdoptionProcess());
        bundle.putString("phonenumber",pi.getAreaCode() + pi.getPhoneNumber());
        bundle.putString("emailto",pi.getEmail());
        bundle.putString("petname",pi.getPetName());
        bundle.putString("url",getIntent().getStringExtra("url"));
        fragment.setArguments(bundle);
        Log.d("TCR", "onCreate: " + pi.getAdoptionProcess());

        FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
        trans.replace(R.id.adoption_frame_layout, fragment, "fragment");
        trans.commit();

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
