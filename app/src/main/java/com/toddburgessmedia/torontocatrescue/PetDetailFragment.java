package com.toddburgessmedia.torontocatrescue;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.toddburgessmedia.torontocatrescue.data.LimitedPetDetail;
import com.toddburgessmedia.torontocatrescue.data.PetDetailInfo;
import com.toddburgessmedia.torontocatrescue.model.PetListModel;
import com.toddburgessmedia.torontocatrescue.view.PhotoThumbNails;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.text.MessageFormat;

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

    @BindView(R.id.petdetail_frag_bonded_cardview)
    CardView bonded;

    @BindView(R.id.petdetail_frag_important_message)
    TextView importantMessage;

    @BindView(R.id.petdetail_frag_important_photo)
    ImageView importantPhoto;

    @BindString(R.string.petdetail_important_message)
    String message;

    @BindString(R.string.petdetail_email_subject)
    String emailSubject;

    @BindString(R.string.petdetail_email_subject_bonded)
    String emailSubjectBonded;

    String catName;

    ProgressDialog progress;

    String petID;
    private PetDetailInfo info;
    private LimitedPetDetail limitedBonded;
    private LimitedPetDetail limitedPet;

    @BindView(R.id.petdetail_frag_infobutton)
    Button moreInfo;

    @BindView(R.id.petdetail_frag_adoptbutton)
    Button adoptButton;

    @BindString(R.string.petdetail_button_adopt)
    String adoptText;

    @BindString(R.string.petdetail_email_body)
    String emailBody;

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
        catName = getArguments().getString("petName");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.petdetail_fragment, container, false);
        ButterKnife.bind(this, view);

        thumbNails.setMainImage(mainImage);
        moreInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().startActivity(Intent.createChooser(getMoreInformation(), "Send E-Mail"));
            }
        });



        if (savedInstanceState != null) {
            petID = savedInstanceState.getString("petID");
            catName = savedInstanceState.getString("catName");
            info = savedInstanceState.getParcelable("info");
            limitedBonded = savedInstanceState.getParcelable("bonded");
            limitedPet = savedInstanceState.getParcelable("limitedPet");
            updateView();
            if (limitedBonded != null) {
                addBondedCardView();
            }
        } else {
            progress = new ProgressDialog(getContext());
            progress.setMessage("Loading " + catName);
            progress.show();

            getPetInformation();
        }
        adoptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), AdoptionActivity.class);
                i.putExtra("petDetail", info);
                i.putExtra("url", limitedPet.getPetDetailsUrl());
                startActivity(i);
            }
        });
        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString("petID",petID);
        outState.putString("catName",catName);
        outState.putParcelable("info",info);
        if (bonded != null) {
            outState.putParcelable("bonded",limitedBonded);
        }
        outState.putParcelable("limitedPet",limitedPet);

    }



    public void getPetInformation() {
        petListModel.fetchPetDetail(petID);
        petListModel.fetchLimtedPetDetail(petID,false);
    }

    @Subscribe
    public void updateView (PetListModel.PetDetailMessage message) {

        progress.dismiss();
        info = message.getPetDetail();

        updateView();
    }

    private void updateView() {
        header.setText(subPetName(info.getPetName().toUpperCase(),greeting));

        thumbNails.setThumbNailImages(info.getPetImages());
        thumbNails.initView();

        facts.setText(subPetName(info.getPetName(),factsPrefix));
        breed.setText(info.getPrimaryBreed());
        colour.setText(info.getFurColour());
        age.setText(info.getAge());
        sex.setText(info.getSex());

        addFacts.setText(subPetName(info.getPetName(),addInfo));
        fixed.setText(getFixedStatus(info.getSex()));
        setAdditionalInfoTextView(shots, info.getShotsCurrent());
        setAdditionalInfoTextView(kids, info.getGoodWithKids());
        setAdditionalInfoTextView(cats, info.getGoodWithCats());
        setAdditionalInfoTextView(dogs, info.getGoodWithDogs());

        story.setText(Html.fromHtml(info.getDescription()));

        if (info.getBondedTo() != null) {
            if (limitedBonded != null) {
                addBondedCardView();
            } else {
                catName = info.getPetName();
                petListModel.fetchLimtedPetDetail(info.getBondedTo(), true);
            }
        }

        adoptButton.setText(subPetName(info.getPetName(),adoptText));
    }

    @Subscribe
    public void updateBondedInfo(PetListModel.LimitedPetDetailMessage limitedPetDetailMessage) {

        if (!limitedPetDetailMessage.getFlag()) {
            Log.d("TCR", "updateBondedInfo: " + limitedPetDetailMessage.getLimitedPetDetail().getPetName());
            limitedPet = limitedPetDetailMessage.getLimitedPetDetail();
            return;
        }

        limitedBonded = limitedPetDetailMessage.getLimitedPetDetail();

        addBondedCardView();

    }

    private void addBondedCardView() {
        MessageFormat mf = new MessageFormat(message);
        String[] subs = {catName, limitedBonded.getPetName()};
        String display = mf.format(subs);


        bonded.setVisibility(View.VISIBLE);
        bonded.setOnClickListener(new BondedClickListener(limitedBonded.getPetID()));
        Picasso.with(getContext()).load(limitedBonded.getImages().get(0).getThumbnailUrl()).into(importantPhoto);
        importantPhoto.setVisibility(View.VISIBLE);
        importantMessage.setText(display);
    }

    private String subPetName(String petName, String targetText) {

        MessageFormat mf = new MessageFormat(targetText);
        String[] subs = {petName};
        return mf.format(subs);

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

    private void setAdditionalInfoTextView(TextView infoTV, String value) {

        String text = infoTV.getText().toString();
        String newtext;

        Log.d("TCR", "setAdditionalInfoTextView: " + infoTV.getId() + " " + value);

        if (value == null) {
            infoTV.setVisibility(View.GONE);
            newtext = "";
        } else if (value.equals("1")) {
            newtext = text;
        } else if (value.equals("0")) {
            infoTV.setTextColor(Color.RED);
            newtext = "Not " + text;
        } else {
            infoTV.setVisibility(View.GONE);
            newtext = "";
        }
        infoTV.setText(newtext);
    }

    public class BondedClickListener implements View.OnClickListener {

        public String petID;

        public BondedClickListener (String petID) {
            this.petID = petID;
        }

        @Override
        public void onClick(View view) {
            Intent i = new Intent(getContext(), PetDetailActivity.class);
            i.putExtra("petID", petID);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            getActivity().startActivity(i);
        }
    }

    private Intent getMoreInformation() {

        String subject;
        if (info.getBondedTo() != null) {
            MessageFormat mf = new MessageFormat(emailSubjectBonded);
            String[] subs = {info.getPetName(), limitedBonded.getPetName()};
            subject = mf.format(subs);
        } else {
            subject = subPetName(info.getPetName(),emailSubject);
        }


        String[] to = {info.getEmail()};

        Intent email = new Intent(Intent.ACTION_SEND);
        email.setData(Uri.parse("mailto:"));
        email.setType("text/plain");
        email.putExtra(Intent.EXTRA_EMAIL, to);
        email.putExtra(Intent.EXTRA_SUBJECT, subject);
        email.putExtra(Intent.EXTRA_TEXT, getEmailBody());

        return email;
    }

    private String getEmailBody () {

        MessageFormat mf = new MessageFormat(emailBody);
        String[] subs = {info.getPetName(),limitedPet.getPetDetailsUrl()};
        return mf.format(subs);
    }

    @Subscribe
    public void onError (Throwable t) {

        Toast.makeText(getContext(), R.string.network_error, Toast.LENGTH_SHORT).show();
    }


    public class ShareIntentInfoMessage {

        String url;
        String petName;

        public ShareIntentInfoMessage(String url, String petName) {
            this.url = url;
            this.petName = petName;
        }

        public String getPetName() {
            return petName;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

}
