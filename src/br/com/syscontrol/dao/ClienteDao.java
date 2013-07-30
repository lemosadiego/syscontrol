/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.syscontrol.dao;


import br.com.syscontrol.model.ClientePessoa;
import java.sql.SQLException;
import java.util.ArrayList;
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
public class ClienteDao implements IClienteDAO<ClientePessoa> {
    
    private static final Logger LOG = Logger.getLogger("ClienteDao"); 
    private final Session session;   
    
    public ClienteDao() {
        this.session = HibernateFactory.getSession();    
    }  
    

    @Override
    public ClientePessoa buscarPorNome(String nome) throws SQLException {
        
        LOG.info("Iniciando busca de Cliente por Nome [" + nome + "]");
        Criteria criteria = session.createCriteria(ClientePessoa.class);
        criteria.add(Restrictions.like("nome", "%"+nome+"%")).addOrder(Order.asc("nome"));
        
        try{
            synchronized (this.session) {            
                List<ClientePessoa> listaObject = criteria.list();				
                if(listaObject.isEmpty()){
                        return new ClientePessoa();
                }else{                        
                        return listaObject.get(0);	
                }
            }
        }catch(Exception ex){
            LOG.error("Falha ao realizar consulta de Cliente por Nome",ex);
            throw new SQLException();
        }
    }
    
    @Override
    public List<ClientePessoa> buscarListaPorNome(String nome) throws SQLException {
        
        LOG.info("Iniciando busca de Cliente por Nome [" + nome + "]");
        Criteria criteria = session.createCriteria(ClientePessoa.class);
        criteria.add(Restrictions.like("nome", "%"+nome+"%")).addOrder(Order.asc("nome"));
        
        try{
            synchronized (this.session) {            
                List<ClientePessoa> listaObject = criteria.list();				
                if(listaObject.isEmpty()){
                        return new ArrayList<ClientePessoa>();
                }else{                        
                        return listaObject;	
                }
            }
        }catch(Exception ex){
            LOG.error("Falha ao realizar consulta de Cliente por Nome",ex);
            throw new SQLException();
        }
    }

   

    @Override
    public ClientePessoa buscarPorId(Long id) throws SQLException {
        LOG.info("Iniciando busca de Cliente por Id [" + id + "]");
        Criteria criteria = session.createCriteria(ClientePessoa.class);
        criteria.add(Restrictions.eq("id", id));
        
        try{
            synchronized (this.session) {            
                List<ClientePessoa> listaObject = criteria.list();				
                if(listaObject.isEmpty()){
                        return new ClientePessoa();
                }else{
                        return listaObject.get(0);	
                }
            }
        }catch(Exception ex){
            LOG.error("Falha ao realizar consulta de Cliente por Id",ex);
            throw new SQLException();
        }
    }

    @Override
    public void atualizar(ClientePessoa cliente) throws SQLException {
        LOG.info("Iniciando atualização do Cliente Id [" + cliente.getIdCliente()+ "]");
        try{
            Transaction tx = session.beginTransaction();
             session.update(cliente);
             tx.commit(); 
        }catch(Exception ex){
            LOG.error("Falha ao atualizar Cliente",ex);
            throw new SQLException();
        }
    }

    @Override
    public List<ClientePessoa> buscarTodos() throws SQLException {
        LOG.info("Iniciando busca de todos os Clientes");
        Criteria criteria = session.createCriteria(ClientePessoa.class);
        criteria.addOrder(Order.asc("nome"));
        
        try{
            synchronized (this.session) {                  
                List<ClientePessoa> listaClientes = criteria.list();
                LOG.info("Encontrados [" + listaClientes.size() + "] Clientes" ) ;
               return listaClientes;              
            }
        }catch(Exception ex){
            LOG.error("Falha ao buscar todos os Clientes",ex);
            throw new SQLException();
        }
    }

    @Override
    public void remover(ClientePessoa cliente) throws SQLException{
        LOG.info("Removendo o  Cliente Id [" + cliente.getIdCliente()+ "]");
        try{
            Transaction tx = session.beginTransaction();
            session.delete(cliente);
            tx.commit();
        }catch(Exception ex){
            LOG.error("Falha ao remover o Cliente",ex);
            throw new SQLException();
        }
    }

    @Override
    public void salvar(ClientePessoa cliente) throws SQLException {
        LOG.info("Salvando o  Cliente Nome [" + cliente.getNome() + "]");
        try{
            Transaction tx = session.beginTransaction();
            session.save(cliente);
            tx.commit(); 
        }catch(Exception ex){
            throw new SQLException();
        }        
    }   
}
