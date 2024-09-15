import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class LongStrategy {
    private long integerCount = 0;
    private Long integerMax;
    private Long integerMin;
    private long integerSum = 0;
    private Flags flags;
    private FileWriter writer;

    public LongStrategy(Flags flags) throws IOException {
        this.flags = flags;
        initWriter();
    }

    public void parse(Long l) {
        try{
            writer.write(l + System.lineSeparator());
            if(integerMax == null || integerMax < l){
                integerMax = l;
            }
            if(integerMin == null || integerMin > l){
                integerMin = l;
            }
            integerSum += l;
            integerCount++;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printStatistic(Flags flags){
        if(integerCount > 0 && flags.getS()){
            System.out.println("Количество строк занесённых в integers: " + integerCount);
        }
        if(integerCount > 0 && flags.getF()){
            System.out.println("Максимальное число в integers: " + integerMax);
            System.out.println("Минимальное число в integers: " + integerMin);
            System.out.println("Сумма чисел в integers: " + integerSum);
            System.out.println("Среднее значение в integers: " + integerSum / integerCount);
            System.out.println();
        }
    }

    public void initWriter() throws IOException {
        String fileName = "integers.txt";
        String fileDirectory = flags.getOFileDirectory();
        fileName = flags.getpPrefix() != null ? flags.getpPrefix() + fileName : fileName;
        writer = new FileWriter(fileDirectory + fileName, flags.getA());
    }

    public void finish() throws IOException {
        writer.close();
    }
}
