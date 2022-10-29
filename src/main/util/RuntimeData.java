package main.util;

public class RuntimeData {
    private static int outputFlag = 1;
    private static int debugMode = 1;
    private static int missCompulsorio = 0;
    private static int missConflito = 0;
    private static int missCapacidade = 0;

    public void setMissCompulsorio(int amount) {
        missCompulsorio = missCompulsorio + amount;
    }

    public void setMissCapacidade(int amount) {
        missCapacidade = missCapacidade + amount;
    }

    public void setMissConflito(int amount) {
        missConflito = missConflito + amount;
    }

    public int getMissCompulsorio() {
        return missCompulsorio;
    }

    public int getMissConflito() {
        return missConflito;
    }

    public int getMissCapacidade() {
        return missCapacidade;
    }

    public static int getOutputFlag() {
        return outputFlag;
    }

    public static void setOutputFlag(int outputFlag) {
        RuntimeData.outputFlag = outputFlag;
    }

    public static int getDebugMode() {
        return debugMode;
    }

    public static void setDebugMode(int debugMode) {
        RuntimeData.debugMode = debugMode;
    }


}
