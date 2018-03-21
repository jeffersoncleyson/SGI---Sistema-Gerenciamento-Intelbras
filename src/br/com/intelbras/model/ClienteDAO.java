/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.intelbras.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author WesleyReis
 */
public class ClienteDAO implements DAO {

    BancoDados _BD;

    private Connection _con = null;
    private ResultSet _rs = null;
    private Statement _st = null;
    private PreparedStatement _pst = null;

    public static void main(String[] args) throws SQLException {

        ClienteDAO cliente = new ClienteDAO();

        cliente.abrirConexao();

        cliente._st = cliente._con.createStatement();
        
        cliente._rs = cliente._st.executeQuery("Select * from cliente");
        
        while (cliente._rs.next()) {
            
            System.out.println(cliente._rs.getString(1));
            System.out.println(cliente._rs.getString(2));
            System.out.println(cliente._rs.getString(3));
        }

        cliente.fecharConexao();

    }
//====================================================================================================================
//====================================================================================================================

    public ClienteDAO() {
        this._BD = new BancoDados();
    }
    
    
//====================================================================================================================
//====================================================================================================================
    public ArrayList<Object> listarTodos() {
        abrirConexao();
        try {

        } catch (Exception ex) {
            System.out.println("Erro: Conexão Banco! :(");
        } finally {
            fecharConexao();
        }
        return null;
    }

//====================================================================================================================
//====================================================================================================================
    public boolean cadastrar(Object obj) {
        
        Cliente cliente = (Cliente) obj;
        
        

        return false;
    }

//====================================================================================================================
//====================================================================================================================
    public boolean editar(Object obj) {
        abrirConexao();
        try {

        } catch (Exception ex) {
            System.out.println("Erro: Conexão Banco! :(");
        } finally {
            fecharConexao();
        }
        return false;
    }
//====================================================================================================================
//====================================================================================================================

    public boolean remover(Object obj) {
        abrirConexao();
        try {

        } catch (Exception ex) {
            System.out.println("Erro: Conexão Banco! :(");
        } finally {
            fecharConexao();
        }
        return false;
    }
//====================================================================================================================
//====================================================================================================================

    public boolean abrirConexao() {
        try {
            _con = DriverManager.getConnection(_BD.getUrl(), _BD.getUsuario(), _BD.getSenha());
            System.out.println("Conexão Banco! :)");
            return true;
        } catch (SQLException ex) {
            System.out.println("Erro: Conexão Banco! :(");
            System.out.println(ex);
            return false;
        }
    }
//====================================================================================================================
//====================================================================================================================

    public void fecharConexao() {
        try {
            if (_rs != null) {
                _rs.close();
            }
            if (_st != null) {
                _st.close();
            }
            if (_con != null) {
                _con.close();
            }
        } catch (SQLException ex) {
            System.out.println("Conexão não pode ser fechada! :(");
        }
    }
}