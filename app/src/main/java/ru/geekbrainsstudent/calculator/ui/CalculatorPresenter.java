package ru.geekbrainsstudent.calculator.ui;

import java.text.DecimalFormat;

import ru.geekbrainsstudent.calculator.model.Calculator;
import ru.geekbrainsstudent.calculator.model.Operator;

public class CalculatorPresenter {
    DecimalFormat formater = new DecimalFormat("#.##");
    private CalculatorView view;
    private Calculator calculator;
    private double argOne;
    private Double argTwo;
    private Operator selectedOperator;

    public CalculatorPresenter(CalculatorView view, Calculator calculator) {
        this.view = view;
        this.calculator = calculator;
    }

    public void onDigitPressed(int digit) {
        if(argTwo == null){
            argOne = argOne * 10 + digit;
            showFormatted(argOne);
        }else{
            argTwo = argTwo * 10 + digit;
            showFormatted(argTwo);
        }

    }

    public void onOperatorPressed(Operator operator) {
        if(selectedOperator != null){
            argOne = calculator.perform(argOne,argTwo,selectedOperator);
            view.showResult(String.valueOf(argOne));
        }

        argTwo = 0.0;
        selectedOperator = operator;
    }

    private  void showFormatted(double value){
        view.showResult(formater.format(value));
    }

    public void onDotPressed() {
    }
}
