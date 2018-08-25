package com.flippinbits.kcorey.hellodemo.circus;

public class Circus {

    private static Circus instance;

    private Circus() {
        instance = this;
    }

    public static Circus getCircus() {
        if (instance == null) {
            instance = new Circus();
        }
        return instance;
    }
}
