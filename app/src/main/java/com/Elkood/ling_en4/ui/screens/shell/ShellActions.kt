package com.Elkood.ling_en4.ui.screens.shell

import android.app.Activity
import android.content.Intent
import androidx.core.content.FileProvider
import com.Elkood.ling_en4.R
import com.Elkood.ling_en4.ui.screens.about.AboutActivity
import com.Elkood.ling_en4.ui.screens.eng3.Eng3Activity
import com.Elkood.ling_en4.ui.screens.settings.SettingsActivity
import com.Elkood.ling_en4.ui.screens.statistics.StatisticsActivity
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

fun handleDrawerAction(activity: Activity, action: DrawerAction) {
    when (action) {
        DrawerAction.SCORES -> openScreen(activity, StatisticsActivity::class.java)
        DrawerAction.HELP -> Unit // inert today — parity with legacy (no handler)
        DrawerAction.SHARE -> shareApk(activity)
        DrawerAction.SETTINGS -> openScreen(activity, SettingsActivity::class.java)
        DrawerAction.ABOUT -> openScreen(activity, AboutActivity::class.java)
        DrawerAction.ENG3 -> openScreen(activity, Eng3Activity::class.java)
    }
}

private fun openScreen(activity: Activity, target: Class<*>) {
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
