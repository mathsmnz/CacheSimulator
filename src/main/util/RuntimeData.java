package main.util;

import java.util.LinkedList;
import java.util.Queue;

public class RuntimeData {
    private static int outputFlag = 1;
    private static int debugMode = 0;
    private static int addressCount = 1;
    public static int linesFilled = 0;
    private static int missCompulsorio = 0;
    private static int missConflito = 0;
    private static int missCapacidade = 0;
    static Queue<Integer> q = new LinkedList<>();

    public static void setQueue(int posicao) {
        q.add(posicao);
    }

    public static Queue<Integer> getQueue() {
        return q;
    }

    public static Queue<Integer> removeQueue(){
        q.remove();
        return q;
    }

    public static void setLinesFilled(int amount){
        linesFilled = linesFilled + amount;
    }

    public static int getLinesFilled(){
        return RuntimeData.linesFilled;
    }

    public static void setMissCompulsorio(int amount) {
        missCompulsorio = missCompulsorio + amount;
    }

    public static void setMissCapacidade(int amount) {
        missCapacidade = missCapacidade + amount;
    }

    public static void setMissConflito(int amount) {
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
