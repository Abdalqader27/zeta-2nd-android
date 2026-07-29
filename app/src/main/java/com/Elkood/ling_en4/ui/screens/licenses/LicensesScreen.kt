package com.Elkood.ling_en4.ui.screens.licenses

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.Elkood.ling_en4.R

// Paste verbatim from activity_licenses.xml body TextView. Leading blank line intentional.
private val LICENSES_TEXT: String = """
DotLoadersPack-Android
Copyright 2017 Suneet AgrawalBottomNavigationViewEx
        MIT License
Copyright (c) 2017 ittianyu

AndroidExpandingViewLibrary
 Copyright (c) 2016, Diego Bezerra diego.bezerra@gmail.com

BlurPopupWindow
Licensed under the Apache License, Version 2.0 (the  Licence);

SmartTabLayout
Copyright (C) 2015 ogaclejapan
Copyright (C) 2013 The Android Open Source Project

Timeline-View
Copyright 2018 Vipul Asri

Licensed under the Apache License, Version 2.0 (the  Licence);


Licensed under the Apache License, Version 2.0 (the  Licence);

MaterialSearchView
Copyright 2015 Miguel Catalan Bañuls

Licensed under the Apache License, Version 2.0 (the  Licence);


picasso-transformations
Copyright 2018 Wasabeef

Licensed under the Apache License, Version 2.0 (the  Licence);



zoomage
Copyright 2016 Jeffrey Sibbold

Licensed under the Apache License, Version 2.0 (the  Licence);


MaterialSearchView
Copyright 2015 Miguel Catalan Bañuls

Licensed under the Apache License, Version 2.0 (the  Licence);


lottie-android
Lottie for Android

easypermissions
Copyright 2017 Google

Licensed under the Apache License, Version 2.0 (the  Licence);


CounterFab
opyright 2016 André Mion

Licensed under the Apache License, Version 2.0 (the  Licence);



Instabug-Android


MaterialTapTargetPrompt
Copyright (C) 2016-2018 Samuel Wall

Licensed under the Apache License, Version 2.0 (the  Licence);"""

@Composable
fun LicensesScreen() {
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
        ) {
            Text(
                text = "licenses",
                fontSize = 30.sp,
                modifier = Modifier.padding(10.dp),
            )
            Text(
                text = LICENSES_TEXT,
                fontSize = 20.sp,
                lineHeight = 30.sp, // ~1.5x of 20sp
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .background(colorResource(R.color.grey_Light))
                    .padding(10.dp),
            )
        }
    }
}
