/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.syscontrol.controller;

import br.com.syscontrol.dao.ContratoDao;
import br.com.syscontrol.model.BuscaContratoTableModel;
import br.com.syscontrol.model.Contrato;
import br.com.syscontrol.dao.IContratoDAO;
import br.com.syscontrol.exception.ContratoException;
import br.com.syscontrol.model.ItemPedido;
import br.com.syscontrol.model.ServicoContratadoTableModel;
import br.com.syscontrol.model.ServicoContrato;
import br.com.syscontrol.model.ServicoContratoTableModel;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Diego
 */
public class ContratoAction {

    private ServicoContratoTableModel servicoContratoTableModel = new ServicoContratoTableModel();

    private BuscaContratoTableModel buscaContratoTableModel = new BuscaContratoTableModel();

    private Contrato contrato = new Contrato();

    private IContratoDAO<Contrato> contratoDao = new ContratoDao();

    private String valorCampoBusca;

    
    public void salvar(Contrato contrato) throws ContratoException {
        try{
            contratoDao.salvar(contrato);
        }catch(Exception ex){
            throw new ContratoException("Atenção: Falha durante a inclusão do Contrato Codigo [" + contrato.getIdContrato() + "]. O seguinte erro ocorreu:" + ex.getMessage(), ex);            
        }
            
    }

    public double adicionarValor(double valorAntigo, double valorNovo){
        valorAntigo = valorAntigo + valorNovo;
        return valorAntigo;
    }

    public double removerValor(double valorAntigo, double valorRemovido){
        valorAntigo = valorAntigo - valorRemovido;
        return valorAntigo;
    }

    public void addItemPedidoContratadoGerenciador(JTable table,ServicoContrato servicoContrato) throws ContratoException {
            ((ServicoContratadoTableModel)table.getModel()).addItem(servicoContrato);
    }

    
    public void remover(Contrato contrato) throws ContratoException {

        try{
            contratoDao.remover(contrato);
        }catch(Exception ex){
            throw new ContratoException("Atenção: Falha durante a exclusão do Contrato Codigo [" + contrato.getIdContrato() + "]. O seguinte erro ocorreu:" + ex.getMessage(), ex);            
        }            
    }

    
    public void atualizar(Contrato contrato) throws ContratoException {
        
        try{
            contratoDao.atualizar(contrato);
        }catch(Exception ex){
            throw new ContratoException("Atenção: Falha durante a atualização do Contrato Codigo [" + contrato.getIdContrato() + "]. O seguinte erro ocorreu:" + ex.getMessage(), ex);            
        }          
    }


    public void setTableModel(AbstractTableModel abstractTableModel) throws ContratoException {
        this.servicoContratoTableModel = (ServicoContratoTableModel) abstractTableModel;
    }

    
    public AbstractTableModel getTableModel() throws ContratoException {
        return this.servicoContratoTableModel;
    }

  


    public void popularListaContratos(ArrayList<Contrato> lista) throws ContratoException {
            this.getBuscaContratoTableModel().addListaDeContratos(lista);
    }

    public void popularServicosContrato(JTable table) throws ContratoException {
                 //Monta o grid contendo os dados do serviço
            DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
            renderer.setHorizontalAlignment(SwingConstants.LEFT);

            table.setModel(this.getTableModel());
            table.getColumnModel().getColumn(0).setCellRenderer(renderer);
            table.getColumnModel().getColumn(1).setCellRenderer(renderer);
            table.getColumnModel().getColumn(2).setCellRenderer(renderer);
            table.getColumnModel().getColumn(3).setCellRenderer(renderer);
            table.getColumnModel().getColumn(4).setCellRenderer(renderer);

            table.getColumnModel().getColumn(0).setMaxWidth(80);
            table.getColumnModel().getColumn(1).setMaxWidth(300);
            table.getColumnModel().getColumn(2).setMaxWidth(80);
            table.getColumnModel().getColumn(3).setMaxWidth(150);
            table.getColumnModel().getColumn(4).setMaxWidth(150);
            table.repaint();
    }

    public void popularListaServicosContratadosGerenciador(JTable table) throws ContratoException {
                 //Monta o grid contendo os dados do serviço
            DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
            renderer.setHorizontalAlignment(SwingConstants.LEFT);            
            table.setModel(new ServicoContratadoTableModel());
            table.getColumnModel().getColumn(0).setCellRenderer(renderer);
            table.getColumnModel().getColumn(1).setCellRenderer(renderer);
            table.getColumnModel().getColumn(2).setCellRenderer(renderer);
            table.getColumnModel().getColumn(0).setMaxWidth(530);
            table.getColumnModel().getColumn(1).setMaxWidth(80);
            table.getColumnModel().getColumn(2).setMaxWidth(90);
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

    public void addServicoContrato(ServicoContrato servico){
        this.contrato.getItensContratados().add(servico);
    }

    /**
     * @return the buscaContratoTableModel
     */
    public BuscaContratoTableModel getBuscaContratoTableModel() {
        return buscaContratoTableModel;
    }

    /**
     * @param buscaContratoTableModel the buscaContratoTableModel to set
     */
    public void setBuscaContratoTableModel(BuscaContratoTableModel buscaContratoTableModel) {
        this.buscaContratoTableModel = buscaContratoTableModel;
    }

    /**
     * @return the valorCodigoBusca
     */
    public String getValorCampoBusca() {
        return valorCampoBusca;
    }

    /**
     * @param valorCodigoBusca the valorCodigoBusca to set
     */
    public void setValorCampoBusca(String valorCampoBusca) {
        this.valorCampoBusca = valorCampoBusca;
    }

    public List converterItensContratadosToItensOs(List<ServicoContrato> itensContratados){

        ItemPedido item;
        ServicoContrato sc;
        List<ItemPedido> lista = new ArrayList();
        Iterator i = itensContratados.iterator();

        while(i.hasNext()){
            item = new ItemPedido();
            sc = (ServicoContrato) i.next();
            item.setServico(sc.getServico());
            item.setValor(sc.getValor());
            item.setDataVencimentoGarantia(sc.getDataProximaExecucao());
            lista.add(item);
        }

        return lista;
    }   
   
    
    public List getContratoPorNomeCliente(String nome) throws ContratoException {
        try {
            return contratoDao.buscarPorNomeCliente(nome);
        } catch (SQLException ex) {
             throw new ContratoException("Atenção: Falha durante a consulta de Contrato por Cliente Nome [" + nome + "]. O seguinte erro ocorreu:" + ex.getMessage(), ex);            
        }
    }

    
    public Contrato getContratoPorId(String codigo) throws ContratoException {
        try {
            return contratoDao.buscarPorId(codigo);
        } catch (SQLException ex) {
             throw new ContratoException("Atenção: Falha durante a consulta de Contrato por Codigo [" + codigo + "]. O seguinte erro ocorreu:" + ex.getMessage(), ex);            
        }
        
    }

    
    public void removerItensContrato(Contrato contrato) throws ContratoException {
        contrato.getItensContratados().clear();
    }    
}
