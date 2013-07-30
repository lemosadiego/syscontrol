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
public interface IClientePessoaFisicaDAO<T> {
    
    public abstract void salvar(T clienteCondominio) throws SQLException;
        
    public abstract void remover(T clienteCondominio) throws SQLException;
    
    public abstract void atualizar(T clienteCondominio) throws SQLException;
    
    public abstract T buscarPorNome(String nome) throws SQLException;
    
    public abstract List<T> buscarListaPorNome(String nome) throws SQLException;
    
    public abstract List<T> buscarListaPorBairro(String bairro) throws SQLException;
    
    public abstract List<T> buscarListaPorCpf(String cpf) throws SQLException;
    
    public abstract List<T> buscarListaPorRg(String rg) throws SQLException;
    
    public abstract List<T> buscarListaPorEndereco(String endereco) throws SQLException;
    
    public abstract List<T> buscarListaPorTelefoneComercial(String telefoneComercial) throws SQLException;    
    
    public abstract List<T> buscarListaPorTelefoneCelular(String telefoneCelular) throws SQLException;    

    public abstract T buscarPorId(Long id) throws SQLException;

    public abstract List<T> buscarTodos() throws SQLException;
    
    public abstract boolean isCpfExistente(T clienteCondominio) throws SQLException;
    
    public abstract boolean isRgExistente(T clienteCondominio) throws SQLException;
    
    public abstract boolean isNomeExistente(T clienteCondominio) throws SQLException;
    
}
