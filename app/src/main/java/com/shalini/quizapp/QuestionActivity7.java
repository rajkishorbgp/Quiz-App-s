package com.shalini.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class QuestionActivity7 extends AppCompatActivity {

    int flag=0;
    int marks=0;
    public static int correct=0;
    int wrong=0;
    String [] questions={"Which of the following is not a Java feature?",
            "Which keyword is used to inherit a class in Java?",
            "Which of the following is the default value of a local variable in Java?",
            "Which method is the entry point of a Java program?",
            "Which of the following is true about final keyword in Java?" };
    String [] options={"Object-Oriented","Portable","Dynamic and Extensible","Pointer Arithmetic",
            "super","this","extends","implements",
            "null","0","false","No default value",
            "main","start","run","init",
            "A final class can be inherited.","A final method cannot be overridden.","A final variable’s value can be changed.","final is used only with methods."

    };
    String [] answers={
            "Pointer Arithmetic",
            "extends",
            "No default value",
            "main",
            "A final method cannot be overridden",

    };
    TextView quitBtn,dispNo,score,question;
    Button nextBtn;
    RadioGroup radio_g;
    RadioButton rb1,rb2,rb3,rb4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_question7);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        quitBtn=findViewById(R.id.quitBtn);
        dispNo=findViewById(R.id.dispNo);
        score=findViewById(R.id.score);
        question=findViewById(R.id.question);
        nextBtn=findViewById(R.id.nextBtn);
        radio_g=findViewById(R.id.answerGroup);
        rb1=findViewById(R.id.radioBtn1);
        rb2=findViewById(R.id.radioBtn2);
        rb3=findViewById(R.id.radioBtn3);
        rb4=findViewById(R.id.radioBtn4);
        question.setText(questions[flag]);
        rb1.setText(options[0]);
        rb2.setText(options[1]);
        rb3.setText(options[2]);
        rb4.setText(options[3]);

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(radio_g.getCheckedRadioButtonId()==-1){
                    Toast.makeText(QuestionActivity7.this, "Please select an option", Toast.LENGTH_SHORT).show();
                    return;
                }
                RadioButton uAnswer=findViewById(radio_g.getCheckedRadioButtonId());
                String ansText=uAnswer.getText().toString();

                if(ansText.equals(answers[flag])){
                    correct++;
                    Toast.makeText(QuestionActivity7.this, "Hurry!! it was correct", Toast.LENGTH_SHORT).show();
                }else{
                    wrong++;
                }
                flag++;
                if (score!=null){
                    score.setText(""+correct);
                    if(flag<questions.length){
                        question.setText(questions[flag]);
                        rb1.setText(options[flag*4]);
                        rb2.setText(options[flag*4+ 1]);
                        rb3.setText(options[flag*4+ 2]);
                        rb4.setText(options[flag*4+ 3]);

                        dispNo.setText((flag+1) +  "/"  +questions.length);
                    }else {
                        marks=correct;
                        Intent intent=new Intent(QuestionActivity7.this,ResultActivity.class);
                        intent.putExtra("attempted",flag);
                        intent.putExtra("correct",correct);
                        intent.putExtra("wrong",wrong);
                        startActivity(intent);
                        finish();
                    }
                    radio_g.clearCheck();
                }
            }
        });
        quitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(QuestionActivity7.this,ResultActivity.class);
                intent.putExtra("attempted",flag);
                intent.putExtra("correct",correct);
                intent.putExtra("wrong",wrong);
                startActivity(intent);
                finish();
            }
        });


    }
}