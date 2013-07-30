/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.syscontrol.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

/**
 *
 * @author Diego
 */
@Entity(name="Usuario")
public class Usuario implements Serializable {
        
        @Id @GeneratedValue 
        private Long idUsuario;
        private String usuario;
        private String senha;
        private Integer bloqueado = 0;
        
        @ManyToOne(targetEntity=Colaborador.class)
	@JoinColumn(name="idColaborador")
        private Colaborador colaborador;
        
        @ManyToMany(  
            targetEntity=GrupoUsuario.class,  
            cascade={CascadeType.PERSIST, CascadeType.MERGE}  
        )  
        @JoinTable(  
            name="usuario_grupo",  
            joinColumns=@JoinColumn(name="idUsuario"),  
            inverseJoinColumns=@JoinColumn(name="idGrupoUsuario")  
        ) 
	private List<GrupoUsuario> grupos;

    /**
     * @return the id
     */
    public Long getId() {
        return idUsuario;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.idUsuario = id;
    }

    /**
     * @return the usuario
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the senha
     */
    public String getSenha() {
        return senha;
    }

    /**
     * @param senha the senha to set
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }

    /**
     * @return the bloqueado
     */
    public int getBloqueado() {
        return bloqueado;
    }

    /**
     * @param bloqueado the bloqueado to set
     */
    public void setBloqueado(int bloqueado) {
        this.bloqueado = bloqueado;
    }

    /**
     * @return the colaborador
     */
    public Colaborador getColaborador() {
        return colaborador;
    }

    /**
     * @param colaborador the colaborador to set
     */
    public void setColaborador(Colaborador colaborador) {
        this.colaborador = colaborador;
    }

    /**
     * @return the grupos
     */
    public List<GrupoUsuario> getGrupos() {
        return grupos;
    }

    /**
     * @param grupos the grupos to set
     */
    public void setGrupos(List<GrupoUsuario> grupos) {
        this.grupos = grupos;
    }
        
}
