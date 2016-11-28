package com.toddburgessmedia.torontocatrescue;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.Space;
import android.telephony.TelephonyManager;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.text.MessageFormat;

import butterknife.BindString;
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

    @BindView(R.id.adoption_frag_spacer)
    Space spacer;

    @BindView(R.id.adoption_frag_email_button)
    Button emailButton;

    @BindString(R.string.adopt_email_body)
    String emailBody;

    @BindString(R.string.adopt_email_subject)
    String emailSubject;

    String adoptionString;
    String phoneNumber;
    String emailTo;
    String bioURL;
    private String petName;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        adoptionString = getArguments().getString("description");
        phoneNumber = getArguments().getString("phonenumber");
        emailTo = getArguments().getString("emailto");
        bioURL = getArguments().getString("url");
        petName = getArguments().getString("petname");

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.adoption_fragment, container, false);
        ButterKnife.bind(this, view);

        description.setText(Html.fromHtml(adoptionString));

        if (((TelephonyManager)getContext().getSystemService(Context.TELEPHONY_SERVICE)).getPhoneType()
                == TelephonyManager.PHONE_TYPE_NONE)
        {
         call.setVisibility(View.GONE);
            spacer.setVisibility(View.GONE);
        }

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+phoneNumber));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getActivity().startActivity(intent);
            }
        });

        emailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().startActivity(Intent.createChooser(getEmailIntent(), "Send E-Mail"));
            }
        });

        return view;
    }

    private Intent getEmailIntent() {

        String subject;
        MessageFormat mf = new MessageFormat(emailSubject);
        String[] subs = {petName};
        subject = mf.format(subs);

        String body;
        MessageFormat mfb = new MessageFormat(emailBody);
        String[] bodysubs = {petName,bioURL};
        body = mfb.format(bodysubs);


        String[] to = {emailTo};

        Intent email = new Intent(Intent.ACTION_SEND);
        email.setData(Uri.parse("mailto:"));
        email.setType("text/plain");
        email.putExtra(Intent.EXTRA_EMAIL, to);
        email.putExtra(Intent.EXTRA_SUBJECT, subject);
        email.putExtra(Intent.EXTRA_TEXT, body);

        return email;
    }

}
