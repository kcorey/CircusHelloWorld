package com.flippinbits.kcorey.hellodemo.circus.acts;

import android.util.Log;

import com.flippinbits.kcorey.hellodemo.circus.Circus;
import com.flippinbits.kcorey.hellodemo.circus.CircusAct;
import com.flippinbits.kcorey.hellodemo.circus.events.CEResumed;
import com.flippinbits.kcorey.hellodemo.circus.events.CEToggleButtonClicked;
import com.flippinbits.kcorey.hellodemo.circus.events.CEvent;
import com.flippinbits.kcorey.hellodemo.circus.events.LongRunningEvent;
import com.flippinbits.kcorey.hellodemo.circus.states.CSHello;
import com.flippinbits.kcorey.hellodemo.circus.states.CSLongRunning;
import com.flippinbits.kcorey.hellodemo.circus.states.CState;

public class HelloAct extends CircusAct {

    private static final String TAG = "HelloAct";
    public static final int SECONDS_TO_PROCESS_HELLO_IN_MILLIS = 2000;

    private String messageState = "Initial Hello.";

    private int currentcounter;

    @Override
    public CState getState() {
        return new CSHello(this.messageState, currentcounter);
    }

    @Override
    public void handleEvent(CEvent theEvent) {
        if (theEvent instanceof CEResumed) {
            // Show the current state of the HelloAct
            Circus.getCircus().render(getState());

            try {
                Thread.sleep(SECONDS_TO_PROCESS_HELLO_IN_MILLIS);
                Circus.getCircus().render(new CSHello("Final Hello.", currentcounter));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else if (theEvent instanceof LongRunningEvent) {
            LongRunningEvent lre = (LongRunningEvent) theEvent;
            currentcounter = lre.getCounter();

            Circus.getCircus().render(new CSLongRunning(currentcounter));

        } else if (theEvent instanceof CEToggleButtonClicked) {
            Circus.getCircus().getLongRunningPlugin().toggleRunning();

        } else {
            // Don't expect that event.  Punt and report it for tracking down.
            Log.d(TAG, "handleEvent: unexpected event " + theEvent.toString());

        }
    }
}
