package com.audinarium.sequence.sequence;

import com.audinarium.sequence.sequence.Graphics.Chord;

import java.util.Collection;

/**
 * Created by Volodymyr on 12/20/2016.
 */

public class Note implements Comparable<Note>
{
    @Override
    public int compareTo(Note o)
    {
        if(keyId == o.keyId)
            return 0;
        if(keyId > o.keyId)
            return 1;
        else
            return -1;
    }

    public enum Offset {None, Sharp, Flat};

    /** Index of the note, starting from middle C. */
    public int index;
    public int keyId;

    public Offset offset;

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

    public Note toBass()
    {
        Note note = fromKeyId(keyId + 5);
        index = note.index;
        offset = note.offset;
        return this;
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

    public static Note[] fromKeyIds(int[] ids)
    {
        Note[] notes = new Note[ids.length];

        for(int i = 0; i < ids.length; ++i)
            notes[i] = Note.fromKeyId(ids[i]);

        return notes;
    }

    public static Note fromKeyName(Chord.KeyNames name)
    {
        return fromKeyId(name.ordinal());
    }
}
