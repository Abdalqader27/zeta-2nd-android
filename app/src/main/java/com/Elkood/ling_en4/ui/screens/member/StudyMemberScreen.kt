package com.Elkood.ling_en4.ui.screens.member

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.combinedClickable
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

@OptIn(ExperimentalFoundationApi::class)
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
                .border(0.5.dp, Color(0xFF2B5A83), RoundedCornerShape(8.dp))
                .combinedClickable(onClick = {}, onLongClick = onLongPress),
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
