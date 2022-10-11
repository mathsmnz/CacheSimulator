package main.util;

import java.util.Arrays;

import static main.util.UtilKt.isInteger;

public class CliParser {
    public static Integer parse(String[] args){
        int argLength = args.length;
        switch (argLength) {
            default -> {
                //System.out.println(args.length);
                System.out.println("Numero de argumentos incorreto. Utilize:");
                System.out.println("java cache_simulator <nsets> <bsize> <assoc> <substituição> <flag_saida> arquivo_de_entrada");
                System.exit(1);
            }
            case 1 -> {
                System.out.println(Arrays.toString(args));
                return 0;
            }
            case 6 -> {
                if (isInteger(args[0]) && isInteger(args[1]) && isInteger(args[2]) && isInteger(args[4])){
                    int nsets = Integer.parseInt(args[0]);
                    int bsize = Integer.parseInt(args[1]);
                    int assoc = Integer.parseInt(args[2]);
                    String subst = args[3];
                    int flagOut = Integer.parseInt(args[4]);
                    String arquivoEntrada = args[5];

                    System.out.printf("nsets = %d\n", nsets);
                    System.out.printf("bsize = %d\n", bsize);
                    System.out.printf("assoc = %d\n", assoc);
                    System.out.printf("subst = %s\n", subst);
                    System.out.printf("flagOut = %d\n", flagOut);
                    System.out.printf("arquivo = %s\n", arquivoEntrada);
                    return 1;
                }
            }
        }
        System.exit(1);
        return -1;
    }
}
