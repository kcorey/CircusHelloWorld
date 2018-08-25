package com.flippinbits.kcorey.hellodemo;

import android.app.Application;

import com.flippinbits.kcorey.hellodemo.circus.Circus;

public class App extends Application {
    Circus circus;
    private App instance;

    @Override
    public void onCreate() {
        super.onCreate();
        circus = Circus.getCircus();

        instance = this;
    }
}
