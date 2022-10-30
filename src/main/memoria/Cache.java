package main.memoria;

import java.util.ArrayList;

import static main.util.RuntimeData.*;
import static main.util.Util.getRandom;
import static main.util.Util.log2;

public class Cache {

    private int assoc;
    private int offset;
    private int tag;
    private int indice;
    private int sub;
    private int max_capacidade;
    private int capacidade = 0;
    private int hit_count = 0;
    private int miss_count = 0;
    private ArrayList<Bloco> blocos;

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

    private void initBlocos(int assoc, int nset){
        for(int i = 0; i < assoc * nset; i++){
            blocos.add(i, new Bloco(getIndice(), getOffset()));
        }
    }
    private int[] decode(int address) {
        int[] retVal = new int[4];
        retVal[0] = address; // address
        retVal[1] = address >> (this.offset + this.indice); // tag
        retVal[2] = address << (this.tag + this.indice) >>> (this.tag + this.indice); // offset
        retVal[3] = ((address >> this.getOffset()) & (2 ^ this.getIndice() - 1)); // indice
        return retVal;
    }

    //Retorna falso caso já tenha algo na cache
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
     *
     * @param endereco endereco para a realizacao da leitura
     * @return resultado da leitura
     */
    //Possiveis retornos
    //0 - Escrita com erro compulsorio
    //1 - Escrita com erro de conflito
    //2 - Escrita com erro de capacidade
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

    public void find(int endereco) {
        if (!read(endereco)) {
            write(endereco);
        }
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