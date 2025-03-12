package com.example.calculadora;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText edt_num_1,edt_num_2;
    TextView txt_result;
    Button btn_calc;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        edt_num_1 = findViewById(R.id.edt_num1);
        edt_num_2 = findViewById(R.id.edt_num2);
        btn_calc = findViewById(R.id.btn_calc);

        txt_result = findViewById(R.id.txt_result);

        spinner = findViewById(R.id.spinner_operations);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.operation_array,
                android.R.layout.simple_spinner_item
        );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter( adapter );
        SpinnerActivity spinnerActivity = new SpinnerActivity();
        spinner.setOnItemSelectedListener( spinnerActivity );

        btn_calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt_result.setText("");

                String operador = spinnerActivity.getOperador();
                String num1 = edt_num_1.getText().toString();
                String num2 = edt_num_2.getText().toString();

                double result = 0.;

                if (num1.isEmpty()||num2.isEmpty()) {
                    Toast.makeText(MainActivity.this,"Por favor llenar todos los campos", Toast.LENGTH_SHORT ).show();
                } else {
                    switch (operador) {
                        case "+":
                            result = Double.parseDouble(num1) + Double.parseDouble(num2);
                            break;
                        case "-":
                            result = Double.parseDouble(num1) - Double.parseDouble(num2);
                            break;
                        case "*":
                            result = Double.parseDouble(num1) * Double.parseDouble(num2);
                            break;
                        case "/":
                            if (!num2.equals("0")) {
                                result = Double.parseDouble(num1) / Double.parseDouble(num2);
                                result = Math.floor(result * 1000) / 1000;
                            } else {
                                Toast.makeText(MainActivity.this,"No es posible dividir sobre 0",Toast.LENGTH_SHORT).show();
                                return;
                            }
                            break;
                    }
                }

                txt_result.setText( result+"" );
            }
        });

    }
}

