package com.kstorozh.game.controller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.kstorozh.game.view.View;

import java.util.Stack;

public class GameStateController {
    private Stack<View> stateStack;


    public GameStateController() {
        stateStack = new Stack<View>();
    }

    public void pushState(View state)
    {
        stateStack.push(state);
    }

    public void popState()
    {

        stateStack.pop().dispose();
    }

    public void setState(View state)
    {
        stateStack.pop().dispose();
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
