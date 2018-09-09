package com.flippinbits.kcorey.hellodemo.circus;

import com.flippinbits.kcorey.hellodemo.circus.states.CState;

public interface StateReceiver {
    void render(CState newState);
}
