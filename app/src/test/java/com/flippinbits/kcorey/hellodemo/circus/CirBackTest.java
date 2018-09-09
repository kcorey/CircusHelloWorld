package com.flippinbits.kcorey.hellodemo.circus;

import com.flippinbits.kcorey.hellodemo.circus.events.CEvent;
import com.flippinbits.kcorey.hellodemo.circus.states.CSHello;
import com.flippinbits.kcorey.hellodemo.circus.states.CState;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;

public class CirBackTest implements BackgroundStateReceiver {

    Circus circus;
    CState state;

    CompletableFuture<String> future;

    @Before
    public void setUp() throws Exception {
        circus = Circus.getCircus();
        circus.setStateRecevier(this);
        future = new CompletableFuture<>();
    }

    @After
    public void tearDown() throws Exception {
        circus.destroyCircus();
        circus = null;
        state = null;
        future = null;
    }

    @Test
    public void cirBackExists() {
        MockCirBack mockCirBack = new MockCirBack();

        String description = mockCirBack.toString();

        assertNotNull(mockCirBack);
        assertNotNull(description);
    }

    @Test
    public void cirBackRendersProperly() {
        MockCirBack mockCirBack = new MockCirBack();

        mockCirBack.render(new CSHello());

        try {
            String result = future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        Assert.assertNotNull(state);
        assertEquals(state, new CSHello());

    }

    @Override
    public void renderNewState(CState newState) {
        future.complete(newState.getClass().getSimpleName());

        state = newState;
    }


    class MockCirBack extends CirBack {

        @Override
        public CState getState() {
            return null;
        }

        @Override
        public void handleEvent(CEvent theEvent) {

        }
    }
}