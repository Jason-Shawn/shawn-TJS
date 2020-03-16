package com.shawn.demo.model;

public class Train {

    private Integer arrival;

    private Integer departure;

    public Train(Integer arrival, Integer departure) {
        this.arrival = arrival;
        this.departure = departure;
    }

    public Integer getArrival() {
        return arrival;
    }

    public void setArrival(Integer arrival) {
        this.arrival = arrival;
    }

    public Integer getDeparture() {
        return departure;
    }

    public void setDeparture(Integer departure) {
        this.departure = departure;
    }
}