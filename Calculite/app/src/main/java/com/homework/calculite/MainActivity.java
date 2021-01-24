package com.homework.calculite;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private TextView result;
    private TextView resultOperation;
    private TextView selectOperation;
    private Button addition;
    private Button subtraction;
    private Button multiplication;
    private Button degree;

    public static final int REQUEST_CODE = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = (TextView) findViewById(R.id.result_text);
        resultOperation = (TextView) findViewById(R.id.operation_result);
        selectOperation = (TextView) findViewById(R.id.select_operation_text);

        addition = (Button) findViewById(R.id.addition);
        addition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendOperation("+");
            }
        });

        subtraction = (Button) findViewById(R.id.subtraction);
        subtraction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendOperation("-");
            }
        });

        multiplication = (Button) findViewById(R.id.multiplication);
        multiplication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendOperation("*");
            }
        });

        degree = (Button) findViewById(R.id.degree);
        degree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendOperation("/");
            }
        });
    }

    private void sendOperation(String operation) {
        Intent intent = OperationActivity.getIntent(MainActivity.this, operation);
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != Activity.RESULT_OK) {
            resultOperation.setText("");
            Toast.makeText(MainActivity.this, R.string.cancel_operation, Toast.LENGTH_SHORT).show();
            return;
        }
            String firstNumber = data.getStringExtra("firstNumber");
            String secondNumber = data.getStringExtra("secondNumber");
            String operation = data.getStringExtra("operation");
            String result = data.getStringExtra("result");
            String equality = "=";
            String messageResult = firstNumber + operation + secondNumber + equality + result;
            resultOperation.setText(messageResult);
    }
}