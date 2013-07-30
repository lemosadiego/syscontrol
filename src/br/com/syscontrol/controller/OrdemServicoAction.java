/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.syscontrol.controller;


import br.com.syscontrol.dao.ContratoDao;
import br.com.syscontrol.dao.IOrdemServicoDAO;
import br.com.syscontrol.dao.OrdemServicoPadraoDao;
import br.com.syscontrol.exception.OrdemServicoException;
import br.com.syscontrol.formularios.FrmPrincipal;

import br.com.syscontrol.helper.Util;
import br.com.syscontrol.model.BuscaOrdemServicoTableModel;
import br.com.syscontrol.model.ClientePessoa;
import br.com.syscontrol.model.Contrato;
import br.com.syscontrol.model.ItemPedido;
import br.com.syscontrol.model.Observable;
import br.com.syscontrol.model.Observer;
import br.com.syscontrol.model.OrdemServicoPadrao;
import br.com.syscontrol.model.OrdemServicoTableModel;
import br.com.syscontrol.model.Pedido;
import br.com.syscontrol.model.Servico;
import br.com.syscontrol.model.ServicoContrato;
import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Diego
 */
public class OrdemServicoAction implements Observable{

    private ArrayList observers;

    private OrdemServicoTableModel ordemServicoTableModel = new OrdemServicoTableModel();

    private BuscaOrdemServicoTableModel buscaOrdemServicoTableModel = new BuscaOrdemServicoTableModel();

    private IOrdemServicoDAO<OrdemServicoPadrao> ordemServicoPadraoDao = new OrdemServicoPadraoDao();
    
    private OrdemServicoPadrao ordemServicoPadrao;

    private ContratoDao contratoDaoInterface = new ContratoDao();

    private PedidoAction pedidoAction;    
    
    private SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

    private FrmPrincipal telaPrincipal;

    private ArrayList itensOs;

    public OrdemServicoAction(FrmPrincipal tela){
        observers = new ArrayList();
        telaPrincipal = tela;
        registerObserver(telaPrincipal);
        itensOs = new ArrayList();
        pedidoAction = new PedidoAction(tela);
    }


   
    public boolean isStatusOSValido() throws OrdemServicoException{

        try{
            if(!this.getOrdemServicoPadrao().getStatus().equalsIgnoreCase("FECHADA")){
                if(this.getOrdemServicoPadrao().getDataExecucao()!= null){
                    if(!this.getOrdemServicoPadrao().getDataExecucao().equals("")){
                        javax.swing.JOptionPane.showMessageDialog(null, "Data e Hora de execução só devem ser informadas em caso de fechamento. Status não é válido!","Atenção - Aviso",0);
                        return false;
                    }
                }
            }else if(this.getOrdemServicoPadrao().getStatus().equalsIgnoreCase("FECHADA")){
                if(this.getOrdemServicoPadrao().getDataExecucao()!= null){
                    if(!this.getOrdemServicoPadrao().getDataExecucao().equals("")){
                        if(this.isDataExecucaoValida()){
                            return true;
                        }else{
                            return false;
                        }
                    }
                }
            }
        }catch(Exception e){
            throw new OrdemServicoException("Atenção: Falha ao validar status de OS. O seguinte erro ocorreu:" + e.getMessage(), e);            
        }
            
            return true;
    }




   
    public boolean isDataExecucaoValida() throws OrdemServicoException{

        try{
            Date dataExecucao = format.parse(this.getOrdemServicoPadrao().getDataExecucao());
            Date dataHoje = format.parse(new Util().getDataHoje());

            if(this.getOrdemServicoPadrao().getDataExecucao().equals("") || this.getOrdemServicoPadrao().getHoraExecucao() == null){
              javax.swing.JOptionPane.showMessageDialog(null, "Os Campos Data Execução e Hora Execução precisam ser informados.","Atenção",0);
            }

            if(dataExecucao.after(dataHoje)){
              javax.swing.JOptionPane.showMessageDialog(null, "A Data de execução não pode ser maior que a data atual.","Atenção - Aviso",0);
              return false;
            }
        }catch(Exception e){
           throw new OrdemServicoException("Atenção: Falha ao validar Data de Execução de OS. O seguinte erro ocorreu:" + e.getMessage(), e);            
        }
        
        return true;
    }

    
   
   
    public void inserir(OrdemServicoPadrao os) throws OrdemServicoException {

        try {
            if(os.getContrato() == null && os.getPedido() == null){
                javax.swing.JOptionPane.showMessageDialog(telaPrincipal, "Não é possível realizar a operação!. Não existe um Pedido ou Contrato associado."
                        ,"Atenção",0);
            }else if(os.getIdOrdemServico()!= null && os.getIdOrdemServico() >0){
                javax.swing.JOptionPane.showMessageDialog(telaPrincipal, "Atenção. A OS já existe na base de dados. O processo de inclusão será cancelado.","Atenção",2);
            }else{                
                if(!os.getStatus().equalsIgnoreCase("Aberta")){
                    javax.swing.JOptionPane.showMessageDialog(telaPrincipal, "Uma Ordem de Serviço só pode ser incluída com status = ABERTA. Favor corrigir o status!","Atenção",0);
                }else{
                    /*
                    if(os.getPedido()!=null){
                        os = (OrdemServicoPadrao) this.getOsPorPedido(os.getPedido().getIdPedido());
                        //os = (OrdemServicoPadrao) this.getOrdemServicoAction().consultarPorId(os.getIdOrdemServico());
                    }

                    if(os.getContrato()!=null){
                            os = (OrdemServicoPadrao) this.getOsPorContrato(os.getContrato().getIdContrato());
                            //os = (OrdemServicoPadrao) this.getOrdemServicoAction().consultarPorId(os.getIdOrdemServico());
                    }
                    */
                    this.getOrdemServicoPadraoDao().salvar(os);
                    javax.swing.JOptionPane.showMessageDialog(telaPrincipal, "O cadastrado foi realizado com suceso!","Aviso",1);
                    this.notifyObservers(os);
                }
            }
        } catch (Exception ex) {
            throw new OrdemServicoException("Atenção: Falha ao incluir OS Numero [" + os.getIdOrdemServico() + "] . O seguinte erro ocorreu:" + ex.getMessage(), ex);            
        }                 
    }

    
   
    public void remover(OrdemServicoPadrao os) throws OrdemServicoException {
        try{
         this.getOrdemServicoPadraoDao().remover(os);
        }catch(Exception e){
            throw new OrdemServicoException("Atenção: Falha ao remover OS Numero [" + os.getIdOrdemServico() + "] . O seguinte erro ocorreu:" + e.getMessage(), e);            
        }
    }

    
    
    public void alterar(OrdemServicoPadrao os) throws Exception {

        try{
            this.getOrdemServicoPadraoDao().atualizar(os);
             this.notifyObservers(os);        
        }catch(Exception e){
            throw new OrdemServicoException("Atenção: Falha ao remover OS Numero [" + os.getIdOrdemServico() + "] . O seguinte erro ocorreu:" + e.getMessage(), e);            
        }
        
        
//        try{
//            if(os.getStatus().equalsIgnoreCase("Aberta") ||
//                    os.getStatus().equalsIgnoreCase("Cancelada")||
//                    os.getStatus().equalsIgnoreCase("Indisp. ClientePessoa")){
//                    this.getOrdemServicoPadraoDao().atualizar(os);
//
//            }else if(os.getStatus().equalsIgnoreCase("Fechada") && os.getDataExecucao()!= null && os.getHoraExecucao()!= null){
//                    this.getOrdemServicoPadraoDao().alterar(os);
//
//                    if(os.getContrato()!= null){
//                        this.getOrdemServicoPadraoDao().atualizarDadosServicosContrato(os);
//                    }
//                    
//                    javax.swing.JOptionPane.showMessageDialog(telaPrincipal, "A alteração foi realizada com sucesso!","Aviso",1);
//            }else {
//                 javax.swing.JOptionPane.showMessageDialog(telaPrincipal, "A Data e Hora da Execução precisam ser informadas para o STATUS = FECHADA. ","Atenção - Aviso",2);
//            }
//        }catch(Exception e){
//            javax.swing.JOptionPane.showMessageDialog(telaPrincipal, "Falha ao alterar a OS. O seguinte erro ocorreu:" + e.getMessage(),"Atenção - Erro",3);
//        }

       
    }

    
   
    public List<OrdemServicoPadrao> getOsPorCliente(ClientePessoa cliente) throws OrdemServicoException {
        try{
              //Carrega as informaçoes basicas da ordem de serviço
            return getOrdemServicoPadraoDao().buscarPorCliente(cliente);
        }catch(Exception e){
            throw new OrdemServicoException("Atenção: Falha ao consultar OS por Cliente Nome [" + cliente.getNome() + "] . O seguinte erro ocorreu:" + e.getMessage(), e);            
        }
    }

   
    public OrdemServicoPadrao getOsPorPedido(Long numeroPedido) throws OrdemServicoException{
        try{
         return getOrdemServicoPadraoDao().buscarPorPedido(numeroPedido);
        }catch(SQLException e){
            throw new OrdemServicoException("Atenção: Falha ao consultar OS por Pedido Numero [" + numeroPedido + "] . O seguinte erro ocorreu:" + e.getMessage(), e);            
        }         
    }

   
    public OrdemServicoPadrao getOsPorContrato(String codigoContrato) throws OrdemServicoException{
        try{
         return getOrdemServicoPadraoDao().buscarPorContrato(codigoContrato);
        }catch(Exception e){
            throw new OrdemServicoException("Atenção: Falha ao consultar OS por Contrato Codigo [" + codigoContrato + "] . O seguinte erro ocorreu:" + e.getMessage(), e);            
        }         
    }

    
   
    public OrdemServicoPadrao getOsPorId(long id) throws OrdemServicoException {
        try{
         return getOrdemServicoPadraoDao().buscarPorId(id);
        }catch(SQLException e){
            throw new OrdemServicoException("Atenção: Falha ao consultar OS por ID [" + id + "] . O seguinte erro ocorreu:" + e.getMessage(), e);            
        }
    }

   

   
    public void popularServicosPedido(JTable table) throws Exception {
                 //Monta o grid contendo os dados do serviço
            DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
            renderer.setHorizontalAlignment(SwingConstants.LEFT);

            table.setModel(this.getOrdemServicoTableModel());
            table.getColumnModel().getColumn(0).setCellRenderer(renderer);
            table.getColumnModel().getColumn(1).setCellRenderer(renderer);
            table.getColumnModel().getColumn(2).setCellRenderer(renderer);
            table.getColumnModel().getColumn(0).setMaxWidth(530);
            table.getColumnModel().getColumn(1).setMaxWidth(100);
            table.getColumnModel().getColumn(2).setMaxWidth(70);
            table.repaint();
    }

   
    public void aplicarModelOrdemServico(JTable table) throws Exception {
                 //Monta o grid contendo os dados do serviço
            DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
            renderer.setHorizontalAlignment(SwingConstants.LEFT);

            table.setModel(this.getOrdemServicoTableModel());
            table.getColumnModel().getColumn(0).setCellRenderer(renderer);
            table.getColumnModel().getColumn(1).setCellRenderer(renderer);
            table.getColumnModel().getColumn(2).setCellRenderer(renderer);
            table.getColumnModel().getColumn(0).setMaxWidth(530);
            table.getColumnModel().getColumn(1).setMaxWidth(100);
            table.getColumnModel().getColumn(2).setMaxWidth(130);
            table.repaint();
    }

   
    public void popularListaOrdensBusca(ArrayList<OrdemServicoPadrao> lista) throws Exception {
            this.getBuscaOrdemServicoTableModel().addListaDeOrdens(lista);
    }
    /**
     * @return the servicoPedidoTableModel
     */
    public OrdemServicoTableModel getOrdemServicoTableModel() {
        return ordemServicoTableModel;
    }

    /**
     * @param servicoPedidoTableModel the servicoPedidoTableModel to set
     */
    public void setOrdemServicoTableModel(OrdemServicoTableModel ordemServicoTableModel) {
        this.ordemServicoTableModel = ordemServicoTableModel;
    }

    /**
     * @return the ordemServicoPadrao
     */
    public OrdemServicoPadrao getOrdemServicoPadrao() {
        return ordemServicoPadrao;
    }

    /**
     * @param ordemServicoPadrao the ordemServicoPadrao to set
     */
    public void setOrdemServicoPadrao(OrdemServicoPadrao ordemServicoPadrao) {
        this.ordemServicoPadrao = ordemServicoPadrao;
    }

    /**
     * @return the buscaOrdemServicoTableModel
     */
    public BuscaOrdemServicoTableModel getBuscaOrdemServicoTableModel() {
        return buscaOrdemServicoTableModel;
    }

    /**
     * @param buscaOrdemServicoTableModel the buscaOrdemServicoTableModel to set
     */
    public void setBuscaOrdemServicoTableModel(BuscaOrdemServicoTableModel buscaOrdemServicoTableModel) {
        this.buscaOrdemServicoTableModel = buscaOrdemServicoTableModel;
    }

  
   
    public ItemPedido converterServicoToItemPedido(Servico servico){
        Calendar calendar = Calendar.getInstance();
        //Atribui a data do dia
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),calendar.get(Calendar.DATE));
        calendar.add(Calendar.MONTH, servico.getGarantia());

        ItemPedido itemPedido = new ItemPedido();
        itemPedido.setServico(servico);        
        itemPedido.setServico(servico);
        itemPedido.setValor(servico.getValor());
        itemPedido.setDataVencimentoGarantia(format.format(calendar.getTime()));

        return itemPedido;
    }

   
    public void addItemOS(Object item, List<ItemPedido> itensOS){
            Servico s = (Servico) item;
            ItemPedido itemPedido = new ItemPedido();
            itemPedido.setServico(s);            
            itemPedido.setValor(s.getValor());
            itemPedido.setDataVencimentoGarantia(this.getDataVencimento(s.getGarantia()));
            itensOS.add(itemPedido);            
    }

   
    public boolean isItemEscolhido(List l, ItemPedido i){

        Boolean retorno = false;
        Iterator iterator = l.iterator();
        while (iterator.hasNext()){
            ItemPedido item = (ItemPedido) iterator.next();

            if(item.getServico().getDescricao().equalsIgnoreCase(i.getServico().getDescricao())){
                retorno = true;
            }
        }

        return retorno;
    }

   
    public boolean isItemOriginal(List l, ItemPedido i){

        Boolean retorno = false;
        Iterator iterator = l.iterator();
        while (iterator.hasNext()){
            ItemPedido item = (ItemPedido) iterator.next();

            if(item.getServico().getDescricao().equalsIgnoreCase(i.getServico().getDescricao())){
                retorno = true;
            }
        }

        return retorno;
    }


   
    public String getDataVencimento(int mesesGarantia){
        //Instancia variáveis
        Calendar calendar = Calendar.getInstance();
        //Atribui a data do dia
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),calendar.get(Calendar.DATE));
        calendar.add(Calendar.MONTH, mesesGarantia);
        return format.format(calendar.getTime());
    }

    
 

   
    public void prepararNovaOs(String codigo) {

        if(codigo != null && codigo.substring(0,1).equalsIgnoreCase("C")){

            try {

               ordemServicoPadrao = new OrdemServicoPadrao(contratoDaoInterface.buscarPorId(codigo));

               ordemServicoPadrao.setDataEmissao(new Util().getDataHoje());

               ordemServicoPadrao.setHoraEmissao(new Util().getHora());
               
                if(ordemServicoPadrao.getContrato() != null){

                    if(ordemServicoPadrao.getStatus().equalsIgnoreCase(OrdemServicoPadrao.StatusOs.Aberta.toString())){
                        int r = javax.swing.JOptionPane.showConfirmDialog(telaPrincipal,"Já existe uma OS em andamento para o Contrato informado. Deseja continuar e criar uma nova OS ?","Atenção - Aviso",1);

                        if(r==0){
                             /*Variavel necessária para atualizar os itens da tela de OS caso o usuario queira ver
                             a lista de serviços originais.*/
                            setItensOs((ArrayList) ordemServicoPadrao.getContrato().getItensContratados());

                            this.notifyObservers(ordemServicoPadrao);
                        }
                    }else{
                        setItensOs((ArrayList) ordemServicoPadrao.getContrato().getItensContratados());
                        this.notifyObservers(ordemServicoPadrao);
                    }
                }
            } catch (Exception ex) {
                javax.swing.JOptionPane.showMessageDialog(telaPrincipal,"Falha ao realizar consulta de OS. O código informado nao existe. Detalhes do Objeto: " + ex.getMessage(),"Atenção - Aviso",1);
            }
        }else{
            try {
               ordemServicoPadrao = new OrdemServicoPadrao(pedidoAction.buscarPorId(Long.parseLong(codigo)));

               ordemServicoPadrao.setDataEmissao(new Util().getDataHoje());

               ordemServicoPadrao.setHoraEmissao(new Util().getHora());

                if(ordemServicoPadrao.getPedido() != null){
                    
                    if(!ordemServicoPadrao.getPedido().getItensPedido().isEmpty()){

                        if(ordemServicoPadrao.getStatus().equalsIgnoreCase(OrdemServicoPadrao.StatusOs.Aberta.toString())){
                            int r = javax.swing.JOptionPane.showConfirmDialog(telaPrincipal,"Já existe uma OS em andamento para o Pedido informado. Deseja continuar e criar uma nova OS ?","Atenção - Aviso",1);

                            if(r==0){
                                /*Variavel necessária para atualizar os itens da tela de OS caso o usuario queira ver
                                a lista de serviços originais.*/
                                setItensOs((ArrayList) ordemServicoPadrao.getPedido().getItensPedido());

                                this.notifyObservers(ordemServicoPadrao);
                            }
                        }else{
                            setItensOs((ArrayList) ordemServicoPadrao.getPedido().getItensPedido());

                            this.notifyObservers(ordemServicoPadrao);
                        }
                    }else{
                        javax.swing.JOptionPane.showMessageDialog(telaPrincipal,"Não foi encontrada nenhuma ordem de serviço com o código informado.","Atenção - Aviso",1);
                    }
                }
            } catch (Exception ex) {
                javax.swing.JOptionPane.showMessageDialog(telaPrincipal,"Falha ao realizar consulta de OS. O código informado nao existe. Detalhes do Objeto:" + ex.getMessage(),"Atenção - Aviso",1);
            }
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

   
    public void configurarStatus() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

   
    public void validarGarantia() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

   
    public String getStatus() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

   
    public String getDataExecucao() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

   
    public Time getHoraExecucao() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

   
    public Contrato getContrato() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

   
    public void incluirServicos(OrdemServicoPadrao os, List servicos) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

   
    public void atualizarServicos(OrdemServicoPadrao os, List servicos) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

   
    public void removerServicos(OrdemServicoPadrao os, List servicos) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

   
    public Long getNextIdOs() throws Exception {
        return this.getOrdemServicoPadraoDao().getNextIdOs();
    }

   
    public boolean isOsContrato(Long idContrato) throws Exception {
        return (ordemServicoPadrao.getContrato() != null ? true : false);
    }

   
    public void removerServicoOs(int index) throws Exception {

        Double totalOs = this.getOrdemServicoPadrao().getValor();

        Double valorItem = null;       

        if(this.getOrdemServicoPadrao().getContrato()!= null){
            if(this.getOrdemServicoPadrao().getContrato().getItensContratados().size()== 1){
                javax.swing.JOptionPane.showMessageDialog(telaPrincipal,"Não é possível realizar a exclusão. É necessário que a OS contenha pelo menos 1 serviço associado.","Atenção - Aviso",2);
            }else{
                valorItem = ((ServicoContrato)this.getOrdemServicoPadrao().getContrato().getItensContratados().get(index)).getValor();
                this.getOrdemServicoPadrao().getContrato().getItensContratados().remove(index);
                this.getOrdemServicoPadrao().setValor((totalOs - valorItem));
            }
        }else if (this.getOrdemServicoPadrao().getPedido()!= null){
            if(this.getOrdemServicoPadrao().getPedido().getItensPedido().size()== 1){
                javax.swing.JOptionPane.showMessageDialog(telaPrincipal,"Não é possível realizar a exclusão. É necessário que a OS contenha pelo menos 1 serviço associado.","Atenção - Aviso",2);
            }else{
                valorItem = ((ItemPedido)this.getOrdemServicoPadrao().getPedido().getItensPedido().get(index)).getValor();
                this.getOrdemServicoPadrao().getPedido().getItensPedido().remove(index);
                this.getOrdemServicoPadrao().setValor((totalOs - valorItem));
            }
        }

        this.notifyObservers(this.getOrdemServicoPadrao());
    }

    /**
     * @return the itensOs
     */
    public ArrayList getItensOs() {
        return itensOs;
    }

    /**
     * @param itensOs the itensOs to set
     */
    public void setItensOs(ArrayList lista) {

        Iterator i = lista.iterator();
        
        this.itensOs.clear();

        while(i.hasNext()){
            this.itensOs.add(i.next());
        }
        
    }
 

   
    public List converterItensContratadosToItensOs(List<ServicoContrato> itensContratados) {

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

   
    public void aplicarStatusLost() {

        if(String.valueOf(telaPrincipal.cmbStatusOsPadrao.getSelectedItem()).equalsIgnoreCase("Aberta")){
            telaPrincipal.txtDataExecucaoOsPadrao.setEnabled(false);
            telaPrincipal.txtDataExecucaoOsPadrao.setValue(null);
            telaPrincipal.txtHoraExecucaoOsPadrao.setEnabled(false);
            telaPrincipal.txtHoraExecucaoOsPadrao.setText("");
        }else{
            telaPrincipal.txtDataExecucaoOsPadrao.setEnabled(true);
            telaPrincipal.txtHoraExecucaoOsPadrao.setEnabled(true);
        }
        
    }

   
    public void consultarOsPorCodigo(long codigoOs) throws OrdemServicoException {

            //Obtem o codigo do pedido para busca            
            try {
                this.setOrdemServicoPadrao(this.getOsPorId(codigoOs));

                if(this.getOrdemServicoPadrao() == null){
                    throw new OrdemServicoException("Atenção: Ordem de Serviço Codigo ["+ codigoOs + "] não encontrada!", null);                    
                }else{
                    this.notifyObservers(this.ordemServicoPadrao);
                }
            } catch (Exception ex) {       
                    throw new OrdemServicoException("Atenção: Falha durante a consulta da Ordem de Serviço Codigo ["+ codigoOs + "]", ex);             
            }
        
    }

   
    public List consultarOsPorPedido(Pedido pedido) throws OrdemServicoException {

        try{
              //Carrega as informaçoes basicas da ordem de serviço
            return getOrdemServicoPadraoDao().buscarListaPorPedido(pedido);
        }catch(SQLException e){
            throw new OrdemServicoException("Atenção: Falha durante a consulta da Ordem de Serviço Por Pedido Codigo ["+ pedido.getIdPedido() + "]", e);             
        }
        /*

       try {
                this.setOrdemServicoPadrao((OrdemServicoPadrao) this.getOsPorPedido(numeroPedido));

                if(this.getOrdemServicoPadrao() == null){
                    javax.swing.JOptionPane.showMessageDialog(telaPrincipal, "Ordem de serviço não encontrada!","Atenção",1);
                }else{
                    this.notifyObservers(this.ordemServicoPadrao);
                }
        } catch (SQLException ex) {
              javax.swing.JOptionPane.showMessageDialog(telaPrincipal, "Falha na consulta.O seguinte erro ocorreu: "+ ex.getMessage(),"Atenção",2);
            Logger.getLogger(FrmPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }

        /*
        if(telaPrincipal.cmbBuscaOsPadrao.getSelectedItem().equals("Número Pedido")){
            //Obtem o codigo do pedido para busca
            try {
                this.setOrdemServicoPadrao((OrdemServicoPadrao) this.getOsPorPedido(numeroPedido));

                if(this.getOrdemServicoPadrao() == null){
                    javax.swing.JOptionPane.showMessageDialog(telaPrincipal, "Ordem de serviço não encontrada!","Atenção",1);
                }else{
                    this.notifyObservers(this.ordemServicoPadrao);
                }
            } catch (SQLException ex) {
                  javax.swing.JOptionPane.showMessageDialog(telaPrincipal, "Falha na consulta.O seguinte erro ocorreu: "+ ex.getMessage(),"Atenção",2);
                Logger.getLogger(FrmPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
         * */
         
    }

    /**
     * @return the ordemServicoPadraoDao
     */
    public IOrdemServicoDAO<OrdemServicoPadrao> getOrdemServicoPadraoDao() {
        return ordemServicoPadraoDao;
    }

    /**
     * @param ordemServicoPadraoDao the ordemServicoPadraoDao to set
     */
    public void setOrdemServicoPadraoDao(IOrdemServicoDAO<OrdemServicoPadrao> ordemServicoPadraoDao) {
        this.ordemServicoPadraoDao = ordemServicoPadraoDao;
    }

}
