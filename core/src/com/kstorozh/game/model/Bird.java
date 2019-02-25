package com.kstorozh.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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
    private Sound wing;


    private BirdAnimation animationBird;

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
        pic = new Texture("bird_fly.png");
        animationBird = new BirdAnimation(pic, 3, 0.5f);
        bounds = new Rectangle(x, y, pic.getWidth()/3, pic.getHeight());
        birdSprite = new Sprite(animationBird.getFrame());
        score = 0;
        wing = Gdx.audio.newSound(Gdx.files.internal("wing.ogg"));

    }


    public void update(float dt)
    {
        animationBird.update(dt);
        birdSprite.setRegion(animationBird.getFrame());
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

    public TextureRegion getPic() {
        return animationBird.getFrame();
    }

    public void jump()
    {
        velocity.y = 250;
        isJump = true;
        rotation = 45;
        frameCount = 0;
        wing.play(0.5f);
    }

    public void dispose() {
        pic.dispose();
        wing.dispose();
    }
}
