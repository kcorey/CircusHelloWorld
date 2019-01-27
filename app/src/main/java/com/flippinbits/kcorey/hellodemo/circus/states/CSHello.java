package com.flippinbits.kcorey.hellodemo.circus.states;

import java.util.Objects;

public class CSHello extends CState {

    private String messageToShow;

    public CSHello(String messageToShow) {
        this.messageToShow = messageToShow;
    }

    public String getMessageToShow() {
        return messageToShow;
    }

    public void setMessageToShow(String messageToShow) {
        this.messageToShow = messageToShow;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CSHello)) return false;
        CSHello csHello = (CSHello) o;
        return Objects.equals(messageToShow, csHello.messageToShow);
    }

    @Override
    public int hashCode() {

        return Objects.hash(messageToShow);
    }

    @Override
    public String toString() {
        return "CSHello{" +
                "messageToShow='" + messageToShow + '\'' +
                '}';
    }
}
