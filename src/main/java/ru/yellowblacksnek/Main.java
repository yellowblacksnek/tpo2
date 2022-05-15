package ru.yellowblacksnek;

import ru.yellowblacksnek.basic.Ln;
import ru.yellowblacksnek.basic.MathFunction;
import ru.yellowblacksnek.basic.Sin;
import ru.yellowblacksnek.functions.Cos;
import ru.yellowblacksnek.functions.Log;
import ru.yellowblacksnek.functions.Tan;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static String getInput() throws IOException {
        return reader.readLine();
    }

    public static void main(String[] args) {
        try {
            boolean exit = false;

            while (!exit) {
                exit = runCli();
            }
        } catch (IOException e) {
            System.out.println("Произошла ошибка ввода-вывода, завершение программы.");
            System.exit(1);
        }
    }

    private static boolean runCli() throws IOException {
        System.out.println("Выберите действие:");
        System.out.println("1. Решить систему");
        System.out.println("2. Получить значения из модуля");
        System.out.println("0. Выход");

        int val = 0;

        while(true) {
            String inputStr = getInput();
            String[] input = inputStr.split(" ");
            try {
                val = Integer.parseInt(input[0]);
                if(val < 0 || val > 2) throw new Exception();
                break;
            } catch (Exception e) {
                System.out.println("Вы ввели что-то не то");
            }
        }
        if(val == 0) return true;
        else if(val == 1) {
            solveSystem();
        } else {
            runModule();
        }

        return false;
    }

    private static void solveSystem() throws IOException {
        double precision = setPrecision();
        MathFunction system = new MathSystem(precision);

        double x = 0;
        while(true) {
            System.out.println("Введите x: ");
            String inputStr = getInput();
            String[] input = inputStr.split(" ");
            try {
                x = Double.parseDouble(input[0]);
                break;
            } catch (Exception e) {
                System.out.println("Вы ввели что-то не то");
            }
        }
        double y = system.apply(x);
        System.out.println("Результат: " + y);
    }

    private static void runModule() throws IOException {
        double precision = setPrecision();
        double from, to, step;
        while(true) {
            System.out.println("Введите желаемые диапазон и шаг в формате 'начало конец шаг':");
            String inputStr = getInput();
            String[] input = inputStr.split(" ");
            try {
                from = Double.parseDouble(input[0]);
                to = Double.parseDouble(input[1]);
                step = Double.parseDouble(input[2]);

                if(step > Math.abs(from-to)) {
                    System.out.println("Слишком большой шаг");
                    continue;
                }
                break;
            } catch (Exception e) {
                System.out.println("Вы ввели что-то не то");
            }

        }

        System.out.println("Начало: " + Math.min(from,to));
        System.out.println("Конец: " + Math.max(from,to));
        System.out.println("Шаг: " + step);
        MathFunction fun = chooseModule(precision);
        String fileName = ModulesCSVRunner.runMathFunction(fun, from, to, step);
        if(fileName != null) {
            System.out.println("Данные выгружены в файл: " + fileName);
        }
    }

    private static double setPrecision() throws IOException {
        double precision = 0;
        while(true) {
            System.out.println("Введите желаемую точность (число с плавающей точкой):");
            String inputStr = getInput();
            String[] input = inputStr.split(" ");
            try {
                precision = Double.parseDouble(input[0]);
                if(precision <= 0 || precision >= 1) throw new Exception();
                break;
            } catch (Exception e) {
                System.out.println("Вы ввели что-то не то");
            }

        }
        System.out.println("Установлена точность: " + precision);
        return precision;
    }

    private static MathFunction chooseModule(double precision) throws IOException {
        MathFunction fun = null;
        while(true) {
            System.out.println("Выберите модуль:");
            System.out.println("1. sin\n2. cos\n3. tan\n4. ln\n5. log\n6.система");
            String inputStr = getInput();
            String[] input = inputStr.split(" ");
            try {
                int val = Integer.parseInt(input[0]);
                if(val < 1 || val > 6) throw new Exception();

                if(val == 1) fun = new Sin(precision);
                if(val == 2) fun = new Cos(precision);
                if(val == 3) fun = new Tan(precision);
                if(val == 4) fun = new Ln(precision);
                if(val == 6) fun = new MathSystem(precision);

                if(val == 5) {
                    System.out.println("Введите основание логарифма: ");
                    double base = 0;
                    while(true) {
                        inputStr = getInput();
                        input = inputStr.split(" ");
                        try {
                            base = Double.parseDouble(input[0]);
                            if (base > 0 && base != 1) break;
                        } catch (Exception e) {
                            System.out.println("Вы ввели что-то не то");
                        }
                    }
                    fun = new Log(precision, base);
                }
                break;
            } catch (Exception e) {
                System.out.println("Вы ввели что-то не то");
            }

        }
        System.out.println("Выбранный модуль: " + fun.getClass().getSimpleName());
        return fun;
    }
}
