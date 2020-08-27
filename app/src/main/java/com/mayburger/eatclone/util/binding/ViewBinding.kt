package com.mayburger.eatclone.util.binding

import android.animation.AnimatorSet
import android.animation.LayoutTransition
import android.animation.ObjectAnimator
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mayburger.eatclone.util.constants.RecyclerConstants
import com.mayburger.eatclone.util.ext.setOnSingleClickListener

object ViewBinding {

    @BindingAdapter("onSingleClick")
    @JvmStatic
    fun setOnSingleClick(view: View, runnable: Runnable) {
        view.setOnSingleClickListener {
            runnable.run()
        }
    }

    @BindingAdapter("animateLayoutChanges")
    @JvmStatic
    fun animateLayoutChanges(view: ViewGroup, animate: Boolean) {
        view.layoutTransition
            .enableTransitionType(LayoutTransition.CHANGING);
    }

    @BindingAdapter("recyclerLayoutManager")
    @JvmStatic
    fun horizontalLayoutManager(view: RecyclerView, id: Int) {
        val layoutManager:RecyclerView.LayoutManager? = when (id) {
            RecyclerConstants.VERTICAL_LAYOUT_MANAGER->{
                LinearLayoutManager(view.context)
            }
            RecyclerConstants.HORIZONTAL_LAYOUT_MANAGER->{
                val horizontalLinearLayoutManager = LinearLayoutManager(view.context)
                horizontalLinearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
                horizontalLinearLayoutManager
            }
            RecyclerConstants.GRID_2_LAYOUT_MANAGER->{
                GridLayoutManager(view.context,2)
            }
            RecyclerConstants.GRID_3_LAYOUT_MANAGER->{
                GridLayoutManager(view.context,3)
            }
            else->{
                LinearLayoutManager(view.context)
            }
        }
        view.layoutManager = layoutManager
    }

    @BindingAdapter("onClickAnimate")
    @JvmStatic
    fun setOnClickAnimate(view: View, runnable: Runnable) {
        view.setOnTouchListener { p0, p1 ->
            when (p1?.action) {
                MotionEvent.ACTION_DOWN -> {
                    val scaleDownX = ObjectAnimator.ofFloat(
                        view,
                        "scaleX", 0.9f
                    );
                    val scaleDownY = ObjectAnimator.ofFloat(
                        view,
                        "scaleY", 0.9f
                    );
                    scaleDownX.duration = 150;
                    scaleDownY.duration = 150;
                    val scaleDown = AnimatorSet();
                    scaleDown.play(scaleDownX).with(scaleDownY);
                    scaleDown.start();
                }
                MotionEvent.ACTION_UP -> {
                    val scaleDownX2 = ObjectAnimator.ofFloat(
                        view, "scaleX", 1f
                    );
                    val scaleDownY2 = ObjectAnimator.ofFloat(
                        view, "scaleY", 1f
                    );
                    scaleDownX2.duration = 300;
                    scaleDownY2.duration = 300;

                    val scaleDown2 = AnimatorSet();
                    scaleDown2.play(scaleDownX2).with(scaleDownY2);
                    scaleDown2.start();
                    runnable.run()
                    view.performClick()
                }
                MotionEvent.ACTION_CANCEL -> {
                    val scaleDownX2 = ObjectAnimator.ofFloat(
                        view, "scaleX", 1f
                    );
                    val scaleDownY2 = ObjectAnimator.ofFloat(
                        view, "scaleY", 1f
                    );
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