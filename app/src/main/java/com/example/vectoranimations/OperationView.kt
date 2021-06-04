package com.example.vectoranimations

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView

class OperationView(
    context: Context,
    attrs: AttributeSet
) : FrameLayout(context, attrs) {

    private val layer0 = buildLayer0()
    private val layer1 = buildLayer1()
    private val layer2 = buildLayer2()


    init {
        addViews()

        val rotation = AnimatorInflater.loadAnimator(context, R.animator.rotation)
        val scale = AnimatorInflater.loadAnimator(context, R.animator.scale)

        rotation.setTarget(layer1)
        scale.setTarget(layer2)

        setOnClickListener {
            rotation.start()
            scale.start()
        }
    }

    private fun addViews() {
        addView(layer0)
        addView(layer1)
        addView(layer2)
    }

    private fun buildLayer0(): View {
        return View(context).apply { setBackgroundResource(R.drawable.solid) }
    }

    private fun buildLayer1(): View {
        return View(context).apply { setBackgroundResource(R.drawable.stroke) }
    }

    private fun buildLayer2(): View {
        return ImageView(context).apply {
            setImageResource(R.drawable.ic_plus_24)
            setPadding(30, 30, 30, 30)
        }
    }

}


fun printVlad(it: Any) {
    Log.i("vlad", "$it")
}