package com.kstorozh.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.kstorozh.game.UfBird;
import com.kstorozh.game.controller.GameStateController;

public class GameOverState extends State {
    private Texture background;
    private Texture gameOver;


    protected GameOverState(GameStateController gsm) {
        super(gsm);
        background = new Texture("bd-day.png");
        gameOver = new Texture("gameover.png");
        camera.setToOrtho(false, UfBird.WIDTH, UfBird.HEIGHT);
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched())
        {
            gsm.setState(new PlayState(gsm));
            dispose();
        }
    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.setProjectionMatrix(camera.combined);
        handleInput();
        spriteBatch.begin();
        spriteBatch.draw(background, 0,0, UfBird.WIDTH, UfBird.HEIGHT);
        spriteBatch.draw(gameOver, (UfBird.WIDTH/2 - gameOver.getWidth()/2), (UfBird.HEIGHT/2 - gameOver.getHeight()/2));
        spriteBatch.end();
    }

    @Override
    public void dispose() {

    }
}
