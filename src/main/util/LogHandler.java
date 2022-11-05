package main.util;

import java.io.*;

import static main.util.FileManager.fileCreator;
import static main.util.Util.generateTimestampedFileName;

/**
 * Classe responsável por inicializar e configurar como
 * serão feitos os logs do programa
 **/
public class LogHandler {
    private String[] args = null;
    private String path = null;

    public LogHandler(String[] args) {
        this.args = args;
        this.path = generateTimestampedFileName(this.args);
        log();
    }
    /**
     * Método responsável por configurar a Printstream do log
     **/
    private void log() {
        try {
            if (!fileCreator(path)) {
                System.out.println("[IO]||===> O arquivo será sobrescrito ao prosseguir");
            }

            PrintStream terminalOut = System.out;
            PrintStream fileOut = new PrintStream(new BufferedOutputStream(new FileOutputStream(path)));
            LogStream log = new LogStream(terminalOut, fileOut);
            System.setOut(log);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
