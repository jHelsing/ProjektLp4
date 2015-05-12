package com.icyvenom.needforghetto.model.test;

import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;

/**
 * This class sole purpose is to launch a new Headless libGDX activity. If it isn't launched
 * testing won't work. It launches and creates a new NeedForGhettoTest ApplicationListener.
 * @author Jonathan.
 * @version 1.0
 */
public class HeadlessLauncher {

    public static void main(final String[] args) {
        final HeadlessApplicationConfiguration config = new HeadlessApplicationConfiguration();
        config.renderInterval = 1f/60;
        HeadlessApplication app = new HeadlessApplication(new NeedForGhettoTest());
        app.getApplicationListener().create();
    }
}