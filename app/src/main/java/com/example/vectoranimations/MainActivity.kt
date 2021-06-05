package com.example.vectoranimations

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.vectoranimations.MathOperation.*
import com.example.vectoranimations.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var clickedBtnOp: ButtonMathOperationView? = null

        binding.btnMathOpPlus.onClickListener = { btnPlus ->
            if (clickedBtnOp != btnPlus) {
                btnPlus.startAnim()
                clickedBtnOp?.endAnim()

                binding.avdMathOp.apply {
                    onEndAnim = { btnPlus.endAnim() }
                }.animateTo(PLUS)

                clickedBtnOp = btnPlus
            }
        }

        binding.btnMathOpMinus.onClickListener = { btnMinus ->
            if (clickedBtnOp != btnMinus) {
                btnMinus.startAnim()
                clickedBtnOp?.endAnim()

                binding.avdMathOp.apply {
                    onEndAnim = { btnMinus.endAnim() }
                }.animateTo(MINUS)

                clickedBtnOp = btnMinus
            }
        }

        binding.btnMathOpMultiply.onClickListener = { btnMultiply ->
            if (clickedBtnOp != btnMultiply) {
                btnMultiply.startAnim()
                clickedBtnOp?.endAnim()

                binding.avdMathOp.apply {
                    onEndAnim = { btnMultiply.endAnim() }
                }.animateTo(MULTIPLY)

                clickedBtnOp = btnMultiply
            }
        }

        binding.btnMathOpDivide.onClickListener = { btnDivide ->
            if (clickedBtnOp != btnDivide) {
                btnDivide.startAnim()
                clickedBtnOp?.endAnim()

                binding.avdMathOp.apply {
                    onEndAnim = { btnDivide.endAnim() }
                }.animateTo(DIVIDE)

                clickedBtnOp = btnDivide
            }
        }

    }
}

fun printVlad(it: Any) {
    Log.i("vlad", "$it")
}