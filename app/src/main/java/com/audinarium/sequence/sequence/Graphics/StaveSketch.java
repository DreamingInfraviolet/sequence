package com.audinarium.sequence.sequence.Graphics;

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

    Offset offset;

    public Note()
    {
    }

    public Note(int index)
    {
        this.index = index;
    }

    public Note(int index, Offset offset)
    {
        this.index = index;
        this.offset = offset;
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
        frameRate(30);
    }

    @Override
    public void draw()
    {
        Note[] notes = new Note[] {
                new Note(0, Note.Offset.None),
                new Note(0, Note.Offset.Sharp),
                new Note(1, Note.Offset.Flat),
                new Note(1, Note.Offset.None),
                new Note(1, Note.Offset.Sharp),
                new Note(2, Note.Offset.Flat),
                new Note(2, Note.Offset.None),
                new Note(3, Note.Offset.None),
                new Note(3, Note.Offset.Sharp),
                new Note(4, Note.Offset.Flat),
                new Note(4, Note.Offset.None),
                new Note(4, Note.Offset.Sharp),
                new Note(5, Note.Offset.Flat),
                new Note(5, Note.Offset.None),
                new Note(5, Note.Offset.Sharp),
                new Note(6, Note.Offset.Flat),
                new Note(6, Note.Offset.None),
        };

        int nBars = notes.length / 4 + (notes.length % 4 == 0 ? 0 : 1);

        scroll = (scroll + 10) % 1000;
        {
            int xOffset = 10 - scroll;

            background(255, 255, 255);
            fill(60, 60, 60);
            drawBars(6, xOffset, 40, barHeight);
            fill(0, 0, 0);
            drawNotes(notes, xOffset, 40, barHeight);
        }
    }
}
