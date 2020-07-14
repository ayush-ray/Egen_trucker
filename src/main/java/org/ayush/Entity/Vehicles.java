package org.ayush.Entity;

import javax.persistence.*;

@Entity
public class Vehicles {

    @Id
    @Column(columnDefinition = "VARCHAR(17)")
    private String vin;

    @Column(columnDefinition = "VARCHAR(16)")
    private String make;

    @Column(columnDefinition = "VARCHAR(16)")
    private String model;

    @Column(columnDefinition = "VARCHAR(4)")
    private int year;

    @Column(columnDefinition = "INTEGER(4)")
    private int redlineRpm;

    @Column(columnDefinition = "VARCHAR(3)")
    private int maxFuelVolume;

    private String lastServiceDate;

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public int getRedlineRpm() {
        return redlineRpm;
    }

    public void setRedlineRpm(Object redlineRpm) {
        this.redlineRpm = (int) redlineRpm;
    }

    public int getMaxFuelVolume() {
        return maxFuelVolume;
    }

    public void setMaxFuelVolume(Integer maxFuelVolume) {
        this.maxFuelVolume = maxFuelVolume;
    }

    public String getLastServiceDate() {
        return lastServiceDate;
    }

    public void setLastServiceDate(String lastServiceDate) {
        this.lastServiceDate = lastServiceDate;
    }
}
