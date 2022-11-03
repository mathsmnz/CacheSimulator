package main.util;

import java.util.Random;

/**
 * Classe Util
 */
public class Util {

    /**
     * Método responsável por calcular o log2
     * @param input numero para converter para base 2
     * @return base 2
     */
    public static int log2(int input) {
        if (input > 0) {
            return (int) (Math.log(input) / Math.log(2));
        } else {
            return 0;
        }
    }

    /**
     * Método responsável por gerar um número pseudo aleatório
     * @param upper numero máximo de random
     * @return numero aleatorio
     */
    public static int getRandom(int upper) {
        if (upper > 0) {
            Random r = new Random();
            return r.nextInt(upper + 1);
        } else {
            return 0;
        }
    }

    /**
     * Método que irá imprimir ajuda de como operar o programa
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
     * Método responsável por verificar se uma string pode ser convertida
     * para int
     * @param input String a ser verificada
     * @return false se não for possível converter
     * @return true se for possível converter
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
