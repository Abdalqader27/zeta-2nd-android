# Phase 4 — Home Tab & Drawer Destinations Compose Migration Implementation Plan

> **For agentic workers:** REQUIRED SUB-SKILL: Use superpowers:subagent-driven-development (recommended) or superpowers:executing-plans to implement this plan task-by-task. Steps use checkbox (`- [ ]`) syntax for tracking.

**Goal:** Migrate the Home tab (`Home_fragment`) and the six drawer destinations (About, Settings, old_version/Eng3, statistics, licenses, study_member) from legacy Java Fragments/Activities + XML to Kotlin + Jetpack Compose, preserving behavior, copy, SharedPreferences data, and navigation byte-for-byte.

**Architecture:** One Compose `AppCompatActivity` per drawer destination (matching the existing `ShellActivity`/`ComposeQuizActivity`/`PaperQuizActivity` pattern) — each does `setContent { ZetaTheme { <Screen>() } }` and reads/writes SharedPreferences in `onCreate`. The Home tab becomes a plain `@Composable` (`HomeScreen`) rendered directly as page 2 of the existing `ShellScreen` pager (no Activity). No Navigation-Compose this phase; destinations are launched with plain `Intent`s from `ShellActions.handleDrawerAction`, preserving `overridePendingTransition`. Pure business logic (score formulas, prefs mapping, member list, high-score formatting) lives in small non-Compose Kotlin files so it can be unit-tested on the JVM without Robolectric.

**Tech Stack:** Kotlin 2.0.21, Jetpack Compose (compose-bom 2024.09.00), Material3, AppCompatActivity, JUnit 4 (`junit:junit:4.13.2`), Gradle (AGP 8.9.1).

## Global Constraints

- Package root: `com.Elkood.ling_en4`. New screens live under `com.Elkood.ling_en4.ui.screens.<destination>`.
- Model floors (do not lower): compileSdk 36, minSdk 21, versionCode 11, versionName 1.4.7, compose-bom 2024.09.00, Kotlin 2.0.21, AGP 8.9.1.
- Theme: wrap every screen in `ZetaTheme { ... }` (from `com.Elkood.ling_en4.ui.theme.Theme.kt`). Fonts come from `com.Elkood.ling_en4.ui.theme.Type.kt`: `MuliFontFamily`, `TajawalFontFamily`, `ZetaTypography`.
- **Colors:** use `androidx.compose.ui.res.colorResource(id = R.color.<name>)` inside Composables to pull the exact legacy `colors.xml` values. Do NOT hardcode hex or substitute `ZetaColors.*` where the legacy layout referenced a `@color/` resource — the legacy `colorPrimary` (`#00BCD4`) differs from `ZetaColors.Primary`. Reference values: `colorPrimary #00BCD4`, `colorPrimaryDark #1198A4`, `backber #57727F`, `blueberry #0FB2C0`, `grey #bfbfbf`, `grey_Light #f7f7f7`, `grey2 #e6e6e6`, `green #008000`, `white_20 #33FFFFFF`, `white #fff`, `black #000000`, `color4 #EC8F52`.
- **RTL/LTR:** Home tab renders LTR (`CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr)`); `licenses` body and `study_member` rows render LTR; everything else inherits the app default (RTL). The `ShellScreen` pager already wraps each page in `Rtl` — `HomeScreen` must re-provide `Ltr` internally.
- **Copy is verbatim.** Every Arabic/English string, including trailing spaces and misspellings (`الصوت ` with trailing space, `لقد غيرت اسمك بلفعل `, `For ITE in the Secound year`, `opyright`, `High Score :` with no trailing space), is reproduced EXACTLY as it appears in the legacy source. When a step gives a string literal, copy it character-for-character.
- **SharedPreferences continuity:** store names and keys are preserved so existing user data survives. Settings store = `saveData`; scores store = `SaveScore`; Eng3 store = `sharedprefs` (key `keyhighscore`). All `MODE_PRIVATE`.
- **Build & test command (run from repo root, in background, then read the log):**
  ```bash
  JAVA_HOME=/Users/abdalqaderalnajjar/Library/Java/JavaVirtualMachines/azul-17.0.8.1/Contents/Home ./gradlew assembleDebug testDebugUnitTest
  ```
  Expected end state: `BUILD SUCCESSFUL`.
- **KEEP for Phase 5 (do NOT touch):** `AdapterHome.java`, `CardHome.java`, `En4HomeCardConstants.java`. These are the multi-card Home grid infrastructure migrated in a later phase.
- Facebook deep-link fallback and SweetAlertDialog are replaced by Compose equivalents (an `Intent` helper and a Material3 `AlertDialog` respectively) — see the tasks.

---

## File Structure

New Kotlin files (all under `app/src/main/java/com/Elkood/ling_en4/ui/screens/`):

```
home/HomeScreen.kt                       Home tab Composable (page 2 of ShellScreen). No Activity.
settings/SettingsActivity.kt             AppCompatActivity host.
settings/SettingsScreen.kt               Compose UI.
settings/SettingsLogic.kt                Pure prefs keys + duration-option mapping (unit-tested).
about/AboutActivity.kt                   AppCompatActivity host.
about/AboutScreen.kt                     Compose UI.
about/FacebookIntent.kt                  Facebook deep-link Intent helper.
statistics/StatisticsActivity.kt         AppCompatActivity host.
statistics/StatisticsScreen.kt           Compose UI.
statistics/StatisticsUiState.kt          Pure data + statisticsFrom() factory (unit-tested).
eng3/Eng3Activity.kt                     AppCompatActivity host (score readback via launcher).
eng3/Eng3Screen.kt                       Compose UI.
eng3/Eng3Logic.kt                        Pure prefs keys + formatHighScore() (unit-tested).
member/StudyMemberActivity.kt            AppCompatActivity host.
member/StudyMemberScreen.kt              Compose UI.
member/StudyMembers.kt                   Pure member-name list (unit-tested).
licenses/LicensesActivity.kt             AppCompatActivity host.
licenses/LicensesScreen.kt               Compose UI + verbatim licenses text constant.
```

New test files (under `app/src/test/java/com/Elkood/ling_en4/`):

```
ui/screens/eng3/Eng3LogicTest.kt
ui/screens/settings/SettingsLogicTest.kt
ui/screens/statistics/StatisticsUiStateTest.kt
ui/screens/member/StudyMembersTest.kt
```

Modified files:

```
ui/screens/shell/ShellScreen.kt          Page 2: AndroidFragment<Home_fragment> -> HomeScreen(...).
ui/screens/shell/ShellActions.kt         handleDrawerAction repoints SCORES/SETTINGS/ABOUT/ENG3 to new activities.
AndroidManifest.xml                       Add 6 new activities; remove 6 legacy Header_Elements activities.
```

Deleted after final verification (Task 10):

```
Java: Home_fragment, About, Settings, old_version, statistics, study_member, licenses, Adapter_member
XML layouts for each of the above (8 layouts).
```

---

## Task 1: HomeScreen + wire into ShellScreen page 2

**Files:**
- Create: `app/src/main/java/com/Elkood/ling_en4/ui/screens/home/HomeScreen.kt`
- Modify: `app/src/main/java/com/Elkood/ling_en4/ui/screens/shell/ShellScreen.kt` (line 118 `else` branch; remove `Home_fragment` import line 43)

**Interfaces:**
- Produces: `@Composable fun HomeScreen(onStartExam: () -> Unit, modifier: Modifier = Modifier)`. `onStartExam` is invoked when the user taps the start-exam button; the caller launches the full quiz.
- Consumes (from `ShellScreen`): the page-2 branch calls `HomeScreen(onStartExam = { ... launch ComposeQuizActivity with FULL_QUIZ ... })`.

**Legacy reference (`Home_fragment` + `fragment_home.xml`):** Root LTR, background `@color/white_20`. Top: image `R.drawable.ic_undraw_mathematics_4otb` filling a weighted region (padding ~30dp). A white card (`round15_top` background, corners top 25dp) containing: title `حول الاختبار الشامل` (color `colorPrimary`), a thin `grey_Light` divider (1dp, horizontal margin 8dp), a centered bold description, and a start button. Button text `ابدأ الاختبار`, fill `grey_Light`, text color `backber`, 20dp corners, a 3dp `blueberry` bottom shadow (legacy `FButton`), Tajawal-Bold font. Tapping the button launches `ComposeQuizActivity` with `EXTRA_TOPIC = QuizTopic.FULL_QUIZ.name()` and flag `FLAG_ACTIVITY_SINGLE_TOP`.

- [ ] **Step 1: Create `HomeScreen.kt`**

```kotlin
package com.Elkood.ling_en4.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.weight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.Elkood.ling_en4.R
import com.Elkood.ling_en4.ui.theme.TajawalFontFamily

@Composable
fun HomeScreen(onStartExam: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(colorResource(R.color.white_20)),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(R.drawable.ic_undraw_mathematics_4otb),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .weight(4f)
                .padding(30.dp),
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .clip(RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp))
                .background(colorResource(R.color.white)),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "حول الاختبار الشامل",
                color = colorResource(R.color.colorPrimary),
                fontFamily = TajawalFontFamily,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 5.dp, start = 5.dp, end = 5.dp),
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
                    .height(1.dp)
                    .background(colorResource(R.color.grey_Light)),
            )
            Text(
                text = "يشمل جميع الأسئلة الموجودة في الدورات والأسئلة الذهبية إضافة إلى المقرر اللغة 4",
                textAlign = TextAlign.Center,
                fontFamily = TajawalFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp,
                lineHeight = 22.sp,
                modifier = Modifier.padding(5.dp),
            )
            StartExamButton(
                text = "ابدأ الاختبار",
                onClick = onStartExam,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, top = 10.dp, bottom = 10.dp),
            )
        }
    }
}

@Composable
private fun StartExamButton(text: String, onClick: () -> Unit, modifier: Modifier = Modifier) {
    val shape = RoundedCornerShape(20.dp)
    // Replicates legacy FButton: grey_Light face, backber text, 3dp blueberry bottom shadow.
    Box(modifier = modifier.background(color = colorResource(R.color.blueberry), shape = shape)) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 3.dp)
                .clip(shape)
                .background(colorResource(R.color.grey_Light))
                .clickable(onClick = onClick)
                .padding(vertical = 12.dp),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = text,
                color = colorResource(R.color.backber),
                fontFamily = TajawalFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
            )
        }
    }
}
```

- [ ] **Step 2: Rewire `ShellScreen.kt` page 2**

Remove the now-unused import at line 43:
```kotlin
import com.Elkood.ling_en4.Views.SecondYear.English_4.Home.Home_fragment
```
Replace the page-2 `else` branch (currently line 118):
```kotlin
                                    else -> AndroidFragment<Home_fragment>(Modifier.fillMaxSize())
```
with:
```kotlin
                                    else -> com.Elkood.ling_en4.ui.screens.home.HomeScreen(
                                        onStartExam = {
                                            val intent = android.content.Intent(
                                                activity,
                                                com.Elkood.ling_en4.ui.screens.quiz.ComposeQuizActivity::class.java,
                                            ).setFlags(android.content.Intent.FLAG_ACTIVITY_SINGLE_TOP)
                                            intent.putExtra(
                                                com.Elkood.ling_en4.ui.screens.quiz.ComposeQuizActivity.EXTRA_TOPIC,
                                                com.Elkood.ling_en4.ui.screens.quiz.QuizTopic.FULL_QUIZ.name,
                                            )
                                            activity.startActivity(intent)
                                        },
                                        modifier = Modifier.fillMaxSize(),
                                    )
```
> Note: `EXTRA_TOPIC` and `QuizTopic` already exist on `ComposeQuizActivity` from an earlier phase (the same constant the legacy `Home_fragment` used). If the import for `AndroidFragment` becomes unused after this change AND after confirming pages 0/1 no longer use it, leave the import in place only if page 1 (`AndroidFragment<BankItemsQuiz>`) still needs it — page 1 DOES still use it, so keep the `AndroidFragment` import.

- [ ] **Step 3: Build**

Run: `JAVA_HOME=/Users/abdalqaderalnajjar/Library/Java/JavaVirtualMachines/azul-17.0.8.1/Contents/Home ./gradlew assembleDebug testDebugUnitTest`
Expected: `BUILD SUCCESSFUL`. (No unit test for pure-UI Home; correctness is compile + visual parity.)

- [ ] **Step 4: Commit**

```bash
git add app/src/main/java/com/Elkood/ling_en4/ui/screens/home/HomeScreen.kt app/src/main/java/com/Elkood/ling_en4/ui/screens/shell/ShellScreen.kt
git commit -m "feat: migrate Home tab to Compose HomeScreen"
```

---

## Task 2: StudyMembers data + StudyMemberScreen + StudyMemberActivity

**Files:**
- Create: `app/src/main/java/com/Elkood/ling_en4/ui/screens/member/StudyMembers.kt`
- Create: `app/src/main/java/com/Elkood/ling_en4/ui/screens/member/StudyMemberScreen.kt`
- Create: `app/src/main/java/com/Elkood/ling_en4/ui/screens/member/StudyMemberActivity.kt`
- Test: `app/src/test/java/com/Elkood/ling_en4/ui/screens/member/StudyMembersTest.kt`

**Interfaces:**
- Produces: `val STUDY_MEMBERS: List<String>` (14 names, trailing spaces preserved); `@Composable fun StudyMemberScreen()`; `class StudyMemberActivity : AppCompatActivity`.

**Legacy reference (`study_member` + `activity_study_member.xml` + `row_member.xml`):** Header image `R.drawable.student` (raster JPG), height 176dp, `ContentScale.Crop`, 10dp margin. Subtitle `شكرا خاص للزملاء على المساعدة  ` (two trailing spaces), 10dp margin. A 2dp black separator line (wrapped with 10dp top/side margins, 15dp horizontal padding). Then a list of 14 member rows. Each row is LTR: a CardView (8dp corners, 10dp left/right/top margins) with a horizontal inner layout using the `stroke` drawable background — name TextView (weight 1, gravity center, textSize 20sp) and a plus icon `R.drawable.ic_tab_bar_plus_gray` (width 100dp, height 60dp, gravity right/end, 10dp padding). Long-press on a row shows Toast `Ite 20`; tap does nothing.

- [ ] **Step 1: Write the failing test — `StudyMembersTest.kt`**

```kotlin
package com.Elkood.ling_en4.ui.screens.member

import org.junit.Assert.assertEquals
import org.junit.Test

class StudyMembersTest {
    @Test
    fun hasFourteenMembers() {
        assertEquals(14, STUDY_MEMBERS.size)
    }

    @Test
    fun firstAndLastVerbatim() {
        assertEquals("لانا قدو", STUDY_MEMBERS[0])
        assertEquals("ياسمين اسماعيل  ", STUDY_MEMBERS[13])
    }

    @Test
    fun trailingSpacesPreserved() {
        // index 1 has a fatha on ريَان and two trailing spaces
        assertEquals("ريَان حوري  ", STUDY_MEMBERS[1])
        // index 8 has a double internal space and one trailing space
        assertEquals("فاطمة  الزهراء حمدك ", STUDY_MEMBERS[8])
    }
}
```

- [ ] **Step 2: Run test to verify it fails**

Run: `JAVA_HOME=.../azul-17.0.8.1/Contents/Home ./gradlew testDebugUnitTest --tests "com.Elkood.ling_en4.ui.screens.member.StudyMembersTest"`
Expected: FAIL — `STUDY_MEMBERS` unresolved.

- [ ] **Step 3: Create `StudyMembers.kt`** (preserve every trailing/internal space EXACTLY)

```kotlin
package com.Elkood.ling_en4.ui.screens.member

/**
 * Study-group member names, copied verbatim from the legacy activity_study_member.xml.
 * Trailing and internal spacing is intentional and asserted by StudyMembersTest —
 * do not "clean up" whitespace.
 */
val STUDY_MEMBERS: List<String> = listOf(
    "لانا قدو",
    "ريَان حوري  ",
    "نور سفلو  ",
    "عبدالعزيز شحرور  ",
    "راما لبان ",
    "ميس مهروسة ",
    "دلال فتوح  ",
    "دانيا رجب  ",
    "فاطمة  الزهراء حمدك ",
    "شيماء نجار  ",
    "ليلى وراق ",
    "دعاء مايو ",
    "هديل الابراهيم ",
    "ياسمين اسماعيل  ",
)
```

- [ ] **Step 4: Run test to verify it passes**

Run same `--tests` command. Expected: PASS. If a trailing-space assertion fails, the editor stripped whitespace — re-add it.

- [ ] **Step 5: Create `StudyMemberScreen.kt`**

```kotlin
package com.Elkood.ling_en4.ui.screens.member

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.Elkood.ling_en4.R

@Composable
fun StudyMemberScreen() {
    val context = LocalContext.current
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        item {
            Image(
                painter = painterResource(R.drawable.student),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(176.dp)
                    .padding(10.dp),
            )
            Text(
                text = "شكرا خاص للزملاء على المساعدة  ",
                modifier = Modifier.padding(10.dp),
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp, top = 10.dp)
                    .padding(horizontal = 15.dp)
                    .height(2.dp)
                    .background(Color.Black),
            )
        }
        items(STUDY_MEMBERS) { name ->
            CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
                MemberRow(
                    name = name,
                    onLongPress = { Toast.makeText(context, "Ite 20", Toast.LENGTH_SHORT).show() },
                )
            }
        }
    }
}

@Composable
private fun MemberRow(name: String, onLongPress: () -> Unit) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp, top = 10.dp),
    ) {
        androidx.compose.foundation.layout.Row(
            modifier = Modifier
                .fillMaxWidth()
                .border(0.5.dp, Color(0xFF2B5A83), RoundedCornerShape(8.dp)),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = name,
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                modifier = Modifier
                    .weight(1f)
                    .padding(vertical = 10.dp),
            )
            Image(
                painter = painterResource(R.drawable.ic_tab_bar_plus_gray),
                contentDescription = null,
                modifier = Modifier
                    .size(width = 100.dp, height = 60.dp)
                    .padding(10.dp),
            )
        }
    }
}
```
> The long-press gesture (`combinedClickable`) is optional visual parity — the legacy tap does nothing and long-press shows a toast. If wiring `combinedClickable` adds risk, it is acceptable to omit the toast; the reviewer should treat the toast as Minor. To include it: add `androidx.compose.foundation.ExperimentalFoundationApi` opt-in and `Modifier.combinedClickable(onClick = {}, onLongClick = onLongPress)` on the Row. The `border` color `#2B5A83` matches the legacy `stroke.xml` stroke.

- [ ] **Step 6: Create `StudyMemberActivity.kt`**

```kotlin
package com.Elkood.ling_en4.ui.screens.member

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.Elkood.ling_en4.ui.theme.ZetaTheme

class StudyMemberActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ZetaTheme {
                StudyMemberScreen()
            }
        }
    }
}
```

- [ ] **Step 7: Build (full)**

Run: `JAVA_HOME=.../azul-17.0.8.1/Contents/Home ./gradlew assembleDebug testDebugUnitTest`
Expected: `BUILD SUCCESSFUL`.
> The manifest entry for `StudyMemberActivity` is added in Task 9. It compiles without the manifest entry; it just can't be launched yet.

- [ ] **Step 8: Commit**

```bash
git add app/src/main/java/com/Elkood/ling_en4/ui/screens/member/ app/src/test/java/com/Elkood/ling_en4/ui/screens/member/
git commit -m "feat: migrate study_member to Compose"
```

---

## Task 3: LicensesScreen + LicensesActivity

**Files:**
- Create: `app/src/main/java/com/Elkood/ling_en4/ui/screens/licenses/LicensesScreen.kt`
- Create: `app/src/main/java/com/Elkood/ling_en4/ui/screens/licenses/LicensesActivity.kt`

**Interfaces:**
- Produces: `@Composable fun LicensesScreen()`; `class LicensesActivity : AppCompatActivity`.

**Legacy reference (`licenses` + `activity_licenses.xml`):** Activity has no logic. Root is a vertical scroll (LTR). Title `licenses`, 30sp, 10dp margin. Body: 20sp, `lineSpacingMultiplier 1.5`, 10dp padding, background `grey_Light`, `textDirection ltr`, 10dp margin — a long verbatim block of third-party attributions.

- [ ] **Step 1: Copy the licenses body text verbatim from the XML**

Open `app/src/main/res/layout/activity_licenses.xml`, find the body `TextView`'s `android:text` value, and copy it **character-for-character** (including the leading blank line, the double-space in `(the  Licence)`, the `opyright` misspelling, `Bañuls`, and any duplicated entries). Do NOT retype it from memory or "correct" it. Paste it as the initializer of `LICENSES_TEXT` below. Because the text starts with a blank line, use a raw triple-quoted string and do NOT call `.trimIndent()` / `.trim()`.

- [ ] **Step 2: Create `LicensesScreen.kt`**

```kotlin
package com.Elkood.ling_en4.ui.screens.licenses

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.Elkood.ling_en4.R

// Paste verbatim from activity_licenses.xml body TextView. Leading blank line intentional.
private val LICENSES_TEXT: String = """
<PASTE THE EXACT android:text VALUE HERE — see Step 1>
"""

@Composable
fun LicensesScreen() {
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
        ) {
            Text(
                text = "licenses",
                fontSize = 30.sp,
                modifier = Modifier.padding(10.dp),
            )
            Text(
                text = LICENSES_TEXT,
                fontSize = 20.sp,
                lineHeight = 30.sp, // ~1.5x of 20sp
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .background(colorResource(R.color.grey_Light))
                    .padding(10.dp),
            )
        }
    }
}
```
> `<PASTE ... HERE>` is a real editing step, not a placeholder to skip — Step 1 defines exactly what goes there. The reviewer must diff `LICENSES_TEXT` against the XML `android:text` and confirm they match byte-for-byte.

- [ ] **Step 3: Create `LicensesActivity.kt`**

```kotlin
package com.Elkood.ling_en4.ui.screens.licenses

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.Elkood.ling_en4.ui.theme.ZetaTheme

class LicensesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ZetaTheme {
                LicensesScreen()
            }
        }
    }
}
```

- [ ] **Step 4: Build**

Run: `JAVA_HOME=.../azul-17.0.8.1/Contents/Home ./gradlew assembleDebug`
Expected: `BUILD SUCCESSFUL`.

- [ ] **Step 5: Commit**

```bash
git add app/src/main/java/com/Elkood/ling_en4/ui/screens/licenses/
git commit -m "feat: migrate licenses to Compose"
```

---

## Task 4: Eng3 (old_version) — logic + Screen + Activity with score readback

**Files:**
- Create: `app/src/main/java/com/Elkood/ling_en4/ui/screens/eng3/Eng3Logic.kt`
- Create: `app/src/main/java/com/Elkood/ling_en4/ui/screens/eng3/Eng3Screen.kt`
- Create: `app/src/main/java/com/Elkood/ling_en4/ui/screens/eng3/Eng3Activity.kt`
- Test: `app/src/test/java/com/Elkood/ling_en4/ui/screens/eng3/Eng3LogicTest.kt`

**Interfaces:**
- Produces: `const val ENG3_PREFS = "sharedprefs"`, `const val ENG3_KEY_HIGHSCORE = "keyhighscore"`, `fun formatHighScore(score: Int): String`; `@Composable fun Eng3Screen(highScore: Int, onStart: () -> Unit)`; `class Eng3Activity : AppCompatActivity`.
- Consumes: `ComposeQuizActivity.EXTRA_TOPIC`, `ComposeQuizActivity.EXTRA_SCORE`, `QuizTopic.ENG3` (existing from earlier phase).

**Legacy reference (`old_version` + `activity_old_version.xml`):** Constants `SHARED_PREF = "sharedprefs"`, `KEY_HIGHSCORE = "keyhighscore"`, `REQUST_CODE_QUIZ = 1` (sic). Hero image `R.drawable.brand` tinted `colorPrimary`, height ~300dp. Title `Eng 3 ` (trailing space), 30sp. Subtitle `اختبارات أسئلة دورات لغة 3  ` (two trailing spaces). Button `lets Start`. High-score label `High Score :` + value (NO space after colon), read from `keyhighscore` (default 0). Start: `Intent` to `ComposeQuizActivity` with `EXTRA_TOPIC = QuizTopic.ENG3.name()`, launched via `startActivityForResult(intent, REQUST_CODE_QUIZ)`. On `RESULT_OK`: `score = data.getIntExtra(EXTRA_SCORE, 0)`; if `score > highScore`, persist it to `keyhighscore` and update the label. Back press shows an AlertDialog: title `Quiz`, message `Do You Want To Close Your App ?`, cancelable true, Negative `No` (dismiss), Positive `Yes` (finish()).

- [ ] **Step 1: Write the failing test — `Eng3LogicTest.kt`**

```kotlin
package com.Elkood.ling_en4.ui.screens.eng3

import org.junit.Assert.assertEquals
import org.junit.Test

class Eng3LogicTest {
    @Test
    fun formatsHighScoreWithNoSpaceAfterColon() {
        assertEquals("High Score :0", formatHighScore(0))
        assertEquals("High Score :42", formatHighScore(42))
    }

    @Test
    fun prefsConstantsMatchLegacy() {
        assertEquals("sharedprefs", ENG3_PREFS)
        assertEquals("keyhighscore", ENG3_KEY_HIGHSCORE)
    }
}
```

- [ ] **Step 2: Run test to verify it fails**

Run: `JAVA_HOME=.../azul-17.0.8.1/Contents/Home ./gradlew testDebugUnitTest --tests "com.Elkood.ling_en4.ui.screens.eng3.Eng3LogicTest"`
Expected: FAIL — unresolved references.

- [ ] **Step 3: Create `Eng3Logic.kt`**

```kotlin
package com.Elkood.ling_en4.ui.screens.eng3

const val ENG3_PREFS = "sharedprefs"
const val ENG3_KEY_HIGHSCORE = "keyhighscore"

/** Legacy label: "High Score :" concatenated with the value, with no space after the colon. */
fun formatHighScore(score: Int): String = "High Score :$score"
```

- [ ] **Step 4: Run test to verify it passes** — same `--tests` command. Expected: PASS.

- [ ] **Step 5: Create `Eng3Screen.kt`**

```kotlin
package com.Elkood.ling_en4.ui.screens.eng3

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.Elkood.ling_en4.R
import com.Elkood.ling_en4.ui.components.PrimaryButton

@Composable
fun Eng3Screen(highScore: Int, onStart: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Image(
            painter = painterResource(R.drawable.brand),
            contentDescription = null,
            colorFilter = ColorFilter.tint(colorResource(R.color.colorPrimary)),
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
        )
        Text(text = "Eng 3 ", fontSize = 30.sp)
        Text(text = "اختبارات أسئلة دورات لغة 3  ")
        Text(text = formatHighScore(highScore), modifier = Modifier.padding(vertical = 8.dp))
        PrimaryButton(text = "lets Start", onClick = onStart)
    }
}
```
> `PrimaryButton` is the existing `com.Elkood.ling_en4.ui.components.PrimaryButton(text, onClick, modifier, enabled)`. The legacy button label is `lets Start` (verbatim, lowercase l).

- [ ] **Step 6: Create `Eng3Activity.kt`** (score readback via `rememberLauncherForActivityResult`)

```kotlin
package com.Elkood.ling_en4.ui.screens.eng3

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.Elkood.ling_en4.ui.screens.quiz.ComposeQuizActivity
import com.Elkood.ling_en4.ui.screens.quiz.QuizTopic
import com.Elkood.ling_en4.ui.theme.ZetaTheme

class Eng3Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val prefs = getSharedPreferences(ENG3_PREFS, Context.MODE_PRIVATE)
        setContent {
            ZetaTheme {
                var highScore by remember { mutableIntStateOf(prefs.getInt(ENG3_KEY_HIGHSCORE, 0)) }
                val launcher = rememberLauncherForActivityResult(
                    ActivityResultContracts.StartActivityForResult()
                ) { result ->
                    if (result.resultCode == Activity.RESULT_OK) {
                        val score = result.data?.getIntExtra(ComposeQuizActivity.EXTRA_SCORE, 0) ?: 0
                        if (score > highScore) {
                            highScore = score
                            prefs.edit().putInt(ENG3_KEY_HIGHSCORE, score).apply()
                        }
                    }
                }
                Eng3Screen(
                    highScore = highScore,
                    onStart = {
                        val intent = Intent(this, ComposeQuizActivity::class.java)
                        intent.putExtra(ComposeQuizActivity.EXTRA_TOPIC, QuizTopic.ENG3.name)
                        launcher.launch(intent)
                    },
                )
            }
        }
    }

    override fun onBackPressed() {
        AlertDialog.Builder(this)
            .setTitle("Quiz")
            .setMessage("Do You Want To Close Your App ?")
            .setCancelable(true)
            .setNegativeButton("No") { dialog, _ -> dialog.cancel() }
            .setPositiveButton("Yes") { _, _ -> finish() }
            .show()
    }
}
```
> Verify `ComposeQuizActivity.EXTRA_SCORE` exists (it is the constant the legacy `old_version` read via `data.getIntExtra(EXTRA_SCORE, 0)`). If the constant has a different name in the Kotlin `ComposeQuizActivity`, use that name and note it in the report. `mutableIntStateOf` requires compose-runtime ≥1.6 (satisfied by bom 2024.09.00).

- [ ] **Step 7: Build (full)**

Run: `JAVA_HOME=.../azul-17.0.8.1/Contents/Home ./gradlew assembleDebug testDebugUnitTest`
Expected: `BUILD SUCCESSFUL`.

- [ ] **Step 8: Commit**

```bash
git add app/src/main/java/com/Elkood/ling_en4/ui/screens/eng3/ app/src/test/java/com/Elkood/ling_en4/ui/screens/eng3/
git commit -m "feat: migrate old_version (Eng3) to Compose with score readback"
```

---

## Task 5: Statistics — logic + Screen + Activity

**Files:**
- Create: `app/src/main/java/com/Elkood/ling_en4/ui/screens/statistics/StatisticsUiState.kt`
- Create: `app/src/main/java/com/Elkood/ling_en4/ui/screens/statistics/StatisticsScreen.kt`
- Create: `app/src/main/java/com/Elkood/ling_en4/ui/screens/statistics/StatisticsActivity.kt`
- Test: `app/src/test/java/com/Elkood/ling_en4/ui/screens/statistics/StatisticsUiStateTest.kt`

**Interfaces:**
- Produces: `const val SCORE_PREFS = "SaveScore"`; `data class StatisticsUiState(...)`; `fun statisticsFrom(voc, tf, full, numberPlay, abber, ext, comp): StatisticsUiState`; `@Composable fun StatisticsScreen(state: StatisticsUiState)`; `class StatisticsActivity : AppCompatActivity`.

**Legacy reference (`statistics` + `activity_statistics.xml`):** Store `SaveScore` (read-only). Keys and formulas:
- `voc = getInt("VocHighScore", 0)`
- `tf = getInt("tfscore", 0)`
- `full = getInt("fullscore", 0)` → `fullTrue = full`, `fullFalse = Math.abs(421 - full)`, `mark = (100 * full) / 421f`
- `numberPlay = getInt("numberPlay", 0)`
- `abber = getInt("AbberHighScore", 0)`
- `ext = getInt("ExtHighScore", 0)`
- `comp = getInt("CompHighScore", 0)`

All values displayed via `String.valueOf(...)`; `mark` is displayed as the RAW float `toString()` (no rounding/formatting). Hero image `R.drawable.ic_diagram`; CollapsingToolbar `contentScrim = colorPrimaryDark`, `titleEnabled = false`.
Labels — Section الشامل (icon `ic_058_bunny`): `الإجابات الصحيحة` → `fullTrue` / 421; `الإجابات الخاطئة` → `fullFalse` / 421; ` مرات اللعب` → `numberPlay`; ` علاماتك ` → `mark` / 100.
Section الجزئي (icon `triviafinal`): `المردفات` → `voc` / 46; `صح أو خطأ ` → `tf` / 36; `الاختصارات` → `abber` / 23; `لواحق المواقع ` → `ext` / 20; ` الأسماء المركبة  ` → `comp` / 20.

- [ ] **Step 1: Write the failing test — `StatisticsUiStateTest.kt`**

```kotlin
package com.Elkood.ling_en4.ui.screens.statistics

import org.junit.Assert.assertEquals
import org.junit.Test

class StatisticsUiStateTest {
    @Test
    fun fullScoreDrivesTrueFalseAndMark() {
        val s = statisticsFrom(voc = 0, tf = 0, full = 421, numberPlay = 0, abber = 0, ext = 0, comp = 0)
        assertEquals(421, s.fullTrue)
        assertEquals(0, s.fullFalse)
        assertEquals(100f, s.mark, 0.0001f)
    }

    @Test
    fun zeroFullScoreGivesAllWrongAndZeroMark() {
        val s = statisticsFrom(voc = 0, tf = 0, full = 0, numberPlay = 0, abber = 0, ext = 0, comp = 0)
        assertEquals(0, s.fullTrue)
        assertEquals(421, s.fullFalse)
        assertEquals(0f, s.mark, 0.0001f)
    }

    @Test
    fun partialFullScoreUsesAbsAnd421Denominator() {
        val s = statisticsFrom(voc = 5, tf = 6, full = 210, numberPlay = 3, abber = 7, ext = 8, comp = 9)
        assertEquals(210, s.fullTrue)
        assertEquals(211, s.fullFalse) // abs(421 - 210)
        assertEquals((100 * 210) / 421f, s.mark, 0.0001f)
        // passthrough fields
        assertEquals(5, s.voc)
        assertEquals(6, s.tf)
        assertEquals(3, s.numberPlay)
        assertEquals(7, s.abber)
        assertEquals(8, s.ext)
        assertEquals(9, s.comp)
    }
}
```

- [ ] **Step 2: Run test to verify it fails** — Expected: FAIL (unresolved `statisticsFrom`).

- [ ] **Step 3: Create `StatisticsUiState.kt`**

```kotlin
package com.Elkood.ling_en4.ui.screens.statistics

import kotlin.math.abs

const val SCORE_PREFS = "SaveScore"

data class StatisticsUiState(
    val voc: Int,
    val tf: Int,
    val fullTrue: Int,
    val fullFalse: Int,
    val mark: Float,
    val numberPlay: Int,
    val abber: Int,
    val ext: Int,
    val comp: Int,
)

/** Mirrors the legacy statistics activity: fullFalse = |421 - full|, mark = (100 * full) / 421f. */
fun statisticsFrom(
    voc: Int,
    tf: Int,
    full: Int,
    numberPlay: Int,
    abber: Int,
    ext: Int,
    comp: Int,
): StatisticsUiState = StatisticsUiState(
    voc = voc,
    tf = tf,
    fullTrue = full,
    fullFalse = abs(421 - full),
    mark = (100 * full) / 421f,
    numberPlay = numberPlay,
    abber = abber,
    ext = ext,
    comp = comp,
)
```

- [ ] **Step 4: Run test to verify it passes** — Expected: PASS.

- [ ] **Step 5: Create `StatisticsScreen.kt`**

```kotlin
package com.Elkood.ling_en4.ui.screens.statistics

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.Elkood.ling_en4.R

@Composable
fun StatisticsScreen(state: StatisticsUiState) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
    ) {
        Image(
            painter = painterResource(R.drawable.ic_diagram),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(16.dp),
        )
        SectionHeader(icon = R.drawable.ic_058_bunny, title = "الشامل")
        StatRow(label = "الإجابات الصحيحة", value = "${state.fullTrue}", outOf = "421")
        StatRow(label = "الإجابات الخاطئة", value = "${state.fullFalse}", outOf = "421")
        StatRow(label = " مرات اللعب", value = "${state.numberPlay}", outOf = null)
        StatRow(label = " علاماتك ", value = state.mark.toString(), outOf = "100")

        SectionHeader(icon = R.drawable.triviafinal, title = "الجزئي")
        StatRow(label = "المردفات", value = "${state.voc}", outOf = "46")
        StatRow(label = "صح أو خطأ ", value = "${state.tf}", outOf = "36")
        StatRow(label = "الاختصارات", value = "${state.abber}", outOf = "23")
        StatRow(label = "لواحق المواقع ", value = "${state.ext}", outOf = "20")
        StatRow(label = " الأسماء المركبة  ", value = "${state.comp}", outOf = "20")
    }
}

@Composable
private fun SectionHeader(icon: Int, title: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(painter = painterResource(icon), contentDescription = null, modifier = Modifier.height(32.dp))
        Text(text = title, fontSize = 20.sp, modifier = Modifier.padding(start = 8.dp))
    }
}

@Composable
private fun StatRow(label: String, value: String, outOf: String?) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp),
    ) {
        Text(text = label, modifier = Modifier.weight(1f))
        Text(text = if (outOf != null) "$value / $outOf" else value)
    }
}
```
> `mark` is rendered via `state.mark.toString()` to match the legacy raw-float display (e.g. `49.881233`). Do NOT format it. Verify `R.drawable.ic_058_bunny` and `R.drawable.triviafinal` exist; if a name differs, use the exact resource referenced by `activity_statistics.xml`.

- [ ] **Step 6: Create `StatisticsActivity.kt`**

```kotlin
package com.Elkood.ling_en4.ui.screens.statistics

import android.content.Context
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.Elkood.ling_en4.ui.theme.ZetaTheme

class StatisticsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val prefs = getSharedPreferences(SCORE_PREFS, Context.MODE_PRIVATE)
        val state = statisticsFrom(
            voc = prefs.getInt("VocHighScore", 0),
            tf = prefs.getInt("tfscore", 0),
            full = prefs.getInt("fullscore", 0),
            numberPlay = prefs.getInt("numberPlay", 0),
            abber = prefs.getInt("AbberHighScore", 0),
            ext = prefs.getInt("ExtHighScore", 0),
            comp = prefs.getInt("CompHighScore", 0),
        )
        setContent {
            ZetaTheme {
                StatisticsScreen(state = state)
            }
        }
    }
}
```

- [ ] **Step 7: Build (full)** — Run the full build+test command. Expected: `BUILD SUCCESSFUL`.

- [ ] **Step 8: Commit**

```bash
git add app/src/main/java/com/Elkood/ling_en4/ui/screens/statistics/ app/src/test/java/com/Elkood/ling_en4/ui/screens/statistics/
git commit -m "feat: migrate statistics to Compose"
```

---

## Task 6: Settings — logic + Screen + Activity

**Files:**
- Create: `app/src/main/java/com/Elkood/ling_en4/ui/screens/settings/SettingsLogic.kt`
- Create: `app/src/main/java/com/Elkood/ling_en4/ui/screens/settings/SettingsScreen.kt`
- Create: `app/src/main/java/com/Elkood/ling_en4/ui/screens/settings/SettingsActivity.kt`
- Test: `app/src/test/java/com/Elkood/ling_en4/ui/screens/settings/SettingsLogicTest.kt`

**Interfaces:**
- Produces: prefs key constants (`SETTINGS_PREFS`, `KEY_SAVE_R`, `KEY_SWITCH1`, `KEY_NAME`, `KEY_CHANGE_NAME`), defaults, `val DURATION_OPTIONS: List<String>`, `fun durationLabelFor(saveR: Int): String`; `@Composable fun SettingsScreen(...)`; `class SettingsActivity : AppCompatActivity`.

**Legacy reference (`Settings` + `activity_settings.xml`):** Store `saveData` (MODE_PRIVATE). Keys/defaults: `saveR` int default 1 (radio index into ["30","40","50"]; restored via `getChildAt(getInt("saveR",1))`); `switch1` bool default true (sound-effects switch, label `تأثيرات الصوت ` with trailing space); `Name` String default `"No Name"`; `changeName` bool default true (one-time name-edit lock). Hero image `R.drawable.ic_settings_2`.

Behavior:
- Name field starts **disabled**; Save button (`حفظ`) starts **GONE**.
- Radio group has three options `30`/`40`/`50` mapped to indices 0/1/2; the stored `saveR` IS the selected index. Selecting an option persists the new index (legacy persists radio selection on change). Default checked = index 1 (`40`).
- Sound switch reflects/persists `switch1`.
- Tapping the **edit-name icon**: if `changeName == true`, show a warning dialog (title text `ملاحظة :  \n  يرجى الإنتباه لن تستطيع تعديل اسمك بعد القيام  بحفظه  `, single confirm button `تم`, NOT cancelable), then enable the name field and show the Save button. If `changeName == false`, show Toast `لقد غيرت اسمك بلفعل ` (LENGTH_LONG, sic misspelling) and do nothing else.
- Tapping **Save** (`حفظ`): if `changeName == true`, set `changeName = false`, persist `Name` = current text, hide Save button, disable the name field.

> The legacy uses a `SweetAlertDialog`. Per spec, replace it with a Material3 `AlertDialog` carrying the same text and a single confirm button `تم`, `properties = DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)` to match `setCancelable(false)`.

- [ ] **Step 1: Write the failing test — `SettingsLogicTest.kt`**

```kotlin
package com.Elkood.ling_en4.ui.screens.settings

import org.junit.Assert.assertEquals
import org.junit.Test

class SettingsLogicTest {
    @Test
    fun durationOptionsAreThirtyFortyFifty() {
        assertEquals(listOf("30", "40", "50"), DURATION_OPTIONS)
    }

    @Test
    fun durationLabelMapsIndexToOption() {
        assertEquals("30", durationLabelFor(0))
        assertEquals("40", durationLabelFor(1))
        assertEquals("50", durationLabelFor(2))
    }

    @Test
    fun outOfRangeIndexFallsBackToDefault() {
        assertEquals("40", durationLabelFor(99))
        assertEquals("40", durationLabelFor(-1))
        assertEquals(1, DEFAULT_SAVE_R)
    }

    @Test
    fun prefsConstantsAndDefaultsMatchLegacy() {
        assertEquals("saveData", SETTINGS_PREFS)
        assertEquals("saveR", KEY_SAVE_R)
        assertEquals("switch1", KEY_SWITCH1)
        assertEquals("Name", KEY_NAME)
        assertEquals("changeName", KEY_CHANGE_NAME)
        assertEquals("No Name", DEFAULT_NAME)
    }
}
```

- [ ] **Step 2: Run test to verify it fails** — Expected: FAIL (unresolved references).

- [ ] **Step 3: Create `SettingsLogic.kt`**

```kotlin
package com.Elkood.ling_en4.ui.screens.settings

const val SETTINGS_PREFS = "saveData"
const val KEY_SAVE_R = "saveR"
const val KEY_SWITCH1 = "switch1"
const val KEY_NAME = "Name"
const val KEY_CHANGE_NAME = "changeName"

const val DEFAULT_SAVE_R = 1
const val DEFAULT_SWITCH1 = true
const val DEFAULT_NAME = "No Name"
const val DEFAULT_CHANGE_NAME = true

/** Quiz-duration radio options; the stored saveR is the selected index into this list. */
val DURATION_OPTIONS: List<String> = listOf("30", "40", "50")

fun durationLabelFor(saveR: Int): String =
    DURATION_OPTIONS.getOrElse(saveR) { DURATION_OPTIONS[DEFAULT_SAVE_R] }
```

- [ ] **Step 4: Run test to verify it passes** — Expected: PASS.

- [ ] **Step 5: Create `SettingsScreen.kt`**

```kotlin
package com.Elkood.ling_en4.ui.screens.settings

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import com.Elkood.ling_en4.R

/**
 * Stateless settings UI. State + persistence live in SettingsActivity.
 *
 * @param showWarning whether the one-time name-edit warning dialog is visible.
 */
@Composable
fun SettingsScreen(
    name: String,
    onNameChange: (String) -> Unit,
    nameEnabled: Boolean,
    saveVisible: Boolean,
    selectedDuration: Int,
    onDurationSelected: (Int) -> Unit,
    soundOn: Boolean,
    onSoundChange: (Boolean) -> Unit,
    onEditNameClick: () -> Unit,
    onSaveClick: () -> Unit,
    showWarning: Boolean,
    onDismissWarning: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        Image(
            painter = painterResource(R.drawable.ic_settings_2),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp),
        )

        // Quiz duration radio group
        DURATION_OPTIONS.forEachIndexed { index, label ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .selectable(selected = selectedDuration == index, onClick = { onDurationSelected(index) })
                    .padding(vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                RadioButton(selected = selectedDuration == index, onClick = { onDurationSelected(index) })
                Text(text = label, modifier = Modifier.padding(start = 8.dp))
            }
        }

        // Sound switch
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(text = "تأثيرات الصوت ", modifier = Modifier.weight(1f))
            Switch(checked = soundOn, onCheckedChange = onSoundChange)
        }

        // Name field + edit icon
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            OutlinedTextField(
                value = name,
                onValueChange = onNameChange,
                enabled = nameEnabled,
                singleLine = true,
                modifier = Modifier.weight(1f),
            )
            IconButton(onClick = onEditNameClick) {
                Icon(
                    painter = painterResource(R.drawable.ic_edit_black_24dp),
                    contentDescription = "edit name",
                )
            }
        }

        if (saveVisible) {
            TextButton(onClick = onSaveClick, modifier = Modifier.padding(top = 8.dp)) {
                Text(text = "حفظ")
            }
        }
    }

    if (showWarning) {
        AlertDialog(
            onDismissRequest = onDismissWarning,
            properties = DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false),
            title = { Text("ملاحظة :  \n  يرجى الإنتباه لن تستطيع تعديل اسمك بعد القيام  بحفظه  ") },
            confirmButton = {
                TextButton(onClick = onDismissWarning) { Text("تم") }
            },
        )
    }
}
```
> Use whatever edit icon `activity_settings.xml` referenced for the name-edit control. If `R.drawable.ic_edit_black_24dp` does not exist, substitute the exact drawable the legacy layout used (check the layout) and note it in the report.

- [ ] **Step 6: Create `SettingsActivity.kt`**

```kotlin
package com.Elkood.ling_en4.ui.screens.settings

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import com.Elkood.ling_en4.ui.theme.ZetaTheme

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val prefs = getSharedPreferences(SETTINGS_PREFS, Context.MODE_PRIVATE)
        setContent {
            ZetaTheme {
                val context = LocalContext.current
                var name by remember { mutableStateOf(prefs.getString(KEY_NAME, DEFAULT_NAME) ?: DEFAULT_NAME) }
                var nameEnabled by remember { mutableStateOf(false) }
                var saveVisible by remember { mutableStateOf(false) }
                var showWarning by remember { mutableStateOf(false) }
                var selectedDuration by remember { mutableIntStateOf(prefs.getInt(KEY_SAVE_R, DEFAULT_SAVE_R)) }
                var soundOn by remember { mutableStateOf(prefs.getBoolean(KEY_SWITCH1, DEFAULT_SWITCH1)) }

                SettingsScreen(
                    name = name,
                    onNameChange = { name = it },
                    nameEnabled = nameEnabled,
                    saveVisible = saveVisible,
                    selectedDuration = selectedDuration,
                    onDurationSelected = {
                        selectedDuration = it
                        prefs.edit().putInt(KEY_SAVE_R, it).apply()
                    },
                    soundOn = soundOn,
                    onSoundChange = {
                        soundOn = it
                        prefs.edit().putBoolean(KEY_SWITCH1, it).apply()
                    },
                    onEditNameClick = {
                        if (prefs.getBoolean(KEY_CHANGE_NAME, DEFAULT_CHANGE_NAME)) {
                            showWarning = true
                            nameEnabled = true
                            saveVisible = true
                        } else {
                            Toast.makeText(context, "لقد غيرت اسمك بلفعل ", Toast.LENGTH_LONG).show()
                        }
                    },
                    onSaveClick = {
                        if (prefs.getBoolean(KEY_CHANGE_NAME, DEFAULT_CHANGE_NAME)) {
                            prefs.edit()
                                .putBoolean(KEY_CHANGE_NAME, false)
                                .putString(KEY_NAME, name)
                                .apply()
                            saveVisible = false
                            nameEnabled = false
                        }
                    },
                    showWarning = showWarning,
                    onDismissWarning = { showWarning = false },
                )
            }
        }
    }
}
```

- [ ] **Step 7: Build (full)** — Run full build+test command. Expected: `BUILD SUCCESSFUL`.

- [ ] **Step 8: Commit**

```bash
git add app/src/main/java/com/Elkood/ling_en4/ui/screens/settings/ app/src/test/java/com/Elkood/ling_en4/ui/screens/settings/
git commit -m "feat: migrate Settings to Compose"
```

---

## Task 7: About — FacebookIntent + AboutScreen + AboutActivity

**Files:**
- Create: `app/src/main/java/com/Elkood/ling_en4/ui/screens/about/FacebookIntent.kt`
- Create: `app/src/main/java/com/Elkood/ling_en4/ui/screens/about/AboutScreen.kt`
- Create: `app/src/main/java/com/Elkood/ling_en4/ui/screens/about/AboutActivity.kt`

**Interfaces:**
- Produces: `fun newFacebookIntent(pm: PackageManager, url: String): Intent`; `@Composable fun AboutScreen(onOpenUrl, onOpenFacebook, onOpenMember, onBack)`; `class AboutActivity : AppCompatActivity`.

**Legacy reference (`About` + `activity_about.xml`):** CollapsingToolbar (`titleEnabled = true`, `contentScrim = backber`, background `colorPrimary`, AppBar 350dp, hero `R.drawable.ic_untitled`, back arrow `R.drawable.ic_arrow_back_black_24dp` tinted white → `onBackPressed`). Card1: logo `R.drawable.brand` tinted `backber`, `تطبيق` (20sp), `Zeta` (20sp bold), ` For ITE in the Secound year` (verbatim, sic), body `تطبيق أُعدَّ لطلاب كلية الهندسة المعلوماتية للسنة الثانية بحيث يوفِّر تغطية شاملة لمقرَّر اللغة 4 إضافة إلى أسئلة الدورات `. Card2: title `فريق العمل الدراسي` (20sp bold), image `R.drawable.ic_people` tinted `colorPrimary`, tappable row `أنقر هنا للتعرف` → opens `StudyMemberActivity`. Rows (background `stroke`, black text, 14sp): Privacy row `سياسة الخصوصية` → opens privacy URL; Licenses row `الشهادات` → opens `LicensesActivity`. `تواصل معنا `. Facebook icon `R.drawable.ic_facebook_logo_in_circular_button_outlined_social_symbol` tinted grey → `newFacebookIntent`; Telegram icon `R.drawable.ic_telegram` tinted grey → telegram URL. Footer `جميع الحقوق محفوظة `.
URLs: privacy `https://www.freeprivacypolicy.com/privacy/view/25844dc6eb5275b8022df849a23f1ca6`; telegram `https://t.me/AbdalqaderNajjaR`; facebook `https://www.facebook.com/abdalqader.najjar.9/`.
Facebook package `com.facebook.katana`; if the FB app is installed & enabled, rewrite the URI to `fb://facewebmodal/f?href=<url>`; otherwise open the plain URL. Toast on no-FB-app path is legacy `No FaceBook App` (only shown if the resolved Intent fails to start — see Step 3).

- [ ] **Step 1: Create `FacebookIntent.kt`**

```kotlin
package com.Elkood.ling_en4.ui.screens.about

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri

/**
 * Mirrors the legacy About.newFacebookIntent: if the Facebook app is installed and enabled,
 * open the fb:// deep link; otherwise fall back to the plain web URL.
 */
fun newFacebookIntent(pm: PackageManager, url: String): Intent {
    var uri = Uri.parse(url)
    try {
        val applicationInfo = pm.getApplicationInfo("com.facebook.katana", 0)
        if (applicationInfo.enabled) {
            uri = Uri.parse("fb://facewebmodal/f?href=$url")
        }
    } catch (ignored: PackageManager.NameNotFoundException) {
    }
    return Intent(Intent.ACTION_VIEW, uri)
}
```

- [ ] **Step 2: Create `AboutScreen.kt`**

```kotlin
package com.Elkood.ling_en4.ui.screens.about

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.Elkood.ling_en4.R

const val ABOUT_PRIVACY_URL = "https://www.freeprivacypolicy.com/privacy/view/25844dc6eb5275b8022df849a23f1ca6"
const val ABOUT_TELEGRAM_URL = "https://t.me/AbdalqaderNajjaR"
const val ABOUT_FACEBOOK_URL = "https://www.facebook.com/abdalqader.najjar.9/"

@Composable
fun AboutScreen(
    onOpenUrl: (String) -> Unit,
    onOpenFacebook: () -> Unit,
    onOpenMember: () -> Unit,
    onBack: () -> Unit,
) {
    Column(modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState())) {
        // Hero with back arrow
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(350.dp)
                .background(colorResource(R.color.colorPrimary)),
        ) {
            Image(
                painter = painterResource(R.drawable.ic_untitled),
                contentDescription = null,
                modifier = Modifier.fillMaxSize().padding(24.dp),
            )
            IconButton(onClick = onBack) {
                Icon(
                    painter = painterResource(R.drawable.ic_arrow_back_black_24dp),
                    contentDescription = "back",
                    tint = Color.White,
                )
            }
        }

        // Card 1: app description
        Card(modifier = Modifier.fillMaxWidth().padding(10.dp)) {
            Column(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Image(
                    painter = painterResource(R.drawable.brand),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(colorResource(R.color.backber)),
                    modifier = Modifier.size(80.dp),
                )
                Text(text = "تطبيق", fontSize = 20.sp)
                Text(text = "Zeta", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Text(text = " For ITE in the Secound year")
                Text(
                    text = "تطبيق أُعدَّ لطلاب كلية الهندسة المعلوماتية للسنة الثانية بحيث يوفِّر تغطية شاملة لمقرَّر اللغة 4 إضافة إلى أسئلة الدورات ",
                    modifier = Modifier.padding(top = 8.dp),
                )
            }
        }

        // Card 2: study team + links
        Card(modifier = Modifier.fillMaxWidth().padding(10.dp)) {
            Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
                Text(text = "فريق العمل الدراسي", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable(onClick = onOpenMember)
                        .padding(vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Image(
                        painter = painterResource(R.drawable.ic_people),
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(colorResource(R.color.colorPrimary)),
                        modifier = Modifier.size(32.dp),
                    )
                    Text(text = "أنقر هنا للتعرف", modifier = Modifier.padding(start = 8.dp))
                }
                LinkRow(text = "سياسة الخصوصية", onClick = { onOpenUrl(ABOUT_PRIVACY_URL) })
                LinkRow(text = "الشهادات", onClick = onOpenMemberLicensesPlaceholder@ {})
            }
        }

        Text(text = "تواصل معنا ", modifier = Modifier.padding(start = 16.dp, top = 8.dp))
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            horizontalArrangement = Arrangement.Center,
        ) {
            IconButton(onClick = onOpenFacebook) {
                Icon(
                    painter = painterResource(R.drawable.ic_facebook_logo_in_circular_button_outlined_social_symbol),
                    contentDescription = "facebook",
                    tint = colorResource(R.color.grey),
                )
            }
            IconButton(onClick = { onOpenUrl(ABOUT_TELEGRAM_URL) }) {
                Icon(
                    painter = painterResource(R.drawable.ic_telegram),
                    contentDescription = "telegram",
                    tint = colorResource(R.color.grey),
                )
            }
        }
        Text(
            text = "جميع الحقوق محفوظة ",
            modifier = Modifier.fillMaxWidth().padding(16.dp),
        )
    }
}

@Composable
private fun LinkRow(text: String, onClick: () -> Unit) {
    Text(
        text = text,
        fontSize = 14.sp,
        color = Color.Black,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .clickable(onClick = onClick),
    )
}
```
> IMPORTANT correction to make while implementing: the `الشهادات` (Licenses) row must open `LicensesActivity`, not the placeholder shown above. The screen should take a dedicated `onOpenLicenses: () -> Unit` parameter. Add it to the `AboutScreen` signature and wire `LinkRow(text = "الشهادات", onClick = onOpenLicenses)`. (The placeholder is only in this snippet to flag the wiring — replace it.) Update the signature to:
> `fun AboutScreen(onOpenUrl, onOpenFacebook, onOpenMember, onOpenLicenses, onBack)`.

- [ ] **Step 3: Create `AboutActivity.kt`**

```kotlin
package com.Elkood.ling_en4.ui.screens.about

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.Elkood.ling_en4.ui.screens.licenses.LicensesActivity
import com.Elkood.ling_en4.ui.screens.member.StudyMemberActivity
import com.Elkood.ling_en4.ui.theme.ZetaTheme

class AboutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ZetaTheme {
                AboutScreen(
                    onOpenUrl = { url ->
                        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
                    },
                    onOpenFacebook = {
                        try {
                            startActivity(newFacebookIntent(packageManager, ABOUT_FACEBOOK_URL))
                        } catch (e: ActivityNotFoundException) {
                            Toast.makeText(this, "No FaceBook App", Toast.LENGTH_SHORT).show()
                        }
                    },
                    onOpenMember = { startActivity(Intent(this, StudyMemberActivity::class.java)) },
                    onOpenLicenses = { startActivity(Intent(this, LicensesActivity::class.java)) },
                    onBack = { onBackPressedDispatcher.onBackPressed() },
                )
            }
        }
    }
}
```

- [ ] **Step 4: Build** — Run: `JAVA_HOME=.../azul-17.0.8.1/Contents/Home ./gradlew assembleDebug`. Expected: `BUILD SUCCESSFUL`. Verify all referenced drawables (`ic_untitled`, `ic_arrow_back_black_24dp`, `brand`, `ic_people`, `ic_facebook_logo_in_circular_button_outlined_social_symbol`, `ic_telegram`) exist; substitute exact names from `activity_about.xml` if any differ, and note substitutions in the report.

- [ ] **Step 5: Commit**

```bash
git add app/src/main/java/com/Elkood/ling_en4/ui/screens/about/
git commit -m "feat: migrate About to Compose"
```

---

## Task 8: Add new activities to the manifest

**Files:**
- Modify: `app/src/main/AndroidManifest.xml`

**Interfaces:**
- Consumes: the six new activity classes from Tasks 2–7.
- Produces: launchable manifest entries so Task 9 can repoint the drawer.

This task ONLY adds the six new Compose activities. Legacy entries are removed in Task 10 (after the drawer is repointed and verified) to keep the app launchable at every commit.

- [ ] **Step 1: Add the six new activities** inside `<application>` (place them next to the other `ui.screens.*` activities, e.g. after the `PaperQuizActivity` entry). App-level `android:screenOrientation="portrait"` already applies; match the existing Compose activities by using `android:theme="@style/AppTheme"`.

```xml
        <activity
            android:name=".ui.screens.settings.SettingsActivity"
            android:theme="@style/AppTheme" />
        <activity
            android:name=".ui.screens.about.AboutActivity"
            android:theme="@style/AppTheme" />
        <activity
            android:name=".ui.screens.statistics.StatisticsActivity"
            android:theme="@style/AppTheme" />
        <activity
            android:name=".ui.screens.eng3.Eng3Activity"
            android:theme="@style/AppTheme" />
        <activity
            android:name=".ui.screens.member.StudyMemberActivity"
            android:theme="@style/AppTheme" />
        <activity
            android:name=".ui.screens.licenses.LicensesActivity"
            android:theme="@style/AppTheme" />
```

- [ ] **Step 2: Build** — Run: `JAVA_HOME=.../azul-17.0.8.1/Contents/Home ./gradlew assembleDebug`. Expected: `BUILD SUCCESSFUL` (no duplicate-activity error; the legacy entries have distinct fully-qualified names).

- [ ] **Step 3: Commit**

```bash
git add app/src/main/AndroidManifest.xml
git commit -m "chore: register new Compose drawer activities in manifest"
```

---

## Task 9: Repoint the drawer to the new Compose activities

**Files:**
- Modify: `app/src/main/java/com/Elkood/ling_en4/ui/screens/shell/ShellActions.kt`

**Interfaces:**
- Consumes: `SettingsActivity`, `AboutActivity`, `StatisticsActivity`, `Eng3Activity` (Tasks 6, 7, 5, 4). `StudyMemberActivity` and `LicensesActivity` are reached FROM About (Task 7), not directly from the drawer.

**Reference:** `handleDrawerAction(activity, action)` currently starts the legacy Java activities for the drawer items. Repoint the four drawer-reachable destinations to the new Compose activities. Preserve the existing `overridePendingTransition(R.anim.fade_in, R.anim.fade_out)` call after each `startActivity`. Leave `SHARE` (= `shareApk`) and `HELP` (inert) exactly as they are.

- [ ] **Step 1: Read `ShellActions.kt`** to confirm the exact `when`/branch structure and the enum/constant names (`SCORES`, `SETTINGS`, `ABOUT`, `ENG3`, `SHARE`, `HELP`) and the existing `overridePendingTransition` usage.

- [ ] **Step 2: Repoint the four branches.** For each of the statistics/settings/about/eng3 branches, replace the legacy `Intent(activity, <LegacyJavaClass>::class.java)` target with the corresponding new Compose activity, keeping the surrounding `startActivity` + `overridePendingTransition` intact. Example shape (adapt to the file's actual style — do not change unrelated branches):

```kotlin
        DrawerAction.SCORES -> {
            activity.startActivity(Intent(activity, com.Elkood.ling_en4.ui.screens.statistics.StatisticsActivity::class.java))
            activity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }
        DrawerAction.SETTINGS -> {
            activity.startActivity(Intent(activity, com.Elkood.ling_en4.ui.screens.settings.SettingsActivity::class.java))
            activity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }
        DrawerAction.ABOUT -> {
            activity.startActivity(Intent(activity, com.Elkood.ling_en4.ui.screens.about.AboutActivity::class.java))
            activity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }
        DrawerAction.ENG3 -> {
            activity.startActivity(Intent(activity, com.Elkood.ling_en4.ui.screens.eng3.Eng3Activity::class.java))
            activity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }
```
> Use the ACTUAL action names/constants found in Step 1 (the names above are indicative). Remove any now-unused imports of the legacy Java activities from `ShellActions.kt`.

- [ ] **Step 3: Build (full)** — Run full build+test command. Expected: `BUILD SUCCESSFUL`.

- [ ] **Step 4: Commit**

```bash
git add app/src/main/java/com/Elkood/ling_en4/ui/screens/shell/ShellActions.kt
git commit -m "feat: repoint drawer to Compose destinations"
```

---

## Task 10: Delete legacy Java + XML and remove old manifest entries

**Files:**
- Delete (Java): `Home_fragment`, `About`, `Settings`, `old_version`, `statistics`, `study_member`, `licenses`, `Adapter_member`
- Delete (layouts): the layout XML for each of the above (8 files)
- Modify: `app/src/main/AndroidManifest.xml` (remove the 6 legacy `Header_Elements` activity entries)

**Do NOT delete:** `AdapterHome.java`, `CardHome.java`, `En4HomeCardConstants.java` (Phase 5).

- [ ] **Step 1: Confirm no remaining references** to each legacy class before deleting. Run:

```bash
cd /Users/abdalqaderalnajjar/StudioProjects/zeta-2nd-android
grep -rn "Home_fragment\|Header_Elements.About\|Header_Elements.Settings\|Header_Elements.old_version\|Header_Elements.statistics\|Header_Elements.study_member\|Header_Elements.licenses\|Adapter_member" app/src/main
```
Expected after Tasks 1–9: only the AndroidManifest legacy entries (removed in Step 3) and the files themselves. If any Kotlin/Java still references these classes, fix that first — do not delete a class that is still referenced.

- [ ] **Step 2: Locate and delete the legacy files.** Find exact paths first:

```bash
find app/src/main/java -name "About.java" -o -name "Settings.java" -o -name "old_version.java" \
  -o -name "statistics.java" -o -name "study_member.java" -o -name "licenses.java" \
  -o -name "Adapter_member.java" -o -name "Home_fragment.java"
find app/src/main/res/layout -name "activity_about.xml" -o -name "activity_settings.xml" \
  -o -name "activity_old_version.xml" -o -name "activity_statistics.xml" \
  -o -name "activity_study_member.xml" -o -name "activity_licenses.xml" \
  -o -name "row_member.xml" -o -name "fragment_home.xml"
```
Then `git rm` each returned path. (`row_member.xml` is the study_member row layout; `fragment_home.xml` is the Home layout. Confirm the exact layout filenames from the `find` output before removing — some may differ.)

- [ ] **Step 3: Remove the 6 legacy `Header_Elements` activity entries** from `AndroidManifest.xml` (currently lines ~65–93 and ~105–107): `.Header_Elements.statistics`, `.Header_Elements.old_version`, `.Header_Elements.licenses`, `.Header_Elements.study_member`, `.Header_Elements.Settings`, `.Header_Elements.About`. Leave all other activities (unit_package, Extinsions, True_false, Compound_Nouns, Abbrevationss, Vocabulary, ComposeQuizActivity, PaperQuizActivity, ShellActivity, SplashActivity, QuizActivity_True_false, and the six new Compose activities) intact.

- [ ] **Step 4: Build (full)** — Run full build+test command. Expected: `BUILD SUCCESSFUL`. This confirms nothing referenced the deleted classes/layouts.

- [ ] **Step 5: Commit**

```bash
git add -A
git commit -m "chore: remove legacy Home/drawer Java and XML after Compose migration"
```

---

## Self-Review

**1. Spec coverage** (each spec item → task):
- Home tab (`Home_fragment`) → Task 1. ✅
- study_member → Task 2. ✅
- licenses → Task 3. ✅
- old_version/Eng3 (+ score readback via launcher) → Task 4. ✅
- statistics (+ formulas) → Task 5. ✅
- Settings (+ radio/switch/name-lock, warning dialog) → Task 6. ✅
- About (+ Facebook fallback, privacy/telegram/member/licenses links) → Task 7. ✅
- Manifest: add 6 new activities → Task 8; remove 6 legacy entries → Task 10. ✅
- Drawer rewire (`handleDrawerAction`) → Task 9. ✅
- ShellScreen page-2 rewire → Task 1. ✅
- Legacy Java/XML deletion (8 Java + 8 layouts); keep AdapterHome/CardHome/En4HomeCardConstants → Task 10. ✅
- SharedPreferences continuity (`saveData`, `SaveScore`, `sharedprefs`/`keyhighscore`) → Tasks 4/5/6. ✅
- RTL/LTR (Home, licenses body, member rows LTR) → Tasks 1/3/2. ✅
- Unit tests (JUnit 4, no Robolectric): Eng3, Settings, Statistics, StudyMembers → Tasks 4/5/6/2. ✅

**2. Placeholder scan:** The only intentional fill-in is `LICENSES_TEXT` in Task 3, which is a defined copy-from-XML step (Step 1 specifies the source and the byte-for-byte requirement) plus the `الشهادات`/`onOpenLicenses` wiring correction in Task 7 Step 2. No `TODO`/`TBD`/"add error handling"/"similar to Task N" placeholders remain; every code step carries full code.

**3. Type consistency:** `ComposeQuizActivity.EXTRA_TOPIC` / `EXTRA_SCORE` and `QuizTopic.FULL_QUIZ` / `QuizTopic.ENG3` are used in Tasks 1 and 4 with the `.name` (Kotlin enum) accessor — flagged for the implementer to confirm the exact Kotlin constant names against the existing `ComposeQuizActivity`. `statisticsFrom(...)` signature (Task 5) matches its call in `StatisticsActivity`. `durationLabelFor`/`DURATION_OPTIONS`/`DEFAULT_SAVE_R` (Task 6) are consistent between logic, test, and screen. `formatHighScore`/`ENG3_PREFS`/`ENG3_KEY_HIGHSCORE` (Task 4) consistent across logic/test/activity. `STUDY_MEMBERS` (Task 2) consistent across data/test/screen.

**Known implementer verifications (call out in reports):** exact drawable resource names in About/Settings/Statistics; exact Kotlin names of `ComposeQuizActivity.EXTRA_TOPIC`/`EXTRA_SCORE`/`QuizTopic` members; exact `DrawerAction` constant names in `ShellActions.kt`; exact legacy layout filenames before `git rm`; and copying `LICENSES_TEXT` byte-for-byte from `activity_licenses.xml`.
