/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.intelbras.model;

import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;


/**
 *
 * @author aluno
 */
public class ClienteDAOTest {

    private ClienteDAO clienteDAO;
    private Cliente c1;
    private boolean valida;
    ArrayList clientes = new ArrayList();

    public ClienteDAOTest() {
        clienteDAO = ClienteDAO.getInstance();
        c1.setBairroCliente("Inatel");
        c1.setCepCliente("37540000");
        c1.setCpfCliente("125785478578");
        c1.setDataNascCliente("03/04/2000");
        c1.setEnderecoCliente("Rua dos palmares");
        c1.setNomeCliente("Emerson");
        c1.setRgCliente("352648752");
        c1.setSexoCliente("M");
        c1.setTelefoneCliente("(35)998152079");
    }

    @Test
    public void cadrastrar_cliente() {
        valida = false;
        valida = clienteDAO.cadastrar(c1);
        assertEquals(true, valida);
    }

    @Test
    public void buscar_cliente() {
        valida = false;
        clientes = clienteDAO.listarTodos();
        for (Object cliente1 : clientes) {
            Cliente c = (Cliente)cliente1;
            if (c.getNomeCliente().equals(c1.getNomeCliente()) ) {
                c1.setIdCliente(c.getIdCliente());
                valida = true;
                break;
            }
        }
        assertEquals(true, valida);
    }

    @Test
    public void editar_cliente() {
        valida = false;
        c1.setNomeCliente("Jefferson");
        valida = clienteDAO.editar(c1);
        assertEquals(true, valida);
    }

    @Test
    public void remover_cliente() {
        valida = false;
        valida = clienteDAO.remover(c1.getIdCliente());
        assertEquals(true, valida);
    }

}
