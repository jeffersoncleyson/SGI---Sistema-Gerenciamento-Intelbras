/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.intelbras.model;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author WesleyReis
 */
public class Venda {
   private int idVenda;
   private String dataVenda;
   private float valorTotalVenda;
   private String obsVenda;
   private ArrayList<Produto> produtos;

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

    public String getObsVenda() {
        return obsVenda;
    }

    public void setObsVenda(String obsVenda) {
        this.obsVenda = obsVenda;
    }

    public ArrayList<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(ArrayList<Produto> produtos) {
        this.produtos = produtos;
    }
   
   
    
}
