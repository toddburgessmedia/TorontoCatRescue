package com.toddburgessmedia.torontocatrescue;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

import com.toddburgessmedia.torontocatrescue.data.Pet;
import com.toddburgessmedia.torontocatrescue.data.PetList;
import com.toddburgessmedia.torontocatrescue.model.PetListModel;
import com.toddburgessmedia.torontocatrescue.presenter.PetListPresenter;
import com.toddburgessmedia.torontocatrescue.view.PetListView;
import com.toddburgessmedia.torontocatrescue.view.RecyclerViewPetListAdapter;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.toddburgessmedia.torontocatrescue.dagger.Injector.getAppComponent;

/**
 * Created by Todd Burgess (todd@toddburgessmedia.com on 21/11/16.
 */

public class MainFragment extends Fragment implements PetListView {

    final int MAXSCREENWIDTH = 1700;

    @BindView(R.id.tcr_fragment_rv)
    RecyclerView rv;

    RecyclerViewPetListAdapter adapter;
    ArrayList<Pet> petList;

    @Inject
    PetListModel petListModel;

    PetListPresenter presenter;

    @Inject
    WindowManager wm;

    ProgressDialog progress;

    @Inject
    public void getPresenter (PetListPresenter presenter) {
        this.presenter = presenter;
        this.presenter.setPetListView(this);
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        getAppComponent().inject(this);
        super.onCreate(savedInstanceState);


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.tcrmain_fragment, container, false);
        ButterKnife.bind(this,view);

        rv.setLayoutManager(new GridLayoutManager(getContext(), getColumnSize()));
        rv.setHasFixedSize(true);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (savedInstanceState != null) {
            PetList pl = savedInstanceState.getParcelable("petlist");
            if (pl != null) {
                petList = pl.getPetList();
                //updatePetList();
            }
        } else {
            presenter.getPetList();
        }

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        PetList pl = new PetList();
        pl.setPetList(petList);

        outState.putParcelable("petlist", pl);

    }

    public void startProgressDialog() {

        if (progress == null) {
            progress = new ProgressDialog(getContext());
        }
        progress.setMessage(getString(R.string.mainFragment_progress));
        progress.show();
    }

    private int getColumnSize() {

        DisplayMetrics metrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(metrics);

        int width = metrics.widthPixels;
        if (width > MAXSCREENWIDTH) {
            return 4;
        } else if (width > (MAXSCREENWIDTH - 600)) {
            return 3;
        } else {
            return 2;
        }
    }

    public void stopProgressDialog() {

        if (progress != null) {
            progress.dismiss();
        }
    }

    public void updatePetList(PetList petList) {
        adapter = new RecyclerViewPetListAdapter(getContext(), petList.getPetList(), this);
        rv.setAdapter(adapter);
    }

    public void getPetsbySexAge(String sex, String age) {

        if (petList == null) {
            return;
        }
        rv.invalidate();
        adapter.updateList(PetList.getPetsBySexAge(sex,age,petList));
    }

    public void onError() {

        Toast.makeText(getContext(), R.string.network_error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClickPet(Pet pet) {
        Intent i = new Intent(getContext(), PetDetailActivity.class);
        i.putExtra(PetDetailActivity.PETID, pet.getPetID());
        i.putExtra(PetDetailActivity.PETURL, pet.getDetailsURL());
        i.putExtra(PetDetailActivity.PETNAME, pet.getPetName());
        getActivity().startActivity(i);

    }
}
