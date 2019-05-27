package com.flippinbits.kcorey.hellodemo.circus.events;

import java.util.Objects;

public class LongRunningEvent extends CEvent {
    private int counter;

    public LongRunningEvent(int counter) {
        this.counter = counter;
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
        if (!(o instanceof LongRunningEvent)) return false;
        LongRunningEvent that = (LongRunningEvent) o;
        return counter == that.counter;
    }

    @Override
    public int hashCode() {
        return Objects.hash(counter);
    }

    @Override
    public String toString() {
        return "LongRunningEvent{" +
                "counter=" + counter +
                '}';
    }
}
