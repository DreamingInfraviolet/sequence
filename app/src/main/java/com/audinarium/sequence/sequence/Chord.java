package com.audinarium.sequence.sequence;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Niamh on 29/11/2016.
 * Represents a chord, as well as some chord-related methods.
 */

public class Chord{

    public KeyName doh, mi, soh;
    public String name;

    public Chord(KeyName doh_, KeyName mi_, KeyName soh_, String name_)
    {
        doh = doh_;
        mi = mi_;
        soh = soh_;
        name = name_;
    }

    @Override
    public String toString()
    {
        return name;
    }

    public boolean containsKey(KeyName key)
    {
        return doh==key || mi==key || soh==key;
    }

    public enum KeyName
    {
        C, CS, D, DS, E, F, FS, G, GS, A, AS, B
    };

    public enum ChordNames{
        ChordC, ChordCS, ChordD, ChordDS, ChordE, ChordF, ChordFS, ChordG, ChordGS, ChordA, ChordAS, ChordB
    };

    public static Chord[] sGetChordsWithKeys(KeyName[] names, Chord[] chordList)
    {
        return sGetChordsWithKeys(names, chordList, names.length);
    }

    /** Returns all chords that contain all of the key names in the chord
     * @param names An array of keys to search for
     * @param numberToSucceed Will return a chord if at least numberToSucceed keys are in it.*/
    public static Chord[] sGetChordsWithKeys(KeyName[] names, Chord[] chordList, int numberToSucceed)
    {
        ArrayList<Chord> output = new ArrayList<>();

        for(Chord c : chordList)
        {
            int successes = 0;

            for(int i = 0; i < names.length; ++i)
                if(c.containsKey(names[i]))
                    ++successes;

            if(successes >= numberToSucceed)
                output.add(c);
        }

        return output.toArray(new Chord[output.size()]);
    }

    public static Chord sMajor[] = new Chord[]
            {
                    new Chord(KeyName.C, KeyName.E, KeyName.G, "C"),
                    new Chord(KeyName.CS, KeyName.F, KeyName.GS, "C#/D♭"),
                    new Chord(KeyName.D, KeyName.FS, KeyName.A, "D"),
                    new Chord(KeyName.DS, KeyName.G, KeyName.AS, "D#/E♭"),
                    new Chord(KeyName.E, KeyName.GS, KeyName.B, "E" ),
                    new Chord(KeyName.F, KeyName.A, KeyName.C, "F"),
                    new Chord(KeyName.FS, KeyName.AS, KeyName.CS, "F#/G♭"),
                    new Chord(KeyName.G, KeyName.B, KeyName.D, "G"),
                    new Chord(KeyName.GS, KeyName.C, KeyName.DS, "G#/A♭"),
                    new Chord(KeyName.A, KeyName.CS, KeyName.E, "A"),
                    new Chord(KeyName.AS, KeyName.D, KeyName.F, "A#/B♭"),
                    new Chord(KeyName.B, KeyName.DS, KeyName.FS, "B")

            };
    public static Chord sMinor[] = new Chord[]
            {
                    new Chord(KeyName.C, KeyName.DS, KeyName.G, "Cm"),
                    new Chord(KeyName.CS, KeyName.E, KeyName.GS, "C#/D♭m"),
                    new Chord(KeyName.D, KeyName.F, KeyName.A, "Dm"),
                    new Chord(KeyName.DS, KeyName.FS, KeyName.AS, "D#/E♭m"),
                    new Chord(KeyName.E, KeyName.G, KeyName.B, "Em"),
                    new Chord(KeyName.F, KeyName.GS, KeyName.C, "Fm"),
                    new Chord(KeyName.FS, KeyName.A, KeyName.CS, "F#/G♭m"),
                    new Chord(KeyName.G, KeyName.AS, KeyName.D, "Gm"),
                    new Chord(KeyName.GS, KeyName.B, KeyName.DS, "G#/A♭m"),
                    new Chord(KeyName.A, KeyName.C, KeyName.E, "Am"),
                    new Chord(KeyName.AS, KeyName.CS, KeyName.F, "A#/B♭m"),
                    new Chord(KeyName.B, KeyName.D, KeyName.FS, "Bm")
            };




    public static KeyName keyIdToName(int id)
    {
        //id is an index of keys from C to ...

        id = id % 12;
        return new KeyName[]{
                //Niamh input
                KeyName.C, KeyName.CS, KeyName.D, KeyName.DS, KeyName.E, KeyName.F,
                KeyName.FS, KeyName.G, KeyName.GS, KeyName.A, KeyName.AS, KeyName.B
        }[id];
    }

    public static KeyName[] fromKeyIds(int[] ids)
    {
        KeyName[] array = new KeyName[ids.length];
        for(int i = 0; i < ids.length; ++i)
            array[i] = keyIdToName(ids[i]);
        return array;
    }

    public Note[] getConsecutiveNotes()
    {
        Note a = Note.fromKeyName(doh);
        Note b = Note.fromKeyName(mi);
        Note c = Note.fromKeyName(soh);
        Note[] arr = new Note[]{a, b, c};
        Arrays.sort(arr);
        return arr;
    }
}
