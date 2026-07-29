package com.Elkood.ling_en4.ui.screens.shell

import android.app.Activity
import android.content.Intent
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
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.fragment.compose.AndroidFragment
import com.Elkood.ling_en4.R
import com.Elkood.ling_en4.Views.SecondYear.English_4.BankItemsQuiz
import com.Elkood.ling_en4.data.model.QuizTopic
import com.Elkood.ling_en4.ui.screens.home.HomeScreen
import com.Elkood.ling_en4.ui.screens.quiz.ComposeQuizActivity
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
                            beyondViewportPageCount = SHELL_TABS.size - 1,
                            modifier = Modifier.fillMaxSize(),
                        ) { page ->
                            CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
                                when (page) {
                                    0 -> com.Elkood.ling_en4.ui.screens.paperquiz.PaperListScreen(
                                        onPaperClick = { com.Elkood.ling_en4.ui.screens.paperquiz.PaperQuizActivity.start(activity, it) }
                                    )
                                    1 -> AndroidFragment<BankItemsQuiz>(Modifier.fillMaxSize())
                                    else -> HomeScreen(
                                        onStartExam = {
                                            val intent = Intent(
                                                activity,
                                                ComposeQuizActivity::class.java,
                                            ).setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
                                            intent.putExtra(
                                                ComposeQuizActivity.EXTRA_TOPIC,
                                                QuizTopic.FULL_QUIZ.name,
                                            )
                                            activity.startActivity(intent)
                                        },
                                        modifier = Modifier.fillMaxSize(),
                                    )
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
