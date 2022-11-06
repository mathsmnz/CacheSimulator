package main.memoria;

import java.util.ArrayList;

import static main.util.RuntimeData.*;
import static main.util.Util.*;

/**
 * Classe principal da memória cache, contém os parametros
 * da cache e os conjuntos da cache
 */
public class Cache {

    private int assoc;
    private int offset;
    private int tag;
    private int indice;
    private int sub;
    private int capacidade = 0;
    private ArrayList<Conjunto> conjuntos;

    /**
     * @param nset  numero de conjunto
     * @param bsize tamanho do Via
     * @param assoc associatividade
     * @param sub   politica de substituicão conforme os números abaixo:
     *              1 -> RANDOM
     *              2 -> FIFO
     *              3 -> LRU
     */
    public Cache(int nset, int bsize, int assoc, int sub) {
        this.setOffset(log2(bsize));
        this.setIndice(log2(nset));
        this.setTag(32 - getOffset() - getIndice());
        this.setAssoc(assoc);
        this.setSub(sub);
        this.capacidade = assoc;
        setConjuntos(new ArrayList<>(nset));
        initConjuntos(assoc, nset, bsize);

        if(getOutputFlag() == 0){
            System.out.printf("[CACHE]|==> n_offset: %d\n", getOffset());
            System.out.printf("[CACHE]|==> n_indice: %d\n", getIndice());
            System.out.printf("[CACHE]|==> n_tag: %d\n", getTag());
            System.out.printf("[CACHE]|==> assoc: %d\n", getAssoc());
            System.out.printf("[CACHE]|==> sub: %d\n", getSub());
            System.out.printf("[CACHE]|==> capacidade: %d\n", capacidade);
        }


    }

    /**
     * Método initConjuntos
     * inicia os Conjuntos
     *
     * @param assoc associatividade
     * @param nset  conjuntos
     */
    private void initConjuntos(int assoc, int nset, int bsize) {
        for (int i = 0; i < nset; i++) {
            conjuntos.add(i, new Conjunto(-1, assoc));
        }
    }

    /**
     * Método responsável por ao receber um endereço, o decodificar
     * retornando argumentos a serem operados
     *
     * @param address a ser decodicado
     * @return int[] com os argumentos decodificados, 0 - addres, 1 - tag, 2 - offset, 3 - indice
     */
    private int[] decode(int address) {
        int[] retVal = new int[4];
        String convertedAddress = addressToBinary(address);
        retVal[0] = address; // address
        retVal[1] = Integer.parseInt(convertedAddress.substring(0, 32 - (getOffset() + getIndice())), 2); // tag
        if (getOffset() > 0) {
            retVal[2] = Integer.parseInt(convertedAddress.substring(32 - getOffset(), 32), 2);
        } else {
            retVal[2] = 0;
        } // offset
        if(getIndice() > 0){
            retVal[3] = Integer.parseInt(convertedAddress.substring(getTag(), 32 - getOffset()), 2);
        }else{
            retVal[3] = 0;
        } // indice
        return retVal;
    }

    /**
     * Método para a leitura em cache
     * Possiveis retornos
     * true - hit
     * false - miss
     *
     * @param endereco recebe endereco
     * @return retorna falso caso já tenha algo na cache
     */
    private boolean read(int endereco) {
        int[] args = decode(endereco);

        Conjunto conjunto = conjuntos.get(args[3]);
        if (conjunto.getIndice() == -1) {
            conjunto.setIndice(args[3]);
        }


        for(int i = 0; i < conjunto.getVias().length; i++){
            if(conjunto.getVias()[i].getTag() == args[1]){
                if(getOutputFlag() == 0){
                    System.out.printf("\n[CACHE]||==> Tag [%d] encontrada, offset [%d]\n", args[1], i);
                }
                return true;
            }
        }
        return false;
    }

    /**
     * Método de para escrita em cache
     * Possiveis retornos
     * 0 - Escrita com erro compulsorio
     * 1 - Escrita com erro de conflito
     * 2 - Escrita com erro de capacidade
     *
     * @param endereco endereco para a realizacao da leitura
     * @return resultado da escrita
     */
    private int write(int endereco) {
        if (getVias() != null) {
            int pos = 0;
            int[] args = decode(endereco);
            Conjunto conjunto = conjuntos.get(args[3]);
            if (getAssoc() > 1) {
                int off = args[2];

                switch (getSub()) {
                    case 1 -> pos = getRandom(capacidade - 1);
                    case 2 -> pos = conjunto.getFirstElement();
                    case 3 -> pos = conjunto.getLastUsed();
                    default -> {
                        if (getOutputFlag() == 0) {
                            System.err.println("[CACHE]|==>Erro ao escolher modo de substiuição");
                        }
                    }
                }
            }
            int output = conjunto.access(pos, args[1]);

            missHandler(output, endereco);

            return output;
        }
        return -1;
    }

    private void missHandler(int input, int endereco) {

        int miss = 0;

        if (input != 0) {
            int event = 0;
            //Define se o miss sera de capacidade ou conflito
            if (getLinesFilled() >= capacidade) {
                setMissCapacidade(1);
            } else {
                event = 1;
                setMissConflito(1);
            }
            if (getOutputFlag() == 0) {
                switch (event) {
                    case 0 -> {
                        System.out.printf("[CACHE]||==> [%d] - Miss capacidade\n", endereco);
                    }
                    case 1 -> {
                        System.out.printf("[CACHE]||==> [%d] - Miss conflito\n", endereco);
                    }
                }
            }
        } else {
            setMissCompulsorio(1);
            if (getOutputFlag() == 0) {
                System.out.printf("[CACHE]||==> [%d] - Miss compulsorio\n", endereco);
            }
        }
    }

    /**
     * Método para se operar na cache
     *
     * @param endereco a ser encontrado na cache
     */
    public void find(int endereco) {
        if (!read(endereco)) {
            write(endereco);
        }else{
            if(getOutputFlag() == 0){
                System.out.printf("[CACHE]||==> [%d] - Hit\n", endereco);
            }
        }
    }

    /**
     * @return o valor da associatividade
     */
    public int getAssoc() {
        return assoc;
    }

    /**
     * @param assoc valor da associatividade
     */
    public void setAssoc(int assoc) {
        this.assoc = assoc;
    }

    /**
     * @return o offset
     */
    public int getOffset() {
        return offset;
    }

    /**
     * @param offset a ser definido
     */
    public void setOffset(int offset) {
        this.offset = offset;
    }

    /**
     * @return o valor da tag
     */
    public int getTag() {
        return tag;
    }

    /**
     * @param tag a ser definido
     */
    public void setTag(int tag) {
        this.tag = tag;
    }

    /**
     * @return o valor do indice
     */
    public int getIndice() {
        return indice;
    }

    /**
     * @param indice a ser definido
     */
    public void setIndice(int indice) {
        this.indice = indice;
    }

    /**
     * @return retorna o numero da politica de substituicao
     * 1 -> RANDOM
     * 2 -> FIFO
     * 3 -> LRU
     */
    public int getSub() {
        return sub;
    }

    /**
     * @param sub recebe o valor da politica de substituicão conforme:
     *            1 -> RANDOM
     *            2 -> FIFO
     *            3 -> LRU
     */
    public void setSub(int sub) {
        this.sub = sub;
    }

    /**
     * @return Vias
     */
    public ArrayList<Conjunto> getVias() {
        return conjuntos;
    }

    /**
     * @param conjuntos recebe os Vias da classe Via
     */
    public void setConjuntos(ArrayList<Conjunto> conjuntos) {
        this.conjuntos = conjuntos;
    }
}
