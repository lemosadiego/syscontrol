/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.syscontrol.dao;

import br.com.syscontrol.model.Usuario;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Diego Lemos
 */
public interface IUsuarioDAO<T> {
    
    public abstract void salvar(T usuario) throws SQLException;
    
    public abstract void atualizar(T usuario) throws SQLException;
    
    public abstract void remover(T usuario) throws SQLException;
       
    public abstract Usuario buscarPorNome(String nome) throws SQLException;   

    public abstract Usuario buscarPorId(Long id) throws SQLException;

    public abstract List<T> buscarTodos() throws SQLException;  
    
    public abstract Usuario getUsuario(T usuario)throws SQLException; 
    
    public abstract boolean isUsuarioCadastrado(T usuario) throws SQLException; 
    
    public abstract boolean isLoginCadastrado(T usuario) throws SQLException; 
}
