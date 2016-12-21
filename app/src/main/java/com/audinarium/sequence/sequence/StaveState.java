package com.audinarium.sequence.sequence;

import processing.core.PApplet;

/**
 * Created by Volodymyr on 12/21/2016.
 */

public class StaveState
{
    /** The state that the stave is currently in. */
    public enum State {Start, Paused, Playing, End};

    /** Settings defining how to show the stave */
    public StaveSettings settings;

    /** The index of the current note being played */
    public int notePlayingIndex = 0;

    /** The x coordinate of the point we should try to look at */
    public float desiredLookCentre = 0;

    /** The x coordinate of the poing we are currently looking at */
    public float currentLookCentre = desiredLookCentre;

    /** The time (in milliseconds) when the last note was played. -1 if never */
    public long timeOfLastNote = -1;

    /** The notes we are to show */
    public Note[] notes = new Note[0];

    /** Chords for each bar */
    public Chord[] chords = new Chord[0];

    /** If true, we should follow the currently played note. Otherwise allow full freedom to user */
    public boolean shouldFollowPlayingIndex = false;

    /** The current state (what we are doing) */
    public State state = State.Start;

    public void startPlayback()
    {
        shouldFollowPlayingIndex = true;
        if(state == StaveState.State.End)
            notePlayingIndex = 0;
        state = StaveState.State.Playing;
    }

    public void pausePlayback()
    {
        shouldFollowPlayingIndex = false;
        state = State.Paused;
    }


    public float getKeyOffset(PApplet papplet, int playIndex)
    {
        return settings.getXNoteOffset(papplet)
                - papplet.textWidth(MusicFont.staff5Lines) / 8.0f
                + playIndex * settings.getXNoteOffset(papplet);
    }


    public float getXOffset(PApplet papplet)
    {
        float leftPadding = papplet.textWidth(settings.clefSymbol)
                + Math.max(papplet.textWidth(settings.timeSignatureBottom),
                           papplet.textWidth(settings.timeSignatureTop));
        return papplet.width / 2 - currentLookCentre + leftPadding;
    }


    void getChordCentre(PApplet papplet, float[] output, int chordIndex, float rectH, float xOffset)
    {
        float xPosition = xOffset + chordIndex * papplet.textWidth(MusicFont.staff5Lines)
                + papplet.textWidth(MusicFont.staff5Lines) / 2.0f;
        float yPosition = settings.yOffset + settings.barHeight + settings.barHeight / 2 + rectH;
        output[0] = xPosition;
        output[1] = yPosition;
    }

    public float[][] getChordCentres(PApplet papplet)
    {
        float[][] output = new float[chords.length][4];

        float rectW = papplet.textWidth(MusicFont.staff5Lines) * 0.9f;
        float rectH = settings.textHeight * 1.4f;

        for(int i = 0; i < chords.length; ++i)
        {
            float[] centre = new float[2];
            getChordCentre(papplet, centre, i, rectH, getXOffset(papplet));

            output[i][0] = centre[0];
            output[i][1] = centre[1];
            output[i][2] = rectW;
            output[i][3] = rectH;
        }

        return output;
    }

    public long timeSinceLastNote()
    {
        return System.currentTimeMillis() - timeOfLastNote;
    }

    public boolean shouldPlayNote()
    {
        // If notes remain, state is playing, and last played 1/4th of a second ago
        return notePlayingIndex < notes.length
                && state == State.Playing
                && timeSinceLastNote() >= 250;
    }

    public int countBarsNeeded()
    {
        return notes.length / 4 + (notes.length % 4 == 0 ? 0 : 1);
    }
}
