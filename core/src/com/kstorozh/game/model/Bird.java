package com.kstorozh.game.model;

import com.badlogic.gdx.graphics.Texture;
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

    private static final int SPEED = 100;

    public Bird(int x, int y) {
        position = new Vector3(x,y,0);
        velocity = new Vector3(0,0,0);
        pic = new Texture("bird.png");
        bounds = new Rectangle(x, y, pic.getWidth(), pic.getHeight());
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
    }
}
