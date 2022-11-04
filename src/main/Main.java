package main;

import main.memoria.Cache;
import main.util.CliParser;
import main.util.FileManager;
import main.util.LogHandler;
import main.util.LogStream;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static main.util.RuntimeData.*;
import static main.util.Util.*;

public class Main extends FileManager {
    public static void main(String[] args) throws FileNotFoundException {
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
            System.out.println("Debug");
        }
    }
}
