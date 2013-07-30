/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.syscontrol.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author Diego
 * Descreve os grupos de acesso que o sistema contem
 *
 */

@Entity
public class GrupoUsuario implements Serializable {

    @Id
    private Long idGrupoUsuario;
    private String descricao;
    

    /**
     * @return the id
     */
    public Long getIdGrupoUsuario() {
        return idGrupoUsuario;
    }

    /**
     * @param id the id to set
     */
    public void setIdGrupoUsuario(Long idGrupoUsuario) {
        this.idGrupoUsuario = idGrupoUsuario;
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

  
}
