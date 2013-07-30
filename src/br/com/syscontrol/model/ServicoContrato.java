/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.syscontrol.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 *
 * @author Diego Lemos
 * Essa classe é responsavel por representa um serviço que faz parte do Contrato
 * O item valor apesar de tambem estar contido no Servico, representa um possivel valor diferente do padrao 
 * por exemplo um desconto.
 */
@Entity
public class ServicoContrato implements Serializable {
    
    @Id
    private Long idServicoContrato;
    
    @OneToOne(targetEntity=Servico.class)
    @JoinColumn(name="idServico")
    private Servico servico;
    
    @OneToOne(targetEntity=Contrato.class)
    @JoinColumn(name="idContrato")
    private Contrato contrato;
    
    private String dataUltimaExecucao;
    private String dataProximaExecucao;
    private String dataVencimentoGarantia;
    private double valor;

    public ServicoContrato() {
    }

    public ServicoContrato(Servico servico){
        this.servico = servico;
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
    public String getDataUltimaExecucao() {
        return dataUltimaExecucao;
    }

    /**
     * @param dataUltimaExecucao the dataUltimaExecucao to set
     */
    public void setDataUltimaExecucao(String dataUltimaExecucao) {
        this.dataUltimaExecucao = dataUltimaExecucao;
    }

    /**
     * @return the dataProximaExecucao
     */
    public String getDataProximaExecucao() {
        return dataProximaExecucao;
    }

    /**
     * @param dataProximaExecucao the dataProximaExecucao to set
     */
    public void setDataProximaExecucao(String dataProximaExecucao) {
        this.dataProximaExecucao = dataProximaExecucao;
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
     * @return the dataVencimentoGarantia
     */
    public String getDataVencimentoGarantia() {
        return dataVencimentoGarantia;
    }

    /**
     * @param dataVencimentoGarantia the dataVencimentoGarantia to set
     */
    public void setDataVencimentoGarantia(String dataVencimentoGarantia) {
        this.dataVencimentoGarantia = dataVencimentoGarantia;
    }

    /**
     * @return the contrato
     */
    public Contrato getContrato() {
        return contrato;
    }

    /**
     * @param contrato the contrato to set
     */
    public void setContrato(Contrato contrato) {
        this.contrato = contrato;
    }

    /**
     * @return the idServicoContrato
     */
    public Long getIdServicoContrato() {
        return idServicoContrato;
    }

    /**
     * @param idServicoContrato the idServicoContrato to set
     */
    public void setIdServicoContrato(Long idServicoContrato) {
        this.idServicoContrato = idServicoContrato;
    }


}
