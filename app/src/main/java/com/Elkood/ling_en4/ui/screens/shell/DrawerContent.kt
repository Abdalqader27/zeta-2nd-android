package com.Elkood.ling_en4.ui.screens.shell

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
