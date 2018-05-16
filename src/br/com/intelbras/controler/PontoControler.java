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
import javax.swing.JTextField;
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

    private void atualiza() {
        array = pontoDAO.listarTodos();
        tela.repaint();
    }

    public void preencherTabela() {
        dtm = (DefaultTableModel) ((JTable) mapa.get("tbl_ponto")).getModel();
        dtm.getDataVector().removeAllElements();
        atualiza();

        if (array != null) {
            for (Object object : array) {
                Ponto ponto = (Ponto) object;
                if (ponto != null) {
                    this.dtm.insertRow(dtm.getRowCount(), new Object[]{
                        ponto.getIdPonto(),
                        ponto.getFuncionario().getSetorFuncionario(),
                        ponto.getFuncionario().getNomeFuncionario(),
                        ponto.getDataPonto()
                    });
                }
            }
        }

    }

    public void pesquisaSetor(String setor) {
        dtm = (DefaultTableModel) ((JTable) mapa.get("tbl_ponto")).getModel();
        dtm.getDataVector().removeAllElements();
        limpaCampos(3);
        atualiza();

        if (array != null) {
            for (Object object : array) {
                Ponto ponto = (Ponto) object;
                if (ponto != null) {
                    if (ponto.getFuncionario().getSetorFuncionario().equals(setor)) {
                        this.dtm.insertRow(dtm.getRowCount(), new Object[]{
                            ponto.getIdPonto(),
                            ponto.getFuncionario().getSetorFuncionario(),
                            ponto.getFuncionario().getNomeFuncionario(),
                            ponto.getDataPonto()
                        });
                    }
                }
            }
        }
    }

    public void pesquisaNome(String nome) {
        dtm = (DefaultTableModel) ((JTable) mapa.get("tbl_ponto")).getModel();
        dtm.getDataVector().removeAllElements();
        limpaCampos(2);
        atualiza();

        if (array != null) {
            for (Object object : array) {
                Ponto ponto = (Ponto) object;
                if (ponto != null) {
                    if (ponto.getFuncionario().getNomeFuncionario().equals(nome)) {
                        this.dtm.insertRow(dtm.getRowCount(), new Object[]{
                            ponto.getIdPonto(),
                            ponto.getFuncionario().getSetorFuncionario(),
                            ponto.getFuncionario().getNomeFuncionario(),
                            ponto.getDataPonto()
                        });
                    }
                }
            }
        }
    }

    public void pesquisaData(String data) {
        dtm = (DefaultTableModel) ((JTable) mapa.get("tbl_ponto")).getModel();
        dtm.getDataVector().removeAllElements();
        limpaCampos(1);
        atualiza();

        if (array != null) {
            for (Object object : array) {
                Ponto ponto = (Ponto) object;
                if (ponto != null) {
                    if (ponto.getDataPonto().equals(data)) {
                        this.dtm.insertRow(dtm.getRowCount(), new Object[]{
                            ponto.getIdPonto(),
                            ponto.getFuncionario().getSetorFuncionario(),
                            ponto.getFuncionario().getNomeFuncionario(),
                            ponto.getDataPonto()
                        });
                    }
                }
            }
        }
    }
    
    private void limpaCampos(int i){
        switch (i) {
            case 1://data
                ((JTextField) mapa.get("txt_nome")).setText("");
                ((JTextField) mapa.get("txt_setor")).setText("");
                break;
            case 2://nome
                ((JTextField) mapa.get("txt_data")).setText("");
                ((JTextField) mapa.get("txt_setor")).setText("");
                break;
            case 3://setor
                ((JTextField) mapa.get("txt_data")).setText("");
                ((JTextField) mapa.get("txt_nome")).setText("");
                break;
        }
    }
}
