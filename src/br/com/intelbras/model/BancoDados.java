/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.intelbras.model;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author WesleyReis
 */
public class BancoDados {
    
    
    private String usuario;
    private String senha;
    private String nomeBanco;
    private String url;
    private String JDBC;

    public BancoDados() {

        this.usuario = "root";
        this.senha = "root";
        this.nomeBanco = "intelbras";
        this.url = "jdbc:mysql://localhost:3306/" + this.nomeBanco;
        this.JDBC = "com.mysql.jdbc.Driver";
                
    }

    public String getUsuario() {
        return usuario;
    }

    public String getSenha() {
        return senha;
    }

    public String getUrl() {
        return url;
    }

    public String getJDBC() {
        return JDBC;
    }
    
    
    
    
}
