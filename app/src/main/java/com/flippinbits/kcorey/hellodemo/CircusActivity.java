package com.flippinbits.kcorey.hellodemo;

import android.support.v7.app.AppCompatActivity;

import com.flippinbits.kcorey.hellodemo.circus.states.CState;
import com.flippinbits.kcorey.hellodemo.circus.StateReceiver;
import com.flippinbits.kcorey.hellodemo.circus.Twig;

public class CircusActivity extends AppCompatActivity implements StateReceiver {

    // child classes will use this one.
    public void render(CState newState) {
        Twig.d("CircusActivity:render() called!");
    }

    public void renderNewState(CState newState) {
        this.render(newState);
    }
}
