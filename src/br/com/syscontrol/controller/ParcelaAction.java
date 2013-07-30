/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.syscontrol.controller;

import br.com.syscontrol.model.FormaPagamento;
import br.com.syscontrol.model.Parcela;
import br.com.syscontrol.model.ParcelaPedidoTableModel;
import br.com.syscontrol.model.Pedido;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 * @author Diego
 * Data Criacao: 10/12/2011
 */
public class ParcelaAction implements GenericAction{

    AbstractTableModel parcelaPedidoTableModel;
    Pedido pedido;

    @Override
    public void inserir(Object o) throws Exception {
        ((ParcelaPedidoTableModel)parcelaPedidoTableModel).addParcela((Parcela)o);
    }

    @Override
    public void remover(Object o) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void alterar(Object o) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Collection buscarPorNome(Object o) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Collection buscarPorId(Object o) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Collection buscarTodos() throws Exception {
        return((ParcelaPedidoTableModel)parcelaPedidoTableModel).getLinhas();
    }

    @Override
    public void setTableModel(AbstractTableModel abstractTableModel) throws Exception {
        parcelaPedidoTableModel = abstractTableModel;
    }

    @Override
    public AbstractTableModel getTableModel() throws Exception {

        if(parcelaPedidoTableModel!= null){
            return parcelaPedidoTableModel;
        }else{
            return new ParcelaPedidoTableModel();
        }
        
    }

    @Override
    public boolean isServicoExistente(Object o) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * @return the formaPagamento
     */
    public FormaPagamento getFormaPagamento() {
        return pedido.getFormaPagamento();
    }

    /**
     * @param formaPagamento the formaPagamento to set
     */
    public void setFormaPagamento(FormaPagamento formaPagamento) {
        if(formaPagamento == null){
            this.pedido.setFormaPagamento(new FormaPagamento());
        }else{
            this.pedido.setFormaPagamento(formaPagamento);
        }

    }

    /*Popula tabela se o modo de escolha de parcelamento for = automatico*/
    public void populaTabelaAutomatica(){

        try {
                ((ParcelaPedidoTableModel) this.getTableModel()).limpar();
                ((ParcelaPedidoTableModel) this.getTableModel()).addListaDeParcelas(this.getFormaPagamento().getParcelas());
                
                            
        } catch (Exception ex) {
            Logger.getLogger(ParcelaAction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /*Popula tabela se o modo de escolha de parcelamento for = automatico*/
    public void populaTabelaManual(){

        try {
                ((ParcelaPedidoTableModel) this.getTableModel()).addListaDeParcelas(this.getFormaPagamento().getParcelas());

        } catch (Exception ex) {
            Logger.getLogger(ParcelaAction.class.getName()).log(Level.SEVERE, null, ex);
        }

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

    @Override
    public GenericAction getAction() {
        return this;
    }

}
