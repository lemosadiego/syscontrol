/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.syscontrol.dao;

import br.com.syscontrol.model.Contrato;
import java.sql.SQLException;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Diego
 */
public class ContratoDao implements IContratoDAO<Contrato> {

    private static final Logger LOG = Logger.getLogger("ContratoDao"); 
    private final Session session;   
    
    public ContratoDao() {
        this.session = HibernateFactory.getSession();    
    }  
    
    @Override
    public void salvar(Contrato contrato) throws SQLException {
        LOG.info("Salvando o  Contrato ID [" + contrato.getIdContrato() + "]");
        try{
            Transaction tx = session.beginTransaction();
            session.save(contrato);
            tx.commit(); 
        }catch(Exception ex){
            LOG.error("Falha ao Salvar o  Contrato ID [" + contrato.getIdContrato() + "]",ex);
            throw new SQLException();
        } 
    }

    @Override
    public void remover(Contrato contrato) throws SQLException {
        LOG.info("Removendo o  Contrato ID [" + contrato.getIdContrato() + "]");
        try{
            Transaction tx = session.beginTransaction();
            session.delete(contrato);
            tx.commit(); 
        }catch(Exception ex){
            LOG.error("Falha ao Remover o  Contrato ID [" + contrato.getIdContrato() + "]",ex);
            throw new SQLException();
        }
    }

    @Override
    public void atualizar(Contrato contrato) throws SQLException {
       LOG.info("Atualizando o  Contrato ID [" + contrato.getIdContrato() + "]");
        try{
            Transaction tx = session.beginTransaction();
            session.update(contrato);
            tx.commit(); 
        }catch(Exception ex){
            LOG.error("Falha ao atualizar o  Contrato ID [" + contrato.getIdContrato() + "]",ex);
            throw new SQLException();
        }
    }

    @Override
    public List<Contrato> buscarPorNomeCliente(String nome) throws SQLException {
        LOG.info("Iniciando busca de Contrato por Nome de Cliente [" + nome + "]");
        Criteria criteria = session.createCriteria(Contrato.class);
        criteria.add(Restrictions.ilike("cliente.nome", nome)).addOrder(Order.asc("cliente.nome"));
        
        try{
            synchronized (this.session) {            
                List<Contrato> listaContratos = criteria.list();				
                if(listaContratos.isEmpty()){
                        return null;
                }else{                        
                        return listaContratos;	
                }
            }
        }catch(Exception ex){
            LOG.error("Falha ao realizar consulta de Cliente por Nome",ex);
            throw new SQLException();
        }
    }

    @Override
    public Contrato buscarPorId(String codigo) throws SQLException {
        LOG.info("Iniciando busca de Contrato por ID  [" + codigo + "]");
        Criteria criteria = session.createCriteria(Contrato.class);
        criteria.add(Restrictions.ilike("idContrato", codigo)).addOrder(Order.asc("cliente.nome"));
        
        try{
            synchronized (this.session) {            
                List<Contrato> listaContratos = criteria.list();				
                if(listaContratos.isEmpty()){
                        return null;
                }else{                        
                        return listaContratos.get(0);	
                }
            }
        }catch(Exception ex){
            LOG.error("Falha ao realizar consulta de Cliente por Nome",ex);
            throw new SQLException();
        }
    }

//    Conexao conexao = new Conexao();
//    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
//
//
//    //Grava informaçoes na tabela de relacionamento muitos para muitos , PEDIDOSSERVICOS
//    @Override
//    public void incluirItensContrato(Contrato contrato) throws SQLException{
//
//        //Instancia variáveis
//            Calendar calendar = Calendar.getInstance();
//            //Atribui a data do dia
//            calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),calendar.get(Calendar.DATE));
//
//        String sql = "INSERT INTO ITENSCONTRATO VALUES("
//                + "?,"
//                + "?,"
//                + "?,"
//                + "?,"
//                + "?)";
//
//        Connection conContrato = conexao.abreConexao();
//
//        PreparedStatement pstmt;
//
//        for(Object o : contrato.getItensContratados()){
//            try {
//                ServicoContrato sc = (ServicoContrato) o;
//                pstmt = conContrato.prepareStatement(sql);
//                pstmt.setString(1, contrato.getIdContrato());
//                pstmt.setLong(2, sc.getServico().getIdServico());
//                pstmt.setDouble(3, sc.getValor());
//
//                if(sc.getDataUltimaExecucao()== null){
//                    pstmt.setDate(4, null);
//                }else{
//                    pstmt.setDate(4, java.sql.Date.valueOf(new Util().formataSqlDate(sc.getDataUltimaExecucao())));
//                }
//
//                if(sc.getDataProximaExecucao()== null){
//                    pstmt.setDate(5, null);
//                }else{
//                    pstmt.setDate(5, java.sql.Date.valueOf(new Util().formataSqlDate(sc.getDataProximaExecucao())));
//                }
//                
//                pstmt.executeUpdate();
//            } catch (SQLException ex) {
//                throw new SQLException(ex);
//            }
//        }
//        conexao.fechaConexao(conContrato);
//    }
//
//
//    @Override
//    public void inserir(Contrato contrato) throws SQLException {
//
//             String sql1 = "INSERT INTO CONTRATOS "
//                    + "VALUES("
//                    + "?,"
//                    + "?,"
//                    + "?,"
//                    + "?,"
//                    + "?,"
//                    + "?,"
//                    + "?)";
//
//             Connection con = conexao.abreConexao();
//
//             PreparedStatement pstmt = con.prepareStatement(sql1);
//
//        try{
//            pstmt.setString(1, contrato.getIdContrato());
//            pstmt.setLong(2, contrato.getCliente().getIdContrato());
//            pstmt.setString(3, contrato.getObservacao());
//            pstmt.setDouble(4, contrato.getTotal());
//            pstmt.setInt(5,contrato.getPeriodo());
//            pstmt.setDate(6, java.sql.Date.valueOf(new Util().formataSqlDate(contrato.getDataInicioContrato())));
//            pstmt.setDate(7, java.sql.Date.valueOf(new Util().formataSqlDate(contrato.getDataExpiracao())));
//            pstmt.executeUpdate();
//            this.incluirItensContrato(contrato);            
//        }catch(SQLException e){
//            throw new SQLException(e);
//        }finally{
//            conexao.fechaConexao(con,pstmt);
//        }
//
//    }
//
//    @Override
//    public void remover(Contrato contrato) throws SQLException {
//
//         String sqlItensContrato = "DELETE FROM ITENSCONTRATO WHERE IDCONTRATO = ?";
//
//         String sqlContrato = "DELETE FROM CONTRATOS WHERE IDCONTRATO = ?";
//
//         Connection con = conexao.abreConexao();
//
//         PreparedStatement pstmt1 = con.prepareStatement(sqlItensContrato);
//
//         PreparedStatement pstmt2 = con.prepareStatement(sqlContrato);
//
//        try {            
//            pstmt1.setString(1, contrato.getIdContrato());
//            pstmt2.setString(1, contrato.getIdContrato());
//            pstmt1.executeUpdate();
//            pstmt2.executeUpdate();
//        } catch (SQLException ex) {
//            throw new SQLException(ex);
//        }finally{
//            conexao.fechaConexao(con,pstmt1,pstmt2);
//        }
//        
//    }
//
//
//    @Override
//    public void removerItensContrato(Contrato contrato) throws SQLException {
//
//         String sqlItensContrato = "DELETE FROM ITENSCONTRATO WHERE IDCONTRATO = ?";
//
//         Connection con = conexao.abreConexao();
//
//         PreparedStatement pstmt1 = con.prepareStatement(sqlItensContrato);
//
//        try {            
//            pstmt1.setString(1, contrato.getIdContrato());
//            pstmt1.executeUpdate();
//        } catch (SQLException ex) {
//            throw new SQLException(ex);
//        }finally{
//            conexao.fechaConexao(con,pstmt1);
//        }
//        
//    }
//
//
//
//    @Override
//    public void alterar(Contrato contrato) throws SQLException {
//
//         String sql = "UPDATE CONTRATOS SET "
//                 + "OBSERVACAO=?,"
//                 + "TOTAL=?,"
//                 + "PERIODO=?,"
//                 + "DATAINICIOCONTRATO=?,"
//                 + "DATAEXPIRACAO=? "
//                 + "WHERE IDCONTRATO = ?";
//                 
//        Connection con = conexao.abreConexao();
//
//        PreparedStatement pstmt = con.prepareStatement(sql);
//
//        try{
//            pstmt.setString(1, contrato.getObservacao());
//            pstmt.setDouble(2, contrato.getTotal());
//            pstmt.setInt(3, contrato.getPeriodo());
//            pstmt.setDate(4, java.sql.Date.valueOf(new Util().formataSqlDate(contrato.getDataInicioContrato())));
//            pstmt.setDate(5,java.sql.Date.valueOf(new Util().formataSqlDate(contrato.getDataExpiracao())));
//            pstmt.setString(6, contrato.getIdContrato());
//            pstmt.executeUpdate();
//
//            this.removerItensContrato(contrato);
//            this.incluirItensContrato(contrato);
//        }catch(SQLException ex){
//            throw new SQLException(ex);
//        }finally{
//            conexao.fechaConexao(con,pstmt);
//        }       
//    }
//
//    /*Consulta contrato pelo nome do cliente*/
//    @Override
//    public List getContratoPorNomeCliente(String nome) throws SQLException {
//        
//         ArrayList<Contrato> lista = new ArrayList<Contrato>();
//
//         String sql = "SELECT C.* FROM CONTRATOS C, CLIENTES CC "+
//                      "WHERE C.IDCLIENTE = CC.ID AND CC.NOME LIKE '%"+nome+"%' ORDER BY CC.NOME";
//
//         ResultSet rs = null;
//
//         Contrato contratoConsulta = null;
//
//         Connection con = conexao.abreConexao();
//
//         Statement stmt = con.createStatement();
//         
//        try {
//            
//            rs = stmt.executeQuery(sql);
//            while(rs.next()){
//                contratoConsulta = new Contrato();
//                contratoConsulta.setIdContrato(rs.getString("IDCONTRATO"));
//                contratoConsulta.setCliente(new ClientePessoaDao().getClientePessoaPorCodigo((int) rs.getLong("IDCLIENTE")));
//               
//                contratoConsulta.setDataInicioContrato(new Util().formataSqlDateToText(rs.getDate("DATAINICIOCONTRATO").toString()));
//                contratoConsulta.setDataExpiracao(new Util().formataSqlDateToText(rs.getDate("DATAEXPIRACAO").toString()));
//
//                if(rs.getString("OBSERVACAO")!= null)
//                contratoConsulta.setObservacao(rs.getString("OBSERVACAO"));
//
//                contratoConsulta.setTotal(rs.getDouble("TOTAL"));
//                contratoConsulta.setPeriodo(rs.getInt("PERIODO"));
//                this.getServicosContratados(contratoConsulta);
//
//                lista.add(contratoConsulta);
//            }
//        } catch (SQLException ex) {
//            throw new SQLException(ex);
//        }finally{
//            conexao.fechaConexao(con,rs,stmt);
//        }
//        
//
//        return lista;
//    }
//
//    //Consulta Informaçoes do contrato por Id
//    @Override
//    public Contrato getContratoPorId(String  codigo) throws SQLException {
//
//        String sql = "SELECT * FROM CONTRATOS WHERE IDCONTRATO = ?";
//        
//        ResultSet rs = null;
//
//        Contrato contratoConsulta = null;
//
//        ClientePessoaDao clienteDao = new ClientePessoaDao();
//
//        Cliente cliente = new Cliente();
//
//        Connection con = conexao.abreConexao();
//
//        PreparedStatement pstmt = con.prepareStatement(sql);
//        
//        try {
//            
//            pstmt.setString(1, codigo);
//
//            rs =pstmt.executeQuery();
//
//            if(rs.next()){
//                contratoConsulta = new Contrato();
//                contratoConsulta.setIdContrato(codigo);
//                cliente = clienteDao.getClientePessoaPorCodigo((int)rs.getLong("IDCLIENTE"));
//                contratoConsulta.setCliente(cliente);
//
//                contratoConsulta.setDataExpiracao(new Util().formataSqlDateToText(rs.getDate("DATAEXPIRACAO").toString()));
//                contratoConsulta.setDataInicioContrato(new Util().formataSqlDateToText(rs.getDate("DATAINICIOCONTRATO").toString()));
//                contratoConsulta.setTotal(rs.getDouble("TOTAL"));
//                contratoConsulta.setPeriodo(rs.getInt("PERIODO"));
//                contratoConsulta.setObservacao(rs.getString("OBSERVACAO"));
//
//                contratoConsulta = (Contrato) this.getServicosContratados(contratoConsulta);
//            }
//        } catch (SQLException ex) {
//            throw new SQLException(ex);
//        }finally{
//            conexao.fechaConexao(con,rs,pstmt);
//        }
//        
//        return contratoConsulta;     
//    }
//    
//    
//
//    @Override
//    public Contrato getServicosContratados(Contrato contrato) throws SQLException{
//
//         String sql = "SELECT "+
//                          "s.idServico,"+
//                          "s.descricao,"+                          
//                          "s.garantia,"+
//                          "contratos.idContrato,"+
//                          "contratos.idCliente,"+
//                          "contratos.observacao,"+
//                          "contratos.total,"+
//                          "contratos.periodo,"+
//                          "contratos.dataInicioContrato,"+
//                          "contratos.dataExpiracao,"+
//                          "i.valor," +
//                          "i.dataUltimaExecucao," +
//                          "i.dataProximaExecucao " +
//                        "FROM "+
//                          "itenscontrato i "+
//                          "INNER JOIN contratos ON (i.idContrato = contratos.idContrato) " +
//                          "INNER JOIN servicos s ON (i.idServico = s.idServico) " +
//                          "WHERE contratos.idContrato = ?";
//
//         ResultSet rs = null;
//
//         ArrayList itensContrato = new ArrayList();
//
//         ServicoContrato sc = null;
//
//         Servico servico = null;
//
//         Connection con = conexao.abreConexao();
//
//         PreparedStatement pstmt = con.prepareStatement(sql);
//         //Indicador se existe serviço ou nao
//         Boolean flag = false;
//
//        try {
//         
//         pstmt.setString(1, contrato.getIdContrato());
//
//         rs =pstmt.executeQuery();
//
//         while(rs.next()){
//                servico = new Servico();
//                sc = new ServicoContrato();
//                servico.setIdServico(rs.getLong("IDSERVICO"));
//                servico.setDescricao(rs.getString("DESCRICAO"));                
//                //servico.setValor(rs.getDouble("S.VALOR"));
//                sc.setValor(rs.getDouble("VALOR"));
//
//                if(rs.getDate("DATAPROXIMAEXECUCAO")!= null){
//                    sc.setDataProximaExecucao(new Util().formataSqlDateToText(rs.getDate("DATAPROXIMAEXECUCAO").toString()));
//                }
//
//                if(rs.getDate("DATAULTIMAEXECUCAO") != null){
//                    sc.setDataUltimaExecucao(new Util().formataSqlDateToText(rs.getDate("DATAULTIMAEXECUCAO").toString()));
//                }
//
//                servico.setGarantia(rs.getInt("GARANTIA"));
//                sc.setServico(servico);
//                itensContrato.add(sc);
//                contrato.setIdContrato(rs.getString("IDCONTRATO"));                
//                contrato.setObservacao(rs.getString("OBSERVACAO"));
//                contrato.setPeriodo(rs.getInt("PERIODO"));
//                contrato.setTotal(rs.getDouble("TOTAL"));
//                contrato.setDataInicioContrato(new Util().formataSqlDateToText(rs.getDate("DATAINICIOCONTRATO").toString()));
//                contrato.setDataExpiracao(new Util().formataSqlDateToText(rs.getDate("DATAEXPIRACAO").toString()));
//                flag = true;
//         }
//
//         if(flag){
//                contrato.setItensContratados(itensContrato);
//         }         
//            
//        } catch (SQLException ex) {
//            throw new SQLException(ex);
//        }finally{
//            conexao.fechaConexao(con,rs,pstmt);
//        }
//        
//        return contrato;
//    }   
//    
//
//    @Override
//    public void removerServicosContratados(String idContrato) throws SQLException{
//
//        String sql = "DELETE FROM PEDIDOSSERVICOS WHERE IDCONTRATO = ?";
//
//        Connection con = conexao.abreConexao();
//
//        PreparedStatement pstmt = con.prepareStatement(sql);
//
//        try {            
//            pstmt.setString(1,  idContrato);
//            pstmt.executeUpdate();
//        } catch (SQLException ex) {
//            throw new SQLException(ex);
//        }finally{
//            conexao.fechaConexao(con,pstmt);
//        }
//        
//    }

}
