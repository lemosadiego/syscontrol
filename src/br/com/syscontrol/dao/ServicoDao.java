/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.syscontrol.dao;


import br.com.syscontrol.model.Servico;
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
public class ServicoDao implements IServicoDAO<Servico> {

    private static final Logger LOG = Logger.getLogger("ServicoDao"); 
    private final Session session;   
    
    public ServicoDao() {
        this.session = HibernateFactory.getSession();    
    } 


    @Override
    public void salvar(Servico servico) throws SQLException {
        LOG.info("Salvando o  Servico Nome [" + servico.getDescricao() + "]");
        try{
            Transaction tx = session.beginTransaction();
            session.save(servico);
            tx.commit(); 
        }catch(Exception ex){
            LOG.error("Falha ao salvar o Servico Nome [" + servico.getDescricao() + "]",ex);
            throw new SQLException();
        } 
    }

    @Override
    public void remover(Servico servico) throws SQLException {
        LOG.info("Removendo o  Servico Nome [" + servico.getDescricao() + "]");
        try{
            Transaction tx = session.beginTransaction();
            session.delete(servico);
            tx.commit(); 
        }catch(Exception ex){
            LOG.error("Falha ao remover o Servico Nome [" + servico.getDescricao() + "]",ex);
            throw new SQLException();
        }
    }

    @Override
    public void atualizar(Servico servico) throws SQLException {
        LOG.info("Atualizando o  Servico Nome [" + servico.getDescricao() + "]");
        try{
            Transaction tx = session.beginTransaction();
            session.save(servico);
            tx.commit(); 
        }catch(Exception ex){
            LOG.error("Falha ao atualizar o Servico Nome [" + servico.getDescricao() + "]",ex);
            throw new SQLException();
        }
    }

    @Override
    public List<Servico> buscarPorNome(String nome) throws SQLException {
        LOG.info("Iniciando busca de Servico por Nome [" + nome + "]");
        Criteria criteria = session.createCriteria(Servico.class);
        criteria.add(Restrictions.eq("descricao", nome));
        
        try{
            synchronized (this.session) {            
                List<Servico> listaObject = criteria.list();				
                if(listaObject.isEmpty()){
                        return new ArrayList<Servico>();
                }else{                        
                        return listaObject;	
                }
            }
        }catch(Exception ex){
            LOG.error("Falha ao buscar de Servico por Nome [" + nome + "]",ex);
            throw new SQLException();
        }
    }

    @Override
    public Servico buscarPorId(Long id) throws SQLException {
        LOG.info("Iniciando busca de Servico por Id [" + id + "]");
        Criteria criteria = session.createCriteria(Servico.class);        
        criteria.add(Restrictions.eq("idServico", id));
        
        try{
            synchronized (this.session) {            
                List<Servico> listaObject = criteria.list();				
                if(listaObject.isEmpty()){
                        return null;
                }else{                        
                        return listaObject.get(0);	
                }
            }
        }catch(Exception ex){
            LOG.error("Falha ao buscar de Servico por Id [" + id + "]",ex);
            throw new SQLException();
        }
    }

    @Override
    public List<Servico> buscarTodos() throws SQLException {
        LOG.info("Iniciando busca de Todos os Servicos");
        Criteria criteria = session.createCriteria(Servico.class);               
        
        try{
            synchronized (this.session) {            
                List<Servico> listaObject = criteria.list();				
                if(listaObject.isEmpty()){
                        return new ArrayList<Servico>();
                }else{                        
                        return listaObject;	
                }
            }
        }catch(Exception ex){
            LOG.error("Falha ao buscar Todos os Servicos",ex);
            throw new SQLException();
        }
    }

    @Override
    public boolean isServicoExistente(Servico servico) throws SQLException {
        LOG.info("Iniciando validação de existencia de Servico por Nome [" + servico.getDescricao() + "]");
        Criteria criteria = session.createCriteria(Servico.class);        
        criteria.add(Restrictions.eq("descricao", servico.getDescricao()));
        
        try{
            synchronized (this.session) {            
                List<Servico> listaObject = criteria.list();				
                if(listaObject.isEmpty()){
                        return false;
                }else{                        
                        return true;	
                }
            }
        }catch(Exception ex){
            LOG.error("Falha ao validar existencia de Servico por Nome [" + servico.getDescricao() + "]",ex);
            throw new SQLException();
        }
    }
}
