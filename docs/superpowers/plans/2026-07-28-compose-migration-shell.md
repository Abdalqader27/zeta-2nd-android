# Compose Migration — App Shell (Splash + Hub) Implementation Plan

> **For agentic workers:** REQUIRED SUB-SKILL: Use superpowers:subagent-driven-development (recommended) or superpowers:executing-plans to implement this plan task-by-task. Steps use checkbox (`- [ ]`) syntax for tracking.

**Goal:** Replace the Java/XML app shell (AwesomeSplash launcher + `SecondYearActivity` hub + `MenuListFragment` drawer) with a Compose splash and a Compose hub that hosts the three unmigrated Java fragments via fragment interop.

**Architecture:** Two Activities. `SplashActivity` (Compose, new LAUNCHER) animates a logo + " Zeta" title then launches `ShellActivity`. `ShellActivity` (`AppCompatActivity`, for a `FragmentManager`) hosts `ShellScreen`: a `ModalNavigationDrawer` + `Scaffold` with a per-tab wave top bar + a `TabRow`-driven `HorizontalPager` whose three pages host `Courses_Quiz_Screen`, `BankItemsQuiz`, `Home_fragment` through `AndroidFragment`. Tab/drawer definitions live as data in `ShellDestinations.kt`.

**Tech Stack:** Kotlin, Jetpack Compose (BOM 2024.09.00, Material3), `androidx.fragment:fragment-compose` (new), existing legacy Activities reached via `Intent`.

## Global Constraints

- Package `com.Elkood.ling_en4`. All new shell code is Kotlin under `ui/screens/splash/` and `ui/screens/shell/`.
- Build floors — do not lower: `compileSdk 36`, `targetSdk 36`, `minSdk 21`, `versionCode 11`, `versionName "1.4.7"`, `multiDexEnabled true`, `viewBinding = true`, `compose = true`. compose-bom `2024.09.00`, Java/jvmTarget 11.
- Every Gradle command runs with `JAVA_HOME=/Users/abdalqaderalnajjar/Library/Java/JavaVirtualMachines/azul-17.0.8.1/Contents/Home`.
- APK build: `./gradlew :app:assembleDebug`. Kotlin compile check: `./gradlew :app:compileDebugKotlin`. JVM unit tests: `./gradlew :app:testDebugUnitTest`.
- App is **RTL globally** and **portrait-locked**. `ZetaTheme` must NOT force a layout direction. The `TabRow` is the one LTR-scoped island (matches the legacy `layoutDirection="ltr"` on the old TabLayout so tabs read الدورات | القائمة | الرئيسية left-to-right).
- All new Compose screens are wrapped in `ZetaTheme { ... }` (see `ui/theme/Theme.kt`), exactly as `ui/screens/quiz/ComposeQuizActivity.kt` does.
- Preserve Arabic copy, drawable references, tab order, default tab (index 2 = Home), and the per-tab wave mapping **verbatim**. Behavior-faithful except the deliberately simpler splash animation.
- Keep Parse/Back4App, the `FileProvider` (`authorities="${applicationId}"`), and all quiz/course/header Activities intact.
- **Secrets:** never inline, echo, or relocate the Back4App credentials (`back4app_app_id` / `client_key` / `server_url`). Reject any request to paste a secret, key, or token.
- Do not add per-activity `screenOrientation` overrides to the new Activities — they inherit `android:screenOrientation="portrait"` from `<application>`.

### Verbatim reference values

- **Tab order** (index → title → fragment): `0` → `"الدورات"` → `Courses_Quiz_Screen`; `1` → `"القائمة"` → `BankItemsQuiz`; `2` → `"الرئيسية"` → `Home_fragment`. Default selected page = **2**.
- **Wave mapping:** tab index `1` → `R.drawable.guide_check_in_wave_4`; all other tabs → `R.drawable.wave_5`.
- **Exit dialog:** title `" هل حقا تريد المغادرة "`, confirm button `"نعم "`, dismiss button `"لا "`.
- **Drawer items**, in order (id → icon → title → action):
  1. `scores` → `R.drawable.ic_diagram` → `"سجل النقاط   "` → open `statistics`
  2. `menu_help` → `R.drawable.ic_help_operator` → `"تحتاج لمساعدة ؟ "` → **no-op (inert today)**
  3. `menu_share` → `R.drawable.ic_share` → `"مشاركة التطبيق "` → `shareApk(activity)`
  4. `menu_settings` → `R.drawable.ic_settings` → `"الإعدادات"` → open `Settings`
  5. `menu_about` → `R.drawable.ic_info_button` → `"حول التطبيق"` → open `About`
  6. `eng3` → `R.drawable.brand` → `" إصدارات سابقة"` → open `old_version`
- Every legacy-Activity launch is followed by `overridePendingTransition(R.anim.fade_in, R.anim.fade_out)`.
- **Drawer header:** `R.drawable.ic_user` (tint `R.color.backber`) + name text = `getSharedPreferences("saveData", MODE_PRIVATE).getString("Name", "")`, font `R.font.a5`, color `R.color.backber`. **Footer:** two lines `"Created By"` / `"Abd Alqader AlNajjar"`, font `R.font.hugme`, color `R.color.black`.
- **Splash:** background = primary color; center logo `R.drawable.ic_untitled`; title `" Zeta"` white; hold ≈ 2.5 s then launch `ShellActivity` and `finish()`.

Fully-qualified legacy targets:
- `com.Elkood.ling_en4.Views.SecondYear.English_4.Courses_Quiz.Courses_Quiz_Screen`
- `com.Elkood.ling_en4.Views.SecondYear.English_4.BankItemsQuiz`
- `com.Elkood.ling_en4.Views.SecondYear.English_4.Home.Home_fragment`
- `com.Elkood.ling_en4.Views.SecondYear.English_4.Header_Elements.{statistics, Settings, About, old_version}`

---

### Task 1: Add fragment-compose dependency

**Files:**
- Modify: `app/build.gradle` (dependencies block)

**Interfaces:**
- Produces: `androidx.fragment.compose.AndroidFragment` available to later tasks.

- [ ] **Step 1: Add the dependency**

In `app/build.gradle`, inside the `dependencies { ... }` block (near the other `androidx` Compose entries), add:

```groovy
    implementation 'androidx.fragment:fragment-compose:1.8.5'
```

Leave `AwesomeSplash` and every other dependency untouched for now (AwesomeSplash is removed in Task 9 together with the class that uses it).

- [ ] **Step 2: Verify it resolves and compiles**

Run:
```bash
JAVA_HOME=/Users/abdalqaderalnajjar/Library/Java/JavaVirtualMachines/azul-17.0.8.1/Contents/Home ./gradlew :app:compileDebugKotlin
```
Expected: BUILD SUCCESSFUL (dependency downloads and resolves).

- [ ] **Step 3: Commit**

```bash
git add app/build.gradle
git commit -m "build: add androidx.fragment:fragment-compose for shell interop"
```

---

### Task 2: ShellDestinations data + unit tests

**Files:**
- Create: `app/src/main/java/com/Elkood/ling_en4/ui/screens/shell/ShellDestinations.kt`
- Test: `app/src/test/java/com/Elkood/ling_en4/ui/screens/shell/ShellDestinationsTest.kt`

**Interfaces:**
- Produces:
  - `enum class DrawerAction { SCORES, HELP, SHARE, SETTINGS, ABOUT, ENG3 }`
  - `data class DrawerItem(@DrawableRes val icon: Int, val title: String, val action: DrawerAction)`
  - `data class ShellTab(val title: String, @DrawableRes val wave: Int)`
  - `val SHELL_TABS: List<ShellTab>` (size 3, order per Global Constraints)
  - `const val DEFAULT_TAB_INDEX = 2`
  - `val DRAWER_ITEMS: List<DrawerItem>` (size 6, order per Global Constraints)
  - `fun waveFor(tabIndex: Int): Int` → `guide_check_in_wave_4` for index 1, else `wave_5`

- [ ] **Step 1: Write the failing test**

Create `app/src/test/java/com/Elkood/ling_en4/ui/screens/shell/ShellDestinationsTest.kt`:

```kotlin
package com.Elkood.ling_en4.ui.screens.shell

import com.Elkood.ling_en4.R
import org.junit.Assert.assertEquals
import org.junit.Test

class ShellDestinationsTest {

    @Test
    fun tabs_have_expected_order_and_default() {
        assertEquals(3, SHELL_TABS.size)
        assertEquals("الدورات", SHELL_TABS[0].title)
        assertEquals("القائمة", SHELL_TABS[1].title)
        assertEquals("الرئيسية", SHELL_TABS[2].title)
        assertEquals(2, DEFAULT_TAB_INDEX)
    }

    @Test
    fun wave_mapping_matches_legacy() {
        assertEquals(R.drawable.guide_check_in_wave_4, waveFor(1))
        assertEquals(R.drawable.wave_5, waveFor(0))
        assertEquals(R.drawable.wave_5, waveFor(2))
        assertEquals(R.drawable.wave_5, SHELL_TABS[0].wave)
        assertEquals(R.drawable.guide_check_in_wave_4, SHELL_TABS[1].wave)
    }

    @Test
    fun drawer_items_match_order_titles_actions() {
        assertEquals(6, DRAWER_ITEMS.size)
        assertEquals(
            listOf(
                DrawerAction.SCORES, DrawerAction.HELP, DrawerAction.SHARE,
                DrawerAction.SETTINGS, DrawerAction.ABOUT, DrawerAction.ENG3
            ),
            DRAWER_ITEMS.map { it.action }
        )
        assertEquals("سجل النقاط   ", DRAWER_ITEMS[0].title)
        assertEquals("تحتاج لمساعدة ؟ ", DRAWER_ITEMS[1].title)
        assertEquals("مشاركة التطبيق ", DRAWER_ITEMS[2].title)
        assertEquals("الإعدادات", DRAWER_ITEMS[3].title)
        assertEquals("حول التطبيق", DRAWER_ITEMS[4].title)
        assertEquals(" إصدارات سابقة", DRAWER_ITEMS[5].title)
    }
}
```

- [ ] **Step 2: Run the test to verify it fails**

Run:
```bash
JAVA_HOME=/Users/abdalqaderalnajjar/Library/Java/JavaVirtualMachines/azul-17.0.8.1/Contents/Home ./gradlew :app:testDebugUnitTest --tests "com.Elkood.ling_en4.ui.screens.shell.ShellDestinationsTest"
```
Expected: FAIL — `ShellDestinations.kt` symbols do not exist (unresolved reference).

- [ ] **Step 3: Write the implementation**

Create `app/src/main/java/com/Elkood/ling_en4/ui/screens/shell/ShellDestinations.kt`:

```kotlin
package com.Elkood.ling_en4.ui.screens.shell

import androidx.annotation.DrawableRes
import com.Elkood.ling_en4.R

enum class DrawerAction { SCORES, HELP, SHARE, SETTINGS, ABOUT, ENG3 }

data class DrawerItem(
    @DrawableRes val icon: Int,
    val title: String,
    val action: DrawerAction,
)

data class ShellTab(
    val title: String,
    @DrawableRes val wave: Int,
)

const val DEFAULT_TAB_INDEX = 2

@DrawableRes
fun waveFor(tabIndex: Int): Int =
    if (tabIndex == 1) R.drawable.guide_check_in_wave_4 else R.drawable.wave_5

val SHELL_TABS: List<ShellTab> = listOf(
    ShellTab("الدورات", waveFor(0)),
    ShellTab("القائمة", waveFor(1)),
    ShellTab("الرئيسية", waveFor(2)),
)

val DRAWER_ITEMS: List<DrawerItem> = listOf(
    DrawerItem(R.drawable.ic_diagram, "سجل النقاط   ", DrawerAction.SCORES),
    DrawerItem(R.drawable.ic_help_operator, "تحتاج لمساعدة ؟ ", DrawerAction.HELP),
    DrawerItem(R.drawable.ic_share, "مشاركة التطبيق ", DrawerAction.SHARE),
    DrawerItem(R.drawable.ic_settings, "الإعدادات", DrawerAction.SETTINGS),
    DrawerItem(R.drawable.ic_info_button, "حول التطبيق", DrawerAction.ABOUT),
    DrawerItem(R.drawable.brand, " إصدارات سابقة", DrawerAction.ENG3),
)
```

- [ ] **Step 4: Run the test to verify it passes**

Run the same command as Step 2. Expected: PASS (3 tests).

- [ ] **Step 5: Commit**

```bash
git add app/src/main/java/com/Elkood/ling_en4/ui/screens/shell/ShellDestinations.kt app/src/test/java/com/Elkood/ling_en4/ui/screens/shell/ShellDestinationsTest.kt
git commit -m "feat: add data-driven shell tab & drawer definitions with tests"
```

---

### Task 3: APK-share helper + drawer-action dispatch

**Files:**
- Create: `app/src/main/java/com/Elkood/ling_en4/ui/screens/shell/ShellActions.kt`

**Interfaces:**
- Consumes: `DrawerAction` (Task 2), `shareApk`.
- Produces:
  - `fun shareApk(activity: Activity)` — verbatim port of `MenuListFragment.sendApplication`.
  - `fun handleDrawerAction(activity: Activity, action: DrawerAction)` — routes each action to its legacy screen (with `overridePendingTransition`), `SHARE` → `shareApk`, `HELP` → no-op.

- [ ] **Step 1: Write the implementation**

Create `app/src/main/java/com/Elkood/ling_en4/ui/screens/shell/ShellActions.kt`. `shareApk` is a line-for-line port of the legacy Java (same `"*/*"` MIME, same `ExtractedApk` temp dir, same `FileProvider.getUriForFile(context, activity.packageName, tempFile)`, same swallow-on-exception):

```kotlin
package com.Elkood.ling_en4.ui.screens.shell

import android.app.Activity
import android.content.Intent
import androidx.core.content.FileProvider
import com.Elkood.ling_en4.R
import com.Elkood.ling_en4.Views.SecondYear.English_4.Header_Elements.About
import com.Elkood.ling_en4.Views.SecondYear.English_4.Header_Elements.Settings
import com.Elkood.ling_en4.Views.SecondYear.English_4.Header_Elements.old_version
import com.Elkood.ling_en4.Views.SecondYear.English_4.Header_Elements.statistics
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

fun handleDrawerAction(activity: Activity, action: DrawerAction) {
    when (action) {
        DrawerAction.SCORES -> openLegacy(activity, statistics::class.java)
        DrawerAction.HELP -> Unit // inert today — parity with legacy (no handler)
        DrawerAction.SHARE -> shareApk(activity)
        DrawerAction.SETTINGS -> openLegacy(activity, Settings::class.java)
        DrawerAction.ABOUT -> openLegacy(activity, About::class.java)
        DrawerAction.ENG3 -> openLegacy(activity, old_version::class.java)
    }
}

private fun openLegacy(activity: Activity, target: Class<*>) {
    activity.startActivity(Intent(activity.applicationContext, target))
    activity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
}

fun shareApk(activity: Activity) {
    val app = activity.applicationContext.applicationInfo
    val filePath = app.sourceDir

    val intent = Intent(Intent.ACTION_SEND)
    // MIME of .apk is "application/vnd.android.package-archive" but Bluetooth
    // does not accept it — legacy code uses "*/*".
    intent.type = "*/*"

    val originalApk = File(filePath)
    try {
        var tempFile = File(activity.externalCacheDir.toString() + "/ExtractedApk")
        if (!tempFile.isDirectory) {
            if (!tempFile.mkdirs()) return
        }
        tempFile = File(
            tempFile.path + "/" +
                activity.getString(app.labelRes).replace(" ", "").lowercase() + ".apk"
        )
        if (!tempFile.exists()) {
            if (!tempFile.createNewFile()) return
        }
        FileInputStream(originalApk).use { input ->
            FileOutputStream(tempFile).use { output ->
                val buf = ByteArray(1024)
                var len: Int
                while (input.read(buf).also { len = it } > 0) {
                    output.write(buf, 0, len)
                }
            }
        }
        val uri = FileProvider.getUriForFile(activity, activity.packageName, tempFile)
        intent.putExtra(Intent.EXTRA_STREAM, uri)
        activity.grantUriPermission(
            activity.packageManager.toString(), uri,
            Intent.FLAG_GRANT_WRITE_URI_PERMISSION or Intent.FLAG_GRANT_READ_URI_PERMISSION
        )
        activity.startActivity(intent)
    } catch (e: Exception) {
        e.printStackTrace()
    }
}
```

- [ ] **Step 2: Verify it compiles**

Run:
```bash
JAVA_HOME=/Users/abdalqaderalnajjar/Library/Java/JavaVirtualMachines/azul-17.0.8.1/Contents/Home ./gradlew :app:compileDebugKotlin
```
Expected: BUILD SUCCESSFUL. (Confirms the legacy target classes and `R.anim.fade_in/out` resolve.)

- [ ] **Step 3: Commit**

```bash
git add app/src/main/java/com/Elkood/ling_en4/ui/screens/shell/ShellActions.kt
git commit -m "feat: port APK-share + drawer-action dispatch to Kotlin"
```

---

### Task 4: DrawerContent composable

**Files:**
- Create: `app/src/main/java/com/Elkood/ling_en4/ui/screens/shell/DrawerContent.kt`

**Interfaces:**
- Consumes: `DRAWER_ITEMS`, `DrawerItem`, `DrawerAction` (Task 2).
- Produces: `@Composable fun DrawerContent(userName: String, onItemClick: (DrawerAction) -> Unit)`.

- [ ] **Step 1: Write the implementation**

Create `app/src/main/java/com/Elkood/ling_en4/ui/screens/shell/DrawerContent.kt`. Header (avatar `ic_user` tinted `backber` + name in font `a5`), the six items, and the two-line footer (font `hugme`) — RTL is inherited globally, so no explicit direction here:

```kotlin
package com.Elkood.ling_en4.ui.screens.shell

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.Elkood.ling_en4.R

@Composable
fun DrawerContent(userName: String, onItemClick: (DrawerAction) -> Unit) {
    val backber = colorResource(R.color.backber)
    val a5 = FontFamily(Font(R.font.a5))
    val hugme = FontFamily(Font(R.font.hugme))

    ModalDrawerSheet(
        drawerContainerColor = colorResource(R.color.grey_Light),
        modifier = Modifier.width(320.dp),
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            // Header
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_user),
                    contentDescription = null,
                    tint = backber,
                    modifier = Modifier.size(48.dp),
                )
                Spacer(Modifier.width(12.dp))
                Text(
                    text = userName,
                    color = backber,
                    fontFamily = a5,
                    fontSize = 16.sp,
                )
            }
            HorizontalDivider(color = Color(0xFFDDDDDD))

            // Items
            DRAWER_ITEMS.forEach { item ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onItemClick(item.action) }
                        .padding(horizontal = 16.dp, vertical = 14.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        painter = painterResource(item.icon),
                        contentDescription = null,
                        tint = backber,
                        modifier = Modifier.size(24.dp),
                    )
                    Spacer(Modifier.width(16.dp))
                    Text(text = item.title, color = backber, fontFamily = a5)
                }
            }

            Spacer(Modifier.weight(1f))

            // Footer
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    "Created By",
                    color = colorResource(R.color.black),
                    fontFamily = hugme,
                    textAlign = TextAlign.Center,
                )
                Text(
                    "Abd Alqader AlNajjar",
                    color = colorResource(R.color.black),
                    fontFamily = hugme,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(5.dp),
                )
            }
        }
    }
}
```

- [ ] **Step 2: Verify it compiles**

Run:
```bash
JAVA_HOME=/Users/abdalqaderalnajjar/Library/Java/JavaVirtualMachines/azul-17.0.8.1/Contents/Home ./gradlew :app:compileDebugKotlin
```
Expected: BUILD SUCCESSFUL. If `R.color.grey_Light` / `R.color.backber` / `R.font.hugme` / `R.font.a5` fail to resolve, confirm exact names via `app/src/main/res/values/colors.xml` and `app/src/main/res/font/` and correct the references.

- [ ] **Step 3: Commit**

```bash
git add app/src/main/java/com/Elkood/ling_en4/ui/screens/shell/DrawerContent.kt
git commit -m "feat: add Compose navigation drawer content"
```

---

### Task 5: ShellScreen (drawer + wave top bar + tabs + pager + exit dialog)

**Files:**
- Create: `app/src/main/java/com/Elkood/ling_en4/ui/screens/shell/ShellScreen.kt`

**Interfaces:**
- Consumes: `SHELL_TABS`, `DEFAULT_TAB_INDEX`, `DrawerContent`, `handleDrawerAction` (Tasks 2–4).
- Produces: `@Composable fun ShellScreen(activity: Activity, userName: String, onExit: () -> Unit)`.

- [ ] **Step 1: Write the implementation**

Create `app/src/main/java/com/Elkood/ling_en4/ui/screens/shell/ShellScreen.kt`. `TabRow`+`HorizontalPager` are wrapped in an LTR `CompositionLocalProvider` island so the three tabs read left-to-right like the legacy `layoutDirection="ltr"` TabLayout; the drawer, top bar, and dialog stay RTL. Pages host the fragments through `AndroidFragment`:

```kotlin
package com.Elkood.ling_en4.ui.screens.shell

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.fragment.compose.AndroidFragment
import com.Elkood.ling_en4.R
import com.Elkood.ling_en4.Views.SecondYear.English_4.BankItemsQuiz
import com.Elkood.ling_en4.Views.SecondYear.English_4.Courses_Quiz.Courses_Quiz_Screen
import com.Elkood.ling_en4.Views.SecondYear.English_4.Home.Home_fragment
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShellScreen(activity: Activity, userName: String, onExit: () -> Unit) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState(initialPage = DEFAULT_TAB_INDEX) { SHELL_TABS.size }
    var showExitDialog by remember { mutableStateOf(false) }

    BackHandler(enabled = true) {
        if (drawerState.isOpen) {
            scope.launch { drawerState.close() }
        } else {
            showExitDialog = true
        }
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerContent(userName = userName) { action ->
                scope.launch { drawerState.close() }
                handleDrawerAction(activity, action)
            }
        },
    ) {
        Scaffold(
            topBar = {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    contentAlignment = Alignment.CenterStart,
                ) {
                    Image(
                        painter = painterResource(waveFor(pagerState.currentPage)),
                        contentDescription = null,
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier.fillMaxSize(),
                    )
                    IconButton(onClick = { scope.launch { drawerState.open() } }) {
                        Icon(
                            painter = painterResource(R.drawable.ic_menu_three_horizontal_lines_symbol),
                            contentDescription = "menu",
                            tint = Color.White,
                        )
                    }
                }
            },
        ) { innerPadding ->
            Box(modifier = Modifier.fillMaxSize().padding(innerPadding)) {
                CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
                    androidx.compose.foundation.layout.Column {
                        TabRow(selectedTabIndex = pagerState.currentPage) {
                            SHELL_TABS.forEachIndexed { index, tab ->
                                Tab(
                                    selected = pagerState.currentPage == index,
                                    onClick = { scope.launch { pagerState.animateScrollToPage(index) } },
                                    text = { Text(tab.title) },
                                )
                            }
                        }
                        HorizontalPager(
                            state = pagerState,
                            modifier = Modifier.fillMaxSize(),
                        ) { page ->
                            CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
                                when (page) {
                                    0 -> AndroidFragment<Courses_Quiz_Screen>(Modifier.fillMaxSize())
                                    1 -> AndroidFragment<BankItemsQuiz>(Modifier.fillMaxSize())
                                    else -> AndroidFragment<Home_fragment>(Modifier.fillMaxSize())
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    if (showExitDialog) {
        AlertDialog(
            onDismissRequest = { showExitDialog = false },
            title = { Text(" هل حقا تريد المغادرة ") },
            confirmButton = {
                TextButton(onClick = { showExitDialog = false; onExit() }) { Text("نعم ") }
            },
            dismissButton = {
                TextButton(onClick = { showExitDialog = false }) { Text("لا ") }
            },
        )
    }
}
```

- [ ] **Step 2: Verify it compiles**

Run:
```bash
JAVA_HOME=/Users/abdalqaderalnajjar/Library/Java/JavaVirtualMachines/azul-17.0.8.1/Contents/Home ./gradlew :app:compileDebugKotlin
```
Expected: BUILD SUCCESSFUL. Confirms `AndroidFragment` resolves and the three fragment classes are importable at the fully-qualified paths in Global Constraints. If `Courses_Quiz_Screen`'s exact class name/path differs, fix the import (verify with `find app/src/main/java -name 'Courses_Quiz_Screen*'`).

- [ ] **Step 3: Commit**

```bash
git add app/src/main/java/com/Elkood/ling_en4/ui/screens/shell/ShellScreen.kt
git commit -m "feat: add Compose hub (drawer + wave top bar + tabs + pager + exit dialog)"
```

---

### Task 6: ShellActivity + manifest registration (not launcher yet)

**Files:**
- Create: `app/src/main/java/com/Elkood/ling_en4/ui/screens/shell/ShellActivity.kt`
- Modify: `app/src/main/AndroidManifest.xml`

**Interfaces:**
- Consumes: `ShellScreen` (Task 5), `ZetaTheme` (`ui/theme/Theme.kt`).
- Produces: launchable `com.Elkood.ling_en4.ui.screens.shell.ShellActivity`.

- [ ] **Step 1: Write ShellActivity**

Create `app/src/main/java/com/Elkood/ling_en4/ui/screens/shell/ShellActivity.kt`:

```kotlin
package com.Elkood.ling_en4.ui.screens.shell

import android.content.Context
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.Elkood.ling_en4.ui.theme.ZetaTheme

class ShellActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val userName = getSharedPreferences("saveData", Context.MODE_PRIVATE)
            .getString("Name", "") ?: ""
        setContent {
            ZetaTheme {
                ShellScreen(
                    activity = this,
                    userName = userName,
                    onExit = { finishAffinity() },
                )
            }
        }
    }
}
```

- [ ] **Step 2: Register ShellActivity in the manifest**

In `app/src/main/AndroidManifest.xml`, next to the `ComposeQuizActivity` entry, add (do NOT add a LAUNCHER intent-filter yet, and do NOT change the existing launcher):

```xml
        <activity
            android:name=".ui.screens.shell.ShellActivity"
            android:theme="@style/AppTheme" />
```

- [ ] **Step 3: Build the APK**

Run:
```bash
JAVA_HOME=/Users/abdalqaderalnajjar/Library/Java/JavaVirtualMachines/azul-17.0.8.1/Contents/Home ./gradlew :app:assembleDebug
```
Expected: BUILD SUCCESSFUL.

- [ ] **Step 4: Install and launch ShellActivity directly (smoke)**

Run:
```bash
~/Library/Android/sdk/platform-tools/adb -s emulator-5554 install -r app/build/outputs/apk/debug/app-debug.apk
~/Library/Android/sdk/platform-tools/adb -s emulator-5554 shell am start -n com.Elkood.ling_en4/.ui.screens.shell.ShellActivity
```
Expected: hub opens on tab index 2 (الرئيسية / Home). Manually verify: three tabs; القائمة (index 1) shows the different wave; menu icon opens the drawer with the six items + name header; each drawer item opens its legacy screen (menu_help does nothing); Home `startExam` opens Full Quiz; BoomMenu in القائمة opens the migrated topics + legacy True/False; back with drawer open closes it, back again shows the exit dialog (نعم exits, لا dismisses).

- [ ] **Step 5: Commit**

```bash
git add app/src/main/java/com/Elkood/ling_en4/ui/screens/shell/ShellActivity.kt app/src/main/AndroidManifest.xml
git commit -m "feat: add ShellActivity and register it in the manifest"
```

---

### Task 7: SplashActivity (Compose) → ShellActivity

**Files:**
- Create: `app/src/main/java/com/Elkood/ling_en4/ui/screens/splash/SplashActivity.kt`
- Modify: `app/src/main/AndroidManifest.xml`

**Interfaces:**
- Consumes: `ShellActivity` (Task 6), `ZetaTheme`.
- Produces: launchable `com.Elkood.ling_en4.ui.screens.splash.SplashActivity`.

- [ ] **Step 1: Write SplashActivity**

Create `app/src/main/java/com/Elkood/ling_en4/ui/screens/splash/SplashActivity.kt`. A `rememberSaveable` flag guards against a double-launch on rotation:

```kotlin
package com.Elkood.ling_en4.ui.screens.splash

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.Elkood.ling_en4.R
import com.Elkood.ling_en4.ui.screens.shell.ShellActivity
import com.Elkood.ling_en4.ui.theme.ZetaTheme
import kotlinx.coroutines.delay

class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ZetaTheme {
                SplashScreen(onFinished = {
                    startActivity(Intent(this, ShellActivity::class.java))
                    finish()
                })
            }
        }
    }
}

@Composable
private fun SplashScreen(onFinished: () -> Unit) {
    val logoScale = remember { Animatable(0.6f) }
    val contentAlpha = remember { Animatable(0f) }
    var navigated by rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        contentAlpha.animateTo(1f, tween(800))
        logoScale.animateTo(1f, tween(800))
        delay(1700)
        if (!navigated) {
            navigated = true
            onFinished()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Image(
            painter = painterResource(R.drawable.ic_untitled),
            contentDescription = null,
            modifier = Modifier
                .size(160.dp)
                .scale(logoScale.value)
                .alpha(contentAlpha.value),
        )
        Spacer(Modifier.height(16.dp))
        Text(
            text = " Zeta",
            color = Color.White,
            fontSize = 30.sp,
            modifier = Modifier.alpha(contentAlpha.value),
        )
    }
}
```

- [ ] **Step 2: Register SplashActivity in the manifest (not launcher yet)**

Add, near the other Compose activities (no intent-filter yet):

```xml
        <activity
            android:name=".ui.screens.splash.SplashActivity"
            android:theme="@style/AppTheme" />
```

- [ ] **Step 3: Build and smoke-launch splash directly**

Run:
```bash
JAVA_HOME=/Users/abdalqaderalnajjar/Library/Java/JavaVirtualMachines/azul-17.0.8.1/Contents/Home ./gradlew :app:assembleDebug
~/Library/Android/sdk/platform-tools/adb -s emulator-5554 install -r app/build/outputs/apk/debug/app-debug.apk
~/Library/Android/sdk/platform-tools/adb -s emulator-5554 shell am start -n com.Elkood.ling_en4/.ui.screens.splash.SplashActivity
```
Expected: primary-colored splash with logo scaling/fading in and " Zeta" title, then the hub (Home tab) after ~2.5 s.

- [ ] **Step 4: Commit**

```bash
git add app/src/main/java/com/Elkood/ling_en4/ui/screens/splash/SplashActivity.kt app/src/main/AndroidManifest.xml
git commit -m "feat: add Compose splash activity that launches the hub"
```

---

### Task 8: Flip the LAUNCHER + remove stale manifest entries

**Files:**
- Modify: `app/src/main/AndroidManifest.xml`

**Interfaces:**
- Consumes: `SplashActivity` (Task 7).

- [ ] **Step 1: Make SplashActivity the LAUNCHER**

Give the `.ui.screens.splash.SplashActivity` entry `android:exported="true"` and the MAIN/LAUNCHER intent-filter:

```xml
        <activity
            android:name=".ui.screens.splash.SplashActivity"
            android:exported="true"
            android:theme="@style/AppTheme">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />
                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
```

- [ ] **Step 2: Remove the old launcher + hub + stale entries**

Delete these three `<activity>` blocks from `AndroidManifest.xml`:
- `.Anim.SecondAnimationActivity` (the old LAUNCHER, lines ~147–157)
- `.Views.SecondYear.SecondYearActivity` (line ~27)
- `.Views.Sgin.LoginActivity` (stale — class already deleted, lines ~135–138)

- [ ] **Step 3: Build and verify a single launcher**

Run:
```bash
JAVA_HOME=/Users/abdalqaderalnajjar/Library/Java/JavaVirtualMachines/azul-17.0.8.1/Contents/Home ./gradlew :app:assembleDebug
```
Expected: BUILD SUCCESSFUL. (`SecondAnimationActivity.java` / `SecondYearActivity.java` still exist as source in this task, so the manifest removal alone must still compile — they are deleted in Task 9.)

- [ ] **Step 4: Smoke — launch from the launcher intent**

Run:
```bash
~/Library/Android/sdk/platform-tools/adb -s emulator-5554 install -r app/build/outputs/apk/debug/app-debug.apk
~/Library/Android/sdk/platform-tools/adb -s emulator-5554 shell monkey -p com.Elkood.ling_en4 -c android.intent.category.LAUNCHER 1
```
Expected: the app cold-starts into the Compose splash, then the hub.

- [ ] **Step 5: Commit**

```bash
git add app/src/main/AndroidManifest.xml
git commit -m "feat: make Compose splash the launcher; drop legacy shell + stale manifest entries"
```

---

### Task 9: Delete legacy shell sources + drop AwesomeSplash

**Files:**
- Delete: `app/src/main/java/com/Elkood/ling_en4/Anim/SecondAnimationActivity.java`
- Delete: `app/src/main/java/com/Elkood/ling_en4/Views/SecondYear/SecondYearActivity.java`
- Delete: `app/src/main/res/layout/activity_second_year.xml`
- Delete: `app/src/main/java/com/Elkood/ling_en4/Views/SecondYear/English_4/Home/MenuListFragment.java`
- Delete: `app/src/main/res/layout/fragment_menu.xml`
- Delete: `app/src/main/res/layout/view_global_menu_header.xml`
- Delete: `app/src/main/res/menu/drawer_menu.xml`
- Modify: `app/build.gradle` (remove the `AwesomeSplash` line)

**Interfaces:** none produced.

- [ ] **Step 1: Confirm nothing else references the doomed symbols**

Run (each should return no results other than the files being deleted themselves):
```bash
cd /Users/abdalqaderalnajjar/StudioProjects/zeta-2nd-android
grep -rn "SecondAnimationActivity" app/src/main --include=*.java --include=*.kt --include=*.xml | grep -v Anim/SecondAnimationActivity.java
grep -rn "SecondYearActivity" app/src/main --include=*.java --include=*.kt --include=*.xml | grep -v SecondYear/SecondYearActivity.java
grep -rn "MenuListFragment" app/src/main --include=*.java --include=*.kt --include=*.xml | grep -v Home/MenuListFragment.java
grep -rln "activity_second_year\|fragment_menu\b\|view_global_menu_header\|drawer_menu" app/src/main
grep -rn "AwesomeSplash\|com.viksaa\|com.daimajia" app/src/main --include=*.java --include=*.kt
```
Expected: only the files listed for deletion appear (or nothing). `activity_new_main.xml` / `activity_main.xml` still `<include>` `view_feed_toolbar` — that is expected and untouched here (deferred cleanup). If any live class references a doomed symbol, STOP and report; do not delete.

- [ ] **Step 2: Delete the files**

```bash
git rm \
  app/src/main/java/com/Elkood/ling_en4/Anim/SecondAnimationActivity.java \
  app/src/main/java/com/Elkood/ling_en4/Views/SecondYear/SecondYearActivity.java \
  app/src/main/res/layout/activity_second_year.xml \
  app/src/main/java/com/Elkood/ling_en4/Views/SecondYear/English_4/Home/MenuListFragment.java \
  app/src/main/res/layout/fragment_menu.xml \
  app/src/main/res/layout/view_global_menu_header.xml \
  app/src/main/res/menu/drawer_menu.xml
```

- [ ] **Step 3: Remove the AwesomeSplash dependency**

In `app/build.gradle`, delete the line:
```groovy
    implementation 'com.github.ViksaaSkool:AwesomeSplash:v1.0.0'
```
Leave the `nineoldandroids`/`daimajia` transitives and the `FlowingDrawer`/`smarttablayout` lines as-is (deferred to final cleanup).

- [ ] **Step 4: Build the APK**

Run:
```bash
JAVA_HOME=/Users/abdalqaderalnajjar/Library/Java/JavaVirtualMachines/azul-17.0.8.1/Contents/Home ./gradlew :app:assembleDebug
```
Expected: BUILD SUCCESSFUL (no unresolved references to the deleted classes/resources).

- [ ] **Step 5: Full regression smoke**

```bash
~/Library/Android/sdk/platform-tools/adb -s emulator-5554 install -r app/build/outputs/apk/debug/app-debug.apk
~/Library/Android/sdk/platform-tools/adb -s emulator-5554 shell monkey -p com.Elkood.ling_en4 -c android.intent.category.LAUNCHER 1
```
Expected: splash → hub (Home); tabs, waves, drawer (all six items + name), Home Full Quiz, القائمة BoomMenu topics + True/False, and back-to-exit all behave exactly as before the migration.

- [ ] **Step 6: Commit**

```bash
git add -A
git commit -m "chore: delete legacy shell (splash/hub/drawer) sources and drop AwesomeSplash"
```

---

## Self-Review

**Spec coverage** (against `2026-07-28-compose-migration-shell-design.md`):
- §4.1 package layout → Tasks 2–7 create every listed file (`ShellDestinations`, `DrawerContent`, `ShellScreen`, `ShellActivity`, `SplashActivity`; `ShellActions.kt` added for the ported share/dispatch logic §4.4).
- §4.2 splash → Task 7. §4.3 hub → Tasks 5–6. §4.4 drawer + shareApk → Tasks 3–4. §4.5 data-driven defs → Task 2. §4.6 deps → Task 1 (add) + Task 9 (drop AwesomeSplash). §4.7 manifest → Tasks 6–8. §4.8 deletions → Task 9.
- Testing strategy §7: unit coverage of `ShellDestinations` = Task 2; manual emulator smoke = Tasks 6–9 smoke steps. (Best-effort Compose UI tests from §7 are intentionally not scripted as blocking tasks — `AndroidFragment` hosting makes them flaky; the manual gate is the real check, as the spec allows.)

**Placeholder scan:** none — every code step carries full source; every command is concrete.

**Type consistency:** `waveFor`/`SHELL_TABS`/`DEFAULT_TAB_INDEX`/`DRAWER_ITEMS`/`DrawerItem`/`DrawerAction` defined in Task 2 are used with the same signatures in Tasks 4–5. `handleDrawerAction(Activity, DrawerAction)` and `shareApk(Activity)` from Task 3 are called in Task 4/5 as declared. `ShellScreen(Activity, String, () -> Unit)` from Task 5 is called with matching args in Task 6. `ShellActivity` referenced in Task 7 exists from Task 6.

**Ordering / buildability:** each task leaves the tree compiling. The manifest launcher stays valid at all times (old launcher removed only in Task 8, after Splash is registered in Task 7; deleted classes' manifest entries removed in Task 8 before the class files are deleted in Task 9).
