# Phase 3 — Paper Quizzes: Compose Migration Implementation Plan

> **For agentic workers:** REQUIRED SUB-SKILL: Use superpowers:subagent-driven-development (recommended) or superpowers:executing-plans to implement this plan task-by-task. Steps use checkbox (`- [ ]`) syntax for tracking.

**Goal:** Replace the nine near-identical legacy "exam paper" Activities with one data-driven Jetpack Compose screen (`PaperQuizScreen`) parameterized by an `ExamPaper` enum, backed by an in-memory `En4Papers` content table converted verbatim from the legacy `En4R20xx*Constants` files.

**Architecture:** Pure data (`PaperQuestion`, `Paper`, `ExamPaper`) + a content object (`En4Papers`) feed a stateless card (`PaperQuestionCard`) and a screen (`PaperQuizScreen`) that hoists per-question reveal state with `rememberSaveable`. A `PaperListScreen` grid replaces the legacy tab-0 fragment; tapping a card launches `PaperQuizActivity` (Intent-based, mirroring the existing `ComposeQuizActivity` precedent). No ViewModel, no repository, no network — the only mutable state is each question's ephemeral "selected option / revealed".

**Tech Stack:** Kotlin 2.0.21, Jetpack Compose (compose-bom 2024.09.00), Material3, JUnit4 unit tests. Single-module Android app (`:app`), package `com.Elkood.ling_en4`.

## Global Constraints

Every task's requirements implicitly include this section.

- **Build env (MANDATORY):** Prefix every Gradle command inline with `JAVA_HOME=/Users/abdalqaderalnajjar/Library/Java/JavaVirtualMachines/azul-17.0.8.1/Contents/Home`. There is no shell state persistence between tool calls.
- **Build floors (do not lower):** compileSdk 36, targetSdk 36, minSdk 21, versionCode 11, versionName "1.4.7", compose-bom 2024.09.00, Kotlin 2.0.21, AGP 8.9.1.
- **Verbatim content rule:** All user-facing English text (prompts, options, passages, headers) and Arabic card copy is preserved **exactly** as in the legacy source — including legacy typos, trailing spaces, and odd spacing. Never "fix" content text.
- **Naming standard:** packages all-lowercase no underscores; PascalCase classes/composables; camelCase members no abbreviations; enums for closed variant sets. Legacy typos are fixed **in code identifiers only**, never in content strings.
- **RTL/LTR:** App UI is Arabic RTL globally. Quiz *content* (passages, questions, options) is English and must be wrapped `CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr)` (same pattern as the existing `InteractiveQuizScreen`). The `PaperListScreen` cards stay RTL (app default).
- **No new features:** No score, pass/fail, timer, high-score, or result contract — these papers never had them (master spec §9).
- **No legacy deletion / no NavHost consolidation this phase** — legacy Pattern-B files stay in place (swept in Phase 7); Compose activities stay Intent-launched.
- **Commits:** Conventional Commits. Every commit message ends with the trailer:
  `Co-Authored-By: Claude Opus 4.8 <noreply@anthropic.com>`

### Conversion Recipe (used by Tasks 5–13)

The legacy `Quiz` constructor is:
`new Quiz(String quiz, int r1_check, int r2_check, int r3_check, int r4_check, String r1_text, String r2_text, String r3_text, String r4_text)`

Convention: `check == 1` → the single correct option; `check == 0` → a wrong option; `check == -1` → option absent (hidden row, for 2- and 3-option questions).

The `q(...)` builder (Task 4) mirrors this constructor argument-for-argument, so each legacy `new Quiz(P, a,b,c,d, t1,t2,t3,t4)` becomes exactly `q(P, a,b,c,d, t1,t2,t3,t4)` — a mechanical transcription. The builder drops `-1` options and computes `correctIndex` from the `== 1` flag.

### Header / Passage Recipe (used by Tasks 5–13)

Each paper's non-question text lives in `app/src/main/res/layout/activity_r20xx.xml` (with some strings indirected through `app/src/main/res/values/strings.xml`):

- **`headerTitle`** = the text of the header `CardView`'s TextView(s) (university/faculty line + form line). Resolve any `@string/...` to its literal value in `strings.xml`. If there are two header lines, join them with `"\n"`.
- **`instruction`** = the instruction TextView (e.g. `@string/_1_read_the_following_passage_and_answer_the_qustions`). Use `""` if the paper has none.
- **`passage`** = the reading-passage `CardView` TextView (e.g. `@string/r2018`). Use `""` for grammar-only papers with no passage.
- Preserve every string **verbatim**, including typos, trailing spaces, and line breaks.

---

## Task 1: `PaperQuestion` data model

**Files:**
- Create: `app/src/main/java/com/Elkood/ling_en4/data/model/PaperQuestion.kt`
- Test: `app/src/test/java/com/Elkood/ling_en4/data/model/PaperQuestionTest.kt`

**Interfaces:**
- Produces: `data class PaperQuestion(val prompt: String, val options: List<String>, val correctIndex: Int)` with `init` enforcing `options.size in 2..4` and `correctIndex in options.indices`.

- [ ] **Step 1: Write the failing test**

```kotlin
package com.Elkood.ling_en4.data.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Test

class PaperQuestionTest {
    @Test
    fun stores_prompt_options_and_correct_index() {
        val q = PaperQuestion("p", listOf("a", "b", "c"), correctIndex = 2)
        assertEquals("c", q.options[q.correctIndex])
    }

    @Test
    fun allows_two_options() {
        assertEquals(2, PaperQuestion("p", listOf("a", "b"), 0).options.size)
    }

    @Test
    fun rejects_fewer_than_two_options() {
        assertThrows(IllegalArgumentException::class.java) {
            PaperQuestion("p", listOf("a"), 0)
        }
    }

    @Test
    fun rejects_more_than_four_options() {
        assertThrows(IllegalArgumentException::class.java) {
            PaperQuestion("p", listOf("a", "b", "c", "d", "e"), 0)
        }
    }

    @Test
    fun rejects_out_of_range_correct_index() {
        assertThrows(IllegalArgumentException::class.java) {
            PaperQuestion("p", listOf("a", "b"), 2)
        }
    }
}
```

- [ ] **Step 2: Run test to verify it fails**

Run: `JAVA_HOME=/Users/abdalqaderalnajjar/Library/Java/JavaVirtualMachines/azul-17.0.8.1/Contents/Home ./gradlew :app:testDebugUnitTest --tests "com.Elkood.ling_en4.data.model.PaperQuestionTest"`
Expected: FAIL — `PaperQuestion` unresolved reference.

- [ ] **Step 3: Write minimal implementation**

```kotlin
package com.Elkood.ling_en4.data.model

data class PaperQuestion(
    val prompt: String,
    val options: List<String>,
    val correctIndex: Int,
) {
    init {
        require(options.size in 2..4) { "A PaperQuestion must have 2..4 options, got ${options.size}" }
        require(correctIndex in options.indices) { "correctIndex $correctIndex out of range for ${options.size} options" }
    }
}
```

- [ ] **Step 4: Run test to verify it passes**

Run: `JAVA_HOME=/Users/abdalqaderalnajjar/Library/Java/JavaVirtualMachines/azul-17.0.8.1/Contents/Home ./gradlew :app:testDebugUnitTest --tests "com.Elkood.ling_en4.data.model.PaperQuestionTest"`
Expected: PASS (5 tests).

- [ ] **Step 5: Commit**

```bash
git add app/src/main/java/com/Elkood/ling_en4/data/model/PaperQuestion.kt app/src/test/java/com/Elkood/ling_en4/data/model/PaperQuestionTest.kt
git commit -m "feat(paperquiz): add PaperQuestion model

Co-Authored-By: Claude Opus 4.8 <noreply@anthropic.com>"
```

---

## Task 2: `ExamPaper` enum + `Paper` data model

**Files:**
- Create: `app/src/main/java/com/Elkood/ling_en4/data/model/ExamPaper.kt`
- Create: `app/src/main/java/com/Elkood/ling_en4/data/model/Paper.kt`
- Test: `app/src/test/java/com/Elkood/ling_en4/data/model/ExamPaperTest.kt`

**Interfaces:**
- Consumes: `PaperQuestion` (Task 1).
- Produces:
  - `enum class ExamPaper(val cardTitle: String, val cardSubtitle: String)` with nine entries in display order `R2018, R2017, R2017_ALT, R2016, R2016_ALT, R2015, R2014, R2013, R2012`.
  - `data class Paper(val examPaper: ExamPaper, val headerTitle: String, val instruction: String, val passage: String, val questions: List<PaperQuestion>)`.

**Card copy (verbatim from legacy `Courses_Quiz_Screen` item_repeater names + `choice.xml`/`choice2.xml` radio labels — confirmed against `Adapter_Courses` dialog→activity mapping):**
- `R2018` title `"دورة 2018 "`, subtitle `"الدورة الاولى فقط "`
- `R2017` title `"دورة 2017 "`, subtitle `"الأولى "` (choice2 `f1` → `r2017`)
- `R2017_ALT` title `"دورة 2017 "`, subtitle `"الثانية "` (choice2 `f2` → `r2017_2`)
- `R2016` title `"دورة 2016 "`, subtitle `"الأولى "` (choice `arbic_lan` → `r2016`)
- `R2016_ALT` title `"دورة 2016 "`, subtitle `"التكميلية"` (choice `eng_lan` → `R2016_3`)
- `R2015` title `"دورة 2015 "`, subtitle `"الدورة الاولى فقط "`
- `R2014` title `"دورة 2014 "`, subtitle `"الدورة الاولى فقط  "` (note: two trailing spaces)
- `R2013` title `"دورة 2013 "`, subtitle `"الدورة الاولى فقط "`
- `R2012` title `"دورة 2012 "`, subtitle `"الدورة الثالثة فقط "`

- [ ] **Step 1: Write the failing test**

```kotlin
package com.Elkood.ling_en4.data.model

import org.junit.Assert.assertEquals
import org.junit.Test

class ExamPaperTest {
    @Test
    fun has_nine_variants_in_display_order() {
        assertEquals(
            listOf("R2018", "R2017", "R2017_ALT", "R2016", "R2016_ALT", "R2015", "R2014", "R2013", "R2012"),
            ExamPaper.entries.map { it.name },
        )
    }

    @Test
    fun carries_verbatim_card_copy() {
        assertEquals("دورة 2018 ", ExamPaper.R2018.cardTitle)
        assertEquals("الدورة الاولى فقط ", ExamPaper.R2018.cardSubtitle)
        assertEquals("الأولى ", ExamPaper.R2017.cardSubtitle)
        assertEquals("الثانية ", ExamPaper.R2017_ALT.cardSubtitle)
        assertEquals("الأولى ", ExamPaper.R2016.cardSubtitle)
        assertEquals("التكميلية", ExamPaper.R2016_ALT.cardSubtitle)
        assertEquals("الدورة الثالثة فقط ", ExamPaper.R2012.cardSubtitle)
    }

    @Test
    fun paper_holds_its_fields() {
        val p = Paper(ExamPaper.R2012, "h", "", "", listOf(PaperQuestion("q", listOf("a", "b"), 0)))
        assertEquals(ExamPaper.R2012, p.examPaper)
        assertEquals(1, p.questions.size)
    }
}
```

- [ ] **Step 2: Run test to verify it fails**

Run: `JAVA_HOME=/Users/abdalqaderalnajjar/Library/Java/JavaVirtualMachines/azul-17.0.8.1/Contents/Home ./gradlew :app:testDebugUnitTest --tests "com.Elkood.ling_en4.data.model.ExamPaperTest"`
Expected: FAIL — `ExamPaper`/`Paper` unresolved.

- [ ] **Step 3: Write minimal implementation**

`ExamPaper.kt`:
```kotlin
package com.Elkood.ling_en4.data.model

/** The nine legacy "exam paper" variants, in card display order (newest first).
 * R2017/R2017_ALT = legacy r2017/r2017_2; R2016/R2016_ALT = legacy r2016/R2016_3. */
enum class ExamPaper(val cardTitle: String, val cardSubtitle: String) {
    R2018("دورة 2018 ", "الدورة الاولى فقط "),
    R2017("دورة 2017 ", "الأولى "),
    R2017_ALT("دورة 2017 ", "الثانية "),
    R2016("دورة 2016 ", "الأولى "),
    R2016_ALT("دورة 2016 ", "التكميلية"),
    R2015("دورة 2015 ", "الدورة الاولى فقط "),
    R2014("دورة 2014 ", "الدورة الاولى فقط  "),
    R2013("دورة 2013 ", "الدورة الاولى فقط "),
    R2012("دورة 2012 ", "الدورة الثالثة فقط "),
}
```

`Paper.kt`:
```kotlin
package com.Elkood.ling_en4.data.model

data class Paper(
    val examPaper: ExamPaper,
    val headerTitle: String,
    val instruction: String,
    val passage: String,
    val questions: List<PaperQuestion>,
)
```

- [ ] **Step 4: Run test to verify it passes**

Run: `JAVA_HOME=/Users/abdalqaderalnajjar/Library/Java/JavaVirtualMachines/azul-17.0.8.1/Contents/Home ./gradlew :app:testDebugUnitTest --tests "com.Elkood.ling_en4.data.model.ExamPaperTest"`
Expected: PASS (3 tests).

- [ ] **Step 5: Commit**

```bash
git add app/src/main/java/com/Elkood/ling_en4/data/model/ExamPaper.kt app/src/main/java/com/Elkood/ling_en4/data/model/Paper.kt app/src/test/java/com/Elkood/ling_en4/data/model/ExamPaperTest.kt
git commit -m "feat(paperquiz): add ExamPaper enum and Paper model

Co-Authored-By: Claude Opus 4.8 <noreply@anthropic.com>"
```

---

## Task 3: `optionVisual` reveal helper

**Files:**
- Create: `app/src/main/java/com/Elkood/ling_en4/ui/screens/paperquiz/OptionVisual.kt`
- Test: `app/src/test/java/com/Elkood/ling_en4/ui/screens/paperquiz/OptionVisualTest.kt`

**Interfaces:**
- Produces: `enum class OptionVisual { Normal, Correct, Wrong }` and `fun optionVisual(index: Int, correctIndex: Int, revealed: Boolean): OptionVisual`.

- [ ] **Step 1: Write the failing test**

```kotlin
package com.Elkood.ling_en4.ui.screens.paperquiz

import org.junit.Assert.assertEquals
import org.junit.Test

class OptionVisualTest {
    @Test
    fun normal_for_all_indices_before_reveal() {
        assertEquals(OptionVisual.Normal, optionVisual(index = 0, correctIndex = 2, revealed = false))
        assertEquals(OptionVisual.Normal, optionVisual(index = 2, correctIndex = 2, revealed = false))
    }

    @Test
    fun correct_only_for_correct_index_after_reveal() {
        assertEquals(OptionVisual.Correct, optionVisual(index = 2, correctIndex = 2, revealed = true))
    }

    @Test
    fun wrong_for_other_indices_after_reveal_regardless_of_user_pick() {
        assertEquals(OptionVisual.Wrong, optionVisual(index = 0, correctIndex = 2, revealed = true))
        assertEquals(OptionVisual.Wrong, optionVisual(index = 1, correctIndex = 2, revealed = true))
        assertEquals(OptionVisual.Wrong, optionVisual(index = 3, correctIndex = 2, revealed = true))
    }
}
```

- [ ] **Step 2: Run test to verify it fails**

Run: `JAVA_HOME=/Users/abdalqaderalnajjar/Library/Java/JavaVirtualMachines/azul-17.0.8.1/Contents/Home ./gradlew :app:testDebugUnitTest --tests "com.Elkood.ling_en4.ui.screens.paperquiz.OptionVisualTest"`
Expected: FAIL — `optionVisual` unresolved.

- [ ] **Step 3: Write minimal implementation**

```kotlin
package com.Elkood.ling_en4.ui.screens.paperquiz

/** Visual state of one option row after (or before) the "Check" reveal. */
enum class OptionVisual { Normal, Correct, Wrong }

/** Faithful to legacy: before reveal everything is Normal; after reveal the
 * correct option is Correct and EVERY other present option is Wrong,
 * independent of the user's own selection. */
fun optionVisual(index: Int, correctIndex: Int, revealed: Boolean): OptionVisual = when {
    !revealed -> OptionVisual.Normal
    index == correctIndex -> OptionVisual.Correct
    else -> OptionVisual.Wrong
}
```

- [ ] **Step 4: Run test to verify it passes**

Run: `JAVA_HOME=/Users/abdalqaderalnajjar/Library/Java/JavaVirtualMachines/azul-17.0.8.1/Contents/Home ./gradlew :app:testDebugUnitTest --tests "com.Elkood.ling_en4.ui.screens.paperquiz.OptionVisualTest"`
Expected: PASS (3 tests).

- [ ] **Step 5: Commit**

```bash
git add app/src/main/java/com/Elkood/ling_en4/ui/screens/paperquiz/OptionVisual.kt app/src/test/java/com/Elkood/ling_en4/ui/screens/paperquiz/OptionVisualTest.kt
git commit -m "feat(paperquiz): add optionVisual reveal helper

Co-Authored-By: Claude Opus 4.8 <noreply@anthropic.com>"
```

---

## Task 4: `q()` content builder

**Files:**
- Create: `app/src/main/java/com/Elkood/ling_en4/data/content/papers/PaperBuilder.kt`
- Test: `app/src/test/java/com/Elkood/ling_en4/data/content/papers/PaperBuilderTest.kt`

**Interfaces:**
- Consumes: `PaperQuestion` (Task 1).
- Produces: `internal fun q(prompt: String, r1Check: Int, r2Check: Int, r3Check: Int, r4Check: Int, r1Text: String, r2Text: String, r3Text: String, r4Text: String): PaperQuestion` — drops options whose flag is `-1`, sets `correctIndex` to the single option whose flag is `1`, and requires exactly one correct flag.

Note: `internal` members of `main` are visible to the module's unit tests, so the test can call `q(...)` directly.

- [ ] **Step 1: Write the failing test**

```kotlin
package com.Elkood.ling_en4.data.content.papers

import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Test

class PaperBuilderTest {
    @Test
    fun drops_absent_options_and_finds_correct_index() {
        val q = q("p", 0, 1, -1, -1, "a", "b", "c", "d")
        assertEquals(listOf("a", "b"), q.options)
        assertEquals(1, q.correctIndex)
    }

    @Test
    fun keeps_all_four_when_no_flag_is_minus_one() {
        val q = q("p", 1, 0, 0, 0, "a", "b", "c", "d")
        assertEquals(4, q.options.size)
        assertEquals(0, q.correctIndex)
    }

    @Test
    fun keeps_three_options() {
        val q = q("p", 0, 0, 1, -1, "a", "b", "c", "d")
        assertEquals(listOf("a", "b", "c"), q.options)
        assertEquals(2, q.correctIndex)
    }

    @Test
    fun rejects_when_no_correct_flag() {
        assertThrows(IllegalArgumentException::class.java) {
            q("p", 0, 0, -1, -1, "a", "b", "c", "d")
        }
    }
}
```

- [ ] **Step 2: Run test to verify it fails**

Run: `JAVA_HOME=/Users/abdalqaderalnajjar/Library/Java/JavaVirtualMachines/azul-17.0.8.1/Contents/Home ./gradlew :app:testDebugUnitTest --tests "com.Elkood.ling_en4.data.content.papers.PaperBuilderTest"`
Expected: FAIL — `q` unresolved.

- [ ] **Step 3: Write minimal implementation**

```kotlin
package com.Elkood.ling_en4.data.content.papers

import com.Elkood.ling_en4.data.model.PaperQuestion

/** Mirrors the legacy `Quiz(quiz, r1..r4_check, r1..r4_text)` constructor argument-for-argument.
 * Drops options whose check flag is -1 (absent rows) and sets correctIndex to the single
 * option whose flag is 1. */
internal fun q(
    prompt: String,
    r1Check: Int, r2Check: Int, r3Check: Int, r4Check: Int,
    r1Text: String, r2Text: String, r3Text: String, r4Text: String,
): PaperQuestion {
    val present = listOf(
        r1Check to r1Text,
        r2Check to r2Text,
        r3Check to r3Text,
        r4Check to r4Text,
    ).filter { it.first != -1 }
    require(present.count { it.first == 1 } == 1) {
        "Question must have exactly one correct option (flag==1): $prompt"
    }
    return PaperQuestion(
        prompt = prompt,
        options = present.map { it.second },
        correctIndex = present.indexOfFirst { it.first == 1 },
    )
}
```

- [ ] **Step 4: Run test to verify it passes**

Run: `JAVA_HOME=/Users/abdalqaderalnajjar/Library/Java/JavaVirtualMachines/azul-17.0.8.1/Contents/Home ./gradlew :app:testDebugUnitTest --tests "com.Elkood.ling_en4.data.content.papers.PaperBuilderTest"`
Expected: PASS (4 tests).

- [ ] **Step 5: Commit**

```bash
git add app/src/main/java/com/Elkood/ling_en4/data/content/papers/PaperBuilder.kt app/src/test/java/com/Elkood/ling_en4/data/content/papers/PaperBuilderTest.kt
git commit -m "feat(paperquiz): add q() content builder

Co-Authored-By: Claude Opus 4.8 <noreply@anthropic.com>"
```

---

## Tasks 5–13: Per-paper content conversion

Each of these nine tasks converts one legacy `En4R20xx*Constants.java` into an `internal val` in its own file under `data/content/papers/`, lifting the header/instruction/passage from the matching `activity_r20xx.xml` per the **Header / Passage Recipe**, and transcribing every `new Quiz(...)` via the `q(...)` builder per the **Conversion Recipe** (both in Global Constraints).

**Shared conventions for all nine tasks:**
- File package: `com.Elkood.ling_en4.data.content.papers`; imports `com.Elkood.ling_en4.data.model.ExamPaper` and `com.Elkood.ling_en4.data.model.Paper`.
- Transcribe **verbatim** — every string, space, and typo exactly as in the source. Do not reorder, merge, or "clean up" questions.
- The `q(...)` argument order is identical to the legacy `new Quiz(...)` argument order, so each `list.add(new Quiz(P, a,b,c,d, t1,t2,t3,t4));` becomes one `q(P, a,b,c,d, t1,t2,t3,t4),` entry.
- Each task's test asserts the exact question count and that every question satisfies the model invariants (2–4 options, in-range `correctIndex`, non-blank prompt). Construction already enforces the invariants; the count is the completeness gate.
- Run only that task's test class each cycle.

| Task | `val` name | File | Legacy source Constants | Legacy layout | Count |
|------|-----------|------|-------------------------|---------------|-------|
| 5  | `paperR2018`    | `PaperR2018.kt`    | `En4R2018Constants.java`   | `activity_r2018.xml`  | 60 |
| 6  | `paperR2017`    | `PaperR2017.kt`    | `En4R2017Constants.java`   | `activity_r2017.xml`  | 54 |
| 7  | `paperR2017Alt` | `PaperR2017Alt.kt` | `En4R2017_2Constants.java` | `activity_r2017_2.xml` (or the `r2017_2` layout) | 60 |
| 8  | `paperR2016`    | `PaperR2016.kt`    | `En4R2016Constants.java`   | `activity_r2016.xml`  | 60 |
| 9  | `paperR2016Alt` | `PaperR2016Alt.kt` | `En4R2016_3Constants.java` | `activity_r2016_3.xml` (or the `R2016_3` layout) | 60 |
| 10 | `paperR2015`    | `PaperR2015.kt`    | `En4R2015Constants.java`   | `activity_r2015.xml`  | 60 |
| 11 | `paperR2014`    | `PaperR2014.kt`    | `En4R2014Constants.java`   | `activity_r2014.xml`  | 60 |
| 12 | `paperR2013`    | `PaperR2013.kt`    | `En4R2013Constants.java`   | `activity_r2013.xml`  | 60 |
| 13 | `paperR2012`    | `PaperR2012.kt`    | `En4R2012Constants.java`   | `activity_r2012.xml`  | 54 |

> The exact `activity_r20xx.xml` filename may differ per paper (legacy naming is inconsistent — e.g. the R2016_3 / r2017_2 activities). Locate the layout that the corresponding legacy Activity calls `setContentView(...)` on, and lift the header/instruction/passage from there. If a paper's layout has no passage CardView, use `passage = ""`.

Each task follows the same five steps. The template below is shown for **Task 5 (`paperR2018`, count 60)**; Tasks 6–13 are identical in shape with their own `val` name, file, source, layout, and count from the table.

### Task 5: Convert R2018 (60 questions) — worked template

**Files:**
- Create: `app/src/main/java/com/Elkood/ling_en4/data/content/papers/PaperR2018.kt`
- Test: `app/src/test/java/com/Elkood/ling_en4/data/content/papers/PaperR2018Test.kt`

**Interfaces:**
- Consumes: `q(...)` (Task 4), `Paper`/`ExamPaper` (Task 2).
- Produces: `internal val paperR2018: Paper`.

- [ ] **Step 1: Write the failing test**

```kotlin
package com.Elkood.ling_en4.data.content.papers

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class PaperR2018Test {
    @Test
    fun has_expected_question_count() {
        assertEquals(60, paperR2018.questions.size)
    }

    @Test
    fun every_question_has_valid_shape() {
        paperR2018.questions.forEach { q ->
            assertTrue("options 2..4", q.options.size in 2..4)
            assertTrue("correctIndex in range", q.correctIndex in q.options.indices)
            assertTrue("prompt non-blank", q.prompt.isNotBlank())
        }
    }
}
```

- [ ] **Step 2: Run test to verify it fails**

Run: `JAVA_HOME=/Users/abdalqaderalnajjar/Library/Java/JavaVirtualMachines/azul-17.0.8.1/Contents/Home ./gradlew :app:testDebugUnitTest --tests "com.Elkood.ling_en4.data.content.papers.PaperR2018Test"`
Expected: FAIL — `paperR2018` unresolved.

- [ ] **Step 3: Write the implementation**

Open `app/src/main/java/com/Elkood/ling_en4/Constants/En4/En4R2018Constants.java` and `app/src/main/res/layout/activity_r2018.xml` (+ `strings.xml` for `@string/university_of_aleppo_faculty_of_informatic`, `@string/_1_read_the_following_passage_and_answer_the_qustions`, `@string/r2018`). Transcribe verbatim. Structure:

```kotlin
package com.Elkood.ling_en4.data.content.papers

import com.Elkood.ling_en4.data.model.ExamPaper
import com.Elkood.ling_en4.data.model.Paper

internal val paperR2018: Paper = Paper(
    examPaper = ExamPaper.R2018,
    headerTitle = "<university/faculty @string value>\nForm / A / 2018 - One Term",
    instruction = "<@string/_1_read_the_following_passage_and_answer_the_qustions value>",
    passage = "<@string/r2018 value>",
    questions = listOf(
        // one q(...) per legacy `new Quiz(...)`, in source order, e.g.:
        // q("1. ...", 0, 1, 0, 0, "A. ...", "B. ...", "C. ...", "D. ..."),
        // ... 60 entries total ...
    ),
)
```

(For reference, the exact transcription shape from `En4R2012Constants` question 1 is:
`list.add(new Quiz("1.\t The most suitable time clause...", 0, 1, 0, 0, " A. Once", "B. when", "C. before ", "D. until "));`
→ `q("1.\t The most suitable time clause...", 0, 1, 0, 0, " A. Once", "B. when", "C. before ", "D. until "),`. Preserve the tab, leading/trailing spaces, and every character.)

- [ ] **Step 4: Run test to verify it passes**

Run: `JAVA_HOME=/Users/abdalqaderalnajjar/Library/Java/JavaVirtualMachines/azul-17.0.8.1/Contents/Home ./gradlew :app:testDebugUnitTest --tests "com.Elkood.ling_en4.data.content.papers.PaperR2018Test"`
Expected: PASS (2 tests). If the count assertion fails, a question was dropped or duplicated — diff against the source. If a `require`/`IllegalArgumentException` fires at construction, a question's flags don't have exactly one `1`, or an option count is out of range — re-check that row against the source.

- [ ] **Step 5: Commit**

```bash
git add app/src/main/java/com/Elkood/ling_en4/data/content/papers/PaperR2018.kt app/src/test/java/com/Elkood/ling_en4/data/content/papers/PaperR2018Test.kt
git commit -m "feat(paperquiz): convert R2018 paper content

Co-Authored-By: Claude Opus 4.8 <noreply@anthropic.com>"
```

### Task 6: Convert R2017 (54 questions)

**Files:**
- Create: `app/src/main/java/com/Elkood/ling_en4/data/content/papers/PaperR2017.kt`
- Test: `app/src/test/java/com/Elkood/ling_en4/data/content/papers/PaperR2017Test.kt`

**Interfaces:**
- Consumes: `q(...)` (Task 4), `Paper`/`ExamPaper` (Task 2).
- Produces: `internal val paperR2017: Paper` (uses `ExamPaper.R2017`).

- [ ] **Step 1: Write the failing test**

```kotlin
package com.Elkood.ling_en4.data.content.papers

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class PaperR2017Test {
    @Test
    fun has_expected_question_count() {
        assertEquals(54, paperR2017.questions.size)
    }

    @Test
    fun every_question_has_valid_shape() {
        paperR2017.questions.forEach { q ->
            assertTrue("options 2..4", q.options.size in 2..4)
            assertTrue("correctIndex in range", q.correctIndex in q.options.indices)
            assertTrue("prompt non-blank", q.prompt.isNotBlank())
        }
    }
}
```

- [ ] **Step 2: Run test to verify it fails**

Run: `JAVA_HOME=/Users/abdalqaderalnajjar/Library/Java/JavaVirtualMachines/azul-17.0.8.1/Contents/Home ./gradlew :app:testDebugUnitTest --tests "com.Elkood.ling_en4.data.content.papers.PaperR2017Test"`
Expected: FAIL — `paperR2017` unresolved.

- [ ] **Step 3: Write the implementation**

Open `app/src/main/java/com/Elkood/ling_en4/Constants/En4/En4R2017Constants.java` and the layout the legacy `r2017` Activity inflates (+ `strings.xml`). Transcribe verbatim per the Conversion Recipe and Header/Passage Recipe:

```kotlin
package com.Elkood.ling_en4.data.content.papers

import com.Elkood.ling_en4.data.model.ExamPaper
import com.Elkood.ling_en4.data.model.Paper

internal val paperR2017: Paper = Paper(
    examPaper = ExamPaper.R2017,
    headerTitle = "<header line(s), @string values resolved, joined with \\n>",
    instruction = "<instruction @string value, or \"\" if none>",
    passage = "<passage @string value, or \"\" if none>",
    questions = listOf(
        // one q(...) per legacy `new Quiz(...)`, in source order — 54 entries total
    ),
)
```

- [ ] **Step 4: Run test to verify it passes**

Run: `JAVA_HOME=/Users/abdalqaderalnajjar/Library/Java/JavaVirtualMachines/azul-17.0.8.1/Contents/Home ./gradlew :app:testDebugUnitTest --tests "com.Elkood.ling_en4.data.content.papers.PaperR2017Test"`
Expected: PASS (2 tests). A count mismatch means a question was dropped/duplicated; an `IllegalArgumentException` at construction means a row's flags lack exactly one `1` or an option count is out of range — re-check against the source.

- [ ] **Step 5: Commit**

```bash
git add app/src/main/java/com/Elkood/ling_en4/data/content/papers/PaperR2017.kt app/src/test/java/com/Elkood/ling_en4/data/content/papers/PaperR2017Test.kt
git commit -m "feat(paperquiz): convert R2017 paper content (first session)

Co-Authored-By: Claude Opus 4.8 <noreply@anthropic.com>"
```

---

### Task 7: Convert R2017 supplementary (60 questions)

**Files:**
- Create: `app/src/main/java/com/Elkood/ling_en4/data/content/papers/PaperR2017Alt.kt`
- Test: `app/src/test/java/com/Elkood/ling_en4/data/content/papers/PaperR2017AltTest.kt`

**Interfaces:**
- Consumes: `q(...)` (Task 4), `Paper`/`ExamPaper` (Task 2).
- Produces: `internal val paperR2017Alt: Paper` (uses `ExamPaper.R2017_ALT`; legacy source is the `r2017_2` chain).

- [ ] **Step 1: Write the failing test**

```kotlin
package com.Elkood.ling_en4.data.content.papers

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class PaperR2017AltTest {
    @Test
    fun has_expected_question_count() {
        assertEquals(60, paperR2017Alt.questions.size)
    }

    @Test
    fun every_question_has_valid_shape() {
        paperR2017Alt.questions.forEach { q ->
            assertTrue("options 2..4", q.options.size in 2..4)
            assertTrue("correctIndex in range", q.correctIndex in q.options.indices)
            assertTrue("prompt non-blank", q.prompt.isNotBlank())
        }
    }
}
```

- [ ] **Step 2: Run test to verify it fails**

Run: `JAVA_HOME=/Users/abdalqaderalnajjar/Library/Java/JavaVirtualMachines/azul-17.0.8.1/Contents/Home ./gradlew :app:testDebugUnitTest --tests "com.Elkood.ling_en4.data.content.papers.PaperR2017AltTest"`
Expected: FAIL — `paperR2017Alt` unresolved.

- [ ] **Step 3: Write the implementation**

Open `app/src/main/java/com/Elkood/ling_en4/Constants/En4/En4R2017_2Constants.java` and the layout the legacy `r2017_2` Activity inflates (+ `strings.xml`). Transcribe verbatim per the Conversion Recipe and Header/Passage Recipe:

```kotlin
package com.Elkood.ling_en4.data.content.papers

import com.Elkood.ling_en4.data.model.ExamPaper
import com.Elkood.ling_en4.data.model.Paper

internal val paperR2017Alt: Paper = Paper(
    examPaper = ExamPaper.R2017_ALT,
    headerTitle = "<header line(s), @string values resolved, joined with \\n>",
    instruction = "<instruction @string value, or \"\" if none>",
    passage = "<passage @string value, or \"\" if none>",
    questions = listOf(
        // one q(...) per legacy `new Quiz(...)`, in source order — 60 entries total
    ),
)
```

- [ ] **Step 4: Run test to verify it passes**

Run: `JAVA_HOME=/Users/abdalqaderalnajjar/Library/Java/JavaVirtualMachines/azul-17.0.8.1/Contents/Home ./gradlew :app:testDebugUnitTest --tests "com.Elkood.ling_en4.data.content.papers.PaperR2017AltTest"`
Expected: PASS (2 tests). A count mismatch means a question was dropped/duplicated; an `IllegalArgumentException` at construction means a row's flags lack exactly one `1` or an option count is out of range — re-check against the source.

- [ ] **Step 5: Commit**

```bash
git add app/src/main/java/com/Elkood/ling_en4/data/content/papers/PaperR2017Alt.kt app/src/test/java/com/Elkood/ling_en4/data/content/papers/PaperR2017AltTest.kt
git commit -m "feat(paperquiz): convert R2017 supplementary paper content

Co-Authored-By: Claude Opus 4.8 <noreply@anthropic.com>"
```

---

### Task 8: Convert R2016 (60 questions)

**Files:**
- Create: `app/src/main/java/com/Elkood/ling_en4/data/content/papers/PaperR2016.kt`
- Test: `app/src/test/java/com/Elkood/ling_en4/data/content/papers/PaperR2016Test.kt`

**Interfaces:**
- Consumes: `q(...)` (Task 4), `Paper`/`ExamPaper` (Task 2).
- Produces: `internal val paperR2016: Paper` (uses `ExamPaper.R2016`; legacy source is the `r2016` chain — the Arabic-language variant).

- [ ] **Step 1: Write the failing test**

```kotlin
package com.Elkood.ling_en4.data.content.papers

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class PaperR2016Test {
    @Test
    fun has_expected_question_count() {
        assertEquals(60, paperR2016.questions.size)
    }

    @Test
    fun every_question_has_valid_shape() {
        paperR2016.questions.forEach { q ->
            assertTrue("options 2..4", q.options.size in 2..4)
            assertTrue("correctIndex in range", q.correctIndex in q.options.indices)
            assertTrue("prompt non-blank", q.prompt.isNotBlank())
        }
    }
}
```

- [ ] **Step 2: Run test to verify it fails**

Run: `JAVA_HOME=/Users/abdalqaderalnajjar/Library/Java/JavaVirtualMachines/azul-17.0.8.1/Contents/Home ./gradlew :app:testDebugUnitTest --tests "com.Elkood.ling_en4.data.content.papers.PaperR2016Test"`
Expected: FAIL — `paperR2016` unresolved.

- [ ] **Step 3: Write the implementation**

Open `app/src/main/java/com/Elkood/ling_en4/Constants/En4/En4R2016Constants.java` and the layout the legacy `r2016` Activity inflates (+ `strings.xml`). Transcribe verbatim per the Conversion Recipe and Header/Passage Recipe:

```kotlin
package com.Elkood.ling_en4.data.content.papers

import com.Elkood.ling_en4.data.model.ExamPaper
import com.Elkood.ling_en4.data.model.Paper

internal val paperR2016: Paper = Paper(
    examPaper = ExamPaper.R2016,
    headerTitle = "<header line(s), @string values resolved, joined with \\n>",
    instruction = "<instruction @string value, or \"\" if none>",
    passage = "<passage @string value, or \"\" if none>",
    questions = listOf(
        // one q(...) per legacy `new Quiz(...)`, in source order — 60 entries total
    ),
)
```

- [ ] **Step 4: Run test to verify it passes**

Run: `JAVA_HOME=/Users/abdalqaderalnajjar/Library/Java/JavaVirtualMachines/azul-17.0.8.1/Contents/Home ./gradlew :app:testDebugUnitTest --tests "com.Elkood.ling_en4.data.content.papers.PaperR2016Test"`
Expected: PASS (2 tests). A count mismatch means a question was dropped/duplicated; an `IllegalArgumentException` at construction means a row's flags lack exactly one `1` or an option count is out of range — re-check against the source.

- [ ] **Step 5: Commit**

```bash
git add app/src/main/java/com/Elkood/ling_en4/data/content/papers/PaperR2016.kt app/src/test/java/com/Elkood/ling_en4/data/content/papers/PaperR2016Test.kt
git commit -m "feat(paperquiz): convert R2016 paper content (first session)

Co-Authored-By: Claude Opus 4.8 <noreply@anthropic.com>"
```

---

### Task 9: Convert R2016 supplementary (60 questions)

**Files:**
- Create: `app/src/main/java/com/Elkood/ling_en4/data/content/papers/PaperR2016Alt.kt`
- Test: `app/src/test/java/com/Elkood/ling_en4/data/content/papers/PaperR2016AltTest.kt`

**Interfaces:**
- Consumes: `q(...)` (Task 4), `Paper`/`ExamPaper` (Task 2).
- Produces: `internal val paperR2016Alt: Paper` (uses `ExamPaper.R2016_ALT`; legacy source is the `R2016_3` chain — the supplementary/English-language variant).

- [ ] **Step 1: Write the failing test**

```kotlin
package com.Elkood.ling_en4.data.content.papers

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class PaperR2016AltTest {
    @Test
    fun has_expected_question_count() {
        assertEquals(60, paperR2016Alt.questions.size)
    }

    @Test
    fun every_question_has_valid_shape() {
        paperR2016Alt.questions.forEach { q ->
            assertTrue("options 2..4", q.options.size in 2..4)
            assertTrue("correctIndex in range", q.correctIndex in q.options.indices)
            assertTrue("prompt non-blank", q.prompt.isNotBlank())
        }
    }
}
```

- [ ] **Step 2: Run test to verify it fails**

Run: `JAVA_HOME=/Users/abdalqaderalnajjar/Library/Java/JavaVirtualMachines/azul-17.0.8.1/Contents/Home ./gradlew :app:testDebugUnitTest --tests "com.Elkood.ling_en4.data.content.papers.PaperR2016AltTest"`
Expected: FAIL — `paperR2016Alt` unresolved.

- [ ] **Step 3: Write the implementation**

Open `app/src/main/java/com/Elkood/ling_en4/Constants/En4/En4R2016_3Constants.java` and the layout the legacy `R2016_3` Activity inflates (+ `strings.xml`). Transcribe verbatim per the Conversion Recipe and Header/Passage Recipe:

```kotlin
package com.Elkood.ling_en4.data.content.papers

import com.Elkood.ling_en4.data.model.ExamPaper
import com.Elkood.ling_en4.data.model.Paper

internal val paperR2016Alt: Paper = Paper(
    examPaper = ExamPaper.R2016_ALT,
    headerTitle = "<header line(s), @string values resolved, joined with \\n>",
    instruction = "<instruction @string value, or \"\" if none>",
    passage = "<passage @string value, or \"\" if none>",
    questions = listOf(
        // one q(...) per legacy `new Quiz(...)`, in source order — 60 entries total
    ),
)
```

- [ ] **Step 4: Run test to verify it passes**

Run: `JAVA_HOME=/Users/abdalqaderalnajjar/Library/Java/JavaVirtualMachines/azul-17.0.8.1/Contents/Home ./gradlew :app:testDebugUnitTest --tests "com.Elkood.ling_en4.data.content.papers.PaperR2016AltTest"`
Expected: PASS (2 tests). A count mismatch means a question was dropped/duplicated; an `IllegalArgumentException` at construction means a row's flags lack exactly one `1` or an option count is out of range — re-check against the source.

- [ ] **Step 5: Commit**

```bash
git add app/src/main/java/com/Elkood/ling_en4/data/content/papers/PaperR2016Alt.kt app/src/test/java/com/Elkood/ling_en4/data/content/papers/PaperR2016AltTest.kt
git commit -m "feat(paperquiz): convert R2016 supplementary paper content

Co-Authored-By: Claude Opus 4.8 <noreply@anthropic.com>"
```

---

### Task 10: Convert R2015 (60 questions)

**Files:**
- Create: `app/src/main/java/com/Elkood/ling_en4/data/content/papers/PaperR2015.kt`
- Test: `app/src/test/java/com/Elkood/ling_en4/data/content/papers/PaperR2015Test.kt`

**Interfaces:**
- Consumes: `q(...)` (Task 4), `Paper`/`ExamPaper` (Task 2).
- Produces: `internal val paperR2015: Paper` (uses `ExamPaper.R2015`).

- [ ] **Step 1: Write the failing test**

```kotlin
package com.Elkood.ling_en4.data.content.papers

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class PaperR2015Test {
    @Test
    fun has_expected_question_count() {
        assertEquals(60, paperR2015.questions.size)
    }

    @Test
    fun every_question_has_valid_shape() {
        paperR2015.questions.forEach { q ->
            assertTrue("options 2..4", q.options.size in 2..4)
            assertTrue("correctIndex in range", q.correctIndex in q.options.indices)
            assertTrue("prompt non-blank", q.prompt.isNotBlank())
        }
    }
}
```

- [ ] **Step 2: Run test to verify it fails**

Run: `JAVA_HOME=/Users/abdalqaderalnajjar/Library/Java/JavaVirtualMachines/azul-17.0.8.1/Contents/Home ./gradlew :app:testDebugUnitTest --tests "com.Elkood.ling_en4.data.content.papers.PaperR2015Test"`
Expected: FAIL — `paperR2015` unresolved.

- [ ] **Step 3: Write the implementation**

Open `app/src/main/java/com/Elkood/ling_en4/Constants/En4/En4R2015Constants.java` and the layout the legacy `R2015` Activity inflates (+ `strings.xml`). Transcribe verbatim per the Conversion Recipe and Header/Passage Recipe:

```kotlin
package com.Elkood.ling_en4.data.content.papers

import com.Elkood.ling_en4.data.model.ExamPaper
import com.Elkood.ling_en4.data.model.Paper

internal val paperR2015: Paper = Paper(
    examPaper = ExamPaper.R2015,
    headerTitle = "<header line(s), @string values resolved, joined with \\n>",
    instruction = "<instruction @string value, or \"\" if none>",
    passage = "<passage @string value, or \"\" if none>",
    questions = listOf(
        // one q(...) per legacy `new Quiz(...)`, in source order — 60 entries total
    ),
)
```

- [ ] **Step 4: Run test to verify it passes**

Run: `JAVA_HOME=/Users/abdalqaderalnajjar/Library/Java/JavaVirtualMachines/azul-17.0.8.1/Contents/Home ./gradlew :app:testDebugUnitTest --tests "com.Elkood.ling_en4.data.content.papers.PaperR2015Test"`
Expected: PASS (2 tests). A count mismatch means a question was dropped/duplicated; an `IllegalArgumentException` at construction means a row's flags lack exactly one `1` or an option count is out of range — re-check against the source.

- [ ] **Step 5: Commit**

```bash
git add app/src/main/java/com/Elkood/ling_en4/data/content/papers/PaperR2015.kt app/src/test/java/com/Elkood/ling_en4/data/content/papers/PaperR2015Test.kt
git commit -m "feat(paperquiz): convert R2015 paper content

Co-Authored-By: Claude Opus 4.8 <noreply@anthropic.com>"
```

---

### Task 11: Convert R2014 (60 questions)

**Files:**
- Create: `app/src/main/java/com/Elkood/ling_en4/data/content/papers/PaperR2014.kt`
- Test: `app/src/test/java/com/Elkood/ling_en4/data/content/papers/PaperR2014Test.kt`

**Interfaces:**
- Consumes: `q(...)` (Task 4), `Paper`/`ExamPaper` (Task 2).
- Produces: `internal val paperR2014: Paper` (uses `ExamPaper.R2014`).

- [ ] **Step 1: Write the failing test**

```kotlin
package com.Elkood.ling_en4.data.content.papers

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class PaperR2014Test {
    @Test
    fun has_expected_question_count() {
        assertEquals(60, paperR2014.questions.size)
    }

    @Test
    fun every_question_has_valid_shape() {
        paperR2014.questions.forEach { q ->
            assertTrue("options 2..4", q.options.size in 2..4)
            assertTrue("correctIndex in range", q.correctIndex in q.options.indices)
            assertTrue("prompt non-blank", q.prompt.isNotBlank())
        }
    }
}
```

- [ ] **Step 2: Run test to verify it fails**

Run: `JAVA_HOME=/Users/abdalqaderalnajjar/Library/Java/JavaVirtualMachines/azul-17.0.8.1/Contents/Home ./gradlew :app:testDebugUnitTest --tests "com.Elkood.ling_en4.data.content.papers.PaperR2014Test"`
Expected: FAIL — `paperR2014` unresolved.

- [ ] **Step 3: Write the implementation**

Open `app/src/main/java/com/Elkood/ling_en4/Constants/En4/En4R2014Constants.java` and the layout the legacy `R2014` Activity inflates (+ `strings.xml`). Transcribe verbatim per the Conversion Recipe and Header/Passage Recipe:

```kotlin
package com.Elkood.ling_en4.data.content.papers

import com.Elkood.ling_en4.data.model.ExamPaper
import com.Elkood.ling_en4.data.model.Paper

internal val paperR2014: Paper = Paper(
    examPaper = ExamPaper.R2014,
    headerTitle = "<header line(s), @string values resolved, joined with \\n>",
    instruction = "<instruction @string value, or \"\" if none>",
    passage = "<passage @string value, or \"\" if none>",
    questions = listOf(
        // one q(...) per legacy `new Quiz(...)`, in source order — 60 entries total
    ),
)
```

- [ ] **Step 4: Run test to verify it passes**

Run: `JAVA_HOME=/Users/abdalqaderalnajjar/Library/Java/JavaVirtualMachines/azul-17.0.8.1/Contents/Home ./gradlew :app:testDebugUnitTest --tests "com.Elkood.ling_en4.data.content.papers.PaperR2014Test"`
Expected: PASS (2 tests). A count mismatch means a question was dropped/duplicated; an `IllegalArgumentException` at construction means a row's flags lack exactly one `1` or an option count is out of range — re-check against the source.

- [ ] **Step 5: Commit**

```bash
git add app/src/main/java/com/Elkood/ling_en4/data/content/papers/PaperR2014.kt app/src/test/java/com/Elkood/ling_en4/data/content/papers/PaperR2014Test.kt
git commit -m "feat(paperquiz): convert R2014 paper content

Co-Authored-By: Claude Opus 4.8 <noreply@anthropic.com>"
```

---

### Task 12: Convert R2013 (60 questions)

**Files:**
- Create: `app/src/main/java/com/Elkood/ling_en4/data/content/papers/PaperR2013.kt`
- Test: `app/src/test/java/com/Elkood/ling_en4/data/content/papers/PaperR2013Test.kt`

**Interfaces:**
- Consumes: `q(...)` (Task 4), `Paper`/`ExamPaper` (Task 2).
- Produces: `internal val paperR2013: Paper` (uses `ExamPaper.R2013`).

- [ ] **Step 1: Write the failing test**

```kotlin
package com.Elkood.ling_en4.data.content.papers

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class PaperR2013Test {
    @Test
    fun has_expected_question_count() {
        assertEquals(60, paperR2013.questions.size)
    }

    @Test
    fun every_question_has_valid_shape() {
        paperR2013.questions.forEach { q ->
            assertTrue("options 2..4", q.options.size in 2..4)
            assertTrue("correctIndex in range", q.correctIndex in q.options.indices)
            assertTrue("prompt non-blank", q.prompt.isNotBlank())
        }
    }
}
```

- [ ] **Step 2: Run test to verify it fails**

Run: `JAVA_HOME=/Users/abdalqaderalnajjar/Library/Java/JavaVirtualMachines/azul-17.0.8.1/Contents/Home ./gradlew :app:testDebugUnitTest --tests "com.Elkood.ling_en4.data.content.papers.PaperR2013Test"`
Expected: FAIL — `paperR2013` unresolved.

- [ ] **Step 3: Write the implementation**

Open `app/src/main/java/com/Elkood/ling_en4/Constants/En4/En4R2013Constants.java` and the layout the legacy `R2013` Activity inflates (+ `strings.xml`). Transcribe verbatim per the Conversion Recipe and Header/Passage Recipe:

```kotlin
package com.Elkood.ling_en4.data.content.papers

import com.Elkood.ling_en4.data.model.ExamPaper
import com.Elkood.ling_en4.data.model.Paper

internal val paperR2013: Paper = Paper(
    examPaper = ExamPaper.R2013,
    headerTitle = "<header line(s), @string values resolved, joined with \\n>",
    instruction = "<instruction @string value, or \"\" if none>",
    passage = "<passage @string value, or \"\" if none>",
    questions = listOf(
        // one q(...) per legacy `new Quiz(...)`, in source order — 60 entries total
    ),
)
```

- [ ] **Step 4: Run test to verify it passes**

Run: `JAVA_HOME=/Users/abdalqaderalnajjar/Library/Java/JavaVirtualMachines/azul-17.0.8.1/Contents/Home ./gradlew :app:testDebugUnitTest --tests "com.Elkood.ling_en4.data.content.papers.PaperR2013Test"`
Expected: PASS (2 tests). A count mismatch means a question was dropped/duplicated; an `IllegalArgumentException` at construction means a row's flags lack exactly one `1` or an option count is out of range — re-check against the source.

- [ ] **Step 5: Commit**

```bash
git add app/src/main/java/com/Elkood/ling_en4/data/content/papers/PaperR2013.kt app/src/test/java/com/Elkood/ling_en4/data/content/papers/PaperR2013Test.kt
git commit -m "feat(paperquiz): convert R2013 paper content

Co-Authored-By: Claude Opus 4.8 <noreply@anthropic.com>"
```

---

### Task 13: Convert R2012 (54 questions)

**Files:**
- Create: `app/src/main/java/com/Elkood/ling_en4/data/content/papers/PaperR2012.kt`
- Test: `app/src/test/java/com/Elkood/ling_en4/data/content/papers/PaperR2012Test.kt`

**Interfaces:**
- Consumes: `q(...)` (Task 4), `Paper`/`ExamPaper` (Task 2).
- Produces: `internal val paperR2012: Paper` (uses `ExamPaper.R2012`).

- [ ] **Step 1: Write the failing test**

```kotlin
package com.Elkood.ling_en4.data.content.papers

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class PaperR2012Test {
    @Test
    fun has_expected_question_count() {
        assertEquals(54, paperR2012.questions.size)
    }

    @Test
    fun every_question_has_valid_shape() {
        paperR2012.questions.forEach { q ->
            assertTrue("options 2..4", q.options.size in 2..4)
            assertTrue("correctIndex in range", q.correctIndex in q.options.indices)
            assertTrue("prompt non-blank", q.prompt.isNotBlank())
        }
    }
}
```

- [ ] **Step 2: Run test to verify it fails**

Run: `JAVA_HOME=/Users/abdalqaderalnajjar/Library/Java/JavaVirtualMachines/azul-17.0.8.1/Contents/Home ./gradlew :app:testDebugUnitTest --tests "com.Elkood.ling_en4.data.content.papers.PaperR2012Test"`
Expected: FAIL — `paperR2012` unresolved.

- [ ] **Step 3: Write the implementation**

Open `app/src/main/java/com/Elkood/ling_en4/Constants/En4/En4R2012Constants.java` and the layout the legacy `R2012` Activity inflates (+ `strings.xml`). Transcribe verbatim per the Conversion Recipe and Header/Passage Recipe. The first row is a confirmed worked example:

`list.add(new Quiz("1.\t The most suitable time clause...", 0, 1, 0, 0, " A. Once", "B. when", "C. before ", "D. until "));`
→ `q("1.\t The most suitable time clause...", 0, 1, 0, 0, " A. Once", "B. when", "C. before ", "D. until "),` (correctIndex resolves to `"B. when"`).

```kotlin
package com.Elkood.ling_en4.data.content.papers

import com.Elkood.ling_en4.data.model.ExamPaper
import com.Elkood.ling_en4.data.model.Paper

internal val paperR2012: Paper = Paper(
    examPaper = ExamPaper.R2012,
    headerTitle = "<header line(s), @string values resolved, joined with \\n>",
    instruction = "<instruction @string value, or \"\" if none>",
    passage = "<passage @string value, or \"\" if none>",
    questions = listOf(
        q("1.\t The most suitable time clause...", 0, 1, 0, 0, " A. Once", "B. when", "C. before ", "D. until "),
        // ... remaining entries, in source order — 54 entries total
    ),
)
```

- [ ] **Step 4: Run test to verify it passes**

Run: `JAVA_HOME=/Users/abdalqaderalnajjar/Library/Java/JavaVirtualMachines/azul-17.0.8.1/Contents/Home ./gradlew :app:testDebugUnitTest --tests "com.Elkood.ling_en4.data.content.papers.PaperR2012Test"`
Expected: PASS (2 tests). A count mismatch means a question was dropped/duplicated; an `IllegalArgumentException` at construction means a row's flags lack exactly one `1` or an option count is out of range — re-check against the source.

- [ ] **Step 5: Commit**

```bash
git add app/src/main/java/com/Elkood/ling_en4/data/content/papers/PaperR2012.kt app/src/test/java/com/Elkood/ling_en4/data/content/papers/PaperR2012Test.kt
git commit -m "feat(paperquiz): convert R2012 paper content

Co-Authored-By: Claude Opus 4.8 <noreply@anthropic.com>"
```

---

## Task 14: `En4Papers` dispatch + content-integrity test

**Files:**
- Create: `app/src/main/java/com/Elkood/ling_en4/data/content/En4Papers.kt`
- Test: `app/src/test/java/com/Elkood/ling_en4/data/content/En4PapersTest.kt`

**Interfaces:**
- Consumes: all nine `paperR20xx` vals (Tasks 5–13), `ExamPaper`/`Paper` (Task 2).
- Produces: `object En4Papers { fun paper(examPaper: ExamPaper): Paper }`.

- [ ] **Step 1: Write the failing test**

```kotlin
package com.Elkood.ling_en4.data.content

import com.Elkood.ling_en4.data.model.ExamPaper
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class En4PapersTest {
    private val expectedCounts = mapOf(
        ExamPaper.R2018 to 60,
        ExamPaper.R2017 to 54,
        ExamPaper.R2017_ALT to 60,
        ExamPaper.R2016 to 60,
        ExamPaper.R2016_ALT to 60,
        ExamPaper.R2015 to 60,
        ExamPaper.R2014 to 60,
        ExamPaper.R2013 to 60,
        ExamPaper.R2012 to 54,
    )

    @Test
    fun every_paper_matches_legacy_question_count() {
        expectedCounts.forEach { (paper, count) ->
            assertEquals("count for $paper", count, En4Papers.paper(paper).questions.size)
        }
    }

    @Test
    fun resolves_all_nine_variants_with_correct_exam_paper() {
        assertEquals(9, ExamPaper.entries.size)
        ExamPaper.entries.forEach { p ->
            assertEquals(p, En4Papers.paper(p).examPaper)
        }
    }

    @Test
    fun every_question_has_valid_shape_and_one_correct_answer() {
        ExamPaper.entries.forEach { p ->
            En4Papers.paper(p).questions.forEach { q ->
                assertTrue(q.options.size in 2..4)
                assertTrue(q.correctIndex in q.options.indices)
                assertTrue(q.prompt.isNotBlank())
            }
        }
    }
}
```

- [ ] **Step 2: Run test to verify it fails**

Run: `JAVA_HOME=/Users/abdalqaderalnajjar/Library/Java/JavaVirtualMachines/azul-17.0.8.1/Contents/Home ./gradlew :app:testDebugUnitTest --tests "com.Elkood.ling_en4.data.content.En4PapersTest"`
Expected: FAIL — `En4Papers` unresolved.

- [ ] **Step 3: Write minimal implementation**

```kotlin
package com.Elkood.ling_en4.data.content

import com.Elkood.ling_en4.data.content.papers.paperR2012
import com.Elkood.ling_en4.data.content.papers.paperR2013
import com.Elkood.ling_en4.data.content.papers.paperR2014
import com.Elkood.ling_en4.data.content.papers.paperR2015
import com.Elkood.ling_en4.data.content.papers.paperR2016
import com.Elkood.ling_en4.data.content.papers.paperR2016Alt
import com.Elkood.ling_en4.data.content.papers.paperR2017
import com.Elkood.ling_en4.data.content.papers.paperR2017Alt
import com.Elkood.ling_en4.data.content.papers.paperR2018
import com.Elkood.ling_en4.data.model.ExamPaper
import com.Elkood.ling_en4.data.model.Paper

/** In-memory exam-paper content, converted verbatim from the legacy En4R20xx*Constants. */
object En4Papers {
    fun paper(examPaper: ExamPaper): Paper = when (examPaper) {
        ExamPaper.R2018 -> paperR2018
        ExamPaper.R2017 -> paperR2017
        ExamPaper.R2017_ALT -> paperR2017Alt
        ExamPaper.R2016 -> paperR2016
        ExamPaper.R2016_ALT -> paperR2016Alt
        ExamPaper.R2015 -> paperR2015
        ExamPaper.R2014 -> paperR2014
        ExamPaper.R2013 -> paperR2013
        ExamPaper.R2012 -> paperR2012
    }
}
```

- [ ] **Step 4: Run test to verify it passes**

Run: `JAVA_HOME=/Users/abdalqaderalnajjar/Library/Java/JavaVirtualMachines/azul-17.0.8.1/Contents/Home ./gradlew :app:testDebugUnitTest --tests "com.Elkood.ling_en4.data.content.En4PapersTest"`
Expected: PASS (3 tests).

- [ ] **Step 5: Commit**

```bash
git add app/src/main/java/com/Elkood/ling_en4/data/content/En4Papers.kt app/src/test/java/com/Elkood/ling_en4/data/content/En4PapersTest.kt
git commit -m "feat(paperquiz): add En4Papers dispatch and integrity test

Co-Authored-By: Claude Opus 4.8 <noreply@anthropic.com>"
```

---

## Task 15: Add `TajawalFontFamily` to `Type.kt`

**Files:**
- Modify: `app/src/main/java/com/Elkood/ling_en4/ui/theme/Type.kt`

**Interfaces:**
- Produces: `val TajawalFontFamily: FontFamily` (top-level in `ui.theme`), referencing existing resources `R.font.tajawal_regular`, `R.font.tajawal_light`, `R.font.tajawal_medium`, `R.font.tajawal_bold`.

This is a font-resource wiring change with no unit test (fonts resolve at runtime). It is verified by Kotlin compilation.

- [ ] **Step 1: Add the font family**

Add below the existing `MuliFontFamily` declaration:

```kotlin
val TajawalFontFamily = FontFamily(
    Font(R.font.tajawal_regular, FontWeight.Normal),
    Font(R.font.tajawal_light, FontWeight.Light),
    Font(R.font.tajawal_medium, FontWeight.Medium),
    Font(R.font.tajawal_bold, FontWeight.Bold),
)
```

- [ ] **Step 2: Verify it compiles**

Run: `JAVA_HOME=/Users/abdalqaderalnajjar/Library/Java/JavaVirtualMachines/azul-17.0.8.1/Contents/Home ./gradlew :app:compileDebugKotlin`
Expected: BUILD SUCCESSFUL.

- [ ] **Step 3: Commit**

```bash
git add app/src/main/java/com/Elkood/ling_en4/ui/theme/Type.kt
git commit -m "feat(theme): add TajawalFontFamily for Arabic card labels

Co-Authored-By: Claude Opus 4.8 <noreply@anthropic.com>"
```

---

## Task 16: `PaperQuestionCard` composable

**Files:**
- Create: `app/src/main/java/com/Elkood/ling_en4/ui/screens/paperquiz/PaperQuestionCard.kt`

**Interfaces:**
- Consumes: `PaperQuestion` (Task 1), `optionVisual`/`OptionVisual` (Task 3), `ZetaColors`/`MuliFontFamily` (theme), drawables `R.drawable.ic_check_green`, `R.drawable.ic_cancel_mark`.
- Produces: `@Composable fun PaperQuestionCard(question: PaperQuestion, selectedIndex: Int?, revealed: Boolean, onSelect: (Int) -> Unit, onCheck: () -> Unit)`.

Stateless except for a local "please select an answer" prompt flag. Verified by compilation (composables aren't unit-tested in this module; reveal semantics are covered by `OptionVisualTest`).

- [ ] **Step 1: Write the composable**

```kotlin
package com.Elkood.ling_en4.ui.screens.paperquiz

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.Elkood.ling_en4.R
import com.Elkood.ling_en4.ui.theme.MuliFontFamily
import com.Elkood.ling_en4.ui.theme.ZetaColors

@Composable
fun PaperQuestionCard(
    question: PaperQuestion,
    selectedIndex: Int?,
    revealed: Boolean,
    onSelect: (Int) -> Unit,
    onCheck: () -> Unit,
) {
    var showSelectPrompt by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 5.dp, vertical = 6.dp)) {
        Text(
            text = question.prompt,
            color = ZetaColors.QuestionText,
            fontFamily = MuliFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            modifier = Modifier.fillMaxWidth().padding(bottom = 6.dp),
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(ZetaColors.QuestionBg, RoundedCornerShape(20.dp))
                .padding(8.dp),
        ) {
            question.options.forEachIndexed { index, option ->
                val visual = optionVisual(index, question.correctIndex, revealed)
                val textColor = when (visual) {
                    OptionVisual.Normal -> ZetaColors.OptionText
                    OptionVisual.Correct -> ZetaColors.Correct
                    OptionVisual.Wrong -> ZetaColors.WrongText
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .selectable(
                            selected = selectedIndex == index,
                            enabled = !revealed,
                            onClick = { showSelectPrompt = false; onSelect(index) },
                        )
                        .padding(vertical = 2.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    RadioButton(
                        selected = selectedIndex == index,
                        enabled = !revealed,
                        onClick = { showSelectPrompt = false; onSelect(index) },
                    )
                    Text(
                        text = option,
                        color = textColor,
                        fontFamily = MuliFontFamily,
                        modifier = Modifier.weight(1f).padding(start = 4.dp),
                    )
                    if (visual == OptionVisual.Correct) {
                        Icon(
                            painter = painterResource(R.drawable.ic_check_green),
                            contentDescription = null,
                            tint = Color.Unspecified,
                        )
                    } else if (visual == OptionVisual.Wrong) {
                        Icon(
                            painter = painterResource(R.drawable.ic_cancel_mark),
                            contentDescription = null,
                            tint = Color.Unspecified,
                        )
                    }
                }
            }
        }

        if (showSelectPrompt && !revealed) {
            Text(
                text = "يرجى اختيار الجواب",
                color = ZetaColors.WrongText,
                fontFamily = MuliFontFamily,
                modifier = Modifier.padding(top = 4.dp),
            )
        }

        if (!revealed) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 6.dp)
                    .background(ZetaColors.Accent, RoundedCornerShape(20.dp))
                    .clickable {
                        if (selectedIndex == null) showSelectPrompt = true else onCheck()
                    }
                    .padding(vertical = 8.dp, horizontal = 12.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_check_green),
                    contentDescription = null,
                    tint = Color.Unspecified,
                )
                Text(
                    text = "Check ",
                    color = ZetaColors.Surface,
                    fontFamily = MuliFontFamily,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 6.dp),
                )
            }
        }
    }
}
```

- [ ] **Step 2: Verify it compiles**

Run: `JAVA_HOME=/Users/abdalqaderalnajjar/Library/Java/JavaVirtualMachines/azul-17.0.8.1/Contents/Home ./gradlew :app:compileDebugKotlin`
Expected: BUILD SUCCESSFUL. (If `ic_check_green`/`ic_cancel_mark` resolve issues arise, confirm they exist in `res/drawable` — they do: `ic_check_green.xml`, `ic_cancel_mark.xml`.)

- [ ] **Step 3: Commit**

```bash
git add app/src/main/java/com/Elkood/ling_en4/ui/screens/paperquiz/PaperQuestionCard.kt
git commit -m "feat(paperquiz): add PaperQuestionCard composable

Co-Authored-By: Claude Opus 4.8 <noreply@anthropic.com>"
```

---

## Task 17: `PaperQuizScreen` composable

**Files:**
- Create: `app/src/main/java/com/Elkood/ling_en4/ui/screens/paperquiz/PaperQuizScreen.kt`

**Interfaces:**
- Consumes: `Paper` (Task 2), `PaperQuestionCard` (Task 16), `MuliFontFamily`/`ZetaColors` (theme).
- Produces: `@Composable fun PaperQuizScreen(paper: Paper, onBack: () -> Unit)`.

Owns per-question reveal state hoisted across the list via `rememberSaveable` snapshot lists (so a question checked at the top stays revealed after scrolling). Content is LTR-scoped. Verified by compilation.

- [ ] **Step 1: Write the composable**

```kotlin
package com.Elkood.ling_en4.ui.screens.paperquiz

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.CompositionLocalProvider
import com.Elkood.ling_en4.data.model.Paper
import com.Elkood.ling_en4.ui.theme.MuliFontFamily
import com.Elkood.ling_en4.ui.theme.ZetaColors

@Composable
fun PaperQuizScreen(paper: Paper, onBack: () -> Unit) {
    BackHandler(onBack = onBack)

    val selected = rememberSaveable(
        paper.examPaper,
        saver = listSaver(save = { it.toList() }, restore = { it.toMutableStateList() }),
    ) { MutableList(paper.questions.size) { -1 }.toMutableStateList() }

    val revealed = rememberSaveable(
        paper.examPaper,
        saver = listSaver(save = { it.toList() }, restore = { it.toMutableStateList() }),
    ) { MutableList(paper.questions.size) { false }.toMutableStateList() }

    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
        LazyColumn(modifier = Modifier.fillMaxSize().padding(horizontal = 4.dp)) {
            item {
                IconButton(onClick = onBack) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                }
            }
            item {
                Text(
                    text = paper.headerTitle,
                    fontFamily = MuliFontFamily,
                    modifier = Modifier.fillMaxWidth().padding(10.dp),
                )
            }
            if (paper.instruction.isNotEmpty()) {
                item {
                    Text(
                        text = paper.instruction,
                        fontFamily = MuliFontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 5.dp, vertical = 8.dp),
                    )
                }
            }
            if (paper.passage.isNotEmpty()) {
                item {
                    Text(
                        text = paper.passage,
                        color = ZetaColors.OnSurface,
                        fontFamily = MuliFontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(ZetaColors.Surface)
                            .padding(6.dp),
                    )
                }
            }
            itemsIndexed(paper.questions) { index, question ->
                PaperQuestionCard(
                    question = question,
                    selectedIndex = selected[index].takeIf { it >= 0 },
                    revealed = revealed[index],
                    onSelect = { selected[index] = it },
                    onCheck = { revealed[index] = true },
                )
            }
        }
    }
}
```

Note: add the missing `import androidx.compose.foundation.background` if the compiler flags it (used on the passage block).

- [ ] **Step 2: Verify it compiles**

Run: `JAVA_HOME=/Users/abdalqaderalnajjar/Library/Java/JavaVirtualMachines/azul-17.0.8.1/Contents/Home ./gradlew :app:compileDebugKotlin`
Expected: BUILD SUCCESSFUL. Resolve any missing imports (e.g. `background`) the compiler reports.

- [ ] **Step 3: Commit**

```bash
git add app/src/main/java/com/Elkood/ling_en4/ui/screens/paperquiz/PaperQuizScreen.kt
git commit -m "feat(paperquiz): add PaperQuizScreen with hoisted reveal state

Co-Authored-By: Claude Opus 4.8 <noreply@anthropic.com>"
```

---

## Task 18: `PaperListScreen` grid + `PaperCard`

**Files:**
- Create: `app/src/main/java/com/Elkood/ling_en4/ui/screens/paperquiz/PaperListScreen.kt`

**Interfaces:**
- Consumes: `ExamPaper` (Task 2), `TajawalFontFamily` (Task 15), `R.drawable.ic_folder`.
- Produces: `@Composable fun PaperListScreen(onPaperClick: (ExamPaper) -> Unit)`; private `@Composable fun PaperCard(examPaper: ExamPaper, onClick: () -> Unit)`.

2-column `LazyVerticalGrid`, RTL (app default — do NOT wrap in an LTR provider). Cards reproduce `row_repeter.xml`: rounded card, folder icon, Tajawal-bold title, grey `#cccccc` subtitle. Verified by compilation.

- [ ] **Step 1: Write the composable**

```kotlin
package com.Elkood.ling_en4.ui.screens.paperquiz

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.Elkood.ling_en4.R
import com.Elkood.ling_en4.data.model.ExamPaper
import com.Elkood.ling_en4.ui.theme.TajawalFontFamily

@Composable
fun PaperListScreen(onPaperClick: (ExamPaper) -> Unit) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize().padding(4.dp),
    ) {
        items(ExamPaper.entries) { examPaper ->
            PaperCard(examPaper = examPaper, onClick = { onPaperClick(examPaper) })
        }
    }
}

@Composable
private fun PaperCard(examPaper: ExamPaper, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .background(Color(0xFFFFFFFF), RoundedCornerShape(15.dp))
            .clickable(onClick = onClick)
            .padding(vertical = 12.dp, horizontal = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_folder),
            contentDescription = null,
            tint = Color.Unspecified,
            modifier = Modifier.height(70.dp).padding(top = 10.dp),
        )
        Text(
            text = examPaper.cardTitle,
            fontFamily = TajawalFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 19.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
        )
        Text(
            text = examPaper.cardSubtitle,
            color = Color(0xFFCCCCCC),
            fontFamily = TajawalFontFamily,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth().padding(5.dp),
        )
    }
}
```

- [ ] **Step 2: Verify it compiles**

Run: `JAVA_HOME=/Users/abdalqaderalnajjar/Library/Java/JavaVirtualMachines/azul-17.0.8.1/Contents/Home ./gradlew :app:compileDebugKotlin`
Expected: BUILD SUCCESSFUL.

- [ ] **Step 3: Commit**

```bash
git add app/src/main/java/com/Elkood/ling_en4/ui/screens/paperquiz/PaperListScreen.kt
git commit -m "feat(paperquiz): add PaperListScreen grid

Co-Authored-By: Claude Opus 4.8 <noreply@anthropic.com>"
```

---

## Task 19: `PaperQuizActivity` host + manifest entry

**Files:**
- Create: `app/src/main/java/com/Elkood/ling_en4/ui/screens/paperquiz/PaperQuizActivity.kt`
- Modify: `app/src/main/AndroidManifest.xml`

**Interfaces:**
- Consumes: `En4Papers` (Task 14), `ExamPaper` (Task 2), `PaperQuizScreen` (Task 17), `ZetaTheme` (theme).
- Produces: `class PaperQuizActivity : ComponentActivity` with `companion object { const val EXTRA_PAPER; fun start(context: Context, examPaper: ExamPaper) }`.

Mirrors the `ComposeQuizActivity` precedent. Verified by full `assembleDebug`.

- [ ] **Step 1: Write the Activity**

```kotlin
package com.Elkood.ling_en4.ui.screens.paperquiz

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.Elkood.ling_en4.data.content.En4Papers
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

        val paper = En4Papers.paper(examPaper)

        setContent {
            ZetaTheme {
                PaperQuizScreen(paper = paper, onBack = { finish() })
            }
        }
    }
}
```

- [ ] **Step 2: Register in the manifest**

In `app/src/main/AndroidManifest.xml`, add alongside the other Compose activities (next to `ComposeQuizActivity`, around line 134-137):

```xml
        <activity
            android:name=".ui.screens.paperquiz.PaperQuizActivity"
            android:screenOrientation="fullSensor"
            android:theme="@style/AppTheme" />
```

- [ ] **Step 3: Verify it builds**

Run: `JAVA_HOME=/Users/abdalqaderalnajjar/Library/Java/JavaVirtualMachines/azul-17.0.8.1/Contents/Home ./gradlew :app:assembleDebug`
Expected: BUILD SUCCESSFUL.

- [ ] **Step 4: Commit**

```bash
git add app/src/main/java/com/Elkood/ling_en4/ui/screens/paperquiz/PaperQuizActivity.kt app/src/main/AndroidManifest.xml
git commit -m "feat(paperquiz): add PaperQuizActivity host and manifest entry

Co-Authored-By: Claude Opus 4.8 <noreply@anthropic.com>"
```

---

## Task 20: Wire `PaperListScreen` into shell tab 0

**Files:**
- Modify: `app/src/main/java/com/Elkood/ling_en4/ui/screens/shell/ShellScreen.kt`

**Interfaces:**
- Consumes: `PaperListScreen` (Task 18), `PaperQuizActivity` (Task 19).

Replaces the legacy `AndroidFragment<Courses_Quiz_Screen>` on shell tab 0 with the Compose `PaperListScreen`, launching `PaperQuizActivity` on card tap. This is the phase's integration point — after this, tab 0 is fully Compose and the legacy Pattern-B chain is orphaned (left in place for Phase 7). Verified by full `assembleDebug` + manual smoke.

- [ ] **Step 1: Add a context handle**

In `ShellScreen.kt`, inside the composable body (before the `Scaffold`/`Box` that hosts the pager), add:

```kotlin
val context = androidx.compose.ui.platform.LocalContext.current
```

(If a `LocalContext.current` handle already exists in scope, reuse it.)

- [ ] **Step 2: Replace the tab-0 branch**

At `ShellScreen.kt:115`, change:

```kotlin
                                    0 -> AndroidFragment<Courses_Quiz_Screen>(Modifier.fillMaxSize())
```

to:

```kotlin
                                    0 -> com.Elkood.ling_en4.ui.screens.paperquiz.PaperListScreen(
                                        onPaperClick = { com.Elkood.ling_en4.ui.screens.paperquiz.PaperQuizActivity.start(context, it) }
                                    )
```

Leave the `Courses_Quiz_Screen` import in place if the compiler does not flag it as an error (unused imports are warnings, not failures); otherwise remove it. Do not touch tabs 1 and 2.

- [ ] **Step 3: Build the APK**

Run: `JAVA_HOME=/Users/abdalqaderalnajjar/Library/Java/JavaVirtualMachines/azul-17.0.8.1/Contents/Home ./gradlew :app:assembleDebug`
Expected: BUILD SUCCESSFUL.

- [ ] **Step 4: Run the full unit-test suite**

Run: `JAVA_HOME=/Users/abdalqaderalnajjar/Library/Java/JavaVirtualMachines/azul-17.0.8.1/Contents/Home ./gradlew :app:testDebugUnitTest`
Expected: BUILD SUCCESSFUL — all model/builder/content/optionVisual tests green.

- [ ] **Step 5: Manual smoke check**

Install and launch the app; on the home shell, tab 0 ("الدورات") now shows a 2-column RTL grid of nine paper cards (2018, 2017×2, 2016×2, 2015, 2014, 2013, 2012). Tap each split pair to confirm the subtitles distinguish them. Open a paper: header/instruction/passage render LTR, questions render with radio options; tap **Check** with no selection → "يرجى اختيار الجواب"; select then Check → correct option green with ✓, all others red with ✗, radios disabled; scroll away and back → reveal persists; system back returns to the shell.

- [ ] **Step 6: Commit**

```bash
git add app/src/main/java/com/Elkood/ling_en4/ui/screens/shell/ShellScreen.kt
git commit -m "feat(paperquiz): replace tab-0 fragment with Compose PaperListScreen

Co-Authored-By: Claude Opus 4.8 <noreply@anthropic.com>"
```

---

## Notes for the executor

- The nine content-conversion tasks (5–13) are near-mechanical transcription from a named source file; run them on a cheap/fast model tier. The UI tasks (16–20) involve Compose integration; use a standard tier.
- The count assertion in each per-paper test is the completeness gate; the `q()` builder's `require` and `PaperQuestion.init` are the correctness gates (they throw at construction on a bad row). A green per-paper test means the transcription is complete and structurally valid — a reviewer should still diff a sample of rows against the legacy source for verbatim fidelity.
- Legacy files are intentionally left untouched (`Courses_Quiz_Screen`, `Adapter_Courses`, `Adapter_Quiz`, the nine `R20xx` Activities, `En4R20xx*Constants`, and the `activity_r20xx`/`quiz_row_2017`/`choice*`/`row_repeter` layouts). They are swept in Phase 7.
