package main.memoria;

import static main.util.RuntimeData.*;

/**
 * classe Conjunto
 */
public class Conjunto {

    private int indice = -1;
    private int posistionToEvict;
    private int capacity;
    private boolean isFull = false;
    private int currentUsage;
    private Via[] vias;

    /**
     * classe Via
     */
    public static class Via {
        private long timestamp = 0;
        private int tag = -1;
        private boolean IsEmpty;

        public long getTimestamp() {
            return timestamp;
        }

        public void setTimestamp() {
            this.timestamp = System.currentTimeMillis();
        }

        /**
         * @return retorna a tag
         */
        public int getTag() {
            return tag;
        }

        /**
         * @param tag a ser inicializado
         */
        public void setTag(int tag) {
            this.tag = tag;
        }

        /**
         * @return se a via está vazia
         */
        public boolean isEmpty() {
            return IsEmpty;
        }

        /**
         * @param empty muda o estado boolean da via
         */
        public void setEmpty(boolean empty) {
            IsEmpty = empty;
        }

        /**
         * Construtor Via
         */
        public Via() {
            this.IsEmpty = true;
        }
    }

    /**
     * @return uso da cache
     */
    public int getCurrentUsage() {
        return currentUsage;
    }

    /**
     * @param amount a ser incrementado
     */
    public void setCurrentUsage(int amount) {
        this.currentUsage = this.currentUsage + amount;
    }

    /**
     *
     */
    public Via[] getVias() {
        return vias;
    }

    /**
     * @param vias array de Via com a capacidade já definida
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
     * @param capacity a ser inicializada
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }


    /**
     * @return retorna o índice
     */
    public int getIndice() {
        return indice;
    }

    /**
     * @param indice a ser inicializado
     */
    public void setIndice(int indice) {
        this.indice = indice;
    }

    /**
     * @return a posição a substituir
     */
    public int getPosistionToEvict() {
        return posistionToEvict;
    }

    /**
     * Método responsável por definir a posição
     * com a timestamp mais antiga
     */
    public void setPositionToEvict() {
        int retVal = 0;

        if(getCapacity() != 0){
            int pos = 0;
            for(int i = 1; i < getCapacity(); i++){
                if(getVias()[i].getTimestamp() < getVias()[i - 1].timestamp){
                    pos = i;
                }
            }
            retVal = pos;
        }

        this.posistionToEvict = retVal;
    }

    /**
     * @param indice deste conjunto
     * @param offset numero de posições deste conjunto
     */
    public Conjunto(int indice, int offset) {
        setIndice(indice);
        setCapacity(offset);
        setVias(new Via[getCapacity()]);
    }

    /**
     * @param offset posição a ser acessada
     * @param tag    a ser escrita
     * @return valores:
     * Erro → -1 <br>
     * Ha espaço → 0 <br>
     * Miss de Conflito → 1 <br>
     */
    public int access(int offset, int tag, int sub) {
        if (getVias() == null) {
            return -1;
        } else {
            if (getCurrentUsage() >= getCapacity()) {
                if (getDebugMode() == 1) {
                    System.out.printf("\n[CACHE]||==> [%d] Completamente preenchido\n", getIndice());
                }
                if (!isFull) {
                    setLinesFilled(1);
                    isFull = true;
                }
            }
            if (getDebugMode() == 1) {
                System.out.printf("\n[CACHE]||==> Substituindo elemento [%d]\n", offset);
                int temp = getVias()[offset].getTag();
                if (temp == -1) {
                    System.out.printf("[CACHE]||==> Inicializando com tag => [%d]\n", tag);
                } else {
                    System.out.printf("[CACHE]||==> [%d] => [%d]\n", temp, tag);
                }
            }
            getVias()[offset].setTag(tag);
            getVias()[offset].setTimestamp();
            setPositionToEvict();

            if (getVias()[offset].isEmpty()) {
                setCurrentUsage(1);
                getVias()[offset].setEmpty(false);
                if (getDebugMode() == 1) {
                    System.out.printf("[CACHE]||==> [%d] - Espaco restante => [%d]\n", getIndice(), capacity - getCurrentUsage());
                }
                return 0;
            } else {
                return 1;
            }
        }
    }

}
