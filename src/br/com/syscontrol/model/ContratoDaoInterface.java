/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.syscontrol.model;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Diego
 */
public interface ContratoDaoInterface {

    public void incluirItensContrato(Contrato contrato) throws SQLException;

    public void inserir(Contrato contrato) throws SQLException;

    public void remover(Contrato contrato) throws SQLException;

    public void alterar(Contrato contrato) throws SQLException;

    public List getContratoPorNomeCliente(String nome) throws SQLException;

    public Contrato getContratoPorId(String codigo) throws SQLException;

    public void removerItensContrato(Contrato contrato) throws SQLException;

    public Contrato getServicosContratados(Contrato contrato) throws SQLException;

    public void removerServicosContratados(String idContrato) throws SQLException;


}
