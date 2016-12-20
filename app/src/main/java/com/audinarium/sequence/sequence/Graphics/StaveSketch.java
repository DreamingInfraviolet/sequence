package com.audinarium.sequence.sequence.Graphics;

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
 */

public class StaveSketch extends PApplet
{
    PFont musicFont;
    int barHeight;
    int scroll = 0;
    int notePlayingIndex = -1;
    long timeOfLastNote = -1;
    Note[] mNotes = null;
    float stepX;
    int[] notePlayingColour = new int[] {0, 100, 150};
    ArrayList<Chord> mChords;

    enum State {Paused, Playing, Chord};

    State state = State.Playing;

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

    void drawBars(int n, float xOffset, float yOffset, float h)
    {
        float step = textWidth(MusicFont.staff5Lines);
        float y = yOffset + h;

        text(MusicFont.barlineSingle, xOffset, y);

        text(MusicFont.timeSignature4, xOffset, y - h / 8.0f * 2);
        text(MusicFont.timeSignature4, xOffset, y - h / 8.0f * 6);

        for (int i = 0; i < n; ++i)
        {
            float startPixel = xOffset + i * step;

            text(MusicFont.staff5Lines, startPixel, y);
            String separator = i == n - 1 ? MusicFont.barlineFinal : MusicFont.barlineSingle;
            text(separator, startPixel + step - textWidth(separator), y);
        }
    }

    /**
     * @param notes An array of note indices. Index 0 is middle C, index 1 is C#, etc. */
    void drawNotes(Note[] notes, float xOffset, float yOffset, float barHeight)
    {
        float stepY = barHeight / 8.0f;
        float startY = yOffset + barHeight + stepY * 2;

        for (int i = 0; i < notes.length; ++i)
        {
            if(i == notePlayingIndex)
                fill(notePlayingColour[0], notePlayingColour[1], notePlayingColour[2]);
            else
                fill(0, 0, 0);

            Note note = notes[i];

            float x = xOffset + stepX - textWidth(MusicFont.staff5Lines) / 8.0f + i * stepX;
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
        musicFont = createFont("Bravura.otf", barHeight);
        textFont(musicFont);
        textSize(barHeight);
        stepX = textWidth(MusicFont.staff5Lines) / 4.0f;
        frameRate(60);
    }

    float getRelativePlayBarPosition()
    {
        return notePlayingIndex * stepX;
    }

    float getXOffset()
    {
        float barPos = getRelativePlayBarPosition();
        float xOffset = width / 2 - barPos;
        return xOffset;
    }

    void drawStave()
    {
        int nBars = mNotes.length / 4 + (mNotes.length % 4 == 0 ? 0 : 1);

        {
            int xOffset = (int)getXOffset();
            int yOffset = 40;

            background(255, 255, 255);
            fill(60, 60, 60);
            drawBars(nBars, xOffset, yOffset, barHeight);
            drawNotes(mNotes, xOffset, yOffset, barHeight);
        }
    }

    void drawChords()
    {

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
        scroll = (scroll + 10) % 1000;
        drawStave();

        if(state == State.Playing)
        {
            if(shouldPlayNote())
            {
                AudioPlayback.play(mNotes[++notePlayingIndex].keyId);
                timeOfLastNote = System.currentTimeMillis();
            }
        }
    }
}
