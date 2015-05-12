package com.icyvenom.needforghetto.model.test;

import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import com.icyvenom.needforghetto.NeedForGhetto;
import com.icyvenom.needforghetto.model.World;

/**
 * This class is used to be able to test the models that are dependant on libGDX.
 * @author Jonathan.
 * @version 1.0
 */
public class HeadlessLauncher {

    public static SpecialApplication test;

    public static void main(final String[] args) {
        final HeadlessApplicationConfiguration config = new HeadlessApplicationConfiguration();
        config.renderInterval = 1f/60; // Likely want 1f/60 for 60 fps
        test = new SpecialApplication(new NeedForGhettoTest());
    }
}