package com.kstorozh.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.kstorozh.game.controller.GameStateController;
import com.kstorozh.game.model.Bird;
import com.kstorozh.game.UfBird;
import com.kstorozh.game.model.Tube;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class PlayView extends View {

    private static final int LAND_MINUS = -50;
    private Bird bird;
    private Texture background;
    private static final String imagePath = "sky.png";
    private String scoreStringName;
    private BitmapFont bitmapScoreName;
    private Set<Tube> passedTube = new HashSet<Tube>();
    private Texture land;
    private static final String landImagePath = "land.png";
    private Vector2 landPos1, landPos2;
    private Sound loose;

    private ArrayList<Tube> tubes = new ArrayList<Tube>();

    public PlayView(GameStateController gsm) {
        super(gsm);
        bird = new Bird(50, 300);
        camera.setToOrtho(false, (int)(UfBird.WIDTH*0.7), (int)(UfBird.HEIGHT*0.7));
        background = new Texture(imagePath);
        createTubes();
        scoreStringName = "score: 0";
        bitmapScoreName = new BitmapFont();
        land = new Texture(landImagePath);
        landPos1 = new Vector2(camera.position.x - camera.viewportWidth/2, LAND_MINUS);
        landPos2 = new Vector2((camera.position.x - camera.viewportWidth/2) + land.getWidth(), LAND_MINUS);
        loose = Gdx.audio.newSound(Gdx.files.internal("die.ogg"));
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
            if (t.collides(bird.getBounds())) {
                loose.play(0.5f);
                gsm.setState(new GameOverView(gsm, bird));
            }
            if (camera.position.x- (camera.viewportWidth/2) > landPos1.x + land.getWidth())
                landPos1.add(land.getWidth() * 2, 0);
            if (camera.position.x- (camera.viewportWidth/2) > landPos2.x + land.getWidth())
                landPos2.add(land.getWidth() * 2, 0);
        }
        if (bird.getPosition().y <= land.getHeight() + LAND_MINUS) {
            loose.play(0.5f);
            gsm.setState(new GameOverView(gsm, bird));
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
        bird.birdSprite.draw(spriteBatch);
        scoreStringName = "score: " + bird.getScore();
        bitmapScoreName.setColor(0.973f, 0.718f, 0.2f, 1.0f);
        bitmapScoreName.getData().setScale((float) 1.5);
        bitmapScoreName.draw(spriteBatch, scoreStringName, camera.position.x , 550);
        spriteBatch.draw(land, landPos1.x, landPos1.y);
        spriteBatch.draw(land, landPos2.x, landPos2.y);
        spriteBatch.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        bird.dispose();
        for (Tube t: tubes) {
            t.dispose();
        }
        bitmapScoreName.dispose();
        land.dispose();
        loose.dispose();
    }
}
