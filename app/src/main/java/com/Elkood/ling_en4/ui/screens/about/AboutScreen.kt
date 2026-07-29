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
    onOpenLicenses: () -> Unit,
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
                LinkRow(text = "الشهادات", onClick = onOpenLicenses)
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
