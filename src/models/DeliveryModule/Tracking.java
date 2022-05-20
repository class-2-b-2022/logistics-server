package models.DeliveryModule;

import java.io.Serializable;
import java.sql.Date;

public class Tracking implements  Serializable{
    private String driverName;
    private Integer trackerId;
    private String currentLocation;
    private String destination;
    private String sourceLocation;
    private String nearestStation;
    private String departTime;
    private String arriveTime;
    private String fuelLevel;

    public String getFuelLevel() {
        return fuelLevel;
    }

    public void setFuelLevel(String fuelLevel) {
        this.fuelLevel = fuelLevel;
    }

    private Date createdAt;

    public Tracking() {
    }

    public Tracking(String driverName, Integer trackerId,String fuelLevel, String currentLocation, String destination, String sourceLocation, String nearestStation, String departTime, String arriveTime, Date createdAt) {
        this.driverName = driverName;
        this.trackerId = trackerId;
        this.currentLocation = currentLocation;
        this.destination = destination;
        this.sourceLocation = sourceLocation;
        this.nearestStation = nearestStation;
        this.departTime = departTime;
        this.fuelLevel = fuelLevel;
        this.arriveTime = arriveTime;
        this.createdAt = createdAt;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public Integer getTrackerId() {
        return trackerId;
    }

    public void setTrackerId(Integer trackerId) {
        this.trackerId = trackerId;
    }

    public String getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getSourceLocation() {
        return sourceLocation;
    }

    public void setSourceLocation(String sourceLocation) {
        this.sourceLocation = sourceLocation;
    }

    public String getNearestStation() {
        return nearestStation;
    }

    public void setNearestStation(String nearestStation) {
        this.nearestStation = nearestStation;
    }

    public String getDepartTime() {
        return departTime;
    }

    public void setDepartTime(String departTime) {
        this.departTime = departTime;
    }

    public String getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(String arriveTime) {
        this.arriveTime = arriveTime;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
