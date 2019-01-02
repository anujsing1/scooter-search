package com.beam.scooter.model;

public class LocationResponse {
    private String title;
    private Double lat;
    private Double lng;

    public LocationResponse(LocationEntity locationEntity) {
        this.title = locationEntity.getName();
        this.lat = locationEntity.getLocation().getY();
        this.lng = locationEntity.getLocation().getX();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }
}
