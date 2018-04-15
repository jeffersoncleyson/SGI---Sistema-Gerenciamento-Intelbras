/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.intelbras.controler;

import br.com.intelbras.model.Funcionario;
import br.com.intelbras.model.Login;
import br.com.intelbras.model.LoginDAO;
import br.com.intelbras.view.InicioView;
import br.com.intelbras.view.LoginView;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author WesleyReis
 */
public class LoginControler {

    LoginDAO loginDAO;

    public LoginControler() {
        this.loginDAO = new LoginDAO();
    }

    public void login(JTextField usuario, JTextField senha, LoginView tela) {
        Funcionario funcionario;
        Login login;

        login = verificaLogin(usuario.getText(), senha.getText());

        if (login != null) {
            //checa permissao
            try {
                funcionario = loginDAO.getNivelAcesso(login);

                InicioView inicioView = new InicioView();
                inicioView.setVisible(true);
                tela.dispose();

            } catch (Exception ex) {
                System.out.println(ex);
            }

        } else {
            JOptionPane.showMessageDialog(tela, "Dados Inválidos", "Erro", JOptionPane.WARNING_MESSAGE);
        }
    }

    private Login verificaLogin(String usuario, String senha) {

        ArrayList<Object> array = loginDAO.listarTodos();

        if (array != null) {
            for (Object object : array) {
                Login l = (Login) object;
                if (l != null) {
                    if (l.getEmail().compareTo(usuario) == 0 || l.getUsername().compareTo(usuario) == 0) {
                        if (l.getSenha().compareTo(senha) == 0) {
                            return l;
                        }
                    }
                }
            }
        }
        return null;
    }

}
