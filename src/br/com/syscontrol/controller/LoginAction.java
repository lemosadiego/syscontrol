/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.syscontrol.controller;

import br.com.syscontrol.dao.IUsuarioDAO;
import br.com.syscontrol.dao.UsuarioDao;
import br.com.syscontrol.exception.UsuarioException;
import br.com.syscontrol.model.Usuario;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Diego
 * Classe responsavel por validar as informaçoes de um usuario.
 */
public class LoginAction {

    IUsuarioDAO usuarioDAO = new UsuarioDao();
    Boolean retorno = false;

    public Usuario isUsuarioValido(Usuario u) throws UsuarioException{ 
        
        Usuario usuarioLogin;
        
        try {
            usuarioLogin = usuarioDAO.getUsuario(u);
        } catch (SQLException ex) {
            Logger.getLogger(LoginAction.class.getName()).log(Level.SEVERE, null, ex);
            throw new UsuarioException("Atenção: Falha durante a consulta do Usuario");
        }
        
        return usuarioLogin;
    }

}
