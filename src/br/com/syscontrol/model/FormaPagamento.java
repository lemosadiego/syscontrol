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
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;


/**
 *
 * @author Diego
 */
@Entity
public class FormaPagamento implements Serializable {

    @Id @GeneratedValue
    private Long idFormaPagamento;
    
    @OneToMany(targetEntity=Parcela.class , cascade={CascadeType.ALL})
    @JoinColumn(name="idFormaPagamento")	
    private List<Parcela> parcelas = new ArrayList<Parcela>();
    
//    @OneToOne(targetEntity = Pedido.class, cascade={CascadeType.ALL})
//    @JoinColumn(name="idPedido")
//    private Pedido pedido;
    
    private Double valorTotal = 0.0;
    private Double valorEntrada = 0.0;
    //Define a forma de calculo usado, Automatico = true, Manual = false
    private boolean calculoAutomatico;
     

    /**
     * @return the parcelas
     */
    public List<Parcela> getParcelas() {
        return parcelas;
    }

    /**
     * @param parcelas the parcelas to set
     */
    public void setParcelas(List<Parcela> parcelas) {
        this.parcelas = parcelas;
    }

    /**
     * @return the valorTotal
     */
    public Double getValorTotal() {
        return valorTotal;
    }

    /**
     * @param valorTotal the valorTotal to set
     */
    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    /**
     * @return the calculoAutomatico
     */
    public boolean isCalculoAutomatico() {
        return calculoAutomatico;
    }

    /**
     * @param calculoAutomatico the calculoAutomatico to set
     */
    public void setCalculoAutomatico(boolean calculoAutomatico) {
        this.calculoAutomatico = calculoAutomatico;
    }

    /**
     * @return the valorEntrada
     */
    public Double getValorEntrada() {
        return valorEntrada;
    }

    /**
     * @param valorEntrada the valorEntrada to set
     */
    public void setValorEntrada(Double valorEntrada) {
        this.valorEntrada = valorEntrada;
    }

//    /**
//     * @return the pedido
//     */
//    public Pedido getPedido() {
//        return pedido;
//    }
//
//    /**
//     * @param pedido the pedido to set
//     */
//    public void setPedido(Pedido pedido) {
//        this.pedido = pedido;
//    }

    /**
     * @return the idFormaPagamento
     */
    public Long getIdFormaPagamento() {
        return idFormaPagamento;
    }

    /**
     * @param idFormaPagamento the idFormaPagamento to set
     */
    public void setIdFormaPagamento(Long idFormaPagamento) {
        this.idFormaPagamento = idFormaPagamento;
    }



}
