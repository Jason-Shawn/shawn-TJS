package com.shawn.demo.model;

import java.io.Serializable;

public class Schedule implements Serializable {

    private String[] arrival;

    private String[] departure;

    public Schedule(String[] arrival, String[] departure) {
        this.arrival = arrival;
        this.departure = departure;
    }

    public Schedule(){}

    public String[] getArrival() {
        return arrival;
    }

    public void setArrival(String[] arrival) {
        this.arrival = arrival;
    }

    public String[] getDeparture() {
        return departure;
    }

    public void setDeparture(String[] departure) {
        this.departure = departure;
    }
}