package com.mwaqaspervez.padiatricsbcq;


import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import java.util.ArrayList;
import java.util.List;

public class BCQMain extends AppCompatActivity implements View.OnClickListener, ViewSwitcher.ViewFactory {

    private TextSwitcher questionNumber, ans1, ans2, ans3, ans4;
    private TextSwitcher question;
    private List<String> questionsList;
    private List<String> optionA;
    private List<String> optionB;
    private List<String> optionC;
    private List<String> optionD;
    private List<String> correctAnswerList;
    private int currentPosition = 0;
    private TextView next, previous;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_bcq_main);

        init();
        loadAllQuestions();
        showFirstQuestion();

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }
    }

    private void loadAllQuestions() {
        questionsList.add("What is the name of your pet ?");
        questionsList.add("What is the name of your father ?");

        optionA.add("Dog");
        optionB.add("Cat");
        optionC.add("Elephant");
        optionD.add("Crocodile");
        correctAnswerList.add("Cat");

        optionA.add("Aslam");
        optionB.add("Ali");
        optionC.add("Hussain");
        optionD.add("Mehmood");
        correctAnswerList.add("Mehmood");
    }

    private void showFirstQuestion() {

        question.setText(questionsList.get(currentPosition));
        questionNumber.setText("" + (currentPosition + 1) + "");
        ans1.setText("A) " + optionA.get(currentPosition) + "");
        ans2.setText("B) " + optionB.get(currentPosition) + "");
        ans3.setText("C) " + optionC.get(currentPosition) + "");
        ans4.setText("D) " + optionD.get(currentPosition) + "");
    }

    private void init() {

        //  questionsList = new ArrayList<>(Arrays.asList(getQuestions(getIntent().getStringExtra("subject"))));
        questionsList = new ArrayList<>();
        optionA = new ArrayList<>();
        optionB = new ArrayList<>();
        optionC = new ArrayList<>();
        optionD = new ArrayList<>();
        correctAnswerList = new ArrayList<>();

        question = (TextSwitcher) findViewById(R.id.bcq_question);
        questionNumber = (TextSwitcher) findViewById(R.id.bcq_question_number);
        ans1 = (TextSwitcher) findViewById(R.id.main_answer_i);
        ans2 = (TextSwitcher) findViewById(R.id.main_answer_ii);
        ans3 = (TextSwitcher) findViewById(R.id.main_answer_iii);
        ans4 = (TextSwitcher) findViewById(R.id.main_answer_iv);

        next = (TextView) findViewById(R.id.bcq_next);
        previous = (TextView) findViewById(R.id.bcq_previous);

        ans1.setOnClickListener(this);
        ans2.setOnClickListener(this);
        ans3.setOnClickListener(this);
        ans4.setOnClickListener(this);
        next.setOnClickListener(this);
        previous.setOnClickListener(this);

        question.setFactory(this);
        questionNumber.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                TextView myText = new TextView(BCQMain.this);
                myText.setTextSize(16);
                myText.setTextColor(ContextCompat.getColor(BCQMain.this, R.color.Brown));
                myText.setTypeface(null, Typeface.BOLD);
                return myText;
            }
        });
        ans1.setFactory(this);
        ans2.setFactory(this);
        ans3.setFactory(this);
        ans4.setFactory(this);


        Animation in = AnimationUtils.loadAnimation(this, R.anim.left_to_right);
        Animation out = AnimationUtils.loadAnimation(this, R.anim.right_to_left);

        question.setInAnimation(in);
        question.setOutAnimation(out);
        questionNumber.setInAnimation(in);
        questionNumber.setOutAnimation(out);
        ans1.setInAnimation(in);
        ans1.setOutAnimation(out);
        ans2.setInAnimation(in);
        ans2.setOutAnimation(out);
        ans3.setInAnimation(in);
        ans3.setOutAnimation(out);
        ans4.setInAnimation(in);
        ans4.setOutAnimation(out);

    }

    private String[] getQuestions(String subject) {
        String[] question = null;
        switch (subject) {

            case "fcps_i_anatomy":
                question = getResources().getStringArray(R.array.questions_fcps_i_anatomy);
                break;
            case "fcps_i_physiology":
                question = getResources().getStringArray(R.array.questions_fcps_i_physiology);

                break;
            case "fcps_i_pathology":
                question = getResources().getStringArray(R.array.questions_fcps_i_pathology);

                break;
            case "fcps_ii_imnci":
                question = getResources().getStringArray(R.array.questions_fcps_ii_imnci);

                break;
            case "fcps_ii_growth":
                question = getResources().getStringArray(R.array.questions_fcps_ii_growth);

                break;
            case "fcps_ii_infections":
                question = getResources().getStringArray(R.array.questions_fcps_ii_infections);

                break;
            case "fcps_ii_hematology":
                question = getResources().getStringArray(R.array.questions_fcps_ii_hematology);

                break;
            case "fcps_ii_gastroenterology":
                question = getResources().getStringArray(R.array.questions_fcps_ii_gastroenterology);

                break;
            case "fcps_ii_pulmonology":
                question = getResources().getStringArray(R.array.questions_fcps_ii_pulmonology);

                break;
            case "fcps_ii_nephrology":
                question = getResources().getStringArray(R.array.questions_fcps_ii_nephrology);

                break;
            case "fcps_ii_cardiology":
                question = getResources().getStringArray(R.array.questions_fcps_ii_cardiology);

                break;
            case "fcps_ii_dermatology":
                question = getResources().getStringArray(R.array.questions_fcps_ii_dermatology);

                break;
            case "fcps_ii_rheumatology":
                question = getResources().getStringArray(R.array.questions_fcps_ii_rheumatology);

                break;
            case "fcps_ii_neurology":
                question = getResources().getStringArray(R.array.questions_fcps_ii_neurology);

                break;
            case "fcps_ii_neonatology":
                question = getResources().getStringArray(R.array.questions_fcps_ii_neonatology);

                break;
            case "fcps_ii_orthopedics":
                question = getResources().getStringArray(R.array.questions_fcps_ii_orthopedics);

                break;
            case "fcps_ii_endocrinology":
                question = getResources().getStringArray(R.array.questions_fcps_ii_endocrinology);

                break;
            case "mbb_imnci":
                question = getResources().getStringArray(R.array.questions_mbb_imnci);

                break;
            case "mbb_growth":
                question = getResources().getStringArray(R.array.questions_mbb_growth);

                break;
            case "mbb_neonatology":
                question = getResources().getStringArray(R.array.questions_mbb_neonatology);

                break;
            case "mbb_hematology":
                question = getResources().getStringArray(R.array.questions_mbb_hematology);

                break;
            case "mbb_cardiology":
                question = getResources().getStringArray(R.array.questions_mbb_cardiology);

                break;
            case "mbb_pulmonology":
                question = getResources().getStringArray(R.array.questions_mbb_pulmonology);

                break;
            case "mbb_nephrology":
                question = getResources().getStringArray(R.array.questions_mbb_nephrology);

                break;
            case "mbb_endocrinology":
                question = getResources().getStringArray(R.array.questions_mbb_endocrinology);

                break;
            case "mbb_dermatology":
                question = getResources().getStringArray(R.array.questions_mbb_dermatology);

                break;

            case "free":
                question = getResources().getStringArray(R.array.questions_free);
                break;
        }
        return question;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        this.finish();
        overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.bcq_next:
                currentPosition++;
                if (currentPosition == questionsList.size()) {
                    showCompleteDialog();
                    currentPosition = 0;
                } else
                    loadNextQuestion();
                break;

            case R.id.bcq_previous:
                currentPosition--;
                if (currentPosition == -1)
                    currentPosition = 0;
                else
                    loadNextQuestion();
                break;

            default:
                view.setBackgroundColor(ContextCompat.getColor(this, R.color.Chocolate));
                findViewById(showCorrectAnswer()).
                        setBackgroundColor(ContextCompat.getColor(this, R.color.LawnGreen));
        }
    }


    private void showCompleteDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Quiz Completed.")
                .setCancelable(true)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).create().show();
    }

    private void loadNextQuestion() {
        setDefaultColor();
        question.setText(questionsList.get(currentPosition));
        ans1.setText("A) " + optionA.get(currentPosition) + "");
        ans2.setText("B) " + optionB.get(currentPosition) + "");
        ans3.setText("C) " + optionC.get(currentPosition) + "");
        ans4.setText("D) " + optionD.get(currentPosition) + "");
        questionNumber.setText("" + (currentPosition + 1) + "");

    }

    private void setDefaultColor() {
        ans1.setBackgroundColor(Color.WHITE);
        ans2.setBackgroundColor(Color.WHITE);
        ans3.setBackgroundColor(Color.WHITE);
        ans4.setBackgroundColor(Color.WHITE);
    }


    private int showCorrectAnswer() {
        if (optionA.get(currentPosition).trim().toLowerCase().equals(correctAnswerList.get(currentPosition).trim().toLowerCase()))
            return R.id.main_answer_i;
        else if (optionB.get(currentPosition).trim().toLowerCase().equals(correctAnswerList.get(currentPosition).trim().toLowerCase()))
            return R.id.main_answer_ii;
        else if (optionC.get(currentPosition).trim().toLowerCase().equals(correctAnswerList.get(currentPosition).trim().toLowerCase()))
            return R.id.main_answer_iii;
        else if (optionD.get(currentPosition).trim().toLowerCase().equals(correctAnswerList.get(currentPosition).trim().toLowerCase()))
            return R.id.main_answer_iv;

        return R.id.main_answer_i;
    }

    @Override
    public View makeView() {
        TextView myText = new TextView(this);
        myText.setTextSize(14);
        myText.setTextColor(Color.BLACK);
        return myText;
    }
}
