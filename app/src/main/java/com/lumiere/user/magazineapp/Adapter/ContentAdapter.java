package com.lumiere.user.magazineapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lumiere.user.magazineapp.Model.Edition;
import com.lumiere.user.magazineapp.R;
import com.lumiere.user.magazineapp.Utility.TypefaceUtil;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by user on 04/11/2017.
 */

public class ContentAdapter extends BaseAdapter {

    private ArrayList<Edition> list = new ArrayList<>();
    private Context context;

    public ContentAdapter(ArrayList<Edition> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TypefaceUtil fontChanger = new TypefaceUtil(context.getAssets(),"fonts/Roboto-Regular.ttf");
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.content_layout,parent,false);
        fontChanger.replaceFonts((ViewGroup)v);
        TextView text = (TextView)v.findViewById(R.id.txt_content);
        ImageView img = (ImageView)v.findViewById(R.id.img_content);
        TextView date = (TextView)v.findViewById(R.id.txt_tanggal);
        text.setText(list.get(position).getEditionMagazin());
        date.setText(list.get(position).getDate());

        String url = list.get(position).getUrl();
        Picasso.with(context)
                .load(url)
                .fit()
                .into(img);
        return v;
    }
}
