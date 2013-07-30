/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.syscontrol.dao;

import br.com.syscontrol.model.ClientePessoaJuridica;
import java.sql.*;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Diego
 */
public class ClientePessoaJuridicaDAO implements IClientePessoaJuridicaDAO<ClientePessoaJuridica> {

    private static final Logger LOG = Logger.getLogger("ClientePessoaJuridicaDAO"); 
    private final Session session;   
    
    public ClientePessoaJuridicaDAO() {
        this.session = HibernateFactory.getSession();    
    }

    @Override
    public void salvar(ClientePessoaJuridica clientePessoaJuridica) throws SQLException {
        LOG.info("Salvando o  Cliente Pessoa Juridica Nome [" + clientePessoaJuridica.getNome() + "]");
        try{
            Transaction tx = session.beginTransaction();
            session.save(clientePessoaJuridica);
            tx.commit(); 
        }catch(Exception ex){
            LOG.error("Falha ao salvar o Cliente Pessoa Juridica",ex);
            throw new SQLException();
        }
    }

    @Override
    public void remover(ClientePessoaJuridica clientePessoaJuridica) throws SQLException {
        LOG.info("Removendo o  Cliente Pessoa Juridica Id [" + clientePessoaJuridica.getIdCliente() + "]");
        try{
            Transaction tx = session.beginTransaction();
            session.delete(clientePessoaJuridica);
            tx.commit();
        }catch(Exception ex){
            LOG.error("Falha ao remover o Cliente Pessoa Juridica",ex);
            throw new SQLException();
        }
    }

    @Override
    public void atualizar(ClientePessoaJuridica clientePessoaJuridica) throws SQLException {
        LOG.info("Iniciando atualização do Cliente Pessoa Juridica Id [" + clientePessoaJuridica.getIdCliente()+ "]");
        try{
             Transaction tx = session.beginTransaction();
             session.update(clientePessoaJuridica);
             tx.commit(); 
        }catch(Exception ex){
            LOG.error("Falha ao remover o Cliente Pessoa Juridica",ex);
            throw new SQLException();
        }
    }

    @Override
    public ClientePessoaJuridica buscarPorNome(String nome) throws SQLException {
        LOG.info("Iniciando busca de Cliente Pessoa Juridica Nome [" + nome + "]");
        Criteria criteria = session.createCriteria(ClientePessoaJuridica.class);
        criteria.add(Restrictions.ilike("nome", "%"+ nome+"%")).addOrder(Order.asc("nome"));
        
        try{
            synchronized (this.session) {            
                List<ClientePessoaJuridica> listaObject = criteria.list();				
                if(listaObject.isEmpty()){
                        return new ClientePessoaJuridica();
                }else{                        
                        return listaObject.get(0);	
                }
            }
        }catch(Exception ex){
            LOG.error("Falha ao realizar consulta de de Cliente Pessoa Juridica Nome [" + nome + "]",ex);
            throw new SQLException();
        }
    }

    @Override
    public List<ClientePessoaJuridica> buscarListaPorNome(String nome) throws SQLException {
        LOG.info("Iniciando busca Lista de todos os Cliente Pessoa Juridicas por Nome [" + nome + "]");
        Criteria criteria = session.createCriteria(ClientePessoaJuridica.class);
        criteria.add(Restrictions.ilike("nome", "%"+ nome+"%")).addOrder(Order.asc("nome"));
        
        try{
            synchronized (this.session) {                  
                List<ClientePessoaJuridica> listaClientesCondominios = criteria.list();
                LOG.info("Encontrados [" + listaClientesCondominios.size() + "] Clientes Condominios" ) ;
               return listaClientesCondominios;              
            }
        }catch(Exception ex){
            LOG.error("Falha ao buscar Lista de todos os Cliente Pessoa Juridicas por Nome",ex);
            throw new SQLException();
        }
    }

    @Override
    public List<ClientePessoaJuridica> buscarListaPorBairro(String bairro) throws SQLException {
        LOG.info("Iniciando busca Lista de todos os Cliente Pessoa Juridicas por Bairro [" + bairro + "]");
        Criteria criteria = session.createCriteria(ClientePessoaJuridica.class);
        criteria.add(Restrictions.ilike("bairro", "%"+ bairro+"%")).addOrder(Order.asc("nome"));
        
        try{
            synchronized (this.session) {                  
                List<ClientePessoaJuridica> listaClientesCondominios = criteria.list();
                LOG.info("Encontrados [" + listaClientesCondominios.size() + "] Clientes Condominios" ) ;
               return listaClientesCondominios;              
            }
        }catch(Exception ex){
            LOG.error("Falha ao buscar Lista de todos os Cliente Pessoa Juridicas por Bairro",ex);
            throw new SQLException();
        }
    }

    @Override
    public List<ClientePessoaJuridica> buscarListaPorCnpj(String cnpj) throws SQLException {
       LOG.info("Iniciando busca Lista de todos os Cliente Pessoa Juridicas por CNPJ [" + cnpj + "]");
        Criteria criteria = session.createCriteria(ClientePessoaJuridica.class);
        criteria.add(Restrictions.ilike("cnpj", "%"+ cnpj+"%")).addOrder(Order.asc("nome"));
        
        try{
            synchronized (this.session) {                  
                List<ClientePessoaJuridica> listaClientesCondominios = criteria.list();
                LOG.info("Encontrados [" + listaClientesCondominios.size() + "] Clientes Condominios" ) ;
               return listaClientesCondominios;              
            }
        }catch(Exception ex){
            LOG.error("Falha ao buscar Lista de todos os Cliente Pessoa Juridicas por CNPJ",ex);
            throw new SQLException();
        }
    }

    @Override
    public List<ClientePessoaJuridica> buscarListaPorEndereco(String endereco) throws SQLException {
        LOG.info("Iniciando busca Lista de todos os Cliente Pessoa Juridicas por Endereco [" + endereco + "]");
        Criteria criteria = session.createCriteria(ClientePessoaJuridica.class);
        criteria.add(Restrictions.ilike("endereco", "%"+ endereco+"%")).addOrder(Order.asc("nome"));
        
        try{
            synchronized (this.session) {                  
                List<ClientePessoaJuridica> listaClientesCondominios = criteria.list();
                LOG.info("Encontrados [" + listaClientesCondominios.size() + "] Clientes Condominios" ) ;
               return listaClientesCondominios;              
            }
        }catch(Exception ex){
            LOG.error("Falha ao buscar Lista de todos os Cliente Pessoa Juridicas por Endereco",ex);
            throw new SQLException();
        }
    }

    @Override
    public List<ClientePessoaJuridica> buscarListaPorTelefoneComercial(String telefoneComercial) throws SQLException {
        LOG.info("Iniciando busca Lista de todos os Cliente Pessoa Juridicas por Telefone Comercial [" + telefoneComercial + "]");
        Criteria criteria = session.createCriteria(ClientePessoaJuridica.class);
        criteria.add(Restrictions.ilike("telefoneComercial", "%"+ telefoneComercial+"%")).addOrder(Order.asc("nome"));
        
        try{
            synchronized (this.session) {                  
                List<ClientePessoaJuridica> listaClientesCondominios = criteria.list();
                LOG.info("Encontrados [" + listaClientesCondominios.size() + "] Clientes Condominios" ) ;
               return listaClientesCondominios;              
            }
        }catch(Exception ex){
            LOG.error("Falha ao buscar Lista de todos os Cliente Pessoa Juridicas por Telefone Comercial",ex);
            throw new SQLException();
        }
    }

    @Override
    public ClientePessoaJuridica buscarPorId(Long id) throws SQLException {
        LOG.info("Iniciando busca de Cliente Pessoa Juridica por Id [" + id + "]");
        Criteria criteria = session.createCriteria(ClientePessoaJuridica.class);
        criteria.add(Restrictions.eq("id", id));
        
        try{
            synchronized (this.session) {            
                List<ClientePessoaJuridica> listaObject = criteria.list();				
                if(listaObject.isEmpty()){
                        return new ClientePessoaJuridica();
                }else{
                        return listaObject.get(0);	
                }
            }
        }catch(Exception ex){
            LOG.error("Falha ao realizar consulta de Cliente Pessoa Juridica por Id",ex);
            throw new SQLException();
        }
    }

    @Override
    public List<ClientePessoaJuridica> buscarTodos() throws SQLException {
        LOG.info("Iniciando busca Lista de todos os Cliente Pessoa Juridicas ");
        Criteria criteria = session.createCriteria(ClientePessoaJuridica.class);
        criteria.addOrder(Order.asc("nome"));
        
        try{
            synchronized (this.session) {                  
                List<ClientePessoaJuridica> listaClientesCondominios = criteria.list();
                LOG.info("Encontrados [" + listaClientesCondominios.size() + "] Clientes Condominios" ) ;
               return listaClientesCondominios;              
            }
        }catch(Exception ex){
            LOG.error("Falha ao buscar Lista de todos os Cliente Pessoa Juridicas ",ex);
            throw new SQLException();
        }
    }

    @Override
    public boolean isCnpjExistente(ClientePessoaJuridica clientePessoaJuridica) throws SQLException {
        LOG.info("Iniciando validação de CNPJ Existente para CNPJ [" + clientePessoaJuridica.getCnpj() + "]");
        Criteria criteria = session.createCriteria(ClientePessoaJuridica.class);
        criteria.add(Restrictions.eq("cnpj", clientePessoaJuridica.getCnpj()));
        
        try{
            synchronized (this.session) {            
                List<ClientePessoaJuridica> listaObject = criteria.list();				
                if(listaObject.isEmpty()){
                        return false;
                }else{
                        return true;	
                }
            }
        }catch(Exception ex){
            LOG.error("Falha ao realizar validação de CNPJ Existente para CNPJ [" + clientePessoaJuridica.getCnpj() + "]",ex);
            throw new SQLException();
        }
    }

    @Override
    public boolean isNomeExistente(ClientePessoaJuridica clientePessoaJuridica) throws SQLException {
         LOG.info("Iniciando validação de Nome Existente para Nome [" + clientePessoaJuridica.getNome() + "]");
        Criteria criteria = session.createCriteria(ClientePessoaJuridica.class);
        criteria.add(Restrictions.eq("nome", clientePessoaJuridica.getNome()));
        
        try{
            synchronized (this.session) {            
                List<ClientePessoaJuridica> listaObject = criteria.list();				
                if(listaObject.isEmpty()){
                        return false;
                }else{
                        return true;	
                }
            }
        }catch(Exception ex){
            LOG.error("Falha ao realizar validação de Nome Existente para Nome [" + clientePessoaJuridica.getNome() + "]",ex);
            throw new SQLException();
        }
    }
}
