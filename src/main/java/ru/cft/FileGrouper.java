package ru.cft;

import java.io.*;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

public class FileGrouper {
    private LongStrategy longStrategy;
    private FloatStrategy floatStrategy;
    private StringStrategy stringStrategy;

    public void grout(String[] args) {
            Flags flags = new Flags();
            Iterator<String> iter = Arrays.asList(args).iterator();
            String fileName = skipFlagsAndGetFirstFileName(iter, flags);
            if (fileName == null) {
                System.out.println("В аргументах нет названия файла");
                return
            }
            readAndGroupFile(fileName, flags);
            while (iter.hasNext()) {
                readAndGroupFile(iter.next(), flags);
            }
            if (longStrategy != null) {
                longStrategy.printStatistic(flags);
            } else {
                System.out.println("integers не инициализирована.");
            }

            if (floatStrategy != null) {
                floatStrategy.printStatistic(flags);
            } else {
                System.out.println("floats не инициализирована.");
            }

            if (stringStrategy != null) {
                stringStrategy.printStatistic(flags);
            } else {
                System.out.println("strings не инициализирована.");
            }
        try {
            if(longStrategy != null) {
                longStrategy.finish();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            if(floatStrategy != null) {
                floatStrategy.finish();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            if(stringStrategy != null) {
                stringStrategy.finish();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void readAndGroupFile(String fileName, Flags flags) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line = br.readLine();
            while (line != null) {
                Long l = tryLong(line);
                if(l != null) {
                    if(longStrategy == null) {
                        longStrategy = new LongStrategy(flags);
                    }
                    longStrategy.parse(l);
                } else {
                    Float f = tryFloat(line);
                    if (f != null) {
                        if (floatStrategy == null) {
                            floatStrategy = new FloatStrategy(flags);
                        }
                        floatStrategy.parse(f);
                    } else {
                        if(stringStrategy == null) {
                            stringStrategy = new StringStrategy(flags);
                        }
                        if(!line.isEmpty()) {
                            stringStrategy.parse(line);
                        }
                    }
                }
                line = br.readLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Не найден файл по пути: " + fileName + "\nФайл будет проигнорирован.\n");
        } catch (Exception e) {
            System.out.println("Ошибка при чтении файла: " + fileName);
        }
    }

    public Long tryLong(String line) {
        try {
            return Long.parseLong(line);
        } catch (Exception e) {
            return null;
        }
    }

    public Float tryFloat(String line) {
        try {
            return Float.parseFloat(line);
        } catch (Exception e) {
            return null;
        }
    }
        private String skipFlagsAndGetFirstFileName (Iterator < String > iter, Flags flags) {
            String currentElement = iter.hasNext() ? iter.next() : null;
            if (currentElement != null) {
                while (currentElement.startsWith("-") && iter.hasNext()) {
                    checkFlags(currentElement, iter, flags);
                    currentElement = iter.next();
                }
            }
            return currentElement;
        }

        private void checkFlags (String currentElement, Iterator < String > iter, Flags flags) {
            if (Objects.equals(currentElement, "-o")) {
                String next = iter.next();
                if (!next.startsWith("-")) {
                    flags.setO(true);
                    flags.setoPath(next);
                } else {
                    checkFlags(next, iter, flags);
                }
            }
            if (Objects.equals(currentElement, "-p")) {
                String next = iter.next();
                if (!next.startsWith("-")) {
                    flags.setP(true);
                    flags.setpPrefix(next);
                } else {
                    checkFlags(next, iter, flags);
                }
            }
            if (Objects.equals(currentElement, "-a")) {
                flags.setA(true);
            }
            if (Objects.equals(currentElement, "-s")) {
                flags.setS(true);
            }
            if (Objects.equals(currentElement, "-f")) {
                flags.setF(true);
            }
        }
}