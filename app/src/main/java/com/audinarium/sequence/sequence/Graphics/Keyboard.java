package com.audinarium.sequence.sequence.Graphics;

/**
 * Created by Volodymyr on 10/20/2016.
 *
 * Represents a piano keyboard in local world space.
 * It does not provide a view into the keyboard, and limits itself
 * to generating the keys.
 */

public class Keyboard
{
    /** Represents which keys are black and white on an octave */
    private final static boolean[] sOctaveColourMapping =
            new boolean[]{true, false, true, false, true, true, false, true, false, true, false, true};

    /** The number of keys on the keyboard */
    public final static int   sNKeys = 84;
    /** The local width of the keyboard before projection into the view rectangle */
    public final static float sLocalKeyboardWidth = 1.0f;
    /** The height of white keys (and hence the keyboard) */
    public final static float sWhiteKeyHeight = 1.0f;
    /** The height of black keys */
    public final static float sBlackKeyHeight = sWhiteKeyHeight * 0.7f;
    /** The width of a white key */
    public final static float sWhiteKeyWidth  = sLocalKeyboardWidth / (sNKeys / 12 * 7);
    /** The width of a black key */
    public final static float sBlackKeyWidth  = sWhiteKeyWidth * 0.5f;
    /** Some black keys are slightly offset left or right for seemingly ergonomic/aesthetic purposes.
     *  This is the amount by which they should be offset. */
    public final static float sBlackKeyOffset = sWhiteKeyWidth * 0.05f;
    /** The actual visible sizes of white keys. */
    public static final float sWhiteKeyVisibleWidth = sWhiteKeyWidth * 0.95f;


    /** Represents the key positions on one octave. This is used to determine where keys should be placed. */
    public final static float[] sOctaveKeyPositions =
            new float[]{sWhiteKeyWidth/2,                                        // C
                        sWhiteKeyWidth - sBlackKeyOffset,                        // C#
                        sWhiteKeyWidth/2 + sWhiteKeyWidth               ,        // D
                        sWhiteKeyWidth*2 + sBlackKeyOffset,                      // D#
                        sWhiteKeyWidth/2 + sWhiteKeyWidth*2,                     // E
                        sWhiteKeyWidth*3 + sWhiteKeyWidth/2,                     // F
                        sWhiteKeyWidth*4 - sBlackKeyOffset,                      // F#
                        sWhiteKeyWidth*4 + sWhiteKeyWidth/2,                     // G
                        sWhiteKeyWidth*5 - sBlackKeyOffset,                      // G#
                        sWhiteKeyWidth*5 + sWhiteKeyWidth/2,                     // A
                        sWhiteKeyWidth*6 + sBlackKeyOffset,                      // A#,
                        sWhiteKeyWidth*6 + sWhiteKeyWidth/2,                     // B
            };

    /** Returns the matrix to translate a unit quad to the correct position on the keyboard
     * @TODO: Fill this in */
    public float[] getKeyLocalProjectionMatrix(int index)
    {
        assert(index < sNKeys);
        float w = getKeyWidth(index);
        float h = getKeyHeight(index);
        float x = getKeyPosition(index);
        float depth = isKeyWhite(index) ? 0 : 0.1f;

        return new float[] {
                w, 0, 0, 0,
                0, h, 0, 0,
                0, 0, 0, 0,
                x - w/2, 1-h, depth, 1};
    }

    /** Returns the width of a key in local coordinates */
    public float getKeyWidth(int index)
    {
        return isKeyWhite(index) ? sWhiteKeyVisibleWidth : sBlackKeyWidth;
    }

    /** Returns the height of a key in local coordinates */
    public float getKeyHeight(int index)
    {
        return isKeyWhite(index) ? sWhiteKeyHeight : sBlackKeyHeight;
    }

    /** Returns the position of the key in local coordinates.
     * The centre point is defined to be the middle of the top of the key. */
    public float getKeyPosition(int index)
    {
            int octaveKeyIndex = index % sOctaveKeyPositions.length;
            int octave = index / sOctaveKeyPositions.length;
            float octaveStartPosition = octave * (sWhiteKeyWidth*7);
            return octaveStartPosition + sOctaveKeyPositions[octaveKeyIndex];
    }

    /** Returns true whether the key at the specific index is white */
    public boolean isKeyWhite(int keyIndex)
    {
        return sOctaveColourMapping[keyIndex % sOctaveColourMapping.length];
    }
}
