/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.syscontrol.controller;

import br.com.syscontrol.dao.ClienteCondominioDao;
import br.com.syscontrol.dao.IClienteCondominioDAO;
import br.com.syscontrol.exception.ClienteDaoException;
import br.com.syscontrol.model.BuscaCondominioTableModel;
import br.com.syscontrol.model.ClienteCondominio;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Diego
 */
public class BuscaClienteCondominioAction {

    private IClienteCondominioDAO clienteDao = new ClienteCondominioDao();
    private BuscaCondominioTableModel buscaCondominioTableModel = new BuscaCondominioTableModel();

    public void populaTabelaClientes(List lista) throws SQLException{
        this.buscaCondominioTableModel.limpar();
        this.buscaCondominioTableModel.addListaDeCondominios(lista);
    }

    public ClienteCondominio getCondominioPorCodigo(Long id) throws ClienteDaoException{
        try {
            return (ClienteCondominio)getDao().buscarPorId(id);
        } catch (SQLException ex) {
            Logger.getLogger(BuscaClienteCondominioAction.class.getName()).log(Level.SEVERE, null, ex);
            throw new ClienteDaoException("Atenção: Falha ao Obter Condominio Id [" + id + "]", ex);
        }
    }

    public List getListaCondominioPorCnpj(String cnpj) throws ClienteDaoException{
        try {
            return getDao().buscarListaPorCnpj(cnpj);
        } catch (SQLException ex) {
            Logger.getLogger(BuscaClienteCondominioAction.class.getName()).log(Level.SEVERE, null, ex);
            throw new ClienteDaoException("Atenção: Falha ao Obter Lista de Condominios pelo Cnpj [" + cnpj + "]", ex);
        }
    }

    public List getListaCondominioPorEndereco(String end) throws ClienteDaoException{
        try {
            return getDao().buscarListaPorEndereco(end);
        } catch (SQLException ex) {
            Logger.getLogger(BuscaClienteCondominioAction.class.getName()).log(Level.SEVERE, null, ex);
            throw new ClienteDaoException("Atenção: Falha ao Obter Lista de Condominios pelo Endereço [" + end + "]", ex);
        }
    }

    public List getListaCondominioPorBairro(String bairro) throws ClienteDaoException{
        try {
            return getDao().buscarListaPorBairro(bairro);
        } catch (SQLException ex) {
            Logger.getLogger(BuscaClienteCondominioAction.class.getName()).log(Level.SEVERE, null, ex);
            throw new ClienteDaoException("Atenção: Falha ao Obter Lista de Condominios pelo Bairro [" + bairro + "]", ex);
        }
    }

    public List getListaCondominioPorTelefoneComercial(String tel) throws ClienteDaoException{
        try {
            return getDao().buscarListaPorTelefoneComercial(tel);
        } catch (SQLException ex) {
            Logger.getLogger(BuscaClienteCondominioAction.class.getName()).log(Level.SEVERE, null, ex);
            throw new ClienteDaoException("Atenção: Falha ao Obter Lista de Condominios pelo Telefone Comercial [" + tel + "]", ex);
        }
    }


    public List getListaCondominioPorNome(String nome) throws ClienteDaoException{
        try {
            return getDao().buscarListaPorNome(nome);
        } catch (SQLException ex) {
            Logger.getLogger(BuscaClienteCondominioAction.class.getName()).log(Level.SEVERE, null, ex);
            throw new ClienteDaoException("Atenção: Falha ao Obter Lista de Condominios pelo Nome [" + nome + "]", ex);
        }
    }

    /**
     * @return the dao
     */
    public IClienteCondominioDAO getDao() {
        return clienteDao;
    }

    /**
     * @param dao the dao to set
     */
    public void setDao(ClienteCondominioDao dao) {
        this.clienteDao = dao;
    }

    /**
     * @return the usuarioTableModel
     */
    public BuscaCondominioTableModel getBuscaCondominioTableModel() {
        return buscaCondominioTableModel;
    }

    /**
     * @param usuarioTableModel the usuarioTableModel to set
     */
    public void setGrupoUsuarioTableModel(BuscaCondominioTableModel buscaCondominioTableModel) {
        this.buscaCondominioTableModel = buscaCondominioTableModel;
    }

}
