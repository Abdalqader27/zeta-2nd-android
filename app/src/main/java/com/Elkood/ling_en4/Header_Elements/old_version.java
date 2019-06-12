package com.Elkood.ling_en4.Header_Elements;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.Elkood.ling_en4.Header_Elements.Eng3.QuizActivity_Eng3;
import com.Elkood.ling_en4.R;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class old_version extends AppCompatActivity {
    private static final int REQUST_CODE_QUIZ = 1;
    private static final String SHARED_PREF = "sharedprefs";
    private static final String KEY_HIGHSCORE = "keyhighscore";
    private TextView textViewHighScore;
    private int highScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_old_version);
        Button button = findViewById(R.id.eng3_quiz);
        textViewHighScore = findViewById(R.id.highScore);

        button.setOnClickListener(v -> StartQuiz());
        loadScore();

    }

    private void StartQuiz() {
        Intent intent = new Intent(getApplicationContext(), QuizActivity_Eng3.class);
        startActivityForResult(intent, REQUST_CODE_QUIZ);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUST_CODE_QUIZ) {
            if (resultCode == RESULT_OK) {
                int score = data.getIntExtra(QuizActivity_Eng3.EXTRA_SCORE, 0);
                if (score > highScore) {
                    updateHighScore(score);

                }

            }

        }
    }

    private void loadScore() {

        SharedPreferences preferences = getSharedPreferences(SHARED_PREF, MODE_PRIVATE);
        highScore = preferences.getInt(KEY_HIGHSCORE, 0);
        textViewHighScore.setText("High Score :" + highScore);
    }

    private void updateHighScore(int highScorenew) {

        highScore = highScorenew;
        textViewHighScore.setText("High Score :" + highScore);
        SharedPreferences preferences = getSharedPreferences(SHARED_PREF, MODE_PRIVATE);
        SharedPreferences.Editor edito = preferences.edit();
        edito.putInt(KEY_HIGHSCORE, highScore);
        edito.apply();
    }


    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(old_version.this);
        builder.setMessage("Do You Want To Close Your App ?");
        builder.setTitle("Quiz");
        builder.setCancelable(true);
        builder.setNegativeButton("No", (dialog, i) -> dialog.cancel());
        builder.setPositiveButton("Yes", (dialog, i) -> finish());
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

}
