package com.Elkood.ling_en4.ui.screens.quiz

import android.content.Context
import android.media.MediaPlayer
import com.Elkood.ling_en4.R

class QuizSound(
    private val context: Context,
    private val gated: Boolean = true,
) {

    private var player: MediaPlayer? = null

    private fun soundEnabled(): Boolean {
        if (!gated) return true
        return context.getSharedPreferences("saveData", Context.MODE_PRIVATE)
            .getBoolean("switch1", true)
    }

    private fun play(resId: Int) {
        if (!soundEnabled()) return
        player?.release()
        player = MediaPlayer.create(context, resId).also {
            it.setOnCompletionListener { mp -> mp.release() }
            it.start()
        }
    }

    fun playCorrect() = play(R.raw.correct)
    fun playWrong() = play(R.raw.wrong)

    fun release() {
        player?.release()
        player = null
    }
}
