/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.syscontrol.model;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

/**
 *
 * @author Diego
 */
public interface PedidoDaoInterface {

    public long getNextNumeroPedido() throws SQLException;

    public void incluirItensPedido(Pedido pedido) throws SQLException, ParseException;

    public void inserir(Pedido pedido) throws SQLException, ParseException;

    public void remover(Pedido pedido) throws SQLException;

    public void alterar(Pedido pedido) throws SQLException, ParseException;

    public void atualizarStatusPedido(Pedido pedido) throws SQLException;

    public List getPedidoPorNomeCliente(String nome) throws SQLException;

    public Pedido getPedidoPorId(Long idPedido) throws SQLException;

    public List getPedidosPorCliente(String nomeCliente) throws SQLException;

    public String getNomeServicoPedido(Long idPedido) throws SQLException;

    public int getGarantiaServicoPedido(Long idPedido) throws SQLException;

    public void removerItensPedido(Long idPedido) throws SQLException;

    public void removerFormaPagamentoPedido(Long idPedido) throws SQLException;


}
