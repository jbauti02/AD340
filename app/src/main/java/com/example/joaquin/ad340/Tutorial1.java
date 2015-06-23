package com.example.joaquin.ad340;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Tutorial1 extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial1);
        String getMe = getIntent().getStringExtra("setMeTag");
        TextView text = (TextView)findViewById(R.id.intentHere);
        text.setText(getMe);

    }

    @Override
    protected void onResume() {
    super.onResume();
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        String defaultValue = sharedPref.getString(
                String.valueOf(R.string.save_string_key),
                String.valueOf(R.string.type_here));
        EditText edit = (EditText)findViewById(R.id.save_text);
        edit.setText(defaultValue);
        edit.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if(event.getRawY() > 50) {

                    launchEvent();

                }

                return true;

            }

        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        EditText edit = (EditText)findViewById(R.id.save_text);
        editor.putString(String.valueOf(R.string.save_string_key), edit.getText().toString());
        editor.commit();
    }

    @Override
    protected void onStart() {
    super.onStart();

    }

    @Override
    protected void onDestroy() {
    super.onDestroy();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tutorial1, menu);
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

    public void launchEvent() {

        Intent intent = new Intent(this, Tutorial1a.class);
        intent.addFlags(intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);

    }
}
