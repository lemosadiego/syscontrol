/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.syscontrol.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/** 
 * @author Diego Lemos
 * Data Criação: 18/12/2011
 */
@Entity
public class Contrato implements Serializable {

        /*Coloquei o id como string para seguir o padrao do programa antigo
          que usava o padrao exemplo: C51211*/
        @Id
        private String idContrato;

        @OneToOne(targetEntity = ClientePessoa.class)
        @JoinColumn(name = "idCliente")
        private ClientePessoa cliente = null;
        
        //Lista de itens, objetos ItemContratado
        @OneToMany(targetEntity = Servico.class , cascade={CascadeType.ALL})
        @JoinColumn(name="idServico")
        private List<ItemContratado> itensContratados = new ArrayList();
        
        private String observacao;
        private Double total;
        private Integer periodo;
        private String dataInicioContrato;
        private String dataExpiracao;


    /**
     * @return the id
     */
    public String getIdContrato() {
        return idContrato;
    }

    /**
     * @param id the id to set
     */
    public void setIdContrato(String id) {
        this.idContrato = id;
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
     * @return the total
     */
    public double getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(double total) {
        this.total = total;
    }

    /**
     * @return the periodo
     */
    public int getPeriodo() {
        return periodo;
    }

    /**
     * @param periodo the periodo to set
     */
    public void setPeriodo(int periodo) {
        this.periodo = periodo;
    }

    /**
     * @return the dataInicioContrato
     */
    public String getDataInicioContrato() {
        return dataInicioContrato;
    }

    /**
     * @param dataInicioContrato the dataInicioContrato to set
     */
    public void setDataInicioContrato(String dataInicioContrato) {
        this.dataInicioContrato = dataInicioContrato;
    }

    /**
     * @return the dataExpiracao
     */
    public String getDataExpiracao() {
        return dataExpiracao;
    }

    /**
     * @param dataExpiracao the dataExpiracao to set
     */
    public void setDataExpiracao(String dataExpiracao) {
        this.dataExpiracao = dataExpiracao;
    }

    /**
     * @return the itensContratados
     */
    public List getItensContratados() {
        return itensContratados;
    }

    /**
     * @param itensContratados the itensContratados to set
     */
    public void setItensContratados(List itensContratados) {
        this.itensContratados = itensContratados;
    }


}
