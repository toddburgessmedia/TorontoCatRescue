package com.toddburgessmedia.torontocatrescue.data;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.toddburgessmedia.torontocatrescue.R;

public class PetDetailActivity extends AppCompatActivity {

    String petID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_detail);

        petID = getIntent().getStringExtra("petID");
    }
}
