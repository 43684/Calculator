package com.example.calculator;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

import java.lang.Double;
import java.util.ArrayList;
import java.util.List;


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

    private String getResult(String data){
        List<String> list = new ArrayList<String>();//listan som allting man behöver för att räkna kommer finnas i
        if(data.matches("^(?!.*([/*+]{2}|^[/*+]|.*[/*+]$))[0-9/*.+\\-()]+$") && !data.matches(".*[-][+*/].*") && !data.matches(".*---.*") && !data.matches(".*[-+*/]$")) {//första ger true om det bara har 0-9/+*-     och att /*+ inte sitter bredvid varandra och att nån av de inte är i starten eller slutet av stringen.     andra ger true om det finns nåt sånt -*       den tredje ger true om det finns 3 minus tecken eller mer
            while (data.length() != 0) { //man detectar saker och sparar de på ett speciellt sätt i en string lista. Och varje gång man sparar man removar på data. och på så sätt lite på taget så går man igenom hela stringet och sparar information som kan beräknas sedan i nästa while loop.
                if(data.substring(0,1).equals("-")) {
                    if(data.substring(0,2).equals("--")) {//om det finns 2 (-)
                        list.add(data.substring(0,1));//addar första - i listan som man gör uträkningar med
                        data = data.substring(1, data.length());//removar från stringen vilket är data
                    }
                    list.add(data.substring(0,1));//samma sak här
                    data = data.substring(1, data.length());
                }
                int temp = speicalIndex(data);//kolla specialindex. Men i princip så ger den siffran av första indexen av de olika tecken man kan ha i en beräkning: (+-/*)
                if(temp == -1)//om stringen ser ut så här )något
                    return "Err";
                else if (temp == 1000) {// om string ser ut t.ex så här 85049853490    basiclly bara siffror
                    list.add(data);//addar siffrorna
                    data = "";//removar siffrorna vilket är allt
                }
                else if(temp > 0) { // normalt när första av de här -+/*() är en av +/*
                    if(parantesNearest(data)) { // det här händer när första är ( men det finns siffror innan.
                        list.add(data.substring(0, temp)); //addar allt innan parantesen i listan man använder för att beräkna sen. Btw en lista(string) av saker man kan beräkna kan se ut så här 43, -, 432, *, -, 432, -, -, 66, 34, /, 43
                        data = data.substring(temp, data.length());
                    }
                    else {// om första tecken är en av /*+
                        list.add(data.substring(0, temp));//addar allt innan tecknet
                        list.add(data.substring(temp, temp + 1));//addar tecktnet
                        data = data.substring(temp + 1, data.length());//removar tecknet och allt innan det
                    }
                }
                else {//kommer här bara om data börjar med '('
                    String tempString = data.substring(1, data.length());//så att algoritmen inte går i cirklar måste man ignorera parantesen i början
                    if(specialIndex2(tempString) == -1)//om det finns inga ')'
                        return "Err";
                    else if(specialIndex2(tempString) == 1)//om det ser ut så här "(något)något"
                    {
                        if(getResult(data.substring(1, specialIndex3(tempString) +1)) != "Err")//allt mellan '(' och ')' anledningen det är +1 till specialindex3 är eftersom det tar tempstring som är 1 charachter mindre än data
                            list.add(getResult(data.substring(1, specialIndex3(tempString) +1)));//addar resultatet i listan
                        else return "Err";
                        data = data.substring(specialIndex3(tempString) +2, data.length());//anledningen är pga samma anledning samma anledning som 3 linjer uppåt
                        if(data.matches("^[/+*].*")) {//linjerna innan bara addar resultet av vad som var i parantesen och removar parantesen och vad som var inuti. Om det nåt som (något)*något så blir det fel senare så åtgärder tas. den här kollar om data börjar med en av +-/*
                            list.add(data.substring(0,1));
                            data = data.substring(1, data.length());
                        }

                    }
                    else if(specialIndex2(tempString) == 2)//om det ser ut så här "(något(något"
                    {

                        int startPara = 1;// mängden av '('
                        int endPara = 0;//mängden av ')'
                        String tempData = data.substring(1, data.length());//Det är 1 eftersom vi är här eftersom det är något sånt "(något(något". så vi removar första parantes då vi redan vet att det finns(vilket är varför startpara är också 1)
                        while (startPara != endPara)// det kan finnas hur många startparantäser som helst men när mängden slutparanteser är samma som startparanteser, så kan man beräkna genom av indexet av sista slutparantes
                        {
                            if(specialIndex2(tempData) == -1)//om det finns inga ')'
                                return "Err";
                            else  if (specialIndex2(tempData) == 1) {//om första är ')'
                                endPara++;
                                if(startPara != endPara)//man vill att det ska vara så här i tempdata )något så att man  kan beräkna senare
                                    tempData = tempData.substring(specialIndex3(tempData) +1, tempData.length());
                            }
                            else {//om första är '('
                                startPara++;
                                tempData = tempData.substring(specialIndex3(tempData) +1, tempData.length());
                            }
                        }
                        int actualIndex = specialIndex3(tempData) + (data.length() - tempData.length()); // indexet av slutparantesen av första start parantes man detectade.

                        if(data.substring(1, specialIndex3(tempString) +1).matches(".*[-+*/]$") && data.substring(1, specialIndex3(tempString) +1).length() > 0)// om det är nåt sånt (något*(något
                        {
                            if (!getResult(data.substring(1, specialIndex3(tempString))).equals("Err"))//här kollar den om allt mellan '(' och '(' förutom sista charackter(/+-* ger error om är i slutet av stringen man försöker räkna) så den inte ger "Err" om man gör getresult på det
                                list.add(getResult(data.substring(1, specialIndex3(tempString))));
                            else return "Err";
                            list.add(data.substring(specialIndex3(tempString), specialIndex3(tempString) + 1));//här addar den characktären bakom andra '('. specialIndex2(tempString) == 2 betyder att det är (något(
                        }
                        else if (data.substring(1, specialIndex3(tempString) +1).length() > 0)//om det är något sånt "(någotOchIslutetÄrDetSiffra("
                        {
                            if (!getResult(data.substring(1, specialIndex3(tempString) +1)).equals("Err"))
                                list.add(getResult(data.substring(1, specialIndex3(tempString) +1)));
                            else return "Err";
                        }
                        if(!getResult(data.substring(specialIndex3(tempString) +1, actualIndex)).equals("Err"))//allt mellan andra startparantes och slutparanteset av första startparantes
                            list.add(getResult(data.substring(specialIndex3(tempString) + 1, actualIndex)));
                        else
                            return "Err";
                        data = data.substring(actualIndex +1, data.length());
                        if(data.matches("^[/+*].*")) {//kollar om den börjar med de tecknen
                            list.add(data.substring(0,1));
                            data = data.substring(1, data.length());//de här linjerna addar första charachter i listan och removar den från stringen man försöker räkna
                        }
                    }
                }
            }
            int ogSize = list.size();
            while (list.size() >= 2) {
                if(list.get(0).equals("-") && list.get(1).equals("-")) {// om det är nåt sånt -,-,3 så är det som att -,- inte var där vilket är varför de removas
                    list.remove(0);
                    list.remove(0);
                }
                else if(list.get(0).equals("-") && canConvert(list.get(1)))
                {
                    if (canConvert(list.get(1))) {//kolla canConvert
                        if(list.get(1).substring(0,1).equals("-"))// om det är nåt sånt -,-43
                            list.set(0, list.get(1).substring(1));
                        else //om det är nåt sånt -,43
                            list.set(0, "-" + list.get(1));
                        list.remove(1);
                    }
                    else return "Err";// om det är nåt
                }
                if(list.size() == 1)
                    return list.get(0);
                if (canConvert(list.get(1))) {// om andra stringen i listan är också ett nummer som kan konverteras till float
                    float tempDouble = Float.parseFloat(list.get(0)) * Float.parseFloat(list.get(1));
                    list.set(0, "" + tempDouble);
                    list.remove(1);
                }
                else if (list.get(1).equals("*") && canConvert(list.get(0))) {
                    if(list.get(2).equals("-") && list.get(3).equals("-")) {//om det är nåt sånt 5,*,-,-,5
                        list.remove(2);
                        list.remove(2);//man removar båda minus tecken
                    }
                    else if(list.get(2).equals("-")) { //om det är nåt sånt 5,*,-,5
                        if (canConvert(list.get(3))) {
                            if(list.get(3).substring(0,1).equals("-"))
                                list.set(2, list.get(3).substring(1));
                            else //om det är nåt sånt -,43
                                list.set(2, "-" + list.get(3));
                            list.remove(3);
                        }
                        else return "Err";// om det är nåt
                    }

                    float tempDouble = Float.parseFloat(list.get(0)) * Float.parseFloat(list.get(2));
                    list.set(0, "" + tempDouble);
                    list.remove(1);// om det är något sånt 3,*,4 så removar den i de här linjerna * och 4. efter att ha gjort uträkningen och ändrat postion 0 till resultatet
                    list.remove(1);
                }
                else if (list.get(1).equals("/") &&  canConvert(list.get(0))) {
                    if(list.get(2).equals("-") && list.get(3).equals("-")) {//om det är nåt sånt 5,*,-,-,5
                        list.remove(2);
                        list.remove(2);//man removar båda minus tecken
                    }
                    else if(list.get(2).equals("-")) { //om det är nåt sånt 5,*,-,5
                        if (canConvert(list.get(3))) {
                            if(list.get(3).substring(0,1).equals("-"))
                                list.set(2, list.get(3).substring(1));
                            else //om det är nåt sånt -,43
                                list.set(2, "-" + list.get(3));
                            list.remove(3);
                        }
                        else return "Err";// om det är nåt
                    }

                    float tempDouble = Float.parseFloat(list.get(0)) / Float.parseFloat(list.get(2));
                    list.set(0, "" + tempDouble);
                    list.remove(1);
                    list.remove(1);
                }
                else if (list.get(1).equals("+") && canConvert(list.get(0))) {
                    if(list.get(2).equals("-") && list.get(3).equals("-")) {//om det är nåt sånt 5,*,-,-,5
                        list.remove(2);
                        list.remove(2);//man removar båda minus tecken
                    }
                    else if(list.get(2).equals("-")) { //om det är nåt sånt 5,*,-,5
                        if (canConvert(list.get(3))) {
                            if(list.get(3).substring(0,1).equals("-"))
                                list.set(2, list.get(3).substring(1));
                            else //om det är nåt sånt -,43
                                list.set(2, "-" + list.get(3));
                            list.remove(3);
                        }
                        else return "Err";// om det är nåt
                    }

                    float tempDouble = Float.parseFloat(list.get(0)) + Float.parseFloat(list.get(2));
                    list.set(0, "" + tempDouble);
                    list.remove(1);
                    list.remove(1);
                }
                else if (list.get(1).equals("-") && canConvert(list.get(0))) {
                    if(list.get(2).equals("-") && list.get(3).equals("-")) {//om det är nåt sånt 5,*,-,-,5
                        list.remove(2);
                        list.remove(2);//man removar båda minus tecken
                    }
                    else if( list.get(2).equals("-")) { //om det är nåt sånt 5,*,-,5
                        if (canConvert(list.get(3))) {
                            if(list.get(3).substring(0,1).equals("-"))
                                list.set(2, list.get(3).substring(1));
                            else //om det är nåt sånt -,43
                                list.set(2, "-" + list.get(3));
                            list.remove(3);
                        }
                        else return "Err";// om det är nåt
                    }

                    float tempDouble = Float.parseFloat(list.get(0)) - Float.parseFloat(list.get(2));
                    list.set(0, "" + tempDouble);
                    list.remove(1);
                    list.remove(1);
                }
                else return "Err";
            }
            return list.get(0);
        }
        return "Err";

    }

    private boolean canConvert(String string) {//kollar om man kan konvertera till float eller double. förmodligen int också men försvinner decimaler
        if(string.matches("[0-9.-]+") && string.matches("^[^.]+(\\.[^.]+)?$") && !string.equals("-"))// första kollar om det är bara nummer, punkter och minus tecken. Nästa kollar att det bara finns en punkt och att stringet inte startar eller slutar på det.
            return true;
        return false;
    }


    private  int speicalIndex(String string)//kollar vilket tecker är först, inget tecken så returnar det 1000 som betyder att man behöver inte göra något. Om -1 är det error
    {
        int anInt = 1000;
        if(notMinus(string.indexOf('*')))
            anInt = string.indexOf('*');
        if(notMinus(string.indexOf('+')) && string.indexOf('+') < anInt)
            anInt = string.indexOf('+');
        if(notMinus(string.indexOf('-')) && string.indexOf('-') < anInt)
            anInt = string.indexOf('-');
        if(notMinus(string.indexOf('/')) && string.indexOf('/') < anInt)
            anInt = string.indexOf('/');
        if(notMinus(string.indexOf('(')) && string.indexOf('(') < anInt)
            anInt = string.indexOf('(');
        if(notMinus(string.indexOf(')')) && string.indexOf(')') < anInt)//det blir -1 och index av ')' är mindre än '(' eftersom det ska börja med '('
            anInt = -1;
        return anInt;
    }
    private boolean notMinus(int anInt)
    {
        if(anInt > -1)
            return true;
        return false;
    }

    private boolean parantesNearest(String string)
    {
        int anInt = 1000;
        if(notMinus(string.indexOf('*')))
            anInt = string.indexOf('*');
        if(notMinus(string.indexOf('+')) && string.indexOf('+') < anInt)
            anInt = string.indexOf('+');
        if(notMinus(string.indexOf('-')) && string.indexOf('-') < anInt)
            anInt = string.indexOf('-');
        if(notMinus(string.indexOf('/')) && string.indexOf('/') < anInt)
            anInt = string.indexOf('/');
        if(notMinus(string.indexOf('(')) && string.indexOf('(') < anInt)
            return true;

        return false;

    }
    private int specialIndex2(String string)//kollar vad man ska göra(efter att man vet att det finns en '(' i stringen man ska beräkna) beroende på vem som kommer först eller om det är error
    {
        int anInt = -1;
        if(notMinus(string.indexOf(')')))
            anInt = 1;
        else //blir error här pga om det finns en '(' måste det finnas en ')'
            return anInt;
        if(notMinus(string.indexOf('(')) && string.indexOf('(') < string.indexOf(')'))
            anInt = 2;

        return anInt;
    }
    private int specialIndex3(String string)//kollar indexet av första typ av parantes efter man vet vad man ska göra genom specialIndex2.
    {
        int anInt = string.indexOf(')');
        if(notMinus(string.indexOf('(')) && string.indexOf('(') < anInt)
            return string.indexOf('(');

        return anInt;
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