/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.syscontrol.dao;

import br.com.syscontrol.model.Usuario;
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
public class UsuarioDao implements IUsuarioDAO<Usuario>{

    
    private static final Logger LOG = Logger.getLogger("UsuarioDao"); 
    private final Session session;   
    
    public UsuarioDao() {
        this.session = HibernateFactory.getSession();    
    }  
    

    @Override
    public Usuario buscarPorNome(String nome) throws SQLException {
        
        LOG.info("Iniciando busca de Usuario por Nome [" + nome + "]");
        Criteria criteria = session.createCriteria(Usuario.class);
        criteria.add(Restrictions.ilike("usuario", nome)).addOrder(Order.asc("usuario"));
        
        try{
            synchronized (this.session) {            
                List<Usuario> listaObject = criteria.list();				
                if(listaObject.isEmpty()){
                        return new Usuario();
                }else{                        
                        return listaObject.get(0);	
                }
            }
        }catch(Exception ex){
            LOG.error("Falha ao realizar consulta de Usuario por Nome",ex);
            throw new SQLException();
        }
    }

   

    @Override
    public Usuario buscarPorId(Long id) throws SQLException {
        LOG.info("Iniciando busca de Usuario por Id [" + id + "]");
        Criteria criteria = session.createCriteria(Usuario.class);
        criteria.add(Restrictions.eq("id", id));
        
        try{
            synchronized (this.session) {            
                List<Usuario> listaObject = criteria.list();				
                if(listaObject.isEmpty()){
                        return new Usuario();
                }else{
                        return listaObject.get(0);	
                }
            }
        }catch(Exception ex){
            LOG.error("Falha ao realizar consulta de Usuario por Id",ex);
            throw new SQLException();
        }
    }

    @Override
    public void atualizar(Usuario usuario) throws SQLException {
        LOG.info("Iniciando atualização do Usuario Id [" + usuario.getId() + "]");
        try{
            Transaction tx = session.beginTransaction();
             session.update(usuario);
             tx.commit(); 
        }catch(Exception ex){
            LOG.error("Falha ao atualizar Usuario",ex);
            throw new SQLException();
        }
    }

    @Override
    public List<Usuario> buscarTodos() throws SQLException {
        LOG.info("Iniciando busca de todos os Usuarios");
        Criteria criteria = session.createCriteria(Usuario.class);
        criteria.addOrder(Order.asc("usuario"));
        
        try{
            synchronized (this.session) {                  
                List<Usuario> listaUsuarios = criteria.list();
                LOG.info("Encontrados [" + listaUsuarios.size() + "] Usuarios" ) ;
               return listaUsuarios;              
            }
        }catch(Exception ex){
            LOG.error("Falha ao buscar todos os Usuarios",ex);
            throw new SQLException();
        }
    }

    @Override
    public void remover(Usuario usuario) throws SQLException{
        LOG.info("Removendo o  Usuario Id [" + usuario.getId() + "]");
        try{
            Transaction tx = session.beginTransaction();
            session.delete(usuario);
            tx.commit();
        }catch(Exception ex){
            LOG.error("Falha ao remover o Usuario",ex);
            throw new SQLException();
        }
    }

    @Override
    public void salvar(Usuario usuario) throws SQLException {
        LOG.info("Salvando o  Usuario Nome [" + usuario.getUsuario() + "]");
        try{
            Transaction tx = session.beginTransaction();
            session.save(usuario);
            tx.commit(); 
        }catch(Exception ex){
            LOG.error("Falha ao salvar o Usuario",ex);
            throw new SQLException();
        }        
    }   
    
    @Override
    public Usuario getUsuario(Usuario usuario) throws SQLException{
        
        LOG.info("Iniciando busca de Usuario Nome [" + usuario.getUsuario() + "]");
        Criteria criteria = session.createCriteria(Usuario.class);
        criteria.add(Restrictions.eq("usuario", usuario.getUsuario()));
        criteria.add(Restrictions.eq("senha", usuario.getSenha()));
        
        try{
            synchronized (this.session) {            
                List<Usuario> listaObject = criteria.list();				
                if(listaObject.isEmpty()){
                        return new Usuario();
                }else{
                        return listaObject.get(0);	
                }
            }
        }catch(Exception ex){
            LOG.error("Falha ao realizar consulta de Usuario",ex);
            throw new SQLException();
        }
    }

    @Override
    public boolean isUsuarioCadastrado(Usuario usuario)throws SQLException{
        
        LOG.info("Iniciando busca de Usuario Nome [" + usuario.getUsuario() + "]");
        Criteria criteria = session.createCriteria(Usuario.class);
        criteria.add(Restrictions.eq("colaborador.id", usuario.getColaborador().getId()));
        
        try{
            synchronized (this.session) {            
                List<Usuario> listaObject = criteria.list();				
                if(listaObject.isEmpty()){
                        return false;
                }else{
                        return true;	
                }
            }
        }catch(Exception ex){
            LOG.error("Falha ao realizar consulta de Usuario",ex);
            throw new SQLException();
        }      

    }
    
    @Override
    public boolean isLoginCadastrado(Usuario usuario)throws SQLException{
        
        LOG.info("Iniciando busca de Usuario Nome [" + usuario.getUsuario() + "]");
        Criteria criteria = session.createCriteria(Usuario.class);
        criteria.add(Restrictions.eq("colaborador.id", usuario.getColaborador().getId()));
        
        try{
            synchronized (this.session) {            
                List<Usuario> listaObject = criteria.list();				
                if(listaObject.isEmpty()){
                        return false;
                }else{
                        return true;	
                }
            }
        }catch(Exception ex){
            LOG.error("Falha ao realizar consulta de Usuario",ex);
            throw new SQLException();
        }
        

    }

}
