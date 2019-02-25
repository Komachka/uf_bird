package com.kstorozh.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.kstorozh.game.controller.GameStateController;
import com.kstorozh.game.view.MenuView;

public class UfBird extends ApplicationAdapter {
	public static final int WIDTH = 480;
	public static final int HEIGHT = 800;
	public static final String TITLE = "uf_bird";

	private GameStateController gsm;
	private Music music;
	private static final String musicFile = "music.mp3";

	SpriteBatch batch;

	
	@Override
	public void create () {
		batch = new SpriteBatch();
		gsm = new GameStateController();

		music = Gdx.audio.newMusic(Gdx.files.internal(musicFile));
		music.setLooping(true);
		music.setVolume(0.1f);
		music.play();

		gsm.pushState(new MenuView(gsm));
		Gdx.gl.glClearColor(0.306f, 0.753f, 0.792f, 1.0f);

	}

	@Override
	public void render () {

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);

	}
	
	@Override
	public void dispose () {
		batch.dispose();
		music.dispose();

	}
}
