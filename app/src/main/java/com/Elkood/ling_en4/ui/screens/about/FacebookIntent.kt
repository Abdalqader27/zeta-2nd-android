package com.Elkood.ling_en4.ui.screens.about

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri

/**
 * Mirrors the legacy About.newFacebookIntent: if the Facebook app is installed and enabled,
 * open the fb:// deep link; otherwise fall back to the plain web URL.
 */
fun newFacebookIntent(pm: PackageManager, url: String): Intent {
    var uri = Uri.parse(url)
    try {
        val applicationInfo = pm.getApplicationInfo("com.facebook.katana", 0)
        if (applicationInfo.enabled) {
            uri = Uri.parse("fb://facewebmodal/f?href=$url")
        }
    } catch (ignored: PackageManager.NameNotFoundException) {
    }
    return Intent(Intent.ACTION_VIEW, uri)
}
