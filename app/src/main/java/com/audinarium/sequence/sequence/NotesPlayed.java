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

    boolean anyAre(Chord.KeyNames a, Chord.KeyNames b, Chord.KeyNames c, Chord.KeyNames d, Chord.KeyNames q)
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

                if(anyAre(a, b, c, d, Chord.KeyNames.C))
                {
                    if(anyAre(a, b, c, d, Chord.KeyNames.E))
                    {
                        if(anyAre(a, b, c, d, Chord.KeyNames.G))
                        {
                            chordOutput.add(Chord.sMajor[Chord.ChordNames.ChordC.ordinal()]);
                        }
                        else if(anyAre(a, b, c, d, Chord.KeyNames.A))
                        {
                            chordOutput.add(Chord.sMinor[Chord.ChordNames.ChordA.ordinal()]);
                        }
                    }
                    else if(anyAre(a, b, c, d, Chord.KeyNames.F))
                    {
                        if(anyAre(a, b, c, d, Chord.KeyNames.A))
                        {
                            chordOutput.add(Chord.sMajor[Chord.ChordNames.ChordF.ordinal()]);
                        }
                    }
                }
            }
            //Putting all melodies in 4/4 ... should we have an option to also have in 3/4 maybe?
            //Do same thing for every 3?
            for(int j = 0; j <= 4 ; j++)
            {
            //Search through the first four notes and find the chord with the most matching notes
            //Chord with most matching will be the chord output for the bar
                return chordOutput;
            }
            //Do this for each four notes.
            //....will this depend on duration of note? They may want rests in their music
            //Are we basing this on only crochets or other values aswell? Minums, Quavers, Semibreves?

            //Do we need for 3/4? See below
            /**for(int j = 0; j <= 3; j++)
            {
                //Search through the first four notes and find the chord with the most matching notes
                //Chord with most matching will be the chord output for the bar
                return chordOutput;
            }**/
        }
        //return chord for each bar
        return chordOutput;
    }

}
