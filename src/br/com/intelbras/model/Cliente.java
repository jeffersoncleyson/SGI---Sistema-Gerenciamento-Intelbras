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
public class Cliente {
    
    int idCliente;
    String nomeCliente;
    String cpfCliente;
    String rgCliente;
    String sexoCliente;
    Date dataNascCliente;
    String bairroCliente;
    String cepCliente;
    String telefoneCliente;
    String enderecoCliente;
    
    ArrayList<Venda> compras;
    
    
}
