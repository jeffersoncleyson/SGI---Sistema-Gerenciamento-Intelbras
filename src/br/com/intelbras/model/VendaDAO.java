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
public class VendaDAO implements DAO {

    private static VendaDAO uniqueInstance;
    BancoDados _BD = new BancoDados();

    private Connection _con = null;
    private ResultSet _rs = null;
    private Statement _st = null;
    private PreparedStatement _pst = null;

    private VendaDAO() {
        this._BD = new BancoDados();
    }

    public static synchronized VendaDAO getInstance(){
        if(uniqueInstance==null){
            uniqueInstance = new VendaDAO();
        }
        return uniqueInstance;
    }
    
    public static void main(String[] args) {

        /*Produto p = new Produto();
        p.setIdProduto(1);
        p.setMarcaProduto("Bom Bril");
        p.setModeloProduto("Palha de Aço");
        p.setValorProduto(4);

        Venda venda = new Venda();
        ArrayList<Produto> array = new ArrayList<>();
        array.add(p);

        venda.setClienteId(1);
        venda.setFuncionarioId(1);
        venda.setValorTotalVenda(40);
        venda.setProdutos(array);

        VendaDAO v = new VendaDAO();
        v.cadastrar(venda);*/
        
        VendaDAO v = new VendaDAO();
        for (Object obj : v.listarTodos()) {
            Venda venda = (Venda) obj;
            
            System.out.println(venda.getIdVenda());
            System.out.println(venda.getValorTotalVenda());
            for (Produto produto : venda.getProdutos()) {
                System.out.println("\t"+produto.getIdProduto());
                System.out.println("\t"+produto.getMarcaProduto());
                
            }
            
        }
        
    }

//====================================================================================================================
//====================================================================================================================
    public ArrayList<Object> listarTodos() {
        abrirConexao();
        try {

            ArrayList<Object> array = new ArrayList<>();
            

            this._st = this._con.createStatement();
            this._rs = this._st.executeQuery("SELECT * FROM Vendas");

            while (this._rs.next()) {
                Venda venda = new Venda();

                venda.setIdVenda(this._rs.getInt(1));
                venda.setDataVenda(this._rs.getString(2));
                venda.setValorTotalVenda(this._rs.getFloat(3));
                venda.setClienteId(this._rs.getInt(4));
                venda.setFuncionarioId(this._rs.getInt(5));

                array.add(venda);
            }
            //===============================================================
            
            
            for (Object obj : array) {
                ArrayList<Produto> produtos = new ArrayList<>();
                Venda venda = (Venda) obj;
                
                this._pst = _con.prepareStatement("SELECT * FROM produto INNER JOIN vendas_has_produto ON "
                    + "produto.idProduto = vendas_has_produto.Produto_idProduto WHERE vendas_has_produto.Vendas_idVendas = ?;");
                this._pst.setInt(1, venda.getIdVenda());
                
                this._rs = this._pst.executeQuery();
                
                while (this._rs.next()) {
                    Produto produto = new Produto();
                    
                    produto.setIdProduto(this._rs.getInt(1));
                    produto.setDescricaoProduto(this._rs.getString(2));
                    produto.setMarcaProduto(this._rs.getString(3));
                    produto.setValorProduto(this._rs.getFloat(4));
                    produto.setModeloProduto(this._rs.getString(5));
                    produto.setObsProduto(this._rs.getString(6));
                    
                    produtos.add(produto);
                    
                }
                venda.setProdutos(produtos);
            }
            
            return array;

        } catch (Exception ex) {
            System.out.println("Erro: Conexão Banco! :( Listagem de vendas");
        } finally {
            fecharConexao();
        }
        return null;
    }

//====================================================================================================================
//====================================================================================================================
    public boolean cadastrar(Object obj) {
        boolean gravou = true;

        Venda venda = (Venda) obj;
        abrirConexao();
        try {

            this._pst = _con.prepareStatement("INSERT INTO vendas(`dataVenda`,`valorTotalVenda`,`Cliente_idCliente`,`Funcionario_idFuncionario`) VALUES(?,?,?,?);");
            this._pst.setString(1, venda.getDataVenda());
            this._pst.setFloat(2, venda.getValorTotalVenda());
            this._pst.setInt(3, venda.getClienteId());
            this._pst.setInt(4, venda.getFuncionarioId());

            this._pst.executeUpdate();

            //========================== pegar ultimo id
            _st = _con.createStatement();
            // O ResultSet gera uma tabela de dados retornados por uma pesquisa SQL.
            _rs = _st.executeQuery("SELECT idVendas FROM vendas ORDER BY idVendas DESC LIMIT 1;");

            while (_rs.next()) {
                venda.setIdVenda(_rs.getInt(1));
                //System.out.println(v.getId());
            }

            for (Produto produto : venda.getProdutos()) {
                this._pst = _con.prepareStatement("INSERT INTO vendas_has_produto(`Vendas_idVendas`,`Produto_idProduto`)VALUES(?,?);");
                this._pst.setInt(1, venda.getIdVenda());
                this._pst.setInt(2, produto.getIdProduto());
            }

            this._pst.executeUpdate();

        } catch (Exception ex) {
            gravou = false;
            System.out.println("Erro: Conexão Banco! :( Cadastro de vendas");
            System.out.println(ex);
        } finally {
            fecharConexao();
        }
        return gravou;
    }

//              FORA DO ESCOPO    
//
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
            this._pst = this._con.prepareStatement("DELETE FROM `vendas` WHERE idVendas = ?");
            this._pst.setInt(1, id);
            this._pst.executeUpdate();

            return true;
        } catch (Exception ex) {
            System.out.println("Erro: Conexão Banco! :( Remover venda");
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
