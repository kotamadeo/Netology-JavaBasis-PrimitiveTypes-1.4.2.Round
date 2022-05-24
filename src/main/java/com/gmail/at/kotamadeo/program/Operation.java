package com.gmail.at.kotamadeo.program;

import com.gmail.at.kotamadeo.utils.Utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Operation {
    private double valueDouble;
    private float valueFloat;
    private BigDecimal doubleValueBD;
    private BigDecimal floatValueBD;


    public void setValueDouble(String input) {
        this.valueDouble = Double.parseDouble(input);
    }

    public void setValueFloat(String input) {
        this.valueFloat = Float.parseFloat(input);
    }

    public void isGreater() {
        doubleValueBD = BigDecimal.valueOf(valueDouble).setScale(4, RoundingMode.HALF_UP);
        floatValueBD = BigDecimal.valueOf(valueFloat).setScale(4, RoundingMode.HALF_UP);
        int compare = doubleValueBD.compareTo(floatValueBD);
        System.out.println(compare);
        if (compare == 0) {
            System.out.printf("%sЧисла %s и %s равны.%s%n", Utils.ANSI_PURPLE, valueDouble, valueFloat,
                    Utils.ANSI_RESET);
        } else if (compare > 0) {
            System.out.printf("%sЧисло %s больше чем число %s%s%n", Utils.ANSI_PURPLE, valueDouble, valueFloat,
                    Utils.ANSI_RESET);
        } else if (compare < 0) {
            System.out.printf("%sЧисло %s больше чем число %s%s%n", Utils.ANSI_PURPLE, valueFloat, valueDouble,
                   Utils.ANSI_RESET);
        }
    }

    public void toDiscardTheFractionalPart() {
        doubleValueBD = BigDecimal.valueOf(valueDouble);
        floatValueBD = BigDecimal.valueOf(valueFloat);
        System.out.printf("%sЧисла %s и %s без дробной части: %s и %s%s%n", Utils.ANSI_GREEN, valueDouble,
                valueFloat, doubleValueBD.setScale(0, RoundingMode.DOWN),
                floatValueBD.setScale(0, RoundingMode.DOWN), Utils.ANSI_RESET);
    }

    public void toRoundResult() {
        doubleValueBD = BigDecimal.valueOf(valueDouble);
        floatValueBD = BigDecimal.valueOf(valueFloat);
        System.out.printf("%sЧисла %s и %s c округлением до целых чисел: %s и %s%s%n", Utils.ANSI_GREEN, valueDouble,
                valueFloat, doubleValueBD.setScale(0, RoundingMode.HALF_UP),
                floatValueBD.setScale(0, RoundingMode.HALF_UP), Utils.ANSI_RESET);
    }

    public double getValueDouble() {
        return valueDouble;
    }

    public float getValueFloat() {
        return valueFloat;
    }
}
