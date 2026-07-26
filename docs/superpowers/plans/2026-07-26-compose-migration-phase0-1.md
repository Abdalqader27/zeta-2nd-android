# Compose Migration — Phase 0 (Foundation) + Phase 1 (Pilot Quiz Screen) Implementation Plan

> **For agentic workers:** REQUIRED SUB-SKILL: Use superpowers:subagent-driven-development (recommended) or superpowers:executing-plans to implement this plan task-by-task. Steps use checkbox (`- [ ]`) syntax for tracking.

**Goal:** Stand up Kotlin + Jetpack Compose inside the existing Java/View app, build the shared theme/component/data foundation with corrected naming, and migrate the first duplicated quiz (Vocabulary) to a single reusable `InteractiveQuizScreen` + `QuizViewModel` — launched from the existing Java hub via View↔Compose interop, pixel-faithful to the original.

**Architecture:** Incremental strangler-fig migration. Compose is added alongside the existing Views; no existing Java screen is deleted in these phases. The Vocabulary quiz is rebuilt as one data-driven Compose screen so the remaining 5 Pattern-A quizzes collapse onto it in later phases. Business logic (scoring, countdown, pass rule, high-score persistence) moves into a plain-Kotlin-testable `QuizViewModel`; the composable is a thin render layer. The screen is hosted by a thin `ComposeQuizActivity` that the old `QuizActivity_Vocabulary` launch site now points to.

**Tech Stack:** Kotlin 2.0.21, Jetpack Compose (BOM 2024.09.00), androidx.lifecycle ViewModel + Compose, Navigation-Compose (scaffolding only in Phase 0), Material 3, JUnit4 for ViewModel unit tests. AGP 8.9.1 / Gradle 8.11.1, built with JDK 17.

## Global Constraints

- Package root: `com.Elkood.ling_en4` — copy verbatim. New Compose code lives under `com.Elkood.ling_en4.ui.*` and `com.Elkood.ling_en4.data.*`.
- Build config floors (do not lower): `compileSdk 36`, `targetSdk 36`, `minSdk 21`, `versionCode 11`, `versionName "1.4.7"`, `multiDexEnabled true`, `viewBinding = true` (keep — existing Java screens still use it).
- All Gradle commands MUST run with JDK 17: prefix every `./gradlew …` with `JAVA_HOME=/Users/abdalqaderalnajjar/Library/Java/JavaVirtualMachines/azul-17.0.8.1/Contents/Home`. The machine default (JDK 19) will fail the build.
- Naming standard (from the approved spec, section 6a): fix legacy typos in all new code — `Qutions`→`Question`, `qution`→`prompt`, `Answer`(1-based)→`correctIndex`(0-based), `Extinsions`→`Extensions`, `Abberv`/`Abbrevationss`→`Abbreviations`, `Sgin`→`auth`, `molakas`→`Summary`. Enums use UPPER_SNAKE_CASE; classes PascalCase; composables PascalCase; functions/vals camelCase.
- **Vocabulary question count = 45** (not 46). The legacy `QuizDbHelper_Vocabulary` labels its questions `q1..q10` then `q12..q46` — the label `q11` is skipped, so there are exactly **45** `Qutions_Vocabulary(...)` calls. The original `getAllQustion().size` was therefore 45, and the quiz set `questiontTotalCounter = 45`.
- **Latent original bug — pass threshold:** the legacy quiz shows the "Congratulations" pass dialog only when `score == 46`, which is unreachable with 45 questions. This plan **fixes** the bug by setting the pass threshold to the actual question count (`questions.size`, i.e. 45 for Vocabulary) so a perfect score passes. This is a behavior fix, not a visual change — "pixel-faithful" governs layout/colors/fonts, not reproducing an unreachable-pass defect.
- Pixel-faithful: colors, fonts (Muli family), countdown durations, sounds, pass rule, and toast behavior must match the original `QuizActivity_Vocabulary` exactly. Exact values are carried in each task below.
- Do NOT delete `QuizDbHelper_Vocabulary.java`, `QuaizContract_Vocabulary.java`, `Qutions_Vocabulary.java`, `SoundPlayer.java`, or `Vocabulary.java` in these phases. They are removed in a later cleanup phase once all Pattern-A screens are migrated.
- Work on a branch, not `master`. Commit after every task's final step.

---

## File Structure

**Phase 0 — Foundation**
- `app/build.gradle` — add Kotlin + Compose plugins, dependencies, `buildFeatures.compose`, `composeOptions`/compose compiler plugin; bump `compileOptions`/`kotlinOptions` to Java 11.
- `build.gradle` (root) — add Kotlin Gradle plugin + compose compiler plugin classpaths/aliases.
- `app/src/main/java/com/Elkood/ling_en4/ui/theme/Color.kt` — brand color tokens.
- `app/src/main/java/com/Elkood/ling_en4/ui/theme/Type.kt` — Muli `FontFamily` + `Typography`.
- `app/src/main/java/com/Elkood/ling_en4/ui/theme/Theme.kt` — `ZetaTheme` composable wrapper.
- `app/src/main/java/com/Elkood/ling_en4/data/model/Question.kt` — unified question model.
- `app/src/main/java/com/Elkood/ling_en4/data/model/QuizTopic.kt` — enum of the 6 Pattern-A variants.
- `app/src/main/java/com/Elkood/ling_en4/ui/components/PrimaryButton.kt` — reusable bottom action button.
- `app/src/test/java/com/Elkood/ling_en4/data/model/QuestionTest.kt` — model invariants test.

**Phase 1 — Pilot Vocabulary quiz**
- `app/src/main/java/com/Elkood/ling_en4/data/content/En4Vocabulary.kt` — the 46 Vocabulary questions as a static list + topic config.
- `app/src/main/java/com/Elkood/ling_en4/data/QuizConfig.kt` — per-topic config (title, questions, pass threshold, high-score pref keys).
- `app/src/main/java/com/Elkood/ling_en4/ui/screens/quiz/QuizViewModel.kt` — quiz state machine (scoring, countdown, pass rule, persistence hooks).
- `app/src/main/java/com/Elkood/ling_en4/ui/screens/quiz/QuizUiState.kt` — immutable UI state + `AnswerPhase` enum.
- `app/src/main/java/com/Elkood/ling_en4/ui/screens/quiz/InteractiveQuizScreen.kt` — the composable (pixel-faithful).
- `app/src/main/java/com/Elkood/ling_en4/ui/screens/quiz/QuizSound.kt` — correct/wrong sound + sound-enabled pref.
- `app/src/main/java/com/Elkood/ling_en4/ui/screens/quiz/ComposeQuizActivity.kt` — thin host Activity (interop entry point).
- `app/src/main/AndroidManifest.xml` — register `ComposeQuizActivity`.
- `app/src/main/java/com/Elkood/ling_en4/Views/SecondYear/English_4/BankItemsQuiz.java:100` — repoint the `index == 0` "start Vocabulary quiz" intent to `ComposeQuizActivity` (interop launch). This is the sole launch site (a Fragment; uses `getContext()`).
- `app/src/test/java/com/Elkood/ling_en4/ui/screens/quiz/QuizViewModelTest.kt` — full behavior test.

---

## Task 1: Enable Kotlin + Compose in the Gradle build

**Files:**
- Modify: `build.gradle` (root, lines 3–16 buildscript)
- Modify: `app/build.gradle` (lines 1–3 plugins; 26–29 compileOptions; 32–67 dependencies)

**Interfaces:**
- Consumes: nothing.
- Produces: a build that compiles Kotlin + Compose. Later tasks rely on Compose runtime, `androidx.lifecycle:lifecycle-viewmodel-compose`, `androidx.activity:activity-compose`, and JUnit4 test config being present.

- [ ] **Step 1: Add Kotlin + compose-compiler plugin classpaths to the root `build.gradle`**

In `build.gradle`, inside `buildscript { dependencies { … } }` (after the existing AGP classpath on line 10), add:

```groovy
        classpath 'org.jetbrains.kotlin:kotlin-gradle-plugin:2.0.21'
        classpath 'org.jetbrains.kotlin:compose-compiler-gradle-plugin:2.0.21'
```

- [ ] **Step 2: Apply the plugins in `app/build.gradle`**

Replace the `plugins { … }` block (lines 1–3) with:

```groovy
plugins {
    id 'com.android.application'
    id 'org.jetbrains.kotlin.android'
    id 'org.jetbrains.kotlin.plugin.compose'
}
```

- [ ] **Step 3: Enable Compose and bump Java/Kotlin target**

In `app/build.gradle`, change the `buildFeatures` block (lines 6–8) to also enable compose, and replace `compileOptions` (lines 26–29) and add `kotlinOptions`:

```groovy
    buildFeatures {
        viewBinding = true
        compose = true
    }
```

```groovy
    compileOptions {
        sourceCompatibility JavaVersion.VERSION_11
        targetCompatibility JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = '11'
    }
```

- [ ] **Step 4: Add Compose dependencies**

In `app/build.gradle` `dependencies { … }`, remove the stale stdlib line 34 (`kotlin-stdlib-jdk7:1.5.10`) and add the Compose BOM + artifacts:

```groovy
    implementation platform('androidx.compose:compose-bom:2024.09.00')
    implementation 'androidx.compose.ui:ui'
    implementation 'androidx.compose.ui:ui-tooling-preview'
    implementation 'androidx.compose.material3:material3'
    implementation 'androidx.activity:activity-compose:1.9.2'
    implementation 'androidx.lifecycle:lifecycle-viewmodel-compose:2.8.6'
    implementation 'androidx.lifecycle:lifecycle-runtime-compose:2.8.6'
    debugImplementation 'androidx.compose.ui:ui-tooling'
    androidTestImplementation platform('androidx.compose:compose-bom:2024.09.00')
    androidTestImplementation 'androidx.compose.ui:ui-test-junit4'
```

Leave every existing dependency (appcompat, material, parse, the Aliyun-mirrored legacy libs, etc.) untouched.

- [ ] **Step 5: Run the build to verify Kotlin + Compose wiring compiles**

Run:
```bash
cd /Users/abdalqaderalnajjar/StudioProjects/zeta-2nd-android && JAVA_HOME=/Users/abdalqaderalnajjar/Library/Java/JavaVirtualMachines/azul-17.0.8.1/Contents/Home ./gradlew :app:compileDebugKotlin :app:assembleDebug --console=plain
```
Expected: `BUILD SUCCESSFUL`. (There is no Kotlin source yet; this proves the toolchain resolves and the existing Java still builds.)

- [ ] **Step 6: Commit**

```bash
git add build.gradle app/build.gradle
git commit -m "build: enable Kotlin and Jetpack Compose toolchain"
```

---

## Task 2: Theme foundation (colors, Muli typography, ZetaTheme)

**Files:**
- Create: `app/src/main/java/com/Elkood/ling_en4/ui/theme/Color.kt`
- Create: `app/src/main/java/com/Elkood/ling_en4/ui/theme/Type.kt`
- Create: `app/src/main/java/com/Elkood/ling_en4/ui/theme/Theme.kt`

**Interfaces:**
- Consumes: font resources `R.font.muli_regular`, `R.font.muli_light` (exist in `res/font/`).
- Produces:
  - `object ZetaColors` with `Primary`, `PrimaryDark`, `Accent`, `Correct`, `WrongText`, `OptionText`, `QuestionBg`, `HeaderBg`, `QuestionText` — all `androidx.compose.ui.graphics.Color`.
  - `val MuliFontFamily: FontFamily`
  - `@Composable fun ZetaTheme(content: @Composable () -> Unit)` — applies Material3 color scheme + typography and forces LTR quiz content.

- [ ] **Step 1: Write `Color.kt` with exact brand tokens**

Values copied verbatim from `res/values/colors.xml` and `styles.xml` (`AppTheme` uses the `style_color_*` set).

```kotlin
package com.Elkood.ling_en4.ui.theme

import androidx.compose.ui.graphics.Color

object ZetaColors {
    val Primary = Color(0xFF2D5D82)        // style_color_primary
    val PrimaryDark = Color(0xFF21425D)    // style_color_primary_dark
    val Accent = Color(0xFF01BCD5)         // style_color_accent
    val Correct = Color(0xFF008000)        // green — correct-answer highlight
    val WrongText = Color(0xFFFF0000)      // Color.RED used for revealed options / <15s countdown
    val OptionText = Color(0xFF5E5D5D)     // radio button default text color
    val QuestionText = Color(0xFF57727F)   // backber — question text color
    val QuestionBg = Color(0xFFF7F7F7)     // grey_Light — question + header cell background
    val HeaderBg = Color(0xFFF7F7F7)       // grey_Light — "Quiz"/"Point" header cells
    val Surface = Color(0xFFFFFFFF)        // white
    val OnSurface = Color(0xFF000000)      // black
}
```

- [ ] **Step 2: Write `Type.kt` with the Muli font family**

```kotlin
package com.Elkood.ling_en4.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.Elkood.ling_en4.R

val MuliFontFamily = FontFamily(
    Font(R.font.muli_regular, FontWeight.Normal),
    Font(R.font.muli_light, FontWeight.Light),
)

val ZetaTypography = Typography().let { base ->
    base.copy(
        bodyLarge = base.bodyLarge.copy(fontFamily = MuliFontFamily),
        bodyMedium = base.bodyMedium.copy(fontFamily = MuliFontFamily),
        titleLarge = base.titleLarge.copy(fontFamily = MuliFontFamily),
        labelLarge = base.labelLarge.copy(fontFamily = MuliFontFamily),
    )
}
```

- [ ] **Step 3: Write `Theme.kt`**

```kotlin
package com.Elkood.ling_en4.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val ZetaColorScheme = lightColorScheme(
    primary = ZetaColors.Primary,
    onPrimary = ZetaColors.Surface,
    secondary = ZetaColors.Accent,
    background = ZetaColors.Surface,
    surface = ZetaColors.Surface,
    onSurface = ZetaColors.OnSurface,
)

@Composable
fun ZetaTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = ZetaColorScheme,
        typography = ZetaTypography,
        content = content,
    )
}
```

- [ ] **Step 4: Compile to verify the theme resolves**

Run:
```bash
cd /Users/abdalqaderalnajjar/StudioProjects/zeta-2nd-android && JAVA_HOME=/Users/abdalqaderalnajjar/Library/Java/JavaVirtualMachines/azul-17.0.8.1/Contents/Home ./gradlew :app:compileDebugKotlin --console=plain
```
Expected: `BUILD SUCCESSFUL`.

- [ ] **Step 5: Commit**

```bash
git add app/src/main/java/com/Elkood/ling_en4/ui/theme
git commit -m "feat: add Compose theme (colors, Muli type, ZetaTheme)"
```

---

## Task 3: Unified `Question` model + `QuizTopic` enum (with corrected naming)

**Files:**
- Create: `app/src/main/java/com/Elkood/ling_en4/data/model/Question.kt`
- Create: `app/src/main/java/com/Elkood/ling_en4/data/model/QuizTopic.kt`
- Test: `app/src/test/java/com/Elkood/ling_en4/data/model/QuestionTest.kt`

**Interfaces:**
- Produces:
  - `data class Question(val prompt: String, val options: List<String>, val correctIndex: Int)` where `correctIndex` is **0-based** (legacy `Answer` was 1-based; conversion is `correctIndex = Answer - 1`). Includes `init` validating exactly 4 options and `correctIndex in options.indices`.
  - `enum class QuizTopic { VOCABULARY, COMPOUND_NOUNS, ABBREVIATIONS, EXTENSIONS, ENG3, FULL_QUIZ }`

- [ ] **Step 1: Write the failing test**

```kotlin
package com.Elkood.ling_en4.data.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Test

class QuestionTest {
    @Test
    fun stores_prompt_options_and_zero_based_correct_index() {
        val q = Question("Website :", listOf("a", "b", "c", "d"), correctIndex = 1)
        assertEquals("Website :", q.prompt)
        assertEquals("b", q.options[q.correctIndex])
    }

    @Test
    fun rejects_wrong_option_count() {
        assertThrows(IllegalArgumentException::class.java) {
            Question("q", listOf("a", "b", "c"), correctIndex = 0)
        }
    }

    @Test
    fun rejects_out_of_range_correct_index() {
        assertThrows(IllegalArgumentException::class.java) {
            Question("q", listOf("a", "b", "c", "d"), correctIndex = 4)
        }
    }
}
```

- [ ] **Step 2: Run the test to verify it fails**

Run:
```bash
cd /Users/abdalqaderalnajjar/StudioProjects/zeta-2nd-android && JAVA_HOME=/Users/abdalqaderalnajjar/Library/Java/JavaVirtualMachines/azul-17.0.8.1/Contents/Home ./gradlew :app:testDebugUnitTest --tests "com.Elkood.ling_en4.data.model.QuestionTest" --console=plain
```
Expected: FAIL — `Question` / `unresolved reference`.

- [ ] **Step 3: Write the model + enum**

`Question.kt`:
```kotlin
package com.Elkood.ling_en4.data.model

data class Question(
    val prompt: String,
    val options: List<String>,
    val correctIndex: Int,
) {
    init {
        require(options.size == 4) { "A Question must have exactly 4 options, got ${options.size}" }
        require(correctIndex in options.indices) { "correctIndex $correctIndex out of range" }
    }
}
```

`QuizTopic.kt`:
```kotlin
package com.Elkood.ling_en4.data.model

/** The six interactive (Pattern-A) quiz variants that share one screen. */
enum class QuizTopic {
    VOCABULARY,
    COMPOUND_NOUNS,
    ABBREVIATIONS,
    EXTENSIONS,
    ENG3,
    FULL_QUIZ,
}
```

- [ ] **Step 4: Run the test to verify it passes**

Run:
```bash
cd /Users/abdalqaderalnajjar/StudioProjects/zeta-2nd-android && JAVA_HOME=/Users/abdalqaderalnajjar/Library/Java/JavaVirtualMachines/azul-17.0.8.1/Contents/Home ./gradlew :app:testDebugUnitTest --tests "com.Elkood.ling_en4.data.model.QuestionTest" --console=plain
```
Expected: PASS (3 tests).

- [ ] **Step 5: Commit**

```bash
git add app/src/main/java/com/Elkood/ling_en4/data/model app/src/test/java/com/Elkood/ling_en4/data/model
git commit -m "feat: add unified Question model and QuizTopic enum"
```

---

## Task 4: Reusable `PrimaryButton` component

**Files:**
- Create: `app/src/main/java/com/Elkood/ling_en4/ui/components/PrimaryButton.kt`

**Interfaces:**
- Consumes: `ZetaColors`, `MuliFontFamily`.
- Produces: `@Composable fun PrimaryButton(text: String, onClick: () -> Unit, modifier: Modifier = Modifier, enabled: Boolean = true)` — the full-width bottom action button used by the quiz ("Confirm" / "Next" / "Finish"). Reused by every migrated screen with a bottom CTA.

- [ ] **Step 1: Write the component**

Reproduces the original bottom `Button` (full width, 50dp tall, primary background, white bold Muli text).

```kotlin
package com.Elkood.ling_en4.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.Elkood.ling_en4.ui.theme.MuliFontFamily
import com.Elkood.ling_en4.ui.theme.ZetaColors

@Composable
fun PrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = ZetaColors.Primary,
            contentColor = ZetaColors.Surface,
        ),
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 5.dp, vertical = 5.dp)
            .height(50.dp),
    ) {
        Text(
            text = text,
            fontFamily = MuliFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
        )
    }
}
```

- [ ] **Step 2: Compile to verify it resolves**

Run:
```bash
cd /Users/abdalqaderalnajjar/StudioProjects/zeta-2nd-android && JAVA_HOME=/Users/abdalqaderalnajjar/Library/Java/JavaVirtualMachines/azul-17.0.8.1/Contents/Home ./gradlew :app:compileDebugKotlin --console=plain
```
Expected: `BUILD SUCCESSFUL`.

- [ ] **Step 3: Commit**

```bash
git add app/src/main/java/com/Elkood/ling_en4/ui/components
git commit -m "feat: add reusable PrimaryButton component"
```

---

## Task 5: Vocabulary content object + `QuizConfig`

**Files:**
- Create: `app/src/main/java/com/Elkood/ling_en4/data/content/En4Vocabulary.kt`
- Create: `app/src/main/java/com/Elkood/ling_en4/data/QuizConfig.kt`
- Test: `app/src/test/java/com/Elkood/ling_en4/data/content/En4VocabularyTest.kt`

**Interfaces:**
- Consumes: `Question`, `QuizTopic`.
- Produces:
  - `object En4Vocabulary { val questions: List<Question> }` — the 45 questions from `QuizDbHelper_Vocabulary.fillQuestionTabel()`, each with `correctIndex = legacyAnswer - 1`.
  - `data class QuizConfig(val topic: QuizTopic, val title: String, val questions: List<Question>, val passThreshold: Int, val highScorePrefFile: String, val highScorePrefKey: String)`
  - `fun quizConfigFor(topic: QuizTopic): QuizConfig` — Phase 1 wires VOCABULARY only; other topics `TODO()` until their content objects exist.

- [ ] **Step 1: Write the failing test (pins the count + a couple of known answers)**

Known from source: 45 questions (labels q1–q10 then q12–q46, q11 skipped); Q1 "Website :" correct option is #2 (1-based) → index 1 = "Collection of related webpages."; last Q "Markup Language " correct is #1 → index 0.

```kotlin
package com.Elkood.ling_en4.data.content

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class En4VocabularyTest {
    @Test
    fun has_forty_five_questions() {
        assertEquals(45, En4Vocabulary.questions.size)
    }

    @Test
    fun first_question_maps_answer_to_zero_based_index() {
        val q = En4Vocabulary.questions.first()
        assertTrue(q.prompt.startsWith("Website"))
        assertEquals(1, q.correctIndex)
        assertEquals("Collection of related webpages.", q.options[q.correctIndex])
    }

    @Test
    fun last_question_maps_answer_to_zero_based_index() {
        val q = En4Vocabulary.questions.last()
        assertTrue(q.prompt.startsWith("Markup Language"))
        assertEquals(0, q.correctIndex)
    }

    @Test
    fun every_question_has_valid_options() {
        En4Vocabulary.questions.forEach { assertEquals(4, it.options.size) }
    }
}
```

- [ ] **Step 2: Run the test to verify it fails**

Run:
```bash
cd /Users/abdalqaderalnajjar/StudioProjects/zeta-2nd-android && JAVA_HOME=/Users/abdalqaderalnajjar/Library/Java/JavaVirtualMachines/azul-17.0.8.1/Contents/Home ./gradlew :app:testDebugUnitTest --tests "com.Elkood.ling_en4.data.content.En4VocabularyTest" --console=plain
```
Expected: FAIL — unresolved `En4Vocabulary`.

- [ ] **Step 3: Write `En4Vocabulary.kt`**

Transcribe all 46 questions verbatim from `QuizDbHelper_Vocabulary.java:51-366`, in the same order, converting each `Qutions_Vocabulary(prompt, o1, o2, o3, o4, answer)` to `Question(prompt, listOf(o1, o2, o3, o4), correctIndex = answer - 1)`. Preserve the exact strings including their trailing/leading spaces. The full list (answer shown is the original 1-based value; use `- 1`):

```kotlin
package com.Elkood.ling_en4.data.content

import com.Elkood.ling_en4.data.model.Question

/** Vocabulary interactive-quiz content, migrated verbatim from QuizDbHelper_Vocabulary. */
object En4Vocabulary {

    private fun q(prompt: String, o1: String, o2: String, o3: String, o4: String, answer: Int) =
        Question(prompt, listOf(o1, o2, o3, o4), correctIndex = answer - 1)

    val questions: List<Question> = listOf(
        q("Website : ", "Using reference work like encyclopedias", "Collection of related webpages.", "Composing Music on PC.", "Type of compression used for Bitmap image", 2),
        q("Virus : ", "Self-replicating program.", "Formula used for decompressing component of data stream.", "the amount of data transferred to the cache at any one time ", "Composing Music on PC.", 1),
        q("Office Suite : ", "Downloading Music from the internet", "Set of Standard Programs used in an Office.", "Collection of related webpages.", "Common type of Compression used for video data.", 2),
        q("Bandwidth : ", "High Capacity of internet connection", "a combination of text with sound, graphic and video", "Composing Music on PC. ", "Capacity of a network connection.", 4),
        q("Broadband : ", "Capacity of a network connection.", "Composing Music on PC", "High Capacity of internet connection  ", "a combination of text with sound, graphic and video ", 3),
        q("Data Center", "a system that allows users to interact with a combination of inputs  ", "Facility for storing Large Amount of information", "Common Enterprise resource Planning tool ", "software assistant that performs tasks such as automatic repetitive tasks   ", 2),
        q("SAP ", "Common Enterprise resource Planning tool", "Facility for storing Large Amount of information", "software assistant that performs tasks such as automatic repetitive tasks  ", "a system that allows users to interact with a combination of inputs    ", 1),
        q("MIDI", "Common Enterprise resource Planning tool ", "Facility for storing Large Amount of information", " standard for interconnecting electronic musical instruments and computers.", "software assistant that performs tasks such as automatic repetitive tasks  ", 3),
        q("Mp3 :", "Common Enterprise resource Planning tool ", "Facility for storing Large Amount of information", "software assistant that performs tasks such as automatic repetitive tasks  ", "Downloading Music from the internet. ", 4),
        q("DVD :", "Composing Music on PC ", "Watching Movie ", "Common Enterprise resource Planning tool", "Downloading Music from the internet. ", 2),
        q("Algorithm : ", "Common Enterprise resource Planning tool ", "Formula used for decompressing component of data stream. ", "Facility for storing Large Amount of information", "Using reference work like encyclopedias. ", 2),
        q("I-Frame ", "Abstucting and Indexing  ", "Common Enterprise resource Planning tool ", "Compressed video frame that contains the complete Image Information", "Using reference work like encyclopedias.   ", 3),
        q("JPEG  ", "Using reference work like encyclopedias. ", "Composing Music on PC ", "Formula used for decompressing component of data stream", "Type of compression used for Bitmap image.", 4),
        q("P-Frame ", " Type of compression used for Bitmap image. ", "Compressed video frame known as Predicted Frame.  ", "Compressed video frame that stores Changes between the frame before it and the frame after it. ", "Using reference work like encyclopedias.", 2),
        q("B-Frame :", " Compressed video frame that stores Changes between the frame before it and the frame after it.  ", "Compressed video frame known as Predicted Frame.  ", "Type of compression used for Bitmap image. ", "Using reference work like encyclopedias.", 1),
        q("MPEG : ", " Using reference work like encyclopedias.  ", "Common type of Compression used for video data  ", "Type of compression used for Bitmap image. ", "Downloading Music from the internet. ", 2),
        q("Bracketing :  ", " Using reference work like encyclopedias.  ", "Watching Movie   ", "Set boundaries for the beginning and end of message  ", "Mathematical calculation based on the content of data", 3),
        q("Checksum : ", "Formula used for decompressing component of data stream", "Common type of Compression used for video data     ", "Set boundaries for the beginning and end of message  ", "Mathematical calculation based on the content of data ", 4),
        q("Half-Duplex :", " Transmission mode in which each computer takes turn sending and receiving  ", "Transmission mode in which both computers send and receive at the same time  ", "Set boundaries for the beginning and end of message     ", "Mathematical calculation based on the content of data  ", 1),
        q("Full-Duplex", " Transmission mode in which each computer takes turn sending and receiving  ", "Transmission mode in which both computers send and receive at the same time  ", "Set boundaries for the beginning and end of message     ", "Mathematical calculation based on the content of data  ", 2),
        q("IRC :    ", " Chatting to other users in real-time. ", "Formula used for decompressing component of data stream  ", "Set boundaries for the beginning and end of message    ", "Mathematical calculation based on the content of data   ", 1),
        q("Moos :  ", " Chatting to other users in real-time.    ", "Formula used for decompressing component of data stream", "Mathematical calculation based on the content of data  ", "Taking part in simulation in shared environment  ", 4),
        q("E-Mail : ", "Chatting to other users in real-time.   ", "Mathematical calculation based on the content of data ", "Sending and receiving message   ", "Taking part in simulation in shared environment   ", 3),
        q("FTP : ", "Downloading file from server.", "Sending and receiving message ", "Taking part in simulation in shared environment     ", "Chatting to other users in real-time. ", 1),
        q("WWW :  ", " Downloading file from server.  ", "Browsing web page  ", "branch computer      ", "Taking part in simulation in shared environment ", 2),
        q("Telnet :  ", "Logging on to your computer at a distance  ", "Accessing web pages ", "Browsing web page  ", "Chatting to other users in real-time.", 1),
        q("Usenet : ", " Browsing web page ", "Accessing web pages ", "branch computer     ", "Chatting to other users in real-time.", 2),
        q("Router : ", " Browsing web page ", "Accessing web pages ", "Special computer that’s directs communications.  ", "Main transmission path handling major data traffic", 3),
        q("backbone : ", " Special computer that’s directs communications. ", "Taking part in simulation in shared environment  ", "branch computer   ", "Main transmission path handling major data traffic ", 4),
        q("Internet Address ", " Main transmission path handling major data traffic ", "Accessing web pages ", "Taking part in simulation in shared environment ", "A 32-bit number identifying anode on an IP network ", 4),
        q("Resolution Protocol", " Standard used for software that routes data through get way ", "A 32-bit number identifying anode on an IP network", "Main transmission path handling major data traffic  ", "Taking part in simulation in shared environment ", 1),
        q("Look-up Table  ", " Taking part in simulation in shared environment", "Stored information used to route data through get way.", "Main transmission path handling major data traffic  ", "Standard used for software that routes data through get way ", 2),
        q("Get Way :  ", " Device for connecting dissimilar networks. ", "Standard used for software that routes data through get way", "Main transmission path handling major data traffic  ", "Taking part in simulation in shared environment ", 1),
        q("User Datagram Protocol(UDB) ", " Device for connecting dissimilar networks. ", "Taking part in simulation in shared environment", "Standard used by software that moves information to the correct application on the receiving system of a network.   ", "Main transmission path handling major data traffic ", 3),
        q("Transmission Control Protocol(TCP) ", " Device for connecting dissimilar networks. ", "Taking part in simulation in shared environment", "Standard used by software that moves information to the correct application on the receiving system of a network.   ", "Standard used by software that manage communication exchanges between computers on the internet  ", 4),
        q("ISMTP  ", "Device for connecting dissimilar networks.", "Simple Mail Transfer Protocol that is used to send message between server", " Device for connecting dissimilar networks. ", "Taking part in simulation in shared environment", 2),
        q("Push' Operation ", " Simple Mail Transfer Protocol that is used to send message between server   ", "Device for connecting dissimilar networks.  ", "An E-Mail Transfer Process in which the connection is initiated by the sending computer rather than the receiving computer ", "An E-Mail Transfer Process in which the receiving computer initiates the connection   ", 3),
        q("Pull' Operation  ", " Simple Mail Transfer Protocol that is used to send message between server   ", "Device for connecting dissimilar networks.  ", "An E-Mail Transfer Process in which the connection is initiated by the sending computer rather than the receiving computer ", "An E-Mail Transfer Process in which the receiving computer initiates the connection   ", 4),
        q("POP   ", " Simple Mail Transfer Protocol that is used to send message between server   ", "Device for connecting dissimilar networks.  ", "A Message-Retrieval protocol that download all E-Mail messages at the same time ", "An E-Mail Transfer Process in which the receiving computer initiates the connection   ", 3),
        q("IMAP   ", " Simple Mail Transfer Protocol that is used to send message between server   ", "Device for connecting dissimilar networks.  ", "Mail transfer protocol that initially only retrieves the message handers ", "An E-Mail Transfer Process in which the receiving computer initiates the connection   ", 3),
        q("Metadata   ", " Data about Data  ", "Device for connecting dissimilar networks.  ", "A Message-Retrieval protocol that download all E-Mail messages at the same time ", "An E-Mail Transfer Process in which the receiving computer initiates the connection   ", 1),
        q("GMetalanguage   ", " Data about Data  ", "Language from which you can create other Language. ", "A Message-Retrieval protocol that download all E-Mail messages at the same time ", "Device for connecting dissimilar networks.  ", 2),
        q("HTML  ", " Data about Data  ", "Language from which you can create other Language. ", "example of page presentation Language.", "Device for connecting dissimilar networks.  ", 3),
        q("XML ", " Data about Data  ", "Language from which you can create other Language. ", "example of page presentation Language.", "extensible markup Language ", 4),
        q("Markup Language ", " coding system used for structuring and formatting documents. ", "Language from which you can create other Language. ", "example of page presentation Language.", "extensible markup Language ", 1),
    )
}
```

Note: the legacy DB labels its questions `q1..q10` then `q12..q46` (the label `q11` is skipped, jumping q10 → q12), so there are exactly **45** `Qutions_Vocabulary(...)` calls and the list above has **45** entries. Step 1's test enforces the count. Cross-check against `QuizDbHelper_Vocabulary.java:51-366` line by line.

- [ ] **Step 4: Write `QuizConfig.kt`**

Original Vocabulary persistence: prefs file `"SaveScore"`, key `"VocHighScore"`. The legacy pass rule was the unreachable `score == 46`; here `passThreshold = questions.size` (45) so a perfect score passes — the fix described in Global Constraints.

```kotlin
package com.Elkood.ling_en4.data

import com.Elkood.ling_en4.data.content.En4Vocabulary
import com.Elkood.ling_en4.data.model.Question
import com.Elkood.ling_en4.data.model.QuizTopic

data class QuizConfig(
    val topic: QuizTopic,
    val title: String,
    val questions: List<Question>,
    val passThreshold: Int,
    val highScorePrefFile: String,
    val highScorePrefKey: String,
)

fun quizConfigFor(topic: QuizTopic): QuizConfig = when (topic) {
    QuizTopic.VOCABULARY -> QuizConfig(
        topic = QuizTopic.VOCABULARY,
        title = "Vocabulary",
        questions = En4Vocabulary.questions,
        passThreshold = En4Vocabulary.questions.size,  // 45 — fixes legacy unreachable `score == 46`
        highScorePrefFile = "SaveScore",
        highScorePrefKey = "VocHighScore",
    )
    else -> TODO("Content for $topic is migrated in a later phase")
}
```

- [ ] **Step 5: Run the test to verify it passes**

Run:
```bash
cd /Users/abdalqaderalnajjar/StudioProjects/zeta-2nd-android && JAVA_HOME=/Users/abdalqaderalnajjar/Library/Java/JavaVirtualMachines/azul-17.0.8.1/Contents/Home ./gradlew :app:testDebugUnitTest --tests "com.Elkood.ling_en4.data.content.En4VocabularyTest" --console=plain
```
Expected: PASS (4 tests). If `has_forty_five_questions` fails, a question was dropped/duplicated during transcription — re-diff against the Java source.

- [ ] **Step 6: Commit**

```bash
git add app/src/main/java/com/Elkood/ling_en4/data app/src/test/java/com/Elkood/ling_en4/data
git commit -m "feat: migrate Vocabulary question bank and add QuizConfig"
```

---

## Task 6: `QuizUiState` + `QuizViewModel` (scoring, countdown, pass rule)

**Files:**
- Create: `app/src/main/java/com/Elkood/ling_en4/ui/screens/quiz/QuizUiState.kt`
- Create: `app/src/main/java/com/Elkood/ling_en4/ui/screens/quiz/QuizViewModel.kt`
- Test: `app/src/test/java/com/Elkood/ling_en4/ui/screens/quiz/QuizViewModelTest.kt`

**Interfaces:**
- Consumes: `QuizConfig`, `Question`.
- Produces:
  - `enum class AnswerPhase { ANSWERING, REVEALED }`
  - `data class QuizUiState(val questionNumber: Int, val totalQuestions: Int, val prompt: String, val options: List<String>, val selectedIndex: Int?, val correctIndex: Int?, val phase: AnswerPhase, val score: Int, val secondsLeft: Int, val isLastQuestion: Boolean, val finished: Boolean, val passed: Boolean)`
  - `class QuizViewModel(config: QuizConfig, totalSeconds: Int, shuffle: (List<Question>) -> List<Question> = { it.shuffled() })` exposing `val uiState: StateFlow<QuizUiState>` and methods: `fun selectOption(index: Int)`, `fun confirm()` (grades current, moves to REVEALED, returns whether correct), `fun next()` (advances or finishes), `fun onTimeExpired()` (auto-grades with no selection), `fun tick()` (decrement secondsLeft; when it reaches 0 calls `onTimeExpired`). Countdown length comes from `totalSeconds`; the Activity computes it from the `saveR` pref (40/30/50s) and injects it.
- Note: the ViewModel holds no Android types so it is JVM-unit-testable. Persistence + sound are triggered by the composable via callbacks, not inside the ViewModel.

- [ ] **Step 1: Write the failing test**

Uses a fixed 3-question config and identity shuffle for determinism.

```kotlin
package com.Elkood.ling_en4.ui.screens.quiz

import com.Elkood.ling_en4.data.QuizConfig
import com.Elkood.ling_en4.data.model.Question
import com.Elkood.ling_en4.data.model.QuizTopic
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class QuizViewModelTest {

    private fun config(pass: Int) = QuizConfig(
        topic = QuizTopic.VOCABULARY,
        title = "T",
        questions = listOf(
            Question("q1", listOf("a", "b", "c", "d"), correctIndex = 0),
            Question("q2", listOf("a", "b", "c", "d"), correctIndex = 1),
            Question("q3", listOf("a", "b", "c", "d"), correctIndex = 2),
        ),
        passThreshold = pass,
        highScorePrefFile = "f",
        highScorePrefKey = "k",
    )

    private fun vm(pass: Int = 3) =
        QuizViewModel(config(pass), totalSeconds = 40, shuffle = { it })

    @Test
    fun starts_on_first_question() {
        val s = vm().uiState.value
        assertEquals(1, s.questionNumber)
        assertEquals(3, s.totalQuestions)
        assertEquals("q1", s.prompt)
        assertEquals(AnswerPhase.ANSWERING, s.phase)
        assertEquals(0, s.score)
        assertEquals(40, s.secondsLeft)
    }

    @Test
    fun correct_answer_increments_score_and_reveals() {
        val vm = vm()
        vm.selectOption(0)
        vm.confirm()
        val s = vm.uiState.value
        assertEquals(1, s.score)
        assertEquals(AnswerPhase.REVEALED, s.phase)
        assertEquals(0, s.correctIndex)
    }

    @Test
    fun wrong_answer_does_not_increment_score() {
        val vm = vm()
        vm.selectOption(3)
        vm.confirm()
        assertEquals(0, vm.uiState.value.score)
    }

    @Test
    fun next_advances_and_resets_phase() {
        val vm = vm()
        vm.selectOption(0); vm.confirm(); vm.next()
        val s = vm.uiState.value
        assertEquals(2, s.questionNumber)
        assertEquals("q2", s.prompt)
        assertEquals(AnswerPhase.ANSWERING, s.phase)
        assertEquals(40, s.secondsLeft)
        assertEquals(null, s.selectedIndex)
    }

    @Test
    fun finishing_all_correct_sets_passed_true() {
        val vm = vm(pass = 3)
        vm.selectOption(0); vm.confirm(); vm.next()
        vm.selectOption(1); vm.confirm(); vm.next()
        vm.selectOption(2); vm.confirm()
        assertTrue(vm.uiState.value.isLastQuestion)
        vm.next()
        val s = vm.uiState.value
        assertTrue(s.finished)
        assertTrue(s.passed)
        assertEquals(3, s.score)
    }

    @Test
    fun finishing_below_threshold_sets_passed_false() {
        val vm = vm(pass = 3)
        vm.selectOption(0); vm.confirm(); vm.next()   // correct
        vm.selectOption(0); vm.confirm(); vm.next()   // wrong
        vm.selectOption(2); vm.confirm(); vm.next()   // correct -> score 2
        val s = vm.uiState.value
        assertTrue(s.finished)
        assertFalse(s.passed)
        assertEquals(2, s.score)
    }

    @Test
    fun time_expiry_reveals_without_scoring() {
        val vm = vm()
        vm.onTimeExpired()
        val s = vm.uiState.value
        assertEquals(AnswerPhase.REVEALED, s.phase)
        assertEquals(0, s.score)
        assertEquals(0, s.correctIndex)
    }

    @Test
    fun tick_decrements_and_expires_at_zero() {
        val vm = QuizViewModel(config(3), totalSeconds = 1, shuffle = { it })
        vm.tick()
        val s = vm.uiState.value
        assertEquals(0, s.secondsLeft)
        assertEquals(AnswerPhase.REVEALED, s.phase)
    }
}
```

- [ ] **Step 2: Run the test to verify it fails**

Run:
```bash
cd /Users/abdalqaderalnajjar/StudioProjects/zeta-2nd-android && JAVA_HOME=/Users/abdalqaderalnajjar/Library/Java/JavaVirtualMachines/azul-17.0.8.1/Contents/Home ./gradlew :app:testDebugUnitTest --tests "com.Elkood.ling_en4.ui.screens.quiz.QuizViewModelTest" --console=plain
```
Expected: FAIL — unresolved `QuizViewModel`.

- [ ] **Step 3: Write `QuizUiState.kt`**

```kotlin
package com.Elkood.ling_en4.ui.screens.quiz

enum class AnswerPhase { ANSWERING, REVEALED }

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
)
```

- [ ] **Step 4: Write `QuizViewModel.kt`**

```kotlin
package com.Elkood.ling_en4.ui.screens.quiz

import androidx.lifecycle.ViewModel
import com.Elkood.ling_en4.data.QuizConfig
import com.Elkood.ling_en4.data.model.Question
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class QuizViewModel(
    private val config: QuizConfig,
    private val totalSeconds: Int,
    shuffle: (List<Question>) -> List<Question> = { it.shuffled() },
) : ViewModel() {

    private val questions: List<Question> = shuffle(config.questions)
    private var index = 0
    private var score = 0

    private val _uiState = MutableStateFlow(buildState(AnswerPhase.ANSWERING, null, totalSeconds))
    val uiState: StateFlow<QuizUiState> = _uiState.asStateFlow()

    private val current: Question get() = questions[index]

    private fun buildState(phase: AnswerPhase, selected: Int?, seconds: Int) = QuizUiState(
        questionNumber = index + 1,
        totalQuestions = questions.size,
        prompt = current.prompt,
        options = current.options,
        selectedIndex = selected,
        correctIndex = if (phase == AnswerPhase.REVEALED) current.correctIndex else null,
        phase = phase,
        score = score,
        secondsLeft = seconds,
        isLastQuestion = index == questions.size - 1,
        finished = false,
        passed = false,
    )

    fun selectOption(optionIndex: Int) {
        if (_uiState.value.phase != AnswerPhase.ANSWERING) return
        _uiState.value = _uiState.value.copy(selectedIndex = optionIndex)
    }

    /** Grades the current question, reveals the answer. */
    fun confirm() {
        if (_uiState.value.phase != AnswerPhase.ANSWERING) return
        if (_uiState.value.selectedIndex == current.correctIndex) score++
        reveal()
    }

    /** Called when the countdown reaches zero with no confirm. */
    fun onTimeExpired() {
        if (_uiState.value.phase != AnswerPhase.ANSWERING) return
        if (_uiState.value.selectedIndex == current.correctIndex) score++
        reveal()
    }

    private fun reveal() {
        _uiState.value = _uiState.value.copy(
            phase = AnswerPhase.REVEALED,
            correctIndex = current.correctIndex,
            score = score,
        )
    }

    /** Advances to the next question, or finishes the quiz. */
    fun next() {
        if (index == questions.size - 1) {
            _uiState.value = _uiState.value.copy(
                finished = true,
                passed = score >= config.passThreshold,
                score = score,
            )
            return
        }
        index++
        _uiState.value = buildState(AnswerPhase.ANSWERING, selected = null, seconds = totalSeconds)
    }

    /** One-second countdown tick; expires the question when it hits zero. */
    fun tick() {
        val s = _uiState.value
        if (s.phase != AnswerPhase.ANSWERING) return
        val remaining = (s.secondsLeft - 1).coerceAtLeast(0)
        _uiState.value = s.copy(secondsLeft = remaining)
        if (remaining == 0) onTimeExpired()
    }
}
```

- [ ] **Step 5: Run the test to verify it passes**

Run:
```bash
cd /Users/abdalqaderalnajjar/StudioProjects/zeta-2nd-android && JAVA_HOME=/Users/abdalqaderalnajjar/Library/Java/JavaVirtualMachines/azul-17.0.8.1/Contents/Home ./gradlew :app:testDebugUnitTest --tests "com.Elkood.ling_en4.ui.screens.quiz.QuizViewModelTest" --console=plain
```
Expected: PASS (8 tests).

- [ ] **Step 6: Commit**

```bash
git add app/src/main/java/com/Elkood/ling_en4/ui/screens/quiz/QuizUiState.kt app/src/main/java/com/Elkood/ling_en4/ui/screens/quiz/QuizViewModel.kt app/src/test/java/com/Elkood/ling_en4/ui/screens/quiz/QuizViewModelTest.kt
git commit -m "feat: add QuizViewModel state machine with unit tests"
```

---

## Task 7: `QuizSound` helper (correct/wrong playback + sound pref)

**Files:**
- Create: `app/src/main/java/com/Elkood/ling_en4/ui/screens/quiz/QuizSound.kt`

**Interfaces:**
- Consumes: `R.raw.correct`, `R.raw.wrong` (exist in `res/raw/`); pref file `"saveData"` key `"switch1"` (default `true`) — matches original.
- Produces: `class QuizSound(context: Context)` with `fun playCorrect()`, `fun playWrong()`, `fun release()`. Honors the sound-enabled pref internally.

- [ ] **Step 1: Write the helper**

Original behavior: sound plays only if pref `"switch1"` is `true` (default true); `SoundPlayer.player` was a shared static `MediaPlayer` reset each play. Replicate with a private field released between plays.

```kotlin
package com.Elkood.ling_en4.ui.screens.quiz

import android.content.Context
import android.media.MediaPlayer
import com.Elkood.ling_en4.R

class QuizSound(private val context: Context) {

    private var player: MediaPlayer? = null

    private fun soundEnabled(): Boolean =
        context.getSharedPreferences("saveData", Context.MODE_PRIVATE)
            .getBoolean("switch1", true)

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

- [ ] **Step 2: Compile to verify it resolves**

Run:
```bash
cd /Users/abdalqaderalnajjar/StudioProjects/zeta-2nd-android && JAVA_HOME=/Users/abdalqaderalnajjar/Library/Java/JavaVirtualMachines/azul-17.0.8.1/Contents/Home ./gradlew :app:compileDebugKotlin --console=plain
```
Expected: `BUILD SUCCESSFUL`.

- [ ] **Step 3: Commit**

```bash
git add app/src/main/java/com/Elkood/ling_en4/ui/screens/quiz/QuizSound.kt
git commit -m "feat: add QuizSound helper honoring the sound pref"
```

---

## Task 8: `InteractiveQuizScreen` composable (pixel-faithful)

**Files:**
- Create: `app/src/main/java/com/Elkood/ling_en4/ui/screens/quiz/InteractiveQuizScreen.kt`

**Interfaces:**
- Consumes: `QuizViewModel`, `QuizUiState`, `AnswerPhase`, `ZetaColors`, `MuliFontFamily`, `PrimaryButton`, `QuizSound`.
- Produces: `@Composable fun InteractiveQuizScreen(viewModel: QuizViewModel, onFinished: (score: Int, passed: Boolean) -> Unit)`.
- Layout mirrors `activity_quiz_vocabulary.xml`: a top row of three cards ("Quiz  N / Total", timer with `ic_timer`, "Point  score"); a "Check the correct answer :" label; the question in a `grey_Light` box; four radio options inside a bordered group; a bottom `PrimaryButton` labeled `Confirm` → `Next` → `Finish`. LTR direction (`layoutDirection = Ltr`).

Behavioral rules to reproduce exactly:
- Countdown text format `%02d:%02d` (mm:ss); turns `ZetaColors.WrongText` (red) when `secondsLeft < 15`, else `ZetaColors.OnSurface`.
- On `confirm()`: if selection was correct play correct sound else wrong sound. In REVEALED phase, color every option `WrongText` (red) and the correct option `ZetaColors.Correct` (green) — matching `showSolution()`.
- Button label: `ANSWERING` → "Confirm"; `REVEALED` and not last → "Next"; `REVEALED` and last → "Finish".
- Button in ANSWERING triggers `confirm()`; in REVEALED triggers `next()`.
- When `uiState.finished` becomes true, call `onFinished(score, passed)`.
- Timer: a `LaunchedEffect(questionNumber, phase)` that, while `phase == ANSWERING`, `delay(1000)` then `viewModel.tick()` in a loop.

- [ ] **Step 1: Write the composable**

```kotlin
package com.Elkood.ling_en4.ui.screens.quiz

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.CompositionLocalProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.Elkood.ling_en4.ui.components.PrimaryButton
import com.Elkood.ling_en4.ui.theme.MuliFontFamily
import com.Elkood.ling_en4.ui.theme.ZetaColors
import kotlinx.coroutines.delay

@Composable
fun InteractiveQuizScreen(
    viewModel: QuizViewModel,
    onFinished: (score: Int, passed: Boolean) -> Unit,
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current
    val sound = rememberQuizSound(context)

    // Countdown loop: one tick per second while answering.
    LaunchedEffect(state.questionNumber, state.phase) {
        while (state.phase == AnswerPhase.ANSWERING) {
            delay(1000)
            viewModel.tick()
        }
    }

    // Terminal navigation.
    LaunchedEffect(state.finished) {
        if (state.finished) onFinished(state.score, state.passed)
    }

    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(ZetaColors.Surface)
                .padding(bottom = 66.dp)
                .verticalScroll(rememberScrollState()),
        ) {
            QuizHeaderRow(state)

            Text(
                text = " Check the correct answer : ",
                fontFamily = MuliFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                modifier = Modifier.padding(start = 20.dp, top = 20.dp, end = 20.dp),
            )

            Text(
                text = state.prompt,
                fontFamily = MuliFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
                color = ZetaColors.QuestionText,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 3.dp)
                    .background(ZetaColors.QuestionBg)
                    .padding(10.dp),
            )

            OptionsGroup(state, onSelect = viewModel::selectOption)
        }

        PrimaryButton(
            text = when {
                state.phase == AnswerPhase.ANSWERING -> "Confirm"
                state.isLastQuestion -> "Finish"
                else -> "Next"
            },
            onClick = {
                if (state.phase == AnswerPhase.ANSWERING) {
                    val correct = state.selectedIndex == state.correctIndexIfKnown(viewModel)
                    viewModel.confirm()
                    if (viewModel.uiState.value.score > state.score) sound.playCorrect()
                    else sound.playWrong()
                } else {
                    viewModel.next()
                }
            },
            modifier = Modifier.fillMaxWidth(),
        )
    }
}
```

Note for the implementer: the "did the score go up" comparison above is awkward; prefer having `confirm()` return a `Boolean` (whether correct) and play the sound off that return value. Update `QuizViewModel.confirm()` to `fun confirm(): Boolean` returning `wasCorrect`, keep the Task 6 tests green (they call `confirm()` and ignore the return — still valid), and simplify the button handler to:

```kotlin
onClick = {
    if (state.phase == AnswerPhase.ANSWERING) {
        if (viewModel.confirm()) sound.playCorrect() else sound.playWrong()
    } else {
        viewModel.next()
    }
}
```

Delete the `correctIndexIfKnown` helper and the score-comparison branch. Then add the supporting composables in the same file:

```kotlin
@Composable
private fun QuizHeaderRow(state: QuizUiState) {
    Row(modifier = Modifier.fillMaxWidth().padding(2.dp)) {
        InfoCard(title = "Quiz", value = "${state.questionNumber} / ${state.totalQuestions}", modifier = Modifier.weight(1f))
        InfoCard(
            title = "Timer",
            value = formatSeconds(state.secondsLeft),
            valueColor = if (state.secondsLeft < 15) ZetaColors.WrongText else ZetaColors.OnSurface,
            modifier = Modifier.weight(1f),
        )
        InfoCard(title = "Point", value = state.score.toString(), modifier = Modifier.weight(1f))
    }
}

@Composable
private fun InfoCard(
    title: String,
    value: String,
    modifier: Modifier = Modifier,
    valueColor: Color = ZetaColors.OnSurface,
) {
    Card(modifier = modifier.padding(2.dp)) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
            Text(
                text = title,
                fontFamily = MuliFontFamily,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth().background(ZetaColors.HeaderBg).padding(4.dp),
            )
            Text(
                text = value,
                fontFamily = MuliFontFamily,
                fontWeight = FontWeight.Bold,
                color = valueColor,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth().padding(4.dp),
            )
        }
    }
}

@Composable
private fun OptionsGroup(state: QuizUiState, onSelect: (Int) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(2.dp),
    ) {
        state.options.forEachIndexed { i, option ->
            val color = when {
                state.phase == AnswerPhase.REVEALED && i == state.correctIndex -> ZetaColors.Correct
                state.phase == AnswerPhase.REVEALED -> ZetaColors.WrongText
                else -> ZetaColors.OptionText
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .selectable(
                        selected = state.selectedIndex == i,
                        enabled = state.phase == AnswerPhase.ANSWERING,
                        onClick = { onSelect(i) },
                    )
                    .padding(4.dp),
            ) {
                RadioButton(
                    selected = state.selectedIndex == i,
                    enabled = state.phase == AnswerPhase.ANSWERING,
                    onClick = { onSelect(i) },
                )
                Text(
                    text = option,
                    color = color,
                    fontFamily = MuliFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                )
            }
        }
    }
}

private fun formatSeconds(totalSeconds: Int): String {
    val m = totalSeconds / 60
    val s = totalSeconds % 60
    return "%02d:%02d".format(m, s)
}
```

Add the missing imports the implementer needs (`androidx.compose.runtime.remember`, `androidx.compose.runtime.DisposableEffect`) and a small `rememberQuizSound`:

```kotlin
@Composable
private fun rememberQuizSound(context: android.content.Context): QuizSound {
    val sound = remember { QuizSound(context) }
    DisposableEffect(Unit) { onDispose { sound.release() } }
    return sound
}
```

- [ ] **Step 2: Update `QuizViewModel.confirm()` to return `Boolean`**

Change the signature and body of `confirm()` in `QuizViewModel.kt`:

```kotlin
    fun confirm(): Boolean {
        if (_uiState.value.phase != AnswerPhase.ANSWERING) return false
        val correct = _uiState.value.selectedIndex == current.correctIndex
        if (correct) score++
        reveal()
        return correct
    }
```

- [ ] **Step 3: Re-run the ViewModel tests (must stay green)**

Run:
```bash
cd /Users/abdalqaderalnajjar/StudioProjects/zeta-2nd-android && JAVA_HOME=/Users/abdalqaderalnajjar/Library/Java/JavaVirtualMachines/azul-17.0.8.1/Contents/Home ./gradlew :app:testDebugUnitTest --tests "com.Elkood.ling_en4.ui.screens.quiz.QuizViewModelTest" --console=plain
```
Expected: PASS (8 tests) — the `Boolean` return is ignored by existing tests.

- [ ] **Step 4: Compile the debug variant to verify the composable builds**

Run:
```bash
cd /Users/abdalqaderalnajjar/StudioProjects/zeta-2nd-android && JAVA_HOME=/Users/abdalqaderalnajjar/Library/Java/JavaVirtualMachines/azul-17.0.8.1/Contents/Home ./gradlew :app:compileDebugKotlin --console=plain
```
Expected: `BUILD SUCCESSFUL`. Fix any missing imports flagged by the compiler.

- [ ] **Step 5: Commit**

```bash
git add app/src/main/java/com/Elkood/ling_en4/ui/screens/quiz/InteractiveQuizScreen.kt app/src/main/java/com/Elkood/ling_en4/ui/screens/quiz/QuizViewModel.kt
git commit -m "feat: add InteractiveQuizScreen composable (pixel-faithful Vocabulary quiz)"
```

---

## Task 9: `ComposeQuizActivity` host + interop launch + high-score persistence

**Files:**
- Create: `app/src/main/java/com/Elkood/ling_en4/ui/screens/quiz/ComposeQuizActivity.kt`
- Modify: `app/src/main/AndroidManifest.xml` (register the new Activity near the other quiz activities, ~line 147)
- Modify: `app/src/main/java/com/Elkood/ling_en4/Views/SecondYear/English_4/BankItemsQuiz.java:100` (repoint the `index == 0` interop launch)

**Interfaces:**
- Consumes: `QuizViewModel`, `InteractiveQuizScreen`, `ZetaTheme`, `quizConfigFor`, `QuizTopic`.
- Produces: `ComposeQuizActivity` — reads the `EXTRA_TOPIC` string extra (a `QuizTopic.name`), builds the config, computes the countdown seconds from the `saveR` pref, hosts `InteractiveQuizScreen`, persists the high score on finish, and finishes the Activity.
- Countdown mapping from original (`saveData`/`saveR`): `1` (default) → 40s, `0` → 30s, `2` → 50s.
- High-score persistence from original: `getSharedPreferences(config.highScorePrefFile).getInt(config.highScorePrefKey, 0)`; if `score` greater, write it back.

- [ ] **Step 1: Write `ComposeQuizActivity.kt`**

```kotlin
package com.Elkood.ling_en4.ui.screens.quiz

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.Factory
import androidx.lifecycle.ViewModel
import com.Elkood.ling_en4.data.QuizConfig
import com.Elkood.ling_en4.data.model.QuizTopic
import com.Elkood.ling_en4.data.quizConfigFor
import com.Elkood.ling_en4.ui.theme.ZetaTheme

class ComposeQuizActivity : ComponentActivity() {

    companion object {
        const val EXTRA_TOPIC = "extra_topic"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val topic = QuizTopic.valueOf(
            intent.getStringExtra(EXTRA_TOPIC) ?: QuizTopic.VOCABULARY.name
        )
        val config = quizConfigFor(topic)
        val seconds = countdownSeconds()

        val viewModel = ViewModelProvider(
            this,
            quizViewModelFactory(config, seconds),
        )[QuizViewModel::class.java]

        setContent {
            ZetaTheme {
                InteractiveQuizScreen(
                    viewModel = viewModel,
                    onFinished = { score, _ ->
                        saveHighScore(config, score)
                        finish()
                    },
                )
            }
        }
    }

    /** Original saveR mapping: 1 (default) -> 40s, 0 -> 30s, 2 -> 50s. */
    private fun countdownSeconds(): Int =
        when (getSharedPreferences("saveData", Context.MODE_PRIVATE).getInt("saveR", 1)) {
            0 -> 30
            2 -> 50
            else -> 40
        }

    private fun saveHighScore(config: QuizConfig, score: Int) {
        val prefs = getSharedPreferences(config.highScorePrefFile, Context.MODE_PRIVATE)
        if (prefs.getInt(config.highScorePrefKey, 0) < score) {
            prefs.edit().putInt(config.highScorePrefKey, score).apply()
        }
    }
}

private fun quizViewModelFactory(config: QuizConfig, seconds: Int) = object : Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        QuizViewModel(config, totalSeconds = seconds) as T
}
```

- [ ] **Step 2: Register the Activity in `AndroidManifest.xml`**

Add inside `<application>` (next to the other quiz activities, e.g. after the `QuizActivity_Vocabulary` entry at line 147–150):

```xml
        <activity
            android:name=".ui.screens.quiz.ComposeQuizActivity"
            android:screenOrientation="fullSensor"
            android:theme="@style/AppTheme" />
```

- [ ] **Step 3: Repoint the Vocabulary launch site (`BankItemsQuiz.java`) to Compose**

`BankItemsQuiz` is the boom-menu Fragment whose `changeBoomButton(int index)` starts each quiz. The `index == 0` branch (line 100) launches `QuizActivity_Vocabulary`. Repoint only that branch to `ComposeQuizActivity` with the topic extra; leave `index == 1..4` and `QuizActivity_Vocabulary.java` itself untouched.

Add the imports near the existing `import ...QuizActivity_Vocabulary;` (line 23):

```java
import com.Elkood.ling_en4.data.model.QuizTopic;
import com.Elkood.ling_en4.ui.screens.quiz.ComposeQuizActivity;
```

Replace the `index == 0` body (lines 99–101):

```java
        if (index == 0) {
            Intent intent = new Intent(getContext(), ComposeQuizActivity.class);
            intent.putExtra(ComposeQuizActivity.EXTRA_TOPIC, QuizTopic.VOCABULARY.name());
            requireView().getContext().startActivity(intent);
        } else if (index == 1) {
```

(The existing `import ...QuizActivity_Vocabulary;` on line 23 may now be unused — leave it; it does no harm and `QuizActivity_Vocabulary` is still referenced by the manifest until the later cleanup phase.)

- [ ] **Step 4: Build the full debug APK (Java + Kotlin interop)**

Run:
```bash
cd /Users/abdalqaderalnajjar/StudioProjects/zeta-2nd-android && JAVA_HOME=/Users/abdalqaderalnajjar/Library/Java/JavaVirtualMachines/azul-17.0.8.1/Contents/Home ./gradlew :app:assembleDebug --console=plain
```
Expected: `BUILD SUCCESSFUL`. Kotlin (`ComposeQuizActivity`, `QuizTopic`) must be visible to the Java caller — if the Java import fails to resolve, confirm the Kotlin classes are `public` (default) and rebuild.

- [ ] **Step 5: Manual device/emulator verification (user-run)**

Install and open the app, navigate to the Vocabulary quiz, and confirm against the original:
- Three top cards show `Quiz N / Total`, the countdown timer, and `Point score`.
- Countdown counts down from 00:40 (default pref); turns red under 15s.
- Selecting an option and tapping **Confirm** reveals all options red with the correct one green, plays the correct/wrong sound (if sound pref on), and the button becomes **Next** (**Finish** on the last question).
- Finishing writes the high score and returns to the `BankItemsQuiz` boom-menu screen.

Run:
```bash
cd /Users/abdalqaderalnajjar/StudioProjects/zeta-2nd-android && JAVA_HOME=/Users/abdalqaderalnajjar/Library/Java/JavaVirtualMachines/azul-17.0.8.1/Contents/Home ./gradlew :app:installDebug --console=plain
```

- [ ] **Step 6: Commit**

```bash
git add app/src/main/java/com/Elkood/ling_en4/ui/screens/quiz/ComposeQuizActivity.kt app/src/main/AndroidManifest.xml app/src/main/java/com/Elkood/ling_en4/Views/SecondYear/English_4/BankItemsQuiz.java
git commit -m "feat: launch Compose Vocabulary quiz from Java hub via interop"
```

---

## Self-Review

**1. Spec coverage (Phase 0 + Phase 1 scope):**
- Kotlin + Compose toolchain → Task 1. ✓
- Theme (colors/type/ZetaTheme) → Task 2. ✓
- Reusable component library (seed) → Task 4 (`PrimaryButton`); more components arrive as later screens need them. ✓
- Unified data model with corrected naming (`Question`, `QuizTopic`, 0-based `correctIndex`) → Task 3. ✓
- Vocabulary content migrated off SQLite to static Kotlin (45 questions, verbatim) → Task 5. ✓
- Latent pass-threshold bug (`score == 46` unreachable) fixed → Task 5 `passThreshold = questions.size` + Global Constraints note. ✓
- MVVM state machine (scoring, countdown, pass rule) → Task 6. ✓
- Sound + prefs parity → Task 7, Task 9. ✓
- Pixel-faithful screen → Task 8. ✓
- View↔Compose interop launch from the existing Java hub → Task 9. ✓
- Navigation-Compose: scaffolding only is required in these phases; the single-Activity NavHost shell is a Phase 2 concern (home shell migration) and is intentionally deferred. Noted, not a gap.

**2. Placeholder scan:** The only `TODO()` is in `quizConfigFor` for the five not-yet-migrated topics — intentional and guarded (VOCABULARY is fully wired). Task 8 Step 1 contains an explicit "prefer this instead" refactor note that Task 8 Step 2 resolves by changing `confirm()` to return `Boolean`; the final code has no placeholder. No "TBD"/"handle edge cases"/"similar to Task N" left.

**3. Type consistency:**
- `Question(prompt, options, correctIndex)` — same signature in Tasks 3, 5, 6, 8. ✓
- `QuizConfig(topic, title, questions, passThreshold, highScorePrefFile, highScorePrefKey)` — same in Tasks 5, 6, 9. ✓
- `QuizViewModel(config, totalSeconds, shuffle)` — same in Tasks 6, 8, 9. ✓
- `confirm(): Boolean` — introduced returning `Unit` in Task 6, changed to `Boolean` in Task 8 Step 2; Task 6 tests ignore the return so they stay green. ✓
- `onFinished(score, passed)` — same in Tasks 8 and 9. ✓
- `EXTRA_TOPIC` — defined in Task 9 `ComposeQuizActivity`, used by the Java caller in Task 9 Step 3. ✓

**Known follow-ups (out of scope for Phase 0/1, tracked for later phases):**
- The double-tap-to-exit back behavior and the `SweetAlertDialog` success popup are simplified in Phase 1 (finish returns to the boom menu; pass/fail is persisted). If pixel-faithful parity requires the success dialog, add it as a Compose `AlertDialog` in a Phase 1 follow-up task — flag during device verification.
- `onSaveInstanceState` restoration (score/timer across rotation) is not reproduced; the quiz Activity is `screenOrientation="fullSensor"` so rotation is possible. If required, add `SavedStateHandle` support to `QuizViewModel` in a follow-up.
