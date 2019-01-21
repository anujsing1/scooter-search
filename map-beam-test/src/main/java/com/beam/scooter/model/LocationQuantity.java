package com.beam.scooter.model;

public class LocationQuantity {
    private Point point;
    private Integer quantity;

    public LocationQuantity(Point point, Integer quantity) {
        this.point=point;
        this.quantity = quantity;
    }

    public LocationQuantity(){

    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public Point getPoint() {
        return point;
    }

}
