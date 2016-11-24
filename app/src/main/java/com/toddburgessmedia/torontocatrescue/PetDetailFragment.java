package com.toddburgessmedia.torontocatrescue;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.toddburgessmedia.torontocatrescue.data.PetDetailInfo;
import com.toddburgessmedia.torontocatrescue.model.PetListModel;
import com.toddburgessmedia.torontocatrescue.view.PhotoThumbNails;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Todd Burgess (todd@toddburgessmedia.com on 23/11/16.
 */

public class PetDetailFragment extends Fragment {

    @BindView(R.id.petdetail_frag_header)
    TextView header;

    @BindView(R.id.petdetail_frag_mainimage)
    ImageView mainImage;

    @BindString(R.string.petdetail_greeting)
    String greeting;

    @BindView(R.id.petdetail_frag_thumbnails)
    PhotoThumbNails thumbNails;

    @BindView(R.id.petdetail_frag_facts)
    TextView facts;

    @BindString(R.string.petdetail_facts)
    String factsPrefix;

    @BindView(R.id.petdetail_frag_breed)
    TextView breed;

    @BindView(R.id.petdetail_frag_colour)
    TextView colour;

    @BindView(R.id.petdetail_frag_age)
    TextView age;

    @BindView(R.id.petdetail_frag_sex)
    TextView sex;

    @BindView(R.id.petdetail_frag_hair)
    TextView hair;

    @Inject
    PetListModel petListModel;

    @BindView(R.id.petdetail_frag_add_facts)
    TextView addFacts;

    @BindString(R.string.petdetail_add_info)
    String addInfo;

    @BindString(R.string.petdetail_Spayed)
    String spayed;

    @BindString(R.string.petdetail_Neutered)
    String neutered;

    @BindView(R.id.petdetail_frag_fix_header)
    TextView fixedHeader;

    @BindView(R.id.petdetail_frag_fixed)
    TextView fixed;

    @BindView(R.id.petdetail_frag_shots)
    TextView shots;

    @BindView(R.id.petdetail_frag_kids)
    TextView kids;

    @BindView(R.id.petdetail_frag_cats)
    TextView cats;

    @BindView(R.id.petdetail_frag_dogs)
    TextView dogs;

    @BindView(R.id.petdetail_frag_story)
    TextView story;


    String petID;

    @Override
    public void onStart() {
        super.onStart();

        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    public void onStop() {
        super.onStop();

        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((TorontoCatRescue) getActivity().getApplication()).getTcrComponent().inject(this);

        petID = getArguments().getString("petID");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.petdetail_fragment, container, false);
        ButterKnife.bind(this, view);

        thumbNails.setMainImage(mainImage);

        petListModel.fetchPetDetail(petID);
        return view;
    }

    @Subscribe
    public void updateView (PetListModel.PetDetailMessage message) {

        PetDetailInfo info = message.getPetDetail();

        header.setText(greeting + " " + info.getPetName());

        thumbNails.setThumbNailImages(info.getPetImages());
        thumbNails.initView();

        facts.setText(factsPrefix + " " + info.getPetName());
        breed.setText(info.getPrimaryBreed());
        colour.setText(info.getFurColour());
        age.setText(info.getAge());
        sex.setText(info.getSex());
        hair.setText(info.getFurColour());

        addFacts.setText(addInfo + " " + info.getPetName());
        fixedHeader.setText(getFixedStatus(info.getSex()));
        fixed.setText(setBooleanText(info.getSpayed()));
        shots.setText(setBooleanText(info.getShotsCurrent()));
        kids.setText(setBooleanText(info.getGoodWithKids()));
        cats.setText(setBooleanText(info.getGoodWithCats()));
        dogs.setText(setBooleanText(info.getGoodWithDogs()));

        story.setText(Html.fromHtml(info.getDescription()));
    }

    private String getFixedStatus(String sex) {

        if (sex.equals("Male")) {
            return neutered;
        } else if (sex.equals("Female")) {
            return spayed;
        } else {
            return neutered + "/" + spayed;
        }
    }

    private String setBooleanText(String state) {

        if (state == null) {
            return "Not specified";
        } else if (state.equals("1")) {
            return "Yes";
        } else  {
            return "No";
        }

    }

}
