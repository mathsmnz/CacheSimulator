package main.util;

public class RuntimeData {
    private static int outputFlag = 1;
    private static int debugMode = 1;
    private static int addressCount = 1;
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

    public static int getMissCompulsorio() {
        return missCompulsorio;
    }

    public static int getMissConflito() {
        return missConflito;
    }

    public static int getMissCapacidade() {
        return missCapacidade;
    }

    public static int getAddressCount() {
        return addressCount;
    }

    public static void setAddressCount(int addressCount) {
        if (addressCount != 0) {
            RuntimeData.addressCount = addressCount;
        }
    }

    public static String getOutputlog() {
        int totalMisses = getMissCapacidade() + getMissCompulsorio() + getMissConflito();

        if (totalMisses == 0) totalMisses = 1;

        float compulsoryMissRate = (float) getMissCompulsorio() / (float) totalMisses;
        float capacityMissRate = (float) getMissCapacidade() / (float) totalMisses;
        float conflictMissRate = (float) getMissConflito() / (float) totalMisses;
        int totalHits = getAddressCount() - totalMisses;
        float hitRate = (float) totalHits / (float) getAddressCount();
        float missRate = 1 - hitRate;
        return String.format("%d %.2f %.2f %.2f %.2f %.2f", getAddressCount(), hitRate, missRate, compulsoryMissRate, capacityMissRate, conflictMissRate);

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
