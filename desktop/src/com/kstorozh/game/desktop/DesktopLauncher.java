package com.kstorozh.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.kstorozh.game.UfBird;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = UfBird.WIDTH;
		config.height = UfBird.HEIGHT;
		config.title = UfBird.TITLE;
		new LwjglApplication(new UfBird(), config);
	}
}
