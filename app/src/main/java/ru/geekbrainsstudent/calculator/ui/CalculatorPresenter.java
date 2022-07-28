package ru.geekbrainsstudent.calculator.ui;

import java.io.Serializable;
import java.text.DecimalFormat;

import ru.geekbrainsstudent.calculator.model.Calculator;
import ru.geekbrainsstudent.calculator.model.Operator;

public class CalculatorPresenter implements Serializable {
    DecimalFormat formater = new DecimalFormat("###,###.##########");
    private transient CalculatorView view;
    private transient Calculator calculator;
    private Double argOne;
    private Double argTwo;
    private Operator selectedOperator;
    private boolean lastIsOperator;// отвечает за то, чтобы оператор не был вызван два раза подряд
    private boolean isEmptyView;// отвечает за то, чтобы оператор не был введен раньше чисел
    private boolean thereIsArgOne;
    private boolean thereIsArgTwo;
    private boolean thereIsOperator;
    private boolean lastIsEqual;


    {
        isEmptyView = true;
    }

    public CalculatorPresenter(CalculatorView view, Calculator calculator) {
        this.view = view;
        this.calculator = calculator;
    }

    public void setCalculatorView(CalculatorView view){
        this.view = view;
    }

    public void onDigitPressed(int digit) {
        if (argTwo == null) {
            if (argOne == null) {
                argOne = 0d;
            }

            if (lastIsEqual) {
                argOne = 0.0;
            }// если было нажато равно, после чего цифра, то число начинает набираться заново


            argOne = argOne * 10 + digit;
            thereIsArgOne = true;// есть первый аргумент
            showFormatted(argOne);
        } else {
            thereIsArgTwo = true;//есть второй аргумент
            argTwo = argTwo * 10 + digit;
            showFormatted(argTwo);
        }

        lastIsOperator = false;// если нажата клавиша цифр, то оператор не будет нажат дважды
        isEmptyView = false;// окно не пустое
        lastIsEqual = false;// равно не было последним
    }

    public void onOperatorPressed(Operator operator) {
        if (!lastIsOperator && !isEmptyView) {// проверка на то, что клавиша оператора не будет нажата дважды и поле для ввода чисел не пустое
            if (selectedOperator != null) {
                argOne = calculator.perform(argOne, argTwo, selectedOperator);
                showFormatted(argOne);

                thereIsOperator = false;
                thereIsArgTwo = false;
            }
            argTwo = 0.0;
        }
        selectedOperator = operator;

        lastIsOperator = true;//последним был нажат оператор
        thereIsOperator = true;
        lastIsEqual = false;


    }


    private void showFormatted(Double value) {
        view.showResult(formater.format(value));
    }

    public void onEqualPressed() {
        if (thereIsArgTwo && thereIsArgOne && thereIsOperator) { // проверка возможности нажатия клавиши равенства
            argOne = calculator.perform(argOne, argTwo, selectedOperator);
            showFormatted(argOne);

            selectedOperator = null;
            argTwo = null;

            lastIsEqual = true;
            thereIsOperator = false;
            thereIsArgTwo = false;
        }
    }


}
