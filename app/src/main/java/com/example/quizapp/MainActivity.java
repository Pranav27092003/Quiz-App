package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView totalQuestionsTextView;
    TextView questionTextView;
    TextView textView4;
    Button ansA,ansB,ansC,ansD;
    Button submitAnswer;

    int score=0;
    int p=1;
    int totalQuestions=QuestionsAnswers.question.length;
    int currentQuestionIndex=0;
    String selectedAnswer="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        totalQuestionsTextView=findViewById(R.id.total_questions);
        questionTextView=findViewById(R.id.questions);
        textView4=findViewById(R.id.textView4);
        ansA=findViewById(R.id.ans_A);
        ansB=findViewById(R.id.ans_B);
        ansC=findViewById(R.id.ans_C);
        ansD=findViewById(R.id.ans_D);
        submitAnswer=findViewById(R.id.submit_answer);

        ansA.setOnClickListener(this);
        ansB.setOnClickListener(this);
        ansC.setOnClickListener(this);
        ansD.setOnClickListener(this);
        submitAnswer.setOnClickListener(this);


    totalQuestionsTextView.setText("Total Questions :"+totalQuestions  );
      loadNewQuestions();


    }
    @Override
    public void onClick(View view){

        ansA.setBackgroundColor(Color.WHITE);
        ansB.setBackgroundColor(Color.WHITE);
        ansC.setBackgroundColor(Color.WHITE);
        ansD.setBackgroundColor(Color.WHITE);

        Button clickedButton =(Button) view;
        if(clickedButton.getId()==R.id.submit_answer){
            if(selectedAnswer.equals(QuestionsAnswers.correctAnswers[currentQuestionIndex])){
                score++;
               textView4.setText("Score : " +score+ "/10");
            }
            currentQuestionIndex++;
            p++;
            loadNewQuestions();


        }else{
            // choices have been clicked
            selectedAnswer=clickedButton.getText().toString();
            clickedButton.setBackgroundColor(Color.MAGENTA);
        }

    }

    void loadNewQuestions(){

        if(currentQuestionIndex==totalQuestions){
            finishQuiz();
            return;
        }

        totalQuestionsTextView.setText("Total Question :" +p + "/10");
        questionTextView.setText(QuestionsAnswers.question[currentQuestionIndex]);
        ansA.setText(QuestionsAnswers.options[currentQuestionIndex][0]);
        ansB.setText(QuestionsAnswers.options[currentQuestionIndex][1]);
        ansC.setText(QuestionsAnswers.options[currentQuestionIndex][2]);
        ansD.setText(QuestionsAnswers.options[currentQuestionIndex][3]);
    }

    void finishQuiz()
    {
        String passStatus ="";
        if(score>totalQuestions*0.60){
            passStatus="Passed";
        }
        else
        {
            passStatus="Failed";
        }

        new AlertDialog.Builder(this)
                .setTitle(passStatus)
                .setMessage("Score is "+score+ "out of " +totalQuestions)
                .setPositiveButton("Restart",((dialogInterface, i) -> restartQuiz()))
                .setCancelable(false)
                .show();
    }
    void restartQuiz(){
        score=0;
        p=1;
        currentQuestionIndex=0;
        loadNewQuestions();
    }

}
