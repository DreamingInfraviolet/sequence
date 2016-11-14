package com.audinarium.sequence.sequence.Graphics;

import android.graphics.Shader;
import android.util.ArrayMap;

/**
 * Created by Volodymyr on 10/25/2016.
 */

public class ShaderStore
{
    private static class ShaderPair {
        public ShaderPair(String vertex, String fragment) {
            this.vertex = vertex;
            this.fragment = fragment;
        }
        public String vertex, fragment;
    }


    private final static ArrayMap<String, ShaderPair> sAvailableShaders = new ArrayMap<>();

    static
    {
        // Initialise shaders
        //@TODO fill with useful stuff
        sAvailableShaders.put(
                "simple",
                new ShaderPair(
                    "" +
                            "attribute vec4 vertuv;\n" +
                            "\n" +
                            "uniform mat4 mvp;\n" +
                            "\n" +
                            "void main()\n" +
                            "{\n" +
                                "    gl_Position = mvp*vec4(vertuv.x, vertuv.y, 0, 1);\n" +
                            "}",
                    "" +
                            "uniform vec4 colour;\n" +
                            "\n" +
                            "void main()\n" +
                            "{\n" +
                            "    gl_FragColor = colour;\n" +
                            "}")
        );
    }

    private static ArrayMap<String, ShaderProgram> mMap = new ArrayMap<>();

    private static void createShader(String name) throws RuntimeException {
        ShaderPair pair = sAvailableShaders.get(name);
        if (pair == null)
            throw new RuntimeException("Could not find shader " + name);
        mMap.put(name, new ShaderProgram(pair.vertex, pair.fragment));
    }

    public static ShaderProgram getShader(String name) {
        if(!mMap.containsKey(name))
            createShader(name);
        return mMap.get(name);
    }
}
