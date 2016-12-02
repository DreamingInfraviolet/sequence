package com.audinarium.sequence.sequence.Graphics;

import android.util.Log;
import android.view.MotionEvent;

import com.audinarium.sequence.sequence.AudioPlayback;

import java.util.ArrayList;

import processing.core.PApplet;

/**
 * Created by Volodymyr on 11/17/2016.
 */

public class ProcessingSketch extends PApplet
{
    ArrayList<Chord.KeyNames> mKeysBeingPlayed = new ArrayList<>();
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

                this.fill(mKeyboard.isKeyWhite(key) ? 255 : 0);

                float keyRounding = (whiteOrBlack == 1) ? blackKeyRounding : 0;

                this.rect((x - w / 2), 0, w, h, 0, 0, keyRounding, keyRounding);
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
                        AudioPlayback.play(key);
                        mKeysBeingPlayed.add(keyIdToName(key));

                        return;
                    }
                }
            }
        }
    }

    Chord.KeyNames keyIdToName(int id)
    {
        //id is an index of keys from C to ...

        id = id % 12;
        return new Chord.KeyNames[]{
                //Niamh input
            Chord.KeyNames.C, Chord.KeyNames.CS, Chord.KeyNames.D, Chord.KeyNames.DS, Chord.KeyNames.E, Chord.KeyNames.F,
                Chord.KeyNames.FS, Chord.KeyNames.G, Chord.KeyNames.GS, Chord.KeyNames.A, Chord.KeyNames.AS, Chord.KeyNames.B
        }[id];
    }
}
