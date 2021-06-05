package com.example.vectoranimations

import android.content.Context
import android.graphics.drawable.Animatable2
import android.graphics.drawable.AnimatedVectorDrawable
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.AttributeSet
import androidx.annotation.DrawableRes
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.AppCompatImageView
import com.example.vectoranimations.MathOperation.*

enum class MathOperation {
    PLUS, MINUS, DIVIDE, MULTIPLY
}

@RequiresApi(Build.VERSION_CODES.M)
class AvdMathOperationView(
    context: Context,
    private val attrs: AttributeSet
) : AppCompatImageView(context, attrs) {

    private val avd_center_to_plus = getAVD(R.drawable.avd_center_to_plus)
    private val avd_center_to_minus = getAVD(R.drawable.avd_center_to_minus)
    private val avd_center_to_multiply = getAVD(R.drawable.avd_center_to_multiply)
    private val avd_center_to_divide = getAVD(R.drawable.avd_center_to_divide)

    private val avd_plus_to_minus = getAVD(R.drawable.avd_plus_to_minus)
    private val avd_plus_to_multiply = getAVD(R.drawable.avd_plus_to_multiply)
    private val avd_plus_to_divide = getAVD(R.drawable.avd_plus_to_divide)

    private val avd_minus_to_plus = getAVD(R.drawable.avd_minus_to_plus)
    private val avd_minus_to_multiply = getAVD(R.drawable.avd_minus_to_multiply)
    private val avd_minus_to_divide = getAVD(R.drawable.avd_minus_to_divide)

    private val avd_multiply_to_plus = getAVD(R.drawable.avd_multiply_to_plus)
    private val avd_multiply_to_minus = getAVD(R.drawable.avd_multiply_to_minus)
    private val avd_multiply_to_divide = getAVD(R.drawable.avd_multiply_to_divide)

    private val avd_divide_to_plus = getAVD(R.drawable.avd_divide_to_plus)
    private val avd_divide_to_minus = getAVD(R.drawable.avd_divide_to_minus)
    private val avd_divide_to_multiply = getAVD(R.drawable.avd_divide_to_multiply)

    private var currentMathOperation: MathOperation? = null

    private var avd: AnimatedVectorDrawable? = null

    var onEndAnim: () -> Unit = {}

    init {
        setStartAVD()
    }

    fun animateTo(operation: MathOperation) {
        avd?.clearAnimationCallbacks()
        avd?.reset()
        avd = when (operation) {
            PLUS -> {
                when (currentMathOperation) {
                    PLUS -> return
                    MINUS -> avd_minus_to_plus
                    MULTIPLY -> avd_multiply_to_plus
                    DIVIDE -> avd_divide_to_plus
                    null -> avd_center_to_plus
                }
            }
            MINUS -> {
                when (currentMathOperation) {
                    PLUS -> avd_plus_to_minus
                    MINUS -> return
                    MULTIPLY -> avd_multiply_to_minus
                    DIVIDE -> avd_divide_to_minus
                    null -> avd_center_to_minus
                }
            }
            MULTIPLY -> {
                when (currentMathOperation) {
                    PLUS -> avd_plus_to_multiply
                    MINUS -> avd_minus_to_multiply
                    MULTIPLY -> return
                    DIVIDE -> avd_divide_to_multiply
                    null -> avd_center_to_multiply
                }
            }
            DIVIDE -> {
                when (currentMathOperation) {
                    PLUS -> avd_plus_to_divide
                    MINUS -> avd_minus_to_divide
                    MULTIPLY -> avd_multiply_to_divide
                    DIVIDE -> return
                    null -> avd_center_to_divide
                }
            }
        }
        avd?.let {
            setImageDrawable(it)
            it.registerAnimListener()
            it.start()
        }
    }

    private fun setStartAVD() {
        setImageDrawable(avd_center_to_plus)
    }

    private fun updateCurrentMathOperation() {
        currentMathOperation = when (drawable.constantState) {
            avd_center_to_plus.constantState,
            avd_minus_to_plus.constantState,
            avd_multiply_to_plus.constantState,
            avd_divide_to_plus.constantState,
            -> PLUS
            avd_center_to_minus.constantState,
            avd_plus_to_minus.constantState,
            avd_multiply_to_minus.constantState,
            avd_divide_to_minus.constantState,
            -> MINUS
            avd_center_to_multiply.constantState,
            avd_plus_to_multiply.constantState,
            avd_minus_to_multiply.constantState,
            avd_divide_to_multiply.constantState,
            -> MULTIPLY
            avd_center_to_divide.constantState,
            avd_plus_to_divide.constantState,
            avd_minus_to_divide.constantState,
            avd_multiply_to_divide.constantState,
            -> DIVIDE
            else -> return
        }
    }

    private fun getAVD(@DrawableRes id: Int): AnimatedVectorDrawable {
        return resources.getDrawable(id).toAVD()
    }

    private fun AnimatedVectorDrawable.registerAnimListener() {
        registerAnimationCallback(object : Animatable2.AnimationCallback() {
            override fun onAnimationStart(drawable: Drawable?) {
                // TODO: 05.06.21
            }

            override fun onAnimationEnd(drawable: Drawable?) {
                updateCurrentMathOperation()
                onEndAnim()
            }
        })
    }

    private fun Drawable.toAVD(): AnimatedVectorDrawable {
        return (this as AnimatedVectorDrawable)
    }

}