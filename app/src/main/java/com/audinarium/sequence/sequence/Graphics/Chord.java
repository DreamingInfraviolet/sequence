package com.audinarium.sequence.sequence.Graphics;

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

    public enum KeyNames {
        C, CS, D, DS, E, F, FS, G, GS, A, AS, B
    };

    public enum ChordNamesMajor{
        ChordC, ChordCS, ChordD, ChordDS, ChordE,  ChordF, ChordFS, ChordG, ChordGS, ChordA, ChordAS, ChordB
    };
    public enum ChordNamesMinor{
        ChordCm, ChordCSm, ChordDm, ChordDSm, ChordEm,  ChordFm, ChordFSm, ChordGm, ChordGSm, ChordAm, ChordASm, ChordBm
    };

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
