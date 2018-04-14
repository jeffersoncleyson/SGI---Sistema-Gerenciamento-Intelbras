/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.intelbras.controler;

import br.com.intelbras.model.PontoDAO;

/**
 *
 * @author WesleyReis
 */
public class PontoControler {
    PontoDAO pontoDAO;

    public PontoControler() {
      this.pontoDAO = new PontoDAO();
    }
    
}
