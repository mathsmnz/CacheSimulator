package main;

import main.memoria.Cache;
import main.util.CliParser;
import main.util.FileManager;

import java.util.ArrayList;

import static main.util.RuntimeData.*;
import static main.util.Util.*;

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
                            cache.find(endereco);
                        }
                        System.out.println(getOutputlog());
                    }
                }
            }

        } else {
            for (int i = 0; i < 100; i++) {
                System.out.println(getRandom(1));
            }
        }
    }
}
