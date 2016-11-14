package com.audinarium.sequence.sequence.Graphics;

import android.graphics.Rect;
import android.opengl.GLES20;

/**
 * Created by Volodymyr on 10/20/2016.
 */

public abstract class RenderView implements Drawable
{
    private int x, y, w, h;

    /** Sets the gl viewport of the render view. Should be called during launch and whenever the
     * screen is updated. */
    public void setViewport(int x, int y, int w, int h)
    {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    /* To draw an object in the right position on the screen, we must go through a few
     * coordinate spaces:
     * unit -> local:    convert unit polygonal shapes into local coordinates.
     *                   e.g. converting unit squares to keys on a keyboard.
     *                   This stage is specific to what is being drawn and can't be generalised.
     * local -> view:    convert local objects to view space. A view is a coordinate plane
     *                   in the range [0,1]. Note that in this space the vertices must be
     *                   scaled non-uniformly to compensate for the next step
     * view -> glview:   convert from [0,1] range to [-1,1] range, as expected by opengl
     * glview -> screen: call glViewport to designate the screen subregion to draw to.
     *                   This means that the image will be scaled, but we have compensated beforehand.
     *                   Now just draw :) Does not require a matrix.
     */

    /** Constructs a matrix that translates from local coordinates to aspect-compensated view coordinates.
     * Assumes that the width of the local coordinate world is 1.
     * The matrix to do this is: (android assumes 4x4 matrixes)
     * | 1   0   0 0 |
     * | 0 (w/h) 0 0 |
     * | 0   0   0 0 |
     * | 0   0   0 1 |
     */
    public float[] getLocalToViewMatrix()
    {
        // Column major matrices are expected
        return new float[] {
                1, 0, 0, 0,
                0, ((float)w)/h, 0, 0,
                0, 0, 0, 0,
                0, 0, 0, 1};
    }

    /** Constructs a matrix that translates from a 2d [0,-1] system to a [-1,1] opengl system.
     * The matrix to do this is: (android assumes 4x4 matrixes)
     * | 2 0 0 -1 |
     * | 0 2 0 -1 |
     * | 0 0 0 0  |
     * | 0 0 0 1  |
     */
    public static float[] getNormalToGLMatrix()
    {
        // Column major matrices are expected
        return new float[] {
                2, 0, 0, 0,
                0, 2, 0, 0,
                0, 0, 0, 0,
                -1, -1, 0, 1};
    }

    /** Calls glViewport to draw within the render view */
    public void prepareViewport()
    {
        GLES20.glViewport(x, y, w, h);
    }
}
