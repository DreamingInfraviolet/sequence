package com.audinarium.sequence.sequence;

import com.audinarium.sequence.sequence.Graphics.Chord;
import com.audinarium.sequence.sequence.Graphics.Keyboard;

import java.util.ArrayList;

import static android.os.Build.VERSION_CODES.N;
import static com.audinarium.sequence.sequence.Graphics.Chord.KeyNames.C;
import static com.audinarium.sequence.sequence.Graphics.Chord.KeyNames.D;

/**
 * Created by Niamh on 01/12/2016.
 */

public class NotesPlayed {
    static boolean anyAre(Chord.KeyNames a, Chord.KeyNames b, Chord.KeyNames c, Chord.KeyNames d, Chord.KeyNames q)
    {
        return a == q || b == q || c == q || d == q;
    }

    public ArrayList<Chord> recorded(ArrayList<Chord.KeyNames> notesPlayed)
    {
        ArrayList<Chord> chordOutput = new ArrayList<Chord>();

        for (int i = 0; i < notesPlayed.size(); i += 4)
        {
            int nToProcess = notesPlayed.size() - i;

            if(nToProcess >= 4)
            {
                Chord.KeyNames a = notesPlayed.get(i);
                Chord.KeyNames b = notesPlayed.get(i+1);
                Chord.KeyNames c = notesPlayed.get(i+2);
                Chord.KeyNames d = notesPlayed.get(i+3);

                //Niamh's Comments!!
                //This is so far for if 3 of the notes are in the bar.
                //Have to do it now for 2 and then determine the chord if there is a bar with 2 notes in one chord and 2 in another

                //C/Cm
                if(anyAre(a, b, c, d, Chord.KeyNames.C))
                {
                    if(anyAre(a, b, c, d, Chord.KeyNames.G))
                    {
                        if(anyAre(a, b, c, d, Chord.KeyNames.E))
                        {
                            chordOutput.add(Chord.sMajor[Chord.ChordNames.ChordC.ordinal()]);
                        }
                        else if(anyAre(a, b, c, d, Chord.KeyNames.DS))
                        {
                            chordOutput.add(Chord.sMinor[Chord.ChordNames.ChordC.ordinal()]);
                        }
                    }
                }
                //CS/DF/CSm/DFm
                else if(anyAre(a, b, c, d, Chord.KeyNames.CS))
                {
                    if(anyAre(a, b, c, d, Chord.KeyNames.GS))
                    {
                        if(anyAre(a, b, c, d, Chord.KeyNames.F))
                        {
                            chordOutput.add(Chord.sMajor[Chord.ChordNames.ChordCS.ordinal()]);
                        }
                        else if(anyAre(a, b, c, d, Chord.KeyNames.E))
                        {
                            chordOutput.add(Chord.sMinor[Chord.ChordNames.ChordCS.ordinal()]);
                        }
                    }
                }
                //D/Dm
                else if(anyAre(a, b, c, d, Chord.KeyNames.D))
                {
                    if(anyAre(a,b,c,d, Chord.KeyNames.A))
                    {
                        if(anyAre(a,b,c,d, Chord.KeyNames.FS))
                        {
                            chordOutput.add(Chord.sMajor[Chord.ChordNames.ChordD.ordinal()]);
                        }
                        else if(anyAre(a,b,c,d, Chord.KeyNames.F))
                        {
                            chordOutput.add(Chord.sMinor[Chord.ChordNames.ChordD.ordinal()]);
                        }
                    }
                }
                //DS/EF/DSm/EFm
                else if(anyAre(a,b,c,d, Chord.KeyNames.DS))
                {
                    if(anyAre(a, b, c, d, Chord.KeyNames.AS))
                    {
                        if (anyAre(a, b, c, d, Chord.KeyNames.G))
                        {
                            chordOutput.add(Chord.sMajor[Chord.ChordNames.ChordDS.ordinal()]);
                        }
                        else if (anyAre(a, b, c, d, Chord.KeyNames.FS))
                        {
                            chordOutput.add(Chord.sMinor[Chord.ChordNames.ChordDS.ordinal()]);
                        }
                    }
                }
                //E/Em
                else if(anyAre(a,b,c,d, Chord.KeyNames.E))
                {
                    if(anyAre(a,b,c,d, Chord.KeyNames.B))
                    {
                        if(anyAre(a,b,c,d, Chord.KeyNames.GS))
                        {
                            chordOutput.add(Chord.sMajor[Chord.ChordNames.ChordE.ordinal()]);
                        }
                        else if(anyAre(a,b,c,d, Chord.KeyNames.G))
                        {
                            chordOutput.add(Chord.sMinor[Chord.ChordNames.ChordE.ordinal()]);
                        }
                    }
                }
                //F/Fm
                else if(anyAre(a,b,c,d, Chord.KeyNames.F))
                {
                    if(anyAre(a,b,c,d, Chord.KeyNames.C))
                    {
                        if(anyAre(a,b,c,d, Chord.KeyNames.A))
                        {
                            chordOutput.add(Chord.sMajor[Chord.ChordNames.ChordF.ordinal()]);
                        }
                        else if(anyAre(a,b,c,d, Chord.KeyNames.GS))
                        {
                            chordOutput.add(Chord.sMinor[Chord.ChordNames.ChordF.ordinal()]);
                        }
                    }
                }
                //FS/GF/FSm/GFm
                else if(anyAre(a,b,c,d, Chord.KeyNames.FS))
                {
                    if(anyAre(a,b,c,d, Chord.KeyNames.CS))
                    {
                        if(anyAre(a,b,c,d, Chord.KeyNames.AS))
                        {
                            chordOutput.add(Chord.sMajor[Chord.ChordNames.ChordFS.ordinal()]);
                        }
                        else if(anyAre(a,b,c,d, Chord.KeyNames.A))
                        {
                            chordOutput.add(Chord.sMinor[Chord.ChordNames.ChordFS.ordinal()]);
                        }
                    }
                }
                //G/Gm
                else if(anyAre(a,b,c,d, Chord.KeyNames.G))
                {
                    if(anyAre(a,b,c,d, Chord.KeyNames.D))
                    {
                        if(anyAre(a,b,c,d, Chord.KeyNames.B))
                        {
                            chordOutput.add(Chord.sMajor[Chord.ChordNames.ChordG.ordinal()]);
                        }
                        else if(anyAre(a,b,c,d, Chord.KeyNames.AS))
                        {
                            chordOutput.add(Chord.sMinor[Chord.ChordNames.ChordG.ordinal()]);
                        }
                    }
                }
                //GS/AF/GSm/AFm
                else if(anyAre(a,b,c,d, Chord.KeyNames.GS))
                {
                    if(anyAre(a,b,c,d, Chord.KeyNames.DS))
                    {
                        if(anyAre(a,b,c,d, Chord.KeyNames.C))
                        {
                            chordOutput.add(Chord.sMajor[Chord.ChordNames.ChordGS.ordinal()]);
                        }
                        else if(anyAre(a,b,c,d, Chord.KeyNames.B))
                        {
                            chordOutput.add(Chord.sMinor[Chord.ChordNames.ChordGS.ordinal()]);
                        }
                    }
                }
                //A/Am
                else if(anyAre(a,b,c,d, Chord.KeyNames.A))
                {
                    if(anyAre(a,b,c,d, Chord.KeyNames.E))
                    {
                        if(anyAre(a,b,c,d, Chord.KeyNames.CS))
                        {
                            chordOutput.add(Chord.sMajor[Chord.ChordNames.ChordA.ordinal()]);
                        }
                        else if(anyAre(a,b,c,d, Chord.KeyNames.C))
                        {
                            chordOutput.add(Chord.sMinor[Chord.ChordNames.ChordA.ordinal()]);
                        }
                    }
                }
                //AS/BF/ASm/BFm
                else if(anyAre(a,b,c,d, Chord.KeyNames.AS))
                {
                    if(anyAre(a,b,c,d, Chord.KeyNames.F))
                    {
                        if(anyAre(a,b,c,d, Chord.KeyNames.D))
                        {
                            chordOutput.add(Chord.sMajor[Chord.ChordNames.ChordAS.ordinal()]);
                        }
                        else if(anyAre(a,b,c,d, Chord.KeyNames.CS))
                        {
                            chordOutput.add(Chord.sMinor[Chord.ChordNames.ChordAS.ordinal()]);
                        }
                    }
                }
                //B/Bm
                else if(anyAre(a,b,c,d, Chord.KeyNames.B))
                {
                    if(anyAre(a,b,c,d, Chord.KeyNames.FS))
                    {
                        if(anyAre(a,b,c,d, Chord.KeyNames.DS))
                        {
                            chordOutput.add(Chord.sMajor[Chord.ChordNames.ChordB.ordinal()]);
                        }
                        else if(anyAre(a,b,c,d, Chord.KeyNames.D))
                        {
                            chordOutput.add(Chord.sMinor[Chord.ChordNames.ChordB.ordinal()]);
                        }
                    }
                }
            }

        }
        //return chord for each bar
        return chordOutput;
    }
}