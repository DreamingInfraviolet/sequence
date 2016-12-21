package com.audinarium.sequence.sequence;

import java.util.ArrayList;

/**
 * Created by Niamh on 01/12/2016.
 * This module computes the chords for a sequence of notes
 */

public class ChordComputationModule
{
    static boolean anyAre(Chord.KeyName a, Chord.KeyName b, Chord.KeyName c, Chord.KeyName d, Chord.KeyName q)
    {
        return a == q || b == q || c == q || d == q;
    }

    static boolean anyAre(Chord.KeyName a, Chord.KeyName b, Chord.KeyName c, Chord.KeyName q)
    {
        return a == q || b == q || c == q;
    }

    static boolean anyAre(Chord.KeyName a, Chord.KeyName b, Chord.KeyName q)
    {
        return a == q || b == q;
    }

    static boolean anyAre(Chord.KeyName a, Chord.KeyName q)
    {
        return a == q;
    }

    public ArrayList<Chord> recorded(ArrayList<Chord.KeyName> notesPlayed)
    {
        ArrayList<Chord> chordOutput = new ArrayList<Chord>();

        for (int i = 0; i < notesPlayed.size(); i += 4)
        {
            int nToProcess = notesPlayed.size() - i;

            if (nToProcess == 2)
            {
                Chord.KeyName keyA = notesPlayed.get(i);
                Chord.KeyName keyB = notesPlayed.get(i+1);

                boolean c = anyAre(keyA, keyB, Chord.KeyName.C);
                boolean cs = anyAre(keyA, keyB, Chord.KeyName.CS);
                boolean d = anyAre(keyA, keyB, Chord.KeyName.D);
                boolean ds = anyAre(keyA, keyB, Chord.KeyName.DS);
                boolean e = anyAre(keyA, keyB, Chord.KeyName.E);
                boolean f = anyAre(keyA, keyB, Chord.KeyName.F);
                boolean fs = anyAre(keyA, keyB, Chord.KeyName.FS);
                boolean g = anyAre(keyA, keyB, Chord.KeyName.G);
                boolean gs = anyAre(keyA, keyB, Chord.KeyName.GS);
                boolean a = anyAre(keyA, keyB, Chord.KeyName.A);
                boolean as = anyAre(keyA, keyB, Chord.KeyName.AS);
                boolean b = anyAre(keyA, keyB, Chord.KeyName.B);

                if(c && e || c && g)
                {
                    chordOutput.add(Chord.sMajor[Chord.ChordNames.ChordC.ordinal()]);
                }
                else if(c && ds)
                {
                    chordOutput.add(Chord.sMinor[Chord.ChordNames.ChordC.ordinal()]);
                }
                else if(cs && f || cs && gs)
                {
                    chordOutput.add(Chord.sMajor[Chord.ChordNames.ChordCS.ordinal()]);
                }
                else if(cs && e)
                {
                    chordOutput.add(Chord.sMinor[Chord.ChordNames.ChordCS.ordinal()]);
                }
                else if(d && fs || d && a)
                {
                    chordOutput.add(Chord.sMajor[Chord.ChordNames.ChordD.ordinal()]);
                }
                else if(d && f)
                {
                    chordOutput.add(Chord.sMinor[Chord.ChordNames.ChordD.ordinal()]);
                }
                else if(ds && g || ds && as)
                {
                    chordOutput.add(Chord.sMajor[Chord.ChordNames.ChordDS.ordinal()]);
                }
                else if(ds && fs)
                {
                    chordOutput.add(Chord.sMinor[Chord.ChordNames.ChordDS.ordinal()]);
                }
                else if(e && gs || e && b)
                {
                    chordOutput.add(Chord.sMajor[Chord.ChordNames.ChordE.ordinal()]);
                }
                else if(e && g)
                {
                    chordOutput.add(Chord.sMinor[Chord.ChordNames.ChordE.ordinal()]);
                }
                else if(f && a || f && c)
                {
                    chordOutput.add(Chord.sMajor[Chord.ChordNames.ChordF.ordinal()]);
                }
                else if(f && gs)
                {
                    chordOutput.add(Chord.sMinor[Chord.ChordNames.ChordF.ordinal()]);
                }
                else if(fs && as || fs && cs)
                {
                    chordOutput.add(Chord.sMajor[Chord.ChordNames.ChordFS.ordinal()]);
                }
                else if(fs && a)
                {
                    chordOutput.add(Chord.sMinor[Chord.ChordNames.ChordFS.ordinal()]);
                }
                else if(g && b || g && d)
                {
                    chordOutput.add(Chord.sMajor[Chord.ChordNames.ChordG.ordinal()]);
                }
                else if(g && as)
                {
                    chordOutput.add(Chord.sMinor[Chord.ChordNames.ChordG.ordinal()]);
                }
                else if(gs && c || gs && ds)
                {
                    chordOutput.add(Chord.sMajor[Chord.ChordNames.ChordGS.ordinal()]);
                }
                else if(gs && b)
                {
                    chordOutput.add(Chord.sMinor[Chord.ChordNames.ChordGS.ordinal()]);
                }
                else if(a && cs || a && e)
                {
                    chordOutput.add(Chord.sMajor[Chord.ChordNames.ChordA.ordinal()]);
                }
                else if(a && c)
                {
                    chordOutput.add(Chord.sMinor[Chord.ChordNames.ChordA.ordinal()]);
                }
                else if(as && d || as && f)
                {
                    chordOutput.add(Chord.sMajor[Chord.ChordNames.ChordAS.ordinal()]);
                }
                else if(as && cs)
                {
                    chordOutput.add(Chord.sMinor[Chord.ChordNames.ChordAS.ordinal()]);
                }
                else if(b && ds || b && f)
                {
                    chordOutput.add(Chord.sMajor[Chord.ChordNames.ChordB.ordinal()]);
                }
                else if(b && d)
                {
                    chordOutput.add(Chord.sMinor[Chord.ChordNames.ChordB.ordinal()]);
                }

                //Single Notes - Sorry but they can all be major!
                else if(c)
                {
                    chordOutput.add(Chord.sMajor[Chord.ChordNames.ChordC.ordinal()]);
                }
                else if(cs)
                {
                    chordOutput.add(Chord.sMajor[Chord.ChordNames.ChordCS.ordinal()]);
                }
                else if(d)
                {
                    chordOutput.add(Chord.sMajor[Chord.ChordNames.ChordD.ordinal()]);
                }
                else if(ds)
                {
                    chordOutput.add(Chord.sMajor[Chord.ChordNames.ChordDS.ordinal()]);
                }
                else if(e)
                {
                    chordOutput.add(Chord.sMajor[Chord.ChordNames.ChordE.ordinal()]);
                }
                else if(f)
                {
                    chordOutput.add(Chord.sMajor[Chord.ChordNames.ChordF.ordinal()]);
                }
                else if(fs)
                {
                    chordOutput.add(Chord.sMajor[Chord.ChordNames.ChordFS.ordinal()]);
                }
                else if(g)
                {
                    chordOutput.add(Chord.sMajor[Chord.ChordNames.ChordG.ordinal()]);
                }
                else if(gs)
                {
                    chordOutput.add(Chord.sMajor[Chord.ChordNames.ChordGS.ordinal()]);
                }
                else if(a)
                {
                    chordOutput.add(Chord.sMajor[Chord.ChordNames.ChordAS.ordinal()]);
                }
                else if(as)
                {
                    chordOutput.add(Chord.sMajor[Chord.ChordNames.ChordAS.ordinal()]);
                }
                else if(b)
                {
                    chordOutput.add(Chord.sMajor[Chord.ChordNames.ChordB.ordinal()]);
                }
            }
            else if (nToProcess == 1)
            {
                Chord.KeyName keyA = notesPlayed.get(i);

                boolean c = anyAre(keyA, Chord.KeyName.C);
                boolean cs = anyAre(keyA, Chord.KeyName.CS);
                boolean d = anyAre(keyA, Chord.KeyName.D);
                boolean ds = anyAre(keyA, Chord.KeyName.DS);
                boolean e = anyAre(keyA, Chord.KeyName.E);
                boolean f = anyAre(keyA, Chord.KeyName.F);
                boolean fs = anyAre(keyA, Chord.KeyName.FS);
                boolean g = anyAre(keyA, Chord.KeyName.G);
                boolean gs = anyAre(keyA, Chord.KeyName.GS);
                boolean a = anyAre(keyA, Chord.KeyName.A);
                boolean as = anyAre(keyA, Chord.KeyName.AS);
                boolean b = anyAre(keyA, Chord.KeyName.B);

                if(c)
                {
                    chordOutput.add(Chord.sMajor[Chord.ChordNames.ChordC.ordinal()]);
                }
                else if(cs)
                {
                    chordOutput.add(Chord.sMajor[Chord.ChordNames.ChordCS.ordinal()]);
                }
                else if(d)
                {
                    chordOutput.add(Chord.sMajor[Chord.ChordNames.ChordD.ordinal()]);
                }
                else if(ds)
                {
                    chordOutput.add(Chord.sMajor[Chord.ChordNames.ChordDS.ordinal()]);
                }
                else if(e)
                {
                    chordOutput.add(Chord.sMajor[Chord.ChordNames.ChordE.ordinal()]);
                }
                else if(f)
                {
                    chordOutput.add(Chord.sMajor[Chord.ChordNames.ChordF.ordinal()]);
                }
                else if(fs)
                {
                    chordOutput.add(Chord.sMajor[Chord.ChordNames.ChordFS.ordinal()]);
                }
                else if(g)
                {
                    chordOutput.add(Chord.sMajor[Chord.ChordNames.ChordG.ordinal()]);
                }
                else if(gs)
                {
                    chordOutput.add(Chord.sMajor[Chord.ChordNames.ChordGS.ordinal()]);
                }
                else if(a)
                {
                    chordOutput.add(Chord.sMajor[Chord.ChordNames.ChordAS.ordinal()]);
                }
                else if(as)
                {
                    chordOutput.add(Chord.sMajor[Chord.ChordNames.ChordAS.ordinal()]);
                }
                else if(b)
                {
                    chordOutput.add(Chord.sMajor[Chord.ChordNames.ChordB.ordinal()]);
                }
            }
            else
            {
                boolean c = false, cs = false, d = false, ds = false, e = false, f = false, fs = false,
                        g = false, gs = false, a = false, as = false, b = false;
                Chord.KeyName keyA, keyB, keyC, keyD;

                keyA = notesPlayed.get(i);
                keyB = notesPlayed.get(i+1);
                keyC = notesPlayed.get(i+2);

                if(nToProcess == 3)
                {
                    c = anyAre(keyA, keyB, keyC, Chord.KeyName.C);
                    cs = anyAre(keyA, keyB, keyC, Chord.KeyName.CS);
                    d = anyAre(keyA, keyB, keyC, Chord.KeyName.D);
                    ds = anyAre(keyA, keyB, keyC, Chord.KeyName.DS);
                    e = anyAre(keyA, keyB, keyC, Chord.KeyName.E);
                    f = anyAre(keyA, keyB, keyC, Chord.KeyName.F);
                    fs = anyAre(keyA, keyB, keyC, Chord.KeyName.FS);
                    g = anyAre(keyA, keyB, keyC, Chord.KeyName.G);
                    gs = anyAre(keyA, keyB, keyC, Chord.KeyName.GS);
                    a = anyAre(keyA, keyB, keyC, Chord.KeyName.A);
                    as = anyAre(keyA, keyB, keyC, Chord.KeyName.AS);
                    b = anyAre(keyA, keyB, keyC, Chord.KeyName.B);
                }
                else if(nToProcess >= 4)
                {
                    keyD = notesPlayed.get(i+3);

                    c = anyAre(keyA, keyB, keyC, keyD, Chord.KeyName.C);
                    cs = anyAre(keyA, keyB, keyC, keyD, Chord.KeyName.CS);
                    d = anyAre(keyA, keyB, keyC, keyD, Chord.KeyName.D);
                    ds = anyAre(keyA, keyB, keyC, keyD, Chord.KeyName.DS);
                    e = anyAre(keyA, keyB, keyC, keyD, Chord.KeyName.E);
                    f = anyAre(keyA, keyB, keyC, keyD, Chord.KeyName.F);
                    fs = anyAre(keyA, keyB, keyC, keyD, Chord.KeyName.FS);
                    g = anyAre(keyA, keyB, keyC, keyD, Chord.KeyName.G);
                    gs = anyAre(keyA, keyB, keyC, keyD, Chord.KeyName.GS);
                    a = anyAre(keyA, keyB, keyC, keyD, Chord.KeyName.A);
                    as = anyAre(keyA, keyB, keyC, keyD, Chord.KeyName.AS);
                    b = anyAre(keyA, keyB, keyC, keyD, Chord.KeyName.B);
                }
                else
                {
                    assert false;
                }


                //Niamh's Comments!!

                //This is for if 3 of the notes of one chord are in the bar.
                //Cmajor
                if(c && e && g)
                {
                    chordOutput.add(Chord.sMajor[Chord.ChordNames.ChordC.ordinal()]);
                }
                //Cminor
                else if(c && ds && g)
                {
                    chordOutput.add(Chord.sMinor[Chord.ChordNames.ChordC.ordinal()]);
                }
                //CSmajor
                else if(cs && f && gs)
                {
                    chordOutput.add(Chord.sMajor[Chord.ChordNames.ChordCS.ordinal()]);
                }
                //CSminor
                else if(cs && e && gs)
                {
                    chordOutput.add(Chord.sMinor[Chord.ChordNames.ChordCS.ordinal()]);
                }
                //Dmajor
                else if(d && fs && a)
                {
                    chordOutput.add(Chord.sMajor[Chord.ChordNames.ChordD.ordinal()]);
                }
                //Dminor
                else if(d && f && a)
                {
                    chordOutput.add(Chord.sMinor[Chord.ChordNames.ChordD.ordinal()]);
                }
                //DSmajor
                else if(ds && g && as)
                {
                    chordOutput.add(Chord.sMajor[Chord.ChordNames.ChordDS.ordinal()]);
                }
                //DSminor
                else if(ds && fs && as)
                {
                    chordOutput.add(Chord.sMinor[Chord.ChordNames.ChordDS.ordinal()]);
                }
                //Emajor
                else if(e && gs && b)
                {
                    chordOutput.add(Chord.sMajor[Chord.ChordNames.ChordE.ordinal()]);
                }
                //Eminor
                else if(e && g && b)
                {
                    chordOutput.add(Chord.sMinor[Chord.ChordNames.ChordE.ordinal()]);
                }
                //Fmajor
                else if(f && a && c)
                {
                    chordOutput.add(Chord.sMajor[Chord.ChordNames.ChordF.ordinal()]);
                }
                //Fminor
                else if(f && gs && c)
                {
                    chordOutput.add(Chord.sMinor[Chord.ChordNames.ChordF.ordinal()]);
                }
                //FSmajor
                else if(fs && as && cs)
                {
                    chordOutput.add(Chord.sMajor[Chord.ChordNames.ChordFS.ordinal()]);
                }
                //FSminor
                else if(fs && a && cs)
                {
                    chordOutput.add(Chord.sMinor[Chord.ChordNames.ChordFS.ordinal()]);
                }
                //Gmajor
                else if(g && b && d)
                {
                    chordOutput.add(Chord.sMajor[Chord.ChordNames.ChordG.ordinal()]);
                }
                //Gminor
                else if(g && as && d)
                {
                    chordOutput.add(Chord.sMinor[Chord.ChordNames.ChordG.ordinal()]);
                }
                //GSmajor
                else if(gs && c && ds)
                {
                    chordOutput.add(Chord.sMajor[Chord.ChordNames.ChordGS.ordinal()]);
                }
                //GSminor
                else if(gs && b && ds)
                {
                    chordOutput.add(Chord.sMinor[Chord.ChordNames.ChordGS.ordinal()]);
                }
                //Amajor
                else if(a && cs && e)
                {
                    chordOutput.add(Chord.sMajor[Chord.ChordNames.ChordA.ordinal()]);
                }
                //Aminor
                else if(a && c && e)
                {
                    chordOutput.add(Chord.sMinor[Chord.ChordNames.ChordA.ordinal()]);
                }
                //ASmajor
                else if(as && d && f)
                {
                    chordOutput.add(Chord.sMajor[Chord.ChordNames.ChordAS.ordinal()]);
                }
                //ASminor
                else if(as && cs && f)
                {
                    chordOutput.add(Chord.sMinor[Chord.ChordNames.ChordAS.ordinal()]);
                }
                //Bmajor
                else if(b && ds && f)
                {
                    chordOutput.add(Chord.sMajor[Chord.ChordNames.ChordB.ordinal()]);
                }
                //Bminor
                else if(b && d && f)
                {
                    chordOutput.add(Chord.sMinor[Chord.ChordNames.ChordB.ordinal()]);
                }
                //This is for if there are only 2 notes from a chord in the same bar

                else if(c && e || c && g)
                {
                    chordOutput.add(Chord.sMajor[Chord.ChordNames.ChordC.ordinal()]);
                }
                else if(c && ds)
                {
                    chordOutput.add(Chord.sMinor[Chord.ChordNames.ChordC.ordinal()]);
                }
                else if(cs && f || cs && gs)
                {
                    chordOutput.add(Chord.sMajor[Chord.ChordNames.ChordCS.ordinal()]);
                }
                else if(cs && e)
                {
                    chordOutput.add(Chord.sMinor[Chord.ChordNames.ChordCS.ordinal()]);
                }
                else if(d && fs || d && a)
                {
                    chordOutput.add(Chord.sMajor[Chord.ChordNames.ChordD.ordinal()]);
                }
                else if(d && f)
                {
                    chordOutput.add(Chord.sMinor[Chord.ChordNames.ChordD.ordinal()]);
                }
                else if(ds && g || ds && as)
                {
                    chordOutput.add(Chord.sMajor[Chord.ChordNames.ChordDS.ordinal()]);
                }
                else if(ds && fs)
                {
                    chordOutput.add(Chord.sMinor[Chord.ChordNames.ChordDS.ordinal()]);
                }
                else if(e && gs || e && b)
                {
                    chordOutput.add(Chord.sMajor[Chord.ChordNames.ChordE.ordinal()]);
                }
                else if(e && g)
                {
                    chordOutput.add(Chord.sMinor[Chord.ChordNames.ChordE.ordinal()]);
                }
                else if(f && a || f && c)
                {
                    chordOutput.add(Chord.sMajor[Chord.ChordNames.ChordF.ordinal()]);
                }
                else if(f && gs)
                {
                    chordOutput.add(Chord.sMinor[Chord.ChordNames.ChordF.ordinal()]);
                }
                else if(fs && as || fs && cs)
                {
                    chordOutput.add(Chord.sMajor[Chord.ChordNames.ChordFS.ordinal()]);
                }
                else if(fs && a)
                {
                    chordOutput.add(Chord.sMinor[Chord.ChordNames.ChordFS.ordinal()]);
                }
                else if(g && b || g && d)
                {
                    chordOutput.add(Chord.sMajor[Chord.ChordNames.ChordG.ordinal()]);
                }
                else if(g && as)
                {
                    chordOutput.add(Chord.sMinor[Chord.ChordNames.ChordG.ordinal()]);
                }
                else if(gs && c || gs && ds)
                {
                    chordOutput.add(Chord.sMajor[Chord.ChordNames.ChordGS.ordinal()]);
                }
                else if(gs && b)
                {
                    chordOutput.add(Chord.sMinor[Chord.ChordNames.ChordGS.ordinal()]);
                }
                else if(a && cs || a && e)
                {
                    chordOutput.add(Chord.sMajor[Chord.ChordNames.ChordA.ordinal()]);
                }
                else if(a && c)
                {
                    chordOutput.add(Chord.sMinor[Chord.ChordNames.ChordA.ordinal()]);
                }
                else if(as && d || as && f)
                {
                    chordOutput.add(Chord.sMajor[Chord.ChordNames.ChordAS.ordinal()]);
                }
                else if(as && cs)
                {
                    chordOutput.add(Chord.sMinor[Chord.ChordNames.ChordAS.ordinal()]);
                }
                else if(b && ds || b && f)
                {
                    chordOutput.add(Chord.sMajor[Chord.ChordNames.ChordB.ordinal()]);
                }
                else if(b && d)
                {
                    chordOutput.add(Chord.sMinor[Chord.ChordNames.ChordB.ordinal()]);
                }

                //Single Notes - Sorry but they can all be major!
                else if(c)
                {
                    chordOutput.add(Chord.sMajor[Chord.ChordNames.ChordC.ordinal()]);
                }
                else if(cs)
                {
                    chordOutput.add(Chord.sMajor[Chord.ChordNames.ChordCS.ordinal()]);
                }
                else if(d)
                {
                    chordOutput.add(Chord.sMajor[Chord.ChordNames.ChordD.ordinal()]);
                }
                else if(ds)
                {
                    chordOutput.add(Chord.sMajor[Chord.ChordNames.ChordDS.ordinal()]);
                }
                else if(e)
                {
                    chordOutput.add(Chord.sMajor[Chord.ChordNames.ChordE.ordinal()]);
                }
                else if(f)
                {
                    chordOutput.add(Chord.sMajor[Chord.ChordNames.ChordF.ordinal()]);
                }
                else if(fs)
                {
                    chordOutput.add(Chord.sMajor[Chord.ChordNames.ChordFS.ordinal()]);
                }
                else if(g)
                {
                    chordOutput.add(Chord.sMajor[Chord.ChordNames.ChordG.ordinal()]);
                }
                else if(gs)
                {
                    chordOutput.add(Chord.sMajor[Chord.ChordNames.ChordGS.ordinal()]);
                }
                else if(a)
                {
                    chordOutput.add(Chord.sMajor[Chord.ChordNames.ChordAS.ordinal()]);
                }
                else if(as)
                {
                    chordOutput.add(Chord.sMajor[Chord.ChordNames.ChordAS.ordinal()]);
                }
                else if(b)
                {
                    chordOutput.add(Chord.sMajor[Chord.ChordNames.ChordB.ordinal()]);
                }
            }
        }
        //return chord for each bar
        return chordOutput;
    }
}