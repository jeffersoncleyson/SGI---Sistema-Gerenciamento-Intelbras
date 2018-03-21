/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.intelbras.model;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author WesleyReis
 */
public interface DAO {
    
    public ArrayList<Object> listarTodos();
    public boolean cadastrar(Object obj);
    public boolean editar(Object obj);
    public boolean remover(Object obj);
    public boolean abrirConexao();
    public void fecharConexao();
    
}
