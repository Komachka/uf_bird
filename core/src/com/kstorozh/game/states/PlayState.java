package com.kstorozh.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.kstorozh.game.model.Bird;
import com.kstorozh.game.UfBird;
import com.kstorozh.game.model.Tube;

import java.util.ArrayList;

public class PlayState extends State {

    private Bird bird;
    private Texture beckground;


    private ArrayList<Tube> tubes = new ArrayList<Tube>();

    public PlayState(GameStateManager gsm) {
        super(gsm);
        bird = new Bird(50, 300);
        camera.setToOrtho(false, UfBird.WIDTH/2, UfBird.HEIGHT/2);
        beckground = new Texture("sky.png");
        createTubes();
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
            }
            if (t.collides(bird.getBounds()))
                gsm.setState(new PlayState(gsm));
        }
        camera.update();

    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();

        spriteBatch.draw(beckground, camera.position.x - (camera.viewportWidth/2), camera.position.y - (camera.viewportHeight/2));

        for (Tube tube  : tubes) {
            spriteBatch.draw(tube.getTopTube(), tube.getPositionTopTube().x, tube.getPositionTopTube().y);
            spriteBatch.draw(tube.getBottomTube(), tube.getPositionButtomTube().x, tube.getPositionButtomTube().y);

        }
        spriteBatch.draw(bird.getPic(), bird.getPosition().x, bird.getPosition().y);
        spriteBatch.end();

    }

    @Override
    public void dispose() {

    }
}
