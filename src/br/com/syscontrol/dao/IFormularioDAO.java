/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.syscontrol.dao;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Diego Lemos
 */
interface IFormularioDAO<T> {
    
    public abstract void salvar(T usuario) throws SQLException;
    
    public abstract void atualizar(T usuario) throws SQLException;
    
    public abstract void remover(T usuario) throws SQLException;
       
    public abstract List<T> buscarPorNome(String nome) throws SQLException;   

    public abstract T buscarPorId(Long id) throws SQLException;

    public abstract List<T> buscarTodos() throws SQLException;  
    
}
