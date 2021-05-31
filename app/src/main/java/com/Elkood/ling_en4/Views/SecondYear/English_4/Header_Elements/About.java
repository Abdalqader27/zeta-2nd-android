package com.Elkood.ling_en4.Views.SecondYear.English_4.Header_Elements;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.Elkood.ling_en4.R;

public class About extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        TextView textView=findViewById(R.id.percy);
        textView.setOnClickListener(v -> {
            String url = "https://www.freeprivacypolicy.com/privacy/view/25844dc6eb5275b8022df849a23f1ca6";
            Intent intent1 = new Intent(Intent.ACTION_VIEW);
            intent1.setData(Uri.parse(url));
            startActivity(intent1);

        });
        ImageView BACK=findViewById(R.id.back);
        BACK.setOnClickListener(v -> About.super.onBackPressed());
        ImageView image = findViewById(R.id.image_news_scrolling);
        image.setOnClickListener(v -> {

        });
        ImageView telegram = findViewById(R.id.telegram);
        telegram.setOnClickListener(v->{
            Intent intent=new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://t.me/AbdalqaderNajjaR"));
            v.getContext().startActivity(intent);


        });
        ImageView faceBook = findViewById(R.id.facebook);
        faceBook.setOnClickListener(v -> {
            try{
                String url="https://www.facebook.com/abdalqader.najjar.9/";
                startActivity( newFacebookIntent(getPackageManager(),url))   ;}
            catch (Exception e){
                Toast.makeText(v.getContext(), "No FaceBook App", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void member(View view) {
        Intent intent = new Intent(getApplicationContext(), com.Elkood.ling_en4.Views.SecondYear.English_4.Header_Elements.study_member.class);
        startActivity(intent);
    }

    public void licence(View view) {
        Intent intent = new Intent(getApplicationContext(), com.Elkood.ling_en4.Views.SecondYear.English_4.Header_Elements.licenses.class);
        startActivity(intent);
    }
    private static Intent newFacebookIntent(PackageManager pm, String url) {
        Uri uri = Uri.parse(url);
        try {
            ApplicationInfo applicationInfo = pm.getApplicationInfo("com.facebook.katana", 0);
            if (applicationInfo.enabled) {
                // http://stackoverflow.com/a/24547437/1048340
                uri = Uri.parse("fb://facewebmodal/f?href=" + url);
            }
        } catch (PackageManager.NameNotFoundException ignored) {
        }
        return new Intent(Intent.ACTION_VIEW, uri);
    }
}
