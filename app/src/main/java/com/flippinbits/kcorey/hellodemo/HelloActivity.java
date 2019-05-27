package com.flippinbits.kcorey.hellodemo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.flippinbits.kcorey.hellodemo.circus.Circus;
import com.flippinbits.kcorey.hellodemo.circus.events.CEToggleButtonClicked;
import com.flippinbits.kcorey.hellodemo.circus.states.CSHello;
import com.flippinbits.kcorey.hellodemo.circus.states.CSLongRunning;
import com.flippinbits.kcorey.hellodemo.circus.states.CState;

public class HelloActivity extends CircusActivity {

    private static final String TAG = "HelloActivity";

    private TextView counterText;
    private Button toggleButton;
    /**
     * onCreate - Initialise our parent, and then init ourselves.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);

        setupWidgets();
    }

    private void setupWidgets() {
        counterText = findViewById(R.id.counterText);
        toggleButton = findViewById(R.id.toggleButton);

        toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Circus.getCircus().reportEvent(new CEToggleButtonClicked());
            }
        });
    }

    /**
     * render a new State for the Hello Activity
     *
     * Note that the super.render(newState) is only called if this class cannot handle
     * the newState.
     *
     * @param newState
     */
    @Override
    public void render(CState newState) {
        if (newState instanceof CSHello) {
            CSHello helloState = (CSHello) newState;
            Log.d(TAG, "Redrawing Hello Activity");

            TextView centerLabel = findViewById(R.id.center_label);
            centerLabel.setText(helloState.getMessageToShow());
        } else if (newState instanceof CSLongRunning) {
            CSLongRunning csLongRunning = (CSLongRunning) newState;
            counterText.setText(String.format("%d",csLongRunning.getCounter()));
        } else {
            // Any other event type, and we don't know how to handle it, let our super class have
            // a go.
            super.render(newState);
        }
    }

}
