/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.intelbras.controler;

import br.com.intelbras.model.Cliente;
import br.com.intelbras.model.ClienteDAO;
import br.com.intelbras.view.ClienteView;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author WesleyReis
 */
public class ClienteControler implements AcaoTela {

    private ClienteView tela;
    private ClienteDAO clienteDAO;
    private DefaultTableModel dtm;
    private ArrayList<Object> array;
    private HashMap<String, Object> mapa;

    private boolean editar = false;
    private int idEdicao = -1;

    public ClienteControler(HashMap<String, Object> mapa) {
        this.clienteDAO = new ClienteDAO();
        this.tela = ((ClienteView) mapa.get("tela"));
        this.mapa = mapa;
    }

    @Override
    public void cadastrar() {

        Cliente cliente = new Cliente();
        cliente.setNomeCliente(((JTextField) mapa.get("txt_nome")).getText());
        cliente.setCpfCliente(((JTextField) mapa.get("txt_cpf")).getText());
        cliente.setRgCliente(((JTextField) mapa.get("txt_rg")).getText());
        cliente.setSexoCliente((((JRadioButton) mapa.get("rbtn_sexo")).isSelected()) ? "Masculino" : "Feminino");
        cliente.setDataNascCliente(((JTextField) mapa.get("txt_dataNasc")).getText());
        cliente.setBairroCliente(((JTextField) mapa.get("txt_bairro")).getText());
        cliente.setCepCliente(((JTextField) mapa.get("txt_cep")).getText());
        cliente.setTelefoneCliente(((JTextField) mapa.get("txt_telefone")).getText());
        cliente.setEnderecoCliente(((JTextField) mapa.get("txt_endereco")).getText());

        if (clienteDAO.cadastrar(cliente)) {
            JOptionPane.showMessageDialog(tela, "Cadastrado com sucesso", "Cadastro", JOptionPane.OK_OPTION);
            this.cancelar();
            // limpar campos
        } else {
            JOptionPane.showMessageDialog(tela, "Erro ao cadastrar", "Cadastro", JOptionPane.ERROR);
        }

    }

    public void edicao(int row) {

        ((JTabbedPane) mapa.get("tbd_abas")).setSelectedIndex(0);
        this.editar = true;
        this.estadoBotoes(2);

        Cliente cliente = (Cliente) array.get(row);
        this.idEdicao = cliente.getIdCliente();
        
        ((JTextField) mapa.get("txt_nome")).setText(cliente.getNomeCliente());
        ((JTextField) mapa.get("txt_cpf")).setText(cliente.getCpfCliente());
        ((JTextField) mapa.get("txt_rg")).setText(cliente.getRgCliente());
        if (cliente.getSexoCliente().equals("Masculino")) {
            ((JRadioButton) mapa.get("rbtn_sexo")).setSelected(true);
        } else {
            ((JRadioButton) mapa.get("rbtn_feminino")).setSelected(true);
        }
        ((JTextField) mapa.get("txt_dataNasc")).setText(cliente.getDataNascCliente());
        ((JTextField) mapa.get("txt_bairro")).setText(cliente.getBairroCliente());
        ((JTextField) mapa.get("txt_cep")).setText(cliente.getCepCliente());
        ((JTextField) mapa.get("txt_telefone")).setText(cliente.getTelefoneCliente());
        ((JTextField) mapa.get("txt_endereco")).setText(cliente.getEnderecoCliente());

    }

    @Override
    public void editar(Object obj) {
        try{
            clienteDAO.editar(obj);
            this.cancelar();
        }catch(Exception ex){
            System.out.println(ex);
        }
    }

    @Override
    public void excluir(int id) {
        try {
            if (id < 0) {
                JOptionPane.showMessageDialog(tela, "Nenhuma Linha Selecionada", "Excluir", JOptionPane.WARNING_MESSAGE);
            } else {
                if(JOptionPane.showConfirmDialog(tela, "Deseja excluir o cliente?","Excluir",JOptionPane.YES_NO_OPTION)!=1)
                    clienteDAO.remover(((Cliente) array.get(id)).getIdCliente());
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
        Cliente cliente = new Cliente();
        cliente.setIdCliente(this.idEdicao);
        cliente.setNomeCliente(((JTextField) mapa.get("txt_nome")).getText());
        cliente.setCpfCliente(((JTextField) mapa.get("txt_cpf")).getText());
        cliente.setRgCliente(((JTextField) mapa.get("txt_rg")).getText());
        cliente.setSexoCliente((((JRadioButton) mapa.get("rbtn_sexo")).isSelected()) ? "Masculino" : "Feminino");
        cliente.setDataNascCliente(((JTextField) mapa.get("txt_dataNasc")).getText());
        cliente.setBairroCliente(((JTextField) mapa.get("txt_bairro")).getText());
        cliente.setCepCliente(((JTextField) mapa.get("txt_cep")).getText());
        cliente.setTelefoneCliente(((JTextField) mapa.get("txt_telefone")).getText());
        cliente.setEnderecoCliente(((JTextField) mapa.get("txt_endereco")).getText());
        
        if(validaDados()){
            this.editar(cliente);
        }else{
            
        }
    }

    @Override
    public void preencherTabela(JTable tabela) {
        dtm = (DefaultTableModel) tabela.getModel();

        array = clienteDAO.listarTodos();

        for (Object object : array) {
            Cliente cliente = (Cliente) object;

            this.dtm.insertRow(dtm.getRowCount(), new Object[]{
                cliente.getIdCliente(),
                cliente.getNomeCliente(),
                cliente.getCpfCliente(),
                cliente.getTelefoneCliente()
            });
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
        ((JTextField) mapa.get("txt_nome")).setText("");
        ((JTextField) mapa.get("txt_cpf")).setText("");
        ((JTextField) mapa.get("txt_rg")).setText("");
        ((JRadioButton) mapa.get("rbtn_sexo")).setSelected(true);
        ((JTextField) mapa.get("txt_dataNasc")).setText("");
        ((JTextField) mapa.get("txt_bairro")).setText("");
        ((JTextField) mapa.get("txt_cep")).setText("");
        ((JTextField) mapa.get("txt_telefone")).setText("");
        ((JTextField) mapa.get("txt_endereco")).setText("");
    }

    private boolean validaDados() {
        
        
        return true;
    }
    
    
}
