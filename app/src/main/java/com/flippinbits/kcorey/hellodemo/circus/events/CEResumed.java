package com.flippinbits.kcorey.hellodemo.circus.events;

public class CEResumed extends CEvent {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CEResumed)) return false;
        return true;
    }

    @Override
    public String toString() {
        return "CEResumed{}";
    }
}
