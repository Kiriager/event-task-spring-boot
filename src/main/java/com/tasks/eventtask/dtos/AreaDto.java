package com.tasks.eventtask.dtos;

import javax.validation.constraints.NotNull;

import com.tasks.eventtask.constraints.Latitude;
import com.tasks.eventtask.constraints.Longitude;

public class AreaDto {
    @NotNull
    @Latitude
    private double lat1;

    @NotNull
    @Longitude
    private double lng1;

    @NotNull
    @Latitude
    private double lat2;

    @NotNull
    @Longitude
    private double lng2;

    public AreaDto() {
    }

    public AreaDto(double lat1, double lng1, double lat2, double lng2) {
        this.lat1 = lat1;
        this.lng1 = lng1;
        this.lat2 = lat2;
        this.lng2 = lng2;
    }


    public double getLat1() {
        return this.lat1;
    }

    public void setLat1(double lat1) {
        this.lat1 = lat1;
    }

    public double getLng1() {
        return this.lng1;
    }

    public void setLng1(double lng1) {
        this.lng1 = lng1;
    }

    public double getLat2() {
        return this.lat2;
    }

    public void setLat2(double lat2) {
        this.lat2 = lat2;
    }

    public double getLng2() {
        return this.lng2;
    }

    public void setLng2(double lng2) {
        this.lng2 = lng2;
    }

}
