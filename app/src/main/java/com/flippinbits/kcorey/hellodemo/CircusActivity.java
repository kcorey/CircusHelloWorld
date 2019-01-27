package com.flippinbits.kcorey.hellodemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.flippinbits.kcorey.hellodemo.circus.BackgroundStateReceiver;
import com.flippinbits.kcorey.hellodemo.circus.Circus;
import com.flippinbits.kcorey.hellodemo.circus.Twig;
import com.flippinbits.kcorey.hellodemo.circus.acts.HelloAct;
import com.flippinbits.kcorey.hellodemo.circus.acts.SplashAct;
import com.flippinbits.kcorey.hellodemo.circus.events.CEResumed;
import com.flippinbits.kcorey.hellodemo.circus.events.CEvent;
import com.flippinbits.kcorey.hellodemo.circus.states.CSHello;
import com.flippinbits.kcorey.hellodemo.circus.states.CSSplash;
import com.flippinbits.kcorey.hellodemo.circus.states.CState;

import java.lang.ref.WeakReference;

public class CircusActivity extends AppCompatActivity implements BackgroundStateReceiver {

    private static final String TAG = "CircusActivity";
    private String logTag = "CircusActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        logTag = this.getClass().getSimpleName();
        Log.d(logTag, "onCreate: ");
        Circus.getCircus().setStateReceiver(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        logTag = this.getClass().getSimpleName();
        Log.d(logTag, "onResume: ");
        Circus.getCircus().reportEvent(new CEResumed(logTag));
    }

    @Override
    protected void onStop() {
        super.onStop();
        logTag = this.getClass().getSimpleName();
        Log.d(logTag, "onStop: ");
        Circus.getCircus().removeStateReceiver(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        logTag = this.getClass().getSimpleName();
        Log.d(logTag, "onDestroy: ");
        Circus.getCircus().removeStateReceiver(this);
    }

    void report(CEvent event) {
        Circus.getCircus().reportEvent(event);
    }

    /**
     * Child classes override this render method to handle any
     * states they understand.  Any states they receive that they don't
     * understand get handed back to this render method, where the navigation
     * is handled for the app.
     *
     * @param newState
     */
    public void render(CState newState) {
        Circus circus = Circus.getCircus();

        Twig.d("CircusActivity:render() called!");
        if (newState instanceof CSSplash) {
            circus.setAct(new SplashAct());

        } else if (newState instanceof CSHello) {
            circus.setAct(new HelloAct());
            startActivity(new Intent(this, HelloActivity.class));

        } else {
            Log.d(TAG, "render: Unexpected state " + newState.toString());

        }
    }

    // states from from the act end on the background thread here.
    // We forward them on to the render() method which gets overridden by
    // our subclassed Activities.
    public void renderNewState(final CState newState) {
        final WeakReference<CircusActivity> self = new WeakReference<>(this);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                CircusActivity activity = self.get();
                activity.render(newState);
            }
        });
    }
}
