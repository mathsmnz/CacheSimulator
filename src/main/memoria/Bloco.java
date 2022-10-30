package main.memoria;

public class Bloco {

    private int endereco = -1;
    private int tag;
    private int indice;
    private int lastUsed;
    private int capacity;
    private int currentUsage;
    private int firstElement;
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

    public int getFirstElement() {
        return firstElement;
    }

    public void setFirstElement(int firstElement) {
        this.firstElement = firstElement;
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
        for(int i = 0; i < getCapacity(); i++){
            this.celulas[i] = new Celula();
        }
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
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
        setCelulas(new Celula[getCapacity()]);
    }

    //Return values:
    // Erro -> -1
    // Ha espaco -> 0
    // Miss de capacidade -> 2
    // Miss de conflito -> 1
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
