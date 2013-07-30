/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.syscontrol.dao;

import br.com.syscontrol.model.Formulario;
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
public class FormularioDao implements IFormularioDAO<Formulario> {
    
    private static final Logger LOG = Logger.getLogger("FormularioDao"); 
    private final Session session;   
    
    public FormularioDao() {
        this.session = HibernateFactory.getSession();
    }

    @Override
    public void salvar(Formulario formulario) throws SQLException {
        LOG.info("Salvando o  Formulario Nome [" + formulario.getDescricao() + "]");
        try{
            Transaction tx = session.beginTransaction();
            session.save(formulario);
            tx.commit(); 
        }catch(Exception ex){
            LOG.error("Falha ao salvar o Formulario",ex);
            throw new SQLException();
        }
    }

    @Override
    public void atualizar(Formulario formulario) throws SQLException {
        LOG.info("Atualizando o  Formulario Nome [" + formulario.getDescricao() + "]");
        try{
            Transaction tx = session.beginTransaction();
            session.update(formulario);
            tx.commit(); 
        }catch(Exception ex){
            LOG.error("Falha ao atualizar o Formulario",ex);
            throw new SQLException();
        }
    }

    @Override
    public void remover(Formulario formulario) throws SQLException {
        LOG.info("Removendo o  Formulario Nome [" + formulario.getDescricao() + "]");
        try{
            Transaction tx = session.beginTransaction();
            session.delete(formulario);
            tx.commit(); 
        }catch(Exception ex){
            LOG.error("Falha ao remover o Formulario",ex);
            throw new SQLException();
        }        
    }

    @Override
    public List<Formulario> buscarPorNome(String nome) throws SQLException {
        LOG.info("Iniciando busca de Formulario por Nome [" + nome + "]");
        Criteria criteria = session.createCriteria(Formulario.class);
        criteria.add(Restrictions.ilike("descricao", nome)).addOrder(Order.asc("descricao"));
        
        try{
            synchronized (this.session) {            
                List<Formulario> listaObject = criteria.list();				
                if(listaObject.isEmpty()){
                        return null;
                }else{                        
                        return listaObject;	
                }
            }
        }catch(Exception ex){
            LOG.error("Falha ao realizar busca de Formulario por Nome [" + nome + "]",ex);
            throw new SQLException();
        }
    }

    @Override
    public Formulario buscarPorId(Long id) throws SQLException {
        LOG.info("Iniciando busca de Formulario por Id [" + id + "]");
        Criteria criteria = session.createCriteria(Formulario.class);
        criteria.add(Restrictions.ilike("idFormulario", id));
        
        try{
            synchronized (this.session) {            
                List<Formulario> listaObject = criteria.list();				
                if(listaObject.isEmpty()){
                        return new Formulario();
                }else{                        
                        return listaObject.get(0);	
                }
            }
        }catch(Exception ex){
            LOG.error("Falha ao realizar busca de Formulario por Id [" + id + "]",ex);
            throw new SQLException();
        }
    }

    @Override
    public List<Formulario> buscarTodos() throws SQLException {
        
        LOG.info("Iniciando busca de Todos os Formularios");
        Criteria criteria = session.createCriteria(Formulario.class);
        criteria.addOrder(Order.asc("descricao"));
        
        try{
            synchronized (this.session) {            
                List<Formulario> listaObject = criteria.list();				
                if(listaObject.isEmpty()){
                        return null;
                }else{                        
                        return listaObject;	
                }
            }
        }catch(Exception ex){
            LOG.error("Falha ao realizar busca de Todos os Formularios",ex);
            throw new SQLException();
        }
        
    }


//       Conexao conexao = new Conexao();
//        Connection con;
//
//
//
//    public HashMap getListaFormularios(){
//
//        Statement stmt;
//        ResultSet rs;
//        HashMap lista = new HashMap();
//        Formulario f = null;
//        String sql = "SELECT * FROM FORMULARIOS";
//
//        try {
//            con = conexao.abreConexao();
//            stmt = con.createStatement();
//            rs = stmt.executeQuery(sql);
//            
//            while(rs.next()){
//                f = new Formulario();
//                f.setId(rs.getInt("ID"));
//                f.setDescricao(rs.getString("DESCRICAO"));
//                lista.put(f.getDescricao(), f);
//            }
//            conexao.fechaConexao(con);
//        } catch (SQLException ex) {
//            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return lista;
//    }


   
}
