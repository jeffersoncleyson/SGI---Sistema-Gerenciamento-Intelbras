/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.intelbras.controler;

import java.awt.Dimension;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

/**
 *
 * @author WesleyReis
 */
public class Arquivo {

    private FileWriter fos;
    private FileReader fis;

    private BufferedWriter buffO;
    private BufferedReader buffI;

    public Arquivo() throws FileNotFoundException, IOException {

    }

    //==================================================================
    //==================================================================
    private void abreConexaoIn() throws FileNotFoundException, IOException {
        fis = new FileReader("log.txt");
        buffI = new BufferedReader(fis);
    }

    public ArrayList<Dimension> le() throws IOException, ClassNotFoundException {
        this.abreConexaoIn();

        ArrayList<Dimension> array = new ArrayList<>();

        while (buffI.ready()) {
            String linha = buffI.readLine();
            if (linha.equals("")) {
                return null;
            } else {
                Dimension d = new Dimension();
            }
        }

        this.fechaConexaoIn();
        return null;
    }

    public void fechaConexaoIn() throws IOException {
        fis.close();
        buffI.close();

    }

    //==================================================================
    //==================================================================
    private void abreConexaoOut() throws FileNotFoundException, IOException {
        fos = new FileWriter("log.txt");
        buffO = new BufferedWriter(fos);
    }

    public void salva(ArrayList<String> array) throws IOException {
        this.abreConexaoOut();

        for (String string : array) {
            if(string != null){
                buffO.write(string);
            }
        }
 
        
        this.fechaConexaoOut();
    }

    public void fechaConexaoOut() throws IOException {
        fos.close();
        buffO.close();
    }

    //==================================================================
    //==================================================================
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Arquivo a = new Arquivo();

        System.out.println("\n" + a.le());

    }

}
