package main.util;

import java.io.*;
import java.util.ArrayList;

public class FileManager {
    //Responsável por criar o arquivo caso necessário
    protected static boolean fileCreator(String path) {
        try{
            File arquivo = new File(path);
            if(arquivo.createNewFile()){
                System.out.println("|==>Arquivo criado com sucesso!");
                System.out.println("|==>"+arquivo.getName());
                System.out.println("|==>"+arquivo.getAbsolutePath());
                return true;
            }else{
                System.out.println("|==>Arquivo já existente");
                return false;
            }
        }catch (IOException e){
            System.err.println("|==>Um erro foi encontrado");
            e.printStackTrace();
        }
        return false;
    }

    private static byte[] intToByte(int input){
        byte[] retVal = new byte[4];

        retVal[3] = (byte) (input & 0xFF);
        retVal[2] = (byte) ((input >> 8) & 0xFF);
        retVal[1] = (byte) ((input >> 16) & 0xFF);
        retVal[0] = (byte) ((input >> 24) & 0xFF);

        return retVal;
    }

    //Responsável por escrever os dados fornecidos pelo usuário em um arquivo de texto
    protected static boolean fileWriter(String path, ArrayList<Integer> input) {
        try {
            FileOutputStream arquivo = new FileOutputStream(path);
            File tempFile = new File(path);
            if(!tempFile.exists()){
                fileCreator(path);
            }
            byte[] val;
            for (int i: input) {
                val = intToByte(i);
                arquivo.write(val[0]);
                arquivo.write(val[1]);
                arquivo.write(val[2]);
                arquivo.write(val[3]);
            }
            arquivo.close();
            return true;
        }catch (IOException e){
            e.printStackTrace();
            return false;
        }
    }

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

    //Responsável por ler os arquivos, devolvendo uma List com o que foi lido
    protected static ArrayList<Integer> fileReader(String path) {
        try {
            ArrayList<Integer> retval = new ArrayList<>();
            FileInputStream arquivo = new FileInputStream(path);
            retval.add(binReader(arquivo));
            while (arquivo.available() > 0) {
                retval.add(binReader(arquivo));
            }
            arquivo.close();
            return retval;
        } catch (FileNotFoundException e) {
            System.err.println("|==>Erro! Arquivo nao encontrado");
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    //Verifica se o arquivo existe e é de fato um arquivo, depois devolvendo se a operação foi um sucesso
    //Retorna também, uma mensagem ao usuário relevante á operação feita
    protected static boolean fileChecker(String path, String format) {
        File arquivo = new File(path + format);
        if (arquivo.isFile() && arquivo.exists()) {
            System.out.println("|===| O arquivo \"" + path + "\" foi encontrado");
            return true;
        } else {
            System.out.println("|===| O arquivo \"" + path + "\" não foi encontrado");
            return false;
        }
    }
}
