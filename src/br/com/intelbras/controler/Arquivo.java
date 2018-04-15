/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.intelbras.controler;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
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

/**
 *
 * @author WesleyReis
 */
public class Arquivo {

    private FileOutputStream fos;
    private FileInputStream fis;

    private BufferedOutputStream buffO;
    private BufferedInputStream buffI;

    private OutputStreamWriter out;
    private InputStreamReader in;

    private ObjectOutputStream objOut;
    private ObjectInputStream objIn;

    public Arquivo() throws FileNotFoundException, IOException {

    }

    //==================================================================
    //==================================================================
    private void abreConexaoIn() throws FileNotFoundException, IOException {
        fis = new FileInputStream("log.txt");
        buffI = new BufferedInputStream(fis);
        in = new InputStreamReader(buffI);
        objIn = new ObjectInputStream(fis);
    }

    public String le() throws IOException, ClassNotFoundException {
        this.abreConexaoIn();

        String valor = (String) objIn.readObject();
        while (valor != null) {
            System.out.println(valor);
            
        }
        this.fechaConexaoIn();
        return null;
    }

    public void fechaConexaoIn() throws IOException {
        fis.close();
        buffI.close();
        in.close();
        objIn.close();

    }

    //==================================================================
    //==================================================================
    private void abreConexaoOut() throws FileNotFoundException, IOException {
        fos = new FileOutputStream("log.txt",true);
        buffO = new BufferedOutputStream(fos);
        out = new OutputStreamWriter(buffO);
        objOut = new ObjectOutputStream(fos);
    }

    public void salva(String linha) throws IOException {
        this.abreConexaoOut();
        objOut.writeObject(linha);
        this.fechaConexaoOut();
    }

    public void fechaConexaoOut() throws IOException {
        fos.close();
        buffO.close();
        out.close();
        objOut.close();

    }

    //==================================================================
    //==================================================================
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Arquivo a = new Arquivo();

        a.salva("Hello World");

        System.out.println("\n" + a.le());

    }

}
