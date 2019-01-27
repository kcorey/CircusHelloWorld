package com.flippinbits.kcorey.hellodemo.circus.acts;

import com.flippinbits.kcorey.hellodemo.circus.BackgroundStateReceiver;
import com.flippinbits.kcorey.hellodemo.circus.CircusAct;
import com.flippinbits.kcorey.hellodemo.circus.Circus;
import com.flippinbits.kcorey.hellodemo.circus.StateReceiver;
import com.flippinbits.kcorey.hellodemo.circus.Twig;
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

public class SplashActTest implements StateReceiver, BackgroundStateReceiver {

    Circus circus;
    CState state;
    CEvent event;

    CompletableFuture<String> future, future2;

    @Rule
    public TestName name = new TestName();


    @Before
    public void setUp() throws Exception {
        Twig.d("=======  test " + name.getMethodName());
        circus = Circus.getCircus();
        circus.setAct(new MockAct());
        circus.setStateReceiver(this);
        future = new CompletableFuture<>();
        future2 = new CompletableFuture<>();
    }

    @After
    public void tearDown() throws Exception {
        circus.destroyCircus();
        circus = null;
        state = null;
        event = null;
        future = null;
        future2 = null;
    }

    @Test
    public void SplashActHandlesCEResume() {
        circus.reportEvent(new CEResumed("CSMock"));

        try {
            String result = future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        assertNotNull(event);
        assertEquals(event, new CEResumed("CSMock"));
    }

    @Test
    public void SplashActGivesBackState() {
        circus.reportEvent(new CEResumed("CSMock"));

        try {
            String result = future2.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        assertNotNull(state);
        assertEquals(state, new CSHello("MockAct"));

    }

    @Test
    public void SplashActGivesBackCSMockAndState() {
        circus.reportEvent(new CEResumed("CSMock"));

        try {
            String result = future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        assertNotNull(event);
        assertEquals(event, new CEResumed("CSMock"));
        assertNotNull(state);
        assertEquals(state, new CSHello("MockAct"));

    }


    @Override
    public void render(CState newState) {
        future2.complete(newState.getClass().getSimpleName());

        state = newState;
    }

    @Override
    public void renderNewState(CState newState) {
        render(newState);
    }

    private class MockAct extends CircusAct {

        @Override
        public CState getState() {
            // When an activity loads, it can call getState() on the act to tell it what state to show to the user.
            // for this test program, we don't care.
            return new CSMock();
        }

        @Override
        public void handleEvent(CEvent theEvent) {
            future.complete(theEvent.getClass().getSimpleName());

            event = theEvent;

            if (theEvent instanceof CEResumed) {
                circus.render(new CSHello("MockAct"));
            }
        }
    }

    private class CSMock extends CState {

    }
}