package com.Elkood.ling_en4.Views.Sgin;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.Elkood.ling_en4.NewMain2;
import com.Elkood.ling_en4.R;
import com.parse.Parse;
import com.parse.ParseObject;
import com.xw.repo.widget.BounceScrollView;

import java.util.Objects;

//import com.Elkood.ling_en4.NewMain;

//import com.Elkood.ling_en4.NewMain;

/**
 * A login screen that offers login via email/password.
 */public class LoginActivity extends AppCompatActivity {
    private Button sign;
    private EditText theName_signin;
    private EditText number;
    private CheckBox checkbox;
    private SharedPreferences.Editor editor;
    private LinearLayout loginlinear;
    private BounceScrollView bounceScrollView;

    @Override

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        SharedPreferences preferences = getSharedPreferences("saveData", MODE_PRIVATE);
        editor = preferences.edit();
        if (preferences.getBoolean("checkbox", false)) {
//            startActivity(new Intent(this, NewMain.class));

//            Intent intent = new Intent(getApplicationContext(), Main.class);
            Intent intent = new Intent(getApplicationContext(), NewMain2.class);
            startActivity(intent);
            finish();
        }
        theName_signin = findViewById(R.id.theName_signin);
        loginlinear = findViewById(R.id.linearlogin);
        bounceScrollView = findViewById(R.id.loginBoucingScroll);
        number = findViewById(R.id.number);
        checkbox = findViewById(R.id.checkbox);

        checkbox.setChecked(true);
        sign = findViewById(R.id.sgin);
        theName_signin.setText("");

        number.setText("");
        sign.setOnClickListener(v -> {
            if (sign.getText().toString().equals("") || number.getText().toString().equals("")) {
                Toast.makeText(this, "يرجى ملئ البيانات التالية ", Toast.LENGTH_SHORT).show();
            } else {
                if (isNetworkAvailable()) {

                    if (checkbox.isChecked()) {
                        editor.putBoolean("checkbox", true);
                        editor.apply();
                    }
                    editor.putString("Name", theName_signin.getText().toString());
                    editor.putString("theNumber", number.getText().toString());
                    editor.apply();
                    try {
                        Parse.initialize(new Parse.Configuration.Builder(this)
                                .applicationId(getString(R.string.back4app_app_id))
                                // if defined
                                .clientKey(getString(R.string.back4app_client_key))
                                .server(getString(R.string.back4app_server_url))
                                .build()
                        );
                        final ParseObject myNewObject = new ParseObject("Quiz");
                        myNewObject.put("Username", Objects.requireNonNull(preferences.getString("Name", "NoName")));
                        myNewObject.put("Highscore", 0);
                        myNewObject.saveInBackground(e -> {
                            SharedPreferences sharedPreferences = getSharedPreferences("SaveFile", MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("objectID", myNewObject.getObjectId());
                            editor.commit();
                        });
                    } catch (Exception e) {
                        Toast.makeText(this, e + "", Toast.LENGTH_SHORT).show();
                    }
                    bounceScrollView.setVisibility(View.GONE);
                    loginlinear.setVisibility(View.VISIBLE);
                    Handler handler = new Handler();
                    handler.postDelayed(
                            () -> {
                                Intent intent = new Intent(getApplicationContext(), NewMain2.class);
                                startActivity(intent);
                            }, 2000L);

                } else {
                    Toast.makeText(this, "تـأكد من اتصالك بلانترتت ؟؟؟ ", Toast.LENGTH_LONG).show();
                }
            }


        });
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();

    }
}


