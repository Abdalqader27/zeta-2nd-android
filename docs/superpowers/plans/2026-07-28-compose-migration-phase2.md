# Compose Migration Phase 2 — Remaining Four-Option Quiz Topics Implementation Plan

> **For agentic workers:** REQUIRED SUB-SKILL: Use superpowers:subagent-driven-development (recommended) or superpowers:executing-plans to implement this plan task-by-task. Steps use checkbox (`- [ ]`) syntax for tracking.

**Goal:** Migrate the five remaining four-option quiz variants (Abbreviations, Compound Nouns, Extensions, ENG3, Full Quiz) onto the existing Compose `InteractiveQuizScreen`, reading their content from the legacy SQLite `QuizDbHelper_*` sources at runtime, and repoint every launch site to `ComposeQuizActivity`.

**Architecture:** Strangler-fig continuation of Phase 0-1. A new thin `QuizContentRepository` reads questions from the untouched legacy `QuizDbHelper_*` Java classes and maps each legacy row (1-based `getAnswer()`) to the Compose `Question` model (0-based `correctIndex`). `QuizConfig` gains per-topic behavior flags (fixed vs. `saveR` timer, sound gating, result mode, streak bar, play-count, Parse upload) so all six topics share one `ComposeQuizActivity` + `QuizViewModel` + `InteractiveQuizScreen`. The legacy Java quiz Activities and DbHelpers are NOT deleted — they remain the data source and are simply no longer launched.

**Tech Stack:** Kotlin 2.0.21, Jetpack Compose (compose-bom 2024.09.00), Material3, androidx.lifecycle 2.8.6, Android SQLite (`SQLiteOpenHelper` via legacy `QuizDbHelper_*`), Parse-SDK-Android 1.21.0 (already a dependency), JUnit 4.

## Global Constraints

- **Package name is `com.Elkood.ling_en4`** — capital `E`, capital `K`. Every new file lives under it. Legacy Java lives under `com.Elkood.ling_en4.Views.SecondYear.English_4.*`.
- **Build command:** always prefix Gradle with the JDK path and run from repo root: `JAVA_HOME=/Users/abdalqaderalnajjar/Library/Java/JavaVirtualMachines/azul-17.0.8.1/Contents/Home ./gradlew <task>`.
- **Java/Kotlin source & target compatibility = 11; `jvmTarget = "11"`.** Do not use language features above Java 11 / Kotlin 2.0.
- **App is RTL Arabic globally.** LTR is scoped ONLY to quiz content, already done inside `InteractiveQuizScreen` via `CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr)`. Do not add any app-wide layout-direction change.
- **Corrected naming is mandatory** in all new Kotlin: `prompt` (not `qution`), `correctIndex` 0-based (not 1-based `Answer`), `Question` (not `Qutions`), `options` (not `Option1..4`). Legacy Java getter names (`getQution`, `getOption1`, `getAnswer`, `getAllQustion`) are called verbatim because the Java classes are untouched — the correction happens in the mapping boundary only.
- **`passThreshold = questions.size` for every topic** (a quiz is "passed" only when perfect), consistent with Vocabulary's shipped behavior. The `passed` flag drives no UI in Compose; it is state only.
- **`correctIndex = getAnswer() - 1`** for every legacy row (legacy answers are 1-based; `Question` is 0-based).
- **`Question` invariant is unchanged**: exactly 4 options, `correctIndex in 0..3`. ENG3 rows legitimately contain empty-string options; those stay as `""` in the list (size still 4) and are hidden at render time — never removed from the list, so `correctIndex` alignment is preserved.
- **Secrets:** the Back4App credentials live in `res/values/strings.xml` (`back4app_app_id`, `back4app_client_key`, `back4app_server_url`) and are referenced only via `getString(R.string.…)`. Never inline, echo, or copy their literal values anywhere.
- **Shuffle stays ON for all topics** (the shipped Vocabulary screen shuffles via `QuizViewModel`'s default `shuffle = { it.shuffled() }`); this is the intentional consistency choice for Phase 2.
- **True/False is explicitly OUT OF SCOPE** (a different two-choice screen, deferred to a later phase). `BankItemsQuiz` index 1 (`QuizActivity_True_false`) is left untouched.
- **Legacy classes are NOT deleted.** No `QuizActivity_*` or `QuizDbHelper_*` or `Qutions_*` Java file is removed or edited in this phase, except the three launch-site files (`BankItemsQuiz`, `Home_fragment`, `old_version`) whose Intent targets are repointed.

---

## File Structure

**New files:**
- `app/src/main/java/com/Elkood/ling_en4/data/content/QuizContentRepository.kt` — reads the 5 legacy SQLite sources + Vocabulary static list, maps each to `List<Question>`.

**Modified files:**
- `app/src/main/java/com/Elkood/ling_en4/data/QuizConfig.kt` — add `ResultMode` enum + 6 behavior fields to `QuizConfig`; rewrite `quizConfigFor` to take a `Context` and wire all 6 topics via the repository.
- `app/src/main/java/com/Elkood/ling_en4/ui/screens/quiz/QuizSound.kt` — add a `gated` flag so ENG3 can bypass the `switch1` preference.
- `app/src/main/java/com/Elkood/ling_en4/ui/screens/quiz/QuizUiState.kt` — add `streak` field.
- `app/src/main/java/com/Elkood/ling_en4/ui/screens/quiz/QuizViewModel.kt` — track consecutive-correct `streak`.
- `app/src/test/java/com/Elkood/ling_en4/ui/screens/quiz/QuizViewModelTest.kt` — add streak tests.
- `app/src/main/java/com/Elkood/ling_en4/ui/screens/quiz/InteractiveQuizScreen.kt` — hide empty-string options, add optional streak `LinearProgressIndicator` + 10-streak Toast, config-driven sound gating.
- `app/src/main/java/com/Elkood/ling_en4/ui/screens/quiz/ComposeQuizActivity.kt` — fixed-timer override, result mode (Intent return for ENG3), `numberPlay` increment, Parse upload (Full Quiz), pass sound/streak flags to the screen.
- `app/src/main/java/com/Elkood/ling_en4/Views/SecondYear/English_4/BankItemsQuiz.java` — repoint indices 2/3/4.
- `app/src/main/java/com/Elkood/ling_en4/Views/SecondYear/English_4/Home/Home_fragment.java` — repoint Full Quiz launch.
- `app/src/main/java/com/Elkood/ling_en4/Views/SecondYear/English_4/Header_Elements/old_version.java` — repoint ENG3 launch (keep `startActivityForResult`).

---

### Task 1: QuizContentRepository (legacy SQLite → Question mapping)

**Files:**
- Create: `app/src/main/java/com/Elkood/ling_en4/data/content/QuizContentRepository.kt`

**Interfaces:**
- Consumes: legacy Java `QuizDbHelper_*(Context)` classes and their `getAllQustion()` lists; the model getters `getQution(): String`, `getOption1()..getOption4(): String`, `getAnswer(): Int` (1-based); the existing `En4Vocabulary.questions: List<Question>`.
- Produces: `object QuizContentRepository { fun questionsFor(topic: QuizTopic, context: Context): List<Question> }` — used by `quizConfigFor` (Task 2).

**Context:** The five model types (`Qutions_Abberv`, `Qutions_Comp`, `Qutions_Extinsoins`, `Qutions` for ENG3, `Qutions_Full`) are unrelated classes with identical getter names, so each topic needs its own mapping branch. Reading happens on the calling thread (the Activity's `onCreate`), exactly as the legacy Activities did.

- [ ] **Step 1: Create the repository file**

```kotlin
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
```

- [ ] **Step 2: Compile to verify the file is well-formed and every legacy import resolves**

Run: `JAVA_HOME=/Users/abdalqaderalnajjar/Library/Java/JavaVirtualMachines/azul-17.0.8.1/Contents/Home ./gradlew :app:compileDebugKotlin`
Expected: BUILD SUCCESSFUL. (This proves the DbHelper/model class names, package paths, and getter names all resolve. There is no JVM unit test for this file — SQLite reads require an Android runtime and are verified on device in Task 7.)

- [ ] **Step 3: Commit**

```bash
git add app/src/main/java/com/Elkood/ling_en4/data/content/QuizContentRepository.kt
git commit -m "feat: add QuizContentRepository reading legacy SQLite into Question"
```

---

### Task 2: Extend QuizConfig with per-topic behavior and wire all six topics

**Files:**
- Modify: `app/src/main/java/com/Elkood/ling_en4/data/QuizConfig.kt`
- Modify: `app/src/main/java/com/Elkood/ling_en4/ui/screens/quiz/ComposeQuizActivity.kt:27` (call-site only — `quizConfigFor(topic)` → `quizConfigFor(topic, this)`)

**Interfaces:**
- Consumes: `QuizContentRepository.questionsFor(topic, context)` (Task 1).
- Produces: extended `data class QuizConfig(...)` with fields `fixedSeconds: Int?`, `soundGated: Boolean`, `resultMode: ResultMode`, `showStreakBar: Boolean`, `incrementPlayCount: Boolean`, `uploadToParse: Boolean`; `enum class ResultMode { HIGH_SCORE_PREF, INTENT_RESULT }`; `fun quizConfigFor(topic: QuizTopic, context: Context): QuizConfig`. Consumed by `ComposeQuizActivity` (Task 6) and the task reviewer.

**Context:** New fields all carry defaults matching the current Vocabulary behavior, so Vocabulary's config stays behaviorally identical. The signature change (adding `Context`) forces the one-line call-site update in `ComposeQuizActivity`; making that edit here keeps the build green.

- [ ] **Step 1: Replace the entire contents of `QuizConfig.kt`**

```kotlin
package com.Elkood.ling_en4.data

import android.content.Context
import com.Elkood.ling_en4.data.content.QuizContentRepository
import com.Elkood.ling_en4.data.model.QuizTopic

/** How the quiz reports its result when it finishes. */
enum class ResultMode {
    /** Persist a per-topic high score in SharedPreferences (Vocabulary/Abberv/Comp/Ext/Full). */
    HIGH_SCORE_PREF,

    /** Return the score to the launching Activity via setResult (ENG3). */
    INTENT_RESULT,
}

data class QuizConfig(
    val topic: QuizTopic,
    val title: String,
    val questions: List<Question>,
    val passThreshold: Int,
    /** High-score store — used only when [resultMode] == HIGH_SCORE_PREF. */
    val highScorePrefFile: String,
    val highScorePrefKey: String,
    /** Fixed countdown in seconds; when null the activity uses the saveR preference mapping. */
    val fixedSeconds: Int? = null,
    /** When false, correct/wrong sounds always play regardless of the switch1 preference (ENG3). */
    val soundGated: Boolean = true,
    val resultMode: ResultMode = ResultMode.HIGH_SCORE_PREF,
    /** Show the consecutive-correct streak bar (Full Quiz). */
    val showStreakBar: Boolean = false,
    /** Increment the "numberPlay" counter in SaveScore on launch (Full Quiz). */
    val incrementPlayCount: Boolean = false,
    /** Upload a new high score to the Back4App "Quiz" leaderboard (Full Quiz). */
    val uploadToParse: Boolean = false,
)

// NOTE: Question is imported transitively via QuizContentRepository's return type; the explicit
// import below keeps the `questions` field type resolvable in this file.
import com.Elkood.ling_en4.data.model.Question

fun quizConfigFor(topic: QuizTopic, context: Context): QuizConfig {
    val questions = QuizContentRepository.questionsFor(topic, context)
    return when (topic) {
        QuizTopic.VOCABULARY -> QuizConfig(
            topic = topic,
            title = "Vocabulary",
            questions = questions,
            passThreshold = questions.size,
            highScorePrefFile = "SaveScore",
            highScorePrefKey = "VocHighScore",
        )

        QuizTopic.ABBREVIATIONS -> QuizConfig(
            topic = topic,
            title = "Abbreviations",
            questions = questions,
            passThreshold = questions.size,
            highScorePrefFile = "SaveScore",
            highScorePrefKey = "AbberHighScore",
        )

        QuizTopic.COMPOUND_NOUNS -> QuizConfig(
            topic = topic,
            title = "Compound Nouns",
            questions = questions,
            passThreshold = questions.size,
            highScorePrefFile = "SaveScore",
            highScorePrefKey = "CompHighScore",
        )

        QuizTopic.EXTENSIONS -> QuizConfig(
            topic = topic,
            title = "Extensions",
            questions = questions,
            passThreshold = questions.size,
            highScorePrefFile = "SaveScore",
            highScorePrefKey = "ExtHighScore",
        )

        QuizTopic.ENG3 -> QuizConfig(
            topic = topic,
            title = "ENG3",
            questions = questions,
            passThreshold = questions.size,
            highScorePrefFile = "",           // unused: ENG3 returns its score to the caller
            highScorePrefKey = "",
            fixedSeconds = 40,
            soundGated = false,
            resultMode = ResultMode.INTENT_RESULT,
        )

        QuizTopic.FULL_QUIZ -> QuizConfig(
            topic = topic,
            title = "Full Quiz",
            questions = questions,
            passThreshold = questions.size,
            highScorePrefFile = "SaveScore",
            highScorePrefKey = "fullscore",
            showStreakBar = true,
            incrementPlayCount = true,
            uploadToParse = true,
        )
    }
}
```

> Note on the import placement: Kotlin requires all imports at the top of the file. When writing this file, move `import com.Elkood.ling_en4.data.model.Question` up beside the other imports (right after the `package` line block) rather than mid-file as shown above — the mid-file comment is only to flag that `Question` must be imported. Final import block order:
> ```kotlin
> import android.content.Context
> import com.Elkood.ling_en4.data.content.QuizContentRepository
> import com.Elkood.ling_en4.data.model.Question
> import com.Elkood.ling_en4.data.model.QuizTopic
> ```

- [ ] **Step 2: Update the `ComposeQuizActivity` call site (line ~27)**

Change:
```kotlin
        val config = quizConfigFor(topic)
```
to:
```kotlin
        val config = quizConfigFor(topic, this)
```

- [ ] **Step 3: Compile**

Run: `JAVA_HOME=/Users/abdalqaderalnajjar/Library/Java/JavaVirtualMachines/azul-17.0.8.1/Contents/Home ./gradlew :app:compileDebugKotlin`
Expected: BUILD SUCCESSFUL.

- [ ] **Step 4: Run the existing ViewModel unit tests to confirm no regression**

Run: `JAVA_HOME=/Users/abdalqaderalnajjar/Library/Java/JavaVirtualMachines/azul-17.0.8.1/Contents/Home ./gradlew :app:testDebugUnitTest`
Expected: BUILD SUCCESSFUL, all `QuizViewModelTest` tests pass (the test constructs `QuizConfig` positionally for the required fields; new fields have defaults, so it still compiles and passes).

- [ ] **Step 5: Commit**

```bash
git add app/src/main/java/com/Elkood/ling_en4/data/QuizConfig.kt app/src/main/java/com/Elkood/ling_en4/ui/screens/quiz/ComposeQuizActivity.kt
git commit -m "feat: extend QuizConfig with per-topic behavior and wire all six topics"
```

---

### Task 3: Optional sound gating in QuizSound

**Files:**
- Modify: `app/src/main/java/com/Elkood/ling_en4/ui/screens/quiz/QuizSound.kt`

**Interfaces:**
- Produces: `class QuizSound(context: Context, gated: Boolean = true)` — when `gated` is false, `playCorrect()`/`playWrong()` always play; when true, they respect the existing `saveData`/`switch1` preference (default true). Consumed by `InteractiveQuizScreen` (Task 5).

**Context:** ENG3's legacy Activity plays sounds unconditionally; all other topics gate on `switch1`. The default `gated = true` preserves current Vocabulary behavior.

- [ ] **Step 1: Replace the class with the gated variant**

```kotlin
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
```

- [ ] **Step 2: Compile**

Run: `JAVA_HOME=/Users/abdalqaderalnajjar/Library/Java/JavaVirtualMachines/azul-17.0.8.1/Contents/Home ./gradlew :app:compileDebugKotlin`
Expected: BUILD SUCCESSFUL.

- [ ] **Step 3: Commit**

```bash
git add app/src/main/java/com/Elkood/ling_en4/ui/screens/quiz/QuizSound.kt
git commit -m "feat: add optional sound gating to QuizSound for ENG3"
```

---

### Task 4: Track consecutive-correct streak in the ViewModel

**Files:**
- Modify: `app/src/main/java/com/Elkood/ling_en4/ui/screens/quiz/QuizUiState.kt`
- Modify: `app/src/main/java/com/Elkood/ling_en4/ui/screens/quiz/QuizViewModel.kt`
- Test: `app/src/test/java/com/Elkood/ling_en4/ui/screens/quiz/QuizViewModelTest.kt`

**Interfaces:**
- Produces: `QuizUiState.streak: Int` (running count of consecutive correct answers, reset to 0 on any wrong/expired answer, carried across questions). Consumed by `InteractiveQuizScreen` (Task 5).

**Context:** The Full Quiz shows a 10-segment streak progress bar and toasts at 10 consecutive correct. Streak increments on a correct `confirm()` or a correct-at-expiry, resets on wrong. It persists across `next()` (it is a running streak, not per-question).

- [ ] **Step 1: Write the failing tests (append to `QuizViewModelTest.kt`, before the closing brace)**

```kotlin
    @Test
    fun streak_increments_on_consecutive_correct() {
        val vm = vm()
        assertEquals(0, vm.uiState.value.streak)
        vm.selectOption(0); vm.confirm()
        assertEquals(1, vm.uiState.value.streak)
        vm.next()
        vm.selectOption(1); vm.confirm()
        assertEquals(2, vm.uiState.value.streak)
    }

    @Test
    fun streak_resets_on_wrong_answer() {
        val vm = vm()
        vm.selectOption(0); vm.confirm()          // correct -> streak 1
        assertEquals(1, vm.uiState.value.streak)
        vm.next()
        vm.selectOption(0); vm.confirm()          // q2 correct is index 1, so this is wrong
        assertEquals(0, vm.uiState.value.streak)
    }
```

- [ ] **Step 2: Run the new tests to verify they fail**

Run: `JAVA_HOME=/Users/abdalqaderalnajjar/Library/Java/JavaVirtualMachines/azul-17.0.8.1/Contents/Home ./gradlew :app:testDebugUnitTest --tests "com.Elkood.ling_en4.ui.screens.quiz.QuizViewModelTest"`
Expected: FAIL — compilation error ("unresolved reference: streak") because `QuizUiState` has no `streak` field yet.

- [ ] **Step 3: Add the `streak` field to `QuizUiState`**

In `QuizUiState.kt`, add the field (with a default so no other construction site breaks):

```kotlin
data class QuizUiState(
    val questionNumber: Int,
    val totalQuestions: Int,
    val prompt: String,
    val options: List<String>,
    val selectedIndex: Int?,
    val correctIndex: Int?,
    val phase: AnswerPhase,
    val score: Int,
    val secondsLeft: Int,
    val isLastQuestion: Boolean,
    val finished: Boolean,
    val passed: Boolean,
    val streak: Int = 0,
)
```

- [ ] **Step 4: Track `streak` in `QuizViewModel`**

Add the field, populate it in `buildState`, and update `confirm`, `onTimeExpired`, and `reveal`:

Add near `private var score = 0`:
```kotlin
    private var streak = 0
```

In `buildState(...)`, add `streak = streak,` to the returned `QuizUiState` (e.g. after `passed = false,`).

Replace `confirm()`:
```kotlin
    /** Grades the current question, reveals the answer. */
    fun confirm(): Boolean {
        if (_uiState.value.phase != AnswerPhase.ANSWERING) return false
        val correct = _uiState.value.selectedIndex == current.correctIndex
        if (correct) {
            score++
            streak++
        } else {
            streak = 0
        }
        reveal()
        return correct
    }
```

Replace `onTimeExpired()`:
```kotlin
    /** Called when the countdown reaches zero with no confirm. */
    fun onTimeExpired() {
        if (_uiState.value.phase != AnswerPhase.ANSWERING) return
        val correct = _uiState.value.selectedIndex == current.correctIndex
        if (correct) {
            score++
            streak++
        } else {
            streak = 0
        }
        reveal()
    }
```

Replace `reveal()` to carry the streak:
```kotlin
    private fun reveal() {
        _uiState.value = _uiState.value.copy(
            phase = AnswerPhase.REVEALED,
            correctIndex = current.correctIndex,
            score = score,
            streak = streak,
        )
    }
```

- [ ] **Step 5: Run all ViewModel tests to verify they pass**

Run: `JAVA_HOME=/Users/abdalqaderalnajjar/Library/Java/JavaVirtualMachines/azul-17.0.8.1/Contents/Home ./gradlew :app:testDebugUnitTest --tests "com.Elkood.ling_en4.ui.screens.quiz.QuizViewModelTest"`
Expected: PASS — all tests including the two new streak tests. (`time_expiry_reveals_without_scoring` still passes: no selection means `selectedIndex == correctIndex` is false, so `streak = 0` and score unchanged.)

- [ ] **Step 6: Commit**

```bash
git add app/src/main/java/com/Elkood/ling_en4/ui/screens/quiz/QuizUiState.kt app/src/main/java/com/Elkood/ling_en4/ui/screens/quiz/QuizViewModel.kt app/src/test/java/com/Elkood/ling_en4/ui/screens/quiz/QuizViewModelTest.kt
git commit -m "feat: track consecutive-correct streak in QuizViewModel"
```

---

### Task 5: Screen support — hide empty options, streak bar, config-driven sound

**Files:**
- Modify: `app/src/main/java/com/Elkood/ling_en4/ui/screens/quiz/InteractiveQuizScreen.kt`

**Interfaces:**
- Consumes: `QuizUiState.streak` (Task 4); `QuizSound(context, gated)` (Task 3).
- Produces: `InteractiveQuizScreen(viewModel, onFinished, soundGated: Boolean = true, showStreakBar: Boolean = false)`. Consumed by `ComposeQuizActivity` (Task 6).

**Context:** ENG3 rows carry empty-string options that must not render (the legacy screen set those radios to `GONE`); the option index must stay aligned with `correctIndex`, so empty options are skipped at render, never removed from the list. Full Quiz shows a 10-segment streak bar and toasts once at exactly 10 consecutive correct (Arabic: "أحسنت 10 إجابات صحيحة متتالية"), matching the legacy `tempBar == 10` toast.

- [ ] **Step 1: Add the new imports** at the top of `InteractiveQuizScreen.kt` (with the other imports):

```kotlin
import android.widget.Toast
import androidx.compose.material3.LinearProgressIndicator
```

- [ ] **Step 2: Add the two parameters to the composable signature**

```kotlin
@Composable
fun InteractiveQuizScreen(
    viewModel: QuizViewModel,
    onFinished: (score: Int, passed: Boolean) -> Unit,
    soundGated: Boolean = true,
    showStreakBar: Boolean = false,
) {
```

- [ ] **Step 3: Make the sound respect `soundGated`**

Change the sound construction line from:
```kotlin
    val sound = rememberQuizSound(context)
```
to:
```kotlin
    val sound = rememberQuizSound(context, soundGated)
```

And update `rememberQuizSound` at the bottom of the file:
```kotlin
@Composable
private fun rememberQuizSound(context: android.content.Context, gated: Boolean): QuizSound {
    val sound = remember(gated) { QuizSound(context, gated) }
    DisposableEffect(Unit) { onDispose { sound.release() } }
    return sound
}
```

- [ ] **Step 4: Add the 10-streak toast effect** — place this right after the existing terminal-navigation `LaunchedEffect(state.finished) { ... }` block:

```kotlin
    // Full Quiz: celebrate a 10-in-a-row streak once, matching the legacy tempBar == 10 toast.
    LaunchedEffect(state.streak) {
        if (showStreakBar && state.streak == 10) {
            Toast.makeText(context, "أحسنت 10 إجابات صحيحة متتالية", Toast.LENGTH_SHORT).show()
        }
    }
```

- [ ] **Step 5: Render the streak bar** — inside the scrolling `Column`, immediately after `QuizHeaderRow(state)` and before the "Check the correct answer :" `Text`:

```kotlin
                if (showStreakBar) {
                    LinearProgressIndicator(
                        progress = { (state.streak.coerceAtMost(10)) / 10f },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp, vertical = 4.dp),
                    )
                }
```

- [ ] **Step 6: Hide empty-string options** — in `OptionsGroup`, skip empty options while preserving the index. Change the `forEachIndexed` body so the first line is a guard:

```kotlin
        state.options.forEachIndexed { i, option ->
            if (option.isEmpty()) return@forEachIndexed
            val color = when {
                state.phase == AnswerPhase.REVEALED && i == state.correctIndex -> ZetaColors.Correct
                state.phase == AnswerPhase.REVEALED -> ZetaColors.WrongText
                else -> ZetaColors.OptionText
            }
            // ... unchanged Row/RadioButton/Text ...
        }
```

- [ ] **Step 7: Compile**

Run: `JAVA_HOME=/Users/abdalqaderalnajjar/Library/Java/JavaVirtualMachines/azul-17.0.8.1/Contents/Home ./gradlew :app:compileDebugKotlin`
Expected: BUILD SUCCESSFUL. (`LinearProgressIndicator(progress = { ... })` is the Material3 1.3.x lambda-progress signature shipped with compose-bom 2024.09.00.)

- [ ] **Step 8: Commit**

```bash
git add app/src/main/java/com/Elkood/ling_en4/ui/screens/quiz/InteractiveQuizScreen.kt
git commit -m "feat: screen support for empty-option hiding, streak bar, and sound gating"
```

---

### Task 6: ComposeQuizActivity — fixed timer, result mode, play-count, Parse upload

**Files:**
- Modify: `app/src/main/java/com/Elkood/ling_en4/ui/screens/quiz/ComposeQuizActivity.kt`

**Interfaces:**
- Consumes: `QuizConfig` fields `fixedSeconds`, `soundGated`, `resultMode`, `showStreakBar`, `incrementPlayCount`, `uploadToParse`, `highScorePrefFile`, `highScorePrefKey` (Task 2); `ResultMode` (Task 2); `InteractiveQuizScreen(..., soundGated, showStreakBar)` (Task 5).
- Produces: `ComposeQuizActivity.EXTRA_SCORE = "extrascore"` (read by `old_version` via the identically-valued `QuizActivity_Eng3.EXTRA_SCORE`).

**Context:** This host must now cover three legacy result behaviors: (a) high-score preference (Vocabulary/Abberv/Comp/Ext/Full), (b) Intent return of the score (ENG3), plus the Full-Quiz-only side-effects: incrementing `numberPlay` on launch and uploading a new high score to Back4App's "Quiz" class. Parse upload only fires on a new high score, exactly as the legacy `finishQuiz()` did (inside the `fullscore < score` guard). The Back4App credentials are read from string resources only.

- [ ] **Step 1: Replace the entire contents of `ComposeQuizActivity.kt`**

```kotlin
package com.Elkood.ling_en4.ui.screens.quiz

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.Factory
import androidx.lifecycle.ViewModel
import com.Elkood.ling_en4.R
import com.Elkood.ling_en4.data.QuizConfig
import com.Elkood.ling_en4.data.ResultMode
import com.Elkood.ling_en4.data.model.QuizTopic
import com.Elkood.ling_en4.data.quizConfigFor
import com.Elkood.ling_en4.ui.theme.ZetaTheme
import com.parse.Parse
import com.parse.ParseQuery

class ComposeQuizActivity : ComponentActivity() {

    companion object {
        const val EXTRA_TOPIC = "extra_topic"

        /** Score extra returned to the caller in INTENT_RESULT mode (ENG3). Value matches
         * the legacy QuizActivity_Eng3.EXTRA_SCORE so old_version reads it unchanged. */
        const val EXTRA_SCORE = "extrascore"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val topic = QuizTopic.valueOf(
            intent.getStringExtra(EXTRA_TOPIC) ?: QuizTopic.VOCABULARY.name
        )
        val config = quizConfigFor(topic, this)

        if (config.incrementPlayCount) incrementPlayCount()

        val seconds = countdownSeconds(config)

        val viewModel = ViewModelProvider(
            this,
            quizViewModelFactory(config, seconds),
        )[QuizViewModel::class.java]

        setContent {
            ZetaTheme {
                InteractiveQuizScreen(
                    viewModel = viewModel,
                    onFinished = { score, _ ->
                        onQuizFinished(config, score)
                        finish()
                    },
                    soundGated = config.soundGated,
                    showStreakBar = config.showStreakBar,
                )
            }
        }
    }

    /** Fixed override when configured (ENG3 = 40s); otherwise the saveR mapping:
     * 1 (default) -> 40s, 0 -> 30s, 2 -> 50s. */
    private fun countdownSeconds(config: QuizConfig): Int {
        config.fixedSeconds?.let { return it }
        return when (getSharedPreferences("saveData", Context.MODE_PRIVATE).getInt("saveR", 1)) {
            0 -> 30
            2 -> 50
            else -> 40
        }
    }

    private fun onQuizFinished(config: QuizConfig, score: Int) {
        when (config.resultMode) {
            ResultMode.INTENT_RESULT -> {
                setResult(RESULT_OK, Intent().putExtra(EXTRA_SCORE, score))
            }
            ResultMode.HIGH_SCORE_PREF -> {
                val prefs = getSharedPreferences(config.highScorePrefFile, Context.MODE_PRIVATE)
                if (prefs.getInt(config.highScorePrefKey, 0) < score) {
                    prefs.edit().putInt(config.highScorePrefKey, score).apply()
                    if (config.uploadToParse) uploadHighScore(score)
                }
            }
        }
    }

    /** Full Quiz: bump the play counter in SaveScore, mirroring the legacy onCreate. */
    private fun incrementPlayCount() {
        val prefs = getSharedPreferences("SaveScore", Context.MODE_PRIVATE)
        prefs.edit().putInt("numberPlay", prefs.getInt("numberPlay", 0) + 1).apply()
    }

    /** Full Quiz: push a new high score to the Back4App "Quiz" leaderboard row for this user. */
    private fun uploadHighScore(score: Int) {
        try {
            Parse.initialize(
                Parse.Configuration.Builder(this)
                    .applicationId(getString(R.string.back4app_app_id))
                    .clientKey(getString(R.string.back4app_client_key))
                    .server(getString(R.string.back4app_server_url))
                    .build()
            )
            val query = ParseQuery.getQuery<com.parse.ParseObject>("Quiz")
            val id = getSharedPreferences("SaveFile", Context.MODE_PRIVATE)
                .getString("objectID", "no")
            val name = getSharedPreferences("saveData", Context.MODE_PRIVATE)
                .getString("Name", "noName")
            query.getInBackground(id) { entity, e ->
                if (e == null && entity != null) {
                    entity.put("Highscore", score)
                    if (entity.get("Username") != name && name != null) {
                        entity.put("Username", name)
                    }
                    entity.saveInBackground()
                }
            }
        } catch (e: Exception) {
            Toast.makeText(this, "" + e, Toast.LENGTH_SHORT).show()
        }
    }
}

private fun quizViewModelFactory(config: QuizConfig, seconds: Int) = object : Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        QuizViewModel(config, totalSeconds = seconds) as T
}
```

- [ ] **Step 2: Compile**

Run: `JAVA_HOME=/Users/abdalqaderalnajjar/Library/Java/JavaVirtualMachines/azul-17.0.8.1/Contents/Home ./gradlew :app:compileDebugKotlin`
Expected: BUILD SUCCESSFUL. (Parse imports resolve — `com.github.parse-community.Parse-SDK-Android:parse:1.21.0` is already in `app/build.gradle`.)

- [ ] **Step 3: Commit**

```bash
git add app/src/main/java/com/Elkood/ling_en4/ui/screens/quiz/ComposeQuizActivity.kt
git commit -m "feat: ComposeQuizActivity fixed timer, result mode, play-count, Parse upload"
```

---

### Task 7: Repoint the five launch sites and verify end-to-end

**Files:**
- Modify: `app/src/main/java/com/Elkood/ling_en4/Views/SecondYear/English_4/BankItemsQuiz.java:109-119`
- Modify: `app/src/main/java/com/Elkood/ling_en4/Views/SecondYear/English_4/Home/Home_fragment.java:55`
- Modify: `app/src/main/java/com/Elkood/ling_en4/Views/SecondYear/English_4/Header_Elements/old_version.java:35`

**Interfaces:**
- Consumes: `ComposeQuizActivity.EXTRA_TOPIC`, `QuizTopic` (already imported in `BankItemsQuiz`); `ComposeQuizActivity` is registered in `AndroidManifest.xml` (Phase 0-1).

**Context:** `BankItemsQuiz` index 0 already routes Vocabulary to `ComposeQuizActivity`; index 1 (True/False) stays on its legacy Activity. This task repoints indices 2/3/4, the Full Quiz launch in `Home_fragment`, and the ENG3 launch in `old_version` — keeping `startActivityForResult` so `old_version.onActivityResult` still receives the score via the unchanged `"extrascore"` extra.

- [ ] **Step 1: Repoint `BankItemsQuiz` indices 2, 3, 4** — replace the three `else if` branches (lines ~109-119) so each targets `ComposeQuizActivity` with the topic extra:

```java
        } else if (index == 2) {
            Intent intent = new Intent(getContext(), ComposeQuizActivity.class);
            intent.putExtra(ComposeQuizActivity.EXTRA_TOPIC, QuizTopic.ABBREVIATIONS.name());
            requireView().getContext().startActivity(intent);
        } else if (index == 3) {
            Intent intent = new Intent(getContext(), ComposeQuizActivity.class);
            intent.putExtra(ComposeQuizActivity.EXTRA_TOPIC, QuizTopic.COMPOUND_NOUNS.name());
            requireView().getContext().startActivity(intent);

        } else if (index == 4) {
            Intent intent = new Intent(getContext(), ComposeQuizActivity.class);
            intent.putExtra(ComposeQuizActivity.EXTRA_TOPIC, QuizTopic.EXTENSIONS.name());
            requireView().getContext().startActivity(intent);
        }
```

Confirm `ComposeQuizActivity` and `QuizTopic` are imported in `BankItemsQuiz.java` (index 0 already uses both — they are). The legacy imports of `QuizActivity_Abberv`, `QuizActivity_Comp`, `QuizActivity_Extinsons` become unused; leave them (harmless) or remove them — removing is cleaner. If the project treats unused imports as errors (it does not by default), remove lines 19-21.

- [ ] **Step 2: Repoint `Home_fragment` Full Quiz launch (line 55)** — replace:

```java
            Intent intent = new Intent(v.getContext(), QuizActivity_Full_Quiz.class).setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
```
with:
```java
            Intent intent = new Intent(v.getContext(), com.Elkood.ling_en4.ui.screens.quiz.ComposeQuizActivity.class)
                    .setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            intent.putExtra(com.Elkood.ling_en4.ui.screens.quiz.ComposeQuizActivity.EXTRA_TOPIC,
                    com.Elkood.ling_en4.data.model.QuizTopic.FULL_QUIZ.name());
            startActivity(intent);
```

(Fully-qualified names avoid touching the import block; add imports instead if you prefer — either is fine. The legacy `QuizActivity_Full_Quiz` import at line 17 becomes unused.)

- [ ] **Step 3: Repoint `old_version` ENG3 launch (line 35), keeping `startActivityForResult`** — replace:

```java
        Intent intent = new Intent(getApplicationContext(), QuizActivity_Eng3.class);
        startActivityForResult(intent, REQUST_CODE_QUIZ);
```
with:
```java
        Intent intent = new Intent(getApplicationContext(), com.Elkood.ling_en4.ui.screens.quiz.ComposeQuizActivity.class);
        intent.putExtra(com.Elkood.ling_en4.ui.screens.quiz.ComposeQuizActivity.EXTRA_TOPIC,
                com.Elkood.ling_en4.data.model.QuizTopic.ENG3.name());
        startActivityForResult(intent, REQUST_CODE_QUIZ);
```

Leave `onActivityResult` unchanged — it reads `QuizActivity_Eng3.EXTRA_SCORE` (`"extrascore"`), the same value `ComposeQuizActivity` sets. Leave the `QuizActivity_Eng3` import (still referenced by `onActivityResult`).

- [ ] **Step 4: Full clean assemble of the debug APK**

Run: `JAVA_HOME=/Users/abdalqaderalnajjar/Library/Java/JavaVirtualMachines/azul-17.0.8.1/Contents/Home ./gradlew :app:assembleDebug`
Expected: BUILD SUCCESSFUL.

- [ ] **Step 5: Run the full unit-test suite**

Run: `JAVA_HOME=/Users/abdalqaderalnajjar/Library/Java/JavaVirtualMachines/azul-17.0.8.1/Contents/Home ./gradlew :app:testDebugUnitTest`
Expected: BUILD SUCCESSFUL, all tests pass.

- [ ] **Step 6: Install and device smoke test** (emulator-5554 = Pixel_4 AVD)

```bash
~/Library/Android/sdk/platform-tools/adb -s emulator-5554 install -r app/build/outputs/apk/debug/app-debug.apk
```

Then manually verify each repointed entry point launches the Compose screen and behaves correctly:
- **Bank menu → Abbreviations / Compound Nouns / Extensions** each open the Compose quiz, show 4 options, grade correctly, and on finish persist their high score under `SaveScore`/`AbberHighScore`|`CompHighScore`|`ExtHighScore`. Verify: `~/Library/Android/sdk/platform-tools/adb -s emulator-5554 shell run-as com.Elkood.ling_en4 cat /data/data/com.Elkood.ling_en4/shared_prefs/SaveScore.xml`.
- **ENG3** (via the `old_version` header button): opens the Compose quiz, timer is a fixed 40s, sounds play regardless of the `switch1` setting, rows with empty options are hidden, and on finish the returned score updates the ENG3 high-score TextView in `old_version` (proves the Intent result round-trips).
- **Home → Full Quiz**: opens the Compose quiz, the streak bar advances on consecutive correct answers and resets on a wrong one, toasts at 10 in a row, `numberPlay` incremented on launch, and a new high score writes `SaveScore`/`fullscore`.

Record the smoke-test outcome (pass/fail per entry point) in the task report. If any entry point misbehaves, treat it as a failed task and fix before completing.

- [ ] **Step 7: Commit**

```bash
git add app/src/main/java/com/Elkood/ling_en4/Views/SecondYear/English_4/BankItemsQuiz.java app/src/main/java/com/Elkood/ling_en4/Views/SecondYear/English_4/Home/Home_fragment.java app/src/main/java/com/Elkood/ling_en4/Views/SecondYear/English_4/Header_Elements/old_version.java
git commit -m "feat: repoint Abberv/Comp/Ext/ENG3/Full launch sites to ComposeQuizActivity"
```

---

## Self-Review

**Spec coverage:**
- Repository reading 5 SQLite sources + Vocabulary → **Task 1**.
- `QuizConfig` extension + all-6-topic wiring + `Context` param → **Task 2**.
- ENG3 ungated sound → **Task 3** + wired in **Task 6**.
- Full Quiz streak bar + 10-toast → **Task 4** (state) + **Task 5** (UI).
- ENG3 empty-option hiding → **Task 5**.
- Fixed 40s timer (ENG3) → **Task 6**.
- ENG3 Intent-return result → **Task 6** + **Task 7** (`old_version`).
- Full Quiz `numberPlay` + Parse upload → **Task 6**.
- Per-topic high-score prefs (Abberv/Comp/Ext/Full) → **Task 2** + **Task 6**.
- Launch-site repointing (BankItemsQuiz 2/3/4, Home_fragment, old_version) → **Task 7**.
- `passThreshold = questions.size` per topic → **Task 2**.

**Placeholder scan:** No TBD/TODO/"handle edge cases" placeholders; every code step shows full code. The one prose note (import placement in Task 2) is a formatting clarification with the exact final import block given.

**Type consistency:** `questionsFor(topic, context)` (Task 1) matches its call in `quizConfigFor` (Task 2); `quizConfigFor(topic, context)` matches the call site in `ComposeQuizActivity` (Tasks 2 & 6); `QuizConfig` fields set in Task 2 are exactly those read in Task 6 (`fixedSeconds`, `soundGated`, `resultMode`, `showStreakBar`, `incrementPlayCount`, `uploadToParse`, `highScorePrefFile`, `highScorePrefKey`); `ResultMode` enum (Task 2) used in Task 6; `QuizUiState.streak` (Task 4) read in Task 5; `QuizSound(context, gated)` (Task 3) called in Task 5; `InteractiveQuizScreen(..., soundGated, showStreakBar)` (Task 5) called in Task 6; `ComposeQuizActivity.EXTRA_SCORE` value (`"extrascore"`, Task 6) equals the value `old_version` reads (Task 7). Legacy getter names (`getAllQustion`, `getQution`, `getOption1..4`, `getAnswer`) verified against the actual Java sources.

**Known parity notes (intentional, not gaps):**
- SQLite is read on the calling thread in `onCreate`, exactly as the legacy Activities did.
- Shuffle is ON for all topics (consistency with shipped Vocabulary); legacy topics presented in DB order — this is a deliberate, reviewed behavior change.
- `Question`'s `correctIndex = getAnswer() - 1` will throw if a legacy row has `getAnswer() == 0`; curated DBs are 1-based, and this mirrors Vocabulary's mapping. Any bad row surfaces in the Task 7 device smoke test.
