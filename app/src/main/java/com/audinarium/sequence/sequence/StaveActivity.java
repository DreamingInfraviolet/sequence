package com.audinarium.sequence.sequence;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.audinarium.sequence.sequence.Graphics.StaveSketch;

import processing.android.PFragment;
import processing.core.PApplet;

import static android.R.attr.start;

/**
 * Created by Volodymyr on 12/19/2016.
 * The main stave activity. This just launches the processing and handles buttons.
 */

public class StaveActivity extends AppCompatActivity
{
    boolean playButtonClicked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_stave);
        FragmentManager fragmentManager = getFragmentManager();
        PApplet sketch = new StaveSketch(MainActivity.instance().mSketch.getKeysPlayedAsArray(), this);
        PFragment fragment = new PFragment();
        fragment.setSketch(sketch);
        fragmentManager.beginTransaction()
                .replace(R.id.stave_layout, fragment)
                .commit();

        final ImageButton playButton = (ImageButton)findViewById(R.id.play);
        ImageButton stopButton = (ImageButton) findViewById(R.id.stop);

        playButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                playButtonClicked = !playButtonClicked;
                if(playButtonClicked)
                {
                    playButtonClicked = true;
                    StaveSketch.instance().getState().pausePlayback();
                    playButton.setImageResource(R.drawable.playbutton2);
                }
                else
                {
                    playButtonClicked = false;
                    StaveSketch.instance().getState().startPlayback();
                    playButton.setImageResource(R.drawable.pausebutton2);
                }
            }
        });
        stopButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                MainActivity.instance().mSketch.clearKeysPlayed();
                onBackPressed();
            }
        });
    }
}
