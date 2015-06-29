package com.icyvenom.needforghetto.gamestate;

/**
 * Created by anton on 2015-05-19.
 */
public class GameState {

    public enum State {
        PAUSED,
        RUNNING,
        VICTORY
    }

    // Initial state is running
    public static State currentState = State.RUNNING;

    public static boolean GODMODE = false;

    public static boolean GODMODE_CHEAT = false;

}
