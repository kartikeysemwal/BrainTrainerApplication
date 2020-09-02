package com.example.braintainerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView timer;
    TextView operation;
    TextView score;
    TextView rightorwrong;
    TextView yourscore;
    CountDownTimer cdt;
    int min;
    int max;
    int mi;
    int ma;
    int mainrandom;
    int choosebutton;
    int num1, num2;
    int numberoftimes;
    //int arr[] = new int[4];
    int turn=0;
    Button firstButton, secondButton, thirdButton, fourthButton;
    int numscore=0;

    private static final String FILE_NAME = "brainTrainerApp.txt";
    TextView mEditText;
    TextView textView;

    public void mainFunction(){
        num1 = new Random().nextInt(((max-50) - min) + 1) + min;
        num2 = new Random().nextInt(((max-50) - min) + 1) + min;
        numberoftimes++;
        mainrandom = num1 + num2;

        operation.setText(Integer.toString(num1)+ "+" + Integer.toString(num2));

        choosebutton = new Random().nextInt((ma-mi)+1)+mi;

        Log.i("Calculations", "Mainrandom " + Integer.toString(mainrandom) + "  " + Integer.toString(num1) + "  " + Integer.toString(num2) + "  "
        + Integer.toString(choosebutton+1));

        if(choosebutton==0)
            firstButton.setText(Integer.toString(mainrandom));
        else{
            int ran = new Random().nextInt((max - min) + 1) + min;
            firstButton.setText(Integer.toString(ran));
        }
        if(choosebutton==1)
            secondButton.setText(Integer.toString(mainrandom));
        else {
            int ran = new Random().nextInt((max - min) + 1) + min;
            secondButton.setText(Integer.toString(ran));
        }
        if(choosebutton==2)
            thirdButton.setText(Integer.toString(mainrandom));
        else {
            int ran = new Random().nextInt((max - min) + 1) + min;
            thirdButton.setText(Integer.toString(ran));
        }
        if(choosebutton==3)
            fourthButton.setText(Integer.toString(mainrandom));
        else {
            int ran = new Random().nextInt((max - min) + 1) + min;
            fourthButton.setText(Integer.toString(ran));
        }
    }

    public void checkTimeUp(){
        firstButton.setEnabled(false);
        secondButton.setEnabled(false);
        thirdButton.setEnabled(false);
        fourthButton.setEnabled(false);
    }

    public void startFunction(){
        firstButton.setEnabled(true);
        secondButton.setEnabled(true);
        thirdButton.setEnabled(true);
        fourthButton.setEnabled(true);
        textView.setText("");
        mEditText.setText("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        min = 0;
        max = 100;
        mi = 0;
        ma = 3;
        numberoftimes = 0;

        firstButton = findViewById(R.id.firstButton);
        secondButton = findViewById(R.id.secondButton);
        thirdButton = findViewById(R.id.thirdButton);
        fourthButton = findViewById(R.id.fourthButton);
        operation = findViewById(R.id.operation);
        score = findViewById(R.id.score);
        rightorwrong = findViewById(R.id.rightorwrong);
        yourscore = findViewById(R.id.yourScore);
        mEditText = findViewById(R.id.mEditText);
        textView = findViewById(R.id.textView);

        timer = findViewById(R.id.timer);
        checkTimeUp();

        load();
        final Button startButton = findViewById(R.id.startButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startFunction();
                startButton.setEnabled(false);
                if(turn!=0){
                    cdt.cancel();
                }

                cdt = new CountDownTimer(10000, 1000){
                    public void onTick(long millisecondsuntildone){
                        timer.setText(String.valueOf(millisecondsuntildone/1000));
                    }
                    public void onFinish(){
                        timer.setText("0");
                        checkTimeUp();
                        yourscore.setText("Your score is "+(Integer.toString(numscore)+"/"+Integer.toString(numberoftimes)));
                        save((Integer.toString(numscore)+"/"+Integer.toString(numberoftimes)));
                    }
                }.start();
                mainFunction();
                turn++;
                score.setText(Integer.toString(numscore));
            }
        });

        firstButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(choosebutton==0){
                    numscore++;
                    score.setText(Integer.toString(numscore)+"/"+Integer.toString(numberoftimes));
                    rightorwrong.setText("Correct");
                }
                else
                    rightorwrong.setText("Wrong");
                score.setText(Integer.toString(numscore)+"/"+Integer.toString(numberoftimes));
                mainFunction();
            }
        });
        secondButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(choosebutton==1){
                    numscore++;
                    score.setText(Integer.toString(numscore)+"/"+Integer.toString(numberoftimes));
                    rightorwrong.setText("Correct");
                }
                else
                    rightorwrong.setText("Wrong");
                score.setText(Integer.toString(numscore)+"/"+Integer.toString(numberoftimes));
                mainFunction();
            }
        });
        thirdButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(choosebutton==2){
                    numscore++;
                    score.setText(Integer.toString(numscore)+"/"+Integer.toString(numberoftimes));
                    rightorwrong.setText("Correct");
                }
                else
                    rightorwrong.setText("Wrong");
                score.setText(Integer.toString(numscore)+"/"+Integer.toString(numberoftimes));
                mainFunction();
            }
        });
        fourthButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(choosebutton==3){
                    numscore++;
                    score.setText(Integer.toString(numscore)+"/"+Integer.toString(numberoftimes));
                    rightorwrong.setText("Correct");
                }
                else
                    rightorwrong.setText("Wrong");
                score.setText(Integer.toString(numscore)+"/"+Integer.toString(numberoftimes));
                mainFunction();
            }
        });

    }

    public void save(String s) {
        String text = s;
        FileOutputStream fos = null;

        try {
            fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
            fos.write(text.getBytes());

            mEditText.setText("");
            Toast.makeText(this, "Saved to " + getFilesDir() + "/" + FILE_NAME,
                    Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void load() {
        FileInputStream fis = null;

        try {
            fis = openFileInput(FILE_NAME);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String text;

            while ((text = br.readLine()) != null) {
                sb.append(text).append("\n");
            }

            mEditText.setText(sb.toString());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
