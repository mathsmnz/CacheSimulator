package main.memoria;

public class Bloco {

    private int endereco;
    private int tag;
    private int indice;
    private int lastUsed;
    private int capacity;
    private int currentUsage;
    private Celula[] celulas;

    public class Celula {
        public boolean isEmpty() {
            return IsEmpty;
        }
        public void setEmpty(boolean empty) {
            IsEmpty = empty;
        }
        public boolean isDirty() {
            return IsDirty;
        }
        public void setDirty(boolean dirty) {
            IsDirty = dirty;
        }

        private boolean IsEmpty;
        private boolean IsDirty;

        public Celula() {
            this.IsDirty = false;
            this.IsEmpty = true;
        }
    }

    public int getCurrentUsage() {
        return currentUsage;
    }
    public void setCurrentUsage(int currentUsage) {
        this.currentUsage = currentUsage;
    }
    public Celula[] getCelulas() {
        return celulas;
    }
    public void setCelulas(Celula[] celulas) {
        this.celulas = celulas;
    }
    public int getCapacity(){return capacity;}
    public void setCapacity(int capacity){
        this.capacity = capacity;
    }
    public int getEndereco() {
        return endereco;
    }
    public void setEndereco(int endereco) {
        this.endereco = endereco;
    }
    public int getTag() {
        return tag;
    }
    public void setTag(int tag) {
        this.tag = tag;
    }
    public int getIndice() {
        return indice;
    }
    public void setIndice(int indice) {
        this.indice = indice;
    }
    public int getLastUsed() {
        return lastUsed;
    }
    public void setLastUsed(int lastUsed) {
        this.lastUsed = lastUsed;
    }

    public Bloco(int indice, int offset) {
        setIndice(indice);
        setCapacity((int) Math.pow(offset, 2));
        Celula[] celulas = new Celula[getCapacity()];
        setCelulas(celulas);
    }
    //Return values:
    // Erro -> -1
    // Miss compulsório -> 0
    // Miss conflito -> 1
    // Miss capacidade -> 2
    public int access(int offset){
        if(getCelulas() == null){
            return -1;
        }else{
            if(getCelulas()[offset].isEmpty()){
                return 0;
            }else{
                if(getCelulas()[offset].isDirty()){
                    return 2;
                }
            }
        }
        return offset;
    }

}
