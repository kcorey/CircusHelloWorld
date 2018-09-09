package com.flippinbits.kcorey.hellodemo.circus;

import com.flippinbits.kcorey.hellodemo.circus.events.CEvent;
import com.flippinbits.kcorey.hellodemo.circus.states.CState;

public class Circus implements EventReceiver {

    private static Circus instance;

    private CirBack back;
    private BackgroundStateReceiver stateRecevier;

    private Circus() {
        instance = this;
    }

    public static Circus getCircus() {
        if (instance == null) {
            instance = new Circus();
        }
        return instance;
    }

    @Override
    public void reportEvent(CEvent theEvent) {

        if (back != null) {
            Twig.d("  --> reportEvent: " + theEvent.toString());
            back.handleEvent(theEvent);
        } else {
            Twig.d("  -X-> reportEvent: " + theEvent.toString() + " (no handler)");
        }
    }

    public void render(CState state) {
        if (stateRecevier != null) {
            Twig.d("  <-- " + state.toString());
            stateRecevier.renderNewState(state);
        } else {
            Twig.d("  <-X- " + state.toString() + " (no receiver)");
        }
    }

    public void destroyCircus() {
        back = null;
        stateRecevier = null;
        instance = null;

    }

    public void setBack(CirBack newBack) {
        this.back = newBack;

    }

    public void setStateRecevier(BackgroundStateReceiver stateRecevier) {
        this.stateRecevier = stateRecevier;
    }
}
