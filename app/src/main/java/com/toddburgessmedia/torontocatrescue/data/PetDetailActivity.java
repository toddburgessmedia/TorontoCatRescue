package com.toddburgessmedia.torontocatrescue.data;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.toddburgessmedia.torontocatrescue.PetDetailFragment;
import com.toddburgessmedia.torontocatrescue.R;

public class PetDetailActivity extends AppCompatActivity {

    String petID;

    PetDetailFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_detail);

        petID = getIntent().getStringExtra("petID");

        fragment = new PetDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString("petID",petID);
        fragment.setArguments(bundle);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.petdetail_activity_framelayout, fragment, "fragment");
        transaction.commit();




    }
}
