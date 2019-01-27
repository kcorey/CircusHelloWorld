package com.flippinbits.kcorey.hellodemo.circus;

import com.flippinbits.kcorey.hellodemo.circus.events.CEvent;
import com.flippinbits.kcorey.hellodemo.circus.states.CSHello;
import com.flippinbits.kcorey.hellodemo.circus.states.CState;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;

public class CircusActTest implements BackgroundStateReceiver {

    Circus circus;
    CState state;

    CompletableFuture<String> future;

    @Rule
    public TestName name = new TestName();


    @Before
    public void setUp() throws Exception {
        Twig.d("=======  test " + name.getMethodName());
        circus = Circus.getCircus();
        circus.setStateReceiver(this);
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
    public void cirActExists() {
        MockCirAct mockCirAct = new MockCirAct();

        String description = mockCirAct.toString();

        assertNotNull(mockCirAct);
        assertNotNull(description);
    }

    @Test
    public void cirActRendersProperly() {
        MockCirAct mockCirAct = new MockCirAct();

        mockCirAct.render(new CSHello("cirActRendersProperly"));

        try {
            String result = future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        Assert.assertNotNull(state);
        assertEquals(state, new CSHello("cirActRendersProperly"));

    }

    @Override
    public void renderNewState(CState newState) {
        future.complete(newState.getClass().getSimpleName());

        state = newState;
    }


    class MockCirAct extends CircusAct {

        @Override
        public CState getState() {
            return null;
        }

        @Override
        public void handleEvent(CEvent theEvent) {

        }
    }
}