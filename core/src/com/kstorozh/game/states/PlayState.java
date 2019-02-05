package com.kstorozh.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.kstorozh.game.UfBird;

public class PlayState extends State {

    private Texture bird;


    public PlayState(GameStateManager gsm) {
        super(gsm);
        bird = new Texture("bird.png");
        camera.setToOrtho(false, UfBird.WIDTH, UfBird.HEIGHT);
    }

    @Override
    protected void handleInput() {


    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
        spriteBatch.draw(bird, 10, UfBird.HEIGHT/2);
        spriteBatch.end();

    }

    @Override
    public void dispose() {

    }
}
