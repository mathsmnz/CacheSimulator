package main.memoria;

import java.util.ArrayList;
import java.util.Random;
import main.util.Util;
import main.util.RuntimeData;
import static main.util.Util.log2;

public class Cache {

    private int assoc;
    private int offset;
    private int tag;
    private int indice;
    private int sub;
    private int capacidade;
    private int hit_count = 0;
    private int miss_count = 0;
    private ArrayList<Bloco> blocos;

    public Cache(int nset, int bsize, int assoc, int sub) {
        this.setOffset(log2(bsize));
        this.setIndice(log2(nset));
        this.setTag(32 - getOffset() - getIndice());
        this.setAssoc(assoc);
        this.setSub(sub);
        this.capacidade = assoc*nset;
        setBlocos(new ArrayList<>(assoc * nset));
    }

    private int[] decode(int address){
        int[] retVal = new int[4];
        retVal[0] = address; // address
        retVal[1] = address >> (this.offset + this.indice); // tag
        retVal[2] = address << (this.tag + this.indice) >>> (this.tag + this.indice); // offset
        retVal[3] = ((address >> this.getOffset()) & (2 ^ this.getIndice() - 1)); // indice
        return retVal;
    }

    private boolean read(int endereco) {
        for (Bloco bloco : blocos) {
            if (bloco.getEndereco() == endereco) {
                Bloco temp = bloco;
                this.hit_count++;
                if (assoc == 1) {
                    return !temp.getCelulas()[0].isEmpty();
                } else {
                    int off = endereco;
                    return !temp.getCelulas()[off].isEmpty();
                }
            }
        }
        return false;
    }

    private boolean write(int endereco) {
        for (Bloco bloco : blocos) {
            if (bloco.getEndereco() == endereco) {
                Bloco temp = bloco;
                if (assoc == 1) {
                    if (temp.getCelulas()[0].isEmpty()) {
                        temp.getCelulas()[0].setEmpty(false);
                        return true;
                    } else {
                        temp.getCelulas()[0].setDirty(true);
                        return false;
                    }
                } else {
                    int off = endereco;
                    if (temp.getCelulas()[off].isEmpty()) {
                        temp.getCelulas()[off].setEmpty(false);
                        return true;
                    } else {
                        temp.getCelulas()[off].setDirty(true);
                        return false;
                    }
                }
            }
        }
        return false;
    }

    private void find(int endereco){
        int aux = this.getSub();
        switch (aux) {
            // random
            case 1:
                this.random(endereco);
                break;
            // FIFO
            case 2:
                this.fifo(endereco);
                break;
            // LRU
            case 3:
                this.lru(endereco);
                break;
            default:
                this.random(endereco);
                break;
        }
    }

    private void random(int endereco){

        if (this.read(endereco) == false){
            this.miss_count++;
            this.write(endereco);
        }else{
            Random r = new Random();
            int max = this.tag;
            int posicao = r.nextInt(max);
            this.write(posicao);
        }

    }

    private void fifo(int endereco){
        // algo aqui
    }

    private void lru(int endereco){
        // algo aqui
    }

    public int getAssoc() {
        return assoc;
    }

    public void setAssoc(int assoc) {
        this.assoc = assoc;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
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

    public int getSub() {
        return sub;
    }

    public void setSub(int sub) {
        this.sub = sub;
    }

    public ArrayList<Bloco> getBlocos() {
        return blocos;
    }

    public void setBlocos(ArrayList<Bloco> blocos) {
        this.blocos = blocos;
    }
}