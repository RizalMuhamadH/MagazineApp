package com.lumiere.user.magazineapp.Model;

import android.graphics.Bitmap;

/**
 * Created by user on 04/11/2017.
 */

public class CoverModel {
//    private Bitmap bitmap;

    private String url;
    private String text;


    public CoverModel(String url, String text) {
        this.url = url;
        this.text = text;
    }

//    public CoverModel(Bitmap bitmap, String text) {
//        this.bitmap = bitmap;
//        this.text = text;
//    }

    public String getUrl() {
        return url;
    }

//    public Bitmap getBitmap() {
//        return bitmap;
//    }

    public String getText() {
        return text;
    }
}
