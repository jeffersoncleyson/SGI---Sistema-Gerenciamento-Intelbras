/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.intelbras.controler;

import br.com.intelbras.model.Produto;
import br.com.intelbras.model.ProdutoDAO;
import br.com.intelbras.view.ProdutoView;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author WesleyReis
 */
public class ProdutosControler implements AcaoTela {

    ProdutoDAO produtoDAO;
    private ProdutoView tela;
    private DefaultTableModel dtm;
    private ArrayList<Object> array;
    private HashMap<String, Object> mapa;

    private boolean editar = false;
    private int idEdicao = -1;

    public ProdutosControler(HashMap<String, Object> mapa) {
        this.produtoDAO = new ProdutoDAO();
        this.tela = ((ProdutoView) mapa.get("tela"));
        this.mapa = mapa;
    }

    @Override
    public void cadastrar() {
        Produto produto = new Produto();
        produto.setDescricaoProduto(((JTextField) mapa.get("txt_descricao")).getText());
        produto.setMarcaProduto(((JTextField) mapa.get("txt_marca")).getText());
        produto.setModeloProduto(((JTextField) mapa.get("txt_modelo")).getText());
        produto.setObsProduto(((JTextField) mapa.get("txt_observacao")).getText());
        produto.setValorProduto(Float.parseFloat(((JTextField) mapa.get("txt_valor")).getText()));

        if (produtoDAO.cadastrar(produto)) {
            JOptionPane.showMessageDialog(tela, "Cadastrado com sucesso", "Cadastro", JOptionPane.OK_OPTION);
            this.cancelar();
            // limpar campos
        } else {
            JOptionPane.showMessageDialog(tela, "Erro ao cadastrar", "Cadastro", JOptionPane.OK_OPTION);
        }
    }

    public void edicao(int row) {
        ((JTabbedPane) mapa.get("tbd_abas")).setSelectedIndex(0);
        this.editar = true;
        this.estadoBotoes(2);

        Produto produto = (Produto) array.get(row);
        this.idEdicao = produto.getIdProduto();

        ((JTextField) mapa.get("txt_descricao")).setText(produto.getDescricaoProduto());
        ((JTextField) mapa.get("txt_marca")).setText(produto.getMarcaProduto());
        ((JTextField) mapa.get("txt_modelo")).setText(produto.getModeloProduto());
        ((JTextField) mapa.get("txt_observacao")).setText(produto.getObsProduto());
        ((JTextField) mapa.get("txt_valor")).setText("" + produto.getValorProduto());
    }

    @Override
    public void editar(Object obj) {
        try {
            produtoDAO.editar(obj);
            this.cancelar();
            JOptionPane.showMessageDialog(tela, "Editado com sucesso!", "Edição", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void excluir(int id) {
        try {
            if (id < 0) {
                JOptionPane.showMessageDialog(tela, "Nenhuma Linha Selecionada", "Excluir", JOptionPane.WARNING_MESSAGE);
            } else {
                if (JOptionPane.showConfirmDialog(tela, "Deseja excluir o produto?", "Excluir", JOptionPane.YES_NO_OPTION) != 1) {
                    produtoDAO.remover(((Produto) array.get(id)).getIdProduto());
                    dtm.getDataVector().removeAllElements();
                    this.preencherTabela((JTable)this.mapa.get("tbl_listagem"));
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(tela, "Erro ao excluir", "Erro", JOptionPane.ERROR);
        }
    }

    @Override
    public void atualizar(JTable tabela) {
        dtm.getDataVector().removeAllElements();
        this.preencherTabela(tabela);
    }

    @Override
    public void cancelar() {
        this.editar = false;
        this.idEdicao = -1;
        this.limparCampos();
        this.estadoBotoes(0);
    }

    @Override
    public void finalizar() {
        Produto produto = new Produto();
        produto.setIdProduto(this.idEdicao);
        produto.setDescricaoProduto(((JTextField) mapa.get("txt_descricao")).getText());
        produto.setMarcaProduto(((JTextField) mapa.get("txt_marca")).getText());
        produto.setModeloProduto(((JTextField) mapa.get("txt_modelo")).getText());
        produto.setObsProduto(((JTextField) mapa.get("txt_observacao")).getText());
        produto.setValorProduto(Float.parseFloat(((JTextField) mapa.get("txt_valor")).getText()));

        if (validaDados()) {
            this.editar(produto);
        } else {

        }
    }

    @Override
    public void preencherTabela(JTable tabela) {
        dtm = (DefaultTableModel) tabela.getModel();

        array = produtoDAO.listarTodos();

        if (array != null) {
            for (Object object : array) {
                Produto produto = (Produto) object;
                if (produto != null) {
                    this.dtm.insertRow(dtm.getRowCount(), new Object[]{
                        produto.getIdProduto(),
                        produto.getDescricaoProduto(),
                        produto.getMarcaProduto(),
                        produto.getValorProduto()
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

    public void verificaAba(int aba) {
        if (this.editar) {
            ((JTabbedPane) mapa.get("tbd_abas")).setSelectedIndex(0);
        } else {
            estadoBotoes(aba);
        }

    }

    private void limparCampos() {
        ((JTabbedPane) mapa.get("tbd_abas")).setSelectedIndex(0);
        ((JTextField) mapa.get("txt_descricao")).setText("");
        ((JTextField) mapa.get("txt_marca")).setText("");
        ((JTextField) mapa.get("txt_modelo")).setText("");
        ((JTextField) mapa.get("txt_observacao")).setText("");
        ((JTextField) mapa.get("txt_valor")).setText("");
    }

    private boolean validaDados() {

        return true;
    }

}
