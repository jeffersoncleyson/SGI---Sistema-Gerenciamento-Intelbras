/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.intelbras.model;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author WesleyReis
 */
public class ProdutoDAOTest {
    
    private ProdutoDAO produtoDAO;
    
    public ProdutoDAOTest() {
        produtoDAO = new ProdutoDAO();
    }

    @Test
    public void testCadastrar() {
        
        Produto p = new Produto();
        
        p.setDescricaoProduto("Amido de Milho");
        p.setMarcaProduto("Maizena");
        p.setModeloProduto("Alimenticio");
        p.setValorProduto(3.75f);
        p.setObsProduto("Bom para fazer mingau");
        
        boolean flag = produtoDAO.cadastrar(p);
        
        assertEquals(true, flag);
        
    }
    
    @Test
    public void testEditar() {
        
        Produto p = new Produto();
        
        p.setIdProduto(1);
        p.setDescricaoProduto("Amido de Milho");
        p.setMarcaProduto("Santa Amalia");
        p.setModeloProduto("Alimenticio");
        p.setValorProduto(4.75f);
        p.setObsProduto("Bom para fazer mingau");
        
        boolean teste = produtoDAO.editar(p);
        
        assertEquals(true, teste);
        
    }
    
    @Test
    public void testAbrirConexao() {
        
        boolean teste = produtoDAO.abrirConexao();
        
        assertEquals(true, teste);
        
    }
  
    
}
