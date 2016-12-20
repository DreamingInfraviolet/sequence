package com.audinarium.sequence.sequence.Graphics;

import java.util.ArrayList;

import static android.R.attr.name;
import static android.R.id.list;
import static android.os.Build.VERSION_CODES.M;
import static com.audinarium.sequence.sequence.Graphics.Chord.KeyNames.C;

/**
 * Created by Niamh on 29/11/2016.
 */

public class Chord{

    public KeyNames doh, mi, soh;
    public String name;

    public Chord(KeyNames doh_, KeyNames mi_, KeyNames soh_, String name_)
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

    public boolean containsKey(KeyNames key)
    {
        return doh==key || mi==key || soh==key;
    }

    public enum KeyNames {
        C, CS, D, DS, E, F, FS, G, GS, A, AS, B
    };

    public enum ChordNames{
        ChordC, ChordCS, ChordD, ChordDS, ChordE, ChordF, ChordFS, ChordG, ChordGS, ChordA, ChordAS, ChordB
    };

    public static Chord[] sGetChordsWithKeys(KeyNames[] names, Chord[] chordList)
    {
        return sGetChordsWithKeys(names, chordList, names.length);
    }

    /** Returns all chords that contain all of the key names in the chord
     * @param names An array of keys to search for
     * @param numberToSucceed Will return a chord if at least numberToSucceed keys are in it.*/
    public static Chord[] sGetChordsWithKeys(KeyNames[] names, Chord[] chordList, int numberToSucceed)
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
                    new Chord(KeyNames.C, KeyNames.E, KeyNames.G, "C"),
                    new Chord(KeyNames.CS, KeyNames.F, KeyNames.GS, "C#/D♭"),
                    new Chord(KeyNames.D, KeyNames.FS, KeyNames.A, "D"),
                    new Chord(KeyNames.DS, KeyNames.G, KeyNames.AS, "D#/E♭"),
                    new Chord(KeyNames.E, KeyNames.GS, KeyNames.B, "E" ),
                    new Chord(KeyNames.F, KeyNames.A, KeyNames.C, "F"),
                    new Chord(KeyNames.FS, KeyNames.AS, KeyNames.CS, "F#/G♭"),
                    new Chord(KeyNames.G, KeyNames.B, KeyNames.D, "G"),
                    new Chord(KeyNames.GS, KeyNames.C, KeyNames.DS, "G#/A♭"),
                    new Chord(KeyNames.A, KeyNames.CS, KeyNames.E, "A"),
                    new Chord(KeyNames.AS, KeyNames.D, KeyNames.F, "A#/B♭"),
                    new Chord(KeyNames.B, KeyNames.DS, KeyNames.FS, "B")

            };
    public static Chord sMinor[] = new Chord[]
            {
                    new Chord(KeyNames.C, KeyNames.DS, KeyNames.G, "Cm"),
                    new Chord(KeyNames.CS, KeyNames.E, KeyNames.GS, "C#/D♭m"),
                    new Chord(KeyNames.D, KeyNames.F, KeyNames.A, "Dm"),
                    new Chord(KeyNames.DS, KeyNames.FS, KeyNames.AS, "D#/E♭m"),
                    new Chord(KeyNames.E, KeyNames.G, KeyNames.B, "Em"),
                    new Chord(KeyNames.F, KeyNames.GS, KeyNames.C, "Fm"),
                    new Chord(KeyNames.FS, KeyNames.A, KeyNames.CS, "F#/G♭m"),
                    new Chord(KeyNames.G, KeyNames.AS, KeyNames.D, "Gm"),
                    new Chord(KeyNames.GS, KeyNames.B, KeyNames.DS, "G#/A♭m"),
                    new Chord(KeyNames.A, KeyNames.C, KeyNames.E, "Am"),
                    new Chord(KeyNames.AS, KeyNames.CS, KeyNames.F, "A#/B♭m"),
                    new Chord(KeyNames.B, KeyNames.D, KeyNames.FS, "Bm")
            };




    public static Chord.KeyNames keyIdToName(int id)
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
