package main.memoria;

import static main.util.RuntimeData.*;

/**
 * classe Bloco
 */
public class Conjunto {

    private int indice = -1;
    private int lastUsed;
    private int capacity;
    private boolean isFull = false;
    private int currentUsage;
    private int firstElement;
    private Via[] vias;

    /**
     * classe Celula
     */
    public class Via {

        private int tag = -1;
        private boolean IsEmpty;

        /**
         * @return retorna a tag
         */
        public int getTag() {
            return tag;
        }

        /**
         * @param tag
         */
        public void setTag(int tag) {
            this.tag = tag;
        }

        /**
         * @return se a celula está vazia
         */
        public boolean isEmpty() {
            return IsEmpty;
        }

        /**
         * @param empty muda o estado boolean da célula
         */
        public void setEmpty(boolean empty) {
            IsEmpty = empty;
        }

        /**
         * Construtor Celula
         */
        public Via() {
            this.IsEmpty = true;
        }
    }

    /**
     * @return primeiro elemento
     */
    public int getFirstElement() {
        return firstElement;
    }

    /**
     * @param firstElement primeiro elemento da fila
     */
    public void setFirstElement(int firstElement) {
        this.firstElement = firstElement;
    }

    /**
     * @return uso da cache
     */
    public int getCurrentUsage() {
        return currentUsage;
    }

    /**
     * @param amount
     */
    public void setCurrentUsage(int amount) {
        this.currentUsage = this.currentUsage + amount;
    }

    /**
     * @return
     */
    public Via[] getVias() {
        return vias;
    }

    /**
     * @param vias
     */
    public void setVias(Via[] vias) {
        this.vias = vias;
        for (int i = 0; i < getCapacity(); i++) {
            this.vias[i] = new Via();
        }
    }

    /**
     * @return retorna capacidade
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * @param capacity
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }


    /**
     * @return retorna o indice
     */
    public int getIndice() {
        return indice;
    }

    /**
     * @param indice
     */
    public void setIndice(int indice) {
        this.indice = indice;
    }

    /**
     * @return retorna o ultimo acesso
     */
    public int getLastUsed() {
        return lastUsed;
    }

    /**
     * @param lastUsed
     */
    public void setLastUsed(int lastUsed) {
        this.lastUsed = lastUsed;
    }

    /**
     * @param indice
     * @param offset
     */
    public Conjunto(int indice, int offset) {
        setIndice(indice);
        setCapacity(offset);
        setVias(new Via[getCapacity()]);
    }

    /**
     * @param offset
     * @param tag
     * @return valores
     * Erro -> -1
     * Ha espaco -> 0
     * Miss de Conflito -> 1
     * Miss de capacidade -> 2
     */
    public int access(int offset, int tag) {
        if (getVias() == null) {
            return -1;
        } else {
            if (getCurrentUsage() >= getCapacity()) {
                if (getOutputFlag() == 0) {
                    System.out.printf("\n[CACHE]||==> [%d] Completamente preenchido\n", getIndice());
                }
                if(!isFull){
                    setLinesFilled(1);
                    isFull = true;
                }
            }
            if (getCurrentUsage() == 0) {
                setFirstElement(offset);
            }
            if (getOutputFlag() == 0) {
                System.out.printf("\n[CACHE]||==> Substituindo elemento [%d]\n", offset);
                int temp = getVias()[offset].getTag();
                if (temp == -1) {
                    System.out.printf("[CACHE]||==> Inicializando com tag => [%d]\n", tag);
                } else {
                    System.out.printf("[CACHE]||==> [%d] => [%d]\n", temp, tag);
                }
            }
            getVias()[offset].setTag(tag);

            if (getVias()[offset].isEmpty()) {
                setCurrentUsage(1);
                getVias()[offset].setEmpty(false);
                if (getOutputFlag() == 0) {
                    System.out.printf("[CACHE]||==> [%d] - Espaco restante => [%d]\n", getIndice(), capacity - getCurrentUsage());
                }
                return 0;
            } else {
                return 1;
            }

//            if (getCurrentUsage() >= getCapacity()) {
//                if(getOutputFlag() == 0){
//                    System.out.printf("\n[CACHE]||==> [%d] Completamente preenchido\n", getIndice());
//                }
//                setLinesFilled(1);
//                return 1;
//            } else {
//                if (getCurrentUsage() == 0) {
//                    setFirstElement(offset);
//                }
//                if (getOutputFlag() == 0) {
//                    System.out.printf("\n[CACHE]||==> Substituindo elemento [%d]\n", offset);
//                    int temp = getVias()[offset].getTag();
//                    if(temp == -1){
//                        System.out.printf("[CACHE]||==> Inicializando com tag => [%d]\n", tag);
//                    }else{
//                        System.out.printf("[CACHE]||==> [%d] => [%d]\n", temp, tag);
//                    }
//                }
//                getVias()[offset].setTag(tag);
//
//                if (getVias()[offset].isEmpty()) {
//                    setCurrentUsage(1);
//                    getVias()[offset].setEmpty(false);
//                    if(getOutputFlag() == 0){
//                        System.out.printf("[CACHE]||==> [%d] - Espaco restante => [%d]\n", getIndice(), capacity - getCurrentUsage());
//                    }
//                    return 0;
//                } else {
//                    return 1;
//                }
//            }
        }
    }

}
