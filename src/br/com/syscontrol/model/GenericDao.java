/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.syscontrol.model;

import java.util.Collection;

/**
 *
 * @author Diego
 * Interface responsavel por exibir os metodos basicos do modelo DAO
 *
 */
public interface GenericDao {

    public void inserir(Object o)throws Exception;

    public void remover(Object o)throws Exception;

    public void alterar(Object o)throws Exception;

    public Collection consultarPorNome(Object o)throws Exception;

    public Object consultarPorId(Object o)throws Exception;
    
    public Collection getLista()throws Exception;

    public boolean isObjetoExistente(Object o) throws Exception;





}
