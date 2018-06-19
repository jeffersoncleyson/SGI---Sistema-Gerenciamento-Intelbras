/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.intelbras.controler;

import br.com.intelbras.model.Cliente;
import br.com.intelbras.model.ClienteDAO;
import br.com.intelbras.model.Funcionario;
import br.com.intelbras.model.Produto;
import br.com.intelbras.model.ProdutoDAO;
import br.com.intelbras.model.Venda;
import br.com.intelbras.model.VendaDAO;
import br.com.intelbras.view.ClienteAddVenda;
import br.com.intelbras.view.VendasView;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
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
        this.vendaDAO = VendaDAO.getInstance();
        this.produtoDAO = ProdutoDAO.getInstance();
        arrayProduto = new ArrayList<>();
        arrayVenda = new ArrayList<>();
        this.tela = ((VendasView) mapa.get("tela"));
        this.mapa = mapa;
    }

    @Override
    public void cadastrar() {

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
        dtmProduto.getDataVector().removeAllElements();
        this.preencherTabela(tabela);
    }

    @Override
    public void cancelar() {

        dtmVenda.getDataVector().removeAllElements();
        tela.repaint();
        cliente = null;
        ((JLabel) mapa.get("lbl_cliente")).setText("");
        ((JLabel) mapa.get("lbl_valorTotalCompra")).setText("R$ ");
        ((JLabel) mapa.get("lbl_descricao")).setText("");
        ((JLabel) mapa.get("lbl_marca")).setText("");
        ((JLabel) mapa.get("lbl_modelo")).setText("");
        ((JLabel) mapa.get("lbl_valor")).setText("");
        ((JLabel) mapa.get("lbl_obs")).setText("");

        //this.tela.dispose();
    }

    @Override
    public void finalizar() {

        if (this.arrayVenda == null) {
            JOptionPane.showMessageDialog(tela, "Referência inexistente", "Erro", JOptionPane.ERROR_MESSAGE);
        } else if (this.arrayVenda.size() == 0) {
            JOptionPane.showMessageDialog(tela, "Adicione no mínimo 1 produto", "Erro", JOptionPane.ERROR_MESSAGE);
        } else if (cliente == null) {
            JOptionPane.showMessageDialog(tela, "Adicione o cliente", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {

            Venda venda = new Venda();
            venda.setClienteId(cliente.getIdCliente());
            venda.setFuncionarioId(Funcionario.idAcessoLogado);  //=================================================================== mudar aki
            venda.setProdutos(arrayVenda);
            venda.setValorTotalVenda(calculaValor());

            Date data = new Date(System.currentTimeMillis());
            SimpleDateFormat formatarDate = new SimpleDateFormat("dd-MM-yyyy");

            venda.setDataVenda(formatarDate.format(data));

            if (this.vendaDAO.cadastrar(venda)) {
                JOptionPane.showMessageDialog(tela, "Cadastrado com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

                dtmVenda.getDataVector().removeAllElements();
                tela.repaint();
                cliente = null;
                ((JLabel) mapa.get("lbl_cliente")).setText("");
                ((JLabel) mapa.get("lbl_valorTotalCompra")).setText("R$ ");
                ((JLabel) mapa.get("lbl_descricao")).setText("");
                ((JLabel) mapa.get("lbl_marca")).setText("");
                ((JLabel) mapa.get("lbl_modelo")).setText("");
                ((JLabel) mapa.get("lbl_valor")).setText("");
                ((JLabel) mapa.get("lbl_obs")).setText("");

            } else {
                JOptionPane.showMessageDialog(tela, "Erro ao cadastrar", "Erro", JOptionPane.ERROR_MESSAGE);
            }

        }

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

    public void estadoBotoes(int estado) {
        switch (estado) {
            case 1:
                ((JButton) mapa.get("btn_adicionarCliente")).setEnabled(true);
                ((JButton) mapa.get("btn_cancelar")).setEnabled(true);
                ((JButton) mapa.get("btn_finalizar")).setEnabled(true);
                break;
            case 0:
                ((JButton) mapa.get("btn_adicionarCliente")).setEnabled(true);
                ((JButton) mapa.get("btn_cancelar")).setEnabled(true);
                ((JButton) mapa.get("btn_finalizar")).setEnabled(true);
                break;
            case 2:
                ((JButton) mapa.get("btn_adicionarCliente")).setEnabled(true);
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

    private float calculaValor() {
        float valor = 0;
        for (Produto p : arrayVenda) {
            valor = valor + p.getValorProduto();
        }
        ((JLabel) mapa.get("lbl_valorTotalCompra")).setText("");
        ((JLabel) mapa.get("lbl_valorTotalCompra")).setText("R$ " + valor);

        return valor;
    }

    public void mostrarDescricao(int selectedRow) {
        ((JLabel) mapa.get("lbl_descricao")).setText("" + arrayVenda.get(selectedRow).getDescricaoProduto());
        ((JLabel) mapa.get("lbl_marca")).setText("" + arrayVenda.get(selectedRow).getMarcaProduto());
        ((JLabel) mapa.get("lbl_modelo")).setText("" + arrayVenda.get(selectedRow).getModeloProduto());
        ((JLabel) mapa.get("lbl_valor")).setText("" + arrayVenda.get(selectedRow).getValorProduto());
        ((JLabel) mapa.get("lbl_obs")).setText("" + arrayVenda.get(selectedRow).getObsProduto());
    }

    //==========================================================================
    //======================== Tela add cliente ================================
    private HashMap<String, Object> mapaCliente;
    private ClienteDAO clienteDAO = ClienteDAO.getInstance();
    private DefaultTableModel dtmCliente;
    private ArrayList<Object> arrayCliente;
    Cliente cliente;

    public void abreBuscaCliente() {
        ClienteAddVenda telaBuscaCliente = new ClienteAddVenda();
        telaBuscaCliente.setVisible(true);
        telaBuscaCliente.setLocationRelativeTo(tela);

        mapaCliente = telaBuscaCliente.getMap();
        preencheTabelaCliente("");

        ((JButton) mapaCliente.get("btn_pesquisaSetor")).addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dtmCliente.getDataVector().removeAllElements();
                preencheTabelaCliente(((JTextField) mapaCliente.get("txt_cpf")).getText());

            }
        });

        ((JTable) mapaCliente.get("tbl_cliente")).addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    String cpf = (String) dtmCliente.getValueAt(((JTable) mapaCliente.get("tbl_cliente")).getSelectedRow(), 1);
                    System.out.println(cpf);
                    if (arrayCliente != null) {
                        for (Object c : arrayCliente) {
                            if (((Cliente) c).getCpfCliente().equals(cpf)) {
                                cliente = (Cliente) c;
                                ((JLabel) mapa.get("lbl_cliente")).setText(cliente.getNomeCliente());

                            }
                        }
                    }
                }
            }
        });

    }

    public void preencheTabelaCliente(String palavra) {

        dtmCliente = (DefaultTableModel) ((JTable) mapaCliente.get("tbl_cliente")).getModel();

        arrayCliente = clienteDAO.listarTodos();

        if (arrayCliente != null) {
            for (Object object : arrayCliente) {
                Cliente cliente = (Cliente) object;
                if (cliente != null) {
                    if (cliente.getCpfCliente().contains(palavra)) {
                        this.dtmCliente.insertRow(dtmCliente.getRowCount(), new Object[]{
                            cliente.getNomeCliente(),
                            cliente.getCpfCliente(),
                            cliente.getTelefoneCliente()
                        });
                    }
                }
            }
        }

    }
    

    
    

}
