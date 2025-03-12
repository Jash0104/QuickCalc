package com.example.calculadora;

import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;

public class SpinnerActivity extends Activity implements AdapterView.OnItemSelectedListener {

    String operador;

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        operador = parent.getItemAtPosition( position ).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        operador = "";
    }

    public String getOperador() {
        return operador;
    }
    
}
