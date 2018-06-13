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
import javax.swing.JComboBox;
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
public class FuncionarioControler implements AcaoTela {

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
    public void cadastrar() {

        Funcionario funcionario = new Funcionario();

        if (validaDados()) {

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
            funcionario.setNivelAcesso(((JComboBox<String>) mapa.get("cbx_nivelAcesso")).getSelectedIndex());
            funcionario.setRfidFuncionario(((JTextField) mapa.get("txt_rfid")).getText());

            if (funcionarioDAO.cadastrar(funcionario)) {
                JOptionPane.showMessageDialog(tela, "Cadastrado com sucesso", "Cadastro", JOptionPane.OK_OPTION);
                this.cancelar();
                // limpar campos
            } else {
                JOptionPane.showMessageDialog(tela, "Erro ao cadastrar", "Cadastro", JOptionPane.OK_OPTION);
            }
        } else {
            JOptionPane.showMessageDialog(tela, "Preencha todos os campos", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void edicao(int row) {

        ((JTabbedPane) mapa.get("tbd_abas")).setSelectedIndex(0);
        this.editar = true;
        this.estadoBotoes(2);

        Funcionario funcionario = (Funcionario) array.get(row);
        this.idEdicao = funcionario.getIdFuncionario();

        ((JTextField) mapa.get("txt_nome")).setText(funcionario.getNomeFuncionario());
        ((JTextField) mapa.get("txt_cpf")).setText(funcionario.getCpfFuncionario());
        ((JTextField) mapa.get("txt_rg")).setText(funcionario.getRgFuncionario());
        if (funcionario.getSexoFuncionario().equals("Masculino")) {
            ((JRadioButton) mapa.get("rbtn_sexo")).setSelected(true);
        } else {
            ((JRadioButton) mapa.get("rbtn_feminino")).setSelected(true);
        }
        ((JTextField) mapa.get("txt_bairro")).setText(funcionario.getBairroFuncionario());
        ((JTextField) mapa.get("txt_cep")).setText(funcionario.getCepFuncionario());
        ((JTextField) mapa.get("txt_telefone")).setText(funcionario.getTelefoneFuncionario());
        ((JTextField) mapa.get("txt_endereco")).setText(funcionario.getEnderecoFuncionario());
        ((JTextField) mapa.get("txt_salario")).setText("" + funcionario.getSalarioFuncionario());
        ((JTextField) mapa.get("txt_comissao")).setText("" + funcionario.getComissaoFuncionario());
        ((JTextField) mapa.get("txt_setor")).setText(funcionario.getSetorFuncionario());
        ((JTextField) mapa.get("txt_rfid")).setText(funcionario.getRfidFuncionario());

        ((JComboBox<String>) mapa.get("cbx_nivelAcesso")).setSelectedIndex(funcionario.getNivelAcesso());
    }

    @Override
    public void editar(Object obj) {
        try {
            funcionarioDAO.editar(obj);
            this.cancelar();
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
                if (JOptionPane.showConfirmDialog(tela, "Deseja excluir o funcionario?", "Excluir", JOptionPane.YES_NO_OPTION) != 1) {
                    funcionarioDAO.remover(((Funcionario) array.get(id)).getIdFuncionario());
                    dtm.getDataVector().removeAllElements();
                    this.preencherTabela((JTable) this.mapa.get("tbl_listagem"));
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
        Funcionario funcionario = new Funcionario();
        funcionario.setIdFuncionario(this.idEdicao);
        funcionario.setNomeFuncionario(((JTextField) mapa.get("txt_nome")).getText());
        funcionario.setCpfFuncionario(((JTextField) mapa.get("txt_cpf")).getText());
        funcionario.setRgFuncionario(((JTextField) mapa.get("txt_rg")).getText());
        funcionario.setSexoFuncionario((((JRadioButton) mapa.get("rbtn_sexo")).isSelected()) ? "Masculino" : "Feminino");
        funcionario.setBairroFuncionario(((JTextField) mapa.get("txt_bairro")).getText());
        funcionario.setCepFuncionario(((JTextField) mapa.get("txt_cep")).getText());
        funcionario.setTelefoneFuncionario(((JTextField) mapa.get("txt_telefone")).getText());
        funcionario.setEnderecoFuncionario(((JTextField) mapa.get("txt_endereco")).getText());
        funcionario.setSalarioFuncionario(Float.parseFloat(((JTextField) mapa.get("txt_salario")).getText()));
        funcionario.setComissaoFuncionario(Float.parseFloat(((JTextField) mapa.get("txt_comissao")).getText()));
        funcionario.setSetorFuncionario(((JTextField) mapa.get("txt_setor")).getText());
        funcionario.setNivelAcesso(((JComboBox<String>) mapa.get("cbx_nivelAcesso")).getSelectedIndex());
        funcionario.setRfidFuncionario(((JTextField) mapa.get("txt_rfid")).getText());

        if (validaDados()) {
            this.editar(funcionario);
        } else {

        }
    }

    @Override
    public void preencherTabela(JTable tabela) {
        dtm = (DefaultTableModel) tabela.getModel();

        array = funcionarioDAO.listarTodos();

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
        ((JComboBox<String>) mapa.get("cbx_nivelAcesso")).setSelectedIndex(0);
        ((JTextField) mapa.get("txt_rfid")).setText("");
    }

    private boolean validaDados() {

        boolean teste = true;

        if (((JTextField) mapa.get("txt_nome")).getText().equals("")) {
            teste = false;
        }
        if (((JTextField) mapa.get("txt_cep")).getText().equals("")) {
            teste = false;
        }
        if (((JTextField) mapa.get("txt_comissao")).getText().equals("")) {
            teste = false;
        }
        if (((JTextField) mapa.get("txt_cpf")).getText().equals("")) {
            teste = false;
        }
        if (((JTextField) mapa.get("txt_endereco")).getText().equals("")) {
            teste = false;
        }
        if (((JTextField) mapa.get("txt_salario")).getText().equals("")) {
            teste = false;
        }
        if (((JTextField) mapa.get("txt_rg")).getText().equals("")) {
            teste = false;
        }
        if (((JTextField) mapa.get("txt_setor")).getText().equals("")) {
            teste = false;
        }
        if (((JTextField) mapa.get("txt_telefone")).getText().equals("")) {
            teste = false;
        }
        if (((JTextField) mapa.get("txt_rfid")).getText().equals("")) {
            teste = false;
        }

        return teste;
    }

    public void verificaAba(int aba) {
        if (this.editar) {
            ((JTabbedPane) mapa.get("tbd_abas")).setSelectedIndex(0);
        } else {
            estadoBotoes(aba);
        }

    }
}
