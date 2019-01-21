package com.beam.scooter.model;

import java.util.List;

public class DropOffLocationQuantityResponse {

    private List<LocationQuantity> locationQuantities;
    private Integer proximateDistance;

    public Integer getProximateDistance() {
        return proximateDistance;
    }

    public void setProximateDistance(Integer proximateDistance) {
        this.proximateDistance = proximateDistance;
    }

    public List<LocationQuantity> getLocationQuantities() {
        return locationQuantities;
    }

    public void setLocationQuantities(List<LocationQuantity> locationQuantities) {
        this.locationQuantities = locationQuantities;
    }

}
