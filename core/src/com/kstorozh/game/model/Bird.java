package com.kstorozh.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by kateryna on 12.02.19.
 */

public class Bird {

    private static final int GRAVITY = -15;
    private Vector3 position; //xyz
    private Vector3 velocity;
    private Texture pic;
    private Rectangle bounds;
    private int score;
    public Sprite birdSprite;
    private int rotation = 0;
    private boolean isJump = false;
    private int frameCount = 1;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score+=score;
    }

    private static final int SPEED = 100;

    public Bird(int x, int y) {
        position = new Vector3(x,y,0);
        velocity = new Vector3(0,0,0);
        pic = new Texture("bird.png");
        bounds = new Rectangle(x, y, pic.getWidth(), pic.getHeight());
        birdSprite = new Sprite(pic);
        score = 0;

    }


    public void update(float dt)
    {
        if (position.y > 0)
                velocity.add(0, GRAVITY, 0);
        velocity.scl(dt);
        position.add(SPEED * dt, velocity.y, 0);
        if (position.y < 0)
            position.y = 0;
        velocity.scl(1/dt);
        bounds.setPosition(position.x, position.y);
        frameCount += 1;
        if (isJump && frameCount > 10)
        {
            isJump = false;
            frameCount = 0;
        }
        if (isJump)
        {
            rotation = 45;
        }
        else if (position.y >= 0 && position.y <= 100) {
            rotation = -45;
        }
        else {
            rotation = 0;
        }
        birdSprite.setPosition(position.x, position.y);
        birdSprite.setRotation(rotation);

    }

    public Rectangle getBounds() {
        return bounds;
    }

    public Vector3 getPosition() {
        return position;
    }

    public Texture getPic() {
        return pic;
    }

    public void jump()
    {
        velocity.y = 250;
        isJump = true;
        rotation = 45;
        frameCount = 0;
    }
}
