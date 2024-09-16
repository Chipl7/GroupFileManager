package ru.cft;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

public class StringStrategy {
    private int stringCount = 0;
    private Integer maxLen;
    private Integer minLen;
    private Flags flags;
    private FileWriter writer;

    public StringStrategy(Flags flags) throws IOException {
        this.flags = flags;
        initWriter();
    }

    public void parse(String s) {
        try{
            writer.write(s + System.lineSeparator());
            if (maxLen == null || maxLen < s.length()) {
                maxLen = s.length();
            }
            if (minLen == null || minLen > s.length()) {
                minLen = s.length();
            }
            stringCount++;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printStatistic(Flags flags){
        if(stringCount > 0 && flags.getS()){
            System.out.println("Количество строк занесённых в strings: " + stringCount);
        }
        if(stringCount > 0 && flags.getF()){
            System.out.println("Длина самой большой строки в strings: " + maxLen);
            System.out.println("Длина самой маленькой строки в strings: " + minLen);
        }
    }

    public void initWriter() throws IOException {
        String fileName = "strings.txt";
        String fileDirectory = flags.getOFileDirectory();
        fileName = flags.getpPrefix() != null ? flags.getpPrefix() + fileName : fileName;
        writer = new FileWriter(fileDirectory + fileName, flags.getA());
    }

    public void finish() throws IOException {
        writer.close();
    }
}
