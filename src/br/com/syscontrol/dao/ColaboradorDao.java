/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.syscontrol.dao;

import br.com.syscontrol.model.Colaborador;
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
 * @author Diego
 */
public class ColaboradorDao implements IColaboradorDAO<Colaborador> {

    private static final Logger LOG = Logger.getLogger("ColaboradorDao"); 
    private final Session session;   
    
    public ColaboradorDao() {
        this.session = HibernateFactory.getSession();    
    }      


    @Override
    public Colaborador buscarPorNome(String nome) throws SQLException {
        LOG.info("Iniciando busca de Colaborador por Nome [" + nome + "]");
        Criteria criteria = session.createCriteria(Colaborador.class);
        criteria.add(Restrictions.ilike("nome", nome)).addOrder(Order.asc("nome"));
        
        try{
            synchronized (this.session) {            
                List<Colaborador> listaObject = criteria.list();				
                if(listaObject.isEmpty()){
                        return new Colaborador();
                }else{                        
                        return listaObject.get(0);	
                }
            }
        }catch(Exception ex){
            LOG.error("Falha ao realizar consulta de Colaborador por Nome",ex);
            throw new SQLException();
        }
    }

    @Override
    public void atualizar(Colaborador colaborador) throws SQLException {
        LOG.info("Iniciando atualização do Colaborador Id [" + colaborador.getId() + "]");
        try{
            Transaction tx = session.beginTransaction();
             session.update(colaborador);
             tx.commit(); 
        }catch(Exception ex){
            LOG.error("Falha ao atualizar Colaborador",ex);
            throw new SQLException();
        }
    }

    @Override
    public Colaborador buscarPorId(Long id) throws SQLException {
       LOG.info("Iniciando busca de Colaborador por Id [" + id + "]");
        Criteria criteria = session.createCriteria(Colaborador.class);
        criteria.add(Restrictions.eq("id", id));
        
        try{
            synchronized (this.session) {            
                List<Colaborador> listaObject = criteria.list();				
                if(listaObject.isEmpty()){
                        return new Colaborador();
                }else{
                        return listaObject.get(0);	
                }
            }
        }catch(Exception ex){
            LOG.error("Falha ao realizar consulta de Colaborador por Id",ex);
            throw new SQLException();
        }
    }

    @Override
    public List<Colaborador> buscarTodos() throws SQLException {
        LOG.info("Iniciando busca de todos os Colaboradores");
        Criteria criteria = session.createCriteria(Colaborador.class);
        criteria.addOrder(Order.asc("nome"));
        
        try{
            synchronized (this.session) {                  
                List<Colaborador> listaColaboradores = criteria.list();
                LOG.info("Encontrados [" + listaColaboradores.size() + "] Colaboradores" ) ;
               return listaColaboradores;              
            }
        }catch(Exception ex){
            LOG.error("Falha ao buscar todos os Colaboradores",ex);
            throw new SQLException();
        }
    }

    @Override
    public void remover(Colaborador colaborador) throws SQLException {
        LOG.info("Removendo o  Colaborador Id [" + colaborador.getId() + "]");
        try{
            Transaction tx = session.beginTransaction();
            session.delete(colaborador);
            tx.commit();
        }catch(Exception ex){
            LOG.error("Falha ao remover o Colaborador",ex);
            throw new SQLException();
        }
    }

    @Override
    public void salvar(Colaborador colaborador) throws SQLException {
        LOG.info("Salvando o  Colaborador Nome [" + colaborador.getNome() + "]");
        try{
            Transaction tx = session.beginTransaction();
            session.save(colaborador);
            tx.commit(); 
        }catch(Exception ex){
            LOG.error("Falha ao salvar o Colaborador",ex);
            throw new SQLException();
        }
    }


}
