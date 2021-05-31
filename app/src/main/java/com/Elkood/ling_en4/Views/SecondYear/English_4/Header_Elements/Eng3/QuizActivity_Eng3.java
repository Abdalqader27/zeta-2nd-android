package com.Elkood.ling_en4.Views.SecondYear.English_4.Header_Elements.Eng3;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.Elkood.ling_en4.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class QuizActivity_Eng3 extends AppCompatActivity {

    public static final String EXTRA_SCORE = "extrascore";
    private static final String KEY_SCORE = "keyscore";
    private static final String KEY_QUESTION_COUNT = "keyQustionCount";
    private static final String KEY_Mills_Left = "keymilsLeft";
    private static final String KEY_ANSWERD = "keyanswerd";
    private static final String KEY_QustionList = "keyQustionList";

    private static final long COUNTDOWN_IN_Mills = 40000;
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
    private ArrayList<Qutions> qutionsList;

    private ColorStateList textColorRb;
    private ColorStateList textColorcd;


    private CountDownTimer countDownTimer;
    private long TimeleftMills;

    private int questCounter;
    private int questiontTotalCounter;
    private Qutions currentQuestion;
    private int score;
    private boolean answerd;

    private long backpressedTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_eng3);
        textviewQustion = findViewById(R.id.qution_eng3);
        textViewscore = findViewById(R.id.text_View_score_eng3);
        textViewQustionCount = findViewById(R.id.text_View_count_question_eng3);
        textViewCountDown = findViewById(R.id.countDown_eng3);
        RbGroup = findViewById(R.id.Radio_Group_eng3);
        R1 = findViewById(R.id.radio_button1_eng3);
        R2 = findViewById(R.id.radio_button2_eng3);
        R3 = findViewById(R.id.radio_button3_eng3);
        R4 = findViewById(R.id.radio_button4_eng3);

        ConfirmNext = findViewById(R.id.confirm_next_eng3);

        textColorRb = R1.getTextColors();
        textColorcd = textViewCountDown.getTextColors();
        if (savedInstanceState == null) {
            QuizDbHelper_Eng3 DbHelper = new QuizDbHelper_Eng3(     getApplicationContext());
            qutionsList = DbHelper.getAllQustion();

            questiontTotalCounter = qutionsList.size();
            Collections.shuffle(qutionsList);

            ShowNextQustion();
        } else {
            qutionsList = savedInstanceState.getParcelableArrayList(KEY_QustionList);
            questiontTotalCounter = Objects.requireNonNull(qutionsList).size();
            questCounter = savedInstanceState.getInt(KEY_QUESTION_COUNT);
            currentQuestion = qutionsList.get(questCounter - 1);
            score = savedInstanceState.getInt(KEY_SCORE);
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
                ShowToast();}

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
            if (currentQuestion.getOption1().equals("")) {
                R1.setVisibility(View.GONE);
            } else {
                R1.setVisibility(View.VISIBLE);
            }
            if (currentQuestion.getOption2().equals("")) {
                R2.setVisibility(View.GONE);
            } else {
                R2.setVisibility(View.VISIBLE);
            }

            if (currentQuestion.getOption3().equals("")) {
                R3.setVisibility(View.GONE);
            } else {
                R3.setVisibility(View.VISIBLE);
            }

            if (currentQuestion.getOption4().equals("")) {
                R4.setVisibility(View.GONE);
            } else {
                R4.setVisibility(View.VISIBLE);
            }

            R1.setText(currentQuestion.getOption1());
            R2.setText(currentQuestion.getOption2());
            R3.setText(currentQuestion.getOption3());
            R4.setText(currentQuestion.getOption4());

            questCounter++;
            textViewQustionCount.setText("Question :" + questCounter + " / " + questiontTotalCounter);
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
        RadioButton rbselect = findViewById(RbGroup.getCheckedRadioButtonId());
        int answerNr = RbGroup.indexOfChild(rbselect) + 1;

        if (answerNr == currentQuestion.getAnswer()) {
            score++;
            SoundPlayer.player = MediaPlayer.create(getApplicationContext(), R.raw.correct);
            SoundPlayer.player.start();
            textViewscore.setText("Score :" + score);

        } else {
            SoundPlayer.player = MediaPlayer.create(getApplicationContext(), R.raw.wrong);
            SoundPlayer.player.start();
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
                R1.setTextColor(Color.GREEN);
                //      textviewQustion.setText("Answer 1 is corect");
                break;
            case 2:
                R2.setTextColor(Color.GREEN);
                //  textviewQustion.setText("Answer 2 is corect");
                break;
            case 3:
                R3.setTextColor(Color.GREEN);
                //       textviewQustion.setText("Answer 3 is corect");
                break;
            case 4:
                R4.setTextColor(Color.GREEN);
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
        Intent resultIntrent = new Intent();
        resultIntrent.putExtra(EXTRA_SCORE, score);
        setResult(RESULT_OK, resultIntrent);
        finish();
    }

    @Override
    public void onBackPressed() {


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
        outState.putInt(KEY_SCORE, score);
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
