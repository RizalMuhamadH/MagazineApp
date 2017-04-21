package com.lumiere.user.magazineapp.Model;

/**
 * Created by user on 04/19/2017.
 */

public class AnnouncementModel {
    private String inbox;
    private String date;

    public AnnouncementModel(String inbox, String date) {
        this.inbox = inbox;
        this.date = date;
    }

    public String getInbox() {
        return inbox;
    }

    public String getDate() {
        return date;
    }
}
