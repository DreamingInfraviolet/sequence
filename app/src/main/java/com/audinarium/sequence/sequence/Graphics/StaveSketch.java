package com.audinarium.sequence.sequence.Graphics;

import android.content.Context;
import android.util.Log;

import com.audinarium.sequence.sequence.AudioPlayback;
import com.audinarium.sequence.sequence.MusicFont;
import com.audinarium.sequence.sequence.Note;
import com.audinarium.sequence.sequence.NotesPlayed;
import com.audinarium.sequence.sequence.StaveSettings;
import com.audinarium.sequence.sequence.StaveState;
import com.audinarium.sequence.sequence.Util;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import processing.core.PApplet;
import processing.core.PFont;

/**
 * Created by Volodymyr on 12/19/2016.
 * I am embarrassed to have written it. BUT IT WORKS.
 */

public class StaveSketch extends PApplet
{
    StaveState mCurrentState;
    StaveState mPreviousState = null;
    Context mContext;

    @Override
    public void settings()
    {
        fullScreen();
    }

    public StaveSketch(int[] keys, Context context)
    {
        mContext = context;
        mCurrentState = new StaveState();
        mCurrentState.notes = Note.fromKeyIds(keys);

        Chord.KeyNames[] names = Chord.fromKeyIds(keys);
        ArrayList<Chord.KeyNames> namesList = new ArrayList<>(Arrays.asList(names));
        mCurrentState.chords = Util.listToArrayChord((new NotesPlayed()).recorded(namesList));
    }

    @Override
    public void setup()
    {
        mCurrentState.settings = StaveSettings.generateDefaultSettings(mContext, this);

        textFont(mCurrentState.settings.musicFont);
        textSize(mCurrentState.settings.barHeight);
        frameRate(mCurrentState.settings.framerate);
    }

    void drawBars()
    {
        final int n = mCurrentState.countBarsNeeded();
        final float xOffset = mCurrentState.getXOffset(this);
        final float step = textWidth(MusicFont.staff5Lines);
        final float barHeight = mCurrentState.settings.barHeight;
        final float y = mCurrentState.settings.yOffset + barHeight;

        text(mCurrentState.settings.clefSymbol, xOffset - textWidth(mCurrentState.settings.clefSymbol), y - barHeight / 2.0f);
        text(MusicFont.barlineSingle, xOffset, y);

        for (int i = 0; i < n; ++i)
        {
            float startPixel = xOffset + i * step;

            text(MusicFont.staff5Lines, startPixel, y);
            String separator = i == n - 1 ? MusicFont.barlineFinal : MusicFont.barlineSingle;
            text(separator, startPixel + step - textWidth(separator), y);
        }
    }

    void drawNotes()
    {
        float stepY = mCurrentState.settings.barHeight / 8.0f;
        float startY = mCurrentState.settings.yOffset + mCurrentState.settings.barHeight + stepY * 2;

        for (int i = 0; i < mCurrentState.notes.length; ++i)
        {
            if(i == mCurrentState.notePlayingIndex)
                fill(mCurrentState.settings.primaryColour);

            Note note = mCurrentState.notes[i];

            float x = mCurrentState.getXOffset(this) + mCurrentState.getKeyOffset(this, i);
            float y = startY - stepY * (note.index);

            text((note.index >= 6) ? MusicFont.quarterNoteDown : MusicFont.quarterNoteUp, x, y);

            if (note.index == 0 || note.index == 12)
                text(MusicFont.ledgerLine, x, y);

            if (note.offset == Note.Offset.Sharp)
                text(MusicFont.accidentalSharp, x - textWidth(MusicFont.accidentalSharp) * 1.5f, y);
            else if (note.offset == Note.Offset.Flat)
                text(MusicFont.accidentalFlat, x - textWidth(MusicFont.accidentalFlat) * 1.5f, y);
        }
    }

    private void fill(int[] colour)
    {
        fill(colour[0], colour[1], colour[2]);
    }

    void drawStave()
    {
        {
            background(255, 255, 255);
            fill(mCurrentState.settings.staveColour);
            drawBars();
            drawNotes();
            drawChords();
        }
    }


    void drawChords()
    {
        float[][] chordCentres = mCurrentState.getChordCentres(this);

        for(int i = 0; i < chordCentres.length; ++i)
        {
            float x = chordCentres[i][0];
            float y = chordCentres[i][1];
            float w = chordCentres[i][2];
            float h = chordCentres[i][3];

            fill(mCurrentState.settings.primaryColour);
            rect(x - w / 2, y  - h / 2, w, h);
            fill(0, 0, 0);
            textSize(mCurrentState.settings.textHeight);
            text(mCurrentState.chords[i].toString(), x - textWidth(mCurrentState.chords[i].toString()) / 2, y
                    + mCurrentState.settings.textHeight / 2);
            textSize(mCurrentState.settings.barHeight);
        }
    }

    @Override
    public void draw()
    {
        if (mCurrentState.state == StaveState.State.Start)
            mCurrentState.startPlayback();

        // Move towards target position
        mCurrentState.currentLookCentre =
                Util.lerp(mCurrentState.currentLookCentre,mCurrentState.desiredLookCentre, 0.8f);

        drawStave();

        if(mCurrentState.state == StaveState.State.Playing)
        {
            if(mCurrentState.shouldPlayNote())
            {
                int noteToPlay = mCurrentState.notePlayingIndex++;
                AudioPlayback.play(mCurrentState.notes[noteToPlay].keyId);

                if(noteToPlay % 4 == 0 && (noteToPlay / 4 < mCurrentState.chords.length))
                    AudioPlayback.play(mCurrentState.chords[noteToPlay / 4]);

                mCurrentState.timeOfLastNote = System.currentTimeMillis();
            }

            // No more notes to play
            if(!(mCurrentState.notePlayingIndex < mCurrentState.notes.length))
                mCurrentState.pausePlayback();

            if(mCurrentState.shouldFollowPlayingIndex)
                mCurrentState.desiredLookCentre = mCurrentState.notePlayingIndex * mCurrentState.settings.getXNoteOffset(this);
        }
    }

    @Override
    public void mousePressed()
    {
        // If user tapped while chord was showing, close it
        if(mPreviousState != null)
        {
            mCurrentState = mPreviousState;
            mPreviousState = null;
        }
        else
        {
            float[][] chordCentres = mCurrentState.getChordCentres(this);

            for (int i = 0; i < chordCentres.length; ++i)
            {
                float x = chordCentres[i][0];
                float y = chordCentres[i][1];
                float w = chordCentres[i][2];
                float h = chordCentres[i][3];

                if (mouseX > (x - w / 2) && mouseX < (x + w / 2)
                        && mouseY > (y - h / 2) && mouseY < (y + h / 2))
                {
                    transitionToChordState(mCurrentState.chords[i]);
                    break;
                }
            }
        }
    }

    private void transitionToChordState(Chord chord)
    {
        mPreviousState = mCurrentState;
        mCurrentState = new StaveState();
        mCurrentState.settings = StaveSettings.generateChordDisplaySettings(mContext, this);
        mCurrentState.notes = new Note[]{Note.fromKeyName(chord.doh).toBass(),
                                         Note.fromKeyName(chord.mi).toBass(),
                                         Note.fromKeyName(chord.soh).toBass()};
    }

    @Override
    public void mouseDragged()
    {
        mCurrentState.shouldFollowPlayingIndex = false;
        mCurrentState.desiredLookCentre -= mouseX - pmouseX;
    }
}
