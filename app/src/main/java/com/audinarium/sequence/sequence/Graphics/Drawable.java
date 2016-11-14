package com.audinarium.sequence.sequence.Graphics;

/**
 * Created by Volodymyr on 10/25/2016.
 */

public interface Drawable
{
    /**
     *
     * @param projectionMatrix A matrix to be applied after all internal transformation took place.
     */
    void draw(float[] projectionMatrix);
}
