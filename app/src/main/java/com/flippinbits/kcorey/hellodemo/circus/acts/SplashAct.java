package com.flippinbits.kcorey.hellodemo.circus.acts;

import android.util.Log;

import com.flippinbits.kcorey.hellodemo.circus.Circus;
import com.flippinbits.kcorey.hellodemo.circus.CircusAct;
import com.flippinbits.kcorey.hellodemo.circus.events.CEResumed;
import com.flippinbits.kcorey.hellodemo.circus.events.CEvent;
import com.flippinbits.kcorey.hellodemo.circus.states.CSHello;
import com.flippinbits.kcorey.hellodemo.circus.states.CSSplash;
import com.flippinbits.kcorey.hellodemo.circus.states.CState;

public class SplashAct extends CircusAct {

    private static final String TAG = "SplashAct";
    /**
     * getState - used to return a state pojo with our current state.
     *
     * @return CSSplash - the current state.
     */
    @Override
    public CState getState() {
        return new CSSplash();
    }

    /**
     * handleEvent - handles any event we know how to handle.
     *
     * Events we can't handle are logged.
     *
     * @param theEvent
     */
    @Override
    public void handleEvent(CEvent theEvent) {
        if (theEvent instanceof CEResumed) {
            // Show the current state of the SplashAct
            Circus.getCircus().render(getState());

            try {
                Thread.sleep(5000);
                Circus.getCircus().render(new CSHello("From Splash"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            // Don't expect that event.  Punt and report it for tracking down.
            Log.d(TAG, "handleEvent: unexpected event " + theEvent.toString());
        }
    }
}
