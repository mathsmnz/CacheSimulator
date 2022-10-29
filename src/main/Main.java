package main;

import main.memoria.Cache;
import main.util.CliParser;
import main.util.FileManager;

import java.util.ArrayList;

import static main.util.RuntimeData.getDebugMode;
import static main.util.Util.printHelp;

public class Main extends FileManager {
    public static void main(String[] args) {
        if (getDebugMode() == 0) {
            CliParser parser = new CliParser(args);
            int op = parser.parse();
            switch (op) {
                case 0 -> printHelp();
                case 1 -> {
                    Cache cache = parser.generateCache();
                    ArrayList<Integer> enderecos = fileReader(parser.getPath());
                }
            }
        } else {
            Cache cache = new Cache(256, 2, 4, 2);
            int[] arr = cache.decode(167);
            System.out.println(arr[0]);
            System.out.println(arr[1]);
            System.out.println(arr[2]);
            System.out.println(arr[3]);
        }
    }
}
