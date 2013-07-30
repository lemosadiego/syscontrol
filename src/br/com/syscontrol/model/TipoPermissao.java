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
 */
@Entity
public class TipoPermissao implements Serializable {

        @Id
        private Long idTipoPermissao;
        private String descricao;
        private int nivel;

    /**
     * @return the id
     */
    public Long getId() {
        return idTipoPermissao;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.idTipoPermissao = id;
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

    /**
     * @return the nivel
     */
    public int getNivel() {
        return nivel;
    }

    /**
     * @param nivel the nivel to set
     */
    public void setNivel(int nivel) {
        this.nivel = nivel;
    }
}
