/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.syscontrol.model;

import java.io.Serializable;
import java.sql.Time;
import java.util.Observable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;


/**
 *
 * @author Diego Lemos
 */
@Entity(name = "OrdemServico")
public class OrdemServicoPadrao extends Observable implements Serializable {
    
    @Id
    private Long idOrdemServico;
    
    @OneToOne(targetEntity=Pedido.class, cascade={CascadeType.ALL} )
    @JoinColumn(name="idPedido")	
    private Pedido pedido;
    
    private Double valor = 0.0;
    private String observacao;
    private String usuarioLog;
    private String dataEmissao;
    private Time horaEmissao;
    private String status;
    private String dataExecucao;
    private Time horaExecucao;
    
    public enum StatusOs {
       Aberta, Fechada;
    }



    
    @OneToOne(targetEntity=Contrato.class, cascade={CascadeType.ALL} )
    @JoinColumn(name="idContrato")
    private Contrato contrato; 
    
    @ManyToOne(targetEntity=ClientePessoa.class, cascade={CascadeType.ALL} )
    @JoinColumn(name="idCliente")
    private ClientePessoa cliente;
    

    public OrdemServicoPadrao(Pedido p){
        pedido = p;
        valor = p.getValorPedido();
    }

    public OrdemServicoPadrao(Contrato c){
        contrato = c;
        valor = c.getTotal();
    }
   
     public OrdemServicoPadrao(){        
    }

    /**
     * @return the idOrdemServico
     */
    public Long getIdOrdemServico() {
        return idOrdemServico;
    }

    /**
     * @param idOrdemServico the idOrdemServico to set
     */
    public void setIdOrdemServico(Long idOrdemServico) {
        this.idOrdemServico = idOrdemServico;
    }

    /**
     * @return the pedido
     */
    public Pedido getPedido() {
        return pedido;
    }

    /**
     * @param pedido the pedido to set
     */
    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

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
     * @return the observacao
     */
    public String getObservacao() {
        return observacao;
    }

    /**
     * @param observacao the observacao to set
     */
    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    /**
     * @return the usuarioLog
     */
    public String getUsuarioLog() {
        return usuarioLog;
    }

    /**
     * @param usuarioLog the usuarioLog to set
     */
    public void setUsuarioLog(String usuarioLog) {
        this.usuarioLog = usuarioLog;
    }

    /**
     * @return the dataEmissao
     */
    public String getDataEmissao() {
        return dataEmissao;
    }

    /**
     * @param dataEmissao the dataEmissao to set
     */
    public void setDataEmissao(String dataEmissao) {
        this.dataEmissao = dataEmissao;
    }



    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the horaEmissao
     */
    public Time getHoraEmissao() {
        return horaEmissao;
    }

    /**
     * @param horaEmissao the horaEmissao to set
     */
    public void setHoraEmissao(Time horaEmissao) {
        this.horaEmissao = horaEmissao;
    }

    /**
     * @return the dataExecucao
     */
    public String getDataExecucao() {
        return dataExecucao;
    }

    /**
     * @param dataExecucao the dataExecucao to set
     */
    public void setDataExecucao(String dataExecucao) {
        this.dataExecucao = dataExecucao;
    }

    /**
     * @return the horaExecucao
     */
    public Time getHoraExecucao() {
        return horaExecucao;
    }

    /**
     * @param horaExecucao the horaExecucao to set
     */
    public void setHoraExecucao(Time horaExecucao) {
        this.horaExecucao = horaExecucao;
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
     * @return the cliente
     */
    public ClientePessoa getCliente() {
        return cliente;
    }

    /**
     * @param cliente the cliente to set
     */
    public void setCliente(ClientePessoa cliente) {
        this.cliente = cliente;
    }

}
