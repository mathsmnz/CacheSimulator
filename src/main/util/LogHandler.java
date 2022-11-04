package main.util;

import java.io.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import static main.util.FileManager.fileCreator;

public class LogHandler {
    private String[] args = null;
    private String path = null;

    public String generateTimestampedFileName() {
        String inputArgs = "-" + args[0] + "-" + args[1] + "-" + args[2] + "-" + args[3] + ".txt";
        String date = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Timestamp(System.currentTimeMillis()));
        return date + inputArgs;
    }

    public LogHandler(String[] args) {
        this.args = args;
        this.path = generateTimestampedFileName();
        log();
    }

    private void log() {
        try {
            if (!fileCreator(path)) {
                System.out.println("[IO]||===> O arquivo será sobrescrito ao prosseguir");
            }

            PrintStream terminalOut = System.out;
            PrintStream fileOut = new PrintStream(new BufferedOutputStream(new FileOutputStream(path)));
            LogStream log = new LogStream(terminalOut, fileOut);
            System.setOut(log);
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }

}
