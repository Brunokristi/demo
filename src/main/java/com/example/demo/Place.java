package com.example.demo;

import java.io.*;
import java.util.Scanner;

public class Place implements Serializable {

    private String creator;
    private String caption;
    private String city;
    private String[] activities;
    private double cost;
    private String[] invited;
    private Integer[] going;

    public Place() {
        this.creator = creator;
        this.caption = caption;
        this.city = city;
        this.activities = activities;
        this.cost = cost;
        this.invited = invited;
        this.going = going;
    }

    // Getters
    public String getCreatorPlace() {
        return creator;
    }

    public String getCaption() {
        return caption;
    }

    public String getCity() {
        return city;
    }

    public String[] getActivities() {
        return activities;
    }

    public double getCost() {
        return cost;
    }

    public String[] getInvited() {
        return invited;
    }

    public Integer[] getGoing() {
        return going;
    }

    // Setters
    public void setCreatorPlace(String creator) {
        this.creator = creator;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setActivities(String[] activities) {
        this.activities = activities;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public void setInvited(String[] invited) {
        this.invited = invited;
    }

    public void setGoing(Integer[] going) {
        this.going = going;
    }
}
