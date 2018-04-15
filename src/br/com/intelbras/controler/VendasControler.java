/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.intelbras.controler;

import br.com.intelbras.model.Venda;
import br.com.intelbras.model.VendaDAO;
import br.com.intelbras.view.VendasView;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author WesleyReis
 */
public class VendasControler implements AcaoTela{
       
    private VendasView tela;
    private VendaDAO vendaDAO;
    
    private DefaultTableModel dtmVenda;
    private ArrayList<Object> arrayVenda;
    
    private DefaultTableModel dtmProduto;
    private ArrayList<Object> arrayProduto;
    
    private HashMap<String, Object> mapa;
    
    private boolean editar = false;
    private int idEdicao = -1;

    public VendasControler() {
        
    }
    
    public VendasControler(HashMap<String, Object> mapa) {
        this.vendaDAO = new VendaDAO();
        
        this.tela = ((VendasView) mapa.get("tela"));
        this.mapa = mapa;
    }

    @Override
    public void cadastrar( ) {
        Venda venda = new Venda();
        ///venda.setProdutos(arrayVenda);
        venda.setDataVenda("fsdf");
        venda.setObsVenda("gfsfg");
        venda.setValorTotalVenda(2332);
    }

    public void edicao(int selectedRow) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void editar(Object obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void excluir(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void atualizar(JTable tabela) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void cancelar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void finalizar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void preencherTabela(JTable tabela) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void pesquisaVenda(String text) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void pesquisaFuncionario(String text) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void pesquisaData(String text) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void pesquisaCliente(String text) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void preencherTabela() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
}
