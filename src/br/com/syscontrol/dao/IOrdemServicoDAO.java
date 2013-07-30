/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.syscontrol.dao;


import br.com.syscontrol.model.ClientePessoa;
import br.com.syscontrol.model.OrdemServicoPadrao;
import br.com.syscontrol.model.Pedido;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Diego
 */
public interface IOrdemServicoDAO<T> {

    public void salvar(T os) throws SQLException;        

    public void remover(T os) throws SQLException;

    public void atualizar(OrdemServicoPadrao os) throws SQLException;

    public Long getNextIdOs() throws SQLException;

    public T buscarPorId(Long id) throws SQLException;

    public T buscarPorPedido(Long numeroPedido) throws SQLException;

    public T buscarPorContrato(String idContrato) throws SQLException;

    public List<OrdemServicoPadrao> buscarPorCliente(ClientePessoa cliente) throws SQLException;

    public List<OrdemServicoPadrao> buscarListaPorPedido(Pedido pedido) throws SQLException;}
