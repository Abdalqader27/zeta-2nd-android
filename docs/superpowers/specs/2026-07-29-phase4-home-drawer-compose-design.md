# Phase 4 — Home Tab & Drawer Destinations: Compose Migration Design

**Date:** 2026-07-29
**Phase:** 4 of 7 (see `2026-07-26-compose-migration-design.md` for the master plan)
**Status:** Design — approved, pending spec review

## Goal

Migrate the Home tab (`Home_fragment`) and the six drawer / sub-screen destinations
(`Header_Elements`: `About`, `Settings`, `old_version`, `statistics`, `study_member`,
`licenses`) from Java/XML/Fragments/Activities to Kotlin + Jetpack Compose. After this
phase, tab 2 of the shell is a pure Composable and every drawer action lands on a
Compose screen. The remaining Java in the app is confined to the "Important quizzes"
tab (`BankItemsQuiz` + content, Phase 5) and the full-exam simulators (Phase 6).

## Background & Legacy Behavior

The current shell (`ui/screens/shell/ShellScreen.kt`) is already Compose. It hosts a
`HorizontalPager` of three tabs behind a `ModalNavigationDrawer`:

- **Tab 0 "الدورات"** → `PaperListScreen` (Compose, done in Phase 3).
- **Tab 1 "القائمة"** → `AndroidFragment<BankItemsQuiz>` (Java — **Phase 5**, untouched here).
- **Tab 2 "الرئيسية"** (default) → `AndroidFragment<Home_fragment>` (Java — **this phase**).

The drawer (`DrawerContent.kt`, already Compose) lists six items whose taps are routed
by `handleDrawerAction(activity, action)` in `ShellActions.kt`:

| DrawerAction | Legacy target | This phase |
|---|---|---|
| `SCORES`   | `statistics` Activity | → `StatisticsActivity` (Compose) |
| `HELP`     | `Unit` (inert, legacy parity) | unchanged (inert) |
| `SHARE`    | `shareApk(activity)` | unchanged |
| `SETTINGS` | `Settings` Activity | → `SettingsActivity` (Compose) |
| `ABOUT`    | `About` Activity | → `AboutActivity` (Compose) |
| `ENG3`     | `old_version` Activity | → `Eng3Activity` (Compose) |

`study_member` and `licenses` are **not** drawer items — they are sub-screens launched
**from** `About` (`member(View)` → `study_member`, `licence(View)` → `licenses`).

Key legacy facts to reproduce faithfully:

- **Home** (`fragment_home_fragment.xml`, LTR): an `ic_undraw_mathematics_4otb` illustration
  (weight 4), then a `round15_top` card containing a 1dp `grey_Light` divider, the title
  "حول الاختبار الشامل" (`colorPrimary`), a bold 15sp description
  ("يشمل جميع الأسئلة الموجودة في الدورات والأسئلة الذهبية إضافة إلى المقرر اللغة 4"),
  and the `startExam` `FButton` ("ابدأ الاختبار"). The Java sets **Tajawal Bold**
  (`fonts/tajawal_bold.ttf`) on the button at runtime (overriding the XML `@font/a4`).
  Button colors: `fButtonColor=grey_Light`, `textColor=backber`, corner 20dp, shadow
  `blueberry`/3dp. Tapping launches `ComposeQuizActivity` with `EXTRA_TOPIC =
  QuizTopic.FULL_QUIZ.name()` and `FLAG_ACTIVITY_SINGLE_TOP`. The `nested`
  `BounceScrollView` is empty (dead) and is not reproduced.
- **Settings** (`activity_settings.xml`): store `saveData`. `RadioGroup` (r1/r2/r3, texts
  "30"/"40"/"50") ↔ `saveR` 0/1/2, default index 1. `Switch` ↔ `switch1`, default `true`.
  Name `EditText` ↔ `Name`, default "No Name". One-time edit lock: once `changeName` flag
  is set, the name field is locked; saving a name shows a SweetAlertDialog warning
  "يرجى الإنتباه لن تستطيع تعديل اسمك بعد القيام بحفظه" before committing. Save button,
  back button. `BounceScrollView` → vertical scroll.
- **statistics** (`activity_statistics.xml`): store `SaveScore`, read-only. Nine fields:
  `edit_voca=VocHighScore`, `tfsta=tfscore`, `full_true=fullscore`,
  `fullfalse=abs(421-fullscore)`, `mark=(100*fullscore)/421f`, `numberplay=numberPlay`,
  `abber=AbberHighScore`, `ext=ExtHighScore`, `comp=CompHighScore`. `CollapsingToolbar`
  hero + `CardView`s.
- **old_version** (`activity_old_version.xml`): button `eng3_quiz` → `ComposeQuizActivity`
  with `EXTRA_TOPIC = QuizTopic.ENG3.name()` via `startActivityForResult(REQUST_CODE_QUIZ=1)`;
  on result reads `EXTRA_SCORE`. High score store `sharedprefs` / key `keyhighscore`,
  shown as "High Score :N". Custom back: `AlertDialog` title "Quiz", message
  "Do You Want To Close Your App?", No / Yes(finish).
- **About** (`activity_about.xml`): `CollapsingToolbar` hero + `CardView`s. Privacy link
  `https://www.freeprivacypolicy.com/privacy/view/25844dc6eb5275b8022df849a23f1ca6`;
  telegram `https://t.me/AbdalqaderNajjaR`; facebook via `newFacebookIntent`
  (`fb://facewebmodal/f?href=<url>` if the app is installed, else browser to
  `https://www.facebook.com/abdalqader.najjar.9/`, toast "No FaceBook App" on failure);
  `member` → StudyMember, `licence` → Licenses, back.
- **study_member** (`activity_study_member.xml` + `row_member.xml`): a `RecyclerView`
  (`Adapter_member`) of **14 hard-coded Arabic student names**.
- **licenses** (`activity_licenses.xml`): pure static text content, no logic.

## Design Decisions (resolved during brainstorming)

1. **Separate Compose Activities** (user's choice) — each destination becomes its own
   `AppCompatActivity` hosting one Composable, matching the existing
   `ComposeQuizActivity` / `PaperQuizActivity` / `ShellActivity` pattern. No
   Navigation-Compose is introduced; single-Activity consolidation is deferred to a
   dedicated later phase, once all screens are Compose.
2. **Home is a tab Composable, not an Activity** — `HomeScreen()` replaces
   `AndroidFragment<Home_fragment>` directly at `ShellScreen` page 2.
3. **About owns its sub-screens** — `AboutScreen` launches `StudyMemberActivity` /
   `LicensesActivity` itself, exactly as `About.java` launched `study_member` / `licenses`.
4. **Preserve launch semantics** — `handleDrawerAction` keeps
   `overridePendingTransition(R.anim.fade_in, R.anim.fade_out)` on every `openLegacy`
   navigation; `HELP` stays inert; `SHARE` keeps `shareApk`; `shareApk` and
   `newFacebookIntent` logic are carried over verbatim.
5. **SharedPreferences continuity** — every store name and key is read/written
   identically (`saveData`, `SaveScore`, `sharedprefs`/`keyhighscore`), so existing user
   data (name, settings, high scores, statistics) survives the migration untouched.
6. **Drop dead cruft** — the empty `nested` `BounceScrollView` on Home is not reproduced.
7. **ViewModels only where real mutable state exists** (per master spec). Settings has
   genuine editable state (radio/switch/name + save) → a small screen-local state holder
   is acceptable, but no Android `ViewModel` is required; all state is screen-local
   (`rememberSaveable`) reading/writing `SharedPreferences` on save. Statistics, Licenses,
   StudyMember are read-only/static → plain Composables. Home & Eng3 hold only ephemeral
   UI state. No new `ViewModel` classes are introduced this phase.

## Architecture

### New Kotlin files (under `ui/screens/`)

```
ui/screens/home/HomeScreen.kt                 // tab-2 Composable (no Activity)
ui/screens/settings/SettingsActivity.kt       // AppCompatActivity host
ui/screens/settings/SettingsScreen.kt
ui/screens/about/AboutActivity.kt
ui/screens/about/AboutScreen.kt
ui/screens/about/FacebookIntent.kt            // newFacebookIntent helper (Kotlin)
ui/screens/statistics/StatisticsActivity.kt
ui/screens/statistics/StatisticsScreen.kt
ui/screens/eng3/Eng3Activity.kt
ui/screens/eng3/Eng3Screen.kt
ui/screens/member/StudyMemberActivity.kt
ui/screens/member/StudyMemberScreen.kt
ui/screens/member/StudyMembers.kt             // the 14 hard-coded names
ui/screens/licenses/LicensesActivity.kt
ui/screens/licenses/LicensesScreen.kt
```

Each `*Activity` follows the `ShellActivity` shape: `AppCompatActivity` →
`setContent { ZetaTheme { <Screen>() } }`, reading any needed prefs in `onCreate`.

### Screens

1. **`HomeScreen(onStartExam: () -> Unit)`** — reproduces `fragment_home_fragment.xml`
   under `LayoutDirection.Ltr`: illustration + `round15_top` card (divider, title,
   description, `startExam` button). Reuse the existing `PrimaryButton`/FButton-style
   Compose button styled to match (grey_Light fill, backber text, 20dp corner, shadow,
   Tajawal-Bold). `onStartExam` launches `ComposeQuizActivity` FULL_QUIZ with
   `FLAG_ACTIVITY_SINGLE_TOP`. Wired into `ShellScreen` page 2, replacing
   `AndroidFragment<Home_fragment>`.
2. **`SettingsScreen`** — radios 30/40/50 → `saveR` 0/1/2 (default 1); switch (default
   true) → `switch1`; name field (default "No Name") with the one-time `changeName` lock
   and the Arabic warning `AlertDialog` before committing a name; Save writes all keys to
   `saveData`; back finishes.
3. **`AboutScreen`** — collapsing hero + cards; privacy/telegram/facebook links (facebook
   via `newFacebookIntent`), `member` → `StudyMemberActivity`, `licence` →
   `LicensesActivity`, back.
4. **`StatisticsScreen`** — reads `SaveScore`, renders the nine fields read-only with the
   exact formulas above; collapsing hero + cards.
5. **`Eng3Screen`** — button → `ComposeQuizActivity` ENG3 via
   `rememberLauncherForActivityResult`, reading back `EXTRA_SCORE`; high score from
   `sharedprefs`/`keyhighscore` shown "High Score :N"; custom back `AlertDialog`
   "Do You Want To Close Your App?" No / Yes(finish).
6. **`StudyMemberScreen`** — the 14 names as a `LazyColumn` of `row_member`-style cards.
7. **`LicensesScreen`** — the static licenses text.

### Rewiring & manifest

- `ShellActions.handleDrawerAction`: repoint `SCORES`/`SETTINGS`/`ABOUT`/`ENG3` to the new
  Kotlin activities; keep the fade transition; `HELP`/`SHARE` unchanged.
- `ShellScreen` page 2: `HomeScreen(onStartExam = …)` in place of the `AndroidFragment`.
- `AndroidManifest.xml`: add `SettingsActivity`, `AboutActivity`, `StatisticsActivity`,
  `Eng3Activity`, `StudyMemberActivity`, `LicensesActivity`; remove the six Java
  `<activity>` entries once their replacements are verified.

### Deletions (after each replacement is verified to build & behave)

- Java: `Home/Home_fragment.java`, `Header_Elements/About.java`, `Settings.java`,
  `old_version.java`, `statistics.java`, `study_member.java`, `licenses.java`, and
  `Adapter/Adapter_member.java`.
- Layouts: `fragment_home_fragment.xml`, `activity_about.xml`, `activity_settings.xml`,
  `activity_old_version.xml`, `activity_statistics.xml`, `activity_study_member.xml`,
  `activity_licenses.xml`, `row_member.xml`.
- **Kept (Phase 5):** `Adapter/AdapterHome.java`, `Models/CardHome.java`,
  `Constants/En4/En4HomeCardConstants.java` — used only by `BankItemsQuiz`.

## Cross-cutting constraints

- RTL Arabic UI preserved globally; **Home tab renders LTR** (per its legacy layout).
  Quiz content remains LTR-scoped in `ComposeQuizActivity` (unchanged).
- Reuse `ZetaTheme`, existing colors (`grey_Light`, `backber`, `colorPrimary`,
  `blueberry`, `white_20`, etc.), fonts (Tajawal / `a4` / `a5` / `hugme`), and drawables.
- Model floors unchanged (compileSdk 36, minSdk 21, versionCode 11, versionName 1.4.7,
  compose-bom 2024.09.00, Kotlin 2.0.21, AGP 8.9.1).
- Build after every task with the `JAVA_HOME=…azul-17.0.8.1…` prefix, run in background,
  and confirm success by reading the log file.

## Testing

- **Unit tests** (`app/src/test`) for the pure logic:
  - statistics formulas (`abs(421-fullscore)`, `(100*fullscore)/421f`, field mapping);
  - settings mapping (radio index ↔ `saveR` 0/1/2, defaults, `changeName` lock rule);
  - eng3 high-score read/format ("High Score :N").
- **Build parity**: `assembleDebug` + `testDebugUnitTest` green after each task.
- **Visual/behavioral parity**: verified against the legacy layouts/behavior described
  above (manual/screenshot where feasible); at minimum the app compiles, launches, and
  each migrated screen renders and navigates as before.

## Out of scope (later phases)

- Tab 1 `BankItemsQuiz` and its 7 home cards + MC quiz content (Phase 5).
- `Full_Quizes` full-exam simulators (Phase 6).
- Cleanup of `Constants/En4`, `Adapter`, `Models`, `Utils`, remaining dead layouts (Phase 7).
- Single-Activity / Navigation-Compose consolidation (deferred).
