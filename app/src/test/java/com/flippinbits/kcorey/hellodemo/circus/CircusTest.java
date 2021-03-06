package com.flippinbits.kcorey.hellodemo.circus;

import com.flippinbits.kcorey.hellodemo.circus.events.CEResumed;
import com.flippinbits.kcorey.hellodemo.circus.events.CEvent;
import com.flippinbits.kcorey.hellodemo.circus.states.CSHello;
import com.flippinbits.kcorey.hellodemo.circus.states.CState;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

public class CircusTest implements StateReceiver, BackgroundStateReceiver {

    Circus circus;
    CState state;
    CEvent event;

    CompletableFuture<String> future;

    @Rule
    public TestName name = new TestName();


    @Before
    public void setUp() throws Exception {
        Twig.d("=======  test " + name.getMethodName());
        circus = Circus.getCircus();
        circus.setAct(new MockAct());
        circus.setStateReceiver(this);
        future = new CompletableFuture<>();
    }

    @After
    public void tearDown() throws Exception {
        circus.destroyCircus();
        circus = null;
        state = null;
        event = null;
        future = null;
    }

    @Test
    public void circusRespondsToRender() {
        // Make sure we can render to a circus
        circus.render(new CSHello("circusRespondsToRender"));

        try {
            String result = future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        assertNotNull(state);
        assertEquals(state, new CSHello("circusRespondsToRender"));
    }

    @Test
    public void circusPassesStateOnToStateReceiver() {
        circus.render(new CSHello("circusPassesStateOnToStateReceiver"));

        try {
            String result = future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        assertNotNull(state);
        assertEquals(state, new CSHello("circusPassesStateOnToStateReceiver"));
    }

    @Test
    public void circusCallsHandleEvent() {
        circus.reportEvent(new CEResumed("Testing"));

        try {
            String result = future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        assertNotNull(event);
        assertEquals(event, new CEResumed("Testing"));
    }

    @Override
    public void render(CState newState) {
        future.complete(newState.getClass().getSimpleName());

        state = newState;
    }

    @Override
    public void renderNewState(CState newState) {
        render(newState);
    }

    private class MockAct extends CircusAct {

        @Override
        public CState getState() {
            // When an activity loads, it can call getState() on the back to tell it what state to show to the user.
            // for this test program, we don't care.
            return null;
        }

        @Override
        public void handleEvent(CEvent theEvent) {
            future.complete(theEvent.getClass().getSimpleName());

            event = theEvent;

        }
    }
}

