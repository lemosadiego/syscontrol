/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.syscontrol.controller;

import br.com.syscontrol.model.Contrato;
import br.com.syscontrol.model.ItemPedido;
import br.com.syscontrol.model.OrdemServicoPadrao;
import br.com.syscontrol.model.Servico;
import br.com.syscontrol.model.ServicoContrato;
import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;

/**
 *
 * @author Diego
 */
public interface OrdemServicoController {

    /*Metodo criado para aplicar regras de acordo com o status selecionado*/
    public void aplicarStatusLost();

    public boolean isStatusOSValido() throws ParseException;

    public boolean isDataExecucaoValida() throws ParseException;

    public void popularServicosPedido(JTable table) throws Exception;

    public void aplicarModelOrdemServico(JTable table) throws Exception;

    public void popularListaOrdensBusca(ArrayList<OrdemServicoPadrao> lista) throws Exception;

    public ItemPedido converterServicoToItemPedido(Servico servico);

    public void addItemOS(Object item, List<ItemPedido> itensOS);

    public boolean isItemEscolhido(List l, ItemPedido i);

    public boolean isItemOriginal(List l, ItemPedido i);

    public String getDataVencimento(int mesesGarantia);

    public void prepararNovaOs(String codigo);

    //Metodo responsavel por aplicar as regras de negocio para cada status
    public void configurarStatus() throws Exception;

    //Metodo responsavel por validar a garantia do Pedido ou Contrato
    public void validarGarantia() throws Exception;

    public String getStatus() throws Exception;

    public String getDataExecucao() throws Exception;

    public Time getHoraExecucao() throws Exception;

    public Contrato getContrato() throws Exception;

    /*Metodo responsavel por remover o item selecionado na tela de OS
     da lista de serviços do pedido ou do contrato para aquela OS*/
    public void removerServicoOs(int index) throws Exception;    

    public List converterItensContratadosToItensOs(List<ServicoContrato> itensContratados);

    public void inserir(OrdemServicoPadrao os) throws Exception;

    public void remover(OrdemServicoPadrao os) throws Exception;

    public OrdemServicoPadrao alterar(OrdemServicoPadrao os) throws Exception;

    public Long getNextIdOs() throws Exception;

    public OrdemServicoPadrao getOsPorId(long id) throws Exception;

    public OrdemServicoPadrao getOsPorPedido(long numeroPedido) throws Exception;

    public OrdemServicoPadrao getOsPorContrato(String codigoContrato) throws Exception;

    public boolean isOsContrato(long id) throws Exception;

    public List getServicosOsContrato(long id) throws Exception;

    public void incluirServicos(OrdemServicoPadrao os, List servicos) throws Exception;

    public void atualizarServicos(OrdemServicoPadrao os, List servicos) throws Exception;

    public void removerServicos(OrdemServicoPadrao os, List servicos) throws Exception;

    public List getOsPorCliente(String nomeCliente) throws Exception;

    public void consultarOsPorCodigo(long codigoOs) throws SQLException,ParseException;

    public List consultarOsPorPedido(long numeroPedido) throws SQLException,ParseException;



}
