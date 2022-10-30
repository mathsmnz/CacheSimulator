package main;

import main.memoria.Cache;
import main.util.CliParser;
import main.util.FileManager;

import java.util.ArrayList;

import static main.util.RuntimeData.*;
import static main.util.Util.getRandom;
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
                        setAddressCount(enderecos.size());
                        for (int endereco: enderecos) {
                            if(getOutputFlag() == 0){
                                System.out.println("TODO");
                            }
                        }
                        System.out.println(getOutputlog());
                    }
                }
            }
        } else {
            CliParser parser = new CliParser(args);
            ArrayList<Integer> end = fileReader(args[5]);
            for(int i = 0; i < end.size(); i++){
                System.out.printf("%d -- > %d\n", i, getRandom(5));
            }
        }
    }
}
