package com.audinarium.sequence.sequence.Graphics;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.os.Debug;
import android.util.Log;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import static android.util.Log.e;

/**
 * Created by Volodymyr on 10/13/2016.
 */

public class MainRenderer implements GLSurfaceView.Renderer {

    /** Available render views enum: helps identify render views in array. */
    private enum RenderViews {KeyboardView, StaveView, END};
    /** An array of available render views that should be drawn and updated */
    private RenderView[] mRenderViews = new RenderView[RenderViews.END.ordinal()];

    public MainRenderer()
    {
        super();
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config)
    {
        GLES20.glClearColor(0,1,0,1);

        GLES20.glEnable(GLES20.GL_DEPTH_TEST);
        GLES20.glDepthFunc(GLES20.GL_GREATER);

        // Initialise all render views
        Geometry.init();
        mRenderViews[RenderViews.KeyboardView.ordinal()] = new KeyboardView(null, null);
        mRenderViews[RenderViews.StaveView.ordinal()] = new Stave();
    }

    /* Called after the surface is created and whenever the OpenGL ES surface size changes. */
    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        mRenderViews[RenderViews.KeyboardView.ordinal()].setViewport(0, 0, width, height/2);
        mRenderViews[RenderViews.StaveView.ordinal()].setViewport(0, height/2, width, height/2);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);

        for(RenderView view : mRenderViews)
        {
            view.prepareViewport();
            view.draw(RenderView.getNormalToGLMatrix());
            int err = GLES20.glGetError();
            if(err!=GLES20.GL_NO_ERROR)
                assert(false);
        }
    }
}
