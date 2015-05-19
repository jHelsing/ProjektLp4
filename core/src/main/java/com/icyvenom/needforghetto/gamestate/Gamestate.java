package com.icyvenom.needforghetto.gamestate;

/**
 * Created by anton on 2015-05-19.
 */
public class Gamestate {

    public enum State {
        PAUSED,
        RUNNING
    }

    // Initial state is running
    public static State currentState = State.RUNNING;

}
