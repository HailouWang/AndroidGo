package com.github.hailouwang.demosforapi.widget;

import android.os.Bundle;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;

import com.github.hailouwang.demosforapi.R;

public class LabeledSeekBarActivity extends AppCompatActivity {

    private LabeledSeekBar mLabeledSeekBar;
    private int currentIndex = 0;

    private SeekBar mSeekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_labeled_seek_bar);

        mLabeledSeekBar = (LabeledSeekBar) findViewById(R.id.labeled_seekbar);

        String[] entries = new String[]{"Label1","Label2","Label3","Label4","Label5"};
        int[] values = new int[]{1,2,3,4,5};

        int  max = Math.max(1,entries.length - 1);

        mLabeledSeekBar.setLabels(entries);
        mLabeledSeekBar.setMax(max);
        mLabeledSeekBar.setProgress(currentIndex);
        mLabeledSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        mSeekBar = (SeekBar) findViewById(R.id.seekbar);
        mSeekBar.setMax(max);
        mSeekBar.setProgress(currentIndex);
    }
}
