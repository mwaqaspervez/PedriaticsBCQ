package com.mwaqaspervez.padiatricsbcq;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BCQMain extends AppCompatActivity implements View.OnClickListener {

    private TextView question, questionNumber, ans1, ans2, ans3, ans4;
    private List<String> list;
    private int currentPosition = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_bcq_main);

        list = new ArrayList<>(Arrays.asList(getQuestions(getIntent().getStringExtra("subject"))));

        question = (TextView) findViewById(R.id.bcq_question);
        questionNumber = (TextView) findViewById(R.id.bcq_question_number);
        ans1 = (TextView) findViewById(R.id.main_answer_i);
        ans2 = (TextView) findViewById(R.id.main_answer_ii);
        ans3 = (TextView) findViewById(R.id.main_answer_iii);
        ans4 = (TextView) findViewById(R.id.main_answer_iv);

        ans1.setOnClickListener(this);
        ans2.setOnClickListener(this);
        ans3.setOnClickListener(this);
        ans4.setOnClickListener(this);


        question.setText(list.get(currentPosition));
        questionNumber.setText("Question #" + currentPosition + 1);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }
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
        findViewById(showCorrectAnswer(list.get(currentPosition))).
                setBackgroundColor(ContextCompat.getColor(this, R.color.LawnGreen));
    }

    private int showCorrectAnswer(String answer) {

        if (ans1.getText().toString().trim().toLowerCase().equals(answer.trim().toLowerCase()))
            return R.id.main_answer_i;
        else if (ans2.getText().toString().trim().toLowerCase().equals(answer.trim().toLowerCase()))
            return R.id.main_answer_ii;
        else if (ans3.getText().toString().trim().toLowerCase().equals(answer.trim().toLowerCase()))
            return R.id.main_answer_iii;
        else if (ans4.getText().toString().trim().toLowerCase().equals(answer.trim().toLowerCase()))
            return R.id.main_answer_iv;

        return R.id.main_answer_i;
    }
}
