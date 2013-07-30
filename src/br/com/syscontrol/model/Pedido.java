/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.syscontrol.model;

import br.com.syscontrol.helper.Util;
import java.io.Serializable;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


/**
 *
 * @author Diego
 */
@Entity
public class Pedido implements Serializable {

    @Id @GeneratedValue
    private Long idPedido;
    
    @ManyToOne(targetEntity=ClientePessoa.class, cascade={CascadeType.PERSIST} )
    @JoinColumn(name="idCliente")
    private ClientePessoa cliente;

    private String usuarioCadastro;    
    
    private String dataCadastro;
    
    private String dataAgendamento;
    private Time horaAgendamento;
    private String dataExecucao;
    private Time horaExecucao;    
    private String observacao;
    
    //Contem a lista de serviços do pedido
    @OneToMany(targetEntity=ItemPedido.class , cascade={CascadeType.ALL})
    @JoinColumn(name="idPedido")	
    private List<ItemPedido> itensPedido = new ArrayList<ItemPedido>();
    
    @OneToOne(targetEntity=FormaPagamento.class, cascade={CascadeType.ALL})
    @JoinColumn(name="idFormaPagamento")	
    private FormaPagamento formaPagamento;
    
    private Double valorPedido;
    
    private String status;

    public Pedido(){
        this.dataCadastro = new Util().getDataHoje();
    }

    /**
     * @return the idPedido
     */
    public Long getIdPedido() {
        return idPedido;
    }


    /**
     * @param idPedido the idPedido to set
     */
    public void setIdPedido(Long idPedido) {
        this.idPedido = idPedido;
    }

    /**
     * @return the idCliente
     */
    public ClientePessoa getCliente() {
        return cliente;
    }

    /**
     * @param idCliente the idCliente to set
     */
    public void setCliente(ClientePessoa cliente) {
        this.cliente = cliente;
    }

  
    /**
     * @return the horaAgendamento
     */
    public Time getHoraAgendamento() {
        return horaAgendamento;
    }

    /**
     * @param horaAgendamento the horaAgendamento to set
     */
    public void setHoraAgendamento(Time horaAgendamento) {
        this.horaAgendamento = horaAgendamento;
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
     * @return the itensPedido
     */
    public List<ItemPedido> getItensPedido() {
        return itensPedido;
    }

    /**
     * @param itensPedido the itensPedido to set
     */
    public void setItensPedido(List itensPedido) {
        this.itensPedido = itensPedido;
    }

    /**
     * @return the valorPedido
     */
    public Double getValorPedido() {
        return valorPedido;
    }

    /**
     * @param valorPedido the valorPedido to set
     */
    public void setValorPedido(Double valorPedido) {
        this.valorPedido = valorPedido;
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
     * @return the dataCadastro
     */
    public String getDataCadastro() {
        return dataCadastro;
    }

    /**
     * @param dataCadastro the dataCadastro to set
     */
    public void setDataCadastro(String dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    /**
     * @return the dataAgendamento
     */
    public String getDataAgendamento() {
        return dataAgendamento;
    }

    /**
     * @param dataAgendamento the dataAgendamento to set
     */
    public void setDataAgendamento(String dataAgendamento) {
        this.dataAgendamento = dataAgendamento;
    }

    /**
     * @return the usuarioCadastro
     */
    public String getUsuarioCadastro() {
        return usuarioCadastro;
    }

    /**
     * @param usuarioCadastro the usuarioCadastro to set
     */
    public void setUsuarioCadastro(String usuarioCadastro) {
        this.usuarioCadastro = usuarioCadastro;
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

}
