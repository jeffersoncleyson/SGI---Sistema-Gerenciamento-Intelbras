/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.intelbras.model;

/**
 *
 * @author WesleyReis
 */
public class VendasGerente {
    
    
    private int idVenda;
    private int idCliente;
    private int idNomeFuncionario;
    private String nomeCliente;
    private String nomeFuncionario;
    
    private String data;

    public int getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(int idVenda) {
        this.idVenda = idVenda;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdNomeFuncionario() {
        return idNomeFuncionario;
    }

    public void setIdNomeFuncionario(int idNomeFuncionario) {
        this.idNomeFuncionario = idNomeFuncionario;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getNomeFuncionario() {
        return nomeFuncionario;
    }

    public void setNomeFuncionario(String nomeFuncionario) {
        this.nomeFuncionario = nomeFuncionario;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
    
    
    
}
