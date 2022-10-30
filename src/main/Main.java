package main;

import main.memoria.Cache;
import main.util.CliParser;
import main.util.FileManager;

import java.util.ArrayList;
import java.util.HashSet;

import static main.util.RuntimeData.*;
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
                    if (enderecos != null) {
                        setAddressCount(enderecos.size());
                        for (int endereco : enderecos) {
                            if (getOutputFlag() == 0) {
                                cache.find(endereco);
                            }
                        }
                        System.out.println(getOutputlog());
                    }
                }
            }
        } else {
            ArrayList<Integer> enderecos = fileReader(args[5]);
            HashSet<Integer> possibleReads = new HashSet<>(enderecos);
            int enderecoLido = 13;
            if (possibleReads.add(enderecoLido)) {
                //Do stuff
            } else {
                //Erro a decidir
            }
        }
    }
}
