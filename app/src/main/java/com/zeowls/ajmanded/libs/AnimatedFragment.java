package com.zeowls.ajmanded.libs;

import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.zeowls.ajmanded.R;

/*
 * Created by root on 6/5/17.
 */

public class AnimatedFragment extends Fragment {
    public void startAnimation() {
    }

    public class animate extends AsyncTask<Void, Void, Void> {

        View[] views;

        public animate(View[] views) {
            this.views = views;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            for (View view : views) {
                try {
                    view.setVisibility(View.INVISIBLE);
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }

        @Override
        protected Void doInBackground(Void... params) {
            long delayBetweenAnimations = 200L;
            for (int i = 0; i < views.length; i++) {
                final View view = views[i];
                // We calculate the delay for this Animation, each animation starts 100ms
                // after the previous one
                int delay = (int) (i * delayBetweenAnimations);
                try {
                    view.postDelayed(() -> {
                        Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_in_right);
                        view.setVisibility(View.VISIBLE);
                        view.startAnimation(animation);
                    }, delay);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

    }
}
