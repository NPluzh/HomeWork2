package ru.geekbrainsstudent.calculator.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import ru.geekbrainsstudent.calculator.R;
import ru.geekbrainsstudent.calculator.model.CalculatorImpl;
import ru.geekbrainsstudent.calculator.model.Operator;

public class CalculatorActivity extends AppCompatActivity implements CalculatorView{

    private TextView resultTxt;
    private CalculatorPresenter presenter;




    View.OnClickListener digitClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View view){

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultTxt = findViewById(R.id.result);
        presenter = new CalculatorPresenter(this, new CalculatorImpl());

        Map<Integer, Integer> digits = new HashMap<>();
        digits.put(R.id.button_0, 0);
        digits.put(R.id.button_1, 1);
        digits.put(R.id.button_2, 2);
        digits.put(R.id.button_3, 3);
        digits.put(R.id.button_4, 4);
        digits.put(R.id.button_5, 5);
        digits.put(R.id.button_6, 6);
        digits.put(R.id.button_7, 7);
        digits.put(R.id.button_8, 8);
        digits.put(R.id.button_9, 9);


        View.OnClickListener digitClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onDigitPressed(digits.get(view.getId()));
            };
        };

        findViewById(R.id.button_0).setOnClickListener(digitClickListener);
        findViewById(R.id.button_1).setOnClickListener(digitClickListener);
        findViewById(R.id.button_2).setOnClickListener(digitClickListener);
        findViewById(R.id.button_3).setOnClickListener(digitClickListener);
        findViewById(R.id.button_4).setOnClickListener(digitClickListener);
        findViewById(R.id.button_5).setOnClickListener(digitClickListener);
        findViewById(R.id.button_6).setOnClickListener(digitClickListener);
        findViewById(R.id.button_7).setOnClickListener(digitClickListener);
        findViewById(R.id.button_8).setOnClickListener(digitClickListener);
        findViewById(R.id.button_9).setOnClickListener(digitClickListener);

        Map<Integer, Operator> operators = new HashMap<>();
        operators.put(R.id.button_div,Operator.DIV);
        operators.put(R.id.button_mul,Operator.MUL);
        operators.put(R.id.button_add,Operator.ADD);
        operators.put(R.id.button_sub,Operator.SUB);

        View.OnClickListener operatorClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onOperatorPressed(operators.get(view.getId()));
            };
        };

        findViewById(R.id.button_add).setOnClickListener(operatorClickListener);
        findViewById(R.id.button_mul).setOnClickListener(operatorClickListener);
        findViewById(R.id.button_div).setOnClickListener(operatorClickListener);
        findViewById(R.id.button_sub).setOnClickListener(operatorClickListener);

        findViewById(R.id.button_dot).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onDotPressed();
            }
        });
    };
   @Override
    public void showResult(String result) {
    resultTxt.setText(result);
    }
}