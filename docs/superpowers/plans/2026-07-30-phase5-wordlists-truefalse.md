# Phase 5 — Word Lists, Molakhs & True/False Implementation Plan

> **For agentic workers:** REQUIRED SUB-SKILL: Use superpowers:subagent-driven-development (recommended) or superpowers:executing-plans to implement this plan task-by-task. Steps use checkbox (`- [ ]`) syntax for tracking.

**Goal:** Migrate the remaining `AdapterHome` reference screens (word lists, molakhs summaries, the 4 accordion answer-keys, the True/False answer-key list) and the interactive True/False quiz from Java/RecyclerView/third-party views to Kotlin + Jetpack Compose, retiring every legacy destination the 7-card home grid and the `BankItemsQuiz` BoomMenu point to.

**Architecture:** Each destination is a separate Compose `ComponentActivity` running `setContent { ZetaTheme { <Screen>() } }`. Verbatim content is ported into `data/content` objects backed by typed models in `data/model`; T/F quiz mechanics live in a pure `TrueFalseQuizEngine`. Two parameterized activities (`UnitWordsActivity` via `EXTRA_UNIT`, `ReferenceAccordionActivity` via `EXTRA_TOPIC`) serve the 7 unit screens and 4 accordions respectively. The only Java edits are repointing `AdapterHome`'s 7-case switch and `BankItemsQuiz`'s boom index 1. Tests are pure-JVM JUnit4 over the data/logic layer (no Robolectric); Compose screens are verified by the build gate.

**Tech Stack:** Kotlin 2.0.21, compose-bom 2024.09.00, Material3, JUnit 4 (`junit:junit:4.13.2`), AGP 8.9.1.

## Global Constraints

- Package root: `com.Elkood.ling_en4`. Model floors (do not lower): compileSdk 36, minSdk 21, versionCode 11, versionName 1.4.7.
- Build gate (JDK 17), must end `BUILD SUCCESSFUL`:
  `JAVA_HOME=/Users/abdalqaderalnajjar/Library/Java/JavaVirtualMachines/azul-17.0.8.1/Contents/Home ./gradlew assembleDebug testDebugUnitTest`
- Colors via `colorResource(id = R.color.<name>)`. Available legacy names used here: `colorPrimary` (#00BCD4), `color4` (#EC8F52), `backber` (#57727F), `blueberry` (#0FB2C0), `grey`, `white`, `black`.
- Fonts via `FontFamily(Font(R.font.<name>))`. Existing families in `ui/theme/Type.kt`: `MuliFontFamily`, `TajawalFontFamily`, `ZetaTypography`. Legacy font resources present on disk: `muli` (muli.xml), `a3.ttf`, `a4.ttf`, `a5.ttf`, `hugme.ttf`, `tajawal_bold.ttf`.
- Theme: `ZetaTheme`. RTL/LTR via `CompositionLocalProvider(LocalLayoutDirection provides ...)`.
- **Verbatim copy rule:** every English/Arabic string, label, and count is copied 1:1 from the named Java/XML source. Nothing invented, nothing corrected (the deliberately-wrong index labels `"عدد :  0"` for unit10 and `"عدد :  84"` for unit14 are preserved exactly, double-space after the colon included).
- **DO NOT TOUCH** the shared 6-quiz engine: `InteractiveQuizScreen`, `QuizViewModel`, `QuizConfig`, `QuizTopic`, `ComposeQuizActivity`, `quizConfigFor`. The T/F quiz is a dedicated, independent screen.
- Sound reuse: `com.Elkood.ling_en4.ui.screens.quiz.QuizSound(context)` — gated by SharedPreferences file `saveData`, key `switch1` (default true). Plays `R.raw.correct` / `R.raw.wrong`.

---

## File Structure

**New model files** (`app/src/main/java/com/Elkood/ling_en4/data/model/`):
- `WordPair.kt` — `WordPair(english, arabic)`
- `WordUnitHeader.kt` — `WordUnitHeader(unit, title, countLabel)` (index card)
- `ReferenceItem.kt` — `ReferenceItem(title, englishBody, arabicBody, colorRes)`
- `ReferenceTopic.kt` — `enum ReferenceTopic { VOCABULARY, ABBREVIATIONS, COMPOUND_NOUNS, EXTENSIONS }`
- `MolakhsCard.kt` — `MolakhsCard(title, english, arabic)`
- `MolakhsSummary.kt` — `MolakhsSummary(unitLabel, cards)`
- `TrueFalseItem.kt` — `TrueFalseItem(english, arabic, isTrue)`
- `TrueFalseQuestion.kt` — `TrueFalseQuestion(statement, correctAnswer)`

**New content files** (`app/src/main/java/com/Elkood/ling_en4/data/content/`):
- `WordListContent.kt` — `wordsByUnit: Map<Int, List<WordPair>>`, `indexHeaders: List<WordUnitHeader>`, `unitWordsFor(unit)`
- `ReferenceContent.kt` — `itemsFor(topic)`, `referenceTopicFromExtra(name)`
- `MolakhsContent.kt` — `summaries: List<MolakhsSummary>`
- `TrueFalseContent.kt` — `listItems: List<TrueFalseItem>`, `quizPool: List<TrueFalseQuestion>`

**New logic file** (`app/src/main/java/com/Elkood/ling_en4/ui/screens/truefalse/`):
- `TrueFalseQuizEngine.kt` — pure mechanics + `TF_PREFS`, `TF_KEY`, `shouldPersistHighScore`

**New Compose screens + activities**:
- `ui/screens/wordlist/WordIndexScreen.kt` + `WordIndexActivity.kt`
- `ui/screens/wordlist/UnitWordsScreen.kt` + `UnitWordsActivity.kt`
- `ui/screens/molakhs/MolakhsScreen.kt` + `MolakhsActivity.kt`
- `ui/screens/reference/ReferenceAccordionScreen.kt` + `ReferenceAccordionActivity.kt`
- `ui/screens/truefalse/TrueFalseListScreen.kt` + `TrueFalseListActivity.kt`
- `ui/screens/truefalse/TrueFalseQuizScreen.kt` + `TrueFalseQuizActivity.kt`

**New test files** (`app/src/test/java/com/Elkood/ling_en4/`):
- `data/model/ModelInvariantsTest.kt`
- `data/content/WordListContentTest.kt`
- `data/content/ReferenceContentTest.kt`
- `data/content/MolakhsContentTest.kt`
- `data/content/TrueFalseContentTest.kt`
- `ui/screens/truefalse/TrueFalseQuizEngineTest.kt`

**Modified files:**
- `app/src/main/AndroidManifest.xml` — register 6 new activities; later de-register legacy targets
- `app/src/main/java/com/Elkood/ling_en4/Adapter/AdapterHome.java` — repoint 7-case switch
- `app/src/main/java/com/Elkood/ling_en4/BankItemsQuiz.java` — repoint boom index 1

**Verbatim data sources** (read-only, port FROM these):
- Word units: `.../Views/SecondYear/English_4/Important_quiz/unit_package/unit8.java` … `unit14.java`; index labels `.../Important_quiz/word.java` + `En4WordsConstants.java`
- Accordions: `.../Vocabulary/Vocabulary.java`, `.../Abbrevationss/Abbreviations.java`, `.../Compound_Nouns/Compound_Nouns.java`, `.../Extinsions/Extinsions.java`
- T/F list: `.../Constants/En4/En4TrueFalseConstants.java`
- T/F quiz: `.../True_false/QuizActivity_True_false.java` (`quizData`)
- Molakhs: `.../molakhs/fragment_munit{8,9,11,12,13,14}.xml` (all text is static `android:text` in these layouts)

---

## Task 1: Models + ReferenceTopic enum

**Files:**
- Create: all 8 files under `data/model/` (listed in File Structure)
- Test: `app/src/test/java/com/Elkood/ling_en4/data/model/ModelInvariantsTest.kt`

**Interfaces:**
- Produces: `WordPair(english: String, arabic: String)`; `WordUnitHeader(unit: Int, title: String, countLabel: String)`; `ReferenceItem(title: String, englishBody: String, arabicBody: String, colorRes: Int)`; `enum ReferenceTopic { VOCABULARY, ABBREVIATIONS, COMPOUND_NOUNS, EXTENSIONS }`; `MolakhsCard(title: String?, english: String, arabic: String)`; `MolakhsSummary(unitLabel: String, cards: List<MolakhsCard>)` with `init { require(cards.isNotEmpty()) }`; `TrueFalseItem(english: String, arabic: String, isTrue: Boolean)`; `TrueFalseQuestion(statement: String, correctAnswer: Boolean)`.

- [ ] **Step 1: Write the failing test**

```kotlin
package com.Elkood.ling_en4.data.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Assert.assertTrue
import org.junit.Test

class ModelInvariantsTest {
    @Test
    fun wordPairHoldsBothLanguages() {
        val p = WordPair(english = "book", arabic = "كتاب")
        assertEquals("book", p.english)
        assertEquals("كتاب", p.arabic)
    }

    @Test
    fun referenceTopicHasExactlyFourTopics() {
        assertEquals(4, ReferenceTopic.entries.size)
        assertEquals(
            listOf("VOCABULARY", "ABBREVIATIONS", "COMPOUND_NOUNS", "EXTENSIONS"),
            ReferenceTopic.entries.map { it.name },
        )
    }

    @Test
    fun molakhsSummaryRejectsEmptyCardList() {
        assertThrows(IllegalArgumentException::class.java) {
            MolakhsSummary(unitLabel = "Unit 8 ", cards = emptyList())
        }
    }

    @Test
    fun molakhsSummaryAllowsNonEmptyCards() {
        val s = MolakhsSummary(
            unitLabel = "Unit 8 ",
            cards = listOf(MolakhsCard(title = "T", english = "e", arabic = "ع")),
        )
        assertEquals(1, s.cards.size)
        assertTrue(s.cards.first().title == "T")
    }

    @Test
    fun trueFalseItemAndQuestionCarryBoolean() {
        assertTrue(TrueFalseItem("e", "ع", isTrue = true).isTrue)
        assertTrue(TrueFalseQuestion("s", correctAnswer = false).correctAnswer.not())
    }
}
```

- [ ] **Step 2: Run test to verify it fails**

Run: `JAVA_HOME=/Users/abdalqaderalnajjar/Library/Java/JavaVirtualMachines/azul-17.0.8.1/Contents/Home ./gradlew testDebugUnitTest --tests "com.Elkood.ling_en4.data.model.ModelInvariantsTest"`
Expected: FAIL — unresolved references (`WordPair`, `ReferenceTopic`, `MolakhsSummary`, …).

- [ ] **Step 3: Write minimal implementation**

`data/model/WordPair.kt`:
```kotlin
package com.Elkood.ling_en4.data.model

data class WordPair(val english: String, val arabic: String)
```

`data/model/WordUnitHeader.kt`:
```kotlin
package com.Elkood.ling_en4.data.model

/** One card on the word-list index. [countLabel] is the verbatim Arabic count string. */
data class WordUnitHeader(val unit: Int, val title: String, val countLabel: String)
```

`data/model/ReferenceItem.kt`:
```kotlin
package com.Elkood.ling_en4.data.model

import androidx.annotation.ColorRes

data class ReferenceItem(
    val title: String,
    val englishBody: String,
    val arabicBody: String,
    @ColorRes val colorRes: Int,
)
```

`data/model/ReferenceTopic.kt`:
```kotlin
package com.Elkood.ling_en4.data.model

enum class ReferenceTopic { VOCABULARY, ABBREVIATIONS, COMPOUND_NOUNS, EXTENSIONS }
```

`data/model/MolakhsCard.kt`:
```kotlin
package com.Elkood.ling_en4.data.model

/** One card within a summary page. [title] is null for cards that have no heading. */
data class MolakhsCard(val title: String?, val english: String, val arabic: String)
```

`data/model/MolakhsSummary.kt`:
```kotlin
package com.Elkood.ling_en4.data.model

data class MolakhsSummary(val unitLabel: String, val cards: List<MolakhsCard>) {
    init { require(cards.isNotEmpty()) { "MolakhsSummary must have at least one card" } }
}
```

`data/model/TrueFalseItem.kt`:
```kotlin
package com.Elkood.ling_en4.data.model

/** Answer-key row. [isTrue] decodes the legacy ic_check_green (true) / ic_cancel_mark (false) icon. */
data class TrueFalseItem(val english: String, val arabic: String, val isTrue: Boolean)
```

`data/model/TrueFalseQuestion.kt`:
```kotlin
package com.Elkood.ling_en4.data.model

data class TrueFalseQuestion(val statement: String, val correctAnswer: Boolean)
```

- [ ] **Step 4: Run test to verify it passes**

Run: `JAVA_HOME=/Users/abdalqaderalnajjar/Library/Java/JavaVirtualMachines/azul-17.0.8.1/Contents/Home ./gradlew testDebugUnitTest --tests "com.Elkood.ling_en4.data.model.ModelInvariantsTest"`
Expected: PASS.

- [ ] **Step 5: Commit**

```bash
git add app/src/main/java/com/Elkood/ling_en4/data/model app/src/test/java/com/Elkood/ling_en4/data/model
git commit -m "feat: add Phase 5 data models and ReferenceTopic enum"
```

---

## Task 2: WordListContent (data + routing)

**Files:**
- Create: `app/src/main/java/com/Elkood/ling_en4/data/content/WordListContent.kt`
- Test: `app/src/test/java/com/Elkood/ling_en4/data/content/WordListContentTest.kt`

**Interfaces:**
- Consumes: `WordPair`, `WordUnitHeader` (Task 1).
- Produces: `object WordListContent { val wordsByUnit: Map<Int, List<WordPair>>; val indexHeaders: List<WordUnitHeader>; fun unitWordsFor(unit: Int): List<WordPair>; fun headerFor(unit: Int): WordUnitHeader? }`.

**Verbatim-port instruction (this is transcription, not authorship):** copy every `new WordsMeans("<English>","<Arabic>")` row from `unit8.java`…`unit14.java` into `wordsByUnit`, in source order, `Words`→`english`, `Means`→`arabic`. unit10 has no rows → empty list. The `indexHeaders` titles/labels come verbatim from `En4WordsConstants.java` (preserve the double space after the colon). The exact counts below are pinned and asserted by the test; if a transcription yields a different count, re-transcribe — do not edit the test.

- [ ] **Step 1: Write the failing test**

```kotlin
package com.Elkood.ling_en4.data.content

import com.Elkood.ling_en4.data.model.WordPair
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class WordListContentTest {
    @Test
    fun eachUnitHasItsPinnedCount() {
        assertEquals(52, WordListContent.unitWordsFor(8).size)
        assertEquals(69, WordListContent.unitWordsFor(9).size)
        assertEquals(0, WordListContent.unitWordsFor(10).size)
        assertEquals(120, WordListContent.unitWordsFor(11).size)
        assertEquals(51, WordListContent.unitWordsFor(12).size)
        assertEquals(106, WordListContent.unitWordsFor(13).size)
        assertEquals(35, WordListContent.unitWordsFor(14).size)
    }

    @Test
    fun invalidUnitReturnsEmptyList() {
        assertTrue(WordListContent.unitWordsFor(7).isEmpty())
        assertTrue(WordListContent.unitWordsFor(99).isEmpty())
    }

    @Test
    fun everyPairHasNonBlankEnglishAndArabic() {
        val nonEmptyUnits = listOf(8, 9, 11, 12, 13, 14)
        nonEmptyUnits.forEach { u ->
            WordListContent.unitWordsFor(u).forEach { p: WordPair ->
                assertTrue("unit $u had blank english", p.english.isNotBlank())
                assertTrue("unit $u had blank arabic", p.arabic.isNotBlank())
            }
        }
    }

    @Test
    fun indexHasSevenHeadersInUnitOrderWithVerbatimLabels() {
        assertEquals(7, WordListContent.indexHeaders.size)
        assertEquals(listOf(8, 9, 10, 11, 12, 13, 14), WordListContent.indexHeaders.map { it.unit })
        val h8 = WordListContent.headerFor(8)!!
        assertEquals("Unit 8", h8.title)
        assertEquals("عدد :  51", h8.countLabel)
        val h10 = WordListContent.headerFor(10)!!
        assertEquals("عدد :  0", h10.countLabel)
        val h14 = WordListContent.headerFor(14)!!
        assertEquals("عدد :  84", h14.countLabel)
    }

    @Test
    fun headerForUnknownUnitIsNull() {
        assertNull(WordListContent.headerFor(7))
    }
}
```

- [ ] **Step 2: Run test to verify it fails**

Run: `JAVA_HOME=/Users/abdalqaderalnajjar/Library/Java/JavaVirtualMachines/azul-17.0.8.1/Contents/Home ./gradlew testDebugUnitTest --tests "com.Elkood.ling_en4.data.content.WordListContentTest"`
Expected: FAIL — `WordListContent` unresolved.

- [ ] **Step 3: Write minimal implementation**

Create `WordListContent.kt` with this skeleton, then port the datasets verbatim from the source files. Use the private `w(...)` helper for brevity.

```kotlin
package com.Elkood.ling_en4.data.content

import com.Elkood.ling_en4.data.model.WordPair
import com.Elkood.ling_en4.data.model.WordUnitHeader

object WordListContent {

    private fun w(english: String, arabic: String) = WordPair(english, arabic)

    // Ported verbatim from unit8.java (52 rows), in source order.
    private val unit8: List<WordPair> = listOf(
        // w("<Words>", "<Means>"), … exactly 52 entries from unit8.java
    )
    // Ported verbatim from unit9.java (69 rows).
    private val unit9: List<WordPair> = listOf(/* 69 entries from unit9.java */)
    // unit10.java has no data rows.
    private val unit10: List<WordPair> = emptyList()
    // Ported verbatim from unit11.java (120 rows).
    private val unit11: List<WordPair> = listOf(/* 120 entries from unit11.java */)
    // Ported verbatim from unit12.java (51 rows).
    private val unit12: List<WordPair> = listOf(/* 51 entries from unit12.java */)
    // Ported verbatim from unit13.java (106 rows).
    private val unit13: List<WordPair> = listOf(/* 106 entries from unit13.java */)
    // Ported verbatim from unit14.java (35 rows).
    private val unit14: List<WordPair> = listOf(/* 35 entries from unit14.java */)

    val wordsByUnit: Map<Int, List<WordPair>> = mapOf(
        8 to unit8, 9 to unit9, 10 to unit10, 11 to unit11,
        12 to unit12, 13 to unit13, 14 to unit14,
    )

    // Titles + count labels ported verbatim from En4WordsConstants.java (double space after colon preserved).
    val indexHeaders: List<WordUnitHeader> = listOf(
        WordUnitHeader(8, "Unit 8", "عدد :  51"),
        WordUnitHeader(9, "Unit 9", "عدد :  68"),
        WordUnitHeader(10, "Unit 10", "عدد :  0"),
        WordUnitHeader(11, "Unit 11", "عدد :  119"),
        WordUnitHeader(12, "Unit 12", "عدد :  50"),
        WordUnitHeader(13, "Unit 13", "عدد :  105"),
        WordUnitHeader(14, "Unit 14", "عدد :  84"),
    )

    fun unitWordsFor(unit: Int): List<WordPair> = wordsByUnit[unit] ?: emptyList()

    fun headerFor(unit: Int): WordUnitHeader? = indexHeaders.firstOrNull { it.unit == unit }
}
```

> **Transcription note for the implementer:** open each `unitN.java`, copy the exact `Words`/`Means` string literals (Arabic included) into the corresponding `listOf(...)`. Keep source order. Do not paraphrase, trim, or fix typos. Re-open `En4WordsConstants.java` to confirm the 7 title/label strings match byte-for-byte (some labels intentionally disagree with the real row count — that is correct and must be preserved).

- [ ] **Step 4: Run test to verify it passes**

Run: `JAVA_HOME=/Users/abdalqaderalnajjar/Library/Java/JavaVirtualMachines/azul-17.0.8.1/Contents/Home ./gradlew testDebugUnitTest --tests "com.Elkood.ling_en4.data.content.WordListContentTest"`
Expected: PASS. If a count assertion fails, re-transcribe that unit from source (the pinned count is the contract).

- [ ] **Step 5: Commit**

```bash
git add app/src/main/java/com/Elkood/ling_en4/data/content/WordListContent.kt app/src/test/java/com/Elkood/ling_en4/data/content/WordListContentTest.kt
git commit -m "feat: port word-list content verbatim with pinned counts"
```

---

## Task 3: Word-list screens + activities

**Files:**
- Create: `ui/screens/wordlist/WordIndexScreen.kt`, `WordIndexActivity.kt`, `UnitWordsScreen.kt`, `UnitWordsActivity.kt`
- Modify: `app/src/main/AndroidManifest.xml` (register both activities)

**Interfaces:**
- Consumes: `WordListContent`, `WordUnitHeader`, `WordPair`.
- Produces: `WordIndexActivity` (no extras); `UnitWordsActivity` with `companion object { const val EXTRA_UNIT = "extra_unit" }`.

No unit test (UI layer — matches Phase 4, where screens have no JVM tests; the routing/data logic is covered by Task 2). The deliverable is verified by the build gate.

- [ ] **Step 1: Write `WordIndexScreen.kt`**

```kotlin
package com.Elkood.ling_en4.ui.screens.wordlist

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.Elkood.ling_en4.R
import com.Elkood.ling_en4.data.content.WordListContent
import com.Elkood.ling_en4.data.model.WordUnitHeader

@Composable
fun WordIndexScreen(onUnitClick: (Int) -> Unit) {
    val muli = FontFamily(Font(R.font.muli))
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize().padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(WordListContent.indexHeaders) { header: WordUnitHeader ->
            UnitIndexCard(header, muli) { onUnitClick(header.unit) }
        }
    }
}

@Composable
private fun UnitIndexCard(header: WordUnitHeader, font: FontFamily, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .border(1.dp, colorResource(R.color.grey), RoundedCornerShape(6.dp))
            .background(colorResource(R.color.white), RoundedCornerShape(6.dp))
            .clickable(onClick = onClick)
            .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = header.title,
            color = colorResource(R.color.backber),
            fontFamily = font,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
        )
        Text(
            text = header.countLabel,
            color = colorResource(R.color.backber),
            fontFamily = font,
            modifier = Modifier.padding(top = 8.dp),
        )
    }
}
```

- [ ] **Step 2: Write `WordIndexActivity.kt`**

```kotlin
package com.Elkood.ling_en4.ui.screens.wordlist

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.Elkood.ling_en4.ui.theme.ZetaTheme

class WordIndexActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ZetaTheme {
                WordIndexScreen(onUnitClick = { unit ->
                    startActivity(
                        Intent(this, UnitWordsActivity::class.java)
                            .putExtra(UnitWordsActivity.EXTRA_UNIT, unit),
                    )
                })
            }
        }
    }
}
```

- [ ] **Step 3: Write `UnitWordsScreen.kt`**

```kotlin
package com.Elkood.ling_en4.ui.screens.wordlist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.Elkood.ling_en4.R
import com.Elkood.ling_en4.data.content.WordListContent
import com.Elkood.ling_en4.data.model.WordPair

@Composable
fun UnitWordsScreen(unit: Int) {
    val english = FontFamily(Font(R.font.a5))
    val arabic = FontFamily(Font(R.font.tajawal_bold))
    val words = WordListContent.unitWordsFor(unit)
    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(words) { pair: WordPair -> WordRow(pair, english, arabic) }
    }
}

@Composable
private fun WordRow(pair: WordPair, englishFont: FontFamily, arabicFont: FontFamily) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        WordCell(pair.english, englishFont, Modifier.weight(1f))
        WordCell(pair.arabic, arabicFont, Modifier.weight(1f))
    }
}

@Composable
private fun WordCell(text: String, font: FontFamily, modifier: Modifier) {
    Box(
        modifier = modifier
            .height(56.dp)
            .background(colorResource(R.color.white), RoundedCornerShape(10.dp)),
        contentAlignment = Alignment.Center,
    ) {
        Text(text = text, fontFamily = font, fontWeight = FontWeight.Bold)
    }
}
```

- [ ] **Step 4: Write `UnitWordsActivity.kt`**

```kotlin
package com.Elkood.ling_en4.ui.screens.wordlist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.Elkood.ling_en4.ui.theme.ZetaTheme

class UnitWordsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val unit = intent.getIntExtra(EXTRA_UNIT, -1)
        setContent { ZetaTheme { UnitWordsScreen(unit = unit) } }
    }

    companion object { const val EXTRA_UNIT = "extra_unit" }
}
```

- [ ] **Step 5: Register both activities in `AndroidManifest.xml`**

Add inside `<application>` (mirror the existing Compose activity format):
```xml
<activity android:name=".ui.screens.wordlist.WordIndexActivity" android:screenOrientation="fullSensor" android:theme="@style/AppTheme" />
<activity android:name=".ui.screens.wordlist.UnitWordsActivity" android:screenOrientation="fullSensor" android:theme="@style/AppTheme" />
```

- [ ] **Step 6: Run the build gate**

Run: `JAVA_HOME=/Users/abdalqaderalnajjar/Library/Java/JavaVirtualMachines/azul-17.0.8.1/Contents/Home ./gradlew assembleDebug testDebugUnitTest`
Expected: `BUILD SUCCESSFUL`.

- [ ] **Step 7: Commit**

```bash
git add app/src/main/java/com/Elkood/ling_en4/ui/screens/wordlist app/src/main/AndroidManifest.xml
git commit -m "feat: add Compose word-list index and unit-detail screens"
```

---

## Task 4: ReferenceContent + topic routing

**Files:**
- Create: `app/src/main/java/com/Elkood/ling_en4/data/content/ReferenceContent.kt`
- Test: `app/src/test/java/com/Elkood/ling_en4/data/content/ReferenceContentTest.kt`

**Interfaces:**
- Consumes: `ReferenceItem`, `ReferenceTopic`.
- Produces: `object ReferenceContent { fun itemsFor(topic: ReferenceTopic): List<ReferenceItem>; fun referenceTopicFromExtra(name: String?): ReferenceTopic }`.

**Verbatim-port instruction:** each accordion `addItem(title, english, arabic, colorRes)` call in the source `.java` becomes one `ReferenceItem`. `colorRes` is one of exactly `R.color.colorPrimary` or `R.color.color4` — preserve each row's original color. Counts pinned: VOCABULARY 45, ABBREVIATIONS 25, COMPOUND_NOUNS 20, EXTENSIONS 20.

- [ ] **Step 1: Write the failing test**

```kotlin
package com.Elkood.ling_en4.data.content

import com.Elkood.ling_en4.R
import com.Elkood.ling_en4.data.model.ReferenceTopic
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class ReferenceContentTest {
    @Test
    fun eachTopicHasItsPinnedCount() {
        assertEquals(45, ReferenceContent.itemsFor(ReferenceTopic.VOCABULARY).size)
        assertEquals(25, ReferenceContent.itemsFor(ReferenceTopic.ABBREVIATIONS).size)
        assertEquals(20, ReferenceContent.itemsFor(ReferenceTopic.COMPOUND_NOUNS).size)
        assertEquals(20, ReferenceContent.itemsFor(ReferenceTopic.EXTENSIONS).size)
    }

    @Test
    fun everyColorTagIsOneOfTheTwoLegacyColors() {
        val allowed = setOf(R.color.colorPrimary, R.color.color4)
        ReferenceTopic.entries.forEach { topic ->
            ReferenceContent.itemsFor(topic).forEach { item ->
                assertTrue("bad colorRes in $topic", item.colorRes in allowed)
                assertTrue(item.title.isNotBlank())
            }
        }
    }

    @Test
    fun extraStringResolvesToTopicWithVocabularyDefault() {
        assertEquals(ReferenceTopic.ABBREVIATIONS, ReferenceContent.referenceTopicFromExtra("ABBREVIATIONS"))
        assertEquals(ReferenceTopic.EXTENSIONS, ReferenceContent.referenceTopicFromExtra("EXTENSIONS"))
        assertEquals(ReferenceTopic.VOCABULARY, ReferenceContent.referenceTopicFromExtra(null))
        assertEquals(ReferenceTopic.VOCABULARY, ReferenceContent.referenceTopicFromExtra("garbage"))
    }
}
```

- [ ] **Step 2: Run test to verify it fails**

Run: `JAVA_HOME=/Users/abdalqaderalnajjar/Library/Java/JavaVirtualMachines/azul-17.0.8.1/Contents/Home ./gradlew testDebugUnitTest --tests "com.Elkood.ling_en4.data.content.ReferenceContentTest"`
Expected: FAIL — `ReferenceContent` unresolved.

- [ ] **Step 3: Write minimal implementation**

```kotlin
package com.Elkood.ling_en4.data.content

import com.Elkood.ling_en4.R
import com.Elkood.ling_en4.data.model.ReferenceItem
import com.Elkood.ling_en4.data.model.ReferenceTopic

object ReferenceContent {

    private fun item(title: String, english: String, arabic: String, colorRes: Int) =
        ReferenceItem(title, english, arabic, colorRes)

    // Ported verbatim from Vocabulary.java addItem(...) calls (45 items).
    private val vocabulary: List<ReferenceItem> = listOf(
        // item("<title>", "<english>", "<arabic>", R.color.colorPrimary | R.color.color4), … 45 entries
    )
    // Ported verbatim from Abbreviations.java (25 items).
    private val abbreviations: List<ReferenceItem> = listOf(/* 25 entries */)
    // Ported verbatim from Compound_Nouns.java (20 items).
    private val compoundNouns: List<ReferenceItem> = listOf(/* 20 entries */)
    // Ported verbatim from Extinsions.java (20 items).
    private val extensions: List<ReferenceItem> = listOf(/* 20 entries */)

    fun itemsFor(topic: ReferenceTopic): List<ReferenceItem> = when (topic) {
        ReferenceTopic.VOCABULARY -> vocabulary
        ReferenceTopic.ABBREVIATIONS -> abbreviations
        ReferenceTopic.COMPOUND_NOUNS -> compoundNouns
        ReferenceTopic.EXTENSIONS -> extensions
    }

    fun referenceTopicFromExtra(name: String?): ReferenceTopic =
        ReferenceTopic.entries.firstOrNull { it.name == name } ?: ReferenceTopic.VOCABULARY
}
```

> **Transcription note:** for each `addItem(...)` in the four source files, copy the title, English body, and Arabic body verbatim, and map the color argument to `R.color.colorPrimary` or `R.color.color4` exactly as the source uses it.

- [ ] **Step 4: Run test to verify it passes**

Run: `JAVA_HOME=/Users/abdalqaderalnajjar/Library/Java/JavaVirtualMachines/azul-17.0.8.1/Contents/Home ./gradlew testDebugUnitTest --tests "com.Elkood.ling_en4.data.content.ReferenceContentTest"`
Expected: PASS.

- [ ] **Step 5: Commit**

```bash
git add app/src/main/java/com/Elkood/ling_en4/data/content/ReferenceContent.kt app/src/test/java/com/Elkood/ling_en4/data/content/ReferenceContentTest.kt
git commit -m "feat: port accordion reference content with pinned counts"
```

---

## Task 5: ReferenceAccordionScreen + activity

**Files:**
- Create: `ui/screens/reference/ReferenceAccordionScreen.kt`, `ReferenceAccordionActivity.kt`
- Modify: `AndroidManifest.xml`

**Interfaces:**
- Consumes: `ReferenceContent`, `ReferenceItem`, `ReferenceTopic`.
- Produces: `ReferenceAccordionActivity` with `companion object { const val EXTRA_TOPIC = "extra_topic" }`.

- [ ] **Step 1: Write `ReferenceAccordionScreen.kt`**

```kotlin
package com.Elkood.ling_en4.ui.screens.reference

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.Elkood.ling_en4.R
import com.Elkood.ling_en4.data.content.ReferenceContent
import com.Elkood.ling_en4.data.model.ReferenceItem
import com.Elkood.ling_en4.data.model.ReferenceTopic

@Composable
fun ReferenceAccordionScreen(topic: ReferenceTopic) {
    val items = ReferenceContent.itemsFor(topic)
    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(items) { item: ReferenceItem -> AccordionRow(item) }
    }
}

@Composable
private fun AccordionRow(item: ReferenceItem) {
    var expanded by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, colorResource(R.color.grey), RoundedCornerShape(6.dp))
            .background(colorResource(R.color.white), RoundedCornerShape(6.dp))
            .clickable { expanded = !expanded }
            .padding(12.dp),
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(R.drawable.ic_clubs_word_smash_icon),
                contentDescription = null,
                colorFilter = ColorFilter.tint(colorResource(item.colorRes)),
                modifier = Modifier.size(24.dp),
            )
            Text(
                text = item.title,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1f).padding(horizontal = 8.dp),
            )
            Image(
                painter = painterResource(if (expanded) R.mipmap.less else R.mipmap.more),
                contentDescription = null,
                modifier = Modifier.size(20.dp),
            )
        }
        AnimatedVisibility(visible = expanded) {
            Column(modifier = Modifier.padding(top = 8.dp)) {
                Text(text = item.englishBody)
                Text(text = item.arabicBody, modifier = Modifier.padding(top = 6.dp))
            }
        }
    }
}
```

- [ ] **Step 2: Write `ReferenceAccordionActivity.kt`**

```kotlin
package com.Elkood.ling_en4.ui.screens.reference

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.Elkood.ling_en4.data.content.ReferenceContent
import com.Elkood.ling_en4.ui.theme.ZetaTheme

class ReferenceAccordionActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val topic = ReferenceContent.referenceTopicFromExtra(intent.getStringExtra(EXTRA_TOPIC))
        setContent { ZetaTheme { ReferenceAccordionScreen(topic = topic) } }
    }

    companion object { const val EXTRA_TOPIC = "extra_topic" }
}
```

- [ ] **Step 3: Register in `AndroidManifest.xml`**

```xml
<activity android:name=".ui.screens.reference.ReferenceAccordionActivity" android:screenOrientation="fullSensor" android:theme="@style/AppTheme" />
```

- [ ] **Step 4: Run the build gate**

Run: `JAVA_HOME=/Users/abdalqaderalnajjar/Library/Java/JavaVirtualMachines/azul-17.0.8.1/Contents/Home ./gradlew assembleDebug testDebugUnitTest`
Expected: `BUILD SUCCESSFUL`.

- [ ] **Step 5: Commit**

```bash
git add app/src/main/java/com/Elkood/ling_en4/ui/screens/reference app/src/main/AndroidManifest.xml
git commit -m "feat: add reusable Compose reference accordion screen"
```

---

## Task 6: MolakhsContent

**Files:**
- Create: `app/src/main/java/com/Elkood/ling_en4/data/content/MolakhsContent.kt`
- Test: `app/src/test/java/com/Elkood/ling_en4/data/content/MolakhsContentTest.kt`

**Interfaces:**
- Consumes: `MolakhsSummary`, `MolakhsCard`.
- Produces: `object MolakhsContent { val summaries: List<MolakhsSummary> }`.

**Verbatim-port instruction:** all summary text is static `android:text` inside `fragment_munit{8,9,11,12,13,14}.xml`. For each fragment, walk its card `LinearLayout`s in layout order; each card becomes a `MolakhsCard(title, english, arabic)` — `title` is the heading TextView text if the card has one (else `null`), `english` the English paragraph, `arabic` the Arabic paragraph. 6 summaries, tab labels verbatim WITH trailing spaces: `"Unit 8 "`, `"Unit 9 "`, `"Unit 11 "`, `"Unit 12 "`, `"Unit 13 "`, `"Unit 14 "`. No Unit 10.

- [ ] **Step 1: Write the failing test**

```kotlin
package com.Elkood.ling_en4.data.content

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class MolakhsContentTest {
    @Test
    fun hasSixSummariesWithVerbatimTrailingSpaceLabelsAndNoUnitTen() {
        assertEquals(6, MolakhsContent.summaries.size)
        assertEquals(
            listOf("Unit 8 ", "Unit 9 ", "Unit 11 ", "Unit 12 ", "Unit 13 ", "Unit 14 "),
            MolakhsContent.summaries.map { it.unitLabel },
        )
        assertFalse(MolakhsContent.summaries.any { it.unitLabel.trim() == "Unit 10" })
    }

    @Test
    fun everySummaryHasAtLeastOneCardWithContent() {
        MolakhsContent.summaries.forEach { summary ->
            assertTrue("${summary.unitLabel} had no cards", summary.cards.isNotEmpty())
            summary.cards.forEach { card ->
                assertTrue(card.english.isNotBlank() || card.arabic.isNotBlank())
            }
        }
    }
}
```

- [ ] **Step 2: Run test to verify it fails**

Run: `JAVA_HOME=/Users/abdalqaderalnajjar/Library/Java/JavaVirtualMachines/azul-17.0.8.1/Contents/Home ./gradlew testDebugUnitTest --tests "com.Elkood.ling_en4.data.content.MolakhsContentTest"`
Expected: FAIL — `MolakhsContent` unresolved.

- [ ] **Step 3: Write minimal implementation**

```kotlin
package com.Elkood.ling_en4.data.content

import com.Elkood.ling_en4.data.model.MolakhsCard
import com.Elkood.ling_en4.data.model.MolakhsSummary

object MolakhsContent {

    private fun card(title: String?, english: String, arabic: String) =
        MolakhsCard(title, english, arabic)

    // Each summary's cards ported verbatim from fragment_munitN.xml android:text values, in layout order.
    val summaries: List<MolakhsSummary> = listOf(
        MolakhsSummary("Unit 8 ", listOf(/* cards from fragment_munit8.xml */)),
        MolakhsSummary("Unit 9 ", listOf(/* cards from fragment_munit9.xml */)),
        MolakhsSummary("Unit 11 ", listOf(/* cards from fragment_munit11.xml */)),
        MolakhsSummary("Unit 12 ", listOf(/* cards from fragment_munit12.xml */)),
        MolakhsSummary("Unit 13 ", listOf(/* cards from fragment_munit13.xml */)),
        MolakhsSummary("Unit 14 ", listOf(/* cards from fragment_munit14.xml */)),
    )
}
```

> **Transcription note:** open each `fragment_munitN.xml`; for every card `LinearLayout`, transcribe the heading (if present) → `title`, the English `TextView` text → `english`, the Arabic `TextView` text → `arabic`, using the `card(...)` helper. Preserve line breaks with `\n`. Each summary must have ≥1 card (the model's `require` enforces this).

- [ ] **Step 4: Run test to verify it passes**

Run: `JAVA_HOME=/Users/abdalqaderalnajjar/Library/Java/JavaVirtualMachines/azul-17.0.8.1/Contents/Home ./gradlew testDebugUnitTest --tests "com.Elkood.ling_en4.data.content.MolakhsContentTest"`
Expected: PASS.

- [ ] **Step 5: Commit**

```bash
git add app/src/main/java/com/Elkood/ling_en4/data/content/MolakhsContent.kt app/src/test/java/com/Elkood/ling_en4/data/content/MolakhsContentTest.kt
git commit -m "feat: port molakhs summary content from fragment layouts"
```

---

## Task 7: MolakhsScreen + activity

**Files:**
- Create: `ui/screens/molakhs/MolakhsScreen.kt`, `MolakhsActivity.kt`
- Modify: `AndroidManifest.xml`

**Interfaces:**
- Consumes: `MolakhsContent`, `MolakhsSummary`, `MolakhsCard`.
- Produces: `MolakhsActivity` (no extras; hosts all 6 tabs internally).

- [ ] **Step 1: Write `MolakhsScreen.kt`** (tabs + pager)

```kotlin
package com.Elkood.ling_en4.ui.screens.molakhs

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.Elkood.ling_en4.R
import com.Elkood.ling_en4.data.content.MolakhsContent
import com.Elkood.ling_en4.data.model.MolakhsCard
import com.Elkood.ling_en4.data.model.MolakhsSummary
import kotlinx.coroutines.launch

@Composable
fun MolakhsScreen() {
    val summaries = MolakhsContent.summaries
    val pagerState = rememberPagerState(pageCount = { summaries.size })
    val scope = rememberCoroutineScope()
    val indicator = Color(0xFF0FB2C0)
    Column(modifier = Modifier.fillMaxSize().background(colorResource(R.color.white))) {
        ScrollableTabRow(
            selectedTabIndex = pagerState.currentPage,
            contentColor = indicator,
        ) {
            summaries.forEachIndexed { index, summary ->
                Tab(
                    selected = pagerState.currentPage == index,
                    onClick = { scope.launch { pagerState.animateScrollToPage(index) } },
                    text = { Text(summary.unitLabel) },
                )
            }
        }
        HorizontalPager(state = pagerState, modifier = Modifier.fillMaxSize()) { page ->
            SummaryPage(summaries[page])
        }
    }
}

@Composable
private fun SummaryPage(summary: MolakhsSummary) {
    val heading = FontFamily(Font(R.font.hugme))
    val body = FontFamily(Font(R.font.a5))
    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        items(summary.cards) { card: MolakhsCard -> SummaryCard(card, heading, body) }
    }
}

@Composable
private fun SummaryCard(card: MolakhsCard, headingFont: FontFamily, bodyFont: FontFamily) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, colorResource(R.color.grey), RoundedCornerShape(6.dp))
            .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        card.title?.let {
            Text(
                text = it,
                fontFamily = headingFont,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = colorResource(R.color.colorPrimary),
                modifier = Modifier.padding(bottom = 8.dp),
            )
        }
        Text(text = card.english, fontFamily = bodyFont, fontSize = 16.sp, textAlign = TextAlign.Center)
        HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
        Text(text = card.arabic, fontFamily = bodyFont, textAlign = TextAlign.Center)
    }
}
```

- [ ] **Step 2: Write `MolakhsActivity.kt`**

```kotlin
package com.Elkood.ling_en4.ui.screens.molakhs

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.Elkood.ling_en4.ui.theme.ZetaTheme

class MolakhsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { ZetaTheme { MolakhsScreen() } }
    }
}
```

- [ ] **Step 3: Register in `AndroidManifest.xml`**

```xml
<activity android:name=".ui.screens.molakhs.MolakhsActivity" android:screenOrientation="fullSensor" android:theme="@style/AppTheme" />
```

- [ ] **Step 4: Run the build gate**

Run: `JAVA_HOME=/Users/abdalqaderalnajjar/Library/Java/JavaVirtualMachines/azul-17.0.8.1/Contents/Home ./gradlew assembleDebug testDebugUnitTest`
Expected: `BUILD SUCCESSFUL`.

> If `HorizontalPager`/`rememberPagerState(pageCount = {...})` are unavailable in compose-bom 2024.09.00, they are — the pager graduated to `androidx.compose.foundation.pager` and the `pageCount` lambda overload is standard at this BOM. No extra dependency needed.

- [ ] **Step 5: Commit**

```bash
git add app/src/main/java/com/Elkood/ling_en4/ui/screens/molakhs app/src/main/AndroidManifest.xml
git commit -m "feat: add Compose molakhs tabbed summary screen"
```

---

## Task 8: TrueFalseContent (list + quiz pool)

**Files:**
- Create: `app/src/main/java/com/Elkood/ling_en4/data/content/TrueFalseContent.kt`
- Test: `app/src/test/java/com/Elkood/ling_en4/data/content/TrueFalseContentTest.kt`

**Interfaces:**
- Consumes: `TrueFalseItem`, `TrueFalseQuestion`.
- Produces: `object TrueFalseContent { val listItems: List<TrueFalseItem>; val quizPool: List<TrueFalseQuestion> }`.

**Verbatim-port instruction:** `listItems` from `En4TrueFalseConstants.java` — `text`→`english`, `desc`→`arabic`, `isTrue = (image == ic_check_green)`. Pinned: 36 items, 18 true / 18 false. `quizPool` from `QuizActivity_True_false.java`'s `quizData` (3-col rows) — col0→`statement`, col1 (`"true"`/`"false"`)→`correctAnswer`. Pinned: 35 questions.

- [ ] **Step 1: Write the failing test**

```kotlin
package com.Elkood.ling_en4.data.content

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class TrueFalseContentTest {
    @Test
    fun listHasThirtySixItemsSplitEighteenEighteen() {
        assertEquals(36, TrueFalseContent.listItems.size)
        assertEquals(18, TrueFalseContent.listItems.count { it.isTrue })
        assertEquals(18, TrueFalseContent.listItems.count { !it.isTrue })
    }

    @Test
    fun everyListItemHasBothLanguages() {
        TrueFalseContent.listItems.forEach {
            assertTrue(it.english.isNotBlank())
            assertTrue(it.arabic.isNotBlank())
        }
    }

    @Test
    fun quizPoolHasThirtyFiveQuestionsWithNonBlankStatements() {
        assertEquals(35, TrueFalseContent.quizPool.size)
        TrueFalseContent.quizPool.forEach { assertTrue(it.statement.isNotBlank()) }
    }
}
```

- [ ] **Step 2: Run test to verify it fails**

Run: `JAVA_HOME=/Users/abdalqaderalnajjar/Library/Java/JavaVirtualMachines/azul-17.0.8.1/Contents/Home ./gradlew testDebugUnitTest --tests "com.Elkood.ling_en4.data.content.TrueFalseContentTest"`
Expected: FAIL — `TrueFalseContent` unresolved.

- [ ] **Step 3: Write minimal implementation**

```kotlin
package com.Elkood.ling_en4.data.content

import com.Elkood.ling_en4.data.model.TrueFalseItem
import com.Elkood.ling_en4.data.model.TrueFalseQuestion

object TrueFalseContent {

    private fun li(english: String, arabic: String, isTrue: Boolean) =
        TrueFalseItem(english, arabic, isTrue)

    private fun q(statement: String, correctAnswer: Boolean) =
        TrueFalseQuestion(statement, correctAnswer)

    // Ported verbatim from En4TrueFalseConstants.java (36 items; isTrue = image was ic_check_green).
    val listItems: List<TrueFalseItem> = listOf(
        // li("<text>", "<desc>", true|false), … 36 entries (18 true, 18 false)
    )

    // Ported verbatim from QuizActivity_True_false.java quizData (35 rows; col1 "true"/"false").
    val quizPool: List<TrueFalseQuestion> = listOf(
        // q("<statement>", true|false), … 35 entries
    )
}
```

> **Transcription note:** in `En4TrueFalseConstants.java` each row supplies `text`, `desc`, and an icon; set `isTrue = true` when the icon is `ic_check_green`, `false` when `ic_cancel_mark`. In `QuizActivity_True_false.java` the `quizData` 2-D array's column 1 is the string `"true"` or `"false"` → `correctAnswer = column1 == "true"`. Ignore column 2 (the distractor); the Compose screen shows a fixed TRUE/FALSE pair.

- [ ] **Step 4: Run test to verify it passes**

Run: `JAVA_HOME=/Users/abdalqaderalnajjar/Library/Java/JavaVirtualMachines/azul-17.0.8.1/Contents/Home ./gradlew testDebugUnitTest --tests "com.Elkood.ling_en4.data.content.TrueFalseContentTest"`
Expected: PASS.

- [ ] **Step 5: Commit**

```bash
git add app/src/main/java/com/Elkood/ling_en4/data/content/TrueFalseContent.kt app/src/test/java/com/Elkood/ling_en4/data/content/TrueFalseContentTest.kt
git commit -m "feat: port true/false list and quiz-pool content"
```

---

## Task 9: TrueFalseListScreen + activity

**Files:**
- Create: `ui/screens/truefalse/TrueFalseListScreen.kt`, `TrueFalseListActivity.kt`
- Modify: `AndroidManifest.xml`

**Interfaces:**
- Consumes: `TrueFalseContent`, `TrueFalseItem`.
- Produces: `TrueFalseListActivity` (no extras).

- [ ] **Step 1: Write `TrueFalseListScreen.kt`** (icon on LEFT, LTR root, `cardborder_blue`)

```kotlin
package com.Elkood.ling_en4.ui.screens.truefalse

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.Elkood.ling_en4.R
import com.Elkood.ling_en4.data.content.TrueFalseContent
import com.Elkood.ling_en4.data.model.TrueFalseItem

@Composable
fun TrueFalseListScreen() {
    val english = FontFamily(Font(R.font.muli))
    val arabic = FontFamily(Font(R.font.a3))
    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(TrueFalseContent.listItems) { item: TrueFalseItem -> TrueFalseRow(item, english, arabic) }
    }
}

@Composable
private fun TrueFalseRow(item: TrueFalseItem, englishFont: FontFamily, arabicFont: FontFamily) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, colorResource(R.color.blueberry), RoundedCornerShape(6.dp))
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            painter = painterResource(if (item.isTrue) R.drawable.ic_check_green else R.drawable.ic_cancel_mark),
            contentDescription = null,
            modifier = Modifier.weight(3f).size(40.dp),
        )
        Column(modifier = Modifier.weight(1f).padding(start = 12.dp)) {
            Text(
                text = item.english,
                fontFamily = englishFont,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = colorResource(R.color.backber),
            )
            Text(text = item.arabic, fontFamily = arabicFont, modifier = Modifier.padding(top = 4.dp))
        }
    }
}
```

> The `row_true_false.xml` weights are icon-block 3 / text-block 1 with the icon on the left; the `size(40.dp)` keeps the icon visually bounded within its weighted slot. Root is LTR (Compose default), matching the legacy layout.

- [ ] **Step 2: Write `TrueFalseListActivity.kt`**

```kotlin
package com.Elkood.ling_en4.ui.screens.truefalse

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.Elkood.ling_en4.ui.theme.ZetaTheme

class TrueFalseListActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { ZetaTheme { TrueFalseListScreen() } }
    }
}
```

- [ ] **Step 3: Register in `AndroidManifest.xml`**

```xml
<activity android:name=".ui.screens.truefalse.TrueFalseListActivity" android:screenOrientation="fullSensor" android:theme="@style/AppTheme" />
```

- [ ] **Step 4: Run the build gate**

Run: `JAVA_HOME=/Users/abdalqaderalnajjar/Library/Java/JavaVirtualMachines/azul-17.0.8.1/Contents/Home ./gradlew assembleDebug testDebugUnitTest`
Expected: `BUILD SUCCESSFUL`.

- [ ] **Step 5: Commit**

```bash
git add app/src/main/java/com/Elkood/ling_en4/ui/screens/truefalse app/src/main/AndroidManifest.xml
git commit -m "feat: add Compose true/false answer-key list screen"
```

---

## Task 10: TrueFalseQuizEngine (pure logic)

**Files:**
- Create: `app/src/main/java/com/Elkood/ling_en4/ui/screens/truefalse/TrueFalseQuizEngine.kt`
- Test: `app/src/test/java/com/Elkood/ling_en4/ui/screens/truefalse/TrueFalseQuizEngineTest.kt`

**Interfaces:**
- Consumes: `TrueFalseQuestion`.
- Produces:
  - `const val TF_PREFS = "SaveScore"`, `const val TF_KEY = "tfscore"`
  - `fun shouldPersistHighScore(stored: Int, new: Int): Boolean = new > stored`
  - `class TrueFalseQuizEngine(questions: List<TrueFalseQuestion>, order: List<Int>? = null)` with:
    - `val total: Int`
    - `var score: Int` (read-only externally via property)
    - `fun current(): TrueFalseQuestion`
    - `fun hasNext(): Boolean`
    - `fun answer(choice: Boolean): Boolean` — returns correctness, increments score on correct, advances internal pointer
    - `fun isFinished(): Boolean`

The `order` parameter makes shuffling injectable so tests are deterministic; production passes `questions.indices.shuffled()`.

- [ ] **Step 1: Write the failing test**

```kotlin
package com.Elkood.ling_en4.ui.screens.truefalse

import com.Elkood.ling_en4.data.model.TrueFalseQuestion
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class TrueFalseQuizEngineTest {

    private val pool = listOf(
        TrueFalseQuestion("A", true),
        TrueFalseQuestion("B", false),
        TrueFalseQuestion("C", true),
    )

    @Test
    fun visitsEveryQuestionOnceWithoutRepeat() {
        val engine = TrueFalseQuizEngine(pool, order = listOf(0, 1, 2))
        val seen = mutableListOf<String>()
        while (!engine.isFinished()) {
            seen.add(engine.current().statement)
            engine.answer(true)
        }
        assertEquals(listOf("A", "B", "C"), seen)
        assertEquals(seen.toSet().size, seen.size)
    }

    @Test
    fun scoreIncrementsOnlyOnCorrect() {
        val engine = TrueFalseQuizEngine(pool, order = listOf(0, 1, 2))
        assertTrue(engine.answer(true))   // A is true -> correct
        assertTrue(engine.answer(true).not()) // B is false, answered true -> wrong
        assertTrue(engine.answer(true))   // C is true -> correct
        assertEquals(2, engine.score)
    }

    @Test
    fun finishesAfterAllQuestions() {
        val engine = TrueFalseQuizEngine(pool, order = listOf(0, 1, 2))
        assertFalse(engine.isFinished())
        repeat(3) { engine.answer(false) }
        assertTrue(engine.isFinished())
        assertFalse(engine.hasNext())
    }

    @Test
    fun highScorePersistsOnlyWhenBeaten() {
        assertTrue(shouldPersistHighScore(stored = 5, new = 6))
        assertFalse(shouldPersistHighScore(stored = 6, new = 6))
        assertFalse(shouldPersistHighScore(stored = 7, new = 6))
    }

    @Test
    fun prefsConstantsMatchLegacy() {
        assertEquals("SaveScore", TF_PREFS)
        assertEquals("tfscore", TF_KEY)
    }
}
```

- [ ] **Step 2: Run test to verify it fails**

Run: `JAVA_HOME=/Users/abdalqaderalnajjar/Library/Java/JavaVirtualMachines/azul-17.0.8.1/Contents/Home ./gradlew testDebugUnitTest --tests "com.Elkood.ling_en4.ui.screens.truefalse.TrueFalseQuizEngineTest"`
Expected: FAIL — `TrueFalseQuizEngine` unresolved.

- [ ] **Step 3: Write minimal implementation**

```kotlin
package com.Elkood.ling_en4.ui.screens.truefalse

import com.Elkood.ling_en4.data.model.TrueFalseQuestion

const val TF_PREFS = "SaveScore"
const val TF_KEY = "tfscore"

/** Legacy contract: write the new score only when it beats the stored one. */
fun shouldPersistHighScore(stored: Int, new: Int): Boolean = new > stored

/**
 * Pure, testable True/False quiz mechanics: pick-without-repeat over [questions] in [order]
 * (defaults to source order; production injects a shuffle), score on correct answers only.
 */
class TrueFalseQuizEngine(
    private val questions: List<TrueFalseQuestion>,
    order: List<Int>? = null,
) {
    private val sequence: List<TrueFalseQuestion> =
        (order ?: questions.indices.toList()).map { questions[it] }
    private var index: Int = 0
    var score: Int = 0
        private set

    val total: Int get() = sequence.size

    fun current(): TrueFalseQuestion = sequence[index]

    fun hasNext(): Boolean = index < sequence.size - 1

    fun isFinished(): Boolean = index >= sequence.size

    /** Returns whether [choice] was correct; increments score on correct; advances the pointer. */
    fun answer(choice: Boolean): Boolean {
        val correct = choice == sequence[index].correctAnswer
        if (correct) score++
        index++
        return correct
    }
}
```

- [ ] **Step 4: Run test to verify it passes**

Run: `JAVA_HOME=/Users/abdalqaderalnajjar/Library/Java/JavaVirtualMachines/azul-17.0.8.1/Contents/Home ./gradlew testDebugUnitTest --tests "com.Elkood.ling_en4.ui.screens.truefalse.TrueFalseQuizEngineTest"`
Expected: PASS.

- [ ] **Step 5: Commit**

```bash
git add app/src/main/java/com/Elkood/ling_en4/ui/screens/truefalse/TrueFalseQuizEngine.kt app/src/test/java/com/Elkood/ling_en4/ui/screens/truefalse/TrueFalseQuizEngineTest.kt
git commit -m "feat: add pure True/False quiz engine with tests"
```

---

## Task 11: TrueFalseQuizScreen + activity

**Files:**
- Create: `ui/screens/truefalse/TrueFalseQuizScreen.kt`, `TrueFalseQuizActivity.kt`
- Modify: `AndroidManifest.xml`

**Interfaces:**
- Consumes: `TrueFalseQuizEngine`, `TF_PREFS`, `TF_KEY`, `shouldPersistHighScore`, `TrueFalseContent.quizPool`, `QuizSound`.
- Produces: `TrueFalseQuizActivity` (no extras).

The Activity owns the engine (shuffled pool), the `QuizSound`, the prefs read/write, and drives the screen. High score is written on exit (`onPause`/back), only when beaten.

- [ ] **Step 1: Write `TrueFalseQuizScreen.kt`**

```kotlin
package com.Elkood.ling_en4.ui.screens.truefalse

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.Elkood.ling_en4.data.model.TrueFalseQuestion
import com.Elkood.ling_en4.ui.components.PrimaryButton

/**
 * [engine] is created and owned by the Activity. [onCorrect]/[onWrong] play sound.
 * [onScoreChanged] reports the running score so the Activity can persist the high score on exit.
 */
@Composable
fun TrueFalseQuizScreen(
    engine: TrueFalseQuizEngine,
    onCorrect: () -> Unit,
    onWrong: () -> Unit,
    onScoreChanged: (Int) -> Unit,
) {
    var questionNumber by remember { mutableIntStateOf(1) }
    var score by remember { mutableIntStateOf(0) }
    var finished by remember { mutableStateOf(engine.isFinished()) }
    var dialog by remember { mutableStateOf<DialogState?>(null) }
    var currentStatement by remember { mutableStateOf(if (finished) "" else engine.current().statement) }

    if (finished) {
        Column(
            modifier = Modifier.fillMaxSize().padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(text = "Score : $score / ${engine.total}", fontSize = 24.sp)
        }
        return
    }

    fun submit(choice: Boolean) {
        val q: TrueFalseQuestion = engine.current()
        val correct = engine.answer(choice)
        if (correct) { score = engine.score; onScoreChanged(score); onCorrect() } else { onWrong() }
        dialog = DialogState(
            title = if (correct) "Correct!" else "Wrong...",
            message = "Answer : " + if (q.correctAnswer) "true" else "false",
        )
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
    ) {
        Text(text = "Q$questionNumber", fontSize = 20.sp)
        Text(text = "Score : $score", fontSize = 18.sp)
        Text(text = currentStatement, fontSize = 22.sp, textAlign = TextAlign.Center)
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            PrimaryButton(text = "TRUE", onClick = { submit(true) })
            PrimaryButton(text = "FALSE", onClick = { submit(false) })
        }
    }

    dialog?.let { state ->
        AlertDialog(
            onDismissRequest = { /* non-cancelable: no-op, matches legacy setCancelable(false) */ },
            title = { Text(state.title) },
            text = { Text(state.message) },
            confirmButton = {
                TextButton(onClick = {
                    dialog = null
                    if (engine.isFinished()) {
                        finished = true
                    } else {
                        questionNumber += 1
                        currentStatement = engine.current().statement
                    }
                }) { Text("OK") }
            },
        )
    }
}

private data class DialogState(val title: String, val message: String)
```

- [ ] **Step 2: Write `TrueFalseQuizActivity.kt`**

```kotlin
package com.Elkood.ling_en4.ui.screens.truefalse

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.Elkood.ling_en4.data.content.TrueFalseContent
import com.Elkood.ling_en4.ui.screens.quiz.QuizSound
import com.Elkood.ling_en4.ui.theme.ZetaTheme

class TrueFalseQuizActivity : ComponentActivity() {

    private lateinit var sound: QuizSound
    private val engine by lazy {
        TrueFalseQuizEngine(
            questions = TrueFalseContent.quizPool,
            order = TrueFalseContent.quizPool.indices.shuffled(),
        )
    }
    private var latestScore = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sound = QuizSound(this)
        setContent {
            ZetaTheme {
                TrueFalseQuizScreen(
                    engine = engine,
                    onCorrect = { sound.playCorrect() },
                    onWrong = { sound.playWrong() },
                    onScoreChanged = { latestScore = it },
                )
            }
        }
    }

    override fun onPause() {
        super.onPause()
        val prefs = getSharedPreferences(TF_PREFS, Context.MODE_PRIVATE)
        val stored = prefs.getInt(TF_KEY, 0)
        if (shouldPersistHighScore(stored, latestScore)) {
            prefs.edit().putInt(TF_KEY, latestScore).apply()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        sound.release()
    }
}
```

- [ ] **Step 3: Register in `AndroidManifest.xml`**

```xml
<activity android:name=".ui.screens.truefalse.TrueFalseQuizActivity" android:screenOrientation="fullSensor" android:theme="@style/AppTheme" />
```

- [ ] **Step 4: Run the build gate**

Run: `JAVA_HOME=/Users/abdalqaderalnajjar/Library/Java/JavaVirtualMachines/azul-17.0.8.1/Contents/Home ./gradlew assembleDebug testDebugUnitTest`
Expected: `BUILD SUCCESSFUL`.

- [ ] **Step 5: Commit**

```bash
git add app/src/main/java/com/Elkood/ling_en4/ui/screens/truefalse app/src/main/AndroidManifest.xml
git commit -m "feat: add dedicated Compose True/False quiz screen"
```

---

## Task 12: Repoint Java entry points

**Files:**
- Modify: `app/src/main/java/com/Elkood/ling_en4/Adapter/AdapterHome.java` (7-case switch + imports)
- Modify: `app/src/main/java/com/Elkood/ling_en4/BankItemsQuiz.java` (boom index 1)

**Interfaces:**
- Consumes: `WordIndexActivity`, `ReferenceAccordionActivity` (+ `EXTRA_TOPIC`), `TrueFalseListActivity`, `MolakhsActivity`, `TrueFalseQuizActivity`.

No new unit test — this is a wiring change; the build gate plus manual smoke of the home grid verify it. (Java cannot reference Kotlin `const val EXTRA_TOPIC` the same way; pass the enum name string, which `referenceTopicFromExtra` resolves.)

- [ ] **Step 1: Repoint `AdapterHome.java`**

Update the imports block (lines ~18-24) to remove the 7 legacy destination imports and add:
```java
import com.Elkood.ling_en4.ui.screens.wordlist.WordIndexActivity;
import com.Elkood.ling_en4.ui.screens.reference.ReferenceAccordionActivity;
import com.Elkood.ling_en4.ui.screens.truefalse.TrueFalseListActivity;
import com.Elkood.ling_en4.ui.screens.molakhs.MolakhsActivity;
```

Replace the positional `switch (position1)` bodies in the `ItemClickListener` so each case builds its new intent (keep `FLAG_ACTIVITY_SINGLE_TOP`; drop the "Ite 20" long-press Toast):
```java
Context ctx = view.getContext();
Intent intent;
switch (position1) {
    case 0: // word
        intent = new Intent(ctx, WordIndexActivity.class);
        break;
    case 1: // Vocabulary
        intent = new Intent(ctx, ReferenceAccordionActivity.class);
        intent.putExtra("extra_topic", "VOCABULARY");
        break;
    case 2: // True_false
        intent = new Intent(ctx, TrueFalseListActivity.class);
        break;
    case 3: // Abbreviations
        intent = new Intent(ctx, ReferenceAccordionActivity.class);
        intent.putExtra("extra_topic", "ABBREVIATIONS");
        break;
    case 4: // Compound_Nouns
        intent = new Intent(ctx, ReferenceAccordionActivity.class);
        intent.putExtra("extra_topic", "COMPOUND_NOUNS");
        break;
    case 5: // Extinsions
        intent = new Intent(ctx, ReferenceAccordionActivity.class);
        intent.putExtra("extra_topic", "EXTENSIONS");
        break;
    case 6: // molakas
    default:
        intent = new Intent(ctx, MolakhsActivity.class);
        break;
}
intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
ctx.startActivity(intent);
```

> `"extra_topic"` is the literal value of `ReferenceAccordionActivity.EXTRA_TOPIC`; keep them identical. If the existing long-click listener only shows the `Toast "Ite 20"`, remove that listener body (dead UX); leave the rest of the adapter untouched.

- [ ] **Step 2: Repoint `BankItemsQuiz.java` boom index 1**

At the index-1 branch of `changeBoomButton(index)` (was launching `QuizActivity_True_false`, ~line 102), change the target:
```java
case 1:
    startActivity(new Intent(this, com.Elkood.ling_en4.ui.screens.truefalse.TrueFalseQuizActivity.class));
    break;
```
Leave indices 0/2/3/4 (the `ComposeQuizActivity` VOCABULARY/ABBREVIATIONS/COMPOUND_NOUNS/EXTENSIONS branches) exactly as they are.

- [ ] **Step 3: Run the build gate**

Run: `JAVA_HOME=/Users/abdalqaderalnajjar/Library/Java/JavaVirtualMachines/azul-17.0.8.1/Contents/Home ./gradlew assembleDebug testDebugUnitTest`
Expected: `BUILD SUCCESSFUL`.

- [ ] **Step 4: Commit**

```bash
git add app/src/main/java/com/Elkood/ling_en4/Adapter/AdapterHome.java app/src/main/java/com/Elkood/ling_en4/BankItemsQuiz.java
git commit -m "feat: repoint home grid and BoomMenu to Compose Phase 5 screens"
```

---

## Task 13: De-register legacy activities (cleanup)

**Files:**
- Modify: `app/src/main/AndroidManifest.xml`

Now that every entry point routes to the new Compose activities (verified in Task 12), remove the manifest registrations for the retired legacy destinations. This is the last task so the app is never left in a state where a live route points at a de-registered activity.

**Interfaces:** none produced/consumed; verified by build gate + full home-grid smoke.

- [ ] **Step 1: Remove legacy `<activity>` entries**

From `AndroidManifest.xml`, delete the registrations for the retired targets under `.Views.SecondYear.English_4.Important_quiz.*`:
- `...Important_quiz.word` and `...Important_quiz.unit_package.unit8`…`unit14` (verify whether `unit8` is registered — the earlier grep found only `unit9`–`unit14` + `word`; delete whatever is present, add none)
- `...Important_quiz.molakas`
- `...Vocabulary.Vocabulary`, `...Abbrevationss.Abbreviations`, `...Compound_Nouns.Compound_Nouns`, `...Extinsions.Extinsions`
- `...True_false.True_false` and `...True_false.QuizActivity_True_false`

Leave the Java/fragment source files on disk (deleting source is out of scope for this phase); only the manifest registrations are removed. Do not remove any activity still referenced by a live route.

- [ ] **Step 2: Run the build gate**

Run: `JAVA_HOME=/Users/abdalqaderalnajjar/Library/Java/JavaVirtualMachines/azul-17.0.8.1/Contents/Home ./gradlew assembleDebug testDebugUnitTest`
Expected: `BUILD SUCCESSFUL`.

- [ ] **Step 3: Manual smoke (record result in the ledger)**

Launch the app; from the home grid tap all 7 cards (word → index → a unit; Vocabulary/Abbreviations/Compound_Nouns/Extinsions accordions; True_false list; molakas tabs). From the BoomMenu, tap index 1 → the new T/F quiz runs, sound plays when `switch1` is on, high score persists on exit. Confirm no `ActivityNotFoundException`.

- [ ] **Step 4: Commit**

```bash
git add app/src/main/AndroidManifest.xml
git commit -m "chore: de-register retired legacy Phase 5 activities"
```

---

## Self-Review

**1. Spec coverage** — every spec section maps to a task:
- Word lists (index + unit8–14) → Tasks 2, 3. Molakhs (munit8–14, no Unit 10) → Tasks 6, 7. 4 accordions → Tasks 4, 5. T/F list → Tasks 8, 9. T/F quiz → Tasks 8, 10, 11. Models + `ReferenceTopic` → Task 1. Parameterization (`EXTRA_UNIT`, `EXTRA_TOPIC` w/ VOCABULARY default) → Tasks 3, 5. Entry-point repointing (AdapterHome 7 cards + BankItemsQuiz index 1) → Task 12. Manifest registration → each screen task; de-registration → Task 13. Testing (content integrity, T/F logic, routing) → Tasks 1, 2, 4, 6, 8, 10. Error handling (invalid unit → empty, missing topic → VOCABULARY, guarded high-score) → Tasks 2/3, 4/5, 10/11. Shared quiz engine untouched → Global Constraints + Task 11 uses a separate engine.

**2. Placeholder scan** — the only intentional "fill from source" markers are the verbatim dataset bodies in Tasks 2, 4, 6, 8. These are the legitimate exception the spec's Count-reconciliation note calls out: transcription from a named source file, count-locked by a pinned test. Every code and test block elsewhere is complete and runnable. No "TBD"/"handle edge cases"/"similar to Task N".

**3. Type consistency** — names used across tasks agree: `WordListContent.unitWordsFor`/`headerFor`/`indexHeaders`; `ReferenceContent.itemsFor`/`referenceTopicFromExtra`; `MolakhsContent.summaries`; `TrueFalseContent.listItems`/`quizPool`; `TrueFalseQuizEngine(questions, order)` with `current()`/`answer()`/`isFinished()`/`hasNext()`/`total`/`score`; `TF_PREFS`/`TF_KEY`/`shouldPersistHighScore`; activity extras `EXTRA_UNIT = "extra_unit"`, `EXTRA_TOPIC = "extra_topic"` (Java passes the same literal strings). `MolakhsSummary(unitLabel, cards)`, `MolakhsCard(title, english, arabic)` match model definitions in Task 1.
