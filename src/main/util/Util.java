package main.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Random;

/**
 * Classe Util é uma classe que contém métodos utilitários
 */
public class Util {

    /**
     * Método responsável por gerar o nome do arquivo de log
     * com base nos parametros do benchmark e timestamp da execucao
     * @param args uma array de string com os argumentos
     * @return String com o nome de arquivo
     **/
    public static String generateTimestampedFileName(String[] args) {
        String inputArgs = "-" + args[0] + "-" + args[1] + "-" + args[2] + "-" + args[3] + ".txt";
        String date = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Timestamp(System.currentTimeMillis()));
        return date + inputArgs;
    }

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
     * @return numero aleatório
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
        System.out.println("Para executar o programa se utiliza este comando base:");
    }

    /**
     * Método para converter um endereço em uma ‘string’ de 32
     * @param address recebe endereço
     * @return retorna uma ‘string’ do endereço em 32b
     */
    public static String addressToBinary(int address) {
        String s = Integer.toBinaryString(address);
        return "00000000000000000000000000000000".substring(s.length()) + s;
    }

    /**
     * Método responsável por verificar se uma ‘string’ pode ser convertida
     * para int
     * @param input String a ser verificada
     * @return possíveis retornos: <br>
     *                              <em>false</em> → se não for possível converter
     *                              <em>true</em> → se for possível converter
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
