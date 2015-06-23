package com.example.joaquin.ad340;

import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Tutorial6b extends ActionBarActivity {

    private int numClicks;
    public TextView mText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial6b);
        mText = (TextView)findViewById(R.id.thread_change);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tutorial6b, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void threading_click(View view) {
        new SortingTask().execute(1000000);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10000, 0); // Create a new thread
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void threading_click_again(View view) {
        numClicks++;
        mText = (TextView)findViewById(R.id.thread_change);
        mText.setText("I've been clicked " + Integer.toString(numClicks) + " time(s)!");
    }

    public class SortingTask extends AsyncTask <Integer, Void, String> {

        protected String doInBackground(Integer... num) {
            Random rand = new Random();
            int numberOfRandomNumbers = num[0];
            ArrayList<Integer> listOfRandomIntegers = new ArrayList<Integer>();
            for(int i = 0; i < numberOfRandomNumbers; i++) {

                listOfRandomIntegers.add(rand.nextInt());

            }
            Collections.sort(listOfRandomIntegers);
            String returningList = "";
            for(int j = 0; j < listOfRandomIntegers.size(); j++) {

                returningList += Integer.toString(listOfRandomIntegers.get(j));

            }
            return returningList;
        }

        protected void onPostExecute(String... str) {
            mText.setText(str[0]);
        }

    }

}


