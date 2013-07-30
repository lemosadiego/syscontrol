/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.syscontrol.model;

/**
 *
 * @author Diego
 * Definida para identificar a que grupos um determinado usuario faz parte
 */
public class UsuarioCadaGrupo {

    private int id;
    private Usuario usuario;
    private GrupoUsuario grupo;


    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the usuario
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the grupo
     */
    public GrupoUsuario getGrupo() {
        return grupo;
    }

    /**
     * @param grupo the grupo to set
     */
    public void setGrupo(GrupoUsuario grupo) {
        this.grupo = grupo;
    }

 


    

}
