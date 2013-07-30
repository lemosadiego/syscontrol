/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.syscontrol.dao;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Diego
 */
public interface IContratoDAO<T> {

    public void salvar(T contrato) throws SQLException;

    public void remover(T contrato) throws SQLException;

    public void atualizar(T contrato) throws SQLException;

    public List<T> buscarPorNomeCliente(String nome) throws SQLException;

    public T buscarPorId(String codigo) throws SQLException;
}
