package com.toddburgessmedia.torontocatrescue;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
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
import com.toddburgessmedia.torontocatrescue.model.PetListPresenter;
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

    @BindView(R.id.tcr_fragment_swiperefresh)
    SwipeRefreshLayout swipe;

    RecyclerViewPetListAdapter adapter;
    ArrayList<Pet> petList;

    @Inject
    PetListPresenter petListModel;

    @Inject
    WindowManager wm;

    ProgressDialog progress;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        getAppComponent().inject(this);
        petListModel.setPetListView(this);
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.tcrmain_fragment, container, false);
        ButterKnife.bind(this,view);

        rv.setLayoutManager(new GridLayoutManager(getContext(), getColumnSize()));
        rv.setHasFixedSize(true);

        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getPetList(false);
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (savedInstanceState != null) {
            PetList pl = savedInstanceState.getParcelable("petlist");
            if (pl != null) {
                petList = pl.getPetList();
                updateRecyclerView();
            }
        } else {
            startProgressDialog();
            getPetList(false);
        }

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        PetList pl = new PetList();
        pl.setPetList(petList);

        outState.putParcelable("petlist", pl);

    }

    private void startProgressDialog() {

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

    private void stopProgressDialog() {

        if (progress != null) {
            progress.dismiss();
        }
    }

    public void getPetList(boolean refresh) {

        if (refresh) {
            swipe.setRefreshing(true);
        }
        petListModel.fetchPetList();
    }

    public void updatePetListView(PetList petList) {
        this.petList = petList.getPetList();
        stopProgressDialog();
        updateRecyclerView();
    }

    public void updateRecyclerView() {
        adapter = new RecyclerViewPetListAdapter(getContext(), petList);
        swipe.setRefreshing(false);

        rv.setAdapter(adapter);
    }

    public void getPetsbySexAge(String sex, String age) {

        if (petList == null) {
            return;
        }
        rv.invalidate();
        adapter.updateList(PetList.getPetsBySexAge(sex,age,petList));
    }

    public void onError(Throwable t) {

        stopProgressDialog();
        Toast.makeText(getContext(), R.string.network_error, Toast.LENGTH_SHORT).show();
    }
}
