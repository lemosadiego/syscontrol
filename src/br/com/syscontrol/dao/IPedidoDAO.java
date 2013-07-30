/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.syscontrol.dao;

import br.com.syscontrol.model.ClientePessoa;
import br.com.syscontrol.model.Pedido;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

/**
 *
 * @author Diego
 */
public interface IPedidoDAO<T> {

    public Long getNextId() throws SQLException;   

    public void salvar(T pedido) throws SQLException, ParseException;

    public void remover(T pedido) throws SQLException;

    public void atualizar(T pedido) throws SQLException, ParseException;    

    public List<T> buscarPedidosPorCliente(ClientePessoa cliente) throws SQLException;
    
    public List<T> buscarPedidosPorNomeCliente(String nome) throws SQLException;

    public Pedido buscarPorId(Long idPedido) throws SQLException;          
}
