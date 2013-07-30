/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.syscontrol.controller;

import br.com.syscontrol.dao.ClienteDao;
import br.com.syscontrol.dao.IClienteDAO;
import br.com.syscontrol.exception.ClienteException;
import br.com.syscontrol.model.BuscaPessoaFisicaTableModel;
import br.com.syscontrol.model.ClientePessoa;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Diego
 */
public class ClientePessoaAction {

    private static ClientePessoaAction clientePessoaAction;
    
    public static ClientePessoaAction getInstance(){
        
        if(clientePessoaAction==null){
            clientePessoaAction = new ClientePessoaAction();
        }
         return clientePessoaAction;
    }
    
    IClienteDAO clientePessoaDao = new ClienteDao();
    private BuscaPessoaFisicaTableModel buscaPessoaTableModel = new BuscaPessoaFisicaTableModel();

    public void populaTabelaClientes(List lista) throws SQLException{
        this.buscaPessoaTableModel.limpar();
        this.buscaPessoaTableModel.addListaDePessoas(lista);
    }


    public ClientePessoa buscarPorNome(String nome) throws ClienteException {
        
        try {
           return (ClientePessoa) clientePessoaDao.buscarPorNome(nome);
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioAction.class.getName()).log(Level.SEVERE, null, ex);
            throw new ClienteException("Atenção: Falha durante a busca de Cliente por Nome [" + nome + "]",ex);
        }        
    }
    
    public List<ClientePessoa> buscarListaPorNome(String nome) throws ClienteException {
        
        try {
           return clientePessoaDao.buscarListaPorNome(nome);
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioAction.class.getName()).log(Level.SEVERE, null, ex);
            throw new ClienteException("Atenção: Falha durante a busca de Cliente por Nome [" + nome + "]",ex);
        }        
    }
    
 public List<ClientePessoa> buscarTodos() throws ClienteException {
        
        try {
           return clientePessoaDao.buscarTodos();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioAction.class.getName()).log(Level.SEVERE, null, ex);
            throw new ClienteException("Atenção: Falha durante a busca de Todos os Clientes",ex);
        }        
    }
    
    public void alterar(ClientePessoa clientePessoaFisica) throws ClienteException {
        
        try {
           clientePessoaDao.atualizar(clientePessoaFisica);
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioAction.class.getName()).log(Level.SEVERE, null, ex);
            throw new ClienteException("Atenção: Falha durante a atualização do Cliente [" + clientePessoaFisica.getNome() + "]",ex);
        }        
    }

    public void remover(ClientePessoa clientePessoaFisica) throws ClienteException {
        
        try {
           clientePessoaDao.remover(clientePessoaFisica);
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioAction.class.getName()).log(Level.SEVERE, null, ex);
            throw new ClienteException("Atenção: Falha durante a exclusão do Cliente [" + clientePessoaFisica.getNome() + "]",ex);
        }        
    }

    public ClientePessoa buscarPorId(Long id) throws ClienteException {
        
        try {
           return ((ClientePessoa)clientePessoaDao.buscarPorId(id));
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioAction.class.getName()).log(Level.SEVERE, null, ex);
            throw new ClienteException("Atenção: Falha durante a busca do Cliente Id [" + id + "]",ex);
        }        
    }
   
   /**
     * @return the usuarioTableModel
     */
    public BuscaPessoaFisicaTableModel getBuscaPessoaTableModel() {
        return buscaPessoaTableModel;
    }

    /**
     * @param usuarioTableModel the usuarioTableModel to set
     */
    public void setGrupoUsuarioTableModel(BuscaPessoaFisicaTableModel buscaPessoaTableModel) {
        this.buscaPessoaTableModel = buscaPessoaTableModel;
    }
   
}