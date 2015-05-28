package com.icyvenom.needforghetto.model.bullets;

/**
 * This Enum determine the direction of the object.
 * @author Amar.
 * @version 1.0
 */
public enum BulletDirection {
    UP(1f), DOWN(-1f);

    private final float directionValue;

    BulletDirection(float directionValue) {
        this.directionValue=directionValue;
    }

    public float getDirectionValue() {
        return directionValue;
    }
}