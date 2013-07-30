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
import javax.persistence.OneToOne;

/**
 *
 * @author Diego
 */
@Entity
public class ItemPedido implements Serializable {

        @Id @GeneratedValue
        private Long idItemPedido;
    
        @OneToOne(targetEntity = Servico.class)
        @JoinColumn(name = "idServico")
        private Servico servico;  
        
        private double valor;
        
        private String dataVencimentoGarantia;
        
        @ManyToOne(targetEntity=Pedido.class ,cascade={CascadeType.ALL})
	@JoinColumn(name="idPedido")
	private Pedido pedido;

    /**
     * @return the idServico
     */
    public Long getIdServico() {
        return  getServico().getIdServico();
    }

    /**
     * @param idServico the idServico to set
     */
    public void setIdServico(Long idServico) {
        this.getServico().setIdServico(idServico);
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
     * @return the idItemPedido
     */
    public Long getIdItemPedido() {
        return idItemPedido;
    }

    /**
     * @param idItemPedido the idItemPedido to set
     */
    public void setIdItemPedido(Long idItemPedido) {
        this.idItemPedido = idItemPedido;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (this.idItemPedido != null ? this.idItemPedido.hashCode() : 0);
        hash = 97 * hash + (this.servico != null ? this.servico.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ItemPedido other = (ItemPedido) obj;
        if (this.idItemPedido != other.idItemPedido && (this.idItemPedido == null || !this.idItemPedido.equals(other.idItemPedido))) {
            return false;
        }
        if (this.servico != other.servico && (this.servico == null || !this.servico.equals(other.servico))) {
            return false;
        }
        return true;
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

   
   

}
