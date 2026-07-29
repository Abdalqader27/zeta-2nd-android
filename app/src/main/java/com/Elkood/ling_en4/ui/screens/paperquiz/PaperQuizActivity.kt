package com.Elkood.ling_en4.ui.screens.paperquiz

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.Elkood.ling_en4.data.content.PaperRepository
import com.Elkood.ling_en4.data.model.ExamPaper
import com.Elkood.ling_en4.ui.theme.ZetaTheme

class PaperQuizActivity : ComponentActivity() {

    companion object {
        const val EXTRA_PAPER = "extra_paper"

        fun start(context: Context, examPaper: ExamPaper) {
            context.startActivity(
                Intent(context, PaperQuizActivity::class.java)
                    .putExtra(EXTRA_PAPER, examPaper.name)
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val examPaper = runCatching {
            ExamPaper.valueOf(intent.getStringExtra(EXTRA_PAPER) ?: ExamPaper.R2018.name)
        }.getOrElse { ExamPaper.R2018 }

        val paper = PaperRepository.loadPaper(this, examPaper)

        setContent {
            ZetaTheme {
                PaperQuizScreen(paper = paper, onBack = { finish() })
            }
        }
    }
}
