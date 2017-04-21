package com.lumiere.user.magazineapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lumiere.user.magazineapp.Model.AnnouncementModel;
import com.lumiere.user.magazineapp.R;
import com.lumiere.user.magazineapp.Utility.TypefaceUtil;

import java.util.ArrayList;

/**
 * Created by user on 04/19/2017.
 */

public class AnnouncementAdapter extends BaseAdapter {

    private ArrayList<AnnouncementModel> list = new ArrayList<>();
    private Context context;

    public AnnouncementAdapter(ArrayList<AnnouncementModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TypefaceUtil fontChanger = new TypefaceUtil(context.getAssets(),"fonts/Roboto-Regular.ttf");
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.list_announcement,parent,false);
        fontChanger.replaceFonts((ViewGroup)v);
        TextView inbox = (TextView)v.findViewById(R.id.subject_inbox);
        TextView date = (TextView)v.findViewById(R.id.date_inbox);

        inbox.setText(list.get(position).getInbox());
        date.setText(list.get(position).getDate());
        return v;
    }
}
