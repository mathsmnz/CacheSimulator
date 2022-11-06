package main.util;

/**
 * Classe contem métodos necessários para a contagem de acertos e misses
 * também retorna uma ‘string’ de resultado com o formato flag_saida escolhido
 */
public class RuntimeData {
    private static int outputFlag = 1;
    private static int debugMode = 0;
    private static int addressCount = 1;
    private static int linesFilled = 0;
    private static int missCompulsorio = 0;
    private static int missConflito = 0;
    private static int missCapacidade = 0;
    private static LogHandler log;

    /**
     * @param amount contagem de linhas ocupadas
     */
    public static void setLinesFilled(int amount) {
        linesFilled = linesFilled + amount;
    }

    /**
     * @return numero de linhas ocupadas
     */
    public static int getLinesFilled() {
        return RuntimeData.linesFilled;
    }

    /**
     * @param amount conta o número de misses compulsórios
     */
    public static void setMissCompulsorio(int amount) {
        missCompulsorio = missCompulsorio + amount;
    }

    /**
     * @param amount conta o número de misses de capacidade
     */
    public static void setMissCapacidade(int amount) {
        missCapacidade = missCapacidade + amount;
    }

    /**
     * @param amount conta o numero de misses de conflito
     */
    public static void setMissConflito(int amount) {
        missConflito = missConflito + amount;
    }

    public static void setUpLogging(LogHandler logHandler) {
        RuntimeData.log = logHandler;
    }

    /**
     * @return retorna o número de misses compulsórios
     */
    public static int getMissCompulsorio() {
        return missCompulsorio;
    }

    /**
     * @return retorna o número de misses de conflito
     */
    public static int getMissConflito() {
        return missConflito;
    }

    /**
     * @return retorna o número de misses de capacidade
     */
    public static int getMissCapacidade() {
        return missCapacidade;
    }

    /**
     * @return retorna o número de acessos
     */
    public static int getAddressCount() {
        return addressCount;
    }

    /**
     * @param addressCount cota o número de acessos à cache
     */
    public static void setAddressCount(int addressCount) {
        if (addressCount != 0) {
            RuntimeData.addressCount = addressCount;
        }
    }

    /**
     * Método getOutputLog é o método que retorna uma String com os resultados do benchmark
     * totalMisses = soma de todos os misses (Compulsório + Capacidade + Conflito) <br>
     * if de totalMisses → em caso de misses derem zero. Para não dar erro na hitrate <br>
     * compulsoryMissRate → ratio de miss compulsório <br>
     * capacityMissRate → ratio de miss capacidade <br>
     * conflictMissRate → ratio de miss conflito <br>
     * totalHits → hits totais <br>
     * hitRate → ratio de hits totais <br>
     * missRate → ratio de misses totais <br>
     *
     * @return string final com o formato escolhido
     */
    public static String getOutputLog() {
        String retVal = "";

        int totalMisses = getMissCapacidade() + getMissCompulsorio() + getMissConflito();
        int temp = 0;
        if (totalMisses == 0) {
            temp = 1;
            totalMisses = 1;
        }

        float compulsoryMissRate = (float) getMissCompulsorio() / (float) totalMisses;
        float capacityMissRate = (float) getMissCapacidade() / (float) totalMisses;
        float conflictMissRate = (float) getMissConflito() / (float) totalMisses;

        if (temp == 1) {
            totalMisses = 0;
        }

        int totalHits = getAddressCount() - totalMisses;
        float hitRate = (float) totalHits / (float) getAddressCount();
        float missRate = 1 - hitRate;
        if (RuntimeData.getDebugMode() == 1) {
            retVal = String.format("""
                    [CACHE]|==> Resultados do benchmark
                    [Total Access]|==> %d
                    [Total Hits]|==> %d
                    [Total Misses]|==> %d
                    [Compulsory Misses]|==> %d
                    [Capacity Misses]|==> %d
                    [Conflict Misses]|==> %d
                    [Hit Rate]|==> %.4f
                    [Miss Rate]|==> %.4f
                    [Compulsory miss Rate]|==> %.2f
                    [Capacity miss Rate]|==> %.2f
                    [Conflict miss Rate]|==> %.2f
                    """, getAddressCount(), totalHits, totalMisses, getMissCompulsorio(), getMissCapacidade(), getMissConflito(), hitRate, missRate, compulsoryMissRate, capacityMissRate, conflictMissRate);
        } else {
            retVal = String.format("%d %.4f %.4f %.2f %.2f %.2f", getAddressCount(), hitRate, missRate, compulsoryMissRate, capacityMissRate, conflictMissRate);
        }
        return retVal;

    }

    /**
     * @return flag do output
     */
    public static int getOutputFlag() {
        return outputFlag;
    }

    /**
     * @param outputFlag variável que determina o formato da saida
     */
    public static void setOutputFlag(int outputFlag) {
        RuntimeData.outputFlag = outputFlag;
    }

    /**
     * @return debug
     */
    public static int getDebugMode() {
        return debugMode;
    }

    /**
     * @param debugMode muda o estado do debug
     */
    public static void setDebugMode(int debugMode) {
        RuntimeData.debugMode = debugMode;
    }


}
