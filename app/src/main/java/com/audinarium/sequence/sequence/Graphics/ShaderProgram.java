package com.audinarium.sequence.sequence.Graphics;

import android.opengl.GLES20;

/**
 * Created by Volodymyr on 10/25/2016.
 */

public class ShaderProgram {

    int mVertex, mFragment, mProgram;

    public ShaderProgram(String vertexStr, String fragmentStr) {
        int vertex = loadShader(GLES20.GL_VERTEX_SHADER, vertexStr);
        int fragment = loadShader(GLES20.GL_FRAGMENT_SHADER, fragmentStr);

        int program = GLES20.glCreateProgram();
        if(program == 0)
            throw new RuntimeException("Could not create new shader program");
        throwIfGLError();

        GLES20.glAttachShader(program, vertex);
        throwIfGLError();
        GLES20.glAttachShader(program, fragment);
        throwIfGLError();

        GLES20.glLinkProgram(program);

        int[] linkStatus = new int[1];
        GLES20.glGetProgramiv(program, GLES20.GL_LINK_STATUS, linkStatus, 0);
        if (linkStatus[0] != GLES20.GL_TRUE) {
            GLES20.glDeleteProgram(program);
            throw new RuntimeException("Could not create program: " + GLES20.glGetProgramInfoLog(program));
        }

        mVertex = vertex;
        mFragment = fragment;
        mProgram = program;
    }

    public void use()
    {
        GLES20.glUseProgram(mProgram);
    }

    private int loadShader(int shaderType, String source) {
        int shader = GLES20.glCreateShader(shaderType);
        if (shader != 0) {
            GLES20.glShaderSource(shader, source);
            GLES20.glCompileShader(shader);
            int[] compiled = new int[1];
            GLES20.glGetShaderiv(shader, GLES20.GL_COMPILE_STATUS, compiled, 0);
            if (compiled[0] == 0) {
                GLES20.glDeleteShader(shader);
                throw new RuntimeException("Could not compile shader "
                        + shaderType + ":"
                        + GLES20.glGetShaderInfoLog(shader));
            }
        }
        return shader;
    }

    private void throwIfGLError() {
        int err = GLES20.glGetError();
        if(err != GLES20.GL_NO_ERROR) {
            throw new RuntimeException("GL Error: " + err);
        }
    }
}
