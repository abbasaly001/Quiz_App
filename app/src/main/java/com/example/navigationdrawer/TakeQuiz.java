package com.example.navigationdrawer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.navigationdrawer.R;

import java.util.Random;

public class TakeQuiz extends Fragment {

    char skyletter[] = {'b', 'd', 'f', 'h', 'k', 'l', 't'};
    char grassletter[] = {'a', 'c', 'e', 'i', 'm', 'n', 'o', 'r', 's', 'u', 'v', 'w', 'x', 'z'};
    char rootletter[] = {'g', 'j', 'p', 'q', 'y'};
    int count = 0;
    int ne = 0;
    TextView tx, ex;
    Button sky, grass, root, end;
    Random rand;
    int n;

    public TakeQuiz() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_take_quiz, container, false);

        tx = view.findViewById(R.id.textView);
        ex = view.findViewById(R.id.textView3);
        sky = view.findViewById(R.id.button5);
        grass = view.findViewById(R.id.button6);
        root = view.findViewById(R.id.button8);
        end = view.findViewById(R.id.button10);

        DBHelper db = new DBHelper(getContext());
        rand = new Random();
        n = rand.nextInt(3);
        if (n == 0) {
            char i = skyletter[rand.nextInt(skyletter.length)];
            tx.setText(String.valueOf(i));
        } else if (n == 1) {
            char i = grassletter[rand.nextInt(grassletter.length)];
            tx.setText(String.valueOf(i));
        } else if (n == 2) {
            char i = rootletter[rand.nextInt(rootletter.length)];
            tx.setText(String.valueOf(i));
        }
        ne++;

        sky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (n == 0) {
                    ex.setText("Click for Next Word");
                    count++;
                } else {
                    ex.setText("Click for Next Word");
                    //ex.setText("Your answer is not right");
                }
                generateNewLetter();
            }
        });

        grass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (n == 1) {
                    ex.setText("Select Next Word");
                    //ex.setText("Your answer is right");
                    count++;
                } else {
                    ex.setText("Select Next Word");
                    //ex.setText("Your answer is not right");
                }
                generateNewLetter();
            }
        });

        root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (n == 2) {
                    ex.setText("Select Next Word");
                    //ex.setText("Your answer is right");
                    count++;
                } else {
                    ex.setText("Select Next Word");
                    //ex.setText("Your answer is not right");
                }
                generateNewLetter();
            }
        });

        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int gainScore = count;
                int totalScore = ne - 1; // Subtract 1 to exclude the initial letter

                DBHelper dbHelper = new DBHelper(getContext());
                dbHelper.addQuizResult(gainScore, totalScore);

                ex.setText("Check Score in Menu");
            }
        });

        return view;
    }

    public void generateNewLetter() {
        ne++;
        ex.setText("");
        n = rand.nextInt(3);
        if (n == 0) {
            char i = skyletter[rand.nextInt(skyletter.length)];
            tx.setText(String.valueOf(i));
        } else if (n == 1) {
            char i = grassletter[rand.nextInt(grassletter.length)];
            tx.setText(String.valueOf(i));
        } else if (n == 2) {
            char i = rootletter[rand.nextInt(rootletter.length)];
            tx.setText(String.valueOf(i));
        }
    }
}
