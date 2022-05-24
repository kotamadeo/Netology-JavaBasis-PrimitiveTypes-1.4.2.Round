# **Задача №2 Округление double**

## **Цель**:

1. Написать программу для сравнения ```double``` и ```float``` и округления этих типов.

### *Пример*:

``` Пример 1
0 или выход: чтобы выйти из программы.
1: ввести числа.
2: сравнить числа.
3: отбросить дробную часть чисел.
4: округлить числа до целых чисел.

Введите первое число:
1.5
Введите второе число:
1.6
Введеные числа: 1.5 и 1.6

Число 1.6 больше чем число 1.5

Числа 1.5 и 1.6 без дробной части: 1 и 1

Числа 1.5 и 1.6 c округлением до целых чисел: 2 и 2
```

### **Моя реализация**:

1. Реализация осуществлена в парадигме ООП.
2. Создал структуру классов:

* **Program** - отвечающий за запуск программы, путем инициирования метода *start()* (с инициированием внутри себя
  вспомогательного метода *printMenu()*);

#### Ккласс **Program**:
``` java
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
```
* **Operation** - позволяющий: сравнить два числа через ```void``` метод *isGreater()*, с
  помощью ```BigDecimal```
  отбросить дробную часть через метод *toDiscardTheFractionalPart()*, округлить числа до целых через метод
  *toRoundResult()*, а также соответственно задать значение типа ```double``` через метод *setValueDouble()* и задать
  значение типа ```float``` через метод *setValueFloat()*.

#### Класс **Operation**:
``` java   
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
```

2. Использовал кодирование цвета текста (ANSI).

#### Класс **Utils**:
``` java
public class Utils {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static void printDelim() {
        System.out.println(ANSI_GREEN + "*********************************************" + ANSI_RESET);
    }
}
```

3. Использовал ```try-catch```, чтобы избежать падение программы в исключения.

#### Метод *main()* в классе **Main**:
``` java
public class Main {
    public static void main(String[] args) {
        Program program = new Program();
        program.start();
    }
}
```

## *Вывод в консоль*:

* меню:
``` 
Эта программа способна произвести ряд манипуляций над числами с плавающей точкой.
Возможные команды программы:
0 или выход: чтобы выйти из программы.
1: ввести числа.
2: сравнить числа.
3: отбросить дробную часть чисел.
4: округлить числа до целых чисел.
>>>>>>>
```