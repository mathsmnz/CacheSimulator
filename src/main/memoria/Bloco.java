package main.memoria;

/**
 * classe Bloco
 */
public class Bloco {

    private int endereco = -1;
    private int tag;
    private int indice;
    private int lastUsed;
    private int capacity;
    private int currentUsage;
    private int firstElement;
    private Celula[] celulas;
    
    /**
     * classe Celula
     */
    public class Celula {
        private boolean IsEmpty;
        private boolean IsDirty;
        
        /**
         * 
         * @return se a celula está vazia
         */
        public boolean isEmpty() {
            return IsEmpty;
        }
        
        /**
         * 
         * @param empty muda o estado boolean da célula
         */
        public void setEmpty(boolean empty) {
            IsEmpty = empty;
        }
        
        /**
         * 
         * @return se a celula está suja
         */
        public boolean isDirty() {
            return IsDirty;
        }
        
        /**
         * 
         * @param dirty muda o estado boolean da célula
         */
        public void setDirty(boolean dirty) {
            IsDirty = dirty;
        }
        
        /**
         * Construtor Celula
         */
        public Celula() {
            this.IsDirty = false;
            this.IsEmpty = true;
        }
    }
    
    /**
     * 
     * @return primeiro elemento
     */
    public int getFirstElement() {
        return firstElement;
    }
    
    /**
     * 
     * @param firstElement primeiro elemento da fila
     */
    public void setFirstElement(int firstElement) {
        this.firstElement = firstElement;
    }
    
    /**
     * 
     * @return uso da cache
     */
    public int getCurrentUsage() {
        return currentUsage;
    }
    
    /**
     * 
     * @param currentUsage 
     */
    public void setCurrentUsage(int currentUsage) {
        this.currentUsage = currentUsage;
    }
    
    /**
     * 
     * @return 
     */
    public Celula[] getCelulas() {
        return celulas;
    }
    
    /**
     * 
     * @param celulas
     */
    public void setCelulas(Celula[] celulas) {
        this.celulas = celulas;
        for(int i = 0; i < getCapacity(); i++){
            this.celulas[i] = new Celula();
        }
    }
    
    /**
     * 
     * @return retorna capacidade
     */
    public int getCapacity() {
        return capacity;
    }
    
    /**
     * 
     * @param capacity
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
    
    /**
     * 
     * @return retorna o endereco
     */
    public int getEndereco() {
        return endereco;
    }
    
    /**
     * 
     * @param endereco
     */
    public void setEndereco(int endereco) {
        this.endereco = endereco;
    }
    
    /**
     * 
     * @return retorna a tag
     */
    public int getTag() {
        return tag;
    }
    
    /**
     * 
     * @param tag
     */
    public void setTag(int tag) {
        this.tag = tag;
    }
    
    /**
     * 
     * @return retorna o indice
     */
    public int getIndice() {
        return indice;
    }
    
    /**
     * 
     * @param indice
     */
    public void setIndice(int indice) {
        this.indice = indice;
    }
    
    /**
     * 
     * @return retorna o ultimo acesso
     */
    public int getLastUsed() {
        return lastUsed;
    }
    
    /**
     * 
     * @param lastUsed
     */
    public void setLastUsed(int lastUsed) {
        this.lastUsed = lastUsed;
    }
    
    /**
     * 
     * @param indice 
     * @param offset
     */
    public Bloco(int indice, int offset) {
        setIndice(indice);
        setCapacity((int) Math.pow(offset, 2));
        setCelulas(new Celula[getCapacity()]);
    }

    /**
     * 
     * @param offset
     * @return valores 
     * Erro -> -1
     * Ha espaco -> 0
     * Miss de Conflito -> 1
     * Miss de capacidade -> 2
     */
    public int access(int offset) {
        if (getCelulas() == null) {
            return -1;
        } else {
            if (getCurrentUsage() == getCapacity()) {
                return 2;
            } else {
                if(getCurrentUsage() == 0){
                    setFirstElement(offset);
                }
                if (getCelulas()[offset].isEmpty()) {
                    setCurrentUsage(getCurrentUsage() + 1);
                    getCelulas()[offset].setEmpty(false);
                    setLastUsed(offset);
                    return 0;
                } else {
                    getCelulas()[offset].setDirty(true);
                    setLastUsed(offset);
                    return 1;
                }
            }
        }
    }

}
