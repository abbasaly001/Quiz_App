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
    int clickCount = 0;
    static final int MAX_CLICK_COUNT = 10;

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
        generateNewLetter();

        sky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clickCount < MAX_CLICK_COUNT) {
                    checkAnswer(0);
                    generateNewLetter();
                    clickCount++;
                }
            }
        });

        grass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clickCount < MAX_CLICK_COUNT) {
                    checkAnswer(1);
                    generateNewLetter();
                    clickCount++;
                }
            }
        });

        root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clickCount < MAX_CLICK_COUNT) {
                    checkAnswer(2);
                    generateNewLetter();
                    clickCount++;
                }
            }
        });

        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int gainScore = count;
                int totalScore = ne - 1; // Subtract 1 to exclude the initial letter

                DBHelper dbHelper = new DBHelper(getContext());
                dbHelper.addQuizResult(gainScore, totalScore);

                ex.setText("Check Result in Menu");
            }
        });

        return view;
    }

    private void generateNewLetter() {
        if (ne <= MAX_CLICK_COUNT) {
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
        } else {
            tx.setText("");
            ex.setText("Only 10 questions at one time");
            sky.setEnabled(false);   // Disable all answer buttons
            grass.setEnabled(false);
            root.setEnabled(false);
            end.setEnabled(false);
        }
    }



    private void checkAnswer(int selectedButton) {
        if ((n == 0 && selectedButton == 0) || (n == 1 && selectedButton == 1) || (n == 2 && selectedButton == 2)) {
            ex.setText("Your answer is correct");
            count++;
        } else {
            ex.setText("Your answer is not correct");
        }
    }
}
