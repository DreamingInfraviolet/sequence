package com.audinarium.sequence.sequence.Graphics;

import android.content.Context;

import com.audinarium.sequence.sequence.AudioPlayback;
import com.audinarium.sequence.sequence.Keyboard;
import com.audinarium.sequence.sequence.Util;

import java.util.ArrayList;
import java.util.Stack;

import processing.core.PApplet;

/**
 * Created by Volodymyr on 11/17/2016.
 */

public class PianoSketch extends PApplet
{
    ArrayList<Integer> mKeysBeingPlayed = new ArrayList<>();
    Keyboard mKeyboard = new Keyboard();
    float mKeyScaleMultiplier;
    Stack<Integer> mUndoStack = new Stack();
    Context mContext;

    public PianoSketch(Context context)
    {
        mContext = context;
    }

    public ArrayList<Integer> getKeysPlayed()
    {
        return mKeysBeingPlayed;
    }

    public int[] getKeysPlayedAsArray()
    {
        return Util.listToArrayInt(mKeysBeingPlayed);
    }

    public Stack<Integer> getUndoStack()
    {
        return mUndoStack;
    }

    public void clearKeysPlayed()
    {
        mUndoStack.clear();
        mKeysBeingPlayed.clear();
    }

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
        final float blackKeyRounding = 0.002f * mKeyScaleMultiplier;
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

                int[] keyColour = getKeyColour(key);
                this.fill(keyColour[0], keyColour[1], keyColour[2]);

                float keyRounding = (whiteOrBlack == 1) ? blackKeyRounding : 0;

                this.rect((x - w / 2), 0, w, h, 0, 0, keyRounding, keyRounding);
            }
        }
    }

    int[] getKeyColour(int key)
    {
        int colour = mKeyboard.isKeyWhite(key) ? 255 : 0;

        int[] output = new int[]{colour, colour, colour};

        // Colour-code recently played keys
        int keyPosInArray = getReverseKeyPositionInKeysBeingPlayed(key);
        final int keyDistanceInTime = mKeysBeingPlayed.size() - 1 - keyPosInArray;
        final int maxDistanceInTime = 8;

        if(keyPosInArray != -1 && mKeysBeingPlayed.size() > 0 && keyDistanceInTime <= maxDistanceInTime)
        {
            final float colourInterpolationStrength = 0.4f;
            final float colourAlpha = colourInterpolationStrength * (1 - (float) keyDistanceInTime / maxDistanceInTime);

            int[] defaultColour = Util.getDefaultColour(mContext);
            output[0] = (int) Util.lerp(output[0], defaultColour[0], colourAlpha);
            output[1] = (int) Util.lerp(output[1], defaultColour[1], colourAlpha);
            output[2] = (int) Util.lerp(output[2], defaultColour[2], colourAlpha);
        }

        return output;
    }

    private int getReverseKeyPositionInKeysBeingPlayed(int key)
    {
        for(int i = mKeysBeingPlayed.size() - 1; i >= 0; --i)
        {
            if(mKeysBeingPlayed.get(i) == key)
                return i;
        }

        return -1;
    }

    @Override
    public void mousePressed()
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
                    mUndoStack.clear();
                    AudioPlayback.play(key);
                    mKeysBeingPlayed.add(key);

                    return;
                }
            }
        }
    }
}
