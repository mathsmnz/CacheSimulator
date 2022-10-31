package main.util;

import java.util.Random;

/**
 * Classe Util
 */
public class Util {

    /**
     * Método de conversão
     *
     * @param input numero para converter para base 2
     * @return base 2
     */
    public static int log2(int input) {
        if (input > 0) {
            return (int) (Math.log(input) / Math.log(2));
        } else {
            return 1;
        }
    }

    /**
     * Método random
     *
     * @param upper numero máximo de random
     * @return numero aleatorio
     */
    public static int getRandom(int upper) {
        if (upper > 0) {
            Random r = new Random();
            return r.nextInt(upper);
        } else {
            return 0;
        }
    }

    /**
     * Método auxiliar
     */
    public static void printHelp() {
        System.out.println("TO DO");
    }

    /**
     * Método para converter um endereco em uma string de 32
     * @param address recebe endereco
     * @return retorna uma string do endereco em 32b
     */
    public static String addressToBinary(int address) {
        String s = Integer.toBinaryString(address);
        return "00000000000000000000000000000000".substring(s.length()) + s;
    }

    /**
     * @param input
     * @return
     */
    public static Boolean isInteger(String input) {
        if (input == null) {
            return false;
        } else {
            int length = input.length();
            if (length == 0) {
                return false;
            }
            int i = 0;
            if (input.charAt(0) == '-') {
                if (length == 1) {
                    return false;
                }
                i = 1;
            }
            while (i < length) {
                char c = input.charAt(i);
                if (c < '0' || c > '9') {
                    return false;
                }
                i++;
            }
            return true;
        }
    }
}
