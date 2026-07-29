package com.Elkood.ling_en4.ui.screens.about

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.Elkood.ling_en4.ui.screens.licenses.LicensesActivity
import com.Elkood.ling_en4.ui.screens.member.StudyMemberActivity
import com.Elkood.ling_en4.ui.theme.ZetaTheme

class AboutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ZetaTheme {
                AboutScreen(
                    onOpenUrl = { url ->
                        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
                    },
                    onOpenFacebook = {
                        try {
                            startActivity(newFacebookIntent(packageManager, ABOUT_FACEBOOK_URL))
                        } catch (e: ActivityNotFoundException) {
                            Toast.makeText(this, "No FaceBook App", Toast.LENGTH_SHORT).show()
                        }
                    },
                    onOpenMember = { startActivity(Intent(this, StudyMemberActivity::class.java)) },
                    onOpenLicenses = { startActivity(Intent(this, LicensesActivity::class.java)) },
                    onBack = { onBackPressedDispatcher.onBackPressed() },
                )
            }
        }
    }
}
