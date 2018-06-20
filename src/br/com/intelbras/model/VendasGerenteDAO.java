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
public class VendasGerenteDAO  implements DAO {

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
            this._rs = this._st.executeQuery("SELECT vendas.idVendas, cliente.nomeCliente, funcionario.nomeFuncionario, vendas.dataVenda from vendas "
                    + "inner join cliente on vendas.Cliente_idCliente = cliente.idCliente "
                    + "inner join funcionario on vendas.Funcionario_idFuncionario = funcionario.idFuncionario;");

            while (this._rs.next()) {
                VendasGerente vendaG = new VendasGerente();

                vendaG.setIdVenda(this._rs.getInt(1));
                vendaG.setNomeCliente(this._rs.getString(2));
                vendaG.setNomeFuncionario(this._rs.getString(3));
                vendaG.setData(this._rs.getString(4));

                array.add(vendaG);
            }
            //===============================================================
            
            
            return array;

        } catch (Exception ex) {
            System.out.println("Erro: Conexão Banco! :( Listar vendas gerente");
        } finally {
            fecharConexao();
        }
        return null;
    }

//====================================================================================================================
//====================================================================================================================
    public boolean cadastrar(Object obj) {
        return false;
    }
    
    public boolean editar(Object obj) {

        return false;
    }

//====================================================================================================================
//====================================================================================================================    
    public boolean remover(int id) {
        abrirConexao();
        try {
            this._pst = this._con.prepareStatement("DELETE FROM `vendas` WHERE idVendas = ?");
            this._pst.setInt(1, id);
            this._pst.executeUpdate();

            return true;
        } catch (Exception ex) {
            System.out.println("Erro: Conexão Banco! :( remover vendas Gerente");
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
