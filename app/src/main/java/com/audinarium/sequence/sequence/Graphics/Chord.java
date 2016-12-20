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
                    new Chord(KeyNames.C, KeyNames.E, KeyNames.G, "C Major"),
                    new Chord(KeyNames.CS, KeyNames.F, KeyNames.GS, "C#/D♭ Major"),
                    new Chord(KeyNames.D, KeyNames.FS, KeyNames.A, "D Major"),
                    new Chord(KeyNames.DS, KeyNames.G, KeyNames.AS, "D#/E♭ Major"),
                    new Chord(KeyNames.E, KeyNames.GS, KeyNames.B, "E Major" ),
                    new Chord(KeyNames.F, KeyNames.A, KeyNames.C, "F Major"),
                    new Chord(KeyNames.FS, KeyNames.AS, KeyNames.CS, "F#/G♭ Major"),
                    new Chord(KeyNames.G, KeyNames.B, KeyNames.D, "G Major"),
                    new Chord(KeyNames.GS, KeyNames.C, KeyNames.DS, "G#/A♭ Major"),
                    new Chord(KeyNames.A, KeyNames.CS, KeyNames.E, "A Major"),
                    new Chord(KeyNames.AS, KeyNames.D, KeyNames.F, "A#/B♭ Major"),
                    new Chord(KeyNames.B, KeyNames.DS, KeyNames.FS, "B Major")

            };
    public static Chord sMinor[] = new Chord[]
            {
                    new Chord(KeyNames.C, KeyNames.DS, KeyNames.G, "C Minor"),
                    new Chord(KeyNames.CS, KeyNames.E, KeyNames.GS, "C#/D♭ Minor"),
                    new Chord(KeyNames.D, KeyNames.F, KeyNames.A, "D Minor"),
                    new Chord(KeyNames.DS, KeyNames.FS, KeyNames.AS, "D#/E♭ Minor"),
                    new Chord(KeyNames.E, KeyNames.G, KeyNames.B, "E Minor"),
                    new Chord(KeyNames.F, KeyNames.GS, KeyNames.C, "F Minor"),
                    new Chord(KeyNames.FS, KeyNames.A, KeyNames.CS, "F#/G♭ Minor"),
                    new Chord(KeyNames.G, KeyNames.AS, KeyNames.D, "G Minor"),
                    new Chord(KeyNames.GS, KeyNames.B, KeyNames.DS, "G#/A♭ Minor"),
                    new Chord(KeyNames.A, KeyNames.C, KeyNames.E, "A Minor"),
                    new Chord(KeyNames.AS, KeyNames.CS, KeyNames.F, "A#/B♭ Minor"),
                    new Chord(KeyNames.B, KeyNames.D, KeyNames.FS, "B Minor")
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
