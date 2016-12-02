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

    KeyNames doh, mi, soh;

    public Chord(KeyNames doh_, KeyNames mi_, KeyNames soh_)
    {
        doh = doh_;
        mi = mi_;
        soh = soh_;
    }

    public boolean containsKey(KeyNames key)
    {
        return doh==key || mi==key || soh==key;
    }

    public enum KeyNames {
        C, CS, D, DS, E, F, FS, G, GS, A, AS, B
    };

    public enum ChordNamesMajor{
        ChordC, ChordCS, ChordD, ChordDS, ChordE, ChordF, ChordFS, ChordG, ChordGS, ChordA, ChordAS, ChordB
    };
    public enum ChordNamesMinor{
        ChordCm, ChordCSm, ChordDm, ChordDSm, ChordEm, ChordFm, ChordFSm, ChordGm, ChordGSm, ChordAm, ChordASm, ChordBm
    };

    static void ChordOrderMajor()
    {
        Chord C = sMajor[ChordNamesMajor.ChordC.ordinal()];
        Chord Csharp = sMajor[ChordNamesMajor.ChordCS.ordinal()];
        Chord D = sMajor[ChordNamesMajor.ChordD.ordinal()];
        Chord Dsharp = sMajor[ChordNamesMajor.ChordDS.ordinal()];
        Chord E = sMajor[ChordNamesMajor.ChordE.ordinal()];
        Chord F = sMajor[ChordNamesMajor.ChordF.ordinal()];
        Chord Fsharp = sMajor[ChordNamesMajor.ChordFS.ordinal()];
        Chord G = sMajor[ChordNamesMajor.ChordG.ordinal()];
        Chord Gsharp = sMajor[ChordNamesMajor.ChordGS.ordinal()];
        Chord A = sMajor[ChordNamesMajor.ChordA.ordinal()];
        Chord Asharp = sMajor[ChordNamesMajor.ChordAS.ordinal()];
        Chord B = sMajor[ChordNamesMajor.ChordB.ordinal()];
    }
    static void ChordOrderMinor()
    {
        Chord Cm = sMinor[ChordNamesMinor.ChordCm.ordinal()];
        Chord Csharpm = sMinor[ChordNamesMinor.ChordCSm.ordinal()];
        Chord Dm = sMinor[ChordNamesMinor.ChordDm.ordinal()];
        Chord Dsharpm = sMinor[ChordNamesMinor.ChordDSm.ordinal()];
        Chord Em = sMinor[ChordNamesMinor.ChordEm.ordinal()];
        Chord Fm = sMinor[ChordNamesMinor.ChordFm.ordinal()];
        Chord Fsharpm = sMinor[ChordNamesMinor.ChordFSm.ordinal()];
        Chord Gm = sMinor[ChordNamesMinor.ChordGm.ordinal()];
        Chord Gsharpm = sMinor[ChordNamesMinor.ChordGSm.ordinal()];
        Chord Am = sMinor[ChordNamesMinor.ChordAm.ordinal()];
        Chord Asharpm = sMinor[ChordNamesMinor.ChordASm.ordinal()];
        Chord Bm = sMinor[ChordNamesMinor.ChordBm.ordinal()];
    }

    public static Chord[] sGetChordsWithKeys(KeyNames[] names)
    {
        return sGetChordsWithKeys(names, names.length);
    }

    /** Returns all chords that contain all of the key names in the chord
     * @param names An array of keys to search for
     * @param numberToSucceed Will return a chord if at least numberToSucceed keys are in it.*/
    public static Chord[] sGetChordsWithKeys(KeyNames[] names, int numberToSucceed)
    {
        ArrayList<Chord> output = new ArrayList<>();

        for(Chord c : sMajor)
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

    static Chord sMajor[] = new Chord[]
            {
                    new Chord(KeyNames.C, KeyNames.E, KeyNames.G),
                    new Chord(KeyNames.CS, KeyNames.F, KeyNames.GS),
                    new Chord(KeyNames.D, KeyNames.FS, KeyNames.A),
                    new Chord(KeyNames.DS, KeyNames.G, KeyNames.AS),
                    new Chord(KeyNames.E, KeyNames.GS, KeyNames.B),
                    new Chord(KeyNames.F, KeyNames.A, KeyNames.C),
                    new Chord(KeyNames.FS, KeyNames.AS, KeyNames.CS),
                    new Chord(KeyNames.G, KeyNames.B, KeyNames.D),
                    new Chord(KeyNames.GS, KeyNames.C, KeyNames.DS),
                    new Chord(KeyNames.A, KeyNames.CS, KeyNames.E),
                    new Chord(KeyNames.AS, KeyNames.D, KeyNames.F),
                    new Chord(KeyNames.B, KeyNames.DS, KeyNames.FS)

            };
    static Chord sMinor[] = new Chord[]
            {
                    new Chord(KeyNames.C, KeyNames.DS, KeyNames.G),
                    new Chord(KeyNames.CS, KeyNames.E, KeyNames.GS),
                    new Chord(KeyNames.D, KeyNames.F, KeyNames.A),
                    new Chord(KeyNames.DS, KeyNames.FS, KeyNames.AS),
                    new Chord(KeyNames.E, KeyNames.G, KeyNames.B),
                    new Chord(KeyNames.F, KeyNames.GS, KeyNames.C),
                    new Chord(KeyNames.FS, KeyNames.A, KeyNames.CS),
                    new Chord(KeyNames.G, KeyNames.AS, KeyNames.D),
                    new Chord(KeyNames.GS, KeyNames.B, KeyNames.DS),
                    new Chord(KeyNames.A, KeyNames.C, KeyNames.E),
                    new Chord(KeyNames.AS, KeyNames.CS, KeyNames.F),
                    new Chord(KeyNames.B, KeyNames.D, KeyNames.FS)
            };
}
