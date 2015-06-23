package com.example.joaquin.ad340;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class FlipCardActivity extends Activity {

    private boolean showingBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_flip);

        if (savedInstanceState == null) {
            getFragmentManager()
                    .beginTransaction()
                    .add(R.id.container, new CardFrontFragment())
                    .commit();
        }
        flipCard();
    }

    public static class CardFrontFragment extends Fragment {

        private View view;
        private boolean showingBack = true;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            view = inflater.inflate(R.layout.fragment_flip_card_front, container, false);
            ImageView smileyView = (ImageView)view.findViewById(R.id.smiley_pic);
            smileyView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!showingBack) {
                        showingBack = true;
                        getFragmentManager().popBackStack();
                        return;
                    }

                    getFragmentManager()
                            .beginTransaction()
                            .setCustomAnimations(
                                    R.animator.card_flip_right_in, R.animator.card_flip_right_out,
                                    R.animator.card_flip_left_in, R.animator.card_flip_left_out)
                            .replace(R.id.container, new CardBackFragment())
                            .addToBackStack(null)
                            .commit();
                }

            });
            return view;
        }

    }

    public static class CardBackFragment extends Fragment {

        private View view;
        private boolean showingBack = true;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            view = inflater.inflate(R.layout.fragment_flip_card_back, container, false);
            TextView backText = (TextView) view.findViewById(R.id.back_text);
            backText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (showingBack) {
                        showingBack = false;
                        getFragmentManager().popBackStack();
                        return;
                    }

                    getFragmentManager()
                            .beginTransaction()
                            .setCustomAnimations(
                                    R.animator.card_flip_right_in, R.animator.card_flip_right_out,
                                    R.animator.card_flip_left_in, R.animator.card_flip_left_out)
                            .replace(R.id.container, new CardBackFragment())
                            .addToBackStack(null)
                            .commit();
                }
            });
            return view;
        }

    }

    public void flipCard() {
        if (showingBack) {
            getFragmentManager().popBackStack();
            return;
        }

        // Flip to the back.
        showingBack = true;

        // Create and commit a new fragment transaction that adds the fragment for the back of
        // the card, uses custom animations, and is part of the fragment manager's back stack.

        getFragmentManager()
                .beginTransaction()

                        // Replace the default fragment animations with animator resources representing
                        // rotations when switching to the back of the card, as well as animator
                        // resources representing rotations when flipping back to the front (e.g. when
                        // the system Back button is pressed).
                .setCustomAnimations(
                        R.animator.card_flip_right_in, R.animator.card_flip_right_out,
                        R.animator.card_flip_left_in, R.animator.card_flip_left_out)

                        // Replace any fragments currently in the container view with a fragment
                        // representing the next page (indicated by the just-incremented currentPage
                        // variable).
                .replace(R.id.container, new CardBackFragment())

                        // Add this transaction to the back stack, allowing users to press Back
                        // to get to the front of the card.
                .addToBackStack(null)

                        // Commit the transaction.
                .commit();
    }

}
