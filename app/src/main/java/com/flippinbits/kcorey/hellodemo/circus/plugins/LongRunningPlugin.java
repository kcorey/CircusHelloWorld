package com.flippinbits.kcorey.hellodemo.circus.plugins;

import com.flippinbits.kcorey.hellodemo.circus.Circus;
import com.flippinbits.kcorey.hellodemo.circus.EventReporter;
import com.flippinbits.kcorey.hellodemo.circus.events.CEvent;
import com.flippinbits.kcorey.hellodemo.circus.events.LongRunningEvent;

public class LongRunningPlugin implements EventReporter {

    public static final int ONE_SECOND_IN_MILLIS = 1000;
    private static boolean keepRunning;
    private static Thread runningThread;
    private int counter;

    public LongRunningPlugin() {
        keepRunning = false;
    }

    public void theOneLongFunction(CEvent theEvent) {
        while(keepRunning) {
            try {
                Thread.sleep(ONE_SECOND_IN_MILLIS);
            } catch (InterruptedException e) {
            }
            reportEvent(new LongRunningEvent(counter));
            counter++;
        }
        // Let the garbage collector reap it.
        runningThread = null;
    }

    public void toggleRunning() {
        // Only want one
        if (!keepRunning) {
            counter = 0;
            keepRunning = true;
            runningThread = new Thread() {
                @Override
                public void run() {
                    theOneLongFunction(null);
                }
            };

            runningThread.start();
        } else {
            keepRunning = false;
        }
    }

    @Override
    public void reportEvent(CEvent theEvent) {
        Circus.getCircus().reportEvent(theEvent);
    }
}
