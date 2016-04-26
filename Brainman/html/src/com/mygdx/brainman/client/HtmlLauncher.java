package com.mygdx.brainman.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import com.mygdx.brainman.Brainman;

public class HtmlLauncher extends GwtApplication {

        @Override
        public GwtApplicationConfiguration getConfig () {
        	GwtApplicationConfiguration cfg = new GwtApplicationConfiguration(800,480);
                return cfg;
        }

        @Override
        public ApplicationListener getApplicationListener () {
                return new Brainman();
        }
}