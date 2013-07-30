/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.syscontrol.model;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

/**
 *
 * @author Diego
 */
@Entity
@PrimaryKeyJoinColumn(name="idPessoa")
public class ClienteCondominio extends ClientePessoa {    
    
    private String sindico;
    private String telefoneSindico;
    private String zelador;
    private String telefoneZelador;
    private String cnpj;
    private String ie;
    private String obs;
    private int quantidadeBlocos;
    private int quantidadeAndares;
    private int quantidadeApAndar;
    private int quantidadeQuartos;    
    private String telefoneComercial;
    private String telefoneFax;
    private String email;

    

    /**
     * @return the sindico
     */
    public String getSindico() {        
        return sindico;
        
    }

    /**
     * @param sindico the sindico to set
     */
    public void setSindico(String sindico) {
        this.sindico = sindico;
    }

    /**
     * @return the telefoneSindico
     */
    public String getTelefoneSindico() {
        return telefoneSindico;
    }

    /**
     * @param telefoneSindico the telefoneSindico to set
     */
    public void setTelefoneSindico(String telefoneSindico) {
        this.telefoneSindico = telefoneSindico;
    }

    /**
     * @return the zelador
     */
    public String getZelador() {
        return zelador;
    }

    /**
     * @param zelador the zelador to set
     */
    public void setZelador(String zelador) {
        this.zelador = zelador;
    }

    /**
     * @return the telefoneZelador
     */
    public String getTelefoneZelador() {
        return telefoneZelador;
    }

    /**
     * @param telefoneZelador the telefoneZelador to set
     */
    public void setTelefoneZelador(String telefoneZelador) {
        this.telefoneZelador = telefoneZelador;
    }

    /**
     * @return the cnpj
     */
    public String getCnpj() {
        return cnpj;
    }

    /**
     * @param cnpj the cnpj to set
     */
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    /**
     * @return the ie
     */
    public String getIe() {
        return ie;
    }

    /**
     * @param ie the ie to set
     */
    public void setIe(String ie) {
        this.ie = ie;
    }

    /**
     * @return the obs
     */
    public String getObs() {
        return obs;
    }

    /**
     * @param obs the obs to set
     */
    public void setObs(String obs) {
        this.obs = obs;
    }

    /**
     * @return the numeroBlocos
     */
    public int getNumeroBlocos() {
        return quantidadeBlocos;
    }

    /**
     * @param numeroBlocos the numeroBlocos to set
     */
    public void setNumeroBlocos(int numeroBlocos) {
        this.quantidadeBlocos = numeroBlocos;
    }

    /**
     * @return the numeroAndares
     */
    public int getNumeroAndares() {
        return quantidadeAndares;
    }

    /**
     * @param numeroAndares the numeroAndares to set
     */
    public void setNumeroAndares(int numeroAndares) {
        this.quantidadeAndares = numeroAndares;
    }

    /**
     * @return the numeroApAndar
     */
    public int getNumeroApAndar() {
        return quantidadeApAndar;
    }

    /**
     * @param numeroApAndar the numeroApAndar to set
     */
    public void setNumeroApAndar(int numeroApAndar) {
        this.quantidadeApAndar = numeroApAndar;
    }

    /**
     * @return the numeroQuartos
     */
    public int getNumeroQuartos() {
        return quantidadeQuartos;
    }

    /**
     * @param numeroQuartos the numeroQuartos to set
     */
    public void setNumeroQuartos(int numeroQuartos) {
        this.quantidadeQuartos = numeroQuartos;
    }

    
    /**
     * @return the telefoneComercial
     */
    public String getTelefoneComercial() {
        return telefoneComercial;
    }

    /**
     * @param telefoneComercial the telefoneComercial to set
     */
    public void setTelefoneComercial(String telefoneComercial) {
        this.telefoneComercial = telefoneComercial;
    }

    /**
     * @return the telefoneFax
     */
    public String getTelefoneFax() {
        return telefoneFax;
    }

    /**
     * @param telefoneFax the telefoneFax to set
     */
    public void setTelefoneFax(String telefoneFax) {
        this.telefoneFax = telefoneFax;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }




}
