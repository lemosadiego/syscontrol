/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.syscontrol.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;



/**
 *
 * @author Diego
 */
@Entity(name="Colaborador")
public class Colaborador implements Serializable {

    @Id @GeneratedValue 
    private Long idColaborador;
    private String nome;
    private String endereco;
    private String bairro;
    private String cidade;
    private String cep;
    private String uf;
    private String telefoneResidencial;
    private String telefoneCelular;
    private double salario;
    
    @Column(name="dataContratacao")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataContratacao;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name="dataCadastro")
    private Date dataCadastro;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name="dataDemissao")
    private Date dataDemissao;
    
    
    //private int idCargo;

    /**
     * @return the id
     */
    public Long getId() {
        return idColaborador;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.idColaborador = id;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the endereco
     */
    public String getEndereco() {
        return endereco;
    }

    /**
     * @param endereco the endereco to set
     */
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    /**
     * @return the bairro
     */
    public String getBairro() {
        return bairro;
    }

    /**
     * @param bairro the bairro to set
     */
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    /**
     * @return the cidade
     */
    public String getCidade() {
        return cidade;
    }

    /**
     * @param cidade the cidade to set
     */
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    /**
     * @return the cep
     */
    public String getCep() {
        return cep;
    }

    /**
     * @param cep the cep to set
     */
    public void setCep(String cep) {
        this.cep = cep;
    }

    /**
     * @return the uf
     */
    public String getUf() {
        return uf;
    }

    /**
     * @param uf the uf to set
     */
    public void setUf(String uf) {
        this.uf = uf;
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
     * @return the salario
     */
    public double getSalario() {
        return salario;
    }

    /**
     * @param salario the salario to set
     */
    public void setSalario(double salario) {
        this.salario = salario;
    }

    /**
     * @return the dataContratacao
     */
    public Date getDataContratacao() {
        return dataContratacao;
    }

    /**
     * @param dataContratacao the dataContratacao to set
     */
    public void setDataContratacao(Date dataContratacao) {
        this.dataContratacao = dataContratacao;
    }

    /**
     * @return the dataCadastro
     */
    public Date getDataCadastro() {
        return dataCadastro;
    }

    /**
     * @param dataCadastro the dataCadastro to set
     */
    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    /**
     * @return the dataDemissao
     */
    public Date getDataDemissao() {
        return dataDemissao;
    }

    /**
     * @param dataDemissao the dataDemissao to set
     */
    public void setDataDemissao(Date dataDemissao) {
        this.dataDemissao = dataDemissao;
    }

    /**
     * @return the idCargo
     */
//    public int getIdCargo() {
//        return idCargo;
//    }
//
//    /**
//     * @param idCargo the idCargo to set
//     */
//    public void setIdCargo(int idCargo) {
//        this.idCargo = idCargo;
//    }


}
