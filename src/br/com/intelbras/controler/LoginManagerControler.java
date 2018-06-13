/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.intelbras.controler;

import br.com.intelbras.model.Cliente;
import br.com.intelbras.model.Funcionario;
import br.com.intelbras.model.FuncionarioDAO;
import br.com.intelbras.model.Login;
import br.com.intelbras.model.LoginDAO;
import br.com.intelbras.view.ClienteView;
import br.com.intelbras.view.LoginManagerView;
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
public class LoginManagerControler implements AcaoTela{
    
    private LoginDAO loginDAO;
    private FuncionarioDAO funcionarioDAO;
    private LoginManagerView tela;
    private DefaultTableModel dtm;
    private ArrayList<Object> array;
    private HashMap<String, Object> mapa;
    private ArrayList<Object> arrayF;
    
    private boolean editar = false;
    private int idEdicao = -1;

    public LoginManagerControler(HashMap<String, Object> mapa) {
        this.loginDAO = new LoginDAO();
        this.funcionarioDAO = new FuncionarioDAO();
        
        this.tela = ((LoginManagerView) mapa.get("tela"));
        this.mapa = mapa;
    }
    
    @Override
    public void cadastrar( ) {

        Login login = new Login();

       if(validaDados()){
           
           login.setUsername(((JTextField) mapa.get("txt_username")).getText());
           login.setEmail(((JTextField) mapa.get("txt_email")).getText());
           login.setSenha(((JTextField) mapa.get("txt_senha")).getText());
           login.setFuncionarioId( Integer.parseInt( ((JTextField) mapa.get("txt_idfuncionario")).getText())  );
           
           if (loginDAO.cadastrar(login)) {
                JOptionPane.showMessageDialog(tela, "Cadastrado com sucesso", "Cadastro", JOptionPane.OK_OPTION);
                this.cancelar();
                this.preencherTabela((JTable)this.mapa.get("tbl_listagem"));
                // limpar campos
            } else {
                JOptionPane.showMessageDialog(tela, "Erro ao cadastrar", "Cadastro", JOptionPane.OK_OPTION);
            }
           
       }else{
           JOptionPane.showMessageDialog(tela, "Preencha todos os campos", "Erro",JOptionPane.ERROR_MESSAGE);
       }

    }
    
    public void edicao(int selectedRow) {
        ((JTabbedPane) mapa.get("tbd_abas")).setSelectedIndex(0);
        this.editar = true;
        this.estadoBotoes(2);
        
        Login login = (Login) array.get(selectedRow);
        this.idEdicao = login.getId();

        ((JTextField) mapa.get("txt_email")).setText(login.getEmail());
        ((JTextField) mapa.get("txt_username")).setText(login.getUsername());
        ((JTextField) mapa.get("txt_idfuncionario")).setText(""+login.getFuncionarioId());
        
        
    }
    
    @Override
    public void editar(Object obj) {
        try {
            loginDAO.editar(obj);
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
                if (JOptionPane.showConfirmDialog(tela, "Deseja excluir o cliente?", "Excluir", JOptionPane.YES_NO_OPTION) != 1) {
                    
                    if(loginDAO.remover(((Login) array.get(id)).getId())){
                        dtm.getDataVector().removeAllElements();
                        this.preencherTabela((JTable)this.mapa.get("tbl_listagem"));
                    }else{
                        JOptionPane.showMessageDialog(tela, "Erro ao apagar login", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
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
        
        Login login = new Login();

       if(validaDados()){
           
           login.setId(this.idEdicao);
           login.setUsername(((JTextField) mapa.get("txt_username")).getText());
           login.setEmail(((JTextField) mapa.get("txt_email")).getText());
           login.setSenha(((JTextField) mapa.get("txt_senha")).getText());
           login.setFuncionarioId( Integer.parseInt( ((JTextField) mapa.get("txt_idfuncionario")).getText())  );
           
           if(this.loginDAO.editar(login)){
                JOptionPane.showMessageDialog(tela, "Editado com sucesso", "Edição", JOptionPane.OK_OPTION);
                this.cancelar();
                this.preencherTabela((JTable)this.mapa.get("tbl_listagem"));
                // limpar campos
            } else {
                JOptionPane.showMessageDialog(tela, "Erro ao editar", "Edição", JOptionPane.OK_OPTION);
            }
           
       }else{
           JOptionPane.showMessageDialog(tela, "Preencha todos os campos", "Erro",JOptionPane.ERROR_MESSAGE);
       }        
      
    
    }

    @Override
    public void preencherTabela(JTable tabela) {
        dtm = (DefaultTableModel) tabela.getModel();
        dtm.getDataVector().removeAllElements();
        tela.repaint();
        array = loginDAO.listarTodos();

        if (array != null) {
            for (Object object : array) {
                Login login = (Login) object;
                if ( login != null) {
                    this.dtm.insertRow(dtm.getRowCount(), new Object[]{
                        login.getId(),
                        login.getUsername(),
                        login.getEmail()
                    });
                }
            }
        }
        
        dtm = (DefaultTableModel)((JTable)mapa.get("tbl_funcionario")).getModel();
        dtm.getDataVector().removeAllElements();
        arrayF = funcionarioDAO.listarTodos();
        
        if (arrayF != null) {
            for (Object object : arrayF) {
                Funcionario funcionario = (Funcionario) object;
                if ( funcionario != null) {
                    this.dtm.insertRow(dtm.getRowCount(), new Object[]{
                        funcionario.getIdFuncionario(),
                        funcionario.getNomeFuncionario(),
                        funcionario.getSetorFuncionario(),
                        funcionario.getCpfFuncionario()
                    });
                }
            }
        }
        tela.repaint();
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
        ((JTextField) mapa.get("txt_email")).setText("");
        ((JTextField) mapa.get("txt_username")).setText("");
        ((JTextField) mapa.get("txt_idfuncionario")).setText("");
        ((JTextField) mapa.get("txt_senha")).setText("");
    }

    private boolean validaDados() {
        
        boolean teste = true;
        
        if(((JTextField) mapa.get("txt_email")).getText().equals("")) teste = false;
        if(((JTextField) mapa.get("txt_username")).getText().equals("")) teste = false;
        if(((JTextField) mapa.get("txt_idfuncionario")).getText().equals("")) teste = false;
        if(((JTextField) mapa.get("txt_senha")).getText().equals("")) teste = false;

        return teste;
    }

    public void setaId(int selectedRow){
        if( arrayF != null){
            if(arrayF.size()-1 >=  selectedRow){
                ((JTextField) mapa.get("txt_idfuncionario")).setText(""+((Funcionario)arrayF.get(selectedRow)).getIdFuncionario());
            }
        }
    }    
}
