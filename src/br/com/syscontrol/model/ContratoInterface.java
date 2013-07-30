/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.syscontrol.model;

import java.util.List;

/**
 *
 * @author Diego
 */
public interface ContratoInterface {

    public void incluirItensContrato(Contrato contrato) throws Exception;

    public void inserir(Contrato contrato) throws Exception;

    public void remover(Contrato contrato) throws Exception;

    public void alterar(Contrato contrato) throws Exception;

    public List getContratoPorNomeCliente(String nome) throws Exception;

    public Contrato getContratoPorId(String codigo) throws Exception;

    public void removerItensContrato(Contrato contrato) throws Exception;

    public Contrato getServicosContratados(Contrato contrato) throws Exception;

    public void removerServicosContratados(String idContrato) throws Exception;

}
