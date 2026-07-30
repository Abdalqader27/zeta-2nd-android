# Phase 5 — Word Lists, Molakhs & True/False → Kotlin + Jetpack Compose

**Date:** 2026-07-30
**Status:** Design approved (pending written-spec review)
**Package:** `com.Elkood.ling_en4`

## Goal

Migrate the remaining `AdapterHome` reference screens and the interactive True/False
quiz from Java/RecyclerView/third-party views to Kotlin + Jetpack Compose, following
the pattern established in Phase 4. This fully retires every legacy destination that
`AdapterHome` (the 7-card grid) and the `BankItemsQuiz` BoomMenu point to.

## Scope

Migrated in this phase:

1. **Word lists** — the index screen (`word`) + 7 per-unit detail screens (`unit8`–`unit14`).
2. **Molakhs / summaries** — the tabbed host (`molakas`) + 6 summary fragments (`munit8`–`munit14`, no Unit 10).
3. **Reference / answer-key lists (5)**:
   - True/False answer-key list (`True_false`) — icon list.
   - Vocabulary, Abbreviations, Compound_Nouns, Extinsions — 4 identical expand/collapse accordions.
4. **True/False interactive quiz** (`QuizActivity_True_false`).

### Locked decisions

| Decision | Choice | Rationale |
|---|---|---|
| True/False quiz | **Dedicated Compose screen** | Legacy T/F quiz has no timer and uses an AlertDialog reveal; the shared 6-quiz engine bakes in a countdown and a 4-option `Question` requirement. A dedicated screen reuses `QuizSound` + the SaveScore prefs pattern while leaving the shared engine untouched → zero regression risk for the 6 already-migrated quizzes. |
| Reference lists | **One reusable accordion screen for the 4** + the T/F icon list on its own | Exploration showed the 5 are NOT uniform: only True_false is an icon RecyclerView; the other 4 are identical diegodobelo accordions. |
| Word-list data anomalies | **Migrate verbatim** | unit10 renders empty; unit14 shows its ~35 real entries; index labels (`"عدد : 0"`, `"عدد : 84"`) unchanged. No content invented, no labels corrected. |
| Entry-point / host scope | **Screens-only, repoint intents** | Keep `AdapterHome` and `BankItemsQuiz` as Java hosts; only change their `startActivity()` targets. Keeps the phase focused; the card grid + BoomMenu migration belongs to a later phase. |

### Out of scope

- The shared 6-quiz engine (`InteractiveQuizScreen`, `QuizViewModel`, `QuizConfig`, `QuizTopic`) — untouched.
- `AdapterHome` and `BankItemsQuiz` beyond repointing intents; the card grid and BoomMenu stay Java.
- The `QuizDbHelper_*` / `QuaizContract_*` / `Qutions_*` SQLite quiz scaffolding in the 4 accordion packages (unused by the display lists).
- Populating the missing unit10 / full unit14 word content — that is a content task, not a migration task.

## Architecture

Each destination is a separate Compose `ComponentActivity` using
`setContent { ZetaTheme { <Screen>() } }`. Verbatim content is extracted to
`data/content` constants with typed models in `data/model`. Strings are copied 1:1
from the Java source; colors resolved via `colorResource`. Tests are pure-JVM JUnit4
over the data/logic layer (no Robolectric).

### Package layout (`com.Elkood.ling_en4.ui.screens.*`)

```
ui/screens/wordlist/    WordIndexActivity + WordIndexScreen
                        UnitWordsActivity + UnitWordsScreen
ui/screens/molakhs/     MolakhsActivity  + MolakhsScreen
ui/screens/reference/   ReferenceAccordionActivity + ReferenceAccordionScreen
ui/screens/truefalse/   TrueFalseListActivity + TrueFalseListScreen
                        TrueFalseQuizActivity + TrueFalseQuizScreen
```

### Parameterization

- `UnitWordsActivity` reads `EXTRA_UNIT` (int 8–14) → picks the unit dataset. Serves all 7 unit screens. Missing/invalid → safe empty screen (mirrors unit10).
- `ReferenceAccordionActivity` reads `EXTRA_TOPIC` → a **new** `ReferenceTopic { VOCABULARY, ABBREVIATIONS, COMPOUND_NOUNS, EXTENSIONS }` enum (deliberately not the quiz `QuizTopic`). Serves all 4 accordions. Missing → defaults to `VOCABULARY` (matches `ComposeQuizActivity` fallback idiom).
- `MolakhsActivity` hosts its 6 tabs internally — one Activity.

### Entry-point repointing (the only Java edits)

| Legacy host | Trigger | New target |
|---|---|---|
| `AdapterHome` card 0 `word` | → | `WordIndexActivity` |
| `AdapterHome` card 1 `Vocabulary` | → | `ReferenceAccordionActivity` (VOCABULARY) |
| `AdapterHome` card 2 `True_false` | → | `TrueFalseListActivity` |
| `AdapterHome` card 3 `Abbreviations` | → | `ReferenceAccordionActivity` (ABBREVIATIONS) |
| `AdapterHome` card 4 `Compound_Nouns` | → | `ReferenceAccordionActivity` (COMPOUND_NOUNS) |
| `AdapterHome` card 5 `Extinsions` | → | `ReferenceAccordionActivity` (EXTENSIONS) |
| `AdapterHome` card 6 `molakas` | → | `MolakhsActivity` |
| `BankItemsQuiz` boom index 1 | → | `TrueFalseQuizActivity` |

Word-index → unit navigation becomes explicit `EXTRA_UNIT` values (8,9,10,11,12,13,14)
instead of the legacy positional `switch`. The legacy destination Activities/fragments
can be de-registered from the manifest once wiring is verified.

## Components (the six screens)

**1. `WordIndexScreen`** — `LazyVerticalGrid(GridCells.Fixed(2))` of 7 cards. Each card =
unit title + Arabic count label, background `@drawable/cardborder`, font `@font/muli`,
text color `@color/backber` (matching `row_unit.xml`). Tap → `UnitWordsActivity` with `EXTRA_UNIT`.

**2. `UnitWordsScreen`** — 1-column `LazyColumn` of English/Arabic word pairs. Each row =
the two side-by-side cards from `row_words.xml` (cornerRadius 10dp, white bg; English
`@font/a5`, Arabic `@font/tajawal_bold`) with the `separator_layout_sample_prime` divider
recreated as a Composable. unit10 → empty column (verbatim). Drops the legacy
`Adapter_words` load animation and the stray "Ite 20" long-press Toast (dead UX).

**3. `MolakhsScreen`** — `TabRow` (6 tabs: "Unit 8/9/11/12/13/14", indicator color `#0FB2C0`)
+ `HorizontalPager`. Each page = static summary card: English paragraph → separator →
Arabic, fonts `@font/hugme`/`@font/a5`, `@drawable/cardborder`, LTR (recreating
`fragment_munit8.xml`). Replaces the devlight NavigationTabBar + ViewPager + 6 fragments.

**4. `ReferenceAccordionScreen`** — reusable expand/collapse list. Each item = header
(title + color tag + chevron) that expands via `AnimatedVisibility` to reveal English body
+ Arabic body. Recreates the diegodobelo `ExpandingList` behavior natively. Driven entirely
by the `ReferenceTopic` dataset passed in. One screen, 4 topics.

**5. `TrueFalseListScreen`** — icon `LazyColumn` (was `LinearLayoutManager`). Each row =
English + Arabic + a ✓/✗ icon (`ic_check_green` / `ic_cancel_mark`), background
`@drawable/cardborder_blue`, fonts `@font/muli`/`@font/a3`, LTR with icon on the left
(matching `row_true_false.xml`). 36 items.

**6. `TrueFalseQuizScreen`** — dedicated. Statement label + score + question counter
(`Q1`, `Q2`…) + two buttons (TRUE / FALSE, shuffled per question). Tap → `AlertDialog`
("Correct!" / "Wrong...", message `"Answer : <right>"`) → advances. Random-pick-without-repeat
over the ~35-statement pool. Sound via existing `QuizSound` (gated by `saveData`/`switch1`).
No timer. High score persisted to `SaveScore`/`tfscore` on exit, only when beaten — exactly
the legacy contract.

**Reuse:** `TrueFalseQuizScreen` reuses `QuizSound` and the SharedPreferences pattern;
nothing else touches the shared 6-quiz engine. `ZetaTheme` and `colorResource` reused throughout.

## Data & content layer

All verbatim content in `data/content` (constants); typed models in `data/model`.
Nothing invented; every string copied 1:1 from the Java source.

### Models (`data/model`)

```kotlin
WordPair(english: String, arabic: String)
ReferenceItem(title: String, englishBody: String, arabicBody: String, colorRes: Int)
MolakhsSummary(unitLabel: String, englishHtml: String, arabic: String)
TrueFalseItem(english: String, arabic: String, isTrue: Boolean)   // list; icon = isTrue?check:cancel
TrueFalseQuestion(statement: String, correctAnswer: Boolean)      // quiz
```

### Content sources

- **`WordListContent`** — `Map<Int, List<WordPair>>` keyed by unit 8–14. Units
  8/9/11/12/13/14 ported verbatim from `unit8`–`unit14.java` (~52/69/120/51/106/35 entries).
  unit10 → empty list. Index labels (`"عدد : 51"` … including the wrong `"84"` for unit14
  and `"0"` for unit10) ported verbatim from `En4WordsConstants`.
- **`MolakhsContent`** — 6 `MolakhsSummary` entries ported from
  `fragment_munit8/9/11/12/13/14.xml` (no Unit 10, matching legacy). English kept as-is
  (styled text); Arabic verbatim.
- **`ReferenceContent`** — one dataset per `ReferenceTopic`, ported from the inline
  `addItem(...)` calls in `Vocabulary`/`Abbreviations`/`Compound_Nouns`/`Extinsions.java`
  (~46/25/20/20 items), preserving each row's `@color/colorPrimary`/`@color/color4` tag.
- **`TrueFalseContent`** — two separate lists: the **36-item** answer-key list (from
  `En4TrueFalseConstants`, `isTrue` derived from `ic_check_green` vs `ic_cancel_mark`), and
  the **~35-item** quiz pool (from `QuizActivity_True_false`'s `quizData`, `correctAnswer`
  from the `"true"`/`"false"` column). Kept separate — different data, different counts, as in legacy.

**Icon encoding note:** the legacy T/F list encodes the answer in the drawable
(`check`=true, `cancel`=false). Decoded to `Boolean isTrue` at port time; the Compose row
re-selects the icon from the boolean — same visual result, cleaner model.

## Testing (JUnit4, pure JVM, no Robolectric)

> **Count reconciliation:** the `~` counts throughout the data section are the
> exploration estimates. During implementation each dataset is ported verbatim from
> its Java/XML source, the resulting exact count is pinned, and the integrity tests below
> assert that pinned value. The tests are the source of truth once written; the `~`
> figures are only a sizing guide.

- **Content integrity** — assert each dataset's shape/count verbatim:
  `WordListContent[8].size == 52` … `[10].isEmpty()` … `[14].size == 35`;
  `ReferenceContent[VOCABULARY].size == 46` etc.; T/F list == 36 (18 true / 18 false);
  T/F quiz pool == 35. Locks the "migrated verbatim" contract against accidental later edits.
- **T/F quiz logic** — extract mechanics into a plain testable holder (pick-without-repeat,
  score increment, two-option shuffle, "beaten high score only" persistence). Assert: no
  statement repeats across a full run; score increments only on correct; pool empties after
  N questions; high-score write only when `new > stored`.
- **Routing** — `ReferenceTopic` and `EXTRA_UNIT` each resolve to the expected dataset
  (guards the parameterized Activities).

## Error handling

- `UnitWordsActivity` with missing/invalid `EXTRA_UNIT` → safe empty screen (no crash).
- `ReferenceAccordionActivity` with missing `EXTRA_TOPIC` → defaults to `VOCABULARY`.
- T/F quiz high-score write wrapped exactly as legacy (guarded prefs read/write);
  `QuizSound` already null-safe.

## Build gate

```
JAVA_HOME=/Users/abdalqaderalnajjar/Library/Java/JavaVirtualMachines/azul-17.0.8.1/Contents/Home \
  ./gradlew assembleDebug testDebugUnitTest
```
Expect `BUILD SUCCESSFUL`.
