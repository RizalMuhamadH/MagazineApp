package com.lumiere.user.magazineapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lumiere.user.magazineapp.Activity.ArtikelActivity;
import com.lumiere.user.magazineapp.Activity.CoverMagazineActivity;
import com.lumiere.user.magazineapp.Model.CoverModel;
import com.lumiere.user.magazineapp.R;
import com.lumiere.user.magazineapp.Utility.TypefaceUtil;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;

/**
 * Created by user on 04/11/2017.
 */

public class ViewPagerCoverAdapter extends PagerAdapter {
    private LayoutInflater inflater;
    private ArrayList<CoverModel> list;
    private Context context;

    private ImageView image;
    private TextView text;
    private Intent intent;

    public ViewPagerCoverAdapter(ArrayList<CoverModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        TypefaceUtil fontChanger = new TypefaceUtil(context.getAssets(),"fonts/Roboto-Regular.ttf");
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.cover_layout,container,false);
        fontChanger.replaceFonts((ViewGroup)v);
        image = (ImageView)v.findViewById(R.id.image_cover);
        text = (TextView)v.findViewById(R.id.txt_cover);

        String url = list.get(position).getUrl();
        Picasso.with(context)
                .load(url)
                .fit()
                .into(image);
//        image.setImageResource(R.drawable.logo);
        text.setText(list.get(position).getText());

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((CoverMagazineActivity)context).goToCoverPage();
            }
        });
        container.addView(v);
        return v;
    }
//    private Target target = new Target() {
//        @Override
//        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
//            image.setImageBitmap(bitmap);
//        }
//
//        @Override
//        public void onBitmapFailed(Drawable errorDrawable) {
//
//        }
//
//        @Override
//        public void onPrepareLoad(Drawable placeHolderDrawable) {
//
//        }
//    };

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout)object);
    }
}
