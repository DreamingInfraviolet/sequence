package com.audinarium.sequence.sequence;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.audinarium.sequence.sequence.Graphics.*;
import com.audinarium.sequence.sequence.Graphics.StaveSketch;

import processing.android.PFragment;
import processing.core.PApplet;

/**
 * Created by Volodymyr on 12/19/2016.
 */

public class StaveActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_stave);

        FragmentManager fragmentManager = getFragmentManager();
        PFragment fragment = new PFragment();
        fragment.setSketch(new StaveSketch());
        fragmentManager.beginTransaction()
                .replace(R.id.stave_layout, fragment)
                .commit();

        Button backToKeyboard = (Button)findViewById(R.id.backToKeyboard);

        backToKeyboard.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(StaveActivity.this, MainActivity.class));
            }
        });
    }
}
