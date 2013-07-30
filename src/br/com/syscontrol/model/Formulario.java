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
 *
 */
@Entity
public class Formulario implements Serializable {

    @Id
    private Long idFormulario;
    private String descricao;

    /**
     * @return the id
     */
    public Long getId() {
        return idFormulario;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.idFormulario = id;
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
