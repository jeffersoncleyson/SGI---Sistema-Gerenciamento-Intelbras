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
    int idVenda;
    Date dataVenda;
    float valorTotalVenda;
    String obsVenda;
    ArrayList<Produto> produtos;
    
}