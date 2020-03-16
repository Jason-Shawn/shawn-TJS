package com.shawn.demo.model;

public class Result {

    private boolean looped;

    private Node looped_item;

    public Result(boolean looped, Node looped_item) {
        this.looped = looped;
        this.looped_item = looped_item;
    }

    public boolean isLooped() {
        return looped;
    }

    public void setLooped(boolean looped) {
        this.looped = looped;
    }

    public Node getLooped_item() {
        return looped_item;
    }

    public void setLooped_item(Node looped_item) {
        this.looped_item = looped_item;
    }
}
