/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.intelbras.controler;

import br.com.intelbras.view.InicioView;
import br.com.intelbras.view.LoginView;

/**
 *
 * @author WesleyReis
 */
public class Main {

    public static void main(String[] args) {

        Main.setlookAndFeel();

        LoginView loginView = new LoginView();
        loginView.setLocationRelativeTo(null);
        loginView.setVisible(true);

    }

    public static void setlookAndFeel() {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            System.out.println("Erro Look And Feel");
            System.out.println(ex);
        }
    }

}
