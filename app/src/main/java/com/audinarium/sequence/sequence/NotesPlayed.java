package com.audinarium.sequence.sequence;

import com.audinarium.sequence.sequence.Graphics.Chord;
import com.audinarium.sequence.sequence.Graphics.Keyboard;

import java.util.ArrayList;

import static android.os.Build.VERSION_CODES.N;
import static com.audinarium.sequence.sequence.Graphics.Chord.KeyNames.C;

/**
 * Created by Niamh on 01/12/2016.
 */

public class NotesPlayed {



    public ArrayList<Chord> recorded(ArrayList<Chord.KeyNames> notesPlayed)
    {
        ArrayList<Chord> chordOutput = new ArrayList<Chord>();

        for (int i = 0; i < notesPlayed.size(); i++)
        {
            int nToProcess = notesPlayed.size() - i;

            if(nToProcess > 3)
            {
                Chord[] chords0 = Chord.sGetChordsWithKey(notesPlayed.get(i));
                Chord[] chords1 = Chord.sGetChordsWithKey(notesPlayed.get(i+1));

                Chord[] intersection
            }
        }
        return chordOutput;
    }
}
