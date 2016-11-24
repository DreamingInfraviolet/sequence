package com.audinarium.sequence.sequence;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.util.Log;

/**
 * Created by Volodymyr on 11/24/2016.
 */

public class AudioPlayback
{
    private static SoundPool sSoundPool;
    static private int[] sSoundIds = new int[25];

    public static void init(Context context)
    {
        SoundPool.Builder builder = new SoundPool.Builder();
        builder.setMaxStreams(6);
        sSoundPool = builder.build();

        sSoundIds[0] = sSoundPool.load(context, R.raw.note_c, 1);
        sSoundIds[1] = sSoundPool.load(context, R.raw.note_csharp, 1);
        sSoundIds[2] = sSoundPool.load(context, R.raw.note_d, 1);
        sSoundIds[3] = sSoundPool.load(context, R.raw.note_dsharp, 1);
        sSoundIds[4] = sSoundPool.load(context, R.raw.note_e, 1);
        sSoundIds[5] = sSoundPool.load(context, R.raw.note_f, 1);
        sSoundIds[6] = sSoundPool.load(context, R.raw.note_fsharp, 1);
        sSoundIds[7] = sSoundPool.load(context, R.raw.note_g, 1);
        sSoundIds[8] = sSoundPool.load(context, R.raw.note_gsharp, 1);
        sSoundIds[9] = sSoundPool.load(context, R.raw.note_a, 1);
        sSoundIds[10] = sSoundPool.load(context, R.raw.note_asharp, 1);
        sSoundIds[11] = sSoundPool.load(context, R.raw.note_b, 1);
        sSoundIds[12] = sSoundPool.load(context, R.raw.note_highc, 1);
        sSoundIds[13] = sSoundPool.load(context, R.raw.note_highcsharp, 1);
        sSoundIds[14] = sSoundPool.load(context, R.raw.note_highd, 1);
        sSoundIds[15] = sSoundPool.load(context, R.raw.note_highdsharp, 1);
        sSoundIds[16] = sSoundPool.load(context, R.raw.note_highe, 1);
        sSoundIds[17] = sSoundPool.load(context, R.raw.note_highf, 1);
        sSoundIds[18] = sSoundPool.load(context, R.raw.note_highfsharp, 1);
        sSoundIds[19] = sSoundPool.load(context, R.raw.note_highg, 1);
        sSoundIds[20] = sSoundPool.load(context, R.raw.note_highgsharp, 1);
        sSoundIds[21] = sSoundPool.load(context, R.raw.note_higha, 1);
        sSoundIds[22] = sSoundPool.load(context, R.raw.note_highasharp, 1);
        sSoundIds[23] = sSoundPool.load(context, R.raw.note_highb, 1);
        sSoundIds[24] = sSoundPool.load(context, R.raw.note_highc2, 1);

    }

    public static void play(int note)
    {
        if(note >= sSoundIds.length)
            Log.e("audio", "Can not play note " + note + " : beyond range");

        // id, leftVolume, rightVolume, priority, loop, rate
        sSoundPool.stop(sSoundIds[note]);
        sSoundPool.play(sSoundIds[note], 1, 1, 0, 0, 1);
    }
}
