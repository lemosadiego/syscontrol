/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.syscontrol.dao;

import br.com.syscontrol.model.Formulario;
import br.com.syscontrol.model.GrupoUsuario;
import br.com.syscontrol.model.Permissao;
import java.sql.SQLException;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Diego
 */
public class PermissaoDao implements IPermissaoDAO<Permissao> {

private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger("PermissaoDao"); 
    private final Session session;   
    
    public PermissaoDao() {
        this.session = HibernateFactory.getSession();    
    } 


    @Override
    public void salvar(Permissao permissao) throws SQLException {
        LOG.info("Salvando o  Permissao ID [" + permissao.getIdPermissao() + "]");
        try{
            Transaction tx = session.beginTransaction();
            session.save(permissao);
            tx.commit(); 
        }catch(Exception ex){
            LOG.error("Falha ao salvar o Permissao ID [" + permissao.getIdPermissao() + "]",ex);
            throw new SQLException();
        } 
    }

    @Override
    public void remover(Permissao permissao) throws SQLException {
        LOG.info("Removendo o  Permissao ID [" + permissao.getIdPermissao()+ "]");
        try{
            Transaction tx = session.beginTransaction();
            session.save(permissao);
            tx.commit(); 
        }catch(Exception ex){
            LOG.error("Falha ao remover o Permissao ID [" + permissao.getIdPermissao() + "]",ex);
            throw new SQLException();
        }
    }

    @Override
    public void atualizar(Permissao permissao) throws SQLException {
        LOG.info("Atualizando o  Permissao ID [" + permissao.getIdPermissao()+ "]");
        try{
            Transaction tx = session.beginTransaction();
            session.save(permissao);
            tx.commit(); 
        }catch(Exception ex){
            LOG.error("Falha ao atualizar o Permissao ID [" + permissao.getIdPermissao()+ "]",ex);
            throw new SQLException();
        }
    }

    @Override
    public List<Permissao> buscarPorFormulario(Formulario formulario) throws SQLException {
        LOG.info("Iniciando busca de Permissao por Nome [" + formulario.getDescricao() + "]");
        Criteria criteria = session.createCriteria(Permissao.class);
        criteria.add(Restrictions.ilike("formulario.descricao", formulario.getDescricao())).addOrder(Order.asc("formulario.descricao"));
        
        try{
            synchronized (this.session) {            
                List<Permissao> listaObject = criteria.list();				
                if(listaObject.isEmpty()){
                        return null;
                }else{                        
                        return listaObject;	
                }
            }
        }catch(Exception ex){
            LOG.error("Falha ao buscar de Permissao por Nome [" + formulario.getDescricao() + "]",ex);
            throw new SQLException();
        }
    }

    @Override
    public List<Permissao> buscarPorGrupo(GrupoUsuario grupoUsuario) throws SQLException {
        LOG.info("Iniciando busca de Permissao por Grupo [" + grupoUsuario.getDescricao() + "]");
        Criteria criteria = session.createCriteria(Permissao.class);
        criteria.add(Restrictions.ilike("permissao.grupoUsuario.idGrupoUsuario", grupoUsuario.getIdGrupoUsuario())).addOrder(Order.asc("formulario.descricao"));
        
        try{
            synchronized (this.session) {            
                List<Permissao> listaObject = criteria.list();				
                if(listaObject.isEmpty()){
                        return null;
                }else{                        
                        return listaObject;	
                }
            }
        }catch(Exception ex){
            LOG.error("Falha ao buscar de Permissao Permissao por Grupo [" + grupoUsuario.getDescricao() + "]",ex);
            throw new SQLException();
        }
    }
    
    @Override
    public Permissao buscarPorId(Permissao permissao) throws SQLException {
        LOG.info("Iniciando busca de Permissao por Id [" + permissao.getIdPermissao() + "]");
        Criteria criteria = session.createCriteria(Permissao.class);        
        criteria.add(Restrictions.eq("idPermissao", permissao.getIdPermissao()));
        
        try{
            synchronized (this.session) {            
                List<Permissao> listaObject = criteria.list();				
                if(listaObject.isEmpty()){
                        return null;
                }else{                        
                        return listaObject.get(0);	
                }
            }
        }catch(Exception ex){
            LOG.error("Falha ao buscar de Permissao por Id [" + permissao.getIdPermissao() + "]",ex);
            throw new SQLException();
        }
    }

    @Override
    public List<Permissao> buscarTodos() throws SQLException {
        LOG.info("Iniciando busca de Todos os Permissoes");
        Criteria criteria = session.createCriteria(Permissao.class);               
        
        try{
            synchronized (this.session) {            
                List<Permissao> listaObject = criteria.list();				
                if(listaObject.isEmpty()){
                        return null;
                }else{                        
                        return listaObject;	
                }
            }
        }catch(Exception ex){
            LOG.error("Falha ao buscar Todos os Permissoes",ex);
            throw new SQLException();
        }
    }    

}
