package main;

import main.memoria.Cache;
import main.util.CliParser;
import main.util.FileManager;

import java.util.ArrayList;
import java.util.HashSet;

import static main.util.RuntimeData.*;
import static main.util.Util.addressToBinary;
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
                    HashSet<Integer> teste = new HashSet<>(enderecos);
                    System.out.println(teste.size());
                    if (enderecos != null) {
                        setAddressCount(enderecos.size());
                        for (int endereco : enderecos) {
                            cache.find(endereco);
                        }
//                        int currentPos = 0;
//                        for (int endereco : enderecos) {
//                            if (getLinesFilled() != getAddressCount()) {
//                                cache.find(endereco);
//                                currentPos++;
//                            } else {
//                                setMissCapacidade(getAddressCount() - currentPos);
//                                break;
//                            }
//                        }
                        System.out.println(getOutputlog());
                    }
                }
            }

        } else {

            int address = 2575;
            int[] retVal = new int[4];
            String[] vals = new String[4];

            int offset = 2;
            int tag = 22;
            int indice = 8;

            String convertedAddress = addressToBinary(address);

            vals[0] = convertedAddress;
            vals[1] = convertedAddress.substring(0, 32 - (offset + indice));
            vals[2] = convertedAddress.substring(32 - offset, 32);
            vals[3] = convertedAddress.substring(tag, 32 - offset);

            for (String s : vals) {
                System.out.println(s + " " + s.length());
            }

            System.out.println();
        }
    }
}
