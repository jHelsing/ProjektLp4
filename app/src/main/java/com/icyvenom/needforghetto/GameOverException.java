package com.icyvenom.needforghetto;

/**
 * This is a custom exception that is thrown when the game is over.
 * @author Amar Kulaglic
 * @version 1.0
 */
public class GameOverException extends Exception{

    public GameOverException(){
        super();
    }

    public GameOverException(String str){
        super(str);
    }
}
