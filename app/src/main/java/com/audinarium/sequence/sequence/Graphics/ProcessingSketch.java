package com.audinarium.sequence.sequence.Graphics;

import android.util.Log;
import android.view.MotionEvent;

import processing.core.PApplet;

/**
 * Created by Volodymyr on 11/17/2016.
 */

public class ProcessingSketch extends PApplet
{
    Keyboard mKeyboard = new Keyboard();
    float mViewOffset = 0.0f;
    float mKeyScaleMultiplier;

    public void settings()
    {
        fullScreen();
    }

    public void setup()
    {
        mKeyScaleMultiplier = width * 5;
    }

    public void draw()
    {
        fill(0);
        clear();

        // We're going to draw the white keys first, and then the black keys.
        // I don't want to rely on 3D acceleration, soooo.... Let's loop twice ^^
        for(int whiteOrBlack = 0; whiteOrBlack < 2; ++whiteOrBlack)
        {
            for (int key = 0; key < mKeyboard.sNKeys; ++key)
            {
                if (mKeyboard.isKeyWhite(key) == (whiteOrBlack == 1))
                    continue;
                float w = mKeyboard.getKeyWidth(key) * mKeyScaleMultiplier;
                float h = mKeyboard.getKeyHeight(key) * height;
                float x = mKeyboard.getKeyPosition(key) * mKeyScaleMultiplier;

                this.fill(mKeyboard.isKeyWhite(key) ? 255 : 0);

                this.rect((x - w / 2), 0, w, h);
            }
        }
    }

    public boolean isMouseInKeyboard()
    {
        //@TODO: improve this
        return true;
    }

    @Override
    public void mousePressed()
    {
        if(isMouseInKeyboard())
        {
            for(int whiteOrBlack = 0; whiteOrBlack < 2; ++whiteOrBlack)
            {
                for (int key = 0; key < mKeyboard.sNKeys; ++key)
                {
                    // Try black keys first
                    if (mKeyboard.isKeyWhite(key) == (whiteOrBlack == 0))
                        continue;

                    float w = mKeyboard.getKeyWidth(key) * mKeyScaleMultiplier;
                    float h = mKeyboard.getKeyHeight(key) * height;
                    float x = mKeyboard.getKeyPosition(key) * mKeyScaleMultiplier;

                    x = x - w / 2;

                    if(mouseY < h && mouseX > x && mouseX < x+w)
                    {
                        Log.i("keyboard", "Pressed key " + key);
                        return;
                    }
                }
            }
        }
    }
}
