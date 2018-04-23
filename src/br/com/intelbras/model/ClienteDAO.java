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

    private static ClienteDAO uniqueInstance;
    BancoDados _BD;

    private Connection _con = null;
    private ResultSet _rs = null;
    private Statement _st = null;
    private PreparedStatement _pst = null;

    public static void main(String[] args) throws SQLException {

        ClienteDAO cliente = new ClienteDAO();

        cliente.abrirConexao();

        cliente._st = cliente._con.createStatement();

        cliente._rs = cliente._st.executeQuery("Select * from Cliente");

        while (cliente._rs.next()) {

            System.out.println(cliente._rs.getString(1));
            System.out.println(cliente._rs.getString(2));
            System.out.println(cliente._rs.getString(3));
        }

        cliente.fecharConexao();

    }
//====================================================================================================================
//====================================================================================================================

    private ClienteDAO() {
        this._BD = new BancoDados();
    }

    public static synchronized ClienteDAO getInstance(){
        if(uniqueInstance==null){
            uniqueInstance = new ClienteDAO();
        }
        return uniqueInstance;
    }
    
//====================================================================================================================
//====================================================================================================================
    public ArrayList<Object> listarTodos() {
        abrirConexao();
        try {
            ArrayList<Object> array = new ArrayList<>();

            this._st = this._con.createStatement();
            this._rs = this._st.executeQuery("SELECT * FROM Cliente");

            while (this._rs.next()) {
                Cliente cliente = new Cliente();

                cliente.setIdCliente(this._rs.getInt(1));
                cliente.setNomeCliente(this._rs.getString(2));
                cliente.setCpfCliente(this._rs.getString(3));
                cliente.setRgCliente(this._rs.getString(4));
                cliente.setSexoCliente(this._rs.getString(5));
                cliente.setDataNascCliente(this._rs.getString(6));
                cliente.setBairroCliente(this._rs.getString(7));
                cliente.setCepCliente(this._rs.getString(8));
                cliente.setTelefoneCliente(this._rs.getString(9));
                cliente.setEnderecoCliente(this._rs.getString(10));

                array.add(cliente);
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
    public boolean cadastrar(Object obj) {

        boolean gravou = true;

        Cliente cliente = (Cliente) obj;
        abrirConexao();
        try {

            this._pst = _con.prepareStatement("INSERT INTO `intelbras`.`cliente`(`nomeCliente`,`cpfCliente`,`rgCliente`,`sexoCliente`,`dataNascCliente`,`bairroCliente`,`cepCliente`,`telefoneCliente`,`enderecoCliente`)VALUES(?,?,?,?,?,?,?,?,?);");
            this._pst.setString(1, cliente.getNomeCliente());
            this._pst.setString(2, cliente.getCpfCliente());
            this._pst.setString(3, cliente.getRgCliente());
            this._pst.setString(4, cliente.getSexoCliente());
            this._pst.setString(5, cliente.getDataNascCliente());
            this._pst.setString(6, cliente.getBairroCliente());
            this._pst.setString(7, cliente.getCepCliente());
            this._pst.setString(8, cliente.getTelefoneCliente());
            this._pst.setString(9, cliente.getEnderecoCliente());

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

//====================================================================================================================
//====================================================================================================================
    public boolean editar(Object obj) {
        abrirConexao();
        try {
            Cliente cliente = (Cliente) obj;
             System.out.println(cliente);

            this._pst = this._con.prepareStatement("UPDATE `intelbras`.`cliente` SET `nomeCliente` = ?, `cpfCliente` = ?, `rgCliente` = ?,`sexoCliente` = ?, `dataNascCliente` = ?, `bairroCliente` = ?, `cepCliente` = ?, `telefoneCliente` = ?,`enderecoCliente` = ? WHERE `idCliente` = ?");
            this._pst.setString(1, cliente.getNomeCliente());
            this._pst.setString(2, cliente.getCpfCliente());
            this._pst.setString(3, cliente.getRgCliente());
            this._pst.setString(4, cliente.getSexoCliente());
            this._pst.setString(5, cliente.getDataNascCliente());
            this._pst.setString(6, cliente.getBairroCliente());
            this._pst.setString(7, cliente.getCepCliente());
            this._pst.setString(8, cliente.getTelefoneCliente());
            this._pst.setString(9, cliente.getEnderecoCliente());
            
            this._pst.setInt(10, cliente.getIdCliente());

            this._pst.executeUpdate();

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
    public boolean remover(int id) {
        abrirConexao();
        try {

            this._pst = this._con.prepareStatement("DELETE FROM `intelbras`.`cliente` WHERE idCliente = ?");
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
