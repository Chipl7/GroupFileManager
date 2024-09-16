package ru.cft;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FloatStrategy {
    private int floatCount = 0;
    private Float floatMax;
    private Float floatMin;
    private float floatSum = 0;
    private Flags flags;
    private FileWriter writer;

    public FloatStrategy(Flags flags) throws IOException {
        this.flags = flags;
        initWriter();
    }

    public void parse(Float f) {
        try{
            flags.setA(true);
            writer.write(f + System.lineSeparator());
            if (floatMax == null || floatMax < f) {
                floatMax = f;
            }
            if (floatMin == null || floatMin > f) {
                floatMin = f;
            }
            floatSum += f;
            floatCount++;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printStatistic(Flags flags){
        if(floatCount > 0 && flags.getS()){
            System.out.println("Количество строк занесённых в floats: " + floatCount);
        }
        if(floatCount > 0 && flags.getF()){
            System.out.println("Максимальное число в floats: " + floatMax);
            System.out.println("Минимальное число в floats: " + floatMin);
            System.out.println("Сумма чисел в floats: " + floatSum);
            System.out.println("Среднее значение в floats: " + floatSum / floatCount);
            System.out.println();
        }
    }

    public void initWriter() throws IOException {
        String fileName = "floats.txt";
        String fileDirectory = flags.getOFileDirectory();
        fileName = flags.getpPrefix() != null ? flags.getpPrefix() + fileName : fileName;
        writer = new FileWriter(fileDirectory + fileName, flags.getA());
    }

    public void finish() throws IOException {
        writer.close();
    }
}
