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
public class LoginDAO implements DAO {

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
            ArrayList<Object> array = new ArrayList<>();

            this._st = this._con.createStatement();
            this._rs = this._st.executeQuery("SELECT * FROM login ");

            while (this._rs.next()) {
                Login login = new Login();

                login.setId(this._rs.getInt(1));
                login.setUsername(this._rs.getString(2));
                login.setEmail(this._rs.getString(3));
                login.setSenha(this._rs.getString(4));
                login.setFuncionarioId(this._rs.getInt(5));

                array.add(login);

            }

            return array;
        } catch (Exception ex) {
            System.out.println("Erro: Conexão Banco! :'(");
        } finally {
            fecharConexao();
        }
        return null;
    }

//====================================================================================================================
//====================================================================================================================
    @Override
    public boolean cadastrar(Object obj) {
        abrirConexao();
        boolean gravou = true;
        try {

            Login login = (Login) obj;

            this._pst = _con.prepareStatement("INSERT INTO `login`(`nomeLogin`,`emailLogin`,`senhaLogin`,`Funcionario_idFuncionario`) VALUES(?,?,?,?);");
            this._pst.setString(1, login.getUsername());
            this._pst.setString(2, login.getEmail());
            this._pst.setString(3, login.getSenha());
            this._pst.setInt(4, login.getFuncionarioId());

            this._pst.executeUpdate();

        } catch (Exception ex) {
            System.out.println("Erro: Conexão Banco! :(");
            System.out.println(ex);
            gravou = false;
        } finally {
            fecharConexao();
        }

        return gravou;
    }

    @Override
    public boolean editar(Object obj) {
        abrirConexao();
        boolean gravou = true;
        try {
            Login login = (Login) obj;

            this._pst = _con.prepareStatement("UPDATE `login` SET `nomeLogin` = ?,`emailLogin` = ?,`senhaLogin` = ?,`Funcionario_idFuncionario` =? WHERE idLogin = ?;");
            this._pst.setString(1, login.getUsername());
            this._pst.setString(2, login.getEmail());
            this._pst.setString(3, login.getSenha());
            this._pst.setInt(4, login.getFuncionarioId());

            this._pst.setInt(5, login.getId());

            this._pst.executeUpdate();

        } catch (Exception ex) {
            System.out.println("Erro: Conexão Banco! :(");
            System.out.println(ex);
            gravou = false;
        } finally {
            fecharConexao();
        }
        return gravou;
    }

//====================================================================================================================
//====================================================================================================================    
    public boolean remover(int id) {
        abrirConexao();
        boolean teste = true;
        try {

            this._pst = this._con.prepareStatement("DELETE FROM login WHERE idLogin = ?");
            this._pst.setInt(1, id);
            this._pst.executeUpdate();

        } catch (Exception ex) {
            teste = false;
            System.out.println("Erro: Conexão Banco! :(");
        } finally {
            fecharConexao();
        }
        return teste;
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
//====================================================================================================================
//====================================================================================================================

    public int getNivelAcesso(Login l) {
        abrirConexao();
        int nivel = -1;
        try {

            this._pst = this._con.prepareStatement("SELECT funcionario.nivelAcessoFuncionario FROM funcionario "
                    + "INNER JOIN login ON login.Funcionario_idFuncionario = funcionario.idFuncionario "
                    + "WHERE funcionario.idFuncionario = ?;");
            this._pst.setInt(1, l.getFuncionarioId());

            this._rs = this._pst.executeQuery();

            if(this._rs.next()){
                nivel = this._rs.getInt(1);
            }
            

        } catch (Exception ex) {
            System.out.println("Erro: Conexão Banco! :(");
            ex.printStackTrace();
        } finally {
            fecharConexao();
        }
        return nivel;
    }
}
