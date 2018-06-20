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
public class PontoDAO implements DAO{

    private static PontoDAO uniqueInstance;
    BancoDados _BD = new BancoDados();

    private Connection _con = null;
    private ResultSet _rs = null;
    private Statement _st = null;
    private PreparedStatement _pst = null;

    private PontoDAO() {
        this._BD = new BancoDados();
    }

    public static synchronized PontoDAO getInstance(){
        if(uniqueInstance==null){
            uniqueInstance = new PontoDAO();
        }
        return uniqueInstance;
    }
    
//====================================================================================================================
//====================================================================================================================
    @Override
    public ArrayList<Object> listarTodos() {
        abrirConexao();
        try {
             ArrayList<Object> array = new ArrayList<>();

            this._st = this._con.createStatement();
            this._rs = this._st.executeQuery("SELECT * FROM ponto INNER JOIN funcionario ON ponto.funcionario_idFuncionario = funcionario.idFuncionario");

            while (this._rs.next()) {
                Funcionario funcionario = new Funcionario();
                Ponto ponto = new Ponto();

                ponto.setIdPonto(this._rs.getInt(1));
                ponto.setDataPonto(this._rs.getString(2));
                // 4 chave extrangeira
                funcionario.setIdFuncionario(this._rs.getInt(5));
                funcionario.setNomeFuncionario(this._rs.getString(6));
                funcionario.setCpfFuncionario(this._rs.getString(7));
                funcionario.setRgFuncionario(this._rs.getString(8));
                funcionario.setEnderecoFuncionario(this._rs.getString(9));
                funcionario.setCepFuncionario(this._rs.getString(10));
                funcionario.setBairroFuncionario(this._rs.getString(11));
                funcionario.setTelefoneFuncionario(this._rs.getString(12));
                funcionario.setSexoFuncionario(this._rs.getString(13));
                funcionario.setSalarioFuncionario(this._rs.getInt(14));
                funcionario.setSetorFuncionario(this._rs.getString(15));
                funcionario.setComissaoFuncionario(this._rs.getInt(16));

                ponto.setFuncionario(funcionario);
                
                array.add(ponto);
            }

            return array;
        } catch (Exception ex) {
            System.out.println("Erro: Conexão Banco! :( Listar pontos");
            System.out.println(ex);
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
        try {

            
            // implementar com micro
            
            return true;
        } catch (Exception ex) {
            System.out.println("Erro: Conexão Banco! :(");
        } finally {
            fecharConexao();
        }
        return false;
    }
//====================================================================================================================
//==================================================================================================================== 
    public boolean editar(Object obj) {
        return false;
    }
//====================================================================================================================
//====================================================================================================================    
    public boolean remover(int id) {
        return false;
    }

//====================================================================================================================
//====================================================================================================================
    @Override
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
    @Override
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
