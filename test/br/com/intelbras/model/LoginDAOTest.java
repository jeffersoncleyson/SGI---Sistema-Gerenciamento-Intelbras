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
public class LoginDAOTest {
    
    private LoginDAO loginDAO;
    
    public LoginDAOTest() {
        loginDAO = LoginDAO.getInstance();
    }

    @Test
    public void testCadastrar() {
        
        Login l = new Login();
        
        l.setEmail("emerson@gec.inatel.br");
        l.setUsername("emerson");
        l.setSenha("senha");
        l.setFuncionarioId(1);
        
        boolean flag = loginDAO.cadastrar(l);
        
        assertEquals(true, flag);
        
    }
    @Test
    public void testEditar() {
        
        Login l = new Login();
        
        l.setId(3);
        l.setEmail("emerson2@gec.inatel.br");
        l.setUsername("emerson");
        l.setSenha("senha123");
        l.setFuncionarioId(3);
        
        boolean flag = loginDAO.editar(l);
        
        assertEquals(true, flag);
        
    }
    
    
    
}
