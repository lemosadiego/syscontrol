/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.syscontrol.controller;

import br.com.syscontrol.dao.IPedidoDAO;
import br.com.syscontrol.dao.PagamentoPedidoDao;
import br.com.syscontrol.dao.PedidoDao;
import br.com.syscontrol.exception.PedidoException;
import br.com.syscontrol.formularios.FrmPrincipal;
import br.com.syscontrol.model.BuscaPedidoTableModel;
import br.com.syscontrol.model.ClientePessoa;
import br.com.syscontrol.model.FormaPagamento;
import br.com.syscontrol.model.ItemPedido;
import br.com.syscontrol.model.Observable;
import br.com.syscontrol.model.Observer;
import br.com.syscontrol.model.ParcelaPedidoTableModel;
import br.com.syscontrol.model.Pedido;
import br.com.syscontrol.model.Servico;
import br.com.syscontrol.model.ServicoDisponivelTableModel;
import br.com.syscontrol.model.ServicoPedidoTableModel;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Diego
 */
public class PedidoAction implements Observable {

    private IPedidoDAO<Pedido> dao;    
    private ArrayList observers;
    private String ERRO;
    private Pedido pedido;    
    private FrmPrincipal telaPrincipal;
    private PagamentoPedidoDao pagamentoPedidoDao;
    private ServicoPedidoTableModel servicoPedidoTableModel = new ServicoPedidoTableModel();
    private BuscaPedidoTableModel buscaPedidoTableModel = new BuscaPedidoTableModel();
    private ParcelaPedidoTableModel parcelaPedidoTableModel = new ParcelaPedidoTableModel();

    public PedidoAction(FrmPrincipal tela){
        observers = new ArrayList();
        telaPrincipal = tela;
        registerObserver(telaPrincipal);
        dao = new PedidoDao();
        pagamentoPedidoDao = new PagamentoPedidoDao();
    }


    
    public double adicionaValor(double valorAntigo, double valorNovo){
        valorAntigo = valorAntigo + valorNovo;
        return valorAntigo;
    }

    public void novoPedido(){
        
    }

    
    public double removeValor(double valorAntigo, double valorRemovido){
        valorAntigo = valorAntigo - valorRemovido;
        return valorAntigo;
    }

    

    public PedidoAction(){
        dao = new PedidoDao();
        pagamentoPedidoDao = new PagamentoPedidoDao();
    }

  
    public Long getNumeroPedido() throws PedidoException{
        try{
            return this.getDao().getNextId();
        }catch(Exception e){
            throw new PedidoException("Atenção: Falha durante a busca do Novo Numero de Pedido", e);
        }        
    }


    
    public void salvar(Pedido pedido) throws PedidoException{      
        
        try{
            getDao().salvar(pedido);            
        }catch(Exception e){
            throw new PedidoException("Atenção: Falha durante a inclusão do Pedido [" + pedido.getIdPedido() + "]",e);
        }
    }

    
    public void remover(Pedido pedido) throws PedidoException {        
        
        try{
            getDao().remover(pedido);
        }catch(Exception e){
            throw new PedidoException("Atenção: Falha durante a remoção do Pedido [" + pedido.getIdPedido() + "]",e);
        }
    }

    
    public void atualizar(Pedido pedido) throws PedidoException, ParseException {
        
        try{
            getDao().atualizar(pedido);
        }catch(Exception e){
            throw new PedidoException("Atenção: Falha durante a atualização do Pedido [" + pedido.getIdPedido() + "]", e);
        }        
    }
    
    public List<Pedido> getPedidoPorNomeCliente(ClientePessoa cliente) throws PedidoException {

        try{
            return  getDao().buscarPedidosPorCliente(cliente);
        }catch(Exception e){
            throw new PedidoException("Atenção: Falha durante a busca do Pedido por Cliente ID [" + cliente.getIdCliente() + "]", e);
        }
        
        //Carrega as informaçoes basicas do pedido
        
    }
    
    public List<Pedido> buscarPedidoPorNomeCliente(String nome) throws PedidoException {

        try{
            return  getDao().buscarPedidosPorNomeCliente(nome);
        }catch(Exception e){
            throw new PedidoException("Atenção: Falha durante a busca do Pedido por Nome Cliente [" + nome + "]", e);
        }
        
        //Carrega as informaçoes basicas do pedido
        
    }

    
    public Pedido buscarPorId(Long idPedido) throws PedidoException {

        //Carrega as informaçoes basicas do pedido
        //Pedido p = getDao().buscarPorId(idPedido);

        //clienteDao = new ClienteGenericoDao();
        /*
        if(p != null){
            try {
                p.setCliente(clienteDao.getClienteGenericoPorCodigo(p.getCliente().getId()));
                p = (Pedido) pagamentoPedidoDao.buscarPorId(p);
                p = (Pedido) getDao().getServicosPedido(p);
            } catch (Exception ex) {
                Logger.getLogger(PedidoAction.class.getName()).log(Level.SEVERE, null, ex);
            }
        }*/

        try{
            return getDao().buscarPorId(idPedido);
        }catch(Exception e){
            throw new PedidoException("Atenção: Falha durante a busca do Pedido por ID [" + idPedido + "]", e);
        }        
    }

    
    public boolean  isObjetoValido(Pedido pedido){

        boolean retorno = true;        

        if(pedido.getCliente().getNome().isEmpty()){
            retorno = false;
            setERRO("O campo Nome do Cliente deve ser informado!");
        }else if(pedido.getCliente().getEndereco().isEmpty()){
            retorno = false;
            setERRO("O campo Endereço deve ser informado!");
        }else if(pedido.getCliente().getBairro().isEmpty()){
            retorno = false;
            setERRO("O campo Bairro deve ser informado!");
        }else if(pedido.getCliente().getCidade().isEmpty()){
            retorno = false;
            setERRO("O campo Cidade deve ser informado!");
        }else if(pedido.getItensPedido().isEmpty()){
            retorno = false;
            setERRO("Informe pelo menos um Item do Pedido!");
        }
        return retorno;
    }

    /**
     * @return the ERRO
     */
    public String getERRO() {
        return ERRO;
    }

    /**
     * @param ERRO the ERRO to set
     */
    public void setERRO(String ERRO) {
        this.ERRO = ERRO;
    }
    
    public void popularListaServicos(JTable table) throws PedidoException, Exception {
                 //Monta o grid contendo os dados do serviço
            DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
            renderer.setHorizontalAlignment(SwingConstants.LEFT);

            ServicoAction servicoAction = new ServicoAction();
            table.setModel(servicoAction.getBuscaServicoDisponivelTableModel());
            List<Servico> listaServicosDisponiveis = servicoAction.buscarTodos();
            if(listaServicosDisponiveis.isEmpty()){
                throw new PedidoException("Não existem Serviços cadastrados", null);
            }else{
                ((ServicoDisponivelTableModel)table.getModel()).setLinhas(servicoAction.buscarTodos());
                table.getColumnModel().getColumn(0).setCellRenderer(renderer);
                table.getColumnModel().getColumn(1).setCellRenderer(renderer);
                table.getColumnModel().getColumn(2).setCellRenderer(renderer);
                table.getColumnModel().getColumn(0).setMaxWidth(530);
                table.getColumnModel().getColumn(1).setMaxWidth(100);
                table.getColumnModel().getColumn(2).setMaxWidth(70);
            }
    }

    
    public void addListaPedidos(ArrayList<Pedido> lista) throws PedidoException {
            this.getBuscaPedidoTableModel().addListaDePedidos(lista);
    }

    
    public void popularServicosPedido(JTable table) throws PedidoException {
                 //Monta o grid contendo os dados do serviço
            DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
            renderer.setHorizontalAlignment(SwingConstants.LEFT);
            
            table.setModel(this.getServicoPedidoTableModel());
            table.getColumnModel().getColumn(0).setCellRenderer(renderer);
            table.getColumnModel().getColumn(1).setCellRenderer(renderer);
            table.getColumnModel().getColumn(2).setCellRenderer(renderer);
            table.getColumnModel().getColumn(0).setMaxWidth(500);
            table.getColumnModel().getColumn(1).setMaxWidth(70);
            table.getColumnModel().getColumn(2).setMaxWidth(130);
            table.repaint();
    }

    
    public void addItemPedido(JTable table,ItemPedido itemPedido) throws PedidoException {                
            this.getServicoPedidoTableModel().addItem(itemPedido);
            this.getPedido().getItensPedido().add(itemPedido);
    }

    
    public void removeItemPedido(ItemPedido item, int indiceLinha) throws PedidoException {

            this.getServicoPedidoTableModel().removeItemPedido(indiceLinha);
            this.getPedido().getItensPedido().remove(item);
    }

    /**
     * @return the servicoPedidoTableModel
     */
    public ServicoPedidoTableModel getServicoPedidoTableModel() {
        return servicoPedidoTableModel;
    }

    /**
     * @param servicoPedidoTabaqua
     * leModel the servicoPedidoTableModel to set
     */
    public void setServicoPedidoTableModel(ServicoPedidoTableModel servicoPedidoTableModel) {
        this.servicoPedidoTableModel = servicoPedidoTableModel;
    }

    /**
     * @return the parcelaPedidoTableModel
     */
    public ParcelaPedidoTableModel getParcelaPedidoTableModel() {
        return parcelaPedidoTableModel;
    }

    /**
     * @param parcelaPedidoTableModel the parcelaPedidoTableModel to set
     */
    public void setParcelaPedidoTableModel(ParcelaPedidoTableModel parcelaPedidoTableModel) {
        this.parcelaPedidoTableModel = parcelaPedidoTableModel;
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
     * @return the dao
     */
    public IPedidoDAO getDao() {
        return dao;
    }

    /**
     * @param dao the dao to set
     */
    public void setDao(IPedidoDAO dao) {
        this.dao = dao;
    }

    /**
     * @return the formaPagamento
     */
    public FormaPagamento getFormaPagamento() {
        return this.getPedido().getFormaPagamento();
    }

    /**
     * @param formaPagamento the formaPagamento to set
     */
    public void setFormaPagamento(FormaPagamento formaPagamento) {       
        this.getPedido().setFormaPagamento(formaPagamento);
    }

    /**
     * @return the pagamentoPedidoDao
     */
    public PagamentoPedidoDao getPagamentoPedidoDao() {

        if(pagamentoPedidoDao ==null){
            pagamentoPedidoDao = new PagamentoPedidoDao();
        }
        return pagamentoPedidoDao;
    }

    /**
     * @param pagamentoPedidoDao the pagamentoPedidoDao to set
     */
    public void setPagamentoPedidoDao(PagamentoPedidoDao pagamentoPedidoDao) {
        this.pagamentoPedidoDao = pagamentoPedidoDao;
    }
  

    /**
     * @return the buscaPedidoTableModel
     */
    public BuscaPedidoTableModel getBuscaPedidoTableModel() {
        return buscaPedidoTableModel;
    }

    /**
     * @param buscaPedidoTableModel the buscaPedidoTableModel to set
     */
    public void setBuscaPedidoTableModel(BuscaPedidoTableModel buscaPedidoTableModel) {
        this.buscaPedidoTableModel = buscaPedidoTableModel;
    }

    public Long getNextNumeroPedido() throws PedidoException {
        
        try{
            return this.getDao().getNextId();
        }catch(Exception e){
            throw new PedidoException("Atenção: Falha durante a obtenção do Codigo do Pedido",e);
        }
        
    }

    public final void registerObserver(Observer o) {
        observers.add(o);
    }

    
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    
    public void notifyObservers(Object arg) {

        Iterator i = observers.iterator();

        while(i.hasNext()){
            Observer o = (Observer) i.next();
            o.update(arg);
        }
    }

}
