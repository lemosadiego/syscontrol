/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.syscontrol.dao;

import br.com.syscontrol.model.ClientePessoa;

import br.com.syscontrol.model.Pedido;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
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
public class PedidoDao implements IPedidoDAO<Pedido> {

    private static final Logger LOG = Logger.getLogger("PedidoDao"); 
    private final Session session;   
    
    public PedidoDao() {
        this.session = HibernateFactory.getSession();    
    } 
    
    @Override
    public Long getNextId() throws SQLException {
        LOG.debug("Recuperando Proximo ID");
        String hql = "select max(idPedido)+ 1 from Pedido";	
	Query query = session.createQuery(hql);
		
        try {
           return (Long) query.uniqueResult();
        }catch(Exception ex){
            LOG.error("Falha ao recuperar proximo ID de OS",ex);
            throw new SQLException();
        } 
    }

    @Override
    public void salvar(Pedido pedido) throws SQLException, ParseException {
       LOG.info("Salvando o  Pedido ID [" + pedido.getIdPedido() + "]");
        try{
            Transaction tx2 = session.beginTransaction();
                session.save(pedido);
            tx2.commit(); 
            
        }catch(Exception ex){
            LOG.error("Falha ao Salvar o  Pedido ID [" + pedido.getIdPedido() + "]",ex);
            throw new SQLException();
        } 
    }

    @Override
    public void remover(Pedido pedido) throws SQLException {
        LOG.info("Removendo Pedido ID [" + pedido.getIdPedido() + "]");
        try{
            Transaction tx = session.beginTransaction();      
            session.delete(pedido);
            tx.commit(); 
        }catch(Exception ex){
            LOG.error("Falha ao Remover o  Pedido ID [" + pedido.getIdPedido() + "]",ex);
            throw new SQLException();
        }
    }

    @Override
    public void atualizar(Pedido pedido) throws SQLException, ParseException {
        LOG.info("Atualizando Pedido ID [" + pedido.getIdPedido() + "]");
        try{
            Transaction tx2 = session.beginTransaction();  
             session.update(pedido);             
            tx2.commit(); 
        }catch(Exception ex){
            LOG.error("Falha ao Atualizar Pedido ID [" + pedido.getIdPedido() + "]",ex);
            throw new SQLException();
        }
    }

    @Override
    public List<Pedido> buscarPedidosPorCliente(ClientePessoa cliente) throws SQLException {
        LOG.info("Iniciando busca de Pedido por Cliente Id [" + cliente.getIdCliente() + "]");
        Criteria criteria = session.createCriteria(Pedido.class);
        criteria.add(Restrictions.eq("cliente.idCliente", cliente.getIdCliente())).addOrder(Order.desc("dataExecucao"));
        
        try{
            synchronized (this.session) {            
                List<Pedido> listaObject = criteria.list();				
                if(listaObject.isEmpty()){
                        return null;
                }else{
                        return listaObject;	
                }
            }
        }catch(Exception ex){
            LOG.error("Falha ao realizar busca de Pedido por Cliente Id [" + cliente.getIdCliente() + "]",ex);
            throw new SQLException();
        }
    }
    
    
    @Override
    public List<Pedido> buscarPedidosPorNomeCliente(String nome) throws SQLException {
        LOG.info("Iniciando busca de Pedido por Nome Cliente [" + nome + "]");
        Query query = session.createQuery("select p from Pedido p where p.cliente.nome like '%" + nome + "%'");
        
        try{
            synchronized (this.session) {            
                List<Pedido> listaObject = query.list();				
                if(listaObject.isEmpty()){
                        return new ArrayList<Pedido>();
                }else{
                        return listaObject;	
                }
            }
        }catch(Exception ex){
            LOG.error("Falha ao realizar busca de Pedido por Nome Cliente [" + nome + "]",ex);
            throw new SQLException();
        }
    }

    @Override
    public Pedido buscarPorId(Long idPedido) throws SQLException {
        LOG.info("Iniciando busca de Pedido por Id [" + idPedido + "]");
        Criteria criteria = session.createCriteria(Pedido.class);
        criteria.add(Restrictions.eq("idPedido", idPedido));
        
        try{
            synchronized (this.session) {            
                List<Pedido> listaObject = criteria.list();				
                if(listaObject.isEmpty()){
                        return null;
                }else{
                        return listaObject.get(0);	
                }
            }
        }catch(Exception ex){
            LOG.error("Falha ao realizar busca de Pedido por Id",ex);
            throw new SQLException();
        }
    }

//    Conexao conexao = new Conexao();
//    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
//
//    
//    //Retorna o ultimo pedido cadastrado para auxiliar no metodo incluirItensPedido
//    @Override
//    public long getNextNumeroPedido() throws SQLException{
//
//         String sql = "SELECT MAX(IDPEDIDO)+1 FROM PEDIDOS";
//         ResultSet rs = null;
//         Long retorno = null;
//
//        Connection con = conexao.abreConexao();
//        try {
//            Statement stmt = con.createStatement();
//            rs =stmt.executeQuery(sql);
//
//            if(rs.next()){
//                retorno =  rs.getLong(1);
//            }else{
//                retorno = null;
//            }
//        } catch (SQLException ex) {
//            throw new SQLException(ex);
//        }
//        conexao.fechaConexao(con);
//        return retorno;
//    }
//
//    //Grava informaçoes na tabela de relacionamento muitos para muitos , PEDIDOSSERVICOS
//    @Override
//    public void incluirItensPedido(Pedido pedido) throws SQLException,ParseException{
//
//        //Instancia variáveis
//            Calendar dataExecucao = Calendar.getInstance();
// 
//       String sql = "INSERT INTO PEDIDOSSERVICOS VALUES("
//                + "?,"
//                + "?,"
//                + "?,"
//                + "?)";
//
//        Connection conPedido = conexao.abreConexao();
//        PreparedStatement pstmt = conPedido.prepareStatement(sql);
//
//        try {
//            for(Object o : pedido.getItensPedido()){
//
//                    Servico s = ((ItemPedido) o).getServico();
//
//                    pstmt.setLong(1, pedido.getIdPedido());
//                    pstmt.setLong(2, s.getIdServico());
//                    pstmt.setDouble(3, ((ItemPedido) o).getValor());
//                    if(pedido.getStatus().equalsIgnoreCase("Fechado")){
//                        dataExecucao.setTime(format.parse(pedido.getDataExecucao()));
//                        dataExecucao.add(Calendar.MONTH, s.getGarantia());
//                        pstmt.setDate(4, java.sql.Date.valueOf(new Util().formataSqlDate(format.format(dataExecucao.getTime()))));
//                    }else{
//                        pstmt.setDate(4, null);
//                    }
//                    pstmt.executeUpdate();
//            }
//        }catch(SQLException ex) {
//            throw new SQLException(ex);
//        }finally{
//            conexao.fechaConexao(conPedido,pstmt);
//        }
//    }
//
//    
//    @Override
//    public void inserir(Pedido pedido) throws SQLException, ParseException {
//         
//       String sql1 = "INSERT INTO PEDIDOS "
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
//                    + "?,"
//                    + "?,"
//                    + "?,"
//                    + "?,"
//                    + "?,"
//                    + "?)";
//
//        Connection con = conexao.abreConexao();
//
//        PreparedStatement pstmt = con.prepareStatement(sql1);
//
//        try{            
//            
//            pedido.setIdPedido(this.getNextNumeroPedido());    
//
//            pstmt.setLong(1, pedido.getIdPedido());
//            pstmt.setLong(2, pedido.getCliente().getId());
//            pstmt.setString(3, pedido.getEndereco());
//            pstmt.setString(4, pedido.getBairro());
//            pstmt.setString(5,pedido.getCidade());
//            pstmt.setString(6, pedido.getUf());
//            pstmt.setString(7, pedido.getNomeContato());
//            pstmt.setString(8, pedido.getTelefoneContato());
//            pstmt.setDate(9, java.sql.Date.valueOf(new Util().formataSqlDate(pedido.getDataCadastro())));
//            
//            if(pedido.getDataAgendamento() == null){
//              pstmt.setDate(10, null);
//            }else{
//                pstmt.setDate(10, java.sql.Date.valueOf(new Util().formataSqlDate(pedido.getDataAgendamento())));
//            }
//
//            if(pedido.getHoraAgendamento() == null){
//              pstmt.setDate(11, null);
//            }else{
//                pstmt.setTime(11, pedido.getHoraAgendamento());
//            }
//
//            if(pedido.getDataExecucao() == null){
//              pstmt.setDate(12, null);
//            }else{
//                pstmt.setDate(12, java.sql.Date.valueOf(new Util().formataSqlDate(pedido.getDataExecucao())));
//            }
//
//            if(pedido.getHoraExecucao() == null){
//              pstmt.setDate(13, null);
//            }else{
//                pstmt.setTime(13, pedido.getHoraExecucao());
//            }
//            
//            pstmt.setString(14,pedido.getObservacao());
//            pstmt.setDouble(15,pedido.getValorPedido());
//            pstmt.setString(16,pedido.getStatus());
//            pstmt.executeUpdate();
//
//            this.incluirItensPedido(pedido);            
//        }catch(SQLException e){
//            throw new SQLException(e);
//        }finally{
//            conexao.fechaConexao(con,pstmt);
//        }
//     
//    }
//
//    @Override
//    public void remover(Pedido pedido) throws SQLException {
//
//         String sqlPagamentoPedido = "DELETE FROM PAGAMENTOPEDIDO WHERE IDPEDIDO = ?";
//
//         String sqlPedidosServicos = "DELETE FROM PEDIDOSSERVICOS WHERE IDPEDIDO = ?";
//
//         String sqlPedido = "DELETE FROM PEDIDOS WHERE IDPEDIDO = ?";
//         
//         Connection con = conexao.abreConexao();
//
//        PreparedStatement pstmt1 = con.prepareStatement(sqlPagamentoPedido);
//
//        PreparedStatement pstmt2 = con.prepareStatement(sqlPedidosServicos);
//        
//        PreparedStatement pstmt3 = con.prepareStatement(sqlPedido);
//
//        try {
//            
//            pstmt1.setLong(1, pedido.getIdPedido());
//            pstmt2.setLong(1, pedido.getIdPedido());
//            pstmt3.setLong(1, pedido.getIdPedido());
//
//            pstmt1.executeUpdate();
//            pstmt2.executeUpdate();
//            pstmt3.executeUpdate();
//
//        } catch (SQLException ex) {
//            throw new SQLException(ex);
//        }finally{
//            conexao.fechaConexao(con,pstmt1,pstmt2,pstmt3);
//        }        
//    }
//
//
//    public void atualizarStatus(Pedido pedido) throws SQLException, ParseException {
//
//         String sql = "UPDATE PEDIDOS SET STATUS=? "
//                 + "WHERE IDPEDIDO = ?";
//         
//         Connection con = conexao.abreConexao();
//
//         PreparedStatement pstmt = con.prepareStatement(sql);
//
//         try{
//             pstmt.setString(1,pedido.getStatus());
//             pstmt.setLong(2, pedido.getIdPedido());
//             pstmt.executeUpdate();
//             this.incluirItensPedido(pedido);
//         }catch(SQLException ex){
//             throw new SQLException(ex);
//         }finally{
//             conexao.fechaConexao(con,pstmt);
//         }
//         
//    }
//
//    @Override
//    public void alterar(Pedido pedido) throws SQLException, ParseException {
//
//        String horaExecucao = String.valueOf(pedido.getHoraExecucao());
//
//        Date dataExecucao = null;
//
//        if(horaExecucao == null){
//            horaExecucao = "";
//        }
//
//        if(pedido.getDataExecucao()!= null && !pedido.getDataExecucao().equals("")){
//            dataExecucao = Date.valueOf(new Util().formataSqlDate(pedido.getDataExecucao()));
//        }
//
//
//        String sql = "UPDATE PEDIDOS SET "
//                 + "IDCLIENTE=?,"
//                 + "ENDERECO=?,"
//                 + "BAIRRO=?,"
//                 + "CIDADE=?,"
//                 + "UF=?,"
//                 + "NOMECONTATO=?,"
//                 + "TELEFONECONTATO=?,"
//                 + "DATACADASTRO=?,"
//                 + "DATAAGENDAMENTO=?,"
//                 + "HORAAGENDAMENTO=?,"
//                 + "DATAEXECUCAO=?,"
//                 + "HORAEXECUCAO=?,"
//                 + "OBSERVACAO=?,"
//                 + "VALORPEDIDO=?,"
//                 + "STATUS=?"
//                 + " WHERE IDPEDIDO=?";
//         
//
//         Connection con = conexao.abreConexao();        
//
//         PreparedStatement pstmt = con.prepareStatement(sql);
//
//         try{
//                pstmt.setLong(1, pedido.getCliente().getId());
//                pstmt.setString(2, pedido.getEndereco());
//                pstmt.setString(3, pedido.getBairro());
//                pstmt.setString(4,pedido.getCidade());
//                pstmt.setString(5, pedido.getUf());
//                pstmt.setString(6, pedido.getNomeContato());
//                pstmt.setString(7, pedido.getTelefoneContato());
//                pstmt.setDate(8, java.sql.Date.valueOf(new Util().formataSqlDate(pedido.getDataCadastro())));
//
//                if(pedido.getDataAgendamento() == null){
//                  pstmt.setDate(9, null);
//                }else{
//                    pstmt.setDate(9, java.sql.Date.valueOf(new Util().formataSqlDate(pedido.getDataAgendamento())));
//                }
//
//                if(pedido.getHoraAgendamento() == null){
//                  pstmt.setTime(10, null);
//                }else{
//                    pstmt.setTime(10, pedido.getHoraAgendamento());
//                }
//
//                
//
//                if(pedido.getDataExecucao() == null || pedido.getDataExecucao().equals("")){
//                  pstmt.setDate(11, null);
//                }else{
//                    pstmt.setDate(11, java.sql.Date.valueOf(new Util().formataSqlDate(pedido.getDataExecucao())));
//                }
//
//                if(pedido.getHoraExecucao() == null){
//                  pstmt.setTime(12, null);
//                }else{
//                    pstmt.setTime(12, pedido.getHoraExecucao());
//                }
//
//                pstmt.setString(13,pedido.getObservacao());
//                pstmt.setDouble(14,pedido.getValorPedido());
//                pstmt.setString(15,pedido.getStatus());
//                pstmt.setLong(16, pedido.getIdPedido());
//
//                pstmt.executeUpdate();
//
//                this.incluirItensPedido(pedido);
//         }catch(SQLException ex){
//             throw new SQLException(ex);
//         }finally{
//             conexao.fechaConexao(con,pstmt);
//         }
//    }
//
//    @Override
//    public List getPedidoPorNomeCliente(String nome) throws SQLException{
//
//         ArrayList<Pedido> lista = new ArrayList<Pedido>();
//         
//         String sql = "SELECT * FROM PEDIDOS P,CLIENTES C WHERE P.IDCLIENTE = C.ID AND NOME LIKE '%"+nome+"%' ORDER BY NOME";
//
//         ResultSet rs = null;
//
//         Pedido pedidoConsulta = null;
//
//         ClientePessoaDao clienteDao = new ClientePessoaDao();
//
//         PagamentoPedidoDao pagamentoDao = new PagamentoPedidoDao();
//
//         ClientePessoa cliente = new ClientePessoa();
//
//        Connection con = conexao.abreConexao();
//        try {
//            Statement stmt = con.createStatement();
//            rs = stmt.executeQuery(sql);
//
//            while(rs.next()){
//                pedidoConsulta = new Pedido();
//                pedidoConsulta.setIdPedido(rs.getLong("P.IDPEDIDO"));
//                cliente = clienteDao.getClientePessoaPorCodigo((int) rs.getLong("P.IDCLIENTE"));
//                pedidoConsulta.setCliente(cliente);
//                if(cliente.getEndereco()!= null)
//                pedidoConsulta.setEndereco(cliente.getEndereco());
//
//                if(cliente.getBairro()!= null)
//                pedidoConsulta.setBairro(cliente.getBairro());
//
//                if(cliente.getCidade()!= null)
//                pedidoConsulta.setCidade(cliente.getCidade());
//
//                if(cliente.getUf()!= null)
//                pedidoConsulta.setUf(cliente.getUf());
//
//                if(cliente.getNomeContato()!= null)
//                pedidoConsulta.setNomeContato(cliente.getNomeContato());
//
//                if(cliente.getTelefoneContato()!= null)
//                pedidoConsulta.setTelefoneContato(cliente.getTelefoneContato());
//
//                pedidoConsulta.setDataCadastro(new Util().formataSqlDateToText(rs.getDate("P.DATACADASTRO").toString()));
//                pedidoConsulta.setDataAgendamento(new Util().formataSqlDateToText(rs.getDate("P.DATAAGENDAMENTO").toString()));
//
//                if(rs.getTime("P.HORAAGENDAMENTO")!= null){
//                    pedidoConsulta.setHoraAgendamento(rs.getTime("P.HORAAGENDAMENTO"));
//                }
//
//                if(rs.getDate("P.DATAEXECUCAO")!= null){
//                    pedidoConsulta.setDataExecucao(new Util().formataSqlDateToText(rs.getDate("P.DATAEXECUCAO").toString()));
//                }
//                
//
//                if(rs.getTime("P.HORAEXECUCAO")!= null){
//                    pedidoConsulta.setHoraExecucao(rs.getTime("P.HORAEXECUCAO"));
//                }
//
//                if(rs.getString("P.OBSERVACAO")!= null)
//                pedidoConsulta.setObservacao(rs.getString("P.OBSERVACAO"));
//
//                pedidoConsulta.setValorPedido(rs.getDouble("P.VALORPEDIDO"));
//                pedidoConsulta.setStatus(rs.getString("P.STATUS"));
//                pagamentoDao.consultarPorId(pedidoConsulta);
//                this.getServicosPedido(pedidoConsulta);
//
//                lista.add(pedidoConsulta);
//                
//            }
//        } catch (SQLException ex) {
//            throw new SQLException(ex);
//        }finally{
//            conexao.fechaConexao(con);
//        }
//        
//        
//        return lista;
//    }
//
//    //Consulta Informaçoes Basicas do pedido por Id
//
//    @Override
//    public Pedido getPedidoPorId(Long id) throws SQLException{
//        
//         String sql = "SELECT * FROM PEDIDOS WHERE IDPEDIDO = ?";
//         ResultSet rs = null;
//         Pedido pedidoConsulta = null;
//         ClientePessoa cliente = new ClientePessoa();
//         ClientePessoaDao clienteDao = new ClientePessoaDao();
//
//        Connection con = conexao.abreConexao();
//
//        PreparedStatement pstmt = con.prepareStatement(sql);
//        
//        try {
//            
//            pstmt.setLong(1, id);
//
//            rs =pstmt.executeQuery();
//
//            if(rs.next()){
//                pedidoConsulta = new Pedido();
//                pedidoConsulta.setIdPedido(id);
//
//                cliente = clienteDao.getClientePessoaPorCodigo((int) rs.getLong("IDCLIENTE"));
//                pedidoConsulta.setCliente(cliente);
//                if(rs.getString("ENDERECO")!= null)
//                pedidoConsulta.setEndereco(rs.getString("ENDERECO"));
//
//                if(rs.getString("BAIRRO")!= null)
//                pedidoConsulta.setBairro(rs.getString("BAIRRO"));
//
//                if(rs.getString("CIDADE")!= null)
//                pedidoConsulta.setCidade(rs.getString("CIDADE"));
//
//                if(rs.getString("UF")!= null)
//                pedidoConsulta.setUf(rs.getString("UF"));
//
//                if(rs.getString("NOMECONTATO")!= null)
//                pedidoConsulta.setNomeContato(rs.getString("NOMECONTATO"));
//
//                if(rs.getString("TELEFONECONTATO")!= null)
//                pedidoConsulta.setTelefoneContato(rs.getString("TELEFONECONTATO"));
//
//                pedidoConsulta.setDataCadastro(new Util().formataSqlDateToText(rs.getDate("DATACADASTRO").toString()));
//                pedidoConsulta.setDataAgendamento(new Util().formataSqlDateToText(rs.getDate("DATAAGENDAMENTO").toString()));
//
//                if(rs.getTime("HORAAGENDAMENTO")!= null){
//                    pedidoConsulta.setHoraAgendamento(rs.getTime("HORAAGENDAMENTO"));
//                }
//
//                if(rs.getDate("DATAEXECUCAO")==null){
//                    pedidoConsulta.setDataExecucao("");
//                }else{
//                    pedidoConsulta.setDataExecucao(new Util().formataSqlDateToText(rs.getDate("DATAEXECUCAO").toString()));
//                }
//                
//
//                if(rs.getTime("HORAEXECUCAO")!= null){
//                    pedidoConsulta.setHoraExecucao(rs.getTime("HORAEXECUCAO"));
//                }
//
//                if(rs.getString("OBSERVACAO")!= null)
//                pedidoConsulta.setObservacao(rs.getString("OBSERVACAO"));
//
//                pedidoConsulta.setValorPedido(rs.getDouble("VALORPEDIDO"));
//                pedidoConsulta.setStatus(rs.getString("STATUS"));
//
//                pedidoConsulta = getServicosPedido(pedidoConsulta);
//            }
//        } catch (SQLException ex) {
//            throw new SQLException(ex);
//        }finally{
//            conexao.fechaConexao(con,rs,pstmt);
//        }        
//
//        return pedidoConsulta;
//    }
//
//
//
//    @Override
//    public List getPedidosPorCliente(String nomeCliente) throws SQLException{
//        Statement stmt;
//        ResultSet rs;
//        ClientePessoa cliente = null;
//        List retorno = new ArrayList<Cliente>();
//        String sql = "SELECT P.IDPEDIDO, P.VALORPEDIDO,C.ID,C.NOME,C.ENDERECO,C.BAIRRO,C.CIDADE,C.CEP"
//                + ",C.UF,C.IDTIPOCLIENTE FROM PEDIDOS P,CLIENTES C WHERE P.IDCLIENTE = C.ID AND NOME LIKE '%"+nomeCliente+"%' GROUP BY NOME ORDER BY NOME";
//        Connection con = conexao.abreConexao();
//        stmt = con.createStatement();
//        rs = stmt.executeQuery(sql);
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
//            conexao.fechaConexao(con);
//
//        return retorno;
//    }
//
//    public Pedido getServicosPedido(Pedido pedido) throws SQLException{
//
//         String sql = "SELECT * FROM PEDIDOSSERVICOS WHERE IDPEDIDO = ?";
//
//         ResultSet rs = null;        
//
//         Servico servico = null;
//
//         ItemPedido itemPedido = null;
//
//         List itensPedido = new ArrayList();
//
//         Connection con = conexao.abreConexao();
//         
//         PreparedStatement pstmt = con.prepareStatement(sql);
//
//        try {
//
//            pstmt.setLong(1, pedido.getIdPedido());
//
//            rs =pstmt.executeQuery();
//
//            while(rs.next()){
//                itemPedido = new ItemPedido();
//                servico = new Servico();
//                servico.setIdServico(rs.getLong("IDSERVICO"));
//                servico.setDescricao(this.getNomeServicoPedido(servico.getIdServico()));
//                servico.setGarantia(this.getGarantiaServicoPedido(servico.getIdServico()));
//                itemPedido.setServico(servico);
//                itemPedido.setPedido(pedido);
//                itemPedido.setValor(rs.getDouble("VALOR"));
//                if(rs.getDate("DATAVENCIMENTOGARANTIA")!=null){
//                    itemPedido.setDataVencimentoGarantia(new Util().formataSqlDateToText(rs.getDate("DATAVENCIMENTOGARANTIA").toString()));
//                }
//                
//                itensPedido.add(itemPedido);                
//            }
//            pedido.setItensPedido(itensPedido);
//        } catch (SQLException ex) {
//            throw new SQLException(ex);
//        }finally{
//            conexao.fechaConexao(con,rs,pstmt);
//        }
//        
//        return pedido;
//    }
//
//    @Override
//    public String getNomeServicoPedido(Long id) throws SQLException{
//
//         String sql = "SELECT DESCRICAO FROM SERVICOS WHERE IDSERVICO = ?";
//
//         ResultSet rs = null;
//
//         String retorno = null;
//
//         Connection con = conexao.abreConexao();
//
//         PreparedStatement pstmt = con.prepareStatement(sql);
//         
//        try {
//            
//            pstmt.setLong(1, id);
//
//            rs =pstmt.executeQuery();
//
//            if(rs.next()){
//               retorno = rs.getString("DESCRICAO");
//            }
//
//        } catch (SQLException ex) {
//            throw new SQLException(ex);
//        }finally{
//            conexao.fechaConexao(con,rs,pstmt);
//        }
//        
//        return retorno;
//    }
//
//    @Override
//    public int getGarantiaServicoPedido(Long id) throws SQLException{
//
//         String sql = "SELECT GARANTIA FROM SERVICOS WHERE IDSERVICO = ?";
//
//         ResultSet rs = null;
//
//         Integer retorno = null;
//
//         Connection con = conexao.abreConexao();
//
//         PreparedStatement pstmt = con.prepareStatement(sql);
//
//        try {
//
//            pstmt.setLong(1, id);
//
//            rs =pstmt.executeQuery();
//
//            if(rs.next()){
//               retorno = rs.getInt("GARANTIA");
//            }
//
//        } catch (SQLException ex) {
//            throw new SQLException(ex);
//        }finally{
//            conexao.fechaConexao(con,rs,pstmt);
//        }
//
//        return retorno;
//    }
//
//    @Override
//    public void removerItensPedido(Long idPedido) throws SQLException{
//
//        String sql = "DELETE FROM PEDIDOSSERVICOS WHERE IDPEDIDO = ?";
//
//        Connection con = conexao.abreConexao();
//        
//        PreparedStatement pstmt = con.prepareStatement(sql);
//
//        try {            
//            pstmt.setInt(1, idPedido.intValue());
//            pstmt.executeUpdate();
//        } catch (SQLException ex) {
//            throw new SQLException(ex);
//        }finally{
//            conexao.fechaConexao(con);
//        }
//        
//        
//    }
//
//    @Override
//    public void removerFormaPagamentoPedido(Long idPedido) throws SQLException{
//
//        String sql = "DELETE FROM PAGAMENTOPEDIDO WHERE IDPEDIDO = ?";
//
//        Connection con = conexao.abreConexao();
//
//        PreparedStatement pstmt = con.prepareStatement(sql);
//
//        try {            
//            pstmt.setLong(1, idPedido);
//            pstmt.executeUpdate();
//        } catch (SQLException ex) {
//            throw new SQLException(ex);
//        }finally{
//            conexao.fechaConexao(con,pstmt);
//        }
//    }
//
//    @Override
//    public void atualizarStatusPedido(Pedido pedido) throws SQLException {
//        throw new UnsupportedOperationException("Not supported yet.");
//    }
}
