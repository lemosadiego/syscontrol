/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.syscontrol.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 *
 * @author Diego
 */
@Entity
public class Permissao implements Serializable {

    @Id
    private Long idPermissao;
    
    @OneToOne(targetEntity = GrupoUsuario.class)
    @JoinColumn(name = "idGrupoUsuario")
    private GrupoUsuario grupoUsuario;
    
    @OneToOne(targetEntity = Formulario.class)
    @JoinColumn(name = "idFormulario")
    private Formulario formulario;
    
    @ManyToOne(targetEntity = TipoPermissao.class)
    @JoinColumn(name = "idTipoPermissao")
    private TipoPermissao tipoPermissao;

    
    /**
     * @return the formulario
     */
    public Formulario getFormulario() {
        return formulario;
    }

    /**
     * @param formulario the formulario to set
     */
    public void setFormulario(Formulario formulario) {
        this.formulario = formulario;
    }

    /**
     * @return the tipoPermissao
     */
    public TipoPermissao getTipoPermissao() {
        return tipoPermissao;
    }

    /**
     * @param tipoPermissao the tipoPermissao to set
     */
    public void setTipoPermissao(TipoPermissao tipoPermissao) {
        this.tipoPermissao = tipoPermissao;
    }  

    /**
     * @return the idPermissao
     */
    public Long getIdPermissao() {
        return idPermissao;
    }

    /**
     * @param idPermissao the idPermissao to set
     */
    public void setIdPermissao(Long idPermissao) {
        this.idPermissao = idPermissao;
    }

    /**
     * @return the grupoUsuario
     */
    public GrupoUsuario getGrupoUsuario() {
        return grupoUsuario;
    }

    /**
     * @param grupoUsuario the grupoUsuario to set
     */
    public void setGrupoUsuario(GrupoUsuario grupoUsuario) {
        this.grupoUsuario = grupoUsuario;
    }

}
