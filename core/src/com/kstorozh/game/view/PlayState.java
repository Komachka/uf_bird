package com.kstorozh.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.kstorozh.game.controller.GameStateController;
import com.kstorozh.game.model.Bird;
import com.kstorozh.game.UfBird;
import com.kstorozh.game.model.Tube;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class PlayState extends State {

    private Bird bird;
    private Texture background;
    private static final String imagePath = "sky.png";
    private String scoreStringName;
    BitmapFont bitmapScoreName;
    Set<Tube> passedTube = new HashSet<Tube>();


    private ArrayList<Tube> tubes = new ArrayList<Tube>();

    public PlayState(GameStateController gsm) {
        super(gsm);
        bird = new Bird(50, 300);
        camera.setToOrtho(false, (int)(UfBird.WIDTH*0.7), (int)(UfBird.HEIGHT*0.7));
        background = new Texture(imagePath);
        createTubes();
        scoreStringName = "score: 0";
        bitmapScoreName = new BitmapFont();

    }

    private void createTubes() {
        for (int i = 1; i <= Tube.TUBE_COUNT; i++)
        {
            tubes.add(new Tube(i * (Tube.TUBE_SPACING + Tube.WIDTH)));

        }
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched())
        {
            bird.jump();

        }

    }

    @Override
    public void update(float dt) {
        handleInput();
        bird.update(dt);
        camera.position.x = bird.getPosition().x + 80;
        for (Tube t : tubes)
        {
            if (camera.position.x - (camera.viewportWidth/2) > t.getPositionTopTube().x + t.getTopTube().getWidth())
            {
                t.reposition(t.getPositionTopTube().x + ((Tube.WIDTH + Tube.TUBE_SPACING) * Tube.TUBE_COUNT));
                if (passedTube.contains(t))
                    passedTube.remove(t);
            }
            if (t.pass(bird.getBounds()) && !passedTube.contains(t))
            {
                bird.setScore(1);
                passedTube.add(t);

            }
            if (t.collides(bird.getBounds()))
                gsm.setState(new GameOverState(gsm, bird));

        }
        camera.update();


    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();


        spriteBatch.draw(background, camera.position.x - (camera.viewportWidth/2), camera.position.y - (camera.viewportHeight/2), (int)(background.getWidth()*1.7), (int)(background.getHeight()*1.7));


        for (Tube tube  : tubes) {
            spriteBatch.draw(tube.getTopTube(), tube.getPositionTopTube().x, tube.getPositionTopTube().y);
            spriteBatch.draw(tube.getBottomTube(), tube.getPositionButtomTube().x, tube.getPositionButtomTube().y);

        }
        //spriteBatch.draw(bird.getPic(), bird.getPosition().x, bird.getPosition().y);
        bird.birdSprite.draw(spriteBatch);
        scoreStringName = "score: " + bird.getScore();
        bitmapScoreName.setColor(1.0f, 1.0f, 1.0f, 1.0f);
        bitmapScoreName.draw(spriteBatch, scoreStringName, bird.getPosition().x - 10, 100);
        spriteBatch.end();

    }

    @Override
    public void dispose() {

    }
}
