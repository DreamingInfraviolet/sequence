package com.audinarium.sequence.sequence;

import com.audinarium.sequence.sequence.Graphics.Chord;

import java.util.ArrayList;

/**
 * Created by Volodymyr on 12/21/2016.
 */

public class Util
{
    public static float lerp(float a, float b, float t)
    {
        return t * b + (1 - t) * a;
    }

    public static int[] listToArrayInt(ArrayList<Integer> list)
    {
        int[] ret = new int[list.size()];
        for (int i=0; i < ret.length; i++)
            ret[i] = list.get(i).intValue();
        return ret;
    }

    public static Chord[] listToArrayChord(ArrayList<Chord> list)
    {
        Chord[] ret = new Chord[list.size()];
        for (int i=0; i < ret.length; i++)
            ret[i] = list.get(i);
        return ret;
    }
}
