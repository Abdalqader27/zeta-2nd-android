# Phase 3 — Paper Quizzes: Compose Migration Design

**Date:** 2026-07-28
**Phase:** 3 of 7 (see `2026-07-26-compose-migration-design.md` for the master plan)
**Status:** Design — approved, pending spec review

## Goal

Replace the nine near-identical legacy "exam paper" Activities (`Courses_Quiz/R2012, R2013, R2014, R2015, r2016, R2016_3, r2017, r2017_2, r2018`) with a single data-driven Jetpack Compose screen (`PaperQuizScreen`) parameterized by an `ExamPaper` enum, and move their content into a Kotlin `En4Papers` object. This collapses ~9 clone Activities (+ their adapters, constants, and layouts) into one screen + one content table.

## Background & Legacy Behavior

The legacy Pattern-B "exam paper" quiz is a **static, self-graded practice sheet** — behaviorally distinct from the Pattern-A interactive quiz already migrated in Phase 2:

- **No score, no timer, no pass threshold, no high-score, no result contract.** Each paper simply renders all its questions at once.
- Each Activity (`Views/SecondYear/English_4/Courses_Quiz/R20xx.java`) is a structural clone: `setContentView(activity_r20xx)`, build a `RecyclerView` with `Adapter_Quiz` over an `En4R20xx*Constants.list`, behind a **cosmetic 4-second `postDelayed` loading overlay** (a `SpinKitView` Wave spinner + "الرجاء الانتظار …" + a motivational quote). Nothing is actually loading — the delay is hardcoded.
- `Adapter_Quiz` renders each row from `quiz_row_2017.xml`: the prompt, a single-select `RadioGroup` of 2–4 options, and a "Check" (`solveButton`) button. On Check it reveals the **full answer key** — the correct option turns green (`#008000`) with a ✓ icon, **every** other present option turns red with a ✗ icon — regardless of what the user selected, then disables the radios.
- The `Quiz` model holds a prompt, four `Rn_check` int flags, and four option strings. Convention: `check == 1` → correct option; `check == 0` → wrong; `check == -1` → option absent (that row is hidden — used for 2- and 3-option questions).
- Content lives in **two** places: the questions in `Constants/En4/En4R20xx*Constants.java` (~54–60 questions each, ~522 total), and the reading passage/header text hardcoded as `@string` resources inside each `activity_r20xx.xml`.
- The hub (`Courses_Quiz_Screen` fragment, hosted on shell tab 0 "الدورات") shows **7 cards** but launches **9 papers**: `Adapter_Courses` dispatches by list position, and the 2017 and 2016 cards each open a chooser dialog (`choice2.xml` → `r2017`/`r2017_2`; `choice.xml` → `r2016` Arabic-explained / `R2016_3` English) before navigating. The other five cards go straight to their paper.

## Design Decisions (resolved during brainstorming)

1. **Drop the 4-second fake loading delay entirely.** Content is now in-memory Kotlin and loads instantly; the delay, spinner, "please wait", and motivational quote are legacy cruft and are not reproduced.
2. **Flatten 7 cards + 2 dialogs → 9 cards, no dialogs.** Each of the nine papers is its own card; tapping navigates straight to it. The former dialog choices become distinct cards with distinguishing Arabic subtitles.
3. **Faithful "Check" reveal.** On Check, reveal the full answer key: correct option green + ✓, all other present options red + ✗, ignoring the user's selection. Matches legacy exactly. No aggregate score.
4. **No ViewModel.** With no loading, score, timer, or persistence, the only mutable state is each question's ephemeral "selected option / revealed" — screen-local UI state hoisted with `rememberSaveable`. (Per master spec: ViewModels only where real mutable state exists.)

## Architecture

### Data layer

**`data/model/PaperQuestion.kt`**
```kotlin
data class PaperQuestion(
    val prompt: String,
    val options: List<String>,   // 2..4 options
    val correctIndex: Int,       // 0-based index into options
)
```
`init` requires `options.size in 2..4` and `correctIndex in options.indices`. Mirrors Pattern-A's `Question` but relaxes the exactly-4 constraint (papers include 2- and 3-option items).

**`data/model/Paper.kt`**
```kotlin
data class Paper(
    val examPaper: ExamPaper,
    val headerTitle: String,     // the header CardView title (university / form)
    val instruction: String,     // the passage instruction line ("" if none)
    val passage: String,         // the reading passage ("" for grammar-only papers)
    val questions: List<PaperQuestion>,
)
```
Exact header/instruction/passage field breakdown is confirmed per-paper during content conversion; empty strings are valid where a paper lacks that block.

**`data/model/ExamPaper.kt`**
```kotlin
enum class ExamPaper(val cardTitle: String, val cardSubtitle: String) {
    R2018(...), R2017(...), R2017_ALT(...), R2016(...), R2016_ALT(...),
    R2015(...), R2014(...), R2013(...), R2012(...),
}
```
- Nine variants. `R2017`/`R2017_ALT` = legacy `r2017`/`r2017_2`; `R2016`/`R2016_ALT` = legacy `r2016` (Arabic-explained) / `R2016_3` (English).
- `cardTitle`/`cardSubtitle` carry the Arabic card copy. The two split pairs' subtitles are taken **verbatim** from the legacy chooser-dialog labels (`choice.xml` radios `arbic_lan`/`eng_lan`; `choice2.xml` radios `f1`/`f2`) and the legacy `item_repeater` names. No invented copy.
- Card display order (newest first): `R2018, R2017, R2017_ALT, R2016, R2016_ALT, R2015, R2014, R2013, R2012`.

**`data/content/En4Papers.kt`**
```kotlin
object En4Papers {
    fun paper(examPaper: ExamPaper): Paper
}
```
- Holds all nine papers converted from `En4R20xx*Constants.java` + the passage/header strings lifted out of `activity_r20xx.xml`.
- A private builder maps legacy flags to the model, mirroring `En4Vocabulary`'s `q()` helper: from the four `Rn_check`/`Rn_text` pairs, drop options whose flag is `-1`, and set `correctIndex` to the (single) option whose flag is `1`.
- **Content text is preserved verbatim**, including any legacy typos in the English question/option/passage strings. The master spec's "fix legacy typos" rule applies to **code identifiers only** (class/variable/package names), never to user-facing content.
- Legacy question counts to match exactly: R2012=54, R2013=60, R2014=60, R2015=60, R2016=60, R2016_ALT(2016_3)=60, R2017=54, R2017_ALT(2017_2)=60, R2018=60.

### UI layer (`ui/screens/paperquiz/`)

**`PaperListScreen.kt`** — `PaperListScreen(onPaperClick: (ExamPaper) -> Unit)`
- The 9-card grid that becomes the Compose content of shell **tab 0 ("الدورات")**, replacing the `AndroidFragment<Courses_Quiz_Screen>` currently at `ShellScreen.kt:115`.
- 2-column grid (`LazyVerticalGrid`), RTL Arabic (default app direction). Cards reproduce `row_repeter.xml`: rounded card (`RoundedCornerShape` ≈ `round15`), folder icon (`ic_folder`), Tajawal-bold title, grey (`#cccccc`) subtitle.
- A private `PaperCard(examPaper, onClick)` composable renders one card. No variant dialog.

**`PaperQuizScreen.kt`** — `PaperQuizScreen(paper: Paper, onBack: () -> Unit)`
- Full-screen `LazyColumn`. Content is **LTR-scoped** via `CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr)` (same pattern as `InteractiveQuizScreen`), since passage/questions/options are English.
- Renders, in order: header card (`paper.headerTitle`), instruction line (if non-empty), passage card (if non-empty), then one `PaperQuestionCard` per `paper.questions`.
- Owns the reveal state hoisted across the list: `rememberSaveable` map `questionIndex -> QuestionState(selectedIndex: Int?, revealed: Boolean)`, so a question checked at the top stays revealed after scrolling to the bottom and back. Provides per-question `onSelect`/`onCheck` callbacks that update the map.
- A back affordance (top bar or `BackHandler`) invokes `onBack`.

**`PaperQuestionCard.kt`** — stateless
```kotlin
@Composable
fun PaperQuestionCard(
    question: PaperQuestion,
    selectedIndex: Int?,
    revealed: Boolean,
    onSelect: (Int) -> Unit,
    onCheck: () -> Unit,
)
```
- Reproduces `quiz_row_2017.xml`: prompt (backber color, Muli-bold 16sp), a rounded container (`round20`) with a single-select option group (2–4 rows), and a "Check" button styled per the legacy `solveButton` (`bbb3` background, green-check icon + "Check ").
- Tapping Check with `selectedIndex == null` surfaces the legacy message "يرجى اختيار الجواب" (a `Snackbar` or inline message); otherwise calls `onCheck`.
- When `revealed`: options are disabled; visuals come from a **pure helper**:
```kotlin
enum class OptionVisual { Normal, Correct, Wrong }

fun optionVisual(index: Int, correctIndex: Int, revealed: Boolean): OptionVisual =
    when {
        !revealed -> OptionVisual.Normal
        index == correctIndex -> OptionVisual.Correct   // green + check icon
        else -> OptionVisual.Wrong                       // red + cancel icon
    }
```
`Correct` → green `#008000` text + `ic_check_green`; `Wrong` → red text + `ic_cancel_mark`. The user's own selection does not change this (faithful to legacy).

**`PaperQuizActivity.kt`** — Compose host
- Mirrors the existing `ComposeQuizActivity` precedent. Manifest entry (`android:screenOrientation="fullSensor"`, `android:theme="@style/AppTheme"`), launched by `PaperListScreen` via Intent with `EXTRA_PAPER = examPaper.name`.
- `onCreate`: `ExamPaper.valueOf(extra)` → `En4Papers.paper(it)` → `setContent { ZetaTheme { PaperQuizScreen(paper, onBack = { finish() }) } }`.
- Gives full-screen presentation + system back, consistent with how Pattern-A quizzes launch. **A single unified NavHost is explicitly out of scope for this phase** (deferred to Phase 7).

### Theming (`ui/theme/`)

- Reuse `ZetaTheme`, `ZetaColors` (correct green `#008000`, wrong red), and `MuliFontFamily` for English content.
- **Add `TajawalFontFamily`** to `Type.kt` for the Arabic card labels (only `MuliFontFamily` exists today), referencing the existing `res/font` Tajawal resources used by `row_repeter.xml`.
- Reuse legacy drawables `ic_check_green`, `ic_cancel_mark`, `ic_folder`.

## Data Flow

```
PaperListScreen (tab 0)
  └─ tap card → onPaperClick(ExamPaper)
        └─ Intent(PaperQuizActivity, EXTRA_PAPER = examPaper.name)
              └─ En4Papers.paper(examPaper) : Paper
                    └─ PaperQuizScreen(paper)
                          ├─ header / instruction / passage
                          └─ per question: PaperQuestionCard
                                selectedIndex / revealed  ←→  rememberSaveable map in PaperQuizScreen
                                Check → optionVisual(...) reveals full key
```

No repository, no ViewModel, no network. `En4Papers` is a pure in-memory content source.

## Error Handling

- `PaperQuestion.init` enforces its invariants (2–4 options, in-range `correctIndex`) — a bad conversion fails fast in tests, not silently in the UI.
- `PaperQuizActivity` resolving an unknown/missing `EXTRA_PAPER` falls back to a safe default (or finishes) rather than crashing.
- "Check" with no selection is a guarded no-op with the legacy user message; it never advances the reveal.

## Testing

Unit tests (`./gradlew :app:testDebugUnitTest`):
- **Content integrity (`En4PapersTest`):** every paper's question count matches the legacy Constants (54/60/60/60/60/60/54/60/60); every `correctIndex` is in range; every question has 2–4 options; no blank prompts; exactly one correct option per question.
- **Reveal logic (`OptionVisualTest`):** `optionVisual` returns `Normal` before reveal for all indices; after reveal returns `Correct` only for `correctIndex` and `Wrong` for every other index, independent of the (absent) user selection.
- Each content-conversion task additionally asserts its own paper's count against the legacy source as part of that task's cycle.

Compose UI tests are optional and light (the reveal semantics are fully covered by the pure-function test).

## Out of Scope (Phase 3)

- **No score / pass-fail / high-score** — these papers never had them; adding them would be a new feature (master spec §9).
- **No content edits** — English question/option/passage text is preserved verbatim, legacy typos included.
- **No NavHost consolidation** — Compose activities stay Intent-launched this phase; unifying them under a single NavHost is deferred to Phase 7.
- **No legacy deletion this phase** — replacing the tab-0 fragment cleanly orphans the entire Pattern-B legacy chain (`Courses_Quiz_Screen`, `Adapter_Courses`, the nine `R20xx` Activities, `En4R20xx*Constants`, `Adapter_Quiz`, and the `activity_r20xx`/`quiz_row_2017`/`choice*`/`row_repeter` layouts). These dead files are left in place so the app keeps building, and are swept in the Phase 7 final cleanup (consistent with prior phases).
- The legacy long-press → Toast on cards and the auxiliary `Courses_Quiz_details` Activity are not ported (the latter verified unreachable during implementation).

## File Manifest

**Create:**
- `data/model/PaperQuestion.kt`
- `data/model/Paper.kt`
- `data/model/ExamPaper.kt`
- `data/content/En4Papers.kt`
- `ui/screens/paperquiz/PaperListScreen.kt`
- `ui/screens/paperquiz/PaperQuizScreen.kt`
- `ui/screens/paperquiz/PaperQuestionCard.kt`
- `ui/screens/paperquiz/PaperQuizActivity.kt`
- `app/src/test/java/.../En4PapersTest.kt`
- `app/src/test/java/.../OptionVisualTest.kt`

**Modify:**
- `ui/theme/Type.kt` — add `TajawalFontFamily`.
- `ui/screens/shell/ShellScreen.kt:115` — replace `AndroidFragment<Courses_Quiz_Screen>` on tab 0 with `PaperListScreen`.
- `app/src/main/AndroidManifest.xml` — add `PaperQuizActivity`.

**Left untouched (swept in Phase 7):** all legacy Pattern-B Java Activities, adapters, constants, and layouts.
