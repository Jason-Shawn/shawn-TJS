package com.shawn.demo.model;

import java.util.Objects;

public class Node {

    private int parentPos;

    private int value;

    public Node(int parentPos, int value) {
        this.parentPos = parentPos;
        this.value = value;
    }

    public int getParentPos() {
        return parentPos;
    }

    public void setParentPos(int parentPos) {
        this.parentPos = parentPos;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Node)) return false;
        Node node = (Node) o;
        return getParentPos() == node.getParentPos() &&
                getValue() == node.getValue();
    }

    @Override
    public int hashCode() {

        return Objects.hash(getParentPos(), getValue());
    }
}