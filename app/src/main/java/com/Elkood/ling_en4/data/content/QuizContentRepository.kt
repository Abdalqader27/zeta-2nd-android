package com.Elkood.ling_en4.data.content

import android.content.Context
import com.Elkood.ling_en4.data.model.Question
import com.Elkood.ling_en4.data.model.QuizTopic
import com.Elkood.ling_en4.Views.SecondYear.English_4.Important_quiz.Abbrevationss.QuizDbHelper_Abbervsions
import com.Elkood.ling_en4.Views.SecondYear.English_4.Important_quiz.Compound_Nouns.QuizDbHelper_Comp
import com.Elkood.ling_en4.Views.SecondYear.English_4.Important_quiz.Extinsions.QuizDbHelper_Extinsions
import com.Elkood.ling_en4.Views.SecondYear.English_4.Header_Elements.Eng3.QuizDbHelper_Eng3
import com.Elkood.ling_en4.Views.SecondYear.English_4.Full_Quizes.QuizDbHelper_Full_Quiz

/**
 * Single source of quiz content for the Compose screen. Vocabulary is served from the
 * in-memory [En4Vocabulary] list migrated in Phase 0-1; the other five topics are read at
 * runtime from the untouched legacy SQLite [QuizDbHelper] classes and mapped into [Question].
 *
 * Legacy rows use a 1-based answer; [Question.correctIndex] is 0-based, hence `getAnswer() - 1`.
 * ENG3 rows may contain empty-string options — these are preserved (list size stays 4) and
 * hidden at render time so the answer index stays aligned.
 */
object QuizContentRepository {

    fun questionsFor(topic: QuizTopic, context: Context): List<Question> = when (topic) {
        QuizTopic.VOCABULARY -> En4Vocabulary.questions

        QuizTopic.ABBREVIATIONS -> QuizDbHelper_Abbervsions(context).getAllQustion().map { row ->
            Question(
                prompt = row.getQution(),
                options = listOf(row.getOption1(), row.getOption2(), row.getOption3(), row.getOption4()),
                correctIndex = row.getAnswer() - 1,
            )
        }

        QuizTopic.COMPOUND_NOUNS -> QuizDbHelper_Comp(context).getAllQustion().map { row ->
            Question(
                prompt = row.getQution(),
                options = listOf(row.getOption1(), row.getOption2(), row.getOption3(), row.getOption4()),
                correctIndex = row.getAnswer() - 1,
            )
        }

        QuizTopic.EXTENSIONS -> QuizDbHelper_Extinsions(context).getAllQustion().map { row ->
            Question(
                prompt = row.getQution(),
                options = listOf(row.getOption1(), row.getOption2(), row.getOption3(), row.getOption4()),
                correctIndex = row.getAnswer() - 1,
            )
        }

        QuizTopic.ENG3 -> QuizDbHelper_Eng3(context).getAllQustion().map { row ->
            Question(
                prompt = row.getQution(),
                options = listOf(row.getOption1(), row.getOption2(), row.getOption3(), row.getOption4()),
                correctIndex = row.getAnswer() - 1,
            )
        }

        QuizTopic.FULL_QUIZ -> QuizDbHelper_Full_Quiz(context).getAllQustion().map { row ->
            Question(
                prompt = row.getQution(),
                options = listOf(row.getOption1(), row.getOption2(), row.getOption3(), row.getOption4()),
                correctIndex = row.getAnswer() - 1,
            )
        }
    }
}
