package com.luccasroid.like.nastya.videos.app.Adapter;

import java.io.Serializable;

public class AppLive implements Serializable {
    private String Title;
    private String Thumb;
    private String IDvideo;

    public AppLive() {
    }

    public AppLive(String title, String thumb, String IDvideo) {
        Title = title;
        Thumb = thumb;
        this.IDvideo = IDvideo;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getThumb() {
        return Thumb;
    }

    public void setThumb(String thumb) {
        Thumb = thumb;
    }

    public String getIDvideo() {
        return IDvideo;
    }

    public void setIDvideo(String IDvideo) {
        this.IDvideo = IDvideo;
    }

    @Override
    public String toString() {
        return "AppLive{" +
                "Title='" + Title + '\'' +
                ", Thumb='" + Thumb + '\'' +
                ", IDvideo='" + IDvideo + '\'' +
                '}';
    }
}
