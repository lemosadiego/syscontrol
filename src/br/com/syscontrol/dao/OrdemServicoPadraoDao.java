/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.syscontrol.dao;

import br.com.syscontrol.model.ClientePessoa;
import br.com.syscontrol.model.OrdemServicoPadrao;
import br.com.syscontrol.model.Pedido;
import java.sql.SQLException;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Diego
 */
public class OrdemServicoPadraoDao implements IOrdemServicoDAO<OrdemServicoPadrao>  {
    
     private static final Logger LOG = Logger.getLogger("OrdemServicoPadraoDao"); 
    private final Session session;   
    
    public OrdemServicoPadraoDao() {
        this.session = HibernateFactory.getSession();    
    }  

    @Override
    public void salvar(OrdemServicoPadrao os) throws SQLException {
        LOG.info("Salvando o  OS ID [" + os.getIdOrdemServico() + "]");
        try{
            Transaction tx = session.beginTransaction();
            session.save(os);
            tx.commit(); 
        }catch(Exception ex){
            LOG.error("Falha ao Salvar o  OS ID [" + os.getIdOrdemServico() + "]",ex);
            throw new SQLException();
        } 
    }

    @Override
    public void remover(OrdemServicoPadrao os) throws SQLException {
        LOG.info("Removendo OS ID [" + os.getIdOrdemServico() + "]");
        try{
            Transaction tx = session.beginTransaction();
            session.save(os);
            tx.commit(); 
        }catch(Exception ex){
            LOG.error("Falha ao Remover o  OS ID [" + os.getIdOrdemServico() + "]",ex);
            throw new SQLException();
        } 
    }

    @Override
    public void atualizar(OrdemServicoPadrao os) throws SQLException {
        LOG.info("Atualizando OS ID [" + os.getIdOrdemServico() + "]");
        try{
            Transaction tx = session.beginTransaction();
            session.save(os);
            tx.commit(); 
        }catch(Exception ex){
            LOG.error("Falha ao Atualizar OS ID [" + os.getIdOrdemServico() + "]",ex);
            throw new SQLException();
        } 
    }

    @Override
    public Long getNextIdOs() throws SQLException {        
        LOG.debug("Recuperando Proximo ID");
        String hql = "select max(idOrdemServico)+ 1 from OrdemServicoPadrao o";	
	Query query = session.createQuery(hql);
		
        try {
           return (Long) query.uniqueResult();
        }catch(Exception ex){
            LOG.error("Falha ao recuperar proximo ID de OS",ex);
            throw new SQLException();
        }       
    }

    @Override
    public OrdemServicoPadrao buscarPorId(Long id) throws SQLException {
        LOG.info("Iniciando busca de OS por Id [" + id + "]");
        Criteria criteria = session.createCriteria(OrdemServicoPadrao.class);
        criteria.add(Restrictions.eq("idOrdemServico", id));
        
        try{
            synchronized (this.session) {            
                List<OrdemServicoPadrao> listaObject = criteria.list();				
                if(listaObject.isEmpty()){
                        return null;
                }else{
                        return listaObject.get(0);	
                }
            }
        }catch(Exception ex){
            LOG.error("Falha ao realizar busca de OS por Id",ex);
            throw new SQLException();
        }
    }

    @Override
    public OrdemServicoPadrao buscarPorPedido(Long numeroPedido) throws SQLException {
        LOG.info("Iniciando busca de OS por Pedido [" + numeroPedido + "]");
        Criteria criteria = session.createCriteria(OrdemServicoPadrao.class);
        criteria.add(Restrictions.ilike("pedido.idPedido", numeroPedido));
        
        try{
            synchronized (this.session) {            
                List<OrdemServicoPadrao> listaObject = criteria.list();				
                if(listaObject.isEmpty()){
                        return null;
                }else{                        
                        return listaObject.get(0);	
                }
            }
        }catch(Exception ex){
            LOG.error("Falha ao realizar busca de OS por Pedido [" + numeroPedido + "]",ex);
            throw new SQLException();
        }
    }

    @Override
    public OrdemServicoPadrao buscarPorContrato(String idContrato) throws SQLException {
        LOG.info("Iniciando busca de OS por Contrato [" + idContrato + "]");
        Criteria criteria = session.createCriteria(OrdemServicoPadrao.class);
        criteria.add(Restrictions.ilike("contrato.idContrato", idContrato));
        
        try{
            synchronized (this.session) {            
                List<OrdemServicoPadrao> listaObject = criteria.list();				
                if(listaObject.isEmpty()){
                        return null;
                }else{                        
                        return listaObject.get(0);	
                }
            }
        }catch(Exception ex){
            LOG.error("Falha ao realizar busca de OS por Contrato [" + idContrato + "]",ex);
            throw new SQLException();
        }
    }

    @Override
    public List<OrdemServicoPadrao> buscarPorCliente(ClientePessoa cliente) throws SQLException {
        LOG.info("Iniciando busca de OS por Cliente ID [" + cliente.getIdCliente() + "]");
        Criteria criteria = session.createCriteria(OrdemServicoPadrao.class);
        criteria.add(Restrictions.ilike("cliente.idCliente", cliente.getIdCliente()));
        
        try{
            synchronized (this.session) {            
                List<OrdemServicoPadrao> listaObject = criteria.list();				
                if(listaObject.isEmpty()){
                        return null;
                }else{                        
                        return listaObject;	
                }
            }
        }catch(Exception ex){
            LOG.error("Falha ao realizar busca de OS por Cliente ID [" + cliente.getIdCliente() + "]",ex);
            throw new SQLException();
        }
    }

    @Override
    public List<OrdemServicoPadrao> buscarListaPorPedido(Pedido pedido) throws SQLException {
        LOG.info("Iniciando busca de OS por Pedido ID [" + pedido.getIdPedido() + "]");
        Criteria criteria = session.createCriteria(OrdemServicoPadrao.class);
        criteria.add(Restrictions.ilike("pedido.idPedido", pedido.getIdPedido())).addOrder(Order.desc("pedido.dataExecucao"));
        
        try{
            synchronized (this.session) {            
                List<OrdemServicoPadrao> listaObject = criteria.list();				
                if(listaObject.isEmpty()){
                        return null;
                }else{                        
                        return listaObject;	
                }
            }
        }catch(Exception ex){
            LOG.error("Falha ao realizar busca de OS por Pedido ID [" + pedido.getIdPedido() + "]",ex);
            throw new SQLException();
        }
    }


   

    

//    Conexao conexao = new Conexao();
//
//    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
//
//    Boolean contratoValido = false;  
//
//    public  List consultarServicosOSContrato(Long idOs) throws SQLException{
//
//        String sql = "SELECT servicos.* FROM ordemservicopadrao "+
//                "INNER JOIN servicoscontratadosospadrao ON (ordemservicopadrao.IdOrdemServico = servicoscontratadosospadrao.idOS) "+
//                "INNER JOIN servicos ON (servicoscontratadosospadrao.idServico = servicos.idServico) "+
//                "WHERE ordemservicopadrao.IdOrdemServico = ?";
//
//        ResultSet rs = null;
//
//        ArrayList itensContrato = new ArrayList();
//
//        ServicoContrato sc = null;
//
//        Servico servico = null;
//
//        Connection con = conexao.abreConexao();
//         //Indicador se existe serviço ou nao
//        Boolean flag = false;
//      
//        PreparedStatement pstmt = con.prepareStatement(sql);
//
//        try{
//
//            pstmt.setLong(1, idOs);
//
//            rs =pstmt.executeQuery();
//
//            while(rs.next()){
//                    servico = new Servico();
//                    sc = new ServicoContrato();
//                    servico.setIdServico(rs.getLong("IDSERVICO"));
//                    servico.setDescricao(rs.getString("DESCRICAO"));
//                    //servico.setValor(rs.getDouble("S.VALOR"));
//                    sc.setValor(rs.getDouble("VALOR"));
//                    servico.setGarantia(rs.getInt("GARANTIA"));
//                    sc.setServico(servico);
//                    itensContrato.add(sc);
//                    flag = true;
//             }
//        }catch(SQLException e){
//            throw new SQLException(e);
//        }finally{
//            conexao.fechaConexao(con,rs,pstmt);
//        }
//        
//         return itensContrato;
//    }
//
//    
//    public Collection getListaClientes(String nomeCliente) throws SQLException{
//
//        Statement stmt;
//
//        ResultSet rs;
//
//        ClientePessoa cliente = null;
//
//        Collection retorno = new ArrayList<Cliente>();
//
//        String sql = "SELECT P.IDPEDIDO, P.VALORPEDIDO,C.ID,C.NOME,C.ENDERECO,C.BAIRRO,C.CIDADE,C.CEP"
//                + ",C.UF,C.IDTIPOCLIENTE FROM PEDIDO P,CLIENTES C WHERE P.IDCLIENTE = C.ID AND NOME LIKE '%"+nomeCliente+"%' GROUP BY NOME ORDER BY NOME";
//
//        Connection con = conexao.abreConexao();
//
//        stmt = con.createStatement();
//
//        try{
//            rs = stmt.executeQuery(sql);
//
//            while(rs.next()){
//                cliente = new ClientePessoa();
//                cliente.setId(rs.getLong("ID"));
//                cliente.setNome(rs.getString("NOME"));
//                cliente.setEndereco(rs.getString("ENDERECO"));
//                cliente.setBairro(rs.getString("BAIRRO"));
//                cliente.setCidade(rs.getString("CIDADE"));
//                cliente.setCep(rs.getString("CEP"));
//                cliente.setUf(rs.getString("UF"));
//                cliente.setTipoCliente(rs.getInt("IDTIPOCLIENTE"));
//                retorno.add(cliente);
//            }
//        }catch(SQLException e){
//            throw new SQLException(e);
//        }finally{
//            conexao.fechaConexao(con);
//        }
//
//        return retorno;
//    }
//
//
//
//    @Override
//    public OrdemServicoPadrao inserir(OrdemServicoPadrao os) throws SQLException {
//
//       OrdemServicoPadrao osPadrao = (OrdemServicoPadrao) os;
//
//       Boolean isOsContrato = false;
//
//        String sql1 = "INSERT INTO ORDEMSERVICOPADRAO "
//                    + "VALUES("
//                    + "?,"
//                    + "?,"
//                    + "?,"
//                    + "?,"
//                    + "?,"
//                    + "?,"
//                    + "?,"
//                    + "?,"
//                    + "?,"
//                    + "?,"
//                    + "?)";
//
//
//        Connection con = conexao.abreConexao();
//
//        PreparedStatement pstmt = con.prepareStatement(sql1);
//
//        try{
//            os.setIdOrdemServico(this.getNextIdOs());
//            pstmt.setLong(1, os.getIdOrdemServico());
//            pstmt.setDate(2, java.sql.Date.valueOf(new Util().formataSqlDate(osPadrao.getDataEmissao())));
//            pstmt.setTime(3, osPadrao.getHoraEmissao());
//            pstmt.setDouble(4,osPadrao.getValor());
//
//            if(osPadrao.getPedido()!=null){
//                pstmt.setLong(5, osPadrao.getPedido().getIdPedido());
//            }else{
//                pstmt.setLong(5, 0);
//            }
//            pstmt.setString(6, osPadrao.getUsuarioLog());
//            pstmt.setString(7,osPadrao.getObservacao());
//            pstmt.setString(8, osPadrao.getStatus());
//            pstmt.setDate(9, null);
//            pstmt.setTime(10, null);
//
//            if(osPadrao.getContrato()!=null){
//                pstmt.setString(11, osPadrao.getContrato().getId());
//                contratoValido = true;
//                isOsContrato = true;
//            }else{
//                pstmt.setString(11, null);
//            }
//            pstmt.executeUpdate();
//
//            if(isOsContrato){
//                this.incluirServicos(os, os.getContrato().getItensContratados());
//            }else{
//                this.incluirServicos(os,os.getPedido().getItensPedido());
//            }
//
//        }catch(SQLException e){
//            throw new SQLException(e);
//        }finally{
//            conexao.fechaConexao(con);
//        }
//
//        return os;
//        /*if(contratoValido){
//            this.incluirServicos(osPadrao);
//        }
//         */
//    }
//
//    @Override
//    public void incluirServicos(OrdemServicoPadrao os,List servicos) throws SQLException {
//
//        String sql1 = "INSERT INTO SERVICOSCONTRATADOSOSPADRAO "
//                    + "VALUES("
//                    + "?,"
//                    + "?)";
//
//        Connection con = conexao.abreConexao();
//
//        PreparedStatement pstmt = con.prepareStatement(sql1);
//
//        Iterator i = servicos.iterator();
//
//        try{
//            while(i.hasNext()){
//                        Object o = i.next();
//                        if( o instanceof ServicoContrato){
//                             ServicoContrato item = (ServicoContrato) o;
//                            pstmt.setLong(1, os.getIdOrdemServico());
//                            pstmt.setLong(2, item.getServico().getIdServico());
//                            pstmt.executeUpdate();
//                        }else if(o instanceof ItemPedido){
//                            ItemPedido item = (ItemPedido) o;
//                            pstmt.setLong(1, os.getIdOrdemServico());
//                            pstmt.setLong(2, item.getServico().getIdServico());                            
//                            pstmt.executeUpdate();
//                        }
//            }
//        }catch(SQLException e){
//            throw new SQLException(e);
//        }finally{
//            conexao.fechaConexao(con,pstmt);
//        }
//    }
//
//    @Override
//    public void atualizarServicos(OrdemServicoPadrao os,List servicos) throws SQLException {
//        try{
//            this.removerServicos(os,servicos);
//            this.incluirServicos(os,servicos);
//         }catch(SQLException e){
//            throw new SQLException(e);
//        }
//    }
//
//    @Override
//    public void removerServicos(OrdemServicoPadrao os,List servicos) throws SQLException {
//        
//        String sql1 = "DELETE FROM SERVICOSCONTRATADOSOSPADRAO "
//                    + "WHERE IDOS = ?";
//
//        Connection con = conexao.abreConexao();
//
//        PreparedStatement pstmt = con.prepareStatement(sql1);
//
//        try{
//            pstmt.setLong(1, os.getIdOrdemServico());
//            pstmt.executeUpdate();
//        }catch(SQLException e){
//            throw new SQLException(e);
//        }finally{
//            conexao.fechaConexao(con,pstmt);
//        }
//    }
//
//    @Override
//    public void remover(OrdemServicoPadrao os) throws SQLException {
//
//        String sqlOs = "DELETE FROM ORDEMSERVICOPADRAO WHERE IDORDEMSERVICO = ?";       
//
//        Connection con = conexao.abreConexao();
//
//        PreparedStatement pstmt = con.prepareStatement(sqlOs);
//
//        try{
//            pstmt.setLong(1, os.getIdOrdemServico());
//            pstmt.executeUpdate();            
//        }catch(SQLException e){
//            throw new SQLException(e);
//        }finally{
//            conexao.fechaConexao(con,pstmt);
//        }       
//    }
//
//    @Override
//    public OrdemServicoPadrao alterar(OrdemServicoPadrao os) throws SQLException{
//        
//        String sql1 = "UPDATE ORDEMSERVICOPADRAO SET "
//                + "OBSERVACAO = ?,"
//                + "STATUS=?,"
//                + "USUARIOLOG=?, "
//                + "DATAEXECUCAO = ?,"
//                + "HORAEXECUCAO = ? "
//                + "WHERE IDORDEMSERVICO = ?";
//
//        Connection con = conexao.abreConexao();
//
//        PreparedStatement pstmt = con.prepareStatement(sql1);
//
//        try{
//            pstmt.setString(1,os.getObservacao());
//            pstmt.setString(2, os.getStatus());
//            pstmt.setString(3, os.getUsuarioLog());
//
//            if(os.getDataExecucao()!= null){
//                if(!os.getDataExecucao().equals("")){
//                     pstmt.setDate(4, java.sql.Date.valueOf(new Util().formataSqlDate(os.getDataExecucao())));
//                }
//            }else{
//                    pstmt.setDate(4, null);
//            }
//
//            if(os.getHoraExecucao() != null){
//                pstmt.setTime(5, os.getHoraExecucao());
//            }else{
//                pstmt.setTime(5, null);
//            }
//            pstmt.setLong(6, os.getIdOrdemServico());
//            pstmt.executeUpdate();
//                        
//        }catch(SQLException e){
//            throw new SQLException(e);
//        }finally{
//            conexao.fechaConexao(con,pstmt);
//        }
//
//        return os;
//    }
//
//    @Override
//    public Long getNextIdOs() throws SQLException {
//        
//        ResultSet rs = null;
//
//        String sql = "SELECT MAX(IDORDEMSERVICO)+1 AS IDORDEMSERVICO FROM ORDEMSERVICOPADRAO";
//
//        Connection con = conexao.abreConexao();
//
//        Statement stmt = con.createStatement();
//
//        Long retorno = null;
//
//        try{
//            rs = stmt.executeQuery(sql);
//
//            if(rs.next()){
//                retorno = rs.getLong("IDORDEMSERVICO");
//            }
//        }catch(SQLException e){
//            throw new SQLException(e);
//        }finally{
//            conexao.fechaConexao(con,stmt);
//        }
//        return retorno;
//    }
//
//    @Override
//    public OrdemServicoPadrao getOsPorId(Long id) throws SQLException {
//        
//        ResultSet rs = null;
//
//        OrdemServicoPadrao osConsulta = null;
//
//        String sql = "SELECT * FROM ORDEMSERVICOPADRAO OS WHERE OS.IDORDEMSERVICO = ?";
//
//        Connection con = conexao.abreConexao();
//
//        PreparedStatement pstmt = con.prepareStatement(sql);
//
//        try{
//              pstmt.setLong(1, id);
//
//              rs = pstmt.executeQuery();
//
//              if(rs.next()){
//
//                  
//                  if(rs.getLong("IDPEDIDO") != 0){
//                      osConsulta = new OrdemServicoPadrao((Pedido)new PedidoDao().getPedidoPorId(rs.getLong("IDPEDIDO")));
//                  }
//
//                  
//                  if(rs.getString("IDCONTRATO") != null){
//                      osConsulta = new OrdemServicoPadrao(new ContratoDao().getContratoPorId(rs.getString("IDCONTRATO")));
//                  }
//
//                  if(osConsulta!=null){
//                      List l = this.consultarServicosOSContrato(id);
//                      //osConsulta.getContrato().setItensContratados(l);
//                      osConsulta.setIdOrdemServico(id);
//                      osConsulta.setDataEmissao(new Util().formataSqlDateToText(rs.getDate("DATAEMISSAO").toString()));
//                      osConsulta.setHoraEmissao(rs.getTime("HORAEMISSAO"));
//                      osConsulta.setValor(rs.getDouble("VALOR"));
//                      osConsulta.setUsuarioLog(rs.getString("USUARIOLOG"));
//                      osConsulta.setObservacao(rs.getString("OBSERVACAO"));
//                      osConsulta.setStatus(rs.getString("STATUS"));
//
//                      if(rs.getDate("DATAEXECUCAO") == null){
//                          osConsulta.setDataExecucao("");
//                      }else{
//                        osConsulta.setDataExecucao(new Util().formataSqlDateToText(rs.getDate("DATAEXECUCAO").toString()));
//                      }
//
//                      if(rs.getTime("HORAEXECUCAO") == null){
//                          osConsulta.setHoraExecucao(null);
//                      }else{
//                        osConsulta.setHoraExecucao(rs.getTime("HORAEXECUCAO"));
//                      }
//                      return osConsulta;
//                  }else{
//                      return null;
//                  }
//              }
//        }catch(SQLException e){
//            throw new SQLException(e);
//        }finally{
//            conexao.fechaConexao(con,rs,pstmt);
//        }
//
//        return osConsulta;
//    }
//
//    @Override
//    public OrdemServicoPadrao getOsPorPedido(Long numeroPedido) throws SQLException {
//
//        ResultSet rs = null;
//
//        OrdemServicoPadrao osConsulta = null;
//
//        String sql = "SELECT ordemservicopadrao.* FROM pedidos "+
//                       "INNER JOIN ordemservicopadrao " +
//                       "ON (pedidos.IdPedido = ordemservicopadrao.IdPedido) " +
//                       "WHERE PEDIDOS.IDPEDIDO = ?";
//
//        Connection con = conexao.abreConexao();
//        
//        PreparedStatement pstmt = con.prepareStatement(sql);
//
//        try{
//          pstmt.setLong(1, numeroPedido);
//
//          rs = pstmt.executeQuery();
//
//          if(rs.next()){
//              osConsulta = new OrdemServicoPadrao((Pedido) new PedidoDao().getPedidoPorId(numeroPedido));
//              osConsulta.setIdOrdemServico(rs.getLong("IDORDEMSERVICO"));
//              osConsulta.setDataEmissao(new Util().formataSqlDateToText(rs.getDate("DATAEMISSAO").toString()));
//              osConsulta.setHoraEmissao(rs.getTime("HORAEMISSAO"));
//              osConsulta.setValor(rs.getDouble("VALOR"));
//              osConsulta.setUsuarioLog(rs.getString("USUARIOLOG"));
//              osConsulta.setObservacao(rs.getString("OBSERVACAO"));
//              osConsulta.setStatus(rs.getString("STATUS"));
//              
//             if(rs.getDate("DATAEXECUCAO") == null){
//                  osConsulta.setDataExecucao("");
//              }else{
//                osConsulta.setDataExecucao(new Util().formataSqlDateToText(rs.getDate("DATAEXECUCAO").toString()));
//              }
//
//              if(rs.getTime("HORAEXECUCAO") == null){
//                  osConsulta.setHoraExecucao(null);
//              }else{
//                osConsulta.setHoraExecucao(rs.getTime("HORAEXECUCAO"));
//              }
//          }
//        }catch(SQLException e){
//            throw new SQLException(e);
//       }finally{
//            conexao.fechaConexao(con,rs,pstmt);
//       }         
//          return osConsulta;
//    }
//
//    @Override
//    public OrdemServicoPadrao getOsPorContrato(String codigoContrato) throws SQLException {
//
//      ResultSet rs = null;
//
//      OrdemServicoPadrao osConsulta = null;
//
//      ContratoDao contratoDao = new ContratoDao();
//
//      String sql = "SELECT ordemservicopadrao.* FROM contratos "+
//                   "INNER JOIN ordemservicopadrao " +
//                   "ON (contratos.IdContrato = ordemservicopadrao.IdContrato) " +
//                   "WHERE CONTRATOS.IDCONTRATO = ? AND ordemservicopadrao.STATUS = 'ABERTA'";
//
//      Connection con = conexao.abreConexao();
//
//      PreparedStatement pstmt = con.prepareStatement(sql);
//
//      try{
//          pstmt.setString(1, codigoContrato);
//
//          rs = pstmt.executeQuery();
//
//          if(rs.next()){
//              osConsulta = new OrdemServicoPadrao((Contrato) contratoDao.getContratoPorId(codigoContrato));
//              osConsulta.setIdOrdemServico(rs.getLong("IDORDEMSERVICO"));
//              osConsulta.setDataEmissao(new Util().formataSqlDateToText(rs.getDate("DATAEMISSAO").toString()));
//              osConsulta.setHoraEmissao(rs.getTime("HORAEMISSAO"));
//              osConsulta.setValor(rs.getDouble("VALOR"));
//              osConsulta.setUsuarioLog(rs.getString("USUARIOLOG"));
//              osConsulta.setObservacao(rs.getString("OBSERVACAO"));
//              osConsulta.setStatus(rs.getString("STATUS"));
//
//             if(rs.getDate("DATAEXECUCAO") == null){
//                  osConsulta.setDataExecucao("");
//              }else{
//                osConsulta.setDataExecucao(new Util().formataSqlDateToText(rs.getDate("DATAEXECUCAO").toString()));
//              }
//
//              if(rs.getTime("HORAEXECUCAO") == null){
//                  osConsulta.setHoraExecucao(null);
//              }else{
//                osConsulta.setHoraExecucao(rs.getTime("HORAEXECUCAO"));
//              }
//
//              List servicos = this.consultarServicosOSContrato(osConsulta.getIdOrdemServico());
//                if(!servicos.isEmpty()){
//                    osConsulta.getContrato().setItensContratados(servicos);
//                }
//          }          
//      }catch(SQLException e){
//          throw new SQLException(e);
//      }finally{
//          conexao.fechaConexao(con,rs,pstmt);
//      }         
//          return osConsulta;
//    }
//
//    @Override
//    public boolean isOsContrato(Long id) throws SQLException {
//
//        String sql = "SELECT servicos.* FROM ordemservicopadrao "+
//                "INNER JOIN servicoscontratadosospadrao ON (ordemservicopadrao.IdOrdemServico = servicoscontratadosospadrao.idOS) "+
//                "INNER JOIN servicos ON (servicoscontratadosospadrao.idServico = servicos.idServico) "+
//                "WHERE ordemservicopadrao.IdOrdemServico = ?";
//
//        ResultSet rs = null;
//
//        Connection con = conexao.abreConexao();
//
//        PreparedStatement pstmt = con.prepareStatement(sql);
//
//        try{
//            pstmt.setLong(1, id);
//
//            rs =pstmt.executeQuery();
//
//            if(rs.next()){
//                 return true;
//            }
//        }catch(SQLException e){
//            throw new SQLException(e);
//        }finally{
//            conexao.fechaConexao(con);
//        }         
//         return false;
//    }
//
//
//
//    @Override
//    public List getOsPorCliente(String nomeCliente) throws SQLException {
//
//         ResultSet rs = null;
//
//         OrdemServicoPadrao osConsulta = null;
//                  
//         ArrayList<OrdemServicoPadrao> retorno = new ArrayList<OrdemServicoPadrao>();
//
//         String sql = "SELECT DISTINCT(OS.IDORDEMSERVICO),OS.* FROM"+
//         " ORDEMSERVICOPADRAO OS,PEDIDOS P,CLIENTES C, CONTRATOS CO"+
//         " WHERE (OS.IDCONTRATO = CO.IDCONTRATO AND CO.IDCLIENTE = C.ID AND C.NOME LIKE '%"+nomeCliente+"%') OR"+
//         " (OS.IDPEDIDO = P.IDPEDIDO AND P.IDCLIENTE = C.ID AND C.NOME LIKE '%"+nomeCliente+"%')" +
//          " ORDER BY" +
//          " C.NOME, OS.DATAEMISSAO";
//
//         Connection con = conexao.abreConexao();
//         
//         Statement stmt = con.createStatement();
//
//        try {
//
//          rs = stmt.executeQuery(sql);
//            
//          while(rs.next()){
//
//              if(rs.getLong("IDPEDIDO")!= 0){
//                  osConsulta = new OrdemServicoPadrao((Pedido)new PedidoDao().getPedidoPorId(rs.getLong("IDPEDIDO")));
//              }
//
//              if(rs.getString("IDCONTRATO")!= null){
//                  if(!rs.getString("IDCONTRATO").equals("")){
//                          osConsulta = new OrdemServicoPadrao(new ContratoDao().getContratoPorId(rs.getString("IDCONTRATO")));
//                  }
//              }
//              
//              
//              
//              osConsulta.setIdOrdemServico(rs.getLong("IDORDEMSERVICO"));
//              osConsulta.setDataEmissao(new Util().formataSqlDateToText(rs.getDate("DATAEMISSAO").toString()));
//              osConsulta.setHoraEmissao(rs.getTime("HORAEMISSAO"));
//              osConsulta.setValor(rs.getDouble("VALOR"));
//              osConsulta.setUsuarioLog(rs.getString("USUARIOLOG"));
//              osConsulta.setObservacao(rs.getString("OBSERVACAO"));
//              osConsulta.setStatus(rs.getString("STATUS"));
//
//              if(rs.getDate("DATAEXECUCAO") == null){
//                  osConsulta.setDataExecucao("");
//              }else{
//                osConsulta.setDataExecucao(new Util().formataSqlDateToText(rs.getDate("DATAEXECUCAO").toString()));
//              }
//
//              if(rs.getTime("HORAEXECUCAO") == null){
//                  osConsulta.setHoraExecucao(null);
//              }else{
//                osConsulta.setHoraExecucao(rs.getTime("HORAEXECUCAO"));
//              }        
//
//              retorno.add(osConsulta);
//          }
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//            throw new SQLException(ex);
//        }finally{
//            conexao.fechaConexao(con,rs,stmt);
//        }       
//        return retorno;
//    }
//
//    @Override
//    public List getListOsPorPedido(long numeroPedido) throws SQLException {
//
//         ResultSet rs = null;
//
//         OrdemServicoPadrao osConsulta = null;
//
//         ArrayList<OrdemServicoPadrao> retorno = new ArrayList<OrdemServicoPadrao>();
//
//         String sql = "SELECT ordemservicopadrao.* "+
//                      "FROM clientes "+
//                      "INNER JOIN pedidos ON (clientes.id = pedidos.IdCliente) "+
//                      "INNER JOIN ordemservicopadrao ON (pedidos.IdPedido = ordemservicopadrao.IdPedido) "
//                      + "WHERE pedidos.idpedido = ? ORDER BY NOME";
//
//         Connection con = conexao.abreConexao();
//
//         PreparedStatement pstmt = con.prepareStatement(sql);
//
//        try {
//
//          pstmt.setLong(1, numeroPedido);
//          rs = pstmt.executeQuery();
//
//          while(rs.next()){
//
//              if(rs.getLong("IDPEDIDO")!= 0){
//                  osConsulta = new OrdemServicoPadrao((Pedido)new PedidoDao().getPedidoPorId(rs.getLong("IDPEDIDO")));
//              }
//
//              if(rs.getString("IDCONTRATO")!= null || !rs.getString("IDCONTRATO").equals("")){
//                  osConsulta = new OrdemServicoPadrao(new ContratoDao().getContratoPorId(rs.getString("IDCONTRATO")));
//              }
//
//              osConsulta.setIdOrdemServico(rs.getLong("IDORDEMSERVICO"));
//              osConsulta.setDataEmissao(new Util().formataSqlDateToText(rs.getDate("DATAEMISSAO").toString()));
//              osConsulta.setHoraEmissao(rs.getTime("HORAEMISSAO"));
//              osConsulta.setValor(rs.getDouble("VALOR"));
//              osConsulta.setUsuarioLog(rs.getString("USUARIOLOG"));
//              osConsulta.setObservacao(rs.getString("OBSERVACAO"));
//              osConsulta.setStatus(rs.getString("STATUS"));
//
//              if(rs.getDate("DATAEXECUCAO") == null){
//                  osConsulta.setDataExecucao("");
//              }else{
//                osConsulta.setDataExecucao(new Util().formataSqlDateToText(rs.getDate("DATAEXECUCAO").toString()));
//              }
//
//              if(rs.getTime("HORAEXECUCAO") == null){
//                  osConsulta.setHoraExecucao(null);
//              }else{
//                osConsulta.setHoraExecucao(rs.getTime("HORAEXECUCAO"));
//              }
//
//              retorno.add(osConsulta);
//          }
//        } catch (SQLException ex) {
//            throw new SQLException(ex);
//        }finally{
//            conexao.fechaConexao(con,rs,pstmt);
//        }
//        return retorno;
//    }
//
//    @Override
//    public List getServicosOsContrato(Long id) throws SQLException {
//        ResultSet rs = null;
//
//         OrdemServicoPadrao osConsulta = null;
//
//         ArrayList<OrdemServicoPadrao> retorno = new ArrayList<OrdemServicoPadrao>();
//
//         String sql = "SELECT ordemservicopadrao.* "+
//                      "FROM clientes "+
//                      "INNER JOIN contratos ON (clientes.id = contratos.IdCliente) "+
//                      "INNER JOIN ordemservicopadrao ON (contrator.IdContrato = ordemservicopadrao.IdContrato) "
//                      + "WHERE contratos.idcontrato = ? ORDER BY NOME";
//
//         Connection con = conexao.abreConexao();
//
//         PreparedStatement pstmt = con.prepareStatement(sql);
//
//        try {
//
//          pstmt.setLong(1, id);
//          rs = pstmt.executeQuery();
//
//          while(rs.next()){
//
//              if(rs.getLong("IDPEDIDO")!= 0){
//                  osConsulta = new OrdemServicoPadrao((Pedido)new PedidoDao().getPedidoPorId(rs.getLong("IDPEDIDO")));
//              }
//
//              if(rs.getString("IDCONTRATO")!= null || !rs.getString("IDCONTRATO").equals("")){
//                  osConsulta = new OrdemServicoPadrao(new ContratoDao().getContratoPorId(rs.getString("IDCONTRATO")));
//              }
//
//              osConsulta.setIdOrdemServico(rs.getLong("IDORDEMSERVICO"));
//              osConsulta.setDataEmissao(new Util().formataSqlDateToText(rs.getDate("DATAEMISSAO").toString()));
//              osConsulta.setHoraEmissao(rs.getTime("HORAEMISSAO"));
//              osConsulta.setValor(rs.getDouble("VALOR"));
//              osConsulta.setUsuarioLog(rs.getString("USUARIOLOG"));
//              osConsulta.setObservacao(rs.getString("OBSERVACAO"));
//              osConsulta.setStatus(rs.getString("STATUS"));
//
//              if(rs.getDate("DATAEXECUCAO") == null){
//                  osConsulta.setDataExecucao("");
//              }else{
//                osConsulta.setDataExecucao(new Util().formataSqlDateToText(rs.getDate("DATAEXECUCAO").toString()));
//              }
//
//              if(rs.getTime("HORAEXECUCAO") == null){
//                  osConsulta.setHoraExecucao(null);
//              }else{
//                osConsulta.setHoraExecucao(rs.getTime("HORAEXECUCAO"));
//              }
//
//              retorno.add(osConsulta);
//          }
//        } catch (SQLException ex) {
//            throw new SQLException(ex);
//        }finally{
//            conexao.fechaConexao(con,rs,pstmt);
//        }
//        return retorno;
//    }
//
//    @Override
//    public boolean isOsAberta(Contrato contrato) throws SQLException {
//
//        String sql = "SELECT 1 FROM ORDEMSERVICOPADRAO WHERE IDCONTRATO = ? AND STATUS = 'ABERTA'";
//
//        ResultSet rs = null;
//
//        Connection con = conexao.abreConexao();
//
//        PreparedStatement pstmt = con.prepareStatement(sql);
//
//        try{
//            pstmt.setString(1, contrato.getId());
//            rs = pstmt.executeQuery();
//
//            if(rs.next()){
//                return true;
//            }else{
//                return false;
//            }
//
//        }catch(SQLException e){
//            throw new SQLException(e);
//        }finally{
//          conexao.fechaConexao(con,rs,pstmt);
//        }
//
//    }
//
//    @Override
//    public boolean isOsAberta(Pedido pedido) throws SQLException {
//        String sql = "SELECT 1 FROM ORDEMSERVICOPADRAO WHERE IDPEDIDO = ? AND STATUS = 'ABERTA'";
//
//        ResultSet rs = null;
//
//        Connection con = conexao.abreConexao();
//
//        PreparedStatement pstmt = con.prepareStatement(sql);
//
//        try{
//            pstmt.setLong(1, pedido.getIdPedido());
//            rs = pstmt.executeQuery();
//
//            if(rs.next()){
//                return true;
//            }else{
//                return false;
//            }
//
//        }catch(SQLException e){
//            throw new SQLException(e);
//        }finally{
//          conexao.fechaConexao(con,rs,pstmt);
//        }
//    }
//
//    @Override
//    public void atualizarDadosServicosContrato(OrdemServicoPadrao os) throws SQLException, ParseException {
//
//        String sql = "UPDATE ITENSCONTRATO SET DATAULTIMAEXECUCAO = ?, DATAPROXIMAEXECUCAO = ?"
//                + " WHERE IDCONTRATO = ?";
//
//        Connection con = conexao.abreConexao();
//
//        PreparedStatement pstmt = con.prepareStatement(sql);
//
//        
//        try{
//            Calendar calendar = Calendar.getInstance();
//
//            calendar.setTime(format.parse(os.getDataExecucao()));
//
//            Iterator i = os.getContrato().getItensContratados().iterator();
//
//            while(i.hasNext()){
//                ServicoContrato s = (ServicoContrato) i.next();
//                calendar.add(Calendar.MONTH, s.getServico().getGarantia());
//
//                pstmt.setDate(1, java.sql.Date.valueOf(new Util().formataSqlDate(os.getDataExecucao())));
//                pstmt.setDate(2, java.sql.Date.valueOf(new Util().formataSqlDate(format.format(calendar.getTime()).toString())));
//                pstmt.setString(3, os.getContrato().getId());
//                pstmt.executeUpdate();
//            }
//        }catch(SQLException e){
//            throw new SQLException(e);
//        }catch(ParseException pe){
//            throw new ParseException(pe.getMessage(), pe.getErrorOffset());
//        } finally {
//          conexao.fechaConexao(con,pstmt);
//        }
//    }

}
