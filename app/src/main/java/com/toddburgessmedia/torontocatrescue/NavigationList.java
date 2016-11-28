package com.toddburgessmedia.torontocatrescue;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

/**
 * Created by Todd Burgess (todd@toddburgessmedia.com on 28/11/16.
 */

public class NavigationList extends ArrayAdapter<String> {

    String[] items;
    int[] icons;
    Context context;

    public NavigationList (Context context, String[] items, int[] icons) {
        super(context,R.layout.drawer_list_view,items);

        this.context = context;
        this.items = items;
        this.icons = icons;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.drawer_list_view, null, true);

        TextView tv = (TextView) view.findViewById(R.id.drawer_text);
        ImageView iv = (ImageView) view.findViewById(R.id.drawer_image);
        iv.setImageResource(icons[position]);
        tv.setText(items[position]);

        return view;
    }
}