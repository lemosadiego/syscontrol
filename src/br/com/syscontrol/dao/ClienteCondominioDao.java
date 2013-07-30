/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.syscontrol.dao;

import br.com.syscontrol.model.ClientePessoa;
import br.com.syscontrol.model.ClienteCondominio;
import java.sql.SQLException;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;




/**
 * 
 *
 * @author Diego
 */
public class ClienteCondominioDao implements IClienteCondominioDAO<ClienteCondominio> {
    
    private static final Logger LOG = Logger.getLogger("ClienteCondominioDao"); 
    private final Session session;   
    
    public ClienteCondominioDao() {
        this.session = HibernateFactory.getSession();    
    }

    @Override
    public void salvar(ClienteCondominio clienteCondominio) throws SQLException {
        LOG.info("Salvando o  Cliente Condominio Nome [" + clienteCondominio.getNome() + "]");
        try{
            Transaction tx = session.beginTransaction();
            session.save(clienteCondominio);
            tx.commit(); 
        }catch(Exception ex){
            LOG.error("Falha ao salvar o Cliente Condominio",ex);
            throw new SQLException();
        }
    }

    @Override
    public void remover(ClienteCondominio clienteCondominio) throws SQLException {
        LOG.info("Removendo o  Cliente Condominio Id [" + clienteCondominio.getIdCliente()+ "]");
        try{
            Transaction tx = session.beginTransaction();
            session.delete(clienteCondominio);
            tx.commit();
        }catch(Exception ex){
            LOG.error("Falha ao remover o Cliente Condominio",ex);
            throw new SQLException();
        }
    }

    @Override
    public void atualizar(ClienteCondominio clienteCondominio) throws SQLException {
        LOG.info("Iniciando atualização do Cliente Condominio Id [" + clienteCondominio.getIdCliente()+ "]");
        try{
             Transaction tx = session.beginTransaction();
             session.update(clienteCondominio);
             tx.commit(); 
        }catch(Exception ex){
            LOG.error("Falha ao atualizar o Cliente Condominio",ex);
            throw new SQLException();
        }
    }

    @Override
    public ClienteCondominio buscarPorNome(String nome) throws SQLException {
        LOG.info("Iniciando busca de Cliente Condominio Nome [" + nome + "]");
        Criteria criteria = session.createCriteria(ClientePessoa.class);
        criteria.add(Restrictions.ilike("nome", nome)).addOrder(Order.asc("nome"));
        
        try{
            synchronized (this.session) {            
                List<ClienteCondominio> listaObject = criteria.list();				
                if(listaObject.isEmpty()){
                        return new ClienteCondominio();
                }else{                        
                        return listaObject.get(0);	
                }
            }
        }catch(Exception ex){
            LOG.error("Falha ao realizar consulta de de Cliente Condominio Nome [" + nome + "]",ex);
            throw new SQLException();
        }
    }

    @Override
    public List<ClienteCondominio> buscarListaPorNome(String nome) throws SQLException {
        LOG.info("Iniciando busca Lista de todos os Cliente Condominios por Nome [" + nome + "]");
        Criteria criteria = session.createCriteria(ClienteCondominio.class);
        criteria.add(Restrictions.ilike("nome", "%"+nome+"%")).addOrder(Order.asc("nome"));
        
        try{
            synchronized (this.session) {                  
                List<ClienteCondominio> listaClientesCondominios = criteria.list();
                LOG.info("Encontrados [" + listaClientesCondominios.size() + "] Clientes Condominios" ) ;
               return listaClientesCondominios;              
            }
        }catch(Exception ex){
            LOG.error("Falha ao buscar Lista de todos os Cliente Condominios por Nome",ex);
            throw new SQLException();
        }
    }

    @Override
    public List<ClienteCondominio> buscarListaPorBairro(String bairro) throws SQLException {
        LOG.info("Iniciando busca Lista de todos os Cliente Condominios por Bairro [" + bairro + "]");
        Criteria criteria = session.createCriteria(ClienteCondominio.class);
        criteria.add(Restrictions.ilike("bairro", "%"+bairro+"%")).addOrder(Order.asc("nome"));
        
        try{
            synchronized (this.session) {                  
                List<ClienteCondominio> listaClientesCondominios = criteria.list();
                LOG.info("Encontrados [" + listaClientesCondominios.size() + "] Clientes Condominios" ) ;
               return listaClientesCondominios;              
            }
        }catch(Exception ex){
            LOG.error("Falha ao buscar Lista de todos os Cliente Condominios por Bairro",ex);
            throw new SQLException();
        }
    }

    @Override
    public List<ClienteCondominio> buscarListaPorCnpj(String cnpj) throws SQLException {
       LOG.info("Iniciando busca Lista de todos os Cliente Condominios por CNPJ [" + cnpj + "]");
        Criteria criteria = session.createCriteria(ClienteCondominio.class);
        criteria.add(Restrictions.ilike("cnpj", "%"+cnpj+"%")).addOrder(Order.asc("nome"));
        
        try{
            synchronized (this.session) {                  
                List<ClienteCondominio> listaClientesCondominios = criteria.list();
                LOG.info("Encontrados [" + listaClientesCondominios.size() + "] Clientes Condominios" ) ;
               return listaClientesCondominios;              
            }
        }catch(Exception ex){
            LOG.error("Falha ao buscar Lista de todos os Cliente Condominios por CNPJ",ex);
            throw new SQLException();
        }
    }

    @Override
    public List<ClienteCondominio> buscarListaPorEndereco(String endereco) throws SQLException {
        LOG.info("Iniciando busca Lista de todos os Cliente Condominios por Endereco [" + endereco + "]");
        Criteria criteria = session.createCriteria(ClienteCondominio.class);
        criteria.add(Restrictions.ilike("endereco", "%"+endereco+"%")).addOrder(Order.asc("nome"));
        
        try{
            synchronized (this.session) {                  
                List<ClienteCondominio> listaClientesCondominios = criteria.list();
                LOG.info("Encontrados [" + listaClientesCondominios.size() + "] Clientes Condominios" ) ;
               return listaClientesCondominios;              
            }
        }catch(Exception ex){
            LOG.error("Falha ao buscar Lista de todos os Cliente Condominios por Endereco",ex);
            throw new SQLException();
        }
    }

    @Override
    public List<ClienteCondominio> buscarListaPorTelefoneComercial(String telefoneComercial) throws SQLException {
        LOG.info("Iniciando busca Lista de todos os Cliente Condominios por Telefone Comercial [" + telefoneComercial + "]");
        Criteria criteria = session.createCriteria(ClienteCondominio.class);
        criteria.add(Restrictions.ilike("telefoneComercial", "%"+telefoneComercial+"%")).addOrder(Order.asc("nome"));
        
        try{
            synchronized (this.session) {                  
                List<ClienteCondominio> listaClientesCondominios = criteria.list();
                LOG.info("Encontrados [" + listaClientesCondominios.size() + "] Clientes Condominios" ) ;
               return listaClientesCondominios;              
            }
        }catch(Exception ex){
            LOG.error("Falha ao buscar Lista de todos os Cliente Condominios por Telefone Comercial",ex);
            throw new SQLException();
        }
    }

    @Override
    public ClienteCondominio buscarPorId(Long id) throws SQLException {
        LOG.info("Iniciando busca de Cliente Condominio por Id [" + id + "]");
        Criteria criteria = session.createCriteria(ClienteCondominio.class);
        criteria.add(Restrictions.eq("id", id));
        
        try{
            synchronized (this.session) {            
                List<ClienteCondominio> listaObject = criteria.list();				
                if(listaObject.isEmpty()){
                        return new ClienteCondominio();
                }else{
                        return listaObject.get(0);	
                }
            }
        }catch(Exception ex){
            LOG.error("Falha ao realizar consulta de Cliente Condominio por Id",ex);
            throw new SQLException();
        }
    }

    @Override
    public List<ClienteCondominio> buscarTodos() throws SQLException {
        LOG.info("Iniciando busca Lista de todos os Cliente Condominios ");
        Criteria criteria = session.createCriteria(ClienteCondominio.class);
        criteria.addOrder(Order.asc("nome"));
        
        try{
            synchronized (this.session) {                  
                List<ClienteCondominio> listaClientesCondominios = criteria.list();
                LOG.info("Encontrados [" + listaClientesCondominios.size() + "] Clientes Condominios" ) ;
               return listaClientesCondominios;              
            }
        }catch(Exception ex){
            LOG.error("Falha ao buscar Lista de todos os Cliente Condominios ",ex);
            throw new SQLException();
        }
    }

    @Override
    public boolean isCnpjExistente(ClienteCondominio clienteCondominio) throws SQLException {
        LOG.info("Iniciando validação de CNPJ Existente para CNPJ [" + clienteCondominio.getCnpj() + "]");
        Criteria criteria = session.createCriteria(ClienteCondominio.class);
        criteria.add(Restrictions.eq("cnpj", clienteCondominio.getCnpj()));
        
        try{
            synchronized (this.session) {            
                List<ClienteCondominio> listaObject = criteria.list();				
                if(listaObject.isEmpty()){
                        return false;
                }else{
                        return true;	
                }
            }
        }catch(Exception ex){
            LOG.error("Falha ao realizar validação de CNPJ Existente para CNPJ [" + clienteCondominio.getCnpj() + "]",ex);
            throw new SQLException();
        }
    }

    @Override
    public boolean isNomeExistente(ClienteCondominio clienteCondominio) throws SQLException {
         LOG.info("Iniciando validação de Nome Existente para Nome [" + clienteCondominio.getNome() + "]");
        Criteria criteria = session.createCriteria(ClienteCondominio.class);
        criteria.add(Restrictions.eq("nome", clienteCondominio.getNome()));
        
        try{
            synchronized (this.session) {            
                List<ClienteCondominio> listaObject = criteria.list();				
                if(listaObject.isEmpty()){
                        return false;
                }else{
                        return true;	
                }
            }
        }catch(Exception ex){
            LOG.error("Falha ao realizar validação de Nome Existente para Nome [" + clienteCondominio.getNome() + "]",ex);
            throw new SQLException();
        }
    }
}
