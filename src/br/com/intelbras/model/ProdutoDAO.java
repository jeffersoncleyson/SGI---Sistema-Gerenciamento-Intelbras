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
public class ProdutoDAO implements DAO {

    BancoDados _BD = new BancoDados();

    private Connection _con = null;
    private ResultSet _rs = null;
    private Statement _st = null;
    private PreparedStatement _pst = null;

//====================================================================================================================
//====================================================================================================================
    @Override
    public ArrayList<Object> listarTodos() {
        abrirConexao();
        try {
            ArrayList<Object> array = new ArrayList<>();

            this._st = this._con.createStatement();
            this._rs = this._st.executeQuery("SELECT * FROM produto");

            while (this._rs.next()) {
                Produto produto = new Produto();

                produto.setIdProduto(this._rs.getInt(1));
                produto.setDescricaoProduto(this._rs.getString(2));
                produto.setMarcaProduto(this._rs.getString(3));
                produto.setValorProduto(this._rs.getFloat(4));
                produto.setModeloProduto(this._rs.getString(5));
                produto.setObsProduto(this._rs.getString(6));


                array.add(produto);
            }

            return array;

        } catch (Exception ex) {
            System.out.println("Erro: Conexão Banco! :(");
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

            Produto produto = (Produto) obj;

            this._pst = _con.prepareStatement("INSERT INTO `produto`(`descricaoProduto`,`marcaProduto`,`valorProduto`,"
                    + "`modeloProduto`,`obsProduto`) VALUES(?,?,?,?,?);");
            this._pst.setString(1, produto.getDescricaoProduto());
            this._pst.setString(2, produto.getMarcaProduto());
            this._pst.setFloat(3, produto.getValorProduto());
            this._pst.setString(4, produto.getModeloProduto());
            this._pst.setString(5, produto.getObsProduto());

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

    @Override
    public boolean editar(Object obj) {
        abrirConexao();
        boolean teste = true;
        try {
            Produto produto = (Produto) obj;
             System.out.println(produto);

            this._pst = this._con.prepareStatement("UPDATE `intelbras`.`produto` SET `descricaoProduto` = ?, `marcaProduto` = ?, `valorProduto` = ?,`modeloProduto` = ?, `obsProduto` = ? WHERE `idProduto` = ?");
            this._pst.setString(1, produto.getDescricaoProduto());
            this._pst.setString(2, produto.getMarcaProduto());
            this._pst.setFloat(3, produto.getValorProduto());
            this._pst.setString(4, produto.getModeloProduto());
            this._pst.setString(5, produto.getObsProduto());

            
            this._pst.setInt(6, produto.getIdProduto());

            this._pst.executeUpdate();

        } catch (Exception ex) {
            System.out.println("Erro: Conexão Banco! :(");
            System.out.println(ex);
            teste = false;
        } finally {
            fecharConexao();
        }
        return teste;
    }

//====================================================================================================================
//====================================================================================================================    
    @Override
    public boolean remover(int id) {
        abrirConexao();
        try {

            this._pst = this._con.prepareStatement("DELETE FROM `intelbras`.`produto` WHERE idProduto = ?");
            this._pst.setInt(1, id);
            this._pst.executeUpdate();

            return true;
        } catch (Exception ex) {
            System.out.println("Erro: Conexão Banco! :(");
            System.out.println(ex);
        } finally {
            fecharConexao();
        }
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
