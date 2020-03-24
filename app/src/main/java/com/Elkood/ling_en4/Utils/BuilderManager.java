package com.Elkood.ling_en4.Utils;



import android.graphics.Color;
import android.graphics.Typeface;

import com.Elkood.ling_en4.R;
import com.nightonke.boommenu.BoomButtons.HamButton;
import com.nightonke.boommenu.Util;

import androidx.appcompat.app.AppCompatActivity;


public class BuilderManager extends AppCompatActivity {
    public static HamButton.Builder getHamButtonBuilder(String text, String subText) {


        return new HamButton.Builder()
                .normalText(text).subTypeface(Typeface.SERIF).typeface(Typeface.SERIF)
                .subNormalText(subText).rippleEffect(true).normalColorRes(R.color.color4)
                .highlightedColor(Color.BLUE).highlightedColorRes(R.color.blue)
                .unableColor(Color.BLACK).unableColorRes(R.color.black)
                .pieceColor(Color.WHITE).pieceColorRes(R.color.white).buttonHeight(Util.dp2px(60));
//                .normalColor(0xEC8F52);

    }

    private static BuilderManager ourInstance = new BuilderManager();

    public BuilderManager() {
    }


}
