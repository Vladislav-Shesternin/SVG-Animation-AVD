package com.example.vectoranimations

import android.content.Context
import android.graphics.drawable.Animatable2
import android.graphics.drawable.AnimatedVectorDrawable
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.AttributeSet
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.withStyledAttributes

@RequiresApi(Build.VERSION_CODES.M)
class AvdView(
    context: Context,
    private val attrs: AttributeSet
) : AppCompatImageView(context, attrs) {

    private lateinit var start: AnimatedVectorDrawable
    private lateinit var end: AnimatedVectorDrawable

    init {
        getAttrs()
        setImageDrawable(start)
        setOnClickListener { animateAVD() }
    }

    private fun getAttrs() {
        context.withStyledAttributes(attrs, R.styleable.AvdView) {
            getDrawable(R.styleable.AvdView_src_start)?.let {
                start = it.toAVD()
            }
            getDrawable(R.styleable.AvdView_src_end)?.let {
                end = it.toAVD()
            }
        }
    }

    private fun animateAVD() {
        start.registerAnimationCallback(object : Animatable2.AnimationCallback() {
            override fun onAnimationEnd(drawable: Drawable?) {
                start update end
            }
        })
        end.registerAnimationCallback(object : Animatable2.AnimationCallback() {
            override fun onAnimationEnd(drawable: Drawable?) {
                end update start
            }
        })
        drawable.toAVD().start()
    }

    private infix fun AnimatedVectorDrawable.update(newAVD: AnimatedVectorDrawable) {
        setImageDrawable(newAVD)
        reset()
    }

}

private fun Drawable.toAVD(): AnimatedVectorDrawable {
    return (this as AnimatedVectorDrawable)
}