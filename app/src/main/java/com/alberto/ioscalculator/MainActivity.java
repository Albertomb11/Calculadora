package com.alberto.ioscalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    //Botones
    private TextView numberToCalculate;
    private Button suma;
    private Button resta;
    private Button multiplicar;
    private Button division;
    private Button nine;
    private Button eight;
    private Button seven;
    private Button six;
    private Button five;
    private Button four;
    private Button three;
    private Button two;
    private Button one;
    private Button point;
    private Button cero;
    private Button neg_pos;
    private Button same;
    private Button delete;
    private Button ac;

    //Data
    private double mAccumulator;
    private char mOp;
    private boolean equalsBefore;
    private double secondNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newcalc);

        conectarBotones();
        conectarBotonesConEventos();

        valoresPorDefecto();

    }

    private void conectarBotonesConEventos() {

        suma.setOnClickListener(this);
        resta.setOnClickListener(this);
        multiplicar.setOnClickListener(this);
        division.setOnClickListener(this);
        nine.setOnClickListener(this);
        eight.setOnClickListener(this);
        seven.setOnClickListener(this);
        six.setOnClickListener(this);
        five.setOnClickListener(this);
        four.setOnClickListener(this);
        three.setOnClickListener(this);
        two.setOnClickListener(this);
        one.setOnClickListener(this);
        point.setOnClickListener(this);
        cero.setOnClickListener(this);
        neg_pos.setOnClickListener(this);
        same.setOnClickListener(this);
        delete.setOnClickListener(this);
        ac.setOnClickListener(this);
    }

    private void conectarBotones(){

        numberToCalculate = (TextView) findViewById(R.id.numberToCalculate);
        suma = (Button)findViewById(R.id.sumBtn);
        resta = (Button)findViewById(R.id.restBtn);
        multiplicar = (Button)findViewById(R.id.multBtn);
        division = (Button)findViewById(R.id.divBtn);
        nine = (Button)findViewById(R.id.btnNine);
        eight = (Button)findViewById(R.id.btnEight);
        seven = (Button)findViewById(R.id.btnSeven);
        six = (Button)findViewById(R.id.btnSix);
        five = (Button)findViewById(R.id.btnFive);
        four = (Button)findViewById(R.id.btnFour);
        three = (Button)findViewById(R.id.btnThree);
        two = (Button)findViewById(R.id.btnTwo);
        one = (Button)findViewById(R.id.btnOne);
        point = (Button)findViewById(R.id.btnPoint);
        cero = (Button)findViewById(R.id.btnCero);
        neg_pos = (Button)findViewById(R.id.btnNeg_pos);
        same = (Button)findViewById(R.id.btnSame);
        delete = (Button)findViewById(R.id.btnDelete);
        ac = (Button)findViewById(R.id.btnAC);
    }

    private void valoresPorDefecto() {
        mAccumulator = 0;
        mOp = 0;
        numberToCalculate.setText("0");
        equalsBefore = false;
    }


    @Override
    public void onClick(View v) {
        Button button = (Button) v;
        switch (v.getId()) {
            case R.id.btnCero:
            case R.id.btnOne:
            case R.id.btnTwo:
            case R.id.btnThree:
            case R.id.btnFour:
            case R.id.btnFive:
            case R.id.btnSix:
            case R.id.btnSeven:
            case R.id.btnEight:
            case R.id.btnNine:
                readNumber(button);
                break;
            case R.id.sumBtn:
            case R.id.restBtn:
            case R.id.multBtn:
            case R.id.divBtn:
                applyOp(button);
                break;
            case R.id.btnAC:
                valoresPorDefecto();
                break;
            case R.id.btnNeg_pos:
                changeSign();
                break;
            case R.id.btnPoint:
                addPoint();
                break;
            case R.id.btnDelete:
                deleteNumber();
                break;
            case R.id.btnSame:
                makeOperation();
                break;
            default:
                // Error
        }
    }


    private void makeOperation() {
        if (!equalsBefore){
            secondNumber = Double.parseDouble(numberToCalculate.getText().toString());
        }
        double total = 0;

        switch (mOp) {
            case '+':
                total = mAccumulator + secondNumber;
                break;
            case '-':
                total = mAccumulator - secondNumber;
                break;
            case '*':
                total = mAccumulator * secondNumber;
                break;
            case '/':
                total = mAccumulator / secondNumber;
                break;
            default:
        }

        equalsBefore = true;

        mAccumulator = total;

        long totalInt = (long) total;

        if (total == (double) totalInt) {
            numberToCalculate.setText(totalInt + "");
        } else {
            numberToCalculate.setText(total + "");
        }
    }

    private void applyOp(Button button) {
        mOp = button.getText().toString().charAt(0);
        mAccumulator = Double.parseDouble(numberToCalculate.getText().toString());

        numberToCalculate.setText("0");

        equalsBefore = false;
    }

    private void deleteNumber() {
        String numeroActual = numberToCalculate.getText().toString();

        if (numeroActual.length() == 1 || numeroActual.length() == 2 && Integer.parseInt(numeroActual) < 0) {
            numberToCalculate.setText("0");
        } else {
            numberToCalculate.setText(
                    numeroActual.substring(0, numeroActual.length() - 1)
            );
        }
    }

    private void addPoint() {
        if (!numberToCalculate.getText().toString().contains(".")) {
            numberToCalculate.setText(
                    numberToCalculate.getText().toString() + '.'
            );
        }
    }

    private void changeSign() {
        String numeroActual = numberToCalculate.getText().toString();

        if (!numeroActual.equals("0")) {
            if (numeroActual.charAt(0) == '-') {
                numeroActual = numeroActual.substring(1);
            } else {
                numeroActual = '-' + numeroActual;
            }
            numberToCalculate.setText(numeroActual);
        }
    }


    private void readNumber(Button button) {
        String numero = button.getText().toString();
        String actualNumber = numberToCalculate.getText().toString();

        Log.d("CalcActivity", "mAccumulator = " + mAccumulator + " | mOp = " + mOp);


        if (actualNumber.equals("0")) {
            numberToCalculate.setText(numero);
        } else {
            numberToCalculate.setText(
                    numberToCalculate.getText().toString() + numero
            );
        }

        if (numberToCalculate.getText().toString().equals("0") && mOp == '/') {
            same.setEnabled(false);
        } else {
            same.setEnabled(true);
        }


    }
}
