package ru.dachkovska.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static ru.dachkovska.calculator.ResultStorage.digits;
import static ru.dachkovska.calculator.ResultStorage.n;
import static ru.dachkovska.calculator.ResultStorage.num;
import static ru.dachkovska.calculator.ResultStorage.operator;
import static ru.dachkovska.calculator.ResultStorage.operators;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button button_1, button_2, button_3,button_4,button_5,button_6, button_7,button_8,button_9,button_0;
    Button button_percent,button_CE,button_C,button_backspace,button_fraction,button_sqr,button_sqrt;
    Button button_division,button_multiply,button_subtraction,button_addition,button_sign,button_point,button_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button_1=(Button)findViewById(R.id.button_1);
        button_1.setOnClickListener(this);
        button_2=(Button)findViewById(R.id.button_2);
        button_2.setOnClickListener(this);
        button_3=(Button)findViewById(R.id.button_3);
        button_3.setOnClickListener(this);
        button_4=(Button)findViewById(R.id.button_4);
        button_4.setOnClickListener(this);
        button_5=(Button)findViewById(R.id.button_5);
        button_5.setOnClickListener(this);
        button_6=(Button)findViewById(R.id.button_6);
        button_6.setOnClickListener(this);
        button_7=(Button)findViewById(R.id.button_7);
        button_7.setOnClickListener(this);
        button_8=(Button)findViewById(R.id.button_8);
        button_8.setOnClickListener(this);
        button_9=(Button)findViewById(R.id.button_9);
        button_9.setOnClickListener(this);
        button_0=(Button)findViewById(R.id.button_0);
        button_0.setOnClickListener(this);
        button_percent=(Button)findViewById(R.id.button_percent);
        button_percent.setOnClickListener(this);
        button_CE=(Button)findViewById(R.id.button_CE);
        button_CE.setOnClickListener(this);
        button_C=(Button)findViewById(R.id.button_C);
        button_C.setOnClickListener(this);
        button_backspace=(Button)findViewById(R.id.button_backspace);
        button_backspace.setOnClickListener(this);
        button_fraction=(Button)findViewById(R.id.button_fraction);
        button_fraction.setOnClickListener(this);
        button_sqr=(Button)findViewById(R.id.button_sqr);
        button_sqr.setOnClickListener(this);
        button_sqrt=(Button)findViewById(R.id.button_sqrt);
        button_sqrt.setOnClickListener(this);
        button_division=(Button)findViewById(R.id.button_division);
        button_division.setOnClickListener(this);
        button_multiply=(Button)findViewById(R.id.button_multiply);
        button_multiply.setOnClickListener(this);
        button_subtraction=(Button)findViewById(R.id.button_subtraction);
        button_subtraction.setOnClickListener(this);
        button_addition=(Button)findViewById(R.id.button_addition);
        button_addition.setOnClickListener(this);
        button_sign=(Button)findViewById(R.id.button_sign);
        button_sign.setOnClickListener(this);
        button_point=(Button)findViewById(R.id.button_point);
        button_point.setOnClickListener(this);
        button_result=(Button)findViewById(R.id.button_result);
        button_result.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        TextView infoTextView = (TextView) findViewById(R.id.textView);
        Button button = (Button)v;
        String buttonText = button.getText().toString();

        ifItIsADigit(infoTextView, buttonText);
        ifItIsPointOrSign(v, infoTextView);
        ifItIsAnUnaryOperator(v, infoTextView);
        ifItIsBinaryOperator(infoTextView, buttonText);
        ifItIsBackspaceCEorCButton(v, infoTextView);
        ifItIsAResultButton(infoTextView, buttonText);

    }

    private void ifItIsPointOrSign(View v, TextView infoTextView) {
        switch (v.getId()){
            case R.id.button_sign:
                if (n.contains("-"))
                    n = Double.toString(Double.parseDouble(n)*-1);
                else n="-"+n;
                if (Double.parseDouble(n) % 1 == 0) {
                    n=Integer.toString((int)(Double.parseDouble(n)));
                };
                infoTextView.setText(n);
                break;
            case R.id.button_point:
                if (!n.contains("."))
                    n=n+".";
                infoTextView.setText(n);
                break;
            default:
                break;
        }
    }

    private void ifItIsBackspaceCEorCButton(View v, TextView infoTextView) {
        switch (v.getId()){
            case R.id.button_CE:
                num[1]="";
                n="";
                infoTextView.setText("0");
                break;
            case R.id.button_C:
                num[0]="";
                num[1]="";
                n="";
                operator="";
                infoTextView.setText("0");
                break;
            case R.id.button_backspace:
                if (n.length()>0)
                     n = n.substring (0, n.length() - 1);
                if (n.length()==0) n="0";
                infoTextView.setText(n);
                break;
            default:
                break;
        }
    }

    private void ifItIsAnUnaryOperator(View v, TextView infoTextView) {
        switch (v.getId()){
            case R.id.button_fraction:
                if (n.equals(0))
                    infoTextView.setText("Cannot divide by zero");
                else {
                    n = Double.toString(1 / Double.parseDouble(n));
                    if (Double.parseDouble(n) % 1 == 0) {
                        n=Integer.toString((int)(Double.parseDouble(n)));
                    };
                    infoTextView.setText(n);
                }
                break;
            case R.id.button_sqr:
                n = Double.toString(Double.parseDouble(n)*Double.parseDouble(n));
                if (Double.parseDouble(n) % 1 == 0) {
                    n=Integer.toString((int)(Double.parseDouble(n)));
                };
                infoTextView.setText(n);
                break;
            case R.id.button_sqrt:
                if (n.contains("-"))
                    infoTextView.setText("Invalide input");
                else{
                    n = Double.toString(Math.sqrt(Double.parseDouble(n)));
                    if (Double.parseDouble(n) % 1 == 0) {
                        n=Integer.toString((int)(Double.parseDouble(n)));
                    };
                    infoTextView.setText(n);
                }
                break;
            default:
                break;
        }
    }

    private void ifItIsAResultButton(TextView infoTextView, String buttonText) {
        if (buttonText.equals("="))
        {
            if (!operator.equals("")) {
                if(n.equals("")){
                    operator="";
                    infoTextView.setText(num[0]);
                }
                else {
                    switch (operator){
                        case "/":
                            n = Double.toString(Double.parseDouble(num[0])/Double.parseDouble(n));
                            break;
                        case "*":
                            n = Double.toString(Double.parseDouble(num[0])*Double.parseDouble(n));
                            break;
                        case "-":
                            n = Double.toString(Double.parseDouble(num[0])-Double.parseDouble(n));
                            break;
                        case "+":
                            n = Double.toString(Double.parseDouble(num[0])+Double.parseDouble(n));
                            break;
                    }
                    operator="";
                    if (Double.parseDouble(n) % 1 == 0) {
                        n=Integer.toString((int)(Double.parseDouble(n)));
                    };
                    infoTextView.setText(n);
                    num[0]=n;
                }
            }
        }
    }

    private void ifItIsBinaryOperator(TextView infoTextView, String buttonText) {
        if (operators.contains(buttonText))
        {
            if (operator.equals("")) {
                num[0] = n;
                operator = buttonText;
                n = "";
            }
            else{
                if(n.equals("")){
                    operator=buttonText;
                    infoTextView.setText(num[0]);
                }
                else {
                    switch (operator){
                        case "/":
                            n = Double.toString(Double.parseDouble(num[0])/Double.parseDouble(n));
                            break;
                        case "*":
                            n = Double.toString(Double.parseDouble(num[0])*Double.parseDouble(n));
                            break;
                        case "-":
                            n = Double.toString(Double.parseDouble(num[0])-Double.parseDouble(n));
                            break;
                        case "+":
                            n = Double.toString(Double.parseDouble(num[0])+Double.parseDouble(n));
                            break;
                    }
                    operator=buttonText;
                    if (Double.parseDouble(n) % 1 == 0) {
                        n=Integer.toString((int)(Double.parseDouble(n)));
                    };
                    infoTextView.setText(n);
                    num[0]=n;
                    n="";
                }
            }
        }
    }

    private void ifItIsADigit(TextView infoTextView, String buttonText) {
        if (digits.contains(buttonText))
            n+=buttonText;
        infoTextView.setText(n);
    }
}