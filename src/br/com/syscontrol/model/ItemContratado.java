/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.syscontrol.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author Diego
 */
@Entity
public class ItemContratado implements Serializable {

        @Id
        private Long idItemContratado;
        
        @OneToOne(targetEntity = Servico.class)
        @JoinColumn(name = "idServico")
        private Servico servico;
        
        private Double valor;
        
        @Temporal(javax.persistence.TemporalType.DATE)
        private Date dataVencimentoGarantia;
        
        @Temporal(javax.persistence.TemporalType.DATE)
        private Date dataUltimaExecucao;

    
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
     * @return the dataVencimentoGarantia
     */
    public Date getDataVencimentoGarantia() {
        return dataVencimentoGarantia;
    }

    /**
     * @param dataVencimentoGarantia the dataVencimentoGarantia to set
     */
    public void setDataVencimentoGarantia(Date dataVencimentoGarantia) {
        this.dataVencimentoGarantia = dataVencimentoGarantia;
    }

    /**
     * @return the servico
     */
    public Servico getServico() {
        return servico;
    }

    /**
     * @param servico the servico to set
     */
    public void setServico(Servico servico) {
        this.servico = servico;
    }

    /**
     * @return the dataUltimaExecucao
     */
    public Date getDataUltimaExecucao() {
        return dataUltimaExecucao;
    }

    /**
     * @param dataUltimaExecucao the dataUltimaExecucao to set
     */
    public void setDataUltimaExecucao(Date dataUltimaExecucao) {
        this.dataUltimaExecucao = dataUltimaExecucao;
    }

    /**
     * @return the idItemContratado
     */
    public Long getIdItemContratado() {
        return idItemContratado;
    }

    /**
     * @param idItemContratado the idItemContratado to set
     */
    public void setIdItemContratado(Long idItemContratado) {
        this.idItemContratado = idItemContratado;
    }

}
