package com.gmail.at.kotamadeo.program;

import com.gmail.at.kotamadeo.utils.Utils;

import java.util.Scanner;

public class Program {
    private final Operation operation = new Operation();
    private final Scanner scanner = new Scanner(System.in);

    public void start() {
        String input;
        String[] allInput;
        while (true) {
            try {
                printMenu();
                input = scanner.nextLine();
                if (input.equalsIgnoreCase("выход") || input.equals("0")) {
                    scanner.close();
                    break;
                } else {
                    int operationNumber = Integer.parseInt(input);
                    switch (operationNumber) {
                        case 1:
                            System.out.println(Utils.ANSI_BLUE + "Введите два числа через пробел:" + Utils.ANSI_RESET);
                            allInput = scanner.nextLine().split(" ");
                            operation.setValueDouble(allInput[0]);
                            operation.setValueFloat(allInput[1]);
                            System.out.printf("%sВведеные числа: %s и %s%s%n", Utils.ANSI_BLUE,
                                    operation.getValueDouble(), operation.getValueFloat(), Utils.ANSI_RESET);
                            break;
                        case 2:
                            operation.isGreater();
                            break;
                        case 3:
                            operation.toDiscardTheFractionalPart();
                            break;
                        case 4:
                            operation.toRoundResult();
                            break;
                        default:
                            System.out.println(Utils.ANSI_RED + "Вы ввели неверный номер операции!" + Utils.ANSI_RESET);
                    }
                }
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                System.out.println(Utils.ANSI_RED + "Неверный ввод!" + Utils.ANSI_RESET);
            }
        }
    }

    private void printMenu() {
        System.out.println(Utils.ANSI_YELLOW + "Эта программа способна произвести ряд манипуляций " +
                "над числами с плавающей точкой." + Utils.ANSI_RESET);
        System.out.println(Utils.ANSI_PURPLE + "Возможные команды программы:" + Utils.ANSI_RESET);
        System.out.println("0 или выход: чтобы выйти из программы.");
        System.out.println("1: ввести числа.");
        System.out.println("2: сравнить числа.");
        System.out.println("3: отбросить дробную часть чисел.");
        System.out.println("4: округлить числа до целых чисел.");
        System.out.print(">>>>>>>");
    }
}
