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
 * Interface responsavel por exibir os metodos basicos do modelo DAO
 *
 */
public interface IServicoDAO<T> {

    public void salvar(T servico)throws SQLException;

    public void remover(T servico)throws SQLException;

    public void atualizar(T servico)throws SQLException;

    public List<T> buscarPorNome(String nome)throws SQLException;

    public T buscarPorId(Long id)throws SQLException;
    
    public List<T> buscarTodos()throws SQLException;

    public boolean isServicoExistente(T o) throws SQLException;
}
