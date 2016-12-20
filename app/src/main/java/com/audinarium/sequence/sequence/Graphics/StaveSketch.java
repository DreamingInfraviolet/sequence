package com.audinarium.sequence.sequence.Graphics;

import android.util.Log;

import com.audinarium.sequence.sequence.AudioPlayback;
import com.audinarium.sequence.sequence.MusicFont;
import com.audinarium.sequence.sequence.Note;
import com.audinarium.sequence.sequence.NotesPlayed;

import java.lang.reflect.Array;
import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PFont;

/**
 * Created by Volodymyr on 12/19/2016.
 * I am embarrassed to have written it. BUT IT WORKS.
 */

public class StaveSketch extends PApplet
{
    PFont musicFont;
    int barHeight;
    int scroll = 0;
    int notePlayingIndex = -1;
    float currentLookCentre = notePlayingIndex;
    float desiredLookCentre = notePlayingIndex;
    long timeOfLastNote = -1;
    Note[] mNotes = null;
    float stepX;
    int[] notePlayingColour = new int[] {0, 100, 150};
    ArrayList<Chord> mChords;
    float textHeight;
    int yOffset = 40;

    boolean shouldFollowPlayingIndex = false;
    Chord chordToShow = null;

    enum State {Start, Paused, Playing, End};

    State state = State.Start;

    void startPlayback()
    {
        shouldFollowPlayingIndex = true;
        if(state == State.End)
            notePlayingIndex = -1;
        state = State.Playing;
    }

    void pausePlayback()
    {
        shouldFollowPlayingIndex = false;
        state = State.Paused;
    }

    public StaveSketch(ArrayList<Integer> keys)
    {
        mNotes = new Note[keys.size()];

        for(int i = 0; i < mNotes.length; ++i)
        {
            mNotes[i] = Note.fromKeyId(keys.get(i));
        }

        NotesPlayed np = new NotesPlayed();
        ArrayList<Chord.KeyNames> npIn = new ArrayList<>();
        for(int k : keys)
            npIn.add(Chord.keyIdToName(k));
        mChords = np.recorded(npIn);
    }

    void drawBars(int n, float xOffset, float yOffset)
    {
        float step = textWidth(MusicFont.staff5Lines);
        float y = yOffset + barHeight;

        if(chordToShow != null)
            text(MusicFont.bass, xOffset - textWidth(MusicFont.bass), y - barHeight / 2.0f);
        else
        {
            text(MusicFont.timeSignature4, xOffset - textWidth(MusicFont.timeSignature4), y - barHeight / 8.0f * 2);
            text(MusicFont.timeSignature4, xOffset - textWidth(MusicFont.timeSignature4), y - barHeight / 8.0f * 6);
        }

        text(MusicFont.barlineSingle, xOffset, y);


        for (int i = 0; i < n; ++i)
        {
            float startPixel = xOffset + i * step;

            text(MusicFont.staff5Lines, startPixel, y);
            String separator = i == n - 1 ? MusicFont.barlineFinal : MusicFont.barlineSingle;
            text(separator, startPixel + step - textWidth(separator), y);
        }
    }

    float getKeyOffset(int playIndex)
    {
        return stepX - textWidth(MusicFont.staff5Lines) / 8.0f + playIndex * stepX;
    }

    /**
     * @param notes An array of note indices. Index 0 is middle C, index 1 is C#, etc. */
    void drawNotes(Note[] notes, float xOffset, float yOffset)
    {
        float stepY = barHeight / 8.0f;
        float startY = yOffset + barHeight + stepY * 2;

        for (int i = 0; i < notes.length; ++i)
        {
            if(i == notePlayingIndex && chordToShow == null)
                fill(notePlayingColour[0], notePlayingColour[1], notePlayingColour[2]);
            else
                fill(0, 0, 0);

            Note note = notes[i];

            float x = xOffset + getKeyOffset(i);
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

    @Override
    public void settings()
    {
        fullScreen();
    }

    @Override
    public void setup()
    {
        barHeight = height / 4;
        textHeight = barHeight / 2;
        musicFont = createFont("Bravura.otf", barHeight);
        textFont(musicFont);
        textSize(barHeight);
        stepX = textWidth(MusicFont.staff5Lines) / 4.0f;
        frameRate(60);
    }

    float getXOffset()
    {
        if(chordToShow != null)
            return textWidth(MusicFont.bass) + textWidth(MusicFont.bass) / 10;

        float barPos = currentLookCentre;
        float xOffset = width / 2 - barPos;
        return xOffset;
    }

    void drawStave()
    {

        {
            background(255, 255, 255);
            fill(60, 60, 60);

            int xOffset = (int)getXOffset();
            int nBars;
            Note[] notes;

            if(chordToShow == null)
            {
                nBars = chordToShow == null ? (mNotes.length / 4 + (mNotes.length % 4 == 0 ? 0 : 1)) : 1;
                notes = mNotes;
            }
            else
            {
                nBars = 1;
                notes = new Note[]{Note.fromKeyName(chordToShow.doh), Note.fromKeyName(chordToShow.mi), Note.fromKeyName(chordToShow.soh)};
            }

            drawBars(nBars, xOffset, yOffset);
            drawNotes(notes, xOffset, yOffset);

            if(chordToShow == null)
                drawChords(xOffset, yOffset, barHeight);
        }
    }

    void getChordCentre(float[] output, int chordIndex, float rectH, float xOffset)
    {
        float xPosition = xOffset + chordIndex * textWidth(MusicFont.staff5Lines) + textWidth(MusicFont.staff5Lines) / 2.0f;
        float yPosition = yOffset + barHeight + barHeight / 2 + rectH;
        output[0] = xPosition;
        output[1] = yPosition;
    }

    float[][] getChordCentres(float xOffset)
    {
        float[][] output = new float[mChords.size()][4];

        float rectW = textWidth(MusicFont.staff5Lines) * 0.9f;
        float rectH = textHeight * 1.4f;

        for(int i = 0; i < mChords.size(); ++i)
        {
            float[] centre = new float[2];
            getChordCentre(centre, i, rectH, xOffset);

            output[i][0] = centre[0];
            output[i][1] = centre[1];
            output[i][2] = rectW;
            output[i][3] = rectH;
        }

        return output;
    }

    void drawChords(float xOffset, float yOffset, float barHeight)
    {
        float[][] chordCentres = getChordCentres(xOffset);

        for(int i = 0; i < chordCentres.length; ++i)
        {
            float x = chordCentres[i][0];
            float y = chordCentres[i][1];
            float w = chordCentres[i][2];
            float h = chordCentres[i][3];

            fill(0, 100, 200);
            rect(x - w / 2, y  - h / 2, w, h);
            fill(0, 0, 0);
            textSize(textHeight);
            text(mChords.get(i).toString(), x - textWidth(mChords.get(i).toString()) / 2, y + textHeight / 2);
            textSize(barHeight);
        }
    }

    long timeSinceLastNote()
    {
        return System.currentTimeMillis() - timeOfLastNote;
    }


    boolean shouldPlayNote()
    {
        // If notes remain, state is playing, and last played 1/4th of a second ago
        return notePlayingIndex + 1 < mNotes.length
                && state == State.Playing
                && timeSinceLastNote() >= 250;
    }

    @Override
    public void draw()
    {
        if(chordToShow == null)
        {
            if (state == State.Start)
                startPlayback();

            // Move towards target position
            float lerpAlpha = 0.8f;
            currentLookCentre = (lerpAlpha) * desiredLookCentre + (1 - lerpAlpha) * currentLookCentre;

            scroll = (scroll + 10) % 1000;
        }

        drawStave();

        if(chordToShow == null && state == State.Playing)
        {
            if(shouldPlayNote())
            {
                int noteToPlay = ++notePlayingIndex;
                AudioPlayback.play(mNotes[noteToPlay].keyId);

                if(noteToPlay % 4 == 0)
                    AudioPlayback.play(mChords.get(noteToPlay / 4));

                timeOfLastNote = System.currentTimeMillis();
            }

            // No more notes to play
            if(!(notePlayingIndex + 1 < mNotes.length))
                pausePlayback();

            if(shouldFollowPlayingIndex)
                desiredLookCentre = notePlayingIndex * stepX;
        }
    }

    @Override
    public void mousePressed()
    {
        // If user tapped while chord was showing, close it
        if(chordToShow != null)
            chordToShow = null;
        else
        {
            float[][] chordCentres = getChordCentres(getXOffset());

            for (int i = 0; i < chordCentres.length; ++i)
            {
                float x = chordCentres[i][0];
                float y = chordCentres[i][1];
                float w = chordCentres[i][2];
                float h = chordCentres[i][3];

                if (mouseX > (x - w / 2) && mouseX < (x + w / 2)
                        && mouseY > (y - h / 2) && mouseY < (y + h / 2))
                {
                    chordToShow = mChords.get(i);
                    break;
                }
            }
        }
    }

    @Override
    public void mouseDragged()
    {
        shouldFollowPlayingIndex = false;
        desiredLookCentre -= mouseX - pmouseX;
    }
}
