package com.flippinbits.kcorey.hellodemo.circus;

import com.flippinbits.kcorey.hellodemo.circus.states.CState;

public interface BackgroundStateReceiver {
    void renderNewState(CState newState);
}
