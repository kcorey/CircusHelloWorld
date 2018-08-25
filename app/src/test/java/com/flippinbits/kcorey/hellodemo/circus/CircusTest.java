package com.flippinbits.kcorey.hellodemo.circus;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CircusTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getCircus() {
        Circus circus = Circus.getCircus();

        assertNotNull(circus);
    }
}