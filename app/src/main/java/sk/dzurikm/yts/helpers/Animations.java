package sk.dzurikm.yts.helpers;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;

public class Animations {
    private static final int DEFAULT_ANIMATION_DURATION = 500;

    public static void fadeOut(View view){
        fadeOut(view,DEFAULT_ANIMATION_DURATION,true);
    }

    public static void fadeOut(View view,boolean isEffectPresentAfter){
        fadeOut(view,DEFAULT_ANIMATION_DURATION,isEffectPresentAfter);
    }

    public static void fadeOut(View view, int duration, boolean isEffectPresentAfter){
        view.setAlpha(1f);
        view.animate()
                .alpha(0.0f)
                .setDuration(duration)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        if (isEffectPresentAfter) view.setVisibility(View.GONE);
                    }
                });
    }


    public static void fadeIn(View view){
        fadeIn(view,DEFAULT_ANIMATION_DURATION,true);
    }

    public static void fadeIn(View view,boolean isEffectPresentAfter){
        fadeIn(view,DEFAULT_ANIMATION_DURATION,isEffectPresentAfter);
    }

    public static void fadeIn(View view, int duration, boolean isEffectPresentAfter){
        view.setAlpha(0f);
        view.setVisibility(View.VISIBLE);
        view.animate()
                .alpha(1.0f)
                .setDuration(duration)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);

                        if (isEffectPresentAfter) view.setAlpha(1f);
                    }
                });;
    }
}
