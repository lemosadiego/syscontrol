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
public class ClientePessoaJuridica extends ClientePessoa {   
   
    private String cnpj;
    private String ie;
    private String obs;    
    private String telefoneComercial;
    private String telefoneFax;
    private String nomeResponsavel;
    private String telefoneResponsavel;    
    private String email;

    
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
     * @return the nomeResponsavel
     */
    public String getNomeResponsavel() {
        return nomeResponsavel;
    }

    /**
     * @param nomeResponsavel the nomeResponsavel to set
     */
    public void setNomeResponsavel(String nomeResponsavel) {
        this.nomeResponsavel = nomeResponsavel;
    }

    /**
     * @return the telefoneResponsavel
     */
    public String getTelefoneResponsavel() {
        return telefoneResponsavel;
    }

    /**
     * @param telefoneResponsavel the telefoneResponsavel to set
     */
    public void setTelefoneResponsavel(String telefoneResponsavel) {
        this.telefoneResponsavel = telefoneResponsavel;
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
