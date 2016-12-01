package com.audinarium.sequence.sequence;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.FragmentManager;
import processing.android.PFragment;
import processing.core.PApplet;

import com.audinarium.sequence.sequence.Graphics.Chord;
import com.audinarium.sequence.sequence.Graphics.ProcessingSketch;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AudioPlayback.init(getApplicationContext());

        setContentView(R.layout.activity_main);
        FragmentManager fragmentManager = getFragmentManager();
        PApplet sketch = new ProcessingSketch();
        PFragment fragment = new PFragment();
        fragment.setSketch(sketch);
        fragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
    }

}
