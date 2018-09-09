package com.flippinbits.kcorey.hellodemo.circus;

import com.flippinbits.kcorey.hellodemo.circus.events.CEvent;

public interface EventReceiver {
    void reportEvent(CEvent theEvent);
}
