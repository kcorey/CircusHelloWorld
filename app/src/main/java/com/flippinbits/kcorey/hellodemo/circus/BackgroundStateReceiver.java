package com.flippinbits.kcorey.hellodemo.circus;

import com.flippinbits.kcorey.hellodemo.circus.states.CState;

interface BackgroundStateReceiver {
    void renderNewState(CState newState);
}
