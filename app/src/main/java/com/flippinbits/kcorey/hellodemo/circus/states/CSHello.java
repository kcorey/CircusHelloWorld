package com.flippinbits.kcorey.hellodemo.circus.states;

import java.util.Objects;

public class CSHello extends CState {

    private String messageToShow;
    private int currentCounter;

    public CSHello(String messageToShow, int currentCounter) {
        this.messageToShow = messageToShow;
        this.currentCounter = currentCounter;
    }

    public String getMessageToShow() {
        return messageToShow;
    }

    public void setMessageToShow(String messageToShow) {
        this.messageToShow = messageToShow;
    }

    public int getCurrentCounter() {
        return currentCounter;
    }

    public void setCurrentCounter(int currentCounter) {
        this.currentCounter = currentCounter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CSHello)) return false;
        CSHello csHello = (CSHello) o;
        return currentCounter == csHello.currentCounter &&
                Objects.equals(messageToShow, csHello.messageToShow);
    }

    @Override
    public int hashCode() {
        return Objects.hash(messageToShow, currentCounter);
    }

    @Override
    public String toString() {
        return "CSHello{" +
                "messageToShow='" + messageToShow + '\'' +
                ", currentCounter=" + currentCounter +
                '}';
    }
}
