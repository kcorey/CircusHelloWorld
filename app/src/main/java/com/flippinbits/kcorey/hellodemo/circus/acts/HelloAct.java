package com.flippinbits.kcorey.hellodemo.circus.acts;

import android.util.Log;

import com.flippinbits.kcorey.hellodemo.circus.Circus;
import com.flippinbits.kcorey.hellodemo.circus.CircusAct;
import com.flippinbits.kcorey.hellodemo.circus.events.CEResumed;
import com.flippinbits.kcorey.hellodemo.circus.events.CEvent;
import com.flippinbits.kcorey.hellodemo.circus.states.CSHello;
import com.flippinbits.kcorey.hellodemo.circus.states.CState;

public class HelloAct extends CircusAct {

    private static final String TAG = "HelloAct";

    private String messageState = "Initial Hello.";

    @Override
    public CState getState() {
        return new CSHello(this.messageState);
    }

    @Override
    public void handleEvent(CEvent theEvent) {
        if (theEvent instanceof CEResumed) {
            // Show the current state of the SplashAct
            Circus.getCircus().render(getState());

            try {
                Thread.sleep(5000);
                Circus.getCircus().render(new CSHello("Final Hello."));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            // Don't expect that event.  Punt and report it for tracking down.
            Log.d(TAG, "handleEvent: unexpected event " + theEvent.toString());
        }
    }
}
