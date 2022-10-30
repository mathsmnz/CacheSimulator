package main.util;

import java.util.Random;

/**
 * Classe Util
 */
public class Util {

    /**
     * Método de conversão
     * @param input numero para converter para base 2
     * @return base 2
     */
    public static int log2(int input){
        return (int)(Math.log(input) / Math.log(2));
    }

    /**
     * Método random
     * @param upper numero máximo de random
     * @return numero aleatorio
     */
    public static int getRandom(int upper) {
        Random r =  new Random();
        return r.nextInt(upper);
    }

    /**
     * Método auxiliar
     */
    public static void printHelp(){
        System.out.println("TO DO");
    }

    /**
     *
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
