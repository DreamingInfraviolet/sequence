package com.audinarium.sequence.sequence;

import android.content.Context;
import android.media.SoundPool;
import android.util.Log;

import com.audinarium.sequence.sequence.Graphics.Chord;

import java.util.ArrayList;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by Volodymyr on 11/24/2016.
 */

public class AudioPlayback
{
    private static SoundPool sSoundPool;
    static private int[] sSoundHighIds = new int[25];
    static private int[] sSoundLowIds = new int[13];

    public static void init(Context context)
    {
        SoundPool.Builder builder = new SoundPool.Builder();
        builder.setMaxStreams(6);
        sSoundPool = builder.build();

        sSoundHighIds[0] = sSoundPool.load(context, R.raw.note_c, 1);
        sSoundHighIds[1] = sSoundPool.load(context, R.raw.note_csharp, 1);
        sSoundHighIds[2] = sSoundPool.load(context, R.raw.note_d, 1);
        sSoundHighIds[3] = sSoundPool.load(context, R.raw.note_dsharp, 1);
        sSoundHighIds[4] = sSoundPool.load(context, R.raw.note_e, 1);
        sSoundHighIds[5] = sSoundPool.load(context, R.raw.note_f, 1);
        sSoundHighIds[6] = sSoundPool.load(context, R.raw.note_fsharp, 1);
        sSoundHighIds[7] = sSoundPool.load(context, R.raw.note_g, 1);
        sSoundHighIds[8] = sSoundPool.load(context, R.raw.note_gsharp, 1);
        sSoundHighIds[9] = sSoundPool.load(context, R.raw.note_a, 1);
        sSoundHighIds[10] = sSoundPool.load(context, R.raw.note_asharp, 1);
        sSoundHighIds[11] = sSoundPool.load(context, R.raw.note_b, 1);
        sSoundHighIds[12] = sSoundPool.load(context, R.raw.note_highc, 1);
        sSoundHighIds[13] = sSoundPool.load(context, R.raw.note_highcsharp, 1);
        sSoundHighIds[14] = sSoundPool.load(context, R.raw.note_highd, 1);
        sSoundHighIds[15] = sSoundPool.load(context, R.raw.note_highdsharp, 1);
        sSoundHighIds[16] = sSoundPool.load(context, R.raw.note_highe, 1);
        sSoundHighIds[17] = sSoundPool.load(context, R.raw.note_highf, 1);
        sSoundHighIds[18] = sSoundPool.load(context, R.raw.note_highfsharp, 1);
        sSoundHighIds[19] = sSoundPool.load(context, R.raw.note_highg, 1);
        sSoundHighIds[20] = sSoundPool.load(context, R.raw.note_highgsharp, 1);
        sSoundHighIds[21] = sSoundPool.load(context, R.raw.note_higha, 1);
        sSoundHighIds[22] = sSoundPool.load(context, R.raw.note_highasharp, 1);
        sSoundHighIds[23] = sSoundPool.load(context, R.raw.note_highb, 1);
        sSoundHighIds[24] = sSoundPool.load(context, R.raw.note_highc2, 1);

        sSoundLowIds[0] = sSoundPool.load(context, R.raw.low_c, 1);
        sSoundLowIds[1] = sSoundPool.load(context, R.raw.low_csharp, 1);
        sSoundLowIds[2] = sSoundPool.load(context, R.raw.low_d, 1);
        sSoundLowIds[3] = sSoundPool.load(context, R.raw.low_dsharp, 1);
        sSoundLowIds[4] = sSoundPool.load(context, R.raw.low_e, 1);
        sSoundLowIds[5] = sSoundPool.load(context, R.raw.low_f, 1);
        sSoundLowIds[6] = sSoundPool.load(context, R.raw.low_fsharp, 1);
        sSoundLowIds[7] = sSoundPool.load(context, R.raw.low_g, 1);
        sSoundLowIds[8] = sSoundPool.load(context, R.raw.low_gsharp, 1);
        sSoundLowIds[9] = sSoundPool.load(context, R.raw.low_a, 1);
        sSoundLowIds[10] = sSoundPool.load(context, R.raw.low_asharp, 1);
        sSoundLowIds[11]= sSoundPool.load(context, R.raw.low_b, 1);
        sSoundLowIds[12] = sSoundPool.load(context, R.raw.note_c, 1);

    }

    public enum Clef { Bass, Treble };

    public static void play(Chord q)
    {
        play(q, Clef.Bass);
    }

    public static void play(Chord q, Clef clef)
    {
        play(q.doh.ordinal(), clef);
        play(q.mi.ordinal(), clef);
        play(q.soh.ordinal(), clef);
    }

    public static void play(int note)
    {
        play(note, Clef.Treble);
    }

    public static void play(int note, Clef clef)
    {
        if(note >= sSoundHighIds.length)
            Log.e("audio", "Can not play note " + note + " : beyond range");

        // id, leftVolume, rightVolume, priority, loop, rate
        if(clef == Clef.Treble)
        {
            sSoundPool.stop(sSoundHighIds[note]);
            sSoundPool.play(sSoundHighIds[note], 1, 1, 0, 0, 1);
        }
        else if(clef == Clef.Bass)
        {
            sSoundPool.stop(sSoundLowIds[note]);
            sSoundPool.play(sSoundLowIds[note], 1, 1, 0, 0, 1);
        }
        else
        {
            assert false;
        }
    }
}
