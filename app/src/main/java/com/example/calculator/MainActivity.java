package com.example.calculator;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

import java.lang.Double;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    // TODO: Deklarationen av objekt och variablar

    TextView resultTv,solutionTv;
    MaterialButton buttonC,buttonBrackOpen,buttonBrackClose;
    MaterialButton buttonDivide,buttonMultiply,buttonPlus,buttonMinus,buttonEquals;
    MaterialButton button0,button1,button2,button3,button4,button5,button6,button7,button8,button9;
    MaterialButton buttonAC,buttonDot;

    MaterialButton buttonSqRoute,buttonPercent,buttonCylinderVolume,buttonPythagorasTheorem,buttonCircleArea;

    Boolean buttonPythagorasTheoremClicked = false;
    Boolean buttonCylinderVolumeClicked = false;

    String firstFormulaDigit = "A";
    String secondFormulaDigit = "B";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultTv = findViewById(R.id.result_tv);
        solutionTv = findViewById(R.id.solution_tv);

        assignId(buttonC,R.id.button_c);
        assignId(buttonBrackOpen,R.id.button_open_bracket);
        assignId(buttonBrackClose,R.id.button_close_bracket);
        assignId(buttonDivide,R.id.button_divide);
        assignId(buttonMultiply,R.id.button_multiply);
        assignId(buttonPlus,R.id.button_plus);
        assignId(buttonMinus,R.id.button_minus);
        assignId(buttonEquals,R.id.button_equals);
        assignId(button0,R.id.button_0);
        assignId(button1,R.id.button_1);
        assignId(button2,R.id.button_2);
        assignId(button3,R.id.button_3);
        assignId(button4,R.id.button_4);
        assignId(button5,R.id.button_5);
        assignId(button6,R.id.button_6);
        assignId(button7,R.id.button_7);
        assignId(button8,R.id.button_8);
        assignId(button9,R.id.button_9);
        assignId(buttonAC,R.id.button_ac);
        assignId(buttonDot,R.id.button_dot);
        assignId(buttonSqRoute,R.id.button_sqRoute);
        assignId(buttonPercent,R.id.button_percent);
        assignId(buttonCylinderVolume,R.id.button_cylinderVolume);
        assignId(buttonPythagorasTheorem,R.id.button_pythagorasTheorem);
        assignId(buttonCircleArea,R.id.button_circleArea);


    }

    // TODO: Assigning id to all buttons and implementin onClickListener to check when a button is pressed and by implementing View.OnClickListener to the clas we can call the function to all buttons with assignID method.

    void assignId(MaterialButton btn,int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        MaterialButton button =(MaterialButton) view;
        String buttonText = button.getText().toString();
        String dataToCalculate = solutionTv.getText().toString();
        String dataToCalculateFormula = resultTv.getText().toString();
        buttonPythagorasTheorem = findViewById(R.id.button_pythagorasTheorem);
        buttonCylinderVolume = findViewById(R.id.button_cylinderVolume);

        // TODO: Check if we have used a formula before and clean the view and start fresh


        if (!dataToCalculate.isEmpty() && (dataToCalculate.charAt(0) == 'C' || dataToCalculate.charAt(0) == 'V' || dataToCalculate.charAt(0) == 'I')) {
            dataToCalculate = "";
            firstFormulaDigit = "A";
            secondFormulaDigit = "B";

        }


        // TODO:Pythagorans theorem started
        /*
        * Vi byter namn po theoremans knapp för att få en inledning vad knappen gör nästa gång
        * och gör så att man mattar in bara siffror inte nån annan funktion exempel addition eller multiplikation ect
        * så andra knapparna är döda typ sen implementerar huvudfunktiner för att ränsa fält
        * eller gå tillbaka om man behöver det eller få fram resultat*/

        if(buttonText.equals("A² + B² = C²")){

            button.setText("B² >");
            firstFormulaDigit = dataToCalculateFormula;
            solutionTv.setText(firstFormulaDigit+"² + B² = C²");
            buttonPythagorasTheoremClicked = true;
            return;
        }
        if(buttonText.equals("B² >")){
            button.setText("Result");
            firstFormulaDigit = getResult(resultTv.getText().toString());

            solutionTv.setText(firstFormulaDigit+"² + B² = C²");
            resultTv.setText("0");
            return;
        }
        if(buttonText.equals("Result")){
            button.setText("A² + B² = C²");
            secondFormulaDigit = getResult(resultTv.getText().toString());

            solutionTv.setText("C = √("+firstFormulaDigit+"² +" + secondFormulaDigit+"²)");
            resultTv.setText(calculateFormula());

            buttonPythagorasTheoremClicked = false;


            return;
        }
        if(buttonPythagorasTheoremClicked) {
            if(buttonText.equals("=") && secondFormulaDigit != "B"){
                buttonPythagorasTheorem.setText("A² + B² = C²");
                secondFormulaDigit = getResult(resultTv.getText().toString());

                solutionTv.setText("C = √("+firstFormulaDigit+"² +" + secondFormulaDigit+"²)");
                resultTv.setText(calculateFormula());

                buttonPythagorasTheoremClicked = false;
                return;
            }
            if(buttonText.equals("AC")){
                solutionTv.setText("");
                resultTv.setText("0");
                buttonPythagorasTheoremClicked = false;
                buttonPythagorasTheorem.setText("A² + B² = C²");
                return;
            }
            if(buttonText.equals("C")){
                if(dataToCalculateFormula.length() >= 2){
                    dataToCalculateFormula = dataToCalculateFormula.substring(0,dataToCalculateFormula.length()-1);
                    if (secondFormulaDigit.equals("B")) {
                        firstFormulaDigit = dataToCalculateFormula;
                    } else {
                        secondFormulaDigit = dataToCalculateFormula;
                    }
                    solutionTv.setText(firstFormulaDigit+"² + "+secondFormulaDigit+ "² = C²");
                }
                else  if(dataToCalculateFormula.length() == 1) {
                    if (secondFormulaDigit.equals("B")) {
                        firstFormulaDigit = "0";
                    } else {
                        secondFormulaDigit = "0";
                    }
                    solutionTv.setText(firstFormulaDigit+"² +" + secondFormulaDigit+"² = C");
                    resultTv.setText("0");

                    return;
                }
            } else {
                dataToCalculateFormula = resultTv.getText().toString();
                if (isNumberOrDot(buttonText)) {
                    dataToCalculateFormula = dataToCalculateFormula + buttonText;
                    if (buttonPythagorasTheorem.getText().toString().equals("B² >")) {
                        firstFormulaDigit = dataToCalculateFormula;
                        solutionTv.setText(firstFormulaDigit+"² + "+secondFormulaDigit+ "² = C²");
                    } else if  (buttonPythagorasTheorem.getText().toString().equals("Result")){
                        secondFormulaDigit = dataToCalculateFormula;
                        solutionTv.setText(firstFormulaDigit+"² + "+secondFormulaDigit+ "² = C²");
                    }
                }
            }
            String number = getResult(dataToCalculateFormula);

            if(!number.equals("Err")){
                resultTv.setText(number);
            }

            return;
        }

        // TODO:Cylinders volume started
        /*
         * Vi byter namn po formula knapp för att få en inledning vad knappen gör nästa gång
         * och gör så att man mattar in bara siffror inte nån annan funktion exempel addition eller multiplikation ect
         * så andra knapparna är döda typ sen implementerar huvudfunktiner för att ränsa fält
         * eller gå tillbaka om man behöver det eller få fram resultat*/
        if(buttonText.equals("V = π r² h")){

            button.setText("h >");
            firstFormulaDigit = dataToCalculateFormula;
            solutionTv.setText("π "+firstFormulaDigit+"² h = V");
            buttonCylinderVolumeClicked = true;
            return;
        }
        if(buttonText.equals("h >")){
            button.setText("Volume");
            firstFormulaDigit = getResult(resultTv.getText().toString());

            solutionTv.setText("π "+firstFormulaDigit+"² h = V");
            resultTv.setText("0");
            return;
        }
        if(buttonText.equals("Volume")){
            button.setText("V = π r² h");
            secondFormulaDigit = getResult(resultTv.getText().toString());

            solutionTv.setText("V = π "+firstFormulaDigit+"²*"+secondFormulaDigit);
            resultTv.setText(calculateFormula());

            buttonCylinderVolumeClicked = false;

            return;
        }
        if(buttonCylinderVolumeClicked) {
            if(buttonText.equals("=") && secondFormulaDigit != "B"){
                buttonCylinderVolume.setText("V = π r² h");

                secondFormulaDigit = getResult(resultTv.getText().toString());

                solutionTv.setText("V = π "+firstFormulaDigit+"²*"+secondFormulaDigit);
                resultTv.setText(calculateFormula());

                buttonCylinderVolumeClicked = false;
                return;
            }
            if(buttonText.equals("AC")){
                solutionTv.setText("");
                resultTv.setText("0");
                buttonCylinderVolumeClicked = false;
                buttonCylinderVolume.setText("V = π r² h");
                return;
            }
            if(buttonText.equals("C")){
                if(dataToCalculateFormula.length() >= 2){
                    dataToCalculateFormula = dataToCalculateFormula.substring(0,dataToCalculateFormula.length()-1);
                    if (secondFormulaDigit.equals("B")) {
                        firstFormulaDigit = dataToCalculateFormula;
                        solutionTv.setText("V = π "+firstFormulaDigit+"² h");
                    } else {
                        secondFormulaDigit = dataToCalculateFormula;
                        solutionTv.setText("π "+firstFormulaDigit+"²*"+secondFormulaDigit+ " = V");
                    }

                }
                else  if(dataToCalculateFormula.length() == 1) {
                    if (secondFormulaDigit.equals("B")) {
                        firstFormulaDigit = "0";
                        solutionTv.setText("π "+firstFormulaDigit+"² h"+ " = V");
                    } else {
                        secondFormulaDigit = "0";
                        solutionTv.setText("π "+firstFormulaDigit+"²*"+secondFormulaDigit+ " = V");
                    }
                    resultTv.setText("0");

                    return;
                }
            } else {
                dataToCalculateFormula = resultTv.getText().toString();
                if (isNumberOrDot(buttonText)) {
                    dataToCalculateFormula = dataToCalculateFormula + buttonText;
                    if (buttonCylinderVolume.getText().toString().equals("h >")) {
                        firstFormulaDigit = dataToCalculateFormula;
                        solutionTv.setText("π "+firstFormulaDigit+"² h"+ " = V");
                    } else if  (buttonCylinderVolume.getText().toString().equals("Volume")){
                        secondFormulaDigit = dataToCalculateFormula;
                        solutionTv.setText("π "+firstFormulaDigit+"²*"+secondFormulaDigit+ " = V");
                    }
                }
            }
            String number = getResult(dataToCalculateFormula);

            if(!number.equals("Err")){
                resultTv.setText(number);
            }

            return;
        }

        // TODO:Resterande mindre komplicerade funktioner

        if(buttonText.equals("AC")){
            solutionTv.setText("");
            resultTv.setText("0");
            return;
        }
        if(buttonText.equals("=")){
            solutionTv.setText(resultTv.getText());
            return;
        }
        if(buttonText.equals("C")){
            if(dataToCalculate.length() >= 2)
                dataToCalculate = dataToCalculate.substring(0,dataToCalculate.length()-1);
            else  if(dataToCalculate.length() == 1) {
                solutionTv.setText("");
                resultTv.setText("0");
                return;
            }
        }else{
            if(buttonText.equals("√")){
                dataToCalculate = calculateSqRoute(dataToCalculate);
            }
            else if(buttonText.equals("%")){
                dataToCalculate = calculatePercent(dataToCalculate);
            }
            else if(buttonText.equals("A = π r²")){
                dataToCalculate = calculateCircleArea(dataToCalculate);
            }
            else {
                dataToCalculate = dataToCalculate+buttonText;
            }

        }
        solutionTv.setText(dataToCalculate);

        String finalResult = getResult(dataToCalculate);

        if(!finalResult.equals("Err")){
            resultTv.setText(finalResult);
        }

    }

    // TODO: getResult function to descript a string into a math problem and then return a result in string format

    String getResult(String data){
        try{
            Context context  = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult =  context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            if(finalResult.endsWith(".0")){
                finalResult = finalResult.replace(".0","");
            }
            return finalResult;
        }catch (Exception e){
            return "Err";
        }
    }

    // TODO: Start of calculations formulas other than calculations


    String calculateSqRoute(String data){
        String failSafeData = getResult(data);
        double result = Double.parseDouble(failSafeData);
        return String.valueOf(Math.sqrt(result));
    }

    String calculatePercent(String data){
        String failSafeData = getResult(data);
        double result = Double.parseDouble(failSafeData);
        return String.valueOf(result/100);
    }

    String calculateCircleArea(String data){
        String failSafeData = getResult(data);
        double result = Double.parseDouble(failSafeData);
        return String.valueOf(Math.PI * Math.pow(result, 2));
    }

    String calculateFormula(){
        double first = Double.parseDouble(firstFormulaDigit);
        double second = Double.parseDouble(secondFormulaDigit);
        if (buttonPythagorasTheoremClicked) {
            double cPow = Math.pow(first,2) + Math.pow(second,2);
            return String.valueOf(Math.sqrt(cPow));
        }

        if (buttonCylinderVolumeClicked) {
            buttonCylinderVolumeClicked = false;
            return String.valueOf(Math.PI * Math.pow(first,2) * second);
        }
        return "";
    }


    // TODO: Failsafe code to stop user to use other buttons while doing formula calculation

    public static boolean isNumberOrDot(String str) {
        if (str.isEmpty()) {
            return false;
        }

        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c) && c != '.') {
                return false;
            }
        }

        return true;
    }


}