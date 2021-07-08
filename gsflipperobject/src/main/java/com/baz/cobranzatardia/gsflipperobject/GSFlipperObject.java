package com.baz.cobranzatardia.gsflipperobject;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

public class GSFlipperObject {
    private final DecelerateInterpolator decelerateInterpolator;
    private final AccelerateDecelerateInterpolator accelerateDecelerateInterpolator;
    private String scaleX;
    private int animationDuration;
    private ObjectAnimator oa1;
    private ObjectAnimator oa2;

    public GSFlipperObject(int duration) {
        scaleX = "scaleX";
        animationDuration = duration;
        decelerateInterpolator = new DecelerateInterpolator();
        accelerateDecelerateInterpolator = new AccelerateDecelerateInterpolator();
    }

    public void flip(final View vistaInicial, final View vistaFinal) {
        oa1 = ObjectAnimator.ofFloat(vistaInicial, scaleX, 1f, 0f);
        oa2 = ObjectAnimator.ofFloat(vistaFinal, scaleX, 0f, 1f);
        oa1.setInterpolator(decelerateInterpolator);
        oa2.setInterpolator(accelerateDecelerateInterpolator);
        oa1.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                vistaFinal.setVisibility(View.VISIBLE);
                vistaInicial.setVisibility(View.GONE);
                oa2.start();
            }
        });

        oa1.setDuration(animationDuration);
        oa2.setDuration(animationDuration);

        oa1.start();
    }
}
