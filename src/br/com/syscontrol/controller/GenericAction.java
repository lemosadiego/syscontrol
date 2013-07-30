/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.syscontrol.controller;

import java.util.Collection;
import javax.swing.table.AbstractTableModel;

/**
 * @author Diego Lemos
 * Data Criação: 10/12/2011
 */
public interface GenericAction {

    public void inserir(Object o) throws Exception;

    public void remover(Object o) throws Exception;

    public void alterar(Object o) throws Exception;

    public Collection buscarPorNome(Object o) throws Exception;

    public Object buscarPorId(Object o) throws Exception;

    public Collection buscarTodos() throws Exception;

    public void setTableModel(AbstractTableModel abstractTableModel) throws Exception;

    public AbstractTableModel getTableModel() throws Exception;

    public boolean isServicoExistente(Object o) throws Exception;

    public GenericAction getAction();


}
