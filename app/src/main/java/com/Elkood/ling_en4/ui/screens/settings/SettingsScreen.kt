package com.Elkood.ling_en4.ui.screens.settings

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import com.Elkood.ling_en4.R

/**
 * Stateless settings UI. State + persistence live in SettingsActivity.
 *
 * @param showWarning whether the one-time name-edit warning dialog is visible.
 */
@Composable
fun SettingsScreen(
    name: String,
    onNameChange: (String) -> Unit,
    nameEnabled: Boolean,
    saveVisible: Boolean,
    selectedDuration: Int,
    onDurationSelected: (Int) -> Unit,
    soundOn: Boolean,
    onSoundChange: (Boolean) -> Unit,
    onEditNameClick: () -> Unit,
    onSaveClick: () -> Unit,
    showWarning: Boolean,
    onDismissWarning: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        Image(
            painter = painterResource(R.drawable.ic_settings_2),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp),
        )

        // Quiz duration radio group
        Text(
            text = "وقت السؤال",
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            textAlign = TextAlign.Center,
        )
        DURATION_OPTIONS.forEachIndexed { index, label ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .selectable(selected = selectedDuration == index, onClick = { onDurationSelected(index) })
                    .padding(vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                RadioButton(selected = selectedDuration == index, onClick = { onDurationSelected(index) })
                Text(text = label, modifier = Modifier.padding(start = 8.dp))
            }
        }

        // Sound switch
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(text = "تأثيرات الصوت ", modifier = Modifier.weight(1f))
            Switch(checked = soundOn, onCheckedChange = onSoundChange)
        }

        // Name field + edit icon
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            OutlinedTextField(
                value = name,
                onValueChange = onNameChange,
                enabled = nameEnabled,
                singleLine = true,
                modifier = Modifier.weight(1f),
            )
            IconButton(onClick = onEditNameClick) {
                Icon(
                    painter = painterResource(R.drawable.ic_edit),
                    contentDescription = "edit name",
                )
            }
        }

        if (saveVisible) {
            TextButton(onClick = onSaveClick, modifier = Modifier.padding(top = 8.dp)) {
                Text(text = "حفظ")
            }
        }
    }

    if (showWarning) {
        AlertDialog(
            onDismissRequest = onDismissWarning,
            properties = DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false),
            title = { Text("ملاحظة :  \n  يرجى الإنتباه لن تستطيع تعديل اسمك بعد القيام  بحفظه  ") },
            confirmButton = {
                TextButton(onClick = onDismissWarning) { Text("تم") }
            },
        )
    }
}
