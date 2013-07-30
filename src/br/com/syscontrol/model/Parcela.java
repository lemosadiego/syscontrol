/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.syscontrol.model;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author Diego
 */
@Entity
public class Parcela implements Serializable {

        @Id @GeneratedValue
        private Long idParcela;
        
        private Integer numeroParcela;
        
        private String dataVencimento;
        
        private Double valor;    
        
        @ManyToOne(targetEntity=FormaPagamento.class, cascade={CascadeType.PERSIST})
	@JoinColumn(name="idFormaPagamento")
	private FormaPagamento formaPagamento;

        /**
     * @return the valor
     */
    public Double getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(Double valor) {
        this.valor = valor;
    }

    /**
     * @return the idPagamento
     */
 

    /**
     * @param idPagamento the idPagamento to set
     */

    /**
     * @return the dataVencimento
     */
    public String getDataVencimento() {
        return dataVencimento;
    }

    /**
     * @param dataVencimento the dataVencimento to set
     */
    public void setDataVencimento(String dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    /**
     * @return the idParcela
     */
    public Long getIdParcela() {
        return idParcela;
    }

    /**
     * @param idParcela the idParcela to set
     */
    public void setIdParcela(Long idParcela) {
        this.idParcela = idParcela;
    }

    /**
     * @return the numeroParcela
     */
    public Integer getNumeroParcela() {
        return numeroParcela;
    }

    /**
     * @param numeroParcela the numeroParcela to set
     */
    public void setNumeroParcela(Integer numeroParcela) {
        this.numeroParcela = numeroParcela;
    }

    /**
     * @return the formaPagamento
     */
    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    /**
     * @param formaPagamento the formaPagamento to set
     */
    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

}
