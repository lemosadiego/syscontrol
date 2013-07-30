/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.syscontrol.helper;

import br.com.syscontrol.model.FormaPagamento;
import br.com.syscontrol.model.ItemPedido;
import br.com.syscontrol.model.Parcela;
import br.com.syscontrol.model.Pedido;

/**
 *
 * @author Diego
 */
public class PedidoHelper {


    private Pedido pedido;

    public PedidoHelper(){

    }

    public PedidoHelper(Pedido pedido){
        this.pedido = pedido;
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


    public void addParcela(Parcela parcela){
        FormaPagamento fp = getPedido().getFormaPagamento();
        fp.getParcelas().add(parcela);
    }

    public void deleteParcela(Parcela parcela){
        FormaPagamento fp = getPedido().getFormaPagamento();
        fp.getParcelas().remove(parcela);
    }


    public void addItemPedido(ItemPedido itemPedido){
        this.getPedido().getItensPedido().add(itemPedido);
    }

    public void removeItemPedido(ItemPedido itemPedido){
        this.getPedido().getItensPedido().remove(itemPedido);
    }



}
