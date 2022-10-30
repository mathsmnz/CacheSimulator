package main.util;

/**
 * Classe RuntimeData
 * classe contem métodos necessários para a contagem de hits e misses
 * também retorna uma string de output com o formato flag_saida escolhido
 */
public class RuntimeData {
    private static int outputFlag = 1;
    private static int debugMode = 0;
    private static int addressCount = 1;
    public static int linesFilled = 0;
    private static int missCompulsorio = 0;
    private static int missConflito = 0;
    private static int missCapacidade = 0;
    
    /**
     * 
     * @param amount contagem de linhas ocupadas
     */
    public static void setLinesFilled(int amount){
        linesFilled = linesFilled + amount;
    }
    
     /**
     * 
     * @return numero de linhas ocupadas
     */
    public static int getLinesFilled(){
        return RuntimeData.linesFilled;
    }
    
    /**
     * 
     * @param amount conta o número de misses compulsorios
     */
    public static void setMissCompulsorio(int amount) {
        missCompulsorio = missCompulsorio + amount;
    }
    
    /**
     * 
     * @param amount conta o número de misses de capacidade
     */
    public static void setMissCapacidade(int amount) {
        missCapacidade = missCapacidade + amount;
    }
    
    /**
     * 
     * @param amount conta o numero de misses de conflito
     */
    public static void setMissConflito(int amount) {
        missConflito = missConflito + amount;
    }
    
    /**
     * 
     * @return retorna o numero de misses compulsorios
     */
    public static int getMissCompulsorio() {
        return missCompulsorio;
    }
    
    /**
     * 
     * @return retorna o numero de misses de conflito
     */
    public static int getMissConflito() {
        return missConflito;
    }
    
    /**
     * 
     * @return retorna o numero de misses de capacidade
     */
    public static int getMissCapacidade() {
        return missCapacidade;
    }
    
    /**
     * 
     * @return retorna o número de acessos
     */
    public static int getAddressCount() {
        return addressCount;
    }
    
    /**
     * 
     * @param addressCount cota o número de acessos à cache
     */
    public static void setAddressCount(int addressCount) {
        if (addressCount != 0) {
            RuntimeData.addressCount = addressCount;
        }
    }
    
   /**
     * Método getOutputlog
     * totalMisses = soma de todos os misses(Compulsorio + Capacidade + Conflito)
     * if de total misses -> em caso de misses derem zero. Para não dar erro na hitrate
     * compulsoryMissRate -> ratio de miss compulsorio  
     * capacityMissRate -> ratio de miss capacidade
     * conflictMissRate -> ratio de miss conflito 
     * totalHits -> hits totais
     * hitRate -> ratio de hits totais
     * missRate -> ratio de misses totais
     * @return string final com o formato escolhido
     */
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
    
    /**
     * 
     * @return flag do output
     */
    public static int getOutputFlag() {
        return outputFlag;
    }
    
    /**
     * 
     * @param outputFlag variavel que determina o formato da saida
     */
    public static void setOutputFlag(int outputFlag) {
        RuntimeData.outputFlag = outputFlag;
    }
    
    /**
     * 
     * @return debug
     */
    public static int getDebugMode() {
        return debugMode;
    }
    
    /**
     * 
     * @param debugMode muda o estado do debug
     */
    public static void setDebugMode(int debugMode) {
        RuntimeData.debugMode = debugMode;
    }


}
