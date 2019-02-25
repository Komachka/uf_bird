package com.kstorozh.game.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.ArrayList;

public class BirdAnimation {
    private ArrayList<TextureRegion> frames = new ArrayList<TextureRegion>();
    private float maxFrameTime;
    private float currentFrameTime;

    private int frameCount;
    private int frameCurrent;

    public BirdAnimation(Texture texture, int frameCount, float cycleTyme) {

        TextureRegion textureRegion = new TextureRegion(texture);
        int frameWidth = textureRegion.getRegionWidth()/frameCount;
        for (int i = 0; i <frameCount ; i++) {
            frames.add(new TextureRegion(textureRegion, i * frameWidth, 0, frameWidth, textureRegion.getRegionHeight()));
        }
        this.frameCount = frameCount;
        maxFrameTime = cycleTyme / frameCount;
        frameCurrent = 0;

    }


    public void update(float dt)
    {
        currentFrameTime += dt;
        if (currentFrameTime > maxFrameTime)
        {
            frameCurrent++;
            currentFrameTime = 0;
        }
        if (frameCurrent >= frameCount)
        {
            frameCurrent = 0;
        }
    }

    public TextureRegion getFrame()
    {
        return frames.get(frameCurrent);
    }

}