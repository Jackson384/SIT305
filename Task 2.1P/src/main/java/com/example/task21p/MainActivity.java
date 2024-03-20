package com.example.task21p;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Spinner spinnerSourceUnit, spinnerDestinationUnit;
    private EditText editTextInputValue;
    private Button buttonConvert;
    private TextView textViewResult;

    private Spinner spinnerUnitType;
    private ArrayAdapter<CharSequence> adapterLength, adapterWeight, adapterTemperature;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinnerUnitType = findViewById(R.id.spinnerUnitType);
        spinnerSourceUnit = findViewById(R.id.spinnerSourceUnit);
        spinnerDestinationUnit = findViewById(R.id.spinnerDestinationUnit);
        editTextInputValue = findViewById(R.id.editTextInputValue); // Initialize editTextInputValue
        buttonConvert = findViewById(R.id.buttonConvert); // Initialize buttonConvert
        textViewResult = findViewById(R.id.textViewResult);

        adapterLength = ArrayAdapter.createFromResource(
                this,
                R.array.length_units,
                android.R.layout.simple_spinner_item
        );
        adapterWeight = ArrayAdapter.createFromResource(
                this,
                R.array.weight_units,
                android.R.layout.simple_spinner_item
        );
        adapterTemperature = ArrayAdapter.createFromResource(
                this,
                R.array.temperature_units,
                android.R.layout.simple_spinner_item
        );

        spinnerUnitType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String selectedUnitType = parentView.getItemAtPosition(position).toString();
                switch (selectedUnitType) {
                    case "Length":
                        spinnerSourceUnit.setAdapter(adapterLength);
                        spinnerDestinationUnit.setAdapter(adapterLength);
                        break;
                    case "Weight":
                        spinnerSourceUnit.setAdapter(adapterWeight);
                        spinnerDestinationUnit.setAdapter(adapterWeight);
                        break;
                    case "Temperature":
                        spinnerSourceUnit.setAdapter(adapterTemperature);
                        spinnerDestinationUnit.setAdapter(adapterTemperature);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing
            }
        });

        buttonConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convert();
            }
        });
    }



    private void convert() {
        String selectedUnitType = spinnerUnitType.getSelectedItem().toString();
        String sourceUnit = spinnerSourceUnit.getSelectedItem().toString();
        String destinationUnit = spinnerDestinationUnit.getSelectedItem().toString();
        double inputValue = Double.parseDouble(editTextInputValue.getText().toString());
        double result = 0.0;

        switch (selectedUnitType) {
            case "Length":
                result = convertLength(sourceUnit, destinationUnit, inputValue);
                break;
            case "Weight":
                result = convertWeight(sourceUnit, destinationUnit, inputValue);
                break;
            case "Temperature":
                result = convertTemperature(sourceUnit, destinationUnit, inputValue);
                break;
        }

        textViewResult.setText(String.valueOf(result));
    }

    private double convertLength(String sourceUnit, String destinationUnit, double inputValue) {
        double result = 0.0;

        switch (sourceUnit) {
            case "Inch":
                if (destinationUnit.equals("Foot")) {
                    result = inputValue / 12.0;
                } else if (destinationUnit.equals("Yard")) {
                    result = inputValue / 36.0;
                } else if (destinationUnit.equals("Mile")) {
                    result = inputValue / 63360.0;
                }
                break;
            case "Foot":
                if (destinationUnit.equals("Inch")) {
                    result = inputValue * 12.0;
                } else if (destinationUnit.equals("Yard")) {
                    result = inputValue / 3.0;
                } else if (destinationUnit.equals("Mile")) {
                    result = inputValue / 5280.0;
                }
                break;
            case "Yard":
                if (destinationUnit.equals("Inch")) {
                    result = inputValue * 36.0;
                } else if (destinationUnit.equals("Foot")) {
                    result = inputValue * 3.0;
                } else if (destinationUnit.equals("Mile")) {
                    result = inputValue / 1760.0;
                }
                break;
            case "Mile":
                if (destinationUnit.equals("Inch")) {
                    result = inputValue * 63360.0;
                } else if (destinationUnit.equals("Foot")) {
                    result = inputValue * 5280.0;
                } else if (destinationUnit.equals("Yard")) {
                    result = inputValue * 1760.0;
                }
                break;
        }

        return result;
    }

    private double convertWeight(String sourceUnit, String destinationUnit, double inputValue) {
        double result = 0.0;
        switch (sourceUnit) {
            case "Pound":
                if (destinationUnit.equals("Ounce")) {
                    result = inputValue * 16;
                } else if (destinationUnit.equals("Ton")) {
                    result = inputValue / 2000;
                }
                break;
            case "Ounce":
                if (destinationUnit.equals("Pound")) {
                    result = inputValue / 16;
                } else if (destinationUnit.equals("Ton")) {
                    result = inputValue / 32000;
                }
                break;
            case "Ton":
                if (destinationUnit.equals("Pound")) {
                    result = inputValue * 2000;
                } else if (destinationUnit.equals("Ounce")) {
                    result = inputValue * 32000;
                }
                break;
        }
        return result;
    }

    private double convertTemperature(String sourceUnit, String destinationUnit, double inputValue) {
        double result = 0.0;
        switch (sourceUnit) {
            case "Celsius":
                if (destinationUnit.equals("Fahrenheit")) {
                    result = (inputValue * 9 / 5) + 32;
                } else if (destinationUnit.equals("Kelvin")) {
                    result = inputValue + 273.15;
                }
                break;
            case "Fahrenheit":
                if (destinationUnit.equals("Celsius")) {
                    result = (inputValue - 32) * 5 / 9;
                } else if (destinationUnit.equals("Kelvin")) {
                    result = (inputValue + 459.67) * 5 / 9;
                }
                break;
            case "Kelvin":
                if (destinationUnit.equals("Celsius")) {
                    result = inputValue - 273.15;
                } else if (destinationUnit.equals("Fahrenheit")) {
                    result = (inputValue * 9 / 5) - 459.67;
                }
                break;
        }
        return result;
    }


}