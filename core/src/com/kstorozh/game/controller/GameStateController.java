package com.kstorozh.game.controller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.kstorozh.game.view.State;

import java.util.Stack;

public class GameStateController {
    private Stack<State> stateStack;


    public GameStateController() {
        stateStack = new Stack<State>();
    }

    public void pushState(State state)
    {
        stateStack.push(state);
    }

    public void popState()
    {
        stateStack.pop();
    }

    public void setState(State state)
    {
        stateStack.pop();
        stateStack.push(state);
    }

    public void update(float dt) // change time btw  2 randers
    {
        stateStack.peek().update(dt);
    }

    public void render(SpriteBatch spriteBatch)
    {
        stateStack.peek().render(spriteBatch);
    }
}
