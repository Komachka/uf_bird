package com.kstorozh.game.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.kstorozh.game.UfBird;

import java.util.Random;

/**
 * Created by kateryna on 12.02.19.
 */

public class Tube {
    private static final int FLUCTUATION = 100;
    public static final int TUBE_GAP = 100;
    public static final int TUBE_SPACING = 125;
    public static final int TUBE_COUNT = 4;
    public static final int LOWEST_OPENING = 120;
    public static final int WIDTH = 52; // size of texture
    private Texture topTube, bottomTube;
    private Vector2 positionTopTube, positionButtomTube;
    private static final String topTubeImagePath = "tubeTop.png";
    private static final String bottomTubeImagePath = "tubeBottom.png";
    private Rectangle boundsTop, boundsBottom;



    private Random random;
    public Tube(int xStart) {
        this.topTube = new Texture(topTubeImagePath);
        this.bottomTube = new Texture(bottomTubeImagePath);
        random = new Random();
        positionTopTube = new Vector2(xStart, random.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING);
        positionButtomTube = new Vector2(xStart, positionTopTube.y - TUBE_GAP - bottomTube.getHeight());

        boundsTop = new Rectangle(positionTopTube.x, positionTopTube.y, topTube.getWidth(), topTube.getHeight());
        boundsBottom = new Rectangle(positionButtomTube.x, positionButtomTube.y, bottomTube.getWidth(), bottomTube.getHeight());
    }


    public Texture getTopTube() {
        return topTube;
    }

    public Texture getBottomTube() {
        return bottomTube;
    }

    public Vector2 getPositionTopTube() {
        return positionTopTube;
    }

    public Vector2 getPositionButtomTube() {
        return positionButtomTube;
    }

    public void reposition(float x)
    {
        positionTopTube.set(x, random.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING);
        positionButtomTube.set(x, positionTopTube.y - TUBE_GAP - bottomTube.getHeight());
        boundsTop.setPosition(positionTopTube.x, positionTopTube.y);
        boundsBottom.setPosition(positionButtomTube.x, positionButtomTube.y);
    }

    public boolean collides(Rectangle birdRec)
    {
        return birdRec.overlaps(boundsBottom) || birdRec.overlaps(boundsTop);
    }

    public boolean pass(Rectangle birdRec) {
        //new rectangle = last vertical line of tubes
        return birdRec.overlaps(new Rectangle(boundsTop.x+boundsTop.width, 0, 1, UfBird.HEIGHT));
    }
}
