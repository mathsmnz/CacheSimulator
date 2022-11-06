package main.util;

import java.io.*;
import java.util.ArrayList;

import static main.util.RuntimeData.getOutputFlag;

/**
 * Classe FileManager
 * Manipula os arquivos
 */
public class FileManager {

    /**
     * Responsável por criar o arquivo caso necessário
     *
     * @param path caminho do arquivo
     * @return retorna false se houver erro na execucão
     */
    public static boolean fileCreator(String path) {
        try {
            File arquivo = new File(path);
            if (arquivo.createNewFile()) {
                if (getOutputFlag() == 0) {
                    System.out.println("[IO]|==>Arquivo criado com sucesso!");
                    System.out.println("[IO]|==>" + arquivo.getName());
                    System.out.println("[IO]|==>" + arquivo.getAbsolutePath());
                }
                return true;
            } else {
                if (getOutputFlag() == 0) {
                    System.out.println("[IO]|==>Arquivo já existente");
                }
                return false;
            }
        } catch (IOException e) {
            if (getOutputFlag() == 0) {
                System.err.println("[IO]|==>Um erro foi encontrado");
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * Converte um int para um byte de acordo com o arquivo de entrada
     *
     * @param input valor a ser convertido
     * @return "valor" convertido
     */
    private static byte[] intToByte(int input) {
        byte[] retVal = new byte[4];

        retVal[3] = (byte) (input & 0xFF);
        retVal[2] = (byte) ((input >> 8) & 0xFF);
        retVal[1] = (byte) ((input >> 16) & 0xFF);
        retVal[0] = (byte) ((input >> 24) & 0xFF);

        return retVal;
    }

    /**
     * Responsável por escrever os dados fornecidos
     * pelo usuário em um arquivo de saída
     *
     * @param path  caminho para escrita
     * @param input lista com os numeros a serem escritos
     * @return arquivo de saída
     */
    protected static boolean fileWriter(String path, ArrayList<Integer> input) {
        try {
            FileOutputStream arquivo = new FileOutputStream(path);
            File tempFile = new File(path);
            if (!tempFile.exists()) {
                fileCreator(path);
            }
            byte[] val;
            for (int i : input) {
                val = intToByte(i);
                arquivo.write(val[0]);
                arquivo.write(val[1]);
                arquivo.write(val[2]);
                arquivo.write(val[3]);
            }
            arquivo.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Lê o arquivo binário
     *
     * @param arquivo de entrada
     * @return -1 em caso de erro
     */
    private static int binReader(FileInputStream arquivo) {
        try {
            byte[] bytes = arquivo.readNBytes(4);
            return ((bytes[0] & 0xFF) << 24) |
                    ((bytes[1] & 0xFF) << 16) |
                    ((bytes[2] & 0xFF) << 8) |
                    ((bytes[3] & 0xFF));
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * Responsável por ler os arquivos, devolvendo uma ArrayList com o que foi lido
     *
     * @param path caminho do arquivo a ser lido
     * @return ArrayList do que foi lido
     */
    public static ArrayList<Integer> fileReader(String path) {
        try {
            if (fileChecker(path)) {
                ArrayList<Integer> retval = new ArrayList<>();
                FileInputStream arquivo = new FileInputStream(path);
                retval.add(binReader(arquivo));
                while (arquivo.available() > 0) {
                    retval.add(binReader(arquivo));
                }
                arquivo.close();
                if (getOutputFlag() == 0) {
                    System.out.println("[IO]|===|Foram lidas " + retval.size() + " linhas");
                }
                return retval;
            } else {
                return null;
            }
        } catch (FileNotFoundException e) {
            if (getOutputFlag() == 0) {
                System.err.println("[IO]|==>Arquivo não encontrado");
                e.printStackTrace();
            }
            return null;
        } catch (IOException e) {
            if (getOutputFlag() == 0) {
                System.err.println("[IO]|==>Exception de IO");
                e.printStackTrace();
            }
            return null;
        }
    }

    /**
     * Verifica se o arquivo existe e é de fato um arquivo,
     * depois devolvendo se a operação foi um sucesso
     *
     * @param path caminho do arquivo a ser verificado
     * @return uma mensagem ao usuário relevante á operação feita
     */
    protected static boolean fileChecker(String path) {
        File arquivo = new File(path);
        if (arquivo.isFile() && arquivo.exists()) {
            if (getOutputFlag() == 0) {
                System.out.println("[IO]|===| O arquivo \"" + path + "\" foi encontrado");
            }
            return true;
        } else {
            if (getOutputFlag() == 0) {
                System.err.println("[IO]|===| O arquivo \"" + path + "\" não foi encontrado");
            }
            return false;
        }
    }
}
