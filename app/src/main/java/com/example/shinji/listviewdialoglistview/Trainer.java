package com.example.shinji.listviewdialoglistview;

import android.graphics.Bitmap;

/**
 * Created by shinji on 2016/06/29.
 */
public class Trainer {
    private Bitmap icon;
    private String name;
    private String comment;

    public Bitmap getIcon() {
        return icon;
    }
    public void setIcon(Bitmap icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }


}