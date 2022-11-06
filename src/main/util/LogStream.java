package main.util;

import java.io.PrintStream;


/**
 * Uma classe que permite imprimir um dado objeto tanto para o terminal
 * quanto para um arquivo dado, derivada de DualStream:
 * <a href="https://stackoverflow.com/a/65536759/12520896">Código original</a>
 */

public class LogStream extends PrintStream {
    private final PrintStream logOutput = null;
    private final PrintStream consoleOutput;

    public LogStream(final PrintStream consoleOutput, final PrintStream logOutput) {
        super(logOutput, true);
        this.consoleOutput = consoleOutput;

    }

    @Override
    public void println() {
        consoleOutput.println();
        super.println();
    }

    @Override
    public void println(final Object output) {
        consoleOutput.println(output);
        super.println(output);
    }

    @Override
    public void println(final String output) {
        consoleOutput.println(output);
        super.println(output);
    }

    @Override
    public PrintStream printf(final String output, final Object... variables) {
        consoleOutput.printf(output, variables);
        super.printf(output, variables);
        return this;
    }
}
