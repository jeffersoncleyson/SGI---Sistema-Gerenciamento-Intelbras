/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.intelbras.controler;

import br.com.intelbras.view.LoginView;

/**
 *
 * @author WesleyReis
 */
public class Main {
    
    public static void main(String[] args) {
        
       LoginControler loginControler = new LoginControler(new LoginView());
        
        
    }
    
}
