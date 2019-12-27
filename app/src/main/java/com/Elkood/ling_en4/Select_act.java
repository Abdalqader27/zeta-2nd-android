package com.Elkood.ling_en4;

import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.Elkood.ling_en4.EN1.MainEn1;

import stream.customalert.CustomAlertDialogue;

public class Select_act extends AppCompatActivity {
    ViewGroup en4,En1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_act);
        InitUI();
    }

    public void InitUI() {
        en4 = findViewById(R.id.en4);
        en4.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP));
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        });
        En1=findViewById(R.id.En1);
        En1.setOnClickListener(v->{
            startActivity(new Intent(getApplicationContext(), MainEn1.class).setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP));
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        });
    }

    @Override
    public void onBackPressed() {
        try {

            CustomAlertDialogue.Builder alert = new CustomAlertDialogue.Builder(Select_act.this)
                    .setStyle(CustomAlertDialogue.Style.DIALOGUE)
                    .setCancelable(false)
                    .setTitle("تنبيه!!  ")
                    .setMessage("هل حقا تريد  الخروج ؟")
                    .setPositiveText("نعم ")
                    .setPositiveColor(R.color.negative)
                    .setOnPositiveClicked((view12, dialog) -> {
                        finishAffinity();
                        dialog.dismiss();
                    })
                    .setNegativeText("لا ")
                    .setNegativeColor(R.color.positive)
                    .setOnNegativeClicked((view1, dialog) -> dialog.dismiss())
                    .setDecorView(Select_act.this.getWindow().getDecorView())
                    .build();
            alert.show();

        } catch (Exception e) {
            CustomAlertDialogue.Builder alert = new CustomAlertDialogue.Builder(Select_act.this)
                    .setStyle(CustomAlertDialogue.Style.DIALOGUE)
                    .setCancelable(false)
                    .setTitle("تنبيه!!  ")
                    .setMessage("هل حقا تريد  الخروج ؟")
                    .setPositiveText("نعم ")
                    .setPositiveColor(R.color.negative)
                    .setOnPositiveClicked((view12, dialog) -> {
                        super.onBackPressed();

                        dialog.dismiss();
                    })
                    .setNegativeText("لا ")
                    .setNegativeColor(R.color.positive)
                    .setOnNegativeClicked((view1, dialog) -> dialog.dismiss())
                    .setDecorView(Select_act.this.getWindow().getDecorView())
                    .build();
            alert.show();
        }


    }
}
