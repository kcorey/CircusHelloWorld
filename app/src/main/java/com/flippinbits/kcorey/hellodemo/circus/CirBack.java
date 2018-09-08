package com.flippinbits.kcorey.hellodemo.circus;

public abstract class CirBack {

    private String name;

    public CirBack() {
        this.name = this.getClass().getSimpleName();
    }

    public abstract CState getState();

    public abstract void handleEvent(CEvent theEvent);

    public void render(CState state) {
        Circus.getCircus().render(state);
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "{" +
                "name='" + name + '\'' +
                '}';
    }
}
