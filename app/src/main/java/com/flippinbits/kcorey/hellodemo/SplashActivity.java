package com.flippinbits.kcorey.hellodemo;

import android.os.Bundle;
import android.util.Log;

import com.flippinbits.kcorey.hellodemo.circus.states.CSSplash;
import com.flippinbits.kcorey.hellodemo.circus.states.CState;

public class SplashActivity extends CircusActivity {

    private static final String TAG = "SplashActivity";

    /**
     * onCreate - Initialise our parent, and then init ourselves.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    /**
     * render a new State for the Splash Activity
     *
     * Note that the super.render(newState) is only called if this class cannot handle
     * the newState.
     *
     * @param newState
     */
    @Override
    public void render(CState newState) {
        if (newState instanceof CSSplash) {
            // Since this is just a Splash screen, we don't necessarily need to do anything here.
            // However, if this were a real app, we'd draw the current state into the widget
            // so that the backend and frontends agree what's the right value.
            Log.d(TAG, "Redrawing Splash Activity");
        } else {
            // Any other event type, and we don't know how to handle it, let our super class have
            // a go.
            super.render(newState);
        }
    }
}
