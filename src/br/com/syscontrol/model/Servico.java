/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.syscontrol.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author Diego
 */
@Entity
public class Servico implements Serializable {

    @Id @GeneratedValue
    protected Long idServico;
    protected String descricao;
    protected Double valor;
    protected Integer garantia;

    /**
     * @return the idServico
     */
    public Long getIdServico() {
        return idServico;
    }

    /**
     * @param idServico the idServico to set
     */
    public void setIdServico(Long idServico) {
        this.idServico = idServico;
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
     * @return the valor
     */
    public double getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(double valor) {
        this.valor = valor;
    }

    /**
     * @return the garantia
     */
    public int getGarantia() {
        return garantia;
    }

    /**
     * @param garantia the garantia to set
     */
    public void setGarantia(int garantia) {
        this.garantia = garantia;
    }

    public boolean isValido() {
        
        if(descricao.isEmpty()){
            return false;
        }else if(valor <= 0){
            return false;
        }else if(garantia <= 0){
            return false;
        }
        
        return true;
    }

}
