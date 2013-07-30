package br.com.syscontrol.dao;


import org.hibernate.Session;
import org.hibernate.cfg.AnnotationConfiguration;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Diego
 */
public class HibernateFactory {
    
    private static final AnnotationConfiguration configurationHibernate = new AnnotationConfiguration();
    
    private static Session session = null;
    
    public static Session getSession(){
        
        if(session == null){
           configurationHibernate.configure("Configuracao/hibernate.cfg.xml");
           session = configurationHibernate.buildSessionFactory().openSession();
        }
        
        return session;        
    }
}
