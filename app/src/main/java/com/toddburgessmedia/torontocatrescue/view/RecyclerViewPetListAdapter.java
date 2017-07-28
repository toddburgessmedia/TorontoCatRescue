package com.toddburgessmedia.torontocatrescue.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.toddburgessmedia.torontocatrescue.R;
import com.toddburgessmedia.torontocatrescue.data.Pet;
import com.toddburgessmedia.torontocatrescue.data.PetList;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Todd Burgess (todd@toddburgessmedia.com on 21/11/16.
 */

public class RecyclerViewPetListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public final int PETVIEWTYPE = 1;

    ArrayList<Pet> petList;
    Context context;
    PetListView petListView;

    public RecyclerViewPetListAdapter (Context context, ArrayList<Pet> petList, PetListView petListView) {

        this.context = context;
        this.petList = new ArrayList<>(petList);
        this.petListView = petListView;

    }

    @Override
    public int getItemCount() {

        if (petList == null) {
            return 0;
        } else {

            return petList.size();
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v;

        switch (viewType) {
            case PETVIEWTYPE:
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_petlist, parent, false);
                return new ViewHolderPet(v);
            default:
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_petlist, parent, false);
                return new ViewHolderPet(v);
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        switch (getItemViewType(position)) {
            case PETVIEWTYPE:
                Pet p = petList.get(position);
                ViewHolderPet vp = (ViewHolderPet) holder;
                Picasso.with(context).load(p.getResultsPhotoURL()).into(vp.resultPhoto);
                vp.petName.setText(p.getPetName());
                vp.petName.setContentDescription(p.getPetName());
                vp.petSex.setText(getSex(p.getSex()));
                vp.breed.setText(p.getPrimaryBreed());
                vp.age.setText(getAge(p.getAge()));
        }

    }

    private String getAge(String age) {

        return age.substring(0, 1).toUpperCase() + age.substring(1);
    }

    private String getSex(String sex) {
        if (sex.equals("m")) {
            return "Male";
        } else {
            return "Female";
        }
    }

    public void updateList (PetList list) {

        ArrayList<Pet> newList = list.getPetList();
        petList.clear();
        petList.addAll(newList);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return PETVIEWTYPE;
    }

    protected class ViewHolderPet extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.rv_petlist_result_photo)
        ImageView resultPhoto;

        @BindView(R.id.rv_petlist_pet_name)
        TextView petName;

        @BindView(R.id.rv_petlist_sex)
        TextView petSex;

        @BindView(R.id.rv_petlist_primary_breed)
        TextView breed;

        @BindView(R.id.rv_petlist_age)
        TextView age;

        public ViewHolderPet (View view) {
            super(view);

            ButterKnife.bind(this, view);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Pet p = petList.get(getAdapterPosition());
            petListView.onClickPet(p);
        }
    }

}
