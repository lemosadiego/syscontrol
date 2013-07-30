/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.syscontrol.dao;

import br.com.syscontrol.model.Colaborador;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Diego Lemos
 */
public interface IColaboradorDAO<T> {
    
    public abstract Colaborador buscarPorNome(String nome) throws SQLException;
    
    public abstract void atualizar(T colaborador) throws SQLException;

    public abstract Colaborador buscarPorId(Long id) throws SQLException;

    public abstract List<T> buscarTodos() throws SQLException;

    public abstract void remover(T colaborador) throws SQLException;

    public abstract void salvar(T colaborador) throws SQLException;
    
}
