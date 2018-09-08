package com.flippinbits.kcorey.hellodemo.circus;

public class Circus implements EventReceiver {

    private static Circus instance;

    private CirBack back;

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
        Twig.d("  -> reportEvent: " + theEvent.toString());

        back.handleEvent(theEvent);
    }

    public void render(CState state) {

    }
}
