package com.audinarium.sequence.sequence;

/**
 * Created by Volodymyr on 12/20/2016.
 * A musical note class representing a note with an index that can also be drawn on a stave.
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

    public Note(int keyId, int index, Offset offset)
    {
        this.keyId = keyId;
        this.index = index;
        this.offset = offset;
    }

    public Note toBass()
    {
        Note note = fromKeyId(keyId, true);
        index = note.index;
        offset = note.offset;
        return this;
    }

    public static Note fromKeyId(int id)
    {
        return fromKeyId(id, false);
    }

    public static Note fromKeyId(int id, boolean bass)
    {
        int octave = id / 12;
        int key = id % 12;

        int noteIndex = new int[]{0, 0, 1, 1, 2, 3, 3, 4, 4, 5, 5, 6}[key] + (bass ? 5 : 0);
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

    public static Note fromKeyName(Chord.KeyName name)
    {
        return fromKeyId(name.ordinal());
    }
}
