/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.intelbras.controler;

import javax.swing.JTable;

/**
 *
 * @author WesleyReis
 */
public interface AcaoTela {
    
    public void cadastrar();
    public void editar(Object obj);
    public void excluir(int id);
    public void atualizar(JTable tabela);
    public void cancelar();
    public void finalizar();
    public void preencherTabela(JTable tabela);
    
}
