package com.flippinbits.kcorey.hellodemo.circus.events;

import java.util.Objects;

public class CEResumed extends CEvent {

    private String activity;

    public CEResumed(String activity) {
        super();
        this.activity = activity;
    }

    @Override
    public String toString() {
        return "CEResumed{" +
                "activity='" + activity + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CEResumed)) return false;
        CEResumed ceResumed = (CEResumed) o;
        return Objects.equals(activity, ceResumed.activity);
    }

    @Override
    public int hashCode() {

        return Objects.hash(activity);
    }
}
