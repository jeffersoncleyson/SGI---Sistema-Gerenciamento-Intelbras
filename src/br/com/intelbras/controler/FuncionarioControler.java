/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.intelbras.controler;

import br.com.intelbras.model.Funcionario;
import br.com.intelbras.model.FuncionarioDAO;
import br.com.intelbras.view.FuncionarioView;
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
public class FuncionarioControler implements AcaoTela{

    private FuncionarioView tela;
    private DefaultTableModel dtm;
    private ArrayList<Object> array;
    private HashMap<String, Object> mapa;
    FuncionarioDAO funcionarioDAO;
    
    private boolean editar = false;
    private int idEdicao = -1;
    
    public FuncionarioControler(HashMap<String, Object> mapa) {
        this.funcionarioDAO = new FuncionarioDAO();
        this.tela = ((FuncionarioView) mapa.get("tela"));
        this.mapa = mapa;
    }
    
    @Override
    public void cadastrar( ) {
        Funcionario funcionario = new Funcionario();
        funcionario.setNomeFuncionario(((JTextField) mapa.get("txt_nome")).getText());
        funcionario.setCepFuncionario(((JTextField) mapa.get("txt_cep")).getText());
        funcionario.setComissaoFuncionario(Float.parseFloat(((JTextField) mapa.get("txt_comissao")).getText()));
        funcionario.setCpfFuncionario(((JTextField) mapa.get("txt_cpf")).getText());
        funcionario.setEnderecoFuncionario(((JTextField) mapa.get("txt_endereco")).getText());
        funcionario.setSalarioFuncionario(Float.parseFloat(((JTextField) mapa.get("txt_salario")).getText()));
        funcionario.setRgFuncionario(((JTextField) mapa.get("txt_rg")).getText());
        funcionario.setSetorFuncionario(((JTextField) mapa.get("txt_setor")).getText());
        funcionario.setTelefoneFuncionario(((JTextField) mapa.get("txt_telefone")).getText());
        funcionario.setSexoFuncionario(((JRadioButton) mapa.get("rbtn_sexo")).isSelected() ? "Masculino" : "Feminino");
        
        if (funcionarioDAO.cadastrar(funcionario)) {
            JOptionPane.showMessageDialog(tela, "Cadastrado com sucesso", "Cadastro", JOptionPane.OK_OPTION);
            this.cancelar();
            // limpar campos
        } else {
            JOptionPane.showMessageDialog(tela, "Erro ao cadastrar", "Cadastro", JOptionPane.OK_OPTION);
        }
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
        this.editar = false;
        this.idEdicao = -1;
        this.limparCampos();
        this.estadoBotoes(0);
    }

    @Override
    public void finalizar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void preencherTabela(JTable tabela) {
        
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
    }
    
}
