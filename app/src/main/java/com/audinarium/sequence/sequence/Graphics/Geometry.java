package com.audinarium.sequence.sequence.Graphics;

import android.opengl.GLES20;

import java.nio.FloatBuffer;

/**
 * Created by Volodymyr on 10/25/2016.
 */

public class Geometry
{
    private int mBuffer;
    private int mNumberOfVertices;

    public static void init()
    {
        sIdentityQuad = new Geometry(new float[]{0, 0, 0.0f, 0.0f,
                    1, 0, 0.5f, 0.0f,
                    0, 1, 0.0f, 0.5f,
                    0, 1, 0.0f, 0.5f,
                    1, 0, 0.5f, 0.0f,
                    1, 1, 0.5f, 0.5f});
    }

    public static Geometry sIdentityQuad = null;
    public Geometry(float[] vertuv)
    {
        if(vertuv == null || vertuv.length % 4 != 0)
            throw new IllegalArgumentException();
        int[] buffer = new int[1];
        buffer[0]=-1;
        GLES20.glGenBuffers(1, buffer, 0);
        mBuffer = buffer[0];
        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, mBuffer);
        GLES20.glBufferData(GLES20.GL_ARRAY_BUFFER, vertuv.length * 4, FloatBuffer.wrap(vertuv), GLES20.GL_STATIC_DRAW);
        mNumberOfVertices = vertuv.length / 4;
    }

    public void draw()
    {
        GLES20.glEnableVertexAttribArray(0);
        GLES20.glVertexAttribPointer(0, 4, GLES20.GL_FLOAT, false, 0, 0);
        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, mBuffer);
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, mNumberOfVertices);
    }
}
