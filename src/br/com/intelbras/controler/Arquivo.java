/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.intelbras.controler;

import br.com.intelbras.model.Funcionario;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
    private void abreConexaoIn(int i) throws FileNotFoundException, IOException {
        if (i == 1) {
            fis = new FileReader("botoes.txt");
            buffI = new BufferedReader(fis);
        } else {
            fis = new FileReader("log.txt");
            buffI = new BufferedReader(fis);
        }
    }

    public ArrayList<Point> leCoordenadaBotoes() throws IOException, ClassNotFoundException {
        this.abreConexaoIn(1);

        ArrayList<Point> array = new ArrayList<>();

        while (buffI.ready()) {
            String linha = buffI.readLine();
            if (linha.equals("")) {
                break;
            } else {
                Point p = new Point();

                String[] coordenada = linha.split("-");
                p.x = Integer.parseInt(coordenada[0]);
                p.y = Integer.parseInt(coordenada[1]);

                array.add(p);
            }
        }

        this.fechaConexaoIn();
        return array;
    }

    public void fechaConexaoIn() throws IOException {
        fis.close();
        buffI.close();

    }

    //==================================================================
    //==================================================================
    private void abreConexaoOut(int i) throws FileNotFoundException, IOException {

        if(i == 1) {
            fos = new FileWriter("botoes.txt");
            buffO = new BufferedWriter(fos);
        }else{
            fos = new FileWriter("log.txt",true);
            buffO = new BufferedWriter(fos);
        }
    }

    public void salvaCoordenadaBotoes(ArrayList<String> array) throws IOException {
        this.abreConexaoOut(1);

        for (String string : array) {
            if (string != null) {
                buffO.write(string);
                buffO.newLine();
            }
        }

        this.fechaConexaoOut();
    }

    public void fechaConexaoOut() throws IOException {
        buffO.close();
        fos.close();
    }

    //==================================================================
    //==================================================================
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Arquivo a = new Arquivo();

        System.out.println("\n" + a.leCoordenadaBotoes());

    }
    //==================================================================
    //==================================================================

    public int leLog() throws IOException, ClassNotFoundException {
        this.abreConexaoIn(2);
        ArrayList<Point> array = new ArrayList<>();

        while (buffI.ready()) {
            String linha = buffI.readLine();
            if (linha.equals("")) {
                break;
            } else {
                Point p = new Point();

                String[] coordenada = linha.split("-");
                p.x = Integer.parseInt(coordenada[0]);
                p.y = Integer.parseInt(coordenada[1]);

                array.add(p);
            }
        }

        this.fechaConexaoIn();
        return 0;
    }

    //==================================================================
    //==================================================================
    public void salvaLog(Funcionario funcionario) throws IOException {
        this.abreConexaoOut(2);
        

        this.fechaConexaoOut();
    }

}
