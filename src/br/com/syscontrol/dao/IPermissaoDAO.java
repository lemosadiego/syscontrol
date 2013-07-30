/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.syscontrol.dao;

import br.com.syscontrol.model.Formulario;
import br.com.syscontrol.model.GrupoUsuario;
import br.com.syscontrol.model.Permissao;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Diego Lemos
 */
public interface IPermissaoDAO<T> {
    
    public void salvar(T permissao)throws SQLException;

    public void remover(T permissao)throws SQLException;

    public void atualizar(T permissao)throws SQLException;   

    public T buscarPorId(T permissao)throws SQLException;
    
    public List<T> buscarTodos()throws SQLException;

    public List<Permissao> buscarPorFormulario(Formulario formulario) throws SQLException;
    
    public List<Permissao> buscarPorGrupo(GrupoUsuario grupoUsuario) throws SQLException;   
}
