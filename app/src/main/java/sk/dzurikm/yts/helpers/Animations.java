package sk.dzurikm.yts.helpers;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;

public class Animations {
    private static final int DEFAULT_ANIMATION_DURATION = 500;

    public static void fadeOut(View view){
        fadeOut(view,DEFAULT_ANIMATION_DURATION);
    }

    public static void fadeOut(View view, int duration){
        view.setAlpha(1f);
        view.animate()
                .alpha(0.0f)
                .setDuration(duration)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        view.setVisibility(View.GONE);
                    }
                });
    }

    public static void fadeIn(View view){
        fadeIn(view,DEFAULT_ANIMATION_DURATION);
    }

    public static void fadeIn(View view, int duration){
        view.setAlpha(0f);
        view.setVisibility(View.VISIBLE);
        view.animate()
                .alpha(1.0f)
                .setDuration(duration)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        view.setAlpha(1f);
                    }
                });;
    }
}
