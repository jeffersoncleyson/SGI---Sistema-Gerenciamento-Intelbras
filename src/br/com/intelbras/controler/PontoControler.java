/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.intelbras.controler;

import br.com.intelbras.model.Cliente;
import br.com.intelbras.model.ClienteDAO;
import br.com.intelbras.model.Ponto;
import br.com.intelbras.model.PontoDAO;
import br.com.intelbras.view.ClienteView;
import br.com.intelbras.view.PontosView;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author WesleyReis
 */
public class PontoControler {

    private PontosView tela;
    private PontoDAO pontoDAO;
    private DefaultTableModel dtm;
    private ArrayList<Object> array;
    private HashMap<String, Object> mapa;

    public PontoControler(HashMap<String, Object> mapa) {
        this.pontoDAO = new PontoDAO();
        this.mapa = mapa;
        this.tela = ((PontosView) mapa.get("tela"));
    }

    public void preencherTabela(JTable tabela) {
        dtm = (DefaultTableModel) tabela.getModel();

        array = pontoDAO.listarTodos();

        if (array != null) {
            for (Object object : array) {
                Ponto ponto = (Ponto) object;
                if (ponto != null) {
                    this.dtm.insertRow(dtm.getRowCount(), new Object[]{
                        ponto.getIdPonto(),
                        ponto.getSetorPonto(),
                        ponto.getFuncionario().getNomeFuncionario(),
                        ponto.getDataPonto()
                    });
                }
            }
        }
        
    }
    
    public void preencherTabela(JTable tbl_ponto, ArrayList<Object> array) {

        
    }
    
    private void pesquisa(){
        
    }

}
