/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.syscontrol.controller;

import br.com.syscontrol.dao.ColaboradorDao;
import br.com.syscontrol.dao.IColaboradorDAO;
import br.com.syscontrol.model.Colaborador;
import br.com.syscontrol.model.Usuario;
import br.com.syscontrol.model.UsuarioCadaGrupoTableModel;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Diego
 */
public class UsuarioCadaGrupoAction {

//    private UsuarioCadaGrupoDao dao = new UsuarioCadaGrupoDao();
//    private UsuarioCadaGrupoTableModel ucgTableModel = new UsuarioCadaGrupoTableModel();
//    private IColaboradorDAO colaboradorDAO;
//    private Usuario usuario;
//
//     public UsuarioCadaGrupoAction(){
//       
//    }    
//
//    public List getListaGruposPorUsuario(Usuario u){
//        return dao.getListaGruposPorUsuario(u);
//    }
//
//    public void populaTabelaUsuarioCadaGrupo(Usuario u){
//        this.getUcgTableModel().limpar();
//        this.getUcgTableModel().addListaDeUsuariosCadaGrupo(this.getListaGruposPorUsuario(u));
//    }
//
//    public Usuario getUsuario(String nomeColaborador){
//        return dao.getUsuario(nomeColaborador);
//    }
//    
//
//    /**
//     * @return the usuario
//     */
//    public Usuario getUsuario() {
//        return usuario;
//    }
//
//    /**
//     * @param usuario the usuario to set
//     */
//    public void setUsuario(Usuario usuario) {
//        this.usuario = usuario;
//    }
//
//    /**
//     * @return the ucgTableModel
//     */
//    public UsuarioCadaGrupoTableModel getUcgTableModel() {
//        return ucgTableModel;
//    }
//
//    /**
//     * @param ucgTableModel the ucgTableModel to set
//     */
//    public void setUcgTableModel(UsuarioCadaGrupoTableModel ucgTableModel) {
//        this.ucgTableModel = ucgTableModel;
//    }
//
//    public List<Colaborador> getListaColaboradores(){
//        
//        colaboradorDAO = new ColaboradorDao();
//        
//        List<Colaborador> listaColaboradores = null;
//        
//        try {
//            listaColaboradores = colaboradorDAO.buscarTodos();
//        } catch (SQLException ex) {
//            Logger.getLogger(UsuarioCadaGrupoAction.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//        return listaColaboradores;
//    }

}
