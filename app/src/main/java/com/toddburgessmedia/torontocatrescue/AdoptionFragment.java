package com.toddburgessmedia.torontocatrescue;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Todd Burgess (todd@toddburgessmedia.com on 25/11/16.
 */



public class AdoptionFragment extends Fragment {

    @BindView(R.id.adoption_frag_description)
    TextView description;

    @BindView(R.id.adoption_frag_call_button)
    Button call;

    String adoptionString;
    String phoneNumber;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        adoptionString = getArguments().getString("description");
        phoneNumber = getArguments().getString("phonenumber");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.adoption_fragment, container, false);
        ButterKnife.bind(this, view);

        description.setText(Html.fromHtml(adoptionString));

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+phoneNumber));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getActivity().startActivity(intent);
            }
        });


        return view;


    }
}
