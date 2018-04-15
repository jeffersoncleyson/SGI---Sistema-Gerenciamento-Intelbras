/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.intelbras.controler;

import br.com.intelbras.model.Produto;
import br.com.intelbras.model.ProdutoDAO;
import br.com.intelbras.model.Venda;
import br.com.intelbras.model.VendaDAO;
import br.com.intelbras.view.VendasView;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author WesleyReis
 */
public class VendasControler implements AcaoTela {

    private VendasView tela;
    private VendaDAO vendaDAO;
    private ProdutoDAO produtoDAO;

    private DefaultTableModel dtmVenda;
    private ArrayList<Produto> arrayVenda;

    private DefaultTableModel dtmProduto;
    private ArrayList<Object> arrayProduto;

    private HashMap<String, Object> mapa;

    private boolean editar = false;
    private int idEdicao = -1;

    public VendasControler(HashMap<String, Object> mapa) {
        this.vendaDAO = new VendaDAO();
        this.produtoDAO = new ProdutoDAO();
        arrayProduto = new ArrayList<>();
        arrayVenda = new ArrayList<>();
        this.tela = ((VendasView) mapa.get("tela"));
        this.mapa = mapa;
    }

    @Override
    public void cadastrar() {
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
        dtmProduto = (DefaultTableModel) tabela.getModel();

        arrayProduto = produtoDAO.listarTodos();

        if (arrayProduto != null) {
            for (Object object : arrayProduto) {
                Produto produto = (Produto) object;
                if (produto != null) {
                    this.dtmProduto.insertRow(dtmProduto.getRowCount(), new Object[]{
                        produto.getIdProduto(),
                        produto.getDescricaoProduto(),
                        produto.getMarcaProduto(),
                        produto.getValorProduto()
                    });
                }
            }
        }
    }

    public void preencherTabela(JTable tabela, int index) {
        dtmVenda = (DefaultTableModel) tabela.getModel();

        if (arrayProduto != null && index != -1) {

            this.dtmVenda.insertRow(dtmVenda.getRowCount(), new Object[]{
                ((Produto) arrayProduto.get(index)).getIdProduto(),
                ((Produto) arrayProduto.get(index)).getDescricaoProduto(),
                ((Produto) arrayProduto.get(index)).getMarcaProduto(),
                ((Produto) arrayProduto.get(index)).getValorProduto()
            });

            this.arrayVenda.add(((Produto) arrayProduto.get(index)));

            calculaValor();
        }
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

    public void estadoBotoes(int estado) {
        switch (estado) {
            case 1:
                ((JButton) mapa.get("btn_adicionarCliente")).setEnabled(true);
                ((JButton) mapa.get("btn_adicionarProduto")).setEnabled(true);
                ((JButton) mapa.get("btn_cancelar")).setEnabled(true);
                ((JButton) mapa.get("btn_finalizar")).setEnabled(true);
                break;
            case 0:
                ((JButton) mapa.get("btn_adicionarCliente")).setEnabled(true);
                ((JButton) mapa.get("btn_adicionarProduto")).setEnabled(true);
                ((JButton) mapa.get("btn_cancelar")).setEnabled(true);
                ((JButton) mapa.get("btn_finalizar")).setEnabled(true);
                break;
            case 2:
                ((JButton) mapa.get("btn_adicionarCliente")).setEnabled(true);
                ((JButton) mapa.get("btn_adicionarProduto")).setEnabled(true);
                ((JButton) mapa.get("btn_cancelar")).setEnabled(true);
                ((JButton) mapa.get("btn_finalizar")).setEnabled(true);
                break;
            default:
                break;
        }
    }

    public void removerTabela(JTable tbl_compras, int selectedRow) {
        if (arrayVenda != null && selectedRow != -1) {
            arrayVenda.remove(selectedRow);
            ((DefaultTableModel) tbl_compras.getModel()).removeRow(selectedRow);
            calculaValor();
        }
    }
    
    private void calculaValor(){
        float valor = 0;
            for (Produto p : arrayVenda) {
                valor = valor + p.getValorProduto();
            }
            ((JLabel) mapa.get("lbl_valorTotalCompra")).setText("");
            ((JLabel) mapa.get("lbl_valorTotalCompra")).setText("R$ " + valor);
    }

    public void mostrarDescricao(int selectedRow) {
        ((JLabel) mapa.get("lbl_descricao")).setText("" + arrayVenda.get(selectedRow).getDescricaoProduto());
        ((JLabel) mapa.get("lbl_marca")).setText("" + arrayVenda.get(selectedRow).getMarcaProduto());
        ((JLabel) mapa.get("lbl_modelo")).setText("" + arrayVenda.get(selectedRow).getModeloProduto());
        ((JLabel) mapa.get("lbl_valor")).setText("" + arrayVenda.get(selectedRow).getValorProduto());
        ((JLabel) mapa.get("lbl_obs")).setText("" + arrayVenda.get(selectedRow).getObsProduto());
    }

}
