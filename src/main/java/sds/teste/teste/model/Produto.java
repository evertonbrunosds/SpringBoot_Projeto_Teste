package sds.teste.teste.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity //TRANSFORMA A CLASSE EM TABELA DO BANCO DE DADOS
public class Produto {

    //#region Atributos
    @Id //INDICA QUE ESTE ATRIBUTO SE TRATA DE UM ID
    @GeneratedValue(strategy = GenerationType.AUTO) //INDICA QUE ESSE ATRIBUTO É AUTO-INCREMENTÁVEL
    private int id;

    private String nome;

    private int quantidade;

    private double valor;

    private String observacao;
    //#endregion

    //#region GETTERS e SETTERS
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
    //#endregion

}
