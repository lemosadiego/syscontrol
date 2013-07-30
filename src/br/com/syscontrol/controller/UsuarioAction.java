/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.syscontrol.controller;

import br.com.syscontrol.dao.ColaboradorDao;
import br.com.syscontrol.dao.IColaboradorDAO;
import br.com.syscontrol.dao.IUsuarioDAO;
import br.com.syscontrol.dao.UsuarioDao;
import br.com.syscontrol.exception.UsuarioException;
import br.com.syscontrol.model.Usuario;
import br.com.syscontrol.model.UsuarioTableModel;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Diego
 * Criei esta classe para estabelecer as funcionalidades referetes ao Usuario e seus formularios
 */
public class UsuarioAction {

    private IUsuarioDAO usuarioDAO = new UsuarioDao();
    private UsuarioTableModel usuarioTableModel = new UsuarioTableModel();
    private IColaboradorDAO colaboradorDAO = new ColaboradorDao();

    public List getListaColaboradores()throws UsuarioException{
        try {
            return colaboradorDAO.buscarTodos();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioAction.class.getName()).log(Level.SEVERE, null, ex);
            throw new UsuarioException("Atenção: Falha ao listar Colaboradores");
        }
    }


    public void populaTabelaUsuarios() throws UsuarioException{
        this.usuarioTableModel.limpar();
        this.usuarioTableModel.addListaDeUsuarios((List<Usuario>) this.getListaUsuarios());
    }



    public void incluir(Usuario u) throws UsuarioException{
        try {
            getDao().salvar(u);
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioAction.class.getName()).log(Level.SEVERE, null, ex);
            throw new UsuarioException("Atenção: Falha durante a inclusao do Usuario [" + u.getUsuario() + "]");
        }
    }


    public void alterar(Usuario u) throws UsuarioException{
        
         try {
            getDao().atualizar(u);
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioAction.class.getName()).log(Level.SEVERE, null, ex);
            throw new UsuarioException("Atenção: Falha durante a atualização do Usuario [" + u.getUsuario() + "]");
        }
        
    }

    public void deletar(Usuario u) throws UsuarioException{
        try {
            getDao().remover(u);
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioAction.class.getName()).log(Level.SEVERE, null, ex);
            throw new UsuarioException("Atenção: Falha durante a exclusão do Usuario [" + u.getUsuario() + "]");
        }
    }   

    public List getListaUsuarios() throws UsuarioException{
        
        try {
            return getDao().buscarTodos();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioAction.class.getName()).log(Level.SEVERE, null, ex);
            throw new UsuarioException("Atenção: Falha durante a listagem de Todos os Usuarios");
        }
        
    }

    public boolean isUsuarioCadastrado(Usuario u) throws UsuarioException{
        
        try {
            return getDao().isUsuarioCadastrado(u);
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioAction.class.getName()).log(Level.SEVERE, null, ex);
            throw new UsuarioException("Atenção: Falha durante a verificação de Usuario Cadastrado");
        }
        
    }

    public boolean isLoginExistente(Usuario u) throws UsuarioException{
        try {
            return getDao().isLoginCadastrado(u);
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioAction.class.getName()).log(Level.SEVERE, null, ex);
            throw new UsuarioException("Atenção: Falha durante a verificação de Login Cadastrado");
        }
    }

    
    public Long getIdColaborador(String nome) throws UsuarioException{
        
        try {
            return colaboradorDAO.buscarPorNome(nome).getId();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioAction.class.getName()).log(Level.SEVERE, null, ex);
            throw new UsuarioException("Atenção: Falha durante a busca do Colaborador");
        }
        
    }

    public Usuario buscarPorId(Long id) throws UsuarioException {
        try {
            return usuarioDAO.buscarPorId(id);
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioAction.class.getName()).log(Level.SEVERE, null, ex);
            throw new UsuarioException("Atenção: Falha durante a busca do Usuario por ID [" + id + "]");
        }
    }
    
    public Usuario getUsuario(Usuario usuario) throws UsuarioException {
        try {
            return usuarioDAO.getUsuario(usuario);
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioAction.class.getName()).log(Level.SEVERE, null, ex);
            throw new UsuarioException("Atenção: Falha durante a busca do Usuario por Login [" + usuario.getUsuario() + "]  e Senha [" + usuario.getSenha() + "]");
        }
    }

    

    /**
     * @return the dao
     */
    public IUsuarioDAO getDao() {
        return usuarioDAO;
    }

    /**
     * @param dao the dao to set
     */
    public void setDao(UsuarioDao dao) {
        this.usuarioDAO = dao;
    }

    /**
     * @return the usuarioTableModel
     */
    public UsuarioTableModel getUsuarioTableModel() {
        return usuarioTableModel;
    }

    /**
     * @param usuarioTableModel the usuarioTableModel to set
     */
    public void setUsuarioTableModel(UsuarioTableModel usuarioTableModel) {
        this.usuarioTableModel = usuarioTableModel;
    }


}
