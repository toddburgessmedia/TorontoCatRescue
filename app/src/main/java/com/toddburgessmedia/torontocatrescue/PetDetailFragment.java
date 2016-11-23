package com.toddburgessmedia.torontocatrescue;

import android.support.v4.app.Fragment;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by Todd Burgess (todd@toddburgessmedia.com on 23/11/16.
 */

public class PetDetailFragment extends Fragment {

    @BindView(R.id.petdetail_frag_header)
    TextView header;

    @BindView(R.id.petdetail_frag_mainimage)
    ImageView mainImage;

    String thumbIVprefix = "petdetail_frag_thumb_";
    ImageView thumbOne;

    ArrayList<ImageView> thumbnails;


}
