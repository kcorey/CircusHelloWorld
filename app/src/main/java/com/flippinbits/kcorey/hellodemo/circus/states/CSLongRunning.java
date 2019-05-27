package com.flippinbits.kcorey.hellodemo.circus.states;

import java.util.Objects;

public class CSLongRunning extends CState {
    private int counter;

    public CSLongRunning(int newCounter) {
        this.counter = newCounter;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CSLongRunning)) return false;
        CSLongRunning that = (CSLongRunning) o;
        return counter == that.counter;
    }

    @Override
    public int hashCode() {
        return Objects.hash(counter);
    }

    @Override
    public String toString() {
        return "CSLongRunning{" +
                "counter=" + counter +
                '}';
    }
}
