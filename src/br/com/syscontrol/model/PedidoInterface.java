/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.syscontrol.model;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;

/**
 *
 * @author Diego
 */
public interface PedidoInterface {

    public double adicionaValor(double valorAntigo, double valorNovo);

    public double removeValor(double valorAntigo, double valorRemovido);

    public void atualizarStatus(Pedido pedido) throws SQLException, ParseException;

    public boolean  isObjetoValido(Pedido pedido) throws SQLException;

    public void popularListaServicos(JTable table) throws SQLException, Exception;

    public void addListaPedidos(ArrayList<Pedido> lista) throws SQLException;

    public void popularServicosPedido(JTable table) throws SQLException;

    public void addItemPedido(JTable table,ItemPedido itemPedido) throws SQLException;

    public void removeItemPedido(ItemPedido item, int indiceLinha) throws SQLException;

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

    public void removerItensPedido(Long idPedido) throws SQLException;

    public void removerFormaPagamentoPedido(Long idPedido) throws SQLException;
}
