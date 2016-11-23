package com.toddburgessmedia.torontocatrescue;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.toddburgessmedia.torontocatrescue.data.PetDetailActivity;
import com.toddburgessmedia.torontocatrescue.view.RecyclerViewPetListAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class TCRMain extends AppCompatActivity {

    MainFragment fragment;

    @Override
    protected void onStart() {
        super.onStart();

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
        setContentView(R.layout.activity_tcrmain);

        fragment = new MainFragment();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.tcrmain_framelayout, fragment, "mainfragment");
        transaction.commit();
    }

    @Subscribe
    public void startPetDetailActivity (RecyclerViewPetListAdapter.PetListClickMessage message) {

        Intent i = new Intent(this, PetDetailActivity.class);
        i.putExtra("petID", message.getPetID());
        startActivity(i);
    }


}
