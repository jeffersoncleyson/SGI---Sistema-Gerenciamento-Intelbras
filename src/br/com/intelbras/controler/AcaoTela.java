/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.intelbras.controler;

/**
 *
 * @author WesleyReis
 */
public interface AcaoTela {
    
    public void cadastrar(Object obj);
    public void editar(int id,Object obj);
    public void excluir(int id);
    public void atualizar();
    public void cancelar();
    public void finalizar();
    
}
