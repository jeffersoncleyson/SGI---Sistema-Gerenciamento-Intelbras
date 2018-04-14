/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.intelbras.model;

import java.util.Date;

/**
 *
 * @author WesleyReis
 */
public class Ponto {

    private int idPonto;
    private Date dataPonto;
    private String setorPonto;
    private Funcionario funcionario;

    public int getIdPonto() {
        return idPonto;
    }

    public void setIdPonto(int idPonto) {
        this.idPonto = idPonto;
    }

    public Date getDataPonto() {
        return dataPonto;
    }

    public void setDataPonto(Date dataPonto) {
        this.dataPonto = dataPonto;
    }

    public String getSetorPonto() {
        return setorPonto;
    }

    public void setSetorPonto(String setorPonto) {
        this.setorPonto = setorPonto;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }
    
    

}
