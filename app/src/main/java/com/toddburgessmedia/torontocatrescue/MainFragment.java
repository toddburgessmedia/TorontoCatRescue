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
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.toddburgessmedia.torontocatrescue.data.Pet;
import com.toddburgessmedia.torontocatrescue.data.PetList;
import com.toddburgessmedia.torontocatrescue.presenter.PetListPresenter;
import com.toddburgessmedia.torontocatrescue.view.PetListView;
import com.toddburgessmedia.torontocatrescue.view.RecyclerViewPetListAdapter;

import javax.inject.Inject;

import butterknife.BindArray;
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

    @BindView(R.id.tcr_fragment_age_spinner)
    Spinner age;

    @BindView(R.id.tcr_fragment_sex_spinner)
    Spinner sex;

    @BindArray(R.array.age_spinner)
    String[] ageArray;

    @BindArray(R.array.sex_spinner)
    String[] sexArray;

    RecyclerViewPetListAdapter adapter;

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
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.tcrmain_menu, menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.menu_main_refresh) {
            presenter.getPetList();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.tcrmain_fragment, container, false);
        ButterKnife.bind(this,view);

        rv.setLayoutManager(new GridLayoutManager(getActivity(), getColumnSize()));
        rv.setHasFixedSize(true);

        createSpinners();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (savedInstanceState != null) {
            presenter.restoreInstanceState(savedInstanceState.getParcelable("petlist"));
        } else {
            presenter.getPetList();
        }
    }

    private void createSpinners() {
        ArrayAdapter<CharSequence> ageAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.age_spinner, R.layout.spinner_item);
        ageAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        age.setAdapter(ageAdapter);

        ArrayAdapter<CharSequence> sexAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.sex_spinner, R.layout.spinner_item);
        sexAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sex.setAdapter(sexAdapter);

        age.setOnItemSelectedListener(new PetSpinner());

        sex.setOnItemSelectedListener(new PetSpinner());
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putParcelable("petlist", presenter.saveInstanceState());
    }

    public void startProgressDialog() {

        if (progress == null) {
            progress = new ProgressDialog(getActivity());
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

    @Override
    public void updatePetList(PetList petList) {

        rv.invalidate();
        adapter.updateList(petList);
    }

    @Override
    public void displayPetList(PetList petList) {
            adapter = new RecyclerViewPetListAdapter(getActivity(), petList.getPetList(), this);
            rv.setAdapter(adapter);
    }

    @Override
    public void onError() {

        Toast.makeText(getActivity(), R.string.network_error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClickPet(Pet pet) {
        Intent i = new Intent(getActivity(), PetDetailActivity.class);
        i.putExtra(PetDetailActivity.PETID, pet.getPetID());
        getActivity().startActivity(i);

    }


    public class PetSpinner implements AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            presenter.getPetsbySexAge(sexArray[sex.getSelectedItemPosition()],
                    ageArray[age.getSelectedItemPosition()]);
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }

}
