package com.Elkood.ling_en4.EN4.Header_Elements;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.Elkood.ling_en4.EN4.MenuListFragment;
import com.Elkood.ling_en4.R;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class Settings extends AppCompatActivity {
    private EditText name;
    private SharedPreferences.Editor editor;
    private Button btnsave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        SharedPreferences preferences = getSharedPreferences("saveData", MODE_PRIVATE);
        editor = preferences.edit();
        RadioButton r1 = findViewById(R.id.r1);
        RadioButton r2 = findViewById(R.id.r2);
        RadioButton r3 = findViewById(R.id.r3);
        RadioGroup group1 = findViewById(R.id.Group);

        ((RadioButton) group1.getChildAt(preferences.getInt("saveR", 1))).setChecked(true);


        group1.setOnCheckedChangeListener((group, checkedId) -> {
            checkedId = group.getCheckedRadioButtonId();
            RadioButton genderchoosed = findViewById(checkedId);
            String gender = genderchoosed.getText().toString();
            Toast.makeText(this, gender, Toast.LENGTH_SHORT).show();
            switch (gender) {
                case "30":
                    editor.putInt("saveR", 0).apply();

                    break;
                case "40":
                    editor.putInt("saveR", 1).apply();

                    break;
                case "50":
                    editor.putInt("saveR", 2).apply();
                    break;
            }
        });
        Switch aSwitch = findViewById(R.id.switch1);
        if (preferences.getBoolean("switch1", true)) {
            aSwitch.setChecked(true);
        } else {
            aSwitch.setChecked(false);
        }
        aSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                editor.putBoolean("switch1", true);
                editor.apply();
            } else {
                editor.putBoolean("switch1", false);
                editor.apply();
            }

        });
        if (aSwitch.isChecked()) {
            editor.putBoolean("switch1", true);
            editor.apply();
        } else {
            editor.putBoolean("switch1", false);
            editor.apply();

        }

        name = findViewById(R.id.name_settings);
        btnsave = findViewById(R.id.saveBtn);


        name.setText(preferences.getString("Name", "No Name"));


        ImageView imageView = findViewById(R.id.edit_name);

        imageView.setOnClickListener(v -> {

            if (preferences.getBoolean("changeName", true)) {
                SweetAlertDialog dialog = new SweetAlertDialog(getWindow().getContext(), SweetAlertDialog.ERROR_TYPE);
                dialog.setTitleText("ملاحظة :  \n  يرجى الإنتباه لن تستطيع تعديل اسمك بعد القيام  بحفظه  ")
                        .setConfirmText("تم")
                        .setConfirmClickListener(sweetAlertDialog -> dialog.dismiss()).show();
                dialog.setCancelable(false);
                if (!name.isEnabled()) {
                    name.setEnabled(true);
                    btnsave.setVisibility(View.VISIBLE);
                } else {
                    name.setEnabled(false);
                    btnsave.setVisibility(View.GONE);
                }

            } else {
                Toast.makeText(this, "لقد غيرت اسمك بلفعل ", Toast.LENGTH_LONG).show();
            }
        });
        btnsave.setOnClickListener(v -> {

            if (preferences.getBoolean("changeName", true)) {
                editor.putBoolean("changeName", false);
                editor.apply();

                editor.putString("Name", name.getText().toString());
                editor.apply();
                btnsave.setVisibility(View.GONE);
                name.setEnabled(false);
                MenuListFragment.setName();
            }
        });
        findViewById(R.id.back).setOnClickListener(v -> {
            super.onBackPressed();
        });

    }

}
