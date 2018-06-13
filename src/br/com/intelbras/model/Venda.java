/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.intelbras.model;

import java.util.ArrayList;

/**
 *
 * @author WesleyReis
 */
public class Venda {
   private int idVenda;
   private String dataVenda;
   private float valorTotalVenda;
   private ArrayList<Produto> produtos;
   private int clienteId;
   private int funcionarioId;

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public int getFuncionarioId() {
        return funcionarioId;
    }

    public void setFuncionarioId(int funcionarioId) {
        this.funcionarioId = funcionarioId;
    }

   
   
    public int getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(int idVenda) {
        this.idVenda = idVenda;
    }

    public String getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(String dataVenda) {
        this.dataVenda = dataVenda;
    }

    public float getValorTotalVenda() {
        return valorTotalVenda;
    }

    public void setValorTotalVenda(float valorTotalVenda) {
        this.valorTotalVenda = valorTotalVenda;
    }

    public ArrayList<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(ArrayList<Produto> produtos) {
        this.produtos = produtos;
    }
   
   
    
}
