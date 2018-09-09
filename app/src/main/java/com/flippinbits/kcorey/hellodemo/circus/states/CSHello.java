package com.flippinbits.kcorey.hellodemo.circus.states;

public class CSHello extends CState {
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof CSHello)) return false;
        return true;
    }
}
