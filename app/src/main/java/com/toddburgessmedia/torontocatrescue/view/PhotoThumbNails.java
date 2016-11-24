package com.toddburgessmedia.torontocatrescue.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.squareup.picasso.Picasso;
import com.toddburgessmedia.torontocatrescue.R;
import com.toddburgessmedia.torontocatrescue.data.PetDetailImage;

import java.util.ArrayList;

/**
 * Created by Todd Burgess (todd@toddburgessmedia.com on 23/11/16.
 */

public class PhotoThumbNails extends LinearLayout {

    ImageView thumb1;
    ImageView thumb2;
    ImageView thumb3;
    ImageView thumb4;
    ArrayList<ImageView> thumbnails;
    int size = 0;

    ImageView mainImage;

    ArrayList<PetDetailImage> imageList;
    Context context;

    public PhotoThumbNails(Context context) {
        super(context);
        this.context = context;
        initViews(context);
    }

    public PhotoThumbNails(Context context, AttributeSet attrs) {
        super(context,attrs);
        this.context = context;
        initViews(context);
    }

    public PhotoThumbNails(Context context, AttributeSet attrs, int defStyle) {
        super(context,attrs,defStyle);
        this.context = context;
        initViews(context);
    }

    private void initViews (Context context) {

        setOrientation(HORIZONTAL);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.photothumbnails, this);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        thumb1 = (ImageView) this.findViewById(R.id.photothumbnails_1);
        thumb2 = (ImageView) this.findViewById(R.id.photothumbnails_2);
        thumb3 = (ImageView) this.findViewById(R.id.photothumbnails_3);
        thumb4 = (ImageView) this.findViewById(R.id.photothumbnails_4);

        thumbnails = new ArrayList<>(4);
        thumbnails.add(thumb1);
        thumbnails.add(thumb2);
        thumbnails.add(thumb3);
        thumbnails.add(thumb4);
    }

    public void setMainImage (ImageView iv) {

        mainImage = iv;
    }

    public void setThumbNailImages (ArrayList<PetDetailImage> images) {
        imageList = images;
    }

    public void initView() {

        PetDetailImage pi;
        ImageView iv;
        for (int i = 0; i < imageList.size(); i++) {
            pi = imageList.get(i);
            iv = thumbnails.get(i);
            Picasso.with(context).load(pi.getThumbnailUrl()).into(iv);
            iv.setVisibility(VISIBLE);
            iv.setOnClickListener(new PhotoThumbNailListener(pi.getOriginalUrl(), mainImage));
            size++;
        }

        if (size > 0) {
            Picasso.with(context).load(imageList.get(0).getOriginalUrl()).into(mainImage);
        }

    }

    public class PhotoThumbNailListener implements View.OnClickListener {

        String url;
        ImageView main;

        public PhotoThumbNailListener(String url,ImageView iv) {
            this.url = url;
            main = iv;
        }


        @Override
        public void onClick(View view) {
            Picasso.with(context).load(url).into(main);
        }
    }
}
