package com.kstorozh.game.states;


import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public abstract class State {
    protected OrthographicCamera camera;
    protected Vector3 mouse;
    protected GameStateManager gsm;


    protected State(GameStateManager gsm) {
        this.gsm = gsm;
        camera = new OrthographicCamera();
        mouse = new Vector3();
    }

    protected abstract void  handleInput();
    public abstract  void update(float dt); // delta time
    public abstract void render(SpriteBatch spriteBatch); // container for everything one big blob
    public abstract void dispose();



}