package com.example.joaquin.ad340;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.transition.Fade;
import android.transition.Scene;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

public class Tutorial7a extends ActionBarActivity {

    private ViewGroup sceneRoot;
    private Scene aScene;
    private Scene anotherScene;
    private boolean switched = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial7a);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tutorial7a, menu);
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

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void scene_click(View view) {
        sceneRoot = (ViewGroup)findViewById(R.id.scene_root);

            aScene = Scene.getSceneForLayout(
                    sceneRoot,
                    R.layout.a_scene,
                    this);

            anotherScene = Scene.getSceneForLayout(
                    sceneRoot,
                    R.layout.another_scene,
                    this);

        if(switched) {
            Transition fade = new Fade(Fade.IN);
            TransitionManager.go(anotherScene, fade);
            switched = false;
        } else {
            Transition fade = new Fade(Fade.IN);
            TransitionManager.go(aScene, fade);
            switched = true;
        }
    }

    public void goToCard(View view) {
        Intent intent = new Intent(Tutorial7a.this, FlipCardActivity.class);
        startActivity(intent);
    }
}
