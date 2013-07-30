/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.syscontrol.model;


import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

/**
 *
 * @author Diego
 */
public interface OrdemServicoDaoInterface {

    public OrdemServicoPadrao inserir(OrdemServicoPadrao os) throws SQLException;

    public void incluirServicos(OrdemServicoPadrao os, List servicos) throws SQLException;

    public void atualizarServicos(OrdemServicoPadrao os, List servicos) throws SQLException;

    public void atualizarDadosServicosContrato(OrdemServicoPadrao os) throws SQLException, ParseException;

    public void removerServicos(OrdemServicoPadrao os, List servicos) throws SQLException;

    public void remover(OrdemServicoPadrao os) throws SQLException;

    public OrdemServicoPadrao alterar(OrdemServicoPadrao os) throws SQLException;

    public Long getNextIdOs() throws SQLException;

    public OrdemServicoPadrao getOsPorId(Long id) throws SQLException;

    public OrdemServicoPadrao getOsPorPedido(Long numeroPedido) throws SQLException;

    public OrdemServicoPadrao getOsPorContrato(String codigoContrato) throws SQLException;

    public List getOsPorCliente(String nomeCliente) throws SQLException;

    public List getListOsPorPedido(long numeroPedido) throws SQLException;

    public boolean isOsContrato(Long id) throws SQLException;

    public boolean isOsAberta(Contrato contrato) throws SQLException;

    public boolean isOsAberta(Pedido pedido) throws SQLException;

    public List getServicosOsContrato(Long id) throws SQLException;

}
