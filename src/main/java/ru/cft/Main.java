package ru.cft;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
          FileGrouper fileGrouper = new FileGrouper();
          fileGrouper.grout(args);
    }
}