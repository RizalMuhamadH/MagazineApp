package com.lumiere.user.magazineapp.Model;

/**
 * Created by user on 04/26/2017.
 */

public class Edition {
    private String editionMagazin;
    private String date;
    private String url;

    public Edition(String editionMagazin, String date, String url) {
        this.editionMagazin = editionMagazin;
        this.date = date;
        this.url = url;
    }

    public String getEditionMagazin() {
        return editionMagazin;
    }

    public String getDate() {
        return date;
    }

    public String getUrl() {
        return url;
    }
}
