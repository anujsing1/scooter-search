package com.beam.scooter.model;

import java.io.Serializable;
import java.util.Objects;

public class LocationEntry implements Serializable {
    private String title;
    private Double longitude;
    private Double latitude;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getLongitude() {
        return this.longitude;
    }

    public void setLongitude(final Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return this.latitude;
    }

    public void setLatitude(final Double latitude) {
        this.latitude = latitude;
    }

    @Override
    public String toString() {
        return "LocationEntry{" +
                "longitude='" + longitude + '\'' +
                ", latitude='" + latitude + '\'' +
                '}';
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        final LocationEntry that = (LocationEntry) o;
        return Objects.equals(this.getLongitude(), that.getLongitude()) &&
                Objects.equals(this.getLatitude(), that.getLatitude());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getLongitude(), this.getLatitude());
    }
}
