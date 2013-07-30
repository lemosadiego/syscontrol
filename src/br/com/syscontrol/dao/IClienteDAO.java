/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.syscontrol.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Diego Lemos
 */
public interface IClienteDAO<T> {
    
    public abstract T buscarPorNome(String nome) throws SQLException;
    
    public abstract void atualizar(T cliente) throws SQLException;

    public abstract T buscarPorId(Long id) throws SQLException;

    public abstract List<T> buscarTodos() throws SQLException;
    
    public abstract List<T> buscarListaPorNome(String nome) throws SQLException;

    public abstract void remover(T cliente) throws SQLException;

    public abstract void salvar(T cliente) throws SQLException;
    
}
