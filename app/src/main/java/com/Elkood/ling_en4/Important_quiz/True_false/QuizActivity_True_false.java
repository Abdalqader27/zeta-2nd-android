package com.Elkood.ling_en4.Important_quiz.True_false;

import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.Elkood.ling_en4.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class QuizActivity_True_false extends AppCompatActivity {
    private SharedPreferences preferencesScore;
    private SharedPreferences.Editor editeScore;
    private static MediaPlayer player;
    private TextView countLabel;
    private TextView questionLabel;
    private String rightAnswer;
    private int rightAnswerCount = 0;
    private int quizCount = 1;
    private TextView right_point;
    private SharedPreferences preferences;

    private final ArrayList<ArrayList<String>> quizArray = new ArrayList<>();

    private final String[][] quizData = {
            // {"Country", "Right Answer", "Choice1", "Choice2", "Choice3"}
            {"Software from an ASP must be installed locally on a user's computer", "false", "true"},
            {"You need a high bandwidth connection to use an ASP service ", "true", "false"},
            {"ASPs usually use their own storage space for customers", "false", "true"},
            {"Using an ASP gives you more flexibility", "true", "false"},
            {"An e-commerce business usually provides all of the required technology itself", "false", "true"},
            {"JPEG the most common compression system used for video ", "false", "true"},
            {"P-frame only store the changes in the image", "true", "false"},
            {"here is always at least one P-frame between tow I-frame", "true", "false"},
            {"B-frame store the complete picture information", "false", "true"},
            {"P-frame only store the changes in the image", "true", "false"},
            {"There can only be one B-frame between each I and P-frame", "false", "true"},
            {"There are typically about four P-frames between each I-frame", "true", "false"},
            {"Most the work that an application does to prepare a message for sending over a network is not seen by the user", "true", "false"},
            {"ASCII Is always used to transmit a data", "false", "true"},
            {"The encryption layer compressed the message", "true", "false"},
            {"The network layer keeps a copy of each packet until it arrives at the next node undamaged", "false", "true"},
            {"Analogue signal are used on ordinary telephone lines", "true", "false"},
            {"When a message arrives at its destination, it passes through the same seven network communication layers as when it was sent, but in reverse order", "true", "false"},
            {"Internet addresses are an integral part of the IP protocol.", "true", "false"},
            {"Internet addresses can be written as a series of numbers", "true", "false"},
            {"UDP software provides the final routing for data within the receiving system.", "true", "false"},
            {"Different mail systems transfer emails in different ways.", "true", "false"},
            {"SMTP is used for sending emails from a PC to a server", "true", "false"},
            {"SMTP does not allow a delivered message to be cancelled", "true", "false"},
            {"HTML is a markup language", "true", "false"},
            {"Internet searches will be better with XML files", "true", "false"},
            {"UDP recovers packets that aren't successfully delivered.", "false", "true"},
            {"TCP only works with packet-switched networks.", "false", "true"},
            {"TCP only works when it is combined with IP", "false", "true"},
            {"IMAP4 requires more bandwidth than the other email protocols.", "false", "true"},
            {"SMTP delivers messages one at a time.", "false", "true"},
            {"SMTP is only one of many protocols used to send mail between servers.", "false", "true"},
            {"POP protocol allows the user to download one message at a time", "false", "true"},
            {"XML files can only be used on Unix systems.", "false", "true"},
            {"XML files can only be read by browser programs.", "false", "true"},


    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_true_false);
        preferences = getSharedPreferences("saveData", MODE_PRIVATE);
        right_point = findViewById(R.id.points);
        countLabel = findViewById(R.id.countLabel);
        questionLabel = findViewById(R.id.questionLabel);
        Button answerBtn1 = findViewById(R.id.answerBtn1);
        Button answerBtn2 = findViewById(R.id.answerBtn2);
        preferencesScore = getSharedPreferences("SaveScore", MODE_PRIVATE);
        editeScore = preferencesScore.edit();
        // Create quizArray from quizData.
        for (String[] aQuizData : quizData) {
            // Prepare array.
            ArrayList<String> tmpArray = new ArrayList<>();
            tmpArray.add(aQuizData[0]);  // Country
            tmpArray.add(aQuizData[1]);  // Right Answer
            tmpArray.add(aQuizData[2]);  // Choice1
            // Choice3

            // Add tmpArray to quizArray.
            quizArray.add(tmpArray);
        }

        showNextQuiz();

    }

    private void showNextQuiz() {

        // Update quizCountLabel.
        countLabel.setText("Q" + quizCount);

        // Generate random number between 0 and 14 (quizArray's size - 1).
        Random random = new Random();
        int randomNum = random.nextInt(quizArray.size());

        // Pick one quiz set.
        ArrayList<String> quiz = quizArray.get(randomNum);

        // Set question and right answer.
        // Array format: {"Country", "Right Answer", "Choice1", "Choice2", "Choice3"}
        questionLabel.setText(quiz.get(0));
        rightAnswer = quiz.get(1);

        // Remove "Country" from quiz and Shuffle choices.
        quiz.remove(0);
        Collections.shuffle(quiz);

        // Set Choices.
//        answerBtn1.setText(quiz.get(0));
//        answerBtn2.setText(quiz.get(1));

        // Remove this quiz from quizArray.
        quizArray.remove(randomNum);

    }

    public void checkAnswer(View view) {

        // Get pushed button.
        Button answerBtn = findViewById(view.getId());
        String btnText = answerBtn.getText().toString();

        String alertTitle;

        if (btnText.equalsIgnoreCase(rightAnswer)) {
            // Correct!
            alertTitle = "Correct!";
            if (player != null) {
                player.release();
            }
            if (preferences.getBoolean("switch1", true)) {

                player = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                player.start();
            }

            rightAnswerCount++;


        } else {
            // Wrong...
            if (player != null) {
                player.release();
            }
            if (preferences.getBoolean("switch1", true)) {

                player = MediaPlayer.create(getApplicationContext(), R.raw.wrong);
                player.start();
            }

            alertTitle = "Wrong...";
        }

        // Create Dialog.
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(alertTitle);
        builder.setMessage("Answer : " + rightAnswer);
        builder.setPositiveButton("OK", (dialogInterface, i) -> {
            if (quizCount == quizData.length) {
                // Show Result.
//                Toast.makeText(QuizActivity_True_false.this, "تم الانتهاء يرجى الخروج ", Toast.LENGTH_SHORT).show();
                SweetAlertDialog dialog = new SweetAlertDialog(getWindow().getContext(), SweetAlertDialog.SUCCESS_TYPE);

                dialog.setTitleText("\n لقد أنهيت الأختبار  بنجاح ")
                        .setConfirmText("خروج  ")
                        .setConfirmClickListener(Dialog -> onBackPressed()).show();
            } else {
                try {
                    setText(rightAnswerCount, right_point);
                } catch (Exception e) {
                    Toast.makeText(this, e + "", Toast.LENGTH_SHORT).show();
                }
                quizCount++;
                showNextQuiz();
            }
        });
        builder.setCancelable(false);
        builder.show();

    }

    private static void setText(int score, TextView textView) {
        textView.setText(String.valueOf(score));

    }

    @Override
    public void onBackPressed() {
        if (preferencesScore.getInt("tfscore", 0) < rightAnswerCount) {
            editeScore.putInt("tfscore", rightAnswerCount);
            editeScore.apply();

        }
        super.onBackPressed();

    }

}
