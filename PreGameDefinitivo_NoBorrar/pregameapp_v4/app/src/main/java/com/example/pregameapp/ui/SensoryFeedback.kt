package com.example.pregameapp.ui

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import androidx.compose.animation.core.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.graphicsLayer

/**
 * Utilidad para manejar la vibración del dispositivo de forma sencilla.
 */
object SensoryFeedback {
    
    fun vibrateSuccess(context: Context) {
        val vibrator = getVibrator(context)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator.vibrate(VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE))
        } else {
            @Suppress("DEPRECATION")
            vibrator.vibrate(50)
        }
    }

    fun vibrateError(context: Context) {
        val vibrator = getVibrator(context)
        val pattern = longArrayOf(0, 50, 50, 50)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator.vibrate(VibrationEffect.createWaveform(pattern, -1))
        } else {
            @Suppress("DEPRECATION")
            vibrator.vibrate(pattern, -1)
        }
    }

    fun vibrateSelection(context: Context) {
        val vibrator = getVibrator(context)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator.vibrate(VibrationEffect.createOneShot(10, 100))
        } else {
            @Suppress("DEPRECATION")
            vibrator.vibrate(10)
        }
    }

    private fun getVibrator(context: Context): Vibrator {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val vibratorManager = context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
            vibratorManager.defaultVibrator
        } else {
            @Suppress("DEPRECATION")
            context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        }
    }
}

/**
 * Modificador para añadir un efecto de "sacudida" (shake) visual.
 */
fun Modifier.shake(enabled: Boolean): Modifier = composed {
    val infiniteTransition = rememberInfiniteTransition(label = "shake")
    val translation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = if (enabled) 10f else 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(50, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "shakeTranslation"
    )

    this.graphicsLayer {
        translationX = if (enabled) translation else 0f
    }
}
