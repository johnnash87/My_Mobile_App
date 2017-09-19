package com.example.johnnash.week5_modernart;

/**
 * Created by John Nash on 08/09/2017.
 */

import java.util.Random;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.annotation.TargetApi;
import android.graphics.Color;


public class DialogFragment extends Fragment implements SeekBar.OnSeekBarChangeListener {

    private View View1, View2, View3, View4, View5;
    private SeekBar SeekBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.content_main, container, false);
        SeekBar = (SeekBar) rootView.findViewById(R.id.seek_bar);
        View1 = (View) rootView.findViewById(R.id.view1);
        View2 = (View) rootView.findViewById(R.id.view2);
        View3 = (View) rootView.findViewById(R.id.view3);
        View4 = (View) rootView.findViewById(R.id.view4);
        View5 = (View) rootView.findViewById(R.id.view5);
        SeekBar.setOnSeekBarChangeListener(this);
        RandomColors();
        return rootView;
    }

    private void RandomColors(){
        RandomColor(View1, getRandomColor());
        RandomColor(View2, getRandomColor());
        RandomColor(View3, getRandomColor());
        RandomColor(View4, getRandomColor());
        RandomColor(View5, getRandomColor());
    }

    private void RandomColor(View view, int color){
        view.setBackgroundColor(color);
        view.setTag(color);
    }

    private int getRandomColor(){
        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(156), rnd.nextInt(256), rnd.nextInt(256));
        return color;
    }

    private void changeColor(View view, int additionalValue) {
        int color = (Integer) view.getTag();
        color += additionalValue;
        view.setBackgroundColor(color);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress,
                                  boolean fromUser) {
        changeColor(View1, progress);
        changeColor(View2, progress);
        changeColor(View3, progress);
        changeColor(View4, progress);
        changeColor(View5, progress);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

}
