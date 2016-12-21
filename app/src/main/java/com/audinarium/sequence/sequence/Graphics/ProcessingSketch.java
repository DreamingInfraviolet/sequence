package com.audinarium.sequence.sequence.Graphics;

import android.util.Log;

import com.audinarium.sequence.sequence.AudioPlayback;
import com.audinarium.sequence.sequence.NotesPlayed;
import com.audinarium.sequence.sequence.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

import processing.core.PApplet;

/**
 * Created by Volodymyr on 11/17/2016.
 */

public class ProcessingSketch extends PApplet
{
    final int primaryColourR = 0, primaryColourG = 70, primaryColourB  = 140;
    ArrayList<Integer> mKeysBeingPlayed = new ArrayList<>();
    Keyboard mKeyboard = new Keyboard();
    float mViewOffset = 0.0f;
    float mKeyScaleMultiplier;
    Stack<Integer> mUndoStack = new Stack();

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

            output[0] = (int)(colourAlpha * primaryColourR + (1-colourAlpha) * output[0]);
            output[1] = (int)(colourAlpha * primaryColourG + (1-colourAlpha) * output[1]);
            output[2] = (int)(colourAlpha * primaryColourB + (1-colourAlpha) * output[2]);
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
