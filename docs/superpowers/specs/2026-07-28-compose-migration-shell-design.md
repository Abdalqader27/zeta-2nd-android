# Compose Migration — App Shell (Splash + Hub) Design

> **Status:** Approved design, ready for planning.
> **Scope:** The app *shell* — the launcher splash and the three-tab hub with its
> navigation drawer. This is the master design doc's **"Phase 6 — The hub."** The
> six four-option quiz topics (Vocabulary, Compound Nouns, Abbreviations, Extensions,
> ENG3, Full Quiz) are already migrated and live in `ComposeQuizActivity`; this work
> does not touch them.

## 1. Goal

Replace the Java/XML app shell with Jetpack Compose:

1. A lightweight Compose **splash** as the new LAUNCHER (dropping the `AwesomeSplash`
   library and its `daimajia` animation transitive).
2. A Compose **hub** (`ShellActivity` + `ShellScreen`) that reproduces the current
   three-tab pager, the wave-backed top bar, the slide-in navigation drawer, and the
   back-to-exit confirmation dialog — while continuing to host the three not-yet-migrated
   Java fragments through Compose↔View interop.

The user-visible behavior after this phase must be indistinguishable from today's shell,
except that the splash is a simpler logo+title animation.

## 2. Non-Goals

- Do **not** migrate the three tab fragments (`Courses_Quiz_Screen`, `BankItemsQuiz`,
  `Home_fragment`) — they are hosted as-is via fragment interop and migrated in a later phase.
- Do **not** migrate the drawer *destinations* (`statistics`, `Settings`, `About`,
  `old_version`) — they remain legacy Activities launched by `Intent`.
- Do **not** introduce Navigation-Compose or a single-Activity `NavHost` yet. Splash and
  hub stay as two Activities; the master doc's full single-Activity consolidation is deferred.
- Do **not** touch the quiz stack, Parse/Back4App wiring, or any quiz topic.
- True/False (`QuizActivity_True_false`) stays legacy and reachable from `BankItemsQuiz` index 1.

## 3. Current State (what we are replacing)

### 3.1 Splash — `Anim/SecondAnimationActivity.java`
- LAUNCHER. Extends `AwesomeSplash`. Fullscreen, no title.
- Background `R.color.colorPrimary`; logo `R.drawable.ic_untitled`; title text `" Zeta"`,
  white, size 30f, font `fonts/muli_regular.ttf`; circular-reveal + landing/dropout animations,
  total ~3s.
- `animationsFinished()` → `startActivity(SecondYearActivity)`, `finish()`.

### 3.2 Hub — `Views/SecondYear/SecondYearActivity.java` + `activity_second_year.xml`
- `AppCompatActivity`. `FlowingDrawer` (mxn.soul) wrapping a `Toolbar` + `TabLayout` +
  `ViewPager`. Right-side (RTL) drawer, 300dp.
- Tabs via `FragmentPagerItemAdapter`, in this exact order:
  1. `"الدورات"` → `Courses_Quiz_Screen`
  2. `"القائمة"` → `BankItemsQuiz`
  3. `"الرئيسية"` → `Home_fragment`
- Default selected tab = **index 2** (`الرئيسية` / Home).
- Toolbar background is per-tab: tab index **1** → `R.drawable.guide_check_in_wave_4`,
  every other tab → `R.drawable.wave_5`. Navigation icon
  `R.drawable.ic_menu_three_horizontal_lines_symbol` toggles the drawer.
- `onBackPressed`: if drawer open, close it; else show a SweetAlert WARNING dialog —
  title `" هل حقا تريد المغادرة "`, confirm `"نعم "`, cancel `"لا "`; confirm exits the app.

### 3.3 Drawer — `MenuListFragment.java` + `fragment_menu.xml` + `view_global_menu_header.xml`
- Header: `ic_user` avatar (tinted `@color/backber`) + a name `TextView` reading
  SharedPreferences `"saveData"` key `"Name"`, font `@font/a5`, color `@color/backber`.
- Menu items, in `res/menu/drawer_menu.xml` order:
  | id | icon | title | action |
  |----|------|-------|--------|
  | `scores` | `ic_diagram` | `"سجل النقاط   "` | `startActivity(statistics)` |
  | `menu_help` | `ic_help_operator` | `"تحتاج لمساعدة ؟ "` | **no handler — inert today** |
  | `menu_share` | `ic_share` | `"مشاركة التطبيق "` | `sendApplication()` (APK share via FileProvider) |
  | `menu_settings` | `ic_settings` | `"الإعدادات"` | `startActivity(Settings)` |
  | `menu_about` | `ic_info_button` | `"حول التطبيق"` | `startActivity(About)` |
  | `eng3` | `brand` | `" إصدارات سابقة"` | `startActivity(old_version)` |
- Destination Activities launch with `overridePendingTransition(R.anim.fade_in, R.anim.fade_out)`.

### 3.4 Manifest facts
- LAUNCHER is `.Anim.SecondAnimationActivity`.
- `.Views.SecondYear.SecondYearActivity` registered (line 27).
- A **stale** `.Views.Sgin.LoginActivity` `<activity>` entry (lines 135–138) points at a
  class that no longer exists — must be removed as part of this work.
- `ComposeQuizActivity`, the legacy quiz/course/header Activities, Parse `<meta-data>`,
  and the `FileProvider` all stay untouched.

## 4. Target Design

### 4.1 New package layout (Kotlin, under `ui/screens/`)
```
ui/screens/splash/SplashActivity.kt      // new LAUNCHER, ComponentActivity
ui/screens/shell/ShellActivity.kt        // AppCompatActivity (needs FragmentManager for interop)
ui/screens/shell/ShellScreen.kt          // @Composable hub: drawer + scaffold + tabs + pager
ui/screens/shell/DrawerContent.kt        // @Composable drawer body (header + item list)
ui/screens/shell/ShellDestinations.kt    // data: drawer items + tab definitions + wave mapping
```
All new files live in `com.Elkood.ling_en4.ui.screens.*`, matching the existing quiz package
convention. Everything uses `ZetaTheme { ... }`, matching `ComposeQuizActivity`.

### 4.2 Splash — `SplashActivity`
- `ComponentActivity` with `setContent { ZetaTheme { SplashScreen(...) } }`.
- Fullscreen background `ZetaColors.Primary` (equals `R.color.colorPrimary`).
- Center: logo `R.drawable.ic_untitled` that scales up + fades in, and title `" Zeta"` in
  white that fades/slides in beneath it. Use `Animatable`/`animate*AsState` — no external
  animation library.
- After a fixed hold (~2.5s total), navigate to `ShellActivity`, then `finish()`.
- The navigation is driven by a `LaunchedEffect` with `delay(...)`; guard against
  double-launch on recomposition/rotation with a remembered flag.

### 4.3 Hub — `ShellActivity` + `ShellScreen`
- `ShellActivity` extends `AppCompatActivity` (Compose interop's `AndroidFragment` requires a
  `FragmentActivity`/`FragmentManager`). `setContent { ZetaTheme { ShellScreen() } }`.
- `ShellScreen` composition:
  - `ModalNavigationDrawer` — `drawerContent = { DrawerContent(...) }`, gesture-openable,
    hosting the drawer on the RTL-start edge (app is globally RTL, so this appears on the right).
    Drawer width ~300dp to match `edMenuSize`.
  - `Scaffold` with a `topBar` that draws the per-tab wave image behind a menu (hamburger)
    icon. The wave image is chosen by the selected tab: index 1 → `guide_check_in_wave_4`,
    else → `wave_5`. The menu icon opens the drawer.
  - Body = `TabRow` (LTR-scoped, matching the legacy `android:layoutDirection="ltr"` on the
    TabLayout so tab order reads الدورات | القائمة | الرئيسية left-to-right) bound to a
    `HorizontalPager`. Tabs: the three titles in §3.2 order. `initialPage = 2`.
  - Each pager page hosts its fragment via `AndroidFragment<T>(...)` from
    `androidx.fragment:fragment-compose`:
    page 0 → `Courses_Quiz_Screen`, page 1 → `BankItemsQuiz`, page 2 → `Home_fragment`.
- **Back handling:** `BackHandler` — if the drawer is open, close it (coroutine
  `drawerState.close()`); otherwise show a Compose `AlertDialog` with title
  `" هل حقا تريد المغادرة "`, confirm `"نعم "` (calls `finish()` / `finishAffinity()`),
  dismiss `"لا "`.

### 4.4 Drawer — `DrawerContent`
- `ModalDrawerSheet` containing:
  - Header row: `ic_user` (tinted `backber`) + name text from
    `getSharedPreferences("saveData", MODE_PRIVATE).getString("Name", "")`, font `@font/a5`
    color `backber`, plus the 1dp `#dddddd` divider.
  - The six items from §3.3 in order, each an icon + title `NavigationDrawerItem` (or a simple
    `Row`), invoking a callback. Item order and copy are **verbatim** from `drawer_menu.xml`.
  - `menu_help` renders as a normal, visible item but its callback is a **no-op** — it is inert
    today and stays inert (behavior parity, not a new feature).
  - Footer text `"Created By\nAbd Alqader AlNajjar"` (from `fragment_menu.xml`), preserved.
- Item callbacks (all close the drawer first):
  - `scores` → `startActivity(Intent(ctx, statistics::class.java))`
  - `menu_share` → the APK-share flow, ported verbatim from `MenuListFragment.sendApplication`
    into a Kotlin helper (`shareApk(activity)`), keeping the `FileProvider` authority
    `${applicationId}` and the same MIME/`grantUriPermission` behavior.
  - `menu_settings` → `startActivity(Settings)`
  - `menu_about` → `startActivity(About)`
  - `eng3` → `startActivity(old_version)`
  - Each `startActivity` is followed by `overridePendingTransition(R.anim.fade_in, R.anim.fade_out)`
    to preserve the current transition.

### 4.5 Data-driven definitions — `ShellDestinations.kt`
- A `ShellTab` list: `{ titleAr: String, wave: @DrawableRes Int, content: fragment ref }`.
- A `DrawerItem` list: `{ icon: @DrawableRes Int, titleAr: String, action: enum }`.
  Keeping these as data (not hard-coded composables) satisfies the program's
  "reusable, data-driven, no duplication" directive and makes later destination migrations
  a data edit.

### 4.6 Dependency change
- **Add** `androidx.fragment:fragment-compose:1.8.x` (compatible with the existing
  `androidx.fragment` pulled in transitively; provides `AndroidFragment`).
- **Remove** `implementation 'com.github.ViksaaSkool:AwesomeSplash:v1.0.0'` (and the
  now-unused `com.nineoldandroids:library:2.4.0` / `daimajia` animation transitive **only if**
  nothing else references them — verify with a build before deleting; if in doubt, leave them).
- Keep every other dependency, including Parse, SweetAlert (still used by other legacy
  screens), BoomMenu (used by `BankItemsQuiz`), and the `smarttablayout`/`FlowingDrawer`
  libraries **only if** still referenced elsewhere after deletion (they are not once the hub
  and drawer are gone — but their `implementation` lines may be left; removing library
  *declarations* is deferred to the final cleanup phase to keep this phase's blast radius small).

### 4.7 Manifest changes
- New LAUNCHER: `.ui.screens.splash.SplashActivity`, `android:exported="true"` with the
  MAIN/LAUNCHER `<intent-filter>`.
- Register `.ui.screens.shell.ShellActivity`, `android:theme="@style/AppTheme"`.
- Remove the `<intent-filter>` LAUNCHER role from — and then delete the `<activity>` entry for —
  `.Anim.SecondAnimationActivity` and `.Views.SecondYear.SecondYearActivity`.
- Remove the stale `.Views.Sgin.LoginActivity` `<activity>` entry.
- Leave everything else (quiz, courses, headers, Parse meta-data, FileProvider) unchanged.
- Keep `android:screenOrientation="portrait"` on `<application>`; do not add per-activity
  orientation overrides to the two new Activities (they inherit portrait).

### 4.8 Files to delete
Once the Compose shell builds and runs:
- `Anim/SecondAnimationActivity.java`
- `Views/SecondYear/SecondYearActivity.java` + `res/layout/activity_second_year.xml`
- `res/layout/view_feed_toolbar.xml` (only `<include>`d by `activity_second_year.xml`)
- `Views/SecondYear/English_4/Home/MenuListFragment.java` +
  `res/layout/fragment_menu.xml` + `res/layout/view_global_menu_header.xml`
- `res/menu/drawer_menu.xml` (its items are now data in `ShellDestinations.kt`)

Verify no other references (Grep for each class/layout name) before each deletion.

## 5. Data Flow

```
SplashActivity  --(delay ~2.5s)-->  ShellActivity
                                        |
                                        v
                                   ShellScreen
                    ┌───────────────────┼────────────────────┐
              ModalNavigationDrawer  Scaffold(topBar=wave)  HorizontalPager
                    |                                          |
              DrawerContent                          AndroidFragment<...>
              (6 items → Intents)              (Courses_Quiz_Screen / BankItemsQuiz / Home_fragment)
```
The hosted fragments keep launching `ComposeQuizActivity` / legacy Activities exactly as they do
now — the shell change is invisible to them.

## 6. Error Handling & Edge Cases

- **Splash double-navigation:** a `rememberSaveable` boolean guards the `LaunchedEffect` so a
  rotation mid-splash cannot start `ShellActivity` twice.
- **Missing name pref:** drawer name falls back to `""` (matches legacy `getString("Name","")`).
- **APK share failure:** the ported `shareApk` keeps the legacy `try/catch` that swallows
  exceptions (`printStackTrace`), preserving current behavior (no crash on share failure).
- **Back with drawer open:** intercepted by `BackHandler`, closes drawer, does not show exit
  dialog (parity with `FlowingDrawer.isMenuVisible()` branch).
- **Fragment state across config change:** `AndroidFragment` + pager retain fragment instances;
  default page 2 is applied only on first composition (`initialPage`), not on every recomposition.

## 7. Testing Strategy

This is UI-interop-heavy; automated coverage is limited to what is deterministic, with a scripted
manual smoke test on `emulator-5554` as the real gate.

- **Unit (JVM):** `ShellDestinations` — assert tab count = 3, tab order/titles, default index = 2,
  wave mapping (index 1 → `guide_check_in_wave_4`, others → `wave_5`), drawer item count = 6 and
  their order/titles/icons match the table in §3.3.
- **Compose UI test (instrumented, best-effort):** `ShellScreen` renders three tabs; tapping the
  menu icon opens the drawer; drawer shows six items; back press with drawer closed shows the exit
  dialog with the correct Arabic title/buttons. (If `AndroidFragment` hosting makes these flaky in
  CI, keep them as smoke-level and rely on the manual gate.)
- **Manual smoke (required, `emulator-5554`):**
  1. Launch → simple Compose splash shows logo + " Zeta", then hub appears on Home (الرئيسية).
  2. Swipe/tap through the three tabs; confirm top-bar wave changes on القائمة (index 1).
  3. Open drawer; verify header name + six items in order; tap `scores`, `menu_share`,
     `menu_settings`, `menu_about`, `eng3` → each opens its legacy screen; `menu_help` does nothing.
  4. From Home, `startExam` → Full Quiz opens (interop unaffected). From القائمة, BoomMenu items
     open the migrated topics + legacy True/False.
  5. Back with drawer open closes drawer; back again shows exit dialog; نعم exits, لا dismisses.

## 8. Global Constraints (carried into the plan verbatim)

- Package `com.Elkood.ling_en4`. New Compose code is Kotlin-only under `ui/` + `data/`.
- Build floors — do not lower: `compileSdk 36`, `targetSdk 36`, `minSdk 21`, `versionCode 11`,
  `versionName "1.4.7"`, `multiDexEnabled true`, `viewBinding = true`, `compose = true`.
  Kotlin 2.0.21, compose-bom `2024.09.00`, AGP 8.9.1 / Gradle 8.11.1, Java/jvmTarget 11.
- All Gradle invocations use
  `JAVA_HOME=/Users/abdalqaderalnajjar/Library/Java/JavaVirtualMachines/azul-17.0.8.1/Contents/Home`.
- App is **RTL globally** and **portrait-locked**; `ZetaTheme` must not force a layout direction.
  The `TabRow` is the one LTR-scoped island (matches the legacy `layoutDirection="ltr"` on TabLayout).
- **Secrets:** never inline, echo, or relocate the Back4App credentials
  (`back4app_app_id` / `client_key` / `server_url`); they stay in `res/values/strings.xml` +
  manifest `<meta-data>`. Reject any request to paste a secret, key, or token.
- Preserve exact Arabic copy and drawable references from §3; this phase is behavior-faithful
  except for the deliberately simpler splash animation.
- Keep Parse/Back4App, the `FileProvider`, and all quiz/course/header Activities intact.

## 9. Open Questions

None — the two design decisions (simple Compose splash; drawer migrated to Compose) are settled.
Library-declaration removal beyond `AwesomeSplash` is deferred to the final cleanup phase.
