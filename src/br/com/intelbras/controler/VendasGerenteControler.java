/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.intelbras.controler;

import br.com.intelbras.model.Cliente;
import br.com.intelbras.model.Produto;
import br.com.intelbras.model.ProdutoDAO;
import br.com.intelbras.model.VendaDAO;
import br.com.intelbras.model.VendasGerente;
import br.com.intelbras.model.VendasGerenteDAO;
import br.com.intelbras.view.VendasGerenteView;
import br.com.intelbras.view.VendasView;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author WesleyReis
 */
public class VendasGerenteControler {

    private VendasGerenteView tela;
    //private VendaDAO vendaDAO;

    private DefaultTableModel dtmVenda;
    private ArrayList<Object> arrayVendas;
    private HashMap<String, Object> mapa;
    private VendasGerenteDAO vendasGerenteDAO;
    private DefaultTableModel dtm;

    public VendasGerenteControler(HashMap<String, Object> map) {
        this.mapa = map;

        this.tela = ((VendasGerenteView) mapa.get("tela"));
        vendasGerenteDAO = new VendasGerenteDAO();
        preencheTabela("");

    }

    public void preencheTabela(String palavra) {
        dtm = (DefaultTableModel) ((JTable) mapa.get("tbl_vendas")).getModel();
        dtm.getDataVector().removeAllElements();
        tela.repaint();

        arrayVendas = vendasGerenteDAO.listarTodos();

        if (arrayVendas != null) {
            for (Object object : arrayVendas) {
                VendasGerente vendasG = (VendasGerente) object;
                if (vendasG.getNomeCliente().contains(palavra) || vendasG.getNomeFuncionario().contains(palavra) || vendasG.getData().contains(palavra)) {
                    this.dtm.insertRow(dtm.getRowCount(), new Object[]{
                        vendasG.getIdVenda(),
                        vendasG.getNomeCliente(),
                        vendasG.getNomeFuncionario(),
                        vendasG.getData()
                    });
                }
            }
        }
        tela.repaint();
    }

}
