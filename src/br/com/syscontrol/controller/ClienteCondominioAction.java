/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.syscontrol.controller;

import br.com.syscontrol.dao.ClienteCondominioDao;
import br.com.syscontrol.dao.IClienteCondominioDAO;
import br.com.syscontrol.exception.ClienteException;
import br.com.syscontrol.model.ClienteCondominio;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Diego
 */
public class ClienteCondominioAction {

    IClienteCondominioDAO clienteCondominioDao = new ClienteCondominioDao();

    public void salvar(ClienteCondominio clienteCondominio) throws ClienteException {
        
        try {
            clienteCondominioDao.salvar(clienteCondominio);
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioAction.class.getName()).log(Level.SEVERE, null, ex);
            throw new ClienteException("Atenção: Falha durante a inclusao do Cliente [" + clienteCondominio.getNome() + "]",ex);
        }        
    }
    
    public ClienteCondominio buscarPorNome(String nome) throws ClienteException {
        
        try {
           return (ClienteCondominio) clienteCondominioDao.buscarPorNome(nome);
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioAction.class.getName()).log(Level.SEVERE, null, ex);
            throw new ClienteException("Atenção: Falha durante a busca de Cliente por Nome [" + nome + "]",ex);
        }        
    }
    
    public List<ClienteCondominio> buscarListaPorNome(String nome) throws ClienteException {
        
        try {
           return clienteCondominioDao.buscarListaPorNome(nome);
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioAction.class.getName()).log(Level.SEVERE, null, ex);
            throw new ClienteException("Atenção: Falha durante a busca de Lista de Cliente por Nome [" + nome + "]",ex);
        }        
    }

    public List<ClienteCondominio> buscarListaPorBairro(String bairro) throws ClienteException {
        
        try {
           return clienteCondominioDao.buscarListaPorBairro(bairro);
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioAction.class.getName()).log(Level.SEVERE, null, ex);
            throw new ClienteException("Atenção: Falha durante a busca de Lista de Cliente por Bairro [" + bairro + "]",ex);
        }        
    }
    
    public List<ClienteCondominio> buscarListaPorCnpj(String cnpj) throws ClienteException {
        
        try {
           return clienteCondominioDao.buscarListaPorCnpj(cnpj);
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioAction.class.getName()).log(Level.SEVERE, null, ex);
            throw new ClienteException("Atenção: Falha durante a busca de Lista de Cliente por Cnpj [" + cnpj + "]",ex);
        }        
    }
    
    public List<ClienteCondominio> buscarListaPorEndereco(String end) throws ClienteException {
        
        try {
           return clienteCondominioDao.buscarListaPorEndereco(end);
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioAction.class.getName()).log(Level.SEVERE, null, ex);
            throw new ClienteException("Atenção: Falha durante a busca de Lista de Cliente por Endereço [" + end + "]",ex);
        }        
    }
    
    public List<ClienteCondominio> buscarListaPorTelefoneComercial(String telefoneComercial) throws ClienteException {
        
        try {
           return clienteCondominioDao.buscarListaPorTelefoneComercial(telefoneComercial);
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioAction.class.getName()).log(Level.SEVERE, null, ex);
            throw new ClienteException("Atenção: Falha durante a busca de Lista de Cliente por Telefone Comercial [" + telefoneComercial + "]",ex);
        }        
    }
    
    public List<ClienteCondominio> buscarTodos() throws ClienteException {
        
        try {
           return clienteCondominioDao.buscarTodos();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioAction.class.getName()).log(Level.SEVERE, null, ex);
            throw new ClienteException("Atenção: Falha durante a busca de Todos os Clientes",ex);
        }        
    }
    
    public void atualizar(ClienteCondominio clienteCondominio) throws ClienteException {
        
        try {
           clienteCondominioDao.atualizar(clienteCondominio);
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioAction.class.getName()).log(Level.SEVERE, null, ex);
            throw new ClienteException("Atenção: Falha durante a atualização do Cliente [" + clienteCondominio.getNome() + "]",ex);
        }        
    }

    public void deletar(ClienteCondominio clienteCondominio) throws ClienteException {
        
        try {
           clienteCondominioDao.remover(clienteCondominio);
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioAction.class.getName()).log(Level.SEVERE, null, ex);
            throw new ClienteException("Atenção: Falha durante a exclusão do Cliente [" + clienteCondominio.getNome() + "]",ex);
        }        
    }

    public ClienteCondominio buscarPorId(Long id) throws ClienteException {
        
        try {
           return ((ClienteCondominio)clienteCondominioDao.buscarPorId(id));
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioAction.class.getName()).log(Level.SEVERE, null, ex);
            throw new ClienteException("Atenção: Falha durante a busca do Cliente Id [" + id + "]",ex);
        }        
    }

   public boolean isNomeExistente(ClienteCondominio clienteCondominio) throws ClienteException {
       
       try {
           return clienteCondominioDao.isNomeExistente(clienteCondominio);
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioAction.class.getName()).log(Level.SEVERE, null, ex);
            throw new ClienteException("Atenção: Falha durante a verificação de existência do nome do Cliente  [" + clienteCondominio.getNome() + "]",ex);
        }       
   }

   public boolean isCnpjExistente(ClienteCondominio clienteCondominio) throws ClienteException {
       
       try {
           return clienteCondominioDao.isCnpjExistente(clienteCondominio);
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioAction.class.getName()).log(Level.SEVERE, null, ex);
            throw new ClienteException("Atenção: Falha durante a verificação de existência do CNPJ do Cliente  [" + clienteCondominio.getCnpj() + "]",ex);
        }       
   }


}
