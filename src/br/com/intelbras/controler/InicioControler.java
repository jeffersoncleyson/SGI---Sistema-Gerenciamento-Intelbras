/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.intelbras.controler;

import br.com.intelbras.view.ClienteView;
import br.com.intelbras.view.FuncionarioView;
import br.com.intelbras.view.InicioView;
import br.com.intelbras.view.LoginManagerView;
import br.com.intelbras.view.LoginView;
import br.com.intelbras.view.PontosView;
import br.com.intelbras.view.ProdutoView;
import br.com.intelbras.view.VendasView;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.filechooser.FileNameExtensionFilter;


/**
 *
 * @author WesleyReis
 */
public class InicioControler {

    
    public InicioControler() {

    }
    
    
    
    public void arrastarBotao(JButton botao, MouseEvent evt) {
        botao.setLocation(botao.getLocation().x + evt.getX() - (botao.getSize().width /2) , botao.getLocation().y + evt.getY() - (botao.getSize().height /2));
    }

    public void mudarPlanoFundo(JLabel label){
        alterarImagem(label);
    }
    
    public void logout(InicioView tela){
        LoginView loginView = new LoginView();
        loginView.setVisible(true);
        tela.dispose();
        
    }
     //============================== Abrir Views
    //============================== 
    public void abrirCliente(InicioView tela){
        ClienteView cliente = new ClienteView();
        cliente.setVisible(true);
        cliente.setLocationRelativeTo(tela);
    }
    public void abrirFuncionario(InicioView tela){
        FuncionarioView funcionario = new FuncionarioView();
        funcionario.setVisible(true);
        funcionario.setLocationRelativeTo(tela);
    }
    public void abrirProduto(InicioView tela){
        ProdutoView produto = new ProdutoView();
        produto.setVisible(true);
        produto.setLocationRelativeTo(tela);
    }
    public void abrirPonto(InicioView tela){
        PontosView ponto = new PontosView();
        ponto.setVisible(true);
        ponto.setLocationRelativeTo(tela);
    }
    public void abrirVenda(InicioView tela){
        VendasView venda = new VendasView();
        venda.setVisible(true);
        venda.setLocationRelativeTo(tela);
    }
    public void abrirLogin(InicioView tela){
        LoginManagerView loginManager = new LoginManagerView();
        loginManager.setVisible(true);
        loginManager.setLocationRelativeTo(tela);
    }
    
    //============================== Metodos privados
    //============================== 
    
    // Metodo para alterar a imagem do label de fundo
    private void alterarImagem(JLabel label) {

        // seta imagens
        try {
            // TODO add your handling code here:    
            JFileChooser chooser = new JFileChooser();//cria uma instancia do JFileChooser
            //filtro para as imagend
            FileNameExtensionFilter filtro = new FileNameExtensionFilter("JPG & GIF & PNG Images", "jpg", "gif", "png");
            //altera o filtro
            chooser.setFileFilter(filtro);

            String local = "";
            int returnVal = chooser.showOpenDialog(label);//abre JFileChooser
            if (returnVal == JFileChooser.APPROVE_OPTION) {//verifica se clicou em ok
                local = (chooser.getSelectedFile().getAbsolutePath());//local recebe a localização da imagem           
            }

            File origem = new File(local);
            File destino = new File("C:\\Users\\Public\\Pictures\\telaFundo.png");
            copiarArquivo(origem, destino);

            ImageIcon img = new javax.swing.ImageIcon("C:\\Users\\Public\\Pictures\\telaFundo.png");
            img.setImage(img.getImage().getScaledInstance(617, 447, 100));

            label.setIcon(img);
        } catch (FileNotFoundException ex) {
            System.out.println("Arquivo nao encontrado");
        } catch (IOException ex) {
            System.out.println("Erro ao importar");
        }
    }

    
    // Metodo para compiar um arquivo para outro diretorio
    private void copiarArquivo(File source, File destination) throws IOException {
        if (destination.exists()) {
            destination.delete();
        }
        FileChannel sourceChannel = null;
        FileChannel destinationChannel = null;
        try {
            sourceChannel = new FileInputStream(source).getChannel();
            destinationChannel = new FileOutputStream(destination).getChannel();
            sourceChannel.transferTo(0, sourceChannel.size(),
                    destinationChannel);
        } finally {
            if (sourceChannel != null && sourceChannel.isOpen()) {
                sourceChannel.close();
            }
            if (destinationChannel != null && destinationChannel.isOpen()) {
                destinationChannel.close();
            }
        }
    }
    
    
}
