# Design: Java → Kotlin + Jetpack Compose Migration

**Date:** 2026-07-26
**App:** `com.Elkood.ling_en4` ("Zeta" / Elkood — Egyptian secondary-school English learning app)
**Status:** Approved design — ready for implementation planning

## 1. Goal

Fully migrate the app's UI from Java + XML + Activities to **Kotlin + Jetpack Compose**,
built on **MVVM + Navigation-Compose**, replacing duplicated screens with a small set of
reusable, data-driven components. Migration is **incremental with View↔Compose interop**:
the app builds and runs after every phase. Visuals stay **pixel-faithful** to the currently
shipped app.

Compose is Kotlin-only, so this is necessarily a Java→Kotlin conversion as well.

## 2. Current-state facts (from codebase survey)

- ~120 `.java` files, ~43 Activity subclasses, ~26 Fragments, 93 layout XMLs. RTL Arabic UI.
  No ViewModels, no DI, no repositories.
- **Duplication core:** quiz screens are near-identical copies.
  - **Pattern A — interactive one-question-at-a-time quiz** (~350 lines each, line-for-line
    copies): `QuizActivity_Vocabulary`, `QuizActivity_Comp`, `QuizActivity_Abberv`,
    `QuizActivity_Extinsons`, `Header_Elements/Eng3/QuizActivity_Eng3`, and
    `Full_Quizes/QuizActivity_Full_Quiz` (the outlier — Parse-backed). They differ ONLY in:
    layout/id names, the high-score prefs key, the pass-threshold constant, and the data
    source class.
  - **Pattern B — "exam paper" RecyclerView quiz** (~50 lines each, clones):
    `Courses_Quiz/R2012, R2013, R2014, R2015, r2016, R2016_3, r2017, r2017_2, r2018`. Differ
    ONLY in the Constants class + layout/id names. Render all questions at once, graded inline
    by `Adapter_Quiz`, behind a fake 4s `SpinKitView` loading delay.
  - **Word lists:** `Important_quiz/unit_package/unit8..unit14` — each builds a hardcoded
    inline `ArrayList<WordsMeans>` into a grid via `Adapter_words`. Differ only in the words.
  - **True/False:** `QuizActivity_True_false` — its own small mechanism (hardcoded
    `String[][]`, two answer buttons, AlertDialog feedback, prefs key `"tfscore"`).
- **Data is hardcoded in source**, three ways:
  1. `Constants/En4/*.java` — classes exposing a public `ArrayList<Model>` (course papers →
     `Quiz`; `En4HomeCardConstants` → `CardHome`; `En4TrueFalseConstants` → `TrueFalse`;
     `En4WordsConstants` → `WordsMeans`).
  2. **Throwaway SQLite** via `QuizDbHelper_*` + `QuaizContract_*` — used only by Pattern-A.
     Questions are hardcoded in the helper, written to SQLite `onCreate`, then read straight
     back. A pointless round-trip.
  3. Inline `ArrayList` in the Activity (unit8–14, True/False).
- **Models:** `Quiz` (question + 4 int check-flags + 4 option strings), `TrueFalse`,
  `WordsMeans`, `CardHome`, `item_repeater`, and 5 structurally-identical per-variant
  `Qutions_*` (Parcelable: question + 4 options + int answer).
- **Parse (Back4App)** used in exactly 2 files: `LoginActivity` (user bootstrap) and
  `QuizActivity_Full_Quiz` (leaderboard read/write). Credentials via string resources +
  manifest `<meta-data>`.
- **firebase-messaging** is declared but has **no code usage**.
- **Entry flow:** launcher `Anim.SecondAnimationActivity` (AwesomeSplash) → goes **straight to
  `SecondYearActivity`**, bypassing `LoginActivity`. `NewMain2` (a second, bottom-nav shell)
  is registered but unreachable.
- **Live hub:** `SecondYearActivity` = FlowingDrawer + SmartTabLayout + ViewPager, 3 tabs
  (Courses / List / Home, default Home). Back press → SweetAlert confirm-exit.
- **9 adapters** (`Adapter_Quiz`, `Adapter_words`, `Adapter_true_false`, `AdapterHome`,
  `Adapter_member`, `Adapter_Courses`, `Adapter_Unit`, `TabPageAdapetr`,
  `SectionPagerAdapter`); navigation is embedded as Intents in their `onClick`.
- **Third-party UI libs actually used:** AwesomeSplash, flowingdrawer, smarttablayout,
  meow-bottom-navigation, boommenu, navigationtabbar, sweetalert, spinkit, BounceScrollView,
  expandingview, android-flat-button (FButton). **Unused (drop):** ultraviewpager,
  card-slider, BottomNavigationViewEx, firebase-messaging.

## 3. Approved decisions

| Decision | Choice |
|---|---|
| Migration strategy | Incremental, with View↔Compose interop; app runnable after each phase |
| Visual fidelity | Pixel-faithful to the shipped app |
| Architecture | MVVM + Navigation-Compose, single Activity |
| Home shell | **`SecondYearActivity`** (drawer + tabs + pager). Drop `NewMain2`. |
| Login flow | **Keep bypassed** (splash → hub). Leaderboard via existing prefs/identity. |
| Backend | **Keep Parse leaderboard** (wrapped in a repository); **drop firebase-messaging**. |

## 4. Target architecture

- **Single `MainActivity`** hosting a Navigation-Compose `NavHost`. Every screen is a route.
  Adapter-embedded Intents become `navController.navigate(route)`.
- **MVVM:** ViewModels only where there is real mutable state (chiefly `QuizViewModel`:
  current index, score, countdown, answered flag, selected option). Static content screens
  (word lists, menus, About, licenses) are stateless composables reading a repository.
- **State** as `StateFlow<UiState>` immutable data classes.
- **Persistence:** scattered `SharedPreferences` (high scores, countdown duration, sound
  toggle) consolidated behind a `UserPrefs` (DataStore) wrapper.

### Package layout (new Kotlin; coexists with legacy during transition)

```
com.Elkood.ling_en4/
  MainActivity.kt                  single activity, hosts NavHost (added in Phase 6)
  ui/theme/                        Color, Type (Arabic FontFamily), Theme, Shape
  ui/components/                   WaveTopBar, QuizOptionRow, ScoreBar, CountdownChip,
                                   PrimaryButton (FButton→), ConfirmExitDialog (SweetAlert→),
                                   ResultDialog, LoadingSpinner (SpinKit→), MenuCard, WordRow,
                                   IntroExpandable (expandingview→)
  ui/navigation/                   ZetaNavHost, Routes
  ui/screens/
    splash/                        AwesomeSplash → animated Compose splash
    home/                          hub: ModalNavigationDrawer + TabRow + HorizontalPager
    quiz/                          InteractiveQuizScreen + QuizViewModel  (replaces Pattern A)
    paperquiz/                     PaperQuizScreen                        (replaces Pattern B)
    wordlist/                      WordListScreen                         (replaces unit8–14)
    truefalse/                     TrueFalseScreen
    intro/                         one parameterized intro (Vocabulary/Compound/Abbrev/Extins)
    header/                        About, licenses, statistics, settings, study_member, ...
    profile/                       profile screens
  data/model/                      Question, QuizConfig, WordPair, TrueFalseItem, MenuCard,
                                   CourseItem
  data/content/                    En4Papers, En4Words, En4TrueFalse, En4Menu
                                   (Constants/En4/* → Kotlin objects)
  data/repository/                 ContentRepository, LeaderboardRepository (Parse), UserPrefs
```

### Screen consolidation (the core simplification)

| Legacy | Count | Becomes |
|---|---|---|
| Pattern-A interactive quizzes | 6 Activities + SQLite helpers + 5 `Qutions_*` | 1 `InteractiveQuizScreen` + `QuizViewModel`, parameterized by `QuizConfig` (question list, high-score key, pass threshold, sound flag) |
| Pattern-B course papers | 9 Activities | 1 `PaperQuizScreen` (LazyColumn), parameterized by question list |
| Word lists (unit8–14) | 7 Activities | 1 `WordListScreen`, parameterized by word list |
| True/False | 1 Activity | 1 `TrueFalseScreen` |
| Intro landing screens | 4 Activities | 1 parameterized `IntroScreen` |

**Deleted outright:** `QuizDbHelper_*`, `QuaizContract_*` (SQLite round-trip), the per-variant
`Qutions_*` classes (unified into one `Question`), `NewMain2` + its bottom-nav shell, and the
four unused libraries.

## 5. Theming (pixel-faithful)

- Compose `ColorScheme` from the ~6 real colors: `style_color_primary #2d5d82`,
  `style_color_primary_dark #21425d`, `style_color_accent #01bcd5`, `colorPrimary #00BCD4`,
  `green #008000` (correct-answer highlight), greys. Drop the unused `fbutton_color_*` palette.
- Consolidated Arabic `FontFamily` (Tajawal / Cairo), replacing runtime `TypefaceUtil` font
  overrides.
- **RTL preserved** (`LayoutDirection.Rtl`).
- `wave_*` / `guide_check_in_wave_*` header backgrounds reproduced as background composables /
  vector assets.

## 6. Interop & phasing

Each new Compose screen is first hosted in a lightweight Compose-hosting Activity and launched
from the existing Java hub via the same Intents, so the app compiles and runs after every
phase. The hub is collapsed into the single-Activity `NavHost` last.

- **Phase 0 — Foundation:** apply Kotlin + Compose Gradle plugins; add Compose BOM/deps; build
  theme, the reusable component library, `data/model`, `data/content` (convert Constants),
  repositories, and `UserPrefs`. No user-visible change.
- **Phase 1 — Pilot:** `InteractiveQuizScreen` + `QuizViewModel`; migrate the Vocabulary quiz
  (intro + quiz) end-to-end, launched from the existing hub. Verify on device.
- **Phase 2 — Collapse Pattern A:** repoint Compound_Nouns, Abbreviations, Extinsions, Eng3 to
  the same screen (data only). Delete the 5 duplicate Activities, SQLite helpers, `Qutions_*`.
- **Phase 3 — Paper quizzes:** `PaperQuizScreen`; migrate all 9 R20xx course papers.
- **Phase 4 — Word lists + True/False:** `WordListScreen` (unit8–14) + `TrueFalseScreen`.
- **Phase 5 — Intro + Header elements:** parameterized intro; About, licenses, statistics,
  settings, study_member, old_version.
- **Phase 6 — The hub:** Home / Courses / List, drawer, tabs, splash → single-Activity
  `NavHost`. Drop `NewMain2` and the dead libraries.
- **Phase 7 — Cleanup:** delete remaining legacy Java/XML, remove ViewBinding, final AAB build.

Each phase gets its own spec → plan → implement cycle so review happens in reviewable chunks.

## 6a. Naming standard (applies to everything migrated)

The legacy code is riddled with typos and non-descriptive names. Correct naming is a
first-class requirement of this migration — nothing is carried over verbatim.

**Conventions**
- Packages: all-lowercase, no underscores (`ui.screens.quiz`, not `Important_quiz`).
- Classes / data classes / enums: `PascalCase`, descriptive nouns.
- Composables: `PascalCase` functions named for what they render (`InteractiveQuizScreen`,
  `QuizOptionRow`).
- ViewModels: `<Feature>ViewModel`; UI state: `<Feature>UiState`.
- Properties/functions: `camelCase`, no abbreviations (`questionIndex`, not `questCounter`).
- Enums for closed variant sets (`QuizTopic`, `ExamPaper`, `VocabularyUnit`) instead of
  copy-pasted classes.
- Fonts referenced by semantic family name (`Tajawal`, `Cairo`), not `a5`/`st`/`r4`.
- All typos fixed: Sgin→sign/auth, Seach→search, Qutions→Question, Extinsions→Extensions,
  Abbrevationss→Abbreviations, molakas/molakhs→Summary, QuaizContract→(deleted),
  TabPageAdapetr→(deleted).

**Rename map (legacy → migrated)**

| Legacy | Migrated |
|---|---|
| `Anim.SecondAnimationActivity` | `ui.screens.splash` → `SplashScreen` |
| `Views.SecondYear.SecondYearActivity` (hub) | `ui.screens.home` → `HomeScreen` (hosted by `MainActivity` + `NavHost`) |
| `NewMain2` | *deleted* |
| `Views.Sgin.LoginActivity` | `ui.screens.auth` → `LoginScreen` (kept, bypassed) |
| `QuizActivity_Vocabulary/_Comp/_Abberv/_Extinsons/_Eng3/_Full_Quiz` | `ui.screens.quiz` → one `InteractiveQuizScreen` + `QuizViewModel`; variants = `QuizTopic` enum (`VOCABULARY, COMPOUND_NOUNS, ABBREVIATIONS, EXTENSIONS, ENG3, FULL_QUIZ`) |
| `Courses_Quiz.R2012…r2018` (+ `R2016_3`, `r2017_2`) | `ui.screens.paperquiz` → one `PaperQuizScreen`; variants = `ExamPaper` enum (`R2012…R2018`, `R2016_ALT`, `R2017_ALT`) |
| `Important_quiz.unit_package.unit8…unit14` | `ui.screens.wordlist` → one `WordListScreen`; variants = `VocabularyUnit` enum (`UNIT8…UNIT14`) |
| `QuizActivity_True_false` | `ui.screens.truefalse` → `TrueFalseScreen` |
| intro screens `Vocabulary/Compound_Nouns/Abbreviations/Extinsions` | `ui.screens.intro` → one `IntroScreen(topic: QuizTopic)` |
| `Important_quiz.molakas` | `ui.screens.summary` → `SummaryScreen` |
| `Header_Elements.*` (About, licenses, statistics, old_version, study_member, Settings) | `ui.screens.settings` → `AboutScreen`, `LicensesScreen`, `StatisticsScreen`, `OldVersionScreen`, `StudyMembersScreen`, `SettingsScreen` |
| Model `Quiz` | `PaperQuestion` (question + 4 options + correct-option flags) |
| Models `Qutions_Vocabulary/_Comp/_Abberv/_Extinsoins/_Full` | unified `Question` (prompt + 4 options + `correctIndex`) |
| Model `TrueFalse` | `TrueFalseItem` |
| Model `WordsMeans` | `WordPair` (`word`, `meaning`) |
| Model `CardHome` | `MenuCard` |
| Model `item_repeater` | `CourseItem` |
| `Constants.En4.En4R20xxConstants` | `data.content.En4Papers` (keyed by `ExamPaper`) |
| `Constants.En4.En4HomeCardConstants` | `data.content.En4Menu` |
| `Constants.En4.En4TrueFalseConstants` | `data.content.En4TrueFalse` |
| `Constants.En4.En4WordsConstants` | `data.content.En4Words` |
| `QuizDbHelper_*`, `QuaizContract_*` (SQLite) | *deleted* |
| `Adapter_Quiz/_words/_true_false/AdapterHome/_member/_Courses/_Unit` | replaced by `LazyColumn`/`LazyVerticalGrid` item composables |
| `TabPageAdapetr`, `SectionPagerAdapter` | replaced by `HorizontalPager` + `TabRow` |
| `Utils.TypefaceUtil` (runtime font override) | `ui.theme.Type` (`FontFamily`) |

## 7. Error handling

- **Parse/leaderboard:** `LeaderboardRepository` returns a sealed result; the UI degrades
  gracefully to local-only high scores on network/Parse failure (matches today's behavior,
  where the app runs fine offline for everything except Full_Quiz).
- **Content:** all quiz/word content is static Kotlin data — no runtime load failures possible;
  the former SQLite failure surface disappears.
- **Quiz state:** `QuizViewModel` holds state across configuration changes (replacing the
  manual `onSaveInstanceState`/`onRestoreInstanceState` bundle plumbing).

## 8. Testing

- **Unit:** `QuizViewModel` logic (scoring, countdown, answer checking, pass threshold) with
  JUnit — the highest-value tests, since one ViewModel now backs all interactive quizzes.
- **UI:** Compose UI tests for `InteractiveQuizScreen` (select → confirm → next → finish) and
  `TrueFalseScreen`.
- **Manual/device:** each phase verified on emulator/device by the user (per approved plan),
  since fidelity to the shipped UI is a goal.
- **Build gate:** `assembleDebug` green after every phase; `bundleRelease` green at Phase 7.

## 9. Out of scope

- No content changes (questions/words/answers stay exactly as-is).
- No new features; no visual redesign.
- No backend migration away from Parse/Back4App.
- Restoring the bypassed login flow (kept bypassed).
