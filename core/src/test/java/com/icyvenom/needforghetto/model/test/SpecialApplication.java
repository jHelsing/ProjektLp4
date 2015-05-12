package com.icyvenom.needforghetto.model.test;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.icyvenom.needforghetto.model.World;

/**
 * Created by Jonathan on 2015-05-12.
 */
public class SpecialApplication extends HeadlessApplication {

    private ApplicationListener listener;

    public SpecialApplication(ApplicationListener listener) {
        super(listener);
    }

    public SpecialApplication(ApplicationListener listener, Object  o) {
        super(listener);
    }
}
