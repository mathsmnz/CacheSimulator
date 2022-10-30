package main;

import main.memoria.Cache;
import main.util.CliParser;
import main.util.FileManager;

import java.util.ArrayList;

import static main.util.RuntimeData.getDebugMode;
import static main.util.RuntimeData.getOutputlog;
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
                    if(enderecos != null){
                        for (int endereco: enderecos) {
                            System.out.println("TODO");
                        }
                    }
                    System.out.println(getOutputlog());
                }
            }
        } else {
            System.out.println("INSERT TEST HERE");
        }
    }
}
