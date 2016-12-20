package com.audinarium.sequence.sequence.Graphics;

import com.audinarium.sequence.sequence.AudioPlayback;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PFont;

/**
 * Created by Volodymyr on 12/19/2016.
 */

class Note
{
    enum Offset {None, Sharp, Flat};

    /** Index of the note, starting from middle C. */
    public int index;
    public int keyId;

    Offset offset;

    public Note()
    {
    }

    public Note(int index)
    {
        this.index = index;
    }

    public Note(int keyId, int index, Offset offset)
    {
        this.keyId = keyId;
        this.index = index;
        this.offset = offset;
    }

    public static Note fromKeyId(int id)
    {
        int octave = id / 12;
        int key = id % 12;

        int noteIndex = new int[]{0, 0, 1, 1, 2, 3, 3, 4, 4, 5, 5, 6}[key];
        Offset offset = (key == 1 || key == 3 || key == 6 || key == 8 || key == 10) ? Offset.Sharp : Offset.None;

        noteIndex += octave * 7;

        return new Note(id, noteIndex, offset);
    }
}

class MusicFont
{
    public final static String clef = "\uE050";
    public final static String barlineSingle = "\uE030";
    public final static String barlineFinal = "\uE032";
    public final static String staff5Lines = "\uE014\uE014\uE014\uE014\uE014\uE014\uE014\uE014";
    public final static String quarterNoteDown = "\uE1D6";
    public final static String quarterNoteUp = "\uE1D5";
    public final static String ledgerLine = "\uE022";
    public final static String accidentalFlat = "\uE260";
    public final static String accidentalSharp = "\uE262";
    public final static String timeSignature4 = "\uE084";
}

public class StaveSketch extends PApplet
{
    PFont musicFont;
    int barHeight;
    int scroll = 0;
    int notePlayingIndex = -1;
    long timeOfLastNote = -1;
    Note[] mNotes = null;

    enum State {Paused, Playing, Chord};

    State state = State.Playing;

    public StaveSketch(ArrayList<Integer> keys)
    {
        mNotes = new Note[keys.size()];

        for(int i = 0; i < mNotes.length; ++i)
        {
            mNotes[i] = Note.fromKeyId(keys.get(i));
        }
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
        float stepX = textWidth(MusicFont.staff5Lines) / 4.0f;
        float startY = yOffset + barHeight + stepY * 2;

        for (int i = 0; i < notes.length; ++i)
        {
            Note note = notes[i];

            float x = xOffset + stepX - textWidth(MusicFont.staff5Lines) / 8.0f + i * stepX;
            float y = startY - stepY * (note.index);

            text(MusicFont.quarterNoteUp, x, y);

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
        barHeight = height / 3;
        musicFont = createFont("Bravura.otf", barHeight);
        textFont(musicFont);
        textSize(barHeight);
        frameRate(60);
    }

    void drawPlayBar(int xOffset, int yOffset, int barHeight)
    {
        float stepX = textWidth(MusicFont.staff5Lines) / 4.0f;
        rect(xOffset + notePlayingIndex * stepX, yOffset, barHeight, 4);
    }

    void drawStave()
    {
        int nBars = mNotes.length / 4 + (mNotes.length % 4 == 0 ? 0 : 1);

        {
            int xOffset = 10 - scroll;
            int yOffset = 40;

            background(255, 255, 255);
            fill(60, 60, 60);
            drawBars(nBars, xOffset, yOffset, barHeight);
            fill(0, 0, 0);
            drawNotes(mNotes, xOffset, yOffset, barHeight);
            fill(0, 40, 200);
            drawPlayBar(xOffset, yOffset, barHeight);
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
