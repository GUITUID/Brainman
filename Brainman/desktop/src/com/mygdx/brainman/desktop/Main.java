package com.mygdx.brainman.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.brainman.Brainman;

public class Main {
	public static void main (String[] args) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title="Brainman";
		config.width=800;
		config.height=480;
		new LwjglApplication(new Brainman(), config);
	}
}
