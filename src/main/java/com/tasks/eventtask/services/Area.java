package com.tasks.eventtask.services;

import javax.validation.constraints.NotNull;

import com.tasks.eventtask.constraints.Latitude;
import com.tasks.eventtask.constraints.Longitude;

public class Area{
    @NotNull
    @Latitude
    private double top;

    @NotNull
    @Longitude
    private double left;

    @NotNull
    @Latitude
    private double bottom;

    @NotNull
    @Longitude
    private double right;

    public Area() {
    }

    public Area(double lat1, double lng1, double lat2, double lng2) {
        if (lat2 >= lat1) {
            top = lat2;
            bottom = lat1;
        } else {
            top = lat1;
            bottom = lat2;
        }
        
        if (lng1 >= lng2) {
            left = lng2;
            right = lng1;
        } else {
            left = lng1;
            right = lng2;
        }
    }

    public double getTop() {
        return this.top;
    }

    public void setTop(double top) {
        this.top = top;
    }

    public double getLeft() {
        return this.left;
    }

    public void setLeft(double left) {
        this.left = left;
    }

    public double getBottom() {
        return this.bottom;
    }

    public void setBottom(double bottom) {
        this.bottom = bottom;
    }

    public double getRight() {
        return this.right;
    }

    public void setRight(double right) {
        this.right = right;
    }

}
