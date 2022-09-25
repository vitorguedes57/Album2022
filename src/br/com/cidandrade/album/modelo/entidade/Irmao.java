package br.com.cidandrade.album.modelo.entidade;

public class Irmao {

    private int id;
    private String nome, contato;

    public Irmao(String nome) {
        this.nome = nome;
    }

    public Irmao(int id, String nome, String contato) {
        this.id = id;
        this.nome = nome;
        this.contato = contato;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getContato() {
        return contato;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }
}
