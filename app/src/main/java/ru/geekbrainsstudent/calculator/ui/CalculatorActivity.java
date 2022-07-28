package ru.geekbrainsstudent.calculator.ui;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import ru.geekbrainsstudent.calculator.R;
import ru.geekbrainsstudent.calculator.model.CalculatorImpl;
import ru.geekbrainsstudent.calculator.model.Operator;
import ru.geekbrainsstudent.calculator.model.Theme;
import ru.geekbrainsstudent.calculator.model.ThemeRepository;
import ru.geekbrainsstudent.calculator.model.ThemeRepositoryImpl;

public class CalculatorActivity extends AppCompatActivity implements CalculatorView{

    private TextView resultTxt;
    private CalculatorPresenter presenter;
    private ThemeRepository themeRepository;
    private final static String keyPresenter = "Presenter";
    private final static String keyTextView = "TextView";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        themeRepository = ThemeRepositoryImpl.getInstance(this);
        setTheme(themeRepository.getSavedTheme().getThemeRes());
        setContentView(R.layout.activity_main);
        resultTxt = findViewById(R.id.result);
        if(savedInstanceState == null){
            presenter = new CalculatorPresenter(this, new CalculatorImpl());
        }
        init();
    };

    @Override
    protected void onSaveInstanceState(@NonNull Bundle instanceState){
        super.onSaveInstanceState(instanceState);
        instanceState.putSerializable(keyPresenter, presenter);
        instanceState.putString(keyTextView, resultTxt.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle instanceState) {
        super.onRestoreInstanceState(instanceState);
        presenter = (CalculatorPresenter) instanceState.getSerializable(keyPresenter);
        presenter.setCalculatorView(this);
        resultTxt.setText(instanceState.getSerializable(keyTextView).toString());
    }

    private void init(){
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


        View.OnClickListener equalClickListener = new View.OnClickListener() {// слушатель на нажатия кнопки со знаком равенства
            @Override
            public void onClick(View view) {
                presenter.onEqualPressed();
            };
        };
        findViewById(R.id.button_equal).setOnClickListener(equalClickListener);//прикрепление слушателя к view


        ActivityResultLauncher<Intent> themeLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if(result.getResultCode() == Activity.RESULT_OK){
                    Intent intent = result.getData();

                    Theme selectedTheme = (Theme)intent.getSerializableExtra(SelectThemeActivity.EXTRA_THEME);

                    themeRepository.saveTheme(selectedTheme);
                    recreate();

                }
            }
        });

        findViewById(R.id.theme).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CalculatorActivity.this, SelectThemeActivity.class);
                intent.putExtra(SelectThemeActivity.EXTRA_THEME, themeRepository.getSavedTheme());

                themeLauncher.launch(intent);
            }
        });
    }

   @Override
    public void showResult(String result) {
    resultTxt.setText(result);
    }

}