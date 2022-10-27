package main.memoria;

public class Bloco {

    private int endereco;
    private int tag;
    private int indice;
    private boolean ehSujo;
    private boolean isEmpty;
    private int lastUsed;

    public Bloco() {
        ehSujo = false;
        isEmpty = true;
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

    public boolean isEhSujo() {
        return ehSujo;
    }

    public void setEhSujo(boolean ehSujo) {
        this.ehSujo = ehSujo;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public void setEmpty(boolean empty) {
        isEmpty = empty;
    }

    public int getLastUsed() {
        return lastUsed;
    }

    public void setLastUsed(int lastUsed) {
        this.lastUsed = lastUsed;
    }

    public Bloco(int endereco, int n_offset, int n_indice) {
        this.endereco = endereco;
        this.tag = endereco >> (n_offset + n_indice);
        this.indice = (endereco >> n_offset) & (2 ^ n_indice);
        this.isEmpty = false;

    }


}
