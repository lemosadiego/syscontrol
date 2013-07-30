/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.syscontrol.controller;

import br.com.syscontrol.dao.ServicoDao;
import br.com.syscontrol.exception.ServicoException;
import br.com.syscontrol.dao.IServicoDAO;
import br.com.syscontrol.model.Servico;
import br.com.syscontrol.model.ServicoDisponivelTableModel;
import java.util.List;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import org.apache.log4j.Logger;

/**
 *
 * @author Diego
 * Ultima atualização : 01/10/2011
 * Classe controladora do objeto de negocio Servico
 */
public class ServicoAction {

    private static final Logger LOG = Logger.getLogger("ServicoAction"); 
    IServicoDAO<Servico> dao = new ServicoDao();
    private ServicoDisponivelTableModel buscaServicoDisponivelTableModel = new ServicoDisponivelTableModel();

    
    public void popularListaServicos(JTable table) throws Exception {
                 //Monta o grid contendo os dados do serviço
            DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
            renderer.setHorizontalAlignment(SwingConstants.LEFT);

            ServicoAction servicoAction = new ServicoAction();
            table.setModel(servicoAction.getBuscaServicoDisponivelTableModel());
            ((ServicoDisponivelTableModel)table.getModel()).setLinhas((List<Servico>) servicoAction.buscarTodos());
            table.getColumnModel().getColumn(0).setCellRenderer(renderer);
            table.getColumnModel().getColumn(1).setCellRenderer(renderer);
            table.getColumnModel().getColumn(2).setCellRenderer(renderer);
            table.getColumnModel().getColumn(3).setCellRenderer(renderer);
            table.getColumnModel().getColumn(0).setMaxWidth(100);
            table.getColumnModel().getColumn(1).setMaxWidth(250);
            table.getColumnModel().getColumn(2).setMaxWidth(100);
            table.getColumnModel().getColumn(3).setMaxWidth(150);
    }

     public ServicoDisponivelTableModel getBuscaServicoDisponivelTableModel() {
        return buscaServicoDisponivelTableModel;
    }

    /**
     * @param usuarioTableModel the usuarioTableModel to set
     */
    public void setServicoDisponivelTableModel(ServicoDisponivelTableModel buscaServicoDisponivelTableModel) {
        this.buscaServicoDisponivelTableModel = buscaServicoDisponivelTableModel;
    }


    public ServicoDisponivelTableModel getServicoDisponivelTableModel() {
        return this.buscaServicoDisponivelTableModel;
    }

    
    public void salvar(Servico o) throws ServicoException {
        try{
            dao.salvar(o);
        }catch(Exception e){
            LOG.error("Atenção: Falha durante a inclusão do Serviço [" + o.getDescricao() + "]", e);                   
            throw new ServicoException("Atenção: Falha durante a inclusao do Usuario [" + o.getDescricao() + "]");
        }
    }

    
    public void remover(Servico o) throws ServicoException {
         try{
            dao.remover(o);
        }catch(Exception e){
            LOG.error("Atenção: Falha durante a remoção do Servico [" + o.getDescricao() + "]", e);                   
            throw new ServicoException("Atenção: Falha durante a remoção do Servico [" + o.getDescricao() + "]");
        }
    }

    
    public void atualizar(Servico o) throws ServicoException {
         try{
            dao.atualizar(o);
        }catch(Exception e){
            LOG.error("Atenção: Falha durante a atualização do Servico [" + o.getDescricao() + "]", e);                   
            throw new ServicoException("Atenção: Falha durante a atualização do Servico [" + o.getDescricao() + "]");
        }
    }

    
    public List<Servico> buscarPorNome(String nome) throws ServicoException {
         try{
            return dao.buscarPorNome(nome);
        }catch(Exception e){
            LOG.error("Atenção: Falha durante a busca do Servico por Nome [" + nome + "]", e);                   
            throw new ServicoException("Atenção: Falha durante a busca do Servico por Nome [" + nome + "]");
        }
    }

    
    public Servico buscarPorId(Long id) throws ServicoException {
        try{
            return dao.buscarPorId(id);
        }catch(Exception e){
            LOG.error("Atenção: Falha durante a busca do Servico por ID [" + id + "]", e);                   
            throw new ServicoException("Atenção: Falha durante a busca do Servico por ID [" + id + "]");
        }
    }

    
    public List<Servico> buscarTodos() throws Exception {
        try{
            return dao.buscarTodos();
        }catch(Exception e){
            LOG.error("Atenção: Falha durante a busca de todos os Servicos", e);                   
            throw new ServicoException("Atenção: Falha durante a busca de todos os Servicos");
        }
    }

    
    public void setTableModel(AbstractTableModel abstractTableModel) throws Exception {
        try{
            buscaServicoDisponivelTableModel = (ServicoDisponivelTableModel) abstractTableModel;
        }catch(Exception e){
            throw new Exception(e);
        }
    }

    
    public AbstractTableModel getTableModel() throws Exception {
        try{
            return buscaServicoDisponivelTableModel;
        }catch(Exception e){
            throw new Exception(e);
        }
    }

    
    public boolean isServicoExistente(Servico servico) throws Exception {
       try{
            return dao.isServicoExistente(servico);
        }catch(Exception e){
            LOG.error("Atenção: Falha durante a verificação de Serviço Existente para o Serviço ID [" + servico.getIdServico() + "]", e);                   
            throw new ServicoException("Atenção: Falha durante a verificação de Serviço Existente para o Serviço ID [" + servico.getIdServico() + "]");
        }
    }
}
