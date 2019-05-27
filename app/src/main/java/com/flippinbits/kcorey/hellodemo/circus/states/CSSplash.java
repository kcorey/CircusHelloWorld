package com.flippinbits.kcorey.hellodemo.circus.states;

import java.util.Objects;

public class CSSplash extends CState {
    private String message;

    public CSSplash(String from_splash) {
        message = from_splash;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CSSplash)) return false;
        CSSplash csSplash = (CSSplash) o;
        return Objects.equals(message, csSplash.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message);
    }

    @Override
    public String toString() {
        return "CSSplash{" +
                "message='" + message + '\'' +
                '}';
    }
}
