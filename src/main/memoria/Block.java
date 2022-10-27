package main.memoria;

public class Block {

    int endereco;
    int tag;
    int indice;
    boolean ehSujo;
    boolean isEmpty;

    Block(){
        ehSujo = false;
        isEmpty = true;
    }

    public Block(int endereco, int n_offset, int n_indice){
        this.endereco = endereco;
        this.tag = endereco >> (n_offset + n_indice);
        this.indice = (endereco >> n_offset) & (2^n_indice);
        this.isEmpty = false;
    }

    @Override
    public String toString() {
        return "Block ---->" +
                "\nendereco=" + endereco +
                "\ntag=" + tag +
                "\nindice=" + indice +
                "\nehSujo=" + ehSujo +
                "\nisEmpty=" + isEmpty;
    }
}
