package main.util;

import main.memoria.Cache;

import java.util.Arrays;
import java.util.Locale;

import static main.util.RuntimeData.*;
import static main.util.Util.isInteger;

/**
 * Classe CliParser é a classe que lida com a entrada de argumentos por CLI
 * <nsets> <bsize> <assoc> <    substituição     > <flag_saida> <file_name>
 * <0-LRU 1-RANDOM 2-FIFO>
 */
public class CliParser {
    private final String[] args;
    private final int[] cacheConfig = new int[5];
    private String path;

    /**
     * @return o caminho
     */
    public String getPath() {
        return path;
    }

    /**
     * @param args a serem definidos
     */
    public CliParser(String[] args) {
        this.args = args;
    }

    /**
     * @return uma nova cache com as configurações dadas
     */
    public Cache generateCache() {
        return new Cache(cacheConfig[0], cacheConfig[1], cacheConfig[2], cacheConfig[3]);
    }

    /**
     * @return modo de operação do programa
     */
    public int parse() {
        int argLength = args.length;
        switch (argLength) {
            default -> {
                //System.out.println(args.length);
                if (getOutputFlag() == 0) {
                    System.out.println("[ARGS]|==>Numero de argumentos incorreto. Utilize:");
                    System.out.println("[ARGS]|==>java cache_simulator <nsets> <bsize> <assoc> <substituição> <flag_saida> arquivo_de_entrada");
                }
                System.exit(1);
            }
            case 1 -> {
                System.out.println(Arrays.toString(args));
                return 0;
            }
            case 6 -> {
                if (isInteger(args[0]) && isInteger(args[1]) && isInteger(args[2]) && isInteger(args[4])) {
                    cacheConfig[0] = Integer.parseInt(args[0]); // n_sets
                    cacheConfig[1] = Integer.parseInt(args[1]); // b_size
                    cacheConfig[2] = Integer.parseInt(args[2]); // assoc
//                  cacheConfig[3] = args[3]                    // substituicao
                    cacheConfig[4] = Integer.parseInt(args[4]); // flag
                    if (cacheConfig[4] == 0) {
                        setOutputFlag(0);
                        setDebugMode(1);
                    } else if (cacheConfig[4] == 2) {
                        RuntimeData.setUpLogging(new LogHandler(args));
                        setOutputFlag(0);
                        setDebugMode(1);
                    } else {
                        setOutputFlag(1);
                        setDebugMode(0);
                    }
                    args[3] = args[3].toLowerCase(Locale.ROOT);
                    switch (args[3]) {
                        case "l" -> cacheConfig[3] = 0;
                        case "r" -> cacheConfig[3] = 1;
                        case "f" -> cacheConfig[3] = 2;
                        default -> System.out.println("[ARGS]|==>Erro, modo de substiuição não reconhecido!");
                    }

                    int nsets = cacheConfig[0];
                    int bsize = cacheConfig[1];
                    int assoc = cacheConfig[2];
                    int flagOut = cacheConfig[4];

                    String subst = args[3];
                    String arquivoEntrada = args[5];
                    this.path = arquivoEntrada;

                    if (getOutputFlag() == 0) {
                        System.out.println("[ARGS]|==>Os argumentos inseridos foram:");
                        System.out.printf("[ARGS]|==>nsets = %d\n", nsets);
                        System.out.printf("[ARGS]|==>bsize = %d\n", bsize);
                        System.out.printf("[ARGS]|==>assoc = %d\n", assoc);
                        System.out.printf("[ARGS]|==>subst = %s\n", subst);
                        System.out.printf("[ARGS]|==>flagOut = %d\n", flagOut);
                        System.out.printf("[ARGS]|==>arquivo = %s\n", arquivoEntrada);
                    }
                    return 1;
                }
            }
        }
        System.exit(1);
        return -1;
    }
}
