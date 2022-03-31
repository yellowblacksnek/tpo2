package ru.yellowblacksnek;

import ru.yellowblacksnek.basic.MathFunction;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ModulesCSVRunner {

    public static void runMathFunction(MathFunction fun, double from, double to, double step) {
        Path newFilePath = Paths.get(
                "csv/"+
                fun.getClass().getSimpleName()+
                "_"+
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd--HH-mm-ss")));
        System.out.println(newFilePath);
        try {
            Files.createDirectories(Paths.get("csv"));
            Files.createFile(newFilePath);
            StringBuilder str = new StringBuilder();
            while (from <= to) {
                str.append(from);
                str.append(",");
                str.append(fun.apply(from));
                str.append(";\n");
                from+=step;
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(String.valueOf(newFilePath)));
            writer.append(str);

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
