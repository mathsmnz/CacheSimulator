package main.memoria;

import java.util.ArrayList;

import static main.util.RuntimeData.*;
import static main.util.Util.getRandom;
import static main.util.Util.log2;

/**
 * Classe Cache
 */
public class Cache {

    private int assoc;
    private int offset;
    private int tag;
    private int indice;
    private int sub;
    private int capacidade = 0;
    private ArrayList<Bloco> blocos;
    
    /**
     * 
     * @param nset
     * @param bsize
     * @param assoc
     * @param sub
     */
    public Cache(int nset, int bsize, int assoc, int sub) {
        this.setOffset(log2(bsize));
        this.setIndice(log2(nset));
        this.setTag(32 - getOffset() - getIndice());
        this.setAssoc(assoc);
        this.setSub(sub);
        this.capacidade = assoc * nset;
        setBlocos(new ArrayList<>(capacidade));
        initBlocos(assoc, nset);
    }
    
    /**
     * 
     * @param assoc
     * @param nset
     */
    private void initBlocos(int assoc, int nset){
        for(int i = 0; i < assoc * nset; i++){
            blocos.add(i, new Bloco(getIndice(), getOffset()));
        }
    }
    
    /**
     * 
     * @param address
     * @return
     */
    private int[] decode(int address) {
        int[] retVal = new int[4];
        retVal[0] = address; // address
        retVal[1] = address >> (this.offset + this.indice); // tag
        retVal[2] = address << (this.tag + this.indice) >>> (this.tag + this.indice); // offset
        retVal[3] = ((address >> this.getOffset()) & (2 ^ this.getIndice() - 1)); // indice
        return retVal;
    }

    /**
     * 
     * @param endereco recebe endereco
     * @return retorna falso caso já tenha algo na cache
     */
    private boolean read(int endereco) {
        int[] args = decode(endereco);
        Bloco bloco = blocos.get(args[3]);
        if(bloco.getEndereco() == -1){
            bloco.setEndereco(endereco);
        }

        int pos = 0;
        if(getAssoc() > 1){
            pos = args[2];
        }

        return !bloco.getCelulas()[pos].isEmpty();
    }

    /**
     * Método de para escrita em cache
     * Possiveis retornos
     * 0 - Escrita com erro compulsorio
     * 1 - Escrita com erro de conflito
     * 2 - Escrita com erro de capacidade
     * @param endereco endereco para a realizacao da leitura
     * @return resultado da leitura
     */
    private int write(int endereco) {
        if (getBlocos() != null) {
            for (Bloco bloco : getBlocos()) {
                int pos = 0;
                if (getAssoc() > 1) {
                    int off = decode(endereco)[2];
                    switch (getSub()) {
                        case 1 -> pos = getRandom(off);
                        case 2 -> pos = bloco.getFirstElement();
                        case 3 -> pos = bloco.getLastUsed();
                        default -> {
                            if (getOutputFlag() == 0) {
                                System.out.println("[CACHE]|==>Erro ao escolher modo de substiuição");
                            }
                        }
                    }
                }
                if (bloco.getEndereco() == endereco) {
                    int output = bloco.access(pos);

                    switch (output) {
                        case 0 -> setMissCompulsorio(1);
                        case 1 -> setMissConflito(1);
                        case 2 -> {
                            setMissCapacidade(1);
                            setLinesFilled(1);
                        }
                    }

                    if (getOutputFlag() == 0) {
                        switch (output) {
                            case 1 -> System.out.printf("[CACHE]||==> [%d] - Miss conflito\n", endereco);
                            case 0 -> System.out.printf("[CACHE]||==> [%d] - Miss compulsorio\n", endereco);
                            case 2 -> System.out.printf("[CACHE]||==> [%d] - Miss capacidade\n", endereco);
                        }
                    }

                    return output;
                }
            }
        }
        return -1;
    }
    
    /**
     *  
     * @param endereco
     */
    public void find(int endereco) {
        if (!read(endereco)) {
            write(endereco);
        }
    }
    
    /**
     * 
     * @return o valor da associatividade
     */
    public int getAssoc() {
        return assoc;
    }
    
    /**
     * 
     * @param assoc valor da associatividade
     */
    public void setAssoc(int assoc) {
        this.assoc = assoc;
    }
    
    /**
     * 
     * @return o offset
     */
    public int getOffset() {
        return offset;
    }
    
    /**
     * 
     * @param offset
     */
    public void setOffset(int offset) {
        this.offset = offset;
    }
    
    /**
     * 
     * @return o valor da tag
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
     * @return o valor do indice
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
     * @return retorna o numero da politica de substituicao
     * 1 -> RANDOM
     * 2 -> FIFO
     * 3 -> LRU
     */
    public int getSub() {
        return sub;
    }
    
     /**
     * 
     * @param sub
     */
    public void setSub(int sub) {
        this.sub = sub;
    }
    
    /**
     * 
     * @return blocos
     */
    public ArrayList<Bloco> getBlocos() {
        return blocos;
    }
    
    /**
     * 
     * @param blocos
     */
    public void setBlocos(ArrayList<Bloco> blocos) {
        this.blocos = blocos;
    }
}
