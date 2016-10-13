package com.audinarium.sequence.sequence.Graphics;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

import com.audinarium.sequence.sequence.Graphics.MainRenderer;

/**
 * Created by Volodymyr on 10/13/2016.
 */

class MainGlSurfaceView extends GLSurfaceView {

    private final Renderer mainRenderer;

    public MainGlSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);

        setEGLContextClientVersion(2);
        mainRenderer = new MainRenderer();
        setRenderer(mainRenderer);

        // Set to RENDERMODE_CONTINUOUSLY if needed.
        setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
    }
}
