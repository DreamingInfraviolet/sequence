package com.audinarium.sequence.sequence.Graphics;

import android.opengl.GLES20;
import android.opengl.Matrix;

/**
 * Created by Volodymyr on 11/8/2016.
 *
 * Represents a view into a keyboard.
 */

public class KeyboardView extends RenderView
{
    /** Current offset from the left in local keyboard coordinates */
    private float mViewOffset = 0;
    /** The keys are scaled by this value to simulate zoom */
    private float mZoomLevel = 4.0f;
    /** The keyboard object to use for rendering */
    private Keyboard            mKeyboard;
    /** The callback class to call when a note is pressed. Can be null */
    private NotePressedCallback mNotePressedCallback;

    /** Creates a new keyboard view.
     *
     * @param keyboard The keyboard object to use. If null, will create a default one.
     * @param callback The callback to execute when a note is pressed. If null, no callback.
     */
    public KeyboardView(Keyboard keyboard, NotePressedCallback callback)
    {
        mKeyboard = (keyboard == null) ? new Keyboard() : keyboard;
        mNotePressedCallback = callback;
    }

    @Override
    public void draw(float[] projectionMatrix)
    {
        GLES20.glClearColor(0, 0, 0, 1);
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);

        ShaderProgram shader = ShaderStore.getShader("simple");

        for(int key = 0; key < mKeyboard.sNKeys; ++key)
        {
            float[] matrix = mKeyboard.getKeyLocalProjectionMatrix(key);
            float[] scaleMatrix = new float[]{mZoomLevel, 0, 0, 0,
                                              0, 1, 0, 0,
                                              0, 0, 1, 0,
                                              0, 0, 0, 1};
            float[] scaledMatrix = new float[16];
            Matrix.multiplyMM(scaledMatrix, 0, scaleMatrix, 0, matrix, 0);
            float[] colour = mKeyboard.isKeyWhite(key) ? new float[]{1,1,1,1} : new float[]{0,0,0,1};

            float[] finalMatrix = new float[16];
            Matrix.multiplyMM(finalMatrix, 0, projectionMatrix, 0, scaledMatrix, 0);

            shader.use();
            GLES20.glUniformMatrix4fv(GLES20.glGetUniformLocation(shader.mProgram, "mvp"),
                                      1, false, finalMatrix , 0);
            GLES20.glUniform4f(GLES20.glGetUniformLocation(shader.mProgram, "colour"),
                                colour[0],colour[1],colour[2],colour[3]);

            Geometry.sIdentityQuad.draw();
        }
    }
}
