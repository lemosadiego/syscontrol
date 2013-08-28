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
public class ClientePessoaFisica extends ClientePessoa {    
    
    private String cpf;
    private String rg;
    private String obs;
    private String telefoneResidencial;
    private String telefoneComercial;
    private String telefoneCelular;
    private String telefoneFax;
    private String contato;    

    /**
     * @return the nome
     */
    @Override
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    @Override
    public void setNome(String nome) {
        this.nome = nome;
    }
  

    /**
     * @return the cnpj
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * @param cnpj the cnpj to set
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    /**
     * @return the ie
     */
    public String getRg() {
        return rg;
    }

    /**
     * @param ie the ie to set
     */
    public void setRg(String rg) {
        this.rg = rg;
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
     * @return the telefoneResidencial
     */
    public String getTelefoneResidencial() {
        return telefoneResidencial;
    }

    /**
     * @param telefoneResidencial the telefoneResidencial to set
     */
    public void setTelefoneResidencial(String telefoneResidencial) {
        this.telefoneResidencial = telefoneResidencial;
    }

    /**
     * @return the telefoneCelular
     */
    public String getTelefoneCelular() {
        return telefoneCelular;
    }

    /**
     * @param telefoneCelular the telefoneCelular to set
     */
    public void setTelefoneCelular(String telefoneCelular) {
        this.telefoneCelular = telefoneCelular;
    }

    /**
     * @return the contato
     */
    public String getContato() {
        return contato;
    }

    /**
     * @param contato the contato to set
     */
    public void setContato(String contato) {
        this.contato = contato;
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

    
}
