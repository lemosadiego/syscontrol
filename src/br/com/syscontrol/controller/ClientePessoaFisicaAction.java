/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.syscontrol.controller;

import br.com.syscontrol.dao.ClientePessoaFisicaDao;
import br.com.syscontrol.dao.IClientePessoaFisicaDAO;
import br.com.syscontrol.exception.ClienteException;
import br.com.syscontrol.model.BuscaPessoaFisicaTableModel;
import br.com.syscontrol.model.ClientePessoaFisica;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Diego
 */
public class ClientePessoaFisicaAction {

    private static ClientePessoaFisicaAction clientePessoaFisicaAction;
    
    public static ClientePessoaFisicaAction getInstance(){
        
        if(clientePessoaFisicaAction==null){
            clientePessoaFisicaAction = new ClientePessoaFisicaAction();
        }
         return clientePessoaFisicaAction;
    }
    

    IClientePessoaFisicaDAO clientePessoaFisicaDao = new ClientePessoaFisicaDao();
    private BuscaPessoaFisicaTableModel buscaPessoaTableModel = new BuscaPessoaFisicaTableModel();

    public void populaTabelaClientes(List lista) throws SQLException{
        this.buscaPessoaTableModel.limpar();
        this.buscaPessoaTableModel.addListaDePessoas(lista);
    }

    public void salvar(ClientePessoaFisica clientePessoaFisica) throws ClienteException{    
        
        try {
            clientePessoaFisicaDao.salvar(clientePessoaFisica);
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioAction.class.getName()).log(Level.SEVERE, null, ex);
            throw new ClienteException("Atenção: Falha durante a inclusao do Cliente [" + clientePessoaFisica.getNome() + "]",ex);
        }         
    }

    public ClientePessoaFisica buscarPorNome(String nome) throws ClienteException {
        
        try {
           return (ClientePessoaFisica) clientePessoaFisicaDao.buscarPorNome(nome);
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioAction.class.getName()).log(Level.SEVERE, null, ex);
            throw new ClienteException("Atenção: Falha durante a busca de Cliente por Nome [" + nome + "]",ex);
        }        
    }
    
    public List<ClientePessoaFisica> buscarListaPorNome(String nome) throws ClienteException {
        
        try {
           return clientePessoaFisicaDao.buscarListaPorNome(nome);
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioAction.class.getName()).log(Level.SEVERE, null, ex);
            throw new ClienteException("Atenção: Falha durante a busca de Lista de Cliente por Nome [" + nome + "]",ex);
        }        
    }

    public List<ClientePessoaFisica> buscarListaPorBairro(String bairro) throws ClienteException {
        
        try {
           return clientePessoaFisicaDao.buscarListaPorBairro(bairro);
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioAction.class.getName()).log(Level.SEVERE, null, ex);
            throw new ClienteException("Atenção: Falha durante a busca de Lista de Cliente por Bairro [" + bairro + "]",ex);
        }        
    }
    
    public List<ClientePessoaFisica> buscarListaPorCpf(String cpf) throws ClienteException {
        
        try {
           return clientePessoaFisicaDao.buscarListaPorCpf(cpf);
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioAction.class.getName()).log(Level.SEVERE, null, ex);
            throw new ClienteException("Atenção: Falha durante a busca de Lista de Cliente por Cpf [" + cpf + "]",ex);
        }        
    }
    
    public List<ClientePessoaFisica> buscarListaPorRg(String rg) throws ClienteException {
        
        try {
           return clientePessoaFisicaDao.buscarListaPorCpf(rg);
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioAction.class.getName()).log(Level.SEVERE, null, ex);
            throw new ClienteException("Atenção: Falha durante a busca de Lista de Cliente por Cpf [" + rg + "]",ex);
        }        
    }
    
    public List<ClientePessoaFisica> buscarListaPorEndereco(String end) throws ClienteException {
        
        try {
           return clientePessoaFisicaDao.buscarListaPorEndereco(end);
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioAction.class.getName()).log(Level.SEVERE, null, ex);
            throw new ClienteException("Atenção: Falha durante a busca de Lista de Cliente por Endereço [" + end + "]",ex);
        }        
    }
    
    public List<ClientePessoaFisica> buscarListaPorTelefoneComercial(String telefoneComercial) throws ClienteException {
        
        try {
           return clientePessoaFisicaDao.buscarListaPorTelefoneComercial(telefoneComercial);
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioAction.class.getName()).log(Level.SEVERE, null, ex);
            throw new ClienteException("Atenção: Falha durante a busca de Lista de Cliente por Telefone Comercial [" + telefoneComercial + "]",ex);
        }        
    }
    
    public List<ClientePessoaFisica> buscarListaPorTelefoneCelular(String telefoneCelular) throws ClienteException {
        
        try {
           return clientePessoaFisicaDao.buscarListaPorTelefoneCelular(telefoneCelular);
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioAction.class.getName()).log(Level.SEVERE, null, ex);
            throw new ClienteException("Atenção: Falha durante a busca de Lista de Cliente por Telefone Comercial [" + telefoneCelular + "]",ex);
        }        
    }
    
    public List<ClientePessoaFisica> buscarTodos() throws ClienteException {
        
        try {
           return clientePessoaFisicaDao.buscarTodos();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioAction.class.getName()).log(Level.SEVERE, null, ex);
            throw new ClienteException("Atenção: Falha durante a busca de Todos os Clientes",ex);
        }        
    }
    
    public void alterar(ClientePessoaFisica clientePessoaFisica) throws ClienteException {
        
        try {
           clientePessoaFisicaDao.atualizar(clientePessoaFisica);
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioAction.class.getName()).log(Level.SEVERE, null, ex);
            throw new ClienteException("Atenção: Falha durante a atualização do Cliente [" + clientePessoaFisica.getNome() + "]",ex);
        }        
    }

    public void remover(ClientePessoaFisica clientePessoaFisica) throws ClienteException {
        
        try {
           clientePessoaFisicaDao.remover(clientePessoaFisica);
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioAction.class.getName()).log(Level.SEVERE, null, ex);
            throw new ClienteException("Atenção: Falha durante a exclusão do Cliente [" + clientePessoaFisica.getNome() + "]",ex);
        }        
    }

    public ClientePessoaFisica buscarPorId(Long id) throws ClienteException {
        
        try {
           return ((ClientePessoaFisica)clientePessoaFisicaDao.buscarPorId(id));
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioAction.class.getName()).log(Level.SEVERE, null, ex);
            throw new ClienteException("Atenção: Falha durante a busca do Cliente Id [" + id + "]",ex);
        }        
    }

   public boolean isNomeExistente(ClientePessoaFisica clientePessoaFisica) throws ClienteException {
       
       try {
           return clientePessoaFisicaDao.isNomeExistente(clientePessoaFisica);
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioAction.class.getName()).log(Level.SEVERE, null, ex);
            throw new ClienteException("Atenção: Falha durante a verificação de existência do nome do Cliente  [" + clientePessoaFisica.getNome() + "]",ex);
        }       
   }

   public boolean isCpfExistente(ClientePessoaFisica clientePessoaFisica) throws ClienteException {
       
       try {
           return clientePessoaFisicaDao.isCpfExistente(clientePessoaFisica);
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioAction.class.getName()).log(Level.SEVERE, null, ex);
            throw new ClienteException("Atenção: Falha durante a verificação de existência do CNPJ do Cliente  [" + clientePessoaFisica.getCpf()+ "]",ex);
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
