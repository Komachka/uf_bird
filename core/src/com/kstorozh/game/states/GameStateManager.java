package com.kstorozh.game.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

public class GameStateManager {
    private Stack<State> stateStack;


    public GameStateManager() {
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
