package com.example.vectoranimations

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.core.content.withStyledAttributes

class ButtonMathOperationView(
    context: Context,
    private val attrs: AttributeSet
) : FrameLayout(context, attrs) {

    private var icon: Drawable? = null

    private lateinit var layer0: View
    private lateinit var layer1: View
    private lateinit var layer2: View

    private val rotate = AnimatorInflater.loadAnimator(context, R.animator.rotate)
    private val scale = AnimatorInflater.loadAnimator(context, R.animator.scale)

    private val set = AnimatorSet()

    var onClickListener: (ButtonMathOperationView) -> Unit = {}

    init {
        getAttrs()
        setUpViews()
        setUpAnims()
        setUpClickListener()
        addViews()
    }

    fun startAnim() {
        set.apply { playTogether(rotate, scale) }.start()
    }

    fun endAnim() {
        set.cancel()
    }

    private fun getAttrs() {
        context.withStyledAttributes(attrs, R.styleable.MathOperationView) {
            getDrawable(R.styleable.MathOperationView_icon)?.let {
                icon = it
            }
        }
    }

    private fun setUpViews() {
        layer0 = buildLayer0()
        layer1 = buildLayer1()
        layer2 = buildLayer2()
    }

    private fun setUpAnims() {
        rotate.setTarget(layer1)
        scale.setTarget(layer2)
    }

    private fun buildLayer0(): View {
        return View(context).apply { setBackgroundResource(R.drawable.solid) }
    }

    private fun buildLayer1(): View {
        return View(context).apply { setBackgroundResource(R.drawable.stroke) }
    }

    private fun buildLayer2(): View {
        return ImageView(context).apply {
            setImageDrawable(icon)
            setPadding(30, 30, 30, 30)
        }
    }

    private fun addViews() {
        addView(layer0)
        addView(layer1)
        addView(layer2)
    }

    private fun setUpClickListener() {
        setOnClickListener { onClickListener(this) }
    }

}