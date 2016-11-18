package com.audinarium.sequence.sequence.Graphics;

import android.opengl.GLES20;
import android.opengl.Matrix;

import processing.core.PApplet;

/**
 * Created by Volodymyr on 11/17/2016.
 */

public class ProcessingSketch extends PApplet
{
    Keyboard mKeyboard = new Keyboard();
    float mViewOffset = 0.0f;


    public void settings()
    {
        fullScreen();
    }

    public void setup() { }

    public void draw()
    {
        float scale = width * 5;
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
                float w = mKeyboard.getKeyWidth(key) * scale;
                float h = mKeyboard.getKeyHeight(key) * height;
                float x = mKeyboard.getKeyPosition(key) * scale;

                this.fill(mKeyboard.isKeyWhite(key) ? 255 : 0);

                this.rect((x - w / 2), 0, w, h);
            }
        }
    }
}
