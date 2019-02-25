package com.kstorozh.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.kstorozh.game.UfBird;
import com.kstorozh.game.controller.GameStateController;
import com.kstorozh.game.model.Bird;

public class GameOverView extends View {
    private Texture background;
    private Texture gameOver;
    private String scoreStringName;
    BitmapFont bitmapScoreName;
    private Bird bird;
    private Texture playBut;


    protected GameOverView(GameStateController gsm, Bird bird) {
        super(gsm);
        background = new Texture("bd-day.png");
        gameOver = new Texture("gameover.png");
        camera.setToOrtho(false, UfBird.WIDTH, UfBird.HEIGHT);
        scoreStringName = "score: 0";
        bitmapScoreName = new BitmapFont();
        this.bird = bird;
        playBut = new Texture("message.png");
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched())
        {
            gsm.setState(new PlayView(gsm));
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
        spriteBatch.draw(gameOver, (UfBird.WIDTH/2 - gameOver.getWidth()/2), (UfBird.HEIGHT/2 + gameOver.getHeight()/2));
        scoreStringName = "score: " + bird.getScore();
        bitmapScoreName.setColor(0.973f, 0.718f, 0.2f, 1.0f);
        bitmapScoreName.draw(spriteBatch, scoreStringName, (UfBird.WIDTH/2 - gameOver.getWidth()/4),(UfBird.HEIGHT/2));
        bitmapScoreName.getData().setScale(2);
        spriteBatch.draw(playBut, (UfBird.WIDTH/2 - playBut.getWidth()/2), (UfBird.HEIGHT/2 - 100));

        spriteBatch.end();
    }

    @Override
    public void dispose() {
        gameOver.dispose();
        bitmapScoreName.dispose();
    }
}
