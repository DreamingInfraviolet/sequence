package com.audinarium.sequence.sequence.Graphics;

import processing.core.PApplet;

/**
 * Created by Volodymyr on 11/17/2016.
 */

public class ProcessingSketch extends PApplet
{
    public void settings()
    {
        size(600, 600);
    }

    public void setup() { }

    public void draw()
    {
        if (mousePressed)
        {
            ellipse(mouseX, mouseY, 50, 50);
        }
    }
}
