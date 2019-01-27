package com.flippinbits.kcorey.hellodemo.circus;

import com.flippinbits.kcorey.hellodemo.circus.acts.SplashAct;
import com.flippinbits.kcorey.hellodemo.circus.events.CEvent;
import com.flippinbits.kcorey.hellodemo.circus.states.CState;

public class Circus implements EventReceiver {

    private static Circus instance;

    private CircusAct act;
    private BackgroundStateReceiver stateReceiver;
    private Thread eventThread;

    private Circus() {
        instance = this;
    }

    public static Circus getCircus() {
        if (instance == null) {
            Twig.d("Circus created.");
            instance = new Circus();

            instance.setAct(new SplashAct());
        }
        return instance;
    }

    @Override
    public void reportEvent(final CEvent theEvent) {

        if (act != null) {
            Twig.d("  --> reportEvent: " + theEvent.toString());
            /*
             * Watch out here!  We're creating a single thread, and assigning it to a field in Circus.
             */
            eventThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    act.handleEvent(theEvent);
                }
            });
            eventThread.start();
        } else {
            Twig.d("  -X-> reportEvent: " + theEvent.toString() + " (no handler)");
        }
    }

    public void render(CState state) {
        if (stateReceiver != null) {
            Twig.d("  <-- render " + state.toString());
            stateReceiver.renderNewState(state);
        } else {
            Twig.d("  <-X- render " + state.toString() + " (no receiver)");
        }
    }

    public void destroyCircus() {
        act = null;
        stateReceiver = null;
        instance = null;

    }

    public void setAct(CircusAct newAct) {
        Twig.d("  ^^^ New Act: " + newAct);
        this.act = newAct;
    }

    public void setStateReceiver(BackgroundStateReceiver stateReceiver) {
        Twig.d("  vvv New StateReceiver");
        this.stateReceiver = stateReceiver;
    }

    public void removeStateReceiver(BackgroundStateReceiver stateReceiver) {
        if (this.stateReceiver.equals(stateReceiver)) {
            this.stateReceiver = null;
        }
    }
}
