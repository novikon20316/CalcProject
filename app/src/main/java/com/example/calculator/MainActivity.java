package com.example.calculator;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    TextView screen;
    char op = ' ';
    int num1=0,num2=0;
    boolean isEqualClicked = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        screen = findViewById(R.id.TV);
        screen.setText("");
    }

    public void funcNumbers(View view) {
        Button btn = (Button) view;
        if((num1 != 0 && num2 != 0) || (isEqualClicked)){
            op = ' ';
            num1 = 0;
            num2 = 0;
            screen.setText("");
            isEqualClicked = false;
        }
        if(op == ' ' && screen.getText().toString().equals("0")){
            screen.setText("");
        }
        if(btn.getText() != "0" || (btn.getText() == "0" && screen.getText() != "")) {
            if(!btn.getText().toString().equals("0") && screen.getText().toString().equals("0")){
                screen.setText(btn.getText().toString());
            }else
                screen.setText(screen.getText() + btn.getText().toString());
        }
    }

    public void funcOperators(View view) {
        Button btn = (Button) view;
        if (isEqualClicked) {
            isEqualClicked = false;
            num2 = 0;
        }
        op = btn.getText().toString().charAt(0);
        num1 = Integer.parseInt(screen.getText().toString());
        screen.setText("");
    }

    public void funcEquals(View view) {
        num2 = Integer.parseInt(screen.getText().toString());
        switch (op) {
            case '+': {
                screen.setText(String.valueOf(num1 + num2));
                break;
            }case '-': {
                screen.setText(String.valueOf(num1 - num2));
                break;
            } case 'X': {
                screen.setText(String.valueOf(num1 * num2));
                break;
            }case '/': {
                if (num2 != 0) {
                    screen.setText(String.valueOf(num1 / num2));
                }else{
                    screen.setText("Error");
                }
                break;
            }
        }
        isEqualClicked = true;
    }
}