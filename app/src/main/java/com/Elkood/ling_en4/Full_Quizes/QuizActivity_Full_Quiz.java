package com.Elkood.ling_en4.Full_Quizes;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.Elkood.ling_en4.Important_quiz.Vocabulary.SoundPlayer;
import com.Elkood.ling_en4.R;
import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class QuizActivity_Full_Quiz extends AppCompatActivity {


    private static final String KEY_QUESTION_COUNT = "keyQustionCount";
    private static final String KEY_Mills_Left = "keymilsLeft";
    private static final String KEY_ANSWERD = "keyanswerd";
    private static final String KEY_QustionList = "keyQustionList";

    private static long COUNTDOWN_IN_Mills;
    private TextView textviewQustion;
    private TextView textViewscore;
    private TextView textViewQustionCount;
    private TextView textViewCountDown;
    private RadioGroup RbGroup;
    private RadioButton R1;
    private RadioButton R2;
    private RadioButton R3;
    private RadioButton R4;

    private Button ConfirmNext;
    private ArrayList<Qutions_Full> qutionsList;

    private ColorStateList textColorRb;
    private ColorStateList textColorcd;


    private CountDownTimer countDownTimer;
    private long TimeleftMills;

    private int questCounter;
    private int questiontTotalCounter;
    private Qutions_Full currentQuestion;
    private int score;
    private boolean answerd;

    private long backpressedTime;
    private SharedPreferences preferences;
    private SharedPreferences ScorePrefrence;
    private SharedPreferences.Editor editerScore;
    private ProgressBar bar;
    private static int tempBar = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_full);

        bar = findViewById(R.id.progressbarfull);
        ScorePrefrence = getSharedPreferences("SaveScore", MODE_PRIVATE);
        editerScore = ScorePrefrence.edit();
        editerScore.putInt("numberPlay", ScorePrefrence.getInt("numberPlay", 0) + 1);
        editerScore.apply();
        textviewQustion = findViewById(R.id.qution_full);
        textViewscore = findViewById(R.id.text_View_score_full);
        textViewQustionCount = findViewById(R.id.text_View_count_question_full);
        textViewCountDown = findViewById(R.id.countDown_full);
        RbGroup = findViewById(R.id.Radio_Group_full);
        R1 = findViewById(R.id.radio_button1_full);
        R2 = findViewById(R.id.radio_button2_full);
        R3 = findViewById(R.id.radio_button3_full);
        R4 = findViewById(R.id.radio_button4_full);

        ConfirmNext = findViewById(R.id.confirm_next_full);
        preferences = getSharedPreferences("saveData", MODE_PRIVATE);
        if (preferences.getInt("saveR", 1) == 1) {
            COUNTDOWN_IN_Mills = 40000;
        } else if (preferences.getInt("saveR", 1) == 0) {
            COUNTDOWN_IN_Mills = 30000;

        } else if (preferences.getInt("saveR", 1) == 2) {
            COUNTDOWN_IN_Mills = 50000;

        }
        textColorRb = R1.getTextColors();
        textColorcd = textViewCountDown.getTextColors();
        if (savedInstanceState == null) {
            QuizDbHelper_Full_Quiz DbHelper = new QuizDbHelper_Full_Quiz(getApplicationContext());
            qutionsList = DbHelper.getAllQustion();

            questiontTotalCounter = qutionsList.size();
            Collections.shuffle(qutionsList);

            ShowNextQustion();
        } else {
            qutionsList = savedInstanceState.getParcelableArrayList(KEY_QustionList);
            questiontTotalCounter = Objects.requireNonNull(qutionsList).size();
            questCounter = savedInstanceState.getInt(KEY_QUESTION_COUNT);
            currentQuestion = qutionsList.get(questCounter - 1);
            TimeleftMills = savedInstanceState.getLong(KEY_Mills_Left);
            answerd = savedInstanceState.getBoolean(KEY_ANSWERD);

            if (!answerd) {
                startCountDown();
            } else {

                UpdateCountDownText();
                showSolution();
            }

        }
        ConfirmNext.setOnClickListener(v -> {
            if (SoundPlayer.player != null) {
                SoundPlayer.player.release();
            }
            if (!answerd) {
                if (R1.isChecked() || R2.isChecked() || R3.isChecked() || R4.isChecked()) {
                    CheckAnswer();
                } else {
/*
                    Toast.makeText(getApplicationContext(), "Please Select an answer", Toast.LENGTH_SHORT).show();
             */
                    ShowToast();
                }

            } else {
                ShowNextQustion();
            }
        });

    }

    private void ShowNextQustion() {
        R1.setTextColor(textColorRb);
        R2.setTextColor(textColorRb);
        R3.setTextColor(textColorRb);
        R4.setTextColor(textColorRb);

        RbGroup.clearCheck();

        if (questCounter < questiontTotalCounter) {

            currentQuestion = qutionsList.get(questCounter);
            textviewQustion.setText(currentQuestion.getQution());
            R1.setText(currentQuestion.getOption1());
            R2.setText(currentQuestion.getOption2());
            R3.setText(currentQuestion.getOption3());
            R4.setText(currentQuestion.getOption4());

            questCounter++;
            textViewQustionCount.setText(questCounter + " / " + questiontTotalCounter);
            answerd = false;
            ConfirmNext.setText("Confirm");

            TimeleftMills = COUNTDOWN_IN_Mills;

            startCountDown();
        } else {
            finishQuiz();
        }


    }

    private void startCountDown() {
        countDownTimer = new CountDownTimer(TimeleftMills, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                TimeleftMills = millisUntilFinished;
                UpdateCountDownText();

            }

            @Override
            public void onFinish() {
                TimeleftMills = 0;
                UpdateCountDownText();
                CheckAnswer();

            }
        }.start();


    }

    private void UpdateCountDownText() {
        int minutes = (int) (TimeleftMills / 1000) / 60;
        int secound = (int) (TimeleftMills / 1000) % 60;

        String TimeForamted = String.format(Locale.ENGLISH, "%02d:%02d", minutes, secound);
        textViewCountDown.setText(TimeForamted);
        if (TimeleftMills < 15000) {

            textViewCountDown.setTextColor(Color.RED);
        } else {
            textViewCountDown.setTextColor(textColorcd);
        }


    }

    private void CheckAnswer() {
        answerd = true;
        countDownTimer.cancel();
        int answerNr = 0;
        if (R1.isChecked()) answerNr = 1;
        if (R2.isChecked()) answerNr = 2;
        if (R3.isChecked()) answerNr = 3;
        if (R4.isChecked()) answerNr = 4;

        if (answerNr == currentQuestion.getAnswer()) {

            score++;
            tempBar++;
            if(tempBar==10){
                Toast.makeText(this, "أحسنت 10 إجابات صحيحة متتالية ", Toast.LENGTH_SHORT).show();
            }
            bar.setProgress(tempBar);
            if (SoundPlayer.player != null)
                SoundPlayer.player.release();
            if (preferences.getBoolean("switch1", true)) {
                SoundPlayer.player = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                SoundPlayer.player.start();
            }

            textViewscore.setText("Your Score is  " + String.valueOf(score));


        } else {
            tempBar = 0;
            bar.setProgress(tempBar);
            if (SoundPlayer.player != null)
                SoundPlayer.player.release();
            if (preferences.getBoolean("switch1", true)) {

                SoundPlayer.player = MediaPlayer.create(getApplicationContext(), R.raw.wrong);
                SoundPlayer.player.start();
            }
        }

        showSolution();
    }

    @SuppressLint("ResourceAsColor")
    private void showSolution() {

        R1.setTextColor(Color.RED);
        R2.setTextColor(Color.RED);
        R3.setTextColor(Color.RED);
        R4.setTextColor(Color.RED);

        switch (currentQuestion.getAnswer()) {

            case 1:
                R1.setTextColor(getResources().getColor(R.color.green));
                //      textviewQustion.setText("Answer 1 is corect");
                break;
            case 2:
                R2.setTextColor(getResources().getColor(R.color.green));
                //  textviewQustion.setText("Answer 2 is corect");
                break;
            case 3:
                R3.setTextColor(getResources().getColor(R.color.green));
                //       textviewQustion.setText("Answer 3 is corect");
                break;
            case 4:
                R4.setTextColor(getResources().getColor(R.color.green));
                //     textviewQustion.setText("Answer 4 is corect");
                break;


        }
        if (questCounter < questiontTotalCounter) {
            ConfirmNext.setText("Next");
        } else {

            ConfirmNext.setText("Finish");


        }

    }

    private void finishQuiz() {
        if (ScorePrefrence.getInt("fullscore", 0) < score) {
            editerScore.putInt("fullscore", score);
            editerScore.apply();

//            if (ScorePrefrence.getInt("fullscore", 0) >= 100) {
            try {


                Parse.initialize(new Parse.Configuration.Builder(this)
                        .applicationId(getString(R.string.back4app_app_id))
                        // if defined
                        .clientKey(getString(R.string.back4app_client_key))
                        .server(getString(R.string.back4app_server_url))
                        .build()
                );


                ParseQuery<ParseObject> query = ParseQuery.getQuery("Quiz");
                SharedPreferences sharedPreferences = getSharedPreferences("SaveFile", MODE_PRIVATE);
                String ID = sharedPreferences.getString("objectID", "no");
                query.getInBackground(ID, (entity, e) -> {
                    if (e == null) {
                        // Update the fields we want to
                        entity.put("Highscore", score);
                        if (!Objects.requireNonNull(entity.get("Username")).equals(preferences.getString("Name", "noName")))
                            entity.put("Username", Objects.requireNonNull(preferences.getString("Name", "noName")));
                        entity.saveInBackground();
                    }
                });
            } catch (Exception e) {
                Toast.makeText(this, "" + e, Toast.LENGTH_SHORT).show();
            }

        }

        finish();
    }

    @Override
    public void onBackPressed() {
//        SweetAlertDialog dialog = new SweetAlertDialog(getWindow().getContext(), R.drawable.ic_check_green);
//        dialog.setTitleText(" Hey  \n Your Point is   ( "+"" +score+" )"+
//                "\nThe Wrong Answer is  ( "+ (46-score)+" )")
//                .setConfirmText("Close   ")
//                .setConfirmClickListener(sweetAlertDialog -> finishQuiz()).show();

        if (backpressedTime + 2000 > System.currentTimeMillis()) {
            finishQuiz();


        } else {
            Toast.makeText(getApplicationContext(), "Press Again To Exit", Toast.LENGTH_SHORT).show();
        }
        backpressedTime = System.currentTimeMillis();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {

            countDownTimer.cancel();
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_QUESTION_COUNT, questCounter);
        outState.putLong(KEY_Mills_Left, TimeleftMills);
        outState.putBoolean(KEY_ANSWERD, answerd);
        outState.putParcelableArrayList(KEY_QustionList, qutionsList);
    }

    private void ShowToast() {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom_toast, findViewById(R.id.root_Custom1));
        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CLIP_VERTICAL, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();

    }
}
