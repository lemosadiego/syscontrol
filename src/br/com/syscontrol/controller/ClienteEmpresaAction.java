/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.syscontrol.controller;


import br.com.syscontrol.dao.ClientePessoaJuridicaDAO;
import br.com.syscontrol.dao.IClientePessoaJuridicaDAO;
import br.com.syscontrol.exception.ClienteException;
import br.com.syscontrol.model.BuscaEmpresaTableModel;
import br.com.syscontrol.model.ClientePessoaJuridica;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Diego
 */
public class ClienteEmpresaAction {

    IClientePessoaJuridicaDAO<ClientePessoaJuridica> clienteEmpresaDao = new ClientePessoaJuridicaDAO();
    BuscaEmpresaTableModel BuscaEmpresaTableModel = new BuscaEmpresaTableModel();

    public void populaTabelaClientes(List lista) throws SQLException{
        this.BuscaEmpresaTableModel.limpar();
        this.BuscaEmpresaTableModel.addListaDeEmpresas(lista);
    }
   
    public void salvar(ClientePessoaJuridica clienteEmpresa) throws ClienteException{    
        
        try {
            clienteEmpresaDao.salvar(clienteEmpresa);
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioAction.class.getName()).log(Level.SEVERE, null, ex);
            throw new ClienteException("Atenção: Falha durante a inclusao do Cliente [" + clienteEmpresa.getNome() + "]",ex);
        }         
    }

    public ClientePessoaJuridica buscarPorNome(String nome) throws ClienteException {
        
        try {
           return (ClientePessoaJuridica) clienteEmpresaDao.buscarPorNome(nome);
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioAction.class.getName()).log(Level.SEVERE, null, ex);
            throw new ClienteException("Atenção: Falha durante a busca de Cliente por Nome [" + nome + "]",ex);
        }        
    }
    
    public List<ClientePessoaJuridica> buscarListaPorNome(String nome) throws ClienteException {
        
        try {
           return clienteEmpresaDao.buscarListaPorNome(nome);
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioAction.class.getName()).log(Level.SEVERE, null, ex);
            throw new ClienteException("Atenção: Falha durante a busca de Lista de Cliente por Nome [" + nome + "]",ex);
        }        
    }

    public List<ClientePessoaJuridica> buscarListaPorBairro(String bairro) throws ClienteException {
        
        try {
           return clienteEmpresaDao.buscarListaPorBairro(bairro);
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioAction.class.getName()).log(Level.SEVERE, null, ex);
            throw new ClienteException("Atenção: Falha durante a busca de Lista de Cliente por Bairro [" + bairro + "]",ex);
        }        
    }
    
    public List<ClientePessoaJuridica> buscarListaPorCnpj(String cnpj) throws ClienteException {
        
        try {
           return clienteEmpresaDao.buscarListaPorCnpj(cnpj);
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioAction.class.getName()).log(Level.SEVERE, null, ex);
            throw new ClienteException("Atenção: Falha durante a busca de Lista de Cliente por Cnpj [" + cnpj + "]",ex);
        }        
    }
    
    public List<ClientePessoaJuridica> buscarListaPorEndereco(String end) throws ClienteException {
        
        try {
           return clienteEmpresaDao.buscarListaPorEndereco(end);
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioAction.class.getName()).log(Level.SEVERE, null, ex);
            throw new ClienteException("Atenção: Falha durante a busca de Lista de Cliente por Endereço [" + end + "]",ex);
        }        
    }
    
    public List<ClientePessoaJuridica> buscarListaPorTelefoneComercial(String telefoneComercial) throws ClienteException {
        
        try {
           return clienteEmpresaDao.buscarListaPorTelefoneComercial(telefoneComercial);
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioAction.class.getName()).log(Level.SEVERE, null, ex);
            throw new ClienteException("Atenção: Falha durante a busca de Lista de Cliente por Telefone Comercial [" + telefoneComercial + "]",ex);
        }        
    }
    
    public List<ClientePessoaJuridica> buscarTodos() throws ClienteException {
        
        try {
           return clienteEmpresaDao.buscarTodos();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioAction.class.getName()).log(Level.SEVERE, null, ex);
            throw new ClienteException("Atenção: Falha durante a busca de Todos os Clientes",ex);
        }        
    }
    
    public void alterar(ClientePessoaJuridica clienteEmpresa) throws ClienteException {
        
        try {
           clienteEmpresaDao.atualizar(clienteEmpresa);
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioAction.class.getName()).log(Level.SEVERE, null, ex);
            throw new ClienteException("Atenção: Falha durante a atualização do Cliente [" + clienteEmpresa.getNome() + "]",ex);
        }        
    }

    public void remover(ClientePessoaJuridica clienteEmpresa) throws ClienteException {
        
        try {
           clienteEmpresaDao.remover(clienteEmpresa);
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioAction.class.getName()).log(Level.SEVERE, null, ex);
            throw new ClienteException("Atenção: Falha durante a exclusão do Cliente [" + clienteEmpresa.getNome() + "]",ex);
        }        
    }

    public ClientePessoaJuridica buscarPorId(Long id) throws ClienteException {
        
        try {
           return ((ClientePessoaJuridica)clienteEmpresaDao.buscarPorId(id));
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioAction.class.getName()).log(Level.SEVERE, null, ex);
            throw new ClienteException("Atenção: Falha durante a busca do Cliente Id [" + id + "]",ex);
        }        
    }

   public boolean isNomeExistente(ClientePessoaJuridica clienteEmpresa) throws ClienteException {
       
       try {
           return clienteEmpresaDao.isNomeExistente(clienteEmpresa);
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioAction.class.getName()).log(Level.SEVERE, null, ex);
            throw new ClienteException("Atenção: Falha durante a verificação de existência do nome do Cliente  [" + clienteEmpresa.getNome() + "]",ex);
        }       
   }

   public boolean isCnpjExistente(ClientePessoaJuridica clienteEmpresa) throws ClienteException {
       
       try {
           return clienteEmpresaDao.isCnpjExistente(clienteEmpresa);
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioAction.class.getName()).log(Level.SEVERE, null, ex);
            throw new ClienteException("Atenção: Falha durante a verificação de existência do CNPJ do Cliente  [" + clienteEmpresa.getCnpj() + "]",ex);
        }       
   }
   
   /**
     * @return the usuarioTableModel
     */
    public BuscaEmpresaTableModel getBuscaEmpresaTableModel() {
        return BuscaEmpresaTableModel;
    }

    /**
     * @param usuarioTableModel the usuarioTableModel to set
     */
    public void setGrupoUsuarioTableModel(BuscaEmpresaTableModel BuscaEmpresaTableModel) {
        this.BuscaEmpresaTableModel = BuscaEmpresaTableModel;
    }
}
