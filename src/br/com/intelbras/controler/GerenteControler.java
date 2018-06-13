/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.intelbras.controler;

import br.com.intelbras.model.Funcionario;
import br.com.intelbras.model.VendaDAO;
import br.com.intelbras.view.VendasView;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author WesleyReis
 */
public class GerenteControler {

    private VendasView tela;
    private DefaultTableModel dtm;
    private ArrayList<Object> array;
    private HashMap<String, Object> mapa;
    VendaDAO vendaDAO;

    private boolean editar = false;
    private int idEdicao = -1;

    public GerenteControler(HashMap<String, Object> mapa) {
        this.vendaDAO = new VendaDAO();
        this.tela = ((VendasView) mapa.get("tela"));
        this.mapa = mapa;
    }

    public void atualizar(JTable tabela) {
        dtm.getDataVector().removeAllElements();
        this.preencherTabela(tabela);
    }

    public void cancelar() {
        this.editar = false;
        this.idEdicao = -1;
        this.limparCampos();
        this.estadoBotoes(0);
    }

    public void excluir(int id) {

        if (this.vendaDAO.remover(id)) {
            JOptionPane.showMessageDialog(tela, "Excluido com sucesso!", "Exclusão", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(tela, "Falha ao excluir!", "Exclusão", JOptionPane.ERROR_MESSAGE);
        }

    }

    public void preencherTabela(JTable tabela) {
        dtm = (DefaultTableModel) tabela.getModel();

        array = vendaDAO.listarTodos();

        if (array != null) {
            for (Object object : array) {
                Funcionario funcionario = (Funcionario) object;
                if (funcionario != null) {
                    this.dtm.insertRow(dtm.getRowCount(), new Object[]{
                        funcionario.getIdFuncionario(),
                        funcionario.getNomeFuncionario(),
                        funcionario.getCpfFuncionario(),
                        funcionario.getTelefoneFuncionario()
                    });
                }
            }
        }
    }

    public void estadoBotoes(int estado) {
        switch (estado) {
            case 1:
                ((JButton) mapa.get("btn_cadastrar")).setEnabled(false);
                ((JButton) mapa.get("btn_editar")).setEnabled(true);
                ((JButton) mapa.get("btn_excluir")).setEnabled(true);
                ((JButton) mapa.get("btn_finalizar")).setEnabled(false);
                ((JButton) mapa.get("btn_atualizar")).setEnabled(true);
                ((JButton) mapa.get("btn_cancelar")).setEnabled(false);
                break;
            case 0:
                ((JButton) mapa.get("btn_cadastrar")).setEnabled(true);
                ((JButton) mapa.get("btn_editar")).setEnabled(false);
                ((JButton) mapa.get("btn_excluir")).setEnabled(false);
                ((JButton) mapa.get("btn_finalizar")).setEnabled(false);
                ((JButton) mapa.get("btn_atualizar")).setEnabled(false);
                ((JButton) mapa.get("btn_cancelar")).setEnabled(false);
                break;
            case 2:
                ((JButton) mapa.get("btn_cadastrar")).setEnabled(false);
                ((JButton) mapa.get("btn_editar")).setEnabled(false);
                ((JButton) mapa.get("btn_excluir")).setEnabled(false);
                ((JButton) mapa.get("btn_finalizar")).setEnabled(true);
                ((JButton) mapa.get("btn_atualizar")).setEnabled(false);
                ((JButton) mapa.get("btn_cancelar")).setEnabled(true);
                break;
            default:
                break;
        }
    }

    private void limparCampos() {
        ((JTabbedPane) mapa.get("tbd_abas")).setSelectedIndex(0);
        ((JTextField) mapa.get("txt_nome")).setText("");
        ((JTextField) mapa.get("txt_bairro")).setText("");
        ((JTextField) mapa.get("txt_cep")).setText("");
        ((JRadioButton) mapa.get("rbtn_sexo")).setSelected(true);
        ((JTextField) mapa.get("txt_cpf")).setText("");
        ((JTextField) mapa.get("txt_endereco")).setText("");
        ((JTextField) mapa.get("txt_rg")).setText("");
        ((JTextField) mapa.get("txt_salario")).setText("");
        ((JTextField) mapa.get("txt_setor")).setText("");
        ((JTextField) mapa.get("txt_telefone")).setText("");
        ((JTextField) mapa.get("txt_comissao")).setText("");
    }

    public void verificaAba(int aba) {
        if (this.editar) {
            ((JTabbedPane) mapa.get("tbd_abas")).setSelectedIndex(0);
        } else {
            estadoBotoes(aba);
        }

    }
}
