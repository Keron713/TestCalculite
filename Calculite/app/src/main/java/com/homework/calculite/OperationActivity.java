package com.homework.calculite;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class OperationActivity extends AppCompatActivity {

    private Button getResult;
    private EditText firstNumber;
    private EditText secondNumber;
    private TextView operationElement;

    private int firstNum;
    private int secondNum;
    private String result;
    private String firstNumberStorage;
    private String secondNumberStorage;

    private String operation;

    public static final int REQUEST_CODE = 123;
    public static final String KEY_OPERATION = "operation";
    public static final String RESULT = "result";
    public static final String FIRST_NUMBER = "firstNumber";
    public static final String SECOND_NUMBER = "secondNumber";

    public static Intent getIntent(Context context, String operation) {
        Intent intent = new Intent(context, OperationActivity.class);
        intent.putExtra(KEY_OPERATION, operation);
        return intent;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        setResult(Activity.RESULT_CANCELED);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operation);

        operation = getIntent().getExtras().getString(KEY_OPERATION);

        firstNumber = (EditText) findViewById(R.id.first_number);

        secondNumber = (EditText) findViewById(R.id.second_number);

        operationElement = (TextView) findViewById(R.id.operation_text);
        operationElement.setText(operation);


        getResult = (Button) findViewById(R.id.get_result);
        getResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (firstNumber.getText().toString().isEmpty() || secondNumber.getText().toString().isEmpty()) {
                    // toast
                    Toast.makeText(OperationActivity.this, R.string.enter_nums, Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    firstNum = Integer.parseInt(firstNumber.getText().toString());
                    secondNum = Integer.parseInt(secondNumber.getText().toString());
                } catch (NumberFormatException e) {
                    Toast.makeText(OperationActivity.this, R.string.wrong_numbers, Toast.LENGTH_SHORT).show();
                    return;
                }

//                 стартовать метод для вычисления
                switch (operation) {
                    case "+":
                        int resultInt = firstNum + secondNum;
                        result = String.valueOf(resultInt);

                        firstNumberStorage = String.valueOf(firstNum);
                        secondNumberStorage = String.valueOf(secondNum);

                        sendIntentResult();

                        break;
                    case "-":
                        resultInt = firstNum - secondNum;
                        result = String.valueOf(resultInt);

                        firstNumberStorage = String.valueOf(firstNum);
                        secondNumberStorage = String.valueOf(secondNum);

                        sendIntentResult();

                        break;
                    case "*":
                        resultInt = firstNum * secondNum;
                        result = String.valueOf(resultInt);

                        firstNumberStorage = String.valueOf(firstNum);
                        secondNumberStorage = String.valueOf(secondNum);

                        sendIntentResult();

                        break;
                    case "/":
                        if (secondNum != 0) {
                            float resultFloat = (float) firstNum / secondNum;

                            firstNumberStorage = String.valueOf(firstNum);
                            secondNumberStorage = String.valueOf(secondNum);
                            result = String.format(String.valueOf(resultFloat), "%.2f");

                            sendIntentResult();

                        } else {
                            Toast.makeText(OperationActivity.this, R.string.delete_zero, Toast.LENGTH_SHORT).show();
                        }
                        break;
                }
            }
        });
    }

    private void sendIntentResult() {
        Intent intent = new Intent();
        intent.putExtra(FIRST_NUMBER, firstNumberStorage);
        intent.putExtra(SECOND_NUMBER, secondNumberStorage);
        intent.putExtra(KEY_OPERATION, operation);
        intent.putExtra(RESULT, result);
        setResult(Activity.RESULT_OK, intent);
        // закрыть активность
        finish();
    }

}