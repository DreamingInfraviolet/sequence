package com.audinarium.sequence.sequence;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.res.ResourcesCompat;

import processing.core.PApplet;
import processing.core.PFont;

/**
 * Created by Volodymyr on 12/21/2016.
 * Represents stave settings. This can be reused for multiple staves.
 */

public class StaveSettings
{
    public int[] primaryColour;
    public int[] noteColour;
    public int[] staveColour;
    public PFont musicFont;
    public int barHeight;
    public int textHeight;
    public String timeSignatureTop;
    public String timeSignatureBottom;
    public String clefSymbol;
    public int yOffset;
    public int framerate;
    public float clefYOffset;

    public static StaveSettings generateDefaultSettings(Context context, PApplet papplet)
    {
        StaveSettings s = new StaveSettings();
        s.noteColour = new int[]{0, 0, 0};
        s.staveColour = new int[]{60, 60, 60};
        s.primaryColour = Util.getDefaultColour(context);
        s.musicFont = papplet.createFont("Bravura.otf", 100);
        s.barHeight = papplet.height / 4;
        s.textHeight = papplet.height / 8;
        s.timeSignatureTop = MusicFont.timeSignature4;
        s.timeSignatureBottom = MusicFont.timeSignature4;
        s.clefSymbol = MusicFont.treble;
        s.yOffset = papplet.height / 8;
        s.framerate = 60;
        s.clefYOffset = 0;

        return s;
    }

    public static StaveSettings generateChordDisplaySettings(Context context, PApplet papplet)
    {
        StaveSettings defaultSettings = generateDefaultSettings(context, papplet);
        defaultSettings.clefSymbol = MusicFont.bass;
        defaultSettings.clefYOffset = defaultSettings.barHeight / 2.0f;
        return defaultSettings;
    }

    public float getNoteSpacing(PApplet papplet)
    {
        return papplet.textWidth(MusicFont.staff5Lines) / 5.0f;
    }
}
