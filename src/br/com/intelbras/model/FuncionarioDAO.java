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
public class FuncionarioDAO implements DAO{

    BancoDados _BD = new BancoDados();

    private Connection _con = null;
    private ResultSet _rs = null;
    private Statement _st = null;
    private PreparedStatement _pst = null;

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
        abrirConexao();
        
        boolean gravou = true;
        
        try {
            
            Funcionario funcionario = (Funcionario) obj;
            
            this._pst = _con.prepareStatement("INSERT INTO `intelbras`.`funcionario`(`nomeFuncionario`,`cpfFuncionario`,`rgFuncionario`,"
                    + "`enderecoFuncionario`,`cepFuncionario`,`bairroFuncionario`,`telefoneFuncionario`,"
                    + "`sexoFuncionario`,`salarioFuncionario`,`setorFuncionario`,`comissaoFuncionario`)VALUES(?,?,?,?,?,?,?,?,?,?,?);");
            this._pst.setString(1, funcionario.getNomeFuncionario());
            this._pst.setString(2, funcionario.getCpfFuncionario());
            this._pst.setString(3, funcionario.getRgFuncionario());
            this._pst.setString(4, funcionario.getEnderecoFuncionario());
            this._pst.setString(5, funcionario.getCepFuncionario());
            this._pst.setString(6, funcionario.getBairroFuncionario());
            this._pst.setString(7, funcionario.getTelefoneFuncionario());
            this._pst.setString(8, funcionario.getSexoFuncionario());
            this._pst.setFloat(9, funcionario.getSalarioFuncionario());
            this._pst.setString(10, funcionario.getSetorFuncionario());
            this._pst.setFloat(11, funcionario.getComissaoFuncionario());

            this._pst.executeUpdate();
            
        } catch (Exception ex) {
            gravou = false;
            System.out.println("Erro: Conexão Banco! :(");
            System.out.println(ex);
        } finally {
            fecharConexao();
        }
        return gravou;
    }

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
    public boolean remover(int id) {
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
