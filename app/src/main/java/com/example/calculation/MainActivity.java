package com.example.calculation;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.math.BigDecimal;

import service.CalculationService;

public class MainActivity extends AppCompatActivity {

    private boolean isPlus = false;

    private BigDecimal heightMeterValue = BigDecimal.ZERO;

    private BigDecimal heightAirMeterValue = BigDecimal.ZERO;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText heightFeet = (EditText) findViewById(R.id.heightFeet);
        EditText heightAirMeters = (EditText) findViewById(R.id.heightAeroMeter);

        heightAirMeters.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if (s.length() > 0) {
                    heightAirMeterValue = BigDecimal.valueOf(Double.parseDouble(s.toString()));
                }
            }
        });

        TextView heightMeter = (TextView) findViewById(R.id.heightMeters);
        Button resultBtn = (Button) findViewById(R.id.resultBtn);

        heightFeet.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @SuppressLint("SetTextI18n")
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if (s.length() > 0) {
                    BigDecimal heightFeetValue = BigDecimal.valueOf(Double.parseDouble(s.toString()));
                    heightMeterValue = CalculationService.feetToMeters(heightFeetValue);
                    heightMeter.setText(heightMeterValue.toPlainString() + " м.");
                } else {
                    heightMeter.setText("");
                }
            }
        });

        Button plus = (Button) findViewById(R.id.plusBtn);

        Button minus = (Button) findViewById(R.id.minusBtn);

        plus.setOnClickListener(v -> {
            isPlus = true;
            plus.setEnabled(false);
            minus.setEnabled(true);
        });

        minus.setOnClickListener(v -> {
            isPlus = false;
            minus.setEnabled(false);
            plus.setEnabled(true);
        });

        TextView resultText = (TextView) findViewById(R.id.resultText);

        resultBtn.setOnClickListener(v -> {
            BigDecimal totalHeight = CalculationService.getTotalHeight(heightMeterValue, heightAirMeterValue, isPlus);
            resultText.setText(totalHeight.toPlainString() + " м.");
        });

    }

    public void heightFeetClick(View view) {

    }
}