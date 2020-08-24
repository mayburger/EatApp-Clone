package com.mayburger.eatclone.util

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter

object ViewBinding {

    @BindingAdapter("imageResource")
    @JvmStatic
    fun setImageResource(view: ImageView, resource:Int){
        view.setImageResource(resource)
    }
    @BindingAdapter("backgroundResource")
    @JvmStatic
    fun setBackgroundResource(view: View, resource:Int){
        view.setBackgroundResource(resource)
    }

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
//        view.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                switch (event.getAction()) {
//                    case MotionEvent.ACTION_DOWN:
//                    ObjectAnimator scaleDownX = ObjectAnimator.ofFloat(imgSpin,
//                    "scaleX", 0.8f);
//                    ObjectAnimator scaleDownY = ObjectAnimator.ofFloat(imgSpin,
//                    "scaleY", 0.8f);
//                    scaleDownX.setDuration(1000);
//                    scaleDownY.setDuration(1000);
//
//                    AnimatorSet scaleDown = new AnimatorSet();
//                    scaleDown.play(scaleDownX).with(scaleDownY);
//
//                    scaleDown.start();
//
//                    spinslot();
//                    break;
//
//                    case MotionEvent.ACTION_UP:
//                    ObjectAnimator scaleDownX2 = ObjectAnimator.ofFloat(
//                            imgSpin, "scaleX", 1f);
//                    ObjectAnimator scaleDownY2 = ObjectAnimator.ofFloat(
//                            imgSpin, "scaleY", 1f);
//                    scaleDownX2.setDuration(1000);
//                    scaleDownY2.setDuration(1000);
//
//                    AnimatorSet scaleDown2 = new AnimatorSet();
//                    scaleDown2.play(scaleDownX2).with(scaleDownY2);
//
//                    scaleDown2.start();
//
//                    imgSpin.setEnabled(false);
//                    break;
//                }
//                return true;
//            }
//        });
    }

}