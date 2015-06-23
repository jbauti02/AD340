package com.example.joaquin.ad340;

import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class Tutorial4a extends ActionBarActivity {

    private static final String TAG = "Debug";
    private GestureDetectorCompat mDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial4a);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tutorial4a, menu);
        mDetector = new GestureDetectorCompat(this, new MyGestureListener());
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        this.mDetector.onTouchEvent(event);
        return super.onTouchEvent(event);

    }

    class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        private static final String DEBUG_TAG = "Gestures";

       @Override
       public boolean onDown(MotionEvent event) {
           Log.d(DEBUG_TAG, "onDown" + event.toString());
           return true;

       }

        @Override
        public boolean onDoubleTap(MotionEvent event) {
            TextView text = (TextView)findViewById(R.id.double_clicker);
            if(text.getVisibility() == View.VISIBLE)
                text.setVisibility(View.INVISIBLE);
            else
                text.setVisibility(View.VISIBLE);
            return true;

        }

    }




}
