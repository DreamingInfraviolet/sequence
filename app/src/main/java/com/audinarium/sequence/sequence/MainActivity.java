package com.audinarium.sequence.sequence;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.FragmentManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import processing.android.PFragment;

import com.audinarium.sequence.sequence.Graphics.ProcessingSketch;

public class MainActivity extends AppCompatActivity {

    private static MainActivity sInstance = null;

    public static MainActivity instance()
    {
        return sInstance;
    }

    public ProcessingSketch mSketch;

    @Override
    protected void onResume()
    {
        super.onResume();
        sInstance = this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sInstance = this;

        super.onCreate(savedInstanceState);

        AudioPlayback.init(getApplicationContext());

        setContentView(R.layout.activity_main);
        FragmentManager fragmentManager = getFragmentManager();
        mSketch = new ProcessingSketch();
        PFragment fragment = new PFragment();
        fragment.setSketch(mSketch);
        fragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commit();


        final ImageButton submitButton = (ImageButton)findViewById(R.id.submitButton);

        submitButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(MainActivity.this, StaveActivity.class));
            }
        });

        //Niamhs Button Programming
        final Button undoButton = (Button)findViewById(R.id.undoButton);
        final Button redoButton = (Button)findViewById(R.id.redoButton);
        final Button resetButton = (Button)findViewById(R.id.resetButton);

        undoButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                MainActivity.instance().mSketch.getUndoStack().push(MainActivity.instance().mSketch.getKeysPlayed().get(MainActivity.instance().mSketch.getKeysPlayed().size() - 1));
                MainActivity.instance().mSketch.getKeysPlayed().remove(MainActivity.instance().mSketch.getKeysPlayed().size() - 1);
            }
        });

        redoButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(MainActivity.instance().mSketch.getUndoStack().empty())
                    return;
                MainActivity.instance().mSketch.getKeysPlayed().add(MainActivity.instance().mSketch.getUndoStack().pop());
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                MainActivity.instance().mSketch.clearKeysPlayed();
            }
        });

    }

}
