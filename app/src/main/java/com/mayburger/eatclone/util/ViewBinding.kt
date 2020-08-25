package com.mayburger.eatclone.util

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.view.MotionEvent
import android.view.View
import androidx.databinding.BindingAdapter

object ViewBinding {

    @BindingAdapter("onClickAnimate")
    @JvmStatic
    fun setOnClickAnimate(view:View, runnable:Runnable){
        view.setOnTouchListener { p0, p1 ->
            when(p1?.action){
                MotionEvent.ACTION_DOWN->{
                    val scaleDownX = ObjectAnimator.ofFloat(view,
                        "scaleX", 0.9f);
                    val scaleDownY = ObjectAnimator.ofFloat(view,
                        "scaleY", 0.9f);
                    scaleDownX.duration = 150;
                    scaleDownY.duration = 150;
                    val scaleDown = AnimatorSet();
                    scaleDown.play(scaleDownX).with(scaleDownY);
                    scaleDown.start();
                }
                MotionEvent.ACTION_UP->{
                    val  scaleDownX2 = ObjectAnimator.ofFloat(
                        view, "scaleX", 1f);
                    val  scaleDownY2 = ObjectAnimator.ofFloat(
                        view, "scaleY", 1f);
                    scaleDownX2.duration = 300;
                    scaleDownY2.duration = 300;

                    val scaleDown2 = AnimatorSet();
                    scaleDown2.play(scaleDownX2).with(scaleDownY2);
                    scaleDown2.start();
                    runnable.run()
                    view.performClick()
                }
                MotionEvent.ACTION_CANCEL->{
                    val  scaleDownX2 = ObjectAnimator.ofFloat(
                        view, "scaleX", 1f);
                    val  scaleDownY2 = ObjectAnimator.ofFloat(
                        view, "scaleY", 1f);
                    scaleDownX2.duration = 300;
                    scaleDownY2.duration = 300;

                    val scaleDown2 = AnimatorSet();
                    scaleDown2.play(scaleDownX2).with(scaleDownY2);
                    scaleDown2.start();
                }
            }
            true
        }
    }

}